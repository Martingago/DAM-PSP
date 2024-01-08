package ejercicio_filosofos;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Filosofo extends Thread {

    private Monitor mesa;
    private int comensal;
    private int indiceComensal;

    public Filosofo(Monitor mesa, int comensal) {
        this.mesa = mesa;
        this.comensal = comensal;
        this.indiceComensal = comensal - 1;
    }

    public void run() {
        while (true) {
            this.pensando();
            mesa.cogerTenedor(indiceComensal);
            this.comiendo();
            System.out.println("Filosofo " + comensal + " deja de comer, tenedores libres:"
                    + mesa.tenedorIzquierda(this.indiceComensal + 1) + " " + this.mesa.tenedorDerecha(indiceComensal + 1));
            mesa.dejarTenedores(indiceComensal);
        }
    }

    public void pensando() {
        try {
            System.out.println("Filosofo " + comensal + " esta pensando");
            sleep(new Random().nextInt(5000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void comiendo() {
        try {
            System.out.println("Filosofo " + comensal + " esta comiendo");
            sleep(new Random().nextInt(5000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Filosofo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
