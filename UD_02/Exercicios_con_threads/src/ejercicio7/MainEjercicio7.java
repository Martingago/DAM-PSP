package ejercicio7;

public class MainEjercicio7 {

    public static void main(String[] args) {
        
        Buffer b = new Buffer(4);
        
        Productor p = new Productor(b);
        Consumidor c = new Consumidor(b);
        
        p.start();
        c.start();
        
    }

}
