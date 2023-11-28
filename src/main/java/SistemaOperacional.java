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

public class SistemaOperacional {

    private GerenciadorDeMemoria gerenciadorDeMemoria;
    private Escalonador escalonadorCPU;
    private Escalonador escalonadorES;
    private List<Processo> processosFinalizados;
    private int tempoAtual;

    public SistemaOperacional(GerenciadorDeMemoria mem, Escalonador escalonadorCPU, Escalonador escalonadorES) {
        this.gerenciadorDeMemoria = mem;
        this.escalonadorCPU = escalonadorCPU;
        this.escalonadorES = escalonadorES;
        this.processosFinalizados = new LinkedList<>();
        this.tempoAtual = 0;

        Recurso recursoCPU = new Recurso("CPU");
        Recurso recursoES = new Recurso("dispositivo de E/S");

        escalonadorCPU.adicionar(recursoCPU);
        escalonadorES.adicionar(recursoES);
    }

    public SistemaOperacional(int capacidadeMemoria) {
        this(new GerenciadorDeMemoriaFragmentada(capacidadeMemoria), new EscalonadorRoundRobin(5), new EscalonadorSRTF());
    }

    public GerenciadorDeMemoria getGerenciadorDeMemoria() {
        return gerenciadorDeMemoria;
    }

    public Escalonador getEscalonadorCPU() {
        return escalonadorCPU;
    }

    public Escalonador getEscalonadorES() {
        return escalonadorES;
    }

    public List<Processo> getProcessosFinalizados() {
        return processosFinalizados;
    }

    public int getTempoAtual() {
        return tempoAtual;
    }

    public void adicionarProcesso(Processo processo) {
        if (processo == null) {
            throw new NullPointerException("Processo nulo");
        }

        try {
            gerenciadorDeMemoria.alocar(processo.getTamanho());
        } catch (IllegalArgumentException e) {
            System.out.println("Nao foi possivel adicionar processo " + processo.getNome() + " " + e.getMessage());
            return;
        }

        if (processo.getRequerCPU()) {
            escalonadorCPU.adicionar(new ParTempoProcesso(0, processo));
            System.out.println("Processo " + processo.getNome() + " adicionado");
        } else {
            escalonadorES.adicionar(new ParTempoProcesso(0, processo));
            System.out.println("Processo " + processo.getNome() + " enviado a CPU");
        }
    }

    public void executarPasso() {
        System.out.println("Processando tempo t=" + tempoAtual);

        ParTempoProcesso parCPU = escalonadorCPU.gerenciar();
        ParTempoProcesso parES = escalonadorES.gerenciar();

        processarParTempoProcesso(parCPU, escalonadorES, "CPU");
        processarParTempoProcesso(parES, escalonadorCPU, "dispositivo de E/S");

        tempoAtual++;

        System.out.println();
    }

    private void processarParTempoProcesso(ParTempoProcesso par, Escalonador outroEscalonador, String recurso) {
        if (par != null) {
            par.getProcesso().setTerminouCPU();
            if (par.getProcesso().getRequerES()) {
                outroEscalonador.adicionar(new ParTempoProcesso(0, par.getProcesso()));
                System.out.println("Processo " + par.getProcesso().getNome() + " enviado ao " + recurso);
            } else {
                System.out.println("Processo " + par.getProcesso().getNome() + " terminou de executar");
                processosFinalizados.add(par.getProcesso());
                gerenciadorDeMemoria.desalocar(par.getProcesso().getTamanho());
            }
        }
    }

    public void executar() {
        while (escalonadorCPU.haProcessos() || escalonadorES.haProcessos()) {
            executarPasso();
        }
        System.out.println("Nao ha mais processos para executar");
    }

    public void executar(LinkedList<ParTempoProcesso> listaProcessos) {
        listaProcessos.sort(null);
        while (escalonadorCPU.haProcessos() || escalonadorES.haProcessos() || !listaProcessos.isEmpty()) {
            while (!listaProcessos.isEmpty() && listaProcessos.peek().getTempo() == tempoAtual) {
                ParTempoProcesso par = listaProcessos.poll();
                adicionarProcesso(par.getProcesso());
            }
            executarPasso();
        }
        System.out.println("Nao ha mais processos para executar");
    }

    public void imprimirEstatisticas() {
        System.out.println("Tempo de execucao total: " + tempoAtual);

        double ociosidadeCPU = (double) escalonadorCPU.getTempoOcioso() / tempoAtual * 100;
        double ociosidadeES = (double) escalonadorES.getTempoO

