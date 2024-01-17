package sockets;

import java.io.IOException;
import java.net.*;

public class ClienteUDP {
    
    /**
     * Función que envía datos a un servidor
     * @param PUERTO_LOCAL
     * @param PUERTO_DESTINO
     * @param DIRECCION
     * @param msj
     * @return mensaje con el resumen de datos enviados
     */
    public static String enviarMensajeServidor(int PUERTO_LOCAL, int PUERTO_DESTINO, String DIRECCION, String msj) {
        try {
            //Se crea un socket con el puerto local del cliente
            DatagramSocket socket = new DatagramSocket(PUERTO_LOCAL);
            byte[] buffer = new byte[15];
            buffer = msj.getBytes();
            InetAddress direccionIP = InetAddress.getByName(DIRECCION);
            
            //Con los parámetros recibidos se crea un objeto DatagramPacket que será enviado al servidor
            DatagramPacket paquete = new DatagramPacket(buffer, msj.length(),
                    InetAddress.getByName(DIRECCION), PUERTO_DESTINO);
            
            //Se envía el paquete y se cierra el socket
            socket.send(paquete);
            socket.close();
            
            String salida =  "Puerto local: " + PUERTO_LOCAL + 
                    "\nPuerto destino: "+ PUERTO_DESTINO + 
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
