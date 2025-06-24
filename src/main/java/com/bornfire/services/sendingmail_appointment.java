package com.bornfire.services;

import com.bornfire.entities.CandEvalFormEntity;
import com.bornfire.entities.CandEvalFormRep;

import com.bornfire.entities.CandEvalFormEntity;
import com.bornfire.entities.CandEvalFormRep;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
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
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class sendingmail_appointment {

    @Autowired
    CandEvalFormRep CandEvalFormRep;
    @Autowired
  	Environment env;

    public String sendingmail(String from, String host, String to, String cc, String username, String password, String ref_no) throws IOException {

        // Set up the properties for SMTP configuration
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587"); // Port for TLS/STARTTLS
        properties.put("mail.smtp.auth", "true"); // Enable authentication
        properties.put("mail.smtp.starttls.enable", "true"); // Enable STARTTLS

        // Get the session object for authenticating SMTP connection
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a MimeMessage for the email
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from)); // Set email sender
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to)); // Add recipient
            message.setSubject("APPOINTMENT LETTER"); // Set subject of the email

            // Add CC recipients if provided
            if (cc != null && !cc.isEmpty()) {
                String[] ccAddresses = cc.split(",");
                for (String ccAddress : ccAddresses) {
                    message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccAddress));
                }
            }

            // Create a multipart container for email content (text, images, attachments)
            MimeMultipart multipart = new MimeMultipart();

            // Add text part for company introduction
            MimeBodyPart textPart = new MimeBodyPart();
             // Add this body part to the multipart

            // Fetch candidate evaluation form data using ref_no from database
            List<CandEvalFormEntity> place = CandEvalFormRep.getCvfmail(ref_no);

            // Iterate through the candidate list to generate personalized email content
            for (CandEvalFormEntity att : place) {

                // Create another multipart for the candidate-specific email content
                MimeMultipart multipart2 = new MimeMultipart();

                // Create a body part for the HTML text content
                MimeBodyPart textPart2 = new MimeBodyPart();

                // Use StringBuilder to build the HTML content dynamically
                StringBuilder htmlContent1 = new StringBuilder();

                // Personalize the email content for each candidate
                htmlContent1.append("<p>Dear ").append(att.getCandi_name()).append(",</p>")
                .append("<p>Following our recent discussions, we are delighted to offer you the position of ")
                .append(att.getPosition_title())
                .append(" with Our Organization. Bornfire creates and redefines solutions for the BFSI Sector. "
                + "If you join Bornfire, you will become part of a fast-paced and dedicated team that works together "
                + "to provide our clients with the highest possible level of service and advice.<br><br>"
                + "As a member of Our Bornfire team, we would ask for your commitment to delivering outstanding quality and "
                + "results that exceed client expectations. In return, we are committed to providing you with every opportunity "
                + "to learn, grow, and stretch to the highest level of your ability and potential.<br><br>"
                + "We are confident you will find this new opportunity both challenging and rewarding. The following points "
                + "outline the terms and conditions we are proposing.<br><br>"
                + "Your starting salary (cost to the company) will be Rs.")
                .append(att.getCtc())
                .append(" annually as agreed upon by us.<br><br>"
                	+"Please review the attached documents for your appointment letter and salary structure" 
                 +"If you have any questions or need further clarification, do not hesitate to contact us."
                + "With Regards,<br><br>"
                + "Kalidass K<br><br>"); // Continue with the rest of the offer content

                // Set the text as HTML content
               // textPart2.setText(htmlContent1.toString(), "UTF-8", "html");
              //  multipart2.addBodyPart(textPart2); // Add this HTML content part

                // Add company logo as inline image
                try {
                    MimeBodyPart imagePart = new MimeBodyPart();
                    InputStream logoStream = getClass().getResourceAsStream("/static/images/Picture1.jpg");
                    if (logoStream != null) {
                        byte[] imageData = readAllBytes(logoStream); // Read the image bytes
                        imagePart.setDataHandler(new DataHandler(new ByteArrayDataSource(imageData, "image/jpeg")));
                        imagePart.setHeader("Content-ID", "<logo>");
                        multipart2.addBodyPart(imagePart);

                        // Reference the logo in HTML
                        htmlContent1.append("<img src='cid:logo' alt='Bornfire Logo' width='100' height='50' /><br>");
                    } else {
                        System.err.println("Error loading Picture1.jpg from classpath.");
                    }
                } catch (Exception e) {
                    System.err.println("Error loading Picture1.jpg: " + e.getMessage());
                }

                // Add the rest of the email content (footer, HR contact details, etc.)
                StringBuilder htmlContent2 = new StringBuilder();
                htmlContent2.append("<p>HR Executive</p>")
                .append("<p>Bornfire Innovations Private Limited<br>")
                .append("10, Soundaraiyar Street, Ammapet, Salem - 636003 Tamilnadu, India</p>")
                .append("<p>Land Line: +91 44 24650400</p>")
                .append("<p><a href='http://bornfire.in'>http://bornfire.in</a></p>")
                .append("<p>Disclaimer: The information in this mail is confidential...</p>"); // Continue with contact details

                // Add another inline image (if needed)
                try {
                    MimeBodyPart imagePart1 = new MimeBodyPart();
                    InputStream logoStream1 = getClass().getResourceAsStream("/static/images/logo.png");
                    if (logoStream1 != null) {
                        byte[] imageData1 = readAllBytes(logoStream1); // Read second image bytes
                        imagePart1.setDataHandler(new DataHandler(new ByteArrayDataSource(imageData1, "image/png")));
                        imagePart1.setHeader("Content-ID", "<logo1>");
                        multipart2.addBodyPart(imagePart1);

                        htmlContent2.append("<img src='cid:logo1' alt='Bornfire Logo' width='100' height='70' /><br>");
                    } else {
                        System.err.println("Error loading logo.png from classpath.");
                    }
                } catch (Exception e) {
                    System.err.println("Error loading logo.png: " + e.getMessage());
                }

                // Add the footer to the email
                htmlContent2.append("<p><a href='http://bornfire.in'>http://bornfire.in</a></p>")
                .append("<p>Disclaimer: The information in this mail is confidential and is intended solely for the addressee. Access to this mail by anyone else is unauthorized. Copying or further distribution beyond the original recipient may be unlawful. We are not responsible for any damage caused by a virus or alteration of the e-mail by a third party or otherwise. The contents of this message may not necessarily represent the views or policies of Bornfire Innovations.</p>")
                .append("<p>Thank you for considering this offer, and we look forward to your positive response.</p>");

                // Create a MimeBodyPart for the full HTML content
                MimeBodyPart htmlPart = new MimeBodyPart();
                htmlPart.setContent(htmlContent1.toString() + htmlContent2.toString(), "text/html");

                // Add the HTML part to the multipart2
                multipart2.addBodyPart(htmlPart);

                // Add each body part from multipart2 to multipart
                for (int i = 0; i < multipart2.getCount(); i++) {
                    multipart.addBodyPart(multipart2.getBodyPart(i));
                }
            }

            // Add attachments: Appointment Letter
            List<CandEvalFormEntity> places = CandEvalFormRep.getCvfmail(ref_no);
            for (CandEvalFormEntity atts : places) {
                byte[] imageData = atts.getAppointment();
                if (imageData != null) {
                    String attachmentName = "Bornfire-AppointmentLetter-" + ref_no +atts.getCandi_name()+ ".pdf";
                    MimeBodyPart attachmentPart = new MimeBodyPart();
                    attachmentPart.setDataHandler(new DataHandler(new ByteArrayDataSource(imageData, "application/pdf")));
                    attachmentPart.setFileName(attachmentName);
                    multipart.addBodyPart(attachmentPart);
                }
            }

            // Add attachments: Salary Structure
            List<CandEvalFormEntity> placess = CandEvalFormRep.getCvfmail(ref_no);
            for (CandEvalFormEntity atts : placess) {
                byte[] imageData = atts.getSalarystru();
                if (imageData != null) {
                    String attachmentName = "Bornfire-SalaryStructure-" + ref_no +atts.getCandi_name()+ ".pdf";
                    MimeBodyPart attachmentPart = new MimeBodyPart();
                    attachmentPart.setDataHandler(new DataHandler(new ByteArrayDataSource(imageData, "application/pdf")));
                    attachmentPart.setFileName(attachmentName);
                    multipart.addBodyPart(attachmentPart);
                }
            }

            // Set the full multipart content in the email message
            message.setContent(multipart);

            // Send the email
            Transport.send(message);

            // Return success message
            return "Mail Sent Successfully";

        } catch (MessagingException mex) {
            mex.printStackTrace();
            return "Mail Sending Failed";
        }
    }

    // Utility method to read the bytes of an input stream
    private byte[] readAllBytes(InputStream inputStream) throws IOException {
        try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
            int nRead;
            byte[] data = new byte[16384];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }
            buffer.flush();
            return buffer.toByteArray();
        }
    }
}
