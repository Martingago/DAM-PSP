package Ejercicio_1;

import Ejercicio_1.cifradorCesar.CifradorCesar;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Main_Amigo1 {

    /**
     * Clase que actua como cliente que se conecta al servidor.
     *
     * @param args
     */
    public static void main(String[] args) {
        final String HOST = "127.0.0.1";
        final int PUERTO = 5002;
        DataInputStream in; //Stream de entrada de datos
        DataOutputStream out; //Stream de salida de datos
        Scanner sc = new Scanner(System.in);
        CifradorCesar cifrador;

        try {
            Socket socket = new Socket(HOST, PUERTO);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            int cifradoValue = 19; //Se inicializa el cifrador en un valor de cifrado

            // El cliente genera su cifrador con el valor anterior y se lo envia al servidor para que el servidor sepa
            // que valor de "COMUNICACION" se va a emplear en esta conexion.
            cifrador = new CifradorCesar(cifradoValue);
            out.writeInt(cifradoValue);
            System.out.println("Se ha establecido un valor de cifrado en la conexion: " + cifradoValue);
            
            System.out.println("Introduce el mensaje a enviar: ");
            String msg = sc.nextLine();
            //Se cifra el mensaje
            String msgCifrado = cifrador.cifrar(msg, cifrador.getAlfabetoCifrado());
            //Se envía el mensaje:
            out.writeUTF(msgCifrado);

            //Se recibe mensaje:
            String msgRecibidoCifrado = in.readUTF();
            //Se descifra el mensaje:
            String msgRecibido = cifrador.descifrar(msgRecibidoCifrado, cifrador.getAlfabetoCifrado());
            System.out.println("Mensaje Recibido: \n" + "[" + msgRecibidoCifrado + "]: " + msgRecibido);

            //Se cierran las conexiones
            in.close();
            out.close();
            socket.close();

        } catch (IOException ex) {
            System.out.println("Error de excepcion: " + ex);
        }
    }

}
