/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author recab
 */
import java.util.LinkedList;

public class EscalonadorRoundRobin extends EscalonadorFifo {
    private int fatiaDeTempo;

    public EscalonadorRoundRobin(int fatiaDeTempo) {
        super();
        this.fatiaDeTempo = fatiaDeTempo;
    }

    public int getFatiaDeTempo() {
        return fatiaDeTempo;
    }

    @Override
    protected boolean verificarFim() {
        if (super.verificarFim()) {
            return true;
        }

        if (getContadorDeTempo() % fatiaDeTempo == 0) {
            System.out.println("Fim da fatia de tempo do processo " + getParTempoProcessoAtivo().getProcesso().getNome());
            getParTempoProcessoAtivo().reduzirTempo(fatiaDeTempo);
            getProcessos().add(getParTempoProcessoAtivo());
            limparProcessoAtivo();
            return false;
        }

        return false;
    }

    @Override
    public String toString() {
        return "Escalonador Round Robin";
    }

    public static void main(String[] args) {
        // Exemplo de uso
        EscalonadorRoundRobin escalonadorRR = new EscalonadorRoundRobin(3);
        Recurso recurso = new Recurso("CPU");

        escalonadorRR.adicionar(recurso);

        Processo processo1 = new Processo("Processo1");
        Processo processo2 = new Processo("Processo2");

        ParTempoProcesso par1 = new ParTempoProcesso(5, processo1);
        ParTempoProcesso par2 = new ParTempoProcesso(4, processo2);

        escalonadorRR.adicionar(par1);
        escalonadorRR.adicionar(par2);

        while (escalonadorRR.haProcessos()) {
            escalonadorRR.gerenciar();
        }
    }
}

