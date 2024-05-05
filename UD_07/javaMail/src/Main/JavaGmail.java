package Main;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaGmail {

    public static void main(String[] args) {
        Properties props = new Properties();
        
        String mailFrom = "magachenvio@gmail.com"; //email que envia el mensaje
        String password = "bhth wvir bczs bmax"; //password del email que envia el mensaje
        String mailTo = "chgnitram@gmail.com"; //email que recibe el mensaje

        // Configuración del servidor SMTP de Google
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true"); //Habilita TLS
        props.setProperty("mail.smtp.port", "587");
        props.setProperty("mail.smtp.user", mailFrom);
        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        props.setProperty("mail.smtp.auth", "true");
        

        Session session = Session.getInstance(props);
        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(mailFrom)); //Se establece la direccion del email que envia
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo)); //Se establece el mail que lo recibe

            message.setSubject("Este es el título del mail");
            message.setText("Este es el cuerpo del correo electrónico que voy a mandar como parte de la actividad");
            
            Transport transport = session.getTransport("smtp");
            transport.connect(mailFrom, password);
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            transport.close();
            System.out.println("Se ha enviado el mensaje");
        } catch (MessagingException me) {
            System.out.println("Error al enviar el correo" + me);
        }
    }
    
}
