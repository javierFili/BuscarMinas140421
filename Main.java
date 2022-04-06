import backend.BuscaMinas;

public class Main {
    public static void main(String args[]) {
        BuscaMinas buscaMinas = new BuscaMinas();
        buscaMinas.ponerMinasEnTablero();
        buscaMinas.mostrarMinas();
        buscaMinas.llenarTablero();
        buscaMinas.mostrar();
        boolean esMina = buscaMinas.buscarMina(1, 2);
        System.out.println("En la posicion "+ 1 +" , "+ 2 +" Piso a una mina:?="+esMina);
        boolean esMina1 = buscaMinas.buscarMina(2, 2);
        System.out.println("En la posicion "+ 2 +" , "+ 2 +" Piso a una mina:?="+esMina1);
        boolean esMina2= buscaMinas.buscarMina(4, 2);
        System.out.println("En la posicion "+ 4 +" , "+ 2 +" Piso a una mina:?="+esMina2);
    }
}
