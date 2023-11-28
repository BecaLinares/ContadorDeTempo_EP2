/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author recab
 */
public abstract class GerenciadorDeMemoria {
    private int quantidadeTotal;
    public int quantidadeAlocada;
    private double alocacaoMaxima;

    public GerenciadorDeMemoria(int quantidadeTotal) {
        this.quantidadeTotal = quantidadeTotal;
        this.quantidadeAlocada = 0;
        this.alocacaoMaxima = 0.0;
    }

    public int getQuantidadeTotal() {
        return quantidadeTotal;
    }

    public int getQuantidadeDisponivel() {
        return getQuantidadeTotal() - getQuantidadeAlocada();
    }

    public abstract void alocar(int quantidadePedida);

    public abstract void desalocar(int quantidadePedida);

    public abstract int getQuantidadeAlocada();

    public double getAlocacaoAtual() {
        return 100.0 * getQuantidadeAlocada() / getQuantidadeTotal();
    }

    public double getAlocacaoMaxima() {
        return alocacaoMaxima;
    }

    protected void atualizarAlocacaoMaxima() {
        alocacaoMaxima = Math.max(alocacaoMaxima, getAlocacaoAtual());
    }
}

