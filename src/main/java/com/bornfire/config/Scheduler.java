package com.bornfire.config;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.bornfire.entities.PlacementMaintenance;
import com.bornfire.entities.PlacementMaintenanceRep;
import com.bornfire.entities.PlacementMaster;
import com.bornfire.services.AckReciever;
import com.bornfire.services.LogService;
import com.bornfire.services.ReceivingMailNew;
import com.bornfire.services.RecievingMail;
import com.bornfire.services.RecievingMail2;
import com.bornfire.services.SchedulerToggleService;
import com.bornfire.services.SendingCronMails;
import com.bornfire.services.Spsendingmail;
import com.bornfire.services.sendingmail;

@Component
public class Scheduler {
	@Autowired
	RecievingMail recievingMail;

	@Autowired
	AckReciever ackReciever;

	@Autowired
	RecievingMail2 recievingMail2;

	@Autowired
	sendingmail sendingmails;

	@Autowired
	Spsendingmail spsendingmail;

	@Autowired
	SendingCronMails sendingCronMails;
	
	@Autowired
	LogService logService;
	
	@Autowired
	private SchedulerToggleService schedulerToggleService;

	//@Scheduled(fixedRate = 5000)
	public String getmail2344() throws SQLException, ParseException {
		
		PlacementMaintenance placementmaintenance = new PlacementMaintenance();
		String user = "grn@bornfire.co.in";
		String password = "Bornfire#123";
		String storeType = "pop3";
		String pop3Host = "sg2plzcpnl491716.prod.sin2.secureserver.net";

		try {
			// You need to implement the receiveEmail method in the recievingMail class
			String result = recievingMail.receiveEmail(pop3Host, storeType, user, password, placementmaintenance);
			return result;
		} catch (Exception e) {
			// Handle exceptions appropriately
			e.printStackTrace();
			return "Error: " + e.getMessage();
		}
	}
	
	@Scheduled(fixedRate = 5000)
	public void getmailSheduler() throws SQLException, ParseException {
	    if (!schedulerToggleService.isSchedulerEnabled()) {
	    	logService.add("Scheduler is OFF. Skipping execution.");
	        return; // Skip execution when disabled
	    }

	    PlacementMaintenance placementmaintenance = new PlacementMaintenance();
	    String user = "grn@bornfire.co.in";
	    String password = "Bornfire#123";
	    String storeType = "pop3";
	    String pop3Host = "sg2plzcpnl491716.prod.sin2.secureserver.net";

	    try {
	    	logService.add("-------------Scheduler Starts Running-------------");
	        String result = recievingMail.receiveEmail(pop3Host, storeType, user, password, placementmaintenance);
	        logService.add("Scheduler End successfully");
	    } catch (Exception e) {
	    	logService.add("Error in scheduler: " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	/*
	 * @Autowired ReceivingMailNew receive;
	 * 
	 * @Autowired PlacementMaintenanceRep placementMaintenanceRep;
	 * //@Scheduled(fixedRate = 5000) public String GetGRNMail() throws
	 * SQLException, ParseException { // Call receiveEmail to get the extracted
	 * details List<Map<String, String>> emailDetailsList = receive.receiveEmail();
	 * 
	 * // Process each email's details for (Map<String, String> details :
	 * emailDetailsList) { // Create and populate the PlacementMaster entity
	 * PlacementMaintenance placementmaintenance = new PlacementMaintenance();
	 * 
	 * 
	 * 
	 * placementmaintenance.setGrn_no(details.get("GRN No"));
	 * placementmaintenance.setPo_no(details.get("PO No"));
	 * placementmaintenance.setEmp_name(details.get("Employee Name"));
	 * placementmaintenance.setEmp_id(details.get("Employee ID"));
	 * placementmaintenance.setGrn_efforts(details.get("Effort"));
	 * placementmaintenance.setGrn_amt(details.get("Amount"));
	 * placementmaintenance.setPo_id("123456789");
	 * 
	 * 
	 * 
	 * 
	 * // Save the PlacementMaster entity to the database
	 * placementMaintenanceRep.save(placementmaintenance);
	 * System.out.println("PlacementMaster saved successfully!"); }
	 * 
	 * return "Emails processed and PlacementMaster records saved."; }
	 */
	
	
	
	@Autowired 
	ReceivingMailNew receive;
	//@Scheduled(fixedRate = 5000)
	public String GetGRNMail() throws SQLException, ParseException {
			PlacementMaintenance placementmaintenance = new PlacementMaintenance();
			String user = "grn@bornfire.co.in";
			String password = "Bornfire#123";
			String storeType = "pop3";
			String pop3Host = "sg2plzcpnl491716.prod.sin2.secureserver.net";

			try {
				// You need to implement the receiveEmail method in the recievingMail class
				String result = receive.receiveEmail(pop3Host, storeType, user, password, placementmaintenance);
				return result;
			} catch (Exception e) {
				// Handle exceptions appropriately
				e.printStackTrace();
				return "Error: " + e.getMessage();
			}
		}
	//@Scheduled(fixedRate = 10000)
	public String getmail234() throws SQLException, ParseException {
		PlacementMaintenance placementmaintenance = new PlacementMaintenance();
		System.out.println("starting of the schduler");
		String user = "acknowledgement@bornfire.co.in";
		String password = "Bornfire#123";
		String storeType = "pop3";
		String pop3Host = "sg2plzcpnl491716.prod.sin2.secureserver.net";
		String start3 = "10";
		try {
			return ackReciever.receiveEmail(pop3Host, storeType, user, password, placementmaintenance);
		} catch (Exception e) {
			e.printStackTrace();
			return "Error receiving emails: " + e.getMessage();
		}
	}

	// @Scheduled(cron = "00 00 00 * * ?")
	public String getannniversary() throws SQLException, ParseException {
		String user = "hr@bornfire.in";
		String password = "Managerbfi@123";
		String storeType = "pop3";
		String pop3Host = "sg2plzcpnl491716.prod.sin2.secureserver.net";
		LocalDate currentDate = LocalDate.now();
		try {
			// You need to implement the receiveEmail method in the recievingMail class
			String result = sendingCronMails.anniversaryWish(pop3Host, user, password, currentDate);
			return result;
		} catch (Exception e) {
			// Handle exceptions appropriately
			e.printStackTrace();
			return "Error: " + e.getMessage();
		}
	}


	
	//@Scheduled(cron = "0 15 13 * * ?") // This runs daily at 1:15 PM
	 //@Scheduled(cron = "0 11 10 * * ?") // This runs daily at 10:11 AM
	@Scheduled(cron = "10 10 10 * * ?")
	public String getbirthday() throws SQLException, ParseException {
	     String user = "hr@bornfire.in";
	     String password = "Managerbfi@123";
	     String storeType = "pop3";
	     String pop3Host = "sg2plzcpnl491716.prod.sin2.secureserver.net";
	     LocalDate currentDate = LocalDate.now();
	     try {
	         String result = sendingCronMails.birthdayWish(pop3Host, user, password, currentDate);
	         return result;
	     } catch (Exception e) {
	         e.printStackTrace();
	         return "Error: " + e.getMessage();
	     }
	 }


	 // @Scheduled(cron = "0 02 15 * * ?")
	public String getwelcome() throws SQLException, ParseException {
		String user = "hr@bornfire.in";
		String password = "Managerbfi@123";
		String storeType = "pop3";
		String pop3Host = "sg2plzcpnl491716.prod.sin2.secureserver.net";
		LocalDate currentDate = LocalDate.now();
		try {
			// You need to implement the receiveEmail method in the recievingMail class
			String result = sendingCronMails.WelcomeInvite(pop3Host, user, password, currentDate);
			return result;
		} catch (Exception e) {
			// Handle exceptions appropriately
			e.printStackTrace();
			return "Error: " + e.getMessage();
		}
	}

	// @Scheduled(fixedRate = 5000)
	public String getmail2() throws SQLException, ParseException {

		// return "Success";
		PlacementMaintenance placementmaintenance = new PlacementMaintenance();
		String user = "grn@bornfire.in";
		String password = "Sound@Amman3";
		String storeType = "pop3";
		String pop3Host = "sg2plzcpnl491716.prod.sin2.secureserver.net";
		return recievingMail2.receiveEmail2(pop3Host, storeType, user, password, placementmaintenance);
	}

	// @Scheduled(fixedRate = 5000)
	/*
	 * public String sendmails( ) throws SQLException, ParseException {
	 * System.out.println("Hi"); String to = "ragul.r@bornfire.in"; String from
	 * ="accts@bornfire.in"; String username = "accts@bornfire.in";//change
	 * accordingly String password = "VNivas@636003";//change accordingly String
	 * host = "sg2plzcpnl491716.prod.sin2.secureserver.net"; return
	 * sendingmails.sendmail( from, username, password, to,host ) ;
	 * 
	 * }
	 */
	// @Scheduled(fixedRate = 10000)
	public String spsendmails() throws SQLException, ParseException {
		System.out.println("Hi");
		String to = "siddhaiyan@bornfire.in";
		String from = "accts@bornfire.in";
		String username = "accts@bornfire.in";// change accordingly
		String password = "VNivas@636003";// change accordingly
		String host = "sg2plzcpnl491716.prod.sin2.secureserver.net";
		String Sp = "STACKPOS";
		String inv_due_date = "01-JUL-2023";
		String inv_date = "31-JUL-2023";
		return spsendingmail.sendmail(from, username, password, to, host, Sp, inv_due_date, inv_date);

	}
}