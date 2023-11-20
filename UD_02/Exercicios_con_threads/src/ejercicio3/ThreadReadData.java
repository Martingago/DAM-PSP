package ejercicio3;

public class ThreadReadData implements Runnable {

    public static int total_caracteres = 0; //total de caracteres que tienen todos los archivos pasados como parametro
    public static int total_palabras = 0; //total de palabras que tienen todos los archivos pasados como parametro
    public static int total_lineas = 0; //total de líneas que tienen todos los archivos pasados como parametro

    private String path;

    public ThreadReadData(String path) {
        this.path = path;
    }

    /**
     * Crea un hilo que comprobará las caracterísitcas de un fichero. Dicho hilo
     * esta sincronizado con los atributos de la clase, de forma que si varios
     * hilos se ejecutan al mismo tiempo, los valores se sincronizarán para que
     * no se sobreescriban datos
     */
    @Override
    public void run() {
        synchronized (ThreadReadData.class) {
            ReadDataFromDocument read = new ReadDataFromDocument();
            int[] resultado = read.readCharacteristicsFromDocument(path);

            total_caracteres += resultado[0];
            total_palabras += resultado[1];
            total_lineas += resultado[2];
        }
    }

}
