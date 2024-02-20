package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ServidorThread extends Thread {

    private DataInputStream inSocket; //entrada de datos
    private Socket socket;
    private PrintWriter output;
    private boolean run = true;

    public ServidorThread(Socket socket) throws IOException {
        this.socket = socket;
        this.inSocket = new DataInputStream(socket.getInputStream());

    }

    //hilo que permite en el servidor leer los datos recibidos desde el lado cliente
    @Override
    public void run() {
        while (run) {
            try {
                String salidaTexto = inSocket.readUTF();
                System.out.println(salidaTexto);
                if (salidaTexto.equals("sair()")) {
                    run = false;
                }
            } catch (IOException ex) {
                System.out.println("Error en la lectura de datos");
                break;
            }
        }
        System.out.println("Terminado el hilo del servidor");
    }

    //Detiene el hilo principal
    public void stopThread() {
        this.run = false;
    }

}
