package threads2_contador;


public class MainContador {

  
    public static void main(String[] args) {
        // TODO code application logic here
        Contador c1 = new Contador("c1", 20);
        Contador c2 = new Contador("c2", 30);
        Contador c3 = new Contador("c3", 50);
        Contador c4 = new Contador("c4", 10);
        
        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);
        Thread t3 = new Thread(c3);
        Thread t4 = new Thread(c4);
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        
        /**
         * El join lo que hace es esperar a que se ejecuten todos los códigos 
         */
        try{
        t1.join();
        t2.join();
        t3.join();
        t4.join();
        
        }catch(InterruptedException ie){
            System.out.println("Error en ejecucion: \n" + ie);
        }
        System.out.println("Fin del programa!");
    }

}
