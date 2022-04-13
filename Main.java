import backend.BuscaMinas;
import backend.enums.EstadoJuego;

public class Main {
    public static void main(String[] args) {
        int[] posX = new int[]{1, 8, 0, 6};
        int[] posY = new int[]{1, 0, 0, 2};
        BuscaMinas buscaMinas = new BuscaMinas();
        boolean terminado = false;
        int escenario = 0;
        while (!terminado && escenario < posX.length) {
            if (escenario == 2) {
                buscaMinas.marcarBandera(posX[escenario], posY[escenario]);
            } else {
                buscaMinas.excavar(posX[escenario], posY[escenario]);
            }
            EstadoJuego estado = buscaMinas.obtenerEstado();
            terminado = (estado == EstadoJuego.EnJuego) ? false : true;
            System.out.println(buscaMinas.toString());
            escenario++;
        }
        buscaMinas.obtenerEstado();
        System.out.println("El juego termino en: " + buscaMinas.obtenerEstado());
    }
}

