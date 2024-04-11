// MainClienteRSA.java
package Ejercicio_2;

import Ejercicio_2.cifradorRSA.CifradorRSA;
import java.io.*;
import java.net.*;
import java.security.*;
import java.util.Scanner;
import javax.crypto.*;

public class MainClienteRSA {

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException {
        CifradorRSA cifradorRSA;
        final String HOST = "127.0.0.1";
        final int PUERTO = 8080;
        ObjectOutputStream out;
        ObjectInputStream in;

        cifradorRSA = new CifradorRSA();
        try {
            Socket socket = new Socket(HOST, PUERTO);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            //Variables de mensajes:
            byte[] msgRecibidoCifrado = null;
            String msgRecibidoDescifrado = null;
            
            out.writeObject(cifradorRSA.getPublica());
            System.out.println("Clave pública enviada al servidor");

            PublicKey claveServidor = (PublicKey) in.readObject();
            System.out.println("Recibida clave pública del servidor");
            System.out.println("----------- LA CONEXIÓN AHORA ES CIFRADA CON RSA -----------");
            //Recibe mensaje
            msgRecibidoCifrado = (byte[]) in.readObject();
            msgRecibidoDescifrado = cifradorRSA.leerDatos(cifradorRSA, msgRecibidoCifrado);
            System.out.println("Mensaje servidor: " + msgRecibidoDescifrado);
            
            //Envia mensaje
            Scanner sc = new Scanner(System.in);
            System.out.println("Introduce mensaje al servidor [Mellon] para validar");
            String respuesta = sc.nextLine();

            byte[] respuestaCifrada = cifradorRSA.cifrar(respuesta.getBytes(), claveServidor);
            out.writeObject(respuestaCifrada);
            
            //Recibe mensaje
            msgRecibidoCifrado = (byte[]) in.readObject();
            msgRecibidoDescifrado = cifradorRSA.leerDatos(cifradorRSA, msgRecibidoCifrado);
            System.out.println("Mensaje servidor: " + msgRecibidoDescifrado);
            
            
        } catch (IOException | ClassNotFoundException  ex) {
            System.out.println("Error de excepción: " + ex);
        }
    }
    
}
