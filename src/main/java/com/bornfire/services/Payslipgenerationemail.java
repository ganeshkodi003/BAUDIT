package com.bornfire.services;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.bornfire.entities.CandEvalFormEntity;
import com.bornfire.entities.CandEvalFormRep;
import com.bornfire.entities.paystructureentity;
import com.bornfire.entities.paystructurerep;

@Service
public class Payslipgenerationemail {
	
	 @Autowired
	    CandEvalFormRep CandEvalFormRep;
	    @Autowired
		Environment env;
	    @Autowired
	 		paystructurerep Paystructurerep;
	    public String Payslipgenerationemails(String from, String host, String to, String username, String password, String ref_no, List<String> y) {
	        // Get the session object
	        Properties properties = new Properties();
	        properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.port", "587"); // Port for TLS/STARTTLS
	        properties.put("mail.smtp.auth", "true"); // Enable authentication
	        properties.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS
	        System.out.println(y);

	        Session session = Session.getInstance(properties, new Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(username, password);
	            }
	        });
	        
	        List<paystructureentity> place = Paystructurerep.getpaystructure(ref_no);
	        for (paystructureentity att : place) {
	            try {
	                MimeMessage message = new MimeMessage(session);
	                message.setFrom(new InternetAddress(from));
	                message.addRecipient(Message.RecipientType.TO, new InternetAddress(att.getEmail_id()));
	                message.setSubject("BORNFIRE MANAGEMENT");

	                // Create multipart content
	                MimeMultipart multipart = new MimeMultipart();

	                // Add text part
	                MimeBodyPart textPart = new MimeBodyPart();
	                textPart.setText("Hello, this is an example of sending email.");
	                multipart.addBodyPart(textPart);

	                // Add attachments
	                for (String fileName : y) {
	                    try {
	                        String entireFilePath = env.getProperty("output.exportpath") + fileName;
	                        MimeBodyPart attachmentPart = new MimeBodyPart();
	                        attachmentPart.attachFile(new File(entireFilePath));
	                        multipart.addBodyPart(attachmentPart);
	                    } catch (IOException e) {
	                        e.printStackTrace();
	                        // Handle exception if file attachment fails
	                        return "Attachment failed: " + e.getMessage(); // Return error message
	                    }
	                    System.out.println(fileName);
	                }

	                // Set the multipart as the content of the message
	                message.setContent(multipart);

	                // Send the message
	                Transport.send(message);
	                System.out.println("Message sent successfully.");
	            } catch (MessagingException e) {
	                e.printStackTrace();
	                return "Send failed";
	            }
	        }
	        return "Send successfully";


}
}