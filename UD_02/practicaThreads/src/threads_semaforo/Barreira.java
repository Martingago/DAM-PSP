package threads_semaforo;

import java.util.concurrent.Semaphore;

class Barreira {

    private int numFios;
    private int contador;
    private Semaphore mutex;
    private Semaphore barreira;

    public Barreira(int numFios) {
        this.numFios = numFios;
        this.contador = 0;
        this.mutex = new Semaphore(1);
        this.barreira = new Semaphore(0);
    }

    public void agardar() throws InterruptedException {
        mutex.acquire();
        contador++;
        mutex.release();
        if (contador == numFios) {
            System.out.println("Liberando barreira!");
            // Liberar todos los hilos en espera
            for (int i = 0; i < numFios; i++) {
                barreira.release();
            }
        }
        barreira.acquire(); //Agardar polos outros fíos
        mutex.acquire();
        contador--;
        System.out.println(contador);
        mutex.release();
        if (contador == 0) {
            System.out.println("Liberando ultimo fio");
            barreira.release(); //Liberar o último fío para reiniciar a barreira;

        }
        System.out.println("Fin del proceso");
    }
}
