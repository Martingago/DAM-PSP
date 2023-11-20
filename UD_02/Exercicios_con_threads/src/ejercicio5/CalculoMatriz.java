package ejercicio5;

public class CalculoMatriz {

    /**
     * Funcion que realiza los cálculos de la multiplicación entre 2 matrices
     * Información de matrices y cómo realizar sus cálculos
     * https://www.youtube.com/watch?v=e8dxVu1hQN8
     *
     * @param matrizA
     * @param matrizB
     * @return resultado de los calculos
     */
    public long[][] calcularMuplicacionMatrices(int[][] matrizA, int[][] matrizB) {
        /**
         *
         * Para calcular matrices primero deberemos comprobar si se pueden
         * multiplicar: El número de columnas de la primera metriz debe ser
         * igual al número de filas de la segunda matriz La matriz resultante
         * será el número de filas de la primera y las columnas de la segunda.
         */
        int MA_filas = matrizA.length;
        int MA_columnas = matrizA[0].length;
        int MB_filas = matrizB.length;
        int MB_columnas = matrizB[0].length;
        
        long[][] resultadoOperacion = new long[MA_filas][MB_columnas]; //se crea una matriz del tamaño del resultado
        if (MA_columnas == MB_filas) {
            System.out.println("Matrices válidas, realizando cálculo:");
            
            
            long startTime = System.currentTimeMillis();
            
            //Se realiza el calculo de la multiplicación de las 2 matrices:
            for (int filas = 0; filas < MA_filas; filas++) {
                for (int columnas = 0; columnas < MB_columnas; columnas++) {
                    for (int sumatorio = 0; sumatorio < MA_columnas; sumatorio++) {
                        resultadoOperacion[filas][columnas] += matrizA[filas][sumatorio] * matrizB[sumatorio][columnas];
                    }
                }
            }

            long endTime = System.currentTimeMillis();
            
            System.out.println("Resultado:");
            
            long resultado = endTime - startTime;
            System.out.println("El resultado de la operación ha sido de: " + resultado + "ms");
            
        } else {
            System.out.println("Las matrices no pueden ser multiplicadas");
        }

        return resultadoOperacion;
    }



}
