package main;

import java.security.*;
import java.util.Arrays;
import java.util.Scanner;
import javax.crypto.*;

public class GestorCifradoRSA {

    private static KeyPair claves; //Generador de pares de claves
    private static KeyPairGenerator generadorClaves; //Generador de claves RSA
    private static Cipher cifrador; //Objeto encargado del cifrado/Descifrado

    public static void main(String[] args) throws Exception{
        GestorCifradoRSA gestor = new GestorCifradoRSA();
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce texto para encriptar: ");
        String mensaje = sc.nextLine();
        
        byte[] mensajeCifrado = gestor.cifrar(mensaje.getBytes(), gestor.getPublica());
        System.out.println("Mensaje cifrado: " + Arrays.toString(mensajeCifrado));
        
        byte[] mensajeDesencriptado = gestor.descifrar(mensajeCifrado, gestor.getPrivada());
        System.out.println("Mensaje desencriptado: " + new String(mensajeDesencriptado));
        
    }
    /**
     * Se instancian las variables de la clase generadoras del algoritmo RSA.
     * 
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException 
     */
    public GestorCifradoRSA() throws NoSuchAlgorithmException, NoSuchPaddingException {
        generadorClaves = KeyPairGenerator.getInstance("RSA");
        generadorClaves.initialize(1024);
        claves = generadorClaves.generateKeyPair();
        cifrador = Cipher.getInstance("RSA");
    }

    /**
     * Método getter clave pública
     * @return 
     */
    public static PublicKey getPublica() {
        return claves.getPublic();
    }
    
    /**
     * Método getter clave privada
     * @return 
     */
    public static PrivateKey getPrivada() {
        return claves.getPrivate();
    }

    /**
     * Método para cifrar los datos
     * Se inicializa el Cipher en modo ENCRYPT  con la clave proporcionada, se cifran los resultados, y de devuelve
     * el mensaje cifrado
     * @param paraCifrar array de bytes para cifrar
     * @param claveCifrado clave publica para cifrar
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException 
     */
    public byte[] cifrar(byte[] paraCifrar, Key claveCifrado)
            throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        cifrador.init(Cipher.ENCRYPT_MODE, claveCifrado);
        return cifrador.doFinal(paraCifrar);
    }

    /**
     * Método para descrifrar los datos.
     * Se inicializa el Cipher en modo DECRYPT con la clave privada proporcionada, se descifran los resutados y devuelve un array de bytes 
     * @param paraDescifrar array de bytes a descifrar 
     * @param claveDescifrado
     * @return
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException 
     */
    public static byte[] descifrar(byte[] paraDescifrar, Key claveDescifrado)
            throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        cifrador.init(Cipher.DECRYPT_MODE, claveDescifrado);
        return cifrador.doFinal(paraDescifrar);
    }

}
