
package cliente;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteMain {
  
    
    public static void main(String[] args) {
        DataOutputStream outSocket; //salida de datos
        
        Scanner sc = new Scanner(System.in);
        //System.out.println("Introduce la dirección ip a la que te quieres conectar:");
        //String DIRECCION = sc.nextLine();
        String DIRECCION = "localhost";
        //System.out.println("Introduce el PUERTO al que te quieres conectar");
        //int PUERTO = sc.nextInt();
        int PUERTO = 6000;

        //Se realiza la conexion con el socket
        try {
            Socket socket = new Socket(DIRECCION, PUERTO);
            outSocket = new DataOutputStream(socket.getOutputStream());
            
            //Si la conexion es exitosa, se crea un hilo de cliente encargado de leer toda la informacion procedente del servidor:
            ClientThread hiloLectura = new ClientThread(socket);
            hiloLectura.start();
            
            String mensajeCliente = null;
            do{
                mensajeCliente = sc.nextLine();
                outSocket.writeUTF("<<<<: "+ mensajeCliente);
            
            }while(!mensajeCliente.equals("sair()"));
            hiloLectura.stopThread();
            System.out.println("saliendo de la app...");
            
            
        } catch (IOException ex) {
            System.out.println("No se ha podido realiza la conexión a: \n" + DIRECCION + " con el puerto: "+ PUERTO);
        }
        
        
        
    }

}
