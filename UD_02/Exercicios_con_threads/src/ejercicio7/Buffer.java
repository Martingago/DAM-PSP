package ejercicio7;

import java.util.logging.Level;
import java.util.logging.Logger;


public class Buffer {

    private int[] buffer;
    private int siguiente;
    private boolean isEmpty;
    private boolean isFull;

    public Buffer(int size) {
        this.buffer = new int[size];
        this.siguiente = 0;
        this.isEmpty = true;
        this.isFull = false;
    }

    public synchronized int consumir() {
        while(this.isEmpty){
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println("Error de interrupción " + ex);
            }
        }
        siguiente--;
        this.isFull = false;
        if(siguiente == 0){
            this.isEmpty = true;
        }
        notifyAll();
        return this.buffer[this.siguiente];
    }

    public synchronized void consumir(int dato) {
        while(this.isFull){
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println("Error de interrupción " + ex);
            }
        }
        buffer[siguiente] = 10;
        siguiente++;
        this.isEmpty = false;
        //Si siguiente es del tamáño del buffer, quiere decir que éste está lleno
        if(siguiente == this.buffer.length){
            this.isFull = true;
        }
        notifyAll();
    }
}
