package ejercicio_filosofos;

import java.util.logging.Level;

public class Monitor {

    public boolean[] tenedores;

    public Monitor(int numTenedores) {
        this.tenedores = new boolean[numTenedores];
    }

    /**
     * El tenedor que va a estar a la izquierda siempre será igual a la posicion del filosofo (Se ve a través de un dibujo)
     * @param i
     * @return posicion dentro de tenedores del tenedor correspondiente al comensal
     */
    public int tenedorIzquierda(int i) {
        return i;
    }

    /**
     * El tenedor de la derecha va a ser siempre 1 menos, a diferencia del filosofo 1, que su anterior tenedor será el 5 (ultimo)
     * @param i
     * @return posicion dentro de tenedores del tenedor correspondiente al comensal
     */
    public int tenedorDerecha(int i) {
        if (i == 0) {
            return this.tenedores.length - 1;
        } else {
            return i - 1;
        }

    }
    /**
     * El comensal de la posicion indicada tratará de coger ambos tenedores de sus lados. 
     * En caso de que uno de los 2 no esté disponible esperará
     * @param comensal 
     */
    public synchronized void cogerTenedor(int comensal){
        //Obtiene los datos de si esos tenedores estan disponibles o no
        //Con que uno de ellos no esté disponible, ya se tiene que esperar
        while(tenedores[tenedorIzquierda(comensal)] || tenedores[tenedorDerecha(comensal)]){
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println("Error de espera");
            }
        }
        tenedores[tenedorIzquierda(comensal)] = true;
        tenedores[tenedorDerecha(comensal)] = true;
    }
    
    public synchronized void dejarTenedores(int comensal){
        tenedores[tenedorIzquierda(comensal)] = false;
        tenedores[tenedorDerecha(comensal)] = false;
        notifyAll();
    
    }
    
}
