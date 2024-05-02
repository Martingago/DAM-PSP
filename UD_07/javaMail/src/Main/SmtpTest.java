package Main;

import java.io.*;
import java.util.*;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

public class SmtpTest {

    public static void main(String[] args) {
        // outMail login Details
        final String outmailUsername = "outmail-username";
        final String outmailPassword = "outmail-password";

        // Recipient's Email Address
        String toEmail = "magachrecibo@yopmail.com";

        // Sender's Email Address
        String fromEmail = "magachenvio@yopmail.com";
        String fromEmailName = "Firstname Lastname";

        // Set the email properties
        Properties props = new Properties();
        props.put("mail.smtp.host", "localhost");	
        props.put("mail.smtp.port", "25"); 						
        props.put("mail.smtp.auth", "true"); 						

        // Create an authenticator object 
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(outmailUsername, outmailPassword);
            }
        };

        Session session = Session.getInstance(props, auth);

        try {
            MimeMessage msg = new MimeMessage(session);

            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("Content-Transfer-Encoding", "8bit");
            msg.setSentDate(new Date());

            msg.setFrom(new InternetAddress(fromEmail, fromEmailName));
            msg.setReplyTo(InternetAddress.parse(fromEmail, false));

            msg.setSubject("Subject of the email goes here", "UTF-8");

            msg.setText("Body of the message goes here", "UTF-8");

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

            Transport.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
