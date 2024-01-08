package threads_semaforo;


public class OlaMundoConFios {

    public static void main(String[] args) {
        int numFios = 4;
        Barreira barreira = new Barreira(numFios);
        for (int i = 1; i <= numFios; i++) {
            Thread fio = new OlaMundoFio(i, barreira);
            fio.start();
        }
        
    }
    
}