package myApp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MiProcessBuilder {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Error, los parametros no son válidos");
            System.exit(-1);
        } else {
            int base = Integer.parseInt(args[0]);
            int veces = Integer.parseInt(args[1]);
            int time = Integer.parseInt(args[2]);

            int resultado = 0;
            int numBase = base;
                //PrintStream fileOut = new PrintStream("./" + nombreProceso + ".txt"); //Por cada proceso generamos un documento diferente con el nombre del proceso
                for (int i = 0; i < veces; i++) {
                    try {
                        resultado = resultado + (numBase + (numBase + 1));
                        numBase = numBase + 1;
                        LocalDateTime now = LocalDateTime.now(); //obtenemos el momento actual de ejecución
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"); //formateador de fecha
                        String timeFormat = now.format(format); //formateamos el momento obtenido con fecha y hora y se lo asignamos la la variable timeFormat
                        System.out.println(" se ha ejecutado: " + timeFormat + ". Cálculo " + (i + 1) + " de: " + veces + " Resultado: " + resultado); //Hacemos que la salida sea por el fichero de texto

                        Thread.sleep(time); //retardo de accion 
                    } catch (InterruptedException ex) {
                        System.out.println("Error de interrupción del sistema");
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
