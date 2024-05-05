package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MainActividadYopmail {

    public static void main(String[] args) {

        //Credenciales para la cuenta de GMAIL
        String username = "magachenvio@gmail.com";
        String password = "bhth wvir bczs bmax";
        String mailTo = "magachrecibido@yopmail.com"; //email que recibe el mensaje

        String documentoAdjunto = "adjunto.txt"; //documento adjunto (por defecto en la raiz del proyecto)

        // Le as propiedades do servidor SMTP do arquivo smtp.properties
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("./smtp.local.properties"));
        } catch (IOException e) {
            throw new RuntimeException("No se ha encontrado el fichero smtp.local.properties", e);
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

            if (documentoAdjunto != null) {
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
