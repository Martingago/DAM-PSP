package sockets;

import java.io.IOException;
import java.net.*;

public class ClienteUDP {
    //Se inicializa el lado cliente    

    //Variables a las que enviar la información:
    private static int PUERTO_LOCAL; //numero del puerto local
    private static int PUERTO_DESTINO; //numero del puerto de destino
    private static String DIRECCION; //direccion/HOST a la que se enviarán los datos
    private static InetAddress IP; //direccion IP a la que se enviaran los datos
    private static String MENSAJE; //mensaje a enviar

    public static void main(String[] args) {

        PUERTO_LOCAL = 34567;
        PUERTO_DESTINO = 12345;
        DIRECCION = "localhost"; //también podria ser: 127.0.0.1
        DIRECCION = "127.0.0.1";
        MENSAJE = "Enviando saudos ao servidor";

        try {
            //Se crea el socket
            DatagramSocket mySocket = new DatagramSocket(PUERTO_LOCAL);

            //Se crea un array de bytes con la información a enviar
            byte[] buffer = new byte[15];

            buffer = MENSAJE.getBytes();

            //Se crea el datagrama con la información a enviar
            DatagramPacket paquete = new DatagramPacket(buffer, MENSAJE.length(),
                    InetAddress.getByName(DIRECCION), PUERTO_DESTINO);

            //Se obtiene la direccion IP del servidor
            IP = InetAddress.getByName(DIRECCION);

            System.out.println("-----------------------------------------------------");
            System.out.println("| Datos de la infomación que se enviará:            |");
            System.out.println("-----------------------------------------------------");
            System.out.printf("| Puerto destino: %-33s |\n", PUERTO_DESTINO);
            System.out.printf("| Puerto local: %-35s |\n", PUERTO_LOCAL);
            System.out.printf("| Direccion: %-38s |\n", DIRECCION);
            System.out.printf("| Direccion IP del host: %-26s |\n", IP);
            System.out.printf("| Tamaño mensaje: %-33s |\n", MENSAJE.length());
            System.out.println("-----------------------------------------------------\n");

            mySocket.send(paquete);
            mySocket.close();

        } catch (SocketException e) {
            System.out.println("Error en el socket \n" + e);

        } catch (UnknownHostException ex) {
            System.out.println("Error, el host no se conoce \n" + ex);
        } catch (IOException ex) {
            System.out.println("Error al enviar los datos del paquete \n" + ex);
        }
    }

}
