
package Exercicio5;

public class CalculoMatrizThread implements Runnable {
    private int inicio;
    private int fin;
    private int[][] matrizA;
    private int[][] matrizB;
    private long[][] resultadoOperacion;

    public CalculoMatrizThread(int inicio, int fin, int[][] matrizA, int[][] matrizB, long[][] resultadoOperacion) {
        this.inicio = inicio;
        this.fin = fin;
        this.matrizA = matrizA;
        this.matrizB = matrizB;
        this.resultadoOperacion = resultadoOperacion;
    }

    @Override
    public void run() {
        for (int filas = inicio; filas < fin; filas++) {
            for (int columnas = 0; columnas < matrizB[0].length; columnas++) {
                for (int sumatorio = 0; sumatorio < matrizA[0].length; sumatorio++) {
                    resultadoOperacion[filas][columnas] += matrizA[filas][sumatorio] * matrizB[sumatorio][columnas];
                }
            }
        }
    }

    public static void calcularMuplicacionMatrices(int[][] matrizA, int[][] matrizB) {
        int MA_filas = matrizA.length;
        int MA_columnas = matrizA[0].length;
        int MB_filas = matrizB.length;
        int MB_columnas = matrizB[0].length;
        if (MA_columnas == MB_filas) {
            System.out.println("Matrices válidas, realizando cálculo:");
            long[][] resultadoOperacion = new long[MA_filas][MB_columnas]; //se crea una matriz del tamaño del resultado

            long startTime = System.currentTimeMillis();

            Thread t1 = new Thread(new CalculoMatrizThread(0, MA_filas / 4, matrizA, matrizB, resultadoOperacion));
            Thread t2 = new Thread(new CalculoMatrizThread(MA_filas / 4, MA_filas / 2, matrizA, matrizB, resultadoOperacion));
            Thread t3 = new Thread(new CalculoMatrizThread(MA_filas / 2, MA_filas * 3 / 4, matrizA, matrizB, resultadoOperacion));
            Thread t4 = new Thread(new CalculoMatrizThread(MA_filas * 3 / 4, MA_filas, matrizA, matrizB, resultadoOperacion));

            t1.start();
            t2.start();
            t3.start();
            t4.start();

            try {
                t1.join();
                t2.join();
                t3.join();
                t4.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            long endTime = System.currentTimeMillis();

            System.out.println("Resultado:");
            printMatriz(resultadoOperacion);
            long resultado = endTime - startTime;
            System.out.println("El resultado de la operación ha sido de: " + resultado + "ms");

        } else {
            System.out.println("Las matrices no pueden ser multiplicadas");
        }
    }

    public static void printMatriz(long[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}


