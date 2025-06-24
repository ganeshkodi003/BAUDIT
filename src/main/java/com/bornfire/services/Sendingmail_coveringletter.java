package com.bornfire.services;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
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
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.bornfire.entities.CandEvalFormEntity;
import com.bornfire.entities.Salary_Pay_Entity;
import com.bornfire.entities.Salary_Pay_Rep;
import com.ibm.icu.math.BigDecimal;

@Service
public class Sendingmail_coveringletter {
	@Autowired
	Salary_Pay_Rep salary_Pay_Rep;
	
	public String sendingctcmail(String from, String host, String to, String cc, String username, String password, String emp_no, String ctc) throws IOException {
	    Properties properties = new Properties();
	    properties.put("mail.smtp.host", host);
	    properties.put("mail.smtp.port", "465");
	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.starttls.enable", "true");
	    properties.put("mail.smtp.ssl.trust", "*");
	    properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    properties.put("mail.smtp.socketFactory.fallback", "false");

	    System.out.println(ctc);

	    Session session = Session.getInstance(properties, new Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	        }
	    });

	    try {
	        MimeMessage message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(from));
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	        message.setSubject("Employee Appraisal – Salary Revision");

	        if (cc != null && !cc.isEmpty()) {
	            String[] ccAddresses = cc.split(",");
	            for (String ccAddress : ccAddresses) {
	                message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccAddress));
	            }
	        }

	        // Main multipart
	        MimeMultipart multipart = new MimeMultipart("related");

	        // Part 1: Text (with inline image reference)
	        MimeBodyPart htmlBodyPart = new MimeBodyPart();

	        List<Salary_Pay_Entity> place = salary_Pay_Rep.getactc(emp_no, ctc);
	        StringBuilder htmlContent = new StringBuilder();
	        
	        for (Salary_Pay_Entity att : place) {
	        	 BigDecimal ctcAmount = att.getCtc_amt() != null ? new BigDecimal(att.getCtc_amt()) : BigDecimal.ZERO;
	        	    String formattedCtcAmount = new DecimalFormat("#,##0.00").format(ctcAmount);
	        	    String ctcInWords = convertToIndianCurrencyWords(ctcAmount);

	            htmlContent.append(
	                "<p>Dear ").append(att.getEmp_name()).append(",</p>")
	                .append("<p>We are pleased to inform you that due to your consistent outstanding performance and dedication to your role, we are providing you with a salary increment effective from ")
	                .append(new SimpleDateFormat("dd-MM-yyyy").format(att.getCtc_eff_date()))
	                .append(". Your new salary will be Rs.").append(formattedCtcAmount)
	                .append(" (Rupees ").append(ctcInWords).append(" Only). ")
	                .append("Please refer to the attached Salary Structure for components and breakup details.</p>")
	                .append("<p>Your recent contributions have not gone unnoticed, and this raise reflects our recognition of your efforts. We value your continuous hard work and commitment to Bornfire, and we believe you will continue to excel in your position as ")
	                .append(att.getEmp_desig())
	                .append(".</p>")
	                .append("<p>Thank you for being an integral part of our team. We look forward to seeing your continued growth and success.</p>")
	                .append("<p>Warm Regards,<br>Valarmathi Sivakumar<br>HR-Executive.</p>")
	                .append("<div><img src='cid:HR_Signature'></div>");
	        }

	        htmlBodyPart.setContent(htmlContent.toString(), "text/html; charset=utf-8");
	        multipart.addBodyPart(htmlBodyPart);

	        // Part 2: HR Signature Image
	        MimeBodyPart imageBodyPart = new MimeBodyPart();
	        ClassPathResource imageResource = new ClassPathResource("static/images/HR_Signature.png");
	        File imageFile = imageResource.getFile();
	        FileDataSource fds = new FileDataSource(imageFile);
	        imageBodyPart.setDataHandler(new DataHandler(fds));
	        imageBodyPart.setHeader("Content-ID", "<HR_Signature>");
	        imageBodyPart.setDisposition(MimeBodyPart.INLINE);
	        multipart.addBodyPart(imageBodyPart);

	        // Attachments
	        List<Salary_Pay_Entity> places = salary_Pay_Rep.getactc(emp_no, ctc);

	        for (Salary_Pay_Entity atts : places) {
	            try {
	                byte[] imageData = atts.getStr(); // attachment 1
	                if (imageData != null) {
	                    String attachmentName = "Bornfire-SalaryStructure-" + emp_no + ".pdf";
	                    MimeBodyPart attachmentPart = new MimeBodyPart();
	                    attachmentPart.setDataHandler(new DataHandler(new ByteArrayDataSource(imageData, "application/pdf")));
	                    attachmentPart.setFileName(attachmentName);
	                    multipart.addBodyPart(attachmentPart);
	                } else {
	                    System.err.println("No data found for Covering Letter attachment.");
	                }
	            } catch (MessagingException e) {
	                e.printStackTrace();
	                return "Attachment (Covering Letter) failed: " + e.getMessage();
	            }
	        }
	        
	        for (Salary_Pay_Entity atts : places) {
	            try {
	                byte[] imageData = atts.getRevision(); // attachment 2
	                if (imageData != null) {
	                    String attachmentName = "Bornfire-SalaryRevision-Covering-Letter-" + emp_no + ".pdf";
	                    MimeBodyPart attachmentPart = new MimeBodyPart();
	                    attachmentPart.setDataHandler(new DataHandler(new ByteArrayDataSource(imageData, "application/pdf")));
	                    attachmentPart.setFileName(attachmentName);
	                    multipart.addBodyPart(attachmentPart);
	                } else {
	                    System.err.println("No data found for Salary Structure attachment.");
	                }
	            } catch (MessagingException e) {
	                e.printStackTrace();
	                return "Attachment (Salary Structure) failed: " + e.getMessage();
	            }
	        }

	        message.setContent(multipart);

	        Transport.send(message);
	        System.out.println("Message sent successfully.");
	    } catch (MessagingException e) {
	        e.printStackTrace();
	        return "send failed";
	    }
	    return "send successfully";
	}

	private String convertToIndianCurrencyWords(BigDecimal amount) {
	    String[] units = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", 
	                        "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", 
	                        "Eighteen", "Nineteen" };
	    String[] tens = { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };

	    long number = amount.longValue();
	    if (number == 0) {
	        return "Zero";
	    }

	    StringBuilder words = new StringBuilder();

	    if (number / 10000000 > 0) {
	        words.append(convertToIndianCurrencyWords(BigDecimal.valueOf(number / 10000000))).append(" Crore ");
	        number %= 10000000;
	    }
	    if (number / 100000 > 0) {
	        words.append(convertToIndianCurrencyWords(BigDecimal.valueOf(number / 100000))).append(" Lakh ");
	        number %= 100000;
	    }
	    if (number / 1000 > 0) {
	        words.append(convertToIndianCurrencyWords(BigDecimal.valueOf(number / 1000))).append(" Thousand ");
	        number %= 1000;
	    }
	    if (number / 100 > 0) {
	        words.append(convertToIndianCurrencyWords(BigDecimal.valueOf(number / 100))).append(" Hundred ");
	        number %= 100;
	    }
	    if (number > 0) {
	        if (number < 20) {
	            words.append(units[(int) number]);
	        } else {
	            words.append(tens[(int) number / 10]);
	            if ((number % 10) > 0) {
	                words.append(" ").append(units[(int) number % 10]);
	            }
	        }
	    }

	    return words.toString().trim();
	}

}
