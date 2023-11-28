/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author recab
 */
import java.util.LinkedList;

public class EscalonadorFifo extends Escalonador {

    @Override
    public boolean adicionar(ParTempoProcesso par) {
        if (par.getTempoProcessoAtivo() == 0) {
            throw new IllegalArgumentException("Tempo requerido zero");
        }

        getProcessos().add(par);
        return true;
    }

    @Override
    public boolean verificarFim() {
        return getContadorDeTempo() == getParTempoProcessoAtivo().getTempoProcessoAtivo();
    }

    @Override
    public String toString() {
        return "Escalonador First In First Out";
    }

    public static void main(String[] args) {
        // Exemplo de uso
        EscalonadorFIFO escalonadorFIFO = new EscalonadorFIFO();
        Recurso recurso = new Recurso("CPU");

        escalonadorFIFO.adicionar(recurso);

        Processo processo1 = new Processo("Processo1");
        Processo processo2 = new Processo("Processo2");

        ParTempoProcesso par1 = new ParTempoProcesso(5, processo1);
        ParTempoProcesso par2 = new ParTempoProcesso(3, processo2);

        escalonadorFIFO.adicionar(par1);
        escalonadorFIFO.adicionar(par2);

        while (escalonadorFIFO.haProcessos()) {
            escalonadorFIFO.gerenciar();
        }
    }
}

