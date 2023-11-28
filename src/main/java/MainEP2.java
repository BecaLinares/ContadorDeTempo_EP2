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
import java.util.Scanner;

public class MainEP2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Configuração do primeiro sistema operacional
        SistemaOperacional sistema1 = configurarSistema(scanner, "primeiro");

        // Configuração do segundo sistema operacional
        SistemaOperacional sistema2 = configurarSistema(scanner, "segundo");

        // Criação de listas de processos equivalentes para ambos os sistemas
        LinkedList<ParTempoProcesso> listaProcessosSistema1 = criarListaProcessos(scanner);
        LinkedList<ParTempoProcesso> listaProcessosSistema2 = new LinkedList<>(listaProcessosSistema1);

        // Execução do primeiro sistema operacional
        System.out.println("************************************");
        System.out.println("* Executando primeira configuracao *");
        System.out.println("************************************");
        sistema1.executar(listaProcessosSistema1);

        // Execução do segundo sistema operacional
        System.out.println("************************************");
        System.out.println("* Executando segunda configuracao *");
        System.out.println("************************************");
        sistema2.executar(listaProcessosSistema2);

        // Exibição das estatísticas
        System.out.println("*****************************************");
        System.out.println("* Estatisticas da primeira configuracao *");
        System.out.println("*****************************************");
        sistema1.imprimirEstatisticas();

        System.out.println("****************************************");
        System.out.println("* Estatisticas da segunda configuracao *");
        System.out.println("****************************************");
        sistema2.imprimirEstatisticas();

        scanner.close();
    }

    private static SistemaOperacional configurarSistema(Scanner scanner, String nomeSistema) {
        System.out.println("************************************");
        System.out.println("* Simulador de Sistema Operacional *");
        System.out.println("************************************");
        System.out.println("Configuracao do " + nomeSistema + " sistema operacional:");

        // Tipo do escalonador de CPU
        System.out.println("Qual o tipo do escalonador de CPU?");
        Escalonador escalonadorCPU = obterEscalonador(scanner);

        // Tipo do escalonador de E/S
        System.out.println("Qual o tipo do escalonador de E/S?");
        Escalonador escalonadorES = obterEscalonador(scanner);

        // Tipo do gerenciador de memória
        System.out.println("Qual o tipo de gerenciador de memoria?");
        GerenciadorDeMemoria gerenciadorMemoria = obterGerenciadorMemoria(scanner);

        // Criação do sistema operacional
        return new SistemaOperacional(gerenciadorMemoria, escalonadorCPU, escalonadorES);
    }

    private static Escalonador obterEscalonador(Scanner scanner) {
        System.out.println("1) First In First Out");
        System.out.println("2) Round Robin");
        System.out.println("3) Shortest Remaining Time First");
        int escolha = scanner.nextInt();

        switch (escolha) {
            case 1:
                return new EscalonadorFifo();
            case 2:
                System.out.println("Digite o tamanho da fatia de tempo:");
                int fatiaDeTempo = scanner.nextInt();
                return new EscalonadorRoundRobin(fatiaDeTempo);
            case 3:
                return new EscalonadorSRTF();
            default:
                throw new IllegalArgumentException("Escolha inválida");
        }
    }

    private static GerenciadorDeMemoria obterGerenciadorMemoria(Scanner scanner) {
        System.out.println("1) Gerenciador tipo jarra");
        System.out.println("2) Gerenciador com fragmentos");
        int escolha = scanner.nextInt();

        switch (escolha) {
            case 1:
                return new GerenciadorDeMemoriaBalde();
            case 2:
                return new GerenciadorDeMemoriaFragmentada(100); // Capacidade de memória fixa em 100 para exemplo
            default:
                throw new IllegalArgumentException("Escolha inválida");
        }
    }

    private static LinkedList<ParTempoProcesso> criarListaProcessos(Scanner scanner) {
        LinkedList<ParTempoProcesso> listaProcessos = new LinkedList<>();
        boolean adicionarMaisProcessos = true;

        while (adicionarMaisProcessos) {
            System.out.println("Digite o nome do processo:");
            String nome = scanner.next();

            System.out.println("Digite o tempo de CPU:");
            int tempoCPU = scanner.nextInt();

            System.out.println("Digite o tempo de E/S:");
            int tempoES = scanner.nextInt();

            System.out.println("Digite a memoria necessaria:");
            int memoriaNecessaria = scanner.nextInt();

            System.out.println("O processo vai primeiro para a CPU? (true ou false)");
            boolean primeiroParaCPU = scanner.nextBoolean();

            System.out.println("Digite o momento no qual o processo deve iniciar a execucao:");
            int momentoInicio = scanner.nextInt();

            Processo processo = new Processo(nome, tempoCPU, tempoES, memoriaNecessaria, primeiroParaCPU);
            ParTempoProcesso parTempoProcesso = new ParTempoProcesso(momentoInicio, processo);
            listaProcessos.add(parTempoProcesso);

            System.out.println("Adicionar mais processos?");
            System.out.println("0) Nao");
            System.out.println("1) Sim");
            adicionarMaisProcessos = scanner.nextInt() == 1;
        }

        return listaProcessos;
    }
}
