package main;

import java.util.Scanner;

public class CifradorCesar {

    private static String alfabeto = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ0123456789-";
    private static String alfabetoCifrado;
    public static void main(String[] args) {
        rotar(alfabeto, 1);
        System.out.println("Introduce el texto a cifrar:");
        Scanner sc = new Scanner(System.in);
        String textToEncript = sc.nextLine();
        String cif = cifrar(textToEncript, alfabetoCifrado);
        System.out.println("Texto cifrado: " + cif);
        String descif = descifrar(cif, alfabetoCifrado);
        System.out.println("Texto descifrado: " + descif);

    }

    /**
     * Rota las posiciones del alfabeto "n" veces y crea un nuevo alfabetoCifrado
     * @param cad alfabeto sin cifrar
     * @param veces numero de veces que se rota el alfabeto.
     * 
     * Al rotar el abecedario se crea un nuevo abecedarioCifrado que actúa como diccionario en el que la
     * posicion de cada letra se corresponde al valor del abecesario original.
     */
    public static void rotar(String cad, int veces) {
        alfabetoCifrado = alfabeto.substring(veces) + alfabeto.substring(0, veces);
    }

    /**
     * Cifra un mensaje empleando como diccionario el alfabetoCifrado.
     * @param mensaje mensaje a cifrar
     * @param clave diccionario alfabetoCifrado
     * @return texto cifrado
     * 
     * Emplea el abecedarioCifrado como diccionario.
     * Recorre todo el texto a cifrar, sobre cada letra se obtiene su posicion en el abecedario original y dicha posicion
     * se establece en el abecedarioCifrado. Obteniendo así un texto rotado tantas veces como se haya establecido en la 
     * función de rotar.
     * 
     */
    public static String cifrar(String mensaje, String clave) {
        String cifrado = "";
        mensaje = mensaje.toUpperCase();
        char caracter;
        for (int i = 0; i < mensaje.length(); i++) {
            caracter = mensaje.charAt(i);
            int pos = alfabeto.indexOf(caracter);
            if (pos == -1) {
                cifrado += caracter;
            } else {
                cifrado += clave.charAt(pos);
            }
        }

        return cifrado;
    }

    /**
     * Desencripta un mensaje empleando el diccionario del alfabetoCifrado
     * @param mensaje mensaje a descifrar
     * @param clave diccionario con el que se realizará la desenciptación.
     * @return texto desencriptado.
     * 
     * Emplea el abecedarioCifrado como diccionario.
     * Recorre el texto cifrado y sobre cada letra obtiene su posición en el abecedarioCifrado y dicha posición
     * la establece con la del abecedario original. Obtiene así que letra original se corresponde con la cifrada.
     */
    public static String descifrar(String mensaje, String clave) {
        String descifrado = "";
        mensaje = mensaje.toUpperCase();

        char caracter;
        for (int i = 0; i < mensaje.length(); i++) {
            caracter = mensaje.charAt(i);
            int pos = clave.indexOf(caracter);
            if (pos == -1) {
                descifrado += caracter;
            } else {
              descifrado += alfabeto.charAt(pos);
            }
        }
        return descifrado;
    }

}
