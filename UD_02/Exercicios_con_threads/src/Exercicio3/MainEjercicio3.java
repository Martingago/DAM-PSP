package Exercicio3;

public class MainEjercicio3 {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis(); // Tiempo inicial
        //Código para probar que el programa funciona sin tener que pasar parámetros:
       
        /**
        String[] argsTest = {"./test/test_ejercicio3/test.txt", "./test/test_ejercicio3/test2.txt", "./test/test_ejercicio3/test3.txt", "./test/test_ejercicio3/test4.txt", "./test/test_ejercicio3/test5.txt", "./test/test_ejercicio3/test6.txt"};
        
        ThreadManager thread = new ThreadManager();
        //Ejecuta los threads de forma secuencial
        //thread.ejecutarThreadsSecuenciales(argsTest);
        //Ejecuta los thread de forma paralela
        thread.ejecutarThreadsParalelos(argsTest);
        */
        
        //Codigo que ejecuta el programa pasandole parámetros
        if(args.length != 0){
            System.out.println("Cargando datos de: " + args.length + " ficheros");
            ThreadManager thread = new ThreadManager();
            //Realiza una carga secuencial de los threads:
            //thread.ejecutarThreadsSecuenciales(args);
            
            //Realiza la ejecución de los threads de forma paralela:
            thread.ejecutarThreadsParalelos(args);
            
            
        }else{
            System.out.println("No se ha añadido ningún fichero");
        }
        
        
        long endTime = System.currentTimeMillis(); // Tiempo final
        long executionTime = endTime - startTime; // Tiempo de ejecución

        System.out.println("------------------------------------------------------------------\n"
                + "El tiempo de ejecución fue de: " + executionTime + " milisegundos.");

    }

}
