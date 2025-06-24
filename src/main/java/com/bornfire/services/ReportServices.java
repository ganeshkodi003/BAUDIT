package com.bornfire.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Service
public class ReportServices {

	@Autowired
	Environment env;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSource srcdataSource;

	public File getFileAttendance(String emp_id, String cal_month, String cal_year, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Attendance_Report" + emp_id + "_" + cal_month + "_" + cal_year;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/AttendanceRegister_Report.jrxml");
			} else {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/AttendanceRegister_Report.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			// logger.info("Assigning Parameters for Jasper");

			map.put("emp_id", emp_id);
			map.put("cal_month", cal_month);
			map.put("cal_year", cal_year);

			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}

	public File getFileDailyAttendance(String cal_year, String cal_month, String cal_date, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Attendance_Report" + "_" + cal_month + "_" + cal_year + "_" + cal_date;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass()
						.getResourceAsStream("/static/jasper/AttendanceRegister_Daily_Report.jrxml");
			} else {

				jasperFile = this.getClass()
						.getResourceAsStream("/static/jasper/AttendanceRegister_Daily_Report.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("CAL_YEAR", cal_year);

			map.put("CAL_MONTH", cal_month);

			map.put("CAL_DATE", cal_date);
			System.out.println(cal_year+"cal_year"+cal_month+"cal_month"+cal_date+"cal_date");

			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}

	public File getFileMonthyAttendance(String cal_month, String cal_year, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Attendance_Report" + "_" + cal_month + "_" + cal_year;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass()
						.getResourceAsStream("/static/jasper/Attendence_month_report.jrxml");
			} else {

				jasperFile = this.getClass()
						.getResourceAsStream("/static/jasper/Attendence_month_report.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("CAL_YEAR", cal_year);

			map.put("CAL_MONTH", cal_month);
			System.out.println(cal_year+"cal_year"+cal_month+"cal_month");


			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}
	
	
	public File getFileLeaveRegisterALL(String employee_id, String year, String leave_category, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		
		
		System.out.println("leave_category"+employee_id+"leaveeeeeeeeeeeeeeeeee"+leave_category);
		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Leave_Register" + "_" + employee_id + "_" + year;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/Leaveregisterall_leave.jrxml");
			} else {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/Leaveregisterall_leave.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("EMPLOYEE_ID", employee_id);
			map.put("YEAR", year);
			

			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}

	public File getFileLeaveRegister(String employee_id, String year, String leave_category, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		
		
		System.out.println("leave_categoryyyyyyyyyyyyyyyy"+leave_category);
		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Leave_Register" + "_" + employee_id + "_" + year;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/LeaveRegister_Report.jrxml");
			} else {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/LeaveRegister_Report.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("EMPLOYEE_ID", employee_id);
			map.put("YEAR", year);
			map.put("LEAVE_CATEGORY", leave_category);

			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}

	public File getFileTimeSheet(String emp_id, String year, String month, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Time_Sheet" + "_" + emp_id + "_" + year;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/TimesheerReport.jrxml");
			} else {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/TimesheerReport.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("emp_id", emp_id);
			map.put("year", year);
			map.put("month", month);

			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}

	public File getFileWorkAssign(String emp_id, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Work_Assign" + "_" + emp_id;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/WorkAssign_Report.jrxml");
			} else {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/WorkAssign_Report.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("emp_id", emp_id);
			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}

	public File getFileProfileMaster(String emp_id, String ProfileType, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Profile_Master" + "_" + emp_id;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/ProfileMaster_Report.jrxml");
			} else {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/ProfileMaster_Report.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("EMP_ID", emp_id);
			// map.put("profileType", ProfileType);
			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}

	public File getFileProject(String proj_id, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Project_Report" + "_" + proj_id;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/ProjectMaster_Report.jrxml");
			} else {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/ProjectMaster_Report.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("PROJ_ID", proj_id);

			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}

	public File getFileHolidayList(String cal_year, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Attendance_Report" + "_" + cal_year;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/Holiday_List_Report.jrxml");
			} else {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/Holiday_List_Report.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("CAL_YEAR", cal_year);

			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}

	public File getFileHolidayDetailsList(String cal_year, String report_type)
			throws FileNotFoundException, JRException, SQLException {

		String path = "Downloads";

		String fileName = "";
		String zipFileName = "";
		File outputFile;

		fileName = "Attendance_Report" + "_" + cal_year;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			if (report_type.equals("Pdf")) {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/HolidayList_Details.jrxml");
			} else {

				jasperFile = this.getClass().getResourceAsStream("/static/jasper/HolidayList_Details.jrxml");

			}

			JasperReport jr = JasperCompileManager.compileReport(jasperFile);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("CAL_YEAR", cal_year);

			if (report_type.equals("Pdf")) {
				fileName = fileName + ".pdf";
				path += fileName;

				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

				JasperExportManager.exportReportToPdfFile(jp, path);

			} else {

				fileName = fileName + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}
}
