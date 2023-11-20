package ejercicio4_test;

public class Carrera {

    private static final int META = 70;
    private static final Object lock = new Object();

    private static int resultadoHilo1 = 0;
    private static int resultadoHilo2 = 0;
    private static int countHilo1 = 0;
    private static int countHilo2 = 0;
    private static boolean empate = false;

    public static void main(String[] args) {
        Thread hilo1 = new Thread(() -> correr("Hilo 1"));
        Thread hilo2 = new Thread(() -> correr("Hilo 2"));

        hilo1.start();
        hilo2.start();

        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (lock) {
            if (resultadoHilo2 >= META && resultadoHilo1 >= META && countHilo1 == countHilo2) {
                empate = true;
            }
        }

        if (empate) {
            System.out.println("¡Empate!");
        } else if (resultadoHilo1 >= META) {
            System.out.println("¡Hilo 1 ha ganado! con: " + resultadoHilo1);
        } else {
            System.out.println("¡Hilo 2 ha ganado! con: " + resultadoHilo2);
        }
    }

    private static void correr(String nombreHilo) {
        do {
            //int valorAleatorio = (int) (Math.random() * 6);
             int valorAleatorio = 10;
            synchronized (lock) {
                if (nombreHilo.equals("Hilo 1")) {
                    resultadoHilo1 += valorAleatorio;
                    countHilo1++;
                } else {
                    resultadoHilo2 += valorAleatorio;
                    countHilo2++;
                }
                System.out.println(
                        nombreHilo + ": " + resultadoHilo1 + " (" + countHilo1 + ") - Hilo 2: " + resultadoHilo2 + " (" + countHilo2 + ")");

                try {
                    lock.notify(); // Despierta al otro hilo
                    if (resultadoHilo1 < META || resultadoHilo2 < META) {
                        lock.wait(); // Espera a que el otro hilo termine su movimiento
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }while (resultadoHilo1 < META && resultadoHilo2 < META);

        synchronized (lock) {
            lock.notify(); // Despierta al otro hilo en caso de que esté esperando
        }
    }
}
