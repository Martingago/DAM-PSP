package Exercicio3;

public class MainEjercicio3 {

    public static void main(String[] args) {

        //Código para probar que el programa funciona sin tener que pasar parámetros:
        String[] argsTest = {"test.txt", "test2.txt", "test3.txt", "test4.txt", "test5.txt", "test6.txt"};
        long startTime = System.currentTimeMillis(); // Tiempo inicial
        ThreadManager thread = new ThreadManager();

        //Ejecuta los threads de forma secuencial
        thread.ejecutarThreadsSecuenciales(argsTest);
        
        //Ejecuta los thread de forma paralela
        //thread.ejecutarThreadsParalelos(argsTest);
        
        long endTime = System.currentTimeMillis(); // Tiempo final
        long executionTime = endTime - startTime; // Tiempo de ejecución

        System.out.println("------------------------------------------------------------------\n"
                          + "El tiempo de ejecución fue de: " + executionTime + " milisegundos.");

    }

}
