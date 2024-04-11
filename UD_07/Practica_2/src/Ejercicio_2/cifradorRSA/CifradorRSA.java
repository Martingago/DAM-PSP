package Ejercicio_2.cifradorRSA;

import java.security.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.*;

public class CifradorRSA {

    private KeyPair claves; //Generador de pares de claves
    private KeyPairGenerator generadorClaves; //Generador de claves RSA
    private Cipher cifrador; //Objeto encargado del cifrado/Descifrado

    /**
     * Se instancian las variables de la clase generadoras del algoritmo RSA.
     *
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     */
    public CifradorRSA() throws NoSuchAlgorithmException, NoSuchPaddingException {
        generadorClaves = KeyPairGenerator.getInstance("RSA");
        generadorClaves.initialize(2048);
        claves = generadorClaves.generateKeyPair();
        cifrador = Cipher.getInstance("RSA");
    }

    /**
     * Método getter clave pública
     *
     * @return
     */
    public PublicKey getPublica() {
        return claves.getPublic();
    }

    /**
     * Método getter clave privada
     *
     * @return
     */
    public PrivateKey getPrivada() {
        return claves.getPrivate();
    }

    /**
     * Método para cifrar los datos Se inicializa el Cipher en modo ENCRYPT con
     * la clave proporcionada, se cifran los resultados, y de devuelve el
     * mensaje cifrado
     *
     * @param paraCifrar array de bytes para cifrar
     * @param claveCifrado clave publica para cifrar
     * @return
     */
    public byte[] cifrar(byte[] paraCifrar, Key claveCifrado) {
        try {
            cifrador.init(Cipher.ENCRYPT_MODE, claveCifrado);
            return cifrador.doFinal(paraCifrar);
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            System.out.println("Error: " + ex);
        }
        return null;
    }

    /**
     * Método para descrifrar los datos. Se inicializa el Cipher en modo DECRYPT
     * con la clave privada proporcionada, se descifran los resutados y devuelve
     * un array de bytes
     *
     * @param paraDescifrar array de bytes a descifrar
     * @param claveDescifrado
     * @return
     */
    public byte[] descifrar(byte[] paraDescifrar, Key claveDescifrado) {
        try {
            cifrador.init(Cipher.DECRYPT_MODE, claveDescifrado);
            return cifrador.doFinal(paraDescifrar);
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException ex) {
            System.out.println("Error: " + ex);
        }
        return null;
    }
    
        /**
     * Lee los datos cifrados que recibe y devuelve un String
     * @param cifradorRSA
     * @param datosCifrados
     * @return 
     */
    public String leerDatos(CifradorRSA cifradorRSA, byte[] datosCifrados){
        byte[] msgDescifrado = cifradorRSA.descifrar(datosCifrados, cifradorRSA.getPrivada());
        return new String(msgDescifrado);
    }
    
    
}
