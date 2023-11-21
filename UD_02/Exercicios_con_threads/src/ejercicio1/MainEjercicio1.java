package ejercicio1;

public class MainEjercicio1 {

    public static void main(String[] args) {
        //Implements runnable;
        for(int i = 1; i<= 6; i++){
            ImplementsRunnable runnable = new ImplementsRunnable(i, 10);
            Thread t = new Thread(runnable);
            t.start();
        }
        
        //Extends Thread:
        for(int i = 1; i<=6; i++){
        ImplementsThread t = new ImplementsThread(i, 10);
        t.start();
        }
    }

}
