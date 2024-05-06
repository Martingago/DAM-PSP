package main.ud5_practica_1;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class UD5_Practica_1 {

    public static void main(String[] args) {
        Properties props = new Properties();

        String mailFrom = "magachenvio@yopmail.com"; //email que envia el mensaje
        String mailTo = "magachrecibo@yopmail.com"; //email que recibe el mensaje
        //Confguración del servidor SMTP
        props.put("mail.smtp.host", "localhost");
        props.put("mail.smtp.port", "25");

        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailFrom)); //Se establece la direccion del email que envia
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));

            message.setSubject("Este es el título del mail");

            // Fill the message
            message.setText("Este es el cuerpo del correo electrónico que voy a mandar como parte de la actividad");
            Transport t = session.getTransport("smtp");
            Transport.send(message, message.getAllRecipients());
            t.close();
            System.out.println("Se ha enviado el mensaje");
        } catch (MessagingException me) {
            //Aqui se deberia o mostrar un mensaje de error o en lugar
            //de no hacer nada con la excepcion, lanzarla para que el modulo
            //superior la capture y avise al usuario con un popup, por ejemplo.
            System.out.println("Error al enviar el correo" + me);
        }
    }
}
