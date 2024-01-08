package sockets;

import java.io.IOException;
import java.net.*;

public class ServidorUDP {

    private static int PUERTO_LOCAL;

    //Se inicializa el servidor
    public static void main(String[] args) {

        try {

            PUERTO_LOCAL = 12345;
            DatagramSocket mySocket = new DatagramSocket(PUERTO_LOCAL);
            //buffer de lectura de datos:
            byte[] buffer = new byte[128];

            System.out.println("Servidor a la escucha.\nPuerto nº: " + PUERTO_LOCAL + "\n");
            //Paquete con la información recibida:

            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
            mySocket.receive(paquete);

            System.out.println("-------------------------------------------------------");
            System.out.println("| Mensaje recibido en el servidor                     |");
            System.out.println("-------------------------------------------------------");
            System.out.printf("| Ip de origen: %-37s |\n", paquete.getAddress().getHostAddress());
            System.out.printf("| Puerto de origen: %-33s |\n", paquete.getPort());
            System.out.printf("| Puerto de destino: %-32s |\n", PUERTO_LOCAL);
            System.out.printf("| Numero de caracteres recibidos: %-19s |\n", paquete.getLength());
            System.out.println("| Mensaje recibido:                                   |");
            System.out.println(new String(paquete.getData()));
            System.out.println("-------------------------------------------------------");
            System.out.println("Cerrando socket servidor");
            mySocket.close();
        } catch (SocketException ex) {
            System.out.println("Error en el proceso del socket \n" + ex);
        } catch (IOException ex) {
            System.out.println("error de interrupción del sistema \n" + ex);
        }

    }
}
