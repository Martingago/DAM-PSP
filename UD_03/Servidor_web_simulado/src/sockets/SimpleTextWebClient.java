package sockets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleTextWebClient {

    public static void main(String[] args) {
        // TODO code application logic here

        String HOST = "localhost";
        int PUERTO = 8080;

        try {
            // TODO code application logic here
            Socket socket = new Socket(HOST, PUERTO);
            System.out.println("Conexion con el servidor");
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            pw.write("GET ");

        } catch (IOException ex) {
            System.out.println("IOException: \n" + ex);
        }
    }

}
