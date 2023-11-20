package ejercicio4;

public class ThreadAnimales implements Runnable {

    MovimientoAnimales mov = new MovimientoAnimales();

    private EnumTipoAnimal tipoAnimal; //tipo de animal que será el hilo
    private int posicionAnimal; //posicion en la que se encuentra el animal
    private int resultadoMovimiento; //resultado de un movimiento

    public ThreadAnimales(EnumTipoAnimal tipoAnimal, int posicion) {
        this.tipoAnimal = tipoAnimal;
        this.posicionAnimal = posicion;

    }

    @Override
    public void run() {

        while (posicionAnimal < 70) {

            try {
                resultadoMovimiento = tipoAnimal.movimiento();
                posicionAnimal += resultadoMovimiento;

                if (posicionAnimal < 1) {
                    posicionAnimal = 1;
                }

                if (resultadoMovimiento > 0) {
                    System.out.println(tipoAnimal.name() + " ha  avanzado: " + resultadoMovimiento + " casillas");
                } else {
                    System.out.println(tipoAnimal.name() + " ha  retrocedido: " + resultadoMovimiento * -1 + " casillas");
                }

                String animal = (tipoAnimal.name() == "TORTUGA") ? "T" : "C";
                String avance = "";
                for (int i = 1; i <= 70; i++) {
                    if (i < posicionAnimal) {
                        avance += "#";
                    } else if (i == posicionAnimal) {
                        avance += animal;
                    } else if (i > posicionAnimal && i != 70) {
                        avance += "_";
                    } else if (i == 70) {
                        avance += "/meta";
                    }
                }
                System.out.printf("%-10s %s%n", tipoAnimal, avance);

                Thread.sleep(10);
            } catch (InterruptedException ex) {
                System.out.println("Error de interrupción del proceso" + ex);
            }

        }

        System.out.println("Ha alcanzado la meta!");
    }
}