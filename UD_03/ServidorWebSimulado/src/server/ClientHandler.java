package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {

    Socket socketCliente;

    public ClientHandler(Socket socketCliente) {
        this.socketCliente = socketCliente;
    }

    @Override
    public void run() {
        System.out.println("Ejecutando proceso de gestión del cliente.");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
            PrintWriter writer = new PrintWriter(socketCliente.getOutputStream(), true);

            // Lee la solicitud del cliente
            String line;
            while (!(line = reader.readLine()).isEmpty()) {
                System.out.println("Solicitud recibida: " + line);
            }

            // Simula el acceso al contenido
            // Envía la respuesta al cliente
            writer.println("HTTP/1.1 200 OK");
            writer.println("Content-Type: text/plain");
            writer.println();
            writer.println("¡Ola! Este é un servidor web simulado en Java.");

            // Cierra recursos
            writer.close();
            reader.close();
            socketCliente.close();
        } catch (IOException e) {
            System.out.println("Error en el manejo del cliente: " + e);
        }
    }
}
