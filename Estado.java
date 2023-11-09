package quebraCabeca8Pedacos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Estado {
    int[][] tabuleiro;
    Estado pai;
    int movimento;
    int custo;

    public Estado(int[][] tabuleiro, Estado pai, int movimento, int custo) {
        this.tabuleiro = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.tabuleiro[i][j] = tabuleiro[i][j];
            }
        }
        this.pai = pai;
        this.movimento = movimento;
        this.custo = custo;
    }

    public int calcularHeuristica(int[][] objetivo) {
        int h = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] != objetivo[i][j]) {
                    h++;
                }
            }
        }
        return h;
    }
}