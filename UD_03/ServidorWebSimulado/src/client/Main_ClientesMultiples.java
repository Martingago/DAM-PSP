package client;

public class Main_ClientesMultiples {

    // Crea un programa principal que inicie varios clientes simultaneamente
    public static void main(String[] args) {
        // Este sería el punto principal del ejercicio.
        // Ejecutar el fichero de server.ClientHandler para lanzar el servidor.

        // Se inician varios clientes simultáneamente:
        for (int i = 0; i < 10; i++) {
            // Crear un nuevo hilo para ejecutar un cliente
            Thread clientThread = new Thread(new ClienteRunnable());
            clientThread.start();
        }
    }

    // Clase interna que ejecuta la clase client.SimpleTextWebClient
    // Emulará el cliente y sus acciones en el servidor
    private static class ClienteRunnable implements Runnable {
        @Override
        public void run() {
            // Crea una instancia de SimpleTextWebClient y ejecuta su método main
            SimpleTextWebClient.main(new String[]{});
        }
    }
}
