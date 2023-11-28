/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author recab
 */
public class GerenciadorDeMemoriaBalde extends GerenciadorDeMemoria {

    public GerenciadorDeMemoriaBalde(int quantidadeTotal) {
        super(quantidadeTotal);
    }

    @Override
    public void alocar(int quantidadePedida) {
        if (quantidadePedida <= getQuantidadeDisponivel()) {
            quantidadeAlocada += quantidadePedida;
            atualizarAlocacaoMaxima();
        } else {
            throw new IllegalArgumentException("Memoria insuficiente");
        }
    }

    @Override
    public void desalocar(int quantidadePedida) {
        if (quantidadePedida <= quantidadeAlocada) {
            quantidadeAlocada -= quantidadePedida;
        } else {
            throw new IllegalArgumentException("Quantidade invalida");
        }
    }

    @Override
    public int getQuantidadeAlocada() {
        return quantidadeAlocada;
    }
}
