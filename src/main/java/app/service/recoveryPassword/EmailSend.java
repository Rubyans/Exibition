package app.service.recoveryPassword;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSend {
    private static final Logger LOGGER = Logger.getLogger(EmailSend.class);

    public static void mailSender(String email,String password) {
        try (InputStream input = EmailSend.class.getClassLoader().getResourceAsStream("mail.properties")) {
            Properties propertyEmail = new Properties();
            propertyEmail.load(input);

            Session mailSession = Session.getDefaultInstance(propertyEmail, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(propertyEmail.getProperty("mail.myemail"), propertyEmail.getProperty("mail.mypassword"));
                }
            });
            Message message = new MimeMessage(mailSession);
            message.setFrom(new InternetAddress(propertyEmail.getProperty("mail.mypassword")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Відновлення паролю");
            message.setText("Your PIN-CODE - "+password);
            Transport.send(message);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
