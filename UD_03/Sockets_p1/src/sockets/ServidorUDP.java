package sockets;

import java.io.IOException;
import java.net.*;

public class ServidorUDP {

    private static int PUERTO_LOCAL;
    
    /**
     * Funcion que inicia el servidor
     * @param puerto puerto que se quiere abrir
     * @return DatagramSocket con la información del servidor
     */
    public DatagramSocket initServidorUDP(int puerto) {
        DatagramSocket socket = null;
        try {
            PUERTO_LOCAL = puerto;
            socket = new DatagramSocket(PUERTO_LOCAL);
            System.out.println("Servidor lanzado con éxito!");
        } catch (SocketException ex) {
            System.out.println("Error en el proceso del socket \n" + ex);
        }
        System.out.println("continuamos...");
        return socket;
    }

    /**
     * Funcion que mantiene a la escucha a un socket
     * @param socket
     * @return String de salida con los datos recibidos / o de finalización de la escucha
     */
    public String listenServidorUDP(DatagramSocket socket) {
        String salida = null;
        try {
            System.out.println("Comienza la escucha!");
            //buffer de lectura de datos:
            byte[] buffer = new byte[128];
            DatagramPacket paquete = new DatagramPacket(buffer, buffer.length);
            socket.receive(paquete);
            String mensaje = new String(paquete.getData());
            //Se cierra el socket
            socket.close();
            salida = "DATOS RECIBIDOS EN EL SERVIDOR: "+
                    "\nIP de origen: " + paquete.getAddress().getHostAddress() +
                    "\nPuerto de origen: " + paquete.getPort() +
                    "\nPuerto de destino: " + PUERTO_LOCAL +
                    "\nNumero de caracteres recibidos: " + paquete.getLength() +
                    "\nMensaje recibido: \n"+ mensaje;
            System.out.println("Servidor cerrado");
        } catch (IOException ex) {
            System.out.println("Error de excepcion escucha del servidor: " + ex);
            socket.close();
            salida = "Ha finalizado la ejecución del servidor.";
            
        }finally{
            System.out.println("Terminada la escucha del servidor");
        }
        return salida;
    }

}
