package servidor;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServidorMain {

    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        Socket socket;

        DataOutputStream outSocket; //salida de datos

        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el número del puerto en el que quieres iniciar el servidor:");
        int PUERTO = sc.nextInt();
        sc.nextLine();  // Consume el '\n' que queda después de nextInt()
        
        try {
            //Se lanza el servidor
            serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado en el puerto: " + PUERTO);
            
            //Se acepta la conexion del cliente
            socket = serverSocket.accept();
            System.out.println("Cliente conectado: " + socket.getInetAddress());
            outSocket = new DataOutputStream(socket.getOutputStream());

            //Envia un mensaje al cliente indicando que se ha conectado
            outSocket.writeUTF("Conexión realizada con éxito");
            outSocket.writeUTF("----------------------------");

            //Se crea un hilo de gestion en el servidor para leer los datos recibidos a la par que sigue ejecutando el programa
            ServidorThread hiloServidor = new ServidorThread(socket);
            hiloServidor.start();
            
            //Se leen los datos de saida del lado servidor
            String mensaje = null;
            do {
                mensaje = sc.nextLine(); //mensaje que envia el servidor al cliente
                outSocket.writeUTF(mensaje);
            } while (!mensaje.equals("sair()"));
            
            //Se cierra la conexion
            try {
                if(!socket.isClosed()){
                    socket.close(); //Se cierra el socket
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el socket" + e);
            }

        } catch (IOException ex) {
            System.out.println("Error en la conexion con el cliente");
        }

    }

}
