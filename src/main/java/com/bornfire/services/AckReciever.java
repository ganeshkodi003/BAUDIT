package com.bornfire.services;

import java.io.IOException;

import java.io.InputStream;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.BodyPart;

import javax.mail.Folder;

import javax.mail.Message;

import javax.mail.MessagingException;

import javax.mail.Multipart;

import javax.mail.NoSuchProviderException;

import javax.mail.Session;

import javax.mail.internet.MimeMultipart;

import javax.mail.search.FlagTerm;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.bornfire.entities.InvoiceMaster;

import com.bornfire.entities.InvoiceMasterRep;

import com.bornfire.entities.PlacementMaintenance;

import com.bornfire.entities.PlacementMaintenanceRep;

import com.ibm.icu.text.DecimalFormat;

import com.sun.mail.pop3.POP3Store;

import oracle.net.aso.e;

@Service

public class AckReciever {

	@Autowired

	PlacementMaintenanceRep placementMaintenanceRep;
	

	@Autowired

	InvoiceMasterRep invoiceMasterRep;

	// Method to receive and process emails
	public String receiveEmail(String pop3Host, String storeType, String user, String password, PlacementMaintenance placementmaintenance) {
	    try {
	        System.out.println("Starting the email processing...");

	        // Set up email properties and session
	        Properties properties = new Properties();
	        properties.put("mail.pop3.host", pop3Host);
	        Session emailSession = Session.getDefaultInstance(properties);
	        System.out.println("Email session created.");

	        // Connect to the email server
	        POP3Store emailStore = (POP3Store) emailSession.getStore(storeType);
	        emailStore.connect(user, password);
	        System.out.println("Connected to email store.");

	        // Access the inbox folder
	        Folder emailFolder = emailStore.getFolder("INBOX");
	        emailFolder.open(Folder.READ_ONLY);
	       // System.out.println("Opened inbox folder.");

	        // Fetch all messages from the inbox
	        Message[] messages = emailFolder.getMessages();
	      //  System.out.println("Fetched " + messages.length + " messages.");

	        // Limit the number of messages to process (e.g., the last 15 emails)
	        if (messages.length > 0) {
	            Message message = messages[messages.length - 1]; // Last email
	            String subject = message.getSubject();
	            System.out.println("Subject: " + subject);

	            // Extract email content
	            String emailContent = getTextFromMessage(message);
	           // System.out.println("Extracted email content: " + emailContent);  // Now should contain content

	            // Process email if it contains "Acknowledgement"
	            if (subject != null && subject.contains("Acknowledgement")) {
	                System.out.println("Acknowledgement is present in the email.");

	                if (emailContent == null || emailContent.trim().isEmpty()) {
	                    System.out.println("Email content is null or empty. Unable to process.");
	                    return emailContent; // Exit the method to avoid further processing
	                }
	                
	                // Extract the table content (rows)
	                String rows = emailContent.trim(); // Trim whitespace
	                Map<String, String> invoiceToDPMap = new LinkedHashMap<>();

	                // Parse rows to extract key-value pairs (Invoice Number and DP Number)
	                System.out.println("Processing rows...");

	                // Use regex to find the specific pattern in the rows
	                Pattern pattern = Pattern.compile("(BORNFIRE\\d{4}[A-Z]\\d{3})\\s+\\S+\\s+\\S+\\s+\\S+\\s+(0\\d+)");
	                Matcher matcher = pattern.matcher(rows);

	                while (matcher.find()) {
	                    String invoiceNumber = matcher.group(1); // Matches BORNFIRE pattern
	                    String dpNumber = matcher.group(2);      // Matches the following DP Number

	                    invoiceToDPMap.put(invoiceNumber, dpNumber);
	                }

	                // Log extracted key-value pairs
	                System.out.println("Extracted Invoice to DP mappings:");
	                for (Map.Entry<String, String> entry : invoiceToDPMap.entrySet()) {
	                    System.out.println("Invoice No: " + entry.getKey() + ", DP No: " + entry.getValue());
	                }

	                // Process the extracted pairs
	                for (Map.Entry<String, String> entry : invoiceToDPMap.entrySet()) {
	                    String invoiceNo = entry.getKey();
	                    String dpNo = entry.getValue();

	                    System.out.println("Processing Invoice No: " + invoiceNo + ", DP No: " + dpNo);

	                 String updatedDate = CreateAAdd(placementmaintenance, invoiceNo, dpNo);
					
					 if (updatedDate != null) { System.out.println("Updated Record for Invoice: "
						  + invoiceNo); } else {
					  System.out.println("Failed to update record for Invoice: " + invoiceNo); }
						
	                }
	            }
	        }

	        // Close folder and store
	        emailFolder.close(false);
	        emailStore.close();
	        System.out.println("Email folder and store closed successfully.");

	    } catch (Exception e) {
	        System.out.println("Error processing emails: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return "Emails Processed Successfully";
	}

	public String getTextFromMessage(Message message) throws Exception {
	    System.out.println("Processing message content...");

	    // Check if the message is plain text
	    if (message.isMimeType("text/plain")) {
	        System.out.println("Detected MIME type: text/plain");
	        return (String) message.getContent();
	    }
	    
	    // Check if the message is HTML
	    else if (message.isMimeType("text/html")) {
	        System.out.println("Detected MIME type: text/html");
	        String html = (String) message.getContent();
	        return org.jsoup.Jsoup.parse(html).text(); // Extract plain text from HTML
	    }
	    
	    // Handle multipart messages
	    else if (message.isMimeType("multipart/*")) {
	        System.out.println("Detected MIME type: multipart/*");
	        Multipart multipart = (Multipart) message.getContent();
	        return extractMultipartContent(multipart);
	    }

	    System.out.println("Unsupported MIME type or empty content.");
	    return "";
	}
	private String CreateAAdd(PlacementMaintenance placementmaintenance, String invoiceNo, String dpNo) {
	    String updatedDate = null;
	    System.out.println("Updating Invoice: " + invoiceNo + " with DP Number: " + dpNo);

	    try {
	        // Fetch existing record by Invoice Number
	        PlacementMaintenance existingRecord = placementMaintenanceRep.getAlist(invoiceNo);
	        if (existingRecord != null) {
	            // Check if the DP number is already set for the invoice
	            if (existingRecord.getDp_no() != null && existingRecord.getDp_no().equals(dpNo)) {
	                // DP number is already set for this invoice, so log and skip updating
	                System.out.println("Invoice No: " + invoiceNo + " and DP No: " + dpNo + " are already present in the database.");
	            } else {
	                // Update the record with the DP number
	                existingRecord.setDp_no(dpNo);
	                placementMaintenanceRep.save(existingRecord);  // Uncomment to save changes
	                updatedDate = existingRecord.getInv_no();
	                System.out.println("Updated Date for Invoice: " + invoiceNo + " is: " + updatedDate);
	            }
	        } else {
	            System.out.println("No record found for Invoice: " + invoiceNo);
	        }
	    } catch (Exception e) {
	        System.out.println("Error in CreateAAdd: " + e.getMessage());
	        e.printStackTrace();
	    }

	    return updatedDate;
	}


	// Method to extract text content from an email message

	private String extractMultipartContent(Multipart multipart) throws Exception {
	    StringBuilder content = new StringBuilder();
	    
	    // Iterate through each part of the multipart message
	    for (int i = 0; i < multipart.getCount(); i++) {
	        BodyPart bodyPart = multipart.getBodyPart(i);
	        System.out.println("Processing part: " + (i + 1) + ", MIME type: " + bodyPart.getContentType());

	        // If the part is plain text, extract the content
	        if (bodyPart.isMimeType("text/plain")) {
	            content.append(bodyPart.getContent().toString());
	        }
	        
	        // If the part is HTML, extract the plain text from it
	        else if (bodyPart.isMimeType("text/html")) {
	            String html = bodyPart.getContent().toString();
	            content.append(org.jsoup.Jsoup.parse(html).text());
	        }
	        
	        // If it's a nested multipart, recursively extract content
	        else if (bodyPart.isMimeType("multipart/*")) {
	            Multipart nestedMultipart = (Multipart) bodyPart.getContent();
	            content.append(extractMultipartContent(nestedMultipart));
	        }
	    }
	    return content.toString();
	}

}