package main;

import sockets.ClienteUDP;

public class MainCliente {

  
    public static void main(String[] args) {
        int puerto_destino = 12345;
        int puerto_local =  34567;
        String ip = "localhost"; //también sirve: 127.0.0.1
        String mensaje = "Enviando saludos al servidor";
        
        //Se llama a la funcion encargada de crear un socket y enviar los datos al servidor indicado
        String resultado = ClienteUDP.enviarMensajeServidor(puerto_local, puerto_destino, ip, mensaje);
        System.out.println(resultado);
    }

}
