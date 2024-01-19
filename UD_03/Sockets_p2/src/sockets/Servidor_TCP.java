package sockets;

import java.io.*;
import java.net.*;

public class Servidor_TCP {

    public static void main(String[] args) {

        final int PUERTO = 6000;

        try {
            //Se lanza el servidor el cual estará siempre encendido y aceptando conexiones externas.
            
            ServerSocket servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado.");
            
            //El servidor estará continuamente aceptando conexiones exteriores y mostrando por consola los mensajes que 
            while (true) {
                //El servidor acepta dispositivos que se conectan al socket
                Socket socket = servidor.accept();
                System.out.println("Cliente conectado");
                
                //Se maneja la entrada de un objeto y se muestra por pantalla
                ObjectInputStream flujo_entrada = new ObjectInputStream(socket.getInputStream());

                PaqueteTCP paquete = (PaqueteTCP) flujo_entrada.readObject();
                System.out.println(paquete.toString());
                socket.close();
                System.out.println("Cliente Desconectado");
            }

        } catch (IOException ex) {
            System.out.println("Error de excepcion de E/S \n" + ex);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error, no se ha podido leer el tipo de objeto recibido \n" + ex);
        }
    }

}
