package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleTextWebClientTask {

    Socket socketCliente;

    public SimpleTextWebClientTask(Socket socketCliente) {
        this.socketCliente = socketCliente;
    }

    /**
     * Función que implementa la lógica del cliente: 
     * mostrar los datos del servidor local
     */
    public void logicaCliente() {
        PrintWriter writer = null;
        try {
            // Envía una solicitud al servidor
            //Para ello se le debe pasar la cabecera: cada \r\n representa un salto de linea
            writer = new PrintWriter(socketCliente.getOutputStream());
            writer.write("GET / HTTP/1.1\r\n");
            writer.write("Host: " + socketCliente.getLocalAddress() + "\r\n");
            writer.write("\r\n");
            writer.flush();
            //Se crea un buffered reader para leer la respuesta del servidor
            BufferedReader reader = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            // Lee la respuesta del servidor
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            //Se cierran los elementos
            reader.close();
            writer.close();
            socketCliente.close();
        } catch (IOException ex) {
            System.out.println("Error en el proceso de manejo del cliente "+ ex);
        } 

    }

}
