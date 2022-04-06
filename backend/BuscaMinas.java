package backend;

import java.awt.*;
import java.util.ArrayList;

/**
 * Una mina estaria reprensetada por *
 * luego se prosigue con sus numeros del 0 al 8 que son la cantidad de minas que tiene a su alrededor
 */
public class BuscaMinas {
    private int casillas;
    private int nroMinas;
    public char tablero[][];
    private ArrayList<Point> posMinas = new ArrayList<Point>();

    public BuscaMinas() {
        this.casillas = 9;
        this.nroMinas = 10;
        tablero = new char[casillas][casillas];
    }

    public void ponerMinasEnTablero() {
        int x = 0;
        int y = 0;
        for (int i = 0; i < nroMinas; i++) {
            x = (int) (Math.random() * nroMinas - 1);
            y = (int) (Math.random() * nroMinas - 1);
            Point punto = new Point(x, y);
            if (noTieneMina(punto)) {
                posMinas.add(punto);
                tablero[x][y] = '*';
            } else {
                //volver a generar el punto.
                //es decir que el j++, no debe ser
                i--;
            }


        }
    }

    private boolean noTieneMina(Point punto) {
        boolean res = true;
        for (int i = 0; i < posMinas.size(); i++) {
            if (punto.x == posMinas.get(i).x && punto.y == posMinas.get(i).y) {
                return false;
            }
        }
        return res;
    }

    //crear test para estos dos...provar que de xD
    public void llenarTablero() {
        for (int i = 0; i < casillas; i++) {
            for (int j = 0; j < casillas; j++) {
                if (tablero[i][j] != '*') {
                    numerarTablero(i, j);
                }
            }
        }
    }

    private void numerarTablero(int x, int y) {
        int num = 0;
        if (x - 1 >= 0 && y - 1 >= 0) {
            if (tablero[x - 1][y - 1] == '*') {
                num++;
            }
        }
        if (x >= 0 && y - 1 >= 0) {
            if (tablero[x][y - 1] == '*') {
                num++;
            }
        }
        if (x + 1 < casillas && y - 1 >= 0) {
            if (tablero[x + 1][y - 1] == '*') {
                num++;
            }
        }
        if (x - 1 >= 0 && y >= 0) {
            if (tablero[x - 1][y] == '*') {
                num++;
            }
        }
        if (x + 1 < casillas && y >= 0) {
            if (tablero[x + 1][y] == '*') {
                num++;
            }
        }
        if (x - 1 >= 0 && y + 1 < casillas) {
            if (tablero[x - 1][y + 1] == '*') {
                num++;
            }
        }
        if (x >= 0 && y + 1 < casillas) {
            if (tablero[x][y + 1] == '*') {
                num++;
            }
        }
        if (x + 1 < casillas && y + 1 < casillas) {
            if (tablero[x + 1][y + 1] == '*') {
                num++;
            }
        }
        tablero[x][y] = Integer.toString(num).charAt(0);
    }

    public void mostrarMinas() {
        for (int i = 0; i < posMinas.size(); i++) {
            int aa = posMinas.get(i).x;
            int bb = posMinas.get(i).y;
            System.out.println(aa + "  " + bb);
        }
    }

    public boolean buscarMina(int x, int y) {
        boolean res = false;
        if (!noTieneMina(new Point(x, y))) {
            res = true;
        }
        return res;
    }

    public void mostrar() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                // System.out.print(tablero[i][j] + "(" + i + "," + j + ")|");
                System.out.print(tablero[i][j] + "|");
            }
            System.out.println();
        }
    }

}
