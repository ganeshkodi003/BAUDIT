package com.bornfire.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bornfire.entities.InvoiceMaster;
import com.bornfire.entities.InvoiceMasterRep;
import com.bornfire.entities.PlacementMaintenance;
import com.bornfire.entities.PlacementMaintenanceRep;
import com.ibm.icu.text.DecimalFormat;
import com.itextpdf.text.log.SysoCounter;
import com.sun.mail.pop3.POP3Store;

@Service

public class RecievingMail {

	@Autowired

	PlacementMaintenanceRep placementMaintenanceRep;

	@Autowired

	InvoiceMasterRep invoiceMasterRep;
	
	
	@Autowired
	LogService logService;
	

	public String receiveEmail(String pop3Host, String storeType, String user, String password,

			PlacementMaintenance placementmaintenance) {

		POP3Store emailStore = null;
		Folder emailFolder = null;
		try {

			Properties properties = new Properties();

			properties.put("mail.pop3.host", pop3Host);

			Session emailSession = Session.getDefaultInstance(properties);

			final int MAX_MESSAGES = 1;

			emailStore = (POP3Store) emailSession.getStore(storeType);

			emailStore.connect(user, password);

			emailFolder = emailStore.getFolder("INBOX");

			emailFolder.open(Folder.READ_ONLY);

			int end = emailFolder.getMessageCount();
			System.out.println("End Mail" + end);
			int start = end - MAX_MESSAGES + 1;
			System.out.println("Start Mail" + start);
			Message messages[] = emailFolder.getMessages(start, end);

			String Sub = null;

			for (int j = 0; j < messages.length; j++) {

				Message messaged = messages[j];

				System.out.println(
						"---------------------------------------------------------------------------------------");

				System.out.println("Email Number " + (j + 1));

				Sub = messaged.getSubject();

				System.out.println("Subject: " + messaged.getSubject());

				String M = getTextFromMessage(messaged);

			}

			System.out.println("Subject :" + Sub);

			String S2 = Sub.substring(0, 7);

			String S1 = Sub.substring(0, 7);

			String S3 = Sub.substring(0, 8);

			System.out.println("S1 subject" + S1.length());

			System.out.println("S2 subject" + S2.length());

			System.out.println("S3 subject" + S3.length());

			if (S1.equals("FW: ECM") || S1.equals("Fw: ECM") || S3.equals("Fwd: ECM")) {

				System.out.println(">>>>>>>>>>>>>>>>INTO THE IFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");

				Message Lmessages[] = emailFolder.getMessages(start, end);

				for (int i = 0; i < Lmessages.length; i++) {

					Message message = Lmessages[i];

					String ss = getTextFromMessage(message);
					System.out.println("Mail Text :" + ss);

					Date d = new Date();

					int year = d.getYear();

					
//					  String states = ss.substring(ss.indexOf("Sent: "),ss.indexOf("To: ragul.r@bornfire.in"));
					 
						
//						  String states = ss.substring(ss.indexOf("Sent: "),
//						 ss.indexOf("To: Ragul R <ragul.r@bornfire.in>"));
						 
					String states = "";

					if (ss.contains("To: ragul.r@bornfire.in")) {
					    states = ss.substring(ss.indexOf("Sent: "), ss.indexOf("To: ragul.r@bornfire.in"));
					} else if (ss.contains("To: Ragul R <ragul.r@bornfire.in>")) {
					    states = ss.substring(ss.indexOf("Sent: "), ss.indexOf("To: Ragul R <ragul.r@bornfire.in>"));
					}


					System.out.println("From Sent to To: " + states);
					String Adate;

					if (states.contains("day, ")) {
						// Extract date when the day of the week is present
						Adate = states.substring(states.indexOf("day, "));
						System.out.println(Adate + " Adate with day");
					} else {
						// Extract date when the day of the week is NOT present
						Adate = states.substring(states.indexOf("Sent: ") + 6);
						System.out.println(Adate + " Adate without day");

						// Normalize Adate to include ", 20" for consistency
						if (!Adate.contains(", 20")) {
							// Convert "07 November 2024 15:01" to "November 7, 2024"
							String[] parts = Adate.split(" ");
							if (parts.length >= 3) {
								Adate = parts[1] + " " + parts[0] + ", " + parts[2];
								System.out.println("Month Date Year :"+Adate);
							}
						}
					}

					

					String Bdate = Adate.substring(0, Adate.indexOf(", 20"));
					System.out.println(Bdate + ": Bdatedddddddddddddddddddddddddddd");

					String Cdate = Bdate.substring(0, Bdate.indexOf(" "));
					
					System.out.println("Cdate: "+Cdate);

					String Ddate = Bdate.substring(Bdate.indexOf(" "));
					
					System.out.println("Ddate: "+Ddate);

	
					String Edate = Adate.substring(Adate.indexOf("202"));

					System.out.println("Edate: "+Edate);
					
					String GRNDate = Ddate.trim() + "-" + Cdate + "-" + Edate.substring(0, 4);

					System.out.println("Final GRNDate: "+GRNDate);
					logService.add("GRN Date:" + GRNDate);

					String state = ss.substring(ss.indexOf("GRN# "), ss.indexOf(" for PO#"));

					String GRN = state.substring(5);

					System.out.println("GRN NO: "+GRN);
					logService.add("GRN NO:" + GRN);

					String empId = ss.substring(ss.indexOf("(") + 1, ss.indexOf(")"));
					System.out.println(empId + "empIddddddddddddddddddddddddddddddddddddd");
					
					String state2 = ss.substring(ss.indexOf("PO# "), ss.indexOf(" )"));

					String PO = state2.substring(4);

					System.out.println("PO NO:"+PO);
					logService.add("PO NO:" + PO);

					String state3 = ss.substring(ss.indexOf("Effort of "), ss.indexOf(" Month"));

					String Effort = state3.substring(10);

					System.out.println("Effort :" + Effort);
					logService.add("Effort:" + Effort);

					String state4 = ss.substring(ss.indexOf("Amount of"), ss.indexOf("INR"));

					System.out.println(state4);

					String Amount = state4.substring(10);

					System.out.println("GRN Amount:"+Amount);
					logService.add("GRN Amount:" + Amount);

					String state5 = null;
					String state6 = null;
					try {
						state5 = ss.substring(ss.indexOf("Hi, GRN#"), ss.indexOf(") ,"));
						System.out.println("State5:"+state5);
						state6 = state5.substring(state5.indexOf("("));
						System.out.println("State6:"+state6);
					} catch (StringIndexOutOfBoundsException e) {
						// Handle the exception when the substring or index operations fail
						state5 = ss.substring(ss.indexOf("Hi, GRN#"), ss.indexOf(", PO#"));
						System.out.println("State5:"+state5);
						state6 = state5.substring(state5.indexOf("("), state5.indexOf(")"));
						System.out.println("State6:"+state6);
					} catch (Exception e) {
						// Handle other exceptions, if needed
						e.printStackTrace();
					}

					String EmpNo = state6.substring(1);

					System.out.println("EMPNO: "+EmpNo);
					logService.add("Employee No:" + EmpNo);

					String state7 = state5.substring(state5.indexOf("raised for Employee "), state5.indexOf("("));

					String EmpName = state7.substring(20);

					System.out.println("EMPNAME: "+EmpName);
					logService.add("Employee Name:" + EmpName);

					String state8 = ss.substring(ss.indexOf("Employee "), ss.indexOf("month"));

					System.out.println(state8);

					String state9 = state8.substring(state8.indexOf("for "));

					System.out.println(state9);

					String Month = state9.substring(4);

					System.out.println("MONTH: "+Month);

					String Gmonth = Month.substring(0, 3);

					System.out.println("GMONTH: "+Gmonth);

					String Umonth = Gmonth.toUpperCase();

					System.out.println("UMONTH: "+Umonth);

					int cyear = year + 1900;

					String GRNmonth = cyear + "-" + Umonth;

					System.out.println("GRNmonth"+GRNmonth);
					logService.add("GRN Month:" + GRNmonth);
					String Fmonth = "00";

					switch (Umonth)

					{

					case "JAN":

						Fmonth = "01";

						System.out.println("01");

						break;

					case "FEB":

						Fmonth = "02";

						System.out.println("02");

						break;

					case "MAR":

						Fmonth = "03";

						System.out.println("03");

						break;

					case "APR":

						Fmonth = "04";

						System.out.println(Fmonth);

						System.out.println("04");

						break;

					case "MAY":

						Fmonth = "05";

						System.out.println("05");

						break;

					case "JUN":

						Fmonth = "06";

						System.out.println("06");

						break;

					case "JUL":

						Fmonth = "07";

						System.out.println("07");

						break;

					case "AUG":

						Fmonth = "08";

						System.out.println("08");

						break;

					case "SEP":

						Fmonth = "09";

						System.out.println("09");

						break;

					case "OCT":

						Fmonth = "10";

						System.out.println("10");

						break;

					case "NOV":

						Fmonth = "11";

						System.out.println("11");

						break;

					case "DEC":

						Fmonth = "12";

						System.out.println("12");

						break;

					default:

						System.out.println("Month is Invalid");

					}

					String ddate = "01/" + Fmonth + "/" + cyear;

					System.out.println(ddate);

					String date = ddate;

					LocalDate givenDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

					LocalDate lastDayOfMonthDateGivenDate = givenDate.withDayOfMonth(

							givenDate.getMonth().length(givenDate.isLeapYear()));

					System.out.println(lastDayOfMonthDateGivenDate);

					LocalDate date1 = lastDayOfMonthDateGivenDate;

					DateTimeFormatter formatters = DateTimeFormatter.ofPattern("d/MM/uuuu");

					String text = date1.format(formatters);

					System.out.println(text.replace("/", ""));

					System.out.println(text);

					String DeliveryDate = text.replace("/", "");

					String po_id = PO + EmpNo + DeliveryDate;
					String po_id1 = PO + DeliveryDate;

					System.out.println("PO ID :"+po_id);
					System.out.println("PO ID1: "+po_id1);

					// String state8=ss.substring(ss.indexOf("PO#"),ss.indexOf(") ,"));

					// System.out.println(EmpName);

					System.out.println("GRN NO: "+GRN +" PO NO: "+ PO +" EFFORTS: "+ Effort +" AMOUNT: "+ Amount + " EMPNO: "+EmpNo +" EMPNAME: "+ EmpName +" GRN DATE: "+ GRNDate);

					// System.out.println(ss.substring());

					CreatePAdd(placementmaintenance, GRN, Amount, Effort, po_id, GRNDate, po_id1, EmpNo);

					/*
					 * StringBuffer alpha = new StringBuffer(),
					 * 
					 * num = new StringBuffer(), special = new StringBuffer();
					 * 
					 * for (int i1=0; i1<ss.length(); i1++)
					 * 
					 * {
					 * 
					 * if (Character.isDigit(ss.charAt(i1)))
					 * 
					 * num.append(ss.charAt(i1));
					 * 
					 * else if(Character.isAlphabetic(ss.charAt(i1)))
					 * 
					 * alpha.append(ss.charAt(i1));
					 * 
					 * else
					 * 
					 * special.append(ss.charAt(i1));
					 * 
					 * }
					 * 
					 * System.out.println(alpha);
					 * 
					 * System.out.println(num);
					 * 
					 * }
					 */

					emailFolder.close(false);

					emailStore.close();

				}

			}

			else {
				System.out.println("INTO THE ELSEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
				System.out.println(S1);

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

					ArrayList<String> a = new ArrayList<String>();
					ArrayList<String> b = new ArrayList<String>();
					// String specificWord = "BORNFIRE\\d+";

					String specificWord = "BORNFIRE\\d{4}[A-Z]\\d{3}";
					String specificWord1 = "\\d{12}";
					// \\d+ matches one or more digits

					// Sample text (replace with your actual text)
					// String ss = "BORNFIRE1234 is a specific word. BORNFIRE5678 can appear
					// multiple times in the text. BORNFIRE999 is what we're looking for.";

					// Create a pattern for the specific word
					Pattern pattern = Pattern.compile(specificWord);
					Matcher matcher = pattern.matcher(ss);

					int count = 0;
					while (matcher.find()) {
						String lastMatch = matcher.group(0);
						System.out.println("Last match: " + lastMatch);
						a.add(lastMatch);
						System.out.println("-0-0-0-0-0-0-0-0-0-0-" + matcher);
						count++;
					}

					Pattern pattern1 = Pattern.compile(specificWord1);
					Matcher matcher1 = pattern1.matcher(ss);

					while (matcher1.find()) {
						String lastMatch = matcher1.group(0);
						System.out.println("Last match: " + lastMatch);
						b.add(lastMatch);
						System.out.println("-0-0-0-0-0-0-0-0-0-0-" + matcher1);
						// count++;
					}

					if (a.size() == b.size()) {
						int size = a.size();
						for (int i1 = 0; i1 < size; i1++) {
							System.out.println("Value from list1: " + a.get(i1));
							System.out.println("Value from list2: " + b.get(i1));
							CreateAAdd(placementmaintenance, a.get(i1), b.get(i1), DPdate);

						}
					} else {
						System.out.println(
								"Both ArrayLists must have the same size to iterate through them in one loop.");
					}
					System.out.println(
							"The word matching '" + specificWord + "' appears " + count + " times in the text.");

					String IN = ss.substring(ss.indexOf("BORNFIRE"), ss.indexOf("Regards,"));

					System.out.println(IN);

					String IN1 = IN.substring(IN.indexOf("BORNFIRE"), 16);

					System.out.println(IN1);

					String DP = IN.substring(IN.indexOf("INR"));

					String DP1 = DP.substring(7, 19);

					System.out.println(DP1);
					System.out.println("))))))))))))))))" + DP1 + "JJJJJJJJJJJJ" + DPdate);
					// CreateAAdd(placementmaintenance, IN1, DP1, DPdate);

					System.out.println(CreateAAdd(placementmaintenance, IN1, DP1, DPdate));

					emailFolder.close(false);

					emailStore.close();

				}

			}

			// int end=emailFolder.getMessageCount();

			// int start=end - MAX_MESSAGES + 1;

		} catch (NoSuchProviderException e) {

			e.printStackTrace();

		} catch (MessagingException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		} finally {
			try {
				if (emailFolder != null && emailFolder.isOpen()) {
					emailFolder.close(false); // Close the folder
				}
				if (emailStore != null && emailStore.isConnected()) {
					emailStore.close(); // Close the store
				}
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}

		return password;

	}

	private Date CreateAAdd(PlacementMaintenance placementmaintenance, String IN1, String DP1, String DPdate) {
		System.out.println(
				"kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
		PlacementMaintenance up = placementMaintenanceRep.getAlist(IN1);
		System.out.println(placementMaintenanceRep.getAlist(IN1));
		System.out.println(IN1 + "3333333333" + DP1 + "$$$$$$$$$$$$$" + DPdate);
		up.setDp_date(DPdate);

		up.setDp_no(DP1);
		System.out.println("placement savingggggggggggggggggggggggggggggggggggggggggggggg");

		placementMaintenanceRep.save(up);

		// msg = "PlacementMaintenance Added Successfully";

		return up.getDp_date();

		// TODO Auto-generated method stub

	}

	private String CreatePAdd(PlacementMaintenance placementmaintenance, String Effort, String Amount, String GRN,
			String po_id, String GRNDate, String po_id1, String EmpNo) {
		System.out.println("createppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppppadd");
		System.out.println("{{{{{{{{{{{{{{{{{{{{{{{{{" + placementmaintenance.getPo_id());
		Optional<PlacementMaintenance> up = null;
		Optional<PlacementMaintenance> op = null;
		System.out.println(po_id + "------------");
		// String po_id = "6170003683280048531122024"; // or retrieve it dynamically

		// Check if the entity with po_id exists
		if (placementMaintenanceRep.findById(po_id).isPresent()) {
			up = placementMaintenanceRep.findById(po_id);
			System.out.println("checkkkkkkkkkkkkkkkkkkkkkkkkkkk" + up);
		} else {
			BigInteger po_idBigInt = new BigInteger(po_id);
			BigInteger po_id3 = po_idBigInt.subtract(BigInteger.ONE);
			String po_id2 = po_id3.toString();
			System.out.println(po_id2 + "11111111111");
			up = placementMaintenanceRep.findById(po_id2);
		}

		// String po_id1 = "your_dynamic_po_id1_value"; // replace with your actual
		// dynamic value

		// Check if the entity with po_id1 exists
		if (placementMaintenanceRep.findById(po_id1).isPresent()) {
			op = placementMaintenanceRep.findById(po_id1);
		} else {
			BigInteger po_id1BigInt = new BigInteger(po_id1);
			BigInteger po_id3 = po_id1BigInt.subtract(BigInteger.ONE);
			String po_id2 = po_id3.toString();
			System.out.println(po_id2 + "222222222222222222222222222");
			op = placementMaintenanceRep.findById(po_id2);
		}

		if (up.isPresent()) {

			if (up.get().getLocation().equals("CHENNAI")) {

				System.out.println(Effort + GRN + Amount);

				System.out.println(up.get().getLocation());

				double i = Double.valueOf(Amount);

				DecimalFormat f = new DecimalFormat("#,##,##0.00");

				System.out.println(f.format(i));

				up.get().setGrn_efforts(GRN);

				up.get().setGrn_no(Effort);

				up.get().setGrn_amt(f.format(i));

				System.out.println("------------->>>>>>>>>>>>>" + up.get().setGrn_amt(f.format(i)));

				// String amt = Amount;

				// Double GrnAmt = Double.parseDouble(Amount);

				System.out.println(Amount);

				// System.out.println("<<<<<>>>>"+GrnAmt);

				// double Gst_val = GrnAmt*18/100;

				// System.out.println(f.format(Gst_val));

				// int grnint = Double.valueOf(GRN).intValue();

				// BigDecimal totint = new BigDecimal(GrnAmt);

				// System.out.println(totint+"<<<<<>>>>>");

				up.get().setInv_igst("0");

				up.get().setInv_tot_gst("0");

				up.get().setInv_tot_amt(String.valueOf(up.get().setGrn_amt(f.format(i))));

				up.get().setGrn_date(GRNDate);
				System.out.println((up.get().getSp()));
				// up.get().setGrn_no(GRN);
				if (up.get().getSp() == null) {
				} else if ((up.get().getSp()).equals("ASOFT")) {
					String o = up.get().getSp_rate().replace(",", "");

					String p = o.substring(0, o.indexOf("."));
					// Double p1=Double.parseDouble(GRN);
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					// BigDecimal op1=Integer.parseInt(p)*bigDecimalValue;
					System.out.println(op1);
					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);
					up.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("KKKKKKKKKKKKKKK" + f.format(bd).toString());
					BigDecimal percentage = BigDecimal.valueOf(18); // Percentage value
					long longValue = op1.longValue();
					BigDecimal igst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					// int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(igst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// System.out.println(bd);
					// BigDecimal bd1= bd.add(bd);
					BigDecimal de = bde.add(bd);
					up.get().setSp_inv_cgst("0");
					up.get().setSp_inv_sgst("0");
					up.get().setSp_inv_igst(f.format(bde).toString());
					up.get().setSp_inv_tot_gst(f.format(bde).toString());
					up.get().setSp_inv_tot_amt(f.format(de).toString());
				} else if ((up.get().getSp()).equals("MISCOT")) {
					String o = up.get().getSp_rate().replace(",", "");

					String p = o.substring(0, o.indexOf("."));
					// Double p1=Double.parseDouble(GRN);
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					// BigDecimal op1=Integer.parseInt(p)*bigDecimalValue;
					System.out.println(op1);
					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);
					up.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("KKKKKKKKKKKKKKK" + f.format(bd).toString());
					BigDecimal percentage = BigDecimal.valueOf(18); // Percentage value
					long longValue = op1.longValue();
					BigDecimal igst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					// int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(igst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// System.out.println(bd);
					// BigDecimal bd1= bd.add(bd);
					BigDecimal de = bde.add(bd);
					up.get().setSp_inv_cgst("0");
					up.get().setSp_inv_sgst("0");
					up.get().setSp_inv_igst(f.format(bde).toString());
					up.get().setSp_inv_tot_gst(f.format(bde).toString());
					up.get().setSp_inv_tot_amt(f.format(de).toString());
				} else if ((up.get().getSp()).equals("STACKPOS")) {
					System.out.println(up.get().getSp_rate());
					String o = up.get().getSp_rate().replace(",", "");
					String p = o.substring(0, o.indexOf("."));
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					// int op1=Integer.parseInt(p)*p1;
					System.out.println(op1);
					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);

					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);
					up.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("JJJJJJJJJJJJ" + f.format(op1).toString());
					BigDecimal percentage = BigDecimal.valueOf(9); // Percentage value
					long longValue = op1.longValue();
					BigDecimal igst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					// int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(igst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// System.out.println(bd);
					BigDecimal bd1 = bde.add(bde);
					BigDecimal de = bd1.add(bd);
					up.get().setSp_inv_cgst(f.format(bde).toString());
					up.get().setSp_inv_sgst(f.format(bde).toString());
					up.get().setSp_inv_tot_gst(f.format(bd1).toString());
					up.get().setSp_inv_tot_amt(f.format(de).toString());
				} else if ((up.get().getSp()).equals("WHITESTONE")) {
					String o = up.get().getFixed_amt().replace(",", "");
					String p = o.substring(0, o.indexOf("."));
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					System.out.println(op1);
					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);

					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);

					up.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("LLLLLLLLLLLLLLLLL" + f.format(bd).toString());
					BigDecimal percentage = BigDecimal.valueOf(9); // Percentage value
					long longValue = op1.longValue();
					BigDecimal cgst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					// int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(cgst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					System.out.println(bde);
					BigDecimal bd1 = bde.add(bde);
					BigDecimal de = bd1.add(bd);
					up.get().setSp_inv_cgst(f.format(bde).toString());
					up.get().setSp_inv_sgst(f.format(bde).toString());
					up.get().setSp_inv_tot_gst(f.format(bd1).toString());
					up.get().setSp_inv_tot_amt(f.format(de).toString());
				}

				placementMaintenanceRep.save(up.get());

			}

			else {

				System.out.println(Effort + GRN + Amount);

				System.out.println(up.get().getLocation());

				double i = Double.valueOf(Amount);

				DecimalFormat f = new DecimalFormat("#,##,##0.00");

				System.out.println(f.format(i));

				up.get().setGrn_efforts(GRN);

				up.get().setGrn_no(Effort);

				up.get().setGrn_amt(f.format(i));

				// String amt = Amount;

				Double GrnAmt = Double.parseDouble(Amount);

				System.out.println(Amount);

				System.out.println("<<<<<>>>>" + GrnAmt);

				double Gst_val = GrnAmt * 18 / 100;

				System.out.println(f.format(Gst_val));

				int grnint = Double.valueOf(GRN).intValue();

				BigDecimal totint = new BigDecimal(GrnAmt + Math.round(Gst_val));

				System.out.println(totint + "<<<<<>>>>>");

				up.get().setInv_igst(String.valueOf(f.format(Math.round(Gst_val))));
				System.out.println("Updated IGST value: " + up.get().getInv_igst() + ", Gst_val: " + Gst_val);

				up.get().setInv_tot_gst(String.valueOf(f.format(Math.round(Gst_val))));

				up.get().setInv_tot_amt(String.valueOf(f.format(totint)));

				// up.get().setGrn_date(GRNDate);

				System.out.println(up.get().getSp() + "||||||||||||||");

				// up.get().setGrn_no(GRN);

				if (up.get().getSp() == null) {
				} else if ((up.get().getSp()).equals("ASOFT")) {
					String o = up.get().getSp_rate().replace(",", "");

					String p = o.substring(0, o.indexOf("."));
					// Double p1=Double.parseDouble(GRN);
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					// BigDecimal op1=Integer.parseInt(p)*bigDecimalValue;
					System.out.println(op1);
					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);
					up.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("KKKKKKKKKKKKKKK" + f.format(bd).toString());
					BigDecimal percentage = BigDecimal.valueOf(18); // Percentage value
					long longValue = op1.longValue();
					BigDecimal igst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					// int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(igst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// System.out.println(bd);
					// BigDecimal bd1= bd.add(bd);
					BigDecimal de = bde.add(bd);
					up.get().setSp_inv_cgst("0");
					up.get().setSp_inv_sgst("0");
					up.get().setSp_inv_igst(f.format(bde).toString());
					up.get().setSp_inv_tot_gst(f.format(bde).toString());
					up.get().setSp_inv_tot_amt(f.format(de).toString());
				} else if ((up.get().getSp()).equals("MISCOT")) {
					String o = up.get().getSp_rate().replace(",", "");

					String p = o.substring(0, o.indexOf("."));
					// Double p1=Double.parseDouble(GRN);
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					// BigDecimal op1=Integer.parseInt(p)*bigDecimalValue;
					System.out.println(op1);
					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);
					up.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("KKKKKKKKKKKKKKK" + f.format(bd).toString());
					BigDecimal percentage = BigDecimal.valueOf(18); // Percentage value
					long longValue = op1.longValue();
					BigDecimal igst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					// int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(igst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// System.out.println(bd);
					// BigDecimal bd1= bd.add(bd);
					BigDecimal de = bde.add(bd);
					up.get().setSp_inv_cgst("0");
					up.get().setSp_inv_sgst("0");
					up.get().setSp_inv_igst(f.format(bde).toString());
					up.get().setSp_inv_tot_gst(f.format(bde).toString());
					up.get().setSp_inv_tot_amt(f.format(de).toString());
				} else if ((up.get().getSp()).equals("STACKPOS")) {
					System.out.println(up.get().getSp_rate());
					String o = up.get().getSp_rate().replace(",", "");
					String p = o.substring(0, o.indexOf("."));
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					// int op1=Integer.parseInt(p)*p1;
					System.out.println(op1);
					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);

					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);
					up.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("JJJJJJJJJJJJ" + f.format(op1).toString());
					BigDecimal percentage = BigDecimal.valueOf(9); // Percentage value
					long longValue = op1.longValue();
					BigDecimal igst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					// int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(igst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// System.out.println(bd);
					BigDecimal bd1 = bde.add(bde);
					BigDecimal de = bd1.add(bd);
					up.get().setSp_inv_cgst(f.format(bde).toString());
					up.get().setSp_inv_sgst(f.format(bde).toString());
					up.get().setSp_inv_tot_gst(f.format(bd1).toString());
					up.get().setSp_inv_tot_amt(f.format(de).toString());
				} else if ((up.get().getSp()).equals("WHITESTONE")) {
					String o = up.get().getSp_rate().replace(",", "");
					String p = o.substring(0, o.indexOf("."));
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					System.out.println(op1);
					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);

					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);

					up.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("LLLLLLLLLLLLLLLLL" + f.format(bd).toString());
					BigDecimal percentage = BigDecimal.valueOf(9); // Percentage value
					long longValue = op1.longValue();
					BigDecimal cgst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					// int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(cgst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					System.out.println(bde);
					BigDecimal bd1 = bde.add(bde);
					BigDecimal de = bd1.add(bd);
					up.get().setSp_inv_cgst(f.format(bde).toString());
					up.get().setSp_inv_sgst(f.format(bde).toString());
					up.get().setSp_inv_tot_gst(f.format(bd1).toString());
					up.get().setSp_inv_tot_amt(f.format(de).toString());
					System.out.println(up.get() + "jjjjjjjjjjjjjj");
					System.out.println("SP_INV_CGST: " + up.get().getSp_inv_cgst());
					System.out.println("SP_INV_SGST: " + up.get().getSp_inv_sgst());
					System.out.println("SP_INV_TOT_GST: " + up.get().getSp_inv_tot_gst());
					System.out.println("SP_INV_TOT_AMT: " + up.get().getSp_inv_tot_amt());

				}

				try {
					System.out.println("Entity to be saved: " + up.get());
					placementMaintenanceRep.save(up.get());
				} catch (Exception e) {
					System.err.println("Error while saving entity: " + e.getMessage());
					e.printStackTrace();
				}

			}

		} else if (op.isPresent()) {

			if (op.get().getLocation().equals("CHENNAI")) {

				System.out.println("******************" + Effort + GRN + Amount);

				System.out.println(up.get().getLocation());

				double i = Double.valueOf(Amount);

				DecimalFormat f = new DecimalFormat("#,##,##0.00");

				System.out.println(f.format(i));

				// op.get().setPo_id(PO+EmpNo+DeliveryDate);

				op.get().setEmp_id(EmpNo);

				op.get().setGrn_efforts(GRN);

				op.get().setGrn_no(Effort);

				op.get().setGrn_amt(f.format(i));

				System.out.println("------------->>>>>>>>>>>>>" + op.get().setGrn_amt(f.format(i)));

				// String amt = Amount;

				// Double GrnAmt = Double.parseDouble(Amount);

				System.out.println(Amount);

				// System.out.println("<<<<<>>>>"+GrnAmt);

				// double Gst_val = GrnAmt*18/100;

				// System.out.println(f.format(Gst_val));

				// int grnint = Double.valueOf(GRN).intValue();

				// BigDecimal totint = new BigDecimal(GrnAmt);

				// System.out.println(totint+"<<<<<>>>>>");

				op.get().setInv_igst("0");

				op.get().setInv_tot_gst("0");

				op.get().setInv_tot_amt(String.valueOf(op.get().setGrn_amt(f.format(i))));

				op.get().setGrn_date(GRNDate);

				// up.get().setGrn_no(GRN);

				if (op.get().getSp() == null) {
				} else if ((op.get().getSp()).equals("ASOFT")) {
					String o = up.get().getSp_rate().replace(",", "");

					String p = o.substring(0, o.indexOf("."));
					// Double p1=Double.parseDouble(GRN);
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					// BigDecimal op1=Integer.parseInt(p)*bigDecimalValue;
					System.out.println(op1);
					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);
					up.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("KKKKKKKKKKKKKKK" + f.format(bd).toString());
					BigDecimal percentage = BigDecimal.valueOf(18); // Percentage value
					long longValue = op1.longValue();
					BigDecimal igst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					// int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(igst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// System.out.println(bd);
					// BigDecimal bd1= bd.add(bd);
					BigDecimal de = bde.add(bd);
					up.get().setSp_inv_cgst("0");
					up.get().setSp_inv_sgst("0");
					up.get().setSp_inv_igst(f.format(bde).toString());
					up.get().setSp_inv_tot_gst(f.format(bde).toString());
					up.get().setSp_inv_tot_amt(f.format(de).toString());
				} else if ((op.get().getSp()).equals("MISCOT")) {
					String o = up.get().getSp_rate().replace(",", "");

					String p = o.substring(0, o.indexOf("."));
					// Double p1=Double.parseDouble(GRN);
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					// BigDecimal op1=Integer.parseInt(p)*bigDecimalValue;
					System.out.println(op1);
					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);
					up.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("KKKKKKKKKKKKKKK" + f.format(bd).toString());
					BigDecimal percentage = BigDecimal.valueOf(18); // Percentage value
					long longValue = op1.longValue();
					BigDecimal igst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					// int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(igst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// System.out.println(bd);
					// BigDecimal bd1= bd.add(bd);
					BigDecimal de = bde.add(bd);
					up.get().setSp_inv_cgst("0");
					up.get().setSp_inv_sgst("0");
					up.get().setSp_inv_igst(f.format(bde).toString());
					up.get().setSp_inv_tot_gst(f.format(bde).toString());
					up.get().setSp_inv_tot_amt(f.format(de).toString());
				} else if ((op.get().getSp()).equals("STACKPOS")) {
					System.out.println(up.get().getSp_rate());
					String o = up.get().getSp_rate().replace(",", "");
					String p = o.substring(0, o.indexOf("."));
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					// int op1=Integer.parseInt(p)*p1;
					System.out.println(op1);
					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);

					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);
					up.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("JJJJJJJJJJJJ" + f.format(op1).toString());
					BigDecimal percentage = BigDecimal.valueOf(9); // Percentage value
					long longValue = op1.longValue();
					BigDecimal igst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					// int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(igst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// System.out.println(bd);
					BigDecimal bd1 = bde.add(bde);
					BigDecimal de = bd1.add(bd);
					up.get().setSp_inv_cgst(f.format(bde).toString());
					up.get().setSp_inv_sgst(f.format(bde).toString());
					up.get().setSp_inv_tot_gst(f.format(bd1).toString());
					up.get().setSp_inv_tot_amt(f.format(de).toString());
				} else if ((op.get().getSp()).equals("WHITESTONE")) {
					String o = up.get().getFixed_amt().replace(",", "");
					String p = o.substring(0, o.indexOf("."));
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					System.out.println(op1);
					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);

					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);

					up.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("LLLLLLLLLLLLLLLLL" + f.format(bd).toString());
					BigDecimal percentage = BigDecimal.valueOf(9); // Percentage value
					long longValue = op1.longValue();
					BigDecimal cgst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					// int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(cgst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					System.out.println(bde);
					BigDecimal bd1 = bde.add(bde);
					BigDecimal de = bd1.add(bd);
					up.get().setSp_inv_cgst(f.format(bde).toString());
					up.get().setSp_inv_sgst(f.format(bde).toString());
					up.get().setSp_inv_tot_gst(f.format(bd1).toString());
					up.get().setSp_inv_tot_amt(f.format(de).toString());

				}

				placementMaintenanceRep.save(up.get());

			}

			else {

				System.out.println("**************" + Effort + GRN + Amount);

				System.out.println("Locationnnnnnnnnn" + op.get().getLocation());

				double i = Double.valueOf(Amount);

				DecimalFormat f = new DecimalFormat("#,##,##0.00");

				System.out.println(f.format(i));

				op.get().setGrn_efforts(GRN);

				op.get().setGrn_no(Effort);
				op.get().setEmp_id(EmpNo);
				op.get().setGrn_amt(f.format(i));

				// String amt = Amount;

				Double GrnAmt = Double.parseDouble(Amount);

				System.out.println(Amount);

				System.out.println("<<<<<>>>>" + GrnAmt);

				double Gst_val = GrnAmt * 18 / 100;

				System.out.println(f.format(Gst_val));

				int grnint = Double.valueOf(GRN).intValue();

				BigDecimal totint = new BigDecimal(GrnAmt + Math.round(Gst_val));

				System.out.println(totint + "<<<<<>>>>>");

				op.get().setInv_igst(String.valueOf(f.format(Math.round(Gst_val))));

				op.get().setInv_tot_gst(String.valueOf(f.format(Math.round(Gst_val))));

				op.get().setInv_tot_amt(String.valueOf(f.format(totint)));

				op.get().setGrn_date(GRNDate);

				System.out.println("||||||||||||||||" + op.get().getSp());
				System.out.println("INV_IGST: " + op.get().getInv_igst());
				System.out.println("INV_TOT_GST: " + op.get().getInv_tot_gst());
				System.out.println("INV_TOT_AMT: " + op.get().getInv_tot_amt());
				System.out.println("GRN_DATE: " + op.get().getGrn_date());
				// up.get().setGrn_no(GRN);
				if (op.get().getSp() == null) {

				} else if ((op.get().getSp()).equals("ASOFT")) {
					String o = op.get().getSp_rate().replace(",", "");

					String p = o.substring(0, o.indexOf("."));
					// Double p1=Double.parseDouble(GRN);
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					// BigDecimal op1=Integer.parseInt(p)*bigDecimalValue;
					System.out.println(op1);
					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);
					op.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("KKKKKKKKKKKKKKK" + f.format(bd).toString());
					BigDecimal percentage = BigDecimal.valueOf(18); // Percentage value
					long longValue = op1.longValue();
					BigDecimal igst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					// int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(igst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// System.out.println(bd);
					// BigDecimal bd1= bd.add(bd);
					BigDecimal de = bde.add(bd);
					op.get().setSp_inv_cgst("0");
					op.get().setSp_inv_sgst("0");
					op.get().setSp_inv_igst(f.format(bde).toString());
					op.get().setSp_inv_tot_gst(f.format(bde).toString());
					op.get().setSp_inv_tot_amt(f.format(de).toString());
				} else if ((op.get().getSp()).equals("MISCOT")) {
					String o = op.get().getSp_rate().replace(",", "");

					String p = o.substring(0, o.indexOf("."));
					// Double p1=Double.parseDouble(GRN);
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					// BigDecimal op1=Integer.parseInt(p)*bigDecimalValue;
					System.out.println(op1);
					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);
					op.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("KKKKKKKKKKKKKKK" + f.format(bd).toString());
					BigDecimal percentage = BigDecimal.valueOf(18); // Percentage value
					long longValue = op1.longValue();
					BigDecimal igst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					// int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(igst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// System.out.println(bd);
					// BigDecimal bd1= bd.add(bd);
					BigDecimal de = bde.add(bd);
					op.get().setSp_inv_cgst("0");
					op.get().setSp_inv_sgst("0");
					op.get().setSp_inv_igst(f.format(bde).toString());
					op.get().setSp_inv_tot_gst(f.format(bde).toString());
					op.get().setSp_inv_tot_amt(f.format(de).toString());
				} else if ((op.get().getSp()).equals("STACKPOS")) {
					System.out.println(op.get().getSp_rate());
					String o = op.get().getSp_rate().replace(",", "");
					String p = o.substring(0, o.indexOf("."));
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					// int op1=Integer.parseInt(p)*p1;
					System.out.println(op1);
					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);

					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);
					op.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("JJJJJJJJJJJJ" + f.format(op1).toString());
					BigDecimal percentage = BigDecimal.valueOf(9); // Percentage value
					long longValue = op1.longValue();
					BigDecimal igst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					// int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(igst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					// System.out.println(bd);
					BigDecimal bd1 = bde.add(bde);
					BigDecimal de = bd1.add(bd);
					op.get().setSp_inv_cgst(f.format(bde).toString());
					op.get().setSp_inv_sgst(f.format(bde).toString());
					op.get().setSp_inv_tot_gst(f.format(bd1).toString());
					op.get().setSp_inv_tot_amt(f.format(de).toString());
				} else if ((op.get().getSp()).equals("WHITESTONE")) {
					String o = null;
					String p = null;
					if (op.get().getFixed_amt() != null) {
						o = op.get().getFixed_amt().replace(",", "");
						p = o.substring(0, o.indexOf("."));
					} else {
						o = op.get().getSp_rate().replace(",", "");

						p = o.substring(0, o.indexOf("."));
					}
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					System.out.println(op1);
					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);
					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);

					op.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("LLLLLLLLLLLLLLLLL" + f.format(bd).toString());
					BigDecimal percentage = BigDecimal.valueOf(9); // Percentage value
					long longValue = op1.longValue();
					BigDecimal cgst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					// int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(cgst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					System.out.println(bde);
					BigDecimal bd1 = bde.add(bde);
					BigDecimal de = bd1.add(bd);
					op.get().setSp_inv_cgst(f.format(bde).toString());
					op.get().setSp_inv_sgst(f.format(bde).toString());
					op.get().setSp_inv_tot_gst(f.format(bd1).toString());
					op.get().setSp_inv_tot_amt(f.format(de).toString());
				} else if ((op.get().getSp()).equals("OMSYS")) {
					String o = null;
					String p = null;
					if (op.get().getFixed_amt() != null) {
						o = op.get().getFixed_amt().replace(",", "");
						p = o.substring(0, o.indexOf("."));
					} else {
						o = op.get().getSp_rate().replace(",", "");

						p = o.substring(0, o.indexOf("."));
					}
					BigDecimal bigDecimalValue = new BigDecimal(GRN);
					BigDecimal op1 = bigDecimalValue.multiply(BigDecimal.valueOf(Integer.parseInt(p)));
					System.out.println(op1);
					// int o=(Integer.parseInt(up.get().getFixed_amt()))*Integer.parseInt(GRN);
					// System.out.println(o);
					int decimalPlaces = 0;
					BigDecimal bd = new BigDecimal(op1.toString());
					bd = bd.setScale(decimalPlaces, RoundingMode.HALF_UP);

					op.get().setSp_inv_amt(f.format(bd).toString());
					System.out.println("LLLLLLLLLLLLLLLLL" + f.format(bd).toString());
					BigDecimal percentage = BigDecimal.valueOf(9); // Percentage value
					long longValue = op1.longValue();
					BigDecimal cgst = BigDecimal.valueOf(longValue)
							.multiply(percentage.divide(BigDecimal.valueOf(100)));
					// int decimalPlaces = 0;
					BigDecimal bde = new BigDecimal(cgst.toString());
					bde = bde.setScale(decimalPlaces, RoundingMode.HALF_UP);
					System.out.println(bde);
					BigDecimal bd1 = bde.add(bde);
					BigDecimal de = bd1.add(bd);
					op.get().setSp_inv_cgst(f.format(bde).toString());
					op.get().setSp_inv_sgst(f.format(bde).toString());
					op.get().setSp_inv_tot_gst(f.format(bd1).toString());
					op.get().setSp_inv_tot_amt(f.format(de).toString());
				}

				placementMaintenanceRep.save(op.get());

			}

		} else {
		}
		Optional<InvoiceMaster> up1 = null;
		Optional<InvoiceMaster> op1 = null;

		// String po_id = "6170003683280048531122024"; // Replace with your dynamic
		// value
		// String po_id1 = "your_dynamic_po_id1_value"; // Replace with your dynamic
		// value

		// Check if the entity with po_id exists
		if (invoiceMasterRep.findById(po_id).isPresent()) {
			up1 = invoiceMasterRep.findById(po_id);
		} else {
			BigInteger po_idBigInt = new BigInteger(po_id);
			BigInteger po_id3 = po_idBigInt.subtract(BigInteger.ONE);
			String po_id2 = po_id3.toString();
			System.out.println(po_id2 + "11111111111");
			up1 = invoiceMasterRep.findById(po_id2);
		}

		// Check if the entity with po_id1 exists
		if (invoiceMasterRep.findById(po_id1).isPresent()) {
			op1 = invoiceMasterRep.findById(po_id1);
		} else {
			BigInteger po_id1BigInt = new BigInteger(po_id1);
			BigInteger po_id3 = po_id1BigInt.subtract(BigInteger.ONE);
			String po_id2 = po_id3.toString();
			System.out.println(po_id2 + "222222222222222222222222222");
			op1 = invoiceMasterRep.findById(po_id2);
		}

		// up1 = invoiceMasterRep.findById(po_id);

		if (up1.isPresent()) {

			if (up1.get().getLocation().equals("CHENNAI")) {

				System.out.println(Effort + GRN + Amount);

				System.out.println(up1.get().getLocation());

				double i = Double.valueOf(Amount);

				DecimalFormat f = new DecimalFormat("#,##,##0.00");

				System.out.println(f.format(i));

				up1.get().setGrn_efforts(GRN);

				up1.get().setGrn_no(Effort);

				up1.get().setGrn_amt(f.format(i));

				System.out.println("------------->>>>>>>>>>>>>" + up.get().setGrn_amt(f.format(i)));

				// String amt = Amount;

				// Double GrnAmt = Double.parseDouble(Amount);

				System.out.println(Amount);

				// System.out.println("<<<<<>>>>"+GrnAmt);

				// double Gst_val = GrnAmt*18/100;

				// System.out.println(f.format(Gst_val));

				// int grnint = Double.valueOf(GRN).intValue();

				// BigDecimal totint = new BigDecimal(GrnAmt);

				// System.out.println(totint+"<<<<<>>>>>");

				up1.get().setInv_igst("0");

				up1.get().setInv_tot_gst("0");

				up1.get().setInv_tot_amt(String.valueOf(up.get().setGrn_amt(f.format(i))));

				NumToWords w = new NumToWords();

				// String num=String.valueOf(totint);

				// int ii=Integer.parseInt(num);

				// String j=w.convert((ii));

				// System.out.println(j);

				String bob = Double.toString(Double.valueOf(Amount));

				String[] convert = bob.split("\\.");

				System.out.println("===================>>>>>>>>>>" + bob);

				int a = Integer.parseInt(convert[0]);

				System.out.println(w.convert(a));

				int b = Integer.parseInt(convert[1] + 0);

				if (b == 0) {

					System.out.println();

					System.out.println(w.convert(a)); // 13454

					// System.out.println(EnglishNumberToWords.convert(b)); // 92345

					// String Rupee=EnglishNumberToWords.convert(totint1[]);

					// System.out.println("*** " + EnglishNumberToWords.convert(totint));

					up1.get().setTotal_amt(("(Rupees " + w.convert(a) + " Only)"));

				} else {

					System.out.println(w.convert(a)); // 13454

					System.out.println(w.convert(b)); // 92345

					// String Rupee=EnglishNumberToWords.convert(totint1[]);

					// System.out.println("*** " + EnglishNumberToWords.convert(totint));

					up1.get().setTotal_amt(("(Rupees " + w.convert(a) + " and " + w.convert(b) + " Paise Only)"));

				}

				up1.get().setEnclosures("LUT (ARN: AD330323059503E)");

				up1.get().setGrn_date(GRNDate);

				// up.get().setGrn_no(GRN);

				invoiceMasterRep.save(up1.get());

			} else {

				System.out.println(Amount);

				double i = Double.valueOf(Amount);

				System.out.println(i);

				DecimalFormat f = new DecimalFormat("#,##,##0.00");

				up1.get().setGrn_amt(f.format(i));

				up1.get().setGrn_efforts(GRN);

				up1.get().setGrn_no(Effort);

				up1.get().setGrn_date(GRNDate);

				double GrnAmt = Double.valueOf(Amount);

				double Gst_val = GrnAmt * 18 / 100;

				// String S=Integer.toString(Gst_val);

				// String[] parts = S.split("\\.");

				// System.out.println(parts[0]);

				System.out.println(Math.round(Gst_val));

				int grnint = Double.valueOf(GRN).intValue();

				double totint = GrnAmt + Math.round(Gst_val);

				System.out.println(totint);

				up1.get().setInv_igst(String.valueOf(f.format(Math.round(Gst_val))));

				up1.get().setInv_tot_gst(String.valueOf(f.format(Math.round(Gst_val))));

				up1.get().setInv_tot_amt(String.valueOf(f.format(totint)));

				NumToWords w = new NumToWords();

				// String num=String.valueOf(totint);

				// int ii=Integer.parseInt(num);

				// String j=w.convert((ii));

				// System.out.println(j);

				String bob = Double.toString(totint);

				String[] convert = bob.split("\\.");

				int a = Integer.parseInt(convert[0]);

				System.out.println(w.convert(a));

				int b = Integer.parseInt(convert[1] + 0);

				if (b == 0) {

					System.out.println();

					System.out.println(w.convert(a)); // 13454

					// System.out.println(EnglishNumberToWords.convert(b)); // 92345

					// String Rupee=EnglishNumberToWords.convert(totint1[]);

					// System.out.println("*** " + EnglishNumberToWords.convert(totint));

					up1.get().setTotal_amt(("(Rupees " + w.convert(a) + " Only)"));

				} else {

					System.out.println(w.convert(a)); // 13454

					System.out.println(w.convert(b)); // 92345

					// String Rupee=EnglishNumberToWords.convert(totint1[]);

					// System.out.println("*** " + EnglishNumberToWords.convert(totint));

					up1.get().setTotal_amt(("(Rupees " + w.convert(a) + " and " + w.convert(b) + " Paise Only)"));

				}

				up1.get().setGrn_date(GRNDate);

				invoiceMasterRep.save(up1.get());

			}

		}

		else if (op1.isPresent()) {

			if (op1.get().getLocation().equals("CHENNAI")) {

				System.out.println(Effort + GRN + Amount);

				System.out.println(up1.get().getLocation());

				double i = Double.valueOf(Amount);

				DecimalFormat f = new DecimalFormat("#,##,##0.00");

				System.out.println(f.format(i));

				// op1.get().setPo_id(PO+EmpNo+DeliveryDate);

				op1.get().setEmp_id(EmpNo);

				op1.get().setGrn_efforts(GRN);

				op1.get().setGrn_no(Effort);

				op1.get().setGrn_amt(f.format(i));

				System.out.println("------------->>>>>>>>>>>>>" + op.get().setGrn_amt(f.format(i)));

				// String amt = Amount;

				// Double GrnAmt = Double.parseDouble(Amount);

				System.out.println(Amount);

				// System.out.println("<<<<<>>>>"+GrnAmt);

				// double Gst_val = GrnAmt*18/100;

				// System.out.println(f.format(Gst_val));

				// int grnint = Double.valueOf(GRN).intValue();

				// BigDecimal totint = new BigDecimal(GrnAmt);

				// System.out.println(totint+"<<<<<>>>>>");

				op1.get().setInv_igst("0");

				op1.get().setInv_tot_gst("0");

				op1.get().setInv_tot_amt(String.valueOf(op.get().setGrn_amt(f.format(i))));

				NumToWords w = new NumToWords();

				// String num=String.valueOf(totint);

				// int ii=Integer.parseInt(num);

				// String j=w.convert((ii));

				// System.out.println(j);

				String bob = Double.toString(Double.valueOf(Amount));

				String[] convert = bob.split("\\.");

				System.out.println("===================>>>>>>>>>>" + bob);

				int a = Integer.parseInt(convert[0]);

				System.out.println(w.convert(a));

				int b = Integer.parseInt(convert[1] + 0);

				if (b == 0) {

					System.out.println();

					System.out.println(w.convert(a)); // 13454

					// System.out.println(EnglishNumberToWords.convert(b)); // 92345

					// String Rupee=EnglishNumberToWords.convert(totint1[]);

					// System.out.println("*** " + EnglishNumberToWords.convert(totint));

					up1.get().setTotal_amt(("(Rupees " + w.convert(a) + " Only)"));

				} else {

					System.out.println(w.convert(a)); // 13454

					System.out.println(w.convert(b)); // 92345

					// String Rupee=EnglishNumberToWords.convert(totint1[]);

					// System.out.println("*** " + EnglishNumberToWords.convert(totint));

					up1.get().setTotal_amt(("(Rupees " + w.convert(a) + " and " + w.convert(b) + " Paise Only)"));

				}

				op1.get().setEnclosures("LUT (ARN: AD330323059503E)");

				op1.get().setGrn_date(GRNDate);

				// up.get().setGrn_no(GRN);

				invoiceMasterRep.save(op1.get());

			} else {

				System.out.println(Amount);

				double i = Double.valueOf(Amount);

				System.out.println(i);

				DecimalFormat f = new DecimalFormat("#,##,##0.00");

				op1.get().setGrn_amt(f.format(i));

				op1.get().setGrn_efforts(GRN);

				op1.get().setGrn_no(Effort);

				op1.get().setGrn_date(GRNDate);

				op1.get().setEmp_id(EmpNo);

				op1.get().setPo_id(po_id1);

				double GrnAmt = Double.valueOf(Amount);

				double Gst_val = GrnAmt * 18 / 100;

				// String S=Integer.toString(Gst_val);

				// String[] parts = S.split("\\.");

				// System.out.println(parts[0]);

				System.out.println(Math.round(Gst_val));

				int grnint = Double.valueOf(GRN).intValue();

				double totint = GrnAmt + Math.round(Gst_val);

				System.out.println(totint);

				op1.get().setInv_igst(String.valueOf(f.format(Math.round(Gst_val))));

				op1.get().setInv_tot_gst(String.valueOf(f.format(Math.round(Gst_val))));

				op1.get().setInv_tot_amt(String.valueOf(f.format(totint)));

				NumToWords w = new NumToWords();

				// String num=String.valueOf(totint);

				// int ii=Integer.parseInt(num);

				// String j=w.convert((ii));

				// System.out.println(j);

				String bob = Double.toString(totint);

				String[] convert = bob.split("\\.");

				int a = Integer.parseInt(convert[0]);

				System.out.println(w.convert(a));

				int b = Integer.parseInt(convert[1] + 0);

				if (b == 0) {

					System.out.println();

					System.out.println(w.convert(a)); // 13454

					// System.out.println(EnglishNumberToWords.convert(b)); // 92345

					// String Rupee=EnglishNumberToWords.convert(totint1[]);

					// System.out.println("*** " + EnglishNumberToWords.convert(totint));

					op1.get().setTotal_amt(("(Rupees " + w.convert(a) + " Only)"));

				} else {

					System.out.println(w.convert(a)); // 13454

					System.out.println(w.convert(b)); // 92345

					// String Rupee=EnglishNumberToWords.convert(totint1[]);

					// System.out.println("*** " + EnglishNumberToWords.convert(totint));

					op1.get().setTotal_amt(("(Rupees " + w.convert(a) + " and " + w.convert(b) + " Paise Only)"));

				}

				op1.get().setGrn_date(GRNDate);

				invoiceMasterRep.save(op1.get());

			}

		}

		// msg = "PlacementMaintenance Added Successfully";

		return null;

		// TODO Auto-generated method stub

	}

	private BigDecimal BigDecimal(int op1) {
		// TODO Auto-generated method stub
		return null;
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