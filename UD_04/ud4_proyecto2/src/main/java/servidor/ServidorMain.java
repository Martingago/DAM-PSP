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

        /**
         * Dado que en la actividad se indica que el servidor recibe conexion de
         * varios clientes, he seguido el ejemplo propuesto en los apuntes y he
         * añadido un ArrayList para manejar de forma adecuada los distintos
         * threads de posibles conexiones entrandes
         *
         */
        Scanner sc = new Scanner(System.in);
        //System.out.println("Introduce el número del puerto en el que quieres iniciar el servidor:");
        //int PUERTO = sc.nextInt();
        int PUERTO = 6000;
        //Se lanza el servidor
        try {
            serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado en el puerto: " + PUERTO);
            //Se acepta la conexion del cliente
            socket = serverSocket.accept();
            System.out.println("Cliente conectado: " + socket.getInetAddress());
            outSocket = new DataOutputStream(socket.getOutputStream());

            //Envia un mensaje al cliente indicando que se ha conectado
            outSocket.writeUTF("Conexión realizada con éxito");
            outSocket.writeUTF("----------------------------\n");

            //Se crea un hilo de gestion para el cliente para ver sus datos
            ServidorThread hiloServidor = new ServidorThread(socket);
            hiloServidor.start();
            sc = new Scanner(System.in); //se abre el scanner:
            String mensaje = null;
            do {
                //mensaje que envia el servidor al cliente:
                mensaje = sc.nextLine();
                outSocket.writeUTF("<<<<: " + mensaje);
            } while (!mensaje.equals("sair()"));

            //Se cierra la conexion
            try {
                //Se llama a la funcion para que detenga el hilo que lee datos del socket
                hiloServidor.stopThread();
                socket.close(); //Se cierra el socket
                System.out.println("Conexion cerrada");
            } catch (IOException e) {
                System.out.println("Error al cerrar el socket");
            }

        } catch (IOException ex) {
            System.out.println("Error en la conexion con el cliente");
        }

    }

}
