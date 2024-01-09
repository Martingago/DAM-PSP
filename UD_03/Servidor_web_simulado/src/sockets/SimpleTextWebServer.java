package sockets;

import java.io.*;
import java.net.*;
import threads.ClientHandler;

public class SimpleTextWebServer {

    public static void main(String[] args) {
        
        try {
            //Se inicializa el servidor
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Servidor iniciado.");
            while(true){
            //Se aceptaran conexiones
            Socket socket = serverSocket.accept();
            
            //Por cada cliente que se recibe se crea un hilo que haga su gestión 
            // y se pasa su socket para posteriormente cerrarlo dentro del propio hilo cuando se termine de procesar su información.
            ClientHandler manejo = new ClientHandler(socket);
            Thread hilo_manejo_cliente = new Thread(manejo);
            hilo_manejo_cliente.start();
            }
            
        } catch (IOException ex) {
            System.out.println("Error de excepción en el servidor \n" + ex);
        }
        
    }
        

}
