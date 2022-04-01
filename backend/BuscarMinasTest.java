package backend;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuscarMinasTest {

    @Test
    public void verificarLLenadoDeMinas(){
        BuscarMinas buscaMinas= new BuscarMinas();
        char tabla[][]=buscaMinas.tablero;
        boolean res = false;
        int contador = 0;
        buscaMinas.ponerMinasEnTablero();
        for(int i = 0 ; i<9;i++){
            for(int j = 0 ; j < 9 ; j++){
                if(tabla[i][j]=='*'){
                    contador++;
                }
            }
        }
        if(contador == 10){
            res = true;
        }
        assertEquals(res,true);
    }

}