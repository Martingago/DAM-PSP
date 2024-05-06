package ud5_practica_3;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

public class UD5_Practica_3 {

    //Actividad 3 empleando el servicio de GMAIL para poder enviar correos.
    public static void main(String[] args) {

        //Credenciales para la cuenta de GMAIL
        String username = "magachenvio@gmail.com"; //mail de prueba
        String password = "bhth wvir bczs bmax"; //password de aplicación configurado

        String mailTo = "magachrecibido@yopmail.com, gagochorenmartin@gmail.com"; //email que recibe el mensaje

        // Le as propiedades del servidor SMTP del archivo smtp.properties
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("./smtp.properties")); //fichero que contiene las propiedades smtp para enviar correos desde gmail
        } catch (IOException e) {
            throw new RuntimeException("No se ha encontrado el fichero smtp.properties", e);
        }

        // Crea una sesión SMIT con las propiedades especificadas
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

            //Multipart para el cuerpo y el mensaje.
            MimeMultipart multipart = new MimeMultipart("related");

            //Cuerpo
            BodyPart cuerpo = new MimeBodyPart();
            String cuerpoHTML = "<h1>Este es el titulo del cuerpo del correo</h1>"
                    + "<p>Este párrafo es contenido que estoy enviendo por correo electrónico a través de una aplicación JAVA!</p>"
                    + "<img src=\\\"cid:imageCorreo\\\">";

            cuerpo.setContent(cuerpoHTML, "text/html");
            multipart.addBodyPart(cuerpo);

            // Imagen incrustada
            cuerpo = new MimeBodyPart();
            DataSource fds = new FileDataSource("./image.jpg"); // Ruta a la imagen
            cuerpo.setDataHandler(new DataHandler(fds));
            cuerpo.setHeader("Content-ID", "<imageCorreo>");
            cuerpo.setHeader("Content-Disposition", "inline; filename=\"imagen_adjunta.jpg\""); // Añade el nombre de la imagen
            multipart.addBodyPart(cuerpo);

            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Correo electrónico enviado con éxito!");

        } catch (MessagingException e) {
            // Xestiona calquera excepción que poida ocorrer durante o proceso de envío do correo electrónico
            throw new RuntimeException(e);
        }

    }

}
