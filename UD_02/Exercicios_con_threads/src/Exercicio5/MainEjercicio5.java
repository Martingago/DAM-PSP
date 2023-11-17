package Exercicio5;

import Exercicio5_GeneradorMatrices.*;

public class MainEjercicio5 {

    public static void main(String[] args) {
        CalculoMatriz calculo = new CalculoMatriz();
        /**
         * Para que los test sean lo más similares posibles, he creado un
         * sencillo generador de Matrices, he copiado el código resultante, y he
         * creado dentro del paquete Exercicio5_GeneradorMatrices una clase
         * llamada TestMatrices en la que se encuentran las matrices que voy a
         * emplear para las pruebas unitarias de rendimiento
         */
        TestMatrices i = new TestMatrices();

        calculo.calcularMuplicacionMatrices(i.matrizA, i.matrizB);
        CalculoMatrizThread.calcularMuplicacionMatrices(i.matrizA, i.matrizB);
    }

}
