package Exercicio2;

public class IncrementWithThread extends Thread {

    private static  int contador = 0;
    private int nombre;

    public IncrementWithThread(int nombre) {
    this.nombre = nombre;
    }
    
    /**
     * Ejecuta el método run pero no existe ningún tipo de sincronización
     * El resultado puede no ser el esperado en caso de que varios hilos accedan a la variable contador
     */
    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            contador++;
            System.out.println("Proceso " + nombre + "| contador: " + contador);
        }
    }
}
