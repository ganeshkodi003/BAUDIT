package com.bornfire.services;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
@Service
public class WorkAssignmentReportExcel  {
	@Autowired
	Environment env;
	@Autowired
	DataSource srcdataSource;
	/*
	 * @Override protected void buildExcelDocument( Map<String, Object> model,
	 * Workbook workbook, HttpServletRequest request, HttpServletResponse response)
	 * throws Exception {
	 * 
	 * // define excel file name to be exported
	 * response.addHeader("Content-Disposition",
	 * "attachment;fileName=WorkAssignmentReport.xlsx");
	 * 
	 * // read data provided by controller
	 * 
	 * @SuppressWarnings("unchecked") List<BTMWorkAssignment> list =
	 * (List<BTMWorkAssignment>) model.get("list");
	 * 
	 * // create one sheet Sheet sheet =
	 * workbook.createSheet("WorkAssignmentReport");
	 * 
	 * // create row0 as a header Row row0 = sheet.createRow(0);
	 * 
	 * CellStyle style = workbook.createCellStyle(); XSSFFont font = (XSSFFont)
	 * workbook.createFont(); font.setBold(true); font.setFontHeight(16);
	 * style.setFont(font);
	 * 
	 * row0.createCell(0).setCellValue("EMP ID");
	 * row0.createCell(1).setCellValue("EMP NAME");
	 * row0.createCell(2).setCellValue("DESIGN");
	 * row0.createCell(3).setCellValue("DOP");
	 * row0.createCell(4).setCellValue("DOJ");
	 * row0.createCell(5).setCellValue("LOCATION");
	 * row0.createCell(6).setCellValue("GROUP");
	 * row0.createCell(7).setCellValue("TEAM");
	 * row0.createCell(8).setCellValue("REPORT MANAGER");
	 * row0.createCell(9).setCellValue("APP AUTH");
	 * row0.createCell(10).setCellValue("ASSIGN DATE");
	 * row0.createCell(11).setCellValue("CURRENT ASSIGNMENT");
	 * row0.createCell(12).setCellValue("START DATE");
	 * row0.createCell(13).setCellValue("WORK DETAILS");
	 * row0.createCell(14).setCellValue("WORK CONTRAINTS");
	 * 
	 * 
	 * // create row1 onwards from List<T> int rowNum = 1; for(BTMWorkAssignment
	 * report : list) { Row row = sheet.createRow(rowNum++); CellStyle style1 =
	 * workbook.createCellStyle(); XSSFFont font1 = (XSSFFont)
	 * workbook.createFont(); font1.setBold(true); font1.setFontHeight(16);
	 * style1.setFont(font); row.createCell(0).setCellValue("");
	 * row.createCell(1).setCellValue(""); row.createCell(2).setCellValue("");
	 * row.createCell(3).setCellValue(""); row.createCell(4).setCellValue("");
	 * row.createCell(5).setCellValue(""); row.createCell(6).setCellValue("");
	 * row.createCell(7).setCellValue(""); row.createCell(8).setCellValue("");
	 * row.createCell(9).setCellValue(""); row.createCell(10).setCellValue("");
	 * row.createCell(11).setCellValue(""); row.createCell(12).setCellValue("");
	 * row.createCell(13).setCellValue(""); row.createCell(14).setCellValue(""); } }
	 */
	 public File getFile(@RequestParam String emp_id) throws FileNotFoundException, JRException, SQLException {

			System.out.println("0000");

			String path = this.env.getProperty("/User/user/Downloads");


			String fileName = "";
			String zipFileName = "";
			File outputFile;


			fileName = "WorkAssignReport" +'_' + emp_id;

			zipFileName = fileName + ".zip";

			try {
				InputStream jasperFile;

				// logger.info("Getting Jasper file :" + "Third_PARTY");

				

					jasperFile = this.getClass().getResourceAsStream("/static/jasper/WorkAssignReport.jrxml");
				

				JasperReport jr =JasperCompileManager.compileReport(jasperFile);
				System.out.println(jr);
				
				HashMap<String, Object> map = new HashMap<String, Object>();

				map.put("emp_id", emp_id);

			//	if (filetype.equals("PDF")) {
					fileName = fileName + ".pdf";
					path = fileName;
					System.out.println(path+"8888");
					JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
					System.out.println(jp+" btm");
					JasperExportManager.exportReportToPdfFile(jp,path);
					//logger.info("PDF File exported");
			//	} else {

			/*
			 * System.out.println("EXCEEEEEll"); fileName = fileName + ".xlsx"; path +=
			 * fileName; System.out.println("@@@@"); JasperPrint jp =
			 * JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
			 * System.out.println("123"); System.out.println(jp); JRXlsxExporter exporter =
			 * new JRXlsxExporter(); exporter.setExporterInput(new SimpleExporterInput(jp));
			 * exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
			 * exporter.exportReport(); // logger.info("Excel File exported"); }
			 */

			} catch (Exception e) {
				e.printStackTrace();
			}

			outputFile = new File(path);

			return outputFile;
		}
}