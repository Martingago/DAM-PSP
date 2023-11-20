package ejercicio4;

import java.util.Random;

public class MovimientoAnimales {

    /**
     * Crea un numero pseudo-aleatorio entre 0 y 100
     *
     * @return int
     */
    public int generateRandomValue() {
        Random r = new Random();
        //al estar el 0 inlcuido tiene que tener valores de 0 a 99 (para obtener 100 resultados)
        return r.nextInt(100) + 1; //le añado 1 para que los valores sean entre 1 y 100
    }

    /**
     * Función que a través de un número pseudo-aleatorio realiza el movimiento
     * de la tortuga
     *
     * @return número de casillas que avanza o retrocede la tortuga;
     */
    public int movimientoTortuga() {
        int value = generateRandomValue();

//        if (value <= 50) {
//            return 3;
//        } else if (value > 50 && value <= 70) {
//            return -6;
//        } else if (value > 70 && value <= 100) {
//            return 1;
//        } else {
//            System.out.println("Error al obtener un valor en tortuga");
//            return -1;
//        }
    return 10;
    }

    /**
     * Funcion que a través de un numero pseudo-aleatorio realiza el movimiento
     * de la liebre
     *
     * @return número de casillas que avanza o retrocede la liebre;
     */
    public int movimientoLiebre() {
        int value = generateRandomValue();
//        if (value <= 20) {
//            return 0;
//        } else if (value > 20 && value <= 40) {
//            return 9;
//        } else if (value > 40 && value <= 50) {
//            return -12;
//        } else if (value > 50 && value <= 80) {
//            return 1;
//        } else if (value > 80 && value <= 100) {
//            return -2;
//        } else {
//            System.out.println("Error al obtener un valor en liebre");
//            return -1;
//        }
        return 10;
    }

}
