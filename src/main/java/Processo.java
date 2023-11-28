/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author recab
 */
public class Processo extends ContadorDeTempo {
    private static int proximoId = 1;
    private int id;
    private String nome;
    private boolean cpuPrimeiro;
    private int tempoTotalCPU;
    private int tempoTotalES;
    private int memoriaNecessaria;
    private boolean terminouCPU;
    private boolean terminouES;

    public Processo(String nome, boolean cpuPrimeiro, int tempoTotalCPU, int tempoTotalES, int memoriaNecessaria) {
        this.id = proximoId();
        this.nome = nome;
        this.cpuPrimeiro = cpuPrimeiro;
        this.tempoTotalCPU = tempoTotalCPU;
        this.tempoTotalES = tempoTotalES;
        this.memoriaNecessaria = memoriaNecessaria;
        this.terminouCPU = false;
        this.terminouES = false;
    }

    public String getNome() {
        return nome;
    }

    public static int proximoId() {
        return proximoId++;
    }

    public int getId() {
        return id;
    }

    public boolean isCpuPrimeiro() {
        return cpuPrimeiro;
    }

    public int getTempoTotalCPU() {
        return tempoTotalCPU;
    }

    public int getTempoTotalES() {
        return tempoTotalES;
    }

    public int getMemoriaNecessaria() {
        return memoriaNecessaria;
    }

    public boolean isTerminouCPU() {
        return terminouCPU;
    }

    public void setTerminouCPU() {
        terminouCPU = true;
    }

    public boolean isTerminouES() {
        return terminouES;
    }

    public void setTerminouES() {
        terminouES = true;
    }

    @Override
    public String toString() {
        // Calcula a porcentagem de ociosidade (supondo que você tenha um método para isso)
        double porcentagemOcioso = calcularPorcentagemOcioso();

        // Formata a String de saída
        return String.format("Processo" + prrocesso + "nome" + nome + "id" + id + "Porcentagem Ocioso" + PorcentagemOcioso);
    }
}
