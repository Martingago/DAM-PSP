package sockets;

import java.io.IOException;
import java.net.*;

public class ClienteUDP {
    public static String enviarMensajeServidor(int PUERTO_LOCAL, int PUERTO_DESTINO, String DIRECCION, String msj) {
        try {
            DatagramSocket socket = new DatagramSocket(PUERTO_LOCAL);
            byte[] buffer = new byte[15];
            buffer = msj.getBytes();
            InetAddress direccionIP = InetAddress.getByName(DIRECCION);
            DatagramPacket paquete = new DatagramPacket(buffer, msj.length(),
                    InetAddress.getByName(DIRECCION), PUERTO_DESTINO);
            socket.send(paquete);
            socket.close();
            
            String salida =  "Puerto local: " + PUERTO_LOCAL + " Puerto destino: "+ PUERTO_DESTINO + 
                    "\nHost: " + DIRECCION + 
                    "\nDirección IP: " + direccionIP +
                    "\nTamaño mensaje: " + msj.length();
            
            return salida;
        } catch (SocketException e) {
            System.out.println("Error en el socket \n" + e);

        } catch (UnknownHostException ex) {
            System.out.println("Error, el host no se conoce \n" + ex);
        } catch (IOException ex) {
            System.out.println("Error al enviar los datos del paquete \n" + ex);
        }
        return "Los parámetros no son válidos!\nError al enviar mensaje!";
    }

}
