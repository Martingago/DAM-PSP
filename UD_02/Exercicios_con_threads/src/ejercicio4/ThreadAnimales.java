package ejercicio4;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class ThreadAnimales extends Thread {

    
    private CyclicBarrier barrier;
    private AnimalData datoAnimal;
    private EnumTipoAnimal tipoAnimal; //tipo de animal que será el hilo
    MovimientoAnimales mov = new MovimientoAnimales();
    
    /**
     * Crea un hilo que iniciará el recorrido de un animal hasta que alcance 70 puntos
     * @param datoAnimal información del animal que realizará la carrera (mombre, icono)
     * @param tipoAnimal ENUM con su tipo de animal ==> útil para establecer sus movimientos según su ENUM
     * @param barrier barrera para sincronizar que los animales tengan los mimos turnos
     */
    public ThreadAnimales(AnimalData datoAnimal, EnumTipoAnimal tipoAnimal, CyclicBarrier barrier) {
        this.datoAnimal = datoAnimal;
        this.tipoAnimal = tipoAnimal;
        this.barrier = barrier;

    }

    public void run() {
        int posicionAnimal = datoAnimal.getPosicionAnimal();
        while (posicionAnimal < 70) {
            posicionAnimal = datoAnimal.getPosicionAnimal();
            try {
                int movimiento = tipoAnimal.movimiento();
                posicionAnimal += movimiento;
                //Se impide que el animal retroceda de la salida
                if (posicionAnimal < 1) {
                    posicionAnimal = 1;
                }
                //Se establecen los mensajes de movimiento:
                String msg = "";
                if(movimiento <0){
                msg = datoAnimal.getNombreAnimal() + " ha retrocedido " +movimiento * -1 + " casillas";
                }else{
                msg = datoAnimal.getNombreAnimal() + " ha avanzado " +movimiento + " casillas";
                }
                //Se actualiza la posicion del animal
                datoAnimal.setPosicionAnimal(posicionAnimal); 

                //Se establecen los mensajes de cada animal: icono, posicion, y resultado de movimiento
                char icono = datoAnimal.getIconoNombre();
                String avance = "";
                for (int i = 1; i <= 70; i++) {
                    if (i < posicionAnimal) {
                        avance += "#";
                    } else if (i == posicionAnimal) {
                        avance += icono;
                    } else if (i > posicionAnimal && i != 70) {
                        avance += "_";
                    } else if (i == 70) {
                        avance += "|";
                    }
                }
                System.out.printf("%-10s %s %s %n", datoAnimal.getNombreAnimal(), avance, msg);

                Thread.sleep(1000);
                barrier.await();
            } catch (InterruptedException ex) {
                System.out.println("Error de interrupción del proceso" + ex);
            } catch (BrokenBarrierException ex) {
                System.out.println("Error de interrupción en el código");
            }

        }
        if(Carrera.comprobarGanadorCarrera()){
            System.exit(0);
        };
    }
}
