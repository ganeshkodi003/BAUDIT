package com.bornfire.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bornfire.entities.PlacementMaintenance;
import com.bornfire.entities.PlacementMaintenanceRep;
import com.ibm.icu.text.DecimalFormat;

@Service
public class sendingmail {

	@Autowired
	PlacementMaintenanceRep placementMaintenanceRep;

	public String sendmail(String from, String username, String password, String to, String host,
			List<String> checkedValues) throws ParseException {

		System.out.println("Hello");
		System.out.println(checkedValues.toString());
		String a = checkedValues.toString();
		String b = a.replace("[", "");
		String c = b.replace("]", "");
		String d = null;
		if (c.charAt(0) == ',') {
			d = c.substring(2);
		} else {
			d = c;
		}

		System.out.println(d);

		String[] numbers = d.split(",\\s*");

		// Use a StringBuilder to construct the output string
		StringBuilder output = new StringBuilder();
		for (int i = 0; i < numbers.length; i++) {
			// Append the single-quoted number
			output.append("'").append(numbers[i]).append("'");
			// Append a comma if it's not the last number
			if (i < numbers.length - 1) {
				output.append(", ");
			}
		}

		// Convert StringBuilder to String and print the result
		String result1 = output.toString();
		System.out.println(result1);
		List<PlacementMaintenance> place = placementMaintenanceRep.getPonullDetails2(checkedValues);
		ArrayList<PlacementMaintenance> PO_Status = new ArrayList<>();

		for (PlacementMaintenance att : place) {
			PlacementMaintenance finallist = new PlacementMaintenance();

			finallist.setPm_email(att.getPm_email());
			finallist.setPo_month(att.getPo_month());
			PO_Status.add(finallist);
		}

		System.out.println(PO_Status.toString());
		System.out.println(">>>>>>>>>" + PO_Status);

		String[] poStatusArray = new String[PO_Status.size()];

		for (int i = 0; i < PO_Status.size(); i++) {
			poStatusArray[i] = PO_Status.get(i).getPm_email();
		}

		System.out.println("Using for loop:");
		for (int i = 0; i < poStatusArray.length; i++) {
			System.out.println(poStatusArray[i].toString());
		}

		HashSet<String> uniqueValues = new HashSet<>();
		String[] stringArrayWithoutDuplicates;

		// Populate the uniqueValues set and remove duplicates
		for (String element : poStatusArray) {
			uniqueValues.add(element);
			uniqueValues.add("Mahalakshmi_Prasad@edgeverve.com");
			uniqueValues.add("RCShekar@edgeverve.com");

		}

		// Convert the set back to an array
		stringArrayWithoutDuplicates = uniqueValues.toArray(new String[0]);

		// Print the array without duplicates
		System.out.println("Array without duplicates:");
		for (String element : stringArrayWithoutDuplicates) {
			System.out.println("{{{{{{{{{{{{{" + element + "}}}}}}}}}}}}}");
		}

		String[] pomonthStatusArray = new String[PO_Status.size()];

		for (int i = 0; i < PO_Status.size(); i++) {
			pomonthStatusArray[i] = PO_Status.get(i).getPo_month();
		}

		System.out.println("Using for month loop:");
		for (int i = 0; i < pomonthStatusArray.length; i++) {
			System.out.println(pomonthStatusArray[i].toString());
		}

		HashSet<String> uniqueValues1 = new HashSet<>();
		String[] stringArrayWithoutDuplicates1;

		// Populate the uniqueValues set and remove duplicates
		for (String element2 : pomonthStatusArray) {
			uniqueValues1.add(element2);
		}

		// Convert the set back to an array
		stringArrayWithoutDuplicates1 = uniqueValues1.toArray(new String[0]);

		// Print the array without duplicates
		System.out.println("Array without duplicates:");
		for (String element3 : stringArrayWithoutDuplicates1) {
			System.out.println("{{{{{{{{{{{{{" + element3 + "}}}}}}}}}}}}}");
		}

		String result = convertArrayToString(stringArrayWithoutDuplicates1);

		System.out.println("+++++++++++++++++" + result);
		String to2[] = { "ragul.r@bornfire.in", "siddhaiyan@bornfire.in", "kalidass.k@bornfire.in" };

		Properties props = new Properties();
		if(username == "ragul.r@bornfire.co.in") {
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.trust", "*");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
		}else {
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtpout.secureserver.net");
			props.put("mail.smtp.port", "587");

			
		}
		
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		


		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.

			// message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			String valueToRemove = "rahul_garg@infosys.com";

			// Remove the value
			String[] newArray = removeElement(stringArrayWithoutDuplicates, valueToRemove);

			for (String recipient : newArray) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			}

			for (String recipient : to2) {
				message.addRecipient(Message.RecipientType.CC, new InternetAddress(recipient));
			}

			// Set Subject: header field
			message.setSubject("Reminder For GRN");

			// Now set the actual message
			message.setText("Hello, this is sample for to check send " + "email using JavaMailAPI ");
			
			StringBuilder sb = new StringBuilder();
			sb.append("<html>");
			sb.append("<head>");
			sb.append("</head>");
			sb.append("<p>");
			sb.append("Dear All,");
			sb.append("</p>");
			sb.append("<p>");
			sb.append("Greetings,");
			sb.append("</p>");
			sb.append("<p>");
			sb.append(
					"We would like to inform you that we are yet to recieve the GRNs in the respect of the following POs for the month of "
							+ result);
			sb.append("</p>");
			sb.append("<table>");
			sb.append(
					"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> PO NO");
			sb.append("</th>");
			sb.append("<th style = \"background: #333; "
					+ "color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> PO Date");
			sb.append("</th>");

			sb.append(
					"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Employee Name");
			sb.append("</th>");
			sb.append(
					"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Employee Number");
			sb.append("</th>");
			sb.append(
					"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Rate");
			sb.append("</th>");
			sb.append(
					"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Bill Total Amount");
			sb.append("</th>");
			sb.append(
					"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Delivery Date");
			sb.append("</th>");
			sb.append(
					"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Month");
			sb.append("</th>");
			sb.append(
					"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Project Manager");
			sb.append("</th>");
			sb.append(
					"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Project Manager Mail");
			sb.append("</th>");
			sb.append(
					"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Location");
			sb.append("</th>");

			for (PlacementMaintenance att : place) {
				sb.append("<tr>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> " + att.getPo_no());
				sb.append("</td>");

				SimpleDateFormat desiredDateFormat = new SimpleDateFormat("dd-MM-yyyy");
				String desiredDateStr = desiredDateFormat.format(att.getPo_date());
				String delidate = desiredDateFormat.format(att.getPo_delivery_date());
				System.out.println("Desired Date String: " + desiredDateStr);

				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> " + desiredDateStr);
				sb.append("</td>");

				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
						+ att.getEmp_name());
				sb.append("</td>");
				sb.append(
						"<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> " + att.getEmp_id());
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
						+ att.getPo_rate_inr());
				sb.append("</td>");

				DecimalFormat f = new DecimalFormat("#,##,##0.00");
				int i = 0;
				if (att.getBill_total_amt().contains(",") && att.getBill_total_amt().contains(".00")) {
					String u = att.getBill_total_amt().substring(0, att.getBill_total_amt().indexOf(".00"));
					System.out.println(u);
					i = Integer.parseInt(u.replace(",", ""));
					System.out.println(i);

					System.out.println(f.format(i));
				} else {
					i = Integer.parseInt(att.getBill_total_amt());
					System.out.println(i);
					System.out.println(f.format(i));
				}

				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> " + f.format(i));
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> " + delidate);
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
						+ att.getPo_month());
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
						+ att.getProj_mgr());
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
						+ att.getPm_email());
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
						+ att.getLocation());
				sb.append("</td>");
				sb.append("</tr>");
			}
			sb.append("</table>");
			sb.append("<p>");
			sb.append("We request you to kindly arrange to release the GRN early so that we can submit our invoices.");
			sb.append("</p>");
			sb.append("<p style=\" margin-bottom: 0.2em;\">Sincerely,</p>");
			sb.append("<p style=\" margin-bottom: 0.2em;\">Bornfire Innovation Private Limited</p>");
			sb.append("<a href=\"https://bornfire.in\">http://bornfire.in</a>");
			sb.append("<p style=\" margin-bottom: 0.5em;\">Note:This is System Generated Message</p>");
			sb.append("</body>");
			sb.append("</html>");

			System.out.println(sb.toString());
			String bo = sb.toString();

			message.setContent(bo, "text/html");

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		return username;
	}

	public static String convertArrayToString(String[] array) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < array.length; i++) {
			sb.append(array[i]);

			if (i < array.length - 1) {
				sb.append(", "); // Adding a comma and space after each element
			} else {
				sb.append("."); // Adding a period after the last element
			}
		}

		return sb.toString();
	}

	public static String[] removeElement(String[] array, String elementToRemove) {
		int indexToRemove = -1;

		// Find the index of the element to remove
		for (int i = 0; i < array.length; i++) {
			if (array[i].equals(elementToRemove)) {
				indexToRemove = i;
				break;
			}
		}

		// If the element is found, create a new array without it
		if (indexToRemove != -1) {
			String[] newArray = new String[array.length - 1];
			for (int i = 0, j = 0; i < array.length; i++) {
				if (i != indexToRemove) {
					newArray[j++] = array[i];
				}
			}
			return newArray;
		} else {
			// If the element is not found, return the original array
			return array;
		}
	}

	public static String removeFirstAndLastChar(String str) {
		if (str == null || str.length() <= 1) {
			return ""; // Return empty string if the input is too short
		}
		return str.substring(1, str.length() - 1);
	}
}