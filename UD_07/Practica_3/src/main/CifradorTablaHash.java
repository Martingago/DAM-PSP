package main;

import java.util.HashMap;
import java.util.Scanner;

public class CifradorTablaHash {

    private HashMap<Character, Character> taboaHash; //Diccionario CARACTER - CLAVE
    private HashMap<Character, Character> taboaHashDesencriptacion; //Diccionario CLAVE - CARACTER

    /**
     * Constructor del cifrador que actuará como diccionario
     * El constructor llama al método crearDiccionario el cual generará automaticamente los diccionarios
     * Tanto de encriptacion como de desencriptacion de la actividad.
     */
    public CifradorTablaHash(int rotacion) {
        crearDiccionario(rotacion);
    }

    public String cifrar(String mensaxe) {
        StringBuilder msgCifrado = new StringBuilder();
        //Se recorren todos los caracteres de la cadena de texto
        for (int i = 0; i < mensaxe.length(); i++) {
            char c = mensaxe.charAt(i);
            //Si el map contiene el caracter que queremos codificar, entonces obtenemos su hash y lo añadimos a la cadena de msgCifrado
            if (taboaHash.containsKey(c)) {

                msgCifrado.append(taboaHash.get(c));
            }
        }
        return msgCifrado.toString();
    }

    public String descifrar(String mensaxe) {
        StringBuilder msgCifrado = new StringBuilder();
        //Se recorren todos los caracteres de la cadena de texto
        for (int i = 0; i < mensaxe.length(); i++) {
            char c = mensaxe.charAt(i);
            //Si el map contiene el caracter que queremos codificar, entonces obtenemos su hash y lo añadimos a la cadena de msgCifrado
            if (taboaHashDesencriptacion.containsKey(c)) {

                msgCifrado.append(taboaHashDesencriptacion.get(c));
            }
        }
        return msgCifrado.toString();
    }

    /**
     * Crea 2 diccionarios para la encriptación y desencriptacion
     *
     * @param rotacion que tendrá el hashMap
     *
     * Para hacer mas eficiente la encriptacion y desencriptacion se crean 2 diccionarios: 
     *      taboaHash: contiene los valores de caracter => clave : Este se usa para encriptar 
     *      taboaHashDesencriptacion: contiene los valores de clave => caracter : Este se usa para desencriptar
     */
    public void crearDiccionario(int rotacion) {
        taboaHash = new HashMap<>();
        taboaHashDesencriptacion = new HashMap<>();

        // Se carga el hashMap con todos los caracteres rotacion y se le atribuye a cada uno un hash
        for (int i = 0; i < 256; i++) {
            taboaHash.put((char) i, (char) rotacion);
            taboaHashDesencriptacion.put((char) rotacion, (char) i);
            rotacion = (rotacion + 1) % 256; // Esto asegura que el valor de rotacion siempre esté en el rango 0-255
        }

    }

    public static void main(String[] args) {
        /**
         * Se crea un objeto cifrador, recibe como parametro el numero de
         * rotaciones, y este constructor llama al método crearDiccionario que
         * generará 2 diccionarios (encriptacion y desencriptacion) para mejorar
         * la eficiencia de DESENCRIPTACION, ya que con un único diccionario en
         * caso de querer desencriptar, tendríamos que recorrer todo el
         * diccionario buscando en el valor de map y tendríamos que obtener su
         * clave para obtener así la desencriptacion. Con éste método tanto en
         * encriptacion como en desencriptacion podemos buscar por la clave, lo
         * que mejora considerablemente la eficiencia algorítmica.
         */

        CifradorTablaHash cifrador = new CifradorTablaHash(12);
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce texto a cifrar: ");
        String text = sc.nextLine();
        String cifrado = cifrador.cifrar(text);
        String descifrado = cifrador.descifrar(cifrado);
        System.out.println("texto cifrado: " + cifrado);
        System.out.println("Texto descifrado: " + descifrado);

    }

}
