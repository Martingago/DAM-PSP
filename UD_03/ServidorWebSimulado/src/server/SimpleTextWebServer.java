package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleTextWebServer {

    public static void main(String[] args) {
        // TODO code application logic here
        try {
            System.out.println("Servidor iniciado");
            ServerSocket servidor = new ServerSocket(8080);
            //Mientas el servidor esté funcionando se aceptan clientes
            while (true) {
                //Se obtiene el socket de cada cliente para poder manejarlo de forma externa
                Socket clientSocket = servidor.accept();
                System.out.println("Cliente conectado");
                //Se debe crear un processBuilder para emular el acceso al contenido del servidor
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "echo Emulando acceso del cliente a ficheros...");
                Process process = pb.start();
                
                //El proceso se ha ejecutado, pero vamos a mostrar por consola del programa la salida que tendria
                //el proceso creado con anterioridad:
                
                InputStream is = process.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                // Escribir la salida en la consola
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }

                //Se inicia un hilo dónde se manejará la petición del cliente
                Thread hilo = new Thread(new ClientHandler(clientSocket));
                hilo.start();
            }
        } catch (IOException ex) {
            System.out.println("Error de proceso en el servidor\n" + ex);
        }

    }

}
