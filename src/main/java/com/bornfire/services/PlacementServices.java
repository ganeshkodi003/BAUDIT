package com.bornfire.services;

import com.bornfire.entities.Account_Ledger_Entity;
import com.bornfire.entities.Account_Ledger_Rep;
import com.bornfire.entities.BTM_GST_MASTER;
import com.bornfire.entities.BTM_REV_PART_TRAN;
import com.bornfire.entities.BTM_REV_PART_TRANREPO;
import com.bornfire.entities.BTM_TRANS_PARTITION_DETAILS;
import com.bornfire.entities.BTM_TRANS_POINT_DETAILS;
import com.bornfire.entities.Baj_Sal_Work_Entity;
import com.bornfire.entities.BankMaster;
import com.bornfire.entities.BankMasterRep;
import com.bornfire.entities.ClientMaster;
import com.bornfire.entities.ClientMasterRep;
import com.bornfire.entities.InvoiceMaster;
import com.bornfire.entities.InvoiceMasterRep;
import com.bornfire.entities.PlacementMaintenance;
import com.bornfire.entities.PlacementMaintenanceRep;
import com.bornfire.entities.PlacementMaster;
import com.bornfire.entities.PlacementMasterRep;
import com.bornfire.entities.PlacementResourceMaster;
import com.bornfire.entities.PlacementResourcesMasterRepo;
import com.bornfire.entities.ProfessionalCharge;
import com.bornfire.entities.ProfessionalChargeRep;
import com.bornfire.entities.TimesheetManagement;
import com.bornfire.entities.TimesheetManagementRep;
import com.bornfire.entities.paystructureentity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

//import antlr.collections.List;
//import com.bornfire.entities.UserProfile;

@Service
@ConfigurationProperties("output")
@Transactional
public class PlacementServices {

	// private static final Logger logger =
	// LoggerFactory.getLogger(PlacementServices.class);

	private static final Logger logger = LoggerFactory.getLogger(PlacementServices.class);
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSource srcdataSource;

	@Autowired
	ClientMasterRep clientMasterRep;
	
	@Autowired
	BankMasterRep bankMasterRep;
	
	@Autowired
	PlacementResourcesMasterRepo placementResourcesMasterRepo;

	@Autowired
	PlacementMasterRep placementMasterRep;
	
	@Autowired
	Environment env;

	@Autowired
	TimesheetManagementRep timesheetManagementRep;

	@Autowired
	ProfessionalChargeRep professionalChargeRep;

	@Autowired
	InvoiceMasterRep invoiceMasterRep;

	
	@Autowired
	PlacementMaintenanceRep placementmaintenancerep;
	@Autowired
	com.bornfire.entities.BTM_GST_MASTERREPO BTM_GST_MASTERREPO;
	
	@Autowired
	Account_Ledger_Rep account_Ledger_Rep;
	@Autowired
	com.bornfire.entities.BTM_TRANS_POINT_DETAILSRepo BTM_TRANS_POINT_DETAILSRepo;
	@Autowired
	com.bornfire.entities.BTM_TRANS_PARTITION_DETAILSREPO BTM_TRANS_PARTITION_DETAILSrepo;
	
	@Autowired
	BTM_REV_PART_TRANREPO BTM_REV_PART_TRANREPO;
	@NotNull
	private String exportpath;

	@Value("${default.password}")
	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getExportpath() {
		return exportpath;
	}

	public void setExportpath(String exportpath) {
		this.exportpath = exportpath;
	}
	
	
	
//	===============Client Master===================

	/*
	 * public String addClientUser(ClientMaster clientMaster, String formmode) {
	 * 
	 * String msg = ""; System.out.println("jaan");
	 * 
	 * if (formmode.equals("add")) {
	 * 
	 * ClientMaster up = clientMaster;
	 * 
	 * up.setEntity_flg("Y");
	 * 
	 * clientMasterRep.save(up);
	 * 
	 * msg = "Added Successfully"; } else if (formmode.equals("edit")) {
	 * 
	 * ClientMaster up = clientMaster; up.setEntity_flg("Y");
	 * clientMasterRep.save(up); msg = "Edited Successfully"; }
	 * 
	 * return msg; }
	 * 
	 * // Get User
	 * 
	 * public ClientMaster getClientUser(String id) {
	 * 
	 * if (clientMasterRep.existsById(id)) { ClientMaster up =
	 * clientMasterRep.findById(id).get(); up.getEntity_flg(); return up; } else {
	 * return new ClientMaster(); } };
	 */

	// Get List




//	====================== Resource Master =========================

	public String addUser(PlacementResourceMaster placementResourceMaster, String formmode) {

		String msg = "";

		if (formmode.equals("add")) {

			PlacementResourceMaster up = placementResourceMaster;

			up.setEntity_flg("Y");
			placementResourcesMasterRepo.save(up);

			msg = "Added Successfully";
		} else if (formmode.equals("edit")) {

			PlacementResourceMaster up = placementResourceMaster;

			up.setEntity_flg("Y");
			placementResourcesMasterRepo.save(up);

			msg = "Edited Successfully";
		}
		return msg;
	}

//	Get user
	public PlacementResourceMaster getUser(String id) {

		if (placementResourcesMasterRepo.existsById(id)) {
			PlacementResourceMaster up = placementResourcesMasterRepo.findById(id).get();
			up.getEntity_flg();
			return up;
		} else {
			return new PlacementResourceMaster();
		}

	};

//	get List

	public List<PlacementResourceMaster> getUsersList() {

		List<PlacementResourceMaster> users = placementResourcesMasterRepo.getplacementlist();

		return users;

	}

//	============  Professional Charge  =================

	public String addUser1(ProfessionalCharge professionalCharge, String formmode) {

		String msg = "";

		if (formmode.equals("add")) {

			ProfessionalCharge up = professionalCharge;
			professionalChargeRep.save(up);
			up.setEntity_flg("Y");
			msg = "Added Successfully";

		} else if (formmode.equals("edit")) {

			ProfessionalCharge up = professionalCharge;
			up.setEntity_flg("Y");
			professionalChargeRep.save(up);
			msg = "Edited Successfully";

		}
		return msg;
	}

	// get user

	public ProfessionalCharge getUser1(BigDecimal id) {

		if (professionalChargeRep.existsById(id)) {
			ProfessionalCharge up = professionalChargeRep.findById(id).get();
			up.getEntity_flg();
			return up;
		} else {
			return new ProfessionalCharge();
		}

	};

	// userList

	public List<ProfessionalCharge> getUsersList1() {

		List<ProfessionalCharge> users = professionalChargeRep.getplacementlist1();

		return users;

	}

//	============  InvoiceMaster Charge  =================

	/*
	 * public String addInvoiceUser(InvoiceMaster invoiceMaster, String formmode) {
	 * 
	 * String msg = "";
	 * 
	 * if (formmode.equals("add")) {
	 * 
	 * InvoiceMaster up = invoiceMaster; invoiceMasterRep.save(up);
	 * up.setEntity_flg("Y"); msg = "Added Successfully";
	 * 
	 * } else if (formmode.equals("edit")) {
	 * 
	 * InvoiceMaster up = invoiceMaster; up.setEntity_flg("Y");
	 * invoiceMasterRep.save(up); msg = "Edited Successfully";
	 * 
	 * } return msg; }
	 */
	


//	get user

	/*
	 * public InvoiceMaster getInvoiceUser(String invoice_no) {
	 * 
	 * if (invoiceMasterRep.existsById(invoice_no)) { InvoiceMaster up =
	 * invoiceMasterRep.findById(invoice_no).get(); //up.getEntity_flg(); return up;
	 * } else { return new InvoiceMaster(); } };
	 */

	// get List

	/*
	 * public List<InvoiceMaster> getInvoiceList() {
	 * 
	 * List<InvoiceMaster> users = invoiceMasterRep.getplacementlist2();
	 * 
	 * return users;
	 * 
	 * }
	 */
	
	

//	===============Placement Master===================

	public String addPlacementUser(PlacementMaster placementMaster, String formmode) {

		String msg = "";

		if (formmode.equals("add")) {

			PlacementMaster up = placementMaster;

			up.setEntity_flg("Y");

			placementMasterRep.save(up);

			msg = "Added Successfully";
		} else if (formmode.equals("edit")) {

			PlacementMaster up = placementMaster;
			up.setEntity_flg("Y");
			placementMasterRep.save(up);
			msg = "Edited Successfully";
		}

		return msg;
	}

	// Get User

	public PlacementMaster getPlacementUser(String id) {

		if (placementMasterRep.existsById(id)) {
			PlacementMaster up = placementMasterRep.findById(id).get();
			up.getEntity_flg();
			return up;
		} else {
			return new PlacementMaster();
		}
	};
	
	// clientmaster
	
	/*
	 * public ClientMaster getClientlist(String id) {
	 * 
	 * if(clientMasterRep.existsById(id)) {
	 * 
	 * ClientMaster up = clientMasterRep.findById(id).get(); up.getEntity_flg();
	 * return up; } else { return new ClientMaster(); } }
	 */
	// Get List

	public List<PlacementMaster> getPlacementMasterlist(String id, String name) {

		List<PlacementMaster> users = placementMasterRep.findByParam(id, name);

		return users;

	}

//	Time sheet 

	public String addTimesheetUser(TimesheetManagement timesheetManagement, String formmode) {

		String msg = "";

		if (formmode.equals("add")) {

			TimesheetManagement up = timesheetManagement;

			up.setEntity_flg("Y");

			timesheetManagementRep.save(up);

			msg = "Added Successfully";
			
		} else if (formmode.equals("edit")) {

			TimesheetManagement up = timesheetManagement;

			up.setEntity_flg("Y");
			timesheetManagementRep.save(up);
			msg = "Edited Successfully";
		}

		return msg;
	}

	// Get User

	public TimesheetManagement getTimesheetUser(String id) {

		if (timesheetManagementRep.existsById(id)) {
			TimesheetManagement up = timesheetManagementRep.findById(id).get();
			up.getEntity_flg();
			return up;
		} else {
			return new TimesheetManagement();
		}
	};

//	Get List

	public List<TimesheetManagement> getTimesheetManagementList(String id, String name) {

		List<TimesheetManagement> users = timesheetManagementRep.findByParam(id, name);

		return users;

	}
//==================================JASPER==============
	public File getFile(String inv_no,  String filetype) throws FileNotFoundException, JRException, SQLException {

		System.out.println("0000");
		//logger.info(pdfgenerator);
		String path = env.getProperty("output.exportpath");
	//	D:\JasperDownload
		
		System.out.println(path);
		
		String fileName = "";
		String zipFileName = "";
		File outputFile;
		

		logger.info("Getting Output file : Third_PARTY");

		fileName = "INVOICE_" + inv_no;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			//logger.info("Getting Jasper file :" + "Third_PARTY");

			if (filetype.equals("pdf")) {
				System.out.println("inner pdf");
					jasperFile = this.getClass()
							.getResourceAsStream("/static/jasper/Invoice_Master1.jrxml");
				}else {
				
					jasperFile = this.getClass()
							.getResourceAsStream("/static/jasper/Invoice_Master1.jrxml");
				
			}

			System.out.println("#####");
			JasperReport jr = JasperCompileManager.compileReport(jasperFile);
			System.out.println("@@@@@@@@");
			System.out.println(jr);
			System.out.println("@@@@@@@@");
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			//logger.info("Assigning Parameters for Jasper");
			
			
			map.put("INV_NO", inv_no);
			
			if (filetype.equals("pdf")) {
				fileName = fileName + ".pdf";
				path = path+fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JasperExportManager.exportReportToPdfFile(jp, path);
				
			}else {

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
//============================================JASPER END ============================
	
	
//	public String exportReport(String reportFormat) throws FileNotFoundException,JRException {
//		
//		List<InvoiceMaster> invoice = (List<InvoiceMaster>) invoiceMasterRep.findAll();
//		//load a file and compile it
//		File file = org.springframework.util.ResourceUtils.getFile("classpath:Invoice_Master.jrxml");
//		JasperReport jasper = JasperCompileManager.compileReport(file.getAbsolutePath());
//		JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(invoice);
//		Map<String, Object> parameters = new HashMap<>();
//		parameters.put("CreatedBy", "invoice");
//		JasperPrint jasperprint = JasperFillManager.fillReport(jasper, parameters, datasource);
//		
//		if(reportFormat.equalsIgnoreCase("pdf")) {
//			
//			JasperExportManager.exportReportToPdfFile(jasperprint, "C:\\invoice"+"invoice.pdf");
//
//		}
//		return "";
//		
//	}

	
	
	//==========================Bank Master================================
	
	@SuppressWarnings("unchecked")
	public Object getBanklist() {
		 List<BankMaster> Banklist =(List<BankMaster>)
			        sessionFactory.getCurrentSession().createQuery("from BankMaster where del_flg='N' order by BANK_SRL_NO").getResultList();

		return Banklist;
	}
	
	public String addBankuser(BankMaster bankMaster, String formmode,String user) {
        String msg = "";
        try {
        	DecimalFormat numformate = new DecimalFormat("000");
        	BigDecimal SRLNUMBER = (BigDecimal) sessionFactory.getCurrentSession().createNativeQuery("SELECT B_SRL_NO.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();
			String srlno = numformate.format(SRLNUMBER);
			String u="00";
			
        	BankMaster up = bankMaster;
        	up.setBank_srl_no(srlno);
        	up.setEntity_flg("N");
        	up.setDel_flg("N");
        	up.setModify_flg("N");
        	up.setEntry_user(user);
        	up.setEntry_time(new Date());
        	bankMasterRep.save(up);
             msg = "Bank Added Successfully";
        } catch (Exception e) {
            msg = "Error Occured. Please contact Administrator";
            e.printStackTrace();
        }
        return msg;
    }
	
	public String bankMasterModify(BankMaster bankMaster , String bank_srl_no , String user) {
		
		String msg="";
		try {
			
			//ClientMaster clientMasterOld = clientMasterRep.getClientlist(client_srl_no) ;
			//clientMasterOld.setClient_srl_no(clientMaster.getClient_srl_no());
			BankMaster bankold = bankMasterRep.getBanklist(bank_srl_no);
			
			bankold.setBank_srl_no(bankMaster.getBank_srl_no());
			bankold.setBank_name(bankMaster.getBank_name());
			bankold.setBank_br(bankMaster.getBank_br());
			bankold.setBank_addr(bankMaster.getBank_addr());
			bankold.setAcct_type(bankMaster.getAcct_type());
			bankold.setAcc_number(bankMaster.getAcc_number());
			bankold.setAcc_name(bankMaster.getAcc_name());
			bankold.setAcct_crncy(bankMaster.getAcct_crncy());
			bankold.setIfsc_code(bankMaster.getIfsc_code());
			bankold.setSwift_code(bankMaster.getSwift_code());
			bankold.setPayment_code(bankMaster.getPayment_code());
			bankold.setModify_user(user);
			bankold.setModify_time(new Date());
			bankold.setEntity_flg("N");
			bankold.setModify_flg("Y");
			bankMasterRep.save(bankold);
			
			msg="Bank Modified Successfully";
		} catch (Exception e) {
			msg = "Error Occured. Please contact Administrator";
			e.printStackTrace();
		}
		return msg;
		
	}
	
public String bankMasterVerify(BankMaster bankMaster, String bank_srl_no, String user) {
		
		String msg="";
		try {
			
			BankMaster bankold = bankMasterRep.getBanklist(bank_srl_no);
			
				
				bankold.setBank_srl_no(bankMaster.getBank_srl_no());
				bankold.setBank_name(bankMaster.getBank_name());
				bankold.setBank_br(bankMaster.getBank_br());
				bankold.setBank_addr(bankMaster.getBank_addr());
				bankold.setAcct_type(bankMaster.getAcct_type());
				bankold.setVerify_user(user);
				bankold.setVerify_time(new Date());
				bankold.setEntity_flg("Y");
				bankold.setModify_flg("N");
				bankMasterRep.save(bankold);
				msg="Verified succesfully";
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
public String bankMasterDelete(BankMaster bankMaster, String bank_srl_no, String user) {
	
	String msg="";
	try {
		BankMaster bankold = bankMasterRep.getBanklist(bank_srl_no);
		
		
		
			//System.out.println(clientMaster.getClient_srl_no());
			bankold.setBank_srl_no(bankMaster.getBank_srl_no());
			bankold.setDel_flg("Y");
			//clientMasterOld.setEntity_flg("N");
			bankMasterRep.save(bankold);
			msg="Deleted succesfully";
			
			
			
		
	}
	catch (Exception e) {
		e.printStackTrace();
	}
	return msg;
}
	
	
	//==========================Client Master===========================
	@SuppressWarnings("unchecked")
	public Object getClientlist() {
		 List<ClientMaster> Clientlist =(List<ClientMaster>)
			        sessionFactory.getCurrentSession().createQuery("from ClientMaster where del_flg='N' order by CLIENT_SRL_NO").getResultList();

		return Clientlist;
	}
	
	public String addClientUser(ClientMaster clientMaster, String formmode, String user) {
        String msg = "";
        try {
        	
        	DecimalFormat numformate = new DecimalFormat("VEN000");
        	BigDecimal ClientNumber = (BigDecimal) sessionFactory.getCurrentSession().createNativeQuery("SELECT CLI_SRL_NO.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();
			String srlno = numformate.format(ClientNumber);
			
			
        	ClientMaster up = clientMaster;
        	up.setClient_srl_no(srlno);
        	up.setDel_flg("N");
			up.setModify_flg("N");
			up.setEntity_flg("N");
			up.setEntry_user(user);
			up.setEntry_time(new Date());
        	
            clientMasterRep.save(up);
             msg = "Client Added Successfully";
        } catch (Exception e) {
            msg = "Error Occured. Please contact Administrator";
            e.printStackTrace();
        }
        return msg;
    }

	
	public String clientMasterModify(ClientMaster clientMaster, String client_srl_no, String user) {
		
		String msg="";
		try {
			ClientMaster clientMasterOld = clientMasterRep.getClientlist(client_srl_no) ; 
			
				
				clientMasterOld.setClient_srl_no(clientMaster.getClient_srl_no());
				clientMasterOld.setPhone(clientMaster.getPhone());
				clientMasterOld.setContact_person(clientMaster.getContact_person());
				clientMasterOld.setClient_type(clientMaster.getClient_type());
				clientMasterOld.setClient_name(clientMaster.getClient_name());
				clientMasterOld.setClient_addr(clientMaster.getClient_addr());
				clientMasterOld.setEmail(clientMaster.getEmail());
				clientMasterOld.setClient_location(clientMaster.getClient_location());
				clientMasterOld.setCin_no(clientMaster.getCin_no());
				clientMasterOld.setGstin(clientMaster.getGstin());
				clientMasterOld.setHsn_code(clientMaster.getHsn_code());
				clientMasterOld.setVendor_code(clientMaster.getVendor_code());
				clientMasterOld.setBill_to(clientMaster.getBill_to());
				clientMasterOld.setDeliver_to(clientMaster.getDeliver_to());
				clientMasterOld.setModify_user(user);
				clientMasterOld.setModify_time(new Date());

				clientMasterOld.setEntity_flg("N");
				clientMasterOld.setModify_flg("Y");
				
				clientMasterRep.save(clientMasterOld);
				msg="Modified succesfully";
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	public String clientMasterVerify(ClientMaster clientMaster, String client_srl_no, String user) {
		
		String msg="";
		try {
			ClientMaster clientMasterOld = clientMasterRep.getClientlist(client_srl_no) ; 
			
				
				clientMasterOld.setClient_srl_no(clientMaster.getClient_srl_no());
				clientMasterOld.setPhone(clientMaster.getPhone());
				clientMasterOld.setVerify_user(user);
				clientMasterOld.setVerify_time(new Date());
				clientMasterOld.setEntity_flg("Y");
				clientMasterOld.setModify_flg("N");
				
				clientMasterRep.save(clientMasterOld);
				msg="Verified succesfully";
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	public String clientMasterDelete(ClientMaster clientMaster, String client_srl_no) {
		
		String msg="";
		try {

			ClientMaster clientMasterOld = clientMasterRep.getClientlist(client_srl_no) ; 
			
	
				
				clientMasterOld.setDel_flg("Y");
				clientMasterOld.setEntity_flg("N");
				clientMasterRep.save(clientMasterOld);
				
				
				/*
				 * clientMasterOld.setClient_srl_no(clientMaster.getClient_srl_no());
				 * clientMasterOld.setClient_type(clientMaster.getClient_type());
				 * clientMasterOld.setClient_name(clientMaster.getClient_name());
				 * clientMasterOld.setClient_addr(clientMaster.getClient_addr());
				 * clientMasterOld.setContact_person(clientMaster.getContact_person());
				 * clientMasterOld.setPhone(clientMaster.getPhone());
				 * clientMasterOld.setEmail(clientMaster.getEmail());
				 * clientMasterOld.setClient_location(clientMaster.getClient_location());
				 * clientMasterOld.setCin_no(clientMaster.getCin_no());
				 * clientMasterOld.setGstin(clientMaster.getGstin());
				 * clientMasterOld.setHsn_code(clientMaster.getHsn_code());
				 * clientMasterOld.setVendor_code(clientMaster.getVendor_code());
				 * clientMasterOld.setBill_to(clientMaster.getBill_to());
				 * clientMasterOld.setDeliver_to(clientMaster.getDeliver_to());
				 */
				
				msg="Deleted succesfully";
				
				
				
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}

	
	@SuppressWarnings("unchecked")
	public Object getplacementlist2() {
		 List<InvoiceMaster> invoicelist =(List<InvoiceMaster>)
			        sessionFactory.getCurrentSession().createQuery("from InvoiceMaster").getResultList();

		return invoicelist;
	}
	
	public String addInvoiceUser(InvoiceMaster invoiceMaster, String formmode) {
        String msg = "";
        try {
        	InvoiceMaster up = invoiceMaster;
        	
        	
        	
        	invoiceMasterRep.save(up);
             msg = "Invoice Added Successfully";
        } catch (Exception e) {
            msg = "Error Occured. Please contact Administrator";
            e.printStackTrace();
        }
        return msg;
    }
	
	public String flagentity;
	
	public String invoiceMasterModify(InvoiceMaster invoiceMaster, String po_id) {
		  int count = 1; 
		  PlacementMaintenance placementMaintenance ;
		 // System.out.println(placementMaintenance.getEntity_flg()+"here is the first strp for checking the flag ");
		String msg="modified";	
		String msg1="Not Set";
		try {
			InvoiceMaster invoiceold = invoiceMasterRep.getplacementlist2(po_id);
			System.out.println(invoiceMaster.getPo_id());
			
			System.out.println(invoiceMaster.getInv_no());
			System.out.println(invoiceMaster.getInv_due_date());
			System.out.println(invoiceMaster.getStatus());	
			System.out.println(invoiceMaster.getAcct_no());
			
			
			
			invoiceold.setInv_no(invoiceMaster.getInv_no());
			invoiceold.setInv_due_date(invoiceMaster.getInv_due_date());
			invoiceold.setInv_date(invoiceMaster.getInv_date());
			invoiceold.setStatus(invoiceMaster.getStatus());
			invoiceold.setTotal_amt(invoiceMaster.getTotal_amt());
			invoiceold.setEmp_id(invoiceMaster.getEmp_id());
			invoiceold.setEmp_name(invoiceMaster.getEmp_name());
			invoiceold.setPm_email(invoiceMaster.getPm_email());
			invoiceold.setProj_mgr(invoiceMaster.getProj_mgr());
			invoiceold.setPo_delivery_date(invoiceMaster.getPo_delivery_date());
			invoiceold.setPo_rate_inr(invoiceMaster.getPo_rate_inr());
			invoiceold.setGrn_efforts(invoiceMaster.getGrn_efforts());
			invoiceold.setGrn_no(invoiceMaster.getGrn_no());
			invoiceold.setGrn_amt(invoiceMaster.getGrn_amt());
			invoiceold.setInv_cgst(invoiceMaster.getInv_cgst());
			invoiceold.setInv_sgst(invoiceMaster.getInv_sgst());
			invoiceold.setInv_igst(invoiceMaster.getInv_igst());
			invoiceold.setInv_tot_gst(invoiceMaster.getInv_igst());
			invoiceold.setInv_tot_amt(invoiceMaster.getInv_tot_amt());
			invoiceold.setGrn_date(invoiceMaster.getGrn_date());
			invoiceold.setAcct_no(invoiceMaster.getAcct_no());
			invoiceMasterRep.save(invoiceold);
			
			 placementMaintenance=placementmaintenancerep.getplacementlist2(po_id);
			System.out.println("checkinggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggg"+placementMaintenance);
			placementMaintenance.setProj_mgr(invoiceMaster.getProj_mgr());
			placementMaintenance.setPm_email(invoiceMaster.getPm_email());
			placementMaintenance.setGrn_no(invoiceMaster.getGrn_no());
			System.out.println(placementMaintenance.getGrn_no()+"grn_nooooooooooooooooooooooooooooooooooooooooooooooooooo");
			placementMaintenance.setGrn_amt(invoiceMaster.getGrn_amt());
			placementMaintenance.setInv_cgst(invoiceMaster.getInv_cgst());
			placementMaintenance.setInv_sgst(invoiceMaster.getInv_sgst());
			placementMaintenance.setInv_igst(invoiceMaster.getInv_igst());
			placementMaintenance.setInv_tot_gst(invoiceMaster.getInv_igst());
			placementMaintenance.setInv_tot_amt(invoiceMaster.getInv_tot_amt());
			placementMaintenance.setGrn_efforts(invoiceMaster.getGrn_efforts());
			placementMaintenance.setPo_rate_inr(invoiceMaster.getPo_rate_inr());
			placementMaintenance.setPo_delivery_date(invoiceMaster.getPo_delivery_date());
			placementMaintenance.setEmp_id(invoiceMaster.getEmp_id());
			placementMaintenance.setInv_no(invoiceMaster.getInv_no());
			placementMaintenance.setInv_due_date(invoiceMaster.getInv_due_date());
			placementMaintenance.setInv_date(invoiceMaster.getInv_date());
			placementMaintenance.setEmp_name(invoiceMaster.getEmp_name());
			placementMaintenance.setGrn_date(invoiceMaster.getGrn_date());
			placementMaintenance.setAcct_no(invoiceMaster.getAcct_no());
			flagentity=placementMaintenance.getEntity_flg();
			System.out.println("flagentityyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy"+flagentity);
			placementMaintenance.setEntity_flg("N");
			
			System.out.println("success in this service");
		
			placementmaintenancerep.save(placementMaintenance);
			
			
			
			
			
			msg="Modified Successfully";
			
			
			/*
			 * if (flagentity.equals("N")) {
			 * System.out.println(placementMaintenance.getEntity_flg()
			 * +"Nhere the second step for checking the entity flag N"); System.out.
			 * println("HERE IT IS ALREADY IN DATABASE SO NO CHANGES  ARE ACCOURE IN GST ");
			 * 
			 * }
			 */

			 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		 placementMaintenance=placementmaintenancerep.getplacementlist2(po_id);	
		 if (!"N".equals(flagentity)) {
			    // This block will execute if flagentity is either null or not "N"
			

			System.out.println("here gst can be meodify the entity flag is null");
		try {
			String po=po_id;
			 if ("Modified Successfully".equals(msg)) {
			        System.out.println("1st STAGE TO THE GST SUBMIT");

			    	 placementMaintenance=placementmaintenancerep.getplacementlist2(po);		    	
			    	System.out.println("stage 1 of TRM SUBMIT " );
		        System.out.println(placementMaintenance.getInv_no() + " invoice number for trm");

		        Account_Ledger_Entity accountLedgerEntity = new Account_Ledger_Entity();
		        BTM_TRANS_POINT_DETAILS BTM_TRANS_POINT_DETAILS = new BTM_TRANS_POINT_DETAILS();

		       List<Account_Ledger_Entity> accountLedgerEntity1 =account_Ledger_Rep.findbyid1();
		       
		        if (accountLedgerEntity1 == null || accountLedgerEntity1.isEmpty()) {
		        	 accountLedgerEntity.setTran_id("TT001");
		        	 System.out.println("it is null and empty");

			            BigDecimal count11 = new BigDecimal(1); 
				        accountLedgerEntity.setPart_tran_id(count11);
				        accountLedgerEntity.setAcct_num("1100001196");
				        accountLedgerEntity.setAcct_name(placementMaintenance.getAcct_name());
				        accountLedgerEntity.setTran_type("TRANSFER");
				        accountLedgerEntity.setPart_tran_type("DEBIT");
				        accountLedgerEntity.setAcct_crncy("0.00");
				        String invTotAmtStr = placementMaintenance.getInv_tot_amt();
				        
				        // Remove commas or spaces if any
				        invTotAmtStr = invTotAmtStr != null ? invTotAmtStr.replace(",", "").trim() : "0.00";

				        // Convert to BigDecimal
				        BigDecimal tranAmt = new BigDecimal(invTotAmtStr);

				        // Set the amount in AccountLedgerEntity
				        accountLedgerEntity.setTran_amt(tranAmt);
				      
				        accountLedgerEntity.setTran_particular(placementMaintenance.getInv_no());
				        accountLedgerEntity.setTran_remarks(placementMaintenance.getInv_no());
				        accountLedgerEntity.setTran_date(placementMaintenance.getInv_date());
				        accountLedgerEntity.setValue_date(placementMaintenance.getInv_date());
				        accountLedgerEntity.setPartition_type("INFY_EDGVER");
				        accountLedgerEntity.setPartition_det(placementMaintenance.getInv_no());
				        accountLedgerEntity.setEntry_user("System");
				        accountLedgerEntity.setEntry_time(new Date());  // Set to current date and time
				        accountLedgerEntity.setTran_status("TEMPLATE");
				        accountLedgerEntity.setDel_flg("N");
				        accountLedgerEntity.setModify_user("System");
				        accountLedgerEntity.setModify_time(new Date());
				        String invTotGstStr = placementMaintenance.getInv_tot_gst();

				        if (invTotGstStr != null && !invTotGstStr.trim().isEmpty()) {
				            // Remove any potential commas or extra spaces before conversion
				            invTotGstStr = invTotGstStr.replace(",", "").trim();

				            // Convert the cleaned string to BigDecimal
				            BigDecimal gstTotal = new BigDecimal(invTotGstStr);
				            accountLedgerEntity.setGst_amount(gstTotal);
				        } else {
				            // Handle the case where inv_tot_gst is null or empty
				            accountLedgerEntity.setGst_amount(BigDecimal.ZERO); // or some default value
				        }
				        accountLedgerEntity.setGst_type("RECIVED");
						/*
						 * DecimalFormat numformate = new DecimalFormat(""); BigDecimal PlacementSrlNo =
						 * (BigDecimal) sessionFactory.getCurrentSession()
						 * .createNativeQuery("SELECT SRL_NO_SEQ.NEXTVAL AS SRL_NO FROM DUAL").
						 * getSingleResult(); String pmsrlno = numformate.format(PlacementSrlNo);
						 * accountLedgerEntity.setSrl_no_seq(pmsrlno);
						 */
				        
				        account_Ledger_Rep.save(accountLedgerEntity);
		        }
		        else {
		        

		            String a = account_Ledger_Rep.getlast();
		            
		            String b = a.substring(2); // Use index 2 to extract after "TT"
		            int c = Integer.parseInt(b) + 1;
		            String d = a.substring(0, 2); // Use index 0 to 2 to keep "TT"
		            
		            accountLedgerEntity.setTran_id(d + String.format("%03d", c));
	           
	            BigDecimal count11 = new BigDecimal(1); 
		        accountLedgerEntity.setPart_tran_id(count11);
		        accountLedgerEntity.setAcct_num("1100001196");
		        accountLedgerEntity.setAcct_name(placementMaintenance.getAcct_name());
		        accountLedgerEntity.setTran_type("TRANSFER");
		        accountLedgerEntity.setPart_tran_type("DEBIT");
		        accountLedgerEntity.setAcct_crncy("0.00");
		        String invTotAmtStr = placementMaintenance.getInv_tot_amt();
		        
		        // Remove commas or spaces if any
		        invTotAmtStr = invTotAmtStr != null ? invTotAmtStr.replace(",", "").trim() : "0.00";

		        // Convert to BigDecimal
		        BigDecimal tranAmt = new BigDecimal(invTotAmtStr);

		        // Set the amount in AccountLedgerEntity
		        accountLedgerEntity.setTran_amt(tranAmt);
		        accountLedgerEntity.setTran_particular(placementMaintenance.getInv_no());
		        accountLedgerEntity.setTran_remarks(placementMaintenance.getInv_no());
		        accountLedgerEntity.setTran_date(placementMaintenance.getInv_date());
		        accountLedgerEntity.setValue_date(placementMaintenance.getInv_date());
		        accountLedgerEntity.setTran_ref_no("INFY_EDGVER");
		        accountLedgerEntity.setPartition_type("INFY_EDGVER");
		        accountLedgerEntity.setPartition_det(placementMaintenance.getInv_no());
		        accountLedgerEntity.setEntry_user("System");
		        accountLedgerEntity.setEntry_time(new Date());  // Set to current date and time
		        accountLedgerEntity.setTran_status("TEMPLATE");
		        accountLedgerEntity.setDel_flg("N");
		        accountLedgerEntity.setModify_user("System");
		        accountLedgerEntity.setModify_time(new Date());
		        String invTotGstStr = placementMaintenance.getInv_tot_gst();

		        if (invTotGstStr != null && !invTotGstStr.trim().isEmpty()) {
		            // Remove any potential commas or extra spaces before conversion
		            invTotGstStr = invTotGstStr.replace(",", "").trim();

		            // Convert the cleaned string to BigDecimal
		            BigDecimal gstTotal = new BigDecimal(invTotGstStr);
		            accountLedgerEntity.setGst_amount(gstTotal);
		        } else {
		            // Handle the case where inv_tot_gst is null or empty
		            accountLedgerEntity.setGst_amount(BigDecimal.ZERO); // or some default value
		        };
		        accountLedgerEntity.setGst_type("RECIVED");
				/*
				 * DecimalFormat numformate = new DecimalFormat(""); BigDecimal PlacementSrlNo =
				 * (BigDecimal) sessionFactory.getCurrentSession()
				 * .createNativeQuery("SELECT SRL_NO_SEQ.NEXTVAL AS SRL_NO FROM DUAL").
				 * getSingleResult(); String pmsrlno = numformate.format(PlacementSrlNo);
				 * accountLedgerEntity.setSrl_no_seq(pmsrlno);
				 */
		        
		        
		                 
		        BTM_TRANS_POINT_DETAILS.setOrg_tran_id(d + String.format("%03d", c));
		        BigDecimal count11tpt = new BigDecimal(1); 
		        BTM_TRANS_POINT_DETAILS.setOrg_part_tran_id(count11tpt);
		        BTM_TRANS_POINT_DETAILS.setOrg_acct_num("1100001196");
		        BTM_TRANS_POINT_DETAILS.setOrg_acct_name(placementMaintenance.getAcct_name());
		        BTM_TRANS_POINT_DETAILS.setOrg_tran_type("TRANSFER");
		        BTM_TRANS_POINT_DETAILS.setOrg_part_tran_type("DEBIT");
		        BTM_TRANS_POINT_DETAILS.setOrg_acct_crncy("0.00");
		        String invTotAmtStrtpt = placementMaintenance.getInv_tot_amt();
		        
		        // Remove commas or spaces if any
		        invTotAmtStr = invTotAmtStr != null ? invTotAmtStr.replace(",", "").trim() : "0.00";

		        // Convert to BigDecimal
		        BigDecimal tranAmttpt = new BigDecimal(invTotAmtStr);

		        // Set the amount in AccountLedgerEntity
		        BTM_TRANS_POINT_DETAILS.setOrg_tran_amt(tranAmttpt);
		        BTM_TRANS_POINT_DETAILS.setOrg_tran_particular(placementMaintenance.getInv_no());
		        BTM_TRANS_POINT_DETAILS.setOrg_tran_remarks(placementMaintenance.getInv_no());
		        java.util.Date utilDate = placementMaintenance.getInv_date();
		        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		        BTM_TRANS_POINT_DETAILS.setOrg_tran_date(sqlDate);
		        java.util.Date utilDate1 = placementMaintenance.getInv_date();
		        java.sql.Date sqlDate1 = new java.sql.Date(utilDate1.getTime());
		        BTM_TRANS_POINT_DETAILS.setOrg_value_date(sqlDate1);
		        BTM_TRANS_POINT_DETAILS.setOrg_tran_ref_no("INFY_EDGVER");
		        BTM_TRANS_POINT_DETAILS.setOrg_partition_type("INFY_EDGVER");
		        BTM_TRANS_POINT_DETAILS.setOrg_partition_det(placementMaintenance.getInv_no());

		        BTM_TRANS_POINT_DETAILS.setDel_flg('N');
		        BTM_TRANS_POINT_DETAILS.setBal_outstd_amt(tranAmttpt);
		        
		  
			
		        BTM_TRANS_POINT_DETAILSRepo.save(BTM_TRANS_POINT_DETAILS);
		        account_Ledger_Rep.save(accountLedgerEntity);
		        }
		       
		        
		        
		        
		    }
		    
		    if (msg.equals("Modified Successfully")) { 
		    	System.out.println("2ND STAGE TO THE GST SUBMIT");
		         placementMaintenance = placementmaintenancerep.getplacementlist2(po); // Get the invoice object
		        System.out.println("stage 1 of TRM SUBMIT " + placementMaintenance);
		        System.out.println(placementMaintenance.getInv_no() + " invoice number for trm");

		        Account_Ledger_Entity accountLedgerEntity = new Account_Ledger_Entity(); 
		        BTM_TRANS_PARTITION_DETAILS BTM_TRANS_PARTITION_DETAILS=new BTM_TRANS_PARTITION_DETAILS();

		        String a = account_Ledger_Rep.getlast();
	            
	            String b = a.substring(2); // Use index 2 to extract after "TT"
	            int c = Integer.parseInt(b) + 1;
	            String d = a.substring(0, 2); // Use index 0 to 2 to keep "TT"
	            
	            accountLedgerEntity.setTran_id(d + String.format("%03d", c));
	            
	            BigDecimal count2 = new BigDecimal(2); 
		        accountLedgerEntity.setPart_tran_id(count2);
		        accountLedgerEntity.setAcct_num("4400004415");
		        accountLedgerEntity.setAcct_name("INCOME FROM PLACEMENTS-INDIA");
		        accountLedgerEntity.setTran_type("TRANSFER");
		        accountLedgerEntity.setPart_tran_type("CREDIT");
		        accountLedgerEntity.setAcct_crncy("0.00");
		      //  BigDecimal invamt = new BigDecimal(placementMaintenance.getGrn_amt().toString()); 
		        String grnAmtStr = placementMaintenance.getGrn_amt();

		        if (grnAmtStr != null && !grnAmtStr.trim().isEmpty()) {
		            // Remove any potential commas or extra spaces before conversion
		            grnAmtStr = grnAmtStr.replace(",", "").trim();

		            // Convert the cleaned string to BigDecimal
		            BigDecimal tranAmt = new BigDecimal(grnAmtStr);
		            accountLedgerEntity.setTran_amt(tranAmt);
		        } else {
		            // Handle the case where grn_amt is null or empty
		            accountLedgerEntity.setTran_amt(BigDecimal.ZERO); // or some default value
		        }
		        accountLedgerEntity.setTran_particular(placementMaintenance.getInv_no());
		        accountLedgerEntity.setTran_remarks(placementMaintenance.getInv_no());
		        accountLedgerEntity.setTran_date(placementMaintenance.getInv_date());
		        accountLedgerEntity.setValue_date(placementMaintenance.getInv_date());
		        accountLedgerEntity.setTran_ref_no("INFY_EDGVER");
		        accountLedgerEntity.setPartition_type("INFY_EDGVER");
		        accountLedgerEntity.setPartition_det(placementMaintenance.getInv_no());
		        accountLedgerEntity.setEntry_user("System");
		        accountLedgerEntity.setEntry_time(new Date());  // Set to current date and time
		        accountLedgerEntity.setTran_status("TEMPLATE");
		        accountLedgerEntity.setDel_flg("N");
		        accountLedgerEntity.setModify_user("System");
		        accountLedgerEntity.setModify_time(new Date());
		        String invTotGstStr = placementMaintenance.getInv_tot_gst();

		        if (invTotGstStr != null && !invTotGstStr.trim().isEmpty()) {
		            // Remove any potential commas or extra spaces before conversion
		            invTotGstStr = invTotGstStr.replace(",", "").trim();

		            // Convert the cleaned string to BigDecimal
		            BigDecimal gstTotal = new BigDecimal(invTotGstStr);
		            accountLedgerEntity.setGst_amount(gstTotal);
		        } else {
		            // Handle the case where inv_tot_gst is null or empty
		            accountLedgerEntity.setGst_amount(BigDecimal.ZERO); // or some default value
		        }
		        accountLedgerEntity.setGst_type("RECIVED");
		        
		        
		        
		        BTM_TRANS_PARTITION_DETAILS.setTran_id(d + String.format("%03d", c));
		   
	            
	           
	            
	            BigDecimal count2partitiondetail = new BigDecimal(2); 
	            BTM_TRANS_PARTITION_DETAILS.setPart_tran_id(count2partitiondetail);
	            BTM_TRANS_PARTITION_DETAILS.setAcct_num("4400004415");
	            BTM_TRANS_PARTITION_DETAILS.setAcct_name("INCOME FROM PLACEMENTS-INDIA");
	            BTM_TRANS_PARTITION_DETAILS.setTran_type("TRANSFER");
	            BTM_TRANS_PARTITION_DETAILS.setPart_tran_type("CREDIT");
	            BTM_TRANS_PARTITION_DETAILS.setAcct_crncy("0.00");
		      //  BigDecimal invamt = new BigDecimal(placementMaintenance.getGrn_amt().toString()); 
		        String grnAmtStrpartiondetail = placementMaintenance.getGrn_amt();

		        if (grnAmtStrpartiondetail != null && !grnAmtStrpartiondetail.trim().isEmpty()) {
		            // Remove any potential commas or extra spaces before conversion
		        	grnAmtStrpartiondetail = grnAmtStrpartiondetail.replace(",", "").trim();

		            // Convert the cleaned string to BigDecimal
		            BigDecimal tranAmt = new BigDecimal(grnAmtStrpartiondetail);
		            BTM_TRANS_PARTITION_DETAILS.setTran_amt(tranAmt);
		        } else {
		            // Handle the case where grn_amt is null or empty
		        	BTM_TRANS_PARTITION_DETAILS.setTran_amt(BigDecimal.ZERO); // or some default value
		        }
		        BTM_TRANS_PARTITION_DETAILS.setTran_particular(placementMaintenance.getInv_no());
		        BTM_TRANS_PARTITION_DETAILS.setTran_remarks(placementMaintenance.getInv_no());
		        
		        
		        java.util.Date utilDatePARTITION = placementMaintenance.getInv_date();
		        java.sql.Date sqlDatePARTION = new java.sql.Date(utilDatePARTITION.getTime());
		        BTM_TRANS_PARTITION_DETAILS.setTran_date(sqlDatePARTION);
		        java.util.Date utilDate1PARTION = placementMaintenance.getInv_date();
		        java.sql.Date sqlDate1PARTION = new java.sql.Date(utilDate1PARTION.getTime());
		        BTM_TRANS_PARTITION_DETAILS.setValue_date(sqlDate1PARTION);
		        BTM_TRANS_PARTITION_DETAILS.setTran_ref_no("INFY_EDGVER");
		        BTM_TRANS_PARTITION_DETAILS.setPartition_type("INFY_EDGVER");
		        BTM_TRANS_PARTITION_DETAILS.setPartition_det(placementMaintenance.getInv_no());
		       
		        String invTotGstStrpartiondetails = placementMaintenance.getInv_tot_gst();

		        if (invTotGstStrpartiondetails != null && !invTotGstStrpartiondetails.trim().isEmpty()) {
		            // Remove any potential commas or extra spaces before conversion
		        	invTotGstStrpartiondetails = invTotGstStrpartiondetails.replace(",", "").trim();

		            // Convert the cleaned string to BigDecimal
		            BigDecimal gstTotal = new BigDecimal(invTotGstStrpartiondetails);
		            BTM_TRANS_PARTITION_DETAILS.setGst_amount(gstTotal);
		        } else {
		            // Handle the case where inv_tot_gst is null or empty
		        	BTM_TRANS_PARTITION_DETAILS.setGst_amount(BigDecimal.ZERO); // or some default value
		        }
		        BTM_TRANS_PARTITION_DETAILS.setGst_type("RECIVED");
		        
		     
		        account_Ledger_Rep.save(accountLedgerEntity);
		        BTM_TRANS_PARTITION_DETAILSrepo.save(BTM_TRANS_PARTITION_DETAILS);

		    }
		   
		    if (msg.equals("Modified Successfully")) {
		    	System.out.println("3ND STAGE TO THE GST SUBMIT");
		         placementMaintenance = placementmaintenancerep.getplacementlist2(po); // Get the invoice object
		        System.out.println("stage 1 of TRM SUBMIT " + placementMaintenance);
		        System.out.println(placementMaintenance.getInv_no() + " invoice number for trm");

		        Account_Ledger_Entity accountLedgerEntity = new Account_Ledger_Entity();
		        BTM_GST_MASTER BTM_GST_MASTER=new BTM_GST_MASTER();
		        BTM_REV_PART_TRAN BTM_REV_PART_TRAN=new BTM_REV_PART_TRAN();
		        // Instantiate the entity
		       // int count = 1; 
		        String a = account_Ledger_Rep.getlast();
	            
	            String b = a.substring(2); // Use index 2 to extract after "TT"
	            int c = Integer.parseInt(b) + 1;
	            String d = a.substring(0, 2); // Use index 0 to 2 to keep "TT"
	            
	            accountLedgerEntity.setTran_id(d + String.format("%03d", c));
	            BigDecimal count3 = new BigDecimal(3); 
		        accountLedgerEntity.setPart_tran_id(count3);
		        accountLedgerEntity.setAcct_num("2700002710");
		        accountLedgerEntity.setAcct_name("GST Receiopts");
		        accountLedgerEntity.setTran_type("TRANSFER");
		        accountLedgerEntity.setPart_tran_type("CREDIT");
		        accountLedgerEntity.setAcct_crncy("0.00");
		        String invTotGst1 = placementMaintenance.getInv_tot_gst();

		     // Remove commas if the string uses them as thousands separators
		     invTotGst1 = invTotGst1.replace(",", "");

		     try {
		         BigDecimal gstAmount = new BigDecimal(invTotGst1);
		         accountLedgerEntity.setTran_amt(gstAmount);
		     } catch (NumberFormatException e) {
		         System.out.println("Invalid format for invTotGst1: " + invTotGst1);
		         // Handle the exception (e.g., log the error, set a default value, etc.)
		     }

		        accountLedgerEntity.setTran_particular(placementMaintenance.getInv_no());
		        accountLedgerEntity.setTran_remarks(placementMaintenance.getInv_no());
		        accountLedgerEntity.setTran_date(placementMaintenance.getInv_date());
		        accountLedgerEntity.setValue_date(placementMaintenance.getInv_date());
		        accountLedgerEntity.setPartition_type("INFY_EDGVER");
		        accountLedgerEntity.setPartition_det(placementMaintenance.getInv_no());
		        accountLedgerEntity.setEntry_user("System");
		        accountLedgerEntity.setEntry_time(new Date());  // Set to current date and time
		        accountLedgerEntity.setTran_status("TEMPLATE");
		        accountLedgerEntity.setDel_flg("N");
		        accountLedgerEntity.setModify_user("System");
		        accountLedgerEntity.setModify_time(new Date());
				
				//  BigDecimal gst_total = new BigDecimal(placementMaintenance.getInv_tot_gst());
		        String invTotGst = placementMaintenance.getInv_tot_gst(); // Assume it returns a String
		        if (invTotGst != null) {
		            // Remove commas if present (e.g., "1,234.56" becomes "1234.56")
		            invTotGst = invTotGst.replace(",", "").trim();

		            try {
		                BigDecimal gstAmount = new BigDecimal(invTotGst);
		                accountLedgerEntity.setGst_amount(gstAmount);
		            } catch (NumberFormatException e) {
		                System.out.println("Invalid numeric format: " + invTotGst);
		                // Handle the exception appropriately (e.g., set a default value or log the error)
		            }
		        } else {
		            System.out.println("invTotGst is null");
		            // Handle the case where invTotGst is null
		        }

				 
		        
		        String invTotGstStr = placementMaintenance.getInv_tot_gst();

		        if (invTotGstStr != null && !invTotGstStr.trim().isEmpty()) {
		            // Remove any potential commas or extra spaces before conversion
		            invTotGstStr = invTotGstStr.replace(",", "").trim();

		            // Convert the cleaned string to BigDecimal
		            BigDecimal gstTotal = new BigDecimal(invTotGstStr);
		            accountLedgerEntity.setGst_amount(gstTotal);
		        } else {
		            // Handle the case where inv_tot_gst is null or empty
		            accountLedgerEntity.setGst_amount(BigDecimal.ZERO); // or some default value
		        }
		        accountLedgerEntity.setGst_type("RECIVED");
		        
		        
		        
	            
		  
		        BTM_GST_MASTER.setTran_id(d + String.format("%03d", c));	
		        BTM_GST_MASTER.setPart_tran_id(("3"));
		       
		        BTM_GST_MASTER.setFin_year("2024-2025");
		        BTM_GST_MASTER.setPart_tran_type("CREDIT");
		        BTM_GST_MASTER.setGstin(placementMaintenance.getGstin());
		        BTM_GST_MASTER.setInvoice_no(placementMaintenance.getInv_no());
		      
		        
		        java.util.Date getInv_date = placementMaintenance.getInv_date();
		        java.sql.Date getInv_date1 = new java.sql.Date(getInv_date.getTime());
		        BTM_GST_MASTER.setInvoice_date(getInv_date1);
		        
		        BigDecimal invamountgst = (placementMaintenance.getInv_amt() != null) 
		        	    ? new BigDecimal(placementMaintenance.getInv_amt()) 
		        	    : BigDecimal.ZERO;

		        	BTM_GST_MASTER.setInv_amt(invamountgst);
		        	
		        	String invCgstStr = placementMaintenance.getInv_cgst();
		        	BigDecimal Inv_cgstgst;

		        	if (invCgstStr != null && !invCgstStr.trim().isEmpty()) {
		        	    try {
		        	        Inv_cgstgst = new BigDecimal(invCgstStr);
		        	    } catch (NumberFormatException e) {
		        	        Inv_cgstgst = BigDecimal.ZERO;
		        	       		        	    }
		        	} else {
		        	    Inv_cgstgst = BigDecimal.ZERO;
		        	}

		        	BTM_GST_MASTER.setInv_cgst(Inv_cgstgst);

		        	String invIgstStr = placementMaintenance.getInv_igst();
		        	BigDecimal getInv_igst;

		        	if (invIgstStr != null && !invIgstStr.trim().isEmpty()) {
		        	    try {
		        	        getInv_igst = new BigDecimal(invIgstStr);
		        	    } catch (NumberFormatException e) {
		        	        getInv_igst = BigDecimal.ZERO;
		        	      
		        	    }
		        	} else {
		        	    getInv_igst = BigDecimal.ZERO;
		        	}

		        	BTM_GST_MASTER.setInv_igst(getInv_igst);

		        	String invSgstStr = placementMaintenance.getInv_sgst();
		        	BigDecimal Inv_sgst1;

		        	if (invSgstStr != null && !invSgstStr.trim().isEmpty()) {
		        	    try {
		        	        Inv_sgst1 = new BigDecimal(invSgstStr);
		        	    } catch (NumberFormatException e) {
		        	
		        	        Inv_sgst1 = BigDecimal.ZERO;
		        	       
		        	    }
		        	} else {
		        	    Inv_sgst1 = BigDecimal.ZERO;
		        	}

		        	BTM_GST_MASTER.setInv_sgst(Inv_sgst1);

		        	String invTotGstStr1 = placementMaintenance.getInv_tot_gst();
		        	BigDecimal totalgst;

		        	if (invTotGstStr1 != null && !invTotGstStr1.trim().isEmpty()) {
		        	    try {
		        	        totalgst = new BigDecimal(invTotGstStr1);
		        	    } catch (NumberFormatException e) {
		        	       
		        	        totalgst = BigDecimal.ZERO;
		        	    
		        	    }
		        	} else {
		        	    totalgst = BigDecimal.ZERO;
		        	}

		        	BTM_GST_MASTER.setTotal_gst_amt(totalgst);

                
                
                String invTotAmtStr = placementMaintenance.getInv_tot_amt();
                BigDecimal Inv_tot_amt;

                if (invTotAmtStr != null && !invTotAmtStr.trim().isEmpty()) {
                    try {
                        Inv_tot_amt = new BigDecimal(invTotAmtStr);
                    } catch (NumberFormatException e) {
                       
                        Inv_tot_amt = BigDecimal.ZERO;
                   
                    }
                } else {
                    Inv_tot_amt = BigDecimal.ZERO;
                }

                BTM_GST_MASTER.setInv_tot_amt(Inv_tot_amt);

             
                java.util.Date sppaymentdate = placementMaintenance.getSp_paymt_date();
                java.sql.Date sppaymentdate2;

                if (sppaymentdate != null) {
                    sppaymentdate2 = new java.sql.Date(sppaymentdate.getTime());
                } else {
                    sppaymentdate2 = null; // or use a default date if needed
                   
                }

                BTM_GST_MASTER.setPayment_date(sppaymentdate2);

              
                java.util.Date Tran_dategst = placementMaintenance.getInv_date();
                java.sql.Date Tran_dategst1;

                if (Tran_dategst != null) {
                    Tran_dategst1 = new java.sql.Date(Tran_dategst.getTime());
                } else {
                    Tran_dategst1 = null; // or use a default date if required
                  
                }

                BTM_GST_MASTER.setTran_date(Tran_dategst1);

		        
                BTM_GST_MASTER.setPay_tran_id(d + String.format("%03d", c));
                BTM_GST_MASTER.setPay_part_tran_id(("3"));
                BTM_GST_MASTER.setTran_status(placementMaintenance.getStatus());
                BTM_GST_MASTER.setClient_remark(BTM_GST_MASTER.getClient_remark());
                
                
                
		        
	            BTM_REV_PART_TRAN.setRev_tran_crncy("0.00");
		      
	            BTM_REV_PART_TRAN.setRev_tran_ref_no("INFY_EDGVER");
	            String invTotGsttran= placementMaintenance.getInv_tot_gst(); // Assume it returns a String
	            if (invTotGsttran != null) {
	               
	                invTotGsttran = invTotGsttran.replace(",", "").trim();

	                try {
	                    BigDecimal revTranAmt = new BigDecimal(invTotGsttran);
	                    BTM_REV_PART_TRAN.setRev_tran_amt(revTranAmt);
	                } catch (NumberFormatException e) {
	                    System.out.println("Invalid numeric format for invTotGsttran: " + invTotGsttran);
	               
	                    BTM_REV_PART_TRAN.setRev_tran_amt(BigDecimal.ZERO); 
	                }
	            } else {
	                System.out.println("invTotGsttran is null");
	                BTM_REV_PART_TRAN.setRev_tran_amt(BigDecimal.ZERO); 
	            }

	            
	            
		        java.util.Date utilDaterev = placementMaintenance.getInv_date();
		        java.sql.Date sqlDatePARTIONrev = new java.sql.Date(utilDaterev.getTime());
		        BTM_REV_PART_TRAN.setRev_tran_date(sqlDatePARTIONrev);
		        java.util.Date utilDate1PARTIONrev = placementMaintenance.getInv_date();
		        java.sql.Date sqlDate1PARTIONrev = new java.sql.Date(utilDate1PARTIONrev.getTime());
		        BTM_REV_PART_TRAN.setRev_value_date(sqlDate1PARTIONrev);
		        
		        
	           
		        
		        
		        
		        BTM_GST_MASTERREPO.save(BTM_GST_MASTER);
		       account_Ledger_Rep.save(accountLedgerEntity);
		     
		         msg1="Setted into gst";
		    }
		   
		    
		    
		    
		    
		} catch (Exception e) {
		    e.printStackTrace();
		}
		}
		else {
			System.out.println("here gst is already exist ");
		}
		return msg+msg1;

	}
	
	public String CreatePMAdd(PlacementMaintenance placementmaintenance, String formmode) {
		String msg = "";
		if(formmode.equals("add")) {
			
			DecimalFormat numformate = new DecimalFormat("0");
        	BigDecimal PlacementSrlNo = (BigDecimal) sessionFactory.getCurrentSession().createNativeQuery("SELECT PM_SRL_NO.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();
			String pmsrlno = numformate.format(PlacementSrlNo);
	
			PlacementMaintenance up = placementmaintenance;
			String pn=placementmaintenance.getPo_no();
			String ei=placementmaintenance.getEmp_id();
			SimpleDateFormat formatdate = new SimpleDateFormat("ddMMyyyy");
			String date2=formatdate.format(placementmaintenance.getPo_date());
			String pd=date2;
			System.out.println(pmsrlno);
			up.setSrl_no(pmsrlno);
			up.setPo_id(pn+ei+pd);
			placementmaintenancerep.save(up);
			
			msg = "PlacementMaintenance Added Successfully";
		}else if(formmode.equals("edit")) {
			
			PlacementMaintenance up1=(PlacementMaintenance) placementmaintenancerep.getPoMain(placementmaintenance.getPo_no());
			//PlacementMaintenance[] up1Array = new PlacementMaintenance[]{placementmaintenancerep.getPoMain(placementmaintenance.getPo_no())};
			System.out.println(up1);
			up1.setPo_date(placementmaintenance.getPo_date());
			up1.setExtn_flg(placementmaintenance.getExtn_flg());
			up1.setExtn_date(placementmaintenance.getExtn_date());
			up1.setProj_mgr(placementmaintenance.getProj_mgr());
			up1.setPm_email(placementmaintenance.getPm_email());
			up1.setUnit_loc(placementmaintenance.getUnit_loc());
			up1.setGstin(placementmaintenance.getGstin());
			up1.setEmp_id(placementmaintenance.getEmp_id());
			up1.setEmp_name(placementmaintenance.getEmp_name());
			up1.setVendor(placementmaintenance.getVendor());
			up1.setLocation(placementmaintenance.getLocation());
			up1.setNo_of_items(placementmaintenance.getNo_of_items());
			up1.setTotal_value(placementmaintenance.getTotal_value());
			
			
			
			placementmaintenancerep.save(up1);
			msg = "PlacementMaintenance Modified Successfully";
		}else if(formmode.equals("modifyg")) {
			
			PlacementMaintenance up5=placementmaintenancerep.getPoM((placementmaintenance.getPo_id()).substring(0,placementmaintenance.getPo_id().indexOf(",")));
			System.out.println(placementmaintenance.getPo_no());
			System.out.println(placementmaintenance.getGrn_amt());
			System.out.println(placementmaintenance.getGrn_date());
			System.out.println(placementmaintenance.getGrn_efforts());
			System.out.println(placementmaintenance.getGrn_no());
			up5.setGrn_no(placementmaintenance.getGrn_no());
			up5.setGrn_date(placementmaintenance.getGrn_date());
			up5.setGrn_amt(placementmaintenance.getGrn_amt());
			up5.setGrn_efforts(placementmaintenance.getGrn_efforts());
			
			System.out.println(placementmaintenance.getGrn_date());
			
			placementmaintenancerep.save(up5);
			
			InvoiceMaster op5 = invoiceMasterRep.getplacementlist2((placementmaintenance.getPo_id()).substring(0,placementmaintenance.getPo_id().indexOf(",")));
			op5.setGrn_no(placementmaintenance.getPo_no());
			op5.setGrn_date(placementmaintenance.getGrn_date());
			op5.setGrn_amt(placementmaintenance.getGrn_amt());
			op5.setGrn_efforts(placementmaintenance.getGrn_efforts());
			invoiceMasterRep.save(op5);
			msg = "GRN Updated Successfully";
		}
		else if(formmode.equals("modifyI")) {
			
			PlacementMaintenance upi=placementmaintenancerep.getPoM((placementmaintenance.getPo_id()).substring(0,placementmaintenance.getPo_id().indexOf(",")));
			System.out.println(placementmaintenance.getEmp_name());
			upi.setInv_no(placementmaintenance.getInv_no());
			upi.setInv_date(placementmaintenance.getInv_date());
			upi.setPo_delivery_date(placementmaintenance.getPo_delivery_date());
			upi.setPo_rate_inr(placementmaintenance.getPo_rate_inr());
			upi.setGrn_efforts(placementmaintenance.getGrn_efforts());
			upi.setGrn_amt(placementmaintenance.getGrn_amt());
			upi.setInv_igst(placementmaintenance.getInv_igst());
			upi.setInv_tot_gst(placementmaintenance.getInv_igst());
			upi.setInv_tot_amt(placementmaintenance.getInv_tot_amt());
			upi.setInv_due_date(placementmaintenance.getInv_due_date());
			
			System.out.println(placementmaintenance.getGrn_date());
			
			placementmaintenancerep.save(upi);
			
			InvoiceMaster opi = invoiceMasterRep.getplacementlist2((placementmaintenance.getPo_id()).substring(0,placementmaintenance.getPo_id().indexOf(",")));
			opi.setInv_no(placementmaintenance.getInv_no());
			opi.setInv_date(placementmaintenance.getInv_date());
			opi.setPo_delivery_date(placementmaintenance.getPo_delivery_date());
			opi.setPo_rate_inr(placementmaintenance.getPo_rate_inr());
			opi.setGrn_efforts(placementmaintenance.getGrn_efforts());
			opi.setGrn_amt(placementmaintenance.getGrn_amt());
			opi.setInv_igst(placementmaintenance.getInv_igst());
			opi.setInv_tot_gst(placementmaintenance.getInv_igst());
			opi.setInv_tot_amt(placementmaintenance.getInv_tot_amt());
			opi.setInv_due_date(placementmaintenance.getInv_due_date());
			invoiceMasterRep.save(opi);
			msg = "Invoice Updated Successfully";
		}
		
		else if(formmode.equals("modify")) {
			System.out.println((placementmaintenance.getPo_id()).substring(0,placementmaintenance.getPo_id().indexOf(",")));
			PlacementMaintenance up2=placementmaintenancerep.getPoM((placementmaintenance.getPo_id()).substring(0,placementmaintenance.getPo_id().indexOf(",")));
			System.out.println(up2);
			System.out.println(placementmaintenance.getPo_id());
			System.out.println(placementmaintenance.getPo_rate_inr());
			System.out.println(placementmaintenance.getUnit_loc());
			System.out.println(placementmaintenance.getPo_delivery_date());
			System.out.println(placementmaintenance.getPo_item_no());
			System.out.println(placementmaintenance.getPo_qty());
			up2.setPo_date(placementmaintenance.getPo_date());	
			up2.setUnit_loc(placementmaintenance.getUnit_loc());
			up2.setPo_rate_inr(placementmaintenance.getPo_rate_inr());
			up2.setPo_amt_inr(placementmaintenance.getPo_amt_inr());
			up2.setPo_delivery_date(placementmaintenance.getPo_delivery_date());
			up2.setPo_item_no(placementmaintenance.getPo_item_no());
			up2.setPo_qty(placementmaintenance.getPo_qty());
			placementmaintenancerep.save(up2);
			
			System.out.println(up2);
			InvoiceMaster op2 = invoiceMasterRep.getplacementlist2((placementmaintenance.getPo_id()).substring(0,placementmaintenance.getPo_id().indexOf(",")));
			op2.setPo_delivery_date(placementmaintenance.getPo_delivery_date());
			op2.setLocation(placementmaintenance.getUnit_loc());
			op2.setPo_rate_inr(placementmaintenance.getPo_rate_inr());
			invoiceMasterRep.save(op2);
			System.out.println(op2);
			msg = "Purchase Order Modified Successfully";
		}
		return msg;
	}
	
	
	/*
	 * public PlacementMaintenance store(MultipartFile file) throws IOException {
	 * String fileName = file.getOriginalFilename(); PlacementMaintenance
	 * placementmaintenance = new
	 * PlacementMaintenance(UUID.randomUUID().toString(),fileName,
	 * file.getContentType(), file.getBytes()); return
	 * placementmaintenancerep.save(placementmaintenance); }
	 */
	
	public PlacementMaintenance getFileById(String id)
	{
		Optional <PlacementMaintenance> fileOptional=placementmaintenancerep.findById(id);
		
		if(fileOptional.isPresent()) {
			return fileOptional.get();
		}
		return null;
	}
	
	public List<PlacementMaintenance> getFileList()
	{
		return placementmaintenancerep.findAll();
		
	}

	public String upload(PlacementMaintenance placementmaintenance, String formmode) {

		String msg = "";
		try {
			
			FileInputStream file = new FileInputStream(new File("C:\\Users\\user\\Documents\\PO No Excel.xlsx"));
			
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			PlacementMaintenance up = placementmaintenance;
			
			up.setUpload_flg("Y");
			placementmaintenancerep.save(up);
			msg = "PlacementMaintenance Upload Successfully";
		} catch (Exception e) {
			msg = "Error Occured. Please contact Administrator";
			e.printStackTrace();
		}
		
		return msg;
	}

	public void save(MultipartFile file) {
		// TODO Auto-generated method stub
		
	}

	
	public File getFile1(String filetype) throws FileNotFoundException, JRException, SQLException {

		
		
		String path = env.getProperty("output.exportpath");
		System.out.println(path);
		
		String fileName = "";
		String zipFileName = "";
		File outputFile;
		

		logger.info("Getting Output file : Third_PARTY");

		fileName = "SPF_ALL";

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			

			if (filetype.equals("pdf")) {
				System.out.println("inner pdf");
					jasperFile = this.getClass()
							.getResourceAsStream("/static/jasper/SPF2.jrxml");
				}else {
				
					jasperFile = this.getClass()
							.getResourceAsStream("/static/jasper/SPF2.jrxml");
				
			}

			
			JasperReport jr = JasperCompileManager.compileReport(jasperFile);
			
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			//logger.info("Assigning Parameters for Jasper");
			
			
			//map.put("SRNO", "AA");
			
			if (filetype.equals("pdf")) {
				fileName = fileName + ".pdf";
				path = path+fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JasperExportManager.exportReportToPdfFile(jp, path);
				
			}else {

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
	
	
public File getFileesi(String filetype) throws FileNotFoundException, JRException, SQLException {

		
		
		String path = env.getProperty("output.exportpath");
		System.out.println(path);
		
		String fileName = "";
		String zipFileName = "";
		File outputFile;
		

		logger.info("Getting Output file : Third_PARTY");

		fileName = "ESI_ALL";

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			

			if (filetype.equals("pdf")) {
				System.out.println("inner pdf");
					jasperFile = this.getClass()
							.getResourceAsStream("/static/jasper/ESI_1.jrxml");
				}else {
				
					jasperFile = this.getClass()
							.getResourceAsStream("/static/jasper/ESI_1.jrxml");
				
			}

			
			JasperReport jr = JasperCompileManager.compileReport(jasperFile);
			
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			//logger.info("Assigning Parameters for Jasper");
			
			
			//map.put("SRNO", "AA");
			
			if (filetype.equals("pdf")) {
				fileName = fileName + ".pdf";
				path = path+fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JasperExportManager.exportReportToPdfFile(jp, path);
				
			}else {

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
	
public File getFile23(String fileType, String mon) throws FileNotFoundException, JRException, SQLException {
    String salaryMonth = mon;
    String path = env.getProperty("output.exportpath");
    String fileName = "SPF" + mon;
    String zipFileName = fileName + ".zip";
    File outputFile;

    logger.info("Getting Output file: Month");

    try {
        File jasperFile;
        File subrep1 = null;
        //InputStream subrep1 = getClass().getResourceAsStream("/static/Ex_Emp.jrxml");

        if (fileType.equals("pdf")) {
            jasperFile = ResourceUtils.getFile("classpath:static/jasper/Origin.jrxml");
            subrep1 = ResourceUtils.getFile("classpath:static/jasper/Ex_Emp.jrxml");
        } else {
            jasperFile = ResourceUtils.getFile("classpath:static/jasper/Origin.jrxml");
            subrep1 = ResourceUtils.getFile("classpath:static/jasper/Ex_Emp.jrxml");
        }

       // JasperReport jr = JasperCompileManager.compileReport(jasperFile);
        JasperReport jr = (JasperReport) JRLoader.loadObject(jasperFile);
        HashMap<String, Object> map = new HashMap<>();
        
        map.put("salary_month", salaryMonth);
        map.put("SubRep", subrep1);
        System.out.println(map);
        if (fileType.equals("pdf")) {
            fileName = fileName + ".pdf";
            path = path + fileName;
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
        // Handle the exception appropriately, e.g., log the error or throw a custom exception.
        // You can add specific error-handling code here.
    }

    outputFile = new File(path);
    return outputFile;
}

public File getFile2(String fileType, String mon) throws FileNotFoundException, JRException, SQLException {

    String salaryMonth = mon;
    String year = mon.substring(0, 4);
    String month = mon.substring(4, 6);

    System.out.println(year + "--------------" + month);

    String yearL;
    switch (month) {
        case "01": yearL = "Jan"; break;
        case "02": yearL = "Feb"; break;
        case "03": yearL = "Mar"; break;
        case "04": yearL = "Apr"; break;
        case "05": yearL = "May"; break;
        case "06": yearL = "Jun"; break;
        case "07": yearL = "Jul"; break;
        case "08": yearL = "Aug"; break;
        case "09": yearL = "Sep"; break;
        case "10": yearL = "Oct"; break;
        case "11": yearL = "Nov"; break;
        case "12": yearL = "Dec"; break;
        default: yearL = "";
    }

    String path = env.getProperty("output.exportpath");
    System.out.println(path);

    String fileName = "BORNFIRE-SPf-" + year + "-" + yearL;
    String zipFileName = fileName + ".zip";
    File outputFile;

    logger.info("Getting Output file : Month");

    try {
        InputStream jasperFile;

        if (fileType.equals("pdf")) {
            System.out.println("inner pdf");
            jasperFile = this.getClass().getResourceAsStream("/static/jasper/Origin.jrxml");
            fileName = fileName + ".pdf";
            path = Paths.get(path, fileName).toString();
        } else {
            jasperFile = this.getClass().getResourceAsStream("/static/jasper/Origin.jrxml");
            fileName = fileName + ".xlsx";
            path = Paths.get(path, fileName).toString();
        }

        JasperReport jr = JasperCompileManager.compileReport(jasperFile);
        HashMap<String, Object> map = new HashMap<>();
        map.put("salary_month", salaryMonth);
        System.out.println(map);

        if (fileType.equals("pdf")) {
            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
            JasperExportManager.exportReportToPdfFile(jp, path);
        } else {
        	 SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
             reportConfig.setSheetNames(new String[]{fileName});
           
        	JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jp));
            exporter.setConfiguration(reportConfig);
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
            exporter.exportReport();
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    outputFile = new File(path);
    return outputFile;
}

	
 /*public File getFileESI2(String filetype,String Mon) throws FileNotFoundException, JRException, SQLException {

		
		String Salary_month=Mon;
		String path = env.getProperty("output.exportpath");
		System.out.println(path);
		
		String fileName = "";
		String zipFileName = "";
		File outputFile;
		

		logger.info("Getting Output file : Month");

		fileName = "ESI"+Mon;

		zipFileName = fileName + ".zip";

		try {
			InputStream jasperFile;

			

			if (filetype.equals("pdf")) {
				System.out.println("inner pdf");
					jasperFile = this.getClass()
							.getResourceAsStream("/static/jasper/lastesi.jrxml");
				}else {
				
					jasperFile = this.getClass()
							.getResourceAsStream("/static/jasper/lastesi.jrxml");
				
			}

			
			JasperReport jr = JasperCompileManager.compileReport(jasperFile);
			
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			//logger.info("Assigning Parameters for Jasper");
			
			
			map.put("salary_month", Salary_month);
			System.out.println(map);
			if (filetype.equals("pdf")) {
				fileName = fileName + ".pdf";
				path = path+fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JasperExportManager.exportReportToPdfFile(jp, path);
				
			}else {

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

*/


public File getFileESI2(String filetype, String Mon) throws FileNotFoundException, JRException, SQLException,IllegalArgumentException {
		String Salary_month=Mon;
		String Year=Mon.substring(0,4);
		String Month=Mon.substring(4,6);
		System.out.println(Year+"--------------"+Month);
		String YearL=null;
		if(Month.equals("01")) {
			YearL="Jan";
		}else if(Month.equals("02")) {
			YearL="Feb";
		}else if(Month.equals("02")) {
			YearL="Feb";
		}else if(Month.equals("03")) {
			YearL="Mar";
		}else if(Month.equals("04")) {
			YearL="Apr";
		}else if(Month.equals("05")) {
			YearL="May";
		}else if(Month.equals("06")) {
			YearL="Jun";
		}else if(Month.equals("07")) {
			YearL="Jul";
		}else if(Month.equals("08")) {
			YearL="Aug";
		}else if(Month.equals("09")) {
			YearL="Sep";
		}else if(Month.equals("10")) {
			YearL="Oct";
		}else if(Month.equals("11")) {
			YearL="Nov";
		}else if(Month.equals("12")) {
			YearL="Dec";
		}
        String path = env.getProperty("output.exportpath");
        System.out.println("path");
        System.out.println(path);

        String fileName = "";
        File outputFile;

        logger.info("Getting Output file : Month");

        fileName = "BORNFIRE-ESIC-" + Year + "-" + YearL;

        try {
            InputStream jasperFile;

            if (filetype.equals("pdf")) {
                System.out.println("inner pdf");
                jasperFile = this.getClass().getResourceAsStream("/static/jasper/lastesi.jrxml");
            } else {
                jasperFile = this.getClass().getResourceAsStream("/static/jasper/lastesi.jrxml");
            }

            if (jasperFile == null) {
                throw new FileNotFoundException("Jasper file not found");
            }

            JasperReport jr = JasperCompileManager.compileReport(jasperFile);

            HashMap<String, Object> map = new HashMap<>();
            map.put("salary_month", Salary_month);
            System.out.println(map);

            if (filetype.equals("pdf")) {
                fileName = fileName + ".pdf";
                path = path + fileName;
                JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
                JasperExportManager.exportReportToPdfFile(jp, path);
            } else {
                fileName = fileName + ".xlsx";
                path += fileName;
                SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
                reportConfig.setSheetNames(new String[]{fileName});
                JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
                JRXlsxExporter exporter = new JRXlsxExporter();
                exporter.setExporterInput(new SimpleExporterInput(jp));
                exporter.setConfiguration(reportConfig);
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
                exporter.exportReport();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw e; // Rethrow the exception for higher-level handling
        } catch (Exception e) {
            e.printStackTrace();
            throw new JRException("Error generating Jasper report", e);
        }

        outputFile = new File(path);
        return outputFile;
    
}


	

public File getSalaryFile(String filetype,String Mon) throws FileNotFoundException, JRException, SQLException {

	
	String Salary_month=Mon;
	String path = env.getProperty("output.exportpath");
	System.out.println(path);
	
	String fileName = "";
	String zipFileName = "";
	File outputFile;
	

	logger.info("Getting Output file : Month");

	fileName = "Salary_"+Mon;

	zipFileName = fileName + ".zip";

	try {
		InputStream jasperFile;

		

		if (filetype.equals("pdf")) {
			System.out.println("inner pdf");
				jasperFile = this.getClass()
						.getResourceAsStream("/static/jasper/SalaryUpload.jrxml");
			}else {
			
				jasperFile = this.getClass()
						.getResourceAsStream("/static/jasper/SalaryUpload.jrxml");
			
		}

		
		JasperReport jr = JasperCompileManager.compileReport(jasperFile);
		
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		//logger.info("Assigning Parameters for Jasper");
		
		
		map.put("salary_month", Salary_month);
		System.out.println(map);
		if (filetype.equals("pdf")) {
			fileName = fileName + ".pdf";
			path = path+fileName;
			JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
			JasperExportManager.exportReportToPdfFile(jp, path);
			
		}else {

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


public File getGstFile(String filetype,String Month,String Year,String Raised) throws FileNotFoundException, JRException, SQLException {

	System.out.println("In the placemenmt service");
	String month=Month;
	String year=Year;
	String raised=Raised;
	String path = env.getProperty("output.exportpath");
	System.out.println(path);
	
	String fileName = "";
	String zipFileName = "";
	File outputFile;
	

	logger.info("Getting Output file : Month");

	fileName = "GST-"+raised;

	zipFileName = fileName + ".zip";

	try {
		
		if(raised.equals("By Us - India")) {
		InputStream jasperFile;

		

		if (filetype.equals("Excel")) {
			System.out.println("inner pdf");
				jasperFile = this.getClass()
						.getResourceAsStream("/static/jasper/ByUsIndiademo_2.jrxml");
			}else {
			
				jasperFile = this.getClass()
						.getResourceAsStream("/static/jasper/ByUsIndiademo_2.jrxml");
			
		}

		
		JasperReport jr = JasperCompileManager.compileReport(jasperFile);
		
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		//logger.info("Assigning Parameters for Jasper");
		
		
		map.put("month", month);
		map.put("year", year);
		System.out.println(map);
		if (filetype.equals("pdf")) {
			fileName = "GST_2024_SEP_ByUs-India" + ".pdf";
			path = path+fileName;
			JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
			JasperExportManager.exportReportToPdfFile(jp, path);
			
		}else {

			fileName = "GST_2024_SEP_ByUs-India" + ".xlsx";
			path += fileName;
			JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setExporterInput(new SimpleExporterInput(jp));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
			exporter.exportReport();
		}

		}
		
		else if(raised.equals("On Us - India")) {
			InputStream jasperFile;

			

			if (filetype.equals("Excel")) {
				System.out.println("inner pdf");
					jasperFile = this.getClass()
							.getResourceAsStream("/static/jasper/OnUsIndia_1.jrxml");
				}else {
				
					jasperFile = this.getClass()
							.getResourceAsStream("/static/jasper/OnUsIndia_1.jrxml");
				
			}

			
			JasperReport jr = JasperCompileManager.compileReport(jasperFile);
			
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			//logger.info("Assigning Parameters for Jasper");
			
			
			map.put("month", month);
			map.put("year", year);
			System.out.println(map);
			if (filetype.equals("pdf")) {
				fileName = "GST_2024_SEP_OnUs-India" + ".pdf";
				path = path+fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JasperExportManager.exportReportToPdfFile(jp, path);
				
			}else {

				fileName = "GST_2024_SEP_OnUs-India" + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();
			}

		}
		
		else if(raised.equals("By Us - Overseas")) {
			InputStream jasperFile;

			

			if (filetype.equals("Excel")) {
				System.out.println("inner pdf");
					jasperFile = this.getClass()
							.getResourceAsStream("/static/jasper/Blank_A4_Landscape.jrxml");
				}else {
				
					jasperFile = this.getClass()
							.getResourceAsStream("/static/jasper/Blank_A4_Landscape.jrxml");
				
			}

			
			JasperReport jr = JasperCompileManager.compileReport(jasperFile);
			
			
			HashMap<String, Object> map = new HashMap<String, Object>();
			//logger.info("Assigning Parameters for Jasper");
			
			
			map.put("month", month);
			map.put("year", year);
			System.out.println(map);
			if (filetype.equals("pdf")) {
				fileName = "GST_2024_SEP_BYUs-Overseas" + ".pdf";
				path = path+fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JasperExportManager.exportReportToPdfFile(jp, path);
				
			}else {

				fileName = "GST_2024_SEP_BYUs-Overseas" + ".xlsx";
				path += fileName;
				JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
				JRXlsxExporter exporter = new JRXlsxExporter();
				exporter.setExporterInput(new SimpleExporterInput(jp));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
				exporter.exportReport();
			}

		}
	} catch (Exception e) {
		e.printStackTrace();
	}

	outputFile = new File(path);
	return outputFile;
}





public File getECLFile1(String filename, String filetype, String Month, String Year) {
    String path = env.getProperty("output.exportpath");
    String fileName = "GST" + filetype;
    File outputFile = null;

    try {
        // Load JasperReport files
        try {
            InputStream[] jasperFiles = {
                    this.getClass().getResourceAsStream("/static/jasper/ByUsIndiademo_2.jrxml"),
                    this.getClass().getResourceAsStream("/static/jasper/Blank_A4_Landscape.jrxml"),
                    this.getClass().getResourceAsStream("/static/jasper/OnUsIndia_1.jrxml"),

            };

            // Compile JasperReports
            JasperReport[] jasperReports = new JasperReport[jasperFiles.length];
            for (int i = 0; i < jasperFiles.length; i++) {
                jasperReports[i] = JasperCompileManager.compileReport(jasperFiles[i]);
            }

            // System.out.println(Month + Year); // Commented out, as it seems unnecessary

            HashMap<String, Object> map = new HashMap<>();
            map.put("month", Month);
            map.put("year", Year);
			System.out.println(map);

            // Fill JasperPrint for each report
            JasperPrint[] jasperPrints = new JasperPrint[jasperReports.length];
            for (int i = 0; i < jasperReports.length; i++) {
                try (Connection connection = srcdataSource.getConnection()) {
                    jasperPrints[i] = JasperFillManager.fillReport(jasperReports[i], map, connection);
                }
            }

            // Combine JasperPrints
            JasperPrint combinedJasperPrint = new JasperPrint();
            for (JasperPrint jasperPrint : jasperPrints) {
                List<JRPrintPage> pages = jasperPrint.getPages();
                for (JRPrintPage page : pages) {
                    combinedJasperPrint.addPage(page);
                }
            }

            // Export to XLSX
            fileName = fileName + ".xlsx";
            path += File.separator + fileName; // Use File.separator for path construction

            SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
            reportConfig.setSheetNames(new String[]{filename});

            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(combinedJasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
            exporter.setConfiguration(reportConfig); // Set the configuration
            exporter.exportReport();
        } catch (Exception e) {
            logger.error("Error generating ECL file", e);
        }

        // Check if the file exists before returning
        if (new File(path).exists()) {
            outputFile = new File(path);
        }
    } catch (Exception e) {
        logger.error("Error generating ECL file", e);
    }

    return outputFile;
}

public File getECLFile(String filename, String filetype, String Month, String Year) throws IOException, JRException, SQLException {
    String path = env.getProperty("output.exportpath");
    String fileName = "GST" + filetype;
    File outputFile = new File(path + File.separator + fileName + ".xlsx");

    try {
        // Load JasperReport files
        InputStream[] jasperFiles = {
            this.getClass().getResourceAsStream("/static/jasper/ByUsIndiademo_2.jrxml"),
            this.getClass().getResourceAsStream("/static/jasper/Blank_A4_Landscape.jrxml"),
            this.getClass().getResourceAsStream("/static/jasper/OnUsIndia_1.jrxml")
        };

        String[] sheetNames = {"GST-BY-US-INDIA", "GST-OVERSEAS", "GST-ON-US-INDIA"};

        // Compile JasperReports
        List<JasperPrint> jasperPrintList = new ArrayList<>();
        HashMap<String, Object> map = new HashMap<>();
        map.put("month", Month);
        map.put("year", Year);
        System.out.println(map);

        for (int i = 0; i < jasperFiles.length; i++) {
            // Compile the report
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperFiles[i]);
            
            // Fill the report
            JasperPrint jp = JasperFillManager.fillReport(jasperReport, map, srcdataSource.getConnection());
            jp.setName(sheetNames[i]);
            jasperPrintList.add(jp);
        }

        // Export to XLSX
        logger.info("Exporting to Excel");

        // Use JRXlsxExporter to export the JasperPrints to an Excel file
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(new FileOutputStream(outputFile)));
        
        exporter.exportReport();
        
        logger.info("Excel File exported successfully");

    } catch (IOException e) {
        logger.error("Error occurred while exporting to Excel: " + e.getMessage());
        throw new IOException("Error occurred while exporting to Excel", e);
    }

    return outputFile;
}


public File gettdsexcel(String filename, String filetype, String moths, String years) {
    String path = this.env.getProperty("output.exportpath");
    String fileName = "TDS" + filetype;
    File outputFile = null;

    try {
       try {
          InputStream[] jasperFiles = new InputStream[]{this.getClass().getResourceAsStream("/static/jasper/tdss.jrxml")};
          JasperReport[] jasperReports = new JasperReport[jasperFiles.length];

          for(int i = 0; i < jasperFiles.length; ++i) {
             jasperReports[i] = JasperCompileManager.compileReport(jasperFiles[i]);
          }

          HashMap<String, Object> map = new HashMap();
          map.put("moths", moths);
          System.out.println(moths);
          map.put("years", years);
          System.out.println(years);
          JasperPrint[] jasperPrints = new JasperPrint[jasperReports.length];

          for(int i = 0; i < jasperReports.length; ++i) {
             Connection connection = this.srcdataSource.getConnection();

             try {
                jasperPrints[i] = JasperFillManager.fillReport(jasperReports[i], map, connection);
             } catch (Throwable var21) {
                if (connection != null) {
                   try {
                      connection.close();
                   } catch (Throwable var20) {
                      var21.addSuppressed(var20);
                   }
                }

                throw var21;
             }

             if (connection != null) {
                connection.close();
             }
          }

          JasperPrint combinedJasperPrint = new JasperPrint();
          JasperPrint[] var26 = jasperPrints;
          int var14 = jasperPrints.length;

          for(int var15 = 0; var15 < var14; ++var15) {
             JasperPrint jasperPrint = var26[var15];
             List<JRPrintPage> pages = jasperPrint.getPages();
             Iterator var18 = pages.iterator();

             while(var18.hasNext()) {
                JRPrintPage page = (JRPrintPage)var18.next();
                combinedJasperPrint.addPage(page);
             }
          }

          fileName = fileName + ".xlsx";
          path = path + File.separator + fileName;
          SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
          reportConfig.setSheetNames(new String[]{filename});
          JRXlsxExporter exporter = new JRXlsxExporter();
          exporter.setExporterInput(new SimpleExporterInput(combinedJasperPrint));
          exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
          exporter.setConfiguration(reportConfig);
          exporter.exportReport();
       } catch (Exception var22) {
          logger.error("Error generating TDS file", var22);
       }

       if ((new File(path)).exists()) {
          outputFile = new File(path);
       }
    } catch (Exception var23) {
       logger.error("Error generating TDS file", var23);
    }

    return outputFile;
 }

public File getFileAcccount_Ledger(String filetype, String acct_num,String fromdate,String todate) 
		throws FileNotFoundException, JRException, SQLException,IllegalArgumentException {
	
    String path = env.getProperty("output.exportpath");
    System.out.println("Export Path"+path);

    String fileName = "";
    File outputFile;
    fileName = "ACCOUNT LEDGER -" + acct_num ;
   

    try {
    	 logger.info("Getting Output file : Month");
    	
        InputStream jasperFile;
        
        if (filetype.equals("pdf")) {
            System.out.println("inner pdf");
            jasperFile = this.getClass().getResourceAsStream("/static/jasper/AccountLedgerFilter.jrxml");
        } else {
            jasperFile = this.getClass().getResourceAsStream("/static/jasper/AccountLedgerFilter.jrxml");
        }
        
      
        
        JasperReport jr = JasperCompileManager.compileReport(jasperFile);
        HashMap<String, Object> map = new HashMap<>();
        map.put("ACCOUNT_NO", acct_num);
        map.put("FROM_DATE", fromdate);
        map.put("TODATE", todate);
        
        if (filetype.equals("pdf")) {
            fileName = fileName + ".pdf";
            path = path + fileName;
            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
            JasperExportManager.exportReportToPdfFile(jp, path);
        } else {
            fileName = fileName + ".xlsx";
            path += fileName;
            SimpleXlsxReportConfiguration reportConfig = new SimpleXlsxReportConfiguration();
            reportConfig.setSheetNames(new String[]{fileName});
            JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
            JRXlsxExporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jp));
            exporter.setConfiguration(reportConfig);
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
            exporter.exportReport();
        }

    } catch (Exception e) {
        e.printStackTrace();
        throw new JRException("Error generating Jasper report", e);
    }

    outputFile = new File(path);
    return outputFile;

}






	}


