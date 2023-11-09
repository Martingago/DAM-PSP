package Exercicio1;

public class MainEjercicio1 {

    public static void main(String[] args) {
        //Implements runnable;
        for(int i = 1; i<= 6; i++){
            Thread t = new Thread(new ImplementsRunnable(i, 10));
            t.start();
        }
        
        //Extends Thread:
        for(int i = 1; i<=6; i++){
        Thread t = new Thread(new ImplementsThread(i, 10));
        t.start();
        }
    }

}
