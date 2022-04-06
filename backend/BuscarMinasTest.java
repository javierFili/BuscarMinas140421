package backend;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuscarMinasTest {

    @Test
    public void verificarLLenadoDeMinas() {
        BuscaMinas buscaMinas = new BuscaMinas();
        char tabla[][] = buscaMinas.tablero;
        boolean res = false;
        int contador = 0;
        buscaMinas.ponerMinasEnTablero();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (tabla[i][j] == '*') {
                    contador++;
                }
            }
        }
        if (contador == 10) {
            res = true;
        }
        assertEquals(res, true);
    }

    @Test
    //sale false por que no se a registrado la posicion 2,3 en el arrayList
    public void verificarXYsiHayMinaSi() {
        BuscaMinas buscaMinas = new BuscaMinas();
        buscaMinas.ponerMinasEnTablero();
        char tabla[][] = buscaMinas.tablero;
        tabla[2][3] = '*';
        boolean hayMina = buscaMinas.buscarMina(2, 3);
        assertEquals(hayMina,true);

    }

    @Test
    public void verificarXYsiHayMinaNo() {
        BuscaMinas buscaMinas = new BuscaMinas();
        buscaMinas.ponerMinasEnTablero();
        char tabla[][] = buscaMinas.tablero;
        tabla[3][3] = ' ';
        boolean hayMina = buscaMinas.buscarMina(3, 3);
        assertEquals(hayMina,false);
    }

}