package com.bornfire.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Env;
import org.springframework.core.env.Environment;

import ch.qos.logback.classic.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;


import org.slf4j.LoggerFactory;
;

public class Account_Ledger_Service {
	/*
	 * private static final Logger logger =
	 * LoggerFactory.getLogger(Account_Ledger_Service.class);
	 */
	private static final Logger logger = (Logger) LoggerFactory.getLogger(Account_Ledger_Service.class);

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSource srcdataSource;
	
	@Autowired
	Environment env;


	public File getaccountledgerfile(String filetype, String accnum, String fdate, String tdate,String bal)
	        throws FileNotFoundException, JRException, SQLException {

	    String accountnum = accnum;
	    String fromdate = fdate;
	    String todate = tdate;
	    String path = env.getProperty("output.exportpath");
	    System.out.println(path);
	    System.out.println(fdate + "  lkdsjhlafjhsd   " + tdate +"balanceeeeee"+bal);

	    String fileName = "AccountLedger_" + accnum + "_" + fdate + "_" + tdate;
	    String zipFileName = fileName + ".zip";
	    File outputFile;

	    logger.info("Generating Account Ledger Report for Account: " + accountnum);

	    try {
	        InputStream jasperFile;

	        if (filetype.equals("pdf")) {
	            System.out.println("Generating PDF");
	            jasperFile = this.getClass().getResourceAsStream("/static/jasper/AccountLedger.jrxml");
	        } else {
	            jasperFile = this.getClass().getResourceAsStream("/static/jasper/AccountLedger.jrxml");
	        }

	        JasperReport jr = JasperCompileManager.compileReport(jasperFile);

	        HashMap<String, Object> map = new HashMap();

	   
	        
	        // Add parameters to map with formatted date strings
	        map.put("acct_num", accountnum);
	        map.put("FROM_DATE", fromdate);  // Pass the formatted date string
	        map.put("TO_DATE", todate);  
	        map.put("Balance", bal); 
	        System.out.println(map);

	        // Generate the report and export to PDF or Excel based on filetype
	        JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());

	        if (filetype.equals("pdf")) {
	            fileName += ".pdf";
	            path = path + fileName;
	            JasperExportManager.exportReportToPdfFile(jp, path);
	        } else {
	            fileName += ".xlsx";
	            path += fileName;
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
