package backend;

import backend.enums.EstadoJuego;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class BuscarMinasTest {

    @Test
    public void verificarLLenadoDeMinas() {
        BuscaMinas buscaMinas = new BuscaMinas();
        boolean res = false;
        int contador = buscaMinas.getPosMinas().size();
        if (contador == 10) {
            res = true;
        }
        assertTrue(res);
    }

    @Test
    public void excavacionPisaMina() {
        BuscaMinas buscaMinas = new BuscaMinas();
        char tabla[][] = buscaMinas.tableroReal;
        boolean res;
        tabla[4][4] = '2';
        res = buscaMinas.excavar(4, 4);
        assertFalse(res);
    }

    @Test
    public void excavacionNoPisaMina() {
        BuscaMinas buscaMinas = new BuscaMinas();
        char tabla[][] = buscaMinas.tableroReal;
        boolean res = false;
        tabla[4][4] = '*';
        res = buscaMinas.excavar(4, 4);
        assertTrue(res);
    }

    @Test
    public void estadoJug1SoloSeAbreEsaPos() {
        BuscaMinas buscaMinas = new BuscaMinas();
        Random random = new Random();
        int fila = 4;
        int columna = 4;
        boolean perteneceMinas = false;
        while (true) {
            for (int i = 0; i < buscaMinas.getPosMinas().size() && !perteneceMinas; i++) {
                if (buscaMinas.getPosMinas().get(i).x == fila && buscaMinas.getPosMinas().get(i).y == columna) {
                    perteneceMinas = true;
                }
            }
            if (perteneceMinas) {
                perteneceMinas = false;
                fila = random.nextInt(9);
                columna = random.nextInt(9);
            } else {
                break;
            }
        }
        buscaMinas.excavar(fila, columna);
        char posSinMina = buscaMinas.tableroMostrar[fila][columna];
        boolean res = false;
        if (posSinMina != '*' && posSinMina != '#') {
            res = true;
        }
        assertTrue(res);
    }

    @Test
    public void estadoJug2SeAbrenVariasPos() {

    }

    @Test
    public void estadoJug3PonerBanderaConBandera() {
        BuscaMinas buscaMinas = new BuscaMinas();
        buscaMinas.marcarBandera(3, 3);
        char res = 'B';
        char posMina = buscaMinas.tableroMostrar[3][3];
        assertEquals(res, posMina);
    }

    @Test
    public void estadoJug3PonerBanderaSinBandera() {
        BuscaMinas buscaMinas = new BuscaMinas();
        char res = '#';
        char posMina = buscaMinas.tableroMostrar[3][3];
        assertEquals(res, posMina);
    }

    @Test
    public void estadoJug4PisarBomba() {
        BuscaMinas buscaMinas = new BuscaMinas();
        Point posMina = buscaMinas.getPosMinas().get(2);
        int x = posMina.x;
        int y = posMina.y;
        boolean res = buscaMinas.excavar(x, y);
        assertEquals(res, true);
    }

    @Test
    public void estadoJug5Ganador() {
        BuscaMinas buscaMinas = new BuscaMinas();
        Random random = new Random();
        EstadoJuego aux = EstadoJuego.EnJuego;
        boolean terminado = false;
        boolean perteneceMinas = false;
        int fila = 0;
        int columna = 0;
        while (!terminado) {
            fila = random.nextInt(9);
            columna = random.nextInt(9);
            while (true) {
                for (int i = 0; i < buscaMinas.getPosMinas().size() && !perteneceMinas; i++) {
                    if (buscaMinas.getPosMinas().get(i).x == fila && buscaMinas.getPosMinas().get(i).y == columna) {
                        perteneceMinas = true;
                    }
                }
                if (perteneceMinas) {
                    perteneceMinas = false;
                    fila = random.nextInt(9);
                    columna = random.nextInt(9);
                } else {
                    break;
                }
            }
            buscaMinas.excavar(fila, columna);
            aux = buscaMinas.obtenerEstado();
            terminado = (aux == EstadoJuego.EnJuego) ? false : true;
        }
        assertEquals(EstadoJuego.Ganado, aux);
    }


}