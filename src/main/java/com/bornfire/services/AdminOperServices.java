package com.bornfire.services;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bornfire.config.AES;
import com.bornfire.entities.BAMInventorymaster;
import com.bornfire.entities.BAMInventryMastRep;
import com.bornfire.entities.BELE_Table_Entity;
import com.bornfire.entities.BELE_Table_Repo;
import com.bornfire.entities.BTMAdminAssociateMod;
import com.bornfire.entities.BTMAdminAssociateModRep;
import com.bornfire.entities.BTMAdminAssociateProfile;
import com.bornfire.entities.BTMAdminAssociateProfileRep;
import com.bornfire.entities.BTMAdminCalendarMaster;
import com.bornfire.entities.BTMAdminCalendarMasterRep;
import com.bornfire.entities.BTMAdminExpenseReportRep;
import com.bornfire.entities.BTMAdminHolidayMaster;
import com.bornfire.entities.BTMAdminHolidayMasterRep;
import com.bornfire.entities.BTMAdminOrganizationMaster;
import com.bornfire.entities.BTMAdminOrganizationMasterRep;
import com.bornfire.entities.BTMAdminProfileManager;
import com.bornfire.entities.BTMAdminProfileMangerRep;
import com.bornfire.entities.BTMDocumentMaster;
import com.bornfire.entities.BTMDocumentMasterRep;
import com.bornfire.entities.BTMEmpTimeSheet;
import com.bornfire.entities.BTMEmpTimeSheetRep;
import com.bornfire.entities.BTMProjectDetailsRep;
import com.bornfire.entities.BTMProjectMaster;
import com.bornfire.entities.BTMProjectMasterRep;
import com.bornfire.entities.BTMProjectTeamDetailsRep;
import com.bornfire.entities.BTMRefCodeMaster;
import com.bornfire.entities.BTMRefCodeMasterRep;
import com.bornfire.entities.BTMTravelMaster;
import com.bornfire.entities.BTMTravelMasterRep;
import com.bornfire.entities.BTMWorkAssignment;
import com.bornfire.entities.BTMWorkAssignmentRep;
import com.bornfire.entities.BamDocumentMasRep;
import com.bornfire.entities.Bamcatcodemaintainrep;
import com.bornfire.entities.Bamcategorycodemain;
import com.bornfire.entities.Bamdocumentmanager;
import com.bornfire.entities.ExpenseMaster;
import com.bornfire.entities.LeaveMaster;
import com.bornfire.entities.LeaveMasterRep;
import com.bornfire.entities.OnDuty;
import com.bornfire.entities.OnDutyRep;
import com.bornfire.entities.ProjectDetails;
import com.bornfire.entities.ProjectTeamDetails;
import com.bornfire.entities.ResourceMaster;
import com.bornfire.entities.ResourceMasterRepo;

@Service
@ConfigurationProperties("output")
@Transactional
public class AdminOperServices {

	@Autowired
	BTMAdminAssociateProfileRep btmAdminAssociateProfileRep;

	@Autowired
	BTMAdminOrganizationMasterRep btmAdminOrganizationMasterRep;

	@Autowired
	BTMAdminHolidayMasterRep btmAdminHolidayMasterRep;

	@Autowired
	BTMAdminProfileMangerRep btmAdminProfileMangerRep;

	@Autowired
	BTMAdminExpenseReportRep btmAdminExpenseReportRep;

	@Autowired
	BTMProjectMasterRep btmProjectMasterRep;

	@Autowired
	BTMTravelMasterRep BTMtravelMasterRep;

	@Autowired
	BTMWorkAssignmentRep btmWorkAssignmentRep;

	@Autowired
	BTMRefCodeMasterRep btmRefCodeMasterRep;

	@Autowired
	BTMDocumentMasterRep btmDocumentMasterRep;

	@Autowired
	LeaveMasterRep leaveMasterRep;

	@Autowired
	OnDutyRep onDutyRep;

	@Autowired
	BTMProjectDetailsRep btmProjectDetailsRep;

	@Autowired
	BTMProjectTeamDetailsRep btmProjectTeamDetailsRep;

	@Autowired
	BTMAdminCalendarMasterRep btmAdminCalendarMasterRep;

	@Autowired
	BTMAdminAssociateModRep btmAdminAssociateModRep;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	ResourceMasterRepo resourceMasterRepo;

	@Autowired
	Bamcatcodemaintainrep Bamcatcodemain;
	
	@Autowired
	BAMInventryMastRep Baminvmasrep;
	
	@Autowired
	BamDocumentMasRep BamDocmasRep;
	
	@Autowired
	BTMEmpTimeSheetRep btmEmpTimeSheetRep;
	
	@Autowired
	BELE_Table_Repo bELE_Table_Repo;


// ==================================== Admin Organization Master ========================================

	public String addOrganizationModyfiy(BTMAdminOrganizationMaster btmAdminOrganizationMaster, String formmode) {

		String msg = "";

		if (formmode.equals("edit")) {

			BTMAdminOrganizationMaster up = btmAdminOrganizationMaster;

			up.setEntity_flg("Y");

			up.setDel_flg("Y");

			btmAdminOrganizationMasterRep.save(up);

			msg = "Modified Successfully";

		}
		return msg;
	}

	public BTMAdminOrganizationMaster getUser(String id) {

		if (btmAdminOrganizationMasterRep.existsById(id)) {
			BTMAdminOrganizationMaster up = btmAdminOrganizationMasterRep.findById(id).get();
			return up;
		} else {
			return new BTMAdminOrganizationMaster();
		}
	};
	/*
	 * public List<BTMAdminAssociateProfile> getAssociteUserslist() {
	 * 
	 * List<BTMAdminAssociateProfile> users =
	 * btmAdminAssociateProfileRep.getAssociatelist();
	 * 
	 * return users;
	 * 
	 * }
	 */

//	================================== Admin Associate master =========================================

	public String addAssociateUser(BTMAdminAssociateProfile user, String formmode, String UserId) {

		String msg = "";
System.out.println(user.getResource_id()+"userrrrrrrrrrrrrrrr");
		if (formmode.equals("edit")) {
			BTMAdminAssociateProfile up1 = btmAdminAssociateProfileRep.getEmployeedetail(user.getResource_id());
			{

				System.out.println(user.getResource_id()+"userrrrrrrrrrrrrrrr");
				user.setEntity_flg("N");
				user.setModify_flg("Y");
				user.setDel_flg("N");
				user.setModify_user(UserId);
				user.setModify_time(new Date());
				Session session = sessionFactory.getCurrentSession();
				session.saveOrUpdate(up1);
				up1.setEntity_flg("N");
				up1.setModify_flg("Y");
				
				BTMAdminAssociateMod mod = new BTMAdminAssociateMod();
				
				String encrypted_password = null;
				try {
					encrypted_password = AES.encrypt(user.getPassword());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				user.setPassword(encrypted_password);
				mod.setPassword(encrypted_password);
				// BTMAdminAssociateMod NEW=new BTMAdminAssociateMod(up1);
				btmAdminAssociateProfileRep.save(user);
				// btmAdminAssociateModRep.save(NEW);
				msg = "Modified Successfully";
			}

		} else if (formmode.equals("verify")) {
			Session session = sessionFactory.getCurrentSession();
			BTMAdminAssociateProfile ver = btmAdminAssociateProfileRep.getEmployeedetail(user.getResource_id());
			ver.setEntity_flg("Y");
			ver.setModify_flg("N");
			ver.setDel_flg("N");
			ver.setVerify_user(UserId);
			ver.setVerify_time(new Date());
			btmAdminAssociateProfileRep.save(ver);
			session.saveOrUpdate(ver);
			BTMAdminAssociateMod Main = new BTMAdminAssociateMod(ver);
			Main.setEntity_flg("N");
			// Main.setModify_flg("N");
			// Main.setDel_flg("N");
			btmAdminAssociateModRep.save(Main);
			// btmAdminAssociatePrifleRep.deleteById(user.getResource_id());
			msg = "Verified Successfully";

		} else if (formmode.equals("delete")) {

			BTMAdminAssociateProfile Dmain = btmAdminAssociateProfileRep.getEmployeedetail(user.getResource_id());
			Dmain.setEntity_flg("N");
			Dmain.setDel_flg("Y");
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(Dmain);
			msg = "Deleted Successfully";

		} else if (formmode.equals("cancel")) {

			BTMAdminAssociateMod ver = btmAdminAssociateModRep.getEmployeedetail(user.getResource_id());
			user.setEntity_flg("Y");
			user.setModify_flg("N");
			user.setDel_flg("N");
			Session session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(ver);
			BTMAdminAssociateProfile Main = new BTMAdminAssociateProfile(ver);
			// Main.setEntity_flg("Y");
			// Main.setModify_flg("N");
			// Main.setDel_flg("N");
			btmAdminAssociateProfileRep.save(Main);
			// btmAdminAssociateModRep.deleteById(user.getResource_id());
			msg = "Cancelled Successfully";

		} else if (formmode.equals("add")) {
			System.out.println("First step in the Service");

			Optional<BTMAdminAssociateProfile> addlist = btmAdminAssociateProfileRep.findById(user.getResource_id());
			if (addlist.isPresent()) {
				msg = "Account Already Exist";
			} else {
				System.out.println(user.getResource_id()+"userrrrrrrrrrrr");
				BTMAdminAssociateProfile up = user;
				BTMAdminAssociateMod up1 = new BTMAdminAssociateMod();
				up.setResource_id(user.getResource_id());
				up.setEntity_flg("N");
				up.setModify_flg("N");
				up.setEntry_user(UserId);
				up.setEntry_time(new Date());
				up1.setResource_id(user.getResource_id());
				up.setDel_flg("N");
				up1.setEntity_flg("N");
				up1.setModify_flg("N");
				up1.setDel_flg("N");
				up1.setEntry_user(UserId);
				up1.setEntry_time(new Date());

				String encrypted_imei = null;
				try {
					encrypted_imei = AES.encrypt(user.getImei());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				up.setImei(encrypted_imei);
				up1.setImei(encrypted_imei);
				String encrypted_password = null;
				try {
					encrypted_password = AES.encrypt(user.getPassword());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				up.setPassword(encrypted_password);
				up1.setPassword(encrypted_password);
				btmAdminAssociateProfileRep.save(user);
				btmAdminAssociateModRep.save(up1);
				msg = "Account Added Successfully";
			}
		}
		return msg;
	}

	public BTMAdminAssociateProfile getAssociteUser(String resId) {

		if (btmAdminAssociateProfileRep.existsById(resId)) {
			BTMAdminAssociateProfile up = btmAdminAssociateProfileRep.findById(resId).get();
			return up;
		} else {
			return new BTMAdminAssociateProfile();
		}
	};

	public BTMAdminAssociateProfile getAssociteVerifyUser(String resId) {

		if (btmAdminAssociateProfileRep.existsById(resId)) {
			BTMAdminAssociateProfile up = btmAdminAssociateProfileRep.findById(resId).get();
			return up;
		} else {
			return new BTMAdminAssociateProfile();
		}
	}

	public BTMAdminAssociateProfile getAssociteCancelUser(String resId) {

		if (btmAdminAssociateProfileRep.existsById(resId)) {
			BTMAdminAssociateProfile up = btmAdminAssociateProfileRep.findById(resId).get();
			return up;
		} else {
			return new BTMAdminAssociateProfile();
		}
	}

	public BTMAdminAssociateProfile getAssociteListUser(String resId) {

		if (btmAdminAssociateProfileRep.existsById(resId)) {
			BTMAdminAssociateProfile up = btmAdminAssociateProfileRep.findById(resId).get();
			return up;
		} else {
			return new BTMAdminAssociateProfile();
		}
	}

// ========================================== Maintenance associate profile ==================================

	public String modifyAssociate(BTMAdminAssociateProfile btmAdminAssociateProfile, String formmode, String userid) {

		String msg = "";

		if (formmode.equals("edit")) {

			BTMAdminAssociateProfile up1 = btmAdminAssociateProfileRep.getEmployeedetail(userid);

			BTMAdminAssociateProfile up = btmAdminAssociateProfile;

			up1.setDis_start_date(up.getDis_start_date());

			up1.setDis_end_date(up.getDis_end_date());

			up1.setEntity_flg("N");

			up1.setDel_flg("Y");

			up1.setDisable_flg(up.getDisable_flg());

			btmAdminAssociateProfileRep.save(up1);

			msg = "Modified Successfully";

		}

		return msg;

	}

// =============================== Maintenance Leave Master=======================
	public String modifyLeave(LeaveMaster leaveMaster, String formmode, String userid, String Id, String employeeId, BigDecimal year, String fromdate, String leave_reference, String todate1) {
	    String msg = "";

	    System.out.println("Rejected Leave");
	    if (formmode.equals("approve")) {
	        LeaveMaster up1 = leaveMasterRep.getleaveMaster(userid);
	        System.out.println("useriddddddddddddddddddd" + userid);
	        System.out.println("employeeId"+employeeId);

	        up1.setStatus("Approved");
	        up1.setAuth_time(new Date());
	        up1.setAuth_user(Id);
	        up1.setEntity_flg("Y");
	        
	      //updated in BELE Table
			BELE_Table_Entity values = bELE_Table_Repo.getvalues(up1.getEmployee_id());
			
			
			if (up1.getLeave_category().equals("CL")) {
			    try {
			        
			    	BigDecimal noOfDays = new BigDecimal(up1.getNo_of_days()); 
			    	System.out.println(noOfDays+"noOfDays");
			    	
			    	BigDecimal currentClAvail = (values.getClAvail() == null) ? BigDecimal.ZERO : values.getClAvail();
			    	System.out.println(currentClAvail+"currentClAvail");
			    	BigDecimal updatedClAvail = currentClAvail.add(noOfDays);
			    	System.out.println(updatedClAvail+"updatedClAvail");
			    	values.setClAvail(updatedClAvail);
			    	BigDecimal currentClOpen = values.getClOpn();
			    	System.out.println("currentClOpen"+currentClOpen);
			    	BigDecimal clBalance = currentClOpen.subtract(updatedClAvail);
			    	values.setClBal(clBalance);
			    	System.out.println("clBalance"+clBalance);
			    	
			    	bELE_Table_Repo.save(values);
			    
			    } catch (NumberFormatException e) {
			        
			        System.err.println("Invalid number format for no_of_days: " + up1.getNo_of_days());
			        e.printStackTrace();
			    }
			}	
		
			

	        leaveMasterRep.save(up1);
	        msg = "Approved Successfully";

	        String formattedFromDate = null;
	        try {
	            // Define and parse the date format
	            SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yy");
	            Date fromDateObject = sdf1.parse(fromdate);
	            formattedFromDate = sdf1.format(fromDateObject);
	            System.out.println("Formatted Date: " + formattedFromDate);
	        } catch (ParseException e) {
	            System.err.println("Error parsing the date: " + e.getMessage());
	            e.printStackTrace();
	            return "Error: Invalid date format.";
	        }

	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        LocalDate startDate = LocalDate.parse(fromdate, formatter);
	        LocalDate endDate = LocalDate.parse(todate1, formatter);

	        // Calculate days between dates
	        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
	        System.out.println("Number of days between " + fromdate + " and " + todate1 + ": " + daysBetween);

	        // Find Sundays
	        List<LocalDate> sundays = findSundaysBetweenDates(startDate, endDate);
	        for (LocalDate sunday : sundays) {
	            System.out.println("Sunday Holiday =====" + sunday);
	        }

	        // Get all dates in the range
	        List<LocalDate> datesInRange = getDatesBetween(startDate, endDate);
	        System.out.println(datesInRange+"datesInRange");
	        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        System.out.println(dateFormatter+"dateFormatter");

	        // Check if leave is approved
	        System.out.println("formattedFromDate"+formattedFromDate+"userid"+userid);
	        LeaveMaster lv1 = leaveMasterRep.getLeavebyFromDateapproved(formattedFromDate, employeeId);
	        if (lv1 == null) {
	            return "Not Approved";
	        } else {
	            for (LocalDate date : datesInRange) {
	            
	                String formattedDate = date.format(dateFormatter);
	            	System.out.println("secind step in the service");
	            	System.out.println(employeeId+"ID"+"formattedDate"+ formattedDate.split("-")[1]+"formattedDate"+formattedDate.split("-")[0]);
	                List<BTMEmpTimeSheet> existingEntries = btmEmpTimeSheetRep.getTimeSheetdatasheet(employeeId, formattedDate.split("-")[1], formattedDate.split("-")[0]);
	                if (!existingEntries.isEmpty()) {
	                	System.out.println("third stage");
	                    BTMEmpTimeSheet existingEntry = existingEntries.get(0);
	                    int dayOfMonth = date.getDayOfMonth();
	                    setDayProperty(existingEntry, dayOfMonth, formattedDate + "||00:00||00:00||00:00||00:00||Leave Applied||Leave Applied||NO");
	                    btmEmpTimeSheetRep.save(existingEntry);
	                } else {
	                    System.out.println("Entity does not exist for date: " + formattedDate);
	                }
	            }
	        }
	    }

	    if (formmode.equals("reject")) {
	        LeaveMaster up1 = leaveMasterRep.getleaveMaster(userid);
	        System.out.println("Rejected Leave"+userid);
	        up1.setStatus("Rejected");
	        up1.setEntity_flg("Y");
	        up1.setReject_remarks("Rejected");
	        up1.setDel_flg("Y");
	        up1.setAuth_time(new Date());
	        up1.setAuth_user(Id);

	        leaveMasterRep.save(up1);
	        msg = "Rejected Successfully";
	    }

	    return msg;
	}

	
	private static void setDayProperty(BTMEmpTimeSheet obj, int day, String value) {
        try {
            // Construct the method name based on the day of the month
            String methodName = "setDate_" + day;

            // Find the method using reflection
            Method method = BTMEmpTimeSheet.class.getMethod(methodName, String.class);

            // Invoke the method to set the value dynamically
            method.invoke(obj, value);
        } catch (Exception e) {
            e.printStackTrace(); // Handle or log the exception accordingly
        }
    }

	private static List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> datesInRange = new ArrayList<>();
        LocalDate currentDate = startDate;

        while (!currentDate.isAfter(endDate)) {
            datesInRange.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }

        return datesInRange;
    }
	
	  public static List<LocalDate> findSundaysBetweenDates(LocalDate fromDate, LocalDate toDate) {
	        List<LocalDate> sundays = new ArrayList<>();

	        while (!fromDate.isAfter(toDate)) {
	            if (fromDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
	                sundays.add(fromDate);
	            }
	            fromDate = fromDate.plusDays(1);
	        }

	        return sundays;
	    }

// ============================= Maintenance OD Master ===================

	public String modifyOd(OnDuty onDuty, String formmode, String userid, String Id) {

		String msg = "";

		if (formmode.equals("approve")) {

			OnDuty up1 = onDutyRep.getOdMaster(userid);

			OnDuty up = onDuty;

			up1.setStatus("Approved");

			up1.setEntity_flg("Y");

			up1.setAuth_time(new Date());

			up1.setAuth_user(Id);

			onDutyRep.save(up1);

			msg = "Approved Successfully";

		}

		if (formmode.equals("reject")) {

			OnDuty up1 = onDutyRep.getOdMaster(userid);

			OnDuty up = onDuty;

			up1.setStatus("Rejected");

			up1.setEntity_flg("Y");

			up1.setReject_remarks("Rejected");

			up1.setDel_flg("Y");

			up1.setAuth_time(new Date());

			up1.setAuth_user(Id);

			onDutyRep.save(up1);

			msg = "Rejected Successfully";

		}

		return msg;

	}

// ============================== Maintenance Expense Master =========================

	public String modifyExpense(ExpenseMaster expenses, String formmode, String resId) {

		String msg = "";

		if (formmode.equals("approve")) {

			ExpenseMaster up1 = btmAdminExpenseReportRep.getExpenseMaster(resId);
			ExpenseMaster up = expenses;
			up1.setStatus("Approved");
			up1.setEntity_flg("Y");
			up1.setDel_flg("Y");

			btmAdminExpenseReportRep.save(up1);

			msg = "Approved Successfully";

		}

		if (formmode.equals("reject")) {

			ExpenseMaster up1 = btmAdminExpenseReportRep.getExpenseMaster(resId);
			ExpenseMaster up = expenses;
			up1.setStatus("Rejected");
			up1.setEntity_flg("Y");
			up1.setDel_flg("Y");

			btmAdminExpenseReportRep.save(up1);

			msg = "Rejected Successfully";

		}

		return msg;

	}

// ===================================== Maintenance Travel Master ================================

	public String modifyTravelMaster(BTMTravelMaster travelMaster, String formmode, String resId) {

		String msg = "";

		if (formmode.equals("approve")) {

			BTMTravelMaster up1 = BTMtravelMasterRep.getTravelMaster(resId);

			BTMTravelMaster up = travelMaster;

			up1.setTra_status("Approved");

			up1.setEntity_flg("Y");

			up1.setDel_flg("Y");

			BTMtravelMasterRep.save(up1);

			msg = "Approved Successfully";

		}

		if (formmode.equals("reject")) {

			BTMTravelMaster up1 = BTMtravelMasterRep.getTravelMaster(resId);
			BTMTravelMaster up = travelMaster;
			up1.setTra_status("Rejected");
			up1.setEntity_flg("Y");
			up1.setDel_flg("Y");

			BTMtravelMasterRep.save(up1);

			msg = "Rejected Successfully";

		}

		return msg;

	}

// ========================= Admin Holiday Master ===========================

	public String addHolidayDetails(BTMAdminHolidayMaster btmAdminHolidayMaster, String formmode, BigDecimal recordNo) {

		String msg = "";

		BTMAdminHolidayMaster up1 = btmAdminHolidayMaster;

		BigDecimal test = btmAdminHolidayMasterRep.getcount(up1.getRecord_srl());

		if (formmode.equals("add")) {

			BTMAdminHolidayMaster up = btmAdminHolidayMaster;

			up.setEntity_flg("Y");

			up.setDel_flg("N");

			btmAdminHolidayMasterRep.save(up);

			msg = "Added Successfully";

		} else if (formmode.equals("edit")) {

			BTMAdminHolidayMaster up = btmAdminHolidayMaster;

			up.setEntity_flg("Y");

			up.setDel_flg("N");

			btmAdminHolidayMasterRep.save(up);

			msg = "Edited Successfully";
		}

		return msg;
	}

	public List<BTMAdminHolidayMaster> getHolidaylist() {

		List<BTMAdminHolidayMaster> users = btmAdminHolidayMasterRep.getAssocitelist();

		return users;

	}

	public BTMAdminHolidayMaster getHolidayDetail(BigDecimal resId) {

		if (btmAdminHolidayMasterRep.existsById(resId)) {
			BTMAdminHolidayMaster up = btmAdminHolidayMasterRep.findById(resId).get();
			return up;
		} else {
			return new BTMAdminHolidayMaster();
		}
	};

//	================================= Admin Profile Manager ===============================

	public List<BTMAdminProfileManager> getProfileManagerlist() {

		List<BTMAdminProfileManager> users = btmAdminProfileMangerRep.getProfilelist();

		return users;

	}

	public BTMAdminProfileManager getProfileManager(String id) {

		if (btmAdminProfileMangerRep.existsById(id)) {
			BTMAdminProfileManager up = btmAdminProfileMangerRep.findById(id).get();
			return up;
		} else {
			return new BTMAdminProfileManager();
		}
	};

	public String addProfileDetails(BTMAdminProfileManager btmAdminProfileManager, String formmode) {

		String msg = "";

		if (formmode.equals("edit")) {

			BTMAdminProfileManager up = btmAdminProfileManager;

			up.setEntity_flg("Y");

			up.setDel_flg("N");

			btmAdminProfileMangerRep.save(up);

			msg = "Edited Successfully";

		} else if (formmode.equals("verify")) {

			BTMAdminProfileManager up = btmAdminProfileManager;

			up.setEntity_flg("Y");

			up.setDel_flg("N");

			up.setModify_flg("Y");

			btmAdminProfileMangerRep.save(up);

			msg = "Verified Successfully";

		} else if (formmode.equals("delete")) {

			BTMAdminProfileManager up = btmAdminProfileManager;

			up.setEntity_flg("N");

			up.setDel_flg("Y");

			btmAdminProfileMangerRep.save(up);

			msg = "Deleted Successfully";
		}

		return msg;
	}

	// LIST AND DETAILS EXPENSE REPORT
	public List<ExpenseMaster> getExpenseReportlist() {

		List<ExpenseMaster> users = btmAdminExpenseReportRep.getReportList();

		return users;

	}

	public ExpenseMaster getReportManager(String id) {

		if (btmAdminExpenseReportRep.existsById(id)) {
			ExpenseMaster up = btmAdminExpenseReportRep.findById(id).get();
			return up;
		} else {
			return new ExpenseMaster();
		}
	}

//	travel Master List

	public List<BTMTravelMaster> getTravelMasterList() {

		List<BTMTravelMaster> users = BTMtravelMasterRep.getTravellist();

		return users;
	}

	// LIST PROFILE MASTER
	public List<BTMProjectMaster> getProjectMasterlist() {

		List<BTMProjectMaster> users = btmProjectMasterRep.getProjectlist();

		return users;
	}

	// Add PROJECT MASTER

	public String addProjectMaster(BTMProjectMaster btmProjectMaster, ProjectDetails projectDetails,
			ProjectTeamDetails projectTeamDetails, String formmode, String userId) {

		String msg = "";
		int count = btmProjectMasterRep.getprojectCount(btmProjectMaster.getProj_id(), btmProjectMaster.getProj_name());

		if (count != 0) {
			msg = "already Exsist";
		} else {

			if (formmode.equals("add")) {

				BTMProjectMaster up = btmProjectMaster;

				up.setEntry_time(new Date());
				up.setEntry_user(userId);
				up.setEntity_flg("Y");
				up.setDel_flg("N");
				up.setRemarks("Pending");

				btmProjectMasterRep.save(up);
				ProjectDetails up1 = projectDetails;
				up1.setProj_id(up.getProj_id());
				up1.setClient_id(up.getClient_id());
				up1.setRemarks(up.getRemarks());
				up1.setEntity_flg("Y");
				up1.setDel_flg("N");

				btmProjectDetailsRep.save(up1);
				ProjectTeamDetails up2 = projectTeamDetails;

				up2.setClient_id(up.getClient_id());
				up2.setProj_id(up.getProj_id());
				up2.setRemarks(up.getRemarks());
				up2.setEntity_flg("Y");
				up2.setDel_flg("N");

				btmProjectTeamDetailsRep.save(up2);

				msg = "Added Successfully";

			}
		}

		if (formmode.equals("edit")) {

			BTMProjectMaster up = btmProjectMaster;
			up.setRemarks(up.getRemarks());
			up.setEntity_flg("Y");
			up.setModify_flg("Y");
			up.setModify_user(userId);
			up.setModify_time(new Date());
			up.setDel_flg("N");

			btmProjectMasterRep.save(up);

			ProjectDetails up1 = projectDetails;

			up1.setProj_id(up.getProj_id());
			up1.setClient_id(up.getClient_id());
			up1.setEntity_flg("Y");
			up1.setModify_flg("Y");
			up1.setDel_flg("N");

			btmProjectDetailsRep.save(up1);

			ProjectTeamDetails up2 = projectTeamDetails;

			up2.setClient_id(up.getClient_id());
			up2.setProj_id(up.getProj_id());
			up2.setEntity_flg("Y");
			up2.setModify_flg("Y");
			up2.setDel_flg("N");

			btmProjectTeamDetailsRep.save(up2);

			msg = "Edited Successfully";

		} else if (formmode.equals("delete")) {

			BTMProjectMaster up = btmProjectMaster;
			up.setEntity_flg("N");
			up.setDel_flg("Y");

			btmProjectMasterRep.save(up);

			ProjectDetails up1 = projectDetails;
			up1.setEntity_flg("N");
			up1.setDel_flg("Y");

			btmProjectDetailsRep.save(up1);

			ProjectTeamDetails up2 = projectTeamDetails;
			up2.setEntity_flg("N");
			up2.setDel_flg("Y");

			btmProjectTeamDetailsRep.save(up2);

			msg = "Deleted Successfully";
		}

		return msg;

	}

//GET PROJECT MASTER
	public BTMProjectMaster getProjectManager(String resId) {

		if (btmProjectMasterRep.existsById(resId)) {
			BTMProjectMaster up = btmProjectMasterRep.findById(resId).get();
			return up;
		} else {
			return new BTMProjectMaster();
		}
	}

	public BTMTravelMaster getTravelMaster(String resId) {

		if (BTMtravelMasterRep.existsById(resId)) {
			BTMTravelMaster up = BTMtravelMasterRep.findById(resId).get();
			return up;
		} else {
			return new BTMTravelMaster();
		}
	}

	// Work Assignment
	public BTMWorkAssignment getWorkAssignmentMaster(String resId) {

		if (btmProjectMasterRep.existsById(resId)) {
			BTMWorkAssignment up = btmWorkAssignmentRep.findById(resId).get();
			return up;
		} else {
			return new BTMWorkAssignment();
		}
	}

	// =============== Admin RefCode master =========================

	public BTMRefCodeMaster getRefMaster(String id) {

		if (btmRefCodeMasterRep.existsById(id)) {
			BTMRefCodeMaster up = btmRefCodeMasterRep.findById(id).get();
			return up;
		} else {
			return new BTMRefCodeMaster();
		}
	}

	public String addRefCodeMaster(BTMRefCodeMaster btmRefCodeMaster, String formmode, String ref_id) {

		String msg = "";

		if (formmode.equals("add")) {

			BTMRefCodeMaster up = btmRefCodeMaster;

			up.setEntity_flg("Y");

			up.setDel_flg("N");

			btmRefCodeMasterRep.save(up);

			msg = "Added Successfully";

		} else if (formmode.equals("edit")) {

			BTMRefCodeMaster up = btmRefCodeMasterRep.getRefMaster(ref_id);
			up.setRef_type_desc(btmRefCodeMaster.getRef_type_desc());
			up.setRef_id_desc(btmRefCodeMaster.getRef_id_desc());
			up.setRemarks(btmRefCodeMaster.getRemarks());
			up.setModule_id(btmRefCodeMaster.getModule_id());
			up.setRef_type(btmRefCodeMaster.getRef_type());
			BTMRefCodeMaster user = btmRefCodeMasterRep.findById(up.getRef_id()).get();

			if (btmRefCodeMaster.getRef_type().equals(user.getRef_type())
					&& btmRefCodeMaster.getRef_type_desc().equals(user.getRef_type_desc())
					&& btmRefCodeMaster.getRef_id_desc().equals(user.getRef_id_desc())
					&& btmRefCodeMaster.getModule_id().equals(user.getModule_id())
					&& btmRefCodeMaster.getRemarks().equals(user.getRemarks())) {
				msg = "No Modification More";
			} else {

				btmRefCodeMasterRep.save(up);

				msg = "Edited Successfully";
			}

		} else if (formmode.equals("delete")) {

			BTMRefCodeMaster up = btmRefCodeMaster;

			up.setEntity_flg("Y");

			up.setDel_flg("Y");

			btmRefCodeMasterRep.save(up);

			msg = "Deleted Successfully";
		}

		return msg;
	}

// =========================== Admin Doc Master =======================================

	public String addDocumentUser(BTMDocumentMaster btmDocumentMaster, String formmode) {

		String msg = "";

		if (formmode.equals("add")) {

			BTMDocumentMaster up = btmDocumentMaster;

			up.setEntity_flg("Y");

			up.setDel_flg("N");

			btmDocumentMasterRep.save(up);

			msg = "Added Successfully";

		} else if (formmode.equals("edit")) {
			BTMDocumentMaster user = btmDocumentMasterRep.findById(btmDocumentMaster.getDoc_ref_no()).get();

			if (btmDocumentMaster.getDoc_id().equals(user.getDoc_id())
					&& btmDocumentMaster.getDoc_name().equals(user.getDoc_name())
					&& btmDocumentMaster.getDoc_desc().equals(user.getDoc_desc())
					&& btmDocumentMaster.getDoc_type().equals(user.getDoc_type())
					&& btmDocumentMaster.getDoc_group().equals(user.getDoc_group())
					&& btmDocumentMaster.getAccess_type().equals(user.getAccess_type())
					&& btmDocumentMaster.getAccess_group().equals(user.getAccess_group())
					&& btmDocumentMaster.getDoc_uploader().equals(user.getDoc_uploader())
					&& btmDocumentMaster.getDoc_approver().equals(user.getDoc_approver())
					&& btmDocumentMaster.getDoc_verifier().equals(user.getDoc_verifier())
					&& btmDocumentMaster.getDoc_owner().equals(user.getDoc_owner())) {
				msg = "No Modification More";
			} else {
				BTMDocumentMaster up = btmDocumentMaster;

				up.setEntity_flg("Y");

				up.setDel_flg("N");

				btmDocumentMasterRep.save(up);

				msg = "Edited Successfully";
			}

		}

		return msg;
	}

	// get calendar list

	public List<BTMAdminCalendarMaster> getCalendarlist() {

		List<BTMAdminCalendarMaster> btm_cal = new ArrayList<>();
		List<Object[]> list_obj = btmAdminCalendarMasterRep.findBysrl();

		for (Object[] obj : list_obj) {

			BTMAdminCalendarMaster cal = new BTMAdminCalendarMaster();

			cal.setYear(String.valueOf(obj[0]));
			cal.setMonth(String.valueOf(obj[1]));

			btm_cal.add(cal);

		}
		return btm_cal;
	}

	// get monthly holiday list from HolidayMaster

	public List<BTMAdminHolidayMaster> getMonthlyHolidaylist(String year, String month) throws ParseException {

		List<BTMAdminHolidayMaster> hol_des = new ArrayList<>();
		List<Object[]> list_obj = btmAdminHolidayMasterRep.getMonthlyHolidaylists(year, month);

		for (Object[] obj : list_obj) {

			BTMAdminHolidayMaster holiday = new BTMAdminHolidayMaster();

			holiday.setCal_year(String.valueOf(obj[0]));
			holiday.setCal_month(String.valueOf(obj[1]));
			holiday.setRecord_date(new SimpleDateFormat("dd-MM-yyyy").parse(String.valueOf(obj[2])));
			// holiday.setDay(String.valueOf(obj[3]));
			holiday.setHoliday_desc(String.valueOf(obj[4]));
			holiday.setHoliday_remarks(String.valueOf(obj[5]));

			hol_des.add(holiday);

		}
		return hol_des;
	}

//////////CATEGORY CODE MAINTAINNANCE
	public String Catecodemaintain(Bamcategorycodemain Bamcategorycodemain, String formmode, String headcode,
			String categorycode, String subcategorycode) {

		String msg = "";

		if (formmode.equals("edit")) {
			System.out.println("The id is : " + Bamcategorycodemain.getSl_no());
			System.out.println("The id is : " + Bamcategorycodemain.getSl_no());
			Optional<Bamcategorycodemain> up = Bamcatcodemain.findById(Bamcategorycodemain.getSl_no());

			if (up.isPresent()) {
				Bamcategorycodemain bamcat = up.get();

				bamcat.setSolid(Bamcategorycodemain.getSolid());
				// bamcat.setLocation(Bamcategorycodemain.getLocation());
				bamcat.setDepreciation_fund_account(Bamcategorycodemain.getDepreciation_fund_account());
				// bamcat.setDepreciation_method(Bamcategorycodemain.getDepreciation_method());
				bamcat.setDepreciation_pandl_account(Bamcategorycodemain.getDepreciation_pandl_account());
				// bamcat.setDepreciation_percentage(Bamcategorycodemain.getDepreciation_percentage());
				bamcat.setAsset_account_number(Bamcategorycodemain.getAsset_account_number());
				Bamcatcodemain.save(bamcat);

				msg = "Modified Successfully";
			}

		} else if (formmode.equals("add")) {

			Session session = sessionFactory.getCurrentSession();

			String asset_code = Bamcategorycodemain.getAsset_code();
			Long count = (Long) session
					.createQuery("SELECT COUNT(sp) FROM Bamcategorycodemain sp WHERE sp.asset_code = :asset_code")
					.setParameter("asset_code", asset_code).uniqueResult();

			if (count > 0) {
				msg = " Asset Code already exists. Please Give different ...";
			} else {

				Session hs = sessionFactory.getCurrentSession();
				Bamcategorycodemain bamcat = new Bamcategorycodemain();
				DecimalFormat numformate = new DecimalFormat("00");
				BigDecimal billNumber = (BigDecimal) hs
						.createNativeQuery("SELECT GENERATE_SRL_NO.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();
				String serialno = numformate.format(billNumber);

				bamcat.setSl_no(serialno);
				bamcat.setHead_code(headcode);
				bamcat.setHead_description(Bamcategorycodemain.getHead_description());
				bamcat.setCategory_code(categorycode);
				bamcat.setCategory_description(Bamcategorycodemain.getCategory_description());
				bamcat.setSub_category_code(subcategorycode);
				bamcat.setSub_category_description(Bamcategorycodemain.getSub_category_description());
				bamcat.setAsset_code(Bamcategorycodemain.getAsset_code());
				bamcat.setAsset_account_number(Bamcategorycodemain.getAsset_account_number());
				bamcat.setDepreciation_fund_account(Bamcategorycodemain.getDepreciation_fund_account());
				// bamcat.setDepreciation_method(Bamcategorycodemain.getDepreciation_method());
				bamcat.setDepreciation_pandl_account(Bamcategorycodemain.getDepreciation_pandl_account());
				// bamcat.setDepreciation_percentage(Bamcategorycodemain.getDepreciation_percentage());
				bamcat.setSolid(Bamcategorycodemain.getSolid());
				// bamcat.setLocation(Bamcategorycodemain.getLocation());
				msg = "Added Successfully";

				Bamcatcodemain.save(bamcat);
			}
		}
		return msg;
	}

	public String deletesrn(String asn) {
		// Attempt to find the entity by its ID (ASN)
		Optional<Bamcategorycodemain> optionalEntity = Bamcatcodemain.findById(asn);

		if (optionalEntity.isPresent()) {
			// If the entity exists, delete it
			Bamcatcodemain.delete(optionalEntity.get());
			return "Deleted Successfully!";
		} else {
			// If the entity does not exist, return an appropriate message
			return "Deletion Failed: Entity with ASN " + asn + " does not exist.";
		}
	}
/////Inventory Master
	public String Invmastadd(BAMInventorymaster BAMInventorymaster, String formmode, String InvMastadd, String userId,String username, String depr_method, String depr_percent, String headcode, String categorycode, String subcategorycode) throws KeyManagementException, NoSuchAlgorithmException {

	    String msg = "";

	    if (InvMastadd.equals("InvMastadd")) {

	        if (formmode.equals("add")) {

	            Session hs = sessionFactory.getCurrentSession();
	            DecimalFormat numformate = new DecimalFormat("000000");
	            String cate_code = "0";

	            BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT INVENTORY_SRL_NO.NEXTVAL AS SRL_NO FROM DUAL")
	                    .getSingleResult();
	            String serialno = numformate.format(billNumber);

	            System.out.println("Sequence is : " + serialno);
	            if (categorycode.equals("I")) {
	                cate_code = "01";
	            } else if (categorycode.equals("II")) {
	                cate_code = "02";
	            } else if (categorycode.equals("III")) {
	                cate_code = "03";
	            } else if (categorycode.equals("IV")) {
	                cate_code = "04";
	            }

	            String AssetSrlNo = BAMInventorymaster.getSOL_ID() + BAMInventorymaster.getLOC_TYPE() + headcode + cate_code + subcategorycode + serialno;

	            System.out.println("Asset serial no is : " + AssetSrlNo);

	            BAMInventorymaster bamcat = BAMInventorymaster;
	            bamcat.setASST_SRL_NO(msg);
	            bamcat.setENTRY_USER(userId);
	            bamcat.setENTITY_FLG("Y");
	            bamcat.setENTRY_TIME(new Date());
	            bamcat.setDEL_FLG("N");
	            
	            System.out.println("The ins : " + depr_method);
	            System.out.println("depr_percent: " + BAMInventorymaster.getDEPR_PERCENT());
	            if (depr_percent != null && !depr_percent.trim().isEmpty()) {
	                
	                bamcat.setDEPR_PERCENT(BAMInventorymaster.getDEPR_PERCENT());
	                bamcat.setDEPR_METHOD(depr_method);
	            } else {
	                System.out.println("The percent is either null or empty.");
	            }
	            Baminvmasrep.save(bamcat);
	            msg = "Asset " + AssetSrlNo + " Generated Successfully...";
	            	            }else if(formmode.equals("edit")){
			
			Optional<BAMInventorymaster> BAMInvms = Baminvmasrep.findById(BAMInventorymaster.getASST_SRL_NO());
			if(BAMInvms.isPresent()) {
				BAMInventorymaster bamcat = BAMInvms.get();
				bamcat.setASST_NAME(BAMInventorymaster.getASST_NAME());

				bamcat.setASST_HEAD(BAMInventorymaster.getASST_HEAD());
				bamcat.setASST_CATEGORY(BAMInventorymaster.getASST_CATEGORY());
				bamcat.setASST_SUB_CATEOGRY(BAMInventorymaster.getASST_SUB_CATEOGRY());
				bamcat.setASST_CATEGORY_DESC(BAMInventorymaster.getASST_CATEGORY_DESC());
				bamcat.setASST_TYPE(BAMInventorymaster.getASST_TYPE());
				bamcat.setASST_CRNCY(BAMInventorymaster.getASST_CRNCY());

				/* bamcat.setDate_of_purchase(BAMInventorymaster.get()); */

				/* bamcat.setYear_of_purchase(BAMInventorymaster.getYear_of_purchase()); */
				
				bamcat.setORG_COST(BAMInventorymaster.getORG_COST());
				bamcat.setASST_EXP_DATE(BAMInventorymaster.getASST_EXP_DATE());

				
				bamcat.setASST_RMKS(BAMInventorymaster.getASST_RMKS());
				bamcat.setDEPR_FLAG(BAMInventorymaster.getDEPR_FLAG());
				bamcat.setDEPR_FREQ(BAMInventorymaster.getDEPR_FREQ());

				if (depr_percent != null && !depr_percent.trim().isEmpty()) {
				    bamcat.setDEPR_PERCENT(BAMInventorymaster.getDEPR_PERCENT());
				    System.out.println(depr_method);
				    bamcat.setDEPR_METHOD(depr_method);
				} else {
				    System.out.println("The percent is either null or empty.");
				}
				bamcat.setACC_DEPR(BAMInventorymaster.getACC_DEPR());
				bamcat.setLIFE_SPAN_MTH(BAMInventorymaster.getLIFE_SPAN_MTH());

				bamcat.setDATE_OF_LAST_DEPR(BAMInventorymaster.getDATE_OF_LAST_DEPR());
				bamcat.setDATE_OF_ACQN(BAMInventorymaster.getDATE_OF_ACQN());
				bamcat.setDATE_OF_LAST_TFR(BAMInventorymaster.getDATE_OF_LAST_TFR());
				bamcat.setCUR_BOOK_VALUE(BAMInventorymaster.getCUR_BOOK_VALUE());
				bamcat.setMKT_VALUE(BAMInventorymaster.getMKT_VALUE());
				bamcat.setLOC_TYPE(BAMInventorymaster.getLOC_TYPE());
				bamcat.setSOL_ID(BAMInventorymaster.getSOL_ID());
				bamcat.setEMP_ID(BAMInventorymaster.getEMP_ID());
				bamcat.setDEPT_DIV_NAME(BAMInventorymaster.getDEPT_DIV_NAME());
				bamcat.setLOCA_ADDR(BAMInventorymaster.getLOCA_ADDR());
				bamcat.setLOC_RMKS(BAMInventorymaster.getLOC_RMKS());
				bamcat.setGTEE_AMT(BAMInventorymaster.getGTEE_AMT());
				bamcat.setGTEE_AMT_PERCENT(BAMInventorymaster.getGTEE_AMT_PERCENT());
				bamcat.setNOM_DEPR_AMT(BAMInventorymaster.getNOM_DEPR_AMT());
				bamcat.setDEPR_RMKS(BAMInventorymaster.getDEPR_RMKS());
				bamcat.setASST_ACCT_NO(BAMInventorymaster.getASST_ACCT_NO());
				bamcat.setMODIFY_USER(BAMInventorymaster.getASST_HEAD());
				bamcat.setMODIFY_TIME(new Date());
				bamcat.setDEL_FLG("N");
				bamcat.setMODIFY_FLG("Y");
				
				Baminvmasrep.save(bamcat);
				msg = "Modified Successfully";
			}else {
				msg="Not a valid id";
			}
			
			
			
		}else if(formmode.equals("verify")){
			
			Optional<BAMInventorymaster> BAMInvms = Baminvmasrep.findById(BAMInventorymaster.getASST_SRL_NO());
			if(BAMInvms.isPresent()) {
				BAMInventorymaster bamcat = BAMInvms.get();
				
				Baminvmasrep.save(bamcat);
				msg = "Verified Successfully";
			}else {
				msg="Error Occured !!!";
			}
			
		}else {
			msg = "Invalid Option Please contact Administrator";
		}
		}else if(InvMastadd.equals("InvTransadd")) {
			
		}
		return msg;
	}
	
	// Document Manager
	public String DocManaaddedit(Bamdocumentmanager Bamdocumentmanager, String formmode, MultipartFile documentFile) {
	    String msg = "";

	    try {
	        if (formmode.equals("edit")) {
	            BamDocmasRep.findById(Bamdocumentmanager.getDoc_id()).ifPresent(bamcat -> {
	                if (documentFile != null && !documentFile.isEmpty()) {
	                    try {
	                        String filePath = saveFile(documentFile, bamcat.getDoc_id());
	                        bamcat.setDoc_location(filePath);
	                    } catch (IOException e) {
	                        throw new RuntimeException("Error saving file", e);
	                    }
	                }
	                bamcat.setModify_time(new Date());
	                bamcat.setDel_flg("N");
	                BamDocmasRep.save(bamcat);
	            });
	            msg = "Modified Successfully";

	        } else if (formmode.equals("add")) {
	            if (documentFile != null && !documentFile.isEmpty()) {
	                try {
	                    String filePath = saveFile(documentFile, Bamdocumentmanager.getDoc_id());
	                    Bamdocumentmanager.setDoc_location(filePath);
	                } catch (IOException e) {
	                    throw new RuntimeException("Error saving file", e);
	                }
	            }
	            Bamdocumentmanager.setDel_flg("N");
	            BamDocmasRep.save(Bamdocumentmanager);
	            msg = "Added Successfully";

	        } else if (formmode.equals("verify")) {
	            BamDocmasRep.findById(Bamdocumentmanager.getDoc_id()).ifPresent(bamcat -> {
	                bamcat.setDel_flg("Y");
	                BamDocmasRep.save(bamcat);
	            });
	            msg = "Verified Successfully";
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        msg = "Document Upload Unsuccessful";
	    }

	    return msg;
	}
	
	

@Value("${document.folder.path}")
private String documentFolderPath;

private String saveFile(MultipartFile file, String docId) throws IOException {
    if (file == null || file.isEmpty()) {
        throw new IOException("File is empty or not provided.");
    }
    if (docId == null || docId.isEmpty()) {
        throw new IOException("Document ID is null or empty.");
    }

    // Define the file name and path
    String fileName = docId + "_" + file.getOriginalFilename();
    String filePath = documentFolderPath + File.separator + fileName;

    // Save file to the specified location
    File destinationFile = new File(filePath);
    if (!destinationFile.getParentFile().exists()) {
        destinationFile.getParentFile().mkdirs();
    }
    file.transferTo(destinationFile);

    return filePath;
}




}
