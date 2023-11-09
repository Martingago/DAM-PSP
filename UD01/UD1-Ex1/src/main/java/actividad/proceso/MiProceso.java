package actividad.proceso;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MiProceso extends Thread {
    int base;
    int veces;
    int time;

    public MiProceso(int base, int veces, int time) {
        super();
        this.base = base;
        this.veces = veces;
        this.time = time;
    }

    @Override
    public void run() {
        String nombreProceso = Thread.currentThread().getName(); //nombre del proceso
        System.out.println("Iniciado proceso: " + nombreProceso);
        int resultado = 0;
        int numBase = base;
        try {
            PrintStream fileOut = new PrintStream("./" + nombreProceso + ".txt"); //Por cada proceso generamos un documento diferente con el nombre del proceso
            for (int i = 0; i < veces; i++) {
                try {
                    resultado = resultado + (numBase + (numBase + 1));
                    numBase = numBase + 1;
                    LocalDateTime now = LocalDateTime.now(); //obtenemos el momento actual de ejecución
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); //formateador de fecha
                    String timeFormat = now.format(format); //formateamos el momento obtenido con fecha y hora y se lo asignamos la la variable timeFormat
                    fileOut.println(nombreProceso + " se ha ejecutado: " + timeFormat + ". Cálculo " + (i + 1) + " de: " + veces + " Resultado: " + resultado); //Hacemos que la salida sea por el fichero de texto

                    Thread.sleep(time); //retardo de accion 
                } catch (InterruptedException ex) {
                    System.out.println("Error de interrupción del sistema");
                    ex.printStackTrace();
                }
            }
            fileOut.close(); //una vez finalizado el bucle se cierra el printSream
            System.out.println(nombreProceso + " ha terminado!"); 
        } catch (FileNotFoundException ex) {
            System.out.println("Error, fichero no encontrado :( ");
            ex.printStackTrace();
        }
        super.run();
    }
}
