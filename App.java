package quebraCabeca8Pedacos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {

	public static void main(String[] args) {
		QuebraCabeca8Puzzle puzzle = new QuebraCabeca8Puzzle();
//        int[][] estadoInicial = puzzle.gerarEstadoInicialAleatorio();
		int[][] estadoInicial =  {
	            {1, 8, 3},
	            {0, 6, 4},
	            {7, 2, 5}
	        };

        int[][] estadoFinal = {
            {1, 2, 3},
            {8, 0, 4},
            {7, 6, 5}
        };
        

        Estado solucao = puzzle.buscarSolucao(estadoInicial, estadoFinal);
        if (solucao == null) {
            System.out.println("Não há solução.");
        } else {
            System.out.println("Solução encontrada em " + solucao.movimento + " movimentos.");
            imprimirSolucao(solucao);
        }
    }
	
	public static void imprimirSolucao(Estado solucao) {
		ArrayList<Estado> caminho = new ArrayList<>();
        Estado estadoAtual = solucao;

        while (estadoAtual != null) {
            caminho.add(estadoAtual);
            estadoAtual = estadoAtual.pai;
        }

        for (int i = caminho.size() - 1; i >= 0; i--) {
            System.out.println("Movimento " + (caminho.size() - i - 1) + ":");
            imprimirTabuleiro(caminho.get(i).tabuleiro);
        }
    }

    public static void imprimirTabuleiro(int[][] tabuleiro) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
