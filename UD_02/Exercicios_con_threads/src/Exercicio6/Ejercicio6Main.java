package Exercicio6;

import Exercicio1.*;
import java.util.concurrent.CyclicBarrier;

public class Ejercicio6Main {

    public static void main(String[] args) {

        final int NUM_THREADS = 4;

//Crea los 4 hilos sin emplear la barrera
//        for (int hilo = 1; hilo <= NUM_THREADS; hilo++) {
//            Thread t = new Thread(new ImplementsRunnable(hilo, 6));
//            t.start();
//        }
        CyclicBarrier barrier = new CyclicBarrier(NUM_THREADS);

        for (int i = 1; i <= NUM_THREADS; i++) {
            new Thread(new ImplementsRunnableBarrier(i, NUM_THREADS, barrier)).start();
        }

    }

}
