package com.bornfire.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.bornfire.entities.PlacementMaintenance;

public class ExcelHelper {
	
	 public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String[] HEADERs = { "PO_NO", "PO_DATE", "VENDOR", "GSTIN", "LOCATION","EMP_REF_NO",
			"EMP_NAME","EMP_NO","PO_MON_SRL","EFFORTS","BILL_MOD","RATE","BILL_TOTAL_AMT","DELIVERY_DATE","MONTH",
			"ENTRY_USER","MODIFY_USER","VERIFY_USER","ENTRY_DATE","MODIFY_DATE","VERIFY_DATE","ENTIRY_FLG","DEL_FLG",
			"UPLOAD_FLG","UPLOAD_TIME"};
	 static String SHEET = "Stock Management";
	public static boolean hasExcelFormat(MultipartFile file) {
	    
		if (!TYPE.equals(file.getContentType())) {
	        return false;
	      }
	      return true;
	}
	
	public static List<PlacementMaintenance> excelToPlacementMaintenance(InputStream is) {
	    try {
	      Workbook workbook = new XSSFWorkbook(is);
	      Sheet sheet = workbook.getSheet(SHEET);
	      Iterator<Row> rows = sheet.iterator();
	      List<PlacementMaintenance> stocks = new ArrayList<PlacementMaintenance>();
	      int rowNumber = 0;
	      while (rows.hasNext()) {
	        Row currentRow = rows.next();
	        // skip header
	        if (rowNumber == 0) {
	          rowNumber++;
	          continue;
	        }
	        Iterator<Cell> cellsInRow = currentRow.iterator();
	        PlacementMaintenance pm = new PlacementMaintenance();
	        int cellIdx = 0;
	        while (cellsInRow.hasNext()) {
	          Cell currentCell = cellsInRow.next();
	         
	          switch (cellIdx) {
	         
	          case 0:
	        	pm.setPo_no(currentCell.getStringCellValue());
	        			System.out.println(currentCell.getStringCellValue()+"    po no");
	            break;
	          case 1:
	        	 pm.setPo_date(currentCell.getDateCellValue());
	        	  System.out.println(currentCell.getDateCellValue()+"      po date");
	            break;
	          case 2:
	        	  pm.setVendor(currentCell.getStringCellValue());
	        	  System.out.println(currentCell.getStringCellValue()+"     vendor");
	        	  
	            break;
	          case 3:
	        	  pm.setGstin(currentCell.getStringCellValue());
	        	  System.out.println(currentCell.getStringCellValue()+"     gstin");
	            break;
	          case 4:
	        	  pm.setLocation(currentCell.getStringCellValue());
	        	  System.out.println(currentCell.getStringCellValue()+"    location");
	            break;
	          case 5:
	        	  pm.setEmp_id( currentCell.getStringCellValue());
	        	  System.out.println(currentCell.getStringCellValue()+"     emp id");
	            break;
	          case 6:
	        	  pm.setEmp_name(currentCell.getStringCellValue());
	        	  System.out.println(currentCell.getStringCellValue()+"   emp name ");
	            break;
	          case 7:
	        	  pm.setEmp_id(currentCell.getStringCellValue());
	        	  System.out.println(currentCell.getStringCellValue()+"    emp id");
	            break;
	          case 8:
	        	  pm.setPo_month(currentCell.getStringCellValue());
	        	  System.out.println(currentCell.getStringCellValue()+"  po month ");
	            break;
	          case 9:
	        	  pm.setPo_qty(currentCell.getStringCellValue());
	        	  System.out.println(currentCell.getStringCellValue()+" po qty");
	            break;
	          case 10:
	        	  pm.setGrn_efforts(currentCell.getStringCellValue());
	        	  System.out.println(currentCell.getStringCellValue()+"  grn efforts");
	            break;
	          case 11:
	        	  pm.setPercent(currentCell.getStringCellValue());
	        	  System.out.println(currentCell.getStringCellValue()+"  percent");
	            break;
	          case 12:
	        	  pm.setRate_mode(currentCell.getStringCellValue());
	        	  System.out.println(currentCell.getNumericCellValue()+"    rate");
	            break;
	          case 13:
	        	  pm.setInv_tot_amt(currentCell.getStringCellValue());
	        	  System.out.println(currentCell.getStringCellValue()+"     inv tot amt");
	            break;
	          case 14:
	        	  pm.setPo_month(currentCell.getStringCellValue());
	        	  System.out.println(currentCell.getStringCellValue()+"      po month");
	            break;
	          case 15:
	        	  pm.setEntry_user(currentCell.getStringCellValue());
	        	  System.out.println(currentCell.getStringCellValue()+"      entry user");
	            break;
	          case 16:
	        	  pm.setModify_flg(currentCell.getStringCellValue());
	        	  System.out.println(currentCell.getStringCellValue()+"      modify user");
	            break;
	          case 17:
	        	  pm.setAuth_user(currentCell.getStringCellValue());
	        	  System.out.println(currentCell.getStringCellValue()+"      verify user");
	            break;
	          case 18:
	        	  pm.setEntry_time(currentCell.getDateCellValue());
	        	  System.out.println(currentCell.getDateCellValue()+"      entry date");
	            break;
	          case 19:
	        	  pm.setModify_time(currentCell.getDateCellValue());
	        	  System.out.println(currentCell.getDateCellValue()+"   modify date");
	            break;
	          case 20:
	        	  pm.setVerify_time(currentCell.getDateCellValue());
	        	  System.out.println(currentCell.getDateCellValue()+"   verify date");
	            break;
	          case 21:
	        	  pm.setEntity_flg(currentCell.getStringCellValue());
	        	  System.out.println(currentCell.getStringCellValue()+"   entity flg");
	            break;
	          case 22:
	        	  pm.setDel_flg(currentCell.getStringCellValue());
	        	  System.out.println(currentCell.getStringCellValue()+"   del flg");
	            break;
	          case 23:
	        	  pm.setUpload_flg(currentCell.getStringCellValue());
	        	  System.out.println(currentCell.getStringCellValue()+"   upload flg");
	            break;
	          case 24:
	        	  pm.setUpload_time(currentCell.getDateCellValue());
	        	  System.out.println(currentCell.getDateCellValue()+"  upload time");
	            break;
	          default:
	            break;
	          }
	          
	         
	          cellIdx++;
	        }
	        stocks.add(pm);
	       
	       
	       
	      }
	      workbook.close();
	      
	      return stocks;
	    } catch (IOException e) {
	      throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
	    }
	  }

}
