package Ejercicio_2;

import Ejercicio_2.cifradorRSA.CifradorRSA;
import java.io.*;
import java.net.*;
import java.security.*;
import javax.crypto.*;

public class MainServidorRSA {

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException {
        CifradorRSA cifradorRSA;
        final int PUERTO = 8080;

        try {
            cifradorRSA = new CifradorRSA();

            ServerSocket serverSocket = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado en el puerto " + PUERTO);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("\n-------------------- CLIENTE CONECTADO --------------------");

                // Crea un hilo para el cliente
                Thread clientThread = new Thread(() -> {
                    try {
                        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                        PublicKey claveCliente = (PublicKey) in.readObject();
                        System.out.println("Clave pública recibida del cliente");

                        out.writeObject(cifradorRSA.getPublica());
                        System.out.println("Clave enviada al cliente");
                        System.out.println("----------- LA CONEXIÓN AHORA ES CIFRADA CON RSA -----------");

                        String msg = "Fala amigo e entra";
                        byte[] msgCifrado = cifradorRSA.cifrar(msg.getBytes(), claveCliente);
                        out.writeObject(msgCifrado);

                        // Se lee mensaje cifrado del cliente
                        byte[] msgRecibidoCifrado = (byte[]) in.readObject();
                        String msgRecibidoDescifrado = cifradorRSA.leerDatos(cifradorRSA, msgRecibidoCifrado);
                        System.out.println("Mensaje cliente: " + msgRecibidoDescifrado);

                        if (msgRecibidoDescifrado.equals("Mellon")) {
                            msg = "Las puertas de Moria están abiertas para ti, amigo";
                        } else {
                            msg = "La autenticación no ha sido correcta!";
                        }

                        msgCifrado = cifradorRSA.cifrar(msg.getBytes(), claveCliente);
                        out.writeObject(msgCifrado);
                        socket.close();
                        System.out.println("Conexión con cliente cerrada.");
                    } catch (IOException | ClassNotFoundException ex) {
                        System.out.println("Error de excepción: " + ex);
                    }
                });

                clientThread.start(); //Inicia el hilo del cliente
            }
        } catch (IOException ex) {
            System.out.println("Error de excepción: " + ex);
        }
    }
}
