package servidor;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ServidorThread extends Thread {

    private DataInputStream inSocket; //entrada de datos
    private Socket socket;
    private PrintWriter output;

    public ServidorThread(Socket socket) throws IOException {
        this.socket = socket;
        this.inSocket = new DataInputStream(socket.getInputStream());

    }

    //hilo que permite en el servidor leer los datos recibidos desde el lado cliente
    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                String salidaTexto = inSocket.readUTF();
                System.out.println("<<<<: " + salidaTexto);
                if (salidaTexto.equals("sair()")) {
                    System.out.println("El cliente ha cerrado la conexion");
                    socket.close();
                    break;
                }
            }
        } catch (IOException ex) {
            System.out.println("El socket está cerrado.");
        } finally {
            try {
                if (socket != null && socket.isClosed()) {
                    socket.close();
                }
                System.exit(0);
            } catch (IOException ex) {
                System.out.println("Error de excepcion en el hilo del servidor: " + ex);
            }

        }

    }

}
