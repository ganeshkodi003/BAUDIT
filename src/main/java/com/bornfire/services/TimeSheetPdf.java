package com.bornfire.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.bornfire.entities.BTMEmpTimeSheet;
import com.bornfire.entities.BTMEmpTimeSheetRep;
import com.bornfire.entities.BTMMTimeSheet;
import com.bornfire.entities.BTMMTimeSheetRep;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
public class TimeSheetPdf {
	@Autowired
	Environment env;
	@Autowired
	DataSource srcdataSource;

	@Autowired
	BTMEmpTimeSheetRep btmEmpTimeSheetRep;
	@Autowired
	Timesheetmaster timesheetmaster;

	@Autowired
	SessionFactory sessionFactory;

	/*
	 * private List<BTMEmpTimeSheet> timeSheetList;
	 * 
	 * public TimeSheetPdf(List<BTMEmpTimeSheet> timesheetReport) {
	 * this.timeSheetList = timesheetReport; }
	 * 
	 * private void writeTableHeader(PdfPTable table) { PdfPCell cell = new
	 * PdfPCell(); cell.setBackgroundColor(Color.BLUE); cell.setPadding(5);
	 * 
	 * Font font = FontFactory.getFont(FontFactory.HELVETICA);
	 * font.setColor(Color.WHITE);
	 * 
	 * 
	 * cell.setPhrase(new Phrase("", font));
	 * 
	 * table.addCell(cell);
	 * 
	 * cell.setPhrase(new Phrase("", font)); table.addCell(cell);
	 * 
	 * cell.setPhrase(new Phrase("", font)); table.addCell(cell);
	 * 
	 * cell.setPhrase(new Phrase("", font)); table.addCell(cell);
	 * 
	 * cell.setPhrase(new Phrase("", font)); table.addCell(cell);
	 * 
	 * }
	 * 
	 * private void writeTableData(PdfPTable table) { for (BTMEmpTimeSheet report :
	 * timeSheetList) {
	 * 
	 * table.addCell(""); table.addCell(""); table.addCell(""); table.addCell("");
	 * table.addCell("");
	 * 
	 * } }
	 * 
	 * public void export(HttpServletResponse response) throws DocumentException,
	 * IOException { Document document = new Document(PageSize.A4);
	 * PdfWriter.getInstance(document, response.getOutputStream());
	 * 
	 * document.open(); Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	 * font.setSize(18); font.setColor(Color.BLUE);
	 * 
	 * Paragraph p = new Paragraph("TIME SHEET REPORT", font);
	 * p.setAlignment(Paragraph.ALIGN_CENTER);
	 * 
	 * document.add(p);
	 * 
	 * PdfPTable table = new PdfPTable(5); table.setWidthPercentage(100f);
	 * table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
	 * table.setSpacingBefore(10);
	 * 
	 * writeTableHeader(table); writeTableData(table);
	 * 
	 * document.add(table);
	 * 
	 * document.close();
	 * 
	 * }
	 */
	public File getFile(@RequestParam String emp_id, String month, BigDecimal year)
			throws FileNotFoundException, JRException, SQLException {

		String path = env.getProperty(emp_id);

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Timesheet_Report" + '_' + emp_id;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			// logger.info("Getting Jasper file :" + "Third_PARTY");

			jasperFile = this.getClass().getResourceAsStream("/static/jasper/Timesheet_Report.jrxml");

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("emp_id", emp_id);
			map.put("month", month);
			map.put("year", year);

			// if (filetype.equals("pdf")) {
			fileName = fileName + ".pdf";
			path = fileName;
			JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
			JasperExportManager.exportReportToPdfFile(jp, path);
			// }
		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);
		return outputFile;
	}

	public String timeSheetVerify(BTMEmpTimeSheet time, String emp_id, BigDecimal year, String month,String userid) {

		String msg = "";

		try {

			BTMEmpTimeSheet up1 = btmEmpTimeSheetRep.getTimeSheetModify(emp_id, year, month);

			up1.setEntity_flg('Y');
			up1.setDate_of_approval(new Date());
			up1.setAuth_user(userid);

			btmEmpTimeSheetRep.save(up1);

			msg = "Approved succesfully";

		} catch (Exception e) {
			msg = "Error Occured. Please contact Administrator";
			e.printStackTrace();
		}
		return msg;
	}
	/*
	 * public BTMEmpTimeSheet getIdMonthYear(String id,String month,BigDecimal year)
	 * {
	 * 
	 * if (btmEmpTimeSheetRep.getTimeSheetVerify(id,month,year) != null) {
	 * BTMEmpTimeSheet up = btmEmpTimeSheetRep.getTimeSheetVerify(id,month,year);
	 * up.getEmp_id(); up.getMonth(); up.getYear(); up.getEntity_flg(); return up; }
	 * else { return new BTMEmpTimeSheet(); }
	 * 
	 * };
	 */

}
