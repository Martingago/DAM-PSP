package Ejercicio_1;

import Ejercicio_1.cifradorCesar.CifradorCesar;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main_Amigo2 {
    
     /**
     * Clase que actua como sevidor que espera a que se conecte 1 cliente.
     * @param args 
     */
    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        Socket socket = null;
        final int PUERTO = 5002;
        DataInputStream in;
        DataOutputStream out;
        CifradorCesar cifrador;

        try {
            //Se inicializa el servidor
            serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado");
            Boolean amigo = false;
            while (!amigo) {
                socket = serverSocket.accept();
                System.out.println("Cliente conectado");

                in = new DataInputStream(socket.getInputStream());
                out = new DataOutputStream(socket.getOutputStream());
                
                //Se crea el cifrador con el codigo recibido por el cliente:
                int codigoCifrador = in.readInt();
                cifrador = new CifradorCesar(codigoCifrador);
                System.out.println("Se ha establecido una conexión cifrada con el cliente: " + codigoCifrador);
                
                String msgRecibidoCifrado = in.readUTF();
                String msgRecibido = cifrador.descifrar(msgRecibidoCifrado, cifrador.getAlfabetoCifrado());

                System.out.println("Mensaje recibido: \n" + "[" + msgRecibidoCifrado + "]: " + msgRecibido);

                //Se envia un mensaje desde el servidor:
                String msg = "HOLA MUNDO DESDE EL SERVIDOR!";
                String msgCifrado = cifrador.cifrar(msg, cifrador.getAlfabetoCifrado());

                //Se envia el mensaje desde el servidor
                out.writeUTF(msgCifrado);

                //Se cierran las conexiones:
                in.close();
                out.close();
                socket.close();
                amigo = true;
            }

        } catch (IOException ex) {
            System.out.println("Error de excepcion:" + ex);
        }

    }

}
