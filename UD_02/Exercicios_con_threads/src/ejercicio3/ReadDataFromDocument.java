package ejercicio3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadDataFromDocument {

    /**
     * Función que, pasado un documento, muestra las características del mismo
     * @param path dirección en la que se encuentra el documento a analizar
     * @return int[] con la información del archivo ordenada por:
     * 0 => N.- caracteres
     * 1 => N.- palabras
     * 3 => N.- líneas
     */
    public int[] readCharacteristicsFromDocument(String path) {
        int caracteres = 0; //total de carácteres (incluídos espacios)
        int palabras = 0; //total de  palabras
        int lineas = 0; //total de líneas

        try {
            File fichero = new File(path);
            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);
            String line = null;

            while ((line = br.readLine()) != null) {
                caracteres += line.length();
                lineas++;
                String[] words = line.split("\\s+"); //separa los elementos de la línea por " " y los introduce en un array
                palabras += words.length;
            }
            System.out.println("------------------------------------\n"
                    + "Las características del archivo son:\n"
                    + "Caracteres: " + caracteres + "\n"
                    + "Palabras:  " + palabras + "\n"
                    + "Líneas: " + lineas + "\n"
                    + "------------------------------------");
        } catch (FileNotFoundException ex) {
            System.out.println("No se ha encontrado el fichero especificado" + ex);
        } catch (IOException ex) {
            System.out.println("Error de excepción al leer el fichero:" + ex);
        }
        int[] resultado = {caracteres, palabras, lineas};
        return resultado;
    }

}
