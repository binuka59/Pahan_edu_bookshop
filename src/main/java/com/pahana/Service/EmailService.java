package com.pahana.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {
	 public void sendEmail(String recipientEmail, String messageBody, String subject) {
	        System.setProperty("https.protocols", "TLSv1.2,TLSv1.3");
	        // SMTP Properties
	        Properties properties = new Properties();
	        properties.put("mail.smtp.host", "smtp.gmail.com");
	        properties.put("mail.smtp.port", "465"); // ✅ Use SSL port
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.ssl.enable", "true");
	        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	        properties.put("mail.smtp.ssl.protocols", "TLSv1.2 TLSv1.3"); // ✅ Force TLS 1.2+
	        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");


	        // Create a session with authentication
	        Session session = Session.getInstance(properties, new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("binuka.lakshan9876@gmail.com", "ytmx gzsn melz qiuh"); // Use app password, not regular password
	            }
	        });

	        try {
	            // Create a message
	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("binuka.lakshan9876@gmail.com")); // Sender email
	            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
	            message.setSubject(subject);
	            message.setText(messageBody);

	            // Send the email
	            Transport.send(message);
	            System.out.println("Email sent successfully!");

	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }

}
