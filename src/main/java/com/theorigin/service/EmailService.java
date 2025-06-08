package main.java.com.theorigin.service;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailService{
    private final String senderEmail = "thesubscriptioninfo@gmail.com"; 
    private final String senderPassword = "mbjw ilwq fvjo acyn"; // replace with your actual email password
    
    private Session createSession(){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // Gmail SMTP server
        props.put("mail.smtp.port", "587"); // TLS port for Gmail
        props.put("mail.smtp.auth", "true"); // Enable authentication
        props.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS

        return Session .getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });
    }

    public void sendEmail(String recipientEmail,String subject,String body){
        try {
            Message message = new MimeMessage(createSession());
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(body);
        
            Transport.send(message);
            System.out.println("Email sent successfully");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}