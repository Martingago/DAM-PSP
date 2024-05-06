package ud5_practica_4;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.mail.*;

public class UD5_Practica_4 {

    public static void main(String[] args) {

        //Credenciales para la cuenta de GMAIL
        String username = "magachenvio@gmail.com"; //mail de prueba
        String password = "bhth wvir bczs bmax"; //password de aplicación configurado

        // Le as propiedades del servidor SMTP del archivo smtp.properties
        Properties props = new Properties();
        props.put("mail.imap.ssl.protocols", "TLSv1.1 TLSv1.2 TLSv1.3"); //habilito todos los protocolos de cifrado para que lea los datos

        try {
            props.load(new FileInputStream("./imap.properties")); //fichero que contiene las propiedades smtp para enviar correos desde gmail
        } catch (IOException e) {
            throw new RuntimeException("No se ha encontrado el fichero imap.properties", e);
        }

        // Crea una sesión SMIT con las propiedades especificadas
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        session.setDebug(true);

        try {
            // Conecta al servidor IMAP y accede a la bandeja de entrada
            Store store = session.getStore("imap");
            store.connect("imap.gmail.com", username, password);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            // Lista los asuntos de los correos electrónicos recibidos
            Message[] messages = inbox.getMessages();
            for (Message message : messages) {
                System.out.println("Asunto: " + message.getSubject());
                System.out.println("-----------------------------------------------------\n");
            }

            // Cierra la conexión
            inbox.close(false);
            store.close();

        } catch (MessagingException e) {
            // Xestiona calquera excepción que poida ocorrer durante o proceso de conexión e acceso aos correos electrónicos
            throw new RuntimeException(e);
        }

    }
}
