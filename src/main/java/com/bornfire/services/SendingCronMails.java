package com.bornfire.services;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.bornfire.entities.ResourceMasterRepo;

@Service
public class SendingCronMails {

	@Autowired
	ResourceMasterRepo resourceMasterRepo;

	public String anniversaryWish(String pop3Host, String user, String password, LocalDate currentDate)
			throws IOException {
		System.out.println("anniversary");
		List<Object[]> anniver = resourceMasterRepo.anniversary(currentDate);
		System.out.println("Fetched anniversary list: " + anniver);

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.host", "sg2plzcpnl491716.prod.sin2.secureserver.net");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		for (Object[] ani : anniver) {
			String employeeId = ani[0].toString();
			String doj = ani[1].toString().split(" ")[0];
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate dateOfJoining = LocalDate.parse(doj, formatter);

			int totalYears = currentDate.getYear() - dateOfJoining.getYear();
			String resourceName = ani[2].toString();
			String email = ani[3].toString();

			String recipient = email;
			String ccmail = "bornfireteam@bornfire.co.in";
			String subject = "Happy Work Anniversary " + resourceName;
			String body = "<html><body>" + "Dear " + resourceName + ",<br><br>" + "Congratulations on your "
					+ totalYears + " year work anniversary! We appreciate your hard work and dedication.<br><br>"
					+ "<div style='flex-shrink: 0;margin-left: 20px; margin-right: 20px;'>"
					+ "<img src='cid:work_anniversary_card' style='width:100%; height:100%;'/>" + "</div>"
					+ "<br>Best regards,<br>HR Team<br>"

					+ "<div style='display: flex; align-items: flex-start;'>"
					// Left Side Logo
					+ "<div style='flex-shrink: 0; margin-right: 20px;'>" + "<img src='cid:image001'/>" + "</div>"

					+ "<div>"
					+ "<span style='color: #2f5496; font-family: Mistral; font-size: 15.0pt;'>Bornfire Innovations Private Limited</span><br>"
					+ "<span style='color: #c45911; font-family: Arial;'>Viji Nivas, Second Floor,</span><br>"
					+ "<span style='color: #ff0000; font-family: Arial;'>10, Soundaraiyar Street, Ammapet,<br>"
					+ "Salem – 636003, Tamilnadu, India</span><br>"
					+ "<span style='color: #2f5496;'>Land Line: +91 427 2917802<br>"
					+ "Mobile: +91 9884298802</span><br>"
					+ "<a href='http://bornfire.in'>http://bornfire.in</a><br><br>" + "</div>" + "</div>"

					+ "</body></html>";

			try {
				// Compose the email message
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(user));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
				 message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccmail));
				message.setSubject(subject);

				// Create a multipart message for text and images
				Multipart multipart = new MimeMultipart("related");

				// Text part (HTML)
				MimeBodyPart textPart = new MimeBodyPart();
				textPart.setContent(body, "text/html; charset=utf-8");
				multipart.addBodyPart(textPart);

				// Inline image part for anniversary card
				MimeBodyPart imagePart1 = new MimeBodyPart();
				ClassPathResource imageResource1 = new ClassPathResource("static/images/work_anniversary_card.png");
				File imageFile1 = imageResource1.getFile();
				FileDataSource imageDataSource1 = new FileDataSource(imageFile1);

				imagePart1.setDataHandler(new DataHandler(imageDataSource1));
				imagePart1.setContentID("<work_anniversary_card>");
				imagePart1.setDisposition(MimeBodyPart.INLINE);
				imagePart1.setFileName("work_anniversary_card.png");

				multipart.addBodyPart(imagePart1);

				// Inline image part for image001
				MimeBodyPart imagePart = new MimeBodyPart();
				ClassPathResource imageResource = new ClassPathResource("static/images/image001.jpg");
				File imageFile = imageResource.getFile();
				FileDataSource imageDataSource = new FileDataSource(imageFile);

				imagePart.setDataHandler(new DataHandler(imageDataSource));
				imagePart.setContentID("<image001>");
				imagePart.setDisposition(MimeBodyPart.INLINE);
				imagePart.setFileName("image001.jpg");

				multipart.addBodyPart(imagePart);

				message.setContent(multipart);

				// Send the email
				Transport.send(message);

				System.out.println("Anniversary email sent successfully to " + recipient);
			} catch (MessagingException | IOException e) {
				e.printStackTrace();
			}

		}

		return "Anniversary emails sent to all employees!";
	}

	public String birthdayWish(String pop3Host, String user, String password, LocalDate currentDate)
			throws IOException {

		System.out.println("birthday"+currentDate);
		List<Object[]> birth = resourceMasterRepo.birthday(currentDate);
		System.out.println(birth+"birth");

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.trust", "*");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.host", "sg2plzcpnl491716.prod.sin2.secureserver.net");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		for (Object[] bir : birth) {
			String employeeId = bir[0].toString();
			String resourceName = bir[1].toString();
			String email = bir[3].toString();

			System.out.println("Sending email to employee ID: " + employeeId);
			System.out.println("Employee Name: " + resourceName);

			String recipient = email;
			 String ccmail = "bornfireteam@bornfire.co.in";
			String subject = "Happy Birthday " + resourceName;

			String body = "<html><body>" + "Dear " + resourceName + ",<br><br>"
					+ "We at Bornfire Team wanted to take a moment to wish you a very happy birthday! "
					+ "Cheers to another wonderful year of excellence!<br>"
					+ "Wishing you a fantastic year ahead filled with joy, success, and all the things you love.<br><br>"
					+ "<div style='flex-shrink: 0;margin-left: 20px; margin-right: 20px;'>"
					+ "<img src='cid:birthday_card' style='width:100%; height:100%;'/>" + "</div>"
					+ "<br>Best regards,<br>HR Team.<br>"

					+ "<div style='display: flex; align-items: flex-start;'>"
					// Left Side Logo
					+ "<div style='flex-shrink: 0; margin-right: 20px;'>" + "<img src='cid:image001'/>" + "</div>"

					+ "<div>"
					+ "<span style='color: #2f5496; font-family: Mistral; font-size: 15.0pt;'>Bornfire Innovations Private Limited</span><br>"
					+ "<span style='color: #c45911; font-family: Arial;'>Viji Nivas, Second Floor,</span><br>"
					+ "<span style='color: #ff0000; font-family: Arial;'>10, Soundaraiyar Street, Ammapet,<br>"
					+ "Salem – 636003, Tamilnadu, India</span><br>"
					+ "<span style='color: #2f5496;'>Land Line: +91 427 2917802<br>"
					+ "Mobile: +91 9884298802</span><br>"
					+ "<a href='http://bornfire.in'>http://bornfire.in</a><br><br>" + "</div>" + "</div>"

					+ "</body></html>";
			try {
				// Compose the email message
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(user));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
				message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccmail));
				message.setSubject(subject);

				// Create a multipart message for text and image
				Multipart multipart = new MimeMultipart("related");

				// Text part (HTML)
				MimeBodyPart textPart = new MimeBodyPart();
				textPart.setContent(body, "text/html; charset=utf-8");
				multipart.addBodyPart(textPart);

				// Inline image part for image001
				MimeBodyPart imagePart1 = new MimeBodyPart();
				ClassPathResource imageResource1 = new ClassPathResource("static/images/birthday_card.png");
				File imageFile1 = imageResource1.getFile();
				FileDataSource imageDataSource1 = new FileDataSource(imageFile1);

				imagePart1.setDataHandler(new DataHandler(imageDataSource1));
				imagePart1.setContentID("<birthday_card>");
				imagePart1.setDisposition(MimeBodyPart.INLINE);
				imagePart1.setFileName("birthday_card.png");

				multipart.addBodyPart(imagePart1);

				// Inline image part for image001
				MimeBodyPart imagePart = new MimeBodyPart();
				ClassPathResource imageResource = new ClassPathResource("static/images/image001.jpg");
				File imageFile = imageResource.getFile();
				FileDataSource imageDataSource = new FileDataSource(imageFile);

				imagePart.setDataHandler(new DataHandler(imageDataSource));
				imagePart.setContentID("<image001>");
				imagePart.setDisposition(MimeBodyPart.INLINE);
				imagePart.setFileName("image001.jpg");

				multipart.addBodyPart(imagePart);

				message.setContent(multipart);
				// Send the email
				Transport.send(message);

				System.out.println("Birthday email sent successfully to " + recipient);
			} catch (MessagingException e) {
				e.printStackTrace();
			}

		}

		return "Birthday emails sent to all employees!";
	}

	public String WelcomeInvite(String pop3Host, String user, String password, LocalDate currentDate)
			throws IOException {

		System.out.println("welcome");
		List<Object[]> welcome = resourceMasterRepo.welcome(currentDate);

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.host", "sg2plzcpnl491716.prod.sin2.secureserver.net");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		for (Object[] wel : welcome) {
			String employeeId = wel[0].toString();
			String resourceName = wel[2].toString();
			String email = wel[3].toString();

			System.out.println("Sending email to employee ID: " + employeeId);
			System.out.println("Employee Name: " + resourceName);

			String recipient = email;
			// String ccmail = "siddhaiyan@bornfire.in, tr.ramkumar@bornfire.in,
			// kalidass.k@bornfire.in";
			String subject = "Welcome to Bornfire, " + resourceName + "!";

			String body = "<html><body>" + "Dear " + resourceName + ",<br><br>"
					+ "We are thrilled to welcome you to the Bornfire team! "
					+ "We believe that your skills will be a great addition to our growing family.<br><br>"
					+ "As you begin this new journey with us, please do not hesitate to reach out with any questions or if there's anything we can help with.<br>"
					+ "We are here to support you and are excited to work together on achieving great things!<br><br>"
					+ "Best regards,<br>HR Team.<br>"

					+ "<div style='display: flex; align-items: flex-start;'>"
					// Left Side Logo
					+ "<div style='flex-shrink: 0; margin-right: 20px;'>" + "<img src='cid:image001'/>" + "</div>"

					+ "<div>"
					+ "<span style='color: #2f5496; font-family: Mistral; font-size: 15.0pt;'>Bornfire Innovations Private Limited</span><br>"
					+ "<span style='color: #c45911; font-family: Arial;'>Viji Nivas, Second Floor,</span><br>"
					+ "<span style='color: #ff0000; font-family: Arial;'>10, Soundaraiyar Street, Ammapet,<br>"
					+ "Salem – 636003, Tamilnadu, India</span><br>"
					+ "<span style='color: #2f5496;'>Land Line: +91 427 2917802<br>"
					+ "Mobile: +91 9884298802</span><br>"
					+ "<a href='http://bornfire.in'>http://bornfire.in</a><br><br>" + "</div>" + "</div>"

					+ "</body></html>";

			try {
				// Compose the email message
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(user));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
				// message.setRecipients(Message.RecipientType.CC,
				// InternetAddress.parse(ccmail));
				message.setSubject(subject);

				// Create a multipart message for text and image
				Multipart multipart = new MimeMultipart("related");

				// Text part (HTML)
				MimeBodyPart textPart = new MimeBodyPart();
				textPart.setContent(body, "text/html; charset=utf-8");
				multipart.addBodyPart(textPart);

				// Inline image part for image001
				MimeBodyPart imagePart = new MimeBodyPart();
				ClassPathResource imageResource = new ClassPathResource("static/images/image001.jpg");
				File imageFile = imageResource.getFile();
				FileDataSource imageDataSource = new FileDataSource(imageFile);

				imagePart.setDataHandler(new DataHandler(imageDataSource));
				imagePart.setContentID("<image001>");
				imagePart.setDisposition(MimeBodyPart.INLINE);
				imagePart.setFileName("image001.jpg");

				multipart.addBodyPart(imagePart);

				message.setContent(multipart);
				// Send the email
				Transport.send(message);

				System.out.println("Welcome email sent successfully to " + recipient);
			} catch (MessagingException e) {
				e.printStackTrace();
			}

		}

		return "Welcome emails sent to all employees!";
	}

	

	public String HolidayMail(String pop3Host, String user, String password, LocalDate currentDate)
			throws IOException {

		System.out.println("Holiday");
		List<Object[]> welcome = resourceMasterRepo.welcome(currentDate);

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.host", "sg2plzcpnl491716.prod.sin2.secureserver.net");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		for (Object[] wel : welcome) {
			String employeeId = wel[0].toString();
			String resourceName = wel[2].toString();
			String email = wel[3].toString();

			System.out.println("Sending email to employee ID: " + employeeId);
			System.out.println("Employee Name: " + resourceName);

			String recipient = email;
			// String ccmail = "siddhaiyan@bornfire.in, tr.ramkumar@bornfire.in,
			// kalidass.k@bornfire.in";
			String subject = "Happy Festive Season from Bornfire Innovations!";

			String body = "<html><body>" + "Dear " + resourceName + ",<br><br>"
					+ "Warm wishes to you and your loved ones during this festive season!<br>"
					+ "As we celebrate, we are grateful for the dedication and hard work that make Bornfire a wonderful place to work.<br><br>"
					+ "Please take this time to relax, recharge, and enjoy the festivities. If there's anything you need from us, feel free to reach out.<br>"
					+ "May this holiday bring joy, health, and happiness to you and your family!<br><br>"
					+ "Best wishes,<br>HR Team<br>"

					+ "<div style='display: flex; align-items: flex-start;'>"
					// Left Side Logo
					+ "<div style='flex-shrink: 0; margin-right: 20px;'>" + "<img src='cid:image001'/>" + "</div>"

					+ "<div>"
					+ "<span style='color: #2f5496; font-family: Mistral; font-size: 15.0pt;'>Bornfire Innovations Private Limited</span><br>"
					+ "<span style='color: #c45911; font-family: Arial;'>Viji Nivas, Second Floor,</span><br>"
					+ "<span style='color: #ff0000; font-family: Arial;'>10, Soundaraiyar Street, Ammapet,<br>"
					+ "Salem – 636003, Tamilnadu, India</span><br>"
					+ "<span style='color: #2f5496;'>Land Line: +91 427 2917802<br>" + "Mobile: +91 9884298802</span><br>"
					+ "<a href='http://bornfire.in'>http://bornfire.in</a><br><br>" + "</div>" + "</div>"

					+ "</body></html>";

			try {
				// Compose the email message
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(user));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
				// message.setRecipients(Message.RecipientType.CC,
				// InternetAddress.parse(ccmail));
				message.setSubject(subject);

				// Create a multipart message for text and image
				Multipart multipart = new MimeMultipart("related");

				// Text part (HTML)
				MimeBodyPart textPart = new MimeBodyPart();
				textPart.setContent(body, "text/html; charset=utf-8");
				multipart.addBodyPart(textPart);

				// Inline image part for image001
				MimeBodyPart imagePart = new MimeBodyPart();
				ClassPathResource imageResource = new ClassPathResource("static/images/image001.jpg");
				File imageFile = imageResource.getFile();
				FileDataSource imageDataSource = new FileDataSource(imageFile);

				imagePart.setDataHandler(new DataHandler(imageDataSource));
				imagePart.setContentID("<image001>");
				imagePart.setDisposition(MimeBodyPart.INLINE);
				imagePart.setFileName("image001.jpg");

				multipart.addBodyPart(imagePart);

				message.setContent(multipart);
				// Send the email
				Transport.send(message);

				System.out.println("Welcome email sent successfully to " + recipient);
			} catch (MessagingException e) {
				e.printStackTrace();
			}

		}

		return "Welcome emails sent to all employees!";
	}

}
