package com.bornfire.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bornfire.entities.Assosiate_Profile_Entity;
import com.bornfire.entities.Assosiate_Profile_Repo;
import com.bornfire.entities.GstBtmEntity;
import com.bornfire.entities.Gstoverseas;
import com.bornfire.entities.GstoverseasRepo;



@Service
@Transactional
public class FileUploadServices {
	
@Autowired
Assosiate_Profile_Repo assosiate_Profile_Repo;
	@Autowired
	SessionFactory sessionFactory;
	
	//public static Object java;
	

	
	public String UploadPO(String screenId, MultipartFile file, String userid, Assosiate_Profile_Entity Assosiate_Profile_Entity )
			throws SQLException, FileNotFoundException, IOException,NullPointerException {
		System.out.println("Entering Second Service Succesfully");
		//String datePattern = "dd-MM-yyyy";  // Adjust this pattern based on your actual date format

        // Create a SimpleDateFormat object for parsing
		 SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yy");
		    SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yy");

		String fileName = file.getOriginalFilename();

		String fileExt = "";
		String msg = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			fileExt = fileName.substring(i + 1);
		}

	if (fileExt.equals("xlsx") || fileExt.equals("xls")) {

			try {
				Workbook workbook = WorkbookFactory.create(file.getInputStream());

				List<HashMap<Integer, String>> mapList = new ArrayList<HashMap<Integer, String>>();
				for (Sheet s : workbook) {
					for (Row r : s) {

						if (!isRowEmpty(r)) {
							if (r.getRowNum() == 0) {
								continue;
							}

							HashMap<Integer, String> map = new HashMap<>();

							for (int j = 0; j < 77; j++) {

								Cell cell = r.getCell(j);
								DataFormatter formatter = new DataFormatter();
								String text = formatter.formatCellValue(cell);
								map.put(j, text);
							}
							mapList.add(map);

						}

					}

				}

				for (HashMap<Integer, String> item : mapList) {
					
					Assosiate_Profile_Entity PO = new Assosiate_Profile_Entity();
					
					
					
					/* String ORGANISATION = item.get(0); */
					
					//System.out.println("ORGANISATION" +ORGANISATION);
					
					PO.setOrganisation(item.get(0));
					System.out.println("hjfebvgfvesjgf dshybdgvfeskyjgvfbdtbyuesbfesybvcesh"+item.get(0));
					
					String CATEGORY = item.get(1);
					System.out.println("CATEGORY: " + CATEGORY);

					String RESOURCE_ID = item.get(2);
					System.out.println("RESOURCE_ID: " + RESOURCE_ID);

					String RESOURCE_NAME = item.get(3);
					System.out.println("RESOURCE_NAME: " + RESOURCE_NAME);

					String EMPLOYEE_REF = item.get(4);
					System.out.println("EMPLOYEE_REF: " + EMPLOYEE_REF);

					String EMPLOYEE_ID = item.get(5);
					System.out.println("EMPLOYEE_ID: " + EMPLOYEE_ID);

					String DOJ = item.get(6);
					System.out.println("DOJ: " + DOJ);

					Date dates = inputFormat.parse(DOJ);
					String formattedDatesss = outputFormat.format(dates);
					System.out.println("Formatted DOJ: " + formattedDatesss);

					String DOB = item.get(7);
					System.out.println("DOB: " + DOB);

					Date dates1 = inputFormat.parse(DOB);
					String formattedDates = outputFormat.format(dates1);
					System.out.println("Formatted DOB: " + formattedDates);

					String GROUPS = item.get(8);
					System.out.println("GROUPS: " + GROUPS);

					String TEAM = item.get(9);
					System.out.println("TEAM: " + TEAM);

					String REPORT_MGR_ID = item.get(10);
					System.out.println("REPORT_MGR_ID: " + REPORT_MGR_ID);

					String REPORT_MGR_NAME = item.get(11);
					System.out.println("REPORT_MGR_NAME: " + REPORT_MGR_NAME);

					String DESIGN = item.get(12);
					System.out.println("DESIGN: " + DESIGN);

					String ROLE = item.get(13);
					System.out.println("ROLE: " + ROLE);

					String QUAL = item.get(14);
					System.out.println("QUAL: " + QUAL);

					String ADDL_QUAL = item.get(15);
					System.out.println("ADDL_QUAL: " + ADDL_QUAL);

					String SKILL_SET = item.get(16);
					System.out.println("SKILL_SET: " + SKILL_SET);

					String EXPERTISE = item.get(17);
					System.out.println("EXPERTISE: " + EXPERTISE);

					String PANCARD = item.get(18);
					System.out.println("PANCARD: " + PANCARD);

					String AADHAR = item.get(19);
					System.out.println("AADHAR: " + AADHAR);

					String PASSPORT = item.get(20);
					System.out.println("PASSPORT: " + PASSPORT);

					String DRIVING_LICENSE = item.get(21);
					System.out.println("DRIVING_LICENSE: " + DRIVING_LICENSE);

					String GENDER = item.get(22);
					System.out.println("GENDER: " + GENDER);

					String BLOOD_GROUP = item.get(23);
					System.out.println("BLOOD_GROUP: " + BLOOD_GROUP);

					String MARITAL_STATUS = item.get(24);
					System.out.println("MARITAL_STATUS: " + MARITAL_STATUS);

					String DEPENDANTS = item.get(25);
					System.out.println("DEPENDANTS: " + DEPENDANTS);

					BigDecimal total_rwa1 = new BigDecimal(DEPENDANTS.trim());
					System.out.println("Parsed DEPENDANTS: " + total_rwa1);

					String MOBILE = item.get(26);
					System.out.println("MOBILE: " + MOBILE);

					String ALT_MOBILE = item.get(27);
					System.out.println("ALT_MOBILE: " + ALT_MOBILE);

					String ACCESS_ID = item.get(28);
					System.out.println("ACCESS_ID: " + ACCESS_ID);

					String EMAIL = item.get(29);
					System.out.println("EMAIL: " + EMAIL);

					String ADDR1 = item.get(30);
					System.out.println("ADDR1: " + ADDR1);

					String ADDR2 = item.get(31);
					System.out.println("ADDR2: " + ADDR2);

					String CITY = item.get(32);
					System.out.println("CITY: " + CITY);

					String STATE = item.get(33);
					System.out.println("STATE: " + STATE);

					String COUNTRY = item.get(34);
					System.out.println("COUNTRY: " + COUNTRY);

					String POSTAL_CODE = item.get(35);
					System.out.println("POSTAL_CODE: " + POSTAL_CODE);

					String LOC_ADDR1 = item.get(36);
					System.out.println("LOC_ADDR1: " + LOC_ADDR1);

					String LOC_ADDR2 = item.get(37);
					System.out.println("LOC_ADDR2: " + LOC_ADDR2);

					String LOC_CITY = item.get(38);
					System.out.println("LOC_CITY: " + LOC_CITY);

					String LOC_STATE = item.get(39);
					System.out.println("LOC_STATE: " + LOC_STATE);

					String LOC_COUNTRY = item.get(40);
					System.out.println("LOC_COUNTRY: " + LOC_COUNTRY);

					String LOC_POSTAL_CODE = item.get(41);
					System.out.println("LOC_POSTAL_CODE: " + LOC_POSTAL_CODE);

					String USER_CONT_PERSON = item.get(42);
					System.out.println("USER_CONT_PERSON: " + USER_CONT_PERSON);

					String CONT_PERSON_NO = item.get(43);
					System.out.println("CONT_PERSON_NO: " + CONT_PERSON_NO);

					BigDecimal total_rwa2 = new BigDecimal(CONT_PERSON_NO.trim());
					System.out.println("Parsed CONT_PERSON_NO: " + total_rwa2);

					String PASSWORD = item.get(44);
					System.out.println("PASSWORD: " + PASSWORD);

					String LIFE_OF_PW = item.get(45);
					System.out.println("LIFE_OF_PW: " + LIFE_OF_PW);

					BigDecimal total_rwa3 = new BigDecimal(LIFE_OF_PW.trim());
					System.out.println("Parsed LIFE_OF_PW: " + total_rwa3);

					String ACCT_EXPY_DATE = item.get(46);
					System.out.println("ACCT_EXPY_DATE: " + ACCT_EXPY_DATE);

					Date dates2 = inputFormat.parse(ACCT_EXPY_DATE);
					String formattedDatesss1 = outputFormat.format(dates2);
					System.out.println("Formatted ACCT_EXPY_DATE: " + formattedDatesss1);

					String PW_EXPY_DATE = item.get(47);
					System.out.println("PW_EXPY_DATE: " + PW_EXPY_DATE);

					Date dates3 = inputFormat.parse(PW_EXPY_DATE);
					String formattedDatesss2 = outputFormat.format(dates3);
					System.out.println("Formatted PW_EXPY_DATE: " + formattedDatesss2);

					String USER_REMARKS = item.get(48);
					System.out.println("USER_REMARKS: " + USER_REMARKS);

					String DISABLE_FLG = item.get(49);
					System.out.println("DISABLE_FLG: " + DISABLE_FLG);

					String DIS_START_DATE = item.get(50);
					System.out.println("DIS_START_DATE: " + DIS_START_DATE);
					Date dates4=null;
					if (DIS_START_DATE != null && !DIS_START_DATE.isEmpty()) {
					     dates4 = inputFormat.parse(DIS_START_DATE);
					    String formattedDatesss3 = outputFormat.format(dates4);
					    System.out.println("Formatted DIS_START_DATE: " + formattedDatesss3);
					} else {
					    System.out.println("Formatted DIS_START_DATE: null");
					}



					String DIS_END_DATE = item.get(51);
					System.out.println("DIS_END_DATE: " + DIS_END_DATE);
					Date dates5=null;
					if (DIS_END_DATE != null && !DIS_END_DATE.isEmpty()) {
						dates5 = inputFormat.parse(DIS_END_DATE);
					    String formattedDatesss5 = outputFormat.format(dates5);
					    System.out.println("Formatted DIS_START_DATE: " + formattedDatesss5);
					} else {
					    System.out.println("Formatted DIS_START_DATE: null");
					}

					String LOGIN_LOW = item.get(52);
					System.out.println("LOGIN_LOW: " + LOGIN_LOW);

					String LOGIN_HIGH = item.get(53);
					System.out.println("LOGIN_HIGH: " + LOGIN_HIGH);

					String ENTRY_USER = item.get(54);
					System.out.println("ENTRY_USER: " + ENTRY_USER);

					String MODIFY_USER = item.get(55);
					System.out.println("MODIFY_USER: " + MODIFY_USER);

					String VERIFY_USER = item.get(56);
					System.out.println("VERIFY_USER: " + VERIFY_USER);

					String ENTRY_TIME = item.get(57);
					System.out.println("ENTRY_TIME: " + ENTRY_TIME);

					Date dates6 = inputFormat.parse(ENTRY_TIME);
					String formattedDatesss6 = outputFormat.format(dates6);
					System.out.println("Formatted ENTRY_TIME: " + formattedDatesss6);

					String MODIFY_TIME = item.get(58);
					System.out.println("MODIFY_TIME: " + MODIFY_TIME);

					Date dates7 = inputFormat.parse(MODIFY_TIME);
					String formattedDatesss7 = outputFormat.format(dates7);
					System.out.println("Formatted MODIFY_TIME: " + formattedDatesss7);

					String VERIFY_TIME = item.get(59);
					System.out.println("VERIFY_TIME: " + VERIFY_TIME);

					Date dates8 = inputFormat.parse(VERIFY_TIME);
					String formattedDatesss8 = outputFormat.format(dates8);
					System.out.println("Formatted VERIFY_TIME: " + formattedDatesss8);

					String DEL_FLG = item.get(60);
					System.out.println("DEL_FLG: " + DEL_FLG);

					String ENTITY_FLG = item.get(61);
					System.out.println("ENTITY_FLG: " + ENTITY_FLG);

					String MODIFY_FLG = item.get(62);
					System.out.println("MODIFY_FLG: " + MODIFY_FLG);

					String FIRST_NAME = item.get(63);
					System.out.println("FIRST_NAME: " + FIRST_NAME);

					String MIDDLE_NAME = item.get(64);
					System.out.println("MIDDLE_NAME: " + MIDDLE_NAME);

					String LAST_NAME = item.get(65);
					System.out.println("LAST_NAME: " + LAST_NAME);

					String SHORT_NAME = item.get(66);
					System.out.println("SHORT_NAME: " + SHORT_NAME);

					String VIRTUAL_FLG = item.get(67);
					System.out.println("VIRTUAL_FLG: " + VIRTUAL_FLG);

					String LOGIN_STATUS = item.get(68);
					System.out.println("LOGIN_STATUS: " + LOGIN_STATUS);

					String CHANNEL_ID = item.get(69);
					System.out.println("CHANNEL_ID: " + CHANNEL_ID);

					String LOCKED_FLG = item.get(70);
					System.out.println("LOCKED_FLG: " + LOCKED_FLG);

					String SESSION_ID = item.get(71);
					System.out.println("SESSION_ID: " + SESSION_ID);

					String IMEI = item.get(72);
					System.out.println("IMEI: " + IMEI);

					String DOR = item.get(73);
					System.out.println("DOR: " + DOR);
					Date dates9 = null;
					Date formattedDatesss9 = null;
					
					if (DOR != null && !DOR.isEmpty()) {
					    try {
					    	dates9 = inputFormat.parse(DOR);
						    String formattedDatesss5 = outputFormat.format(dates9);
		
					        formattedDatesss9 = inputFormat.parse(DOR);
					        System.out.println("Formatted DIS_START_DATE: " + dates9);
					    } catch (ParseException e) {
					        e.printStackTrace();
					    }
					} else {
					    System.out.println("Formatted DIS_START_DATE: null");
					}



					String IFSC = item.get(74);
					System.out.println("IFSC: " + IFSC);

					String BANK = item.get(75);
					System.out.println("BANK: " + BANK);

					String BANK_ACT_NO = item.get(76);
					String banks=null;
					if (BANK_ACT_NO == null || BANK_ACT_NO.isEmpty()) {
						banks=BANK_ACT_NO;
					    System.out.println("BANK_ACT_NO: null"+BANK_ACT_NO);
					} else {
					    System.out.println("BANK_ACT_NO: " + BANK_ACT_NO);
					}


					
























PO.setCategory(CATEGORY);
					PO.setResource_id(RESOURCE_ID);
					PO.setResource_name(RESOURCE_NAME);
					PO.setEmployee_ref(EMPLOYEE_REF);
					PO.setEmployee_id(EMPLOYEE_ID);
					PO.setDoj(dates);
					PO.setDob(dates1);
					PO.setGroups(GROUPS);
					PO.setTeam(TEAM);
					PO.setReport_mgr_id(REPORT_MGR_ID);
					PO.setReport_mgr_name(REPORT_MGR_NAME);
					PO.setDesign(DESIGN);
					PO.setRole(ROLE);
					PO.setQual(QUAL);
					PO.setAddl_qual(ADDL_QUAL);
					PO.setSkill_set(SKILL_SET);
					PO.setExpertise(EXPERTISE);
					PO.setPancard(PANCARD);
					PO.setAadhar(AADHAR);
					PO.setPassport(PASSPORT);
					PO.setDriving_license(DRIVING_LICENSE);
					PO.setGender(GENDER);
					PO.setBlood_group(BLOOD_GROUP);
					PO.setMarital_status(MARITAL_STATUS);
					PO.setDependants(total_rwa1);
					
					PO.setMobile(MOBILE);
					PO.setAlt_mobile(ALT_MOBILE);
					PO.setAccess_id(ACCESS_ID);
					PO.setEmail(EMAIL);
					PO.setAddr1(ADDR1);
					PO.setAddr2(ADDR2);
					PO.setCity(CITY);
					PO.setState(STATE);
					PO.setCountry(COUNTRY);
					PO.setPostal_code(POSTAL_CODE);
					PO.setLoc_addr1(LOC_ADDR1);
					PO.setLoc_addr2(LOC_ADDR2);
					PO.setLoc_city(LOC_CITY);
					PO.setLoc_state(LOC_STATE);
					PO.setLoc_country(LOC_COUNTRY);
					PO.setLoc_postal_code(LOC_POSTAL_CODE);
					PO.setUser_cont_person(USER_CONT_PERSON);
					PO.setCont_person_no(total_rwa2);
					PO.setPassword(PASSWORD);
					PO.setLife_of_pw(total_rwa3);
					PO.setAcct_expy_date(dates2);
					PO.setPw_expy_date(dates3);
					PO.setUser_remarks(USER_REMARKS);
					PO.setDisable_flg(DISABLE_FLG);
					PO.setDis_start_date(dates4);
					PO.setDis_end_date(dates5);
					PO.setLogin_low(LOGIN_LOW);
					PO.setLogin_high(LOGIN_HIGH);
					PO.setEntry_user(ENTRY_USER);
					PO.setModify_user(MODIFY_USER);
					PO.setVerify_user(VERIFY_USER);
					PO.setEntry_time(dates6);
					PO.setModify_time(dates7);
					PO.setVerify_time(dates8);
					PO.setDel_flg(DEL_FLG);
					PO.setEntity_flg(ENTITY_FLG);
					PO.setModify_flg(MODIFY_FLG);
					PO.setFirst_name(FIRST_NAME);
					PO.setMiddle_name(MIDDLE_NAME);
					PO.setLast_name(LAST_NAME);
					PO.setShort_name(SHORT_NAME);
					PO.setVirtual_flg(VIRTUAL_FLG);
					PO.setLogin_status(LOGIN_STATUS);
					PO.setChannel_id(CHANNEL_ID);
					PO.setLocked_flg(LOCKED_FLG);
					PO.setSession_id(SESSION_ID);
					PO.setImei(IMEI);
					PO.setDor(dates9);
					PO.setIfsc(IFSC);
					PO.setBank(BANK);
					PO.setBank_act_no(BANK_ACT_NO);
					
					
					

					
									//	System.out.println(	PO.setBank_act_no(BANK_ACT_NO));

						
						
				
					assosiate_Profile_Repo.save(PO);
				

						msg = "Excel Data Uploaded Successfully";
					}
				}
			 catch (Exception e) {
				e.printStackTrace();
				msg = "File has not been successfully uploaded";
			}
		}
		return msg;

	}

	private static String formatLakh(double d) {
		String s = String.format(Locale.UK, "%1.2f", Math.abs(d));
		s = s.replaceAll("(.+)(...\\...)", "$1,$2");
		while (s.matches("\\d{3,},.+")) {
			s = s.replaceAll("(\\d+)(\\d{2},.+)", "$1,$2");
		}
		return d < 0 ? ("-" + s) : s;
	}

	private boolean isRowEmpty(Row row) {
		boolean isEmpty = true;
		DataFormatter dataFormatter = new DataFormatter();

		if (row != null) {
			for (Cell cell : row) {
				if (dataFormatter.formatCellValue(cell).trim().length() > 0) {
					isEmpty = false;
					break;
				}
			}
		}
		return isEmpty;
	}
	
	
	
	@Autowired
	GstoverseasRepo gstoverseasRepo;
	
	public String Uploadgstservicetwo(String screenId, MultipartFile file, String userid, Gstoverseas Gstoverseas )
			throws SQLException, FileNotFoundException, IOException,NullPointerException {
		System.out.println("Entering third Service Succesfully of GST EXCEL UPLOAD");
		//String datePattern = "dd-MM-yyyy";  // Adjust this pattern based on your actual date format

        // Create a SimpleDateFormat object for parsing
		 SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yy");
		    SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yy");

		String fileName = file.getOriginalFilename();

		String fileExt = "";
		String msg = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			fileExt = fileName.substring(i + 1);
		}

	if (fileExt.equals("xlsx") || fileExt.equals("xls")) {

			try {
				Workbook workbook = WorkbookFactory.create(file.getInputStream());

				List<HashMap<Integer, String>> mapList = new ArrayList<HashMap<Integer, String>>();
				for (Sheet s : workbook) {
					for (Row r : s) {

						if (!isRowEmpty(r)) {
							if (r.getRowNum() == 0) {
								continue;
							}

							HashMap<Integer, String> map = new HashMap<>();

							for (int j = 0; j < 100; j++) {

								Cell cell = r.getCell(j);
								DataFormatter formatter = new DataFormatter();
								String text = formatter.formatCellValue(cell);
								map.put(j, text);
							}
							mapList.add(map);

						}

					}

				}

				for (HashMap<Integer, String> item : mapList) {
					
					Gstoverseas PO = new Gstoverseas();
					
					
					
					/* String ORGANISATION = item.get(0); */
					
					//System.out.println("ORGANISATION" +ORGANISATION);
					
					String trandate = item.get(1);
					System.out.println("DOJ: " + trandate); //trandate
					
					String inv_no = item.get(2);
					System.out.println("inv_no: " + inv_no);
					

					Date trandates = inputFormat.parse(trandate);
					String formattedDatesss = outputFormat.format(trandates);
					System.out.println("Formatted DOJ: " + formattedDatesss);

					String tran_and_parttran= item.get(2);
					System.out.println("tran_and_parttran: " + tran_and_parttran); //invoicenumber
					String client = item.get(3);
					System.out.println("client: " + client);
					
					String description = item.get(4);
					System.out.println("description: " + description);
					
					String employee = item.get(5);
					System.out.println("employee: " + employee);
					
					String start_date = item.get(6);
					System.out.println("DOJ: " + start_date);

					Date start_dates = inputFormat.parse(start_date);
					String formattedDates = outputFormat.format(start_dates);
					System.out.println("Formatted DOJ: " + formattedDates);//start date
					
					String end_date = item.get(7);
					System.out.println("DOJ: " + end_date);

					Date end_dates = inputFormat.parse(end_date);
					String formattedDatesend = outputFormat.format(end_dates);
					System.out.println("Formatted DOJ: " + formattedDatesend);//start date
					
					String inrcurrecncy=item.get(8);
					System.out.println("inr"+inrcurrecncy);
					
					String usd = item.get(9);
					System.out.println("usd: " + usd);//usd
					
					
					  BigDecimal usds=null;

					 String cleanedUsd = usd.replaceAll("[^0-9.]", "");

				        try {
				            // Convert the cleaned string to BigDecimal
				           usds = new BigDecimal(cleanedUsd);
				            System.out.println("usds: " + usds);
				        } catch (NumberFormatException e) {
				            e.printStackTrace();
				            System.out.println("Failed to convert string to BigDecimal: " + cleanedUsd);
				        }
				        

						String details = item.get(9);
						System.out.println("details: " + details);//usd
						
						
						  BigDecimal det=null;

						 String cleaneddet = details.replaceAll("[^0-9.]", "");

					        try {
					            // Convert the cleaned string to BigDecimal
					        	det = new BigDecimal(cleaneddet);
					            System.out.println("det: " + det);
					        } catch (NumberFormatException e) {
					            e.printStackTrace();
					            System.out.println("Failed to convert string to BigDecimal: " + cleaneddet);
					        }




						String rate = item.get(11);
						System.out.println("rate: " + rate);//usd
					    BigDecimal rates = new BigDecimal(rate.trim());
						System.out.println("rate: " + rates);
						
						
						
						/*
						 * BigDecimal inrs=null; String inr = item.get(12);
						 * 
						 * System.out.println("inr: " + inr);
						 * 
						 * try { // Check if the string is null or empty if (inr == null ||
						 * inr.trim().isEmpty()) { throw new
						 * IllegalArgumentException("The INR value is null or empty."); }
						 * 
						 * // Remove commas from the string String cleanedInr = inr.trim().replace(",",
						 * "");
						 * 
						 * // Convert the cleaned string to BigDecimal inrs = new
						 * BigDecimal(cleanedInr); System.out.println("inrs: " + inrs);
						 * 
						 * } catch (NumberFormatException e) {
						 * System.out.println("Invalid number format for INR: " + inr); } catch
						 * (IllegalArgumentException e) { System.out.println(e.getMessage()); }
						 */
							
							BigDecimal inrs = null;
							String inr = item.get(12);
							try {
							    // Check if USD and rate strings are null or empty
							    if (usd == null || usd.trim().isEmpty()) {
							        throw new IllegalArgumentException("The USD value is null or empty.");
							    }
							    if (rate == null || rate.trim().isEmpty()) {
							        throw new IllegalArgumentException("The rate value is null or empty.");
							    }

							    // Remove any unwanted characters from USD and rate strings (e.g., commas, etc.)
							    String cleanedUsds = usd.trim().replaceAll("[^0-9.]", "");
							    String cleanedRate = rate.trim().replaceAll("[^0-9.]", "");

							    // Convert cleaned strings to BigDecimal
							    BigDecimal usdss = new BigDecimal(cleanedUsd);
							    BigDecimal ratess = new BigDecimal(cleanedRate);

							    System.out.println("usds: " + usds);
							    System.out.println("rates: " + rates);

							    // Perform the multiplication of USD and rate
							    inrs = usds.multiply(rates);
							    System.out.println("inrs (USD * rate): " + inrs);

							} catch (NumberFormatException e) {
							    System.out.println("Invalid number format for USD or rate.");
							    e.printStackTrace();
							} catch (IllegalArgumentException e) {
							    System.out.println(e.getMessage());
							}
						
					String parttrantype = item.get(14);
					System.out.println("parttrantyperrrrrrrrrrrrrrrrrrrrrrrr: " + parttrantype);//client

					
					
					
					
					
					

					




					


PO.setTran_date(trandates);
					PO.setTran_and_parttran(tran_and_parttran);
					PO.setPart_tran_type("Recevied");
					PO.setInv_no(inv_no);
					PO.setClient(client);
					PO.setDescription(description);
					PO.setEmployee(employee);
					PO.setStart_date(start_dates);
					PO.setEnd_date(end_dates);
					PO.setTran_crncy(inrcurrecncy);
					
					PO.setInv_amt_fcy(usds);
					PO.setRemit_amt_fcy(det);
					PO.setRate(rates);
					PO.setInv_amt_inr(inrs);
					PO.setTran_and_parttran(parttrantype);
					
					PO.setUniqueid(tran_and_parttran+parttrantype+trandates+inv_no);
					String[] parts = parttrantype.split("/");

					// Get the value before "/"
					String tranId = parts[0];

					// Now you can set it in your PO object
					PO.setTran_id(tranId);
					
					
					String[] parts1 = parttrantype.split("/");

				
					

					// Check if the string contains "/" and has a part after it
					if (parts1.length > 1) {
					 
					    String parttranId = parts1[1];

					
					    BigDecimal parttranIdBigDecimal = new BigDecimal(parttranId);

					    // Set it in your PO object
					    PO.setPart_tran_id(parttranIdBigDecimal);

					    System.out.println("parttranIdBigDecimal: " + parttranIdBigDecimal); // This will print "09"
					} else {
					  
					    System.out.println("The string does not contain a '/' or there's no value after '/'.");
					   
					}
					

					
									
						
						
				
					gstoverseasRepo.save(PO);
				

						msg = "Excel Data Uploaded Successfully";
					}
				}
			 catch (Exception e) {
				e.printStackTrace();
				msg = "File has not been successfully uploaded";
			}
		}
		return msg;

	}

	}


	



