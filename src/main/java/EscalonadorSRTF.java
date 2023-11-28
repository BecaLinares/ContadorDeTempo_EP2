/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author recab
 */
import java.util.Collections;

public class EscalonadorSRTF extends EscalonadorFifo {

    @Override
    public boolean adicionar(ParTempoProcesso par) {
        super.adicionar(par);
        Collections.sort(getProcessos());
        return true;
    }

    @Override
    public String toString() {
        return "Escalonador Shortest Remaining Time First";
    }

    public static void main(String[] args) {
        // Exemplo de uso
        EscalonadorSRTF escalonadorSRTF = new EscalonadorSRTF();
        Recurso recurso = new Recurso("CPU");

        escalonadorSRTF.adicionar(recurso);

        Processo processo1 = new Processo("Processo1");
        Processo processo2 = new Processo("Processo2");

        ParTempoProcesso par1 = new ParTempoProcesso(5, processo1);
        ParTempoProcesso par2 = new ParTempoProcesso(3, processo2);

        escalonadorSRTF.adicionar(par1);
        escalonadorSRTF.adicionar(par2);

        while (escalonadorSRTF.haProcessos()) {
            escalonadorSRTF.gerenciar();
        }
    }
}

