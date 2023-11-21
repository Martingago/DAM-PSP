package ejercicio7;


public class Consumidor extends Thread {

    private Buffer buffer;
    
    public Consumidor(Buffer b){
        this.buffer = b;
    }
    
    public void run(){
    while(true){
        try {
            int consumir = this.buffer.consumir();
            System.out.println("Consumido el valor " + consumir + " del buffer");
            sleep((int) (Math.random() * 2000));
        } catch (InterruptedException ex) {
            System.out.println("Error de interrupción " + ex);
        }
    }
    
    
    }
    
}
