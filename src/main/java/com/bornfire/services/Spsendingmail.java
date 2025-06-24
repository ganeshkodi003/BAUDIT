package com.bornfire.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
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
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.bornfire.entities.PlacementMaintenance;
import com.bornfire.entities.PlacementMaintenanceRep;
import com.bornfire.entities.ResourceMasterRepo;
import com.ibm.icu.text.DecimalFormat;

@Service
public class Spsendingmail {

	@Autowired
	ResourceMasterRepo resourceMasterRepo;

	@Autowired
	PlacementMaintenanceRep placementMaintenanceRep;

	public String sendmail(String from, String username, String password, String to, String host, String sp,
			String inv_due_date, String inv_date) throws ParseException {

		if (sp.equals("WHITESTONE")) {
			// Recipient's email ID needs to be mentioned.
			/*
			 * String to = "destinationemail@gmail.com";
			 * 
			 * // Sender's email ID needs to be mentioned String from =
			 * "fromemail@gmail.com"; final String username = "manishaspatil";//change
			 * accordingly final String password = "******";//change accordingly
			 * 
			 * // Assuming you are sending email through relay.jangosmtp.net String host =
			 * "relay.jangosmtp.net";
			 */

			System.out.println("Hello");
			List<PlacementMaintenance> place = placementMaintenanceRep.getUpdate(sp, inv_due_date, inv_date);
			ArrayList<PlacementMaintenance> PO_Status = new ArrayList<>();

			for (PlacementMaintenance att : place) {
				PlacementMaintenance finallist = new PlacementMaintenance();

				finallist.setPm_email(att.getPm_email());
				finallist.setPo_month(att.getPo_month());
				finallist.setSp_inv_amt(att.getSp_inv_amt());
				finallist.setSp_inv_cgst(att.getSp_inv_cgst());
				finallist.setSp_inv_sgst(att.getSp_inv_sgst());
				;
				finallist.setSp_inv_tot_gst(att.getSp_inv_tot_gst());
				finallist.setSp_inv_tot_amt(att.getSp_inv_tot_amt());
				PO_Status.add(finallist);
			}

			System.out.println(PO_Status.toString());
			System.out.println(">>>>>>>>>" + PO_Status);

			int suminv = 0;
			int sumsgst = 0;
			int sumcgst = 0;
			int sumtotgst = 0;
			int sumtotamt = 0;
			for (int i = 0; i < PO_Status.size(); i++) {
				String p = PO_Status.get(i).getSp_inv_amt().replace(",", "");
				String p1 = PO_Status.get(i).getSp_inv_sgst().replace(",", "");
				String p2 = PO_Status.get(i).getSp_inv_cgst().replace(",", "");
				String p3 = PO_Status.get(i).getSp_inv_tot_gst().replace(",", "");
				String p4 = PO_Status.get(i).getSp_inv_tot_amt().replace(",", "");
				System.out.println(p.substring(0, p.indexOf(".")));
				String o = p.substring(0, p.indexOf("."));
				String o1 = p1.substring(0, p1.indexOf("."));
				String o2 = p2.substring(0, p2.indexOf("."));
				String o3 = p3.substring(0, p3.indexOf("."));
				String o4 = p4.substring(0, p4.indexOf("."));

				DecimalFormat f = new DecimalFormat("#,##,##0.00");
				// System.out.println(f.format(o));

				suminv += Integer.parseInt(o);
				sumsgst += Integer.parseInt(o1);
				sumcgst += Integer.parseInt(o2);
				sumtotgst += Integer.parseInt(o3);
				sumtotamt += Integer.parseInt(o4);

			}
			System.out.println(suminv + "" + sumsgst + "" + sumcgst + "" + sumtotgst + "" + sumtotamt);
			DecimalFormat f = new DecimalFormat("#,##,##0.00");
			String suminv1 = f.format(suminv);
			String sumsgst1 = f.format(sumsgst);
			String sumcgst1 = f.format(sumcgst);
			String sumtotgst1 = f.format(sumtotgst);
			String sumtotamt1 = f.format(sumtotamt);

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
		
			String to2[] = { "ragul.r@bornfire.in", "siddhaiyan@bornfire.in","kalidass.k@bornfire.in" };
			String to1[] = { "chinnasamy.kandasamy@whitestones.in" };
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.ssl.trust", "*");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.socketFactory.fallback", "false");
			
			
			


			// Get the Session object.
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

				
				  for (String recipient : to1) { 
					  message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); }
				 
				for (String recipient : to2) {
					message.addRecipient(Message.RecipientType.CC, new InternetAddress(recipient));
				}

				// Set Subject: header field
				message.setSubject("RE:Invoices for " + result);

				// Now set the actual message
				message.setText("Hello, this is sample for to check send " + "email using JavaMailAPI ");

				StringBuilder sb = new StringBuilder();
				sb.append("<html>");
				sb.append("<head>");
				sb.append("</head>");
				sb.append("<p>");
				sb.append("Dear Team,");
				sb.append("</p>");
				sb.append("<p>");
				sb.append("Greetings,");
				sb.append("</p>");
				sb.append("<p>");
				sb.append(
						"We furnish here below the details of GRN received from Infosys during the current month and the invoices to be submitted for us for further process.");
				sb.append("</p>");
				sb.append("<table>");

				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> SRL NO");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> PO No");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Employee Name");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Employee Id");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> GRN No");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> GRN Date");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Month");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Efforts");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Rate");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Invoice Amount");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> SGST");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> CGST");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Total GST");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Total Invoice Amount");
				sb.append("</th>");
				int serialNo = 1;
				for (PlacementMaintenance att : place) {
					
					sb.append("<tr>");
					
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> ")
				      .append(serialNo) // Add the serial number
				      .append("</td>");
					
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
							+ att.getPo_no());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
							+ att.getEmp_name());
					sb.append("</td>");

					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getEmp_id());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
							+ att.getGrn_no());
					sb.append("</td>");

					String inputDateString = att.getGrn_date();
					String[] parts = att.getGrn_date().split("-");

					for (String part : parts) {
						System.out.println(part);
					}
					String dd;
					String MM = null;

					int count = parts[0].length();
					if (count == 1) {
						dd = "0" + parts[0];
					} else {
						dd = parts[0];
					}

					if (parts[1].equals("January")) {
						MM = "01";
					} else if (parts[1].equals("February")) {
						MM = "02";
					} else if (parts[1].equals("March")) {
						MM = "03";
					} else if (parts[1].equals("April")) {
						MM = "04";
					} else if (parts[1].equals("May")) {
						MM = "05";
					} else if (parts[1].equals("June")) {
						MM = "06";
					} else if (parts[1].equals("July")) {
						MM = "07";
					} else if (parts[1].equals("August")) {
						MM = "08";
					} else if (parts[1].equals("September")) {
						MM = "09";
					} else if (parts[1].equals("October")) {
						MM = "10";
					} else if (parts[1].equals("November")) {
						MM = "11";
					} else if (parts[1].equals("December")) {
						MM = "12";
					} else {

					}

					String date = dd + "-" + MM + "-" + parts[2];
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> " + date);
					sb.append("</td>");

					/*
					 * String
					 * u=att.getBill_total_amt().substring(0,att.getBill_total_amt().indexOf(".00"))
					 * ; System.out.println(u); int i=Integer.parseInt(u.replace(",", ""));
					 * System.out.println(i); DecimalFormat f = new DecimalFormat("#,##,##0.00");
					 * System.out.println(f.format(i));
					 */

					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
							+ att.getPo_month());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getGrn_efforts());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getSp_rate());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getSp_inv_amt());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getSp_inv_sgst());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getSp_inv_cgst());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getSp_inv_tot_gst());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getSp_inv_tot_amt());
					sb.append("</td>");
					sb.append("</tr>");
					  serialNo++;
				}
				System.out.println(suminv1 + sumsgst1 + sumcgst1 + sumtotgst1 + sumtotamt1);
				sb.append("<tr>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\">" + suminv1);
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\">" + sumsgst1);
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\">" + sumcgst1);
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\">" + sumtotgst1);
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\">" + sumtotamt1);
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("<p>");
				sb.append(
						"We request you to submit the invoices early and ensure that the all the invoices are included in the GST return of the current month.");
				sb.append("</p>");
				sb.append("<p style=\" margin-bottom: 0.2em;\">Warm regards,</p>");
				sb.append("<p style=\" margin-bottom: 0.2em;\">Bornfire Innovation Private Limited</p>");
				sb.append("<a href=\"https://bornfire.in\">http://bornfire.in</a>");
				sb.append("<p style=\" margin-bottom: 0.5em;\">Note:This is System Generated Message</p>");
				/*
				 * sb.append("<div>");
				 * sb.append("<div style=\"width:35%;Text-align:center;float:left;\">"); sb.
				 * append("<h3 style=\"color:orange;font-family:Monotype Corsiva, Times, Serif;\">Accounts Executive</h3>"
				 * ); sb.
				 * append("<img  style = \"width:250px;\" src=\"https://www.bornfire.in/assets/home/logo.png\" alt=\"alternatetext\">"
				 * ); sb.append("</div>"); sb.
				 * append("<div style=\"Text-align:left;Width:60%;float:left;padding-left:40px;\">"
				 * ); sb.
				 * append("<br><br><h1 style=\"font-family: 'Mistral', cursive;color:blue;\">Bornfire Innovation Private Limited</h1>"
				 * + "<br><br><br><h5 style=\"color:brown\">Viji Nivas, Second Floor,</h5>" +
				 * "<h5 style=\"color:red;\">10, Soundaraiyar Street, Ammapet,</h5>" +
				 * "<h5 style=\"color:red;\">Salem-636003, Tamilnadu, India</h5>" +
				 * "<h5 style=\"color:blue;\">Land Line: +91 427 2917802</h5>" +
				 * "<h5 style=\"color:blue\">Mobile: +919884298802</h5>" +
				 * "<a href=\"https://bornfire.in\">http://bornfire.in</a></div>");
				 * sb.append("</div>"); sb.
				 * append("<div style=\"color:blue;font-family:Monotype Corsiva, Times, Serif;\"><h4>Disclaimer:<span style=\"font-size:small;\">The information in this mail is confidential and is intended solely for addressee. Access to this mail by anyone else is unauthorised. Copying or further distribution beyond the original recipient may be unlawful.  We are not responsible for any damage caused by a virus or alteration of the e-mail by a third party or otherwise. The contents of this message may not necessarily represent the views or policies of Bornfire Innovations.</span></h4></div>"
				 * );
				 */
				// sb.append("<img
				// src=\"http://localhost:8080/BTM/images/Bornfire%20Signature.png\"
				// alt=\"alternatetext\">");
				sb.append("</body>");
				sb.append("</html>");

				System.out.println(sb.toString());
				String bo = sb.toString();
				/*
				 * message.setContent( "<h1>This is actual message embedded in HTML tags</h1>" +
				 * "<table  style=\\\"border: 1px solid black;\\\"><tr><th>PO No</th><th>PO Date</th><th>Vendor</th><th>GSTIN</th><th>Employee Name</th><th>Employee No</th><th>PO Rate</th><th>Bill Total Amount</th><th>Delivery Date</th><th>Month</th></tr>"
				 * + "" +
				 * "<tbody th:each=\\\\\\\"border: 1px solid black;\\\\\\\"><tr><td ></td></tr></tbody></table>"
				 * , "text/html"); String test;
				 */
				message.setContent(bo, "text/html");

				// Send message
				Transport.send(message);

				System.out.println("Sent message successfully....");
				System.out.println(
						"#############################################################################################################################################################################");
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		} else if (sp.equals("ASOFT")) {
			// Recipient's email ID needs to be mentioned.
			/*
			 * String to = "destinationemail@gmail.com";
			 * 
			 * // Sender's email ID needs to be mentioned String from =
			 * "fromemail@gmail.com"; final String username = "manishaspatil";//change
			 * accordingly final String password = "******";//change accordingly
			 * 
			 * // Assuming you are sending email through relay.jangosmtp.net String host =
			 * "relay.jangosmtp.net";
			 */

			System.out.println("Hello");
			List<PlacementMaintenance> place = placementMaintenanceRep.getUpdate(sp, inv_due_date, inv_date);
			ArrayList<PlacementMaintenance> PO_Status = new ArrayList<>();

			for (PlacementMaintenance att : place) {
				PlacementMaintenance finallist = new PlacementMaintenance();

				finallist.setPm_email(att.getPm_email());
				finallist.setPo_month(att.getPo_month());
				finallist.setSp_inv_amt(att.getSp_inv_amt());
				// finallist.setSp_inv_cgst(att.getSp_inv_cgst());
				finallist.setSp_inv_igst(att.getSp_inv_igst());
				;
				finallist.setSp_inv_tot_gst(att.getSp_inv_tot_gst());
				finallist.setSp_inv_tot_amt(att.getSp_inv_tot_amt());
				PO_Status.add(finallist);
			}

			System.out.println(PO_Status.toString());
			System.out.println(">>>>>>>>>" + PO_Status);

			int suminv = 0;
			int sumsgst = 0;
			int sumigst = 0;
			int sumtotgst = 0;
			int sumtotamt = 0;
			for (int i = 0; i < PO_Status.size(); i++) {
				String p = PO_Status.get(i).getSp_inv_amt().replace(",", "");
				String p2 = PO_Status.get(i).getSp_inv_igst().replace(",", "");
				String p3 = PO_Status.get(i).getSp_inv_tot_gst().replace(",", "");
				String p4 = PO_Status.get(i).getSp_inv_tot_amt().replace(",", "");
				System.out.println(p.substring(0, p.indexOf(".")));
				String o = p.substring(0, p.indexOf("."));
				// String o1=p1.substring(0,p1.indexOf("."));
				String o2 = p2.substring(0, p2.indexOf("."));
				String o3 = p3.substring(0, p3.indexOf("."));
				String o4 = p4.substring(0, p4.indexOf("."));

				DecimalFormat f = new DecimalFormat("#,##,##0.00");
				// System.out.println(f.format(o));

				suminv += Integer.parseInt(o);
				// sumsgst+=Integer.parseInt( o1);
				sumigst += Integer.parseInt(o2);
				sumtotgst += Integer.parseInt(o3);
				sumtotamt += Integer.parseInt(o4);

			}
			System.out.println(suminv + "" + sumsgst + "" + sumigst + "" + sumtotgst + "" + sumtotamt);
			DecimalFormat f = new DecimalFormat("#,##,##0.00");
			String suminv1 = f.format(suminv);
			String sumsgst1 = f.format(sumsgst);
			String sumigst1 = f.format(sumigst);
			String sumtotgst1 = f.format(sumtotgst);
			String sumtotamt1 = f.format(sumtotamt);

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
			String to1[] = { "sreekar@drtcr.com", "hr@asoftglobal.com", "akumar@asoftglobal.com",
					"kkumar@asoftglobal.com" };
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", "587");

			// Get the Session object.
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
				/*
				 * if(Sp == "WHITESTONE") { message.setRecipients(Message.RecipientType.TO,
				 * InternetAddress.parse(to)); } else if(Sp == "ASOFT") {
				 * message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to)); }
				 * else if(Sp == "STACKPOS") { message.setRecipients(Message.RecipientType.TO,
				 * InternetAddress.parse(to)); }
				 */
				// message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

				for (String recipient : to1) {
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
				}

				for (String recipient : to2) {
					message.addRecipient(Message.RecipientType.CC, new InternetAddress(recipient));
				}

				// Set Subject: header field
				message.setSubject("RE:Invoices for " + result);

				// Now set the actual message
				message.setText("Hello, this is sample for to check send " + "email using JavaMailAPI ");

				StringBuilder sb = new StringBuilder();
				sb.append("<html>");
				sb.append("<head>");
				sb.append("</head>");
				sb.append("<p>");
				sb.append("Dear Team,");
				sb.append("</p>");
				sb.append("<p>");
				sb.append("Greetings,");
				sb.append("</p>");
				sb.append("<p>");
				sb.append(
						"We furnish here below the details of GRN received from Infosys during the current month and the invoices to be submitted for us for further process.");
				sb.append("</p>");
				sb.append("<table>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> PO No");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Employee Name");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Employee Id");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> GRN No");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> GRN Date");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Month");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Efforts");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Rate");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Invoice Amount");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> IGST");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Total GST");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Total Invoice Amount");
				sb.append("</th>");
				int serialNo=1;
				for (PlacementMaintenance att : place) {
					sb.append("<tr>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> ")
				      .append(serialNo) // Add the serial number
				      .append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
							+ att.getPo_no());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
							+ att.getEmp_name());
					sb.append("</td>");

					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getEmp_id());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
							+ att.getGrn_no());
					sb.append("</td>");
					String inputDateString = att.getGrn_date();
					String[] parts = att.getGrn_date().split("-");

					for (String part : parts) {
						System.out.println(part);
					}
					String dd;
					String MM = null;

					int count = parts[0].length();
					if (count == 1) {
						dd = "0" + parts[0];
					} else {
						dd = parts[0];
					}

					if (parts[1].equals("January")) {
						MM = "01";
					} else if (parts[1].equals("February")) {
						MM = "02";
					} else if (parts[1].equals("March")) {
						MM = "03";
					} else if (parts[1].equals("April")) {
						MM = "04";
					} else if (parts[1].equals("May")) {
						MM = "05";
					} else if (parts[1].equals("June")) {
						MM = "06";
					} else if (parts[1].equals("July")) {
						MM = "07";
					} else if (parts[1].equals("August")) {
						MM = "08";
					} else if (parts[1].equals("September")) {
						MM = "09";
					} else if (parts[1].equals("October")) {
						MM = "10";
					} else if (parts[1].equals("November")) {
						MM = "11";
					} else if (parts[1].equals("December")) {
						MM = "12";
					} else {

					}

					String date = dd + "-" + MM + "-" + parts[2];
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> " + date);
					sb.append("</td>");

					/*
					 * String
					 * u=att.getBill_total_amt().substring(0,att.getBill_total_amt().indexOf(".00"))
					 * ; System.out.println(u); int i=Integer.parseInt(u.replace(",", ""));
					 * System.out.println(i); DecimalFormat f = new DecimalFormat("#,##,##0.00");
					 * System.out.println(f.format(i));
					 */

					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
							+ att.getPo_month());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getGrn_efforts());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getSp_rate());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getSp_inv_amt());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getSp_inv_igst());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getSp_inv_tot_gst());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getSp_inv_tot_amt());
					sb.append("</td>");
					sb.append("</tr>");
					serialNo++;
				}
				sb.append("<tr>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\">" + suminv1);
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\">" + sumigst1);
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\">" + sumtotgst1);
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\">" + sumtotamt1);
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("<p>");
				sb.append(
						"We request you to submit the invoices early and ensure that the all the invoices are included in the GST return of the current month.");
				sb.append("</p>");
				sb.append("<p style=\" margin-bottom: 0.2em;\">Warm regards,</p>");
				sb.append("<p style=\" margin-bottom: 0.2em;\">Bornfire Innovation Private Limited</p>");
				sb.append("<a href=\"https://bornfire.in\">http://bornfire.in</a>");
				sb.append("<p style=\" margin-bottom: 0.5em;\">Note:This is System Generated Message</p>");
				/*
				 * sb.append("<div>");
				 * sb.append("<div style=\"width:35%;Text-align:center;float:left;\">"); sb.
				 * append("<h3 style=\"color:orange;font-family:Monotype Corsiva, Times, Serif;\">Accounts Executive</h3>"
				 * ); sb.
				 * append("<img  style = \"width:250px;\" src=\"https://www.bornfire.in/assets/home/logo.png\" alt=\"alternatetext\">"
				 * ); sb.append("</div>"); sb.
				 * append("<div style=\"Text-align:left;Width:60%;float:left;padding-left:40px;\">"
				 * ); sb.
				 * append("<br><br><h1 style=\"font-family: 'Mistral', cursive;color:blue;\">Bornfire Innovation Private Limited</h1>"
				 * + "<br><br><br><h5 style=\"color:brown\">Viji Nivas, Second Floor,</h5>" +
				 * "<h5 style=\"color:red;\">10, Soundaraiyar Street, Ammapet,</h5>" +
				 * "<h5 style=\"color:red;\">Salem-636003, Tamilnadu, India</h5>" +
				 * "<h5 style=\"color:blue;\">Land Line: +91 427 2917802</h5>" +
				 * "<h5 style=\"color:blue\">Mobile: +919884298802</h5>" +
				 * "<a href=\"https://bornfire.in\">http://bornfire.in</a></div>");
				 * sb.append("</div>"); sb.
				 * append("<div style=\"color:blue;font-family:Monotype Corsiva, Times, Serif;\"><h4>Disclaimer:<span style=\"font-size:small;\">The information in this mail is confidential and is intended solely for addressee. Access to this mail by anyone else is unauthorised. Copying or further distribution beyond the original recipient may be unlawful.  We are not responsible for any damage caused by a virus or alteration of the e-mail by a third party or otherwise. The contents of this message may not necessarily represent the views or policies of Bornfire Innovations.</span></h4></div>"
				 * );
				 */
				// sb.append("<img
				// src=\"http://localhost:8080/BTM/images/Bornfire%20Signature.png\"
				// alt=\"alternatetext\">");
				sb.append("</body>");
				sb.append("</html>");

				System.out.println(sb.toString());
				String bo = sb.toString();
				/*
				 * message.setContent( "<h1>This is actual message embedded in HTML tags</h1>" +
				 * "<table  style=\\\"border: 1px solid black;\\\"><tr><th>PO No</th><th>PO Date</th><th>Vendor</th><th>GSTIN</th><th>Employee Name</th><th>Employee No</th><th>PO Rate</th><th>Bill Total Amount</th><th>Delivery Date</th><th>Month</th></tr>"
				 * + "" +
				 * "<tbody th:each=\\\\\\\"border: 1px solid black;\\\\\\\"><tr><td ></td></tr></tbody></table>"
				 * , "text/html"); String test;
				 */
				message.setContent(bo, "text/html");

				// Send message
				Transport.send(message);

				System.out.println("Sent message successfully....");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		} else if (sp.equals("MISCOT")) {
			// Recipient's email ID needs to be mentioned.
			/*
			 * String to = "destinationemail@gmail.com";
			 * 
			 * // Sender's email ID needs to be mentioned String from =
			 * "fromemail@gmail.com"; final String username = "manishaspatil";//change
			 * accordingly final String password = "******";//change accordingly
			 * 
			 * // Assuming you are sending email through relay.jangosmtp.net String host =
			 * "relay.jangosmtp.net";
			 */

			System.out.println("Hello");
			List<PlacementMaintenance> place = placementMaintenanceRep.getUpdate(sp, inv_due_date, inv_date);
			ArrayList<PlacementMaintenance> PO_Status = new ArrayList<>();

			for (PlacementMaintenance att : place) {
				PlacementMaintenance finallist = new PlacementMaintenance();

				finallist.setPm_email(att.getPm_email());
				finallist.setPo_month(att.getPo_month());
				finallist.setSp_inv_amt(att.getSp_inv_amt());
				// finallist.setSp_inv_cgst(att.getSp_inv_cgst());
				finallist.setSp_inv_igst(att.getSp_inv_igst());
				;
				finallist.setSp_inv_tot_gst(att.getSp_inv_tot_gst());
				finallist.setSp_inv_tot_amt(att.getSp_inv_tot_amt());
				PO_Status.add(finallist);
			}

			System.out.println(PO_Status.toString());
			System.out.println(">>>>>>>>>" + PO_Status);

			int suminv = 0;
			int sumsgst = 0;
			int sumigst = 0;
			int sumtotgst = 0;
			int sumtotamt = 0;
			for (int i = 0; i < PO_Status.size(); i++) {
				String p = PO_Status.get(i).getSp_inv_amt().replace(",", "");
				String p2 = PO_Status.get(i).getSp_inv_igst().replace(",", "");
				String p3 = PO_Status.get(i).getSp_inv_tot_gst().replace(",", "");
				String p4 = PO_Status.get(i).getSp_inv_tot_amt().replace(",", "");
				System.out.println(p.substring(0, p.indexOf(".")));
				String o = p.substring(0, p.indexOf("."));
				// String o1=p1.substring(0,p1.indexOf("."));
				String o2 = p2.substring(0, p2.indexOf("."));
				String o3 = p3.substring(0, p3.indexOf("."));
				String o4 = p4.substring(0, p4.indexOf("."));

				DecimalFormat f = new DecimalFormat("#,##,##0.00");
				// System.out.println(f.format(o));

				suminv += Integer.parseInt(o);
				// sumsgst+=Integer.parseInt( o1);
				sumigst += Integer.parseInt(o2);
				sumtotgst += Integer.parseInt(o3);
				sumtotamt += Integer.parseInt(o4);

			}
			System.out.println(suminv + "" + sumsgst + "" + sumigst + "" + sumtotgst + "" + sumtotamt);
			DecimalFormat f = new DecimalFormat("#,##,##0.00");
			String suminv1 = f.format(suminv);
			String sumsgst1 = f.format(sumsgst);
			String sumigst1 = f.format(sumigst);
			String sumtotgst1 = f.format(sumtotgst);
			String sumtotamt1 = f.format(sumtotamt);

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
			String to1[] = { "sukanya.c@miscot.com" };
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");

			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", "25");

			// Get the Session object.
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
				/*
				 * if(Sp == "WHITESTONE") { message.setRecipients(Message.RecipientType.TO,
				 * InternetAddress.parse(to)); } else if(Sp == "ASOFT") {
				 * message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to)); }
				 * else if(Sp == "STACKPOS") { message.setRecipients(Message.RecipientType.TO,
				 * InternetAddress.parse(to)); }
				 */
				// message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

				for (String recipient : to1) {
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
				}

				for (String recipient : to2) {
					message.addRecipient(Message.RecipientType.CC, new InternetAddress(recipient));
				}

				// Set Subject: header field
				message.setSubject("RE:Invoices for " + result);

				// Now set the actual message
				message.setText("Hello, this is sample for to check send " + "email using JavaMailAPI ");

				StringBuilder sb = new StringBuilder();
				sb.append("<html>");
				sb.append("<head>");
				sb.append("</head>");
				sb.append("<p>");
				sb.append("Dear Team,");
				sb.append("</p>");
				sb.append("<p>");
				sb.append("Greetings,");
				sb.append("</p>");
				sb.append("<p>");
				sb.append(
						"We furnish here below the details of GRN received from Infosys during the current month and the invoices to be submitted for us for further process.");
				sb.append("</p>");
				sb.append("<table>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> PO No");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Employee Name");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Employee Id");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> GRN No");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> GRN Date");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Month");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Efforts");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Rate");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Invoice Amount");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> IGST");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Total GST");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Total Invoice Amount");
				sb.append("</th>");
				for (PlacementMaintenance att : place) {
					sb.append("<tr>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
							+ att.getPo_no());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
							+ att.getEmp_name());
					sb.append("</td>");

					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getEmp_id());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
							+ att.getGrn_no());
					sb.append("</td>");
					String inputDateString = att.getGrn_date();
					String[] parts = att.getGrn_date().split("-");

					for (String part : parts) {
						System.out.println(part);
					}
					String dd;
					String MM = null;

					int count = parts[0].length();
					if (count == 1) {
						dd = "0" + parts[0];
					} else {
						dd = parts[0];
					}

					if (parts[1].equals("January")) {
						MM = "01";
					} else if (parts[1].equals("February")) {
						MM = "02";
					} else if (parts[1].equals("March")) {
						MM = "03";
					} else if (parts[1].equals("April")) {
						MM = "04";
					} else if (parts[1].equals("May")) {
						MM = "05";
					} else if (parts[1].equals("June")) {
						MM = "06";
					} else if (parts[1].equals("July")) {
						MM = "07";
					} else if (parts[1].equals("August")) {
						MM = "08";
					} else if (parts[1].equals("September")) {
						MM = "09";
					} else if (parts[1].equals("October")) {
						MM = "10";
					} else if (parts[1].equals("November")) {
						MM = "11";
					} else if (parts[1].equals("December")) {
						MM = "12";
					} else {

					}

					String date = dd + "-" + MM + "-" + parts[2];
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> " + date);
					sb.append("</td>");

					/*
					 * String
					 * u=att.getBill_total_amt().substring(0,att.getBill_total_amt().indexOf(".00"))
					 * ; System.out.println(u); int i=Integer.parseInt(u.replace(",", ""));
					 * System.out.println(i); DecimalFormat f = new DecimalFormat("#,##,##0.00");
					 * System.out.println(f.format(i));
					 */

					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
							+ att.getPo_month());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getGrn_efforts());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getSp_rate());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getSp_inv_amt());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getSp_inv_igst());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getSp_inv_tot_gst());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getSp_inv_tot_amt());
					sb.append("</td>");
					sb.append("</tr>");
				}
				sb.append("<tr>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\">" + suminv1);
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\">" + sumigst1);
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\">" + sumtotgst1);
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\">" + sumtotamt1);
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("<p>");
				sb.append(
						"We request you to submit the invoices early and ensure that the all the invoices are included in the GST return of the current month.");
				sb.append("</p>");
				sb.append("<p style=\" margin-bottom: 0.2em;\">Warm regards,</p>");
				sb.append("<p style=\" margin-bottom: 0.2em;\">Bornfire Innovation Private Limited</p>");
				sb.append("<a href=\"https://bornfire.in\">http://bornfire.in</a>");
				sb.append("<p style=\" margin-bottom: 0.5em;\">Note:This is System Generated Message</p>");
				/*
				 * sb.append("<div>");
				 * sb.append("<div style=\"width:35%;Text-align:center;float:left;\">"); sb.
				 * append("<h3 style=\"color:orange;font-family:Monotype Corsiva, Times, Serif;\">Accounts Executive</h3>"
				 * ); sb.
				 * append("<img  style = \"width:250px;\" src=\"https://www.bornfire.in/assets/home/logo.png\" alt=\"alternatetext\">"
				 * ); sb.append("</div>"); sb.
				 * append("<div style=\"Text-align:left;Width:60%;float:left;padding-left:40px;\">"
				 * ); sb.
				 * append("<br><br><h1 style=\"font-family: 'Mistral', cursive;color:blue;\">Bornfire Innovation Private Limited</h1>"
				 * + "<br><br><br><h5 style=\"color:brown\">Viji Nivas, Second Floor,</h5>" +
				 * "<h5 style=\"color:red;\">10, Soundaraiyar Street, Ammapet,</h5>" +
				 * "<h5 style=\"color:red;\">Salem-636003, Tamilnadu, India</h5>" +
				 * "<h5 style=\"color:blue;\">Land Line: +91 427 2917802</h5>" +
				 * "<h5 style=\"color:blue\">Mobile: +919884298802</h5>" +
				 * "<a href=\"https://bornfire.in\">http://bornfire.in</a></div>");
				 * sb.append("</div>"); sb.
				 * append("<div style=\"color:blue;font-family:Monotype Corsiva, Times, Serif;\"><h4>Disclaimer:<span style=\"font-size:small;\">The information in this mail is confidential and is intended solely for addressee. Access to this mail by anyone else is unauthorised. Copying or further distribution beyond the original recipient may be unlawful.  We are not responsible for any damage caused by a virus or alteration of the e-mail by a third party or otherwise. The contents of this message may not necessarily represent the views or policies of Bornfire Innovations.</span></h4></div>"
				 * );
				 */
				// sb.append("<img
				// src=\"http://localhost:8080/BTM/images/Bornfire%20Signature.png\"
				// alt=\"alternatetext\">");
				sb.append("</body>");
				sb.append("</html>");

				System.out.println(sb.toString());
				String bo = sb.toString();
				/*
				 * message.setContent( "<h1>This is actual message embedded in HTML tags</h1>" +
				 * "<table  style=\\\"border: 1px solid black;\\\"><tr><th>PO No</th><th>PO Date</th><th>Vendor</th><th>GSTIN</th><th>Employee Name</th><th>Employee No</th><th>PO Rate</th><th>Bill Total Amount</th><th>Delivery Date</th><th>Month</th></tr>"
				 * + "" +
				 * "<tbody th:each=\\\\\\\"border: 1px solid black;\\\\\\\"><tr><td ></td></tr></tbody></table>"
				 * , "text/html"); String test;
				 */
				message.setContent(bo, "text/html");

				// Send message
				Transport.send(message);

				System.out.println("Sent message successfully....");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		}

		else if (sp.equals("OMSYS")) {

			System.out.println("Hello");
			List<PlacementMaintenance> place = placementMaintenanceRep.getUpdate(sp, inv_due_date, inv_date);
			ArrayList<PlacementMaintenance> PO_Status = new ArrayList<>();

			for (PlacementMaintenance att : place) {
				PlacementMaintenance finallist = new PlacementMaintenance();

				finallist.setPm_email(att.getPm_email());
				finallist.setPo_month(att.getPo_month());
				finallist.setSp_inv_amt(att.getSp_inv_amt());
				// finallist.setSp_inv_igst(att.getSp_inv_igst());;
				finallist.setSp_inv_sgst(att.getSp_inv_sgst());
				finallist.setSp_inv_cgst(att.getSp_inv_cgst());
				finallist.setSp_inv_igst(att.getSp_inv_igst());
				finallist.setSp_inv_tot_gst(att.getSp_inv_tot_gst());
				finallist.setSp_inv_tot_amt(att.getSp_inv_tot_amt());
				PO_Status.add(finallist);
			}

			System.out.println(PO_Status.toString());
			System.out.println(">>>>>>>>>" + PO_Status);

			int suminv = 0;
			int sumsgst = 0;
			// int sumigst=0;

			int sumcgst = 0;
			int sumigst = 0;
			int sumtotgst = 0;
			int sumtotamt = 0;
			for (int i = 0; i < PO_Status.size(); i++) {
				String p = PO_Status.get(i).getSp_inv_amt().replace(",", "");
				// String p2=PO_Status.get(i).getSp_inv_igst().replace(",", "");
				String p1 = PO_Status.get(i).getSp_inv_sgst().replace(",", "");
				String p2 = PO_Status.get(i).getSp_inv_cgst().replace(",", "");
				String p3 = PO_Status.get(i).getSp_inv_tot_gst().replace(",", "");
				String p4 = PO_Status.get(i).getSp_inv_tot_amt().replace(",", "");
				System.out.println(p.substring(0, p.indexOf(".")));
				String o = p.substring(0, p.indexOf("."));
				// String o2=p2.substring(0,p2.indexOf("."));
				String o1 = p1.substring(0, p1.indexOf("."));
				String o2 = p2.substring(0, p2.indexOf("."));
				String o3 = p3.substring(0, p3.indexOf("."));
				String o4 = p4.substring(0, p4.indexOf("."));

				DecimalFormat f = new DecimalFormat("#,##,##0.00");
				// System.out.println(f.format(o));

				suminv += Integer.parseInt(o);
				sumsgst += Integer.parseInt(o1);
				sumcgst += Integer.parseInt(o2);
				sumtotgst += Integer.parseInt(o3);
				sumtotamt += Integer.parseInt(o4);

			}
			System.out.println(suminv + "" + sumsgst + "" + sumcgst + "" + sumtotgst + "" + sumtotamt);
			DecimalFormat f = new DecimalFormat("#,##,##0.00");
			String suminv1 = f.format(suminv);
			String sumsgst1 = f.format(sumsgst);
			String sumcgst1 = f.format(sumcgst);
			// String sumigst1=f.format(sumigst);
			String sumtotgst1 = f.format(sumtotgst);
			String sumtotamt1 = f.format(sumtotamt);

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
			String to1[] = { "suresh@omsys.in", "hrteam@omsys.in" };

			Properties props = new Properties();

			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", "587");

			// Get the Session object.
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
				/*
				 * if(Sp == "WHITESTONE") { message.setRecipients(Message.RecipientType.TO,
				 * InternetAddress.parse(to)); } else if(Sp == "ASOFT") {
				 * message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to)); }
				 * else if(Sp == "STACKPOS") { message.setRecipients(Message.RecipientType.TO,
				 * InternetAddress.parse(to)); }
				 */
				// message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

				for (String recipient : to1) {
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
				}

				for (String recipient : to2) {
					message.addRecipient(Message.RecipientType.CC, new InternetAddress(recipient));
				}

				// Set Subject: header field
				message.setSubject("RE:Invoices for " + result);

				// Now set the actual message
				message.setText("Hello, this is sample for to check send " + "email using JavaMailAPI ");

				StringBuilder sb = new StringBuilder();
				sb.append("<html>");
				sb.append("<head>");
				sb.append("</head>");
				sb.append("<p>");
				sb.append("Dear Team,");
				sb.append("</p>");
				sb.append("<p>");
				sb.append("Greetings,");
				sb.append("</p>");
				sb.append("<p>");
				sb.append(
						"We furnish here below the details of GRN received from Infosys during the current month and the invoices to be submitted for us for further process.");
				sb.append("</p>");
				sb.append("<table>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> PO No");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Employee Name");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Employee Id");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> GRN No");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> GRN Date");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Month");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Efforts");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Rate");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Invoice Amount");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> SGST");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> CGST");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Total GST");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Total Invoice Amount");
				sb.append("</th>");
				for (PlacementMaintenance att : place) {
					sb.append("<tr>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
							+ att.getPo_no());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
							+ att.getEmp_name());
					sb.append("</td>");

					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getEmp_id());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
							+ att.getGrn_no());
					sb.append("</td>");
					String inputDateString = att.getGrn_date();
					String[] parts = att.getGrn_date().split("-");

					for (String part : parts) {
						System.out.println(part);
					}
					String dd;
					String MM = null;

					int count = parts[0].length();
					if (count == 1) {
						dd = "0" + parts[0];
					} else {
						dd = parts[0];
					}

					if (parts[1].equals("January")) {
						MM = "01";
					} else if (parts[1].equals("February")) {
						MM = "02";
					} else if (parts[1].equals("March")) {
						MM = "03";
					} else if (parts[1].equals("April")) {
						MM = "04";
					} else if (parts[1].equals("May")) {
						MM = "05";
					} else if (parts[1].equals("June")) {
						MM = "06";
					} else if (parts[1].equals("July")) {
						MM = "07";
					} else if (parts[1].equals("August")) {
						MM = "08";
					} else if (parts[1].equals("September")) {
						MM = "09";
					} else if (parts[1].equals("October")) {
						MM = "10";
					} else if (parts[1].equals("November")) {
						MM = "11";
					} else if (parts[1].equals("December")) {
						MM = "12";
					} else {

					}

					String date = dd + "-" + MM + "-" + parts[2];
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> " + date);
					sb.append("</td>");

					/*
					 * String
					 * u=att.getBill_total_amt().substring(0,att.getBill_total_amt().indexOf(".00"))
					 * ; System.out.println(u); int i=Integer.parseInt(u.replace(",", ""));
					 * System.out.println(i); DecimalFormat f = new DecimalFormat("#,##,##0.00");
					 * System.out.println(f.format(i));
					 */

					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
							+ att.getPo_month());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getGrn_efforts());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getSp_rate());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getSp_inv_amt());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getSp_inv_sgst());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getSp_inv_cgst());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getSp_inv_tot_gst());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getSp_inv_tot_amt());
					sb.append("</td>");
					sb.append("</tr>");
				}
				sb.append("<tr>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\">" + suminv1);
				sb.append("</td>");
				// sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align:
				// right;\">"+sumigst1 );
				// sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\">" + sumsgst1);
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\">" + sumcgst1);
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\">" + sumtotgst1);
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\">" + sumtotamt1);
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("<p>");
				sb.append(
						"We request you to submit the invoices early and ensure that the all the invoices are included in the GST return of the current month.");
				sb.append("</p>");
				sb.append("<p style=\" margin-bottom: 0.2em;\">Warm regards,</p>");
				sb.append("<p style=\" margin-bottom: 0.2em;\">Bornfire Innovation Private Limited</p>");
				sb.append("<a href=\"https://bornfire.in\">http://bornfire.in</a>");
				sb.append("<p style=\" margin-bottom: 0.5em;\">Note:This is System Generated Message</p>");
				/*
				 * sb.append("<div>");
				 * sb.append("<div style=\"width:35%;Text-align:center;float:left;\">"); sb.
				 * append("<h3 style=\"color:orange;font-family:Monotype Corsiva, Times, Serif;\">Accounts Executive</h3>"
				 * ); sb.
				 * append("<img  style = \"width:250px;\" src=\"https://www.bornfire.in/assets/home/logo.png\" alt=\"alternatetext\">"
				 * ); sb.append("</div>"); sb.
				 * append("<div style=\"Text-align:left;Width:60%;float:left;padding-left:40px;\">"
				 * ); sb.
				 * append("<br><br><h1 style=\"font-family: 'Mistral', cursive;color:blue;\">Bornfire Innovation Private Limited</h1>"
				 * + "<br><br><br><h5 style=\"color:brown\">Viji Nivas, Second Floor,</h5>" +
				 * "<h5 style=\"color:red;\">10, Soundaraiyar Street, Ammapet,</h5>" +
				 * "<h5 style=\"color:red;\">Salem-636003, Tamilnadu, India</h5>" +
				 * "<h5 style=\"color:blue;\">Land Line: +91 427 2917802</h5>" +
				 * "<h5 style=\"color:blue\">Mobile: +919884298802</h5>" +
				 * "<a href=\"https://bornfire.in\">http://bornfire.in</a></div>");
				 * sb.append("</div>"); sb.
				 * append("<div style=\"color:blue;font-family:Monotype Corsiva, Times, Serif;\"><h4>Disclaimer:<span style=\"font-size:small;\">The information in this mail is confidential and is intended solely for addressee. Access to this mail by anyone else is unauthorised. Copying or further distribution beyond the original recipient may be unlawful.  We are not responsible for any damage caused by a virus or alteration of the e-mail by a third party or otherwise. The contents of this message may not necessarily represent the views or policies of Bornfire Innovations.</span></h4></div>"
				 * );
				 */
				// sb.append("<img
				// src=\"http://localhost:8080/BTM/images/Bornfire%20Signature.png\"
				// alt=\"alternatetext\">");
				sb.append("</body>");
				sb.append("</html>");

				System.out.println(sb.toString());
				String bo = sb.toString();
				/*
				 * message.setContent( "<h1>This is actual message embedded in HTML tags</h1>" +
				 * "<table  style=\\\"border: 1px solid black;\\\"><tr><th>PO No</th><th>PO Date</th><th>Vendor</th><th>GSTIN</th><th>Employee Name</th><th>Employee No</th><th>PO Rate</th><th>Bill Total Amount</th><th>Delivery Date</th><th>Month</th></tr>"
				 * + "" +
				 * "<tbody th:each=\\\\\\\"border: 1px solid black;\\\\\\\"><tr><td ></td></tr></tbody></table>"
				 * , "text/html"); String test;
				 */
				message.setContent(bo, "text/html");

				// Send message
				Transport.send(message);

				System.out.println("Sent message successfully....");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
		}

		else if (sp.equals("STACKPOS")) {
			// Recipient's email ID needs to be mentioned.
			/*
			 * String to = "destinationemail@gmail.com";
			 * 
			 * // Sender's email ID needs to be mentioned String from =
			 * "fromemail@gmail.com"; final String username = "manishaspatil";//change
			 * accordingly final String password = "******";//change accordingly
			 * 
			 * // Assuming you are sending email through relay.jangosmtp.net String host =
			 * "relay.jangosmtp.net";
			 */

			System.out.println("Hello");
			List<PlacementMaintenance> place = placementMaintenanceRep.getUpdate(sp, inv_due_date, inv_date);
			ArrayList<PlacementMaintenance> PO_Status = new ArrayList<>();

			for (PlacementMaintenance att : place) {
				PlacementMaintenance finallist = new PlacementMaintenance();

				finallist.setPm_email(att.getPm_email());
				finallist.setPo_month(att.getPo_month());
				finallist.setSp_inv_amt(att.getSp_inv_amt());
				finallist.setSp_inv_cgst(att.getSp_inv_cgst());
				finallist.setSp_inv_sgst(att.getSp_inv_sgst());
				;
				finallist.setSp_inv_tot_gst(att.getSp_inv_tot_gst());
				finallist.setSp_inv_tot_amt(att.getSp_inv_tot_amt());
				PO_Status.add(finallist);
			}

			System.out.println(PO_Status.toString());
			System.out.println(">>>>>>>>>" + PO_Status);

			int suminv = 0;
			int sumsgst = 0;
			int sumcgst = 0;
			int sumtotgst = 0;
			int sumtotamt = 0;
			for (int i = 0; i < PO_Status.size(); i++) {
				String p = PO_Status.get(i).getSp_inv_amt().replace(",", "");
				String p1 = PO_Status.get(i).getSp_inv_sgst().replace(",", "");
				String p2 = PO_Status.get(i).getSp_inv_cgst().replace(",", "");
				String p3 = PO_Status.get(i).getSp_inv_tot_gst().replace(",", "");
				String p4 = PO_Status.get(i).getSp_inv_tot_amt().replace(",", "");
				System.out.println(p.substring(0, p.indexOf(".")));
				String o = p.substring(0, p.indexOf("."));
				String o1 = p1.substring(0, p1.indexOf("."));
				String o2 = p2.substring(0, p2.indexOf("."));
				String o3 = p3.substring(0, p3.indexOf("."));
				String o4 = p4.substring(0, p4.indexOf("."));

				DecimalFormat f = new DecimalFormat("#,##,##0.00");
				// System.out.println(f.format(o));

				suminv += Integer.parseInt(o);
				sumsgst += Integer.parseInt(o1);
				sumcgst += Integer.parseInt(o2);
				sumtotgst += Integer.parseInt(o3);
				sumtotamt += Integer.parseInt(o4);

			}
			System.out.println(suminv + "" + sumsgst + "" + sumcgst + "" + sumtotgst + "" + sumtotamt);
			DecimalFormat f = new DecimalFormat("#,##,##0.00");
			String suminv1 = f.format(suminv);
			String sumsgst1 = f.format(sumsgst);
			String sumcgst1 = f.format(sumcgst);
			String sumtotgst1 = f.format(sumtotgst);
			String sumtotamt1 = f.format(sumtotamt);

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
			String to2[] = { "ragul.r@bornfire.co.in", "siddhaiyan@bornfire.in", "kalidass.k@bornfire.in" };
			String to1[] = { "mani@stackpos.in", "manigandan.kutti@gmail.com", "accounts@stackpos.in" };
			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", "25");

			// Get the Session object.
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
				/*
				 * if(Sp == "WHITESTONE") { message.setRecipients(Message.RecipientType.TO,
				 * InternetAddress.parse(to)); } else if(Sp == "ASOFT") {
				 * message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to)); }
				 * else if(Sp == "STACKPOS") { message.setRecipients(Message.RecipientType.TO,
				 * InternetAddress.parse(to)); }
				 */
				// message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

				
				  for (String recipient : to1) { message.addRecipient(Message.RecipientType.TO,
				  new InternetAddress(recipient)); }
				 

				for (String recipient : to2) {
					message.addRecipient(Message.RecipientType.CC, new InternetAddress(recipient));
				}

				// Set Subject: header field
				message.setSubject("RE:Invoices for " + result);

				// Now set the actual message
				message.setText("Hello, this is sample for to check send " + "email using JavaMailAPI ");

				StringBuilder sb = new StringBuilder();
				sb.append("<html>");
				sb.append("<head>");
				sb.append("</head>");
				sb.append("<p>");
				sb.append("Dear Team,");
				sb.append("</p>");
				sb.append("<p>");
				sb.append("Greetings,");
				sb.append("</p>");
				sb.append("<p>");
				sb.append(
						"We furnish here below the details of GRN received from Infosys during the current month and the invoices to be submitted for us for further process.");
				sb.append("</p>");
				sb.append("<table>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> SRL NO");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> PO No");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Employee Name");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Employee Id");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> GRN No");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> GRN Date");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Month");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Efforts");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Rate");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Invoice Amount");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> SGST");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> CGST");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Total GST");
				sb.append("</th>");
				sb.append(
						"<th style = \"background: #333; color: white; font-weight: bold; padding: 6px; border: 1px solid #ccc; text-align: left;\"> Total Invoice Amount");
				sb.append("</th>");
				int serialNo=1;
				for (PlacementMaintenance att : place) {
					sb.append("<tr>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> ")
				      .append(serialNo) // Add the serial number
				      .append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
							+ att.getPo_no());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
							+ att.getEmp_name());
					sb.append("</td>");

					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getEmp_id());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
							+ att.getGrn_no());
					sb.append("</td>");
					String inputDateString = att.getGrn_date();
					String[] parts = att.getGrn_date().split("-");

					for (String part : parts) {
						System.out.println(part);
					}
					String dd;
					String MM = null;

					int count = parts[0].length();
					if (count == 1) {
						dd = "0" + parts[0];
					} else {
						dd = parts[0];
					}

					if (parts[1].equals("January")) {
						MM = "01";
					} else if (parts[1].equals("February")) {
						MM = "02";
					} else if (parts[1].equals("March")) {
						MM = "03";
					} else if (parts[1].equals("April")) {
						MM = "04";
					} else if (parts[1].equals("May")) {
						MM = "05";
					} else if (parts[1].equals("June")) {
						MM = "06";
					} else if (parts[1].equals("July")) {
						MM = "07";
					} else if (parts[1].equals("August")) {
						MM = "08";
					} else if (parts[1].equals("September")) {
						MM = "09";
					} else if (parts[1].equals("October")) {
						MM = "10";
					} else if (parts[1].equals("November")) {
						MM = "11";
					} else if (parts[1].equals("December")) {
						MM = "12";
					} else {

					}

					String date = dd + "-" + MM + "-" + parts[2];
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> " + date);
					sb.append("</td>");

					/*
					 * String
					 * u=att.getBill_total_amt().substring(0,att.getBill_total_amt().indexOf(".00"))
					 * ; System.out.println(u); int i=Integer.parseInt(u.replace(",", ""));
					 * System.out.println(i); DecimalFormat f = new DecimalFormat("#,##,##0.00");
					 * System.out.println(f.format(i));
					 */

					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\"> "
							+ att.getPo_month());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getGrn_efforts());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getSp_rate());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getSp_inv_amt());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getSp_inv_sgst());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getSp_inv_cgst());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getSp_inv_tot_gst());
					sb.append("</td>");
					sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\"> "
							+ att.getSp_inv_tot_amt());
					sb.append("</td>");
					sb.append("</tr>");
					serialNo++;
				}
				sb.append("<tr>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				
				sb.append("</td>");
				
                 sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: left;\">");
				
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\">" + suminv1);
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\">" + sumsgst1);
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\">" + sumcgst1);
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\">" + sumtotgst1);
				sb.append("</td>");
				sb.append("<td style = \"padding: 6px; border: 1px solid #ccc; text-align: right;\">" + sumtotamt1);
				sb.append("</td>");
				sb.append("</tr>");
				sb.append("</table>");
				sb.append("<p>");
				sb.append(
						"We request you to submit the invoices early and ensure that the all the invoices are included in the GST return of the current month.");
				sb.append("</p>");
				sb.append("<p style=\" margin-bottom: 0.2em;\">Warm regards,</p>");
				sb.append("<p style=\" margin-bottom: 0.2em;\">Bornfire Innovation Private Limited</p>");
				sb.append("<a href=\"https://bornfire.in\">http://bornfire.in</a>");
				sb.append("<p style=\" margin-bottom: 0.5em;\">Note:This is System Generated Message</p>");
				/*
				 * sb.append("<div>");
				 * sb.append("<div style=\"width:35%;Text-align:center;float:left;\">"); sb.
				 * append("<h3 style=\"color:orange;font-family:Monotype Corsiva, Times, Serif;\">Accounts Executive</h3>"
				 * ); sb.
				 * append("<img  style = \"width:250px;\" src=\"https://www.bornfire.in/assets/home/logo.png\" alt=\"alternatetext\">"
				 * ); sb.append("</div>"); sb.
				 * append("<div style=\"Text-align:left;Width:60%;float:left;padding-left:40px;\">"
				 * ); sb.
				 * append("<br><br><h1 style=\"font-family: 'Mistral', cursive;color:blue;\">Bornfire Innovation Private Limited</h1>"
				 * + "<br><br><br><h5 style=\"color:brown\">Viji Nivas, Second Floor,</h5>" +
				 * "<h5 style=\"color:red;\">10, Soundaraiyar Street, Ammapet,</h5>" +
				 * "<h5 style=\"color:red;\">Salem-636003, Tamilnadu, India</h5>" +
				 * "<h5 style=\"color:blue;\">Land Line: +91 427 2917802</h5>" +
				 * "<h5 style=\"color:blue\">Mobile: +919884298802</h5>" +
				 * "<a href=\"https://bornfire.in\">http://bornfire.in</a></div>");
				 * sb.append("</div>"); sb.
				 * append("<div style=\"color:blue;font-family:Monotype Corsiva, Times, Serif;\"><h4>Disclaimer:<span style=\"font-size:small;\">The information in this mail is confidential and is intended solely for addressee. Access to this mail by anyone else is unauthorised. Copying or further distribution beyond the original recipient may be unlawful.  We are not responsible for any damage caused by a virus or alteration of the e-mail by a third party or otherwise. The contents of this message may not necessarily represent the views or policies of Bornfire Innovations.</span></h4></div>"
				 * );
				 */
				// sb.append("<img
				// src=\"http://localhost:8080/BTM/images/Bornfire%20Signature.png\"
				// alt=\"alternatetext\">");
				sb.append("</body>");
				sb.append("</html>");

				System.out.println(sb.toString());
				String bo = sb.toString();
				/*
				 * message.setContent( "<h1>This is actual message embedded in HTML tags</h1>" +
				 * "<table  style=\\\"border: 1px solid black;\\\"><tr><th>PO No</th><th>PO Date</th><th>Vendor</th><th>GSTIN</th><th>Employee Name</th><th>Employee No</th><th>PO Rate</th><th>Bill Total Amount</th><th>Delivery Date</th><th>Month</th></tr>"
				 * + "" +
				 * "<tbody th:each=\\\\\\\"border: 1px solid black;\\\\\\\"><tr><td ></td></tr></tbody></table>"
				 * , "text/html"); String test;
				 */
				message.setContent(bo, "text/html");

				// Send message
				Transport.send(message);

				System.out.println("Sent message successfully....");

			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
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

	
}