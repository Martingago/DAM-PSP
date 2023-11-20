
package Exercicio5;


public class CalculoMatrizThreadSecuencial implements Runnable {
    private int inicio;
    private int fin;
    private int[][] matrizA;
    private int[][] matrizB;
    private static long[][] resultadoCalc; //Matriz que será accesible para todos los threads
    
    
  public CalculoMatrizThreadSecuencial(){}; 
  
    /**
     * Objeto que inicializa los datos para realizar los calculos de las matrices desde un punto hasta otro
     * @param inicio fila de inicio en la que se realizarán los cálculos
     * @param fin fila de fin en la que se realizarán los cálculos
     * @param matrizA datos matriz A
     * @param matrizB datos matriz B
     */
    public CalculoMatrizThreadSecuencial(int inicio, int fin, int[][] matrizA, int[][] matrizB) {
        this.inicio = inicio;
        this.fin = fin;
        this.matrizA = matrizA;
        this.matrizB = matrizB;
    }

    @Override
    public void run() {
        for (int filas = inicio; filas < fin; filas++) {
            for (int columnas = 0; columnas < matrizB[0].length; columnas++) {
                for (int sumatorio = 0; sumatorio < matrizA[0].length; sumatorio++) {
                    
                        resultadoCalc[filas][columnas] += matrizA[filas][sumatorio] * matrizB[sumatorio][columnas];
                    }
                    
                }
            }
    }
    
    /**
     * Funcion que calcula las matrices empleando 4 hilos diferentes.
     * Para que los 4 hilos tengan una carga de trabajo similar lo que hago es asignarle a cada hilo 1/4 de las filas totales, para que en su total hagan 4/4 = 100% del trabajo
     * Esta es la distribución de los hilos:
     *      primer hilo hará la fila correspondiente a 1/4 (de 0 a 1/4)
     *      segundo hilo hará la fila correspondiente a 2/4 (desde 1/4 a 1/2 (hasta la mitad))
     *      tercer hilo hará la fracción correspondiente a  3/4 (desde la mitad 1/2  a 3/4 (hasta las 3/4 partes del final))
     *      cuarto hilo el último cuarto correspondiente 4/4 (desde las 3/4 hasta el fin 4/4 => 1/1 => 1)
     * @param matrizA
     * @param matrizB 
     * 
     * NOTA: Esta es la forma que se me ha ocurrido inicialmente, la ineficiencia que tiene es que para cada thread  generado en:
 "new CalculoMatrizThreadParalelo(int inicio, int fin, int[][]matrizA, int[][]matrizB)" se le pasa por cada hilo las matrices completas
 Realmente una forma mas eficiente de hacerlo sería fragmentar en primera instancia las matrices en el número de hilos necesarios,
 y modificar el código pasandole a cada hilo únicamente ese "fragmento de matriz". Habría que pasarle un índice global para que
 el hilo supiese en que parte del resultadoCalc debe colocar cada índice de "fragmento de matriz"
     * 
     * 
     */
    public  long[][] calcularMuplicacionMatrices(int[][] matrizA, int[][] matrizB) {
        int MA_filas = matrizA.length;
        int MA_columnas = matrizA[0].length;
        int MB_filas = matrizB.length;
        int MB_columnas = matrizB[0].length;
        
        resultadoCalc = new long[MA_filas][MB_columnas]; //se crea una matriz del tamaño del resultado
        if (MA_columnas == MB_filas) {
            try {
                System.out.println("Matrices válidas, realizando cálculo:");
                
                
                long startTime = System.currentTimeMillis();
                /**
                 * Para establecer los límites de cada hilo (desde que punto hasta que punto deben ejecutar los cálculos)
                 * se pueden emplear las fracciones citadas en la anterior explicación.
                 *      De forma que el primer hilo hará desde la fila 0 hasta la fila Ma_filas / 4 (1/4)
                 *      El segundo hilo lo hará desde la fila Ma_filas / 4 (1/4) hasta la mitad MA_filas / 2
                 *      El tercer hilo desde la mitad: MA_filas /2 hasta la fila 3/4: MA_filas * 3 / 4
                 *      Finalmente el último hilo desde la fila 3/4 : MA_filas * 3 / 4 hasta el final 1: MA_filas
                 */
                Thread t1 = new Thread(new CalculoMatrizThreadSecuencial(0, MA_filas / 4, matrizA, matrizB));
                Thread t2 = new Thread(new CalculoMatrizThreadSecuencial(MA_filas / 4, MA_filas / 2, matrizA, matrizB));
                Thread t3 = new Thread(new CalculoMatrizThreadSecuencial(MA_filas / 2, MA_filas * 3 / 4, matrizA, matrizB));
                Thread t4 = new Thread(new CalculoMatrizThreadSecuencial(MA_filas * 3 / 4, MA_filas, matrizA, matrizB));
                
                t1.start();
                t2.start();
                t3.start();
                t4.start();
                t1.join();
                t2.join();
                t3.join();
                t4.join();
                
                long endTime = System.currentTimeMillis();
                
                long resultado = endTime - startTime;
                System.out.println("El resultado de la operación ha sido de: " + resultado + "ms");
            } catch (InterruptedException ex) {
                System.out.println("Error de interrupcion" + ex);
            }
        } else {
            System.out.println("Las matrices no pueden ser multiplicadas");
        }
        return resultadoCalc;
    }
    
    
 


}


