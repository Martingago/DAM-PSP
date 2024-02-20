package cliente;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientThread extends Thread {

    private DataInputStream inSocket; //entrada de datos
    private DataOutputStream outSocket; //salida de datos
    private Socket socket;
    private BufferedReader reader;
    private Boolean run = true;
    /**
     * Constuctor del hilo cliente El constructor debe recibir una referencia
     * del socket al que está conectado para asi obtener de dicha referencia el
     * bufferedReader y poder mostrar por consola los mensajes que recibe desde
     * este socket (servidor).
     *
     * @param socket
     */
    public ClientThread(Socket socket) {
        try {
            this.socket = socket;
            this.inSocket = new DataInputStream(socket.getInputStream());
            this.outSocket = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Error al establecer el buffer de salida de datos");
        }
    }

    /**
     * Hilo encargado de mostrar al usuario toda la informacion proveniente del
     * servidor en un hilo específico para asi evitar bloquear la ejecucion de
     * codigo
     */
    @Override
    public void run() {
        try {
            while (run) {
                String respuesta = inSocket.readUTF();
                System.out.println(respuesta);
                if(respuesta.equals("sair()")){
                    System.out.println("Se ha cerrado la conexion");
                    run = false;
                }
            }
        } catch (IOException e) {
            System.out.println("Error! " + e);
        }
        System.out.println("cerrando hilo");
    }

    public void stopThread(){
        this.run = false;
    }

}
