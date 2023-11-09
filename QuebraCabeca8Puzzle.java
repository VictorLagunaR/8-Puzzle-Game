package quebraCabeca8Pedacos;

import java.util.*;

public class QuebraCabeca8Puzzle {
	
	public static int[][] gerarEstadoInicialAleatorio() {
        int[][] estadoInicial = new int[3][3];
        ArrayList<Integer> numeros = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            numeros.add(i);
        }

        do {
            Collections.shuffle(numeros);

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    estadoInicial[i][j] = numeros.get(3 * i + j);
                    System.out.print("{" + estadoInicial[i][j] + "}");
                }
                System.out.println();
            }
        } while (!isSolucionavel(estadoInicial));

        return estadoInicial;
    }
    
    public static boolean isSolucionavel(int[][] estado) {
    	int inversionCount = 0;
        int[] flattenedState = new int[9];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                flattenedState[3 * i + j] = estado[i][j];
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (flattenedState[i] != 0 && flattenedState[j] != 0 && flattenedState[i] > flattenedState[j]) {
                    inversionCount++;
                }
            }
        }

        return inversionCount % 2 == 0;
    }

    public static Estado buscarSolucao(int[][] estadoInicial, int[][] estadoFinal) {
    	PriorityQueue<Estado> filaPrioridade = new PriorityQueue<>(Comparator.comparingInt(e -> e.custo));
        Map<String, Integer> custoMap = new HashMap<>();
        
        Estado inicial = new Estado(estadoInicial, null, 0, 0);
        filaPrioridade.add(inicial);
        custoMap.put(Arrays.deepToString(inicial.tabuleiro), 0);

        while (!filaPrioridade.isEmpty()) {
            Estado estadoAtual = filaPrioridade.poll();

            if (Arrays.deepEquals(estadoAtual.tabuleiro, estadoFinal)) {
                return estadoAtual;
            }

            int iZero = 0;
            int jZero = 0;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (estadoAtual.tabuleiro[i][j] == 0) {
                        iZero = i;
                        jZero = j;
                        break;
                    }
                }
            }

            int[] movI = {1, -1, 0, 0};
            int[] movJ = {0, 0, 1, -1};

            for (int k = 0; k < 4; k++) {
                int novoI = iZero + movI[k];
                int novoJ = jZero + movJ[k];

                if (novoI >= 0 && novoI < 3 && novoJ >= 0 && novoJ < 3) {
                    int[][] novoTabuleiro = new int[3][3];
//                    System.out.println("Novo Tabuleiro:");
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            novoTabuleiro[i][j] = estadoAtual.tabuleiro[i][j];
//                            System.out.print("{" + novoTabuleiro[i][j] + "}, ");
                        }
//                        System.out.println();
                    }

                    novoTabuleiro[iZero][jZero] = novoTabuleiro[novoI][novoJ];
                    novoTabuleiro[novoI][novoJ] = 0;

                    Estado novoEstado = new Estado(novoTabuleiro, estadoAtual, estadoAtual.movimento + 1, 0);
                    String novoEstadoString = Arrays.deepToString(novoEstado.tabuleiro);

                    if (!custoMap.containsKey(novoEstadoString) || novoEstado.custo < custoMap.get(novoEstadoString)) {
                        custoMap.put(novoEstadoString, novoEstado.custo);
                        novoEstado.custo = novoEstado.custo + novoEstado.calcularHeuristica(estadoFinal);
                        filaPrioridade.add(novoEstado);
                    }
                }
            }
        }

        return null; // Não há solução
    }

    
}