package main;

import java.net.DatagramSocket;
import sockets.ServidorUDP;

public class MainServidor {

    public static void main(String[] args) {
        //Si quieres ejecutar el mismo programa pero con interfaz simplemente ejecuta:
        // interfaz.MainServidorInterfaz => Esta clase contiene todos los métodos necesarios para ejecutar el servidor por si sola

        //Ejecuta los sockets sin la interfaz gráfica:
        ServidorUDP servidor = new ServidorUDP(); //importacion de funciones del socket servidor
        DatagramSocket socket = null; //declaración del socket
        int puerto = 12345;
        socket = servidor.initServidorUDP(puerto); // se inicia el socket en el puerto indicado

        if (socket != null) {
            System.out.println("Se ha lanzado el Servidor en el puerto: " + puerto);
            String result = servidor.listenServidorUDP(socket); //Se mantiene a la escucha y devuelve una cadena
            System.out.println(result);
        }
        socket.close();
    }

}
