package ejercicio5;

import ejercicio5_GeneradorMatrices.TestMatrices;
import java.util.Arrays;

public class MainEjercicio5 {

    public static void main(String[] args) {
        CalculoMatriz calculoSecuencial = new CalculoMatriz();
        CalculoMatrizThreadParalelo calculoThreadParalelo = new CalculoMatrizThreadParalelo();
        CalculoMatrizThreadSecuencial calculoThreadSecuencial = new CalculoMatrizThreadSecuencial();
        /**
         * Para que los test sean lo más similares posibles, he creado un
         * sencillo generador de Matrices, he copiado el código resultante, y he
         * creado dentro del paquete Exercicio5_GeneradorMatrices una clase
         * llamada TestMatrices en la que se encuentran las matrices que voy a
         * emplear para las pruebas unitarias de rendimiento
         */
        TestMatrices i = new TestMatrices();

        /**
         * Realizo un test para verificar que realmente ambos resultados (usando
         * thread/ sin usar thread) son iguales
         */
        long[][] calc1 = calculoSecuencial.calcularMuplicacionMatrices(i.matrizA, i.matrizB);
        long[][] calc2 = calculoThreadParalelo.calcularMuplicacionMatrices(i.matrizA, i.matrizB);
        long[][] calc3 = calculoThreadSecuencial.calcularMuplicacionMatrices(i.matrizA, i.matrizB);
        
        
//        System.out.println("Resultado calculo1:");
//        printMatriz(calc1);
//        System.out.println("Resultado calculo2:");
//        printMatriz(calc2);
//        System.out.println("Resultado calculo3:");
//        printMatriz(calc3);
        
        
        comprobarMatrices(calc2,calc3);
    }

    //Compara el resultado de ambas matrices y muestra por consola si los resultados son correctos o no
    public static void comprobarMatrices(long[][] A, long[][] B) {

        if (Arrays.deepEquals(A, B)) {
            System.out.println("Cálculos correctos");
        } else {
            System.out.println("Cálculos NO correctos");
        }

    }

    //Funcion que muestra por pantalla el resultado de multiplicar matrices
    public static void printMatriz(long[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

}
