package sockets;

import java.io.*;
import java.net.*;

public class Cliente_TCP {

    public static void main(String[] args) {
        
        int PUERTO_ORIGEN = 5678;
        String HOST_DESTINO = "localhost";
        int PUERTO_DESTINO = 6000;
        String mensaje = "Hola mundo desde lado cliente!";
        ObjectOutputStream flujo_salida = null;

        try {
            //Se inicializa el socket
            Socket socket = new Socket(HOST_DESTINO, PUERTO_DESTINO);
            //Flujo de datos de salida que va a ciruclar por el socket creado anteriormente
            flujo_salida = new ObjectOutputStream(socket.getOutputStream());
            //Se crea un paqueteTCP con la informacion a enviar:
            PaqueteTCP paqueteTCP = new PaqueteTCP(PUERTO_ORIGEN, PUERTO_DESTINO, mensaje);
            //Escribe el objeto 
            flujo_salida.writeObject(paqueteTCP);

        } catch (IOException ex) {
            System.out.println("Error desde el cliente \n" + ex);
        }finally{
            try {
                flujo_salida.close();
            } catch (IOException ex) {
                System.out.println("Error de salida:" + ex);
            }
        }
    }

}
