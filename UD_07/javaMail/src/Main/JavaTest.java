package Main;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class JavaTest {

    public static void main(String[] args) {
        // Configura as propiedades do servidor SMTP
        String mailFrom = "magachenvio@gmail.com";
        String password = "bhth wvir bczs bmax"; //password del email que envia el mensaje
        String mailTo = "magachrecibido@yopmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.host", "localhost");
        props.put("mail.smtp.port", "25");

        // Crea unha nova sesión SMTP con as propiedades especificadas
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(mailFrom, password);
            }
        });
        session.setDebug(true);

        try {
            // Crea un novo correo electrónico
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailFrom)); // Remitente
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo)); // Destinatario

            message.setSubject("Asunto do correo"); // Asunto
            message.setText("Corpo da mensaxe"); // Corpo da mensaxe

            // Envia o correo electrónico
            Transport t = session.getTransport("smtp");
            t.connect();
            t.sendMessage(message, message.getAllRecipients());
            t.close();

            // Imprime unha mensaxe de confirmación na consola
            System.out.println("Correo electrónico enviado con éxito!");

        } catch (MessagingException e) {
            // Xestiona calquera excepción que poida ocorrer durante o proceso de envío do correo electrónico
            throw new RuntimeException(e);
        }
    }
}
