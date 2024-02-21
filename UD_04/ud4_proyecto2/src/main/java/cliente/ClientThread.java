package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientThread extends Thread {

    private DataInputStream inSocket; //entrada de datos
    private DataOutputStream outSocket; //salida de datos
    private Socket socket;
    
    
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
            this.inSocket = new DataInputStream(this.socket.getInputStream());
            //this.outSocket = new DataOutputStream(this.socket.getOutputStream());
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
            while (!socket.isClosed()) {
                String respuesta = inSocket.readUTF();
                System.out.println("<<<<: "+respuesta);
                if(respuesta.equals("sair()")){
                    System.out.println("El servidor ha cerrado la conexion");
                    socket.close();
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("El socket está cerrado.");
        }finally{
            try {
                if(socket != null && !socket.isClosed()){
                    socket.close();
                }
                System.exit(0);
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
            }
        }
    }

}
