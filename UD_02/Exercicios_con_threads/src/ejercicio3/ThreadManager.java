package ejercicio3;

import static ejercicio3.ThreadReadData.*;

public class ThreadManager {

    /**
     * Función que ejecuta los threads que obtienen información de los ficheros
     * de forma paralela
     */
    public void ejecutarThreadsParalelos(String[] args) {

        Thread[] threads = new Thread[args.length]; //creo un array para almacenar todos los hilos

        for (int i = 0; i < args.length; i++) {
            /**
             * Por cada arg pasado se crea un nuevo hilo, el cual se encuentra
             * sincronizado con los demás lo que hace que cuando se modifican
             * las variables globales (total_caracteres, total_palabras,
             * total_lineas) se eviten errores en la lectura y sobreescritura de
             * los datos
             */
            threads[i] = new Thread(new ThreadReadData(args[i])); //Se crea un hilo dentro del array de threads
            threads[i].start(); //se inicia el thread creado
            
        }
        //con todos los hilos iniciados esperamos a que todos ellos terminen para que el programa se siga ejecutando
        for (int j = 0; j < threads.length; j++) {
            try {
                threads[j].join();
            } catch (InterruptedException ex) {
                System.out.println("Error de interrupción de datos: " + ex);
            }
        }

        showResult();
    }

    public void ejecutarThreadsSecuenciales(String[] args) {
        for (int i = 0; i < args.length; i++) {
            /**
             * Por cada arg pasado se crea un nuevo hilo, el cual se encuentra
             * sincronizado con los demás lo que hace que cuando se modifican
             * las variables globales (total_caracteres, total_palabras,
             * total_lineas) se eviten errores en la lectura y sobreescritura de
             * los datos
             */
            try {
                Thread t = new Thread(new ThreadReadData(args[i]));
                t.start();
                t.join();
            } catch (InterruptedException ex) {
                System.out.println("Se ha producido una interrupción al leer los datos" + ex);
            }
        }
        showResult();
    }

    public void showResult() {
        System.out.println("El total de caracteres es de:" + total_caracteres);
        System.out.println("El total de palabras es de:" + total_palabras);
        System.out.println("El total de líneas es de:" + total_lineas);
    }

}
