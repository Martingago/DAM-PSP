package ejercicio5_GeneradorMatrices;

public class GeneradorAleatorioDeMatrices {

    /**
     * Genera una cadena de texto que es una inicialización de una matriz en
     * lenguaje de JAVA ESTO ES UN EXTRA PARA PODER GENERAR TEST SIEMPRE CON LOS
     * MISMOS VALORES;
     *
     * @param nombreMatriz
     * @param columnas
     * @param filas
     * @return
     */
    public static String generadorAleatorioMatrices(String nombreMatriz, int columnas, int filas) {
        StringBuilder sb = new StringBuilder();
        sb.append("public int[][] " + nombreMatriz + " = { \n");

        for (int i = 0; i < filas; i++) {
            sb.append("{");
            for (int j = 0; j < columnas; j++) {
                int valor = (int) (Math.random() * 10000);
                sb.append(valor);
                if (j < columnas - 1) {
                    sb.append(", ");
                }
            }
            sb.append("}");
            if (i < filas - 1) {
                sb.append(",\n");
            }
        }

        sb.append("};");
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(generadorAleatorioMatrices("matrizB", 60, 60));
    }

}
