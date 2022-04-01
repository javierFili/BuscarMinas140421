package backend;

import java.awt.*;
import java.util.ArrayList;

/**
 * Una mina estaria reprensetada por *
 * luego se prosigue con sus numeros del 0 al 8 que son la cantidad de minas que tiene a su alrededor
 *
 */
public class BuscarMinas {
    private int casillas;
    private int nroMinas;
    public char tablero[][];
    private Point posMina;
    private ArrayList<Point> posMinas;
    public BuscarMinas(){
        this.posMina = new Point();
        new ArrayList<Point>();
        tablero = new char [9][9];
    }
    public void ponerMinasEnTablero(){

    }
    private void numerarTablero(){

    }
    public boolean buscarMina(){
        return true;
    }

}
