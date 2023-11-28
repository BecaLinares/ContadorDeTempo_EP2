/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author recab
 */
import java.util.LinkedList;
import java.util.Collections;

public class GerenciadorDeMemoriaFragmentada extends GerenciadorDeMemoria {
    private LinkedList<Fragmento> fragmentosAlocados;

    public GerenciadorDeMemoriaFragmentada(int quantidadeTotal) {
        super(quantidadeTotal);
        fragmentosAlocados = new LinkedList<>();
    }

    @Override
    public void alocar(int quantidadePedida) {
        Fragmento novoFragmento = encontrarLacuna(quantidadePedida);
        fragmentosAlocados.add(novoFragmento);
        Collections.sort(fragmentosAlocados);
        atualizarAlocacaoMaxima();
    }

    @Override
    public void desalocar(int quantidadePedida) {
        Fragmento fragmentoDesalocado = encontrarFragmento(quantidadePedida);
        fragmentosAlocados.remove(fragmentoDesalocado);
        atualizarAlocacaoMaxima();
    }

    @Override
    public int getQuantidadeAlocada() {
        int totalAlocado = 0;
        for (Fragmento fragmento : fragmentosAlocados) {
            totalAlocado += fragmento.getTamanho();
        }
        return totalAlocado;
    }

    private Fragmento encontrarLacuna(int quantidadePedida) {
        if (quantidadePedida > getQuantidadeTotal()) {
            throw new IllegalArgumentException("Memória insuficiente");
        }

        if (fragmentosAlocados.isEmpty() || quantidadePedida <= fragmentosAlocados.getFirst().getInicio()) {
            return new Fragmento(0, quantidadePedida);
        }

        for (int i = 0; i < fragmentosAlocados.size() - 1; i++) {
            int lacuna = fragmentosAlocados.get(i + 1).getInicio() - fragmentosAlocados.get(i).getFim() - 1;
            if (lacuna >= quantidadePedida) {
                return new Fragmento(fragmentosAlocados.get(i).getFim() + 1, quantidadePedida);
            }
        }

        int lacunaFinal = getQuantidadeTotal() - fragmentosAlocados.getLast().getFim() - 1;
        if (lacunaFinal >= quantidadePedida) {
            return new Fragmento(fragmentosAlocados.getLast().getFim() + 1, quantidadePedida);
        }

        throw new IllegalArgumentException("Memória insuficiente");
    }

    private Fragmento encontrarFragmento(int tamanho) {
        for (Fragmento fragmento : fragmentosAlocados) {
            if (fragmento.getTamanho() == tamanho) {
                return fragmento;
            }
        }
        throw new IllegalArgumentException("Fragmento de memória de tamanho " + tamanho + " não encontrado");
    }

    private class Fragmento implements Comparable<Fragmento> {
        private int inicio;
        private int tamanho;

        public Fragmento(int inicio, int tamanho) {
            this.inicio = inicio;
            this.tamanho = tamanho;
        }

        public int getInicio() {
            return inicio;
        }

        public int getFim() {
            return inicio + tamanho - 1;
        }

        public int getTamanho() {
            return tamanho;
        }

        @Override
        public int compareTo(Fragmento outroFragmento) {
            return this.inicio - outroFragmento.inicio;
        }
    }
}

