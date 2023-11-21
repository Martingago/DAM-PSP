package ejercicio4;

import java.util.concurrent.CyclicBarrier;

public class Carrera {

    //Se generan 2 objetos animales con la información de posicion para posteriormente compararlos:
    private static AnimalData tortugaInfo = new AnimalData("TORTUGA", 'T');
    private static AnimalData liebreInfo = new AnimalData("LIEBRE", 'L');
    //Si ganador es true, se terminan todos los hilos
    public Carrera() {
        //Se crea una barrera de 2 objetos, cada vez que un hilo dé una vuelta el barrier esperará
        //Una vez se complete el barrier y se avance, se producirá una comprobación:
        //      - Si alguno de los 2 elementos ha llegado a 70
        CyclicBarrier barrier = new CyclicBarrier(2);

        ThreadAnimales tortuga = new ThreadAnimales(tortugaInfo, EnumTipoAnimal.TORTUGA, barrier);
        ThreadAnimales liebre = new ThreadAnimales(liebreInfo, EnumTipoAnimal.LIEBRE, barrier);
        tortuga.start();
        liebre.start();

    }

    /**
     * Funcion que compara las posiciones de los 2 animales de la carrera
     * Si ambos llegan a 70 se produce un empate, si uno de ellos llega a 70 es el ganador
     * @return boolean
     * 
     * Esta función se llama tras completarse 1 thread
     */
    public static boolean comprobarGanadorCarrera() {
        int pT = tortugaInfo.getPosicionAnimal();
        int pL = liebreInfo.getPosicionAnimal();

        if (pT >= 70 && pL >= 70) {
            System.out.println("ES UN EMPATE!");
            return true;
        } else if (pT >= 70) {
            System.out.println(tortugaInfo.getNombreAnimal() + " GANA LA CARRERA");
            return true;
        } else if (pL >= 70) {
            System.out.println(liebreInfo.getNombreAnimal() + " GANA LA CARRERA");
            return true;
        } else {
            return false;
        }
    }

}
