
package Exercicio2;

import java.util.concurrent.atomic.AtomicInteger;


public class IncrementWithAtomicInteger implements Runnable {

    private static final AtomicInteger contadorAtomicInteger = new AtomicInteger(0);
    private int nombre;

    public IncrementWithAtomicInteger(int nombre) {
        this.nombre = nombre;
    }
    
    
    @Override
    public void run() {
        for(int i = 0; i< 5000; i++){
            
            contadorAtomicInteger.incrementAndGet();
            System.out.println("Proceso sincronizado AtomicInteger" + nombre + "| contador: " + contadorAtomicInteger);
        }
        
        
    }
    
}
