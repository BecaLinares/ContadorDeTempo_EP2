/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author recab
 */
import java.util.LinkedList;
import java.util.List;

public abstract class Escalonador {
    private LinkedList<ParTempoProcesso> filaProcessos;
    private ParTempoProcesso parTempoProcessoAtivo;
    private Recurso recurso;
    private int contadorDeTempo;

    public Escalonador() {
        this.filaProcessos = new LinkedList<>();
        this.parTempoProcessoAtivo = null;
        this.recurso = null;
        this.contadorDeTempo = 0;
    }

    public void adicionar(Recurso recurso) {
        if (recurso == null) {
            throw new NullPointerException("Recurso nulo");
        }

        if (this.recurso != null) {
            throw new IllegalArgumentException("Recurso já adicionado");
        }

        this.recurso = recurso;
    }

    public void adicionar(int tempoRequerido, Processo processo) {
        ParTempoProcesso par = new ParTempoProcesso(tempoRequerido, processo);
        adicionar(par);
    }

    public abstract boolean adicionar(ParTempoProcesso par);

    protected abstract boolean verificarFim();

    protected int getContadorDeTempo() {
        return contadorDeTempo;
    }

    protected ParTempoProcesso getParTempoProcessoAtivo() {
        return parTempoProcessoAtivo;
    }

    public List<ParTempoProcesso> getProcessos() {
        return new LinkedList<>(filaProcessos);
    }

    protected Processo limparProcessoAtivo() {
        Processo processoTemp = parTempoProcessoAtivo.getProcesso();
        parTempoProcessoAtivo = null;
        return processoTemp;
    }

    public boolean haProcessos() {
        return parTempoProcessoAtivo != null || !filaProcessos.isEmpty();
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public Processo gerenciar() {
        if (parTempoProcessoAtivo == null) {
            parTempoProcessoAtivo = filaProcessos.poll();
            contadorDeTempo = 0;
        }

        if (parTempoProcessoAtivo == null) {
            return null;
        }

        if (contadorDeTempo > 0) {
            if (parTempoProcessoAtivo.getProcesso() != null) {
                parTempoProcessoAtivo.getProcesso().incrementarTempoAtivo();
            }
        }

        if (verificarFim()) {
            return limparProcessoAtivo();
        }

        if (parTempoProcessoAtivo.getProcesso() != null) {
            parTempoProcessoAtivo.getProcesso().incrementarTempoOcioso();
        }

        recurso.incrementarTempoOcioso();

        System.out.println("Recurso " + recurso.getNome() + " (tamanho da fila: " + filaProcessos.size() + ") processo " + parTempoProcessoAtivo.getProcesso().getNome() + " " + contadorDeTempo + " / " + parTempoProcessoAtivo.tempoRequerido());

        contadorDeTempo++;

        return null;
    }
}

