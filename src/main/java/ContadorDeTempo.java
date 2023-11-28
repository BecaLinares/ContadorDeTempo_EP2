/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */



/**
 *
 * @author recab
 */
public class ContadorDeTempo {
    private int tempoAtivo;
    private int tempoOcioso;

    public void incrementarTempoAtivo() {
        tempoAtivo++;
    }

    public void incrementarTempoOcioso() {
        tempoOcioso++;
    }

    public int getTempoAtivo() {
        return tempoAtivo;
    }

    public int getTempoOcioso() {
        return tempoOcioso;
    }

    public double getPorcentagemOcioso() {
        if (tempoAtivo == 0 && tempoOcioso == 0) {
            return 0.0; // Evita divisão por zero
        }

        return (double) tempoOcioso / (tempoAtivo + tempoOcioso) * 100.0;
    }

    public static void main(String[] args) {
        ContadorDeTempo contador = new ContadorDeTempo();

        // Exemplo de uso
        contador.incrementarTempoAtivo();
        contador.incrementarTempoOcioso();
        contador.incrementarTempoAtivo();

        System.out.println("Tempo Ativo: " + contador.getTempoAtivo());
        System.out.println("Tempo Ocioso: " + contador.getTempoOcioso());
        System.out.println("Porcentagem Ocioso: " + contador.getPorcentagemOcioso() + "%");
    }
}
