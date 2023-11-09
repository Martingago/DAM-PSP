
package threads1_infinite_loop;

public class MainInfiniteLoop {

    public static void main(String[] args) {
        // TODO code application logic here
        
        RunThread h1 = new RunThread(1);
        RunThread h2 = new RunThread(2);
        
        
        Thread t1 = new Thread(h1);
        Thread t2 = new Thread(h2);
        t1.start();
        t2.start();
    }

}
