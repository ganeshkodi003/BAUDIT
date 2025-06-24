package com.bornfire.services;

import java.util.List;
import java.util.Properties;
import java.util.List;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileTypeMap;
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

import com.bornfire.entities.BTM_Call_Log_Repo;
import com.bornfire.entities.BTM_Daily_Checklist_Entity;
import com.bornfire.entities.BTM_Daily_Checklist_Repo;
import com.bornfire.entities.CandEvalFormEntity;
import com.bornfire.entities.CandEvalFormRep;
@Service
public class Sendingmail_Amcchecklist {

	private static final int MAX_RETRIES = 3;
    private static final int RETRY_DELAY_MS = 5000;

	@Autowired
	com.bornfire.entities.BTM_Daily_Checklist_Repo BTM_Daily_Checklist_Repo;
	    @Autowired
		Environment env;

	    public String sending_amcchecklist(String from, String host, String to, String cc, String username, String password,
                String resource_name, String service_date, String service_time1, String service_shift) {
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
message.setSubject("AMC DAILY-CHECKLIST");

if (cc != null && !cc.isEmpty()) {
String[] ccAddresses = cc.split(",");
for (String ccAddress : ccAddresses) {
message.addRecipient(Message.RecipientType.CC, new InternetAddress(ccAddress));
}
}

MimeMultipart multipart = new MimeMultipart();
MimeBodyPart textPart = new MimeBodyPart();
textPart.setText("BORNFIRE INNOVATIONS");
multipart.addBodyPart(textPart);

List<BTM_Daily_Checklist_Entity> checklistEntities = BTM_Daily_Checklist_Repo.getamcmail(resource_name, service_date, service_time1, service_shift);
for (BTM_Daily_Checklist_Entity entity : checklistEntities) {
MimeBodyPart entityTextPart = new MimeBodyPart();
entityTextPart.setText("Bornfire daily checklist of the resource project " + entity.getProject() + " Resource name: " + entity.getResource_name());
multipart.addBodyPart(entityTextPart);

// Handle all types of files
addAttachment(multipart, entity.getNps_transaction_summary_sheetword_file(), "application/pdf", "NPS-Transaction-Summary-Sheet.pdf");
addAttachment(multipart, entity.getNps_transaction_summary_sheetexcel_file(), "application/vnd.ms-excel", "NPS-Transaction-Summary-Sheet.xlsx");
addAttachment(multipart, entity.getTransaction_monitor_inward_file(), "application/pdf", "Transaction-Monitor-Inward.pdf");
addAttachment(multipart, entity.getTransaction_monitor_outward_file(), "application/pdf", "Transaction-Monitor-Outward.pdf");
addAttachment(multipart, entity.getTransaction_monitor_return_file(), "application/pdf", "Transaction-Monitor-Return.pdf");
addAttachment(multipart, entity.getFailure_transaction_details_file(), "application/pdf", "Failure-Transaction-Details.pdf");
addAttachment(multipart, entity.getHnps_channel_transaction_details_file(), "application/pdf", "HNPS-Channel-Transaction-Details.pdf");
addAttachment(multipart, entity.getMobile_and_internet_channel_transaction_details_file(), "application/pdf", "Mobile-and-Internet-Channel-Transaction-Details.pdf");
}

message.setContent(multipart);

for (int attempt = 0; attempt < MAX_RETRIES; attempt++) {
try {
Transport.send(message);
System.out.println("Message sent successfully.");
return "send successfully";
} catch (MessagingException e) {
e.printStackTrace();
System.err.println("Failed to send email on attempt " + (attempt + 1) + ": " + e.getMessage());
if (attempt < MAX_RETRIES - 1) {
 Thread.sleep(RETRY_DELAY_MS);
} else {
 return "send failed after " + MAX_RETRIES + " attempts: " + e.getMessage();
}
}
}
} catch (Exception e) {
e.printStackTrace();
return "send failed: " + e.getMessage();
}

return "send failed";
}

private void addAttachment(MimeMultipart multipart, byte[] fileData, String mimeType, String fileName) {
if (fileData != null) {
try {
MimeBodyPart attachmentPart = new MimeBodyPart();
DataSource dataSource = new ByteArrayDataSource(fileData, mimeType);
attachmentPart.setDataHandler(new DataHandler(dataSource));
attachmentPart.setFileName(fileName);
multipart.addBodyPart(attachmentPart);
} catch (MessagingException e) {
e.printStackTrace();
System.err.println("Failed to attach file: " + fileName);
}
} else {
System.err.println("No data found for attachment: " + fileName);
}
}
}