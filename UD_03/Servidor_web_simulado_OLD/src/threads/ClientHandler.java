package threads;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //Hilo de cada cliente que recibe el servidor.
            //Se debe crear un processBuilder para emular el acceso al contenido del servidor
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "echo Ola! Este é un servidor web simulado en Java.");
            Process process = pb.start();

            // Leer la salida del proceso
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            //Se cierra la conexion
            socket.close();
            System.out.println("Conexión terminada");
        } catch (IOException ex) {
            System.out.println("Error de excepcion en el hilo del servidor.\n" + ex);
        }

    }

}
