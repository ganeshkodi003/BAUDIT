package com.bornfire.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.persistence.Id;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bornfire.entities.InvoiceMaster;
import com.bornfire.entities.InvoiceMasterRep;
import com.bornfire.entities.IssueTracker;
import com.bornfire.entities.IssueTrackerRep;
import com.bornfire.entities.PlacementMaintenance;
import com.bornfire.entities.PlacementMaintenanceRep;

@Service
public class ExcelUploadService {

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	IssueTrackerRep issueTrackerRep;
	@Autowired
	PlacementMaintenanceRep placementMaintenanceRep;
	@Autowired
	InvoiceMasterRep invoiceMasterRep;
	
	@Autowired
	checkservice checkservice;
	
	
	@Autowired
	InvoicesubmitService invoicesubmitService;
	@SuppressWarnings("deprecation")
	public List<IssueTracker> UploadIssue(String screenId, MultipartFile file, String userid, IssueTracker issuetraker)
			throws SQLException, FileNotFoundException, IOException {

		String fileName = file.getOriginalFilename();

		String fileExt = "";
		String msg = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			fileExt = fileName.substring(i + 1);
		}

		List<IssueTracker> result = new ArrayList<IssueTracker>();
		if (fileExt.equals("xlsx") || fileExt.equals("xls")) {

			if (fileName.contains("IssueTracker")) {

				try {

					/*
					 * Workbook workbook =
					 * StreamingReader.builder().rowCacheSize(100).bufferSize(4096)
					 * .open(file.getInputStream());
					 */
					Workbook workbook = WorkbookFactory.create(file.getInputStream());

					List<HashMap<Integer, String>> mapList = new ArrayList<>();
					for (Sheet s : workbook) {
						for (Row r : s) {

							if (!isRowEmpty(r)) {
								if (r.getRowNum() == 0) {
									continue;
								}

								String val = null;

								HashMap<Integer, String> map = new HashMap<>();

								for (int j = 0; j < 28; j++) {

									Cell cell = r.getCell(j);
									if (cell.getCellType() == 0) {
										int val1 = (int) cell.getNumericCellValue();
										val = String.valueOf(val1);
										map.put(j, val);
									} /*
										 * else if(cell.getCellType()== 1) { Date val1 = cell.getDateCellValue();
										 * map.put(j, val); }
										 */ else {
										val = cell.getStringCellValue();
										map.put(j, val);
									}
								}

								mapList.add(map);

							}

						}

					}
					for (HashMap<Integer, String> item : mapList) {
						IssueTracker issue = new IssueTracker();

						Optional<IssueTracker> issueList = issueTrackerRep.findById(item.get(0));
						if (item.get(0) == "") {
							msg = "!--------------------------Excel Sheet Empty--------------------------------!";
							System.out.println(msg);
						} else if (issueList.isPresent()) {
							msg = "!--------------------------------Issue Serial Already Exist--------------------------------------!";
							System.out.println(msg);
						} else {
							issue.setSrl_no(item.get(0));
							issue.setCategory(item.get(1));
							issue.setGroups(item.get(2));
							issue.setProduct(item.get(3));
							issue.setModule(item.get(4));
							issue.setScreen(item.get(5));
							issue.setOperation(item.get(6));
							issue.setOper_desc(item.get(7));
							issue.setIssue_ref_no(item.get(8));
							String sDate1 = item.get(9);
							Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
							issue.setDate_of_issue(date1);
							issue.setRpt_by(item.get(10));
							issue.setApr_by(item.get(11));
							issue.setNat_of_issue(item.get(12));
							issue.setIssue_details(item.get(13));
							issue.setIssue_severity(item.get(14));
							issue.setIssue_rmks(item.get(15));
							issue.setAssign_to(item.get(16));
							String sDate2 = item.get(17);
							Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);

							issue.setAssign_date(date2);
							String fix = item.get(18);
							BigDecimal fix1 = new BigDecimal(fix);
							issue.setFix_period(fix1);
							String sDate3 = item.get(19);
							Date date3 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate3);
							issue.setDel_date(date3);
							issue.setFix_details(item.get(20));
							String sDate4 = item.get(21);
							Date date4 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate4);
							issue.setDate_of_fix(date4);
							issue.setTest_by(item.get(22));
							String sDate5 = item.get(23);
							Date date5 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate5);
							issue.setTest_date(date5);
							issue.setTest_results(item.get(24));
							issue.setIssue_status(item.get(25));
							String turn = item.get(26);
							BigDecimal turn1 = new BigDecimal(turn);
							issue.setTat_per(turn1);
							issue.setFinal_cls(item.get(27));
							issue.setDel_flg("N");

							result.add(issue);

							issueTrackerRep.save(issue);

							msg = "!-------------------------------Excel Data Uploaded Successfully--------------------------------------!";
							System.out.println(msg);

						}

					}

					return result;

				} catch (Exception e) {
					e.printStackTrace();
					throw new CustomException("File has not been successfully uploaded");
				}
			} else {
				throw new CustomException("Invalid File Name");
			}
		}
		return result;
	}

	public String UploadPO(String screenId, MultipartFile file, String userid,
			PlacementMaintenance placementMaintenance) throws SQLException, FileNotFoundException, IOException {

		String fileName = file.getOriginalFilename();

		String fileExt = "";
		String msg = "";

		int i = fileName.lastIndexOf('.');
		if (i > 0) {
			fileExt = fileName.substring(i + 1);
		}

		ArrayList<PlacementMaintenance> result = new ArrayList<PlacementMaintenance>();
		if (fileExt.equals("xlsx") || fileExt.equals("xls")) {

			// if (fileName.contains("PO_Master")) {

			try {

				/*
				 * Workbook workbook =
				 * StreamingReader.builder().rowCacheSize(100).bufferSize(4096)
				 * .open(file.getInputStream());
				 */
				Workbook workbook = WorkbookFactory.create(file.getInputStream());

				List<HashMap<Integer, String>> mapList = new ArrayList<>();
				for (Sheet s : workbook) {
					for (Row r : s) {

						if (!isRowEmpty(r)) {
							if (r.getRowNum() == 0) {
								continue;
							}

							String val = null;

							HashMap<Integer, String> map = new HashMap<>();

							for (int j = 0; j < 50; j++) {

								Cell cell = r.getCell(j);
								DataFormatter formatter = new DataFormatter();
								String text = formatter.formatCellValue(cell);
								map.put(j, text);
								/*
								 * if (cell.getCellComment()==CellType.NUMERIC) { int val1 =
								 * (int)cell.getNumericCellValue(); val = String.valueOf(val1); map.put(j, val);
								 * }
								 * 
								 * else if(cell.getCellType() == CellType.STRING){ val =
								 * cell.getStringCellValue(); map.put(j, val);
								 * 
								 * }
								 */
							}
							mapList.add(map);

						}

					}

				}
				
				for (HashMap<Integer, String> item : mapList) {
					PlacementMaintenance PO = new PlacementMaintenance();
					//InvoiceMaster IM=new InvoiceMaster();
					String sDat2 = item.get(3);
					String p;
					System.out.println(sDat2);
					System.out.println(sDat2.charAt(2));

					if (sDat2.charAt(2) == '/') {
					    System.out.println(sDat2.substring(2, 3));
					    String g[] = sDat2.split("/");

					    for (int i1 = 0; i1 < g.length; i1++) {
					        System.out.println("Element at index " + i1 + ": " + g[i1]);
					    }

					    if (g[2].length() == 2) {
					        p = g[1] + "-" + g[0] + "-20" + g[2];
					        System.out.println("Formatted date: " + p);
					    } else {
					        p = g[1] + "-" + g[0] + "-" + g[2];
					        System.out.println("Formatted date: " + p);
					    }
					} else {
					    p = sDat2;
					    System.out.println("::::::::::" + p);
					}

					try {
					    // Remove any extra double quotes from p
					    p = p.replace("\"", "");
					    System.out.println("Cleaned date string: " + p);
					    
					    SimpleDateFormat formatDate = new SimpleDateFormat("ddMMyyyy");
					    Date dat2 = new SimpleDateFormat("dd-MM-yyyy").parse(p);
					    String m = formatDate.format(dat2);
					    System.out.println(m);
					} catch (ParseException e) {
					    e.printStackTrace();
					    msg = "PO Date Not Valid";
					    return msg;
					}
					String sDate3 = item.get(21);
					if (!sDate3.isEmpty()) {
					    try {
					        // Remove any double quotes from sDate3
					        sDate3 = sDate3.replace("\"", "");
					        System.out.println("Cleaned date string: " + sDate3);
					        
					        // Now parse the cleaned date string
					        Date date3 = new SimpleDateFormat("dd-MM-yyyy").parse(sDate3);
					        
					    } catch (ParseException e) {
					        e.printStackTrace();
					        msg = "PO Delivery Date Not Valid";
					        return msg;
					    }
				}else {
				}
				/*
				 * String sDate4 = item.get(35); if(item.get(35)!="") { try { Date date4 = new
				 * SimpleDateFormat("dd-MM-yyyy").parse(sDate4); } catch (ParseException e) {
				 * e.printStackTrace(); msg="GRN Date Not Valid"; return msg; } }else {
				 * 
				 * }
				 */
					 SimpleDateFormat formatdate = new SimpleDateFormat("ddMMyyyy");
					 Date date3 = new SimpleDateFormat("dd-MM-yyyy").parse(sDate3);
						String m = formatdate.format(date3);
						
						
					Optional<PlacementMaintenance> POList = placementMaintenanceRep
							.findById(item.get(2) + item.get(14) + m);
					
					if (item.get(0) == "") {
						msg = "Excel Sheet Empty";
						System.out.println(msg);
					} else if (POList.isPresent()) {
						msg = "PO Already Exist";
						System.out.println(msg);
					} else {
						DecimalFormat numformate = new DecimalFormat("");
						BigDecimal PlacementSrlNo = (BigDecimal) sessionFactory.getCurrentSession()
								.createNativeQuery("SELECT PM_SRL_NO.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();
						String pmsrlno = numformate.format(PlacementSrlNo);
						
						PO.setSrl_no(pmsrlno);
						
						PO.setPo_id(item.get(2) + item.get(14) + m);
						
						PO.setVendor(item.get(0));
						//IM.setVendor(item.get(0));
						PO.setLocation(item.get(1));
						if(item.get(2).length()!=10){
							PO.setPo_no(item.get(2));
							PO.setFlag('N');
							PO.setMessage("Po No Must Be 10 Digit");
						}else {
						PO.setPo_no(item.get(2));
						PO.setMessage("SUCCESS");
						PO.setFlag('Y');
					}
						String sDate2 = item.get(3);
						if(sDate2!="") {
						Date date2 = new SimpleDateFormat("dd-MM-yyyy").parse(p);
						PO.setPo_date(date2);
						}else {
							
						}
						PO.setUnit_loc(item.get(11));
						PO.setProj_mgr(item.get(6));
						PO.setPm_email(item.get(7));
						PO.setGstin(item.get(12));
						PO.setNo_of_items(item.get(15));
						PO.setEmp_name(item.get(13));
						PO.setEmp_id(item.get(14));
						PO.setPo_item_no(item.get(17));
						PO.setPo_qty(item.get(18));
						
						/*
						 * int i1= Double.valueOf(item.get(19)).intValue(); System.out.println(i1);
						 * DecimalFormat f = new DecimalFormat("#,##,##0.00");
						 */
						
						
						System.out.println(">>>>>>>>>>");
						
						System.out.println("??????????????"+item.get(19));
						
						
						/*
						 * String P=item.get(19); System.out.println(new BigDecimal(P)); double i1=
						 * Double.valueOf(P); System.out.println(); DecimalFormat f = new
						 * DecimalFormat("#,##,##0.00"); System.out.println(f.format(i1));
						 */
						System.out.println(formatLakh(Double.valueOf(item.get(19))));
						//System.out.println();
						PO.setPo_rate_inr(formatLakh(Double.valueOf(item.get(19))));
						PO.setPo_amt_inr(item.get(20));
						PO.setExtn_flg(item.get(4));
						
						String eDate2 = item.get(5);
						if(eDate2!="") {
						Date edate = new SimpleDateFormat("dd-MM-yyyy").parse(eDate2);
						PO.setExtn_date(edate);
						}else {
							
						}
						
						if(sDate3!="") {
						Date date31 = new SimpleDateFormat("dd-MM-yyyy").parse(sDate3);
						PO.setPo_delivery_date(date31);
						}else {
							
						}
						System.out.println("''''''''''''''''''"+item.get(20));
						System.out.println();
						
						// PO.setSp(item.get(22));
						 //System.out.println(formatLakh(Double.valueOf(item.get(23))));
						 
						// PO.setSp_rate(formatLakh(Double.valueOf(item.get(23))));
						// System.out.println(formatLakh(Double.valueOf(item.get(24))));
						// System.out.println(item.get(22));
						// System.out.println(item.get(23));
						 if(item.get(22)==null ||  (item.get(22)=="")) {
							
						} else {
							 PO.setSp(item.get(22)); 
							 System.out.println("---------------"+item.get(22));
						}
						 
						 
						 if(item.get(23)==null ||  item.get(23)=="" ) {
							
							 } else {
								 System.out.println("))))))))))"+formatLakh(Double.valueOf(item.get(23))));
								 PO.setSp_rate(formatLakh(Double.valueOf(item.get(23))));
							 }
						 PO.setRate_mode(item.get(24)); 
						 
						 System.out.println(item.get(25)); 
						 if (item.get(25) == null || item.get(25)=="") {
						 
						 }else { 
							 System.out.println("(((((((((("+formatLakh(Double.valueOf(item.get(25))));
							 PO.setFixed_amt(formatLakh(Double.valueOf(item.get(25))));
							 }
						 if(item.get(26)==null || item.get(26)=="") { 
							
						 }else {
							 PO.setPercent(item.get(26));
							 }
						
						PO.setInv_tot_amt(item.get(31));
						PO.setPo_month(item.get(33));
						PO.setBill_total_amt(item.get(16));
						PO.setCin(item.get(34));
						PO.setPan(item.get(35));
						PO.setVendor1(item.get(37));
						PO.setGstin_1(item.get(36));
						PO.setHsn(item.get(38));
						PO.setBank_name(item.get(39));
						PO.setAcct_type(item.get(40));
						PO.setAcct_no(item.get(41));
						PO.setAcct_name(item.get(42));
						PO.setIfsc_code(item.get(43));
						PO.setSwift_code(item.get(44));
						//IM.setInv_no("4356362377");
						result.add(PO);
						Calendar cal = Calendar.getInstance();
						SimpleDateFormat login = new SimpleDateFormat("dd-MMM-yy");
						String date = login.format(cal.getTime());
                        PO.setUpload_date(date);
                        
						placementMaintenanceRep.save(PO);
						//invoiceMasterRep.save(IM);
						 
						//checkservice.extsubmit(PO);
							
						
						
						
						
					//	System.out.println(datainvo.getPo_no());
						

                       
                        
						//invoiceMasterRep.save(datainvo);

						msg = "Excel Data Uploaded Successfully";

		
					}
					
				}
				
				
				String msg2 = invoicesubmitService.checksub(mapList);
				System.out.println(msg2);
				
if(msg2=="success") {
	System.out.println("hihihihihigggggggggggggggggggggggg");
	return msg;
}
else {
	return "not uploaded";
}
				

			} catch (Exception e) {
				e.printStackTrace();
				msg = "File has not been successfully uploaded";
			}

			/*
			 * } else { msg="Invalid File Name"; }
			 */
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
	
	private static boolean isRowEmpty(Row row) {
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
	
	
	
	
// upload remittence //
	
	
	public void saveInvoiceDetails(String adviceNumber, Date adviceDate, String invoiceNumber) {
	    // Fetch the existing record based on the invoice number
	    PlacementMaintenance existing = placementMaintenanceRep.getAlist(invoiceNumber);
	    
	    // Check if the record exists in the database
	    if (existing != null) {
	        System.out.println("Updating record for Invoice Number: " + invoiceNumber);

	        // Update only the necessary fields
	        existing.setRemit_date(adviceDate);
	        existing.setRemitence_reference(adviceNumber);

	        // Save the updated record without deleting other values
	        placementMaintenanceRep.save(existing);
	    } else {
	        System.out.println("No existing record found for Invoice Number: " + invoiceNumber);
	    }
	}
	
	
	
	public String processPDF(MultipartFile file) throws IOException {
	    PdfReader pdfReader = new PdfReader(file.getInputStream());
	    StringBuilder textBuilder = new StringBuilder();

	    // Extracting text from all pages of the PDF
	    for (int i = 1; i <= pdfReader.getNumberOfPages(); i++) {
	        textBuilder.append(PdfTextExtractor.getTextFromPage(pdfReader, i));
	    }
	    pdfReader.close();

	    String pdfText = textBuilder.toString();
	    System.out.println(pdfText);
System.out.println("----------------------------------------------------------------------------------------------------------------------");
	    // Extracting required fields using regex
	    String adviceNumber = extractValue(pdfText, "Advice Number", "\\d+/\\d+");
	    System.out.println(adviceNumber+"adviceNumber");
	    
	    String adviceDate = extractValue(pdfText, "Advice Date", "\\d{2} \\w+ \\d{4}");
	    System.out.println(adviceDate+"adviceDate");
	   String formattedDateadvicestring = convertDateToRequiredFormat(adviceDate);
	   System.out.println(formattedDateadvicestring+"formattedDateadvicestring");
	    Date formattedDateadviceDate=convertStringToDate(formattedDateadvicestring);
	    System.out.println(formattedDateadviceDate+"formattedDateadviceDate");
	    String invoiceNumber = extractInvoiceNumber(pdfText);
	    System.out.println(invoiceNumber + " invoiceNumber");
	    
	    List<String> invoiceNumbers = extractAllInvoiceNumbers(pdfText);
	    System.out.println("Extracted  ALL Invoice Numbers: " + invoiceNumbers);
	    String extractedNumber = extractTenDigitNumber(pdfText);
	    System.out.println("Extracted 10-digit Number: " + extractedNumber);
	   // ExtractNumbers extractNumbers = new ExtractNumbers();
        List<String> extractedNumbers = extractTenDigitNumbers(pdfText);
        System.out.println("Extracted 10-digit Numbers: " + extractedNumbers);


	    String formattedDate=null;
	    if (extractedNumber != null && !extractedNumber.isEmpty()) {
	        // Extract the 10 characters before the extracted number
	       String  valueBeforeNumber = extractTenCharactersBeforeNumber(pdfText, extractedNumber);
	        System.out.println("10 Characters before the extracted number: " + valueBeforeNumber);
	        
	         formattedDate = convertDateToRequiredFormat(valueBeforeNumber);
	        System.out.println("Formatted Date: " + formattedDate);
	    } else {
	        System.out.println("No 10-digit number starting with '6' found in the PDF.");
	    }

	    
	    Date in_Date=null;
        in_Date = convertStringToDate(formattedDate);
        System.out.println("in_Date"+in_Date);
	    
       
	    
	
	   
	    	    
	    // Fetching the existing record from the database
     // Retrieve all records that match the invoice numbers and date
     // Retrieve all records that match the invoice numbers and date
        List<PlacementMaintenance> existing = placementMaintenanceRep.findByInvoiceNumbersAndDate(invoiceNumbers, formattedDate);

        // Create a HashMap to store records keyed by their INV_NO (Invoice Number)
        Map<String, PlacementMaintenance> recordsMap = new HashMap<>();

        // Map each record by its INV_NO
        for (PlacementMaintenance record : existing) {
            recordsMap.put(record.getInv_no(), record);  // Store by INV_NO as the key
        }

        // Now, iterate over the list of invoiceNumbers
        for (String invoiceNumber1 : invoiceNumbers) {
            // Check if the record with the specified invoice number exists in the map
            if (recordsMap.containsKey(invoiceNumber1)) {
                // Get the existing record from the map
                PlacementMaintenance existingRecord = recordsMap.get(invoiceNumber1);
                
                // Retrieve the INV_NO and INV_DATE of the existing record
                String inv_number_exist = existingRecord.getInv_no();
                Date inv_date_exist = existingRecord.getInv_date();
                String  formattedDate_exist = convertDateTstring(inv_date_exist);

                // Check if both the invoice number and date match
                if (invoiceNumber1.equals(inv_number_exist) && formattedDate.equals(formattedDate_exist)) {
                
                    System.out.println("IN Date: " + formattedDate + " inv_date_exist: " + formattedDate_exist);
                    System.out.println("Updating record for Invoice Number: " + invoiceNumber1+"inv_number_exist"+inv_number_exist);
                    if (existingRecord.getRemitence_reference() != null && !existingRecord.getRemitence_reference().isEmpty()) {
                        System.out.println("Remitence reference is already present: " + existingRecord.getRemitence_reference());
                        return "the remitence reference is already present";  // Skip the update if the remitence reference is already present
                    }
                    // Update the fields as necessary
                    existingRecord.setRemit_date(formattedDateadviceDate);
                    existingRecord.setRemitence_reference(adviceNumber);

                    // Print updated values for debugging
                    System.out.println("Updated Remitance Date: " + existingRecord.getRemit_date());
                    System.out.println("Updated Remitence Reference: " + existingRecord.getRemitence_reference());

                    // Save the updated record
             placementMaintenanceRep.save(existingRecord); // Uncomment to save the record
                } else {
                    System.out.println("No matching record found for Invoice Number: " + invoiceNumber1);
                }
            } else {
                System.out.println("No existing record found for Invoice Number: " + invoiceNumber1);
            }
        }
        return "Uploded Successfully";
	}

	private List<String> extractAllInvoiceNumbers(String text) {
	    Set<String> invoiceNumbersSet = new HashSet<>(); // Use a Set to ensure uniqueness
	    String pattern = "BORNFIRE\\d+D\\d+"; // Regex pattern to match "BORNFIRE<digits>D<digits>"
	    Pattern compiledPattern = Pattern.compile(pattern);
	    Matcher matcher = compiledPattern.matcher(text);

	    // Find all matches and add them to the set
	    while (matcher.find()) {
	        String invoiceNumber = matcher.group();

	        // Remove trailing '/' if it exists
	        if (invoiceNumber.endsWith("/")) {
	            invoiceNumber = invoiceNumber.substring(0, invoiceNumber.length() - 1);
	        }

	        // Add the invoice number to the set (duplicates will be ignored)
	        invoiceNumbersSet.add(invoiceNumber);
	    }

	    // Convert the set to a list and return
	    return new ArrayList<>(invoiceNumbersSet);
	}

	// Method to extract values using regex
	private String extractValue(String text, String label, String pattern) {
	    Pattern regex = Pattern.compile(label + "\\s+" + pattern);
	    Matcher matcher = regex.matcher(text);
	    if (matcher.find()) {
	        String[] parts = matcher.group().split(label);
	        return parts[1].trim();
	    }
	    return null;
	}
	private String extractInvoiceNumber(String text) {
	    // Define a regex pattern to find the invoice number of the format "BORNFIRE<digits>D<digits>"
	    String pattern = "BORNFIRE\\d+D\\d+" ;  // Match "*BORNFIRE" followed by digits, "D", more digits, and optionally "/"
	    Pattern compiledPattern = Pattern.compile(pattern);
	    Matcher matcher = compiledPattern.matcher(text);

	    // If a match is found, return it, otherwise return an empty string
	    if (matcher.find()) {
	        String invoiceNumber = matcher.group();  // Returns the first match

	        // Check if the invoice number ends with '/' and remove it
			/*
			 * if (invoiceNumber.endsWith("/")) { invoiceNumber = invoiceNumber.substring(0,
			 * invoiceNumber.length() - 1); // Remove '/' }
			 */

	        return invoiceNumber;  // Return the cleaned invoice number without '/'
	    } else {
	        return "No invoice number found";
	    }
	}





	public List<String> extractTenDigitNumbers(String text) {
        List<String> numbers = new ArrayList<>(); // List to hold the results
        String pattern = "\\b6\\d{9}\\b"; // Pattern to match a 10-digit number starting with '6'
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(text);

        // Find all matches and add them to the list
        while (matcher.find()) {
            numbers.add(matcher.group()); // Add the matched number to the list
        }

        // Return the list of matched numbers
        return numbers;
    }

private String extractTenDigitNumber(String text) {
    String pattern = "\\b6\\d{9}\\b"; // Pattern to match a 10-digit number starting with '6'
    Pattern compiledPattern = Pattern.compile(pattern);
    Matcher matcher = compiledPattern.matcher(text);

    if (matcher.find()) {
        return matcher.group(); // Return the first match
    }
    return "No 10-digit number found starting with '6'";
}


private String extractTenCharactersBeforeNumber(String text, String number) {
    // Find the index of the extracted number in the text
    int numberIndex = text.indexOf(number);
    
    // Check if the number was found and there are enough characters before it
    if (numberIndex != -1 && numberIndex >= 15) {
        // Extract the 10 characters before the extracted number
        return text.substring(numberIndex - 15, numberIndex).trim();
    } else if (numberIndex != -1) {
        // If there are fewer than 10 characters before the number, return whatever is available
        return text.substring(0, numberIndex).trim();
    }
    
    return "No value found before the extracted number.";
}


	// Updated method to convert the string to date
	private Date convertStringToDate(String dateStr) {
	    try {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	        return dateFormat.parse(dateStr);
	    } catch (ParseException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public String convertDateToRequiredFormat(String inputDate) {
	    try {
	        // Define the input date format (e.g., "02 Sep 2024")
	        SimpleDateFormat inputFormat = new SimpleDateFormat("dd MMM yyyy");
	        
	        // Parse the input date string to a Date object
	        Date date = inputFormat.parse(inputDate);
	        
	        // Define the output date format (e.g., "dd-MM-yyyy")
	        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
	        
	        // Convert the Date object to the desired format
	        return outputFormat.format(date);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	 public static String convertDateTstring(Date date) {
	        // Define the date format you want (dd-MM-yyyy in this case)
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	        
	        // Format the date and return it as a string
	        return dateFormat.format(date);
	    }
	 
	 /*---------------------------------------------SPPDF------------------------------------------------------------------------------*/

	public String processPDFSP(MultipartFile file) throws IOException {
		    PdfReader pdfReader = new PdfReader(file.getInputStream());
		    StringBuilder textBuilder = new StringBuilder();

		    // Extracting text from all pages of the PDF
		    for (int i = 1; i <= pdfReader.getNumberOfPages(); i++) {
		        textBuilder.append(PdfTextExtractor.getTextFromPage(pdfReader, i));
		    }
		    pdfReader.close();

		    String pdfText = textBuilder.toString();
		    System.out.println(pdfText);
		    System.out.println("----------------------------------------------------------------------------------------------------------------------");

		    // Check if "Whitestone" is present in the text
		    if (pdfText.contains("Whitestone")) {
		        // StringBuilder to accumulate the results
		        StringBuilder result = new StringBuilder();

		        // Extract Invoice Number
		        String invoiceNo = extractValueinvoice(pdfText, "Invoice:", "WS/\\w+-\\d+/\\d+");
		        System.out.println("Extracted Invoice No: " + invoiceNo);
		        result.append("Invoice No: ").append(invoiceNo).append("\n");

		        // Extract Dated
		        String invoiceDate = extractValue(pdfText, "Dated :", "\\d{2}/\\d{2}/\\d{4}");
		        Date formattedDateadviceDate=convertStringToDate1(invoiceDate);
			    System.out.println(formattedDateadviceDate+"formattedDateadviceDate");
		             System.out.println("Extracted Dated: " + invoiceDate);
		        result.append("Dated: ").append(invoiceDate).append("\n");

		        // Extract text after "BORNFIRE-PO"
		        String numericValue = extractNumericValueAfter(pdfText, "BORNFIRE-PO-");
		        System.out.println("Numeric value after BORNFIRE-PO-: " + numericValue);
		        result.append("Numeric value after BORNFIRE-PO-: ").append(numericValue).append("\n");

		        // Extract table content
		  //      String tableContent = extractTableContent(pdfText);
		    //    System.out.println("Extracted Table Content: " + tableContent);
		      //  result.append("Extracted Table Content: ").append(tableContent).append("\n");

		        // Extract title and name extractTitle
		        StringBuilder titleResult = new StringBuilder();
		        extractTitle(pdfText, titleResult);
		        System.out.println("Extracted Title and Name: \n" + titleResult.toString());
		        result.append(titleResult);
		        // Cleaned PDF text
		   //     String cleanedText = pdfText.replaceAll("\\s+", " ").trim();
		    //    System.out.println("Cleaned PDF Text: " + cleanedText);
		     //   result.append("Cleaned PDF Text: ").append(cleanedText).append("\n");

		        // Extract the month and year
		        StringBuilder monthYearResult = new StringBuilder();
		        extractMonthAndYear(pdfText, monthYearResult);
		        System.out.println("Extracted Month and Year: \n" + monthYearResult.toString());
		        result.append(monthYearResult);
		        
		        
		        StringBuilder amountsResult = new StringBuilder();
		        extractAmounts(pdfText, amountsResult);
		        System.out.println("Extracted Amounts: \n" + amountsResult.toString());
		        result.append(amountsResult);
		        
		     // Initialize the StringBuilder for the extracted amounts
		        StringBuilder AmountsIndependently = new StringBuilder();

		        // Call the method to extract amounts and append to AmountsIndependently
		        extractAmountsIndependently(pdfText, AmountsIndependently);

		        // Print the extracted amounts
		        System.out.println("Extracted Amounts: \n" + AmountsIndependently.toString());

		        // Append the results to the main result
		        result.append(AmountsIndependently);

		        // Extract tax information (IGST, CGST)
		        StringBuilder taxResult = new StringBuilder();
		        extractTaxInfo(pdfText, taxResult);
		        System.out.println("Extracted Tax Information: \n" + taxResult.toString());
		        result.append(taxResult);
		        // Extract the total amount
		        String totalAmount = extractTotalAmount(pdfText);
		        System.out.println("Total Amount: " + totalAmount);
		        result.append("Total Amounttttttttttttttttttttttttttttttttttttt: ").append(totalAmount).append("\n");
		        
		        
		     // Create StringBuilder to collect results
		        StringBuilder igstResult = new StringBuilder();
		        StringBuilder cgstResult = new StringBuilder();

		        // Call methods to extract IGST and CGST tax information
		        extractIGSTTaxInfo(pdfText, igstResult);
		        extractCGSTTaxInfo(pdfText, cgstResult);

		        // Output the results for IGST and CGST
		        System.out.println("Extracted IGST Tax Information: \n" + igstResult.toString());
		        System.out.println("Extracted CGST Tax Information: \n" + cgstResult.toString());

		        
		        // Print the final consolidated result
		        System.out.println("Final Consolidated Resultttttttttttttttttttttttttttttttttttttttttttttttt:\n" + result.toString());
		        String monthYear = monthYearResult.toString();
		     // Fetch the record from the database
		        PlacementMaintenance existing = placementMaintenanceRep.findByInvoiceNumbersAndDate_spinvoice(numericValue, monthYear);

		        System.out.println("monthYearResulttttttttttttttttttttttt" + monthYearResult + " numericValue" + numericValue);

		        // Check if the record exists
		        if (existing != null) {
		            System.out.println("Record found for PO number: " + existing.getPo_no());

		            // Check if the PO number matches
		            if (existing.getPo_no().equals(numericValue)) {
		                
		                // Check if both SP invoice number and date are not null in the existing record
		                if (existing.getSp_inv_no() != null && existing.getSp_inv_date() != null) {
		                    System.out.println("SP Invoice No and SP Invoice Date already present in the database. Not updating.");

		                    // Return a message if invoice details are already present
		                    return "SP INVOICE NO AND SP INVOICE DATE IS ALREADY PRESENT IN THE DATA BASE";
		                } else {
		                    // Update the invoice number and date since they are not present
		                    System.out.println("Updating SP Invoice No and SP Invoice Date...");
				        	System.out.println("invoiceNo"+invoiceNo+"formattedDateadviceDate"+formattedDateadviceDate);
		                    existing.setSp_inv_no(invoiceNo); // Set the new invoice number
		                    existing.setSp_inv_date(formattedDateadviceDate); // Set the new invoice date

		                    // Save the updated entity back to the database
		                    placementMaintenanceRep.save(existing); // Ensure to save changes to the record
		                    System.out.println("Record updated with new SP Invoice No and SP Invoice Date.");
		                    return "Record updated successfully.";
		                }
		            } else {
		                System.out.println("PO Number is not matching in the database.");
		                return "PO NUMBER IS NOT PRESENT IN THE DATABASE";
		            }
		        } else {
		            System.out.println("No record found for PO number: " + numericValue);
		            return "No record found for the provided PO Number";
		        }

		    }else  if(pdfText.contains("STACKPOS ")) {
		    	
		    	
	    System.out.println("The PDF does contain 'STACKPOS '.");
	    
	    // Extract Name before (Customization)
	    String nameBeforeCustomization = extractNameBeforeCustomization(pdfText);
	    System.out.println("Name: " + nameBeforeCustomization);

	    // Extract SGST Amount
	    String sgstAmount = extractSGSTAmount(pdfText);
	    System.out.println("SGST Amount: " + sgstAmount);

	    // Extract SGST Percentage
	    String sgstPercentage = extractSGSTPercentage(pdfText);
	    System.out.println("SGST Percentage: " + sgstPercentage);

	    // Extract CGST Amount
	    String cgstAmount = extractCGSTAmount(pdfText);
	    System.out.println("CGST Amount: " + cgstAmount);

	    // Extract CGST Percentage
	    String cgstPercentage = extractCGSTPercentage(pdfText);
	    System.out.println("CGST Percentage: " + cgstPercentage);

	    // Extract the Amount with Two Digits   
	    String amountTillTwoDigits = extractAmountTillTwoDigits(pdfText);
	    System.out.println("Amount: " + amountTillTwoDigits);
	    
	    String amountAfterCustomizationcgst = extractAmountAfterCustomization(pdfText);
        System.out.println("Amount after Customizationcgst: " + amountAfterCustomizationcgst);
	    // Additional logic for extracting Invoice details and table data
	    String invoiceMonth = extractValuestackpos_withspace(pdfText, "Invoice for the month of", "\\w+\\s+\\d{4}");
	    String invoiceNo1 = extractValuestackpos(pdfText, "Invoice No :", "\\S+");
	    String invoiceNo2 = extractValuestackpos(pdfText, "Invoice date: ", "\\S+");
	    Date formattedDateadviceDate_stack=convertStringToDate1(invoiceNo2);
	    System.out.println(formattedDateadviceDate_stack+"formattedDateadviceDate_stack");
	    String invoiceName = extractInvoiceName(pdfText, "Customization");
	    System.out.println("Invoice Name: " + invoiceName);

	    // Extract Table Data
	    List<String[]> tableData = extractTableRows(pdfText);
	    System.out.println("\nTable Data:");
	    for (String[] row : tableData) {
	        System.out.println("S.No: " + row[0] + ", Description: " + row[1] + ", Amount: " + row[2]);
	    }

	    // Extract Employee Name
	    String empName = extractEmpName(invoiceName);
	    System.out.println("Employee Name: " + empName);

	    // Print Results
	    System.out.println("Invoice for the Month: " + (invoiceMonth != null ? invoiceMonth : "Not found"));
	    System.out.println("First Invoice No: " + (invoiceNo1 != null ? invoiceNo1 : "Not found"));
	    System.out.println("Second Invoice No: " + (invoiceNo2 != null ? invoiceNo2 : "Not found"));
	    
	    
	    
        PlacementMaintenance existing = placementMaintenanceRep.findByInvoiceNumbersAndDate_spinvoice_stackpos(empName, invoiceMonth);

        System.out.println("invoiceMontttttttttttttttttttttttt" + invoiceMonth + " empName" + empName);

        // Check if the record exists
        if (existing != null) {
            System.out.println("Record found for PO number: " + existing.getPo_no());

            // Check if the PO number matches
            if (existing.getEmp_name().equals(empName)) {
                
                // Check if both SP invoice number and date are not null in the existing record
                System.out.println("Existing name"+existing.getEmp_name()+"empName"+empName);

                if (existing.getSp_inv_no() != null && existing.getSp_inv_date() != null) {
                    System.out.println("SP Invoice No and SP Invoice Date already present in the database. Not updating.");

                    // Return a message if invoice details are already present
                    return "SP INVOICE NO AND SP INVOICE DATE IS ALREADY PRESENT IN THE DATA BASE";
                } else {
                    // Update the invoice number and date since they are not present
                    System.out.println("Updating SP Invoice No and SP Invoice Date...");
		        	System.out.println("invoiceNo"+invoiceNo1+"formattedDateadviceDate"+formattedDateadviceDate_stack);
                    existing.setSp_inv_no(invoiceNo1); // Set the new invoice number
                    existing.setSp_inv_date(formattedDateadviceDate_stack); // Set the new invoice date

                    // Save the updated entity back to the database
                   placementMaintenanceRep.save(existing); // Ensure to save changes to the record
                    System.out.println("Record updated with new SP Invoice No and SP Invoice Date.");
                    return "Record updated successfully.";
                }
            } else {
                System.out.println("PO Number is not matching in the database.");
                return "PO NUMBER IS NOT PRESENT IN THE DATABASE";
            }
        } else {
            System.out.println("No record found for PO number: " + empName);
            return "No record found for the provided PO Number";
        }
	}

		    else {
		    	System.out.println("DIFFERENT PDF IS UPLOADED");
		    	return "DIFFERENT PDF IS UPLOADED";
		    }
		    
		   
		   
		}
	
	private void extractTitle(String pdfText, StringBuilder result) {
	    Pattern titlePattern = Pattern.compile("(Mr\\.|Mrs\\.)\\s([A-Za-z]+)");
	    Matcher matcher = titlePattern.matcher(pdfText);

	    while (matcher.find()) {
	        String title = matcher.group(1); // Mr. or Mrs.
	        String name = matcher.group(2); // Name (without spaces)

	        result.append("Title: ").append(title).append("\n");
	        result.append("Name: ").append(name).append("\n");
	    }
	}


	private void extractTitleAndMonth(String pdfText) {
	    // Modify the regex to capture title, name, month, and year, and stop before "Rs."
	    Pattern titlePattern = Pattern.compile("(Mr\\.|Mrs\\.)\\s([A-Za-z\\s]+?)\\s([A-Za-z]+)\\s(\\d{4})(?=\\s+Rs\\.)");
	    Matcher matcher = titlePattern.matcher(pdfText);

	    while (matcher.find()) {
	        String title = matcher.group(1); // Mr. or Mrs.
	        String name = matcher.group(2); // Name (can include spaces)
	        String month = matcher.group(3); // Month (e.g., Aug)
	        String year = matcher.group(4); // Year (e.g., 2024)

	        System.out.println("Title: " + title);
	        System.out.println("Name: " + name);
	        System.out.println("Month: " + month);
	        System.out.println("Year: " + year);
	    }
	}



	    // Method to extract the month and year (from Jan-Dec and the year)
	private void extractMonthAndYear(String pdfText, StringBuilder result) {
	    String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
	    String monthPatternString = "(" + String.join("|", months) + ")\\s?-?\\s?(\\d{4})";
	    Pattern monthPattern = Pattern.compile(monthPatternString, Pattern.CASE_INSENSITIVE);

	    Matcher matcher = monthPattern.matcher(pdfText);
	    while (matcher.find()) {
	        String month = matcher.group(1); // Month (e.g., Aug)
	        String year = matcher.group(2); // Year (e.g., 2024)
	        String monthUppercase = month.toUpperCase();
	        String monthyear=year+"-"+monthUppercase;
	       // result.append("Month: ").append(month).append("\n");
	      //  result.append("Year: ").append(year).append("\n");
	        result.append(monthyear);
	    }
	}

	    // Method to extract all amounts starting with Rs.
	private void extractAmounts(String pdfText, StringBuilder result) {
	    Pattern amountPattern = Pattern.compile("Rs\\.\\s([\\d,]+\\.\\d{2})");
	    Matcher matcher = amountPattern.matcher(pdfText);

	    while (matcher.find()) {
	        String amount = matcher.group(1);
	        result.append("Amount: Rs. ").append(amount).append("\n");
	    }
	}
	
	private void extractAmountsIndependently(String pdfText, StringBuilder result) {
	    Pattern amountPattern = Pattern.compile("Rs\\.\\s([\\d,]+\\.\\d{2})");
	    Matcher matcher = amountPattern.matcher(pdfText);
	    
	    int counter = 1;
	    while (matcher.find()) {
	        String amount = matcher.group(1);
	        
	        // Call different methods for each match
	        switch (counter) {
	            case 1:
	                extractAmounts1(amount, result);
	                break;
	            case 2:
	                extractAmounts2(amount, result);
	                break;
	            case 3:
	                extractAmounts3(amount, result);
	                break;
	            case 4:
	                extractAmounts4(amount, result);
	                break;
	            default:
	                // Optionally handle more than 4 amounts, or stop after a certain count
	                break;
	        }
	        counter++;
	    }
	}

	// Method for the first amount
	private void extractAmounts1(String amount, StringBuilder result) {
	    result.append("Amount 1: Rs. ").append(amount).append("\n");
	}

	// Method for the second amount
	private void extractAmounts2(String amount, StringBuilder result) {
	    result.append("Amount 2: Rs. ").append(amount).append("\n");
	}

	// Method for the third amount
	private void extractAmounts3(String amount, StringBuilder result) {
	    result.append("Amount 3: Rs. ").append(amount).append("\n");
	}

	// Method for the fourth amount
	private void extractAmounts4(String amount, StringBuilder result) {
	    result.append("Amount 4: Rs. ").append(amount).append("\n");
	}


	    // Method to extract tax information (Tax type and tax amount)
	private void extractTaxInfo(String pdfText, StringBuilder result) {
	    Pattern taxPattern = Pattern.compile("TAX\\s([\\d]+%)\\((IGST|CGST)\\)\\sRs\\.\\s([\\d,]+\\.\\d{2})");
	    Matcher matcher = taxPattern.matcher(pdfText);

	    while (matcher.find()) {
	        String taxPercentage = matcher.group(1);
	        String taxType = matcher.group(2);
	        String taxAmount = matcher.group(3);

	        result.append("Tax Percentage: ").append(taxPercentage).append("\n");
	        result.append("Tax Type: ").append(taxType).append("\n");
	        result.append("Tax Amount: Rs. ").append(taxAmount).append("\n");
	    }
	}
	
	
	// Method to extract IGST tax information
	private void extractIGSTTaxInfo(String pdfText, StringBuilder result) {
	    // Pattern to match IGST tax info
	    Pattern igstPattern = Pattern.compile("TAX\\s([\\d]+%)\\(IGST\\)\\sRs\\.\\s([\\d,]+\\.\\d{2})");
	    Matcher matcher = igstPattern.matcher(pdfText);

	    while (matcher.find()) {
	        String taxPercentage = matcher.group(1); // Tax Percentage
	        String taxAmount = matcher.group(2);     // Tax Amount

	        result.append("IGST Tax Percentage: ").append(taxPercentage).append("\n");
	        result.append("IGST Tax Amount: Rs. ").append(taxAmount).append("\n");
	    }
	}

	// Method to extract CGST tax information
	private void extractCGSTTaxInfo(String pdfText, StringBuilder result) {
	    // Pattern to match CGST tax info
	    Pattern cgstPattern = Pattern.compile("TAX\\s([\\d]+%)\\(CGST\\)\\sRs\\.\\s([\\d,]+\\.\\d{2})");
	    Matcher matcher = cgstPattern.matcher(pdfText);

	    while (matcher.find()) {
	        String taxPercentage = matcher.group(1); // Tax Percentage
	        String taxAmount = matcher.group(2);     // Tax Amount

	        result.append("CGST Tax Percentage: ").append(taxPercentage).append("\n");
	        result.append("CGST Tax Amount: Rs. ").append(taxAmount).append("\n");
	    }
	}



	    // Helper method to clean and extract numeric amount from a string
	    private String cleanAmount(String value) {
	        Pattern pattern = Pattern.compile("Rs\\.\\s([\\d,]+\\.\\d{2})");
	        Matcher matcher = pattern.matcher(value);
	        if (matcher.find()) {
	            return matcher.group(1); // Returns the numeric value after Rs.
	        }
	        return null;
	    }

		// Helper method to extract value based on a key and regex
	    private String extractValueinvoice(String text, String key, String regex) {
	        String pattern = key + "\\s*" + regex;
	        Pattern compiledPattern = Pattern.compile(pattern);
	        Matcher matcher = compiledPattern.matcher(text);
	        if (matcher.find()) {
	            return matcher.group().replace(key, "").trim();
	        }
	        return null;
	    }
		
		
		  private  String extractTotalAmount(String text) {
		        // Pattern to match "Total Rs." followed by the number with commas and two decimal places, ending with "/-"
		        Pattern pattern = Pattern.compile("Total\\s+Rs\\.\\s([\\d,]+\\.\\d{2})\\s*/-");
		        Matcher matcher = pattern.matcher(text);

		        if (matcher.find()) {	
		            return matcher.group(1); // Extract the amount
		        }
		        return null; // Return null if no match is found
		    }
		
		  private Date convertStringToDate1(String dateStr) {
			    // Define possible date formats
			    String[] dateFormats = {
			        "dd/MM/yyyy",   // 21/11/2024
			        
			        "dd-MM-yyyy",   // 21-11-2024
			      
			    };

			    // Try each format until one successfully parses the date
			    for (String pattern : dateFormats) {
			        try {
			            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			            return dateFormat.parse(dateStr); // If successful, return the date
			        } catch (ParseException e) {
			            // If a format doesn't work, continue to the next one
			            continue;
			        }
			    }
			    
			    // Return null if no format worked
			    System.out.println("Failed to parse date: " + dateStr);
			    return null;
			}

		/*----------------------------------------------------------------------------------------------------------------------------------*/
		
		private  String extractNameBeforeCustomization(String text) {
		    Pattern pattern = Pattern.compile("(\\D+)(?=\\(Customization\\))");
		    Matcher matcher = pattern.matcher(text);
		    if (matcher.find()) {
		        return matcher.group(1).trim(); // Extract the name before '(Customization)'
		    }
		    return null;
		}

		private  String extractAmountTillTwoDigits(String text) {
		    Pattern pattern = Pattern.compile("\\d{1,3}(,\\d{3})*(\\.\\d{2})");
		    Matcher matcher = pattern.matcher(text);
		    if (matcher.find()) {
		        return matcher.group(); // Extract the amount with two digits after the decimal point
		    }
		    return null;
		}

		private String extractSGSTPercentage(String text) {
		    // Correct pattern for matching SGST(9%)
		    Pattern pattern = Pattern.compile("SGST\\((\\d+)%\\)");
		    Matcher matcher = pattern.matcher(text);
		    if (matcher.find()) {
		        return matcher.group(1); // Extract the percentage from SGST
		    }
		    return null;
		}


		private  String extractCGSTPercentage(String text) {
		    Pattern pattern = Pattern.compile("CGST\\((\\d+)%\\)");
		    Matcher matcher = pattern.matcher(text);
		    if (matcher.find()) {
		        return matcher.group(1); // Extract the percentage from CGST
		    }
		    return null;
		}
		
		private  String extractSGSTAmount(String text) {
			Pattern pattern = Pattern.compile("SGST\\(\\d+%\\)\\s+([\\d,]+\\.\\d{2})");
		    Matcher matcher = pattern.matcher(text);
		    if (matcher.find()) {
		        return matcher.group(1); // Extract SGST amount with commas and two decimal places
		    }
		    return null;
		}

		private String extractCGSTAmount(String text) {
		    // This pattern matches CGST(9%) followed by the amount with commas and two decimal places
		 //   Pattern pattern = Pattern.compile("CGST\\(\\d+%\\)\\s+([\\d,]+\\.\\d{2})");
	        Pattern pattern = Pattern.compile("CGST\\(\\d+%\\)\\s+([\\d,]+\\.\\d{2})");

		    Matcher matcher = pattern.matcher(text);
		    if (matcher.find()) {
		        return matcher.group(1); // Extract CGST amount with commas and two decimal places
		    }
		    return null;
		}



		private String extractValuestackpos(String text, String key, String regex) {
		    String pattern = key + "\\s*" + regex;
		    Pattern compiledPattern = Pattern.compile(pattern);
		    Matcher matcher = compiledPattern.matcher(text);
		    if (matcher.find()) {
		        return matcher.group().replace(key, "").trim();
		    }
		    return null;
		}

		// Helper method to extract text after a specific keyword
		private String extractNumericValueAfter(String text, String key) {
		    int index = text.indexOf(key);
		    if (index != -1) {
		        // Extract the substring starting right after the key
		        String afterKey = text.substring(index + key.length()).trim();

		        // Use regex to match the numeric value at the start
		        java.util.regex.Matcher matcher = java.util.regex.Pattern.compile("^\\d+").matcher(afterKey);
		        if (matcher.find()) {
		            return matcher.group(); // Return the matched numeric value
		        }
		    }
		    return null; // Return null if no numeric value is found
		}
	
	    private static String extractTableContent(String pdfText) {
	        // Adjust regex to match the table structure
	        String tableRegex = "(SI No\\s+Description - Finacle.*?)(\\s*Total|\\Z)";
	        Pattern pattern = Pattern.compile(tableRegex, Pattern.DOTALL);
	        Matcher matcher = pattern.matcher(pdfText);

	        if (matcher.find()) {
	            return matcher.group(1).trim();
	        }
	        return null;
	    }

	    // Method to extract the MM-yyyy date format from the table
	    private static String extractMonthYearFromTable(String tableContent) {
	        String dateRegex = "\\b[A-Za-z]{3} -\\d{4}\\b"; // Match "Aug -2024"
	        Pattern pattern = Pattern.compile(dateRegex);
	        Matcher matcher = pattern.matcher(tableContent);

	        if (matcher.find()) {
	            String matchedDate = matcher.group();
	            // Convert to MM-yyyy format
	            return (matchedDate);
	        }
	        return null;
	    }

	    private String extractAmountAfterCustomization(String text) {
	        // Pattern to match the word 'Customization' followed by a space and then a number (with commas and decimals)
	        Pattern pattern = Pattern.compile("Customization\\)\\s+([\\d,]+\\.\\d{2})");
	        Matcher matcher = pattern.matcher(text);
	        
	        if (matcher.find()) {
	            return matcher.group(1); // Extract the amount after 'Customization)'
	        }
	        return null; // Return null if no match is found
	    }

	    // Convert "Aug -2024" to "08 2024"
	    private static String convertToMMYYYY(String dateText) {
	        String[] monthMapping = {
	                "Jan", "Feb", "Mar", "Apr", "May", "Jun",
	                "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
	        };

	        String[] parts = dateText.split(" -");
	        if (parts.length == 2) {
	            String month = parts[0].trim();
	            String year = parts[1].trim();

	            for (int i = 0; i < monthMapping.length; i++) {
	                if (monthMapping[i].equalsIgnoreCase(month)) {
	                    return String.format("%02d %s", i + 1, year);
	                }
	            }
	        }
	        return null;
	    }
	    
	    private static String extractValuestackpos_withspace(String text, String key, String valuePattern) {
	        String regex = key + "\\s*" + valuePattern;
	        Pattern pattern = Pattern.compile(regex);
	        Matcher matcher = pattern.matcher(text);

	        if (matcher.find()) {
	            // Extract the matched string and remove the key
	            String matchedValue = matcher.group().replace(key, "").trim();

	            // Split the extracted value into month and year
	            String[] parts = matchedValue.split("\\s+");
	            if (parts.length == 2) {
	                String month = parts[0].toUpperCase(); // Convert month to uppercase
	                String year = parts[1];
	                return year + "-" + month; // Return year first, followed by month
	            }
	        }
	        return null;
	    }



	    // Extract "Customization" with the name before it
	    public static String extractInvoiceName(String pdfText, String key) {
	        String regex = "(.*?)\\s*\\(" + Pattern.quote(key) + "\\)";
	        Matcher matcher = Pattern.compile(regex).matcher(pdfText);

	        if (matcher.find()) {
	            return matcher.group(1).trim(); // Extract name before "Customization"
	        }
	        return null; // Return null if not found
	    }

	    // Extract table rows
	    public static List<String[]> extractTableRows(String pdfText) {
	        List<String[]> tableData = new ArrayList<>();
	        String regex = "(\\d+\\.?)\\s+(.*?)\\s+(\\d+[,.\\d]*)";
	        Matcher matcher = Pattern.compile(regex).matcher(pdfText);

	        while (matcher.find()) {
	            tableData.add(new String[]{
	                matcher.group(1).trim(), // S.No
	                matcher.group(2).trim(), // Description
	                matcher.group(3).trim()  // Amount
	            });
	        }
	        return tableData;
	    }
	    
	    public static String extractEmpName(String invoiceName) {
	        // Remove the number and "Customization" part
	        if (invoiceName != null) {
	            // Remove leading digits (e.g., "1." or "2.")
	            invoiceName = invoiceName.replaceAll("^\\d+\\.\\s*", "");

	            // Remove "Customization" part, keeping only the name
	            invoiceName = invoiceName.replace(" (Customization)", "").trim();

	            // Keep only alphabetic characters and spaces
	            return invoiceName.replaceAll("[^a-zA-Z\\s]", "").trim();
	        }
	        return null; // Return null if invoice name is not found
	    }

	    public static String convertToMonthYearFormat(String monthYear) {
	        try {
	            // Parse the extracted month-year string into a Date object
	            SimpleDateFormat inputFormat = new SimpleDateFormat("MMM -yyyy");
	            Date date = inputFormat.parse(monthYear.trim());

	            // Format the Date object into the desired "yyyy-MMM" format
	            SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MMM");
	            return outputFormat.format(date).toUpperCase(); // To ensure the month is in uppercase
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null; // Return null if the parsing fails
	        }
	    }
	    
	
	 

}