package com.bornfire.services;


import com.bornfire.entities.CandEvalFormEntity;
import com.bornfire.entities.CandEvalFormRep;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Service
public class sendigmail_offerletter {

    @Autowired
    private CandEvalFormRep candEvalFormRep;
    
    @Autowired
    private Environment env;

    public String sendingmailones(String from, String host, String to, String cc, String username, String password, String refNo) {
        // Set up the mail session
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("OFFER LETTER");

            // Handle CC addresses
            if (cc != null && !cc.isEmpty()) {
                for (String ccAddress : cc.split(",")) {
                    message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccAddress));
                }
            }

            MimeMultipart multipart = new MimeMultipart();

            // Add initial HTML text
            MimeBodyPart textPart = new MimeBodyPart();
            StringBuilder htmlContent1 = new StringBuilder();
          //  htmlContent1.append("<p>BORNFIRE INNOVATIONS</p>");

            // Get candidate evaluation form data
            List<CandEvalFormEntity> candidates = candEvalFormRep.getCvfmail(refNo);
            for (CandEvalFormEntity candidate : candidates) {
                htmlContent1.append("<p>Dear ").append(candidate.getCandi_name()).append(",</p>")
                           .append("<p>Following our recent discussions, we are delighted to offer you the position of ")
                           .append(candidate.getPosition_title()).append(" with Our Organization. Bornfire creates and redefines solutions for the BFSI Sector. If you join Bornfire, you will ")
                           .append("become part of a fast-paced and dedicated team that works together to provide our clients with the highest possible level of service and advice.</p>")
                           .append("<p>As a member of Our Bornfire team, we would ask for your commitment to delivering outstanding quality and results that exceed client expectations. In addition, we expect your personal accountability in all the products, actions, advice, and results that you provide as a representative of Our Organization. In return, we are committed to providing you with every opportunity to learn, grow, and stretch to the highest level of your ability and potential.</p>")
                           .append("<p>We are confident you will find this new opportunity both challenging and rewarding. The following points outline the terms and conditions we are proposing.</p>")
                           .append("<p>Your starting salary (cost to the company) will be Rs.").append(candidate.getCtc())
                           .append(" annually as agreed upon by us. Our organization believes that, while maintaining the cost of the employee to the company at agreed levels, the structure of payment has been tailored to suit the specific needs of the employee within the norms set out in the Organization policy.</p>");
            }

            // Add signature
            htmlContent1.append("<p>With Regards,<br>")
                       .append("KALIDASS K <br>");

            // Add logo as an embedded image (Classpath loading)
            try {
                MimeBodyPart imagePart = new MimeBodyPart();
                InputStream logoStream = getClass().getResourceAsStream("/static/images/Picture1.jpg");
                if (logoStream != null) {
                    byte[] imageData = readAllBytes(logoStream);  // Use the utility method here
                    imagePart.setDataHandler(new DataHandler(new ByteArrayDataSource(imageData, "image/jpeg")));
                    imagePart.setHeader("Content-ID", "<logo>");
                    multipart.addBodyPart(imagePart);

                    // Reference the logo in your HTML content
                    htmlContent1.append("<img src='cid:logo' alt='Bornfire Logo' width='100' height='50' /><br>");
                } else {
                    System.err.println("Error loading Picture1.jpg from classpath.");
                }
            } catch (Exception e) {
                System.err.println("Error loading Picture1.jpg: " + e.getMessage());
            }

            // Add the remaining text after the image
            StringBuilder htmlContent2 = new StringBuilder();
            htmlContent2.append("<p>HR Executive</p>")
                       .append("<p>Bornfire Innovations Private Limited<br>")
                       .append("Viji Nivas, Second Floor,<br>")
                       .append("10, Soundaraiyar Street,<br>")
                       .append("Ammapet,<br>")
                       .append("Salem - 636003 Tamilnadu, India</p>")
                       .append("<p>Land Line: +91 44 24650400<br>")
                       .append("Mobile: +91 95668 74563</p>");

            // Add another logo (Classpath loading)
            try {
                MimeBodyPart imagePart1 = new MimeBodyPart();
                InputStream logoStream1 = getClass().getResourceAsStream("/static/images/logo.png");
                if (logoStream1 != null) {
                    byte[] imageData1 = readAllBytes(logoStream1);  // Use the utility method here
                    imagePart1.setDataHandler(new DataHandler(new ByteArrayDataSource(imageData1, "image/png")));
                    imagePart1.setHeader("Content-ID", "<logo1>");
                    multipart.addBodyPart(imagePart1);

                    htmlContent2.append("<img src='cid:logo1' alt='Bornfire Logo' width='100' height='70' /><br>");
                } else {
                    System.err.println("Error loading logo.png from classpath.");
                }
            } catch (Exception e) {
                System.err.println("Error loading logo.png: " + e.getMessage());
            }

            // Add HTML footer
            htmlContent2.append("<p><a href='http://bornfire.in'>http://bornfire.in</a></p>")
                       .append("<p>Disclaimer: The information in this mail is confidential and is intended solely for the addressee. Access to this mail by anyone else is unauthorized. Copying or further distribution beyond the original recipient may be unlawful. We are not responsible for any damage caused by a virus or alteration of the e-mail by a third party or otherwise. The contents of this message may not necessarily represent the views or policies of Bornfire Innovations.</p>")
                       .append("<p>Thank you for considering this offer, and we look forward to your positive response.</p>");

            // Set the HTML content as the message body
            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(htmlContent1.toString() + htmlContent2.toString(), "text/html");
            multipart.addBodyPart(htmlPart);

            // Add any additional attachments if needed
            List<CandEvalFormEntity> attachments = candEvalFormRep.getCvfmail(refNo);
            for (CandEvalFormEntity att : attachments) {
                byte[] attachmentData = att.getOffer(); // Retrieve image data from the database
                if (attachmentData != null) {
                    MimeBodyPart attachmentPart = new MimeBodyPart();
                    String attachmentName = "Bornfire-OfferLetter-" + refNo + "-" + att.getCandi_name() + ".pdf";
                    attachmentPart.setDataHandler(new DataHandler(new ByteArrayDataSource(attachmentData, "application/pdf")));
                    attachmentPart.setFileName(attachmentName);
                    multipart.addBodyPart(attachmentPart);
                } else {
                    System.err.println("No attachment data found for candidate: " + att.getCandi_name());
                }
            }

            // Set the multipart as the content of the message
            message.setContent(multipart);

            // Send the message
            Transport.send(message);
            System.out.println("Message sent successfully.");
            return "send successfully";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "send failed";
        }
    }

    // Utility method to read InputStream into byte array
    private byte[] readAllBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int bytesRead;
        byte[] data = new byte[1024];  // Buffer size can be adjusted if needed

        while ((bytesRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, bytesRead);
        }

        return buffer.toByteArray();
    }
}
