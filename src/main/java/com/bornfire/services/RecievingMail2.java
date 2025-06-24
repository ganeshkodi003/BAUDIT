package com.bornfire.services;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.internet.MimeMultipart;
import javax.mail.search.FlagTerm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bornfire.entities.InvoiceMaster;
import com.bornfire.entities.InvoiceMasterRep;
import com.bornfire.entities.PlacementMaintenance;
import com.bornfire.entities.PlacementMaintenanceRep;
import com.ibm.icu.text.DecimalFormat;
import com.sun.mail.pop3.POP3Store;

@Service

public class RecievingMail2 {
	@Autowired
	PlacementMaintenanceRep placementMaintenanceRep;

	@Autowired
	InvoiceMasterRep invoiceMasterRep;

	public String receiveEmail2(String pop3Host, String storeType, String user, String password,
			PlacementMaintenance placementmaintenance) {
		try {
			Properties properties = new Properties();
			properties.put("mail.pop3.host", pop3Host);
			Session emailSession = Session.getDefaultInstance(properties);

			final int MAX_MESSAGES = 1;

			POP3Store emailStore = (POP3Store) emailSession.getStore(storeType);
			emailStore.connect(user, password);

			Folder emailFolder = emailStore.getFolder("INBOX");
			emailFolder.open(Folder.READ_ONLY);

			// Message[] messages = emailFolder.getMessages();
			// new FlagTerm(new Flags(Flags.Flag.SEEN), false));

			int end = emailFolder.getMessageCount();
			int start = end - MAX_MESSAGES + 1;
			Message messages[] = emailFolder.getMessages(start, end);
			/*
			 * for (int i = 0; i < messages.length; i++) { Message message = messages[i];
			 * System.out.println("---------------------------------");
			 * System.out.println("Email Number " + (i + 1)); System.out.println("Subject: "
			 * + message.getSubject()); System.out.println("From: " + message.getFrom()[0]);
			 * System.out.println("Text: " + message.getContent().toString()); String
			 * M=getTextFromMessage(message); System.out.println(M);
			 */

			// int end=emailFolder.getMessageCount();
			// int start=end - MAX_MESSAGES + 1;
			Message Lmessages[] = emailFolder.getMessages(start, end);
			for (int i = 0; i < Lmessages.length; i++) {
				Message message = Lmessages[i];

				String ss = getTextFromMessage(message);
				// System.out.println("-----------1-------------");
				System.out.println(ss);

				// System.out.println("------------2------------");

				// Object V=message.getContent();
				// System.out.println(V);
				
				// System.out.println(special);
				String states = ss.substring(ss.indexOf("Sent: "), ss.indexOf("To: siddhaiyan@bornfire.in"));
				System.out.println(states);
				String Adate = states.substring(states.indexOf("day, "));
				String Bdate = Adate.substring(5, Adate.indexOf(", 20"));
				String Cdate = Bdate.substring(0, Bdate.indexOf(" "));
				String Ddate = Bdate.substring(Bdate.indexOf(" "));
				String Edate = Adate.substring(Adate.indexOf("202"));
				
				String DPdate = Ddate.trim() + "-" + Cdate + "-" + Edate.substring(0, 4);
				System.out.println(DPdate);
				
			
				    
				    
				String IN=ss.substring(ss.indexOf("BORNFIRE"),ss.indexOf("Regards,"));
				System.out.println(IN);
				String IN1=IN.substring(IN.indexOf("BORNFIRE"),16);
				System.out.println(IN1);
				String DP=IN.substring(IN.indexOf("INR"));
				String DP1=DP.substring(7,19);
				System.out.println(DP1);
				
				CreateAAdd(placementmaintenance, IN1, DP1,DPdate);
				System.out.println(CreateAAdd(placementmaintenance, IN1, DP1,DPdate));
				emailFolder.close(false);
				emailStore.close();

			}
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return password;
	}

	private Date CreateAAdd(PlacementMaintenance placementmaintenance, String IN1, String DP1,String DPdate) {
		
		PlacementMaintenance up = placementMaintenanceRep.getAlist(IN1);
		up.setDp_date(DPdate);
		up.setDp_no(DP1);
		placementMaintenanceRep.save(up);
		// msg = "PlacementMaintenance Added Successfully";
		return up.getDp_date();
		// TODO Auto-generated method stub
	}

	public void main(String[] args) {

		String host = "sg2plcpnl0144.prod.sin2.secureserver.net";// change accordingly
		String mailStoreType = "pop3";
		String username = "vijay.r@bornfire.in";
		String password = "Vijay@123";// change accordingly

		// receiveEmail(host, mailStoreType, username, password);

	}

	private String getTextFromMessage(Message message) throws MessagingException, IOException {
		if (message.isMimeType("text/plain")) {
			return message.getContent().toString();
		}
		if (message.isMimeType("multipart/*")) {
			MimeMultipart mimeMultipart = (MimeMultipart) message.getContent();
			return getTextFromMimeMultipart(mimeMultipart);
		}
		return "";
	}

	private String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
		String result = "";
		for (int i = 0; i < mimeMultipart.getCount(); i++) {
			BodyPart bodyPart = mimeMultipart.getBodyPart(i);
			if (bodyPart.isMimeType("text/plain")) {
				return result + "\n" + bodyPart.getContent(); // without return, same text appears twice in my tests
			}
			result += this.parseBodyPart(bodyPart);
		}
		return result;
	}

	private String parseBodyPart(BodyPart bodyPart) throws MessagingException, IOException {
		if (bodyPart.isMimeType("text/html")) {
			return "\n" + org.jsoup.Jsoup.parse(bodyPart.getContent().toString()).text();
		}
		if (bodyPart.getContent() instanceof MimeMultipart) {
			return getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
		}

		return "";
	}
}
