package sockets;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.*;
import java.net.*;

public class SimpleTextWebServer {
    private int puerto;
    
    public SimpleTextWebServer(int puerto){
        this.puerto = puerto;
    }
    
    public static void main(String[] args) {
        
        try {
            //Se inicializa el servidor
            int puerto = 8080;
            HttpServer httpd = HttpServer.create(new InetSocketAddress(puerto),0);
            HttpContext content = httpd.createContext("/");
            
            content.setHandler(SimpleTextWebServer::gestionarSolicitud);
            httpd.start();
            System.out.println("Servidor iniciado.");
            
            //while(true){
            //Se aceptaran conexiones
            
            
            //Por cada cliente que se recibe se crea un hilo que haga su gestión 
            // y se pasa su socket para posteriormente cerrarlo dentro del propio hilo cuando se termine de procesar su información.
            
            
            //ClientHandler manejo = new ClientHandler(socket);
            //Thread hilo_manejo_cliente = new Thread(manejo);
            //hilo_manejo_cliente.start();
            //}
            
        } catch (IOException ex) {
            System.out.println("Error de excepción en el servidor \n" + ex);
        }
        
    }
    
    private static void gestionarSolicitud(HttpExchange exchange){
        try {
            final int codigo_respuesta = 200;
            String contenido = "Respuesta desde el Servidor!";
            exchange.sendResponseHeaders(codigo_respuesta, contenido.getBytes().length);
            mostrarHeadersSolicitud(exchange);
            OutputStream os = exchange.getResponseBody();
            os.write(contenido.getBytes());
            os.close();
        } catch (IOException ex) {
            System.out.println("Error exception manejando la gestion de solicitud");
        }
    }
    
    private static void mostrarHeadersSolicitud(HttpExchange exchange){
        System.out.println("Encabezados: ");
        exchange.getRequestHeaders().entrySet().forEach(System.out::println);
        System.out.println("");
        System.out.println("Método: " + exchange.getRequestMethod());
        System.out.println("\nQUERY: ");
        URI uri = exchange.getRequestURI();
        System.out.println(uri.getQuery());
    }
    
    
    
    public void iniciarServidor(){
        try{
            ServerSocket serverSocket = new ServerSocket(puerto);
            System.out.println("El servidor se ha iniciado en el puerto: " + puerto);
        }catch(IOException ex){
            System.out.println("Se ha producido un error de excepción: " + ex);
        }
    }
        

}
