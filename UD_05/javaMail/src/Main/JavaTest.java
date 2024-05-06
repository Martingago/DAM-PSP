package Main;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class JavaTest {

    public static void main(String[] args) {
        //En esta actividad el correo no tiene un auth true, lo que hace que no sea necesario escribir la contraseña ni crear una session con
        // getAuthPassword. De hecho en yopmail sale un aviso que el correo no se está enviando desde un gmail.
        String mailFrom = "magachenvio@gmail.com";
        String mailTo = "magachrecibido@yopmail.com";

        Properties props = new Properties();
        
        props.put("mail.smtp.host", "localhost");
        props.put("mail.smtp.port", "25");

        // Se Crea una sesión SMTP con las propiedades especificadas
       Session session = Session.getDefaultInstance(props);
        session.setDebug(true);

        try {
            // Crea el cuerpo de un correo electronico:
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailFrom)); // Remitente
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailTo)); // Destinatario

            message.setSubject("Asunto do correo"); // Asunto
            message.setText("Corpo da mensaxe"); // Cuerpo del mensaje

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
