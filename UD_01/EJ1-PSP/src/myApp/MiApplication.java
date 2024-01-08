
package myApp;

import java.io.File;
import java.io.IOException;
public class MiApplication {
    public static void main(String[] args){
        File f1 = new File("proceso1.txt");
        File f2 = new File("proceso2.txt");
       //Proceso1 
       ProcessBuilder pb1 = new ProcessBuilder("java" , "-cp", ".", "MiProcessBuilder", "10", "20", "1500");
       pb1.redirectOutput(ProcessBuilder.Redirect.appendTo(f1));
       pb1.redirectError(ProcessBuilder.Redirect.appendTo(f1)
        );
       //Proceso2
       ProcessBuilder pb2 = new ProcessBuilder("java", "MiProcessBuilder", "10", "2", "300"); 
       pb2.redirectOutput(ProcessBuilder.Redirect.appendTo(f2));
       pb2.redirectError(ProcessBuilder.Redirect.appendTo(f2));
        
       try{
       pb1.start();
       pb2.start();
       }catch(IOException e){
           System.out.println("Error en el proceso"+ e);
       }
    }

}
