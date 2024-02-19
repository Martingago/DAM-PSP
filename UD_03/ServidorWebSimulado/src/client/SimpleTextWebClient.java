package client;

import java.io.IOException;
import java.net.Socket;

public class SimpleTextWebClient {
    
    
    public static void main(String[] args) {
        // Ejecuta la conexion de un cliente
        final String HOST = "localhost";
        final int PUERTO = 8080;
        try {
            //Creamos un socket
            Socket socketCliente = new Socket(HOST, PUERTO);
            
            //Se realiza la logica del cliente llamando al método:
            new SimpleTextWebClientTask(socketCliente).logicaCliente();

        } catch (IOException ex) {
            System.out.println("Excepción en el socket cliente: " + ex);
        }
    }

}
