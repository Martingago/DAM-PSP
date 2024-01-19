package main;

import sockets.SimpleTextWebClient;
import sockets.SimpleTextWebServer;

public class MainProgram {

    public static void main(String[] args) {
        // TODO code application logic here
       SimpleTextWebServer servidor = new SimpleTextWebServer(8080); 
       SimpleTextWebClient cliente;
       for(int i =0; i< 10; i++){
           cliente = new SimpleTextWebClient();
       }
       
       
        
    }

}
