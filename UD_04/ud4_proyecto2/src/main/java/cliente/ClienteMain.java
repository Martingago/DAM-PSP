package cliente;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteMain {

    public static void main(String[] args) {
        DataOutputStream outSocket; //salida de datos

        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la DIRECCION a la que te quieres conectar:");
        String DIRECCION = sc.nextLine();
        System.out.println("Introduce el PUERTO al que te quieres conectar");
        int PUERTO = sc.nextInt();
        sc.nextLine();  // Consume el '\n' que queda después de nextInt()
        
        try {
            //Se realiza la conexion con el socket
            Socket socket = new Socket(DIRECCION, PUERTO);
            outSocket = new DataOutputStream(socket.getOutputStream());

            //Envia un mensaje al Servidor indicando que se ha conectado
            outSocket.writeUTF("Conexión realizada con éxito");
            outSocket.writeUTF("----------------------------");
            
            //Si la conexion es exitosa, se crea un hilo de cliente encargado de leer toda la informacion procedente del servidor:
            ClientThread hiloLectura = new ClientThread(socket);
            hiloLectura.start();

            String mensajeCliente = null;
            do {
                mensajeCliente = sc.nextLine();
                outSocket.writeUTF(mensajeCliente);
            } while (!mensajeCliente.equals("sair()"));

            //Se cierra la conexion
            try {
                if (!socket.isClosed()) {
                    socket.close(); //Se cierra el socket
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar el socket" + e);
            }

        } catch (IOException ex) {
            System.out.println("No se ha podido realiza la conexión a: \n" + DIRECCION + " con el puerto: " + PUERTO);
        }

    }

}
