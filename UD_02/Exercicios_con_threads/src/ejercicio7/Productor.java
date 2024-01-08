package ejercicio7;

import java.util.Random;

public class Productor extends Thread {

    private Buffer buffer;
    private int numero;

    public Productor(Buffer b) {
        this.buffer = b;
    }

    public void run() {
        while (true) {
            try {
                numero = (int) (Math.random() * 30);
                System.out.println("Depositado y producido " + numero);
                buffer.producir(numero);
                sleep((int) (Math.random() * 2000));
            } catch (InterruptedException ex) {
                System.out.println("Error de interrupción del sistema " + ex);
            }
        }
    }

}
