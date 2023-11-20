package ejercicio1;

public class ImplementsThread extends Thread {

    private int identificador, contador;

    public ImplementsThread(int identificador, int contador) {
        this.identificador = identificador;
        this.contador = contador;
    }

    @Override
    public void run() {
        try {
            for (int i = contador; i > 0; i--) {
                System.out.println("Thread: " + identificador + ": Ola mundo");
                Thread.sleep(identificador * 1000);
            }

        } catch (InterruptedException ex) {
            System.out.println("Error de interrupción de código: " + ex);
        }

    }
}
