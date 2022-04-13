package backend;

import backend.enums.EstadoJuego;

import java.awt.*;
import java.util.ArrayList;

public class BuscaMinas {
    private int casillas;
    private int nroMinas;
    public char tableroReal[][];
    public char tableroMostrar[][];
    private ArrayList<Point> posMinas = new ArrayList<>();
    private boolean pisoMina;

    private int minasX[];
    private int minasY[];

    public BuscaMinas() {
        minasX = new int[]{0, 1, 2, 3, 3, 4, 6, 6, 7, 7};
        minasY = new int[]{0, 0, 1, 2, 4, 3, 2, 6, 3, 4};
        this.casillas = 9;
        this.nroMinas = 10;
        tableroMostrar = new char[casillas][casillas];
        tableroReal = new char[casillas][casillas];
        //ponerMinasEnTablero();
        ponerMinas();
        llenarTablero();
        crearTableroDeMuestra();
        this.pisoMina = false;
    }

    private void ponerMinas() {
        for (int i = 0; i < nroMinas; i++) {
            int a = minasX[i];
            int b = minasY[i];
            tableroReal[a][b] = '*';
        }
    }

    public boolean excavar(int x, int y) {
        boolean res = false;
        if (tableroMostrar[x][y] != '#') {
            return false;
        }
        if (x >= 0 && x < casillas && y >= 0 && y < casillas) {
            desbloquearCeros(x, y);
            if (tableroReal[x][y] == '*') {
                res = true;
                pisoMina = true;
                mostrarPosicionesDeMinas();
            }

        }

        return res;
    }

    private boolean verificarTableroSiYaEsGanador() {
        int nroDeCasiExcavadas = 0;
        int nroDeMinasCasillas = (casillas * casillas) - nroMinas;
        for (int i = 0; i < casillas; i++) {
            for (int j = 0; j < casillas; j++) {
                if (tableroMostrar[i][j] != '#' && tableroMostrar[i][j] != 'B') {
                    nroDeCasiExcavadas++;
                }
            }
        }
        if (nroDeCasiExcavadas == nroDeMinasCasillas) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Point> getPosMinas() {
        return posMinas;
    }


    public boolean marcarBandera(int x, int y) {
        boolean res = false;
        if (x >= 0 && x < casillas && y >= 0 && y < casillas && tableroMostrar[x][y] == '#') {
            tableroMostrar[x][y] = 'B';
            res = true;
        }
        return res;
    }


    public EstadoJuego obtenerEstado() {
        if (verificarTableroSiYaEsGanador()) {
            return EstadoJuego.Ganado;
        } else {
            if (pisoMina) {
                mostrarPosicionesDeMinas();
                return EstadoJuego.Perdido;
            } else {
                return EstadoJuego.EnJuego;
            }
        }
    }

    private void mostrarPosicionesDeMinas() {
        /**for (int i = 0; i < posMinas.size(); i++) {
         int x = posMinas.get(i).x;
         int y = posMinas.get(i).y;
         tableroMostrar[x][y] = '*';
         }
         */
        for (int i = 0; i < nroMinas; i++) {
            int a = minasX[i];
            int b = minasY[i];
            tableroMostrar[a][b] = '*';
        }
    }

    public String toString() {
        String res = "";
        for (int i = 0; i < casillas; i++) {
            for (int j = 0; j < casillas; j++) {
                res += tableroMostrar[i][j];
            }
            res += "\n";
        }
        return res;
    }

    private void crearTableroDeMuestra() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tableroMostrar[i][j] = '#';
            }
        }
    }

    public void ponerMinasEnTablero() {
        for (int i = 0; i < nroMinas; i++) {
            int x = (int) (Math.random() * casillas - 1);
            int y = (int) (Math.random() * casillas - 1);
            Point punto = new Point(x, y);
            if (noTieneMina(punto)) {
                posMinas.add(punto);
                tableroReal[x][y] = '*';
            } else {
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

    private void llenarTablero() {
        for (int i = 0; i < casillas; i++) {
            for (int j = 0; j < casillas; j++) {
                if (tableroReal[i][j] != '*') {
                    numerarTablero(i, j);
                }
            }
        }
    }

    private void numerarTablero(int x, int y) {
        int num = 0;
        if (x - 1 >= 0 && y - 1 >= 0) {
            if (tableroReal[x - 1][y - 1] == '*') {
                num++;
            }
        }
        if (x >= 0 && y - 1 >= 0) {
            if (tableroReal[x][y - 1] == '*') {
                num++;
            }
        }
        if (x + 1 < casillas && y - 1 >= 0) {
            if (tableroReal[x + 1][y - 1] == '*') {
                num++;
            }
        }
        if (x - 1 >= 0 && y >= 0) {
            if (tableroReal[x - 1][y] == '*') {
                num++;
            }
        }
        if (x + 1 < casillas && y >= 0) {
            if (tableroReal[x + 1][y] == '*') {
                num++;
            }
        }
        if (x - 1 >= 0 && y + 1 < casillas) {
            if (tableroReal[x - 1][y + 1] == '*') {
                num++;
            }
        }
        if (x >= 0 && y + 1 < casillas) {
            if (tableroReal[x][y + 1] == '*') {
                num++;
            }
        }
        if (x + 1 < casillas && y + 1 < casillas) {
            if (tableroReal[x + 1][y + 1] == '*') {
                num++;
            }
        }
        tableroReal[x][y] = Integer.toString(num).charAt(0);
    }

    private void desbloquearCeros(int x, int y) {
        if (tableroReal[x][y] == '0') {
            desbloquearCerosContiguos(x, y);
        } else {
            tableroMostrar[x][y] = tableroReal[x][y];
        }
    }

    private void desbloquearCerosContiguos(int x, int y) {
        if (x - 1 >= 0 && y - 1 >= 0 && tableroMostrar[x - 1][y - 1] == '#') {
            if (tableroReal[x - 1][y - 1] == '0') {
                tableroMostrar[x - 1][y - 1] = tableroReal[x - 1][y - 1];
                desbloquearCerosContiguos(x - 1, y - 1);
            } else {
                if (tableroReal[x - 1][y - 1] == '*') {
                } else {
                    tableroMostrar[x - 1][y - 1] = tableroReal[x - 1][y - 1];
                }
            }
        }
        if (y - 1 >= 0 && tableroMostrar[x][y - 1] == '#') {
            if (tableroReal[x][y - 1] == '0') {
                tableroMostrar[x][y - 1] = tableroReal[x][y - 1];
                desbloquearCerosContiguos(x, y - 1);
            } else {
                if (tableroReal[x][y - 1] == '*') {
                } else {
                    tableroMostrar[x][y - 1] = tableroReal[x][y - 1];
                }
            }
        }
        if (x + 1 < casillas && y - 1 >= 0 && tableroMostrar[x + 1][y - 1] == '#') {
            if (tableroReal[x + 1][y - 1] == '0') {
                tableroMostrar[x + 1][y - 1] = tableroReal[x + 1][y - 1];
                desbloquearCerosContiguos(x + 1, y - 1);
            } else {
                if (tableroReal[x + 1][y - 1] == '*') {
                } else {
                    tableroMostrar[x + 1][y - 1] = tableroReal[x + 1][y - 1];
                }
            }
        }
        if (x + 1 < casillas && tableroMostrar[x + 1][y] == '#') {
            if (tableroReal[x + 1][y] == '0') {
                tableroMostrar[x + 1][y] = tableroReal[x + 1][y];
                desbloquearCerosContiguos(x + 1, y);
            } else {
                if (tableroReal[x + 1][y] == '*') {
                } else {
                    tableroMostrar[x + 1][y] = tableroReal[x + 1][y];
                }
            }
        }
        if (x + 1 < casillas && y + 1 < casillas && tableroMostrar[x + 1][y + 1] == '#') {
            if (tableroReal[x + 1][y + 1] == '0') {
                tableroMostrar[x + 1][y + 1] = tableroReal[x + 1][y + 1];
                desbloquearCerosContiguos(x + 1, y + 1);
            } else {
                if (tableroReal[x + 1][y + 1] == '*') {

                } else {
                    tableroMostrar[x + 1][y + 1] = tableroReal[x + 1][y + 1];
                }
            }
        }
        if (y + 1 < casillas && tableroMostrar[x][y + 1] == '#') {
            if (tableroReal[x][y + 1] == '0') {
                tableroMostrar[x][y + 1] = tableroReal[x][y + 1];
                desbloquearCerosContiguos(x, y + 1);
            } else {
                if (tableroReal[x][y + 1] == '*') {
                } else {
                    tableroMostrar[x][y + 1] = tableroReal[x][y + 1];
                }
            }
        }

        if (x - 1 >= 0 && y + 1 < casillas && tableroMostrar[x - 1][y + 1] == '#') {
            if (tableroReal[x - 1][y + 1] == '0') {
                tableroMostrar[x - 1][y + 1] = tableroReal[x - 1][y + 1];
                desbloquearCerosContiguos(x - 1, y + 1);
            } else {
                if (tableroReal[x - 1][y + 1] == '*') {
                } else {
                    tableroMostrar[x - 1][y + 1] = tableroReal[x - 1][y + 1];
                }
            }
        }
        if (x - 1 >= 0 && tableroMostrar[x - 1][y] == '#') {
            if (tableroReal[x - 1][y] == '0') {
                tableroMostrar[x - 1][y] = tableroReal[x - 1][y];
                desbloquearCerosContiguos(x - 1, y);
            } else {
                if (tableroReal[x - 1][y] == '*') {
                } else {
                    tableroMostrar[x - 1][y] = tableroReal[x - 1][y];
                }
            }
        }
    }
}


