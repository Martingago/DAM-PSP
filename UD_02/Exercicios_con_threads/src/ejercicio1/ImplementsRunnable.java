package ejercicio1;

public class ImplementsRunnable implements Runnable {

    private int contador, identificador;

    /**
     * Funcion que crea un hilo que dice "Ola mundo"
     * @param identificador número de hilo que está emitiendo el mensase
     * @param contador veces que el hilo emitirá el mensaje
     */
    public ImplementsRunnable(int identificador, int contador) {
        this.identificador = identificador;
        this.contador = contador;
    }

    @Override
    public void run() {
        try {
            for (int i = contador; i > 0; i--) {
                System.out.println("Runnable " + identificador + ": Ola mundo");
                Thread.sleep(identificador * 1000);
            }
        } catch (InterruptedException ex) {
            System.out.println("Error de interrupcion: " + ex);
        }
    }

}
