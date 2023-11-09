package Exercicio2;

public class SynchronizedWithInterfaceRunnable implements Runnable {

    private static int contadorSincronizadoRunnable = 0;
    private int nombre;

    public SynchronizedWithInterfaceRunnable(int nombre) {
        this.nombre = nombre;
    }

    /**
     * Ejecución del método run con la implementación de Runnable, pero ésta vez con un "synchronized". 
     * para evitar que la salida del código sea diferente a la esperada
     *
     * NOTA: inicialmente en el synchronized tenía puesto (this), pero según he
     * visto seguía existiendo desincronización puesto que (this) sicronizaba
     * dentro de cada hilo, pero al ejecutar diferentes hilos el contexto de la
     * sincronización era individual. Al sincronizar sobre la clase
     * (SynchronizedWithInterfaceRunnable.class) la instancia es global para todos los
     * hilos lo que permite la sincronización en todos los hilos que se ejecuten
     */
    @Override
    public void run() {
        synchronized (SynchronizedWithInterfaceRunnable.class) {
            for (int i = 0; i < 5000; i++) {
                contadorSincronizadoRunnable++;
                System.out.println("Proceso Runnable sincronizado " + nombre + "| contador: " + contadorSincronizadoRunnable);
            }
        }

    }

}
