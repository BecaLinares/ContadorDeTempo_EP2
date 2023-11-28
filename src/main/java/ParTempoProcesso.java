/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author recab
 */
public class ParTempoProcesso implements Comparable<ParTempoProcesso> {
    private int tempo;
    private Processo processo;

    public ParTempoProcesso(int tempo, Processo processo) {
        this.tempo = tempo;
        this.processo = processo;
    }

    public int getTempo() {
        return tempo;
    }

    public Processo getProcesso() {
        return processo;
    }

    public void reduzirTempo(int tempoReducao) {
        tempo -= tempoReducao;
    }

    @Override
    public int compareTo(ParTempoProcesso outroPar) {
        return this.getTempo() - outroPar.getTempo();
    }
}
