package ejercicio2;

public class SynchronizedWithThread extends Thread {

    private static int contadorSincronizado = 0;
    private int nombre;

    public SynchronizedWithThread(int nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Ejecución del método run, pero ésta vez con un "synchronized". 
     * para evitar que la salida del código sea diferente a la esperada
     * 
     * NOTA: inicialmente en el synchronized tenía puesto (this), pero según he visto seguía existiendo
     * desincronización puesto que (this) sicronizaba dentro de cada hilo, pero al ejecutar diferentes hilos el
     * contexto de la sincronización era individual.
     * Al sincronizar sobre la clase (SynchronizedWithThread.class) la instancia es global para todos los hilos
     * lo que permite la sincronización en todos los hilos que se ejecuten
     */
    
    @Override
    public void run() {
        super.run();
        synchronized (SynchronizedWithThread.class) {
            for (int i = 0; i < 5000; i++) {
                contadorSincronizado++;
                System.out.println("Proceso Thread sincronizado " + nombre + "| contador: " + contadorSincronizado);
            }
        }
    }

}
