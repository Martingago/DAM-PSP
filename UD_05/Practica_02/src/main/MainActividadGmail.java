package main;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.Properties;
import java.io.*;

public class MainActividadGmail {

    //Actividad 2 pero empleando el servicio de GMAIL para poder enviar correos.
    public static void main(String[] args) {

        //Credenciales para la cuenta de GMAIL
        String username = "magachenvio@gmail.com"; 
        String password = "bhth wvir bczs bmax";
        
        String mailTo = "magachrecibido@yopmail.com, chgnitram@gmail.com"; //email que recibe el mensaje
        
        String documentoAdjunto = "adjunto.txt"; //documento adjunto (por defecto en la raiz del proyecto)

        // Le as propiedades do servidor SMTP do arquivo smtp.properties
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("./smtp.gmail.properties")); //fichero que contiene las propiedades smtp para enviar correos desde gmail
        } catch (IOException e) {
            throw new RuntimeException("No se ha encontrado el fichero smtp.gmail.properties", e);
        }

        // Crea unha nova sesión SMTP con as propiedades especificadas
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        
        session.setDebug(true);
        
        try {
            // Crea un novo correo electrónico
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username)); // Remitente
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo)); // Destinatarios
            message.setSubject("Asunto do correo"); // Asunto

            //Crea el cuerpo del mensaje
            BodyPart cuerpoMensaje = new MimeBodyPart();
            cuerpoMensaje.setText("Este es el cuerpo de un mensaje");
            
            //Crea un multipart
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(cuerpoMensaje);
            
            if(documentoAdjunto != null){
                cuerpoMensaje = new MimeBodyPart();
                DataSource source = new FileDataSource(documentoAdjunto);
                cuerpoMensaje.setDataHandler(new DataHandler(source));
                cuerpoMensaje.setFileName(documentoAdjunto);
                multipart.addBodyPart(cuerpoMensaje);
                      
            }
            message.setContent(multipart);
            // Envia o correo electrónico
            Transport.send(message);

            // Imprime unha mensaxe de confirmación na consola
            System.out.println("Correo electrónico enviado con éxito!");

        } catch (MessagingException e) {
            // Xestiona calquera excepción que poida ocorrer durante o proceso de envío do correo electrónico
            throw new RuntimeException(e);
        }

    }
}
