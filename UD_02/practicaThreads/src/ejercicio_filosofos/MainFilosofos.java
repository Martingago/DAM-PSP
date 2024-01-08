package ejercicio_filosofos;


public class MainFilosofos {

  
    public static void main(String[] args) {
        
        final int NUMERO_COMENSALES = 5;
        Monitor mesa = new Monitor(NUMERO_COMENSALES);
        
        for(int i = 1; i<= NUMERO_COMENSALES; i++){
            Filosofo filosofo = new Filosofo(mesa, i);
            filosofo.start();
        }
    }

}
