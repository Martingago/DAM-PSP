package main;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.Properties;
import java.io.*;

public class MainActividad {

    public static void main(String[] args) {

        String mailFrom = "magachenvio@gmail.com"; //email que envia el mensaje
        String password = "bhth wvir bczs bmax"; //password del email que envia el mensaje
        String mailTo = "magachrecibido@yopmail.com, magachenviado@yopmail.com"; //email que recibe el mensaje

        // Le as propiedades do servidor SMTP do arquivo smtp.properties
        Properties props = new Properties();
        try {
            props.load(new FileInputStream("./smtp.properties.txt"));
        } catch (IOException e) {
            throw new RuntimeException("Non se puido ler o arquivo smtp.properties.txt", e);
        }

        // Crea unha nova sesión SMTP con as propiedades especificadas
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailFrom, password);
            }
        });
        session.setDebug(true);
        try {
            // Crea un novo correo electrónico
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailFrom)); // Remitente
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo)); // Destinatarios
            message.setSubject("Asunto do correo"); // Asunto

            // Crea a mensaxe
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("Corpo da mensaxe"); // Corpo da mensaxe

            // Crea o anexo
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            String filename = "./adjunto.txt";
            DataSource source = new FileDataSource(filename);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName(filename);

            // Engade o corpo da mensaxe e o anexo ao correo electrónico
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(attachmentBodyPart);
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
