package Exercicio6;


import java.util.concurrent.CyclicBarrier;

public class ImplementsRunnableBarrier implements Runnable {

    private int contador, identificador;
    private static CyclicBarrier barrier;

    public ImplementsRunnableBarrier(int identificador, int contador, CyclicBarrier barrier) {
        this.identificador = identificador;
        this.contador = contador;
        this.barrier = barrier;
    }

    @Override
    public void run() {
        try {
            for (int i = contador; i > 0; i--) {
                System.out.println("Hilo número: " + identificador + " dice: Ola mundo");
                Thread.sleep(identificador * 1000);
            }
            System.out.println("Hilo Runnable " + identificador + " finalizó su acción. Hilo en espera... (esperando la barrera)");
            barrier.await();
            System.out.println("hilo Runnable " + identificador + " ha cruzado la barrera. FIN DEL HILO: " + identificador);
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }

}
