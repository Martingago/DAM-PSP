package ejercicio2;

public class MainEjercicio2 {

    public static void main(String[] args) throws InterruptedException {
        int hilos = 4;

        System.out.println("Resulado esperado: " + hilos * 5000
                + "\n --------------------------------------------");

        /**
         * Lanza "N" hilos sin sincronizar. El resultado de la operación puede
         * variar dependiendo de las características del ordenador En mi caso he
         * necesitado +8 hilos para ver alteraciones en el resultado final con
         * respecto a lo esperado.
         */
        for (int i = 0; i < hilos; i++) {
            Thread t1 = new Thread(new IncrementWithThread(i));
            t1.start();
        }

        /**
         * Lanza "N" hilos sincronizados con la clase "SynchronizedWithThread".
         * El resultado de la operación debe ser SIEMPRE el esperado, puesto que
         * TODOS los hilos se encuentran syncronizados con su clase
         */
        
        for (int i = 0; i < hilos; i++) {
            Thread t2 = new Thread(new SynchronizedWithThread(i));
            t2.start();
        }
        
         /**
         * Lanza "N" hilos sincronizados con la clase "SynchronizedWithInterfaceRunnable".
         * El resultado de la operación debe ser SIEMPRE el esperado, puesto que
         * TODOS los hilos se encuentran syncronizados con su clase
         */
         
        for (int i = 0; i < hilos; i++) {
            Thread t2 = new Thread(new SynchronizedWithInterfaceRunnable(i));
            t2.start();
        }
        
        /**
         * Lanza "N" hilos que utilizan un AtomicInteger para modificar la variable de un entero de forma segura
         * de manera que el resultado final va a ser el resultado esperado
         * A diferencia de emplear synchronized, los atomicInteger no necesitan realizar bloqueos
         */
        for(int i = 0; i< hilos; i++){
            Thread t3 = new Thread(new IncrementWithAtomicInteger(i));
            t3.start();
        }

    }

}
