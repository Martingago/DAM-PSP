
package threads_semaforo;

import static java.lang.Thread.sleep;

class OlaMundoFio extends Thread {

    private int id;
    private Barreira barreira;

    public OlaMundoFio(int id, Barreira barreira) {
        this.id = id;
        this.barreira = barreira;
    }

    @Override
    public void run() {
        try {
            sleep(id * 10);
            System.out.println("Ola Mundo dende o Fío " + id);
            barreira.agardar(); //Agardar na barreira
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("bye");
    }
}