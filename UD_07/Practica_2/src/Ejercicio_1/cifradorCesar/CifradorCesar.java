
package Ejercicio_1.cifradorCesar;


public class CifradorCesar {

    private String alfabeto;
    private String alfabetoCifrado;

    /**
     * Constructor que crea e inicializa tanto el alfabeto, como el diccionario del alfabeto
     * @param cifrador valor int para cifrar el alfabeto.
     */
    public CifradorCesar(int cifrador){
        alfabeto = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ0123456789-";
        rotar(alfabeto, cifrador);
    }
    
    /**
     * Rota las posiciones del alfabeto "n" veces y crea un nuevo alfabetoCifrado
     * @param cad alfabeto sin cifrar
     * @param veces numero de veces que se rota el alfabeto.
     * 
     * Al rotar el abecedario se crea un nuevo abecedarioCifrado que actúa como diccionario en el que la
     * posicion de cada letra se corresponde al valor del abecesario original.
     */
    public void rotar(String cad, int veces) {
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
    public String cifrar(String mensaje, String clave) {
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
    public String descifrar(String mensaje, String clave) {
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

    public String getAlfabeto() {
        return alfabeto;
    }

    public String getAlfabetoCifrado() {
        return alfabetoCifrado;
    }
}

