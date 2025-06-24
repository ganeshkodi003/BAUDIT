package com.bornfire.controller;

import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import com.bornfire.config.Scheduler;
import com.bornfire.entities.*;
import com.bornfire.services.AdminOperServices;
import com.bornfire.services.AttendanceRegisterService;
import com.bornfire.services.BGVService;
import com.bornfire.services.BankDetailService;
import com.bornfire.services.ExcelUploadService;
import com.bornfire.services.FileUploadServices;
import com.bornfire.services.InquiriesServices;
import com.bornfire.services.LogService;
import com.bornfire.services.LoginServices;
import com.bornfire.services.MaintenanceOperServices;
import com.bornfire.services.OnDutyServices;
import com.bornfire.services.PlacementServices;
import com.bornfire.services.ProjectMasterServices;
import com.bornfire.services.ReceivingMailNew;
import com.bornfire.services.ReportServices;
import com.bornfire.services.SchedulerToggleService;
import com.bornfire.services.TimeSheetPdf;
import com.bornfire.services.WorkAssignmentReportExcel;
import com.ibm.icu.text.SimpleDateFormat;
import com.itextpdf.text.pdf.codec.Base64.InputStream;
import com.microsoft.schemas.office.visio.x2012.main.CellType;
import org.springframework.beans.BeanUtils;


import net.sf.jasperreports.engine.JRException;

import org.apache.tika.Tika;

@Controller
@ConfigurationProperties("default")
public class BTMNavigationController {

	private static final Logger logger = LoggerFactory.getLogger(BTMNavigationController.class);
	private static final int EQUAL = 0;
	private static final int BOOLEAN = 0;
	private static final int ERROR = 0;
	@Autowired
	com.bornfire.entities.BTM_TRANS_POINT_DETAILSRepo BTM_TRANS_POINT_DETAILSRepo;
	@Autowired
	GstoverseasRepo gstoverseasRepo;
	@Autowired
	PlacementMaintenanceRep placementMaintenanceRep;

	@Autowired
	BsalRep bsalRep;

	@Autowired
	Emp_BGV_Repo emp_BGV_Repo;

	@Autowired
	GstBtmRep gstBtmRep;

	@Autowired
	GstRep gstRep;

	@Autowired
	spfRepo SpfRepo;

	@Autowired
	spf_repo Spf_repo;

	@Autowired
	LoginServices loginServices;

	@Autowired
	IssueTrackerRep issueTrackerRep;

	@Autowired
	ReportServices reportServices;

	@Autowired
	ClientMasterRep clientMasterRep;

	@Autowired
	DocumentMainRep documentMainRep;

	@Autowired
	BankMasterRep bankMasterRep;

	@Autowired
	BTMAdminProfileMangerRep btmAdminProfileMangerRep;

	@Autowired
	BankDetailService bankDetailService;

	@Autowired
	PlacementServices placementServices;

	@Autowired
	OnDutyServices onDutyServices;

	@Autowired
	DataSource srcdataSource;

	@Autowired
	InquiriesServices inquiriesServices;

	@Autowired
	AdminOperServices adminOperServices;

	@Autowired
	ExcelUploadService excelUploadService;

	@Autowired
	BLRSBatchJobAlertRep blrsBatchJobAlertRep;

	@Autowired
	PlacementMasterRep placementMasterRep;

	@Autowired
	TimesheetManagementRep timesheetManagementRep;

	@Autowired
	InvoiceMasterRep invoiceMasterRep;

	@Autowired
	ProfileManagerRep profileManagerRep;

	@Autowired
	BTMProjectMasterRep btmProjectMasterRep;

	@Autowired
	BTMProjectTeamDetailsRep btmProjectTeamDetailsRep;

	@Autowired
	BTMProjectDetailsRep btmProjectDetailsRep;

	@Autowired
	AttendanceRegisterRep attendanceRegisterRep;

	@Autowired
	OnDutyRep onDutyRep;

	@Autowired
	BTMAdminOndutyCountRep bTMAdminOndutyCountRep;

	@Autowired
	LeaveMasterRep leaveMasterRep;

	@Autowired
	BTMAdminAssociateProfileRep btmAdminAssociateProfileRep;

	@Autowired
	LeaveMasterCounterRep leaveMasterCounterRep;

	@Autowired
	ExtenseMasterRep extenseMasterRep;

	@Autowired
	BTMWorkAssignmentRep btmWorkAssignmentRep;

	@Autowired
	BTMEmpTimeSheetRep bTMEmpTimeSheetRep;

	@Autowired
	BTMTravelMasterRep btmTravelMasterRep;

	@Autowired
	BTMRefCodeMasterRep btmRefCodeMasterRep;

	@Autowired
	BTMAdminOrganizationMasterRep btmAdminOrganizationMasterRep;

	@Autowired
	BTMEmpTimeSheetRep btmEmpTimeSheetRep;

	@Autowired
	BTMMTimeSheetRep btmmTimeSheetRep;

	@Autowired
	TimeSheetBeanRep timeSheetBeanRep;

	@Autowired
	BTMAdminExpenseReportRep btmAdminExpenseReportRep;

	@Autowired
	BTMDocumentMasterRep btmDocumentMasterRep;

	@Autowired
	BTMEventMasterRep btmEventMasterRep;

	@Autowired
	MaintenanceOperServices maintenanceOperServices;

	@Autowired
	AttendanceRegisterService attendanceRegisterService;

	@Autowired
	PlacementResourcesMasterRepo placementResourcesMasterRepo;

	@Autowired
	ResourceMasterRepo resourceMasterRepo;

	@Autowired
	TimeSheetPdf timeSheetPdf;

	@Autowired
	WorkAssignmentReportExcel workAssignmentReportExcel;

	@Autowired
	BTMAdminHolidayMasterRep btmAdminHolidayMasterRep;

	@Autowired
	PlacementMaintenanceRep placementmaintenancerep;

	@Autowired
	BTMAdminAssociateModRep btmAdminAssociateModRep;

	@Autowired
	BTM_BAMInventryMastRep BTM_BAMInventryMastRep;

	@Autowired
	AccessRolesRep accessRolesRep;
	@Autowired
	bexpiRepo bexpiRepoa;

	@Autowired
	tdsRepo tdsRepos;
	@Autowired
	btdsviewRepo btdsviewRepos;
	@Autowired
	com.bornfire.entities.AttendanceRegisterGetRep AttendanceRegisterGetRep;

	SessionFactory sessionFactory;

	@Autowired
	ProjectMasterServices projectMasterServices;

	@Autowired
	Salary_Pay_Rep salary_Pay_Rep;

	@Autowired
	CandEvalFormRep candEvalFormRep;

	@Autowired
	com.bornfire.services.sendingmail_appointment sendingmail_appointment;
	@Autowired
	com.bornfire.services.sendigmail_offerletter sendigmail_offerletter;
	@Autowired
	com.bornfire.services.sendigmail_ReleavingLetter sendigmail_Rel_letter;
	
	

	@Autowired
	com.bornfire.services.Sendingmail_coveringletter sendingmail_coveringletter;

	@Autowired
	com.bornfire.services.sendingmail_batchjob sendingmail_batchjob;

	@Autowired
	paystructurerep Paystructurerep;

	@Autowired
	ProfileManagerRep1 profileManagerRep1;

	@Autowired
	PerdiemMasterRep perdiemMasterRep;

	@Autowired
	Assosiate_Profile_Repo assosiate_Profile_Repo;
	@Autowired
	com.bornfire.entities.Baj_Work_Repo Baj_Work_Repo;

	@Autowired
	FileUploadServices fileUploadServices;

	@Autowired
	BTMTroubleShoot_Rep bTMTroubleShoot_Rep;

	@Autowired
	com.bornfire.entities.Document_Master_Repo Document_Master_Repo;
	@Autowired
	com.bornfire.entities.BTM_Daily_Checklist_Repo BTM_Daily_Checklist_Repo;
	@Autowired
	BTM_Call_Log_Repo BTM_Call_Log_Repo;

	@Autowired
	ProjectDetailsRep projectDetailsRep;

	@Autowired
	MASTER_TABLE_AMC_DAILYLIST_Repo MASTER_TABLE_AMC_DAILYLIST_Repo;

	@Autowired
	com.bornfire.entities.BTM_TRANS_PARTITION_DETAILSREPO BTM_TRANS_PARTITION_DETAILSrepo;

	@Autowired
	BTM_REV_PART_TRANREPO BTM_REV_PART_TRANREPO;

	@Autowired
	com.bornfire.services.Sendingmail_Amcchecklist Sendingmail_Amcchecklist;

	@Autowired
	Chart_Acc_Rep chart_Acc_Rep;

	@Autowired
	Account_Ledger_Rep account_Ledger_Rep;

	@Autowired
	com.bornfire.entities.BTM_GST_MASTERREPO BTM_GST_MASTERREPO;
	@Autowired
	general_infra_details_repo general_infra_details_repo;
	@Autowired
	Daily_server_update_Repo Daily_server_update_Repo;
	@Autowired
	BAJAccountLedgerRepo bAJAccountLedgerRepo;

	@Autowired
	BAJ_TrmView_Repo bAJ_TrmView_Repo;

	@Autowired
	Bamcatcodemaintainrep Bamcatcodemain;

	@Autowired
	BAMInventryMastRep BAMInvmastrep;

	@Autowired
	BAM_AssetFlows_Rep BAM_AssetFlows_rep;

	@Autowired
	BTM_ServerDetails_Repo bTM_ServerDetails_Repo;
	@Autowired
	BamDocumentMasRep BamDocmasRep;

	@Autowired
	ReceivingMailNew receivingMailNew;
	String pagesize;

	@Autowired
	Scheduler scheduler;
	
	@Autowired
	LogService logService;
	@Autowired
    private SchedulerToggleService schedulerToggleService;
	
	@Autowired
	employee_exit_management_repo employee_exit_management_repo;

	public String getPagesize() {
		return pagesize;
	}

	public void setPagesize(String pagesize) {
		this.pagesize = pagesize;
	}

	@RequestMapping(value = "Dashboard", method = { RequestMethod.GET, RequestMethod.POST })
	public String getdashboard(Model md, HttpServletRequest req) {

		String userid = (String) req.getSession().getAttribute("USERID");
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM");
		String formattedCurrentDate = currentDate.format(formatter);
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userid));
		ResourceMaster piconbirthday = resourceMasterRepo.getrole(userid);
		List<ResourceMaster> piconbirthdayall = resourceMasterRepo.getroleall();
		// System.out.println(piconbirthdayall + "piconbirthdayall");
		Date birthdayall = null;
		String formattedDateall = null;
		String flagtoidentify = null;
		List<String> birthdayEmployeesGET = new ArrayList<>();
		List<String> birthdayEmployeesGETid = new ArrayList<>();

		List<Date> birthdayEmployeesdob = new ArrayList<>();

		for (ResourceMaster all : piconbirthdayall) {
			birthdayall = all.getDob();
			flagtoidentify = all.getDisable_flg();
			SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd");
			formattedDateall = formatdate.format(birthdayall);
			String currentDateALL = formatdate.format(new Date());

			// Extract dd-MM part from both dates
			if (formattedDateall.substring(5).equals(currentDateALL.substring(5)) && "N".equals(flagtoidentify)) {
				birthdayEmployeesGET.add(all.getResource_name());
				birthdayEmployeesdob.add(all.getDob());
				birthdayEmployeesGETid.add(all.getResource_id());
			}

		}
		String formattedDatedob = null;

		if (!birthdayEmployeesGET.isEmpty()) {
			SimpleDateFormat formatdate = new SimpleDateFormat("dd-MM");
			List<String> formattedDatedobList = new ArrayList<>(); // List to store formatted dates

			for (Date dob : birthdayEmployeesdob) {
				formattedDatedob = formatdate.format(dob); // Format each date
				formattedDatedobList.add(formattedDatedob);
			}
			md.addAttribute("birthdayEmployeesGET", birthdayEmployeesGET);
			md.addAttribute("birthdayEmployeesGETid", birthdayEmployeesGETid);
			// System.out.println("formattedDatedobList" + formattedDatedobList);
			md.addAttribute("birthdayEmployeesdob", formattedDatedobList);
			// System.out.println("currentDate" + formattedCurrentDate);
			md.addAttribute("current", formattedCurrentDate);
		} else {
			md.addAttribute("birthdayEmployeesGET", null);
		}
		// System.out.println(piconbirthday.getDob());
		Date birthday = piconbirthday.getDob();
		SimpleDateFormat formatdate = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = formatdate.format(birthday);

		
		md.addAttribute("menu", "BTMHeaderMenu");
		
		md.addAttribute("checkAcctExpiry", loginServices.checkAcctexpirty(userid));
		
		int completed = 0;
		int uncompleted = 0;
		md.addAttribute("reportList", "");
		md.addAttribute("completed", completed);
		md.addAttribute("uncompleted", uncompleted);
		md.addAttribute("menu", "Dashboard");
		return "BTMDashboard";

	}

	@RequestMapping(value = "verifyUser", method = RequestMethod.POST)
	@ResponseBody
	public String verifyUser(@ModelAttribute UserProfile userprofile, Model md, HttpServletRequest rq) {
		String userid = (String) rq.getSession().getAttribute("USERID");
		String msg = loginServices.verifyUser(userprofile, userid);

		return msg;

	}

	@RequestMapping(value = "passwordReset", method = RequestMethod.POST)
	@ResponseBody
	public String passwordReset(@ModelAttribute UserProfile userprofile, Model md, HttpServletRequest rq) {
		String userid = (String) rq.getSession().getAttribute("USERID");
		String msg = loginServices.passwordReset(userprofile, userid);

		return msg;
	}

	@RequestMapping(value = "login?logout", method = RequestMethod.POST)
	@ResponseBody
	public String logoutUpdate(HttpServletRequest req) {

		String msg;

		String userid = (String) req.getSession().getAttribute("USERID");

		try {
			logger.info("Updating Logout");
			loginServices.SessionLogging("LOGOUT", "M0", req.getSession().getId(), userid, req.getRemoteAddr(),
					"IN-ACTIVE");
			msg = "successfully logout";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "failed";
		}
		return msg;
	}

//	======================================  Admin Module ====================================================

	@RequestMapping(value = "organizationMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String organizationMaster(@RequestParam(required = false) String formmode, Model md, HttpServletRequest req)
			throws ParseException {

		String EmpId = "U72900TN2017PTC115892";
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("adminOrganization", adminOperServices.getUser(EmpId));

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("adminOrganization", adminOperServices.getUser(EmpId));

		} else {

			md.addAttribute("formmode", formmode);
		}

		return "BTMAdminOrganizationMaster";
	}

	@RequestMapping(value = "organizationMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String organizationMasterAdd(@RequestParam("formmode") String formmode,
			@ModelAttribute BTMAdminOrganizationMaster btmAdminOrganizationMaster, Model md, HttpServletRequest rq) {

		String msg = adminOperServices.addOrganizationModyfiy(btmAdminOrganizationMaster, formmode);
		return msg;
	}

	@RequestMapping(value = "adminAssociateProfile", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminAssociateProfile(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("resId", resId);
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("adminAssociateProfileList", btmAdminAssociateProfileRep.getAssociatelist());
			md.addAttribute("resId", resId);

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("refernce_code", btmRefCodeMasterRep.getBankList());
			md.addAttribute("resId", resId);
		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("adminAssociateProfile", adminOperServices.getAssociteUser(resId));
			BTMAdminAssociateProfile existingProfile = btmAdminAssociateProfileRep.getEmployeeData(resId);

			byte[] fileData1 = existingProfile.getEmp_photo();
			String base64FileData1 = "";
			if (fileData1 != null) {
				System.out.println("hjihihihihi");
				base64FileData1 = Base64.getEncoder().encodeToString(fileData1);
				md.addAttribute("fileData1", base64FileData1);
			}

		} else if (formmode.equals("verify")) {

			md.addAttribute("formmode", "verify");
			BTMAdminAssociateProfile existingProfile = btmAdminAssociateProfileRep.getEmployeeData(resId);
			byte[] fileData1 = existingProfile.getEmp_photo();
			String base64FileData1 = "";
			if (fileData1 != null) {
				System.out.println("hjihihihihi");
				base64FileData1 = Base64.getEncoder().encodeToString(fileData1);
				md.addAttribute("fileData1", base64FileData1);
			}
			md.addAttribute("adminAssociateProfile", adminOperServices.getAssociteVerifyUser(resId));
			md.addAttribute("resId", resId);

		} else if (formmode.equals("delete")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("adminAssociateProfile", adminOperServices.getAssociteUser(resId));
			md.addAttribute("resId", resId);

		} else if (formmode.equals("cancel")) {

			md.addAttribute("formmode", "cancel");
			md.addAttribute("adminAssociateProfile", adminOperServices.getAssociteCancelUser(resId));
			md.addAttribute("resId", resId);

		} else if (formmode.equals("listview")) {

			md.addAttribute("formmode", "listview");
			md.addAttribute("adminAssociateProfile", adminOperServices.getAssociteListUser(resId));
			md.addAttribute("resId", resId);

		}

		return "BTMAdminAssociateProfile";
	}

	@RequestMapping(value = "adminAssociateProfileAdd", method = RequestMethod.POST)
	@ResponseBody
	public String adminAssociateAdd(@RequestParam("formmode") String formmode,
			@ModelAttribute BTMAdminAssociateProfile bTMAdminAssociateProfile,
			@RequestParam(required = false) MultipartFile file, Model md, HttpServletRequest rq) {
		System.out.println("First step in the controller");

		byte[] setImgamc1 = null;

		// Check if a file was uploaded
		if (file != null && !file.isEmpty()) {
			System.out.println("File received: " + file.getOriginalFilename());
			try {
				byte[] empPhotoBytes = file.getBytes();
				bTMAdminAssociateProfile.setEmp_photo(empPhotoBytes);
				System.out.println("File size: " + empPhotoBytes.length + " bytes");
			} catch (IOException e) {
				e.printStackTrace();
				return "Error: Unable to process file";
			}
		} else {
			System.out.println("No file uploaded.");
		}

		// Fetch the user ID from the session
		String userId = (String) rq.getSession().getAttribute("USERID");

		System.out.println("Resource ID: " + bTMAdminAssociateProfile.getResource_id());
		System.out.println("Resource Name: " + bTMAdminAssociateProfile.getResource_name());
		System.out.println("Employee Photo: " + bTMAdminAssociateProfile.getEmp_photo());

		try {
			if (bTMAdminAssociateProfile.getResource_id() != null
					&& !bTMAdminAssociateProfile.getResource_id().isEmpty()) {
				// Fetch the existing user from the database
				BTMAdminAssociateProfile existingUser = btmAdminAssociateProfileRep
						.findBykeyid(bTMAdminAssociateProfile.getResource_id());
				System.out.println("Existing User: " + existingUser);

				// Determine the image data based on formmode and file upload
				if ("verify".equalsIgnoreCase(formmode) || "edit".equalsIgnoreCase(formmode)) {
					if (file != null && !file.isEmpty()) {
						setImgamc1 = file.getBytes();
						System.out.println("New file uploaded and processed.");
					} else if (existingUser != null) {
						setImgamc1 = existingUser.getEmp_photo();
						System.out.println("Using existing photo.");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Error: Unable to fetch or process data.";
		}

		// Set the image data (new or existing) into the profile
		if (setImgamc1 != null) {
			bTMAdminAssociateProfile.setEmp_photo(setImgamc1);
		}

		// Call the service method to add the associate user
		String msg = adminOperServices.addAssociateUser(bTMAdminAssociateProfile, formmode, userId);

		return msg;
	}

	@RequestMapping(value = "/userprofileimage", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> userprofileimage(@RequestParam(required = false) String userphoto)
			throws SQLException {
		System.out.println("Getting the image for employee ID: " + userphoto);

		// Fetch employee profile data based on employee ID
		BTMAdminAssociateProfile employee_Profile = btmAdminAssociateProfileRep.getEmployeeData(userphoto);

		// Check if the employee profile is found
		if (employee_Profile == null) {
			System.out.println("No records found for the given employee ID");
			return ResponseEntity.ok("No records found for the given employee ID");
		}

		byte[] employeePhoto = employee_Profile.getEmp_photo();
		System.out.println("second step");

		// Check if the employee photo is available
		if (employeePhoto != null) {
			System.out.println("Third step");
			String base64FileData = Base64.getEncoder().encodeToString(employeePhoto);
			// System.out.println("Base64 encoded image data: " + base64FileData);
			return ResponseEntity.ok(base64FileData); // Return the Base64 string
		} else {
			return ResponseEntity.ok("Photo content is null");
		}
	}

	/*
	 * @RequestMapping(value = "adminAssociateProfileAdd", method =
	 * RequestMethod.POST)
	 * 
	 * @ResponseBody public String adminAssociateAdd(
	 * 
	 * @RequestParam("formmode") String formmode,
	 * 
	 * @RequestBody BTMAdminAssociateProfile bTMAdminAssociateProfile, Model md,
	 * HttpServletRequest rq) {
	 * 
	 * System.out.println("First step in the controller"); String userId = (String)
	 * rq.getSession().getAttribute("USERID");
	 * 
	 * // Debugging to ensure the object is being populated
	 * System.out.println(bTMAdminAssociateProfile.getResource_id() +
	 * " bTMAdminAssociateProfile");
	 * System.out.println(bTMAdminAssociateProfile.getResource_name() +
	 * " bTMAdminAssociateProfile");
	 * System.out.println(bTMAdminAssociateProfile.getEmp_photo() +
	 * " bTMAdminAssociateProfile");
	 * 
	 * // Process the data String msg =
	 * adminOperServices.addAssociateUser(bTMAdminAssociateProfile, formmode,
	 * userId); return msg; }
	 * 
	 */
	@RequestMapping(value = "adminUserProfile", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminUserProfile(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, Model md, HttpServletRequest req)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		String loginuserid = (String) req.getSession().getAttribute("USERID");
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		loginServices.SessionLogging("USERPROFILE", "M2", req.getSession().getId(), loginuserid, req.getRemoteAddr(),
				"ACTIVE");

		md.addAttribute("menu", "UserProfile");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("userProfiles", loginServices.getUsersList());

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("userProfile", loginServices.getUser(userid));

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("userProfile", loginServices.getUser(""));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("userProfile", loginServices.getUser(userid));
			/*
			 * md.addAttribute("FinUserProfiles", loginServices.getFinUsersList());
			 * md.addAttribute("userProfile", loginServices.getUser(userid));
			 */

		}

		return "BTMUserprofile";
	}

	@RequestMapping(value = "createUser", method = RequestMethod.POST)
	@ResponseBody
	public String createUser(@RequestParam("formmode") String formmode, @ModelAttribute UserProfile userprofile,
			Model md, HttpServletRequest rq) throws NoSuchAlgorithmException, InvalidKeySpecException {

		String userid = (String) rq.getSession().getAttribute("USERID");
		String msg = loginServices.addUser(userprofile, formmode, userid);

		return msg;
	}

	@RequestMapping(value = "adminProfileMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminProfileMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {

		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("adminProfileManagerList", adminOperServices.getProfileManagerlist());

		} else if (formmode.equals("list1")) {

			md.addAttribute("formmode", "list1");
			md.addAttribute("adminProfileManagerList", adminOperServices.getProfileManagerlist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("adminProfileManager", adminOperServices.getProfileManager(resId));

		} else if (formmode.equals("view1")) {

			md.addAttribute("formmode", "view1");
			md.addAttribute("adminProfileManager", adminOperServices.getProfileManager(resId));

		} else if (formmode.equals("verifyList")) {

			md.addAttribute("formmode", "verifyList");
			md.addAttribute("adminProfileManagerList", adminOperServices.getProfileManagerlist());

		} else if (formmode.equals("verify")) {

			md.addAttribute("formmode", "verify");
			md.addAttribute("adminProfileManager", adminOperServices.getProfileManager(resId));

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("adminProfileManager", adminOperServices.getProfileManager(resId));

		} else if (formmode.equals("deleteList")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("adminProfileManagerList", adminOperServices.getProfileManagerlist());

		} else if (formmode.equals("delete")) {

			md.addAttribute("formmode", "delete");
			md.addAttribute("adminProfileManager", adminOperServices.getProfileManager(resId));

		}

		return "BTMAdminProfileMaster";
	}

	@RequestMapping(value = "adminProfileMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String adminProfileMasterAdd(@RequestParam("formmode") String formmode, Model md,
			@ModelAttribute BTMAdminProfileManager btmAdminProfileManager, HttpServletRequest rq) {

		String msg = adminOperServices.addProfileDetails(btmAdminProfileManager, formmode);
		return msg;
	}

	/*
	 * @RequestMapping(value = "adminLeaveMaster", method = { RequestMethod.GET,
	 * RequestMethod.POST }) public String adminLeaveMaster(@RequestParam(required =
	 * false) String formmode,@RequestParam(required = false) BigDecimal year,
	 * 
	 * @RequestParam(required = false) BigDecimal resId, @RequestParam(required =
	 * false) String RefId, Model md, HttpServletRequest req) throws ParseException
	 * { String userId = (String) req.getSession().getAttribute("USERID");
	 * md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
	 * md.addAttribute("menu", "BTMHeaderMenu"); BigDecimal year1 = new
	 * BigDecimal(Calendar.getInstance().get(Calendar.YEAR)); if (formmode == null
	 * || formmode.equals("list")) {
	 * 
	 * md.addAttribute("formmode", "list"); md.addAttribute("AdminLeaveList",
	 * leaveMasterRep.getAdminLeaveList1(year1));
	 * 
	 * }else if (formmode.equals("list1")) {
	 * 
	 * md.addAttribute("formmode", "list1"); md.addAttribute("AdminLeaveList",
	 * leaveMasterRep.getAdminLeaveList1(year));
	 * 
	 * }else if (formmode.equals("view")) {
	 * 
	 * md.addAttribute("formmode", "view"); md.addAttribute("leaveMaster",
	 * onDutyServices.getLeaveDetail(resId)); md.addAttribute("AdminLeaveList",
	 * leaveMasterCounterRep.getLeaveCounterlist(RefId));
	 * 
	 * }
	 * 
	 * return "BTMAdminLeaveMaster"; }
	 */

	@RequestMapping(value = "adminLeaveMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminLeaveMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal year, @RequestParam(required = false) BigDecimal resId,
			@RequestParam(required = false) String RefId, @RequestParam(required = false) String datelist,
			@RequestParam(required = false) String datelist1, @RequestParam(required = false) String datelist2,
			@RequestParam(required = false) String datelist3, @RequestParam(required = false) String datelist4,
			@RequestParam(required = false) String datelist5, @RequestParam(required = false) String datelist6,
			@RequestParam(required = false) String emp_no, @RequestParam(required = false) String yearid,
			@RequestParam(required = false) String empname, Model md, HttpServletRequest req,
			@RequestParam(required = false) String datelist7) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		BigDecimal year1 = new BigDecimal(Calendar.getInstance().get(Calendar.YEAR));

		if (formmode == null || formmode.equals("list1")) {

			md.addAttribute("AdminLeaveList", leaveMasterRep.findLeaveSummaryByYear());
			md.addAttribute("formmode", "list1");

			if (datelist != null) { // Optional: Print the value to the console if
				System.out.println("================+++++++++++++" + datelist);

				List<Object[]> adminvalues = leaveMasterRep.getAdminLeaveList111(datelist);
				/* List<LeaveMaster> adminvalues = leaveMasterRep.gettestig(datelist); */
				md.addAttribute("AdminLeaveList", adminvalues);
			}
			if (datelist1 != null) {
				// Optional: Print the value to the console if needed
				System.out.println("================+++++++++++++" + datelist1);

				List<Object[]> adminvalues1 = leaveMasterRep.getAdmindetailsit1(datelist1);
				// List<LeaveMaster> adminvalues = leaveMasterRep.gettestig(datelist);
				md.addAttribute("AdminLeaveList", adminvalues1);
				;

				// Continue with the logic using adminvalues as needed
			}

			// Continue with the logic using adminvalues as needed }

			if (datelist2 != null) {
				// Optional: Print the value to the console if needed
				System.out.println("================+++++++++++++" + datelist2);

				List<Object[]> adminvalues1 = leaveMasterRep.getAdmindetailsit2(datelist2);
				// List<LeaveMaster> adminvalues = leaveMasterRep.gettestig(datelist);
				md.addAttribute("AdminLeaveList", adminvalues1);
				;

				// Continue with the logic using adminvalues as needed
			}
			if (datelist3 != null) {
				// Optional: Print the value to the console if needed
				System.out.println("================+++++++++++++" + datelist3);

				List<Object[]> adminvalues1 = leaveMasterRep.getAdmindetailsit3(datelist3);
				// List<LeaveMaster> adminvalues = leaveMasterRep.gettestig(datelist);
				md.addAttribute("AdminLeaveList", adminvalues1);
				;

				// Continue with the logic using adminvalues as needed
			}

			if (datelist4 != null) {
				// Optional: Print the value to the console if needed
				System.out.println("================+++++++++++++" + datelist4);

				List<Object[]> adminvalues1 = leaveMasterRep.getAdmindetailsit4(datelist4);
				System.out.println("20199999999999999999999");
				// List<LeaveMaster> adminvalues = leaveMasterRep.gettestig(datelist);
				md.addAttribute("AdminLeaveList", adminvalues1);
				;

				// Continue with the logic using adminvalues as needed
			}

			if (datelist5 != null) {
				// Optional: Print the value to the console if needed
				System.out.println("================+++++++++++++" + datelist5);

				List<Object[]> adminvalues1 = leaveMasterRep.getAdmindetailsit5(datelist5);
				// List<LeaveMaster> adminvalues = leaveMasterRep.gettestig(datelist);
				md.addAttribute("AdminLeaveList", adminvalues1);
				;

				// Continue with the logic using adminvalues as needed
			}

			if (datelist6 != null) { // Optional: Print the value to the console if
				System.out.println("================+++++++++++++" + datelist6);

				List<Object[]> adminvalues = leaveMasterRep.getAdmindetailsit6(datelist6);
				/* List<LeaveMaster> adminvalues = leaveMasterRep.gettestig(datelist); */
				md.addAttribute("AdminLeaveList", adminvalues);
			}
			if (datelist7 != null) { // Optional: Print the value to the console if
				System.out.println("================+++++++++++++" + datelist7);

				List<Object[]> adminvalues = leaveMasterRep.getAdmindetailsit7(datelist7);
				/* List<LeaveMaster> adminvalues = leaveMasterRep.gettestig(datelist); */
				md.addAttribute("AdminLeaveList", adminvalues);
			}
		} else if (formmode.equals("list")) {
			BigDecimal yearconvert = new BigDecimal(yearid);

			// System.out.println(yearconvert+"yearid");

			md.addAttribute("formmode", "list");
			System.out.println("================" + datelist);
			System.out.println("================2222222222222" + datelist1);

			// md.addAttribute("AdminLeaveList", leaveMasterRep.getAdminLeaveList11(year1));
			md.addAttribute("AdminLeaveList", leaveMasterRep.findLeaveSummaryByYearandid(emp_no, empname, yearconvert));

			// md.addAttribute("formmode", "list1");
			// md.addAttribute("AdminLeaveList", leaveMasterRep.getAdminLeaveList1(year));
			// md.addAttribute("AdminLeaveList", leaveMasterRep.getAdminLeaveList2(year1));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("leaveMaster", onDutyServices.getLeaveDetail(resId));
			md.addAttribute("AdminLeaveList", leaveMasterCounterRep.getLeaveCounterlist(RefId));

		} else if (formmode.equals("view1")) {

			md.addAttribute("formmode", "view1");
			// md.addAttribute("leaveMaster", onDutyServices.getLeaveDetail(resId));
			// md.addAttribute("AdminLeaveList",
			// leaveMasterCounterRep.getLeaveCounterlist(RefId));

		}

		return "BTMAdminLeaveMaster";
	}

	@RequestMapping(value = "adminODMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminODMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal year, @RequestParam(required = false) String resId,
			@RequestParam(required = false) String RefId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		BigDecimal year2 = new BigDecimal(Calendar.getInstance().get(Calendar.YEAR));
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			 

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("leaveMaster", onDutyServices.getODDetail(resId));
			md.addAttribute("AdminODList", bTMAdminOndutyCountRep.getOndutyCounterlist(RefId));

		}

		return "BTMAdminODMaster";
	}

	@RequestMapping(value = "adminHolidayMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminHolidayMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("view")) {

			md.addAttribute("formmode", "view");

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", "add");
			md.addAttribute("adminHolidayProfile", new BTMAdminHolidayMaster());

		} else if (formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("adminHolidayList", adminOperServices.getHolidaylist());

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("adminHolidayProfile", adminOperServices.getHolidayDetail(resId));

		} else if (formmode.equals("list1")) {

			md.addAttribute("formmode", "list1");
			md.addAttribute("adminHolidayList", adminOperServices.getHolidaylist());

		} else if (formmode.equals("view1")) {

			md.addAttribute("formmode", "view1");
			md.addAttribute("adminHolidayProfile", adminOperServices.getHolidayDetail(resId));

		}
		return "BTMAdminHolidayMaster";
	}

	@RequestMapping(value = "adminHolidayMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String adminHolidayMasterAdd(@RequestParam("formmode") String formmode,
			@RequestParam(required = false) BigDecimal recordNo,
			@ModelAttribute BTMAdminHolidayMaster btmAdminHolidayMaster, Model md, HttpServletRequest rq) {

		String msg = adminOperServices.addHolidayDetails(btmAdminHolidayMaster, formmode, recordNo);
		return msg;
	}

	@RequestMapping(value = "adminDocumentMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminDocumentMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("view")) {

			md.addAttribute("formmode", "view");

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", "add");
			md.addAttribute("profileManagers", btmAdminAssociateProfileRep.getEmployeedetail2());
		} else if (formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("adminDocumentList", btmDocumentMasterRep.getDocumentlist());

		} else if (formmode.equals("view1")) {

			md.addAttribute("formmode", "view1");
			md.addAttribute("adminDocMaster", btmDocumentMasterRep.getDocument(resId));

		} else if (formmode.equals("list1")) {

			md.addAttribute("formmode", "list1");
			md.addAttribute("adminDocumentList", btmDocumentMasterRep.getDocumentlist());

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("adminDocMaster", btmDocumentMasterRep.getDocument(resId));

		}

		return "BTMAdminDocMaster";
	}

	@RequestMapping(value = "adminDocumentMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String adminDocumentMasterAdd(@RequestParam("formmode") String formmode,
			@RequestParam(required = false) BigDecimal recordNo, @ModelAttribute BTMDocumentMaster btmDocumentMaster,
			Model md, HttpServletRequest rq) {

		String msg = adminOperServices.addDocumentUser(btmDocumentMaster, formmode);
		return msg;
	}

	@RequestMapping(value = "adminReferenceMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminReferenceMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("view")) {

			md.addAttribute("formmode", "view");

		} else if (formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("adminRefCodeMaster", btmRefCodeMasterRep.getRefCodelist());

		} else if (formmode.equals("view1")) {

			md.addAttribute("formmode", "view1");
			md.addAttribute("RefCodeMaster", adminOperServices.getRefMaster(resId));

		} else if (formmode.equals("list1")) {

			md.addAttribute("formmode", "list1");
			md.addAttribute("adminRefCodeMaster", btmRefCodeMasterRep.getRefCodelist());

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("RefCodeMaster", adminOperServices.getRefMaster(resId));

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("deleteList")) {

			md.addAttribute("formmode", "deleteList");
			md.addAttribute("adminRefCodeMaster", btmRefCodeMasterRep.getRefCodelist());

		} else if (formmode.equals("delete")) {

			md.addAttribute("formmode", "delete");
			md.addAttribute("RefCodeMaster", adminOperServices.getRefMaster(resId));
		}

		return "BTMAdminRefCodeMaster";
	}

	@RequestMapping(value = "adminRefCodeMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String adminRefCodeAdd(@RequestParam("formmode") String formmode, @RequestParam("ref_id") String ref_id,
			@ModelAttribute BTMRefCodeMaster btmRefCodeMaster, Model md, HttpServletRequest rq) {
		String msg = adminOperServices.addRefCodeMaster(btmRefCodeMaster, formmode, ref_id);
		return msg;
	}

	@RequestMapping(value = "adminProjectMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminProjectMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) String resName,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("view")) {

			md.addAttribute("formmode", "view");

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", "add");
			md.addAttribute("adminprojectMaster", new BTMProjectMaster());

		} else if (formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("adminProjectMaster", btmProjectMasterRep.getProjectlist());

		} else if (formmode.equals("list1")) {

			md.addAttribute("formmode", "list1");
			md.addAttribute("adminProjectMaster", btmProjectMasterRep.getProjectlist());

		} else if (formmode.equals("deleteList")) {

			md.addAttribute("formmode", "deleteList");
			md.addAttribute("adminProjectMaster", btmProjectMasterRep.getProjectlist());

		} else if (formmode.equals("view1")) {

			md.addAttribute("formmode", "view1");
			md.addAttribute("adminProjectMaster", btmProjectMasterRep.getProjectlist());
			md.addAttribute("adminprojectMaster", btmProjectMasterRep.getProjectShow(resId, resName));
			md.addAttribute("projectDetails", btmProjectDetailsRep.getProjectDetails(resId));
			md.addAttribute("teamDetails", btmProjectTeamDetailsRep.getProjectTeamDetails(resId));

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("adminProjectMaster", btmProjectMasterRep.getProjectlist());
			md.addAttribute("adminprojectMaster", btmProjectMasterRep.getProjectShow(resId, resName));
			md.addAttribute("projectDetails", btmProjectDetailsRep.getProjectDetails(resId));
			md.addAttribute("teamDetails", btmProjectTeamDetailsRep.getProjectTeamDetails(resId));

		} else if (formmode.equals("delete")) {

			md.addAttribute("formmode", "delete");
			md.addAttribute("adminprojectMaster", btmProjectMasterRep.getProjectShow(resId, resName));
			md.addAttribute("projectDetails", btmProjectDetailsRep.getProjectDetails(resId));
			md.addAttribute("teamDetails", btmProjectTeamDetailsRep.getProjectTeamDetails(resId));

		}

		return "BTMAdminProjectMaster";
	}

	@RequestMapping(value = "adminProjectMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String adminProjectMasterAdd(@RequestParam("formmode") String formmode,
			@RequestParam(required = false) String userid, @ModelAttribute BTMProjectMaster btmProjectMaster,
			@ModelAttribute ProjectDetails projectDetails, @ModelAttribute ProjectTeamDetails projectTeamDetails,
			Model md, HttpServletRequest rq) {

		// String userid2 = (String) rq.getSession().getAttribute("USERID");
		String msg = adminOperServices.addProjectMaster(btmProjectMaster, projectDetails, projectTeamDetails, formmode,
				userid);
		return msg;
	}

	@RequestMapping(value = "adminTravelMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminTravelMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("TravelList", adminOperServices.getTravelMasterList());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("adminTravelMaster", adminOperServices.getTravelMaster(resId));
		}

		return "BTMAdminTravelMaster";
	}

	@RequestMapping(value = "adminExpenseReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminExpenseReport(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) String userid, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("expenseReport", adminOperServices.getExpenseReportlist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("adminExpenseReport", adminOperServices.getReportManager(resId));

		}
		return "BTMAdminExpenseReport";
	}

	@RequestMapping(value = "adminWorkAssignment", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminWorkAssignment(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("WorkAssignmentList", btmWorkAssignmentRep.getWorkAssignlist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("WorkAssignment", btmWorkAssignmentRep.getWorkAssign(resId));

		}

		return "BTMAdminWorkAssignment";
	}

	@RequestMapping(value = "adminTimesheetMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminTimesheetMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		String empid = (String) req.getSession().getAttribute("USERID");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");

			md.addAttribute("Profile", btmAdminAssociateProfileRep.getAssociatelist());

		} else if (formmode.equals("list1")) {

			md.addAttribute("formmode", "list1");
			md.addAttribute("TimesheetList", btmEmpTimeSheetRep.getTimeSheetdata(resId));

		}

		return "BTMAdminTimesheetMaster";
	}

	@RequestMapping(value = "adminCalendarMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminCalendarMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String year, @RequestParam(required = false) String month,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("adminCalendarMaster", adminOperServices.getCalendarlist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("adminHolidayDetails", adminOperServices.getMonthlyHolidaylist(year, month));

		}

		return "BTMAdminCalendarMaster";
	}

	@RequestMapping(value = "adminDailyActivity", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminDailyActivity(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			// md.addAttribute("adminCalendarMaster", adminOperServices.getCalendarlist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			// md.addAttribute("adminHolidayDetails",
			// adminOperServices.getMonthlyHolidaylist(year, month));

		}
		return "BTMAdminDailyActivity";
	}

	@RequestMapping(value = "adminDocumentMaintenance", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminDocumentMaintenance(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String emp_id, @RequestParam(required = false) String resId,
			@RequestParam(required = false) String srl_no, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");

		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("resId", resId);
		md.addAttribute("srl_no", srl_no);
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("Document", documentMainRep.Documents());
			// md.addAttribute("adminAssociateProfileList",
			// btmAdminAssociateProfileRep.getAssociatelist());
			md.addAttribute("resId", resId);

		} else if (formmode.equals("view")) {
			md.addAttribute("documentview", documentMainRep.Documentview(resId, srl_no));
			System.out.println(resId + "resId" + srl_no + "srl_no");
			// System.out.println(documentMainRep.Documentview(resId,srl_no)+"resultssssssssssssssssssssssss");
			DocumentMaintenance existingProfile = documentMainRep.getEmployeeData(resId, srl_no);
			byte[] fileData1 = existingProfile.getFile_upload();
			String base64FileData1 = "";
			if (fileData1 != null) {
				System.out.println("hjihihihihi");
				base64FileData1 = Base64.getEncoder().encodeToString(fileData1);
				md.addAttribute("fileData1", base64FileData1);
			}
			md.addAttribute("formmode", "view");

		}

		else if (formmode.equals("add")) {

			md.addAttribute("formmode", "add");
			md.addAttribute("allusers", resourceMasterRepo.gettotaluser());
			md.addAttribute("Document", documentMainRep.Documents());

		}

		return "BTMAdminDocMaintenance";
	}

	@RequestMapping(value = "uploadDoc", method = RequestMethod.POST)
	@ResponseBody
	public String uploadDoc(@RequestParam("document") byte[] file, @ModelAttribute DocumentMaintenance DocMain,
			Model md, HttpServletRequest rq, RedirectAttributes ra) throws ParseException, IOException {

		String msg = onDutyServices.uploadDoc(DocMain, file);
		return msg;
	}

	@RequestMapping(value = "solutionDocumentAdd", method = RequestMethod.POST)
	@ResponseBody
	public String solutionDocumentAdd(@RequestParam("file_upload") byte[] file,
			@ModelAttribute BTMDocumentMaster Doc_ref_no, Model md, HttpServletRequest rq, RedirectAttributes ra)
			throws ParseException, IOException {

		String msg = onDutyServices.uploadDoc(Doc_ref_no, file);
		return msg;
	}

	/*
	 * @RequestMapping(value = "solutionDocumentAddAdmin1", method =
	 * RequestMethod.POST)
	 * 
	 * @ResponseBody public String solutionDocumentAdd(@RequestParam(required =
	 * false) MultipartFile file,
	 * 
	 * @ModelAttribute DocumentMaintenance DocMain, Model md, HttpServletRequest rq,
	 * RedirectAttributes ra) throws IOException {
	 * System.out.println("first step into service"); String msg =
	 * "Document is not uploaded";
	 * 
	 * if (file != null && !file.isEmpty()) { try {
	 * System.out.println("second step into service");
	 * 
	 * // Convert MultipartFile to byte[] byte[] fileBytes = file.getBytes();
	 * 
	 * // Call service to upload the document msg =
	 * onDutyServices.uploadDocadmin(DocMain, fileBytes); } catch (IOException e) {
	 * System.err.println("Error while processing the file: " + e.getMessage()); msg
	 * = "Error while processing the file."; } } else { msg =
	 * "No file uploaded or file is empty."; }
	 * 
	 * return msg; }
	 */
	@RequestMapping(value = "/solutionDocumentAddAdmin", method = RequestMethod.POST)
	@ResponseBody
	public String handleFileUpload(@RequestParam("file_names[]") List<String> fileNames,
			@RequestParam("documents[]") List<MultipartFile> files, @RequestParam(required = false) String empId,
			@RequestParam(required = false) String empName // Optional field for metadata
	) {
		System.out.println("Entering handleFileUpload method.");
		System.out.println("Number of files received: " + (files == null ? 0 : files.size()));
		System.out.println("UploadedBy: " + empId);

		if (files == null || files.isEmpty()) {
			System.out.println("No files uploaded or files are empty.");
			return "No files uploaded or files are empty.";
		}

		StringBuilder responseMessage = new StringBuilder("Uploaded Files:\n");

		try {
			for (int i = 0; i < files.size(); i++) {
				System.out.println("Processing file index: " + i);
				MultipartFile file = files.get(i);
				String fileName = fileNames.get(i);

				System.out.println("File Name: " + fileName);
				System.out.println("File Size: " + file.getSize() + " bytes");

				// Create entity and populate fields
				DocumentMaintenance entity = new DocumentMaintenance();
				entity.setFile_name(fileName);
				System.out.println("Set file_name: " + fileName);

				entity.setFile_upload(file.getBytes());
				System.out.println("Set file_upload: File content as byte array");

				entity.setEmp_id(empId);
				System.out.println("Set emp_id:empId" + entity.getEmp_id());

				entity.setEmp_name(empName);
				System.out.println("Set empName" + empName);

				String uniqueId = fileName + entity.getEmp_id();
				entity.setUnique_id(uniqueId);
				System.out.println("Set unique_id: " + uniqueId);
				String srlNo = generateSrlNo(); // Call method to generate the next serial number
				entity.setSrl_no(srlNo);
				System.out.println("Generated and set srl_no: " + srlNo);

				// Determine document type (MIME type)
				String typeDocument = getDocumentType(file);
				entity.setType(typeDocument); // Set the type in the entity
				System.out.println("Set type: " + typeDocument);

				// Optionally set uploadedBy
				// entity.setUploadedBy(uploadedBy);
				// System.out.println("Set uploadedBy: " + uploadedBy);

				// Save entity to the database
				documentMainRep.save(entity);
				System.out.println("Saved entity to the database.");

				// Append to response message
				responseMessage.append(String.format("Saved File: %s (%s)\n", fileName, typeDocument));
				System.out.println("Updated response message: " + responseMessage.toString());
			}
		} catch (Exception e) {
			System.out.println("Error occurred while processing files: " + e.getMessage());
			return "Error processing files: " + e.getMessage();
		}

		System.out.println("Exiting handleFileUpload method.");
		return responseMessage.toString();
	}

	// Utility method to determine document type based on the file content type
	private String getDocumentType(MultipartFile file) {
		String contentType = file.getContentType();

		if (contentType == null) {
			return "unknown"; // Return "unknown" if no content type can be determined
		}

		if (contentType.startsWith("image")) {
			return "image"; // Handle image files
		} else if (contentType.equals("application/pdf")) {
			return "pdf"; // Handle PDF files
		} else if (contentType.equals("application/vnd.ms-excel")
				|| contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")) {
			return "excel"; // Handle Excel files (xls and xlsx)
		} else if (contentType.equals("application/msword")
				|| contentType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
			return "docx"; // Handle DOCX files
		} else {
			return "unknown"; // Default for unrecognized file types
		}
	}

	private String generateSrlNo() {
		// Fetch the highest srl_no from the database (assumed as String type)
		String maxSrlNo = documentMainRep.findMaxSrlNo();
		System.out.println("Max serial number fetched: " + maxSrlNo); // Debug print

		// Trim any leading/trailing spaces
		maxSrlNo = maxSrlNo != null ? maxSrlNo.trim() : null;

		if (maxSrlNo == null || !maxSrlNo.startsWith("SN-")) {
			return "SN-001"; // If no records or invalid format, start from SN-001
		}

		// Extract the numeric part of the serial number (starting from the 4th
		// character)
		try {
			String numericPart = maxSrlNo.substring(3); // Remove the "SN-" prefix

			// Check if the numeric part is a valid number
			int currentNumber = Integer.parseInt(numericPart);

			// Increment the serial number by 1
			currentNumber++;

			// Return the new serial number in the format SN-xxx
			return String.format("SN-%03d", currentNumber);
		} catch (NumberFormatException e) {
			// If the numeric part is invalid, return a default serial number
			System.out.println("Error parsing numeric part of srl_no: " + maxSrlNo);
			return "SN-001";
		}
	}

	@RequestMapping(value = "/imageadminDocumentview", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> adminDocumentview(@RequestParam(required = false) String userphoto,
			@RequestParam(required = false) String srlno) throws SQLException {
		System.out.println("Getting the file for employee ID: " + userphoto + "srlno" + srlno);

		DocumentMaintenance employee_Profile = documentMainRep.Documentview(userphoto, srlno);

		if (employee_Profile == null) {
			System.out.println("No records found for the given employee ID");
			return ResponseEntity.ok("No records found for the given employee ID");
		}

		byte[] fileData = employee_Profile.getFile_upload();
		String fileName = employee_Profile.getType(); // Assume getFileName() method exists in the employee profile

		System.out.println("File fetched successfully.");

		if (fileData != null && fileName != null) {
			// Determine content type based on file name
			String contentType = determineContentType(fileName);
			System.out.println("contentType" + contentType);
			String base64FileData = Base64.getEncoder().encodeToString(fileData);

			return ResponseEntity.ok().header("Content-Type", contentType).body(base64FileData);
		} else {
			return ResponseEntity.ok("File content is null");
		}
	}

	private String determineContentType(String fileName) {
		if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
			return "image/jpeg";
		} else if (fileName.endsWith(".png")) {
			return "image/png";
		} else if (fileName.endsWith(".pdf")) {
			System.out.println("pdfffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
			return "application/pdf";
		} else if (fileName.endsWith(".xls")) {
			System.out.println("excelllllllllllllllllllllllllllllllllllllllllllllll");
			return "application/vnd.ms-excel";
		} else if (fileName.endsWith(".docx")) {
			System.out.println("docxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
			return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
		}
		return "application/octet-stream"; // Default type if unknown
	}

	@RequestMapping(value = "dateandDay", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<LeaveResponseModal> dateandDay(@RequestParam(required = false) String firstval,
			@RequestParam(required = false) String lastval, @ModelAttribute DocumentMaintenance DocMain, Model md,
			HttpServletRequest rq, RedirectAttributes ra) throws ParseException, IOException {

		ArrayList<LeaveResponseModal> arl = onDutyServices.dateSelector(firstval, lastval);

		return arl;
	}

// ============================================  Admin Module End =====================================

//=============================================  Operation Module =======================================

	@RequestMapping(value = "changePassword", method = { RequestMethod.GET, RequestMethod.POST })
	public String changePassword(@RequestParam(required = false) String formmode, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String userid = (String) req.getSession().getAttribute("USERID");

		md.addAttribute("profileManager", btmAdminAssociateProfileRep.getEmployeedetail(userid));
		return "BTMChangePassword";
	}

	@RequestMapping(value = "changePasswordLogin", method = { RequestMethod.GET, RequestMethod.POST })
	public String changePasswordLogin(@RequestParam(required = false) String formmode, Model md, HttpServletRequest req)
			throws ParseException {
		return "BTMChangePasswordLogin";
	}

	@RequestMapping(value = "changePasswordReq", method = RequestMethod.POST)
	@ResponseBody
	public String changePasswordReq(@RequestParam("oldpass") String oldpass, @RequestParam("newpass") String newpass,
			@RequestParam("userid") String userid, Model md, HttpServletRequest rq) {
		String msg = loginServices.changePassword(oldpass, newpass, userid);
		md.addAttribute("message", "succes");
		md.addAttribute("profileManager", btmAdminAssociateProfileRep.getEmployeedetail(userid));
		return msg;
	}

	@RequestMapping(value = "changePasswordRequest", method = RequestMethod.POST)
	@ResponseBody
	public String changePasswordReq(@RequestParam("oldpass") String oldpass, @RequestParam("newpass") String newpass,
			Model md, HttpServletRequest rq) {
		String userid = (String) rq.getSession().getAttribute("USERID");
		String msg = loginServices.changePassword(oldpass, newpass, userid);

		// Invalidate (clear) the session
		rq.getSession().invalidate();

		return msg;
	}

	/*
	 * @RequestMapping(value = "changePasswordReq", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public String changePasswordReq(@RequestParam("oldpass") String
	 * oldpass, @RequestParam("newpass") String newpass, Model md,
	 * HttpServletRequest rq) { System.out.println(oldpass+"===="+newpass); String
	 * userid = (String) rq.getSession().getAttribute("USERID");
	 * 
	 * String msg = loginServices.changePassword(oldpass, newpass, userid); return
	 * msg; }
	 */

	@RequestMapping(value = "leaveApply", method = { RequestMethod.GET, RequestMethod.POST })
	public String leaveApply(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal resId,
			Model md, HttpServletRequest req) throws ParseException {
		System.out.println("hi");
		String userid2 = (String) req.getSession().getAttribute("USERID");
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		
		List<Object[]> empList = btmAdminAssociateProfileRep.getallempid();
		List<String> empIds = empList.stream()
		                             .map(obj -> String.valueOf(obj[0])) // assuming empId is in obj[0]
		                             .collect(Collectors.toList());
		md.addAttribute("profileManager", empIds);
		
		
		return "BTMLeaveApplyEmp";
	}
	
	@PostMapping("getSingleEmpdatas")
	@ResponseBody
	public BTMAdminAssociateProfile getSingleEmpdatas(@RequestParam("employee_id") String employee_id) {
	    System.out.println("Ajax Called");
	    System.out.println("employee_id = " + employee_id);

	    BTMAdminAssociateProfile empdetails = btmAdminAssociateProfileRep.getEmpDetails(employee_id);
	    System.out.println("empdetails = " + empdetails);

	    return empdetails;
	}


	@RequestMapping(value = "applyLeave", method = { RequestMethod.GET, RequestMethod.POST })
	public String applyLeave(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal resId, Model md, HttpServletRequest req) throws ParseException {

		String userid2 = (String) req.getSession().getAttribute("USERID");
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("profileManager", btmAdminAssociateProfileRep.getEmployeedetail(userid2));
		md.addAttribute("remaining_leave_applyleave", leaveMasterRep.remaining_leave_applyleave(userid2));
		System.out.println(leaveMasterRep.remaining_leave_applyleave(userid2) + "remaining_leave_applyleave");
		// Object[] result = leaveMasterRep.remaining_leave_applyleave(userid2);

		List<Object[]> remainingLeaveData = leaveMasterRep.remaining_leave_applyleave(userid2);
		md.addAttribute("remaining_leave_applyleave", remainingLeaveData);
		System.out.println("remaining_leave_applyleave: " + remainingLeaveData);

		for (Object[] row : remainingLeaveData) {
			String employeeId = (String) row[0]; // Cast to String for EMPLOYEE_ID
			BigDecimal leaveBalance = (BigDecimal) row[1]; // Cast to BigDecimal for LEAVE_BLC
			md.addAttribute("leaveBalance", leaveBalance);
			System.out.println("Employee ID: " + employeeId + ", Leave Balance: " + leaveBalance);
		}

		// md.addAttribute("LeaveList", leaveMasterRep.getLeaveListbyRecord(resId));
		// md.addAttribute("srl_no", onDutyServices.getSrlNoValue());

		return "BTMLeaveMaster";
	}

	@RequestMapping(value = "leaveMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String leaveMasterAdd(@ModelAttribute LeaveMaster leaveMaster,
			@ModelAttribute SampleLeaveMaster sampleLeaveMaster, @RequestParam(required = false) String formmode,
			@RequestParam(required = false) String employee_id, @RequestParam(required = false) String year,
			@RequestParam(required = false) String from_date, Model md, HttpServletRequest rq,
			@ModelAttribute BELE_Table_Entity bELE_Table_Entity) throws ParseException, SQLException {
		System.out.println(leaveMaster.toString());
		String msg = onDutyServices.addLeave(leaveMaster, sampleLeaveMaster, bELE_Table_Entity, formmode);
		return msg;
	}

	@RequestMapping(value = "getprojectdata1", method = RequestMethod.POST)
	@ResponseBody
	public BTMProjectMaster getprojectdata(@RequestParam(required = false) String projId, Model md,
			HttpServletRequest rq) throws ParseException, SQLException {
		String userid1 = (String) rq.getSession().getAttribute("USERID");
		System.out.println("getProjectdetail111111111111111111" + projId);

		if (projId != null && !projId.isEmpty()) {
			md.addAttribute("profileManager", btmProjectMasterRep.getprojectclient(projId));
			System.out.println(btmProjectMasterRep.getprojectclient(projId).toString());
			return btmProjectMasterRep.getprojectclient(projId);
		} else {
			// Handle case where 'a' is null or empty
			// You may return a default response or throw an exception
			return null; // or throw new IllegalArgumentException("Parameter 'a' is required");
		}
	}
	
	@RequestMapping(value = "applyOnduty", method = { RequestMethod.GET, RequestMethod.POST })
	public String applyOnduty(@RequestParam(required = false) String formmode, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String userid1 = (String) req.getSession().getAttribute("USERID");

		List<Object[]> empList = btmAdminAssociateProfileRep.getallempid();
		List<String> empIds = empList.stream()
		                             .map(obj -> String.valueOf(obj[0])) // assuming empId is in obj[0]
		                             .collect(Collectors.toList());
		md.addAttribute("profileManager", empIds);
		md.addAttribute("projectManager", btmProjectMasterRep.getProjectlist());

		return "BTMApplyOnduty";
	}

	@RequestMapping(value = "markOnDuty", method = { RequestMethod.GET, RequestMethod.POST })
	public String markOnDuty(@RequestParam(required = false) String formmode, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String userid1 = (String) req.getSession().getAttribute("USERID");

		md.addAttribute("profileManager", btmAdminAssociateProfileRep.getEmployeedetail(userid1));
		md.addAttribute("projectManager", btmProjectMasterRep.getProjectlist());

		BTMAdminAssociateProfile test = btmAdminAssociateProfileRep.getEmployeedetail(userid1);
		// md.addAttribute("srl_no", onDutyServices.getSrlNo());

		return "BTMMarkOnDuty";
	}

	@RequestMapping(value = "onDutyAdd", method = RequestMethod.POST)
	@ResponseBody
	public String onDutyAdd(@ModelAttribute OnDuty onDuty, @ModelAttribute BTMAdminSampleOD btmAdminOndutyCount,
			@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq)
			throws ParseException, SQLException {

		String msg = onDutyServices.addOnDuty(onDuty, btmAdminOndutyCount, formmode);
		return msg;
	}

	@RequestMapping(value = "travelMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String travelMaster(@RequestParam(required = false) String formmode, Model md, HttpServletRequest req)
			throws ParseException {

		String userid1 = (String) req.getSession().getAttribute("USERID");
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String Travel = "TRA";
		md.addAttribute("travel_Ref", Travel.concat(onDutyServices.getTravelRef()));

		if (formmode == null || formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("profileManager", btmAdminAssociateProfileRep.getEmployeedetail(userid1));

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", "add");
			md.addAttribute("travelMaster", new BTMTravelMaster());
			md.addAttribute("profileManager", btmAdminAssociateProfileRep.getEmployeedetail(userid1));

		}

		return "BTMTravelMaster";
	}

	@RequestMapping(value = "travelMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String travelMasterAdd(@ModelAttribute BTMTravelMaster BTMtravelMaster, Model md, HttpServletRequest rq) {

		String msg = onDutyServices.addTravelList(BTMtravelMaster);
		return msg;
	}

	@RequestMapping(value = "submittravel", method = RequestMethod.POST)
	@ResponseBody
	public String submittravel(@RequestParam(required = false) String tra_ref,
			@ModelAttribute BTMTravelMaster btmTravelMaster, Model md, HttpServletRequest rq) {

		String msg = onDutyServices.addTravelList(btmTravelMaster);

		System.out.println(btmTravelMaster.getAss_id());
		System.out.println(btmTravelMaster.getClient_id());
		System.out.println(btmTravelMaster.getAss_name());
		System.out.println(btmTravelMaster.getPrj_id());
		return msg;
	}

	@RequestMapping(value = "claimExpenses", method = { RequestMethod.GET, RequestMethod.POST })
	public String claimExpenses(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, Model md, HttpServletRequest req) throws ParseException {

		String userid2 = (String) req.getSession().getAttribute("USERID");
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String Expense = "EXP";
		md.addAttribute("expense_Ref", Expense.concat(onDutyServices.getExpRef()));

		if (formmode == null || formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("adminExpenseReport", btmAdminAssociateProfileRep.getEmployeedetail(userid2));

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", "add");
			md.addAttribute("adminExpenseReport", btmAdminAssociateProfileRep.getEmployeedetail(userid2));
		}
		return "BTMClaimExpenses";
	}

	@RequestMapping(value = "claimExpensesAdd", method = RequestMethod.POST)
	@ResponseBody
	public String claimExpensesAdd(@RequestParam("formmode") String formmode,
			@ModelAttribute ExpenseMaster expenseMaster, Model md, HttpServletRequest rq) {

		String msg = onDutyServices.addExpenseReport(expenseMaster, formmode);
		return msg;
	}

	@RequestMapping(value = "workAssignment", method = { RequestMethod.GET, RequestMethod.POST })
	public String workAssignment(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {

		String userid1 = (String) req.getSession().getAttribute("USERID");
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("WorkAssignmentList", btmWorkAssignmentRep.getWorkAssignListById(userid1));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("WorkAssignment", btmWorkAssignmentRep.getWorkAssign(resId));

		}

		return "BTMWorkAssign";
	}

	@RequestMapping(value = "timeSheet", method = { RequestMethod.GET, RequestMethod.POST })
	public String timeSheet(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String emp_id, String month, BigDecimal year, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String userid2 = (String) req.getSession().getAttribute("USERID");
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");

			md.addAttribute("TimesheetList", btmEmpTimeSheetRep.getTimeSheetLast(userid2));

		} else if (formmode.equals("modify")) {

			md.addAttribute("formmode", "modify");
			md.addAttribute("TimesheetList", btmEmpTimeSheetRep.getTimeSheetModify(emp_id, year, month));

		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
			md.addAttribute("TimesheetList", onDutyServices.getTimeSheetselect(userid2));
		} else if (formmode.equals("Yes")) {
			md.addAttribute("formmode", "Yes");
			md.addAttribute("TimesheetList", onDutyServices.getTimeSheetselect(userid2));
		} else if (formmode.equals("addnew")) {
			md.addAttribute("formmode", "addnew");
		}

		return "BTMTimesheetOperation";
	}

	@RequestMapping(value = "timeSheetedit", method = { RequestMethod.GET, RequestMethod.POST })
	public String timeSheetedit(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String month, @RequestParam(required = false) String year,
			@RequestParam(required = false) String empid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {

		String userid2 = (String) req.getSession().getAttribute("USERID");
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("TimesheetList", timeSheetBeanRep.getTimesheetList(userid2));
			// md.addAttribute("WorkList", btmWorkAssignmentRep.getWorkMaster(id2));
			// md.addAttribute("TimesheetList",
			// timesheetManagementRep.getTimesheetList(id2));
			// md.addAttribute("WorkAssignment", btmWorkAssignmentRep.getWorkAssign(resId));
		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("TimesheetList", timeSheetBeanRep.getTimesheetList(userid2));
			// md.addAttribute("WorkList", btmWorkAssignmentRep.getWorkMaster(id2));

			md.addAttribute("timeSheetList", timeSheetBeanRep.getTimeSheetdata(empid, year, month));
			// md.addAttribute("TimesheetList",
			// timesheetManagementRep.getTimesheetList(id2));
			// md.addAttribute("WorkAssignment", btmWorkAssignmentRep.getWorkAssign(id2));

		}

		return "BTMTimeSheet";
	}

	@RequestMapping(value = "timeSheetAdd", method = RequestMethod.POST)
	@ResponseBody
	public String timeSheetAdd(@ModelAttribute BTMEmpTimeSheet btmEmpTimeSheet, Model md, HttpServletRequest rq) {
		String msg = onDutyServices.addTimeSheet(btmEmpTimeSheet);
		return msg;
	}

	@RequestMapping(value = "timeSheetEdit", method = RequestMethod.POST)
	@ResponseBody
	public String timeSheetEdit(@RequestParam(required = false) String empid,
			@RequestParam(required = false) BigDecimal year, @RequestParam(required = false) String month,
			@ModelAttribute BTMEmpTimeSheet btmEmpTimeSheet, Model md, HttpServletRequest rq) {

		String msg = onDutyServices.EditTimeSheet(btmEmpTimeSheet, empid, year, month);
		return msg;
	}

	@RequestMapping(value = "issueTracker", method = { RequestMethod.GET, RequestMethod.POST })
	public String issueTracker(@RequestParam(required = false) String formmode, String srl_no,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("view")) {
			md.addAttribute("formmode", "view");
		} else if (formmode.equals("list")) {
			md.addAttribute("formmode", "list");

			md.addAttribute("IssueTracker", issueTrackerRep.findAll());

		} else if (formmode.equals("elist")) {

			md.addAttribute("formmode", "elist");
			md.addAttribute("issueTracker", issueTrackerRep.getIssueList());

		} else if (formmode.equals("view1")) {
			md.addAttribute("formmode", "view1");
			md.addAttribute("allusers", resourceMasterRepo.gettotaluser());
			md.addAttribute("issueview", issueTrackerRep.getIssue(srl_no));

		}

		else if (formmode.equals("modify")) {

			md.addAttribute("formmode", "modify");
			md.addAttribute("issueview", issueTrackerRep.getIssue(srl_no));
			md.addAttribute("allusers", resourceMasterRepo.gettotaluser());
			md.addAttribute("issuemodify", issueTrackerRep.getIssue(srl_no));

		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
			md.addAttribute("allusers", resourceMasterRepo.gettotaluser());
		} else if (formmode.equals("upload")) {
			md.addAttribute("formmode", "upload");
		}
		return "BTMIssueTracker";
	}

	@RequestMapping(value = "issueAdd", method = RequestMethod.POST)
	@ResponseBody
	public String issueAdd(@RequestParam(required = false) String formmode, @ModelAttribute IssueTracker issuetracker,
			Model md, HttpServletRequest rq) {
		String msg = onDutyServices.addissue(issuetracker, formmode);
		return msg;
	}

	@RequestMapping(value = "issueEdit", method = RequestMethod.POST)
	@ResponseBody
	public String issueEdit(@RequestParam(required = false) String formmode, @ModelAttribute IssueTracker issuetracker,
			Model md, HttpServletRequest rq) {
		String msg = onDutyServices.editissue(issuetracker, formmode);
		return msg;
	}

	@RequestMapping(value = "downFormat", method = RequestMethod.GET)
	public ResponseEntity<Resource> generateExcelReport() throws IOException {
		List<IssueTracker> Issues = issueTrackerRep.getIssueFormat();

		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet();

		int rowCount = 0;
		Row row = sheet.createRow(rowCount++);

		Font font = wb.createFont();
		font.setBold(true);

		CellStyle cellStyle = wb.createCellStyle();
		cellStyle.setBorderTop(BorderStyle.THICK);
		cellStyle.setBorderBottom(BorderStyle.THICK);
		cellStyle.setBorderLeft(BorderStyle.THICK);
		cellStyle.setBorderRight(BorderStyle.THICK);
		cellStyle.setFont(font);

		Cell cell = row.createCell(0);
		cell.setCellValue("srl_no");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(1);
		cell.setCellValue("Category");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(2);
		cell.setCellValue("Groups");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(3);
		cell.setCellValue("Product");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(4);
		cell.setCellValue("Module");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(5);
		cell.setCellValue("Screen");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(6);
		cell.setCellValue("Operation");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(7);
		cell.setCellValue("Description");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(8);
		cell.setCellValue("Issue Ref No");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(9);
		cell.setCellValue("Date of Issue");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(10);
		cell.setCellValue("Reported By");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(11);
		cell.setCellValue("Approved By");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(12);
		cell.setCellValue("Nature of Issue");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(13);
		cell.setCellValue("Issue Details");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(14);
		cell.setCellValue("Severity");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(15);
		cell.setCellValue("Remarks");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(16);
		cell.setCellValue("Assigned To");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(17);
		cell.setCellValue("Date of Assigned");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(18);
		cell.setCellValue("Fix Period");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(19);
		cell.setCellValue("Delivery Date");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(20);
		cell.setCellValue("Fix Details");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(21);
		cell.setCellValue("Date of Fix");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(22);
		cell.setCellValue("Tested By");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(23);
		cell.setCellValue("Tested On");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(24);
		cell.setCellValue("Test Result");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(25);
		cell.setCellValue("Issue Status");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(26);
		cell.setCellValue("Turn Around Time");
		cell.setCellStyle(cellStyle);

		cell = row.createCell(27);
		cell.setCellValue("Final Closure");
		cell.setCellStyle(cellStyle);

		cellStyle = wb.createCellStyle();
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);

		for (IssueTracker issue : Issues) {
			row = sheet.createRow(rowCount++);

			int columnCount = 0;

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getSrl_no());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getCategory());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getGroups());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getProduct());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getModule());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getScreen());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getOperation());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getOper_desc());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getIssue_ref_no());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getDate_of_issue());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getRpt_by());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getApr_by());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getNat_of_issue());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getIssue_details());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getIssue_severity());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getIssue_rmks());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getAssign_to());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getAssign_date());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getFix_period().toString());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getDel_date());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getFix_details());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getDate_of_fix());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getTest_by());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getTest_date());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getTest_results());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getIssue_status());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getTat_per().doubleValue());
			cell.setCellStyle(cellStyle);

			cell = row.createCell(columnCount++);
			cell.setCellValue(issue.getFinal_cls());
			cell.setCellStyle(cellStyle);

		}

		ByteArrayOutputStream os = new ByteArrayOutputStream();

		wb.write(os);
		wb.close();

		ByteArrayInputStream is = new ByteArrayInputStream(os.toByteArray());

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(
				MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
		headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
		headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=IssueTracker.xlsx");

		ResponseEntity<Resource> response = new ResponseEntity<Resource>(new InputStreamResource(is), headers,
				HttpStatus.OK);

		return response;
	}
//================================== Opertion Module End ======================================================

//==================================== Inquiries Module =========================================================

	@RequestMapping(value = "organizationPolicy", method = { RequestMethod.GET, RequestMethod.POST })
	public String organizationPolicy(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		return "BTMOrganizationPolicy";
	}

	@RequestMapping(value = "holidayMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String holidayMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("holidayList", adminOperServices.getHolidaylist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("BTMholidayList", adminOperServices.getHolidayDetail(resId));

		}

		return "BTMHolidayMaster";
	}

	@RequestMapping(value = "associateMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String associateMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String userid1 = (String) req.getSession().getAttribute("USERID");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("associateMaster", inquiriesServices.getAssociateData(userid1));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("associateMaster", inquiriesServices.getAssociateData(userid1));

		}

		return "BTMAssociateProfile";
	}

	@RequestMapping(value = "profileMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String profileMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String id2 = (String) req.getSession().getAttribute("USERID");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			// md.addAttribute("profileMasterList",
			// adminOperServices.getProfileManager(id2));
			md.addAttribute("profileMasterList", btmAdminProfileMangerRep.getProfilelist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("profileMasterList", adminOperServices.getProfileManager(id2));

		}

		return "BTMProfileMaster";
	}

	@RequestMapping(value = "projectMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String projectMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resName, @RequestParam(required = false) String resId, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("projectMasterList", adminOperServices.getProjectMasterlist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("projectList", btmProjectMasterRep.getProjectShow(resId, resName));
			md.addAttribute("projectDetails", btmProjectDetailsRep.getProjectDetails(resId));
			md.addAttribute("projectTeamDetails", btmProjectTeamDetailsRep.getProjectTeamDetails(resId));

		}

		return "BTMInqueriesProjectMaster";
	}

	@RequestMapping(value = "leaveMaster", method = RequestMethod.GET)
	public String leaveMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal resId, @RequestParam(required = false) String RefId, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String userid = (String) req.getSession().getAttribute("USERID");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("LeaveList", leaveMasterRep.getLeaveListbyid(userid));
		}

		else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("LeaveList", leaveMasterRep.getLeaveListbyRecord(resId));
			md.addAttribute("LeaveListref", leaveMasterCounterRep.getleavelistbyrec(RefId));

		}

		return "BTMILeaveMaster";
	}

	@RequestMapping(value = "onDuty", method = { RequestMethod.GET, RequestMethod.POST })
	public String onDuty(@RequestParam(required = false) String formmode, @RequestParam(required = false) String resId,
			@RequestParam(required = false) String RefId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String userid = (String) req.getSession().getAttribute("USERID");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("onDutyList", onDutyRep.getOdListbyid(userid));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("onDutyList", onDutyRep.getOdListbyrecord(resId));
			md.addAttribute("onDutyListCount", bTMAdminOndutyCountRep.getondutybyref(RefId));

		}

		return "BTMOdApply";
	}

	@RequestMapping(value = "listOfTravel", method = { RequestMethod.GET, RequestMethod.POST })
	public String listOfTravel(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String userid = (String) req.getSession().getAttribute("USERID");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("TravelList", btmTravelMasterRep.getTravelListbyid(userid));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("adminTravelMaster", adminOperServices.getTravelMaster(resId));
		}

		return "BTMTravelList";
	}

	@RequestMapping(value = "expensesReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String expensesReport(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String userid2 = (String) req.getSession().getAttribute("USERID");

		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("ExpenseList", extenseMasterRep.getListByassId(userid2));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("expenseReport", adminOperServices.getReportManager(resId));

		}
		return "BTMExpensesReport";

	}

	@RequestMapping(value = "workAssignList", method = { RequestMethod.GET, RequestMethod.POST })
	public String workAssignList(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		String userid1 = (String) req.getSession().getAttribute("USERID");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			// md.addAttribute("WorkAssignmentList",
			// btmWorkAssignmentRep.getWorkAssignListById(userid1));
			md.addAttribute("WorkAssignmentList", btmWorkAssignmentRep.getWorkAssignlist());
		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("WorkAssignment", btmWorkAssignmentRep.getWorkAssign(resId));

		}

		return "BTMInquiriesWorkAssignment";
	}

	@RequestMapping(value = "solutionDocument", method = { RequestMethod.GET, RequestMethod.POST })
	public String solutionDocument(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) String doc_id,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("solutionDocList", btmDocumentMasterRep.getDocumentlist1());
		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", "view");
			md.addAttribute("solutionDocList", btmDocumentMasterRep.getDocument1(doc_id));
		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
		}
		return "BTMSolutionDocument";
	}

	@RequestMapping(value = "attendanceRegister", method = { RequestMethod.GET, RequestMethod.POST })
	public String attendanceRegister(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal serialNo, @RequestParam(required = false) String yearone,
			@RequestParam(required = false) String monthone, @RequestParam(required = false) String dayone, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatdate = new SimpleDateFormat("dd/MM/yyyy");
		String str = formatdate.format(cal.getTime());
		Date dat1 = null;
		try {
			dat1 = formatdate.parse(str);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		// resourceMasterRepo.gettotaluser();
		int totalemployees = resourceMasterRepo.gettotalnum();
		int present = attendanceRegisterRep.getALLpresent(dat1);
		int absent = totalemployees - present;
		System.out.println(absent + "absent");
		int onduty = 0;

		LocalDate currentDate1 = LocalDate.now();

		// Format the date as dd-MM-yyyy
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String formattedDate = currentDate1.format(formatter);

		// Print the formatted date
		System.out.println("Formatted Date: " + formattedDate);

		// Extract day, month, and year
		int day1 = currentDate1.getDayOfMonth();
		int month2 = currentDate1.getMonthValue();
		int year1 = currentDate1.getYear();
		System.out.println("Day: " + day1);
		System.out.println("Month: " + month2);
		System.out.println("Year: " + year1);
		md.addAttribute("attendanceList", attendanceRegisterRep.getALL(dat1));
		Integer absenteesCount = resourceMasterRepo.getAbsenteesCount(year1, month2, day1);
		md.addAttribute("absentecelistcount", absenteesCount);
		Integer LeaveCount = attendanceRegisterRep.getLeaveCount(year1, month2, day1);
		Integer OndutyCount = attendanceRegisterRep.geOnDutyCount(year1, month2, day1);
		Integer presentCount = attendanceRegisterRep.getPresentCount(year1, month2, day1);
		System.out.println("presentCount" + presentCount);
		md.addAttribute("OndutyCount", OndutyCount);
		md.addAttribute("LeaveCount", LeaveCount);
		md.addAttribute("numofEmployees", totalemployees);
		md.addAttribute("numofPresent", presentCount);
		md.addAttribute("numofabsent", absent);
		md.addAttribute("numofonduty", onduty);
		md.addAttribute("allusers", resourceMasterRepo.gettotaluser());
		SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
		String month = formatMonth.format(cal.getTime());

		md.addAttribute("CurrentMonth", month);
		SimpleDateFormat formatyear = new SimpleDateFormat("yyyy");
		String year = formatyear.format(cal.getTime());
		LocalDate currentDate = LocalDate.now();

		int currentYear = currentDate.getYear();
		String currentMonth = String.format("%02d", currentDate.getMonthValue());
		String currentDay = String.format("%02d", currentDate.getDayOfMonth());
		md.addAttribute("Currentyear1", currentYear);
		md.addAttribute("Currentmonth1", currentMonth);
		md.addAttribute("Currentyday1", currentDay);
		System.out.println(currentYear + "" + currentMonth + "" + currentDay + "sms redefining ");
		md.addAttribute("Currentyear", year);
		md.addAttribute("sms", AttendanceRegisterGetRep.getsms(yearone, monthone, dayone));
		// List<AttendanceRegisterGet>
		// smsd=AttendanceRegisterGetRep.getsms(yearone,monthone,dayone);
		// System.out.println("hhhhhhhhhhhhhhhhhhhhhhh"+smsd.get(0));

		System.out.println("sms seendingoooooooooooooooooooooooooooooo"
				+ AttendanceRegisterGetRep.getsms(yearone, monthone, dayone));

		return "BTMAttendanceRegister";
	}

	@RequestMapping(value = "attendanceAbsentRegister", method = { RequestMethod.GET, RequestMethod.POST })
	public String attendanceAbsentRegister(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal serialNo, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		return "BTMAttendanceAbsentList";
	}

	@RequestMapping(value = "timesheetMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String timesheetMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(required = false) String month, @RequestParam(required = false) BigDecimal year,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {

		String empId = (String) req.getSession().getAttribute("USERID");
		// AttendanceID
		// userid3 = (AttendanceID) req.getSession().getAttribute("USERID");
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");

			md.addAttribute("TimesheetList", btmEmpTimeSheetRep.getTimeSheetLast(empId));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("TimesheetList", btmEmpTimeSheetRep.getTimeSheetLast(resId));
		} else if (formmode.equals("view1")) {

			md.addAttribute("formmode", "view1");
			md.addAttribute("TimesheetList", btmEmpTimeSheetRep.getTimeSheetdataView(resId, month, year));
		}

		return "BTMTimesheetMaster";
	}

	@RequestMapping(value = "timesheetMain", method = { RequestMethod.GET, RequestMethod.POST })
	public String timesheetMain(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String emp_id, @RequestParam(required = false) BigDecimal year,
			@RequestParam(required = false) String month, Model md, HttpServletRequest req) throws ParseException {

		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");

			md.addAttribute("TimesheetList", btmEmpTimeSheetRep.getTimeSheetlist());

		} else if (formmode.equals("approve")) {

			md.addAttribute("formmode", "approve");
			md.addAttribute("timeSheetVerify", btmEmpTimeSheetRep.getTimeSheetModify(emp_id, year, month));
		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("TimesheetView", btmmTimeSheetRep.getTimeSheetList(emp_id));
		} else if (formmode.equals("view1")) {

			md.addAttribute("formmode", "view1");
			md.addAttribute("timeSheetVerify", btmmTimeSheetRep.getTimeSheetVerify(emp_id, year, month));
		}

		return "BTMTimeSheetMaintenance";
	}

	@RequestMapping(value = "listOfIssue", method = { RequestMethod.GET, RequestMethod.POST })
	public String listOfIssue(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");

			md.addAttribute("IssueTracker", issueTrackerRep.findAll());

		} else if (formmode.equals("view1")) {
			md.addAttribute("formmode", "view1");
			md.addAttribute("allusers", resourceMasterRepo.gettotaluser());
			md.addAttribute("issueview", issueTrackerRep.getIssue(userid));

		}

		return "BTMIssueTracker";

	}

//   =================================== Reports Module =============================================

	@RequestMapping(value = "attendanceReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String attendanceReport(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("allusers", resourceMasterRepo.gettotaluser());
		md.addAttribute("role", resourceMasterRepo.getrole(userId));
		return "BTMAttendanceReport";
	}

	@RequestMapping(value = "employeeRegister", method = { RequestMethod.GET, RequestMethod.POST })
	public String employeeRegister(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		return "BTMEmployeeRegister";
	}

	@RequestMapping(value = "projectMasterReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String projectMasterReport(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("projectMasterList", adminOperServices.getProjectMasterlist());
		md.addAttribute("allusers", resourceMasterRepo.gettotaluser());
		return "BTMProjectMasterReports";
	}

	@RequestMapping(value = "holidayList", method = { RequestMethod.GET, RequestMethod.POST })
	public String holidayList(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("allusers", resourceMasterRepo.gettotaluser());
		md.addAttribute("menu", "BTMHeaderMenu");
		return "BTMHolidayList";
	}

	@RequestMapping(value = "profileMasterReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String profileMasterReport(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("allusers", resourceMasterRepo.gettotaluser());

		return "BTMProfileMasterReport";
	}

	@RequestMapping(value = "leaveRegister", method = { RequestMethod.GET, RequestMethod.POST })
	public String leaveRegister(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("allusers", resourceMasterRepo.gettotaluser());
		return "BTMLeaveRegister";
	}

	@RequestMapping(value = "timesheetReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String timsheetReport(@RequestParam(required = false) String EmpId,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("profileManagers", btmAdminAssociateProfileRep.getEmployeedetail2());
		md.addAttribute("allusers", resourceMasterRepo.gettotaluser());

		return "BTMTimeSheetJasperReport";
	}

	@RequestMapping(value = "workAssignReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String workAssignReport(@RequestParam(required = false) String empId,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("profileManagers", btmWorkAssignmentRep.getWorkAssigndetail());
		return "BTMWorkAssignJasperReport";
	}

	// ============================================ Placement Menu
	// =====================================

	// ==================================BankMaster=========================================

	@RequestMapping(value = "bankMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String bankMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String bank_srl_no, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("banklist", placementServices.getBanklist());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);

			md.addAttribute("banklist", bankMasterRep.getBanklist(bank_srl_no));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("banklist", bankMasterRep.getBanklist(bank_srl_no));

		} else if (formmode.equals("verify")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("banklist", bankMasterRep.getBanklist(bank_srl_no));

		} else if (formmode.equals("delete")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("banklist", bankMasterRep.getBanklist(bank_srl_no));

		} else {

			md.addAttribute("formmode", formmode);
			md.addAttribute("banklist", bankMasterRep.getBanklist(""));
		}
		return "BTMBankMaster";
	}

	@RequestMapping(value = "bankMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String bankMasterAdd(@RequestParam(required = false) String formmode, @ModelAttribute BankMaster bankMaster,
			Model md, HttpServletRequest rq) {
		String userid1 = (String) rq.getSession().getAttribute("USERID");
		String msg = placementServices.addBankuser(bankMaster, formmode, userid1);
		return msg;
	}

	@RequestMapping(value = "bankMasterModify", method = RequestMethod.POST)
	@ResponseBody
	public String bankMasterModify(@RequestParam(required = false) String bank_srl_no,
			@ModelAttribute BankMaster bankMaster, Model md, HttpServletRequest rq)
			throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException, IOException {
		String userid1 = (String) rq.getSession().getAttribute("USERID");

		String msg = placementServices.bankMasterModify(bankMaster, bank_srl_no, userid1);
		return msg;
	}

	@RequestMapping(value = "bankMasterVerify", method = RequestMethod.POST)
	@ResponseBody
	public String bankMasterVerify(@RequestParam(required = false) String bank_srl_no,
			@ModelAttribute BankMaster bankMaster, Model md, HttpServletRequest rq)
			throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException, IOException {

		String userid1 = (String) rq.getSession().getAttribute("USERID");
		String msg = placementServices.bankMasterVerify(bankMaster, bank_srl_no, userid1);
		return msg;
	}

	@RequestMapping(value = "bankMasterDelete", method = RequestMethod.POST)
	@ResponseBody
	public String bankMasterDelete(@RequestParam(required = false) String bank_srl_no,
			@ModelAttribute BankMaster bankMaster, Model md, HttpServletRequest rq)
			throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException, IOException {
		String userid1 = (String) rq.getSession().getAttribute("USERID");
		String msg = placementServices.bankMasterDelete(bankMaster, bank_srl_no, userid1);
		return msg;
	}

	// =================================clientMaster======================================

	@RequestMapping(value = "clientMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String clientMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String client_srl_no, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("clientlist", placementServices.getClientlist());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("clientlist", clientMasterRep.getClientlist(client_srl_no));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("clientlist", clientMasterRep.getClientlist(client_srl_no));

		} else if (formmode.equals("verify")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("clientlist", clientMasterRep.getClientlist(client_srl_no));

		} else if (formmode.equals("delete")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("clientlist", clientMasterRep.getClientlist(client_srl_no));

		} else {

			md.addAttribute("formmode", formmode);
			md.addAttribute("clientlist", clientMasterRep.getClientlist(" "));
		}
		return "BTMClientMaster";
	}

	@RequestMapping(value = "clientMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String clientMasterAdd(@RequestParam(required = false) String formmode,
			@ModelAttribute ClientMaster clientMaster, Model md, HttpServletRequest rq) {
		String userid1 = (String) rq.getSession().getAttribute("USERID");
		String msg = placementServices.addClientUser(clientMaster, formmode, userid1);
		return msg;
	}

	@RequestMapping(value = "clientMasterModify", method = RequestMethod.POST)
	@ResponseBody
	public String clientMasterModify(@RequestParam(required = false) String client_srl_no,
			@ModelAttribute ClientMaster clientMaster, Model md, HttpServletRequest rq)
			throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException, IOException {

		String userid1 = (String) rq.getSession().getAttribute("USERID");

		String msg = placementServices.clientMasterModify(clientMaster, client_srl_no, userid1);
		return msg;
	}

	@RequestMapping(value = "clientMasterVerify", method = RequestMethod.POST)
	@ResponseBody
	public String clientMasterVerify(@RequestParam(required = false) String client_srl_no,
			@ModelAttribute ClientMaster clientMaster, Model md, HttpServletRequest rq)
			throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException, IOException {
		String userid1 = (String) rq.getSession().getAttribute("USERID");
		String msg = placementServices.clientMasterVerify(clientMaster, client_srl_no, userid1);
		return msg;
	}

	@RequestMapping(value = "clientMasterDelete", method = RequestMethod.POST)
	@ResponseBody
	public String clientMasterDelete(@RequestParam(required = false) String client_srl_no,
			@ModelAttribute ClientMaster clientMaster, Model md, HttpServletRequest rq)
			throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException, IOException {

		String msg = placementServices.clientMasterDelete(clientMaster, client_srl_no);
		return msg;
	}

	// invoice

	@RequestMapping(value = "invoiceMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String invoiceMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String invoice_no, String inv_no, String po_id,
			@RequestParam(required = false) String EmpId, @RequestParam(required = false) String bank_name, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");

			Date currentDate = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
			String formattedDate = dateFormat.format(currentDate);
			md.addAttribute("formattedDate", formattedDate);

			/*
			 * LocalDate currentDate1 = LocalDate.now(); int month =
			 * currentDate1.getMonthValue(); int year = currentDate1.getYear(); LocalDate
			 * previousMonthDate = currentDate1.minusMonths(1); int previousMonth =
			 * previousMonthDate.getMonthValue(); int previousYear = year - 1; String
			 * yearLastTwoDigits = String.valueOf(previousYear).substring(2);
			 */

			LocalDate currentDate1 = LocalDate.now(); // Assume today's date is 2025-02-14
			int month = currentDate1.getMonthValue(); // Current month (2 for February)
			int year = currentDate1.getYear(); // Current year (2025)

			LocalDate previousMonthDate = currentDate1.minusMonths(1); // Previous month date
			int previousMonth = previousMonthDate.getMonthValue(); // Extract previous month

			String yearString = String.valueOf(year).substring(2); // Extract last two digits of year

			System.out.println("Current Year (Last 2 Digits): " + yearString);
			System.out.println("Previous Month: " + previousMonth);

			/*
			 * String yearLastTwoDigits =
			 * String.valueOf(currentDate.getYear()).substring(1);
			 */

			// md.addAttribute("invoiceMasterList", placementServices.getplacementlist2());
			md.addAttribute("invoiceMasterList", invoiceMasterRep.getplacementlist1(previousMonth, yearString));

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("banklist", bankMasterRep.getBanklist2());
			md.addAttribute("clients", clientMasterRep.getClientlist2());

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("invoiceMasterList", invoiceMasterRep.getplacementlist2(po_id));
			// md.addAttribute("invoiceMasterList",
			// placementMaintenanceRep.getinvoicelist(inv_no));
			// md.addAttribute("clients", clientMasterRep.getClientlist2());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("invoiceMasterList", invoiceMasterRep.getplacementlist2(po_id));

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "BTMInvoiceMaster";
	}

	@RequestMapping(value = "invoiceMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String invoiceMasterAdd(@RequestParam(required = false) String formmode,
			@ModelAttribute InvoiceMaster invoiceMaster, Model md, HttpServletRequest rq) {
		String msg = placementServices.addInvoiceUser(invoiceMaster, formmode);
		return msg;
	}

	@RequestMapping(value = "invoiceMasterModify", method = RequestMethod.POST)
	@ResponseBody
	public String invoiceMasterModify(@RequestParam(required = false) String po_id,
			@ModelAttribute InvoiceMaster invoiceMaster, Model md, HttpServletRequest rq)
			throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException, IOException {
		System.out.println("poidddddddddddddddddddddddddddddd" + po_id);
		String msg = placementServices.invoiceMasterModify(invoiceMaster, po_id);
		return msg;
	}

	@RequestMapping(value = "/invoicedelete", method = RequestMethod.POST)

	@ResponseBody
	public String invoicedelete(@RequestParam(required = false) String po_id, PlacementMaintenance placementMaintenance,
			InvoiceMaster invoiceMaster, Model md, HttpServletRequest rq) {

		PlacementMaintenance up = placementMaintenanceRep.getPoM(po_id);
		if (up == null) {
			return "Error: PlacementMaintenance not found for po_id " + po_id;
		}
		placementMaintenanceRep.delete(up);

		InvoiceMaster up1 = invoiceMasterRep.getplacementlist2(po_id);
		if (up1 == null) {
			return "Error: InvoiceMaster not found for po_id " + po_id;
		}
		invoiceMasterRep.delete(up1);

		return "Deleted successfully";

	}

	// ==================================================================================================

	@RequestMapping(value = "resourceMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String resourceMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String EmpId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("placementProfileList", placementServices.getUsersList());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("placementProfile", placementServices.getUser(EmpId));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("placementProfile", placementServices.getUser(EmpId));

		} else {

			md.addAttribute("formmode", formmode);
			md.addAttribute("placementProfile", placementServices.getUser(""));
		}
		return "BTMResourceMaster";
	}

	@RequestMapping(value = "resourceMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String resourceMasterAdd(@RequestParam("formmode") String formmode,
			@ModelAttribute PlacementResourceMaster placementResourceMaster, Model md, HttpServletRequest rq) {
		String msg = placementServices.addUser(placementResourceMaster, formmode);
		return msg;
	}

	@RequestMapping(value = "placementMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String placementMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String EmpId, @RequestParam(required = false) String Placement_id,
			@RequestParam(required = false) String EmpName, @RequestParam(required = false) String mobile_no,
			@RequestParam(required = false) String email, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");

		} else if (formmode.equals("list1")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("Placement_id", Placement_id);
			md.addAttribute("EmpName", EmpName);
			md.addAttribute("mobile_no", mobile_no);
			md.addAttribute("email", email);
			md.addAttribute("placementList", placementServices.getPlacementMasterlist(Placement_id, EmpName));

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("placementProfile", placementServices.getPlacementUser(EmpId));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("placementProfile", placementServices.getPlacementUser(EmpId));
		} else {
			md.addAttribute("formmode", formmode);
			md.addAttribute("placementProfile", placementServices.getPlacementUser(""));
		}

		return "BTMPlacementMaster";
	}

	@RequestMapping(value = "placementMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String placementMasterAdd(@RequestParam("formmode") String formmode,
			@ModelAttribute PlacementMaster placementMaster, Model md, HttpServletRequest rq) {

		String msg = placementServices.addPlacementUser(placementMaster, formmode);
		return msg;
	}

	@RequestMapping(value = "timesheetManagement", method = { RequestMethod.GET, RequestMethod.POST })
	public String timesheetManagement(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String EmpId, @RequestParam(required = false) String Placement_id,
			@RequestParam(required = false) String EmpName, @RequestParam(required = false) String mobile_no,
			@RequestParam(required = false) String email, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");

		} else if (formmode.equals("list1")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("Placement_id", Placement_id);
			md.addAttribute("EmpName", EmpName);
			md.addAttribute("mobile_no", mobile_no);
			md.addAttribute("email", email);
			md.addAttribute("timeSheetList", placementServices.getTimesheetManagementList(Placement_id, EmpName));

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("placementProfile", placementServices.getTimesheetUser(EmpId));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("placementProfile", placementServices.getTimesheetUser(EmpId));

		} else {

			md.addAttribute("formmode", formmode);
			md.addAttribute("placementProfile", placementServices.getTimesheetUser(""));
		}

		return "BTMTimesheetManagement";
	}

	@RequestMapping(value = "timesheetManagementAdd", method = RequestMethod.POST)
	@ResponseBody
	public String timesheetManagementAdd(@RequestParam("formmode") String formmode,
			@ModelAttribute TimesheetManagement timesheetManagement, Model md, HttpServletRequest rq) {

		String msg = placementServices.addTimesheetUser(timesheetManagement, formmode);
		return msg;
	}

	@RequestMapping(value = "professionalCharge", method = { RequestMethod.GET, RequestMethod.POST })
	public String professionalCharge(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal SerialNo, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("professionalChargeList", placementServices.getUsersList1());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("professionalCharge", placementServices.getUser1(SerialNo));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("professionalCharge", placementServices.getUser1(SerialNo));

		} else {

			md.addAttribute("formmode", formmode);
		}

		return "BTMProfessionalCharge";
	}

	@RequestMapping(value = "professionalChargeAdd", method = RequestMethod.POST)

	@ResponseBody
	public String professionalChargeAdd(@RequestParam("formmode") String formmode,
			@ModelAttribute ProfessionalCharge professionalCharge, Model md, HttpServletRequest rq) {

		String msg = placementServices.addUser1(professionalCharge, formmode);
		return msg;
	}

	// ========================== Jasper download ========================/

	@RequestMapping(value = "invoiceReportDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource invoiceReportDownload(HttpServletResponse response,

			@RequestParam(value = "inv_no", required = false) String inv_no,

			@RequestParam(value = "filetype", required = false) String filetype) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			logger.info("Getting download File :" + inv_no + ", FileType :" + filetype + "");

			File repfile = placementServices.getFile(inv_no, filetype);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	@RequestMapping(value = "AttendanceReportDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource AttendanceReportDownload(HttpServletResponse response,

			@RequestParam(value = "emp_id", required = false) String emp_id,
			@RequestParam(value = "cal_month", required = false) String cal_month,
			@RequestParam(value = "cal_year", required = false) String cal_year,

			@RequestParam(value = "report_type", required = false) String report_type)
			throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			logger.info("Getting download File :" + emp_id + ", FileType :" + report_type + "");

			File repfile = reportServices.getFileAttendance(emp_id, cal_month, cal_year, report_type);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	@RequestMapping(value = "AttendanceRegisterDailyReport", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource AttendanceRegisterDailyReport(HttpServletResponse response,

			@RequestParam(value = "login_time", required = false) Date login_time,
			@RequestParam(value = "report_type", required = false) String report_type)
			throws IOException, SQLException {

		response.setContentType("application/octet-stream");
		SimpleDateFormat formatday = new SimpleDateFormat("dd");
		SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
		SimpleDateFormat formatyear = new SimpleDateFormat("yyyy");
		String cal_month = formatMonth.format(login_time);
		String cal_year = formatyear.format(login_time);
		String cal_date = formatday.format(login_time);
		InputStreamResource resource = null;
		try {

			logger.info("Getting download File :" + cal_month + "_" + cal_year + "_" + cal_date + ", FileType :"
					+ report_type + "");

			File repfile = reportServices.getFileDailyAttendance(cal_year, cal_month, cal_date, report_type);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());

			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	@RequestMapping(value = "AttendanceRegisterMonthReport", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource AttendanceRegisterMonthReport(HttpServletResponse response,
			@RequestParam(value = "cal_month", required = false) String cal_month,
			@RequestParam(value = "cal_year", required = false) String cal_year,

			@RequestParam(value = "report_type", required = false) String report_type)
			throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			logger.info("Getting download File :" + cal_month + ", FileType :" + report_type + "");

			File repfile = reportServices.getFileMonthyAttendance(cal_month, cal_year, report_type);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	@RequestMapping(value = "leaveRegisterReportDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource leaveRegisterReportDownload(HttpServletResponse response,
			@RequestParam(value = "employee_id", required = false) String employee_id,
			@RequestParam(value = "year1", required = false) String year1,
			@RequestParam(required = false) String leave_category,

			@RequestParam(value = "report_type", required = false) String report_type)
			throws IOException, SQLException {

		int year = Integer.parseInt(year1);
		System.out.println(year1 + "year11111" + employee_id + "employee_iddddd");

		String updateLeaveBalancesForAllEmployees = "yes";
		if (updateLeaveBalancesForAllEmployees == "yes") {
			int updateleave = leaveMasterRep.updateLeaveBalances(employee_id, year);
			System.out.println(updateleave + "updateleaveeeee");
		}
		System.out.println(leave_category + "leave_category");
		response.setContentType("application/octet-stream");
		InputStreamResource resource = null;
		if ("ALL".equals(leave_category)) {
			try {
				System.out.println("all here we give all the leave of the resources ");
				logger.info("Getting download File :" + employee_id + ", FileType :" + report_type + "");

				File repfile = reportServices.getFileLeaveRegisterALL(employee_id, year1, leave_category, report_type);

				response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
				resource = new InputStreamResource(new FileInputStream(repfile));

			} catch (JRException e) {

				e.printStackTrace();
			}
		} else {
			try {

				logger.info("Getting download File :" + employee_id + ", FileType :" + report_type + "");

				File repfile = reportServices.getFileLeaveRegister(employee_id, year1, leave_category, report_type);

				response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
				resource = new InputStreamResource(new FileInputStream(repfile));

			} catch (JRException e) {

				e.printStackTrace();
			}
		}

		return resource;
	}

	@RequestMapping(value = "projectMasterReportDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource projectMasterReportDownload(HttpServletResponse response,
			@RequestParam(value = "proj_id", required = false) String proj_id,

			@RequestParam(value = "report_type", required = false) String report_type)
			throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			logger.info("Getting download File for proj master :" + proj_id + ", FileType :" + report_type + "");

			File repfile = reportServices.getFileProject(proj_id, report_type);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	@RequestMapping(value = "holidayListReportDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource holidayListReportDownload(HttpServletResponse response,
			@RequestParam(value = "cal_year", required = true) String cal_year,
			@RequestParam(value = "detailsRequired", required = false) String detailsRequired,

			@RequestParam(value = "report_type", required = false) String report_type)
			throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			logger.info("Getting download File :" + cal_year + ", FileType :" + report_type + "");
			if (detailsRequired.equals("No")) {
				File repfile = reportServices.getFileHolidayList(cal_year, report_type);

				response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
				resource = new InputStreamResource(new FileInputStream(repfile));
			} else if (detailsRequired.equals("Yes")) {
				File repfile = reportServices.getFileHolidayDetailsList(cal_year, report_type);

				response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
				resource = new InputStreamResource(new FileInputStream(repfile));
			}

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	@RequestMapping(value = "timesheetReportDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource timesheetReportDownload(HttpServletResponse response,
			@RequestParam(value = "emp_id", required = true) String emp_id,
			@RequestParam(value = "year", required = false) String year,
			@RequestParam(value = "month", required = false) String month,

			@RequestParam(value = "report_type", required = false) String report_type)
			throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			logger.info("Getting download File :" + emp_id + ", FileType :" + report_type + "");

			File repfile = reportServices.getFileTimeSheet(emp_id, year, month, report_type);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	@RequestMapping(value = "workAssignReportDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource workAssignReportDownload(HttpServletResponse response,
			@RequestParam(value = "emp_id", required = true) String emp_id,
			@RequestParam(value = "report_type", required = false) String report_type)
			throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			logger.info("Getting download File :" + emp_id + ", FileType :" + report_type + "");

			File repfile = reportServices.getFileWorkAssign(emp_id, report_type);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	@RequestMapping(value = "profileMasterReportDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource profileMasterReportDownload(HttpServletResponse response,
			@RequestParam(value = "emp_id", required = true) String emp_id,
			@RequestParam(value = "profileType", required = false) String profileType,
			@RequestParam(value = "reportType", required = false) String report_type) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			logger.info("Getting downloaded File :" + emp_id + ", FileType :" + report_type + "");

			File repfile = reportServices.getFileProfileMaster(emp_id, profileType, report_type);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	// ========================================== Maintenance Module
	// ===========================================

	@RequestMapping(value = "MtAssociateProfile", method = { RequestMethod.GET, RequestMethod.POST })

	public String MtAssociateProfile(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("adminAssociateProfileList", btmAdminAssociateProfileRep.getAssociatelist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("adminAssociateProfile", adminOperServices.getAssociteUser(resId));

		}
		return "BTMMAssociateProfile";
	}

	@RequestMapping(value = "MtAssociateProfileAdd", method = RequestMethod.POST)
	@ResponseBody
	public String MtAssociateProfileAdd(@RequestParam("formmode") String formmode,
			@RequestParam("userid") String userid, @ModelAttribute BTMAdminAssociateProfile btmAdminAssociateProfile,
			Model md, HttpServletRequest rq) {

		String msg = adminOperServices.modifyAssociate(btmAdminAssociateProfile, formmode, userid);
		return msg;
	}

	@RequestMapping(value = "MtProjectMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String MtProjectMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) String resName, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("projectMasterList", adminOperServices.getProjectMasterlist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("MtprojectList", btmProjectMasterRep.getProjectShow(resId, resName));
			md.addAttribute("projectDetails", btmProjectDetailsRep.getProjectDetails(resId));
			md.addAttribute("projectTeamDetails", btmProjectTeamDetailsRep.getProjectTeamDetails(resId));

		}

		return "BTMMtProjectMaster";
	}

	@RequestMapping(value = "MtLeaveMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String MtLeaveMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal resId, @RequestParam(required = false) String RefId, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("AdminLeaveList", leaveMasterRep.getLeavelist1());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("MtLeaveMaster", onDutyServices.getLeaveDetail(resId));
			md.addAttribute("LeaveListCounter", leaveMasterCounterRep.getLeaveCounterlist(RefId));

		}

		return "BTMMLeaveMaster";
	}

	@RequestMapping(value = "MtleaveMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String MtleaveMasterAdd(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) String employeeId,
			@ModelAttribute LeaveMaster leaveMaster, Model md, HttpServletRequest rq) {

		System.out.println("Rejected leave controller");
		BigDecimal year = leaveMaster.getYear();
		Date fromDate = leaveMaster.getFrom_date();

		// Create SimpleDateFormat with the desired format
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		// Format the date
		String formattedDate = sdf.format(fromDate);

		// Output the formatted date
		System.out.println("Formatted Date: " + formattedDate);

		Date todate = leaveMaster.getTo_date();

		// Create SimpleDateFormat with the desired format
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");

		// Format the date
		String todate1 = sdf1.format(todate);

		// Output the formatted date
		System.out.println("todate1 Date: " + todate1);
		String leave_reference = leaveMaster.getLeave_reference();
		System.out.println(year + "year" + formattedDate + "formattedDate" + leave_reference + "leave_reference"
				+ todate1 + "todate1");

		String Id = (String) rq.getSession().getAttribute("USERID");

		// String msg=null;
		String msg = adminOperServices.modifyLeave(leaveMaster, formmode, userid, Id, employeeId, year, formattedDate,
				leave_reference, todate1);

		return msg;
	}

	@RequestMapping(value = "MtOnDuty", method = { RequestMethod.GET, RequestMethod.POST })
	public String MtOnDuty(@RequestParam(required = false) String formmode,

			@RequestParam(required = false) String resId, @RequestParam(required = false) String RefId, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");

			md.addAttribute("AdminODList", onDutyRep.getODlist1());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");

			md.addAttribute("MtOnDuty", onDutyServices.getODDetail(resId));

			md.addAttribute("onDutyListCount", bTMAdminOndutyCountRep.getOndutyCounterlist(RefId));

		}

		return "BTMMtOnDuty";
	}

	@RequestMapping(value = "MtOdMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String MtOdMasterAdd(@RequestParam("formmode") String formmode, @RequestParam("userid") String userid,
			@ModelAttribute OnDuty onDuty, Model md, HttpServletRequest rq) throws ParseException {
		String Id = (String) rq.getSession().getAttribute("USERID");
		String msg = adminOperServices.modifyOd(onDuty, formmode, userid, Id);

		return msg;
	}

	@RequestMapping(value = "MtTimesheetMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String MtTimesheetMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		String empid = (String) req.getSession().getAttribute("USERID");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");

			md.addAttribute("WorkAssignmentList", btmWorkAssignmentRep.getWorkAssignlist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("WorkAssignment", btmWorkAssignmentRep.getWorkAssign(resId));

		}

		return "BTMAdminTimesheetMaster";
	}

	@RequestMapping(value = "MtTravelMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String MtTravelMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("AdminTravelList", btmTravelMasterRep.getTravellist1());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("MtTravelMaster", adminOperServices.getTravelMaster(resId));

		}

		return "BTMMtTravelMaster";
	}

	@RequestMapping(value = "MtTravelMasterAdd", method = RequestMethod.POST)
	@ResponseBody
	public String MtTravelMasterAdd(@RequestParam("formmode") String formmode, @RequestParam("userid") String userid,
			@ModelAttribute BTMTravelMaster travelMaster, Model md, HttpServletRequest rq) throws ParseException {

		String msg = adminOperServices.modifyTravelMaster(travelMaster, formmode, userid);
		return msg;
	}

	@RequestMapping(value = "MtExpenseReport", method = { RequestMethod.GET, RequestMethod.POST })
	public String MtExpenseReport(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) String userid,
			@RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("report", btmAdminExpenseReportRep.getReportList1());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("MtExpenseReport", adminOperServices.getReportManager(resId));

		}
		return "BTMMtExpenseReport";

	}

	@RequestMapping(value = "MtExpenseReportAdd", method = RequestMethod.POST)
	@ResponseBody
	public String MtExpenseReportAdd(@RequestParam("formmode") String formmode, @RequestParam("userid") String userid,
			@ModelAttribute ExpenseMaster expenses, Model md, HttpServletRequest rq) throws ParseException {

		String msg = adminOperServices.modifyExpense(expenses, formmode, userid);
		return msg;
	}

	@RequestMapping(value = "MtEventMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String MtEventMaster(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("eventMasterList", btmEventMasterRep.getScreenlist());

		}

		return "BTMMtEventMaster";
	}

	@RequestMapping(value = "MtWorkAssignment", method = { RequestMethod.GET, RequestMethod.POST })
	public String MtWorkAssignment(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("view")) {

			md.addAttribute("formmode", "view");

		} else if (formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("WorkAssignmentList", btmWorkAssignmentRep.getWorkAssignlist());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", "add");
			md.addAttribute("profileManagers", btmAdminAssociateProfileRep.getEmployeedetail2());

		} else if (formmode.equals("list1")) {

			md.addAttribute("formmode", "list1");
			md.addAttribute("WorkAssignmentList", btmWorkAssignmentRep.getWorkAssignlist());

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("WorkAssignmentList", btmWorkAssignmentRep.getWorkAssignlist());
			md.addAttribute("WorkAssignment", btmWorkAssignmentRep.getWorkAssign(resId));

		} else if (formmode.equals("view1")) {

			md.addAttribute("formmode", "view1");
			md.addAttribute("WorkAssignment", btmWorkAssignmentRep.getWorkAssign(resId));

		} else if (formmode.equals("approveList")) {

			md.addAttribute("formmode", "approveList");
			md.addAttribute("WorkAssignmentList", btmWorkAssignmentRep.getWorkAssignlist());
			md.addAttribute("WorkAssignment", btmWorkAssignmentRep.getWorkAssign(resId));

		} else if (formmode.equals("view2")) {

			md.addAttribute("formmode", "view2");
			md.addAttribute("WorkAssignment", btmWorkAssignmentRep.getWorkAssign(resId));

		}

		return "BTMMtWorkAssignment";
	}

	@RequestMapping(value = "MtWorkAssignmentAdd", method = RequestMethod.POST)
	@ResponseBody
	public String MtWorkAssignmentAdd(@RequestParam("formmode") String formmode, @RequestParam("userid") String userid,
			@RequestParam("emp_name") String emp_name, @ModelAttribute BTMWorkAssignment btmWorkAssignment, Model md,
			HttpServletRequest rq) throws ParseException {

		String msg = maintenanceOperServices.addWorkAssign(btmWorkAssignment, formmode, userid, emp_name);
		return msg;
	}

	@RequestMapping(value = "Pomaintenance", method = { RequestMethod.GET, RequestMethod.POST })
	public String Pomaintenance(@RequestParam(required = false) String formmode, String po_delivery_date,
			String po_month, @RequestParam(required = false) String po_no, @RequestParam(required = false) String po_id,
			Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");

		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		System.out.println(formmode);
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("polist", placementmaintenancerep.getMaintenance());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("sview")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("fview")) {
			md.addAttribute("poidlist", placementmaintenancerep.getPoId(po_id));
			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("success")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("slist", placementmaintenancerep.getSdetail());

		} else if (formmode.equals("failure")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("flist", placementmaintenancerep.getFdetail());
		}

		else if (formmode.equals("upload")) {

			md.addAttribute("formmode", formmode);

		}

		else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);
			System.out.println(po_id);
			PlacementMaintenance oo = placementmaintenancerep.getMaintenancelist(po_id);
			System.out.println(":::::::::" + oo.getPo_no());

			md.addAttribute("polist", placementmaintenancerep.getPoMaintenance(po_id));
			md.addAttribute("pmlist", oo);
			md.addAttribute("ilist", placementmaintenancerep.getPolist(oo.getPo_no()));

		}

		else if (formmode.equals("view")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("polist", placementmaintenancerep.getPoMaintenance(po_no));
		} else if (formmode.equals("Modifyg")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("po", placementmaintenancerep.getPoM(po_id));
			// md.addAttribute("polist", placementmaintenancerep.getPoMaintenance(po_id));
			// md.addAttribute("po",placementmaintenancerep.getPoM(po_no));
		} else if (formmode.equals("ModifyI")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("po", placementmaintenancerep.getPoM(po_id));
			// md.addAttribute("polist", placementmaintenancerep.getPoMaintenance(po_id));
			// md.addAttribute("po",placementmaintenancerep.getPoM(po_no));
		} else if (formmode.equals("modify")) {
			md.addAttribute("formmode", formmode);
			md.addAttribute("po", placementmaintenancerep.getPoM(po_id));
			md.addAttribute("polist", placementmaintenancerep.getPoMaintenance(po_no));
		} else if (formmode.equals("ModifyR")) {
			System.out.println(formmode);
			md.addAttribute("formmode", formmode);
			md.addAttribute("po", placementmaintenancerep.getPoM(po_id));
			// md.addAttribute("polist", placementmaintenancerep.getPoMaintenance(po_no));
		}

		return "Pomaintenance";
	}

	@RequestMapping(value = "PurchaseOrders", method = { RequestMethod.GET, RequestMethod.POST })
	public String PurchaseOrders(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String po_no, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("polist", placementmaintenancerep.getpodetails());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "PO";
	}

	@RequestMapping(value = "Remittances", method = { RequestMethod.GET, RequestMethod.POST })
	public String Remittence(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String po_no, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("polist", placementmaintenancerep.getpodetails());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		}

		else if (formmode.equals("upload")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "Remittance";
	}

	@RequestMapping(value = "SPRates", method = { RequestMethod.GET, RequestMethod.POST })
	public String SPRates(@RequestParam(required = false) String formmode, @RequestParam(required = false) String po_no,
			Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("polist", placementmaintenancerep.getpodetails());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "SPRates";
	}

	@RequestMapping(value = "GRNDetails", method = { RequestMethod.GET, RequestMethod.POST })
	public String GRNDetails(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String po_no, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("polist", placementmaintenancerep.getpodetails());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "Grn";
	}

	@RequestMapping(value = "InvoiceDetails", method = { RequestMethod.GET, RequestMethod.POST })
	public String InvoiceDetails(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String po_no, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("polist", placementmaintenancerep.getpodetails());
		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "InvDetails";
	}

	@RequestMapping(value = "Acknowledgement", method = { RequestMethod.GET, RequestMethod.POST })
	public String Acknowledgement(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String po_no, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu",resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("polist", placementmaintenancerep.getpodetails());
		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "InvStatus";
	}

	@RequestMapping(value = "SPInvoices", method = { RequestMethod.GET, RequestMethod.POST })
	public String SPInvoices(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String po_no, @RequestParam(required = false) String poid, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("polist", placementmaintenancerep.getpodetails1());
			// System.out.println("inv" + placementmaintenancerep.getpodetails());
		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {
			String a1 = null;
			PlacementMaintenance myobj = placementmaintenancerep.getpodetailsedit(poid);
			md.addAttribute("editing", placementmaintenancerep.getpodetailsedit(poid));
			a1 = myobj.getPo_id();
			System.out.println("placement edit" + a1);

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("upload")) {

			md.addAttribute("formmode", formmode);

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "Ivc";
	}

	@RequestMapping(value = "SPPayments", method = { RequestMethod.GET, RequestMethod.POST })
	public String SPPayments(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String po_no, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("polist", placementmaintenancerep.getpodetails());
		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "SPpay";
	}

	@RequestMapping(value = "PurchaseOrderDetails", method = { RequestMethod.GET, RequestMethod.POST })
	public String PurchaseOrderDetails(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String po_no, Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("polist", placementmaintenancerep.getpodetails());
		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", formmode);

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "PurchaseOrderDetails";
	}

	@RequestMapping(value = "CreatePMAdd", method = RequestMethod.POST)
	@ResponseBody
	public String CreatePMAdd(@RequestParam(required = false) String formmode,
			@ModelAttribute PlacementMaintenance placementmaintenance, Model md, HttpServletRequest rq) {

		System.out.println(formmode + placementmaintenance.getPo_no() + placementmaintenance.getPo_month()
				+ placementmaintenance.getPo_id());

		String msg = placementServices.CreatePMAdd(placementmaintenance, formmode);
		System.out.println(msg);
		return msg;
	}

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	@ResponseBody
	public String upload(@RequestParam(required = false) String formmode,
			@ModelAttribute PlacementMaintenance placementmaintenance, Model md, HttpServletRequest rq) {
		String msg = placementServices.upload(placementmaintenance, formmode);
		return msg;
	}

	@RequestMapping(value = "logoutsub", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String logoutsub(Model md, HttpServletRequest rq) throws ParseException {

		String userid1 = (String) rq.getSession().getAttribute("USERID");
		String msg = attendanceRegisterService.logoutsubmit(userid1);
		return "BTMStart.html";
	}

	@RequestMapping(value = "TimesheetVerify", method = RequestMethod.POST)
	@ResponseBody
	public String TimesheetVerify(@RequestParam(required = false) String emp_id,
			@RequestParam(required = false) BigDecimal year, @RequestParam(required = false) String month,
			@ModelAttribute BTMEmpTimeSheet time, Model md, HttpServletRequest rq)
			throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException, IOException {
		String userid = (String) rq.getSession().getAttribute("USERID");

		String msg = timeSheetPdf.timeSheetVerify(time, emp_id, year, month, userid);
		return msg;
	}

	// INQURIES ATTENDANCE REGISTER

	@RequestMapping(value = "attendanceRegisterInquries", method = { RequestMethod.GET, RequestMethod.POST })
	public String attendanceRegisterInquries(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal serialNo, Model md, HttpServletRequest req)
			throws ParseException {

		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat formatdate = new SimpleDateFormat("dd/MM/yyyy");
		String str = formatdate.format(cal.getTime());
		Date dat1 = null;
		try {
			dat1 = formatdate.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// resourceMasterRepo.gettotaluser();
		// int totalemployees = resourceMasterRepo.gettotalnum();
		// int present = attendanceRegisterRep.getALLpresent(dat1);
		// int absent = totalemployees-present;
		// int onduty = 0;
		md.addAttribute("attendanceList", attendanceRegisterRep.getALL(dat1));
		// md.addAttribute("numofEmployees",totalemployees);
		// md.addAttribute("numofPresent",present);
		// md.addAttribute("numofAbsent",absent);
		// md.applyLeave("numofOnduty",onduty);
		md.addAttribute("allusers", resourceMasterRepo.gettotaluser());
		SimpleDateFormat formatMonth = new SimpleDateFormat("MM");
		String month = formatMonth.format(cal.getTime());

		md.addAttribute("CurrentMonth", month);
		SimpleDateFormat formatyear = new SimpleDateFormat("yyyy");
		String year = formatyear.format(cal.getTime());
		md.addAttribute("Currentyear", year);

		return "BTMAttendanceRegisterInquires";
	}

	@RequestMapping(value = "BTMAdminAccessandRole", method = { RequestMethod.GET, RequestMethod.POST })
	public String BTMAdminAccessandRole(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String role_id, Model md, HttpServletRequest req)
			throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException {

		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("accessRolesList", accessRolesRep.getRolelist());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("BTMAccessRole", new AccessRoles());

		} else if (formmode.equals("view")) {
			System.out.println("role_id" + role_id);
			md.addAttribute("formmode", "view");
			md.addAttribute("accessRolesview", accessRolesRep.getView(role_id));
		} else if (formmode.equals("modify")) {
			System.out.println("role_id" + role_id);
			md.addAttribute("formmode", "modify");
			md.addAttribute("accessRolesview", accessRolesRep.getView(role_id));
		} else {

			md.addAttribute("formmode", formmode);

		}

		return "AccessRole";
	}

	@RequestMapping(value = "addAccessRole", method = RequestMethod.POST)
	@ResponseBody

	public String addAccessRole(@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq,
			@RequestParam(value = "adminValue", required = false) String adminValue,
			@RequestParam(value = "auditValue", required = false) String auditValue,
			@RequestParam(value = "IPSOperationsValue", required = false) String IPSOperationsValue,
			@RequestParam(value = "monitoringValue", required = false) String monitoringValue,
			@RequestParam(value = "my_tregistrationValue", required = false) String my_tregistrationValue,
			@RequestParam(value = "finalString", required = false) String finalString, @ModelAttribute AccessRoles ar) {
		AccessRoles up = new AccessRoles();
		up.setRole_id(ar.getRole_id());
		up.setRole_desc(ar.getRole_desc());
		up.setPermissions(ar.getPermissions());
		up.setRemarks(ar.getRemarks());
		up.setWork_class(ar.getWork_class());
		up.setMenulist(finalString);
		up.setAdmin(adminValue);
		up.setAudit_operations(auditValue);
		up.setIps_operations(IPSOperationsValue);
		up.setMonitoring(monitoringValue);
		up.setMyt_registration(my_tregistrationValue);
		;
		accessRolesRep.save(up);
		String msg = "Role Added Successfully";
		return msg;

	}

	@PostMapping("/modifyAccessRole")
	@ResponseBody
	public String modifyAccessRole(@RequestParam String role_id, @RequestParam String role_desc,
			@RequestParam String permissions, @RequestParam String work_class, @RequestParam String remarks,
			@RequestParam(required = false) String adminValue, @RequestParam(required = false) String auditValue,
			@RequestParam(required = false) String IPSOperationsValue,
			@RequestParam(required = false) String monitoringValue,
			@RequestParam(required = false) String my_tregistrationValue,
			@RequestParam(required = false) String finalString, @RequestParam(required = false) String opeartion) {

		System.out.println("role_id" + role_id + "role_desc" + role_desc + " " + permissions + " " + work_class + " "
				+ remarks + " " + finalString);
		AccessRoles role = accessRolesRep.findByIds(role_id);
		System.out.println("role" + role);
		role.setRole_desc(role_desc);
		role.setPermissions(permissions);
		role.setWork_class(work_class);
		role.setRemarks(remarks);
		role.setAdmin(adminValue);
		role.setAudit_operations(auditValue);
		role.setAudit_operations(opeartion);
		role.setIps_operations(IPSOperationsValue);
		role.setMonitoring(monitoringValue);
		role.setMyt_registration(my_tregistrationValue);
		role.setMenulist(finalString);
		accessRolesRep.save(role);
		return "Role Updated Successfully";
	}

	@DeleteMapping("/deleteAccessRole")
	@ResponseBody
	public String deleteAccessRole(@RequestParam String role_id) {
		System.out.println("role_id" + role_id);

		accessRolesRep.deleteRoleId(role_id);

		return "Role Deleted Successfully";
	}

	@RequestMapping(value = "fsubmit", method = RequestMethod.POST)
	@ResponseBody
	public String fsubmit(@RequestParam(required = false) String po_id, Model md, HttpServletRequest rq,
			@ModelAttribute PlacementMaintenance placementMaintenance) {
		System.out.println("Hi" + placementMaintenance.getPo_id());
		System.out.println(placementMaintenance.getPo_no());

		PlacementMaintenance up = placementMaintenanceRep.getPoId(po_id);
		up.setPo_no(placementMaintenance.getPo_no());
		up.setPo_delivery_date(placementMaintenance.getPo_delivery_date());
		up.setEmp_id(placementMaintenance.getEmp_id());

		up.setMessage("SUCCESS");
		up.setFlag('Y');
		// System.out.println(up.getPo_no());
		placementMaintenanceRep.save(up);
		return "success";

	}

	@RequestMapping(value = "PO_Status", method = { RequestMethod.GET, RequestMethod.POST })
	public String PO_Status(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String salary_code, Model md, HttpServletRequest req)
			throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		/* md.addAttribute("menu", "paystructure"); */
		System.out.println("hi");
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("postatus_notnull", placementMaintenanceRep.getponulldetails());
		}

		return "PO_Status";

	}

	@RequestMapping(value = "GRNStatus", method = { RequestMethod.GET, RequestMethod.POST })
	public String GRNStatus(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String name, Model md, HttpServletRequest req,
			@ModelAttribute PlacementMaintenance placementMaintenance)
			throws NoSuchAlgorithmException, InvalidKeySpecException, ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		/* md.addAttribute("menu", "paystructure"); */

		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("grnstsnotnull", placementMaintenanceRep.grnstsnotnull());

		}

		return "GRNStatus";
	}

	@RequestMapping(value = "remainder", method = { RequestMethod.GET, RequestMethod.POST })
	public String remainder(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, Model md, HttpServletRequest req) throws ParseException {

		String userid1 = (String) req.getSession().getAttribute("USERID");
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("postatus_notnull", placementMaintenanceRep.getponulldetails());
			System.out.println("placementMaintenanceRep.getponulldetails()"
					+ placementMaintenanceRep.getponulldetails().toString());
		}

		return "Remainder";
	}

	@RequestMapping(value = "spinvoices", method = { RequestMethod.GET, RequestMethod.POST })
	public String spinvoices(@RequestParam(required = false) String formmode, @RequestParam(required = false) String sp,
			@RequestParam(required = false) String inv_due_date, @RequestParam(required = false) String inv_date,
			Model md, HttpServletRequest req) throws ParseException {
		String userid1 = (String) req.getSession().getAttribute("USERID");
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("postatus_notnull", placementMaintenanceRep.getponulldetails());
		} else if (formmode.equals("table")) {
			System.out.println(inv_due_date);
			System.out.println(inv_date);

			List<String> myList = new ArrayList<>();

			// Add elements to the list

			md.addAttribute("formmode", "table");
			md.addAttribute("fuc", sp);
			md.addAttribute("fuc1", inv_due_date);
			md.addAttribute("fuc2", inv_date);
			md.addAttribute("splist", placementMaintenanceRep.getsplist(sp, inv_due_date, inv_date));
			
			BigDecimal sumInvAmt = placementMaintenanceRep.getSumSpInvAmt(sp, inv_due_date, inv_date);
			md.addAttribute("sumInvAmt", sumInvAmt);
			System.out.println("sumInvAmt"+sumInvAmt);
			BigDecimal sumTotGst = placementMaintenanceRep.getSumSpInvTotGst(sp, inv_due_date, inv_date);
			md.addAttribute("sumTotGst", sumTotGst);
			System.out.println("sumTotGst"+sumTotGst);
			BigDecimal sumTotAmt = placementMaintenanceRep.getSumSpInvTotAmt(sp, inv_due_date, inv_date);
			md.addAttribute("sumTotAmt", sumTotAmt);
			System.out.println("sumTotAmt"+sumTotAmt);
		}

		return "SPINVOICES";
	}

	@RequestMapping(value = "spf", method = { RequestMethod.GET, RequestMethod.POST })
	public String spf(@RequestParam(required = false) String formmode, @RequestParam(required = false) String resName,
			@RequestParam(required = false) String a, @RequestParam(required = false) String Month, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode == "table") {
			md.addAttribute("formmode", "table");
		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("editing", Spf_repo.findit(a));
		} else if (formmode.equals("list")) {
			if (Month != null || Month == "") {
				System.out.println("The month passed" + Month);
				List<spf_entity> spfValues = Spf_repo.getall(Month);

				md.addAttribute("ghj", spfValues);
				md.addAttribute("formmode", "list");
				md.addAttribute("month", Month);
			} else {
				YearMonth currentYearMonth = YearMonth.now();

				// Format the current month and year as a string
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
				String formattedMonthYear = currentYearMonth.format(formatter);
				// String[] parts = formattedMonthYear.split(" ");
				// Print the current month and year
				System.out.println("Current Month and Year: " + formattedMonthYear);

				List<spf_entity> spfValues = Spf_repo.getall(formattedMonthYear);

				md.addAttribute("ghj", spfValues);
				md.addAttribute("formmode", "list");

			}

		}

		return "spf";
	}

	@RequestMapping(value = "SPFDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource SPFDownload(HttpServletResponse response

	) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			String filetype = "Excel";
			// logger.info("Getting download File :" + + ", FileType :Excel" + + "");

			File repfile = placementServices.getFile1(filetype);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	@RequestMapping(value = "ESIDownload", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource ESIDownload(HttpServletResponse response

	) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {

			String filetype = "Excel";
			// logger.info("Getting download File :" + + ", FileType :Excel" + + "");

			File repfile = placementServices.getFileesi(filetype);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	@RequestMapping(value = "SPFDownload1", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource SPFDownload1(HttpServletResponse response, @RequestParam(required = false) String MONTH

	) throws IOException, SQLException {

		response.setContentType("application/octet-stream");
		System.out.println("===============" + MONTH);
		InputStreamResource resource = null;
		try {

			String filetype = "Excel";
			String Mon = MONTH;
			// logger.info("Getting download File :" + + ", FileType :Excel" + + "");

			File repfile = placementServices.getFile2(filetype, Mon);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	@RequestMapping(value = "SalaryDownload1", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource SalaryDownload1(HttpServletResponse response,
			@RequestParam(required = false) String MONTH

	) throws IOException, SQLException {

		response.setContentType("application/octet-stream");
		System.out.println("===============" + MONTH);
		InputStreamResource resource = null;
		try {

			String filetype = "Excel";
			String Mon = MONTH;
			// logger.info("Getting download File :" + + ", FileType :Excel" + + "");

			File repfile = placementServices.getSalaryFile(filetype, Mon);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	@RequestMapping(value = "ESIDownload1", method = RequestMethod.GET)
	@ResponseBody
	public InputStreamResource ESIDownload1(HttpServletResponse response, @RequestParam(required = false) String MONTH

	) throws IOException, SQLException {

		response.setContentType("application/octet-stream");
		System.out.println("===============" + MONTH);
		InputStreamResource resource = null;
		try {

			String filetype = "Excel";
			String Mon = MONTH;
			// logger.info("Getting download File :" + + ", FileType :Excel" + + "");

			File repfile = placementServices.getFileESI2(filetype, Mon);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	@RequestMapping(value = "viewtospf", method = RequestMethod.POST)
	@ResponseBody
	public String viewtospf(@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq,
			@RequestParam(required = false) String b) {
		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		System.out.println(b);
		int uniqueIdCounter = Integer.parseInt(b);
		try {
			// AccessRoles up = new AccessRoles();
			// List<BSPF_ENTITY> up1 = SpfRepo.getData(b);
			List<BSPF_ENTITY> up2 = SpfRepo.getData(b);
			List<spf_entity> up3 = new ArrayList<>();

			for (BSPF_ENTITY bspfEntity : up2) {

				if (bspfEntity.getSpf() == null || bspfEntity.getSpf().compareTo(BigDecimal.ZERO) == 0) {

					spf_entity spfEntity = new spf_entity();
					spfEntity.setEmp_no(bspfEntity.getEmp_no());
					spfEntity.setEmp_name(bspfEntity.getEmp_name());
					spfEntity.setEmp_desig(bspfEntity.getEmp_desig());
					spfEntity.setDate_of_birth(bspfEntity.getDate_of_birth());
					spfEntity.setDate_of_join(bspfEntity.getDate_of_join());
					spfEntity.setSpf_acct_no(bspfEntity.getSpf_acct_no());
					spfEntity.setUrn_no(bspfEntity.getUrn_no());
					spfEntity.setNo_of_days(bspfEntity.getNo_of_days());
					spfEntity.setDays_paid(bspfEntity.getDays_paid());
					spfEntity.setBank_name(bspfEntity.getBank_name());
					spfEntity.setBank_acct_no(bspfEntity.getBank_acct_no());
					spfEntity.setSalary_month(bspfEntity.getSalary_month());
					spfEntity.setEmp_cont_12("0.00");
					spfEntity.setEmp_cont_367("0.00");
					spfEntity.setEmp_cont_833("0.00");
					spfEntity.setTot_emp_cont("0.00");

					spfEntity.setHra(bspfEntity.getHra());
					spfEntity.setSpl_allow(bspfEntity.getSpl_allow());
					spfEntity.setMedi_reimb(bspfEntity.getMedi_reimb());
					spfEntity.setConv_allow(bspfEntity.getConv_allow());
					spfEntity.setLunch_allow(bspfEntity.getLunch_allow());
					spfEntity.setEdu_allow(bspfEntity.getEdu_allow());
					spfEntity.setBuss_attire(bspfEntity.getBuss_attire());
					spfEntity.setCar_maint(bspfEntity.getCar_maint());
					spfEntity.setLeave_travel_allow(bspfEntity.getLeave_travel_allow());
					spfEntity.setOutstn_allow(bspfEntity.getOutstn_allow());
					spfEntity.setAnnual_loyal_bonus(bspfEntity.getAnnual_loyal_bonus());
					spfEntity.setOtr_allow(bspfEntity.getOtr_allow());

					String grossSalary = bspfEntity.getGross_salary().toString();
					String formattedValue = formatLakh(Double.valueOf(grossSalary));
					spfEntity.setGross_salary(formattedValue);

					String BasicPay = bspfEntity.getBasic_pay().toString();
					String formattedValue1 = formatLakh(Double.valueOf(BasicPay));
					spfEntity.setBasic_pay(formattedValue1);
					// BigDecimal bigDecimalValue = new BigDecimal(a);
					// spfEntity.setGross_salary(bigDecimalValue);
					spfEntity.setSpf(bspfEntity.getSpf());
					spfEntity.setTds(bspfEntity.getTds());
					spfEntity.setProf_tax(bspfEntity.getProf_tax());
					spfEntity.setEsi(bspfEntity.getEsi());
					spfEntity.setRecovery(bspfEntity.getRecovery());
					spfEntity.setOtr_ded(bspfEntity.getOtr_ded());
					spfEntity.setTotal_deductions(bspfEntity.getTotal_deductions());
					spfEntity.setNet_salary(bspfEntity.getNet_salary());
					spfEntity.setDate_of_pay(bspfEntity.getDate_of_pay());
					spfEntity.setCum_tds_fy(bspfEntity.getCum_tds_fy());
					spfEntity.setCtc_amt(bspfEntity.getCtc_amt());
					spfEntity.setMobile_no(bspfEntity.getMobile_no());
					spfEntity.setEmail_id(bspfEntity.getEmail_id());
					spfEntity.setIfsc_code(bspfEntity.getIfsc_code());
					spfEntity.setRemarks(bspfEntity.getRemarks());
					spfEntity.setAdhar_no(bspfEntity.getAdhar_no());

					spfEntity.setUnique_id(bspfEntity.getSalary_month() + bspfEntity.getEmp_no());
					up3.add(spfEntity);
				}

				else {

					spf_entity spfEntity = new spf_entity();
					spfEntity.setEmp_no(bspfEntity.getEmp_no());
					spfEntity.setEmp_name(bspfEntity.getEmp_name());
					spfEntity.setEmp_desig(bspfEntity.getEmp_desig());
					spfEntity.setDate_of_birth(bspfEntity.getDate_of_birth());
					spfEntity.setDate_of_join(bspfEntity.getDate_of_join());
					spfEntity.setSpf_acct_no(bspfEntity.getSpf_acct_no());
					spfEntity.setUrn_no(bspfEntity.getUrn_no());
					spfEntity.setNo_of_days(bspfEntity.getNo_of_days());
					spfEntity.setDays_paid(bspfEntity.getDays_paid());
					spfEntity.setBank_name(bspfEntity.getBank_name());
					spfEntity.setBank_acct_no(bspfEntity.getBank_acct_no());
					spfEntity.setSalary_month(bspfEntity.getSalary_month());

					BigDecimal expectedValue = new BigDecimal("300000");
					if (bspfEntity.getCtc_amt().compareTo(expectedValue) >= 0) {
						BigDecimal basicPay = new BigDecimal("15000");
						spfEntity.setBasic_pay("15,000.00");

						// BigDecimal basicPay = bspfEntity.getBasic_pay();

						int intValue = basicPay.intValue();
						int emailId = Math.round(15000 * 8.33f / 100);
						String stringValue = Integer.toString(emailId);
						spfEntity.setEmp_cont_833(formatLakh(Double.valueOf(stringValue)));

						int ifsc = Math.round(15000 * 3.67f / 100);
						String stringValue1 = Integer.toString(ifsc);
						spfEntity.setEmp_cont_367(formatLakh(Double.valueOf(stringValue1)));

						int remarks = Math.round(15000 * 12.00f / 100);
						String stringValue2 = Integer.toString(remarks);
						spfEntity.setTot_emp_cont(formatLakh(Double.valueOf(stringValue2)));

					} else if (bspfEntity.getCtc_amt().compareTo(expectedValue) < 0) {
						BigDecimal basicPay = new BigDecimal("13985");
						spfEntity.setBasic_pay("13,985.00");

						int intValue = basicPay.intValue();

						int remarks = 1500;
						String stringValue2 = Integer.toString(remarks);
						spfEntity.setTot_emp_cont(formatLakh(Double.valueOf(stringValue2)));

						int emailId = Math.round(remarks * 8.33f / 12);
						String stringValue = Integer.toString(emailId);
						spfEntity.setEmp_cont_833(formatLakh(Double.valueOf(stringValue)));

						int ifsc = Math.round(remarks * 3.67f / 12);
						String stringValue1 = Integer.toString(ifsc);
						spfEntity.setEmp_cont_367(formatLakh(Double.valueOf(stringValue1)));

					} else {
						// Handle the case where bspfEntity.getCtc_amt() is equal to expectedValue
						// You can add code here if needed.
					}

					String grossSalary = bspfEntity.getGross_salary().toString();
					String formattedValue = formatLakh(Double.valueOf(grossSalary));
					spfEntity.setGross_salary(formattedValue);

					spfEntity.setHra(bspfEntity.getHra());
					spfEntity.setSpl_allow(bspfEntity.getSpl_allow());
					spfEntity.setMedi_reimb(bspfEntity.getMedi_reimb());
					spfEntity.setConv_allow(bspfEntity.getConv_allow());
					spfEntity.setLunch_allow(bspfEntity.getLunch_allow());
					spfEntity.setEdu_allow(bspfEntity.getEdu_allow());
					spfEntity.setBuss_attire(bspfEntity.getBuss_attire());
					spfEntity.setCar_maint(bspfEntity.getCar_maint());
					spfEntity.setLeave_travel_allow(bspfEntity.getLeave_travel_allow());
					spfEntity.setOutstn_allow(bspfEntity.getOutstn_allow());
					spfEntity.setAnnual_loyal_bonus(bspfEntity.getAnnual_loyal_bonus());
					spfEntity.setOtr_allow(bspfEntity.getOtr_allow());
					// spfEntity.setGross_salary(bspfEntity.getGross_salary());
					spfEntity.setSpf(bspfEntity.getSpf());
					spfEntity.setTds(bspfEntity.getTds());
					spfEntity.setProf_tax(bspfEntity.getProf_tax());
					spfEntity.setEsi(bspfEntity.getEsi());
					spfEntity.setRecovery(bspfEntity.getRecovery());
					spfEntity.setOtr_ded(bspfEntity.getOtr_ded());
					spfEntity.setTotal_deductions(bspfEntity.getTotal_deductions());
					spfEntity.setNet_salary(bspfEntity.getNet_salary());
					spfEntity.setDate_of_pay(bspfEntity.getDate_of_pay());
					spfEntity.setCum_tds_fy(bspfEntity.getCum_tds_fy());
					spfEntity.setCtc_amt(bspfEntity.getCtc_amt());
					spfEntity.setMobile_no(bspfEntity.getMobile_no());
					spfEntity.setEmail_id(bspfEntity.getEmail_id());
					spfEntity.setIfsc_code(bspfEntity.getIfsc_code());
					spfEntity.setRemarks(bspfEntity.getRemarks());
					spfEntity.setAdhar_no(bspfEntity.getAdhar_no());

					spfEntity.setUnique_id(bspfEntity.getSalary_month() + bspfEntity.getEmp_no());
					up3.add(spfEntity);
				}

			}
			System.out.println(up3);
			Spf_repo.saveAll(up3);
			String msg = "Data Saved Successfully"; // Changed the message
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
			return "Error: " + e.getMessage();
		}
	}

	@RequestMapping(value = "viewtospf1", method = RequestMethod.POST)
	@ResponseBody
	public String viewtospf1(@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq,
			@RequestParam(required = false) String b) {
		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		System.out.println(b);
		int uniqueIdCounter = Integer.parseInt(b);
		try {
			// AccessRoles up = new AccessRoles();
			// List<BSPF_ENTITY> up1 = SpfRepo.getData(b);
			List<BSPF_ENTITY> up2 = SpfRepo.getData(b);
			List<spf_entity> up3 = new ArrayList<>();

			for (BSPF_ENTITY bspfEntity : up2) {

				if (bspfEntity.getSpf() == null || bspfEntity.getSpf().compareTo(BigDecimal.ZERO) == 0) {

					spf_entity spfEntity = new spf_entity();
					spfEntity.setEmp_no(bspfEntity.getEmp_no());
					spfEntity.setEmp_name(bspfEntity.getEmp_name());
					spfEntity.setEmp_desig(bspfEntity.getEmp_desig());
					spfEntity.setDate_of_birth(bspfEntity.getDate_of_birth());
					spfEntity.setDate_of_join(bspfEntity.getDate_of_join());
					spfEntity.setSpf_acct_no(bspfEntity.getSpf_acct_no());
					spfEntity.setUrn_no(bspfEntity.getUrn_no());
					spfEntity.setNo_of_days(bspfEntity.getNo_of_days());
					spfEntity.setDays_paid(bspfEntity.getDays_paid());
					spfEntity.setBank_name(bspfEntity.getBank_name());
					spfEntity.setBank_acct_no(bspfEntity.getBank_acct_no());
					spfEntity.setSalary_month(bspfEntity.getSalary_month());
					spfEntity.setEmp_cont_12("0");
					spfEntity.setEmp_cont_367("0");
					spfEntity.setEmp_cont_833("0");
					spfEntity.setTot_emp_cont("0");

					spfEntity.setHra(bspfEntity.getHra());
					spfEntity.setSpl_allow(bspfEntity.getSpl_allow());
					spfEntity.setMedi_reimb(bspfEntity.getMedi_reimb());
					spfEntity.setConv_allow(bspfEntity.getConv_allow());
					spfEntity.setLunch_allow(bspfEntity.getLunch_allow());
					spfEntity.setEdu_allow(bspfEntity.getEdu_allow());
					spfEntity.setBuss_attire(bspfEntity.getBuss_attire());
					spfEntity.setCar_maint(bspfEntity.getCar_maint());
					spfEntity.setLeave_travel_allow(bspfEntity.getLeave_travel_allow());
					spfEntity.setOutstn_allow(bspfEntity.getOutstn_allow());
					spfEntity.setAnnual_loyal_bonus(bspfEntity.getAnnual_loyal_bonus());
					spfEntity.setOtr_allow(bspfEntity.getOtr_allow());

					String grossSalary = bspfEntity.getGross_salary().toString();
					// String formattedValue = formatLakh(Double.valueOf(grossSalary));
					spfEntity.setGross_salary(grossSalary);

					String BasicPay = bspfEntity.getBasic_pay().toString();
					// String formattedValue1 = formatLakh(Double.valueOf(BasicPay));
					spfEntity.setBasic_pay(BasicPay);
					// BigDecimal bigDecimalValue = new BigDecimal(a);
					// spfEntity.setGross_salary(bigDecimalValue);
					spfEntity.setSpf(bspfEntity.getSpf());
					spfEntity.setTds(bspfEntity.getTds());
					spfEntity.setProf_tax(bspfEntity.getProf_tax());
					spfEntity.setEsi(bspfEntity.getEsi());
					spfEntity.setRecovery(bspfEntity.getRecovery());
					spfEntity.setOtr_ded(bspfEntity.getOtr_ded());
					spfEntity.setTotal_deductions(bspfEntity.getTotal_deductions());
					spfEntity.setNet_salary(bspfEntity.getNet_salary());
					spfEntity.setDate_of_pay(bspfEntity.getDate_of_pay());
					spfEntity.setCum_tds_fy(bspfEntity.getCum_tds_fy());
					spfEntity.setCtc_amt(bspfEntity.getCtc_amt());
					spfEntity.setMobile_no(bspfEntity.getMobile_no());
					spfEntity.setEmail_id(bspfEntity.getEmail_id());
					spfEntity.setIfsc_code(bspfEntity.getIfsc_code());
					spfEntity.setRemarks(bspfEntity.getRemarks());
					spfEntity.setAdhar_no(bspfEntity.getAdhar_no());
					if (bspfEntity.getBank_name().equals("ICICI")) {
						spfEntity.setTran_type("I");
					} else {
						spfEntity.setTran_type("N");
					}

					spfEntity.setUnique_id(bspfEntity.getSalary_month() + bspfEntity.getEmp_no());
					up3.add(spfEntity);
				} else if (bspfEntity.getSpf().equals(new BigDecimal("2400"))) {

					spf_entity spfEntity = new spf_entity();
					spfEntity.setEmp_no(bspfEntity.getEmp_no());
					spfEntity.setEmp_name(bspfEntity.getEmp_name());
					spfEntity.setEmp_desig(bspfEntity.getEmp_desig());
					spfEntity.setDate_of_birth(bspfEntity.getDate_of_birth());
					spfEntity.setDate_of_join(bspfEntity.getDate_of_join());
					spfEntity.setSpf_acct_no(bspfEntity.getSpf_acct_no());
					spfEntity.setUrn_no(bspfEntity.getUrn_no());
					spfEntity.setNo_of_days(bspfEntity.getNo_of_days());
					spfEntity.setDays_paid(bspfEntity.getDays_paid());
					spfEntity.setBank_name(bspfEntity.getBank_name());
					spfEntity.setBank_acct_no(bspfEntity.getBank_acct_no());
					spfEntity.setSalary_month(bspfEntity.getSalary_month());
					spfEntity.setEmp_cont_12("2400");
					spfEntity.setEmp_cont_367("1150");
					spfEntity.setEmp_cont_833("1250");
					spfEntity.setTot_emp_cont("2400");

					spfEntity.setHra(bspfEntity.getHra());
					spfEntity.setSpl_allow(bspfEntity.getSpl_allow());
					spfEntity.setMedi_reimb(bspfEntity.getMedi_reimb());
					spfEntity.setConv_allow(bspfEntity.getConv_allow());
					spfEntity.setLunch_allow(bspfEntity.getLunch_allow());
					spfEntity.setEdu_allow(bspfEntity.getEdu_allow());
					spfEntity.setBuss_attire(bspfEntity.getBuss_attire());
					spfEntity.setCar_maint(bspfEntity.getCar_maint());
					spfEntity.setLeave_travel_allow(bspfEntity.getLeave_travel_allow());
					spfEntity.setOutstn_allow(bspfEntity.getOutstn_allow());
					spfEntity.setAnnual_loyal_bonus(bspfEntity.getAnnual_loyal_bonus());
					spfEntity.setOtr_allow(bspfEntity.getOtr_allow());

					String grossSalary = bspfEntity.getGross_salary().toString();
					// String formattedValue = formatLakh(Double.valueOf(grossSalary));
					spfEntity.setGross_salary(grossSalary);
					BigDecimal expectedValue = new BigDecimal("300000");
					if (bspfEntity.getCtc_amt().compareTo(expectedValue) >= 0) {
						BigDecimal basicPay = new BigDecimal("15000");
						spfEntity.setBasic_pay("15000");
					}
					// String BasicPay=bspfEntity.getBasic_pay().toString();
					// String formattedValue1 = formatLakh(Double.valueOf(BasicPay));
					// spfEntity.setBasic_pay(BasicPay);
					// BigDecimal bigDecimalValue = new BigDecimal(a);
					// spfEntity.setGross_salary(bigDecimalValue);
					spfEntity.setSpf(bspfEntity.getSpf());
					spfEntity.setTds(bspfEntity.getTds());
					spfEntity.setProf_tax(bspfEntity.getProf_tax());
					spfEntity.setEsi(bspfEntity.getEsi());
					spfEntity.setRecovery(bspfEntity.getRecovery());
					spfEntity.setOtr_ded(bspfEntity.getOtr_ded());
					spfEntity.setTotal_deductions(bspfEntity.getTotal_deductions());
					spfEntity.setNet_salary(bspfEntity.getNet_salary());
					spfEntity.setDate_of_pay(bspfEntity.getDate_of_pay());
					spfEntity.setCum_tds_fy(bspfEntity.getCum_tds_fy());
					spfEntity.setCtc_amt(bspfEntity.getCtc_amt());
					spfEntity.setMobile_no(bspfEntity.getMobile_no());
					spfEntity.setEmail_id(bspfEntity.getEmail_id());
					spfEntity.setIfsc_code(bspfEntity.getIfsc_code());
					spfEntity.setRemarks(bspfEntity.getRemarks());
					spfEntity.setAdhar_no(bspfEntity.getAdhar_no());
					if (bspfEntity.getBank_name().equals("ICICI")) {
						spfEntity.setTran_type("I");
					} else {
						spfEntity.setTran_type("N");
					}

					spfEntity.setUnique_id(bspfEntity.getSalary_month() + bspfEntity.getEmp_no());
					up3.add(spfEntity);
				} else {

					spf_entity spfEntity = new spf_entity();
					spfEntity.setEmp_no(bspfEntity.getEmp_no());
					spfEntity.setEmp_name(bspfEntity.getEmp_name());
					spfEntity.setEmp_desig(bspfEntity.getEmp_desig());
					spfEntity.setDate_of_birth(bspfEntity.getDate_of_birth());
					spfEntity.setDate_of_join(bspfEntity.getDate_of_join());
					spfEntity.setSpf_acct_no(bspfEntity.getSpf_acct_no());
					spfEntity.setUrn_no(bspfEntity.getUrn_no());
					spfEntity.setNo_of_days(bspfEntity.getNo_of_days());
					spfEntity.setDays_paid(bspfEntity.getDays_paid());
					spfEntity.setBank_name(bspfEntity.getBank_name());
					spfEntity.setBank_acct_no(bspfEntity.getBank_acct_no());
					spfEntity.setSalary_month(bspfEntity.getSalary_month());
					/*
					 * if(bspfEntity.getBank_name().equals("ICICI")) { spfEntity.setTran_type("I");
					 * }else { spfEntity.setTran_type("N"); }
					 */

					if (bspfEntity != null && bspfEntity.getBank_name() != null
							&& bspfEntity.getBank_name().equals("ICICI")) {
						spfEntity.setTran_type("I");
					} else {
						spfEntity.setTran_type("N");
					}

					BigDecimal expectedValue = new BigDecimal("250000");
					if (bspfEntity.getCtc_amt().compareTo(expectedValue) >= 0) {
						BigDecimal basicPay = new BigDecimal("15000");
						spfEntity.setBasic_pay("15000");

						// BigDecimal basicPay = bspfEntity.getBasic_pay();

						int intValue = basicPay.intValue();
						int emailId = Math.round(15000 * 8.33f / 100);
						String stringValue = Integer.toString(emailId);
						spfEntity.setEmp_cont_833(stringValue);
						// spfEntity.setEmp_cont_833(formatLakh(Double.valueOf(stringValue)));

						int ifsc = Math.round(15000 * 3.67f / 100);
						String stringValue1 = Integer.toString(ifsc);
						spfEntity.setEmp_cont_367(stringValue1);
						// spfEntity.setEmp_cont_367(formatLakh(Double.valueOf(stringValue1)));

						int remarks = Math.round(15000 * 12.00f / 100);
						String stringValue2 = Integer.toString(remarks);
						spfEntity.setTot_emp_cont(stringValue2);
						// spfEntity.setTot_emp_cont(formatLakh(Double.valueOf(stringValue2)));

					} else if (bspfEntity.getCtc_amt().compareTo(expectedValue) < 0) {
						BigDecimal basicPay = new BigDecimal("13985");
						spfEntity.setBasic_pay("13985");

						int intValue = basicPay.intValue();

						int remarks = 1500;
						String stringValue2 = Integer.toString(remarks);
						spfEntity.setTot_emp_cont(stringValue2);
						// spfEntity.setTot_emp_cont(formatLakh(Double.valueOf(stringValue2)));

						int emailId = Math.round(remarks * 8.33f / 12);
						String stringValue = Integer.toString(emailId);
						spfEntity.setEmp_cont_833(stringValue);
						// spfEntity.setEmp_cont_833(formatLakh(Double.valueOf(stringValue)));

						int ifsc = Math.round(remarks * 3.67f / 12);
						String stringValue1 = Integer.toString(ifsc);
						spfEntity.setEmp_cont_367(stringValue1);
						// spfEntity.setEmp_cont_367(formatLakh(Double.valueOf(stringValue1)));

					} else {
						// Handle the case where bspfEntity.getCtc_amt() is equal to expectedValue
						// You can add code here if needed.
					}

					String grossSalary = bspfEntity.getGross_salary().toString();
					// String formattedValue = formatLakh(Double.valueOf(grossSalary));
					spfEntity.setGross_salary(grossSalary);

					spfEntity.setHra(bspfEntity.getHra());
					spfEntity.setSpl_allow(bspfEntity.getSpl_allow());
					spfEntity.setMedi_reimb(bspfEntity.getMedi_reimb());
					spfEntity.setConv_allow(bspfEntity.getConv_allow());
					spfEntity.setLunch_allow(bspfEntity.getLunch_allow());
					spfEntity.setEdu_allow(bspfEntity.getEdu_allow());
					spfEntity.setBuss_attire(bspfEntity.getBuss_attire());
					spfEntity.setCar_maint(bspfEntity.getCar_maint());
					spfEntity.setLeave_travel_allow(bspfEntity.getLeave_travel_allow());
					spfEntity.setOutstn_allow(bspfEntity.getOutstn_allow());
					spfEntity.setAnnual_loyal_bonus(bspfEntity.getAnnual_loyal_bonus());
					spfEntity.setOtr_allow(bspfEntity.getOtr_allow());
					// spfEntity.setGross_salary(bspfEntity.getGross_salary());
					spfEntity.setSpf(bspfEntity.getSpf());
					spfEntity.setTds(bspfEntity.getTds());
					spfEntity.setProf_tax(bspfEntity.getProf_tax());
					spfEntity.setEsi(bspfEntity.getEsi());
					spfEntity.setRecovery(bspfEntity.getRecovery());
					spfEntity.setOtr_ded(bspfEntity.getOtr_ded());
					spfEntity.setTotal_deductions(bspfEntity.getTotal_deductions());
					spfEntity.setNet_salary(bspfEntity.getNet_salary());
					spfEntity.setDate_of_pay(bspfEntity.getDate_of_pay());
					spfEntity.setCum_tds_fy(bspfEntity.getCum_tds_fy());
					spfEntity.setCtc_amt(bspfEntity.getCtc_amt());
					spfEntity.setMobile_no(bspfEntity.getMobile_no());
					spfEntity.setEmail_id(bspfEntity.getEmail_id());
					spfEntity.setIfsc_code(bspfEntity.getIfsc_code());
					spfEntity.setRemarks(bspfEntity.getRemarks());
					spfEntity.setAdhar_no(bspfEntity.getAdhar_no());

					spfEntity.setUnique_id(bspfEntity.getSalary_month() + bspfEntity.getEmp_no());
					up3.add(spfEntity);
				}

			}
			System.out.println(up3);
			Spf_repo.saveAll(up3);
			String msg = "Data Saved Successfully"; // Changed the message
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
			return "Error: " + e.getMessage();
		}
	}

	private static String formatLakh(double d) {
		String s = String.format(Locale.UK, "%1.2f", Math.abs(d));
		s = s.replaceAll("(.+)(...\\...)", "$1,$2");
		while (s.matches("\\d{3,},.+")) {
			s = s.replaceAll("(\\d+)(\\d{2},.+)", "$1,$2");
		}
		return d < 0 ? ("-" + s) : s;
	}

	/*
	 * private static String formatLakh(double d) { String s =
	 * String.format(Locale.UK, "%1.2f", Math.abs(d)); s =
	 * s.replaceAll("(.+)(...\\...)", "$1,$2"); while (s.matches("\\d{3,},.+")) { s
	 * = s.replaceAll("(\\d+)(\\d{2},.+)", "$1,$2"); } return d < 0 ? ("-" + s) : s;
	 * }
	 */

	/*
	 * @RequestMapping(value = "viewtogst12", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public String viewtogst12(
	 * 
	 * @RequestParam(required = false) String formmode, Model md, HttpServletRequest
	 * rq,
	 * 
	 * @RequestParam(required = false) String b, @RequestParam(required = false)
	 * String a) {
	 * 
	 * String userId = (String) rq.getSession().getAttribute("USERID");
	 * md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
	 * md.addAttribute("menu", "BTMHeaderMenu");
	 * 
	 * System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}"+a +b); //int uniqueIdCounter =
	 * Integer.parseInt(b); //int uniqueIdCounter1 = Integer.parseInt(a);
	 * 
	 * try { List<BEXPI_entity> up2 = bexpiRepoa.getDataOverseas(b,a);
	 * List<Gstoverseas> up3 = new ArrayList<>();
	 * 
	 * for (BEXPI_entity bEXPI_entity : up2) { Gstoverseas gstoverseas = new
	 * Gstoverseas(); gstoverseas.setBank_account(bEXPI_entity.getBank_account());
	 * gstoverseas.setBank_charges(bEXPI_entity.getBank_charges());
	 * gstoverseas.setClient(bEXPI_entity.getClient());
	 * gstoverseas.setDel_flg(bEXPI_entity.getDel_flg());
	 * gstoverseas.setDescription(bEXPI_entity.getDescription());
	 * gstoverseas.setEmployee(bEXPI_entity.getEmployee());
	 * gstoverseas.setEnd_date(bEXPI_entity.getEnd_date());
	 * gstoverseas.setEntity_flg(bEXPI_entity.getEntity_flg());
	 * gstoverseas.setEntry_time(bEXPI_entity.getEntry_time());
	 * gstoverseas.setEntry_user(bEXPI_entity.getEntry_user());
	 * gstoverseas.setEx_fluc(bEXPI_entity.getEx_fluc());
	 * gstoverseas.setFbank_chg_fcy(bEXPI_entity.getFbank_chg_fcy());
	 * gstoverseas.setInv_amt_fcy(bEXPI_entity.getInv_amt_fcy());
	 * gstoverseas.setInv_amt_inr(bEXPI_entity.getInv_amt_inr());
	 * gstoverseas.setInv_date(bEXPI_entity.getInv_date());
	 * gstoverseas.setInv_no(bEXPI_entity.getInv_no());
	 * gstoverseas.setModify_flg(bEXPI_entity.getModify_flg());
	 * gstoverseas.setModify_time(bEXPI_entity.getModify_time());
	 * gstoverseas.setModify_user(bEXPI_entity.getModify_user());
	 * gstoverseas.setPart_tran_id(bEXPI_entity.getPart_tran_id());
	 * gstoverseas.setPart_tran_type(bEXPI_entity.getPart_tran_type());
	 * gstoverseas.setPayment_date(bEXPI_entity.getPayment_date());
	 * gstoverseas.setRate(bEXPI_entity.getRate());
	 * gstoverseas.setRemit_amt_fcy(bEXPI_entity.getRemit_amt_fcy());
	 * gstoverseas.setRemit_amt_inr(bEXPI_entity.getRemit_amt_inr());
	 * gstoverseas.setRemit_rate(bEXPI_entity.getRemit_rate());
	 * gstoverseas.setStart_date(bEXPI_entity.getStart_date());
	 * gstoverseas.setTran_crncy(bEXPI_entity.getTran_crncy());
	 * gstoverseas.setTran_date(bEXPI_entity.getTran_date());
	 * gstoverseas.setTran_id(bEXPI_entity.getTran_id());
	 * gstoverseas.setVerify_time(bEXPI_entity.getVerify_time());
	 * gstoverseas.setVerify_user(bEXPI_entity.getVerify_user());
	 * 
	 * up3.add(gstoverseas); }
	 * 
	 * System.out.println(up3); gstoverseasRepo.saveAll(up3);
	 * 
	 * // gstBtmRep.getInsert(b,a);
	 * 
	 * 
	 * System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}"+a +b); //int uniqueIdCounter =
	 * Integer.parseInt(b); //int uniqueIdCounter1 = Integer.parseInt(a);
	 * 
	 * try { List<gst> up4= gstRep.getDatagst(b,a); List<GstBtmEntity> up5 = new
	 * ArrayList<>();
	 * 
	 * for (gst gsts : up4) { GstBtmEntity gstBtmEntity = new GstBtmEntity();
	 * gstBtmEntity.setClient(gsts.getClient());
	 * gstBtmEntity.setClient_remark(gsts.getClient_remark());
	 * gstBtmEntity.setClient_type(gsts.getClient_type());
	 * gstBtmEntity.setEligible_amt(gsts.getEligible_amt());
	 * gstBtmEntity.setFin_year(gsts.getFin_year());
	 * gstBtmEntity.setGst_type(gsts.getGst_type());
	 * gstBtmEntity.setGstin(gsts.getGstin());
	 * gstBtmEntity.setInv_amt(gsts.getInv_amt());
	 * gstBtmEntity.setInv_cgst(gsts.getInv_cgst());
	 * gstBtmEntity.setInv_desc(gsts.getInv_desc());
	 * gstBtmEntity.setInv_igst(gsts.getInv_igst());
	 * gstBtmEntity.setInv_sgst(gsts.getInv_sgst());
	 * gstBtmEntity.setInv_tot_amt(gsts.getInv_tot_amt());
	 * gstBtmEntity.setInvoice_date(gsts.getInvoice_date());
	 * gstBtmEntity.setInvoice_no(gsts.getInvoice_no());
	 * gstBtmEntity.setPart_tran_id(gsts.getPart_tran_id());
	 * gstBtmEntity.setPart_tran_type(gsts.getPart_tran_type());
	 * gstBtmEntity.setPay_part_tran_id(gsts.getPay_part_tran_id());
	 * gstBtmEntity.setPay_part_tran_type(gsts.getPay_part_tran_type());
	 * gstBtmEntity.setPay_tran_date(gsts.getPay_tran_date());
	 * gstBtmEntity.setPayment_date(gsts.getPayment_date());
	 * gstBtmEntity.setRpay_tran_id(gsts.getRpay_tran_id());
	 * gstBtmEntity.setTotal_gst_amt(gsts.getTotal_gst_amt());
	 * gstBtmEntity.setTran_date(gsts.getTran_date());
	 * gstBtmEntity.setTran_id(gsts.getTran_id());
	 * 
	 * 
	 * up5.add(gstBtmEntity); }
	 * 
	 * System.out.println(up5); gstBtmRep.saveAll(up5);
	 * 
	 * //System.out.println(gstRep.getInsert(a,b)); String msg =
	 * "Data Saved Successfully"; return msg;
	 * 
	 * } catch (Exception e) { e.printStackTrace(); return "Error: " +
	 * e.getMessage(); }
	 * 
	 */
	@RequestMapping(value = "viewtogst1", method = RequestMethod.POST)
	@ResponseBody
	public String viewtogst1(@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq,
			@RequestParam(required = false) String b, @RequestParam(required = false) String a) {

		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}" + a + b);

		try {
			List<BEXPI_entity> up2 = bexpiRepoa.getDataOverseas(b, a);
			List<Gstoverseas> up3 = new ArrayList<>();

			for (BEXPI_entity bEXPI_entity : up2) {
				Gstoverseas gstoverseas = new Gstoverseas();
				gstoverseas.setBank_account(bEXPI_entity.getBank_account());
				gstoverseas.setBank_charges(bEXPI_entity.getBank_charges());
				gstoverseas.setClient(bEXPI_entity.getClient());
				gstoverseas.setDel_flg(bEXPI_entity.getDel_flg());
				gstoverseas.setDescription(bEXPI_entity.getDescription());
				gstoverseas.setEmployee(bEXPI_entity.getEmployee());
				gstoverseas.setEnd_date(bEXPI_entity.getEnd_date());
				gstoverseas.setEntity_flg(bEXPI_entity.getEntity_flg());
				gstoverseas.setEntry_time(bEXPI_entity.getEntry_time());
				gstoverseas.setEntry_user(bEXPI_entity.getEntry_user());
				gstoverseas.setEx_fluc(bEXPI_entity.getEx_fluc());
				gstoverseas.setFbank_chg_fcy(bEXPI_entity.getFbank_chg_fcy());
				gstoverseas.setInv_amt_fcy(bEXPI_entity.getInv_amt_fcy());
				gstoverseas.setInv_amt_inr(bEXPI_entity.getInv_amt_inr());
				gstoverseas.setInv_date(bEXPI_entity.getInv_date());
				gstoverseas.setInv_no(bEXPI_entity.getInv_no());
				gstoverseas.setModify_flg(bEXPI_entity.getModify_flg());
				gstoverseas.setModify_time(bEXPI_entity.getModify_time());
				gstoverseas.setModify_user(bEXPI_entity.getModify_user());
				gstoverseas.setPart_tran_id(bEXPI_entity.getPart_tran_id());
				gstoverseas.setPart_tran_type(bEXPI_entity.getPart_tran_type());
				gstoverseas.setPayment_date(bEXPI_entity.getPayment_date());
				gstoverseas.setRate(bEXPI_entity.getRate());
				gstoverseas.setRemit_amt_fcy(bEXPI_entity.getRemit_amt_fcy());
				gstoverseas.setRemit_amt_inr(bEXPI_entity.getRemit_amt_inr());
				gstoverseas.setRemit_rate(bEXPI_entity.getRemit_rate());
				gstoverseas.setStart_date(bEXPI_entity.getStart_date());
				gstoverseas.setTran_crncy(bEXPI_entity.getTran_crncy());
				gstoverseas.setTran_date(bEXPI_entity.getTran_date());
				gstoverseas.setTran_id(bEXPI_entity.getTran_id());
				gstoverseas.setVerify_time(bEXPI_entity.getVerify_time());
				gstoverseas.setVerify_user(bEXPI_entity.getVerify_user());
				gstoverseas.setUniqueid(bEXPI_entity.getTran_id() + bEXPI_entity.getPart_tran_id());

				up3.add(gstoverseas);
			}

			System.out.println(up3);
			//
			gstoverseasRepo.saveAll(up3);

			// gstBtmRep.getInsert(b,a);

			System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}" + a + b);
			// int uniqueIdCounter = Integer.parseInt(b);
			// int uniqueIdCounter1 = Integer.parseInt(a);

			try {
				List<gst> up4 = gstRep.getDatagst(b, a);
				List<GstBtmEntity> up5 = new ArrayList<>();

				for (gst gsts : up4) {
					GstBtmEntity gstBtmEntity = new GstBtmEntity();
					gstBtmEntity.setClient(gsts.getClient());
					gstBtmEntity.setClient_remark(gsts.getClient_remark());
					gstBtmEntity.setClient_type(gsts.getClient_type());
					gstBtmEntity.setEligible_amt(gsts.getEligible_amt());
					gstBtmEntity.setFin_year(gsts.getFin_year());
					gstBtmEntity.setGst_type(gsts.getGst_type());
					gstBtmEntity.setGstin(gsts.getGstin());
					gstBtmEntity.setInv_amt(gsts.getInv_amt());
					gstBtmEntity.setInv_cgst(gsts.getInv_cgst());
					gstBtmEntity.setInv_desc(gsts.getInv_desc());
					gstBtmEntity.setInv_igst(gsts.getInv_igst());
					gstBtmEntity.setInv_sgst(gsts.getInv_sgst());
					gstBtmEntity.setInv_tot_amt(gsts.getInv_tot_amt());
					gstBtmEntity.setInvoice_date(gsts.getInvoice_date());
					gstBtmEntity.setInvoice_no(gsts.getInvoice_no());
					gstBtmEntity.setPart_tran_id(gsts.getPart_tran_id());
					gstBtmEntity.setPart_tran_type(gsts.getPart_tran_type());
					gstBtmEntity.setPay_part_tran_id(gsts.getPay_part_tran_id());
					gstBtmEntity.setPay_part_tran_type(gsts.getPay_part_tran_type());
					gstBtmEntity.setPay_tran_date(gsts.getPay_tran_date());
					gstBtmEntity.setPayment_date(gsts.getPayment_date());
					gstBtmEntity.setRpay_tran_id(gsts.getRpay_tran_id());
					gstBtmEntity.setTotal_gst_amt(gsts.getTotal_gst_amt());
					gstBtmEntity.setTran_date(gsts.getTran_date());
					gstBtmEntity.setTran_id(gsts.getTran_id());
					gstBtmEntity.setUniqueid(gsts.getTran_id() + gsts.getPart_tran_id());

					up5.add(gstBtmEntity);
				}

				System.out.println(up5);
				gstBtmRep.saveAll(up5);

				// System.out.println(gstRep.getInsert(a,b));
				String msg = "Data Saved Successfully";
				return msg;

			} catch (Exception e) {
				e.printStackTrace();
				return "Error: " + e.getMessage();
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "Error: " + e.getMessage();
		}
	}

	@RequestMapping(value = "esi", method = { RequestMethod.GET, RequestMethod.POST })
	public String esi(@RequestParam(required = false) String formmode, @RequestParam(required = false) String resName,
			@RequestParam(required = false) String a, @RequestParam(required = false) String Month, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list1")) {
			if (Month != null || Month == "") {
				List<spf_entity> spfValues = Spf_repo.getESI(Month);

				for (int i = 0; i < spfValues.size(); i++) {
					spf_entity entity = spfValues.get(i);
					// Do something with the entity, e.g., print its properties
					System.out.println("Bank Acct No: " + entity.getBank_acct_no());
					System.out.println("Bank Name: " + entity.getBank_name());
					System.out.println("Salary Month: " + entity.getSalary_month());

					String stringValue = entity.getGross_salary(); // Replace this with your desired string
					BigDecimal grosspay = new BigDecimal(stringValue);

					int intValue = grosspay.intValue();
					int emailId = Math.round(intValue * 3.25f / 100);
					String stringValue1 = Integer.toString(emailId);
					entity.setEmail_id(stringValue1);

					int ifsc = Math.round(intValue * 0.75f / 100);
					String stringValue2 = Integer.toString(ifsc);
					entity.setIfsc_code(stringValue2);

					int remarks = Math.round(intValue * 4.00f / 100);
					String stringValue3 = Integer.toString(remarks);
					entity.setRemarks(stringValue3);
					// spfValues.set(0, entity);
				}

				md.addAttribute("ghj", spfValues);
				md.addAttribute("formmode", "list1");
				md.addAttribute("month", Month);
			} else {
				YearMonth currentYearMonth = YearMonth.now();

				// Format the current month and year as a string
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
				String formattedMonthYear = currentYearMonth.format(formatter);
				// String[] parts = formattedMonthYear.split(" ");
				// Print the current month and year
				System.out.println("Current Month and Year: " + formattedMonthYear);

				List<spf_entity> spfValues = Spf_repo.getall(formattedMonthYear);

				for (int i = 0; i < spfValues.size(); i++) {
					spf_entity entity = spfValues.get(i);
					// Do something with the entity, e.g., print its properties
					System.out.println("Bank Acct No: " + entity.getBank_acct_no());
					System.out.println("Bank Name: " + entity.getBank_name());
					System.out.println("Salary Month: " + entity.getSalary_month());

					String stringValue = entity.getBasic_pay(); // Replace this with your desired string
					BigDecimal basicPay = new BigDecimal(stringValue);

					int intValue = basicPay.intValue();
					int emailId = Math.round(intValue * 8.33f / 100);
					String stringValue1 = Integer.toString(emailId);
					entity.setEmail_id(stringValue1);

					int ifsc = Math.round(intValue * 3.67f / 100);
					String stringValue11 = Integer.toString(ifsc);
					entity.setIfsc_code(stringValue11);

					int remarks = Math.round(intValue * 12.00f / 100);
					String stringValue2 = Integer.toString(remarks);
					entity.setRemarks(stringValue2);
					spfValues.set(0, entity);

				}

				md.addAttribute("ghj", spfValues);
				md.addAttribute("formmode", "list1");

			}

		} else if (formmode == null || formmode.equals("list")) {
			if (Month != null || Month == "") {
				List<spf_entity> spfValues = Spf_repo.getESI(Month);

				for (int i = 0; i < spfValues.size(); i++) {
					spf_entity entity = spfValues.get(i);
					System.out.println("Hi this is Main for ESI");
					System.out.println("Bank Acct No: " + entity.getBank_acct_no());
					System.out.println("Bank Name: " + entity.getBank_name());
					System.out.println("Salary Month: " + entity.getSalary_month());
					System.out.println("salary " + entity.getGross_salary());
					String stringValue = entity.getGross_salary(); // Replace this with your desired string

					BigDecimal grosspay = new BigDecimal(stringValue);

					int intValue = grosspay.intValue();
					int emailId = Math.round(intValue * 3.25f / 100);
					String stringValue1 = Integer.toString(emailId);
					entity.setEmail_id(stringValue1);

					int ifsc = Math.round(intValue * 0.75f / 100);
					String stringValue2 = Integer.toString(ifsc);
					entity.setIfsc_code(stringValue2);

					int remarks = Math.round(intValue * 4.00f / 100);
					String stringValue3 = Integer.toString(remarks);
					entity.setRemarks(stringValue3);
					// spfValues.set(0, entity);
				}

				md.addAttribute("ghj", spfValues);
				md.addAttribute("formmode", "list");
				md.addAttribute("month", Month);
			} else {
				YearMonth currentYearMonth = YearMonth.now();

				// Format the current month and year as a string
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
				String formattedMonthYear = currentYearMonth.format(formatter);
				// String[] parts = formattedMonthYear.split(" ");
				// Print the current month and year
				System.out.println("Current Month and Year: " + formattedMonthYear);

				List<spf_entity> spfValues = Spf_repo.getall(formattedMonthYear);

				for (int i = 0; i < spfValues.size(); i++) {
					spf_entity entity = spfValues.get(i);
					// Do something with the entity, e.g., print its properties
					System.out.println("Bank Acct No: " + entity.getBank_acct_no());
					System.out.println("Bank Name: " + entity.getBank_name());
					System.out.println("Salary Month: " + entity.getSalary_month());

					String stringValue = entity.getBasic_pay(); // Replace this with your desired string
					BigDecimal basicPay = new BigDecimal(stringValue);

					int intValue = basicPay.intValue();
					int emailId = Math.round(intValue * 8.33f / 100);
					String stringValue1 = Integer.toString(emailId);
					entity.setEmail_id(stringValue1);

					int ifsc = Math.round(intValue * 3.67f / 100);
					String stringValue11 = Integer.toString(ifsc);
					entity.setIfsc_code(stringValue11);

					int remarks = Math.round(intValue * 12.00f / 100);
					String stringValue2 = Integer.toString(remarks);
					entity.setRemarks(stringValue2);
					spfValues.set(0, entity);

				}

				md.addAttribute("ghj", spfValues);
				md.addAttribute("formmode", "list");

			}

		}

		return "ESI";
	}

	@RequestMapping(value = "ackt", method = { RequestMethod.GET, RequestMethod.POST })
	public String ackt(@RequestParam(required = false) String formmode, @RequestParam(required = false) String resName,
			@RequestParam(required = false) String a, @RequestParam(required = false) String Month, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode == "table") {
			md.addAttribute("formmode", "table");
		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("editing", Spf_repo.findit(a));
		} else if (formmode.equals("list")) {
			if (Month != null || Month == "") {
				List<spf_entity> spfValues = Spf_repo.getall(Month);

				md.addAttribute("ghj", spfValues);
				md.addAttribute("formmode", "list");
				md.addAttribute("month", Month);
			} else {
				YearMonth currentYearMonth = YearMonth.now();

				// Format the current month and year as a string
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
				String formattedMonthYear = currentYearMonth.format(formatter);
				// String[] parts = formattedMonthYear.split(" ");
				// Print the current month and year
				System.out.println("Current Month and Year: " + formattedMonthYear);

				List<spf_entity> spfValues = Spf_repo.getall(formattedMonthYear);

				md.addAttribute("ghj", spfValues);
				md.addAttribute("formmode", "list");

			}

		}

		return "AckTrigger";
	}

	@RequestMapping(value = "gst", method = { RequestMethod.GET, RequestMethod.POST })
	public String gst(@RequestParam(required = false) String formmode, @RequestParam(required = false) String raised,
			@RequestParam(required = false) String resName, @RequestParam(required = false) String tran_id,
			@RequestParam(required = false) String Month, @RequestParam(required = false) String Year, Model md,
			@RequestParam(required = false) String uniqueid, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		// System.out.println(raised);

		if (formmode == null || formmode == "table") {
			md.addAttribute("formmode", "table");

			// System.out.println("it is add part train id + train id for gst
			// india"+uniqueid);
		} else if (formmode.equals("table2")) {
			md.addAttribute("formmode", "table2");
		} else if (formmode.equals("vtb")) {
			md.addAttribute("formmode", "vtb");
		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
		}

		else if (formmode.equals("add1")) {
			md.addAttribute("formmode", "add1");
		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("editing", gstBtmRep.findByTran(uniqueid));
		} else if (formmode.equals("edit1")) {

			md.addAttribute("formmode", "edit1");
			md.addAttribute("editingoverseas", gstoverseasRepo.findByTranoverseas(uniqueid));
		} else if (formmode.equals("upload")) {
			md.addAttribute("formmode", "upload");
		} else if (formmode.equals("list")) {
			YearMonth currentYearMonth = YearMonth.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy");
			String formattedMonth = currentYearMonth.format(formatter);
			String formattedYear = currentYearMonth.format(formatter1);
			System.out.println("Current Month and Year: " + formattedMonth + formattedYear);
			System.out.println(Month + Year);
			if (raised.equals("By Us - India")) {
				if (Month == null || Year == null) {
					List<GstBtmEntity> gstvalues = gstBtmRep.getByIndia(formattedMonth, formattedYear);
					md.addAttribute("ghj", gstvalues);

					md.addAttribute("raised", raised);
					md.addAttribute("monthy", formattedMonth);
					md.addAttribute("year", formattedYear);
					md.addAttribute("formmode", "list");

				} else if (Month != null || Year != null) {
					List<GstBtmEntity> gstvalues = gstBtmRep.getByIndia(Month, Year);
					md.addAttribute("ghj", gstvalues);
					md.addAttribute("raised", raised);
					md.addAttribute("monthy", Month);
					md.addAttribute("year", Year);
					md.addAttribute("formmode", "list");
				}
			} else if (raised.equals("On Us - India")) {
				if (Month == null || Year == null) {
					List<GstBtmEntity> gstvalues = gstBtmRep.getOnIndia(formattedMonth, formattedYear);
					md.addAttribute("ghj", gstvalues);
					md.addAttribute("raised", raised);
					md.addAttribute("monthy", formattedMonth);
					md.addAttribute("year", formattedYear);
					md.addAttribute("formmode", "list");
				} else {
					List<GstBtmEntity> gstvalues = gstBtmRep.getOnIndia(Month, Year);
					md.addAttribute("ghj", gstvalues);
					md.addAttribute("raised", raised);
					md.addAttribute("monthy", Month);
					md.addAttribute("year", Year);
					md.addAttribute("formmode", "list");
				}

			}
		} else if (formmode.equals("list1")) {
			YearMonth currentYearMonth1 = YearMonth.now();
			DateTimeFormatter formatter11 = DateTimeFormatter.ofPattern("MM");
			DateTimeFormatter formatter111 = DateTimeFormatter.ofPattern("yyyy");
			String formattedMonth1 = currentYearMonth1.format(formatter111);
			String formattedYear1 = currentYearMonth1.format(formatter111);
			System.out.println("Current Month and Year: " + formattedMonth1 + formattedYear1);
			System.out.println(Month + Year);
			if (raised.equals("By Us - Overseas")) {
				if (Month == null || Year == null) {
					// <GstBtmEntity> gstvalues =
					// gstBtmRep.getOnIndia(formattedMonth1,formattedYear1);

					List<Gstoverseas> gstvaluess = gstoverseasRepo.getBygstoversea(formattedMonth1, formattedYear1);
					md.addAttribute("ghj", gstvaluess);
					System.out.println("+++++" + gstvaluess);
					md.addAttribute("raised", raised);
					md.addAttribute("monthy", formattedMonth1);
					md.addAttribute("year", formattedYear1);
					md.addAttribute("formmode", "list1");
				} else {
					List<Gstoverseas> gstvaluess = gstoverseasRepo.getBygstoversea(Month, Year);
					System.out.println(gstvaluess);
					md.addAttribute("ghj", gstvaluess);
					md.addAttribute("raised", raised);
					md.addAttribute("monthy", Month);
					md.addAttribute("year", Year);
					md.addAttribute("formmode", "list1");
				}

			}
		}

		return "GST";
	}

	@RequestMapping(value = "bank_acct", method = { RequestMethod.GET, RequestMethod.POST })
	public String bank_acct(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String raised, @RequestParam(required = false) String resName,
			@RequestParam(required = false) String a, @RequestParam(required = false) String b,
			@RequestParam(required = false) String Month, @RequestParam(required = false) String Year, Model md,
			HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {
			if (Month == null || Month == "") {
				YearMonth currentYearMonth = YearMonth.now();

				// Format the current month and year as a string
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
				String formattedMonthYear = currentYearMonth.format(formatter);
				// String[] parts = formattedMonthYear.split(" ");
				// Print the current month and year
				System.out.println("Current Month and Year: " + formattedMonthYear);

				List<spf_entity> spfValues = Spf_repo.getall(formattedMonthYear);

				md.addAttribute("ghj", spfValues);
				md.addAttribute("formmode", "list");

			} else {
				List<spf_entity> spfValues = Spf_repo.getall(Month);
				md.addAttribute("ghj", spfValues);
				md.addAttribute("formmode", "list");
				md.addAttribute("month", Month);

			}
			// md.addAttribute("ghj", Spf_repo.getData(b));
			// md.addAttribute("formmode", "list");
		}

		else if (formmode.equals("table")) {
			md.addAttribute("formmode", "table");
		} else if (formmode.equals("edit")) {
			md.addAttribute("formmode", "edit");
			md.addAttribute("editing", Spf_repo.findit(a));
		} else {

		}
		return "BankAcct";
	}

	@RequestMapping(value = "viewtobtm", method = RequestMethod.POST)
	@ResponseBody
	public String viewtobtm(@RequestParam(required = false) String b, Model md, HttpServletRequest rq) {
		// System.out.println(b);
		bankDetailService.getviewtobtm(b);
		return "success";

	}

	@RequestMapping(value = "GstDownload1", method = RequestMethod.GET)

	@ResponseBody
	public InputStreamResource GstDownload1(HttpServletResponse response, @RequestParam(required = false) String raised,
			@RequestParam(required = false) String month, @RequestParam(required = false) String year

	) throws IOException, SQLException {

		response.setContentType("application/octet-stream");
		System.out.println("Gst_Download in the navigation controller" + month + year);
		InputStreamResource resource = null;
		try {

			String filetype = "Excel";
			String Month = month;
			System.out.println(Month + "Month");
			String Year = year;
			System.out.println(Year + "Year");

			String Raised = raised;
			System.out.println(Raised + "Raised");

			// logger.info("Getting download File :" + + ", FileType :Excel" + + "");

			File repfile = placementServices.getGstFile(filetype, Month, Year, Raised);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}

	@RequestMapping(value = "GSTPROFILE", method = RequestMethod.POST)
	@ResponseBody
	public String barathvarson(Model md, HttpServletRequest rq, @ModelAttribute GstBtmEntity gstBtmEntity,
			String tran_id, String Gst_type) {

		System.out.println("The solid Id >>>>>>>>>>>>>>>>>>>>>>>>> " + gstBtmEntity.getTran_id());
		GstBtmEntity up = gstBtmRep.findByTran(gstBtmEntity.getTran_id());
		up.setInv_amt(gstBtmEntity.getInv_amt());
		up.setClient(gstBtmEntity.getClient());
		up.setGstin(gstBtmEntity.getGstin());
		up.setInvoice_no(gstBtmEntity.getInvoice_no());
		up.setInvoice_date(gstBtmEntity.getInvoice_date());
		up.setInv_desc(gstBtmEntity.getInv_desc());
		up.setInv_sgst(gstBtmEntity.getInv_sgst());
		up.setInv_cgst(gstBtmEntity.getInv_cgst());
		up.setInv_igst(gstBtmEntity.getInv_igst());
		up.setTotal_gst_amt(gstBtmEntity.getTotal_gst_amt());
		up.setInv_tot_amt(gstBtmEntity.getInv_tot_amt());
		up.setClient_remark(gstBtmEntity.getClient_remark());
		up.setGst_type(gstBtmEntity.getGst_type());

		System.out.println(gstBtmEntity.getGst_type());
		System.out.println(tran_id);
		gstBtmRep.save(up);
		// System.out.println("SalaryParameter");

		return "success";

	}

	@PostMapping("viewtogst")
	@ResponseBody
	public String viewtogst(@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq,
			@RequestParam(required = false) String b) {
		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		System.out.println(b);
		System.out.println(b.substring(0, 4));
		System.out.println(b.substring(4, 6));
		int uniqueIdCounter = Integer.parseInt(b);
		try {
			// AccessRoles up = new AccessRoles();
			// List<BSPF_ENTITY> up1 = SpfRepo.getData(b);
			List<gst> up2 = gstRep.getData(b.substring(4, 6), b.substring(0, 4));
			List<spf_entity> up3 = new ArrayList<>();

			System.out.println(up2);
			Spf_repo.saveAll(up3);
			String msg = "Data Saved Successfully"; // Changed the message
			return msg;
		} catch (Exception e) {
			e.printStackTrace();
			return "Error: " + e.getMessage();
		}
	}

	@RequestMapping(value = "TDS", method = { RequestMethod.GET, RequestMethod.POST })
	public String TDS(@RequestParam(required = false) String formmode, @RequestParam(required = false) String moths,
			@RequestParam(required = false) String d, @RequestParam(required = false) String twoDigitYear,
			@RequestParam(required = false) String years, @RequestParam(required = false) String date_of_pay,
			@RequestParam(required = false) String uniqueids, @RequestParam(required = false) String Month,
			@RequestParam(required = false) String Year, Model md, tdsentity tdsentity, HttpServletRequest req)
			throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		System.out.println(moths);
		System.out.println(d);
		System.out.println(years);
		if (formmode == null || formmode == "table") {
			md.addAttribute("formmode", "table");
		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", "view");
		}

		else if (formmode.equals("list1")) {

			/*
			 * if(moths == null || years == null) { List<tdsentity> tdsvalues =
			 * tdsRepos.gettdswithdec(moths,years); md.addAttribute("tds",tdsvalues);
			 * 
			 * }else if(moths != null || years!= null){
			 */

			YearMonth currentYearMonth = YearMonth.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy");
			String formattedMonth = currentYearMonth.format(formatter);
			String formattedYear = currentYearMonth.format(formatter1);
			System.out.println("Current Month and Year: " + formattedMonth + formattedYear);
			System.out.println(moths + Year);
			if (moths == null || years == null) {
				List<tdsentity> tdsvalues = tdsRepos.gettdswithfirst(formattedMonth, formattedYear);
				md.addAttribute("tds", tdsvalues);
				md.addAttribute("formmode", "list1");
			} else if (moths != null || years != null) {
				System.out.println("==========================" + moths + "----------------" + years);
				List<tdsentity> tdsvalues = tdsRepos.gettdswithdecs(moths, years);
				md.addAttribute("tds", tdsvalues);
				System.out.println(tdsvalues);
				md.addAttribute("formmode", "list1");// hhhhhhhhhhhhhhhhhh

			}

		}

		else if (formmode.equals("table1")) {
			md.addAttribute("formmode", "table1");
		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
		} else if (formmode.equals("add1")) {
			md.addAttribute("formmode", "add1");
		} else if (formmode.equals("edit")) {

			md.addAttribute("edittab1", tdsRepos.getlisttab1(uniqueids));
			md.addAttribute("formmode", "edit");
		} else if (formmode.equals("edit1")) {

			md.addAttribute("edittab2", tdsRepos.getlisttab2(uniqueids));
			md.addAttribute("formmode", "edit1");
		} else if (formmode.equals("delete")) {
			md.addAttribute("formmode", "delete");
		}

		return "TDS";
	}

	@RequestMapping(value = "editgstonus", method = RequestMethod.POST)
	@ResponseBody
	public String editgstonus(@ModelAttribute GstBtmEntity GstBtmEntity, String tran_id, String Gst_type,
			@RequestParam(required = false) String uniqueid, @RequestParam(required = false) String Ddt,
			@RequestParam(required = false) String dsr, @RequestParam(required = false) String rds,
			@RequestParam(required = false) String temp4, @RequestParam(required = false) String f)
			throws ParseException {
		String u = uniqueid;

		System.out.println(u);

		GstBtmEntity up = gstBtmRep.findByTran(u);
		System.out.println("hi this is uniqueid for editonusindia" + gstBtmRep.findByTran(u));
		System.out.println("hi this is btm");

		// SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss",
		// Locale.ENGLISH);

		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");
			up.setTran_id(GstBtmEntity.getTran_id());
			up.setPart_tran_type(GstBtmEntity.getPart_tran_type());
			up.setClient(GstBtmEntity.getClient());
			up.setGstin(GstBtmEntity.getGstin());
			up.setGst_type(GstBtmEntity.getGst_type());
			up.setInvoice_no(GstBtmEntity.getInvoice_no());
			up.setInv_desc(GstBtmEntity.getInv_desc());
			up.setInvoice_date(GstBtmEntity.getInvoice_date());
			up.setInv_amt(GstBtmEntity.getInv_amt());
			up.setInv_sgst(GstBtmEntity.getInv_sgst());
			up.setInv_cgst(GstBtmEntity.getInv_cgst());
			up.setInv_igst(GstBtmEntity.getInv_igst());
			up.setTotal_gst_amt(GstBtmEntity.getTotal_gst_amt());
			up.setInv_tot_amt(GstBtmEntity.getInv_tot_amt());
			up.setClient_remark(GstBtmEntity.getClient_remark());
			// up.setTran_date(dateFormat.parse(f));
			// up.setInvoice_date(dateFormat.parse(rds));
			up.setTran_date(GstBtmEntity.getTran_date());

			up.setUniqueid(GstBtmEntity.getUniqueid());

			gstBtmRep.save(up);
			System.out.println("hi this is gst edit for india" + GstBtmEntity.getGst_type());
			System.out.println("hi this is btm" + GstBtmEntity.getGstin());
			System.out.println("hi this is btm" + GstBtmEntity.getTran_date());

			// Save the 'up' object with the updated entry_time

		} catch (Exception e) {
			e.printStackTrace(); // Handle potential errors here, such as ParseException
		}

		return "edited Successfully";

	}

	@RequestMapping(value = "editoverseas", method = RequestMethod.POST)
	@ResponseBody
	public String editoverseas(@ModelAttribute Gstoverseas Gstoverseas, String tran_id,
			@RequestParam(required = false) String uniqueid) throws ParseException {
		String u1 = uniqueid;

		Gstoverseas up = gstoverseasRepo.findByTranoverseas(u1);
		System.out.println("hi this is btm");

		// SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss",
		// Locale.ENGLISH);

		try {
			up.setTran_id(Gstoverseas.getTran_id());
			up.setPart_tran_type(Gstoverseas.getPart_tran_type());
			up.setClient(Gstoverseas.getClient());

			up.setInvoice_no(Gstoverseas.getInvoice_no());
			up.setInv_no(Gstoverseas.getInv_no());
			up.setInv_date(Gstoverseas.getInv_date());
			up.setInv_amt_fcy(Gstoverseas.getInv_amt_fcy());
			up.setInv_amt_inr(Gstoverseas.getInv_amt_inr());
			up.setBank_account(Gstoverseas.getBank_account());
			up.setBank_account(Gstoverseas.getBank_account());
			up.setTran_date(Gstoverseas.getTran_date());
			// up.setUniqueid(Gstoverseas.getUniqueid());

			gstoverseasRepo.save(up);
			System.out.println("hi this is gstss from overseas" + Gstoverseas.getTran_id());
			System.out.println("hi this is btm" + Gstoverseas.getInv_no());

			// Save the 'up' object with the updated entry_time

		} catch (Exception e) {
			e.printStackTrace(); // Handle potential errors here, such as ParseException
		}

		return "edited Successfully OVERSEAS";

	}

	/*
	 * @RequestMapping(value = "downloadpage", method = RequestMethod.GET)
	 * 
	 * @ResponseBody public InputStreamResource INRReportDownload
	 * (HttpServletResponse response,
	 * 
	 * @RequestParam(value = "filetype", required = false) String filetype) throws
	 * IOException, SQLException, JRException {
	 * 
	 * response.setContentType("application/octet-stream");
	 * 
	 * InputStreamResource resource = null; try { File repfile =
	 * placementServices.getECLFile(filetype);
	 * 
	 * response.setHeader("Content-Disposition", "attachment; filename=" +
	 * repfile.getName()); response.setContentType(
	 * "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); // Set
	 * the content type to Excel
	 * 
	 * try (InputStream inputStream = new FileInputStream(repfile); OutputStream
	 * outputStream = response.getOutputStream()) {
	 * 
	 * byte[] buffer = new byte[1024]; int bytesRead;
	 * 
	 * while ((bytesRead = inputStream.read(buffer)) != -1) {
	 * outputStream.write(buffer, 0, bytesRead); }
	 * 
	 * outputStream.flush(); } } catch (FileNotFoundException e) { // Handle file
	 * not found exception e.printStackTrace(); // Consider logging or handling the
	 * exception appropriately } catch (IOException e) { // Handle IO exception
	 * e.printStackTrace(); // Consider logging or handling the exception
	 * appropriately } catch (Exception e) { // Handle other exceptions
	 * e.printStackTrace(); // Consider logging or handling the exception
	 * appropriately }
	 * 
	 * return resource; }
	 */

	/*
	 * @RequestMapping(value = "INRReportDownload", method = RequestMethod.GET)
	 * 
	 * @ResponseBody public InputStreamResource INRReportDownload
	 * (HttpServletResponse response,
	 * 
	 * @RequestParam(value = "filetype", required = false) String
	 * filetype,@RequestParam(required = false) String month,@RequestParam(required
	 * = false) String year) throws IOException, SQLException, JRException {
	 * 
	 * response.setContentType("application/octet-stream");
	 * 
	 * InputStreamResource resource = null; try { File repfile =
	 * placementServices.getECLFile(filetype,month,year);
	 * 
	 * response.setHeader("Content-Disposition", "attachment; filename=" +
	 * repfile.getName()); response.setContentType(
	 * "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); // Set
	 * the content type to Excel
	 * 
	 * try (InputStream inputStream = new FileInputStream(repfile); OutputStream
	 * outputStream = response.getOutputStream()) {
	 * 
	 * byte[] buffer = new byte[1024]; int bytesRead;
	 * 
	 * while ((bytesRead = inputStream.read(buffer)) != -1) {
	 * outputStream.write(buffer, 0, bytesRead); }
	 * 
	 * outputStream.flush(); } } catch (FileNotFoundException e) { // Handle file
	 * not found exception e.printStackTrace(); // Consider logging or handling the
	 * exception appropriately } catch (IOException e) { // Handle IO exception
	 * e.printStackTrace(); // Consider logging or handling the exception
	 * appropriately } catch (Exception e) { // Handle other exceptions
	 * e.printStackTrace(); // Consider logging or handling the exception
	 * appropriately }
	 * 
	 * return resource; }
	 */

	@RequestMapping(value = "/INRReportDownload", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<InputStreamResource> INRReportDownload(HttpServletResponse response,
			@RequestParam(required = false) String filetype, @RequestParam(required = false) String month,
			@RequestParam(required = false) String year) throws IOException, SQLException, JRException {

		// Set the response content type
		response.setContentType("application/octet-stream");

		String YearL = null;
		if (month.equals("01")) {
			YearL = "Jan";
		} else if (month.equals("02")) {
			YearL = "Feb";
		} else if (month.equals("02")) {
			YearL = "Feb";
		} else if (month.equals("03")) {
			YearL = "Mar";
		} else if (month.equals("04")) {
			YearL = "Apr";
		} else if (month.equals("05")) {
			YearL = "May";
		} else if (month.equals("06")) {
			YearL = "Jun";
		} else if (month.equals("07")) {
			YearL = "Jul";
		} else if (month.equals("08")) {
			YearL = "Aug";
		} else if (month.equals("09")) {
			YearL = "Sep";
		} else if (month.equals("10")) {
			YearL = "Oct";
		} else if (month.equals("11")) {
			YearL = "Nov";
		} else if (month.equals("12")) {
			YearL = "Dec";
		}
		// Construct the file name
		String fileName = "GST_" + YearL + "-" + year + ".xlsx";

		// Get the file from the service
		File eclFile = placementServices.getECLFile(fileName, filetype, month, year);

		// Prepare the InputStreamResource
		InputStreamResource resource = new InputStreamResource(new FileInputStream(eclFile));

		// Prepare the response headers
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);

		// Return the ResponseEntity with InputStreamResource and headers
		return ResponseEntity.ok().headers(headers).contentLength(eclFile.length())
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
	}

	@RequestMapping(value = "adddatasindia", method = RequestMethod.POST)
	@ResponseBody
	public String adddatasindia(Model md, HttpServletRequest rq, @ModelAttribute GstBtmEntity GstBtmEntity,
			String tran_id, @RequestParam(required = false) String tran_date,
			@RequestParam(required = false) String invoice_date) {

		System.out.println("the rating AGENCY>>>> ");
		GstBtmEntity up = GstBtmEntity;
		up.setTran_date(GstBtmEntity.getTran_date());
		up.setInvoice_date(GstBtmEntity.getInvoice_date());

		up.setUniqueid(GstBtmEntity.getTran_id() + GstBtmEntity.getPart_tran_id());

		System.out.println("hi it is gstss here your adding the record for india");
		System.out.println("hi it is gstss here your adding the record " + GstBtmEntity.getTran_date());

		gstBtmRep.save(up);

		return "add successfullu";

	}

	@RequestMapping(value = "addoverseas", method = RequestMethod.POST)
	@ResponseBody
	public String addoverseas(Model md, HttpServletRequest rq, @ModelAttribute Gstoverseas Gstoverseas,
			String tran_id) {

		System.out.println("the rating AGENCY>>>> ");
		Gstoverseas up = Gstoverseas;
		up.setUniqueid(Gstoverseas.getTran_id() + Gstoverseas.getPart_tran_id());

		System.out.println("hi it is gstss here your adding the record for overseas");

		gstoverseasRepo.save(up);

		return "";

	}

	@RequestMapping(value = "deleteoverseas", method = RequestMethod.POST)
	@ResponseBody
	public String deleteoverseas(Model md, HttpServletRequest rq, @ModelAttribute Gstoverseas Gstoverseas,
			String tran_id) {

		System.out.println("the rating AGENCY>>>> ");
		Gstoverseas up = Gstoverseas;
		System.out.println("hi it is gstss here your adding the record " + Gstoverseas.getTran_date());

		System.out.println("hi it is gstss here your adding the record for overseas");

		gstoverseasRepo.save(up);

		return "deleted successfully";

	}

	@RequestMapping(value = "deleteindia", method = RequestMethod.POST)
	@ResponseBody
	public String deleteindia(Model md, HttpServletRequest rq, @ModelAttribute GstBtmEntity GstBtmEntity,
			String tran_id, @RequestParam(required = false) String tran_date,
			@RequestParam(required = false) String invoice_date) {

		System.out.println("the rating AGENCY>>>> ");
		GstBtmEntity up = GstBtmEntity;

		System.out.println("hi it is gstss here your adding the record for india");
		System.out.println("hi it is gstss here your adding the record " + GstBtmEntity.getTran_date());

		gstBtmRep.save(up);

		return "deleted successfully";

	}

	@RequestMapping(value = "viewtotds", method = RequestMethod.POST)
	@ResponseBody
	public String viewtotds(@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq,
			@RequestParam(required = false) String b, @RequestParam(required = false) String a) {

		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}" + a + b);

		try {
			List<btdsview> up2 = btdsviewRepos.getdatetdslist(b, a);
			List<tdsentity> up3 = new ArrayList<>();

			for (btdsview btdsviews : up2) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				tdsentity tdsentitys = new tdsentity();
				tdsentitys.setEmp_no(btdsviews.getEmp_no());
				tdsentitys.setEmp_name(btdsviews.getEmp_name());
				tdsentitys.setEmp_desig(btdsviews.getEmp_desig());
				tdsentitys.setEmp_group(btdsviews.getEmp_group());
				tdsentitys.setPan_no(btdsviews.getPan_no());
				tdsentitys.setDate_of_birth(btdsviews.getDate_of_birth());
				tdsentitys.setDate_of_joining(btdsviews.getDate_of_joining());
				tdsentitys.setRecord_date(btdsviews.getRecord_date());
				tdsentitys.setSalary_month(btdsviews.getSalary_month());
				tdsentitys.setBasic_pay(btdsviews.getBasic_pay());
				tdsentitys.setHra(btdsviews.getHra());
				tdsentitys.setSpl_allow(btdsviews.getSpl_allow());
				tdsentitys.setMedi_reimb(btdsviews.getMedi_reimb());
				tdsentitys.setConv_allow(btdsviews.getConv_allow());
				tdsentitys.setLunch_allow(btdsviews.getLunch_allow());
				tdsentitys.setEdu_allow(btdsviews.getEdu_allow());
				tdsentitys.setBuss_attire(btdsviews.getBuss_attire());
				tdsentitys.setCar_maint(btdsviews.getCar_maint());
				tdsentitys.setLeave_travel_allow(btdsviews.getLeave_travel_allow());
				tdsentitys.setOutstn_allow(btdsviews.getOutstn_allow());
				tdsentitys.setAnnual_loyal_bonus(btdsviews.getAnnual_loyal_bonus());
				tdsentitys.setOtr_allow(btdsviews.getOtr_allow());
				tdsentitys.setGross_salary(btdsviews.getGross_salary());
				tdsentitys.setSpf(btdsviews.getSpf());
				tdsentitys.setTds(btdsviews.getTds());
				tdsentitys.setProf_tax(btdsviews.getProf_tax());
				tdsentitys.setEsi(btdsviews.getEsi());
				tdsentitys.setRecovery(btdsviews.getRecovery());
				tdsentitys.setOtr_ded(btdsviews.getOtr_ded());
				tdsentitys.setTotal_deductions(btdsviews.getTotal_deductions());
				tdsentitys.setNet_salary(btdsviews.getNet_salary());
				tdsentitys.setDate_of_pay(btdsviews.getDate_of_pay());
				tdsentitys.setCum_tds_fy(btdsviews.getCum_tds_fy());
				tdsentitys.setProv_it(btdsviews.getProv_it());
				tdsentitys.setTax_due(btdsviews.getTax_due());
				tdsentitys.setTax_per_month(btdsviews.getTax_per_month());
				tdsentitys.setBank_name(btdsviews.getBank_name());
				tdsentitys.setBank_bsr_code(btdsviews.getBank_bsr_code());
				tdsentitys.setBank_chalan_no(btdsviews.getBank_chalan_no());
				tdsentitys.setChalan_amt(btdsviews.getChalan_amt());
				tdsentitys.setTds_remit_date(btdsviews.getTds_remit_date());
				tdsentitys.setTds_tran_ref(btdsviews.getTds_tran_ref());
				tdsentitys.setMobile_no(btdsviews.getMobile_no());
				tdsentitys.setEmail_id(btdsviews.getEmail_id());
				tdsentitys.setEntity_flg(btdsviews.getEntity_flg());
				tdsentitys.setDel_flg(btdsviews.getDel_flg());
				tdsentitys.setEntry_time(btdsviews.getEntry_time());
				tdsentitys.setEntry_user(btdsviews.getEntry_user());
				tdsentitys.setModify_flg(btdsviews.getModify_flg());
				tdsentitys.setModify_time(btdsviews.getModify_time());
				tdsentitys.setModify_user(btdsviews.getModify_user());
				tdsentitys.setVerify_time(btdsviews.getVerify_time());
				tdsentitys.setVerify_user(btdsviews.getVerify_user());
				tdsentitys.setRemarks(btdsviews.getRemarks());
				tdsentitys.setAadhar_no(btdsviews.getAadhar_no());
				tdsentitys.setRate_of_tds(btdsviews.getRate_of_tds());
				tdsentitys.setUniqueids(tdsentitys.getEmp_no() + dateFormat.format(tdsentitys.getDate_of_pay()));

				up3.add(tdsentitys);

				// up3.add(gstoverseas);
			}

			System.out.println(up3);
			//
			tdsRepos.saveAll(up3);

			// gstBtmRep.getInsert(b,a);

			System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}" + a + b);
			// int uniqueIdCounter = Integer.parseInt(b);
			// int uniqueIdCounter1 = Integer.parseInt(a);

			// System.out.println(gstRep.getInsert(a,b));
			String msg = "Data Saved Successfully";
			return msg;

		} catch (Exception e) {
			e.printStackTrace();
			return "Error: " + e.getMessage();
		}
	}

	@RequestMapping(value = "submitaddtds", method = RequestMethod.POST)
	@ResponseBody
	public String submitaddtds(Model md, HttpServletRequest rq, @ModelAttribute tdsentity tdsentity, String emp_name) {

		System.out.println("the rating AGENCY>>>> ");
		tdsentity up = tdsentity;
		up.setUniqueids(tdsentity.getEmp_no() + tdsentity.getDate_of_pay());
		System.out.println(tdsentity.getEmp_no() + tdsentity.getDate_of_pay());

		System.out.println("hi it is gstss here your adding the record for TDS");

		tdsRepos.save(up);

		return "added successfully Tds";

	}

	@RequestMapping(value = "submitaddtds2", method = RequestMethod.POST)
	@ResponseBody
	public String submitaddtds2(Model md, HttpServletRequest rq, @ModelAttribute tdsentity tdsentity, String tran_id) {

		System.out.println("the rating AGENCY>>>> ");
		tdsentity up = tdsentity;
		up.setUniqueids(tdsentity.getEmp_no() + tdsentity.getDate_of_pay());
		System.out.println(tdsentity.getEmp_no() + tdsentity.getDate_of_pay());

		System.out.println("hi it is gstss here your adding the record for TDS");

		tdsRepos.save(up);

		return "";

	}

	@RequestMapping(value = "tdstab1edit", method = RequestMethod.POST)
	@ResponseBody
	public String tdstab1edit(@ModelAttribute tdsentity tdsentity, String tran_id,
			@RequestParam(required = false) String uniqueids) throws ParseException {
		String u1 = uniqueids;

		tdsentity up = tdsRepos.getlisttab1(u1);
		System.out.println("hi this is btm");

		// SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss",
		// Locale.ENGLISH);

		try {

			up.setEmp_name(tdsentity.getEmp_name());
			up.setPan_no(tdsentity.getPan_no());
			up.setDate_of_pay(tdsentity.getDate_of_pay());
			up.setNet_salary(tdsentity.getNet_salary());
			up.setRate_of_tds(tdsentity.getTds_tran_ref());
			up.setBank_bsr_code(tdsentity.getBank_bsr_code());
			up.setTds(tdsentity.getTds());
			up.setBank_chalan_no(tdsentity.getBank_chalan_no());
			up.setBank_name(tdsentity.getBank_name());
			up.setChalan_amt(tdsentity.getChalan_amt());
			up.setTds_remit_date(tdsentity.getTds_remit_date());
			up.setTds_tran_ref(tdsentity.getTds_tran_ref());

			// up.setUniqueid(Gstoverseas.getUniqueid());

			tdsRepos.save(up);
			System.out.println("hi this is gstss from tds" + tdsentity.getEmp_name());
			System.out.println("hi this is btm" + tdsentity.getDate_of_pay());

			// Save the 'up' object with the updated entry_time

		} catch (Exception e) {
			e.printStackTrace(); // Handle potential errors here, such as ParseException
		}

		return "edited Successfully tdstable1";

	}

	@RequestMapping(value = "tdstab2edit", method = RequestMethod.POST)
	@ResponseBody
	public String tdstab2edit(@ModelAttribute tdsentity tdsentity, String tran_id,
			@RequestParam(required = false) String uniqueids) throws ParseException {
		String u1 = uniqueids;

		tdsentity up = tdsRepos.getlisttab1(u1);
		System.out.println("hi this is btm");

		// SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss",
		// Locale.ENGLISH);

		try {

			up.setEmp_name(tdsentity.getEmp_name());
			up.setPan_no(tdsentity.getPan_no());
			up.setDate_of_pay(tdsentity.getDate_of_pay());
			up.setNet_salary(tdsentity.getNet_salary());
			up.setRate_of_tds(tdsentity.getTds_tran_ref());
			up.setBank_bsr_code(tdsentity.getBank_bsr_code());
			up.setTds(tdsentity.getTds());
			up.setBank_chalan_no(tdsentity.getBank_chalan_no());
			up.setBank_name(tdsentity.getBank_name());
			up.setChalan_amt(tdsentity.getChalan_amt());
			up.setTds_remit_date(tdsentity.getTds_remit_date());
			up.setTds_tran_ref(tdsentity.getTds_tran_ref());

			// up.setUniqueid(Gstoverseas.getUniqueid());

			tdsRepos.save(up);
			System.out.println("hi this is gstss from tds" + tdsentity.getEmp_name());
			System.out.println("hi this is btm" + tdsentity.getDate_of_pay());

			// Save the 'up' object with the updated entry_time

		} catch (Exception e) {
			e.printStackTrace(); // Handle potential errors here, such as ParseException
		}

		return "edited Successfully tdstable2";

	}

	@RequestMapping(value = "deletetds", method = RequestMethod.POST)
	@ResponseBody
	public String deletetds(Model md, HttpServletRequest rq, @ModelAttribute tdsentity tdsentity, String tran_id,
			@RequestParam(required = false) String uniqueid) {

		System.out.println(uniqueid);
		tdsentity up = tdsRepos.delete1(uniqueid);

		System.out.println("hi it is gstss here your adding the record for india");
		System.out.println("hi it is gstss here your adding the record " + tdsentity.getUniqueids());

		tdsRepos.delete(up);

		return "deleted successfully";

	}

	/*
	 * @RequestMapping(value = "/tdsexceldownload", method = RequestMethod.GET,
	 * produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	 * 
	 * @ResponseBody public ResponseEntity<InputStreamResource> tdsexceldownload(
	 * HttpServletResponse response,
	 * 
	 * @RequestParam(required = false) String filetype,
	 * 
	 * @RequestParam(required = false) String moths,
	 * 
	 * @RequestParam(required = false) String years) throws IOException,
	 * SQLException, JRException {
	 * 
	 * // Set the response content type
	 * response.setContentType("application/octet-stream");
	 * 
	 * String YearL=null; if(moths.equals("01")) { YearL="Jan"; }else
	 * if(moths.equals("02")) { YearL="Feb"; }else if(moths.equals("02")) {
	 * YearL="Feb"; }else if(moths.equals("03")) { YearL="Mar"; }else
	 * if(moths.equals("04")) { YearL="Apr"; }else if(moths.equals("05")) {
	 * YearL="May"; }else if(moths.equals("06")) { YearL="Jun"; }else
	 * if(moths.equals("07")) { YearL="Jul"; }else if(moths.equals("08")) {
	 * YearL="Aug"; }else if(moths.equals("09")) { YearL="Sep"; }else
	 * if(moths.equals("10")) { YearL="Oct"; }else if(moths.equals("11")) {
	 * YearL="Nov"; }else if(moths.equals("12")) { YearL="Dec"; } // Construct the
	 * file name String fileName = "GST_" + YearL + "-" + years + ".xlsx";
	 * 
	 * // Get the file from the service File eclFile =
	 * placementServices.gettdsexcel(fileName,filetype, moths, years);
	 * 
	 * // Prepare the InputStreamResource InputStreamResource resource = new
	 * InputStreamResource(new FileInputStream(eclFile));
	 * 
	 * // Prepare the response headers HttpHeaders headers = new HttpHeaders();
	 * headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" +
	 * fileName);
	 * 
	 * // Return the ResponseEntity with InputStreamResource and headers return
	 * ResponseEntity.ok() .headers(headers) .contentLength(eclFile.length())
	 * .contentType(MediaType.APPLICATION_OCTET_STREAM) .body(resource); }
	 */
	@RequestMapping(value = { "/tdsexceldownload" }, method = { RequestMethod.GET }, produces = {
			"application/octet-stream" })
	@ResponseBody
	public ResponseEntity<InputStreamResource> tdsexceldownload(HttpServletResponse response,
			@RequestParam(required = false) String filetype, @RequestParam(required = false) String moths,
			@RequestParam(required = false) String years) throws IOException, SQLException, JRException {
		response.setContentType("application/octet-stream");
		String YearL = null;
		if (moths.equals("01")) {
			YearL = "Jan";
		} else if (moths.equals("02")) {
			YearL = "Feb";
		} else if (moths.equals("02")) {
			YearL = "Feb";
		} else if (moths.equals("03")) {
			YearL = "Mar";
		} else if (moths.equals("04")) {
			YearL = "Apr";
		} else if (moths.equals("05")) {
			YearL = "May";
		} else if (moths.equals("06")) {
			YearL = "Jun";
		} else if (moths.equals("07")) {
			YearL = "Jul";
		} else if (moths.equals("08")) {
			YearL = "Aug";
		} else if (moths.equals("09")) {
			YearL = "Sep";
		} else if (moths.equals("10")) {
			YearL = "Oct";
		} else if (moths.equals("11")) {
			YearL = "Nov";
		} else if (moths.equals("12")) {
			YearL = "Dec";
		}

		String fileName = "TDS" + YearL + "-" + years + ".xlsx";
		File eclFile = this.placementServices.gettdsexcel(fileName, filetype, moths, years);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(eclFile));
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=" + fileName);
		return ((BodyBuilder) ResponseEntity.ok().headers(headers)).contentLength(eclFile.length())
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
	}

	@RequestMapping(value = "submitdelete", method = RequestMethod.POST)
	@ResponseBody
	public String submitaddtds(Model md, HttpServletRequest rq,
			@ModelAttribute BTMAdminAssociateProfile BTMAdminAssociateProfile,
			@RequestParam(required = false) String resId) {

		System.out.println("fghjkl" + BTMAdminAssociateProfile.getDel_flg());
		BTMAdminAssociateProfile up = btmAdminAssociateProfileRep.delete2(resId);
		System.out.println(resId);
		System.out.println(up);
		up.setDel_flg("Y");

		btmAdminAssociateProfileRep.save(up);

		return "deleted successfully";

	}

	/*
	 * @RequestMapping(value = "sendSmsss", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public String sendSms(Model md, HttpServletRequest rq) {
	 * System.out.println("hihihihihihihihihihihkihihhhhhhhhhhhhhhhhhhhhh");
	 * 
	 * String url = "https://api.smslane.com/api/v2/SendSMS"; String senderId =
	 * "BOFIRE"; // Verify sender ID with the service provider String message =
	 * "Hi Employee, Please Mark Attendance through BTM Application for the day. It is MANDATORY. \n Thanks and Regards, Bornfire Innovation Private Limited."
	 * ; String mobileNumber = "9384374949"; String templateId =
	 * "1707170806443753132"; // Verify template ID with the service provider String
	 * apiKey = "Bornfire2017"; // Verify API key with the service provider String
	 * clientId = "siddhaiyan@bornfire.in";
	 * 
	 * HttpHeaders headers = new HttpHeaders();
	 * headers.setContentType(MediaType.APPLICATION_JSON);
	 * 
	 * // Construct the request body String requestBody = String.format(
	 * "{\"SenderId\":\"%s\",\"Message\":\"%s\",\"MobileNumbers\":\"%s\",\"TemplateId\":\"%s\",\"ApiKey\":\"%s\",\"ClientId\":\"%s\"}",
	 * senderId, message, mobileNumber, templateId, apiKey, clientId);
	 * 
	 * HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
	 * 
	 * RestTemplate restTemplate = new RestTemplate(); String response =
	 * restTemplate.postForObject(url,"nnjnnmn"+request, String.class);
	 * 
	 * return response; }
	 */

	@RequestMapping(value = "/sendsms", method = RequestMethod.POST)
	@ResponseBody
	public String sendSms(@RequestParam(required = false) String SenderId,
			@RequestParam(required = false) String Message, @RequestParam(required = false) String MobileNumbers,
			@RequestParam(required = false) String TemplateId, @RequestParam(required = false) String ApiKey,
			@RequestParam(required = false) String ClientId, @RequestParam(required = false) String oneto) {
		// Build the URL with proper encoding
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.smslane.com/api/v2/SendSMS")
				.queryParam("SenderId", SenderId).queryParam("Message", Message)
				.queryParam("MobileNumbers", MobileNumbers).queryParam("TemplateId", TemplateId)
				.queryParam("ApiKey", ApiKey).queryParam("ClientId", ClientId);

		// Create a RestTemplate instance to make HTTP requests
		RestTemplate restTemplate = new RestTemplate();

		// Send the request and get the response
		String response = restTemplate.getForObject(builder.toUriString() + "hhhhhhhhhh", String.class);
		System.out.println(response);
		return response;
	}

	@RequestMapping(value = "valuesending", method = RequestMethod.POST)
	@ResponseBody
	public String valuesending(Model md, HttpServletRequest rq,
			@ModelAttribute BTMAdminAssociateProfile BTMAdminAssociateProfile,

			@RequestParam(required = false) List<String> encodedNumbers,
			@RequestParam(required = false) List<String> demonumber, @RequestParam(required = false) String l,
			@RequestParam(required = false) String v, @RequestParam(required = false) String s,
			@RequestParam(required = false) String k, @RequestParam(required = false) String i,
			@RequestParam(required = false) String t) {

		try {

			List<String> b = encodedNumbers;

			encodedNumbers.add("9360390299");
			encodedNumbers.add("9486540575");
			encodedNumbers.add("6379817503");

			String encodedSender = URLEncoder.encode(l, "UTF-8");
			String encodedMessage = v;
			String encodedMobileNumbers = URLEncoder.encode(s, "UTF-8");
			String encodedTemplateId = URLEncoder.encode(k, "UTF-8");
			String encodedApiKey = URLEncoder.encode(i, "UTF-8");
			String encodedClientId = URLEncoder.encode(t, "UTF-8");

			for (String m : b) {
				try {
					String apiUrl = "https://api.smslane.com/api/v2/SendSMS";
					String constructedUrl = apiUrl + "?SenderId=" + encodedSender + "&Message=" + v + "&MobileNumbers="
							+ "91" + m + "&TemplateId=" + encodedTemplateId + "&ApiKey=" + encodedApiKey + "&ClientId="
							+ encodedClientId;
					
					System.out.println("constructedUrl"+constructedUrl);

					URL url = new URL(constructedUrl);
					HttpURLConnection connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");

					// Reading the response
					BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					String inputLine;
					StringBuilder response = new StringBuilder();

					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();
					System.out.println(response.toString() + m + "HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
					connection.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return "Sent Successfully";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "Error constructing URL";
		}
	}

	@RequestMapping(value = "send", method = RequestMethod.POST)
	@ResponseBody
	public String sendSMS(@RequestParam String url) {
		System.out.println("hhhhvvvvvvvvvvvvvvvv" + url);

		// Assuming you're passing the constructed URL containing SMS details as a query
		// parameter 'url'
		// Here you can perform additional validation on the request or handle the SMS
		// sending logic

		// For simplicity, I'll just make a GET request to the provided URL
		String dates = url;
		// RestTemplate restTemplate = new RestTemplate();
		// String response = restTemplate.getForObject(apiUrl, String.class);

		// You can return the response received from the SMS sending service or any
		// other relevant message
		return dates;

	}

	@RequestMapping(value = "onetwo", method = RequestMethod.POST)
	@ResponseBody
	public List<String> onetwo(Model md, HttpServletRequest rq,
			@ModelAttribute BTMAdminAssociateProfile BTMAdminAssociateProfile, @RequestParam(required = false) String p,
			@RequestParam(required = false) String q, @RequestParam(required = false) String r,
			@RequestParam(required = false) String url) {
		System.out.println("KKKKK" + p);
		// md.addAttribute("sms", AttendanceRegisterGetRep.getsms(p, q, r));
		// List<String> smsList = AttendanceRegisterGetRep.getsms(p, q, r); // Assuming
		// getsms() returns a
		System.out.println(resourceMasterRepo.smssenmding(p, q, r));
		List<String> smsList = resourceMasterRepo.smssenmding(p, q, r);
		// List<String>
		md.addAttribute("smss", smsList);
		// System.out.println(AttendanceRegisterGetRep.getsms(p, q, r));

		System.out.println(resourceMasterRepo.smssenmding(p, q, r));
		System.out.println(smsList);

		return resourceMasterRepo.smssenmding(p, q, r);

	}

	@RequestMapping(value = "salarystructures", method = { RequestMethod.GET, RequestMethod.POST })
	public String salarystructures(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String emp_no,
			@RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date ctc_eff_date,
			@RequestParam(required = false) String emp_no1, String keyword, Model md, HttpServletRequest req)

	{
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("salarypay", salary_Pay_Rep.getList());
		} else if (formmode.equals("add")) {
			System.out.println(emp_no1);
			md.addAttribute("salarypay", salary_Pay_Rep.getsalfromcvf(emp_no));

			md.addAttribute("formmode", "add");

			md.addAttribute("empty", "");
			md.addAttribute("resmasterlist", resourceMasterRepo.getalist());

			// md.addAttribute("empty", "");
			// md.addAttribute("resmasterlist",salary_Pay_Rep.getalist());

		} else if (formmode.equals("edit")) {
			md.addAttribute("formmode", "edit");
			md.addAttribute("salarypay", salary_Pay_Rep.getaedit1(emp_no, ctc_eff_date));
		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", "view");
			md.addAttribute("salarypay", salary_Pay_Rep.getaedit1(emp_no, ctc_eff_date));
		} else if (formmode.equals("enquiry")) {
			md.addAttribute("formmode", "enquiry");
			md.addAttribute("salarypay", salary_Pay_Rep.getaedit1(emp_no, ctc_eff_date));
		} else if (formmode.equals("revision")) {
			md.addAttribute("formmode", "revision");
			md.addAttribute("salarypay", salary_Pay_Rep.getaedit1(emp_no, ctc_eff_date));
		}
		return "salarystructures";
	}

	@RequestMapping(value = "AddScreen", method = RequestMethod.POST)

	@ResponseBody
	public String AddScreen(Model md, HttpServletRequest rq,

			@ModelAttribute Salary_Pay_Entity salary_Pay_Entity) {
		System.out.println(salary_Pay_Entity + "gggggggggggggggggggggggggggg");
		System.out.println(salary_Pay_Entity.getCtc_amt() + "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");

		Salary_Pay_Entity up = salary_Pay_Entity;
		up.setDel_flg("N");
		up.setEntity_flg("Y");
		up.setModify_flg("N");
		salary_Pay_Rep.save(up);
		System.out.println(up + "hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
		return "Saved Successfully";
	}

	@RequestMapping(value = "modifyscreen", method = RequestMethod.POST)

	@ResponseBody
	public String modifyscreens(@RequestParam(required = false) String emp_no,
			@RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date ctc_eff_date, Model md,
			HttpServletRequest rq, @ModelAttribute Salary_Pay_Entity salary_Pay_Entity) {
		String msg = "";
		Salary_Pay_Entity up = salary_Pay_Entity;
		if (Objects.nonNull(up)) {
			up = salary_Pay_Entity;
			up.setDel_flg("N");
			up.setEntity_flg("Y");
			up.setModify_flg("N");
			salary_Pay_Rep.save(up);
			msg = "Modify Successfully";
		} else {
			msg = "Data Not Found";
		}
		return msg;
	}

	@RequestMapping(value = "revisionscreen", method = RequestMethod.POST)

	@ResponseBody
	public String revisionscreen(@RequestParam(required = false) String emp_no, Model md, HttpServletRequest rq,
			@ModelAttribute Salary_Pay_Entity salary_Pay_Entity) {
		String msg = "";
		Salary_Pay_Entity up = salary_Pay_Entity;
		System.out.println("load...");
		if (Objects.nonNull(up)) {
			up.setDel_flg("N");
			up.setEntity_flg("Y");
			up.setModify_flg("N");
			salary_Pay_Rep.save(up);
			msg = "Revised Successfully";
		} else {
			msg = "Data Not Found";
		}
		return msg;
	}

	@RequestMapping(value = "deletescreen", method = RequestMethod.POST)
	@ResponseBody
	public String deletescreen(@RequestParam(required = false) String emp_no,
			@RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date ctc_eff_date) {
		String msg = null;
		try {
			Salary_Pay_Entity vv = salary_Pay_Rep.getaedit1(emp_no, ctc_eff_date);
			vv.setDel_flg("Y");
			salary_Pay_Rep.delete(vv);
			msg = "Deleted Successfully";
		} catch (Exception e) {
			msg = "Delete Unsuccessfull";
		}
		return msg;
	}

	@GetMapping("paystructures")
	public String paystructures(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String emp_no, @RequestParam(required = false) String salaryMonth,
			@RequestParam(required = false) String empname, String keyword, Model md, HttpServletRequest req)

	{
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("salarypay", Paystructurerep.getList());
		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
		} else if (formmode.equals("edit")) {
			md.addAttribute("formmode", "edit");
			// md.addAttribute("salarypay", Paystructurerep.getaedit(emp_no));
			md.addAttribute("salarypay", Paystructurerep.getpaystructureedit(emp_no, salaryMonth, empname));
		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", "view");
			md.addAttribute("salarypay", Paystructurerep.getaedit(emp_no));
		} else if (formmode.equals("verify")) {
			md.addAttribute("formmode", "verify");
			md.addAttribute("salarypay", Paystructurerep.getaedit(emp_no));
		} else if (formmode.equals("enquiry")) {
			md.addAttribute("formmode", "enquiry");
			md.addAttribute("salarypay", Paystructurerep.getpaystructureedit(emp_no, salaryMonth, empname));
		}
		return "paystructure";
	}

	@RequestMapping(value = "AddScreen1", method = RequestMethod.POST)

	@ResponseBody
	public String AddScreen1(Model md, HttpServletRequest rq,

			@ModelAttribute paystructureentity Paystructureentity) {

		paystructureentity up = Paystructureentity;
		up.setDel_flg("N");
		up.setEntity_flg("N");
		up.setModify_flg("N");
		Paystructurerep.save(up);
		return "Saved Successfully";
	}

	/*
	 * @RequestMapping(value = "/paysverifysubmit/{a}", method = {
	 * RequestMethod.POST })
	 * 
	 * @ResponseBody public String paysverifysubmit(Model md, HttpServletRequest
	 * rq, @PathVariable String a) {
	 * 
	 * System.out.println("verify" + a); paystructureentity up =
	 * Paystructurerep.getaedit(a);
	 * 
	 * if (Objects.nonNull(up)) { up.setEntity_flg("Y"); Paystructurerep.save(up);
	 * return "success"; // Update successful } else { return "failure"; // Update
	 * failed } }
	 */

	@RequestMapping(value = "modifyscreen1", method = RequestMethod.POST)

	@ResponseBody
	public String modifyscreens1(@RequestParam(required = false) String emp_no, Model md, HttpServletRequest rq,
			@ModelAttribute paystructureentity paystructureentity) {
		String msg = "";
		paystructureentity up = Paystructurerep.getaedit(emp_no);
		if (Objects.nonNull(up)) {
			up = paystructureentity;
			up.setDel_flg("N");
			up.setEntity_flg("N");
			up.setModify_flg("N");
			Paystructurerep.save(up);
			msg = "Modify Successfully";
		} else {
			msg = "Data Not Found";
		}
		return msg;
	}

	@RequestMapping(value = "deletescreen1", method = RequestMethod.POST)
	@ResponseBody
	public String deletescreen1(@RequestParam(required = false) String emp_no) {
		String msg = null;
		try {
			paystructureentity vv = Paystructurerep.getaedit(emp_no);
			vv.setDel_flg("Y");
			Paystructurerep.save(vv);
			msg = "Deleted Successfully";
		} catch (Exception e) {
			msg = "Delete Unsuccessfull";
		}
		return msg;
	}

	/*
	 * public static byte[] convertFileToByteArray(File file) throws IOException {
	 * FileInputStream fis = new FileInputStream(file); ByteArrayOutputStream bos =
	 * new ByteArrayOutputStream(); byte[] buffer = new byte[1024]; int bytesRead;
	 * while ((bytesRead = fis.read(buffer)) != -1) { bos.write(buffer, 0,
	 * bytesRead); } fis.close(); return bos.toByteArray(); }
	 */

	/*
	 * @RequestMapping(value = "/cvfsubmit/{refno}", method = RequestMethod.POST)
	 * public String cvfsubmit(@RequestParam(required = false) String
	 * ref_no,@PathVariable String refno,
	 * 
	 * @RequestParam(required = false) MultipartFile annexure_resume,
	 * 
	 * @ModelAttribute CandEvalFormEntity candEvalFormEntity) {
	 * 
	 * System.out.println("Ref No:");
	 * 
	 * // Save 'ref_no' to database or perform other operations try {
	 * candEvalFormEntity.setRef_no(refno); System.out.println("Ref No: " +
	 * candEvalFormEntity.getRef_no());
	 * 
	 * 
	 * byte[] fileContent = annexure_resume.getBytes();
	 * 
	 * candEvalFormEntity.setAnnexure_resume(fileContent);
	 * System.out.println("File Content Length: " + fileContent.length);
	 * 
	 * candEvalFormEntity.setVerify_flg("N");
	 * 
	 * // Save 'candEvalFormEntity' to the database
	 * candEvalFormRep.save(candEvalFormEntity);
	 * 
	 * 
	 * return "success"; }catch (Exception e) { e.printStackTrace(); return "error";
	 * } }
	 */

	/*
	 * @RequestMapping(value = "/cvfsubmit/{refno}", method = { RequestMethod.POST
	 * }) public String cvfsubmit(@RequestParam(required = false) String ref_no)
	 * throws SQLException { String msg = null; String lastChars = null;
	 * 
	 * try { System.out.println("Inside getImages"); List<CandEvalFormEntity> vv =
	 * candEvalFormRep.getCVFListS(ref_no);
	 * System.out.println("The encrypted value: " + vv.get(0).getAnnexure_resume());
	 * 
	 * byte[] valueDecoded = Base64.decodeBase64(vv.get(0).getAnnexure_resume());
	 * System.out.println("Decoded value is: " + new String(valueDecoded)); msg =
	 * new String(valueDecoded); } catch (IOException e) { e.printStackTrace(); }
	 * 
	 * if (msg != null && msg.length() >= 22) { lastChars = msg.substring(22); }
	 * else { // Handle case where msg is null or shorter than 22 characters
	 * lastChars = ""; }
	 * 
	 * return lastChars; }
	 */

	// Utility method to convert MultipartFile to File

	/*
	 * public static File convertMultipartFileToFile(MultipartFile file) throws
	 * IOException { File convFile = new File(file.getOriginalFilename());
	 * file.transferTo(convFile); return convFile; }
	 */
	@RequestMapping(value = "/cvfsubmit", method = RequestMethod.POST)
	@ResponseBody
	public String cvfsubmit(@RequestParam(required = false) String ref_no, Model md, HttpServletRequest rq,
			@ModelAttribute CandEvalFormEntity candEvalFormEntity) {

		// System.out.println("hi" + ref_no);
		// System.out.println("hi" + candEvalFormEntity.getCandi_name());

		CandEvalFormEntity up = candEvalFormEntity;

		up.setVerify_flg("N");

		candEvalFormRep.save(up);

		return "success";

	}

	@RequestMapping(value = "/cvfdelete", method = RequestMethod.POST)
	@ResponseBody
	public String cvfdelete(@RequestParam(required = false) String ref_no, Model md, HttpServletRequest rq) {

		CandEvalFormEntity up = candEvalFormRep.getCVFform(ref_no);
		up.setDel_flg("Y");
		candEvalFormRep.save(up);

		return "Deleted successfully";

	}

	byte[] setvalue1;
	byte[] setImgValue1;

	@RequestMapping(value = "cvffileupload", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String cvffileupload(@RequestParam("file") MultipartFile file, CandEvalFormEntity candEvalFormEntity)
			throws IOException {
		// Call service layer to handle file uploads and form data
		// Print the value of 'fileData' to the console

		// System.out.println("Emp Id: " + document_Master_Entity.getEmp_id());

		byte[] byteArray = file.getBytes();
		this.setvalue1 = byteArray;

		return "success"; // Redirect to upload page after upload
	}

	@RequestMapping(value = "cvffileupload1", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String cvffileupload1(CandEvalFormEntity candEvalFormEntity) {
		candEvalFormEntity.setAnnexure_resume(setvalue1);
		candEvalFormEntity.setVerify_flg("N");
		candEvalFormEntity.setDel_flg("N");
		candEvalFormRep.save(candEvalFormEntity);
		return "success"; // Redirect to upload page after upload
	}

	@RequestMapping(value = "/cvfverifysubmit/{b}", method = RequestMethod.POST)
	@ResponseBody
	public String cvfverifysubmit(@RequestParam(required = false) String ref_no, Model md, HttpServletRequest rq,
			@PathVariable String b, @ModelAttribute CandEvalFormEntity candEvalFormEntity) {

		CandEvalFormEntity up = candEvalFormRep.getCVFform(b);

		System.out.println("verify" + up.getRef_no());
		System.out.println("verify" + up.getAnnexure_resume());

		up.setVerify_flg("Y");

		candEvalFormRep.save(up);

		return "Verified Successfully...";

	}

	@RequestMapping(value = "/cvfmodifysubmit1/{b}", method = RequestMethod.POST)
	@ResponseBody
	public String cvfmodifysubmit1(@RequestParam(required = false) String ref_no, Model md, HttpServletRequest rq,
			@PathVariable String b, @ModelAttribute CandEvalFormEntity candEvalFormEntity) {

		candEvalFormEntity.setRef_no(b);

		CandEvalFormEntity up1 = candEvalFormRep.getCVFform(b);
		candEvalFormEntity.setAnnexure_resume(up1.getAnnexure_resume());

		CandEvalFormEntity up = candEvalFormEntity;
		up.getCandi_name();
		System.out.println(up.getCandi_name());
		System.out.println(up.getAnnexure_resume());

		up.setVerify_flg("N");
		up.setDel_flg("N");	
		up.setAnnexure_resume(setvalue1);
		candEvalFormRep.save(up);

		return "Modified Succesfully...";

	}

	@RequestMapping(value = "/cvfmodifysubmit2/{ref_no}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ByteArrayResource> cvfmodifysubmit2(@PathVariable String ref_no) {
		CandEvalFormEntity entity = candEvalFormRep.getCVFform(ref_no);

		if (entity != null && entity.getAnnexure_resume() != null) {
			byte[] blobData = entity.getAnnexure_resume();
			System.out.println(blobData);

			// Set response headers
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("attachment", "annexure_resume.pdf");

			// Create a ByteArrayResource from the blob data
			ByteArrayResource resource = new ByteArrayResource(blobData);

			// Return ResponseEntity with the PDF blob data
			return ResponseEntity.ok().headers(headers).contentLength(blobData.length).body(resource);
		} else {
			// Return appropriate response if the entity or blob data is not found
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(value = "cvf", method = { RequestMethod.GET, RequestMethod.POST })
	public String cvf(@RequestParam(required = false) String formmode, @RequestParam(required = false) String ref_no,
			Model md, HttpServletRequest rq) {

		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("del", candEvalFormRep.getCVFList());
			// System.out.println(candEvalFormRep.getCVFList());
			md.addAttribute("formmode", "list");

		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");

		} else if (formmode.equals("verify")) {

			md.addAttribute("cvfview", candEvalFormRep.getCVFform(ref_no));
			md.addAttribute("formmode", "verify");

		} else if (formmode.equals("view")) {

			md.addAttribute("cvfview", candEvalFormRep.getCVFform(ref_no));
			md.addAttribute("formmode", "view");

		} else if (formmode.equals("modify")) {

			md.addAttribute("cvfview", candEvalFormRep.getCVFform(ref_no));
			md.addAttribute("formmode", "modify");
		}
		return "CandidateEvaluationForm";
	}

	@RequestMapping(value = "cvfdownload", method = RequestMethod.GET)
	@ResponseBody
	public InputStreamResource cvfdownload(HttpServletResponse response, @RequestParam(required = false) String ref_no,
			@RequestParam(required = false) String filetype) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		System.out.println(ref_no);
		try {
			logger.info("Getting download File :" + ref_no);

			File repfile = projectMasterServices.getcvfpdf(ref_no, filetype);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
		} catch (JRException e) {
			// Log the error using the logger
			logger.error("Error generating TDS file", e);
			// Optionally, rethrow the exception or handle it as needed
			// throw new YourCustomException("Error generating TDS file", e);
		}

		return resource;
	}

	/*
	 * @RequestMapping(value = "sendingmail_appointment", method = {
	 * RequestMethod.GET,RequestMethod.POST }) public String sendmails( Model
	 * md)throws SQLException, ParseException { System.out.println("Hi"); String to
	 * = "thanveerahamed.m@bornfire.in"; String from = "barath.p@bornfire.in";
	 * String username = "barath.p@bornfire.in";//change accordingly String password
	 * = "Bornfire@123";//change accordingly String host =
	 * "sg2plzcpnl491716.prod.sin2.secureserver.net"; return
	 * sendingmail_appointment.sendmail(from,username,password,to,host); // return
	 * "Success"; }
	 */

	@RequestMapping(value = "offerLetter", method = { RequestMethod.GET, RequestMethod.POST })
	public String offerLetter(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@ModelAttribute CandEvalFormEntity candEvalFormEntity, @RequestParam(required = false) String a,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		// md.addAttribute("IssueMaster", issueMasterRep.findAllCustom());

		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		
		md.addAttribute("empty", "");
		md.addAttribute("kkkk", a);
		md.addAttribute("cvfverify", candEvalFormRep.getCVFListapp());

		System.out.println(a);
		md.addAttribute("cvfverifys", candEvalFormRep.getCVFforms(a));

		return "OfferLetter";
	}

	@RequestMapping(value = "SalaryRevision", method = { RequestMethod.GET, RequestMethod.POST })
	public String SalaryRevision(@RequestParam(required = false) String userid,
			@RequestParam(required = false) String formmode, Model md, HttpServletRequest req,
			@RequestParam(required = false) String emp_no,
			@RequestParam(required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date ctc_eff_date) {
		// md.addAttribute("IssueMaster", issueMasterRep.findAllCustom());

		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("getct", salary_Pay_Rep.getList());

		} else if (formmode.equals("ctc")) {
			md.addAttribute("formmode", "ctc");
			md.addAttribute("getctc1", salary_Pay_Rep.getaedit1(emp_no, ctc_eff_date));

		}

		return "SalaryRevision";
	}

	@RequestMapping(value = "SalaryRevisiondownload", method = RequestMethod.GET)
	@ResponseBody
	public InputStreamResource SalaryRevisiondownload(HttpServletResponse response,
			@RequestParam(value = "emp_no", required = false) String emp_no,
			@RequestParam(value = "emp_name", required = false) String emp_name,
			@RequestParam(value = "ctc_eff_date", required = false) String ctc_eff_date,
			@RequestParam(value = "filetype", required = false) String filetype) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		System.out.println(emp_no);
		System.out.println(ctc_eff_date);
		try {
			logger.info("Getting download File :" + emp_no + ", FileType :" + filetype + "");

			File repfile = projectMasterServices.getctcpdf(emp_no, ctc_eff_date, filetype,emp_name);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
		} catch (JRException e) {
			// Log the error using the logger
			logger.error("Error generating TDS file", e);
			// Optionally, rethrow the exception or handle it as needed
			// throw new YourCustomException("Error generating TDS file", e);
		}

		return resource;
	}

	byte[] setctc;
	byte[] setctc1;

	@RequestMapping(value = "fileuploadrevision", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String fileuploadrevision(@RequestParam("file") MultipartFile file,
			@RequestParam("file1") MultipartFile file1, @RequestParam(required = false) String emp,
			Salary_Pay_Entity salary_Pay_Entity) throws IOException {

		byte[] byteArray = file.getBytes();
		this.setctc = byteArray;
		byte[] byteArrays = file1.getBytes();
		this.setctc1 = byteArrays;

		return "success";
	}

	@RequestMapping(value = "fileuploadrevisionsave", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String fileuploadrevisionsave(@RequestParam(required = false) String emp_no,
			@RequestParam(required = false) String ctc,

			Salary_Pay_Entity salary_Pay_Entity) throws ParseException {

		Salary_Pay_Entity up = salary_Pay_Rep.savectc(emp_no, ctc);

		up.setStr(setctc);
		up.setRevision(setctc1);

		salary_Pay_Rep.save(up);
		return "success";
	}

	@RequestMapping(value = "sendingmail_coveringletter", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<String> sendingmail_coveringletter(@RequestParam(required = false) String a,
			@RequestParam(required = false) String cc, @RequestParam(required = false) String d,
			@RequestParam(required = false) String ctc, Model md) throws SQLException, ParseException, IOException {
		System.out.println("Hi");
		String b = a;
		String to = b;

		String from = "hr@bornfire.in";
		String username = "hr@bornfire.in"; // change accordingly
		String password = "Managerbfi@123"; // change accordingly
		String host = "sg2plzcpnl491716.prod.sin2.secureserver.net";
		String emp_no = d;

		System.out.println("sdfghjkl;");

		// Call sendMail method with correct parameters
		sendingmail_coveringletter.sendingctcmail(from, host, to, cc, username, password, emp_no, ctc); // pass from,
																										// host,
		// password, and to

		// Return success response
		return ResponseEntity.status(HttpStatus.OK).body("Email sent successfully");
	}

	@RequestMapping(value = "Appointment_Letter", method = { RequestMethod.GET, RequestMethod.POST })
	public String Appointment_Letter(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) String c,
			@RequestParam(required = false) String a, @RequestParam(required = false) String ref_no,
			CandEvalFormEntity candEvalFormEntity,
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		md.addAttribute("menu", "projectmaster"); // To highlight the menu

		if (formmode == null || formmode.equals("list")) {

			System.out.println(a);

			md.addAttribute("cvfverifys", candEvalFormRep.getCVFforms(a));
			System.out.println(candEvalFormRep.getCVFforms(a));
			md.addAttribute("kkkk", a);
			md.addAttribute("cvfverify", candEvalFormRep.getCVFListapp());
			md.addAttribute("empty", "");
			md.addAttribute("formmode", "list"); // to set which form - valid values are "edit" , "add" & "list"

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			// md.addAttribute("formmode", "add"); // to set which form - valid values are
			// "edit" , "add" & "list"

			// md.addAttribute("domains", userProfileDao.getDomainList());
			// md.addAttribute("projectmaster", userProfileDao.getUser(userid));

		} else if (formmode.equals("AppointmentList")) {
			md.addAttribute("del", candEvalFormRep.getCVFList());

			md.addAttribute("formmode", "AppointmentList");

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", "add");

			// md.addAttribute("domains", userProfileDao.getDomainList());
			// md.addAttribute("projectmaster", userProfileDao.getUser(userid));

		} else {

			md.addAttribute("formmode", formmode);
			// md.addAttribute("domains", reportServices.getDomainList());
			// md.addAttribute("FinUserProfiles", userProfileDao.getFinUsersList());
			// md.addAttribute("projectmaster", userProfileDao.getUser(""));

		}

		return "Appointment_Letter";
	}

	@RequestMapping(value = "resourceflag", method = RequestMethod.POST)
	@ResponseBody
	public String resourceflag(@RequestParam String ref_no, Model md, HttpServletRequest rq) {
		String msg = "verified successfully";
		System.out.println("Appointment letter verified flag" + ref_no);

		ResourceMaster appointment = resourceMasterRepo.getverifyappointment(ref_no);
		System.out.println(appointment.getDel_flg() + "disableflag");
		CandEvalFormEntity appointmentcvf = candEvalFormRep.getverifyappointment(ref_no);
		if ("N".equals(appointment.getDel_flg())) {
			appointment.setDel_flg("Y");
			appointmentcvf.setVerify_flg("N");
			resourceMasterRepo.save(appointment);
			candEvalFormRep.save(appointmentcvf);
		} else if ("Y".equals(appointment.getDel_flg())) {
			appointment.setDel_flg("N");
			appointmentcvf.setVerify_flg("Y");
			resourceMasterRepo.save(appointment);
			candEvalFormRep.save(appointmentcvf);
		} else {
			msg = "verify is not successfully handled";
			System.out.println("verify is not successfully handled");
		}

		return msg;

	}

	@RequestMapping(value = "sendingmail_appointment", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<String> sendMails(@RequestParam(required = false) String a,
			@RequestParam(required = false) String cc, @RequestParam(required = false) String d,
			@RequestParam(required = false) List<String> t, Model md) throws SQLException, ParseException, IOException {
		System.out.println("Hi");
		String b = a;
		String to = b;
		String from = "valarmathi.s@bornfire.in";
		String username = "valarmathi.s@bornfire.in"; // change accordingly
		String password = "Bornfire@33"; // change accordingly
		String host = "sg2plzcpnl491716.prod.sin2.secureserver.net";
		String ref_no = d;
		/*
		 * List<String> y = t; for (String bb : y) { System.out.println(bb);
		 * 
		 * }
		 */

		// Call sendMail method with correct parameters
		sendingmail_appointment.sendingmail(from, host, to, cc, username, password, ref_no); // pass from, host,
																								// password, and to

		// Return success response
		return ResponseEntity.status(HttpStatus.OK).body("Email sent successfully");
	}

	@RequestMapping(value = "Appointmentdownload", method = RequestMethod.GET)
	@ResponseBody
	public InputStreamResource Appointmentdownload(HttpServletResponse response,
			@RequestParam(value = "a", required = false) String a,
			@RequestParam(value = "filetype", required = false) String filetype,
			@RequestParam(value = "candi_name", required = false) String candi_name) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		System.out.println(a);
		try {
			logger.info("Getting download File :" + a + ", FileType :" + filetype + "" +candi_name);

			File repfile = projectMasterServices.getTdsExcel(a, filetype ,candi_name);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
		} catch (JRException e) {
			// Log the error using the logger
			logger.error("Error generating TDS file", e);
			// Optionally, rethrow the exception or handle it as needed
			// throw new YourCustomException("Error generating TDS file", e);
		}

		return resource;
	}

	// private static final Logger logger =
	// LoggerFactory.getLogger(NavigationController.class);

	/*
	 * @RequestMapping(value = "/AppointmentdownloadSSSSS", method =
	 * RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	 * 
	 * @ResponseBody public InputStreamResource
	 * invoiceReportDownloadsww(HttpServletResponse response,
	 * 
	 * @RequestParam(value = "a", required = false) String a,
	 * 
	 * @RequestParam(value = "filetype", required = false) String filetype) throws
	 * IOException, SQLException {
	 * 
	 * response.setContentType("application/octet-stream");
	 * 
	 * InputStreamResource resource = null; try {
	 * logger.info("Getting download File :" + a + ", FileType :" + filetype + "");
	 * 
	 * // File repfile = projectMasterServices.getappointment(a, filetype);
	 * 
	 * response.setHeader("Content-Disposition", "attachment; filename=" +
	 * repfile.getName()); resource = new InputStreamResource(new
	 * FileInputStream(repfile)); } catch (FileNotFoundException e) {
	 * logger.error("File not found", e); // Handle the exception appropriately,
	 * such as returning an error response
	 * response.setStatus(HttpServletResponse.SC_NOT_FOUND); // Set HTTP 404 Not
	 * Found status } catch (JRException e) {
	 * logger.error("Error generating appointment file", e); // Handle the exception
	 * appropriately, such as returning an error response
	 * response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Set HTTP
	 * 500 Internal Server Error status }
	 * 
	 * return resource; }
	 */

	@RequestMapping(value = "offerLetterss", method = RequestMethod.GET)
	@ResponseBody
	public InputStreamResource invoiceReportDownloads(HttpServletResponse response,
			@RequestParam(value = "a", required = false) String a,@RequestParam(value = "b", required = false) String b,
			@RequestParam(value = "filetype", required = false) String filetype) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		System.out.println("GGGGGGG" + a);
		try {
			logger.info("Getting download File :" + a +", FileType :" + filetype + "");

			File repfile = projectMasterServices.getofferExcel(a,b,filetype);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
		} catch (JRException e) {
			// Log the error using the logger
			logger.error("Error generating TDS file", e);
			// Optionally, rethrow the exception or handle it as needed
			// throw new YourCustomException("Error generating TDS file", e);
		}

		return resource;
	}

	@RequestMapping(value = "salarystructuredownload", method = RequestMethod.GET)
	@ResponseBody
	public InputStreamResource invoiceReportDownload1(HttpServletResponse response,
			@RequestParam(value = "a", required = false) String a,
			@RequestParam(value = "ctc_eff_date", required = false) String ctc_eff_date,
			@RequestParam(value = "filetype", required = false) String filetype) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		System.out.println(a);
		System.out.println(ctc_eff_date);
		try {
			logger.info("Getting download File :" + a + ", FileType :" + filetype + "");

			File repfile = projectMasterServices.getsalExcel(a, ctc_eff_date, filetype);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
		} catch (JRException e) {
			// Log the error using the logger
			logger.error("Error generating TDS file", e);
			// Optionally, rethrow the exception or handle it as needed
			// throw new YourCustomException("Error generating TDS file", e);
		}

		return resource;
	}

	@RequestMapping(value = "sendingmail_offerletter", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<String> sendMailss(@RequestParam(required = false) String a,
			@RequestParam(required = false) String cc, @RequestParam(required = false) String d,
			@RequestParam(required = false) List<String> t, Model md) throws SQLException, ParseException, IOException {
		System.out.println("Hi");
		String b = a;
		String to = b;

		String from = "valarmathi.s@bornfire.in";
		String username = "valarmathi.s@bornfire.in"; // change accordingly
		String password = "Bornfire@33"; // change accordingly
		String host = "sg2plzcpnl491716.prod.sin2.secureserver.net";
		String ref_no = d;
		/*
		 * List<String> y = t; for (String bb : y) { System.out.println(bb);
		 * 
		 * }
		 */

		System.out.println("sdfghjkl;");

		// Call sendMail method with correct parameters
		sendigmail_offerletter.sendingmailones(from, host, to, cc, username, password, ref_no); // pass from, host,
																								// password, and to

		// Return success response
		return ResponseEntity.status(HttpStatus.OK).body("Email sent successfully");
	}
	@RequestMapping(value = "sendingmail_Releavingletter", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<String> sendingmail_Releavingletter(@RequestParam(required = false) String a,
			@RequestParam(required = false) String cc, @RequestParam(required = false) String d,
			@RequestParam(required = false) List<String> t, Model md) throws SQLException, ParseException, IOException {
		System.out.println("Hi");
		String b = a;
		String to = b;

		String from = "valarmathi.s@bornfire.co.in";
		String username = "hr@bornfire.in"; // change accordingly
		String password = "Managerbfi@123"; // change accordingly
		String host = "sg2plzcpnl491716.prod.sin2.secureserver.net";
		String ref_no = d;
		/*
		 * List<String> y = t; for (String bb : y) { System.out.println(bb);
		 * 
		 * }
		 */

		System.out.println("sdfghjkl;");

		// Call sendMail method with correct parameters
		sendigmail_Rel_letter.sendingmailones(from, host, to, cc, username, password, ref_no); // pass from, host,
																								// password, and to

		// Return success response
		return ResponseEntity.status(HttpStatus.OK).body("Email sent successfully");
	}
	@RequestMapping(value = "/pmaddsubmit", method = { RequestMethod.GET, RequestMethod.POST })

	@ResponseBody
	public String pmaddsubmit(Model md, HttpServletRequest rq, @RequestParam(required = false) String emp_id,
			@RequestParam(required = false) String emp_name, @RequestParam(required = false) String prev_orgn_1,
			@ModelAttribute ProfileManagerEntity1 profileManagerEntity) {

		System.out.println(emp_id);
		System.out.println(emp_name);
		System.out.println(prev_orgn_1);
		System.out.println("before" + profileManagerEntity.getPrev_orgn_1());

		ProfileManagerEntity1 up = profileManagerEntity;

		up.setVerify_flg("N");

		System.out.println("after" + up.getPrev_orgn_1());

		for (int i = 1; i <= 10; i++) {
			String fieldName = "prev_orgn_" + i;
			String fieldValue = null;
			switch (i) {
			case 1:
				fieldValue = profileManagerEntity.getPrev_orgn_1();
				if (fieldValue != null) {
					profileManagerEntity.setPrev_orgn_1(String.join("||", fieldValue.split(",")));
				}
				break;
			case 2:
				fieldValue = profileManagerEntity.getPrev_orgn_2();
				if (fieldValue != null) {
					profileManagerEntity.setPrev_orgn_2(String.join("||", fieldValue.split(",")));
				}
				break;
			case 3:
				fieldValue = profileManagerEntity.getPrev_orgn_3();
				if (fieldValue != null) {
					profileManagerEntity.setPrev_orgn_3(String.join("||", fieldValue.split(",")));
				}
				break;
			case 4:
				fieldValue = profileManagerEntity.getPrev_orgn_4();
				if (fieldValue != null) {
					profileManagerEntity.setPrev_orgn_4(String.join("||", fieldValue.split(",")));
				}
				break;
			case 5:
				fieldValue = profileManagerEntity.getPrev_orgn_5();
				if (fieldValue != null) {
					profileManagerEntity.setPrev_orgn_5(String.join("||", fieldValue.split(",")));
				}
				break;
			case 6:
				fieldValue = profileManagerEntity.getPrev_orgn_6();
				if (fieldValue != null) {
					profileManagerEntity.setPrev_orgn_6(String.join("||", fieldValue.split(",")));
				}
				break;
			case 7:
				fieldValue = profileManagerEntity.getPrev_orgn_7();
				if (fieldValue != null) {
					profileManagerEntity.setPrev_orgn_7(String.join("||", fieldValue.split(",")));
				}
				break;
			case 8:
				fieldValue = profileManagerEntity.getPrev_orgn_8();
				if (fieldValue != null) {
					profileManagerEntity.setPrev_orgn_8(String.join("||", fieldValue.split(",")));
				}
				break;
			case 9:
				fieldValue = profileManagerEntity.getPrev_orgn_9();
				if (fieldValue != null) {
					profileManagerEntity.setPrev_orgn_9(String.join("||", fieldValue.split(",")));
				}
				break;
			case 10:
				fieldValue = profileManagerEntity.getPrev_orgn_2();
				if (fieldValue != null) {
					profileManagerEntity.setPrev_orgn_2(String.join("||", fieldValue.split(",")));
				}
				break;
			}
		}

		for (int i = 1; i <= 20; i++) {
			String fieldName = "Proj_det_" + i;
			String fieldValue = null;
			switch (i) {
			case 1:
				fieldValue = profileManagerEntity.getProj_det_1();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_1(String.join("||", fieldValue.split(",")));
				}
				break;
			case 2:
				fieldValue = profileManagerEntity.getProj_det_2();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_2(String.join("||", fieldValue.split(",")));
				}
				break;
			case 3:
				fieldValue = profileManagerEntity.getProj_det_3();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_3(String.join("||", fieldValue.split(",")));
				}
				break;
			case 4:
				fieldValue = profileManagerEntity.getProj_det_4();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_4(String.join("||", fieldValue.split(",")));
				}
				break;
			case 5:
				fieldValue = profileManagerEntity.getProj_det_5();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_5(String.join("||", fieldValue.split(",")));
				}
				break;
			case 6:
				fieldValue = profileManagerEntity.getProj_det_6();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_6(String.join("||", fieldValue.split(",")));
				}
				break;
			case 7:
				fieldValue = profileManagerEntity.getProj_det_7();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_7(String.join("||", fieldValue.split(",")));
				}
				break;
			case 8:
				fieldValue = profileManagerEntity.getProj_det_8();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_8(String.join("||", fieldValue.split(",")));
				}
				break;
			case 9:
				fieldValue = profileManagerEntity.getProj_det_9();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_9(String.join("||", fieldValue.split(",")));
				}
				break;
			case 10:
				fieldValue = profileManagerEntity.getProj_det_2();

				if (fieldValue != null) {
					profileManagerEntity.setProj_det_2(String.join("||", fieldValue.split(",")));
				}
				break;
			case 11:
				fieldValue = profileManagerEntity.getProj_det_11();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_11(String.join("||", fieldValue.split(",")));
				}
				break;
			case 12:
				fieldValue = profileManagerEntity.getProj_det_12();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_12(String.join("||", fieldValue.split(",")));
				}
				break;
			case 13:
				fieldValue = profileManagerEntity.getProj_det_13();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_13(String.join("||", fieldValue.split(",")));
				}
				break;
			case 14:
				fieldValue = profileManagerEntity.getProj_det_14();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_14(String.join("||", fieldValue.split(",")));
				}
				break;
			case 15:
				fieldValue = profileManagerEntity.getProj_det_15();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_15(String.join("||", fieldValue.split(",")));
				}
				break;
			case 16:
				fieldValue = profileManagerEntity.getProj_det_16();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_16(String.join("||", fieldValue.split(",")));
				}
				break;
			case 17:
				fieldValue = profileManagerEntity.getProj_det_17();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_17(String.join("||", fieldValue.split(",")));
				}
				break;
			case 18:
				fieldValue = profileManagerEntity.getProj_det_18();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_18(String.join("||", fieldValue.split(",")));
				}
				break;
			case 19:
				fieldValue = profileManagerEntity.getProj_det_19();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_19(String.join("||", fieldValue.split(",")));
				}
				break;
			case 20:
				fieldValue = profileManagerEntity.getProj_det_20();
				if (fieldValue != null) {
					profileManagerEntity.setProj_det_20(String.join("||", fieldValue.split(",")));
				}
				break;
			}
		}

		profileManagerRep1.save(up);

		return "success";
	}

	@RequestMapping(value = "/pmversubmit/{emp}", method = { RequestMethod.POST })
	@ResponseBody
	public String pmversubmit(Model md, HttpServletRequest rq, @PathVariable String emp) {

		System.out.println("verify" + emp);
		ProfileManagerEntity1 up = profileManagerRep1.getPMform(emp);

		if (Objects.nonNull(up)) {
			up.setVerify_flg("Y");
			profileManagerRep1.save(up);
			return "success"; // Update successful
		} else {
			return "failure"; // Update failed
		}
	}

	@RequestMapping(value = "/pmdelete", method = RequestMethod.POST)
	@ResponseBody
	public String pmdelete(@RequestParam String emp_id, Model md, HttpServletRequest rq) {

		ProfileManagerEntity1 up = profileManagerRep1.getPMform(emp_id);

		profileManagerRep1.delete(up);

		return "Deleted successfully";

	}

	@RequestMapping(value = "/promanager", method = { RequestMethod.GET, RequestMethod.POST })
	public String promanager(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String emp_id, Model model, HttpServletRequest req) {

		String userId = (String) req.getSession().getAttribute("USERID");
		model.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		model.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {
			model.addAttribute("list", profileManagerRep1.getPMList());
			model.addAttribute("formmode", "list");
		} else if (formmode.equals("add")) {
			model.addAttribute("formmode", "add");
		} else if (formmode.equals("view")) {
			model.addAttribute("pmview", profileManagerRep1.getPMform(emp_id));

			// Process data for the first table with 6 columns
			List<String> pmForms = profileManagerRep1.getPMforms(emp_id);
			ArrayList<String[]> parsedForms = new ArrayList<>();
			for (String a : pmForms) {
				String[] values = a.split("\\|\\|");
				// Iterate over values and split into rows if more than 6 values are found
				for (int i = 0; i < values.length; i += 6) {
					String[] newRow = new String[6]; // Create a new row with 6 columns

					for (int j = 0; j < 6; j++) {
						// Check if index is within bounds
						if (i + j < values.length) {
							newRow[j] = values[i + j] != null ? values[i + j] : ""; // Replace null with empty string

						} else {
							newRow[j] = ""; // Fill with empty string if fewer than 6 values remain
						}
					}
					// Add the new row to parsedForms only if it has at least one non-zero value

					parsedForms.add(newRow);

				}
			}

			// Print parsed data for the first table
			for (String[] row : parsedForms) {
				System.out.println(Arrays.toString(row));
			}

			// Add parsedForms to the model for the first table
			model.addAttribute("pmlist", parsedForms);

			// Process data for the second table with 7 columns
			List<String> pmForms2 = profileManagerRep1.getPMforms2(emp_id);
			ArrayList<String[]> parsedForms2 = new ArrayList<>();
			for (String a : pmForms2) {
				String[] values = a.split("\\|\\|");
				// Iterate over values and split into rows if more than 7 values are found
				for (int i = 0; i < values.length; i += 7) {
					String[] newRow2 = new String[7]; // Create a new row with 7 columns
					for (int j = 0; j < 7; j++) {
						// Check if index is within bounds
						if (i + j < values.length) {
							newRow2[j] = values[i + j]; // Replace null with empty string
						} else {
							newRow2[j] = ""; // Fill with empty string if fewer than 7 values remain
						}
						// Count empty string values as well
						if (newRow2[j].isEmpty()) {
							// You can increment a counter here if you want to count empty values
						}
					}
					// Add the new row to parsedForms2
					parsedForms2.add(newRow2);
				}
			}

			// Print parsed data for the second table
			for (String[] row : parsedForms2) {
				System.out.println(Arrays.toString(row));
			}

			// Add parsedForms2 to the model for the second table
			model.addAttribute("pmlist2", parsedForms2);

			model.addAttribute("formmode", "view");

		} else if (formmode.equals("verify")) {
			model.addAttribute("pmview", profileManagerRep1.getPMform(emp_id));

			// Process data for the first table with 6 columns
			List<String> pmForms = profileManagerRep1.getPMforms(emp_id);
			ArrayList<String[]> parsedForms = new ArrayList<>();
			for (String a : pmForms) {
				String[] values = a.split("\\|\\|");
				// Iterate over values and split into rows if more than 6 values are found
				for (int i = 0; i < values.length; i += 6) {
					String[] newRow = new String[6]; // Create a new row with 6 columns

					for (int j = 0; j < 6; j++) {
						// Check if index is within bounds
						if (i + j < values.length) {
							newRow[j] = values[i + j] != null ? values[i + j] : ""; // Replace null with empty string

						} else {
							newRow[j] = ""; // Fill with empty string if fewer than 6 values remain
						}
					}
					// Add the new row to parsedForms only if it has at least one non-zero value

					parsedForms.add(newRow);

				}
			}

			// Print parsed data for the first table
			for (String[] row : parsedForms) {
				System.out.println(Arrays.toString(row));
			}

			// Add parsedForms to the model for the first table
			model.addAttribute("pmlist", parsedForms);

			// Process data for the second table with 7 columns
			List<String> pmForms2 = profileManagerRep1.getPMforms2(emp_id);
			ArrayList<String[]> parsedForms2 = new ArrayList<>();
			for (String a : pmForms2) {
				String[] values = a.split("\\|\\|");
				// Iterate over values and split into rows if more than 7 values are found
				for (int i = 0; i < values.length; i += 7) {
					String[] newRow2 = new String[7]; // Create a new row with 7 columns
					for (int j = 0; j < 7; j++) {
						// Check if index is within bounds
						if (i + j < values.length) {
							newRow2[j] = values[i + j]; // Replace null with empty string
						} else {
							newRow2[j] = ""; // Fill with empty string if fewer than 7 values remain
						}
						// Count empty string values as well
						if (newRow2[j].isEmpty()) {
							// You can increment a counter here if you want to count empty values
						}
					}
					// Add the new row to parsedForms2
					parsedForms2.add(newRow2);
				}
			}

			// Print parsed data for the second table
			for (String[] row : parsedForms2) {
				System.out.println(Arrays.toString(row));
			}

			// Add parsedForms2 to the model for the second table
			model.addAttribute("pmlist2", parsedForms2);

			model.addAttribute("formmode", "verify");
		}

		else if (formmode.equals("modify")) {
			model.addAttribute("pmview", profileManagerRep1.getPMform(emp_id));

			// Process data for the first table with 6 columns
			List<String> pmForms = profileManagerRep1.getPMforms(emp_id);
			ArrayList<String[]> parsedForms = new ArrayList<>();
			for (String a : pmForms) {
				String[] values = a.split("\\|\\|");
				// Iterate over values and split into rows if more than 6 values are found
				for (int i = 0; i < values.length; i += 6) {
					String[] newRow = new String[6]; // Create a new row with 6 columns

					for (int j = 0; j < 6; j++) {
						// Check if index is within bounds
						if (i + j < values.length) {
							newRow[j] = values[i + j] != null ? values[i + j] : ""; // Replace null with empty string

						} else {
							newRow[j] = ""; // Fill with empty string if fewer than 6 values remain
						}
					}
					// Add the new row to parsedForms only if it has at least one non-zero value

					parsedForms.add(newRow);

				}
			}

			// Print parsed data for the first table
			for (String[] row : parsedForms) {
				System.out.println(Arrays.toString(row));
			}

			// Add parsedForms to the model for the first table
			model.addAttribute("pmlist", parsedForms);

			// Process data for the second table with 7 columns
			List<String> pmForms2 = profileManagerRep1.getPMforms2(emp_id);
			ArrayList<String[]> parsedForms2 = new ArrayList<>();
			for (String a : pmForms2) {
				String[] values = a.split("\\|\\|");
				// Iterate over values and split into rows if more than 7 values are found
				for (int i = 0; i < values.length; i += 7) {
					String[] newRow2 = new String[7]; // Create a new row with 7 columns
					for (int j = 0; j < 7; j++) {
						// Check if index is within bounds
						if (i + j < values.length) {
							newRow2[j] = values[i + j]; // Replace null with empty string
						} else {
							newRow2[j] = ""; // Fill with empty string if fewer than 7 values remain
						}
						// Count empty string values as well
						if (newRow2[j].isEmpty()) {
							// You can increment a counter here if you want to count empty values
						}
					}
					// Add the new row to parsedForms2
					parsedForms2.add(newRow2);
				}
			}

			// Print parsed data for the second table
			for (String[] row : parsedForms2) {
				System.out.println(Arrays.toString(row));
			}

			// Add parsedForms2 to the model for the second table
			model.addAttribute("pmlist2", parsedForms2);

			model.addAttribute("formmode", "modify");
		}

		return "ProfileManager";
	}

	@RequestMapping(value = "assosiateProfile", method = { RequestMethod.GET, RequestMethod.POST })
	public String assosiateProfile(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resource_id, Model md, HttpServletRequest rq,
			@ModelAttribute Assosiate_Profile_Entity assosiate_Profile_Entity) {

		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("AssosiateList", assosiate_Profile_Repo.getAssosiateList());

		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
		} else if (formmode.equals("verify")) {

			md.addAttribute("formmode", "verify");
			md.addAttribute("AssosiateVerify", assosiate_Profile_Repo.getSingleIdData(resource_id));

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("AssosiateVerify", assosiate_Profile_Repo.getSingleIdData(resource_id));

		} else if (formmode.equals("modify")) {
			md.addAttribute("formmode", "modify");
			md.addAttribute("AssosiateVerify", assosiate_Profile_Repo.getSingleIdData(resource_id));
		}

		return "AssosiateProfile";
	}

	@RequestMapping(value = "assosiateAdd", method = RequestMethod.POST)
	@ResponseBody
	public String assosiateAdd(String ref_no, Model md, HttpServletRequest rq,
			@ModelAttribute Assosiate_Profile_Entity assosiate_Profile_Entity) {
		assosiate_Profile_Entity.setEntity_flg("N");
		assosiate_Profile_Repo.save(assosiate_Profile_Entity);
		return "success";

	}

	@RequestMapping(value = "assosiateDelete", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String assosiateDelete(@RequestParam(required = false) String resource_id,
			@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq,
			@ModelAttribute Assosiate_Profile_Entity assosiate_Profile_Entity) {

		assosiate_Profile_Repo.deleteById(resource_id);

		return "deleted";

	}

	@RequestMapping(value = "assosiateVerify", method = RequestMethod.POST)
	@ResponseBody
	public String assosiateVerify(Model md, HttpServletRequest rq,
			@ModelAttribute Assosiate_Profile_Entity assosiate_Profile_Entity) {

		assosiate_Profile_Entity.setEntity_flg("Y");
		assosiate_Profile_Repo.save(assosiate_Profile_Entity);
		return "success";

	}

	@RequestMapping(value = "assosiateModify", method = RequestMethod.POST)
	@ResponseBody
	public String assosiateModify(String ref_no, Model md, HttpServletRequest rq,
			@ModelAttribute Assosiate_Profile_Entity assosiate_Profile_Entity) {
		assosiate_Profile_Entity.setEntity_flg("N");
		assosiate_Profile_Repo.save(assosiate_Profile_Entity);
		return "success";

	}

	@RequestMapping(value = "pay_master", method = { RequestMethod.GET, RequestMethod.POST })
	public String pay_master(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) String record,
			@RequestParam(required = false) String salaryMonth, @RequestParam(required = false) String empname,
			@RequestParam(required = false) String a, @RequestParam(required = false) String salary_month,
			@RequestParam(required = false) String empno, paystructureentity Paystructureentity,

			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		// String loginuserid = (String) req.getSession().getAttribute("USERID");
		// Logging Navigation
		// loginServices.SessionLogging("USERPROFILE", "M2", req.getSession().getId(),
		// loginuserid, req.getRemoteAddr(),
		// "ACTIVE");

		md.addAttribute("menu", "projectmaster"); // To highlight the menu
		System.out.println("modiy pay master" + empno + salaryMonth);

		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		// System.out.println(salary_month);

		if (formmode == null || formmode.equals("list")) {
			System.out.println(Paystructurerep.getpays(salary_month));
			// md.addAttribute("salarypay", Paystructurerep.getpays(record));
			YearMonth currentYearMonth = YearMonth.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM"); // Corrected pattern for month
			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy");
			String formattedMonth = currentYearMonth.format(formatter);
			String formattedYear = currentYearMonth.format(formatter1);
			System.out.println("Current Month and Year: " + formattedYear + formattedMonth);

			// md.addAttribute("salarypay", Paystructurerep.getpay());
			md.addAttribute("salarypay", Paystructurerep.getpayssdemo(formattedYear, formattedMonth));

			md.addAttribute("formmode", "list");

		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
		} else if (formmode.equals("edit")) {
			md.addAttribute("salarypay", Paystructurerep.getaedits(empno, salaryMonth, empname));
			md.addAttribute("formmode", "edit");

		}

		return "pay_master";
	}

	@RequestMapping(value = "permas", method = { RequestMethod.GET, RequestMethod.POST })
	public String permas(@RequestParam(required = false) String formmode, @RequestParam(required = false) String emp_no,
			Model md, HttpServletRequest rq) {

		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("list1", perdiemMasterRep.getPerMasList());
			md.addAttribute("formmode", "list");

		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");

		} else if (formmode.equals("view")) {

			System.out.println(emp_no);
			md.addAttribute("permasview", perdiemMasterRep.getPerMasform(emp_no));

			md.addAttribute("list5", perdiemMasterRep.getPerMasList3(emp_no));

			md.addAttribute("formmode", "view");

		}

		return "PerdiemMaster";
	}

	@RequestMapping(value = "/permassubmit", method = RequestMethod.POST)
	@ResponseBody
	public String permassubmit(@RequestParam(required = false) String emp_no, Model md, HttpServletRequest rq,
			@ModelAttribute PerdiemMasterEntity perdiemMasterEntity) {

		System.out.println("hi" + emp_no);

		PerdiemMasterEntity up = perdiemMasterEntity;

		up.setEntity_flg("N");

		perdiemMasterRep.save(up);

		return "success";

	}

	@RequestMapping(value = "/permasdelete", method = RequestMethod.POST)
	@ResponseBody
	public String permasdelete(@RequestParam(required = false) String emp_no, Model md, HttpServletRequest rq) {

		PerdiemMasterEntity up = perdiemMasterRep.getPerMasform(emp_no);

		perdiemMasterRep.delete(up);

		return "Deleted successfully";

	}

	@RequestMapping(value = "batchJob", method = { RequestMethod.GET, RequestMethod.POST })
	public String batchJob(@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq) {

		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if ("BFI0183".equals(userId) || "BFI0140".equals(userId)) {
			return "redirect:/paySlipGeneration";
		} else {
			return "BatchJob";
		}
	}

	@RequestMapping(value = "monthlySalaryWork", method = { RequestMethod.GET, RequestMethod.POST })
	public String monthlySalaryWork(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) String record,

			@RequestParam(required = false) String a, @RequestParam(required = false) String uniqueid,
			@RequestParam(required = false) String ref_no, @RequestParam(required = false) String emp_no,

			CandEvalFormEntity candEvalFormEntity,
			@RequestParam(value = "page", required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {

		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		md.addAttribute("menu", "projectmaster"); // To highlight the menu

		System.out.println(record);
		if (formmode == null || formmode.equals("add")) {
			// System.out.println(Paystructurerep.getpays(record));
			// md.addAttribute("salarypay",
			// Paystructurerep.getpays(record));assosiate_Profile_Repo
			// md.addAttribute("salarypay", Baj_Work_Repo.getpays(record));
			// md.addAttribute("salarypay", Paystructurerep.getpay());

			md.addAttribute("formmode", "add");
			;
		} else if (formmode.equals("list1")) {
			// System.out.println(Paystructurerep.getpays(record));
			md.addAttribute("salarypay", Baj_Work_Repo.getpays(record));
			System.out.println(Baj_Work_Repo.getpays(record));
			// md.addAttribute("salarypay", Paystructurerep.getpay());

			md.addAttribute("formmode", "list1");
		} else if (formmode.equals("edit")) {
			md.addAttribute("salarypay", Baj_Work_Repo.getlisttab1(uniqueid));
			md.addAttribute("formmode", "edit");

		} else if (formmode.equals("add1")) {
			md.addAttribute("formmode", "add1");

		} else if (formmode.equals("add2")) {
			md.addAttribute("formmode", "add2");

		} else if (formmode.equals("view")) {
			md.addAttribute("salarypay", Baj_Work_Repo.getlisttab1(uniqueid));
			md.addAttribute("formmode", "view");

		} else if (formmode.equals("verify")) {
			md.addAttribute("salarypay", Baj_Work_Repo.getlisttab1(uniqueid));
			md.addAttribute("formmode", "verify");

		}

		return "monthlySalaryWork";
	}

	@RequestMapping(value = "/monthlyversubmit/{uni}", method = { RequestMethod.POST })
	@ResponseBody
	public String monthlyversubmit(Model md, HttpServletRequest rq, @PathVariable String uni) {

		System.out.println("verify" + uni);
		Baj_Sal_Work_Entity up = Baj_Work_Repo.getlisttab1(uni);

		if (Objects.nonNull(up)) {
			up.setEntity_flg("Y");
			Baj_Work_Repo.save(up);
			return "success"; // Update successful
		} else {
			return "failure"; // Update failed
		}
	}
	/*
	 * @RequestMapping(value = "monthlySalaryWork", method = { RequestMethod.GET,
	 * RequestMethod.POST }) public String monthlySalaryWork(@RequestParam(required
	 * = false) String formmode, Model md, HttpServletRequest rq) {
	 * 
	 * return "MonthlySalaryWork"; }
	 */

	@RequestMapping(value = "monthlySalaryGenerator", method = { RequestMethod.GET, RequestMethod.POST })
	public String monthlySalaryGenerator(@RequestParam(required = false) String formmode, Model md,
			HttpServletRequest rq) {

		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		return "MonthlySalaryGenerator";
	}

	@SuppressWarnings("null")
	@RequestMapping(value = "salaryTransactionCreation", method = { RequestMethod.GET, RequestMethod.POST })
	public String salaryTransactionCreation(@RequestParam(required = false) String formmode, Model md,
			HttpServletRequest rq, paystructureentity Paystructureentity, @RequestParam(required = false) String sal) {

		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("add")) {
			// Fetch data from the database
			List<paystructureentity> basicPayEntities = Paystructurerep.getstc(sal);

			BigDecimal Basic = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getBasic_pay();
				if (basicPay != null) {
					Basic = Basic.add(basicPay);

				} else {

					Basic = Basic.add(BigDecimal.ZERO);

				}
			}

			BigDecimal hra = BigDecimal.ZERO;

			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getHra();
				if (basicPay != null) {
					hra = hra.add(basicPay);
				} else {

					hra = hra.add(BigDecimal.ZERO);
				}
			}

			BigDecimal spl = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {

				BigDecimal basicPay = entity.getSpl_allow();
				if (basicPay != null) {
					spl = spl.add(basicPay);
				} else {

					spl = spl.add(BigDecimal.ZERO);

				}
			}

			BigDecimal med = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getMedi_reimb();
				if (basicPay != null) {
					med = med.add(basicPay);
				} else {

					med = med.add(BigDecimal.ZERO);

				}
			}

			BigDecimal convey = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getConv_allow();
				if (basicPay != null) {
					convey = convey.add(basicPay);

				} else {
					convey = convey.add(BigDecimal.ZERO);
				}
			}

			BigDecimal lunch = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getLunch_allow();
				if (basicPay != null) {

					lunch = lunch.add(basicPay);

				} else {

					lunch = lunch.add(BigDecimal.ZERO);

				}
			}

			BigDecimal educ = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getEdu_allow();
				if (basicPay != null) {

					educ = educ.add(basicPay);

				} else {

					educ = educ.add(BigDecimal.ZERO);

				}
			}

			BigDecimal attire = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getBuss_attire();
				if (basicPay != null) {

					attire = attire.add(basicPay);

				} else {

					attire = attire.add(BigDecimal.ZERO);

				}
			}

			BigDecimal car = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getCar_maint();
				if (basicPay != null) {

					car = car.add(basicPay);

				} else {

					car = car.add(BigDecimal.ZERO);

				}
			}

			BigDecimal leave = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getLeave_travel_allow();
				if (basicPay != null) {

					leave = leave.add(basicPay);

				} else {

					leave = leave.add(BigDecimal.ZERO);

				}
			}

			BigDecimal outsta = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getOutstn_allow();
				if (basicPay != null) {

					outsta = outsta.add(basicPay);

				} else {

					outsta = outsta.add(BigDecimal.ZERO);

				}
			}

			BigDecimal annual = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getAnnual_loyal_bonus();
				if (basicPay != null) {

					annual = annual.add(basicPay);

				} else {

					annual = annual.add(BigDecimal.ZERO);

				}
			}

			BigDecimal other = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getOtr_allow();
				if (basicPay != null) {

					other = other.add(basicPay);

				} else {

					other = other.add(BigDecimal.ZERO);

				}
			}

			BigDecimal gross = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getGross_salary();
				if (basicPay != null) {

					gross = gross.add(basicPay);

				} else {

					gross = gross.add(BigDecimal.ZERO);

				}
			}

			BigDecimal staff = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getSpf();
				if (basicPay != null) {

					staff = staff.add(basicPay);

				} else {

					staff = staff.add(BigDecimal.ZERO);

				}
			}

			BigDecimal inc = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getTds();
				if (basicPay != null) {

					inc = inc.add(basicPay);

				} else {

					inc = inc.add(BigDecimal.ZERO);

				}
			}

			BigDecimal proff = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getProf_tax();
				if (basicPay != null) {

					proff = proff.add(basicPay);

				} else {

					proff = proff.add(BigDecimal.ZERO);

				}
			}

			BigDecimal emplo = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getEsi();
				if (basicPay != null) {

					emplo = emplo.add(basicPay);

				} else {

					emplo = emplo.add(BigDecimal.ZERO);

				}
			}

			BigDecimal recov = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getRecovery();
				if (basicPay != null) {

					recov = recov.add(basicPay);

				} else {

					recov = recov.add(BigDecimal.ZERO);

				}
			}

			BigDecimal otrded = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getOtr_ded();
				if (basicPay != null) {

					otrded = otrded.add(basicPay);

				} else {

					otrded = otrded.add(BigDecimal.ZERO);

				}
			}

			BigDecimal totded = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getTotal_deductions();
				if (basicPay != null) {

					totded = totded.add(basicPay);

				} else {

					totded = totded.add(BigDecimal.ZERO);

				}
			}

			BigDecimal nets = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getNet_salary();
				if (basicPay != null) {

					nets = nets.add(basicPay);

				} else {

					nets = nets.add(BigDecimal.ZERO);

				}
			}

			// System.out.println(Basic);
			md.addAttribute("stc", Basic);
			md.addAttribute("hr", hra);
			md.addAttribute("sp", spl);
			md.addAttribute("me", med);
			md.addAttribute("con", convey);
			md.addAttribute("lun", lunch);
			md.addAttribute("edu", educ);
			md.addAttribute("att", attire);
			md.addAttribute("ca", car);
			md.addAttribute("lea", leave);
			md.addAttribute("out", outsta);
			md.addAttribute("ann", annual);
			md.addAttribute("otr", other);
			md.addAttribute("gro", gross);
			md.addAttribute("sta", staff);
			md.addAttribute("in", inc);
			md.addAttribute("pro", proff);
			md.addAttribute("emp", emplo);
			md.addAttribute("rec", recov);
			md.addAttribute("otrd", otrded);
			md.addAttribute("totd", totded);
			md.addAttribute("net", nets);

			md.addAttribute("formmode", "add");
		} else if (formmode.equals("journal")) {
			md.addAttribute("formmode", "journal");

		} else if (formmode == null || formmode.equals("add1")) {
			// Fetch data from the database
			List<paystructureentity> basicPayEntities = Paystructurerep.getstc(sal);

			BigDecimal Basic = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getBasic_pay();
				if (basicPay != null) {
					Basic = Basic.add(basicPay);

				} else {

					Basic = Basic.add(BigDecimal.ZERO);

				}
			}

			BigDecimal hra = BigDecimal.ZERO;

			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getHra();
				if (basicPay != null) {
					hra = hra.add(basicPay);
				} else {

					hra = hra.add(BigDecimal.ZERO);
				}
			}

			BigDecimal spl = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {

				BigDecimal basicPay = entity.getSpl_allow();
				if (basicPay != null) {
					spl = spl.add(basicPay);
				} else {

					spl = spl.add(BigDecimal.ZERO);

				}
			}

			BigDecimal med = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getMedi_reimb();
				if (basicPay != null) {
					med = med.add(basicPay);
				} else {

					med = med.add(BigDecimal.ZERO);

				}
			}

			BigDecimal convey = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getConv_allow();
				if (basicPay != null) {
					convey = convey.add(basicPay);

				} else {
					convey = convey.add(BigDecimal.ZERO);
				}
			}

			BigDecimal lunch = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getLunch_allow();
				if (basicPay != null) {

					lunch = lunch.add(basicPay);

				} else {

					lunch = lunch.add(BigDecimal.ZERO);

				}
			}

			BigDecimal educ = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getEdu_allow();
				if (basicPay != null) {

					educ = educ.add(basicPay);

				} else {

					educ = educ.add(BigDecimal.ZERO);

				}
			}

			BigDecimal attire = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getBuss_attire();
				if (basicPay != null) {

					attire = attire.add(basicPay);

				} else {

					attire = attire.add(BigDecimal.ZERO);

				}
			}

			BigDecimal car = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getCar_maint();
				if (basicPay != null) {

					car = car.add(basicPay);

				} else {

					car = car.add(BigDecimal.ZERO);

				}
			}

			BigDecimal leave = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getLeave_travel_allow();
				if (basicPay != null) {

					leave = leave.add(basicPay);

				} else {

					leave = leave.add(BigDecimal.ZERO);

				}
			}

			BigDecimal outsta = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getOutstn_allow();
				if (basicPay != null) {

					outsta = outsta.add(basicPay);

				} else {

					outsta = outsta.add(BigDecimal.ZERO);

				}
			}

			BigDecimal annual = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getAnnual_loyal_bonus();
				if (basicPay != null) {

					annual = annual.add(basicPay);

				} else {

					annual = annual.add(BigDecimal.ZERO);

				}
			}

			BigDecimal other = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getOtr_allow();
				if (basicPay != null) {

					other = other.add(basicPay);

				} else {

					other = other.add(BigDecimal.ZERO);

				}
			}

			BigDecimal gross = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getGross_salary();
				if (basicPay != null) {

					gross = gross.add(basicPay);

				} else {

					gross = gross.add(BigDecimal.ZERO);

				}
			}

			BigDecimal staff = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getSpf();
				if (basicPay != null) {

					staff = staff.add(basicPay);

				} else {

					staff = staff.add(BigDecimal.ZERO);

				}
			}

			BigDecimal inc = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getTds();
				if (basicPay != null) {

					inc = inc.add(basicPay);

				} else {

					inc = inc.add(BigDecimal.ZERO);

				}
			}

			BigDecimal proff = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getProf_tax();
				if (basicPay != null) {

					proff = proff.add(basicPay);

				} else {

					proff = proff.add(BigDecimal.ZERO);

				}
			}

			BigDecimal emplo = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getEsi();
				if (basicPay != null) {

					emplo = emplo.add(basicPay);

				} else {

					emplo = emplo.add(BigDecimal.ZERO);

				}
			}

			BigDecimal recov = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getRecovery();
				if (basicPay != null) {

					recov = recov.add(basicPay);

				} else {

					recov = recov.add(BigDecimal.ZERO);

				}
			}

			BigDecimal otrded = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getOtr_ded();
				if (basicPay != null) {

					otrded = otrded.add(basicPay);

				} else {

					otrded = otrded.add(BigDecimal.ZERO);

				}
			}

			BigDecimal totded = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getTotal_deductions();
				if (basicPay != null) {

					totded = totded.add(basicPay);

				} else {

					totded = totded.add(BigDecimal.ZERO);

				}
			}

			BigDecimal nets = BigDecimal.ZERO;
			for (paystructureentity entity : basicPayEntities) {
				BigDecimal basicPay = entity.getNet_salary();
				if (basicPay != null) {

					nets = nets.add(basicPay);

				} else {

					nets = nets.add(BigDecimal.ZERO);

				}
			}

			// System.out.println(Basic);
			md.addAttribute("stc", Basic);
			md.addAttribute("hr", hra);
			md.addAttribute("sp", spl);
			md.addAttribute("me", med);
			md.addAttribute("con", convey);
			md.addAttribute("lun", lunch);
			md.addAttribute("edu", educ);
			md.addAttribute("att", attire);
			md.addAttribute("ca", car);
			md.addAttribute("lea", leave);
			md.addAttribute("out", outsta);
			md.addAttribute("ann", annual);
			md.addAttribute("otr", other);
			md.addAttribute("gro", gross);
			md.addAttribute("sta", staff);
			md.addAttribute("in", inc);
			md.addAttribute("pro", proff);
			md.addAttribute("emp", emplo);
			md.addAttribute("rec", recov);
			md.addAttribute("otrd", otrded);
			md.addAttribute("totd", totded);
			md.addAttribute("net", nets);

			md.addAttribute("formmode", "add1");
		}

		return "SalaryTransactionCreation";
	}

	@RequestMapping(value = "salaryPaymentTransaction", method = { RequestMethod.GET, RequestMethod.POST })
	public String salaryPaymentTransaction(@RequestParam(required = false) String formmode, Model md,
			HttpServletRequest rq) {

		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		return "SalaryPaymentTransaction";
	}

	@RequestMapping(value = "bankFileDownload", method = { RequestMethod.GET, RequestMethod.POST })
	public String bankFileDownload(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String a, Model md, HttpServletRequest rq) {
		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		System.out.println("bankFileDownload" + a);
		if (formmode == null || formmode.equals("list")) {
			// System.out.println(Paystructurerep.getpays(record));
			// md.addAttribute("salarypay",
			// Paystructurerep.getpays(record));assosiate_Profile_Repo
			// md.addAttribute("salarypay", Baj_Work_Repo.getpays(record));
			// md.addAttribute("salarypay", Paystructurerep.getpay());

			md.addAttribute("formmode", "list");
			;
		} else if (formmode.equals("add")) {

			Date currentDate = new Date();

			// Create a date format
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

			// Format the current date
			String formattedDate = dateFormat.format(currentDate);

			// Print the formatted date
			System.out.println("Current Date: " + formattedDate);
			md.addAttribute("formattedDate", formattedDate);
			md.addAttribute("salarypay", Paystructurerep.bankjobicici(a));
			// md.addAttribute("ifsccode", Paystructurerep.bjicicinotpresent(a));

			System.out.println(Paystructurerep.bankjobicici(a));
			// System.out.println(Baj_Work_Repo.getpays(record));
			// md.addAttribute("salarypay", Paystructurerep.getpay());

			md.addAttribute("formmode", "add");
		}
		return "BankFileDownload";
	}

	@RequestMapping(value = "paySlipGeneration", method = { RequestMethod.GET, RequestMethod.POST })
	public String paySlipGeneration(@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq) {

		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("cvfverify", Paystructurerep.getpaystruce());

		return "PaySlipGeneration";
	}

	@RequestMapping(value = "perdiemGeneration", method = { RequestMethod.GET, RequestMethod.POST })
	public String perdiemGeneration(@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq) {

		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("cvfverify", perdiemMasterRep.getperdium());
		return "PerdiemGeneration";
	}

	@RequestMapping(value = "viewtotds1", method = RequestMethod.POST)
	@ResponseBody
	public String viewtotds1(@RequestParam(required = false) String formmode,

			Model md, HttpServletRequest rq, @RequestParam(required = false) String b,
			@RequestParam(required = false) String a, @RequestParam(required = false) String t) throws ParseException {

		String userId = (String) rq.getSession().getAttribute("USERID");
		// md.addAttribute("RoleMenu", Baj_Work_Repo.getpays(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = dateFormat.parse(t);
		System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}" + a);
		System.out.println("]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]" + t);

		// String h = a.substring(0, a.length() - 1);
		// System.out.println(h); // Output: 202

		int lastDigit = Character.getNumericValue(a.charAt(a.length() - 1));
		System.out.println(lastDigit);// Get the last digit as an integer
		lastDigit--; // Increment the last digit
		String h = a.substring(0, a.length() - 1) + lastDigit;

		// Remove the last character and append the incremented digit
		System.out.println("sdfghjkl" + h); // Output: 2024

		String inputYearString = a.substring(0, 4);
		String inputMonthString = a.substring(4);
		System.out.println("sdfghjhgfdsdfghj8888888888" + inputMonthString);
		String YearL = null;

		YearMonth currentYearMonth = YearMonth.now();
		// LocalDate endOfMonth = currentYearMonth.atEndOfMonth();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM");
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy");
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd");

		/*
		 * int n = Integer.parseInt(a); System.out.println("dfdfdfdfdf"+n);
		 * 
		 * 
		 * int month = n % 100;
		 * 
		 * 
		 * YearMonth yearMonth = YearMonth.of(n / 100, month); int lastDayOfMonth =
		 * yearMonth.lengthOfMonth();
		 * 
		 * 
		 * int lastDay = lastDayOfMonth;
		 * 
		 * 
		 * System.out.println("Last day of the month: " + lastDay);
		 */

		int n = Integer.parseInt(a);
		System.out.println("dfdfdfdfdf" + n);

		int month = n % 100;

		// Determine the last day of the month
		YearMonth yearMonth = YearMonth.of(n / 100, month);
		int lastDayOfMonth = yearMonth.lengthOfMonth();

		// Convert lastDayOfMonth to BigDecimal
		BigDecimal lastDay = BigDecimal.valueOf(lastDayOfMonth);
		System.out.println("Last day of the month: " + lastDay);

		// System.out.println(formatter2);
		String formattedMonth = currentYearMonth.format(formatter);
		String formattedYear = currentYearMonth.format(formatter1);
		// String formattedDate = endOfMonth.format(dateFormatter);
		System.out.println(formattedMonth);
		System.out.println(formattedYear);
		String p = formattedYear + formattedMonth;

		System.out.println(a + p + "lkkkkkk");
		// System.out.println("End of the Month Date: " + formattedDate);

		if (a.equals(p)) {

			try {
				List<paystructureentity> up2 = Paystructurerep.getpays(h);
				// List<paystructureentity> salary=Paystructurerep.getpaysnewss(h);
				List<Baj_Sal_Work_Entity> up3 = new ArrayList<>();

				for (paystructureentity salpay : up2) {
					// SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Baj_Sal_Work_Entity salwork = new Baj_Sal_Work_Entity();
					salwork.setEmp_no(salpay.getEmp_no());
					salwork.setEmp_name(salpay.getEmp_name());
					salwork.setEmp_desig(salpay.getEmp_desig());
					salwork.setEmp_group(salpay.getEmp_group());
					salwork.setEmp_division(salpay.getEmp_division());

					salwork.setDate_of_birth(salpay.getDate_of_birth());
					salwork.setDate_of_joining(salpay.getDate_of_joining());

					salwork.setSpf_acct_no(salpay.getSpf_acct_no());
					salwork.setUrn_no(salpay.getUrn_no());
					salwork.setRecord_date(salpay.getRecord_date());
					salwork.setSalary_month(a); // First setting
					// salwork.setSalary_month(salpay.getSalary_month()); // Second setting

					// salwork.setNo_of_days(salpay.getNo_of_days());
					salwork.setNo_of_days(lastDay);
					// System.out.println("dfghjklpppppppppppppppppp"+lastDay);
					// salwork.setDays_paid(salpay.getDays_paid());
					salwork.setDays_paid(lastDay);
					// System.out.println("dfghjklpppppppppppppppppp"+lastDay);
					salwork.setLoss_of_pay(salpay.getLoss_of_pay());
					salwork.setPayment_mode(salpay.getPayment_mode());
					salwork.setBank_name(salpay.getBank_name());
					salwork.setBank_acct_no(salpay.getBank_acct_no());
					salwork.setBasic_pay(salpay.getBasic_pay());
					salwork.setHra(salpay.getHra());
					salwork.setSpl_allow(salpay.getSpl_allow());
					salwork.setMedi_reimb(salpay.getMedi_reimb());

					salwork.setConv_allow(salpay.getConv_allow());
					salwork.setLunch_allow(salpay.getLunch_allow());
					salwork.setEdu_allow(salpay.getEdu_allow());
					salwork.setBuss_attire(salpay.getBuss_attire());
					salwork.setCar_maint(salpay.getCar_maint());
					salwork.setLeave_travel_allow(salpay.getLeave_travel_allow());
					salwork.setOutstn_allow(salpay.getOutstn_allow());
					salwork.setAnnual_loyal_bonus(salpay.getAnnual_loyal_bonus());

					salwork.setOtr_allow(salpay.getOtr_allow());
					salwork.setGross_salary(salpay.getGross_salary());
					salwork.setSpf(salpay.getSpf());
					salwork.setTds(salpay.getTds());
					salwork.setProf_tax(salpay.getProf_tax());
					salwork.setEsi(salpay.getEsi());
					salwork.setRecovery(salpay.getRecovery());
					salwork.setOtr_ded(salpay.getOtr_ded());
					salwork.setTotal_deductions(salpay.getTotal_deductions());
					salwork.setNet_salary(salpay.getNet_salary());
					salwork.setDate_of_pay(salpay.getDate_of_pay());
					salwork.setCum_tds_fy(salpay.getCum_tds_fy());
					salwork.setProv_it(salpay.getProv_it());
					salwork.setTax_due(salpay.getTax_due());
					salwork.setTax_per_month(salpay.getTax_per_month());
					salwork.setCtc_amt(salpay.getCtc_amt());
					salwork.setDecl_status(salpay.getDecl_status());
					salwork.setCtc_eff_date(salpay.getCtc_eff_date());
					salwork.setCtc_end_date(salpay.getCtc_end_date());
					salwork.setMobile_no(salpay.getMobile_no());
					salwork.setEmail_id(salpay.getEmail_id());
					salwork.setRecord_type(salpay.getRecord_type());
					salwork.setRecord_srl_no(salpay.getRecord_srl_no());
					salwork.setRecord_date(salpay.getRecord_date());
					salwork.setRecord_date(date);
					// String d[]=(salpay.getRecord_date().toString()).split(" ");
					// salwork.setRecord_date(d[0]);
					salwork.setPay_status(salpay.getPay_status());
					salwork.setEntity_flg(salpay.getEntity_flg());
					salwork.setDel_flg(salpay.getDel_flg());
					salwork.setEntry_time(salpay.getEntry_time());
					salwork.setEntry_user(salpay.getEntry_user());
					salwork.setModify_flg(salpay.getModify_flg());
					salwork.setModify_time(salpay.getModify_time());
					salwork.setModify_user(salpay.getModify_user());
					salwork.setVerify_time(salpay.getVerify_time());
					salwork.setVerify_user(salpay.getVerify_user());
					salwork.setRemarks(salpay.getRemarks());
					salwork.setAadhar_no(salpay.getAadhar_no());
					salwork.setUniqueid(salwork.getEmp_no() + (salwork.getSalary_month()));
					// System.out.println(salwork.getEmp_no() + (salwork.getSalary_month()));

					up3.add(salwork);

					// up3.add(gstoverseas);
				}

				// System.out.println(up3+"mjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
				//
				Baj_Work_Repo.saveAll(up3);
				// System.out.println("sdfghjkl"+salwork.getUniqueid());

				// gstBtmRep.getInsert(b,a);

				// System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}" + a + b);
				// int uniqueIdCounter = Integer.parseInt(b);
				// int uniqueIdCounter1 = Integer.parseInt(a)salpay

				// System.out.println(gstRep.getInsert(a,b));
				String msg = "Data Saved Successfully";
				return msg;

			} catch (Exception e) {
				e.printStackTrace();
				return "Error: " + e.getMessage();
			}

		}

		else {

			try {
				List<paystructureentity> up2 = Paystructurerep.getpays(a);
				// List<paystructureentity> salary=Paystructurerep.getpaysnewss(h);
				List<Baj_Sal_Work_Entity> up3 = new ArrayList<>();

				for (paystructureentity salpay : up2) {
					// SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
					Baj_Sal_Work_Entity salwork = new Baj_Sal_Work_Entity();
					salwork.setEmp_no(salpay.getEmp_no());
					salwork.setEmp_name(salpay.getEmp_name());
					salwork.setEmp_desig(salpay.getEmp_desig());
					salwork.setEmp_group(salpay.getEmp_group());
					salwork.setEmp_division(salpay.getEmp_division());

					salwork.setDate_of_birth(salpay.getDate_of_birth());
					salwork.setDate_of_joining(salpay.getDate_of_joining());

					salwork.setSpf_acct_no(salpay.getSpf_acct_no());
					salwork.setUrn_no(salpay.getUrn_no());
					salwork.setRecord_date(salpay.getRecord_date());
					salwork.setSalary_month(a); // First setting
					// salwork.setSalary_month(salpay.getSalary_month()); // Second setting

					// salwork.setNo_of_days(salpay.getNo_of_days());
					salwork.setNo_of_days(lastDay);
					// System.out.println("dfghjklpppppppppppppppppp"+lastDay);
					// salwork.setDays_paid(salpay.getDays_paid());
					salwork.setDays_paid(lastDay);
					// System.out.println("dfghjklpppppppppppppppppp"+lastDay);
					salwork.setLoss_of_pay(salpay.getLoss_of_pay());
					salwork.setPayment_mode(salpay.getPayment_mode());
					salwork.setBank_name(salpay.getBank_name());
					salwork.setBank_acct_no(salpay.getBank_acct_no());
					salwork.setBasic_pay(salpay.getBasic_pay());
					salwork.setHra(salpay.getHra());
					salwork.setSpl_allow(salpay.getSpl_allow());
					salwork.setMedi_reimb(salpay.getMedi_reimb());

					salwork.setConv_allow(salpay.getConv_allow());
					salwork.setLunch_allow(salpay.getLunch_allow());
					salwork.setEdu_allow(salpay.getEdu_allow());
					salwork.setBuss_attire(salpay.getBuss_attire());
					salwork.setCar_maint(salpay.getCar_maint());
					salwork.setLeave_travel_allow(salpay.getLeave_travel_allow());
					salwork.setOutstn_allow(salpay.getOutstn_allow());
					salwork.setAnnual_loyal_bonus(salpay.getAnnual_loyal_bonus());

					salwork.setOtr_allow(salpay.getOtr_allow());
					salwork.setGross_salary(salpay.getGross_salary());
					salwork.setSpf(salpay.getSpf());
					salwork.setTds(salpay.getTds());
					salwork.setProf_tax(salpay.getProf_tax());
					salwork.setEsi(salpay.getEsi());
					salwork.setRecovery(salpay.getRecovery());
					salwork.setOtr_ded(salpay.getOtr_ded());
					salwork.setTotal_deductions(salpay.getTotal_deductions());
					salwork.setNet_salary(salpay.getNet_salary());
					salwork.setDate_of_pay(salpay.getDate_of_pay());
					salwork.setCum_tds_fy(salpay.getCum_tds_fy());
					salwork.setProv_it(salpay.getProv_it());
					salwork.setTax_due(salpay.getTax_due());
					salwork.setTax_per_month(salpay.getTax_per_month());
					salwork.setCtc_amt(salpay.getCtc_amt());
					salwork.setDecl_status(salpay.getDecl_status());
					salwork.setCtc_eff_date(salpay.getCtc_eff_date());
					salwork.setCtc_end_date(salpay.getCtc_end_date());
					salwork.setMobile_no(salpay.getMobile_no());
					salwork.setEmail_id(salpay.getEmail_id());
					salwork.setRecord_type(salpay.getRecord_type());
					salwork.setRecord_srl_no(salpay.getRecord_srl_no());
					salwork.setRecord_date(date);
					salwork.setPay_status(salpay.getPay_status());
					salwork.setEntity_flg(salpay.getEntity_flg());
					salwork.setDel_flg(salpay.getDel_flg());
					salwork.setEntry_time(salpay.getEntry_time());
					salwork.setEntry_user(salpay.getEntry_user());
					salwork.setModify_flg(salpay.getModify_flg());
					salwork.setModify_time(salpay.getModify_time());
					salwork.setModify_user(salpay.getModify_user());
					salwork.setVerify_time(salpay.getVerify_time());
					salwork.setVerify_user(salpay.getVerify_user());
					salwork.setRemarks(salpay.getRemarks());
					salwork.setAadhar_no(salpay.getAadhar_no());
					salwork.setUniqueid(salwork.getEmp_no() + (salwork.getSalary_month()));
					// System.out.println(salwork.getEmp_no() + (salwork.getSalary_month()));

					up3.add(salwork);

					// up3.add(gstoverseas);
				}

				System.out.println(up3);
				//
				Baj_Work_Repo.saveAll(up3);
				// System.out.println("sdfghjkl"+salwork.getUniqueid());

				// gstBtmRep.getInsert(b,a);

				// System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}" + a + b);
				// int uniqueIdCounter = Integer.parseInt(b);
				// int uniqueIdCounter1 = Integer.parseInt(a)salpay

				// System.out.println(gstRep.getInsert(a,b));
				String msg = "Data Saved Successfully";
				return msg;

			} catch (Exception e) {
				e.printStackTrace();
				return "Error: " + e.getMessage();
			}

		}
	}

	/*
	 * private BigDecimal parseDate(String t) { // TODO Auto-generated method stub
	 * return null; }
	 */

	@RequestMapping(value = "submitaddbaj", method = RequestMethod.POST)
	@ResponseBody
	public String submitaddbaj(Model md, HttpServletRequest rq, @ModelAttribute Baj_Sal_Work_Entity Baj_Sal_Work_Entity,
			String emp_name) {

		System.out.println("the rating AGENCY>>>> ");
		Baj_Sal_Work_Entity up = Baj_Sal_Work_Entity;
		up.setUniqueid(Baj_Sal_Work_Entity.getEmp_no() + Baj_Sal_Work_Entity.getSalary_month());
		System.out.println(Baj_Sal_Work_Entity.getEmp_no() + Baj_Sal_Work_Entity.getSalary_month());

		System.out.println("hi it is gstss here your adding the record for TDS");

		Baj_Work_Repo.save(up);

		return "added successfully BAJ SALARY";

	}

	@RequestMapping(value = "tdstab1edit1", method = RequestMethod.POST)
	@ResponseBody
	public String tdstab1edit1(@ModelAttribute Baj_Sal_Work_Entity Baj_Sal_Work_Entity, String tran_id,
			@RequestParam(required = false) String uniqueid) throws ParseException {

		System.out.println("hihihi");
		System.out.println(uniqueid);
		// Baj_Sal_Work_Entity up = Baj_Work_Repo.getlisttab1(uniqueid);
		Baj_Sal_Work_Entity up = Baj_Sal_Work_Entity;
		System.out.println("hi this is btm");
		Baj_Work_Repo.save(up);
		// SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss",
		// Locale.ENGLISH);

		/*
		 * try {
		 * 
		 * up.setEmp_name(tdsentity.getEmp_name()); up.setPan_no(tdsentity.getPan_no());
		 * up.setDate_of_pay(tdsentity.getDate_of_pay());
		 * up.setNet_salary(tdsentity.getNet_salary());
		 * up.setRate_of_tds(tdsentity.getTds_tran_ref());
		 * up.setBank_bsr_code(tdsentity.getBank_bsr_code());
		 * up.setTds(tdsentity.getTds());
		 * up.setBank_chalan_no(tdsentity.getBank_chalan_no());
		 * up.setBank_name(tdsentity.getBank_name());
		 * up.setChalan_amt(tdsentity.getChalan_amt());
		 * up.setTds_remit_date(tdsentity.getTds_remit_date());
		 * up.setTds_tran_ref(tdsentity.getTds_tran_ref());
		 * 
		 * //up.setUniqueid(Gstoverseas.getUniqueid());
		 * 
		 * 
		 * 
		 * tdsRepos.save(up);
		 * System.out.println("hi this is gst from tds"+tdsentity.getEmp_name());
		 * System.out.println("hi this is btm"+tdsentity.getDate_of_pay());
		 * 
		 * // Save the 'up' object with the updated entry_time
		 * 
		 * } catch (Exception e) { e.printStackTrace(); // Handle potential errors here,
		 * such as ParseException }
		 */

		return "edited Successfully tdstable1";

	}

	@RequestMapping(value = "deletetds1", method = RequestMethod.POST)
	@ResponseBody
	public String deletetds1(Model md, HttpServletRequest rq, @ModelAttribute Baj_Sal_Work_Entity Baj_Sal_Work_Entity,
			String tran_id, @RequestParam(required = false) String uniqueid) {

		System.out.println(uniqueid);
		Baj_Sal_Work_Entity up = Baj_Work_Repo.getlisttab1(uniqueid);

		System.out.println("hi it is gstss here your adding the record for india");
		System.out.println("hi it is gstss here your adding the record " + Baj_Sal_Work_Entity.getUniqueid());

		Baj_Work_Repo.delete(up);

		return "deleted successfully";

	}

	@RequestMapping(value = "swappingtosalery", method = RequestMethod.POST)
	@ResponseBody
	public String swappingtosalery(@RequestParam(required = false) String formmode, Model md, HttpServletRequest rq,
			@RequestParam(required = false) String b, @RequestParam(required = false) String a) {

		String userId = (String) rq.getSession().getAttribute("USERID");

		System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}" + a);

		try {
			List<Baj_Sal_Work_Entity> up2 = Baj_Work_Repo.getswap(a);
			// System.out.println(up2);
			List<paystructureentity> up3 = new ArrayList<>();

			for (Baj_Sal_Work_Entity salsecond : up2) {
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				paystructureentity salarymain = new paystructureentity();
				salarymain.setEmp_no(salsecond.getEmp_no());
				salarymain.setEmp_name(salsecond.getEmp_name());
				salarymain.setEmp_desig(salsecond.getEmp_desig());
				salarymain.setEmp_group(salsecond.getEmp_group());
				salarymain.setEmp_division(salsecond.getEmp_division());

				salarymain.setDate_of_birth(salsecond.getDate_of_birth());
				salarymain.setDate_of_joining(salsecond.getDate_of_joining());

				salarymain.setSpf_acct_no(salsecond.getSpf_acct_no());
				salarymain.setUrn_no(salsecond.getUrn_no());
				salarymain.setRecord_date(salsecond.getRecord_date());
				salarymain.setSalary_month(salsecond.getSalary_month()); // First setting
				// salarymain.setSalary_month(salsecond.getSalary_month()); // Second setting

				// salarymain.setNo_of_days(salsecond.getNo_of_days());
				salarymain.setNo_of_days(salsecond.getNo_of_days());
				// System.out.println("dfghjklpppppppppppppppppp"+lastDay);
				// salarymain.setDays_paid(salsecond.getDays_paid());
				salarymain.setDays_paid(salsecond.getDays_paid());
				// System.out.println("dfghjklpppppppppppppppppp"+lastDay);
				salarymain.setLoss_of_pay(salsecond.getLoss_of_pay());
				salarymain.setPayment_mode(salsecond.getPayment_mode());
				salarymain.setBank_name(salsecond.getBank_name());
				salarymain.setBank_acct_no(salsecond.getBank_acct_no());
				salarymain.setBasic_pay(salsecond.getBasic_pay());
				salarymain.setHra(salsecond.getHra());
				salarymain.setSpl_allow(salsecond.getSpl_allow());
				salarymain.setMedi_reimb(salsecond.getMedi_reimb());

				salarymain.setConv_allow(salsecond.getConv_allow());
				salarymain.setLunch_allow(salsecond.getLunch_allow());
				salarymain.setEdu_allow(salsecond.getEdu_allow());
				salarymain.setBuss_attire(salsecond.getBuss_attire());
				salarymain.setCar_maint(salsecond.getCar_maint());
				salarymain.setLeave_travel_allow(salsecond.getLeave_travel_allow());
				salarymain.setOutstn_allow(salsecond.getOutstn_allow());
				salarymain.setAnnual_loyal_bonus(salsecond.getAnnual_loyal_bonus());

				salarymain.setOtr_allow(salsecond.getOtr_allow());
				salarymain.setGross_salary(salsecond.getGross_salary());
				salarymain.setSpf(salsecond.getSpf());
				salarymain.setTds(salsecond.getTds());
				salarymain.setProf_tax(salsecond.getProf_tax());
				salarymain.setEsi(salsecond.getEsi());
				salarymain.setRecovery(salsecond.getRecovery());
				salarymain.setOtr_ded(salsecond.getOtr_ded());
				salarymain.setTotal_deductions(salsecond.getTotal_deductions());
				salarymain.setNet_salary(salsecond.getNet_salary());
				salarymain.setDate_of_pay(salsecond.getDate_of_pay());
				salarymain.setCum_tds_fy(salsecond.getCum_tds_fy());
				salarymain.setProv_it(salsecond.getProv_it());
				salarymain.setTax_due(salsecond.getTax_due());
				salarymain.setTax_per_month(salsecond.getTax_per_month());
				salarymain.setCtc_amt(salsecond.getCtc_amt());
				salarymain.setDecl_status(salsecond.getDecl_status());
				salarymain.setCtc_eff_date(salsecond.getCtc_eff_date());
				salarymain.setCtc_end_date(salsecond.getCtc_end_date());
				salarymain.setMobile_no(salsecond.getMobile_no());
				salarymain.setEmail_id(salsecond.getEmail_id());
				salarymain.setRecord_type(salsecond.getRecord_type());
				salarymain.setRecord_srl_no(salsecond.getRecord_srl_no());
				salarymain.setRecord_date(salsecond.getRecord_date());
				// salarymain.setRecord_date(salsecond.getRecord_date());
				// String d[]=(salsecond.getRecord_date().toString()).split(" ");
				// salarymain.setRecord_date(d[0]);
				salarymain.setPay_status(salsecond.getPay_status());
				salarymain.setEntity_flg(salsecond.getEntity_flg());
				salarymain.setDel_flg(salsecond.getDel_flg());
				salarymain.setEntry_time(salsecond.getEntry_time());
				salarymain.setEntry_user(salsecond.getEntry_user());
				salarymain.setModify_flg(salsecond.getModify_flg());
				salarymain.setModify_time(salsecond.getModify_time());
				salarymain.setModify_user(salsecond.getModify_user());
				salarymain.setVerify_time(salsecond.getVerify_time());
				salarymain.setVerify_user(salsecond.getVerify_user());
				salarymain.setRemarks(salsecond.getRemarks());
				salarymain.setAadhar_no(salsecond.getAadhar_no());

				up3.add(salarymain);

				// up3.add(gstoverseas);
			}

			// System.out.println("IIIIIIIIIIIIIIIIIIIIIIIIIIIIII"+up3);
			//
			Paystructurerep.saveAll(up3);

			// gstBtmRep.getInsert(b,a);

			System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}" + up3);
			// int uniqueIdCounter = Integer.parseInt(b);
			// int uniqueIdCounter1 = Integer.parseInt(a);

			// System.out.println(gstRep.getInsert(a,b));
			String msg = "Data swapped Successfully";
			return msg;

		} catch (Exception e) {
			e.printStackTrace();
			return "Error: " + e.getMessage();
		}
	}

	@RequestMapping(value = "accountLedgerPost", method = { RequestMethod.GET, RequestMethod.POST })
	public String accountLedgerPost(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resource_id, Model md, HttpServletRequest rq) {

		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");

		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
		} else if (formmode.equals("verify")) {

			md.addAttribute("formmode", "verify");

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");

		} else if (formmode.equals("modify")) {
			md.addAttribute("formmode", "modify");

		}

		return "AccountLedgerPost";
	}

	byte[] setvalue;
	byte[] setImgValue;

	@RequestMapping(value = "fileupload", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String handleFileUpload(@RequestParam("file") MultipartFile file,
			Document_Master_Entity document_Master_Entity) throws IOException {
		// Call service layer to handle file uploads and form data
		// Print the value of 'fileData' to the console

		// System.out.println("Emp Id: " + document_Master_Entity.getEmp_id());

		byte[] byteArray = file.getBytes();
		this.setvalue = byteArray;

		return "success"; // Redirect to upload page after upload
	}

	@RequestMapping(value = "fileupload2", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String handleFileUpload2(@RequestParam("file") MultipartFile file,
			Document_Master_Entity document_Master_Entity) throws IOException {
		// Call service layer to handle file uploads and form data
		// Print the value of 'fileData' to the console

		// System.out.println("Emp Id: " + document_Master_Entity.getEmp_id());

		byte[] byteArray = file.getBytes();
		this.setImgValue = byteArray;

		return "success"; // Redirect to upload page after upload
	}

	@RequestMapping(value = "fileupload1", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String handleFileUpload1(Document_Master_Entity document_Master_Entity) {
		// Call service layer to handle file uploads and form data
		// Print the value of 'fileData' to the console

		System.out.println("Emp Id: " + document_Master_Entity.getEmp_id());
		document_Master_Entity.setDocument(setvalue);
		document_Master_Entity.setDoc_image(setImgValue);

		Document_Master_Repo.save(document_Master_Entity);
		return "success"; // Redirect to upload page after upload
	}

	@RequestMapping(value = "fileuploadoffervalue", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String fileuploadoffervalue(@RequestParam(required = false) String ref,
			CandEvalFormEntity candEvalFormEntity) {
		// Call service layer to handle file uploads and form data
		// Print the value of 'fileData' to the console
		CandEvalFormEntity up = candEvalFormRep.getoffer(ref);
		System.out.println("getRef_no: " + up.getRef_no());
		up.setOffer(setImgoffer);
		// document_Master_Entity.setDoc_image(setImgValue);

		candEvalFormRep.save(up);
		return "success"; // Redirect to upload page after upload
	}

	byte[] setImgoffer;

	@RequestMapping(value = "fileuploadoffer", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String fileuploadoffer(@RequestParam("file") MultipartFile file, @RequestParam(required = false) String ref,
			CandEvalFormEntity candEvalFormEntity) throws IOException {
		// Call service layer to handle file uploads and form data
		// Print the value of 'fileData' to the console
		System.out.println("jjjjjjjjjjjjkkkkkkkkkkkkkkkkkkkkkkk" + ref);
		// System.out.println("Emp Id: " + document_Master_Entity.getEmp_id());
		System.out.println("ghghghggggggggggggggggggg" + file);

		byte[] byteArray = file.getBytes();
		this.setImgoffer = byteArray;

		return "success"; // Redirect to upload page after upload
	}

	@RequestMapping(value = "fileuploadappointmentvalue", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String fileuploadappointmentvalue(@RequestParam(required = false) String ref,
			@RequestParam(required = false) String date, @RequestParam(required = false) String name,
			@RequestParam(required = false) String position,

			CandEvalFormEntity candEvalFormEntity) throws ParseException {
		// Call service layer to handle file uploads and form data
		// Print the value of 'fileData' to the console
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date dates = dateFormat.parse(date);
		System.out.println(date);

		CandEvalFormEntity up = candEvalFormRep.getappointment(ref);
		System.out.println("getRef_no: " + up.getRef_no());
		up.setRef_no(ref);
		up.setCandi_name(name);
		up.setDate_of_appointment_letter(dates);
		System.out.println(date);
		up.setPosition(position);
		System.out.println("getRef_no" + up.getEmail_id());
		up.setAddress(up.getAddress());
		up.setCtc(up.getCtc());
		up.setEmail_id(up.getEmail_id());
		up.setAppointment(setImgappoint1);
		up.setSalarystru(setImgappoint2);
		// document_Master_Entity.setDoc_image(setImgValue);

		candEvalFormRep.save(up);
		return "Succesfully Uploaded..."; // Redirect to upload page after upload
	}

	byte[] setImgappoint1;
	byte[] setImgappoint2;

	@RequestMapping(value = "fileuploadappointment", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String fileuploadappointment(@RequestParam("file") MultipartFile file,
			@RequestParam("file1") MultipartFile file1, @RequestParam(required = false) String ref,
			CandEvalFormEntity candEvalFormEntity) throws IOException {
		// Call service layer to handle file uploads and form data
		// Print the value of 'fileData' to the console
		System.out.println("appoint" + ref);
		// System.out.println("Emp Id: " + document_Master_Entity.getEmp_id());
		System.out.println("ment" + file);
		System.out.println("ment" + file1);

		byte[] byteArray = file.getBytes();
		this.setImgappoint1 = byteArray;
		byte[] byteArrays = file1.getBytes();
		this.setImgappoint2 = byteArrays;

		return "success"; // Redirect to upload page after upload
	}

	@PostMapping(value = "fileUploadPOMaster1")
	@ResponseBody
	public String uploadFilePO(@RequestParam("file") MultipartFile file, String screenId,
			@ModelAttribute Assosiate_Profile_Entity Assosiate_Profile_Entity, Model md, HttpServletRequest rq)
			throws FileNotFoundException, SQLException, IOException, NullPointerException {

		System.out.println("the testing   rest controller");

		System.out.println("fileSize" + file.getSize());

		if (file.getSize() < 50000000) {
			String userid = (String) rq.getSession().getAttribute("USERID");
			String msg = projectMasterServices.UploadPO(userid, file, userid, Assosiate_Profile_Entity);
			return msg;
		} else {
			return "File has not been successfully uploaded. Requires less than 128 KB size.";
		}

	}

	@RequestMapping(value = { "/bankexceldownload" }, method = { RequestMethod.GET }, produces = {
			"application/octet-stream" })
	@ResponseBody
	public ResponseEntity<InputStreamResource> bankexceldownload(HttpServletResponse response,
			@RequestParam(required = false) String filetype, @RequestParam(required = false) String a,
			@RequestParam(required = false) String years) throws IOException, SQLException, JRException {
		response.setContentType("application/octet-stream");

		String fileName = "bankFileDownload" + a + ".xlsx";
		File eclFile = this.projectMasterServices.gettdsexcelbatchjob(fileName, filetype, a);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(eclFile));
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=" + fileName);
		return ((BodyBuilder) ResponseEntity.ok().headers(headers)).contentLength(eclFile.length())
				.contentType(MediaType.APPLICATION_OCTET_STREAM).body(resource);
	}

	@RequestMapping(value = "batchjobemail", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<String> batchjobemail(@RequestParam(required = false) List<String> t, Model md)
			throws SQLException, ParseException, IOException {
		System.out.println("Hi");

		String to = "barath.p@bornfire.in";
		String from = "barath.p@bornfire.in";
		String username = "barath.p@bornfire.in"; // change accordingly
		String password = "Bornfire@123"; // change accordingly
		String host = "sg2plzcpnl491716.prod.sin2.secureserver.net";

		List<String> y = t;
		for (String bb : y) {
			System.out.println(bb);

		}

		System.out.println("sdfghjkl;");

		// Call sendMail method with correct parameters
		sendingmail_batchjob.batchjobsendingmail(from, host, to, username, password, y); // pass from, host,
																							// password, and to

		// Return success response
		return ResponseEntity.status(HttpStatus.OK).body("Email sent successfully");
	}

	@RequestMapping(value = "payslipgeneration", method = RequestMethod.GET)
	@ResponseBody
	public InputStreamResource payslipgeneration(HttpServletResponse response,
			@RequestParam(value = "a", required = false) String a,
			@RequestParam(value = "t", required = false) String t,
			@RequestParam(value = "filetype", required = false) String filetype) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		System.out.println(a);
		try {
			logger.info("Getting download File :" + a + ", FileType :" + filetype + "");

			File repfile = projectMasterServices.payslip(a, t, filetype);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
		} catch (JRException e) {
			// Log the error using the logger
			logger.error("Error generating TDS file", e);
			// Optionally, rethrow the exception or handle it as needed
			// throw new YourCustomException("Error generating TDS file", e);
		}

		return resource;
	}

	@Autowired
	com.bornfire.services.Payslipgenerationemail Payslipgenerationemail;

	@RequestMapping(value = "payslipgenerationemail", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<String> payslipgenerationemails(@RequestParam(required = false) String a,
			@RequestParam(required = false) String d, @RequestParam(required = false) List<String> t, Model md)
			throws SQLException, ParseException, IOException {
		System.out.println("Hi");

		String to = null;
		String from = "barath.p@bornfire.in";
		String username = "barath.p@bornfire.in"; // change accordingly
		String password = "Bornfire@123"; // change accordingly
		String host = "sg2plzcpnl491716.prod.sin2.secureserver.net";
		String ref_no = d;
		System.out.println("refno" + ref_no);
		List<String> y = t;
		for (String bb : y) {
			System.out.println(bb);

		}

		System.out.println("sdfghjkl;");

		// Call sendMail method with correct parameters
		Payslipgenerationemail.Payslipgenerationemails(from, host, to, username, password, ref_no, y); // pass from,
																										// host,
		// password, and to

		// Return success response
		return ResponseEntity.status(HttpStatus.OK).body("Email sent successfully");
	}

	@RequestMapping(value = "paypredium", method = RequestMethod.GET)
	@ResponseBody
	public InputStreamResource paypredium(HttpServletResponse response,
			@RequestParam(value = "a", required = false) String a,
			@RequestParam(value = "filetype", required = false) String filetype) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		System.out.println(a);
		try {
			logger.info("Getting download File :" + a + ", FileType :" + filetype + "");

			File repfile = projectMasterServices.payslippredim(a, filetype);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
		} catch (JRException e) {
			// Log the error using the logger
			logger.error("Error generating TDS file", e);
			// Optionally, rethrow the exception or handle it as needed
			// throw new YourCustomException("Error generating TDS file", e);
		}

		return resource;
	}

	@GetMapping("Amcchecklist")
	public String Amcchecklist(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String uniqueid, @RequestParam(required = false) String resource_name,
			@RequestParam(required = false) String empname, String keyword, Model md, HttpServletRequest req)

	{
		String userId = (String) req.getSession().getAttribute("USERID");

		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("userId", userId);

		if (formmode == null || formmode.equals("list")) {
			String userid = (String) req.getSession().getAttribute("USERID");
			md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userid));

			// List<Object[]> amclist = BTM_Daily_Checklist_Repo.getamclistwithuser(userId);

			// md.addAttribute("amc", amclist);
			md.addAttribute("viewchecklist", BTM_Daily_Checklist_Repo.findBykeyid(uniqueid));
			List<MASTER_TABLE_AMC_DAILYLIST> amclist = MASTER_TABLE_AMC_DAILYLIST_Repo.findbypopup(userId);
			System.out.println("popupexpiry list" + amclist);
			MASTER_TABLE_AMC_DAILYLIST mastertable = null;
			Date currentDate = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currentDate);
			calendar.add(Calendar.DAY_OF_MONTH, 30); // Add 30 days
			Date datePlus30Days = calendar.getTime();
			if (amclist != null) {
				System.out.println("Condition met: kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
				mastertable = MASTER_TABLE_AMC_DAILYLIST_Repo.findByUserId(userId);
				System.out.println("mastertable: " + mastertable);
				md.addAttribute("mastertable", mastertable);
				md.addAttribute("datePlus30Days", datePlus30Days);
			} else {
				System.out.println("Condition not met.");

				System.out.println("userId (trimmed): '" + (userId != null ? userId.trim() : "null") + "'");
			}

			md.addAttribute("formmode", "list");
		} else if (formmode.equals("add")) {
			// LocalDate currentDate1 = LocalDate.now();
			Date currentDate1 = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(currentDate1);
			calendar.add(Calendar.DAY_OF_MONTH, 30); // Add 30 days
			Date datePlus30Days = calendar.getTime();
			MASTER_TABLE_AMC_DAILYLIST mastertable = MASTER_TABLE_AMC_DAILYLIST_Repo.findByUserId(userId);
			System.out.println(mastertable + "mastertable");

			if (mastertable != null) {
				System.out.println("Condition met: kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
				mastertable = MASTER_TABLE_AMC_DAILYLIST_Repo.findByUserId(userId);
				System.out.println("mastertable: " + mastertable);
				md.addAttribute("mastertable", mastertable);

			} else {
				System.out.println("Condition not met.");

				System.out.println("userId (trimmed): '" + (userId != null ? userId.trim() : "null") + "'");
				md.addAttribute("datePlus30Days", datePlus30Days);
			}

			md.addAttribute("cvfverify", attendanceRegisterRep.getcailychecklist());

			LocalTime currentTime = LocalTime.now();

			// Format the current time (HH:mm)
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			String formattedTimes = currentTime.format(formatter);

			System.out.println("Current Time: " + formattedTimes);

			md.addAttribute("formattedTimes", formattedTimes);

			LocalDate currentDate = LocalDate.now();
			md.addAttribute("currentDate", currentDate);
			md.addAttribute("userId", userId);
			int currentYear = currentDate.getYear();
			String currentMonth = String.format("%02d", currentDate.getMonthValue());
			String currentDay = String.format("%02d", currentDate.getDayOfMonth());
			md.addAttribute("currentMonth", currentMonth);
			md.addAttribute("currentyear", currentYear);
			md.addAttribute("currentday", currentDay);
			System.out.println("Current Date: " + currentDate);
			md.addAttribute("projectManager", btmProjectMasterRep.getProjectlist());

			AttendanceRegister checklistlogin = attendanceRegisterRep.getchecklistlogintime(userId, currentYear,
					currentMonth, currentDay);
			String login = checklistlogin.getLogin_time1();
			String logout = checklistlogin.getLogout_time1();
			LocalTime loginTime = LocalTime.parse(login, DateTimeFormatter.ofPattern("HH:mm:ss"));
			LocalTime logoutTime = null;

			if (logout != null && !logout.isEmpty()) {
				logoutTime = LocalTime.parse(logout, DateTimeFormatter.ofPattern("HH:mm:ss"));
				System.out.println("logout time not null");
			} else {
				// Parse the login time to get the minutes part
				int minutes = loginTime.getMinute();
				// Set logoutTime to 00:MM:00 where MM is the minutes part of login time
				logoutTime = LocalTime.of(0, minutes, 0);
				LocalTime currentTimeelse = LocalTime.now();
				System.out.println("logout time null, using login time minutes: " + logoutTime + "currentTimeelse"
						+ currentTimeelse);

				// md.addAttribute("hoursWorked", currentTimeelse);
			}

			String hoursWorked = calculateHoursWorked(loginTime, logoutTime);
			System.out.println("Hours worked: " + hoursWorked);
			if (hoursWorked == null || "null".equals(hoursWorked) || hoursWorked.trim().isEmpty()) {
				System.out.println("It is entering the if block (hoursWorked is null, 'null', or empty)");
				LocalTime currentTimeelse = LocalTime.now();
				String formattedTime = currentTimeelse.format(DateTimeFormatter.ofPattern("HH:mm"));
				md.addAttribute("hoursWorked", formattedTime); // Set the current time
			} else {
				System.out.println("It is entering the else block (hoursWorked is not null or empty)");
				md.addAttribute("hoursWorked", hoursWorked); // Use the provided value
			}

			String shift = determineShift(loginTime);
			System.out.println("Shift: " + shift);
			md.addAttribute("shift", shift);
			System.out.println("login" + login);

			md.addAttribute("formmode", "add");

		} else if (formmode.equals("modify")) {

			md.addAttribute("viewchecklist", BTM_Daily_Checklist_Repo.findBykeyid(uniqueid));

			BTM_Daily_Checklist_Entity viewchecklist = BTM_Daily_Checklist_Repo.findBykeyid(uniqueid);
			Date expirydate = viewchecklist.getUser_login_expiry_date();
			String resourse = viewchecklist.getResource_name();
			// Retrieve byte arrays from viewchecklist
			byte[] fileData1 = viewchecklist.getNps_transaction_summary_sheetword_file();
			byte[] fileData2 = viewchecklist.getNps_transaction_summary_sheetexcel_file();
			byte[] fileData3 = viewchecklist.getTransaction_monitor_inward_file();
			byte[] fileData4 = viewchecklist.getTransaction_monitor_outward_file();
			byte[] fileData5 = viewchecklist.getTransaction_monitor_return_file();
			byte[] fileData6 = viewchecklist.getFailure_transaction_details_file();
			byte[] fileData7 = viewchecklist.getHnps_channel_transaction_details_file();
			byte[] fileData8 = viewchecklist.getMobile_and_internet_channel_transaction_details_file();

			// Initialize base64 file data variables
			String base64FileData1 = "";
			String base64FileData2 = "";
			String base64FileData3 = "";
			String base64FileData4 = "";
			String base64FileData5 = "";
			String base64FileData6 = "";
			String base64FileData7 = "";
			String base64FileData8 = "";

			// Encode each byte array to Base64 string if not null
			if (fileData1 != null) {
				base64FileData1 = Base64.getEncoder().encodeToString(fileData1);
				md.addAttribute("fileData1", fileData1);
			}
			if (fileData2 != null) {
				base64FileData2 = Base64.getEncoder().encodeToString(fileData2);
				md.addAttribute("fileData2", fileData2);
			}
			if (fileData3 != null) {
				base64FileData3 = Base64.getEncoder().encodeToString(fileData3);
				md.addAttribute("fileData3", fileData3);
			}
			if (fileData4 != null) {
				base64FileData4 = Base64.getEncoder().encodeToString(fileData4);
				md.addAttribute("fileData4", fileData4);
			}
			if (fileData5 != null) {
				base64FileData5 = Base64.getEncoder().encodeToString(fileData5);
				md.addAttribute("fileData5", fileData5);
			}
			if (fileData6 != null) {
				base64FileData6 = Base64.getEncoder().encodeToString(fileData6);
				md.addAttribute("fileData6", fileData6);
			}
			if (fileData7 != null) {
				base64FileData7 = Base64.getEncoder().encodeToString(fileData7);
				md.addAttribute("fileData7", fileData7);
			}
			if (fileData8 != null) {
				base64FileData8 = Base64.getEncoder().encodeToString(fileData8);
				md.addAttribute("fileData8", fileData8);
			}

			// md.addAttribute("base64FileData", base64FileData);
			System.out.println("expirydate" + expirydate + "resourse" + resourse + "userid" + userId);

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");

			String formattedDate = sdf.format(expirydate);
			System.out.println("Formatted expiry date: " + formattedDate);

			MASTER_TABLE_AMC_DAILYLIST mastertable = null;
			if (resourse != null && userId != null && resourse.trim().equals(userId.trim())) {
				System.out.println("Condition met: kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
				mastertable = MASTER_TABLE_AMC_DAILYLIST_Repo.findByUserId(userId);
				System.out.println("mastertable: " + mastertable);
			} else {
				System.out.println("Condition not met.");
				System.out.println("resourse (trimmed): '" + (resourse != null ? resourse.trim() : "null") + "'");
				System.out.println("userId (trimmed): '" + (userId != null ? userId.trim() : "null") + "'");
			}

			md.addAttribute("mastertable", mastertable);

			if (viewchecklist != null && viewchecklist.getRemarks() != null) {
				// Split the remarks string by '||' and trim each part
				String[] remarksArray = viewchecklist.getRemarks().split("\\|\\|");

				System.out.println("remarksArray" + Arrays.toString(remarksArray));
				md.addAttribute("viewchecklist", viewchecklist);
				md.addAttribute("remarksArray", remarksArray);
			} else {
				md.addAttribute("viewchecklist", new BTM_Daily_Checklist_Entity());
				md.addAttribute("remarksArray", new String[0]); // Empty array if remarks are null
			}

			md.addAttribute("formmode", "modify");

		} else if (formmode.equals("view")) {

			md.addAttribute("viewchecklist", BTM_Daily_Checklist_Repo.findBykeyid(uniqueid));

			BTM_Daily_Checklist_Entity viewchecklist = BTM_Daily_Checklist_Repo.findBykeyid(uniqueid);
			Date expirydate = viewchecklist.getUser_login_expiry_date();
			String resourse = viewchecklist.getResource_name();
			// Retrieve byte arrays from viewchecklist
			byte[] fileData1 = viewchecklist.getNps_transaction_summary_sheetword_file();
			byte[] fileData2 = viewchecklist.getNps_transaction_summary_sheetexcel_file();
			byte[] fileData3 = viewchecklist.getTransaction_monitor_inward_file();
			byte[] fileData4 = viewchecklist.getTransaction_monitor_outward_file();
			byte[] fileData5 = viewchecklist.getTransaction_monitor_return_file();
			byte[] fileData6 = viewchecklist.getFailure_transaction_details_file();
			byte[] fileData7 = viewchecklist.getHnps_channel_transaction_details_file();
			byte[] fileData8 = viewchecklist.getMobile_and_internet_channel_transaction_details_file();

			// Initialize base64 file data variables
			String base64FileData1 = "";
			String base64FileData2 = "";
			String base64FileData3 = "";
			String base64FileData4 = "";
			String base64FileData5 = "";
			String base64FileData6 = "";
			String base64FileData7 = "";
			String base64FileData8 = "";

			// Encode each byte array to Base64 string if not null
			if (fileData1 != null) {
				base64FileData1 = Base64.getEncoder().encodeToString(fileData1);
				md.addAttribute("fileData1", fileData1);
			}
			if (fileData2 != null) {
				base64FileData2 = Base64.getEncoder().encodeToString(fileData2);
				md.addAttribute("fileData2", fileData2);
			}
			if (fileData3 != null) {
				base64FileData3 = Base64.getEncoder().encodeToString(fileData3);
				md.addAttribute("fileData3", fileData3);
			}
			if (fileData4 != null) {
				base64FileData4 = Base64.getEncoder().encodeToString(fileData4);
				md.addAttribute("fileData4", fileData4);
			}
			if (fileData5 != null) {
				base64FileData5 = Base64.getEncoder().encodeToString(fileData5);
				md.addAttribute("fileData5", fileData5);
			}
			if (fileData6 != null) {
				base64FileData6 = Base64.getEncoder().encodeToString(fileData6);
				md.addAttribute("fileData6", fileData6);
			}
			if (fileData7 != null) {
				base64FileData7 = Base64.getEncoder().encodeToString(fileData7);
				md.addAttribute("fileData7", fileData7);
			}
			if (fileData8 != null) {
				base64FileData8 = Base64.getEncoder().encodeToString(fileData8);
				md.addAttribute("fileData8", fileData8);
			}

			// md.addAttribute("base64FileData", base64FileData);
			System.out.println("expirydate" + expirydate + "resourse" + resourse + "userid" + userId);

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");

			String formattedDate = sdf.format(expirydate);
			System.out.println("Formatted expiry date: " + formattedDate);

			MASTER_TABLE_AMC_DAILYLIST mastertable = null;
			if (resourse != null && userId != null && resourse.trim().equals(userId.trim())) {
				System.out.println("Condition met: kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
				mastertable = MASTER_TABLE_AMC_DAILYLIST_Repo.findByUserId(userId);
				System.out.println("mastertable: " + mastertable);
			} else {
				System.out.println("Condition not met.");
				System.out.println("resourse (trimmed): '" + (resourse != null ? resourse.trim() : "null") + "'");
				System.out.println("userId (trimmed): '" + (userId != null ? userId.trim() : "null") + "'");
			}

			md.addAttribute("mastertable", mastertable);

			if (viewchecklist != null && viewchecklist.getRemarks() != null) {
				// Split the remarks string by '||' and trim each part
				String[] remarksArray = viewchecklist.getRemarks().split("\\|\\|");

				System.out.println("remarksArray" + Arrays.toString(remarksArray));
				md.addAttribute("viewchecklist", viewchecklist);
				md.addAttribute("remarksArray", remarksArray);
			} else {
				md.addAttribute("viewchecklist", new BTM_Daily_Checklist_Entity());
				md.addAttribute("remarksArray", new String[0]); // Empty array if remarks are null
			}
			md.addAttribute("formmode", "view");

		} else if (formmode.equals("verify")) {
			md.addAttribute("formmode", "verify");

		} else if (formmode.equals("enquiry")) {
			md.addAttribute("formmode", "enquiry");

		}
		return "Amcchecklist";
	}

	public static String determineShift(LocalTime loginTime) {
		String shift;

		LocalTime firstShiftStart = LocalTime.of(10, 0);
		LocalTime firstShiftEnd = LocalTime.of(18, 0);
		LocalTime thirdShiftEnd = LocalTime.of(18, 0);
		LocalTime fourShiftEnd = LocalTime.of(2, 0);

		if (loginTime.isAfter(firstShiftStart) && loginTime.isBefore(firstShiftEnd)) {
			shift = "First Shift";
		} else if (loginTime.isAfter(thirdShiftEnd) && loginTime.isBefore(fourShiftEnd)) {
			shift = "Second Shift";
		} else {
			shift = "Third Shift";
		}

		return shift;
	}

	public static String calculateHoursWorked(LocalTime loginTime, LocalTime logoutTime) {
		if (logoutTime.getHour() == 0 && logoutTime.getMinute() == loginTime.getMinute()
				&& logoutTime.getSecond() == 0) {
			return "null";
		}

		// Calculate duration between login and logout times
		Duration duration = Duration.between(loginTime, logoutTime);

		// Get the total hours, minutes, and seconds from the duration
		long seconds = duration.getSeconds();
		long hoursWorked = seconds / 3600;
		long minutesWorked = (seconds % 3600) / 60;

		return hoursWorked + " hours " + minutesWorked + " minutes";
	}

	@RequestMapping(value = "getChecklist", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<BtmChecklistDto> getChecklist(HttpServletRequest req) {
		System.out.println("hiiiiiiiiiiiiiiiiiiiiii");
		String userId = (String) req.getSession().getAttribute("USERID");
		String role = resourceMasterRepo.getroleid(userId);

		List<Object[]> results;
		if ("ADM".equals(role)) {
			results = BTM_Daily_Checklist_Repo.getamclist();
		} else {
			results = BTM_Daily_Checklist_Repo.getamclistwithuser(userId);
		}

		List<BtmChecklistDto> dtoList = new ArrayList<>();
		for (Object[] row : results) {
			BtmChecklistDto dto = new BtmChecklistDto((String) row[0], // resource_name
					(String) row[1], // service_shift
					(String) row[2], // service_time
					(String) row[3], // log_in_time
					(String) row[4], // log_out_time
					(String) row[5], // project
					(String) row[6], // UNIQUEID
					(String) row[7], // DEL_FLG
					(String) row[8], // ENTRY_FLG
					(Date) row[9], // USER_LOGIN_EXPIRY_DATE
					(Date) row[10] // SSL_CERTIFICATE_EXPIRY_DATE
			);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@RequestMapping(value = "projectdetailchecklist", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ProjectDetailsEntity getprojectdata(@RequestParam("resourceName") String resourceName, Model md) {
		System.out.println("entry to back end" + resourceName);
		ProjectDetailsEntity arl = projectDetailsRep.getprojectdetails(resourceName);
		md.addAttribute("viewchecklist", projectDetailsRep.getprojectdetails(resourceName));

		return arl;
		// return "Success";
	}

	@RequestMapping(value = "amcchecklistsubmit", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> amcchecklistsubmit(
			@ModelAttribute com.bornfire.entities.BTM_Daily_Checklist_Entity BTM_Daily_Checklist_Entity,
			@RequestParam(required = false) String gatewayConnectivityValue,
			@RequestParam(required = false) String concatenatedRemarks,
			@RequestParam(required = false) String databaseConnectivityValue,
			@RequestParam(required = false) String connect24ConnectivityValue,
			@RequestParam(required = false) String dbMemoryCheckValue,
			@RequestParam(required = false) String dbMemoryIncreaseValue,
			@RequestParam(required = false) String nps_transaction_summary_sheet_wordvalue,
			@RequestParam(required = false) String npsTransactionSummarySheetExcelValue,
			@RequestParam(required = false) String transactionMonitorInwardValue,
			@RequestParam(required = false) String transactionMonitorOutwardValue,
			@RequestParam(required = false) String transactionMonitorReturnValue,
			@RequestParam(required = false) String failureTransactionDetailsValue,
			@RequestParam(required = false) String hnpsChannelTransactionDetailsValue,
			@RequestParam(required = false) String mobileInternetChannelTransactionDetailsValue,
			@RequestParam(required = false) MultipartFile file, @RequestParam(required = false) MultipartFile file1,
			@RequestParam(required = false) MultipartFile file2, @RequestParam(required = false) MultipartFile file3,
			@RequestParam(required = false) MultipartFile file4, @RequestParam(required = false) MultipartFile file5,
			@RequestParam(required = false) MultipartFile file6, @RequestParam(required = false) MultipartFile file7,
			Model md, HttpServletRequest req) {

		byte[] setImgamc1 = null;
		byte[] setImgamc2 = null;
		byte[] setImgamc3 = null;
		byte[] setImgamc4 = null;
		byte[] setImgamc5 = null;
		byte[] setImgamc6 = null;
		byte[] setImgamc7 = null;
		byte[] setImgamc8 = null;

		try {
			if (file != null && !file.isEmpty()) {
				setImgamc1 = file.getBytes();
			}
			if (file1 != null && !file1.isEmpty()) {
				setImgamc2 = file1.getBytes();
				System.out.println("fffffffffff" + setImgamc2);
			}
			if (file2 != null && !file2.isEmpty()) {
				setImgamc3 = file2.getBytes();
			}
			if (file3 != null && !file3.isEmpty()) {
				setImgamc4 = file3.getBytes();
			}
			if (file4 != null && !file4.isEmpty()) {
				setImgamc5 = file4.getBytes();
			}
			if (file5 != null && !file5.isEmpty()) {
				setImgamc6 = file5.getBytes();
			}
			if (file6 != null && !file6.isEmpty()) {
				setImgamc7 = file6.getBytes();
			}
			if (file7 != null && !file7.isEmpty()) {
				setImgamc8 = file7.getBytes();
			}

			BTM_Daily_Checklist_Entity.setRemarks(concatenatedRemarks);
			BTM_Daily_Checklist_Entity.setGateway_connectivity(gatewayConnectivityValue);
			BTM_Daily_Checklist_Entity.setDatabase_connectivity(databaseConnectivityValue);
			BTM_Daily_Checklist_Entity.setConnect_24_connectivity(connect24ConnectivityValue);
			BTM_Daily_Checklist_Entity.setDb_memory_check(dbMemoryCheckValue);
			BTM_Daily_Checklist_Entity.setDb_memory_increase(dbMemoryIncreaseValue);
			BTM_Daily_Checklist_Entity.setNps_transaction_summary_sheetword(nps_transaction_summary_sheet_wordvalue);
			BTM_Daily_Checklist_Entity.setNps_transaction_summary_sheetexcel(npsTransactionSummarySheetExcelValue);
			BTM_Daily_Checklist_Entity.setTransaction_monitor_inward(transactionMonitorInwardValue);
			BTM_Daily_Checklist_Entity.setTransaction_monitor_outward(transactionMonitorOutwardValue);
			BTM_Daily_Checklist_Entity.setTransaction_monitor_return(transactionMonitorReturnValue);
			BTM_Daily_Checklist_Entity.setFailure_transaction_details(failureTransactionDetailsValue);
			BTM_Daily_Checklist_Entity.setHnps_channel_transaction_details(hnpsChannelTransactionDetailsValue);
			BTM_Daily_Checklist_Entity
					.setMobile_and_internet_channel_transaction_details(mobileInternetChannelTransactionDetailsValue);
			BTM_Daily_Checklist_Entity.setUniqueid(BTM_Daily_Checklist_Entity.getResource_name()
					+ BTM_Daily_Checklist_Entity.getService_date() + BTM_Daily_Checklist_Entity.getService_shift() +

					BTM_Daily_Checklist_Entity.getProject());

			BTM_Daily_Checklist_Entity.setNps_transaction_summary_sheetword_file(setImgamc1);
			BTM_Daily_Checklist_Entity.setNps_transaction_summary_sheetexcel_file(setImgamc2);
			BTM_Daily_Checklist_Entity.setTransaction_monitor_inward_file(setImgamc3);
			BTM_Daily_Checklist_Entity.setTransaction_monitor_outward_file(setImgamc4);
			BTM_Daily_Checklist_Entity.setTransaction_monitor_return_file(setImgamc5);
			BTM_Daily_Checklist_Entity.setFailure_transaction_details_file(setImgamc6);
			BTM_Daily_Checklist_Entity.setHnps_channel_transaction_details_file(setImgamc7);
			BTM_Daily_Checklist_Entity.setMobile_and_internet_channel_transaction_details_file(setImgamc8);
			BTM_Daily_Checklist_Entity.setDel_flag("N");
			// Call your service
			String msg = projectMasterServices.amcsheckservice(BTM_Daily_Checklist_Entity, md, req);

			// Save to repository
			BTM_Daily_Checklist_Repo.save(BTM_Daily_Checklist_Entity);

			// Return success response
			return ResponseEntity.ok("success");

		} catch (IOException e) {
			// Log exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing files");
		} catch (Exception e) {
			// Log exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
		}
	}

	@RequestMapping(value = "modifyamcchecklist", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> modifyamcchecklist(
			@ModelAttribute com.bornfire.entities.BTM_Daily_Checklist_Entity BTM_Daily_Checklist_Entity,
			@RequestParam(required = false) String gatewayConnectivityValue,
			@RequestParam(required = false) String concatenatedRemarks,
			@RequestParam(required = false) String databaseConnectivityValue,
			@RequestParam(required = false) String connect24ConnectivityValue,
			@RequestParam(required = false) String dbMemoryCheckValue,
			@RequestParam(required = false) String dbMemoryIncreaseValue,
			@RequestParam(required = false) String nps_transaction_summary_sheet_wordvalue,
			@RequestParam(required = false) String npsTransactionSummarySheetExcelValue,
			@RequestParam(required = false) String transactionMonitorInwardValue,
			@RequestParam(required = false) String transactionMonitorOutwardValue,
			@RequestParam(required = false) String transactionMonitorReturnValue,
			@RequestParam(required = false) String failureTransactionDetailsValue,
			@RequestParam(required = false) String hnpsChannelTransactionDetailsValue,
			@RequestParam(required = false) String mobileInternetChannelTransactionDetailsValue,
			@RequestParam(required = false) MultipartFile file, @RequestParam(required = false) MultipartFile file1,
			@RequestParam(required = false) MultipartFile file2, @RequestParam(required = false) MultipartFile file3,
			@RequestParam(required = false) MultipartFile file4, @RequestParam(required = false) MultipartFile file5,
			@RequestParam(required = false) MultipartFile file6, @RequestParam(required = false) MultipartFile file7,
			Model md, HttpServletRequest req) {

		byte[] setImgamc1 = null;
		byte[] setImgamc2 = null;
		byte[] setImgamc3 = null;
		byte[] setImgamc4 = null;
		byte[] setImgamc5 = null;
		byte[] setImgamc6 = null;
		byte[] setImgamc7 = null;
		byte[] setImgamc8 = null;

		try {
			if (BTM_Daily_Checklist_Entity.getResource_name() != null
					&& !BTM_Daily_Checklist_Entity.getResource_name().isEmpty()) {
				System.out.println(
						BTM_Daily_Checklist_Entity.getResource_name() + "" + BTM_Daily_Checklist_Entity.getUniqueid());
				BTM_Daily_Checklist_Entity existingUser = BTM_Daily_Checklist_Repo
						.findBykeyid(BTM_Daily_Checklist_Entity.getUniqueid());
				System.out
						.println("BTM_Daily_Checklist_Entity.getUniqueid()" + BTM_Daily_Checklist_Entity.getUniqueid());
				if (file != null && !file.isEmpty()) {
					setImgamc1 = file.getBytes();
				} else {
					setImgamc1 = existingUser.getNps_transaction_summary_sheetword_file();
					System.out.println("setImgamc1" + setImgamc1);
				}
				if (file1 != null && !file1.isEmpty()) {
					setImgamc2 = file1.getBytes();
				} else {
					setImgamc2 = existingUser.getNps_transaction_summary_sheetexcel_file();
					System.out.println("setImgamc2" + setImgamc2);
				}
				if (file2 != null && !file2.isEmpty()) {
					setImgamc3 = file2.getBytes();
				} else {
					setImgamc3 = existingUser.getTransaction_monitor_inward_file();
					System.out.println("setImgamc3" + setImgamc3);

				}
				if (file3 != null && !file3.isEmpty()) {
					setImgamc4 = file3.getBytes();
				} else {
					setImgamc4 = existingUser.getTransaction_monitor_outward_file();
					System.out.println("setImgamc4" + setImgamc4);

				}
				if (file4 != null && !file4.isEmpty()) {
					setImgamc5 = file4.getBytes();
				} else {
					setImgamc5 = existingUser.getTransaction_monitor_return_file();
					System.out.println("setImgamc5" + setImgamc5);
				}
				if (file5 != null && !file5.isEmpty()) {
					setImgamc6 = file5.getBytes();
				} else {
					setImgamc6 = existingUser.getFailure_transaction_details_file();
					System.out.println("setImgamc6" + setImgamc6);
				}
				if (file6 != null && !file6.isEmpty()) {
					setImgamc7 = file6.getBytes();
				} else {
					setImgamc7 = existingUser.getHnps_channel_transaction_details_file();
					System.out.println("setImgamc7" + setImgamc7);
				}
				if (file7 != null && !file7.isEmpty()) {
					setImgamc8 = file7.getBytes();
				} else {
					setImgamc8 = existingUser.getMobile_and_internet_channel_transaction_details_file();
					System.out.println("setImgamc8" + setImgamc8);
				}
			}

			BTM_Daily_Checklist_Entity.setRemarks(concatenatedRemarks);
			BTM_Daily_Checklist_Entity.setGateway_connectivity(gatewayConnectivityValue);
			BTM_Daily_Checklist_Entity.setDatabase_connectivity(databaseConnectivityValue);
			BTM_Daily_Checklist_Entity.setConnect_24_connectivity(connect24ConnectivityValue);
			BTM_Daily_Checklist_Entity.setDb_memory_check(dbMemoryCheckValue);
			BTM_Daily_Checklist_Entity.setDb_memory_increase(dbMemoryIncreaseValue);
			BTM_Daily_Checklist_Entity.setNps_transaction_summary_sheetword(nps_transaction_summary_sheet_wordvalue);
			BTM_Daily_Checklist_Entity.setNps_transaction_summary_sheetexcel(npsTransactionSummarySheetExcelValue);
			BTM_Daily_Checklist_Entity.setTransaction_monitor_inward(transactionMonitorInwardValue);
			BTM_Daily_Checklist_Entity.setTransaction_monitor_outward(transactionMonitorOutwardValue);
			BTM_Daily_Checklist_Entity.setTransaction_monitor_return(transactionMonitorReturnValue);
			BTM_Daily_Checklist_Entity.setFailure_transaction_details(failureTransactionDetailsValue);
			BTM_Daily_Checklist_Entity.setHnps_channel_transaction_details(hnpsChannelTransactionDetailsValue);
			BTM_Daily_Checklist_Entity
					.setMobile_and_internet_channel_transaction_details(mobileInternetChannelTransactionDetailsValue);
			BTM_Daily_Checklist_Entity.setUniqueid(BTM_Daily_Checklist_Entity.getResource_name()
					+ BTM_Daily_Checklist_Entity.getService_date() + BTM_Daily_Checklist_Entity.getService_shift() +

					BTM_Daily_Checklist_Entity.getProject());

			BTM_Daily_Checklist_Entity.setNps_transaction_summary_sheetword_file(setImgamc1);
			BTM_Daily_Checklist_Entity.setNps_transaction_summary_sheetexcel_file(setImgamc2);
			BTM_Daily_Checklist_Entity.setTransaction_monitor_inward_file(setImgamc3);
			BTM_Daily_Checklist_Entity.setTransaction_monitor_outward_file(setImgamc4);
			BTM_Daily_Checklist_Entity.setTransaction_monitor_return_file(setImgamc5);
			BTM_Daily_Checklist_Entity.setFailure_transaction_details_file(setImgamc6);
			BTM_Daily_Checklist_Entity.setHnps_channel_transaction_details_file(setImgamc7);
			BTM_Daily_Checklist_Entity.setMobile_and_internet_channel_transaction_details_file(setImgamc8);

			// Call your service
			String msg = projectMasterServices.amcsheckservicemodify(BTM_Daily_Checklist_Entity, md, req);

			// Save to repository
			BTM_Daily_Checklist_Repo.save(BTM_Daily_Checklist_Entity);

			// Return success response
			return ResponseEntity.ok("success");

		} catch (IOException e) {
			// Log exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing files");
		} catch (Exception e) {
			// Log exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected error occurred");
		}
	}

	@RequestMapping(value = "deleteuseramc", method = RequestMethod.POST)
	@ResponseBody
	public String deleteuseramc(Model md, HttpServletRequest rq,
			@ModelAttribute BTM_Daily_Checklist_Entity BTM_Daily_Checklist_Entity,
			@RequestParam(required = false) String uniqueid) {

		System.out.println("the rating AGENCY>>>> " + uniqueid);
		// BTM_Daily_Checklist_Entity up = BTM_Daily_Checklist_Entity;

		BTM_Daily_Checklist_Entity up = BTM_Daily_Checklist_Repo.delete1(uniqueid);
		up.setDel_flag("Y");

		System.out.println("hi it is gstss here your adding the record for india");
		System.out.println("hi it is gstss here your adding the record " + up.getUniqueid());

		BTM_Daily_Checklist_Repo.save(up);

		return "deleted successfully";

	}

	@RequestMapping(value = "popupexpirysubmit", method = RequestMethod.POST)
	@ResponseBody
	public String popupexpirysubmit(@ModelAttribute MASTER_TABLE_AMC_DAILYLIST MASTER_TABLE_AMC_DAILYLIST, Model md,

			HttpServletRequest req)
			throws FileNotFoundException, NullPointerException, SQLException, IOException, ParseException {
		System.out.println("entity" + MASTER_TABLE_AMC_DAILYLIST.getResource_name());

		String msg = projectMasterServices.servicepopupexpirysubmit(MASTER_TABLE_AMC_DAILYLIST, md, req);

		System.out.println("hi it is gstss here your adding the record for overseas");

		return "succeess";

	}

	@RequestMapping(value = "sendingmail_amcchecklist", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<String> sendingmail_amcchecklist(@RequestParam(required = false) String resource_name,
			@RequestParam(required = false) String cc, @RequestParam(required = false) String d,
			@RequestParam(required = false) List<String> t, @RequestParam(required = false) String service_date,
			@RequestParam(required = false) String service_time1, @RequestParam(required = false) String service_shift,
			Model md) {

		try {
			// Log the parameters for debugging
			System.out.println("Hi");
			System.out.println("Resource Name: " + resource_name);
			System.out.println("Service Date: " + service_date);
			System.out.println("Service Time: " + service_time1);
			System.out.println("Service Shift: " + service_shift);

			// SMTP configuration
			String to = "amuthasenthuran.s@bornfire.in"; // Recipient email
			String from = "amuthasenthuran.s@bornfire.in"; // Sender email
			String username = "amuthasenthuran.s@bornfire.in"; // SMTP username
			String password = "MiddleEast#123"; // SMTP password
			String host = "sg2plzcpnl491716.prod.sin2.secureserver.net"; // SMTP host

			// Call sendMail method with correct parameters
			Sendingmail_Amcchecklist.sending_amcchecklist(from, host, to, cc, username, password, resource_name,
					service_date, service_time1, service_shift);

			// Return success response
			return ResponseEntity.status(HttpStatus.OK).body("successfully");

		} catch (Exception e) {
			// Log the exception and return error response
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to send email: " + e.getMessage());
		}
	}

	@RequestMapping(value = "CallLog", method = { RequestMethod.GET, RequestMethod.POST })
	public String CallLog(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String project_name,
			@RequestParam(required = false) String lodged_date_and_time,
			@RequestParam(required = false) String call_assign_to, @RequestParam(required = false) String uniqueid,
			Model md, HttpServletRequest req)

	{

		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("Name", resourceMasterRepo.getname(userId));
		System.out.println("resourceMasterRepo.getname(userId)" + resourceMasterRepo.getname(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		System.out.println("userid" + userId);
		md.addAttribute("userId", userId);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
		String currentDatelist = sdf.format(new Date());
		md.addAttribute("currentDatelist1", currentDatelist);

		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy");
		String currentYear1 = LocalDate.now().format(formatter2);
		System.out.println("Current Year in yyyy format: " + currentYear1);

		DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("MM");
		String currentMonth1 = LocalDate.now().format(formatter3);
		System.out.println("Current Month in MM format: " + currentMonth1);

		if (formmode == null || formmode.equals("list")) {

			/*
			 * List<BTM_Call_Log_Entity> callLogList = BTM_Call_Log_Repo.getCalllogList();
			 * System.out.println("callLogList"+callLogList); md.addAttribute("call",
			 * callLogList);
			 */
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yy");
			String currentDatelist1 = sdf1.format(new Date());
			md.addAttribute("currentDatelist", currentDatelist1);

			/*
			 * for (BTM_Call_Log_Entity callLog : callLogList) {
			 * System.out.println("Call Log Entry: " + callLog);
			 * System.out.println("Call Log Entry: " + callLog.getProject_name()); }
			 */

			/*
			 * md.addAttribute("call", BTM_Call_Log_Repo.getCalllogList());
			 * System.out.println(BTM_Call_Log_Repo.getCalllogList());
			 */
			md.addAttribute("formmode", "list");
		} else if (formmode.equals("add")) {
			LocalDate currentDates = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			String  currentDate= currentDates.format(formatter);
			md.addAttribute("currentDate", currentDate);
			md.addAttribute("userId", userId);
			int currentYear = currentDates.getYear();
			String currentMonth = String.format("%02d", currentDates.getMonthValue());
			String currentDay = String.format("%02d", currentDates.getDayOfMonth());
			LocalTime now = LocalTime.now();
			DateTimeFormatter formatters = DateTimeFormatter.ofPattern("HH:mm:ss");
			String formattedTime = now.format(formatters);

			
			md.addAttribute("formattedNow", formattedTime);
		
			// BTMEmpTimeSheet existingdevice=
			// btmEmpTimeSheetRep.getTimeSheetLastproj(userId,currentMonth1,currentYear1);
						ProjectDetailsEntity existingdevice = projectDetailsRep.getprojectview(userId);
						String proj = existingdevice.getResource_id();
						String project = existingdevice.getProject_id();
						System.out.println("project" + project);
						System.out.println("proj" + proj);
						System.out.println("proj" + currentYear);
						System.out.println("proj" + currentMonth);

						// List<String> existingdeviceslist =
						// btmEmpTimeSheetRep.getTimeSheetLastlists(project,currentYear,currentMonth);
						List<String> existingdeviceslist = projectDetailsRep.getcalllogproj(project);
						md.addAttribute("existevalue", existingdeviceslist);
						System.out.println("existingdeviceslist" + existingdeviceslist);
						if (existingdeviceslist.isEmpty()) {
							System.out.println("No records found for the given parameters.");
						} else {
							// If the list is not empty, print out the contents
							System.out.println("List of existing devices: ");
							for (String device : existingdeviceslist) {
								System.out.println(device);
							}
						}
						md.addAttribute("existingdeviceslist", existingdeviceslist);

						System.out.println(existingdevice.getResource_id());
						System.out.println(existingdevice.getClient_id());
						md.addAttribute("projectname", project);
						String lodg = "Lodged";
						md.addAttribute("Lodged", lodg);
			md.addAttribute("formmode", "add");

		}

		else if (formmode.equals("modify")) {

			System.out.println(uniqueid + "uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu");
			String safeUniqueid = uniqueid.replace("&", "&amp;");
			System.out.println(safeUniqueid + "iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
			System.out.println(uniqueid + "gggggggggggggggggggggggggg");
			md.addAttribute("calllogmodify", BTM_Call_Log_Repo.getuniqueid(uniqueid));

			md.addAttribute("formmode", "modify");

			String fname = (String) req.getSession().getAttribute("filename");
			md.addAttribute("fname", fname);
			System.out.println("File name: " + fname);

		} else if (formmode.equals("view")) {
			md.addAttribute("calllogmodify", BTM_Call_Log_Repo.getuniqueid(uniqueid));

			md.addAttribute("formmode", "view");

		} else if (formmode.equals("verify")) {
			md.addAttribute("formmode", "verify");

		} else if (formmode.equals("enquiry")) {
			md.addAttribute("formmode", "enquiry");

		}
		return "BTMCallLog";
	}

	@RequestMapping(value = "getcallog", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Btm_calllog_dto> getcallog(HttpServletRequest req) {
		System.out.println("hiiiiiiiiiiiiiiiiiiiiii");
		String userId = (String) req.getSession().getAttribute("USERID");
		String role = resourceMasterRepo.getroleid(userId);
		String resourcename = resourceMasterRepo.getname1(userId);

		System.out.println("resourcename" + resourcename);

		List<Object[]> results;
		if ("ADM".equals(role)) {
			results = BTM_Call_Log_Repo.getcallloglistFor();
		} else {
			results = BTM_Call_Log_Repo.getcallloglist(resourcename);
		}
		List<Btm_calllog_dto> dtoList = new ArrayList<>();

		for (Object[] row : results) {
			Btm_calllog_dto dto = new Btm_calllog_dto((String) row[0], (String) row[1], (Date) row[2], (String) row[3],
					(String) row[4], (String) row[5], (String) row[6], (String) row[7]

			);
			dtoList.add(dto);
		}
		return dtoList;
	}

	@RequestMapping(value = "calllogdownload", method = RequestMethod.GET)
	@ResponseBody
	public InputStreamResource calllogdownload(HttpServletResponse response,
			@RequestParam(required = false) String filetype) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {
			logger.info("Getting download File ");

			File repfile = projectMasterServices.getcalllogpdf(filetype);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
		} catch (JRException e) {
			logger.error("Error generating TDS file", e);
		}

		return resource;
	}

	@RequestMapping(value = "auditschedulesubmit", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String auditschedulesubmit(Model md, HttpServletRequest rq,
			@RequestBody List<com.bornfire.entities.BTM_Call_Log_Entity> BTM_Call_Log_Entity) {
		System.out.println("Auditsize" + BTM_Call_Log_Entity.size());
		List<BTM_Call_Log_Entity> up = BTM_Call_Log_Entity;
		List<BTM_Call_Log_Entity> newarray = new ArrayList<>();
		for (BTM_Call_Log_Entity audit : up) {

			audit.setEntry_flg("N");
			audit.setVerify_flg("N");
			audit.setDel_flg("N");
			audit.setModify_flg("N");
			String a = audit.getProject_name();
			System.out.println(a);
			newarray.add(audit);

			String uniqueId = audit.getReceived_by() + audit.getLodged_date_and_time() + audit.getRemarks()
					+ audit.getLodged_time();
			uniqueId = uniqueId.replaceAll("[^a-zA-Z0-9 :]", "");
			audit.setUniqueid(uniqueId);
		}

		BTM_Call_Log_Repo.saveAll(newarray);

		return "Successfully Saved";
	}

	@RequestMapping(value = "calllogmodify", method = RequestMethod.POST)
	@ResponseBody
	public String calllogmodify(@ModelAttribute BTM_Call_Log_Entity BTM_Call_Log_Entity,
			@RequestParam(required = false) String project_name, @RequestParam(required = false) String lodged_by,
			@RequestParam(required = false) String lodged_date_and_time,
			@RequestParam(required = false) String call_assign_to, @RequestParam(required = false) String call_details,
			@RequestParam(required = false) String received_by, @RequestParam(required = false) String status,
			@RequestParam(required = false) String remarks, @RequestParam(required = false) String lodged_time,
			@RequestParam(required = false) String uniqueid, Model md) throws ParseException {
		System.out.println("calllogmodify");
		String a1 = uniqueid;
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yy");
		Date lodged_date_time = sdf1.parse(lodged_date_and_time);

		BTM_Call_Log_Entity existing = BTM_Call_Log_Repo.getUniqueid(a1);
		System.out.println("existing" + existing);
		if (existing == null) {
			return "No value";

		} else {
			BTM_Call_Log_Entity.setProject_name(project_name);
			BTM_Call_Log_Entity.setLodged_by(lodged_by);
			BTM_Call_Log_Entity.setLodged_date_and_time(lodged_date_time);
			BTM_Call_Log_Entity.setCall_assign_to(call_assign_to);
			BTM_Call_Log_Entity.setCall_details(call_details);
			BTM_Call_Log_Entity.setReceived_by(received_by);
			BTM_Call_Log_Entity.setStatus(status);
			BTM_Call_Log_Entity.setLodged_time(lodged_time);
			BTM_Call_Log_Entity.setCall_log_file(setImgcalllog);

			BTM_Call_Log_Entity.setEntry_flg("N");
			BTM_Call_Log_Entity.setVerify_flg("N");
			BTM_Call_Log_Entity.setDel_flg("N");
			BTM_Call_Log_Entity.setModify_flg("Y");

			System.out.println(project_name + "project name");
			BTM_Call_Log_Repo.save(BTM_Call_Log_Entity);

			// SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss",
			// Locale.ENGLISH);
			return "Successfully Saved";

		}
	}

	@RequestMapping(value = "calllofpdfmodifysubmit", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public ResponseEntity<Map<String, String>> calllofpdfmodifysubmit(@RequestParam(required = false) String uniqueid,
			BTM_Call_Log_Entity BTM_Call_Log_Entity, Model md) {
		System.out.println("uniqueid" + uniqueid);
		BTM_Call_Log_Entity exist = BTM_Call_Log_Repo.getUniqueidpdf(uniqueid);
		System.out.println("exist" + exist);
		System.out.println("exist: " + exist.getReceived_by());
		exist.setCall_log_file(setImgcalllog);
		exist.setStatus("Resolved");

		String fname = filename;
		System.out.println("file name" + fname);

		Map<String, String> response = new HashMap<>();
		response.put("status", "success");
		response.put("fname", fname);
		BTM_Call_Log_Repo.save(exist);

		return ResponseEntity.ok(response); // Redirect to upload page after upload
	}

	byte[] setImgcalllog;
	String filename;

	@RequestMapping(value = "calllogpdfupload", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String calllogpdfupload(@RequestParam("file") MultipartFile file, @RequestParam(required = false) String ref,
			BTM_Call_Log_Entity BTM_Call_Log_Entity, HttpServletRequest request) throws IOException {

		System.out.println("Received File: " + file.getOriginalFilename());
		String fname = file.getOriginalFilename();
		System.out.println("Reference Type:" + ref);

		System.out.println("Multipart File" + file);

		byte[] byteArray = file.getBytes();
		this.setImgcalllog = byteArray;

		request.getSession().setAttribute("filename", fname);

		return "success"; // Redirect to upload page after upload
	}

	@PostMapping(value = "deletecalllog")
	@ResponseBody
	public String deletecalllog(@RequestParam(required = false) String uniqueid, Model md, HttpServletRequest rq,
			@ModelAttribute BTM_Call_Log_Entity BTM_Call_Log_Entity) {
		// bTMTroubleShoot_Rep.deletethevalue(issue_no);
		BTM_Call_Log_Entity existing = BTM_Call_Log_Repo.getUniqueid(uniqueid);
		existing.setDel_flg("Y");
		BTM_Call_Log_Repo.save(existing);
		System.out.println("Delete Row:" + uniqueid);

		return "deleted Successfully";
	}

	@RequestMapping(value = "amcfilesave", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String amcfilesave(@RequestParam(required = false) String uniqueid,
			BTM_Call_Log_Entity BTM_Call_Log_Entity) {
		// Call service layer to handle file uploads and form data
		// Print the value of 'fileData' to the console
		BTM_Call_Log_Entity exist = BTM_Call_Log_Repo.getUniqueidpdf(uniqueid);
		System.out.println("exist" + exist);
		System.out.println("exist: " + exist.getReceived_by());
		exist.setCall_log_file(setImgcalllog);
		exist.setStatus("Resolved");

		BTM_Call_Log_Repo.save(exist);
		return "success"; // Redirect to upload page after upload
	}

	@RequestMapping(value = "projectdetails", method = { RequestMethod.GET, RequestMethod.POST })
	public String projectdetails(@RequestParam(required = false) String formmode,
	        @RequestParam(required = false) String resourceId, @RequestParam(required = false) String projectId,
	        Model md, HttpServletRequest req) {

		String userId = (String) req.getSession().getAttribute("USERID");

		String username = (String) req.getSession().getAttribute("USERNAME");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("userId", userId);
		md.addAttribute("username", username);

	    if (formmode == null || formmode.equals("list")) {
	        md.addAttribute("formmode", "list");
	        md.addAttribute("projectList", projectDetailsRep.getProjectList());
	    } 
	    else if (formmode.equals("add")) {
	        md.addAttribute("formmode", "add");
	    } 
	    else if (formmode.equals("view") || formmode.equals("modify")) {
	        if (resourceId != null) {
	        	System.out.println("Resource Id"+resourceId);
	        	System.out.println("Project ID Id"+projectId);
	            ProjectDetailsEntity projectView = projectDetailsRep.getprojectview(projectId);
	            md.addAttribute("projectView", projectView);
	            
	            if (projectView != null) {
	            	System.out.println("Inner"+resourceId);
	            	System.out.println("Project ID IdSSS"+projectId);
	                // Split team members data
	                List<Map<String, String>> teamMembers = new ArrayList<>();
	                
	                // Safely split each field
	                String[] resourceIds = projectView.getResource_id_1() != null ? 
	                    projectView.getResource_id_1().split("\\|\\|\\|") : new String[0];
	                String[] resourceNames = projectView.getResource_name_1() != null ? 
	                    projectView.getResource_name_1().split("\\|\\|\\|") : new String[0];
	                String[] startDates = projectView.getStart_date_1() != null ? 
	                    projectView.getStart_date_1().split("\\|\\|\\|") : new String[0];
	                String[] endDates = projectView.getEnd_date_1() != null ? 
	                    projectView.getEnd_date_1().split("\\|\\|\\|") : new String[0];
	                String[] projectPeriods = projectView.getProject_period_1() != null ? 
	                    projectView.getProject_period_1().split("\\|\\|\\|") : new String[0];
	                
	                // Create team members list
	                for (int i = 0; i < resourceIds.length; i++) {
	                    Map<String, String> member = new HashMap<>();
	                    member.put("resource_id_1", resourceIds[i]);
	                    member.put("resource_name_1", i < resourceNames.length ? resourceNames[i] : "");
	                    member.put("start_date_1", i < startDates.length ? startDates[i] : "");
	                    member.put("end_date_1", i < endDates.length ? endDates[i] : "");
	                    member.put("project_period_1", i < projectPeriods.length ? projectPeriods[i] : "");
	                    teamMembers.add(member);
	                }
	                md.addAttribute("teamMembers", teamMembers);
	            }
	        }
	        System.out.println("Outer Loop"+resourceId);
	        if (projectId != null) {
	            md.addAttribute("projectDetails", projectDetailsRep.getprojectDetails(projectId));
	        }
	        
	        md.addAttribute("formmode", formmode);
	    }

	    return "ProjectDetails";
	}
	@PostMapping(value = "deleteuserproject")
	@ResponseBody
	public String deleteuserproject(@RequestParam(required = false) String dataRef,
			@RequestParam(required = false) String dataRef1, Model md, HttpServletRequest rq,
			@ModelAttribute ProjectDetailsEntity ProjectDetailsEntity) {
		// bTMTroubleShoot_Rep.deletethevalue(issue_no);
		System.out.println(dataRef1 + "dataRef" + dataRef);
		ProjectDetailsEntity up = projectDetailsRep.delete1(dataRef, dataRef1);
		up.setDel_flag("Y");
		projectDetailsRep.save(up);
		System.out.println("Delete Row:" + up.getDel_flag());

		return "deleted Successfully";
	}

	@PostMapping(value = "/amcProjectDetailAdd")
	@ResponseBody
	public String amcProjectDetailAdd(@RequestBody List<ProjectDetailsEntity> projectDetailsEntities) {

		List<ProjectDetailsEntity> up = projectDetailsEntities;

		List<ProjectDetailsEntity> up1 = new ArrayList<>();
		for (ProjectDetailsEntity pro : up) {
			pro.setDel_flag("N");
			pro.setUniqueid(pro.getProject_id() + pro.getProject_name() + pro.getResource_id() + pro.getResource_id_1());
			up1.add(pro);
		}

		projectDetailsRep.saveAll(up1);

		return "Successfully Saved";
	}
	
	@PostMapping(value = "/modifyAmcProject")
	@ResponseBody
	public String modifyAmcProject(@RequestBody List<ProjectDetailsEntity> projectDetailsEntities) {

	    List<ProjectDetailsEntity> updatedList = new ArrayList<>();

	    for (ProjectDetailsEntity input : projectDetailsEntities) {

	        // Validate essential input fields
	        if (input.getProject_id() == null || input.getResource_id_1() == null) {
	            continue; // Skip incomplete entries
	        }

	        System.out.println("Id"+input.getProject_id()+"Resource Id"+ input.getResource_id());
	        // Find existing record
	        Optional<ProjectDetailsEntity> existingOpt = projectDetailsRep.findByProjectIdAndResourceId1(
	                input.getProject_id(), input.getResource_id());

	        ProjectDetailsEntity entityToUpdate = existingOpt.orElse(new ProjectDetailsEntity());

	        // Set all fields (from frontend JSON)
	        entityToUpdate.setProject_id(input.getProject_id());
	        entityToUpdate.setProject_name(input.getProject_name());
	        entityToUpdate.setStart_date(input.getStart_date());
	        entityToUpdate.setEnd_date(input.getEnd_date());
	        entityToUpdate.setProject_type(input.getProject_type());
	        entityToUpdate.setRemarks_1(input.getRemarks_1());
	        entityToUpdate.setProject_period(input.getProject_period());
	        entityToUpdate.setClient_id(input.getClient_id());
	        entityToUpdate.setClient_name(input.getClient_name());
	        entityToUpdate.setAddress(input.getAddress());
	        entityToUpdate.setCountry(input.getCountry());
	        entityToUpdate.setResource_id(input.getResource_id());
	        entityToUpdate.setResource_name(input.getResource_name());
	        entityToUpdate.setClient_type(input.getClient_type());
	        entityToUpdate.setRemarks_2(input.getRemarks_2());
	        entityToUpdate.setContact_person(input.getContact_person());
	        entityToUpdate.setDesignation_1(input.getDesignation_1());
	        entityToUpdate.setContact_person_number(input.getContact_person_number());
	        entityToUpdate.setEmail_1(input.getEmail_1());
	        entityToUpdate.setContact_person_number_2(input.getContact_person_number_2());
	        entityToUpdate.setDesignation_2(input.getDesignation_2());
	        entityToUpdate.setEmail_2(input.getEmail_2());
	        entityToUpdate.setContact_person_number_3(input.getContact_person_number_3());
	        entityToUpdate.setDesignation_3(input.getDesignation_3());
	        entityToUpdate.setEmail_3(input.getEmail_3());

	        // Project details from table 1
	        entityToUpdate.setProject_name_2(input.getProject_name_2());
	        entityToUpdate.setLocation_2(input.getLocation_2());
	        entityToUpdate.setStart_date_2(input.getStart_date_2());
	        entityToUpdate.setEnd_date_2(input.getEnd_date_2());
	        entityToUpdate.setDuration_2(input.getDuration_2());
	        entityToUpdate.setYear_2(input.getYear_2());

	        // AMC details from table 3
	        entityToUpdate.setAmc_type_3(input.getAmc_type_3());
	        entityToUpdate.setAmc_period_3(input.getAmc_period_3());
	        entityToUpdate.setStart_date_3(input.getStart_date_3());
	        entityToUpdate.setEnd_date_3(input.getEnd_date_3());
	        entityToUpdate.setProject_name_3(input.getProject_name_3());
	        entityToUpdate.setRemarks_3(input.getRemarks_3());

	        // Team member details from table 2
	        entityToUpdate.setResource_id_1(input.getResource_id_1());
	        entityToUpdate.setResource_name_1(input.getResource_name_1());
	        entityToUpdate.setStart_date_1(input.getStart_date_1());
	        entityToUpdate.setEnd_date_1(input.getEnd_date_1());
	        entityToUpdate.setProject_period_1(input.getProject_period_1());

	        // Final values
	        entityToUpdate.setDel_flag("N");


	        // Add to list
	        updatedList.add(entityToUpdate);
	    }

	    // Save all in one go
	    projectDetailsRep.saveAll(updatedList);

	    return "Successfully Updated";
	}



	@RequestMapping(value = "troubleshooting", method = { RequestMethod.GET, RequestMethod.POST })
	public String troubleshooting(@RequestParam(required = false) String formmode,
			@ModelAttribute BTMTroubleShoot_Entity bTMTroubleShoot_Entity,
			@RequestParam(required = false) String issue_no, Model md, HttpServletRequest rq) {

		String userId = (String) rq.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("Troubleshootlist", bTMTroubleShoot_Rep.getTroubleshootlist());
			md.addAttribute("formmode", "list");
		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
			md.addAttribute("ProjName", btmEmpTimeSheetRep.getprojname(userId));
			System.out.println("PROJECT NAME" + btmEmpTimeSheetRep.getprojname(userId));

			// Retrieve the entity with the greatest ISSUE_NO
			BTMTroubleShoot_Entity up1 = bTMTroubleShoot_Rep.findGreatestIssueNo();
			System.out.println("testingggggggggggggggggg" + (up1 != null ? up1.toString() : "No entity found"));
			String newIssueNo = null;
			// Directly process the single entity
			if (up1 != null) {
				// Assign the single entity to up2
				BTMTroubleShoot_Entity up2 = up1;

				// Processing the entity
				System.out.println("Processing entity: " + up2);

				// Accessing entity fields
				String issueNo = up2.getIssue_no();
				System.out.println("Issue No: " + issueNo);

				try {
					// Convert issueNo to an integer
					int issueNoInt = Integer.parseInt(issueNo);

					// Increment the issue number by 1
					issueNoInt += 1;

					// Convert back to String if needed
					newIssueNo = String.valueOf(issueNoInt);

					System.out.println("New Issue No: " + newIssueNo);
				} catch (NumberFormatException e) {
					System.out.println("Invalid issue number format: " + issueNo);
				}

			} else {
				System.out.println("No entity found with the given criteria.");
			}
			if (up1 != null) {
				String nextIssueNo = up1.getIssue_no() + 1;
				System.out.println("Next Issue No: " + nextIssueNo);
			} else {
				System.out.println("No issue found for today's date.");
			}

			md.addAttribute("issudesc", newIssueNo);

		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", "view");
			md.addAttribute("Troubleshootlist", bTMTroubleShoot_Rep.findByIdCustom(issue_no));
			System.out.println("Viewtrouble" + issue_no);

		} else if (formmode.equals("modify")) {
			md.addAttribute("Troubleshootlist", bTMTroubleShoot_Rep.findByIdCustom(issue_no));
			System.out.println("Modify Value" + issue_no);
			md.addAttribute("formmode", "modify");
		}

		return "BTMTroubleShooting";
	}

	@PostMapping(value = "delete")
	@ResponseBody
	public String delete(@RequestParam(required = false) String uniqueid, Model md, HttpServletRequest rq,
			@ModelAttribute BTMTroubleShoot_Entity bTMTroubleShoot_Entity) {
		// bTMTroubleShoot_Rep.deletethevalue(issue_no);
		System.out.println(uniqueid + "uniqueid");
		BTMTroubleShoot_Entity up = bTMTroubleShoot_Rep.delete1(uniqueid);
		up.setDel_flag("Y");
		bTMTroubleShoot_Rep.save(up);
		System.out.println("Delete Row:" + up.getDel_flag());

		return "deleted Successfully";
	}

	@RequestMapping(value = "submitfunction", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public String submitfunction(Model md, HttpServletRequest rq,
			@RequestBody List<BTMTroubleShoot_Entity> bTMTroubleShoot_Entity) {

		System.out.println("up");
		List<BTMTroubleShoot_Entity> up = bTMTroubleShoot_Entity;
		System.out.println("up" + up);
		bTMTroubleShoot_Entity.forEach(entity -> {
			entity.setDel_flag("N");
			entity.setUniqueid(entity.getIssue_no() + entity.getDescription() + entity.getOpen_date());// Set the
																										// del_flag to
																										// "Y" or any
																										// appropriate
																										// value
		});

		// Save all entities
		bTMTroubleShoot_Rep.saveAll(bTMTroubleShoot_Entity);

		bTMTroubleShoot_Rep.saveAll(up);
		return "Added Successfully";

	}

	@PostMapping("modifysubmit")
	@ResponseBody
	public String modifysubmit(@RequestParam(required = false) String issue_no, Model md, HttpServletRequest rq,
			@ModelAttribute BTMTroubleShoot_Entity bTMTroubleShoot_Entity) {

		BTMTroubleShoot_Entity exist = bTMTroubleShoot_Rep.findidmodify(bTMTroubleShoot_Entity.getIssue_no());
		System.out.println("bTMTroubleShoot_Entity" + bTMTroubleShoot_Entity.toString());
		System.out.println("bTMTroubleShoot_Entity" + bTMTroubleShoot_Entity.getPriority());
		bTMTroubleShoot_Entity.setModify_flag("Y");
		bTMTroubleShoot_Entity.setDel_flag("N");
		bTMTroubleShoot_Entity.setUniqueid(bTMTroubleShoot_Entity.getIssue_no()
				+ bTMTroubleShoot_Entity.getDescription() + bTMTroubleShoot_Entity.getOpen_date());// Set the del_flag
																									// to "Y" or any
																									// appropriate value

		bTMTroubleShoot_Rep.save(bTMTroubleShoot_Entity);

		return "Modified Successfully";

	}

	@RequestMapping(value = "troubleshootingdownload", method = RequestMethod.GET)
	@ResponseBody
	public InputStreamResource troubleshootingdownload(HttpServletResponse response,
			@RequestParam(required = false) String filetype) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		try {
			logger.info("Getting download File ");

			File repfile = projectMasterServices.gettroublepdf(filetype);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
		} catch (JRException e) {
			logger.error("Error generating TDS file", e);
		}

		return resource;
	}

	@PostMapping(value = "gstuploadexcel")
	@ResponseBody
	public String gstuploadexcel(@RequestParam("file") MultipartFile file, String screenId,
			@ModelAttribute Gstoverseas Gstoverseas, Model md, HttpServletRequest rq)
			throws FileNotFoundException, SQLException, IOException, NullPointerException {

		System.out.println("the testing   GST EXCEL UPLOAD");

		System.out.println("fileSize" + file.getSize());

		if (file.getSize() < 50000000) {
			String userid = (String) rq.getSession().getAttribute("USERID");
			String msg = projectMasterServices.Uploadgstserviceone(userid, file, userid, Gstoverseas);
			return msg;
		} else {
			return "File has not been successfully uploaded. Requires less than 128 KB size.";
		}

	}

	@RequestMapping(value = "journalEntries", method = { RequestMethod.GET, RequestMethod.POST })
	public String journalEntries(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String acct_nums, @RequestParam(required = false) String part_tran,
			@RequestParam(required = false) String part_tranid, @RequestParam(required = false) String tran_idss,
			@RequestParam(required = false) String tran_id, @RequestParam(required = false) String part_transs,
			Model md, HttpServletRequest req) {
		System.out.println("jjjjjjjjjjjjjjjjjjjjjjjjjjjjj" + part_tran);
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("jour", account_Ledger_Rep.findByjournal());
			md.addAttribute("formmode", "list");

		} else if (formmode.equals("add")) {

			md.addAttribute("part_tran", part_tran);

			String a = account_Ledger_Rep.getlast();

			String b = a.substring(2); // Use index 2 to extract after "TT"
			int c = Integer.parseInt(b) + 1;
			String d = a.substring(0, 2); // Use index 0 to 2 to keep "TT"

			// accountLedgerEntity.setTran_id(d + String.format("%03d", c));

			md.addAttribute("plusonetran", (d + String.format("%03d", c)));
			md.addAttribute("popup", chart_Acc_Rep.getlistpopup());
			// md.addAttribute("popup", account_Ledger_Rep.popup());
			// System.out.println("123456789"+account_Ledger_Rep.popup());

			md.addAttribute("formmode", "add");
		} else if (formmode.equals("add1")) {

			md.addAttribute("part_transs", part_transs);
			md.addAttribute("tran_idss", tran_idss);
			md.addAttribute("part_tran", part_tran);
			String a = account_Ledger_Rep.getlast();
			String b = a.substring(3);
			int c = Integer.parseInt(b) + 1;
			String d = a.substring(0, 3);
			md.addAttribute("plusonetran", d + c);
			md.addAttribute("formmode", "add1");
			// md.addAttribute("popup", account_Ledger_Rep.popup());
			md.addAttribute("popup", chart_Acc_Rep.getlistpopup());

		} else if (formmode.equals("massentires")) {
			md.addAttribute("formmode", "massentires");
		} else if (formmode.equals("verify")) {

			md.addAttribute("formmode", "verify");

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");

		} else if (formmode.equals("TPT")) {

			if (acct_nums.equals("1100001196")) {
				md.addAttribute("jour", BTM_TRANS_POINT_DETAILSRepo.getjourform(tran_id, part_tranid));

				BTM_TRANS_POINT_DETAILS BTM_TRANS_POINT_DETAILS = BTM_TRANS_POINT_DETAILSRepo.getjourform(tran_id,
						part_tranid);
				System.out.println(BTM_TRANS_POINT_DETAILS.getOrg_tran_id() + "BTM_TRANS_POINT_DETAILS");
				System.out.println(
						"Account number matches!" + BTM_TRANS_POINT_DETAILSRepo.getjourform(tran_id, part_tranid));
			} else {
				System.out.println("Account number does not match.");
				return "errorPage";
			}
			md.addAttribute("formmode", "TPT");

		}

		else if (formmode.equals("TPR")) {

			if (acct_nums.equals("4400004415")) {
				md.addAttribute("jourtpr1", BTM_TRANS_PARTITION_DETAILSrepo.getjourformtpr(tran_id, part_tranid));

				BTM_TRANS_PARTITION_DETAILS BTM_TRANS_PARTITION_DETAILS = BTM_TRANS_PARTITION_DETAILSrepo
						.getjourformtpr(tran_id, part_tranid);
				System.out.println(BTM_TRANS_PARTITION_DETAILS.getTran_id() + "tran_id");

				System.out.println("Account number matches!"
						+ BTM_TRANS_PARTITION_DETAILSrepo.getjourformtpr(tran_id, part_tranid));

			} else {
				System.out.println("Account number does not match.");
				return "errorPage";
			}
			md.addAttribute("formmode", "TPR");

		} else if (formmode.equals("RPT")) {

			if (acct_nums.equals("2700002710")) {
				md.addAttribute("jour", BTM_GST_MASTERREPO.getjourform1GST(tran_id, part_tranid));

				BTM_GST_MASTER BTM_GST_MASTER = BTM_GST_MASTERREPO.getjourform1GST(tran_id, part_tranid);
				System.out.println(BTM_GST_MASTER.getTran_id() + "BTM_TRANS_POINT_DETAILS");

				System.out
						.println("Account number matches!" + BTM_GST_MASTERREPO.getjourform1GST(tran_id, part_tranid));

			} else {
				System.out.println("Account number does not match.");
				return "errorPage";
			}
			md.addAttribute("formmode", "RPT");

		}

		else if (formmode.equals("modify")) {
			md.addAttribute("jour", account_Ledger_Rep.getjourform(tran_id, part_tranid));

			System.out.println(acct_nums + "acct_nummmmmmmmmmmmmmmmmmmmmmm");

			md.addAttribute("formmode", "modify");

		}

		return "JournalEntries";
	}

	@SuppressWarnings("null")
	@RequestMapping(value = "muljoursubmit", method = RequestMethod.POST)
	public String muljoursubmit(@RequestParam(required = false) String[] parttranid,
			@RequestParam(required = false) String[] acctnum, @RequestParam(required = false) String[] acctname,
			@RequestParam(required = false) String[] tranamt, @RequestParam(required = false) String[] tranparticular,
			@RequestParam(required = false) String[] ratecode, @RequestParam(required = false) String[] rate, Model md,
			HttpServletRequest rq) {

		String[] parttra = parttranid;
		String[] actno = acctnum;
		String[] actname = acctname;
		String[] tramt = tranamt;
		String[] tran = tranparticular;
		String[] raco = ratecode;
		String[] ra = rate;

		int maxlength = actno.length - 1;
		System.out.println("length" + maxlength);
		for (String a : tramt) {
			System.out.println("cred " + a);
		}

		for (int i = 0; i < actno.length - 1; i++) {
			Account_Ledger_Entity actled = new Account_Ledger_Entity();

			if (parttra != null) {
				actled.setPart_tran_id(new BigDecimal(parttra[i]));

				System.out.println("testing1:" + actled.getPart_tran_id());

			}

			actled.setAcct_num(actno[i]);
			System.out.println("testing2:" + actled.getAcct_num());
			actled.setAcct_name(actname[i]);
			System.out.println("testing3:" + actled.getAcct_name());
			actled.setTran_amt(new BigDecimal(tramt[i]));
			System.out.println("testing4:" + actled.getAcct_name());

			actled.setTran_particular(tran[i]);
			System.out.println("testing5:" + actled.getTran_particular());
			actled.setRate_code(raco[i]);
			System.out.println("6:" + actled.getRate_code());

			if (ra != null) {
				actled.setRate(new BigDecimal(ra[i]));
				System.out.println("testing7:" + actled.getRate());
			}

			account_Ledger_Rep.save(actled);
		}

		return "success";

	}

	@RequestMapping(value = "journaladd", method = RequestMethod.POST)
	@ResponseBody
	public String journaladd(@RequestParam(required = false) String ref, Account_Ledger_Entity account_Ledger_Entity)
			throws IOException, NullPointerException {
		System.out.println("the rating AGENCY>>>> ");
		Account_Ledger_Entity up = account_Ledger_Entity;
		// SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss",
		// Locale.ENGLISH);
		try {
			DecimalFormat numformate = new DecimalFormat("");
			BigDecimal PlacementSrlNo = (BigDecimal) sessionFactory.getCurrentSession()
					.createNativeQuery("SELECT SRL_NO_SEQ.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();
			String pmsrlno = numformate.format(PlacementSrlNo);
			up.setSrl_no_seq(pmsrlno);
			// account_Ledger_Rep.getsrlNo();
			// String formattedDate = formatter.format(up.getEntry_time());
			// System.out.println(formattedDate);
			// System.out.println("1234567890"+up.getEntry_time());

			// Date newDate = formatter.parse(formattedDate);
			// up.setEntry_time(newDate);
			System.out.println("hgfhgfhrghgrhfgrhfgrhfgrhfgrhf" + up.getSrl_no_seq());
			System.out.println("hgfhgfhrghgrhfgrhfgrhfgrhfgrhf" + up.getAcct_name());
			System.out.println("hgfhgfhrghgrhfgrhfgrhfgrhfgrhf" + up.getAcct_num());
			System.out.println("hgfhgfhrghgrhfgrhfgrhfgrhfgrhf" + up.getTran_id());
			System.out.println("journal entries" + up);

			System.out.println("hi it is gstss here your adding the record for TDS");

			account_Ledger_Rep.save(up);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "added successfully journalentries SALARY";

	}

	@RequestMapping(value = "modifytpt", method = RequestMethod.POST)
	@ResponseBody
	public String modifytpt(@ModelAttribute BTM_TRANS_POINT_DETAILS BTM_TRANS_POINT_DETAILS,
			@RequestParam String tran_id) {

		System.out.println("BTM_TRANS_POINT_DETAILSssssssssssssssssssssssssss" + BTM_TRANS_POINT_DETAILS);
		System.out.println("Guarantor>> " + tran_id);
		// String existing =BTM_TRANS_POINT_DETAILS.getOrg_tran_id();

		BTM_TRANS_POINT_DETAILS existingtranid = BTM_TRANS_POINT_DETAILSRepo.gettranid(tran_id);

		System.out.println("existinggggggggggggg" + existingtranid);
		if (existingtranid == null) {

			return "already exist";

		} else {
			System.out.println("it is submitting");
			BTM_TRANS_POINT_DETAILSRepo.save(BTM_TRANS_POINT_DETAILS);
		}

		return "submitted successfully";
	}

	@RequestMapping(value = "modifytpr", method = RequestMethod.POST)
	@ResponseBody
	public String modifytrm(@ModelAttribute BTM_TRANS_PARTITION_DETAILS BTM_TRANS_PARTITION_DETAILS) {

		System.out.println("BTM_TRANS_POINT_DETAILSssssssssssssssssssssssssss" + BTM_TRANS_PARTITION_DETAILS);
		System.out.println("Guarantor>> " + BTM_TRANS_PARTITION_DETAILS.getTran_id());
		String tran_id = BTM_TRANS_PARTITION_DETAILS.getTran_id();

		BTM_TRANS_PARTITION_DETAILS existingtranid = BTM_TRANS_PARTITION_DETAILSrepo.gettranidpartition(tran_id);

		System.out.println("existinggggggggggggg" + existingtranid);
		if (existingtranid == null) {

			return "Not exist";

		} else {
			System.out.println("it is submitting");
			BTM_TRANS_PARTITION_DETAILSrepo.save(BTM_TRANS_PARTITION_DETAILS);
		}

		return "submitted successfully";
	}

	@RequestMapping(value = "modifyrpt", method = RequestMethod.POST)
	@ResponseBody
	public String modifyrpt(@ModelAttribute BTM_GST_MASTER BTM_GST_MASTER) {

		System.out.println("BTM_TRANS_POINT_DETAILSssssssssssssssssssssssssss" + BTM_GST_MASTER);
		System.out.println("Guarantor>> " + BTM_GST_MASTER.getTran_id());
		String tran_id = BTM_GST_MASTER.getTran_id();
		BTM_GST_MASTER existingtranid = BTM_GST_MASTERREPO.gettranidgst(tran_id);

		System.out.println("existinggggggggggggg" + existingtranid);
		if (existingtranid == null) {

			return "already exist";

		} else {
			System.out.println("it is submitting");
			BTM_GST_MASTERREPO.save(BTM_GST_MASTER);
		}

		return "submitted successfully";
	}

	@GetMapping("popupvalueadd")
	@ResponseBody
	public Chart_Acc_Entity popupvalueadd(@RequestParam String acct_num, @RequestParam String tran_id,
			@RequestParam String part_tran_id) {
		System.out.println(part_tran_id);

		System.out.println("Guarantor>> " + tran_id);
		Chart_Acc_Entity xx = chart_Acc_Rep.getValuepop(tran_id, acct_num, part_tran_id);

		return xx;
	}

	@GetMapping("popupvaluelistpopup")
	@ResponseBody
	public List<Chart_Acc_Entity> popupvaluelistpopup(@RequestParam String a) {
		System.out.println(a);

		System.out.println("popupvaluelistpopup>> " + a);
		List<Chart_Acc_Entity> xx = chart_Acc_Rep.getValuepoplist(a);

		return xx;
	}

	@RequestMapping(value = "servermaintenance", method = { RequestMethod.GET, RequestMethod.POST })
	public String servermaintenance(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resourceId, @RequestParam(required = false) String projectId,
			@RequestParam(required = false) String changepage, @RequestParam(required = false) String emp_id,
			@RequestParam(required = false) String month, @RequestParam(required = false) String year, Model md,
			HttpServletRequest req) {

		String userId = (String) req.getSession().getAttribute("USERID");
		String username = (String) req.getSession().getAttribute("USERNAME");

		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("userId", userId);
		md.addAttribute("username", username);
		System.out.println("changepage: " + changepage);

		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");
		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", "view");
		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
		} else if (formmode.equals("windowscloud")) {
			System.out.println("change page: " + formmode + " changepage: " + changepage);

			genral_infra_details_entity genralInfraDetails = general_infra_details_repo.getgeneraldetails(changepage);

			if (changepage.equals(genralInfraDetails.getServer())) {
				System.out.println("first if condition");
				System.out.println("formmode: " + formmode);
				md.addAttribute("windows", formmode);
				md.addAttribute("listwindows", genralInfraDetails);
			}

			md.addAttribute("formmode", "windowscloud");

		} else if (formmode.equals("Linuxcloud")) {
			System.out.println("change page: " + formmode + " changepage: " + changepage);
			md.addAttribute("windows", formmode);
			md.addAttribute("formmode", "Linuxcloud");

		} else if (formmode.equals("Linuxlocal")) {
			System.out.println("change page: " + formmode + " changepage: " + changepage);
			md.addAttribute("windows", formmode);
			md.addAttribute("formmode", "Linuxlocal");

		} else if (formmode.equals("unixlocal")) {
			System.out.println("change page: " + formmode + " changepage: " + changepage);
			md.addAttribute("windows", formmode);
			md.addAttribute("formmode", "unixlocal");

		} else if (formmode.equals("yearbaseservice")) {
			System.out.println("yearbaseservice");
			System.out.println("changepage: " + changepage);
			md.addAttribute("changepage", changepage);

			List<genral_infra_details_entity> genralInfraDetailsList = general_infra_details_repo
					.getgeneraldetailsall();
			genral_infra_details_entity up1 = null;

			for (genral_infra_details_entity entity : genralInfraDetailsList) {
				up1 = entity;
				System.out.println(up1 + " for loop values of details");
			}

			if (genralInfraDetailsList != null) {
				System.out.println("first step");
				if (up1 != null && up1.getServer() != null && up1.getServer().equals(changepage)) {
					System.out.println("second step");
					genral_infra_details_entity genralInfraDetails = general_infra_details_repo
							.getgeneraldetails(changepage);
					System.out.println(genralInfraDetails.getServer());
					md.addAttribute("server", genralInfraDetails);
				}
			}

			md.addAttribute("formmode", "yearbaseservice");

		} else if (formmode.equals("servicetimesheet")) {
			md.addAttribute("year", year);
			md.addAttribute("month", month);

			System.out.println("year: " + year + " month: " + month + " empid: " + emp_id);
			genral_infra_details_entity genralInfraDetails = general_infra_details_repo.getserverdailysheet(year, month,
					emp_id);
			System.out.println("genral_infra_details_entity: " + genralInfraDetails.getServer());

			md.addAttribute("servicetimesheet", "genral_infra_details_entitys");
			md.addAttribute("formmode", "servicetimesheet");
			md.addAttribute("server", genralInfraDetails);
		}

		return "servermaintenance";
	}

	@RequestMapping(value = "servermaintenance2", method = { RequestMethod.GET, RequestMethod.POST })
	public String servermaintenance2(@RequestParam(required = false) String formmode, Model md,
			@ModelAttribute BTM_ServerDetails_Entity data, HttpServletRequest req,
			@RequestParam(required = false) String page,@RequestParam(required = false) String selectedValue) {

		String userId = (String) req.getSession().getAttribute("USERID");

		String username = (String) req.getSession().getAttribute("USERNAME");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		md.addAttribute("userId", userId);
		md.addAttribute("username", username);

		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("ServiceList", bTM_ServerDetails_Repo.getListofServices());
		} else if (formmode.equals("view")) {
			System.out.println("selectedValue"+selectedValue);
			md.addAttribute("formmode", "view");
			md.addAttribute("DataValues",bTM_ServerDetails_Repo.getServerInfo(selectedValue));
		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");

		} else if ("submit".equals(formmode)) {
			System.out.println("Add Server Details");
			data.toString();
			bTM_ServerDetails_Repo.save(data);

		} else if (formmode.equals("addMonthlyDetails")) {
			md.addAttribute("formmode", "addMonthlyDetails");

		}

		return "BTMServerDetails";
	}

	@GetMapping("/generateDates")
	public String generateDates(@RequestParam("year") int year, @RequestParam("month") String monthName,
			HttpServletRequest req) {

		// Retrieve userId from the session
		String userId = (String) req.getSession().getAttribute("USERID");
		if (userId == null || userId.isEmpty()) {
			return "Error: User ID not found in the session.";
		}

		// Generate unique ID based on year, month, and userId
		String uniqueId = year + "-" + monthName.toUpperCase() + "-" + userId;

		// Convert the month name to a Month enum
		Month month = Month.valueOf(monthName.toUpperCase());

		// Calculate the start and end dates of the given month
		LocalDate startDate = LocalDate.of(year, month, 1);
		LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());

		// Retrieve or create a new record based on the unique ID
		Daily_server_update_entity record = Daily_server_update_Repo.findById(uniqueId)
				.orElse(new Daily_server_update_entity());
		record.setUniqueid(uniqueId);

		// Prepare a DateTimeFormatter for the required format
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("EEEE");

		// Populate each column dynamically
		int dayCounter = 1;
		for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
			// Format the date, day, and fixed time values
			String formattedValue = String.format(
					"%s||%s||00:00||00:00||00:00||00:00||00:00||00:00||00:00||00:00||00:00", date.format(dateFormatter),
					date.format(dayFormatter));

			// Set the value to the corresponding column
			switch (dayCounter) {
			case 1:
				record.setDay1(formattedValue);
				break;
			case 2:
				record.setDay2(formattedValue);
				break;
			case 3:
				record.setDay3(formattedValue);
				break;
			case 4:
				record.setDay4(formattedValue);
				break;
			case 5:
				record.setDay5(formattedValue);
				break;
			case 6:
				record.setDay6(formattedValue);
				break;
			case 7:
				record.setDay7(formattedValue);
				break;
			case 8:
				record.setDay8(formattedValue);
				break;
			case 9:
				record.setDay9(formattedValue);
				break;
			case 10:
				record.setDay10(formattedValue);
				break;
			case 11:
				record.setDay11(formattedValue);
				break;
			case 12:
				record.setDay12(formattedValue);
				break;
			case 13:
				record.setDay13(formattedValue);
				break;
			case 14:
				record.setDay14(formattedValue);
				break;
			case 15:
				record.setDay15(formattedValue);
				break;
			case 16:
				record.setDay16(formattedValue);
				break;
			case 17:
				record.setDay17(formattedValue);
				break;
			case 18:
				record.setDay18(formattedValue);
				break;
			case 19:
				record.setDay19(formattedValue);
				break;
			case 20:
				record.setDay20(formattedValue);
				break;
			case 21:
				record.setDay21(formattedValue);
				break;
			case 22:
				record.setDay22(formattedValue);
				break;
			case 23:
				record.setDay23(formattedValue);
				break;
			case 24:
				record.setDay24(formattedValue);
				break;
			case 25:
				record.setDay25(formattedValue);
				break;
			case 26:
				record.setDay26(formattedValue);
				break;
			case 27:
				record.setDay27(formattedValue);
				break;
			case 28:
				record.setDay28(formattedValue);
				break;
			case 29:
				record.setDay29(formattedValue);
				break;
			case 30:
				record.setDay30(formattedValue);
				break;
			case 31:
				record.setDay31(formattedValue);
				break;
			}
			dayCounter++;
		}

		// Save the record to the database
		Daily_server_update_Repo.save(record);

		return "Dates generated and saved successfully for unique ID: " + uniqueId;
	}

	@RequestMapping(value = "/yearsubmitservice", method = RequestMethod.POST)
	@ResponseBody
	public String yearsubmitservice(@RequestParam(required = false) String emp_no, Model md, HttpServletRequest rq,
			@ModelAttribute genral_infra_details_entity genral_infra_details_entity) {
		String msg = "Added Successfully";
		System.out.println("hi" + genral_infra_details_entity.getIp_address());
		System.out.println(genral_infra_details_entity.getServer());

		if ("windowscloud".equals(genral_infra_details_entity.getServer())) {
			System.out.println("first stage of if condition");
			general_infra_details_repo.save(genral_infra_details_entity);

		} else {
			System.out.println("else condition");
			msg = "error occuring while adding datas";
		}

		return msg;

	}

	@RequestMapping(value = "/windowgeneralsubmit", method = RequestMethod.POST)
	@ResponseBody
	public String windowgeneralsubmit(@RequestParam(required = false) String emp_no, Model md, HttpServletRequest rq,
			@ModelAttribute genral_infra_details_entity genral_infra_details_entity) {

		System.out.println("hi" + genral_infra_details_entity.getIp_address());
		genral_infra_details_entity
				.setUniqueid(genral_infra_details_entity.getIp_address() + genral_infra_details_entity.getServer());

		general_infra_details_repo.save(genral_infra_details_entity);

		return "success";

	}

	@RequestMapping(value = "serverschedulesubmit", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Daily_server_update_entity> listDailyServerUpdates(
			@RequestBody List<Daily_server_update_entity> dailyServerUpdateEntities) {
		// Check if the incoming list is not null or empty
		if (dailyServerUpdateEntities == null || dailyServerUpdateEntities.isEmpty()) {
			return new ArrayList<>(); // Return an empty list if no data received
		}

		System.out.println("Audit size: " + dailyServerUpdateEntities.size());

		List<Daily_server_update_entity> newArray = new ArrayList<>();

		for (Daily_server_update_entity audit : dailyServerUpdateEntities) {
			System.out.println("audit" + audit);
			audit.setUniqueid(audit.getServer() + audit.getDate_of_service() + audit.getIp_address());
			newArray.add(audit);
			System.out.println(audit);
		}

		// Save all entities to the repository
		Daily_server_update_Repo.saveAll(newArray);

		return newArray; // Return the list of saved entities
	}

	@RequestMapping(value = "getserverscheduledata", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Daily_server_update_entity> getServerScheduleData(HttpServletRequest rq,
			@RequestParam(required = false) String serversheet) {
		// Fetch the list of server schedule details
		List<Daily_server_update_entity> dailyServerUpdateEntities = Daily_server_update_Repo
				.getschduleddetails(serversheet);

		// Check if no data is received
		if (dailyServerUpdateEntities == null || dailyServerUpdateEntities.isEmpty()) {
			System.out.println("No data received for server: " + serversheet);
			return new ArrayList<>(); // Return an empty list instead of a message
		}

		System.out.println("Audit size: " + dailyServerUpdateEntities.size());

		// Log the scheduled details
		for (Daily_server_update_entity audit : dailyServerUpdateEntities) {
			System.out.println(audit + " scheduled");
		}

		// Return the fetched data as JSON
		return dailyServerUpdateEntities;
	}

	/*
	 * @RequestMapping(value = "InventoryMaster1", method = { RequestMethod.GET,
	 * RequestMethod.POST }) public String InventoryMaster1(@RequestParam(required =
	 * false) String formmode,String headcode, Model md, HttpServletRequest req)
	 * throws ParseException {
	 * 
	 * 
	 * String userId = (String) req.getSession().getAttribute("USERID");
	 * md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
	 * md.addAttribute("menu", "BTMHeaderMenu"); if (formmode == null ||
	 * formmode.equals("view")) {
	 * 
	 * List<BTM_BAMInventorymaster_Entity> BAMInventorymaster =
	 * BTM_BAMInventryMastRep.getall(); // Fetch your data
	 * md.addAttribute("BAMInventorymaster", BAMInventorymaster);
	 * md.addAttribute("formmode", "list");
	 * 
	 * 
	 * } else if (formmode.equals("add")) {
	 * System.out.println("Formmode :"+formmode);
	 * 
	 * md.addAttribute("formmode", "add"); md.addAttribute("BAMInventorymaster", new
	 * BTM_BAMInventorymaster_Entity()); // List<Bamcategorycodemain>
	 * assetsrlno=Bamcatcodemain.getall();
	 * 
	 * // md.addAttribute("assetsrlno", assetsrlno);
	 * 
	 * }else if (formmode.equals("edit")) {
	 * 
	 * md.addAttribute("formmode", "edit"); md.addAttribute("BAMInventorymaster",
	 * BTM_BAMInventryMastRep.findById(headcode).get());
	 * 
	 * }else if (formmode.equals("verify")) {
	 * 
	 * md.addAttribute("formmode", "verify"); md.addAttribute("BAMInventorymaster",
	 * BTM_BAMInventryMastRep.findById(headcode).get());
	 * 
	 * } else {
	 * 
	 * md.addAttribute("formmode", formmode); }
	 * 
	 * return "BTM_BAMInventoryMana"; }
	 */
	@RequestMapping(value = "AccountLedger", method = { RequestMethod.GET, RequestMethod.POST })
	public String AccountLedger(@RequestParam(required = false) String formmode, Model md, HttpServletRequest req,
			@RequestParam(required = false) String acct_num) {
		System.out.println("The account " + acct_num);
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));

		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("AccountLedger", bAJAccountLedgerRepo.getList());
			md.addAttribute("formmode", "list");

		} else if (formmode.equals("ViewAccLedger")) {
			md.addAttribute("formmode", "ViewAccLedger");
			md.addAttribute("Accountvalue", bAJAccountLedgerRepo.getaccno(acct_num));
			md.addAttribute("AccList", bAJ_TrmView_Repo.getAccRecord(acct_num));

		}

		return "BTMAccountLedger";
	}

	/*
	 * <----------------------- BAM MAINTAINANCE OPTION OPERATION
	 * -------------------->
	 */

	@RequestMapping(value = "Catcodemain", method = { RequestMethod.GET, RequestMethod.POST })
	public String Catcodemain(@RequestParam(required = false) String formmode, String headcode, Model md,
			HttpServletRequest req)

			throws ParseException {
//						String EmpId = "U72900TN2017PTC115892";
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		Bamcategorycodemain newInventory = new Bamcategorycodemain();
		newInventory.setEntry_user(userId); // Set ENTRY_USER as the logged-in user
		newInventory.setModify_user(userId); // Set MODIFY_USER as the logged-in user
		newInventory.setVerify_user(userId); // Set VERIFY_USER as the logged-in user (optional for
		md.addAttribute("entryuser", newInventory);

		if (formmode == null || formmode.equals("view")) {

			List<Bamcategorycodemain> Bamcategorycodemain = Bamcatcodemain.findAllOrderedBySlNo(); // Fetch your data
			md.addAttribute("Bamcategorycodemain", Bamcategorycodemain);
			md.addAttribute("formmode", "list");

		} else if (formmode.equals("edit")) {

			md.addAttribute("formmode", "edit");
			md.addAttribute("Bamcategorycodemain", Bamcatcodemain.getbyId(headcode));

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", "add");
			md.addAttribute("Bamcategorycodemain", new Bamcategorycodemain());
		} else {

			md.addAttribute("formmode", formmode);
		}

		return "BAMCatCodeMain";
	}

	@RequestMapping(value = "deleteserialno", method = RequestMethod.POST)
	@ResponseBody
	public String deleteserialno(@RequestParam("ASN") String ASN) {

		System.out.println("The Asset is :" + ASN);
		String msg = adminOperServices.deletesrn(ASN);
		return msg;
	}

	@RequestMapping(value = "Catcodemaintainadd", method = RequestMethod.POST)
	@ResponseBody
	public String organizationMasterAdd(@RequestParam("formmode") String formmode,
			@ModelAttribute Bamcategorycodemain Bamcategorycodemain, Model md, HttpServletRequest rq,
			@RequestParam(required = false) String headcode, @RequestParam(required = false) String categorycode,
			@RequestParam(required = false) String subcategorycode) {

		System.out.println("The headcode is :" + headcode);
		System.out.println("The categorycode is :" + categorycode);
		System.out.println("The subcategorycode is :" + subcategorycode);
		String msg = adminOperServices.Catecodemaintain(Bamcategorycodemain, formmode, headcode, categorycode,
				subcategorycode);
		return msg;
	}

	
	@RequestMapping(value = "InventoryMaster", method = { RequestMethod.GET, RequestMethod.POST })
	public String InventoryMaster(@RequestParam(required = false) String formmode, String headcode, Model md,
			HttpServletRequest req) throws ParseException {

		String userId = (String) req.getSession().getAttribute("USERID");
		String username = (String) req.getSession().getAttribute("USERNAME");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));

		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("view")) {

			List<BAMInventorymaster> BAMInventorymaster = BAMInvmastrep.getall(); // Fetch your data
			md.addAttribute("BAMInventorymaster", BAMInventorymaster);
			md.addAttribute("formmode", "list");

		} else if (formmode.equals("add")) {
			System.out.println("Formmode :" + formmode);

			BAMInventorymaster newInventory = new BAMInventorymaster();
			newInventory.setENTRY_USER(userId); // Set ENTRY_USER as the logged-in user
			newInventory.setMODIFY_USER(userId); // Set MODIFY_USER as the logged-in user
			// Set VERIFY_USER as the logged-in user (optional for add)

			md.addAttribute("formmode", "add");
			md.addAttribute("BAMInventorymaster", newInventory);

			List<Bamcategorycodemain> assetsrlno = Bamcatcodemain.getall();
			List<String> srl = BAMInvmastrep.getdatas();
			md.addAttribute("lists", srl);
			md.addAttribute("assetsrlno", assetsrlno);
			String assetsrlno1 = BAMInvmastrep.findLatestAssetSerialNumber();
			md.addAttribute("assetsrlno1", assetsrlno1);

		} else if (formmode.equals("edit")) {	
			
			System.out.println("headcode"+headcode);
			
			BAMInventorymaster inventory = BAMInvmastrep.findById(headcode).get();
			
			inventory.setMODIFY_USER(userId); // Set MODIFY_USER as the logged-in user

			md.addAttribute("formmode", "edit");
			md.addAttribute("BAMInventorymaster", inventory);
			md.addAttribute("inventoryListRecords", BAM_AssetFlows_rep.getRecords(headcode));

		} else if (formmode.equals("verify")) {
			System.out.println("verify "+headcode);
			BAMInventorymaster inventory = BAMInvmastrep.findById(headcode).get();
		

			md.addAttribute("formmode", "verify");
			md.addAttribute("BAMInventorymaster", inventory);

		} else if (formmode.equals("view")) {
			System.out.println("View "+headcode);
			BAMInventorymaster inventory = BAMInvmastrep.findById(headcode).get();
			md.addAttribute("formmode", "view");
			md.addAttribute("BAMInventorymaster", inventory);

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "BAMInventoryMana";
	}
	
	@RequestMapping(value = "acliinventory", method = { RequestMethod.GET, RequestMethod.POST })
	public String acliinventory(@RequestParam(required = false) String formmode, String headcode, Model md,
			HttpServletRequest req) throws ParseException {

		String userId = (String) req.getSession().getAttribute("USERID");
		String username = (String) req.getSession().getAttribute("USERNAME");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));

		md.addAttribute("menu", "BTMHeaderMenu");

		if (formmode == null || formmode.equals("view")) {

			List<BAMInventorymaster> BAMInventorymaster = BAMInvmastrep.getall(); // Fetch your data
			md.addAttribute("BAMInventorymaster", BAMInventorymaster);
			md.addAttribute("formmode", "list");

		} else if (formmode.equals("add")) {
			System.out.println("Formmode :" + formmode);

			BAMInventorymaster newInventory = new BAMInventorymaster();
			newInventory.setENTRY_USER(userId); // Set ENTRY_USER as the logged-in user
			newInventory.setMODIFY_USER(userId); // Set MODIFY_USER as the logged-in user
			// Set VERIFY_USER as the logged-in user (optional for add)

			md.addAttribute("formmode", "add");
			md.addAttribute("BAMInventorymaster", newInventory);

			List<Bamcategorycodemain> assetsrlno = Bamcatcodemain.getall();
			List<String> srl = BAMInvmastrep.getdatas();
			md.addAttribute("lists", srl);
			md.addAttribute("assetsrlno", assetsrlno);
			String assetsrlno1 = BAMInvmastrep.findLatestAssetSerialNumber();
			md.addAttribute("assetsrlno1", assetsrlno1);

		} else if (formmode.equals("acliedit")) {	
			
			System.out.println("headcode"+headcode);
			
			BAMInventorymaster inventory = BAMInvmastrep.findById(headcode).get();
			
			inventory.setMODIFY_USER(userId); // Set MODIFY_USER as the logged-in user

			md.addAttribute("formmode", "acliedit");
			md.addAttribute("BAMInventorymaster", inventory);
			md.addAttribute("inventoryListRecords", BAM_AssetFlows_rep.getRecords(headcode));

		} else if (formmode.equals("acliverify")) {
			System.out.println("verify "+headcode);
			BAMInventorymaster inventory = BAMInvmastrep.findById(headcode).get();
		

			md.addAttribute("formmode", "acliverify");
			md.addAttribute("BAMInventorymaster", inventory);

		} else if (formmode.equals("acliview")) {
			System.out.println("View "+headcode);
			BAMInventorymaster inventory = BAMInvmastrep.findById(headcode).get();
			md.addAttribute("formmode", "acliview");
			md.addAttribute("BAMInventorymaster", inventory);

		} else {
			md.addAttribute("formmode", formmode);
		}

		return "AcliInventoryMaster";
	}

	@RequestMapping(value = "InvMastadd", method = RequestMethod.POST)
	@ResponseBody
	public String organizationMasterAdd(@RequestParam("formmode") String formmode,
			@RequestParam(required = false) String de_m, @RequestParam("depr_percent") String depr_percent,
			@RequestParam("Invtype") String Invtype, @ModelAttribute BAMInventorymaster BAMInventorymaster, Model md,
			HttpServletRequest rq, @RequestParam(required = false) String headcode,
			@RequestParam(required = false) String category_code,
			@RequestParam(required = false) String sub_category_code)
			throws KeyManagementException, NoSuchAlgorithmException {

		String userId = (String) rq.getSession().getAttribute("USERID");
		String username = (String) rq.getSession().getAttribute("USERNAME");
		System.out.println("The headcode control : " + headcode);
		System.out.println("The categorycode  : " + category_code);
		System.out.println("The subcategorycode  : " + sub_category_code);

		System.out.println(de_m);
		String msg = adminOperServices.Invmastadd(BAMInventorymaster, formmode, Invtype, userId, username, de_m,
				depr_percent, headcode, category_code, sub_category_code);
		return msg;
	}

	@RequestMapping(value = "get_serial_Inv", method = RequestMethod.GET)
	@ResponseBody
	public BAMInventorymaster get_serial_Inv(@RequestParam(required = false) String AN, Model md) {
		BAMInventorymaster en = new BAMInventorymaster();
		System.out.println("Asset Serial Number is :" + AN);
		BAMInventorymaster getlist = BAMInvmastrep.getview(AN);

		return getlist;
	}

	@RequestMapping(value = "get_serial_main", method = RequestMethod.GET)
	@ResponseBody
	public Bamcategorycodemain get_serial_main(@RequestParam(required = false) String asset_category, Model md) {

		System.out.println("Asset asset_category is :" + asset_category);
		Bamcategorycodemain getlist = Bamcatcodemain.getbyId(asset_category);
		return getlist;
	}

	@RequestMapping(value = "asset_flow", method = { RequestMethod.GET, RequestMethod.POST })
	public String asset_flow(@RequestParam(required = false) String formmode, Model md, HttpServletRequest req)
			throws ParseException {

		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));

		BAM_AssetFlows_Entity newInventory = new BAM_AssetFlows_Entity();
		newInventory.setEntry_user(userId); // Set ENTRY_USER as the logged-in user
		newInventory.setModify_user(userId); // Set MODIFY_USER as the logged-in user
		newInventory.setVerify_user(userId); // Set VERIFY_USER as the logged-in user (optional for add)

		md.addAttribute("entryuser", newInventory);

		// Fetch the list of serial numbers from BAMInvmastrep
		List<String> srl = BAMInvmastrep.getdatas();
		md.addAttribute("lists", srl);
		String assetsrlno1 = BAMInvmastrep.findLatestAssetSerialNumber();
		md.addAttribute("assetsrlno", assetsrlno1);

		/*
		 * Date lastTransferDate = BamInvtrnrep.getdatas();
		 * md.addAttribute("lastTransferDate", lastTransferDate);
		 * System.out.println("Last Transfer Date: " + lastTransferDate);
		 */
		return "BAM_Asset_Flows.html";
	}

	@RequestMapping(value = "assets_flow", method = { RequestMethod.GET, RequestMethod.POST })
	public String markOnDuty(@RequestParam(required = false) String formmode, @RequestParam(required = false) String Id,
			Model md, HttpServletRequest req) throws ParseException {
		String userId = (String) req.getSession().getAttribute("USERID");

		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		System.out.println("Asset is :" + formmode);

		BAM_AssetFlows_Entity newInventory = new BAM_AssetFlows_Entity();
		newInventory.setEntry_user(userId); // Set ENTRY_USER as the logged-in user
		newInventory.setModify_user(userId); // Set MODIFY_USER as the logged-in user
		newInventory.setVerify_user(userId); // Set VERIFY_USER as the logged-in user (optional for add)

		if (formmode == null) {
			md.addAttribute("formmode", "list"); // List

			md.addAttribute("assets", BAM_AssetFlows_rep.getdata());
		} else if ("view".equals(formmode)) {

			System.out.println("Asset is :" + Id);
			md.addAttribute("formmode", "view");

			System.out.println("Asset is :" + BAM_AssetFlows_rep.getview(Id).size());
			md.addAttribute("paramview", BAM_AssetFlows_rep.getview(Id));
			md.addAttribute("paramview1", newInventory);
		}
		return "BTMMarkOnDutys";
	}

///// DOCUMENT MANAGER
@RequestMapping(value = "Documentmanager", method = { RequestMethod.GET, RequestMethod.POST })
public String Bamdocumentmanager(@RequestParam(required = false) String formmode, String headcode, Model md,
                                  HttpServletRequest req) throws ParseException {

    String userId = (String) req.getSession().getAttribute("USERID");
    md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
    md.addAttribute("menu", "BTMHeaderMenu");

    Bamdocumentmanager newInventory = new Bamdocumentmanager();
    newInventory.setEntry_user(userId);
    newInventory.setModify_user(userId);
    md.addAttribute("entryuser", newInventory);

    if (formmode == null || formmode.equals("view")) {
        List<Bamdocumentmanager> Bamdocumentmanager = BamDocmasRep.findAll();
        md.addAttribute("Bamdocumentmanager", Bamdocumentmanager);
        md.addAttribute("formmode", "list");

    } else if (formmode.equals("edit") || formmode.equals("verify")) {
        BamDocmasRep.findById(headcode).ifPresent(bamdoc -> md.addAttribute("Bamdocumentmanager", bamdoc));
        md.addAttribute("formmode", formmode);

    } else if (formmode.equals("add")) {
        md.addAttribute("formmode", "add");
        md.addAttribute("Bamdocumentmanager", new Bamdocumentmanager());

    } else {
        md.addAttribute("formmode", formmode);
    }

    return "BAMDocumentManager";
}

@RequestMapping(value = "DocManageradd", method = RequestMethod.POST)
@ResponseBody
public String organizationMasterAdd(@RequestParam("formmode") String formmode,
                                    @ModelAttribute Bamdocumentmanager Bamdocumentmanager,
                                    @RequestParam(required = false) MultipartFile documentFile,
                                    Model md, HttpServletRequest rq) throws ParseException, IOException {

    return adminOperServices.DocManaaddedit(Bamdocumentmanager, formmode, documentFile);
}

@GetMapping("/downloadDocument")
public ResponseEntity<Resource> downloadDocument(@RequestParam String docId) {
    Optional<Bamdocumentmanager> documentOpt = BamDocmasRep.findById(docId);

    if (documentOpt.isPresent()) {
        Bamdocumentmanager document = documentOpt.get();
        String filePath = document.getDoc_location();
        if (filePath == null || filePath.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        try {
            Path path = Paths.get(filePath);
            if (!Files.exists(path)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            Resource resource = new UrlResource(path.toUri());

            String contentType = Files.probeContentType(path);
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + path.getFileName().toString() + "\"")
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}


	@RequestMapping(value = "getmonthgrn", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Object[]> getmonthgrn(@RequestParam(required = false) String selectedDate, HttpServletRequest req) {
		System.out.println("hiiiiiiiiiiiiiiiiiiiiii");
		String userId = (String) req.getSession().getAttribute("USERID");

		// Get the data from the repository

		System.out.println(selectedDate + "selectedDate");
		List<Object[]> results = invoiceMasterRep.getgrndate(selectedDate);
		for (Object[] getresult : results) {
			System.out.println(getresult + "getresult");
			String empName = (String) getresult[0]; // EMP_NAME at index 0
			String grnNo = (String) getresult[1]; // GRN_NO at index 1
			Date poDeliveryDate = (Date) getresult[2]; // PO_DELIVERY_DATE at index 2
			String grnDate = (String) getresult[3]; // GRN_DATE at index 3
			String invNo = (String) getresult[4]; // INV_NO at index 4
			Date invDate = (Date) getresult[5]; // INV_DATE at index 5
			String vendor = (String) getresult[6]; // VENDOR at index 6
			String grnAmt = (String) getresult[7]; // GRN_AMT at index 7 (assuming it's a numeric value)
			String invIgst = (String) getresult[8];
			String Po_id = (String) getresult[9];// INV_IGST at index 8 (assuming it's a numeric value)

			// Print all the values
			System.out.println("EMP_NAME: " + empName);
			System.out.println("GRN_NO: " + grnNo);
			System.out.println("PO_DELIVERY_DATE: " + poDeliveryDate);
			System.out.println("GRN_DATE: " + grnDate);
			System.out.println("INV_NO: " + invNo);
			System.out.println("INV_DATE: " + invDate);
			System.out.println("VENDOR: " + vendor);
			System.out.println("GRN_AMT: " + grnAmt);
			System.out.println("INV_IGST: " + invIgst);
			System.out.println("Po_id: " + Po_id);
		}
		return results;

		// Convert the query results to a list of InvoiceMaster objects

	}

	@PostMapping(value = "uploadpdf")
	@ResponseBody // Ensure this annotation is used to return plain text response
	public String uploadPDF(@RequestParam("file") MultipartFile file) {
		System.out.println("Into the first Stage");
		if (file.isEmpty()) {
			return "Please upload a PDF file";
		}
		try {
			System.out.println("Into the try block");
			String result = excelUploadService.processPDF(file);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "Error: " + e.getMessage();
		}
	}

	@RequestMapping(value = "getmonthremittence", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public List<Object[]> getmonthremittence(@RequestParam(required = false) String selectedDate,
			HttpServletRequest req) {
		System.out.println("hiiiiiiiiiiiiiiiiiiiiii");
		String userId = (String) req.getSession().getAttribute("USERID");

		// Get the data from the repository

		System.out.println(selectedDate + "selectedDate");
		List<Object[]> results = invoiceMasterRep.getRemittanceData(selectedDate);
		for (Object[] getresult : results) {
			System.out.println(getresult + "getresult");

			String invNo = (String) getresult[0];
			String REMIT_AMT = (String) getresult[1];
			Date REMIT_DATE = (Date) getresult[2];
			String TDS_REC = (String) getresult[3];
			String CREDIT_NOTE = (String) getresult[4];
			Date CN_DATE = (Date) getresult[5];
			String GRN_AMT = (String) getresult[6];// INV_NO at index 4
			String REMITENCE_REFERENCE = (String) getresult[7];

			// Print all the values

			System.out.println("INV_NO: " + invNo);
			System.out.println("REMIT_AMT: " + REMIT_AMT);
			System.out.println("REMIT_DATE: " + REMIT_DATE);
			System.out.println("TDS_REC: " + TDS_REC);
			System.out.println("CREDIT_NOTE: " + CREDIT_NOTE);
			System.out.println("CN_DATE: " + CN_DATE);
			System.out.println("GRN_AMT: " + GRN_AMT);
			System.out.println("REMITENCE_REFERENCE: " + REMITENCE_REFERENCE);
		}

		return results;

		// Convert the query results to a list of InvoiceMaster objects

	}

	@PostMapping(value = "uploadpdfsp")
	@ResponseBody // Ensure this annotation is used to return plain text response
	public String uploadPDFsp(@RequestParam("file") MultipartFile file) {
		System.out.println("Into the first Stage");
		if (file.isEmpty()) {
			return "Please upload a PDF file";
		}
		try {
			System.out.println("Into the try block");
			String result = excelUploadService.processPDFSP(file);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "Error: " + e.getMessage();
		}
	}

	@RequestMapping(value = "admininq", method = { RequestMethod.GET, RequestMethod.POST })
	public String admininq(Model md, HttpServletRequest req, @RequestParam(required = false) String formmode,
			@RequestParam(required = false) String tranId, @RequestParam(required = false) String partTranId,
			@RequestParam(required = false) String acct_num) {
		System.out.println("tranId" + tranId + "partTranId" + partTranId + "acct_num" + acct_num);
		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));

		LocalDate currentDate = LocalDate.now();
		LocalDate previousDate = currentDate.minusDays(1);
		Date date = Date.from(previousDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String formattedDate = dateFormat.format(date);
		System.out.println("formattedDate" + formattedDate);

		if (formmode == null || formmode.equals("add")) {
			md.addAttribute("formmode", "add");
			md.addAttribute("date1", formattedDate);
			md.addAttribute("date", bAJ_TrmView_Repo.senddate(formattedDate));

		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", "view");
			md.addAttribute("jour", bAJ_TrmView_Repo.findByjournalvalues(tranId));
			md.addAttribute("ledgervalues", bAJ_TrmView_Repo.getValuepopvalues(tranId, acct_num, partTranId));
			md.addAttribute("currentPartTran", partTranId);
			System.out.println("part_tran_id" + partTranId);
			md.addAttribute("maxPartTran", bAJ_TrmView_Repo.maxPartranID(tranId));
			System.out.println("bAJ_TrmView_Repo.maxPartranID(partTranId)" + bAJ_TrmView_Repo.maxPartranID(partTranId));
		}

		return "Admin_Inquire";

	}

	@GetMapping("/gettranvalue")
	@ResponseBody
	public Map<String, String> gettranvalue(@RequestParam(required = false) String acctnum, Model md) {
		System.out.println("Acct number5: " + acctnum);

		Map<String, String> response = new HashMap<>();

		List<BAJ_TrmView_Entity> results = bAJ_TrmView_Repo.getvalue(acctnum);

		if (!results.isEmpty()) {
			BAJ_TrmView_Entity firstResult = results.get(0);
			response.put("acct_num", firstResult.getAcct_num());
			response.put("acct_name", firstResult.getAcct_name());
			System.out.println("Account Number: " + firstResult.getAcct_num());
			System.out.println("Account Name: " + firstResult.getAcct_name());
		} else {
			response.put("acct_num", "");
			response.put("acct_name", "");
		}

		return response;
	}

	@GetMapping("/getledgerdata")
	@ResponseBody
	public List<BAJ_TrmView_Entity> getLedgerData(@RequestParam(required = false) String acctnum) {
		System.out.println("Account number for ledger data: " + acctnum);

		// Fetch ledger data from repository
		List<BAJ_TrmView_Entity> results = bAJ_TrmView_Repo.getLedgerEntries(acctnum);
		return results;
	}

	@GetMapping("/setlist2")
	@ResponseBody
	public List<BAJ_TrmView_Entity> setlist2(@RequestParam(required = false) String acctnum) {
		System.out.println("Account number for ledger data: " + acctnum);

		// Initialize the results list
		List<BAJ_TrmView_Entity> results = new ArrayList<>();

		try {
			// Fetch ledger data from repository
			if (acctnum != null && !acctnum.trim().isEmpty()) {
				results = bAJ_TrmView_Repo.getLedgerEntries2(acctnum);
			} else {
				System.out.println("Account number is null or empty.");
			}
		} catch (Exception e) {
			// Log the error
			System.err.println("Error fetching ledger data: " + e.getMessage());
			e.printStackTrace();
		}

		return results;
	}

	@GetMapping("/setlist3")
	@ResponseBody
	public List<BAJ_TrmView_Entity> setlist3(@RequestParam(required = false) String acctnum) {
		System.out.println("Account number for ledger data: " + acctnum);

		// Initialize the results list
		List<BAJ_TrmView_Entity> results = new ArrayList<>();

		try {
			// Fetch ledger data from repository
			if (acctnum != null && !acctnum.trim().isEmpty()) {
				results = bAJ_TrmView_Repo.getLedgerEntries3(acctnum);
			} else {
				System.out.println("Account number is null or empty.");
			}
		} catch (Exception e) {
			// Log the error
			System.err.println("Error fetching ledger data: " + e.getMessage());
			e.printStackTrace();
		}

		return results;
	}

	@GetMapping("/getfiveValues")
	@ResponseBody
	public List<BAJ_TrmView_Entity> getfiveValues() {
		System.out.println("Account Five Date ");

		List<String> acctNumbers = Arrays.asList("2100002160", "2100002164", "2100002165", "2100002166", "2100002167");
		List<BAJ_TrmView_Entity> results = bAJ_TrmView_Repo.getFiveValues(acctNumbers);

		return results;
	}

	@GetMapping("/getThreeValues")
	@ResponseBody
	public List<BAJ_TrmView_Entity> getThreeValues() {
		System.out.println("Account Five Date ");

		List<String> acctNumbers = Arrays.asList("2100002180", "6200006215", "6200006216");
		List<BAJ_TrmView_Entity> results = bAJ_TrmView_Repo.getThreeValues(acctNumbers);
		return results;
	}

	@GetMapping("/getTwoValues")
	@ResponseBody
	public List<BAJ_TrmView_Entity> getTwoValues() {
		System.out.println("Account Five Date ");

		List<String> acctNumbers = Arrays.asList("2100002185", "6200006217");
		List<BAJ_TrmView_Entity> results = bAJ_TrmView_Repo.getTwoValues(acctNumbers);
		return results;
	}

	@GetMapping("/getFourValues")
	@ResponseBody
	public List<BAJ_TrmView_Entity> getFourValues() {
		System.out.println("Account Five Date ");

		List<String> acctNumbers = Arrays.asList("4400004415", "2700002710", "1100001199", "1100001196");
		List<BAJ_TrmView_Entity> results = bAJ_TrmView_Repo.getFourValues(acctNumbers);
		return results;
	}

	@RequestMapping(value = "editspinvoice", method = RequestMethod.POST)
	@ResponseBody
	public String editSpInvoice(@ModelAttribute PlacementMaintenance placementMaintenance,
			@RequestParam(required = false) String inv_no) {
		System.out.println("First step into the endpint" + placementMaintenance.getPo_id());
		PlacementMaintenance exist = placementmaintenancerep.getpodetails_exist(placementMaintenance.getPo_id());
		System.out.println("sp_inv_no" + placementMaintenance.getSp_inv_no());
		System.out.println("sp_inv_date" + placementMaintenance.getSp_inv_date());
		// Check if the retrieved object exists in the database
		if (exist == null) {
			return "No data available in database";
		}

		// Update only the fields sp_inv_no and sp_inv_date

		if (placementMaintenance.getSp_inv_no() == null) {
			System.out.println("sp_inv_no is null, setting to null in the database.");
			exist.setSp_inv_no(null);
		} else {
			System.out.println("sp_inv_no has a value: " + placementMaintenance.getSp_inv_no());
			exist.setSp_inv_no(placementMaintenance.getSp_inv_no());
		}

		try {
			// Adjust the format to the expected Oracle DB format: yyyy-MM-dd
			SimpleDateFormat dbDateFormat = new SimpleDateFormat("dd-MM-yyyy");

			if (placementMaintenance.getSp_inv_date() != null) {
				// Format the incoming date to match the DB's expected format
				String formattedDateString = dbDateFormat.format(placementMaintenance.getSp_inv_date());
				Date formattedDate = dbDateFormat.parse(formattedDateString);
				System.out.println("Formatted sp_inv_date: " + formattedDate);
				exist.setSp_inv_date(formattedDate);
			} else {
				System.out.println("sp_inv_date is null, setting to null in the database.");
				exist.setSp_inv_date(null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Error while formatting date: " + e.getMessage();
		}

		// Save the updated object back to the database
		placementMaintenanceRep.save(exist);

		// Debugging to verify changes

		return "Submitted successfully";
	}

	@Autowired
	Event_manager_details_Repo event_manager_details_Repo;

	@Autowired
	Event_manager_details_Repo2 event_manager_details_Repo2;

	@RequestMapping(value = "eventManager", method = { RequestMethod.GET, RequestMethod.POST })
	public String eventManager(Model md, HttpServletRequest req, @RequestParam(required = false) String formmode,
			@ModelAttribute Event_manager_details_Entity data, @ModelAttribute Event_manager_details_Entity2 data1,
			@RequestParam(required = false) String eventId, @RequestParam(required = false) String Service_No,
			BindingResult result) {

		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			md.addAttribute("eventlist", event_manager_details_Repo.geteventvalues());

		} else if (formmode.equals("listservice")) {

			md.addAttribute("formmode", formmode);

			md.addAttribute("eventlist2", event_manager_details_Repo2.geteventvalues());

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", formmode);

			md.addAttribute("eventlist", event_manager_details_Repo.geteventNO1());

		} else if (formmode.equals("Service")) {

			md.addAttribute("formmode", formmode);
			md.addAttribute("ServiceNO", event_manager_details_Repo2.getServiceNo());

		} else if (formmode.equals("Delete")) {
			md.addAttribute("formmode", "Delete");

			md.addAttribute("adminProfileManager", event_manager_details_Repo.getrecords(eventId));

		}

		else if ("submit".equals(formmode)) {
			data.setDelFlg("N");
			data.setModifyFlg("N");
			data.setEntityFlg("Y");

			if (data.getCurrentDate() != null) {
				try {
					SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
					Date parsedDate = inputDateFormat.parse(data.getCurrentDate().toString());
					SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String formattedDate = outputDateFormat.format(parsedDate);

					data.setCurrentDate(outputDateFormat.parse(formattedDate));
				} catch (ParseException e) {
					result.rejectValue("currentDate", "invalid.date", "Invalid date format. Please use dd/MM/yyyy.");
					return "errorPage"; // Return to an error page or handle accordingly
				}
			}
			Set<String> entityFieldNames = getEntityFieldNames(Event_manager_details_Entity.class);
			Set<String> dataFieldNames = getObjectFieldNames(data);

			event_manager_details_Repo.save(data);

		} else if ("Servicesubmit".equals(formmode)) {
			System.out.println("hhkdashdka");
			data1.setDelFlg("N");
			data1.setModifyFlg("N");
			data1.setEntityFlg("Y");
			event_manager_details_Repo2.save(data1);

		} else if (formmode.equals("Serviceview")) {
			md.addAttribute("formmode", "Serviceview");
			System.out.println("Serviceview" + Service_No);
			List<Event_manager_details_Entity2> obj = event_manager_details_Repo2.getserviceRecords(Service_No);
			md.addAttribute("serviceview", obj);

		} else if (formmode.equals("servicemodify")) {
			md.addAttribute("formmode", "servicemodify");
			List<Event_manager_details_Entity2> obj = event_manager_details_Repo2.getserviceRecords(Service_No);
			md.addAttribute("serviceview", obj);

		} else if (formmode.equals("view") || formmode.equals("modify")) {
			List<Event_manager_details_Entity> obj = event_manager_details_Repo.getrecords(eventId);
			md.addAttribute("eventlist", obj);
			if (formmode.equals("view")) {
				md.addAttribute("formmode", "view");

			} else {

				md.addAttribute("formmode", "modify");
				md.addAttribute("adminProfileManager", event_manager_details_Repo.getrecords(eventId));
			}
		} else if (formmode.equals("upload")) {

			md.addAttribute("formmode", formmode);

		} else if (formmode.equals("listofFeedback")) {

			md.addAttribute("formmode", "listofFeedback");
			md.addAttribute("eventlist", event_manager_details_Repo.geteventvalues());

		}

		return "EventManager.html";
	}

	private Set<String> getEntityFieldNames(Class<?> entityClass) {
		Set<String> fieldNames = new HashSet<>();
		for (java.lang.reflect.Field field : entityClass.getDeclaredFields()) {
			fieldNames.add(field.getName());
		}
		return fieldNames;
	}

	private Set<String> getObjectFieldNames(Object obj) {
		Set<String> fieldNames = new HashSet<>();
		for (java.lang.reflect.Field field : obj.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			try {
				Object value = field.get(obj);
				if (value != null) { // Consider only non-null fields
					fieldNames.add(field.getName());
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return fieldNames;
	}

	/*
	 * private Set<String> getObjectFieldNames(Object obj) { Set<String> fieldNames
	 * = new HashSet<>(); for (java.lang.reflect.Field field :
	 * obj.getClass().getDeclaredFields()) { fieldNames.add(field.getName()); }
	 * return fieldNames; }
	 */
	@Autowired
	Employee_Onboarding_Repo employee_Onboarding_repo;

	@RequestMapping(value = "employeeDox", method = { RequestMethod.GET, RequestMethod.POST })
	public String employeeDox(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req,
			@RequestParam(required = false) String employee_id) throws ParseException {

		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		System.out.println("employee_id" + employee_id);

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			List<Employee_Onboarding_Entity> data = employee_Onboarding_repo.getAllProfiles1();
			md.addAttribute("adminProfileManagerLis", data);

		} else if (formmode.equals("add")) {

			md.addAttribute("formmode", "add");
			md.addAttribute("desi", btmRefCodeMasterRep.getDesigList());
			md.addAttribute("blood", btmRefCodeMasterRep.getbloodList());
			md.addAttribute("gender", btmRefCodeMasterRep.getgenderList());
			md.addAttribute("status", btmRefCodeMasterRep.getmaritalList());
			md.addAttribute("loc", btmRefCodeMasterRep.getlocationList());
			md.addAttribute("qual", btmRefCodeMasterRep.getqualificationList());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			/*
			 * md.addAttribute("adminProfileManager",
			 * Employee_Onboarding_repo.getProfilelist());
			 */
			md.addAttribute("adminProfileManager", employee_Onboarding_repo.getProfileByEmployeeId(employee_id));

		} else if (formmode.equals("Modify")) {
			md.addAttribute("formmode", "Modify");
			md.addAttribute("desi", btmRefCodeMasterRep.getDesigList());
			md.addAttribute("blood", btmRefCodeMasterRep.getbloodList());
			md.addAttribute("gender", btmRefCodeMasterRep.getgenderList());
			md.addAttribute("status", btmRefCodeMasterRep.getmaritalList());
			md.addAttribute("loc", btmRefCodeMasterRep.getlocationList());
			md.addAttribute("qual", btmRefCodeMasterRep.getqualificationList());
			/*
			 * md.addAttribute("adminProfileManager",
			 * Employee_Onboarding_repo.getProfilelist());
			 */
			md.addAttribute("adminProfileManager", employee_Onboarding_repo.getProfileByEmployeeId(employee_id));

		}

		else if (formmode.equals("verify")) {

			md.addAttribute("formmode", "verify");

			md.addAttribute("adminProfileManager", employee_Onboarding_repo.getProfileByEmployeeId(employee_id));

		} else if (formmode.equals("Delete")) {

			md.addAttribute("formmode", "Delete");

			md.addAttribute("adminProfileManager", employee_Onboarding_repo.getProfileByEmployeeIddel(employee_id));

		}
		return "Employeedocx";
	}

	@RequestMapping(value = "employeeDoxmodifyview", method = { RequestMethod.GET, RequestMethod.POST })
	public String employeeDoxmodifyview(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req,
			@RequestParam(required = false) String employee_id) throws ParseException {

		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		System.out.println("employee_id" + employee_id);

		String modifyview = employee_Onboarding_repo.allowviewormodify(employee_id);
		System.out.println("modifyview" + modifyview);

		if (modifyview == null || "N".equalsIgnoreCase(modifyview)) {
			md.addAttribute("formmode", "view");
			md.addAttribute("adminProfileManager", employee_Onboarding_repo.getProfileByEmployeeId(employee_id));
		} else {
			md.addAttribute("formmode", "Modify");
			md.addAttribute("desi", btmRefCodeMasterRep.getDesigList());
			md.addAttribute("blood", btmRefCodeMasterRep.getbloodList());
			md.addAttribute("gender", btmRefCodeMasterRep.getgenderList());
			md.addAttribute("status", btmRefCodeMasterRep.getmaritalList());
			md.addAttribute("loc", btmRefCodeMasterRep.getlocationList());
			md.addAttribute("qual", btmRefCodeMasterRep.getqualificationList());
			md.addAttribute("adminProfileManager", employee_Onboarding_repo.getProfileByEmployeeId(employee_id));
		}

		return "Employeedocx";
	}

	@RequestMapping(value = "employeeDoxLV", method = { RequestMethod.GET, RequestMethod.POST })
	public String employeeDoxLV(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String resId, @RequestParam(required = false) Optional<Integer> page,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req,
			@RequestParam(required = false) String employee_id) throws ParseException {

		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list2")) {

			md.addAttribute("formmode", "list2");
			List<Employee_Onboarding_Entity> data = employee_Onboarding_repo.getAllProfiles1();
			md.addAttribute("adminProfileManagerLis", data);

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			/*
			 * md.addAttribute("adminProfileManager",
			 * Employee_Onboarding_repo.getProfilelist());
			 */
			md.addAttribute("adminProfileManager", employee_Onboarding_repo.getProfileByEmployeeId(employee_id));

		}
		return "Employeedocx";
	}

	@RequestMapping(value = "EmployeedetailssDoc", method = RequestMethod.POST)
	@ResponseBody
	public String adminAssociateAdd(@RequestParam(required = false) MultipartFile document_upload1,
			@RequestParam(required = false) MultipartFile document_upload2,
			@RequestParam(required = false) MultipartFile document_upload3,
			@RequestParam(required = false) MultipartFile document_upload4,
			@RequestParam(required = false) MultipartFile document_upload5,
			@RequestParam(required = false) MultipartFile document_upload6,
			@RequestParam(required = false) MultipartFile document_upload7,
			@RequestParam(required = false) MultipartFile document_upload8,
			@RequestParam(required = false) MultipartFile document_upload9,
			@RequestParam(required = false) MultipartFile document_upload10, @RequestParam String employee_id, // Add
																												// other
																												// fields
																												// as
																												// needed
			@RequestParam String document_no1, @RequestParam String document_no2, @RequestParam String document_no3,
			@RequestParam String document_no4, @RequestParam String document_no5, @RequestParam String document_no6,
			@RequestParam String document_no7, @RequestParam String document_no8, @RequestParam String document_no9,
			@RequestParam String document_no10, Model md, HttpServletRequest rq) {

		System.out.println("First step in the controller");

		try {

			System.out.println(document_no1 + "77");
			Optional<Employee_Onboarding_Entity> existingEmpOpt = employee_Onboarding_repo.findById(employee_id);
			System.out.println("existingEmpOpt: " + existingEmpOpt);
			if (existingEmpOpt.isPresent()) {
				Employee_Onboarding_Entity existingEmp = existingEmpOpt.get();

				// Update or add documents based on document_no
				updateOrAddDocument(existingEmp, document_upload1, document_no1, 1);
				updateOrAddDocument(existingEmp, document_upload2, document_no2, 2);
				updateOrAddDocument(existingEmp, document_upload3, document_no3, 3);
				updateOrAddDocument(existingEmp, document_upload4, document_no4, 4);
				updateOrAddDocument(existingEmp, document_upload5, document_no5, 5);
				updateOrAddDocument(existingEmp, document_upload6, document_no6, 6);
				updateOrAddDocument(existingEmp, document_upload7, document_no7, 7);
				updateOrAddDocument(existingEmp, document_upload8, document_no8, 8);
				updateOrAddDocument(existingEmp, document_upload9, document_no9, 9);
				updateOrAddDocument(existingEmp, document_upload10, document_no10, 10);

				// Save the updated entity
				employee_Onboarding_repo.save(existingEmp);
				return "Files saved successfully";
			} else {
				return "Error: Employee not found";
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "Error: Unable to process files";
		}
	}

	private void updateOrAddDocument(Employee_Onboarding_Entity emp, MultipartFile file, String documentNo,
			int documentNumber) throws IOException {
		if (file != null && !file.isEmpty()) {
			// Convert MultipartFile to byte[]
			byte[] fileBytes = file.getBytes();
			String originalFilename = file.getOriginalFilename();

			// Update the corresponding document fields
			switch (documentNumber) {
			case 1:
				emp.setDocument_upload1(fileBytes);
				emp.setDocument_upload_format1(originalFilename);
				emp.setDocument_no1(documentNo);
				break;
			case 2:
				emp.setDocument_upload2(fileBytes);
				emp.setDocument_upload_format2(originalFilename);
				emp.setDocument_no2(documentNo);
				break;
			case 3:
				emp.setDocument_upload3(fileBytes);
				emp.setDocument_upload_format3(originalFilename);
				emp.setDocument_no3(documentNo);
				break;
			case 4:
				emp.setDocument_upload4(fileBytes);
				emp.setDocument_upload_format4(originalFilename);
				emp.setDocument_no4(documentNo);
				break;
			case 5:
				emp.setDocument_upload5(fileBytes);
				emp.setDocument_upload_format5(originalFilename);
				emp.setDocument_no5(documentNo);
				break;
			case 6:
				emp.setDocument_upload6(fileBytes);
				emp.setDocument_upload_format6(originalFilename);
				emp.setDocument_no6(documentNo);
				break;
			case 7:
				emp.setDocument_upload7(fileBytes);
				emp.setDocument_upload_format7(originalFilename);
				emp.setDocument_no7(documentNo);
				break;
			case 8:
				emp.setDocument_upload8(fileBytes);
				emp.setDocument_upload_format8(originalFilename);
				emp.setDocument_no8(documentNo);
				break;
			case 9:
				emp.setDocument_upload9(fileBytes);
				emp.setDocument_upload_format9(originalFilename);
				emp.setDocument_no9(documentNo);
				break;
			case 10:
				emp.setDocument_upload10(fileBytes);
				emp.setDocument_upload_format10(originalFilename);
				emp.setDocument_no10(documentNo);
				break;
			}
		}
	}

	@RequestMapping(value = "Employeedetailssadd", method = RequestMethod.POST)
	@ResponseBody
	public String Employeedetailssadd(@ModelAttribute Employee_Onboarding_Entity emp,
			@RequestParam(required = false) List<MultipartFile> documents, Model md, HttpServletRequest rq) {
		System.out.println("First step in the controller");

		try {
			// Loop through the documents list and assign to corresponding fields
			for (int i = 0; i < documents.size(); i++) {
				MultipartFile file = documents.get(i);
				if (file != null && !file.isEmpty()) {

					String contentType = file.getContentType();
					byte[] fileBytes = file.getBytes();
					String originalFilename = file.getOriginalFilename();
					System.out.println(originalFilename + "originalFilenameoriginalFilenameoriginalFilename");

					// Set the file content to the corresponding document field in the entity
					switch (i) {
					case 0:
						emp.setDocument_upload1(fileBytes);
						emp.setDocument_upload_format1(originalFilename);
						break;
					case 1:
						emp.setDocument_upload2(fileBytes);
						emp.setDocument_upload_format2(originalFilename);

						break;
					case 2:
						emp.setDocument_upload3(fileBytes);
						emp.setDocument_upload_format3(originalFilename);

						break;
					case 3:
						emp.setDocument_upload4(fileBytes);
						emp.setDocument_upload_format4(originalFilename);

						break;
					case 4:
						emp.setDocument_upload5(fileBytes);
						emp.setDocument_upload_format5(originalFilename);

						break;
					case 5:
						emp.setDocument_upload6(fileBytes);
						emp.setDocument_upload_format6(originalFilename);

						break;
					case 6:
						emp.setDocument_upload7(fileBytes);
						emp.setDocument_upload_format7(originalFilename);

						break;
					case 7:
						emp.setDocument_upload8(fileBytes);
						emp.setDocument_upload_format8(originalFilename);

						break;
					case 8:
						emp.setDocument_upload9(fileBytes);
						emp.setDocument_upload_format9(originalFilename);

						break;
					case 9:
						emp.setDocument_upload10(fileBytes);
						emp.setDocument_upload_format10(originalFilename);

						break;
					}

				}
			}
			emp.setModify_flg("N");
			emp.setDel_flg("N");
			emp.setEntity_flg("N");
			emp.setVerify_flg("N");
			// Save the entity
			employee_Onboarding_repo.save(emp);
			return "Files saved successfully";
		} catch (IOException e) {
			e.printStackTrace();
			return "Error: Unable to process files";
		}
	}

	@RequestMapping(value = "Employeedetailsscol", method = RequestMethod.POST)
	@ResponseBody
	public String Employeedetailss(@ModelAttribute Employee_Onboarding_Entity emp) {
		if (emp.getEmployee_id() == null) {
			return "Error: Employee ID is missing!";
		}

		// Find existing employee record safely
		Optional<Employee_Onboarding_Entity> existingEmpOpt = employee_Onboarding_repo.findById(emp.getEmployee_id());

		if (existingEmpOpt.isPresent()) {
			Employee_Onboarding_Entity existingEmp = existingEmpOpt.get();

			// Retain existing document details
			retainExistingDocuments(emp, existingEmp);

			// Set default values
			emp.setDel_flg("N");
			emp.setEntity_flg("Y");
			emp.setVerify_flg("N");

			// Save updated employee data
			employee_Onboarding_repo.save(emp);
			return "Success";
		} else {
			return "Error: Employee record not found!";
		}
	}

	// Utility function to retain document data
	private void retainExistingDocuments(Employee_Onboarding_Entity emp, Employee_Onboarding_Entity existingEmp) {
		emp.setDocument_upload1(existingEmp.getDocument_upload1());
		emp.setDocument_upload_format1(existingEmp.getDocument_upload_format1());

		emp.setDocument_upload2(existingEmp.getDocument_upload2());
		emp.setDocument_upload_format2(existingEmp.getDocument_upload_format2());

		emp.setDocument_upload3(existingEmp.getDocument_upload3());
		emp.setDocument_upload_format3(existingEmp.getDocument_upload_format3());

		emp.setDocument_upload4(existingEmp.getDocument_upload4());
		emp.setDocument_upload_format4(existingEmp.getDocument_upload_format4());

		emp.setDocument_upload5(existingEmp.getDocument_upload5());
		emp.setDocument_upload_format5(existingEmp.getDocument_upload_format5());

		emp.setDocument_upload6(existingEmp.getDocument_upload6());
		emp.setDocument_upload_format6(existingEmp.getDocument_upload_format6());

		emp.setDocument_upload7(existingEmp.getDocument_upload7());
		emp.setDocument_upload_format7(existingEmp.getDocument_upload_format7());

		emp.setDocument_upload8(existingEmp.getDocument_upload8());
		emp.setDocument_upload_format8(existingEmp.getDocument_upload_format8());

		emp.setDocument_upload9(existingEmp.getDocument_upload9());
		emp.setDocument_upload_format9(existingEmp.getDocument_upload_format9());

		emp.setDocument_upload10(existingEmp.getDocument_upload10());
		emp.setDocument_upload_format10(existingEmp.getDocument_upload_format10());
	}

	@RequestMapping(value = "Employeedetailss", method = RequestMethod.POST)
	@ResponseBody
	public String Employeedetailss(@RequestParam(required = false) MultipartFile document_upload1,
			@RequestParam(required = false) MultipartFile document_upload2,
			@RequestParam(required = false) MultipartFile document_upload3,
			@RequestParam(required = false) MultipartFile document_upload4,
			@RequestParam(required = false) MultipartFile document_upload5,
			@RequestParam(required = false) MultipartFile document_upload6,
			@RequestParam(required = false) MultipartFile document_upload7,
			@RequestParam(required = false) MultipartFile document_upload8,
			@RequestParam(required = false) MultipartFile document_upload9,
			@RequestParam(required = false) MultipartFile document_upload10, @RequestParam String employee_id, // Add
																												// other
																												// fields
																												// as
																												// needed
			@RequestParam String document_no1, @RequestParam String document_no2, @RequestParam String document_no3,
			@RequestParam String document_no4, @RequestParam String document_no5, @RequestParam String document_no6,
			@RequestParam String document_no7, @RequestParam String document_no8, @RequestParam String document_no9,
			@RequestParam String document_no10, Model md, HttpServletRequest rq) {

		System.out.println("First step in the contr888888888888888888888888888oller");

		try {
			// Fetch existing data from the database
			Optional<Employee_Onboarding_Entity> existingEmpOpt = employee_Onboarding_repo.findById(employee_id);
			System.out.println("existingEmpOpt: " + document_no1);
			if (existingEmpOpt.isPresent()) {
				Employee_Onboarding_Entity existingEmp = existingEmpOpt.get();

				// Update or add documents based on document_no
				updateOrAddDocument(existingEmp, document_upload1, document_no1, 1);
				updateOrAddDocument(existingEmp, document_upload2, document_no2, 2);
				updateOrAddDocument(existingEmp, document_upload3, document_no3, 3);
				updateOrAddDocument(existingEmp, document_upload4, document_no4, 4);
				updateOrAddDocument(existingEmp, document_upload5, document_no5, 5);
				updateOrAddDocument(existingEmp, document_upload6, document_no6, 6);
				updateOrAddDocument(existingEmp, document_upload7, document_no7, 7);
				updateOrAddDocument(existingEmp, document_upload8, document_no8, 8);
				updateOrAddDocument(existingEmp, document_upload9, document_no9, 9);
				updateOrAddDocument(existingEmp, document_upload10, document_no10, 10);

				// Save the updated entity
				employee_Onboarding_repo.save(existingEmp);
				return "Files saved successfully";
			} else {
				return "Error: Employee not found";
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "Error: Unable to process files";
		}
	}

	@RequestMapping(value = "/getproof1", method = { RequestMethod.GET })
	@ResponseBody
	public String getproof1(@RequestParam(required = false) String emp_id, String md)
			throws SQLException, ParseException {

		String base64String = null;
		try {
			System.out.println("inside");
			System.out.println("inside");
			System.out.println("inside");
			Employee_Onboarding_Entity vv = employee_Onboarding_repo.getProfileByEmployeeId(emp_id);

			byte[] documentBytes = vv.getDocument_upload2();

			// Encode the byte array into a Base64 string.
			if (documentBytes != null) {
				base64String = Base64.getEncoder().encodeToString(documentBytes);
			} else {
				System.out.println("No document found for emp_id: " + emp_id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return base64String != null ? base64String : "Error: No document available or failed to process.";
	}

	@RequestMapping(value = "/EmployeeDocView", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> EmployeeDocView(@RequestParam(required = false) String emp_id,
			@RequestParam(required = false) String doc_id) throws SQLException {
		System.out.println("Getting the file for employee ID: " + emp_id);
		Employee_Onboarding_Entity vv = employee_Onboarding_repo.getProfileByEmployeeId(emp_id);
		if (vv == null) {
			System.out.println("No records found for the given employee ID");
			return ResponseEntity.ok("No records found for the given employee ID");
		}
		byte[] fileData = null;
		String format = "";

		switch (doc_id) {
		case "1":
			fileData = vv.getDocument_upload1();
			format = vv.getDocument_upload_format1();
			break;
		case "2":
			fileData = vv.getDocument_upload2();
			format = vv.getDocument_upload_format2();
			break;
		case "3":
			fileData = vv.getDocument_upload3();
			format = vv.getDocument_upload_format3();
			break;
		case "4":
			fileData = vv.getDocument_upload4();
			format = vv.getDocument_upload_format4();
			break;
		case "5":
			fileData = vv.getDocument_upload5();
			format = vv.getDocument_upload_format5();
			break;
		case "6":
			fileData = vv.getDocument_upload6();
			format = vv.getDocument_upload_format6();
			break;
		case "7":
			fileData = vv.getDocument_upload7();
			format = vv.getDocument_upload_format7();
			break;
		case "8":
			fileData = vv.getDocument_upload8();
			format = vv.getDocument_upload_format8();
			break;
		case "9":
			fileData = vv.getDocument_upload9();
			format = vv.getDocument_upload_format9();
			break;
		case "10":
			fileData = vv.getDocument_upload10();
			format = vv.getDocument_upload_format10();
			break;
		// Add more cases if necessary
		default:
			return ResponseEntity.ok("Invalid document ID");
		}
		if (fileData != null) {
			String extension = format.substring(format.lastIndexOf(".") + 1).toLowerCase();
			String contentType = getContentType(extension);
			System.out.println("Content Type: " + contentType);

			String base64FileData = Base64.getEncoder().encodeToString(fileData);
			return ResponseEntity.ok().header("Content-Type", contentType).body(base64FileData);
		} else {
			return ResponseEntity.ok("File content is null");
		}
	}

	@RequestMapping(value = "/EmployeeDocView2", method = RequestMethod.GET)
	public void EmployeeDocView2(@RequestParam String emp_id, @RequestParam String doc_id, HttpServletResponse response)
			throws IOException {
		System.out.println("emp_id" + emp_id);
		System.out.println("doc_id" + doc_id);
		Employee_Onboarding_Entity employee = employee_Onboarding_repo.getProfileByEmployeeId(emp_id);
		if (employee == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "No records found");
			return;
		}

		byte[] fileData = null;
		String format = "";

		switch (doc_id) {
		case "1":
			fileData = employee.getDocument_upload1();
			format = employee.getDocument_upload_format1();
			break;
		case "2":
			fileData = employee.getDocument_upload2();
			format = employee.getDocument_upload_format2();
			break;
		case "3":
			fileData = employee.getDocument_upload3();
			format = employee.getDocument_upload_format3();
			break;
		case "4":
			fileData = employee.getDocument_upload4();
			format = employee.getDocument_upload_format4();
			break;
		case "5":
			fileData = employee.getDocument_upload5();
			format = employee.getDocument_upload_format5();
			break;
		case "6":
			fileData = employee.getDocument_upload6();
			format = employee.getDocument_upload_format6();
			break;
		case "7":
			fileData = employee.getDocument_upload7();
			format = employee.getDocument_upload_format7();
			break;
		case "8":
			fileData = employee.getDocument_upload8();
			format = employee.getDocument_upload_format8();
			break;
		case "9":
			fileData = employee.getDocument_upload9();
			format = employee.getDocument_upload_format9();
			break;
		case "10":
			fileData = employee.getDocument_upload10();
			format = employee.getDocument_upload_format10();
			break;
		default:
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid document ID");
			return;
		}

		if (fileData != null) {
			String extension = format.substring(format.lastIndexOf(".") + 1).toLowerCase();
			String contentType = getContentTypeString(extension);

			response.setContentType(contentType);
			response.setHeader("Content-Disposition", "inline; filename=document." + extension);
			response.getOutputStream().write(fileData);
			response.getOutputStream().flush();
		} else {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "File content is null");
		}
	}

	private String getContentTypeString(String extension) {
		Map<String, String> mimeTypes = new HashMap<>();
		mimeTypes.put("jpg", "image/jpeg");
		mimeTypes.put("jpeg", "image/jpeg");
		mimeTypes.put("png", "image/png");
		mimeTypes.put("pdf", "application/pdf");
		mimeTypes.put("xls", "application/vnd.ms-excel");
		mimeTypes.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		mimeTypes.put("doc", "application/msword");
		mimeTypes.put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");

		return mimeTypes.getOrDefault(extension, "application/octet-stream");
	}

	@RequestMapping(value = "EmployeedetailssModify", method = RequestMethod.POST)
	@ResponseBody
	public String EmployeedetailssModify(@ModelAttribute Employee_Onboarding_Entity emp,
			@RequestParam(required = false) List<MultipartFile> documents, Model md, HttpServletRequest rq) {
		System.out.println("First step in the controller");

		try {
			// Loop through the documents list and assign to corresponding fields
			for (int i = 0; i < documents.size(); i++) {
				MultipartFile file = documents.get(i);
				if (file != null && !file.isEmpty()) {
					String contentType = file.getContentType();
					byte[] fileBytes = file.getBytes();
					String originalFilename = file.getOriginalFilename();
					System.out.println(originalFilename + "originalFilenameoriginalFilenameoriginalFilename");

					// Set the file content to the corresponding document field in the entity
					switch (i) {
					case 0:
						emp.setDocument_upload1(fileBytes);
						emp.setDocument_upload_format1(originalFilename);
						break;
					case 1:
						emp.setDocument_upload2(fileBytes);
						emp.setDocument_upload_format2(originalFilename);
						break;
					case 2:
						emp.setDocument_upload3(fileBytes);
						emp.setDocument_upload_format3(originalFilename);
						break;
					case 3:
						emp.setDocument_upload4(fileBytes);
						emp.setDocument_upload_format4(originalFilename);
						break;
					case 4:
						emp.setDocument_upload5(fileBytes);
						emp.setDocument_upload_format5(originalFilename);
						break;
					case 5:
						emp.setDocument_upload6(fileBytes);
						emp.setDocument_upload_format6(originalFilename);
						break;
					case 6:
						emp.setDocument_upload7(fileBytes);
						emp.setDocument_upload_format7(originalFilename);
						break;
					case 7:
						emp.setDocument_upload8(fileBytes);
						emp.setDocument_upload_format8(originalFilename);
						break;
					case 8:
						emp.setDocument_upload9(fileBytes);
						emp.setDocument_upload_format9(originalFilename);
						break;
					case 9:
						emp.setDocument_upload10(fileBytes);
						emp.setDocument_upload_format10(originalFilename);
						break;
					}
				}
			}

			emp.setModify_flg("N");
			emp.setDel_flg("N");
			emp.setEntity_flg("N");
			emp.setVerify_flg("N");

			// Save the entity
			employee_Onboarding_repo.save(emp);
			return "Files saved successfully";
		} catch (IOException e) {
			e.printStackTrace();
			return "Error: Unable to process files";
		}
	}

	@RequestMapping(value = "/deleteRcordByUnitId", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> deleteRcordByUnitId(@RequestParam String employee_id, HttpServletRequest req) {
		if (employee_id == null || employee_id.isEmpty()) {
			return ResponseEntity.badRequest().body("Unit ID is required.");
		}

		String USERID = (String) req.getSession().getAttribute("USERID");
		String msg;

		try {
			Employee_Onboarding_Entity unitEntity = employee_Onboarding_repo.getProfileByEmployeeId1(employee_id);
			if (unitEntity != null) {
				unitEntity.setDel_flg("Y");
				unitEntity.setEntity_flg("N");
				employee_Onboarding_repo.save(unitEntity);
				msg = "Unit Record deleted successfully.";
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unit Record deletion failed. Unit not found.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred during Unit Record deletion.");
		}

		return ResponseEntity.ok(msg);

	}

	@RequestMapping(value = "/verifyRecordByUnitId", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> verifyRecordByUnitId(@RequestParam String employee_id, HttpServletRequest req) {
		System.out.println("Controller hit with employee_id: " + employee_id); // Debugging log

		if (employee_id == null || employee_id.isEmpty()) {
			return ResponseEntity.badRequest().body("Unit ID is required.");
		}

		try {
			Employee_Onboarding_Entity unitEntity = employee_Onboarding_repo.getProfileByEmployeeIds(employee_id);
			if (unitEntity != null) {
				unitEntity.setVerify_flg("Y");
				employee_Onboarding_repo.save(unitEntity);
				return ResponseEntity.ok("Unit Record Verified successfully.");
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unit Record not found.");
			}
		} catch (Exception e) {
			e.printStackTrace(); // Log the exception for debugging
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
		}
	}

	public String getContentType(String extension) {
		switch (extension) {
		case "pdf":
			return "application/pdf";
		case "doc":
			return "application/msword";
		case "docx":
			return "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
		case "jpg":
		case "jpeg":
			return "image/jpeg";
		case "png":
			return "image/png";
		case "txt":
			return "text/plain";
		case "html":
			return "text/html";
		case "xlsx":
			return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"; // For .xlsx
		case "xls":
			return "application/vnd.ms-excel"; // For .xls
		// Add other cases as needed
		default:
			return "application/octet-stream"; // Fallback to a generic MIME type
		}
	}

	@PostMapping("/deleteEvent")
	@ResponseBody
	public String deleteEvent(@RequestParam String eventId) {
		System.out.println("eventId: " + eventId);
		event_manager_details_Repo.deleteEvent(eventId);
		return "success";
	}

	@RequestMapping(value = "BGV", method = { RequestMethod.GET, RequestMethod.POST })
	public String BGV(@RequestParam(required = false) String formmode, Model md, HttpServletRequest req,
			@RequestParam(name = "empId", required = false) String empId) throws ParseException {

		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		System.out.println("empId" + empId);
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("del", emp_BGV_Repo.getValues());
		} else if (formmode.equals("add")) {
			md.addAttribute("formmode", "add");
		} else if (formmode.equals("view")) {
			md.addAttribute("formmode", "view");
			System.out.println("Enter into Formmode View");
			md.addAttribute("bgvviews", emp_BGV_Repo.getviewValues(empId));
			System.out.println("bgvviews" + emp_BGV_Repo.getviewValues(empId));
		} else if (formmode.equals("modify")) {
			md.addAttribute("formmode", "modify");
			System.out.println("Enter into Formmode Modify");
			md.addAttribute("bgvviews", emp_BGV_Repo.getviewValues(empId));
			System.out.println("bgvviews" + emp_BGV_Repo.getviewValues(empId));
		}
		return "Employee_BGV";
	}

	@Autowired
	BGVService bGVService;

	@PostMapping("saveEmployeeData")
	@ResponseBody
	public ResponseEntity<String> saveEmployeeData(@ModelAttribute Emp_BGV_Entity emp_BGV_Entity,
			@RequestParam(value = "experience_letter", required = false) MultipartFile experienceLetter,
			@RequestParam(value = "declaredform", required = false) MultipartFile declaredform,
			@RequestParam(value = "verify_certificate", required = false) MultipartFile verifyCertificate,
			@RequestParam(value = "degree_certificate", required = false) MultipartFile degreeCertificate,
			@RequestParam(value = "pan_card", required = false) MultipartFile panCard,
			@RequestParam(value = "uploadinv", required = false) MultipartFile uploadinv) {

		try {
			if (experienceLetter != null && !experienceLetter.isEmpty()) {
				emp_BGV_Entity.setExp_letter(experienceLetter.getBytes());
			}
			if (declaredform != null && !declaredform.isEmpty()) {
				emp_BGV_Entity.setDeclaration_form(declaredform.getBytes());
			}
			if (verifyCertificate != null && !verifyCertificate.isEmpty()) {
				emp_BGV_Entity.setVerify_cert(verifyCertificate.getBytes());
			}
			if (degreeCertificate != null && !degreeCertificate.isEmpty()) {
				emp_BGV_Entity.setDegree_cert(degreeCertificate.getBytes());
			}
			if (panCard != null && !panCard.isEmpty()) {
				emp_BGV_Entity.setId_proof(panCard.getBytes());
			}
			if (uploadinv != null && !uploadinv.isEmpty()) {
				emp_BGV_Entity.setUpload_invoice(uploadinv.getBytes());
			}
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing files");
		}

		// Save the data
		bGVService.saveEmployeeData(emp_BGV_Entity);
		return ResponseEntity.ok("Data saved successfully");
	}

	@PostMapping("/deleteService")
	@ResponseBody
	public String deleteService(@RequestParam String serviceId) {
		System.out.println("serviceId: " + serviceId);
		event_manager_details_Repo2.deleteService(serviceId);
		return "success";
	}

	@PostMapping("/UploadEop")
	@ResponseBody
	public String UploadEop(MultipartFile file) throws SQLException, IOException {
		String fileName = file.getOriginalFilename();
		String fileExt = fileName.substring(fileName.lastIndexOf('.') + 1);
		String msg = "";

		if (!fileExt.equals("xlsx") && !fileExt.equals("xls")) {
			return "Invalid File Format. Only .xlsx or .xls files are allowed.";
		}

		try (Workbook workbook = WorkbookFactory.create(file.getInputStream())) {
			Map<String, Event_manager_details_Entity> eventMap = new HashMap<>();

			// Process Event Sheet
			Sheet eventSheet = workbook.getSheet("Event");
			if (eventSheet != null) {
				for (Row r : eventSheet) {
					if (!isRowEmpty(r) && r.getRowNum() != 0) {
						Event_manager_details_Entity event = new Event_manager_details_Entity();

						event.setEventId(getCellValue(r.getCell(1)));
						event.setCurrentDate(parseDate(getCellValue(r.getCell(0))));
						event.setEventType(getCellValue(r.getCell(2)));
						event.setEventDescription(getCellValue(r.getCell(3)));
						event.setEventFrequency(getCellValue(r.getCell(4)));
						event.setEventDate(parseDate(getCellValue(r.getCell(5))));
						event.setEventToDate(parseDate(getCellValue(r.getCell(6))));
						event.setStartTime(getCellValue(r.getCell(7)));
						event.setEndTime(getCellValue(r.getCell(8)));
						event.setSingleFlag(getCellValue(r.getCell(9)));
						event.setDelFlg("N");
						event.setEntityFlg("Y");
						event.setModifyFlg("N");
						eventMap.put(event.getEventId(), event);
					}
				}
			}
			Sheet organizerSheet = workbook.getSheet("Organizer");
			if (organizerSheet != null) {
				for (Row r : organizerSheet) {
					if (!isRowEmpty(r) && r.getRowNum() != 0) {
						String eventId = getCellValue(r.getCell(0));
						Event_manager_details_Entity event = eventMap.get(eventId);

						if (event != null) {
							event.setOrganizerType(
									appendWithSeparator(event.getOrganizerType(), getCellValue(r.getCell(1))));
							event.setMode(appendWithSeparator(event.getMode(), getCellValue(r.getCell(2))));
							event.setOrgEmployeeNo(
									appendWithSeparator(event.getOrgEmployeeNo(), getCellValue(r.getCell(3))));
							event.setOrgEmployeeName(
									appendWithSeparator(event.getOrgEmployeeName(), getCellValue(r.getCell(4))));
							event.setOrganizerName(
									appendWithSeparator(event.getOrganizerName(), getCellValue(r.getCell(5))));
							event.setRemarks(appendWithSeparator(event.getRemarks(), getCellValue(r.getCell(6))));
						}
					}
				}
			}

			// Process Participants Sheet
			Sheet participantsSheet = workbook.getSheet("Participants");
			if (participantsSheet != null) {
				for (Row r : participantsSheet) {
					if (!isRowEmpty(r) && r.getRowNum() != 0) {
						String eventId = getCellValue(r.getCell(0));
						Event_manager_details_Entity event = eventMap.get(eventId);

						if (event != null) {
							event.setParticipantEmpNo(
									appendWithSeparator(event.getParticipantEmpNo(), getCellValue(r.getCell(1))));
							event.setParticipantEmpName(
									appendWithSeparator(event.getParticipantEmpName(), getCellValue(r.getCell(2))));
							event.setParticipantLocation(
									appendWithSeparator(event.getParticipantLocation(), getCellValue(r.getCell(3))));
						}
					}
				}
			}

			event_manager_details_Repo.saveAll(eventMap.values());
			msg = "File uploaded successfully.";

		} catch (Exception e) {
			e.printStackTrace();
			msg = "File has not been successfully uploaded.";
		}

		return msg;
	}

	private String appendWithSeparator(String existingValue, String newValue) {
		if (newValue == null || newValue.isEmpty())
			return existingValue;
		return (existingValue == null || existingValue.isEmpty()) ? newValue : existingValue + "|||" + newValue;
	}

	private Date parseDate(String dateStr) {
		if (dateStr == null || dateStr.isEmpty()) {
			return null;
		}
		String[] possibleFormats = { "yyyy-MM-dd", "M/d/yy", "MM/dd/yyyy", "dd-MM-yyyy", "yyyy/MM/dd" };
		for (String format : possibleFormats) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				sdf.setLenient(false); // Strict parsing
				return sdf.parse(dateStr);
			} catch (ParseException e) {
				// Continue to the next format
			}
		}
		// If none of the formats worked, log the error and return null
		System.err.println("Unparseable date: " + dateStr);
		return null;
	}

	private String getCellValue(Cell cell) {
		if (cell == null) {
			return "";
		}
		DataFormatter formatter = new DataFormatter();
		return formatter.formatCellValue(cell);
	}

	private boolean isRowEmpty(Row row) {
		if (row == null) {
			return true;
		}
		for (int i = row.getFirstCellNum(); i < row.getLastCellNum(); i++) {
			Cell cell = row.getCell(i);
			if (cell != null && cell.getCellType() != CellType.EQUAL) {
				return false;
			}
		}
		return true;
	}

	@RequestMapping(value = "eventfeedback", method = { RequestMethod.GET, RequestMethod.POST })
	public String eventfeedback(Model md, HttpServletRequest req, @RequestParam(required = false) String formmode,
			@ModelAttribute Event_manager_details_Entity data, @ModelAttribute Event_manager_details_Entity2 data1,
			@RequestParam(required = false) String eventId, @RequestParam(required = false) String Service_No) {

		String userId = (String) req.getSession().getAttribute("USERID");
		System.out.println("userId: " + userId);
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));

		if (formmode == null || formmode.equals("feedbacklist")) {
			md.addAttribute("formmode", "feedbacklist");
			md.addAttribute("eventlist", event_manager_details_Repo.geteventvalues());
		} else if (formmode.equals("feedbackview") || formmode.equals("feedbackmodify")) {

			List<Event_manager_details_Entity> obj = event_manager_details_Repo.getrecords(eventId);
			md.addAttribute("eventlist2", obj);
			List<Object[]> orgpar = event_manager_details_Repo.getorgpar(eventId);

			boolean userAllowed = false;
			List<String> orgEmployeeNos = new ArrayList<>();
			List<String> participantEmpNos = new ArrayList<>();

			for (Object[] row : orgpar) {
				String orgEmployeeNo = row[0] != null ? row[0].toString() : "";
				String participantEmpNo = row[1] != null ? row[1].toString() : "";

				orgEmployeeNos.add(orgEmployeeNo);
				participantEmpNos.add(participantEmpNo);

				System.out.println("ORG_EMPLOYEE_NO: " + orgEmployeeNo + ", PARTICIPANT_EMP_NO: " + participantEmpNo);

				if (orgEmployeeNo.contains(userId) || participantEmpNo.contains(userId)) {
					userAllowed = true;
				}
			}

			md.addAttribute("orgEmployeeNos", orgEmployeeNos);
			md.addAttribute("participantEmpNos", participantEmpNos);
			md.addAttribute("userId", userId);

			if (!userAllowed) {
				System.out.println("User not allowed for feedback modify");
				md.addAttribute("error", "You are not authorized to modify feedback.");
				return "EventManager.html"; // Redirect back with an error message
			}

			if (formmode.equals("feedbackview")) {
				md.addAttribute("formmode", "feedbackview");
				System.out.println("feedbackview");
			} else {
				md.addAttribute("formmode", "feedbackmodify");
				md.addAttribute("adminProfileManager", event_manager_details_Repo.getrecords(eventId));
				System.out.println("User allowed for feedbackmodify");
			}
		}

		return "EventManager.html";
	}
   // FOR DOWNLOAD FUNCTION - ACCOUNT LEDGER 
   // BY SURIYA
	@RequestMapping(value = "accountledgerdownload", method = RequestMethod.GET)
	@ResponseBody
	public InputStreamResource AccountLedgerDownload(HttpServletResponse response, 
			@RequestParam(required = false) String acct_num,
			@RequestParam(required = false) String fromdate,
			@RequestParam(required = false) String todate
	) throws IOException, SQLException {

		response.setContentType("application/octet-stream");
		System.out.println("===============" + acct_num);
		InputStreamResource resource = null;
		try {

			String filetype = "Excel";
			File repfile = placementServices.getFileAcccount_Ledger(filetype, acct_num ,fromdate ,todate);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));

		} catch (JRException e) {

			e.printStackTrace();
		}

		return resource;
	}
	@PostMapping("/toggle")
	public ResponseEntity<String> toggleScheduler(@RequestParam boolean enable) {
	    schedulerToggleService.setSchedulerEnabled(enable); // Updates flag
	    return ResponseEntity.ok("Scheduler is now " + (enable ? "ON" : "OFF"));
	}
	@GetMapping("/logs")
	public ResponseEntity<List<String>> getLogs() {
	    return ResponseEntity.ok(logService.getLogs());
	}
	
	@DeleteMapping("/clearLogs")
	 public ResponseEntity<Void> clearLogs() {
	        logService.clearLogs();
	        return ResponseEntity.noContent().build();
	   }
	
	@RequestMapping(value = "/BDVDocView", method = RequestMethod.GET)
    public void BDVDocView(@RequestParam String emp_id, @RequestParam String doc_id, HttpServletResponse response)
            throws IOException {

        Emp_BGV_Entity employee = emp_BGV_Repo.getProfileByEmployeeId(emp_id);
        if (employee == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No records found");
            return;
        }

        byte[] fileData = null;
        String fallbackFileName = "";

        switch (doc_id) {
            case "1":
                fileData = employee.getExp_letter();
                fallbackFileName = "experience_letter";
                break;
            case "2":
                fileData = employee.getDegree_cert();
                fallbackFileName = "degree_certificate";
                break;
            case "3":
                fileData = employee.getId_proof();
                fallbackFileName = "id_proof";
                break;
            case "4":
                fileData = employee.getDeclaration_form();
                fallbackFileName = "declaration_form";
                break;
            case "5":
                fileData = employee.getVerify_cert();
                fallbackFileName = "verification_certificate";
                break;
            case "6":
                fileData = employee.getUpload_invoice();
                fallbackFileName = "invoice";
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid document ID");
                return;
        }

        if (fileData != null) {
            Tika tika = new Tika(); // This is Apache Tika
            String mimeType = tika.detect(fileData);
            String extension = getExtensionFromMimeType(mimeType);

            String fileName = fallbackFileName + "." + extension;

            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", "inline; filename=" + fileName);
            response.getOutputStream().write(fileData);
            response.getOutputStream().flush();
        } else {
            response.sendError(HttpServletResponse.SC_NO_CONTENT, "File content is null");
        }
    }

    // Map MIME types to file extensions
    private String getExtensionFromMimeType(String mimeType) {
        switch (mimeType) {
            case "application/pdf":
                return "pdf";
            case "image/jpeg":
                return "jpeg";
            case "image/png":
                return "png";
            case "application/msword":
                return "doc";
            case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
                return "docx";
            case "application/vnd.ms-excel":
                return "xls";
            case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
                return "xlsx";
            default:
                return "bin"; // default binary file
        }
    }
    
    @RequestMapping(value = "/BGVdocDownload", method = RequestMethod.GET)
    public ResponseEntity<byte[]> BGVdocDownload(@RequestParam String emp_id,
                                                 @RequestParam String doc_id) throws SQLException {
        System.out.println("Getting the file for Employee ID: " + emp_id);
        System.out.println("Getting the file for Document ID: " + doc_id);

        Emp_BGV_Entity employee = emp_BGV_Repo.getProfileByEmployeeId(emp_id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }

        byte[] fileData = null;
        String baseFileName = "Document_" + doc_id; // fallback name
        String fileExtension = "";
        String contentType = "application/octet-stream";

        switch (doc_id) {
        	case "1":
        		fileData = employee.getExp_letter();
        		baseFileName= "experience_letter";
            break;
            case "2":
                fileData = employee.getDegree_cert();
                baseFileName = "Degree_Certificate";
                break;
            case "3":
                fileData = employee.getId_proof();
                baseFileName = "ID_Proof";
                break;
            case "4":
                fileData = employee.getDeclaration_form();
                baseFileName = "Declaration_Form";
                break;
            case "5":
                fileData = employee.getVerify_cert();
                baseFileName = "Verify_Certificate";
                break;
            case "6":
                fileData = employee.getUpload_invoice();
                baseFileName= "Invoice";
                break;
            default:
                return ResponseEntity.badRequest().build();
        }

        if (fileData != null) {
            try {
                // Use Apache Tika to detect content type
                Tika tika = new Tika();
                contentType = tika.detect(fileData);

                // Set file extension based on MIME type
                switch (contentType) {
                    case "application/pdf":
                        fileExtension = ".pdf";
                        break;
                    case "image/png":
                        fileExtension = ".png";
                        break;
                    case "image/jpeg":
                        fileExtension = ".jpg";
                        break;
                    default:
                        fileExtension = ""; // unknown, fallback
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            String fileName = baseFileName + fileExtension;

            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
                    .header("Content-Type", contentType)
                    .body(fileData);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(value = "ModifyBGVData", method = RequestMethod.POST)
    @ResponseBody
    public String ModifyBGVData( @RequestParam("employee_id") String empId,
        @RequestParam(value = "experience_letter", required = false) MultipartFile expLetter,
        @RequestParam(value = "verify_certificate", required = false) MultipartFile verifyCert,
        @RequestParam(value = "degree_certificate", required = false) MultipartFile degreeCert,
        @RequestParam(value = "id_proof", required = false) MultipartFile id_proof,
        @RequestParam(value = "declaration_form", required = false) MultipartFile declarationForm,
        @RequestParam(value = "upload_invoice", required = false) MultipartFile uploadInvoice
    ) {
        try {
            System.out.println("Enter into Controller with empId: " + empId);

            Optional<Emp_BGV_Entity> existingEmpOpt = emp_BGV_Repo.findById(empId);
            if (!existingEmpOpt.isPresent()) {
                return "Error: Employee record not found!";
            }

            Emp_BGV_Entity existingEmp = existingEmpOpt.get();
            
            

            if (expLetter != null && !expLetter.isEmpty()) {
                existingEmp.setExp_letter(expLetter.getBytes());
            }
            if (verifyCert != null && !verifyCert.isEmpty()) {
                existingEmp.setVerify_cert(verifyCert.getBytes());
            }
            if (degreeCert != null && !degreeCert.isEmpty()) {
                existingEmp.setDegree_cert(degreeCert.getBytes());
            }
            if (id_proof != null && !id_proof.isEmpty()) {
                existingEmp.setId_proof(id_proof.getBytes());
            }
            if (declarationForm != null && !declarationForm.isEmpty()) {
                existingEmp.setDeclaration_form(declarationForm.getBytes());
            }
            if (uploadInvoice != null && !uploadInvoice.isEmpty()) {
                existingEmp.setUpload_invoice(uploadInvoice.getBytes());
            }

            emp_BGV_Repo.save(existingEmp); 
            return "Success";

        } catch (IOException e) {
            e.printStackTrace();
            return "Error: Unable to update employee documents due to an internal error.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Something went wrong.";
        }
    }
    
    @RequestMapping(value = "ModifyDataBGVSubmit", method = RequestMethod.POST)
    @ResponseBody
    public String ModifyDataBGVSubmit(
    	@ModelAttribute BGVFormData formData,
        @RequestParam("emp_id") String empId,
        @RequestParam("invoice_amt") BigDecimal invoiceAmt,
        @RequestParam(value = "experience_letter", required = false) MultipartFile expLetter,
        @RequestParam(value = "verify_certificate", required = false) MultipartFile verifyCert,
        @RequestParam(value = "degree_certificate", required = false) MultipartFile degreeCert,
        @RequestParam(value = "id_proof", required = false) MultipartFile idProof,
        @RequestParam(value = "declaration_form", required = false) MultipartFile declarationForm,
        @RequestParam(value = "upload_invoice", required = false) MultipartFile uploadInvoice,
        @RequestParam(value = "driving_license", required = false)  String driving_license
    ) {
        try {
            Optional<Emp_BGV_Entity> existingEmpOpt = emp_BGV_Repo.findById(empId);
            if (!existingEmpOpt.isPresent()) {
                return "Error: Employee record not found!";
            }

            Emp_BGV_Entity existingEmp = existingEmpOpt.get();

            if (expLetter != null && !expLetter.isEmpty()) {
                existingEmp.setExp_letter(expLetter.getBytes());
            }
            if (verifyCert != null && !verifyCert.isEmpty()) {
                existingEmp.setVerify_cert(verifyCert.getBytes());
            }
            if (degreeCert != null && !degreeCert.isEmpty()) {
                existingEmp.setDegree_cert(degreeCert.getBytes());
            }
            if (idProof != null && !idProof.isEmpty()) {
                existingEmp.setId_proof(idProof.getBytes());
            }
            if (declarationForm != null && !declarationForm.isEmpty()) {
                existingEmp.setDeclaration_form(declarationForm.getBytes());
            }
            if (uploadInvoice != null && !uploadInvoice.isEmpty()) {
                existingEmp.setUpload_invoice(uploadInvoice.getBytes());
            }

            existingEmp.setInvoice_amt(formData.getInvoice_amt());
            existingEmp.setDriving_license(formData.getDriving_license());
            existingEmp.setEmp_type(formData.getEmp_type());
            existingEmp.setOrganization(formData.getOrganization());
            existingEmp.setEmp_id(formData.getEmp_id());
            existingEmp.setEmp_name(formData.getEmp_name());
            existingEmp.setQual(formData.getQual());
            existingEmp.setDesignation(formData.getDesignation());
            existingEmp.setRole(formData.getRole());
            existingEmp.setAddress(formData.getAddress());
            existingEmp.setDate_of_birth(formData.getDate_of_birth());
            existingEmp.setDate_of_joining(formData.getDate_of_joining());
            existingEmp.setBank_name(formData.getBank_name());
            existingEmp.setAcct_no(formData.getAcct_no());
            existingEmp.setPan_no(formData.getPan_no());
            existingEmp.setAadhaar_no(formData.getAadhaar_no());
            existingEmp.setPassport(formData.getPassport());
            existingEmp.setDriving_license(formData.getDriving_license());
            existingEmp.setMobile_no(formData.getMobile_no());
            existingEmp.setEmail(formData.getEmail());
            existingEmp.setDate_of_email_intimation(formData.getDate_of_email_intimation());
            existingEmp.setPrevious_experience(formData.getPrevious_experience());
            existingEmp.setCheck_details(formData.getCheck_details());
            existingEmp.setDoc_frm_emp(formData.getDoc_frm_emp());
            existingEmp.setDoc_sub_date(formData.getDoc_sub_date());
            existingEmp.setFollow_up_action_1(formData.getFollow_up_action_1());
            existingEmp.setRemainder_date_1(formData.getRemainder_date_1());
            existingEmp.setFollow_up_action_2(formData.getFollow_up_action_2());
            existingEmp.setRemainder_date_2(formData.getRemainder_date_2());
            existingEmp.setFollow_up_action_3(formData.getFollow_up_action_3());
            existingEmp.setRemainder_date_3(formData.getRemainder_date_3());
            existingEmp.setDoc_send_date(formData.getDoc_send_date());
            existingEmp.setDate_of_receipt(formData.getDate_of_receipt());
            existingEmp.setDoc_status(formData.getDoc_status());
            existingEmp.setRemarks(formData.getRemarks());
            existingEmp.setInvoice_date(formData.getInvoice_date()); 
            existingEmp.setPay_date(formData.getPay_date());
            
            emp_BGV_Repo.save(existingEmp);
            return "Success";

        } catch (IOException e) {
            e.printStackTrace();
            return "Error: IO Exception occurred.";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: Something went wrong.";
        }
    }


    @RequestMapping(value = "empexitmanagement", method = { RequestMethod.GET, RequestMethod.POST })
	public String empexitmanagement(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
			@ModelAttribute CandEvalFormEntity candEvalFormEntity, @RequestParam(required = false) String a,
			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
		// md.addAttribute("IssueMaster", issueMasterRep.findAllCustom());

		String userId = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
		md.addAttribute("menu", "BTMHeaderMenu");

		md.addAttribute("empty", "");
		md.addAttribute("kkkk", a);
		md.addAttribute("cvfverify", resourceMasterRepo.getEmployeeIDs());

		if (formmode == null || formmode.equals("list")) {

			md.addAttribute("formmode", "list");
			
			md.addAttribute("ListofExitEmployees",employee_exit_management_repo.getListEmployeeDetails());

		} else if (formmode.equals("donwloadRelieving")) {
			md.addAttribute("formmode", "donwloadRelieving");

		} 
		

		return "RelievingLetter.html";
	}
    
    @RequestMapping(value = "relievingletterdownload", method = RequestMethod.GET)
	@ResponseBody
	public InputStreamResource relievingletterdownload(HttpServletResponse response,
			@RequestParam(value = "filetype", required = false) String filetype,
			@RequestParam(value = "a", required = false) String a,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "resignationDate", required = false) String resignationDate,
			@RequestParam(value = "mailDate", required = false) String mailDate
			) throws IOException, SQLException {

		response.setContentType("application/octet-stream");

		InputStreamResource resource = null;
		System.out.println("Resource Id:"+a);
		System.out.println("Resignation Date"+resignationDate);
		System.out.println("Mail Date"+mailDate);
		System.out.println("FileType"+filetype);
		try {
			logger.info("Getting download File :" + a + ", FileType :" + filetype + "");

			File repfile = projectMasterServices.getrelievingletterDownload(a, resignationDate,mailDate,filetype,name);

			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
			resource = new InputStreamResource(new FileInputStream(repfile));
		} catch (JRException e) {
			// Log the error using the logger
			logger.error("Error generating TDS file", e);
			// Optionally, rethrow the exception or handle it as needed
			// throw new YourCustomException("Error generating TDS file", e);
		}

		return resource;
	}
    
    @RequestMapping(value = "payslipgenration", method = { RequestMethod.GET, RequestMethod.POST })
   	public String payslipgenration(@RequestParam(required = false) String formmode,
   			@RequestParam(required = false) String userid, @RequestParam(required = false) Optional<Integer> page,
   			@ModelAttribute CandEvalFormEntity candEvalFormEntity, @RequestParam(required = false) String a,
   			@RequestParam(value = "size", required = false) Optional<Integer> size, Model md, HttpServletRequest req) {
   		// md.addAttribute("IssueMaster", issueMasterRep.findAllCustom());

   		String userId = (String) req.getSession().getAttribute("USERID");
   		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userId));
   		md.addAttribute("menu", "BTMHeaderMenu");

   		md.addAttribute("empty", "");
   		md.addAttribute("kkkk", a);
   		md.addAttribute("cvfverify", resourceMasterRepo.getEmployeeIDs());

   		if (formmode == null || formmode.equals("donwloadPayslip")) {

   			md.addAttribute("formmode", "donwloadPayslip");

   		} 

   		return "Payslip_Generation.html";
   	}
    
    
    @RequestMapping(value = "payslipdownload", method = RequestMethod.GET)
  	@ResponseBody
  	public InputStreamResource payslipdownload(HttpServletResponse response,
  			@RequestParam(value = "filetype", required = false) String filetype,
  			@RequestParam(value = "a", required = false) String a,
  			@RequestParam(value = "payslipmonth", required = false) String payslipmonth
  			) throws IOException, SQLException {

  		response.setContentType("application/octet-stream");

  		InputStreamResource resource = null;
  		System.out.println("Resource Id:"+a);
  		System.out.println("Payslip Month"+payslipmonth);
  		System.out.println("FileType"+filetype);
//  		try {
//  			logger.info("Getting download File :" + a + ", FileType :" + filetype + "");
//
//  			File repfile = projectMasterServices.getDownloadPayslip(a,payslipmonth,filetype);
//
//  			response.setHeader("Content-Disposition", "attachment; filename=" + repfile.getName());
//  			resource = new InputStreamResource(new FileInputStream(repfile));
//  		} catch (JRException e) {
//  			// Log the error using the logger
//  			logger.error("Error generating TDS file", e);
//  			// Optionally, rethrow the exception or handle it as needed
//  			// throw new YourCustomException("Error generating TDS file", e);
//  		}

  		return resource;
  	}
      
    @RequestMapping(value = "ExitemployeesubmitData", method = RequestMethod.POST)
    @ResponseBody
    public String ExitemployeesubmitData(
            @RequestParam("refNo") String refNo,
            @RequestParam("resignationDateRaw") String resignationDateRaw
    ) {
        try {
           
            ResourceMaster empdetails = resourceMasterRepo.getSingleDetails(refNo);

            if (empdetails == null) {
                return "Employee not found";
            }
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date resignationDate = sdf.parse(resignationDateRaw);

            employee_exit_management_entity entity = new employee_exit_management_entity();
            entity.setEmployee_No(empdetails.getResource_id()); 
            entity.setEmployee_Name(empdetails.getResource_name()); 
            entity.setDate_of_Resignation(resignationDate);
            entity.setDate_of_Joining(empdetails.getDoj()); 
            entity.setDate_of_Birth(empdetails.getDob()); 
            entity.setReason("Resigned"); 
            entity.setEntity_flg('Y');
            entity.setDel_flg('N');
            entity.setEntry_time(new Date());
            employee_exit_management_repo.save(entity);

            return "Added Successfully...";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed: " + e.getMessage();
        }
    }


    @RequestMapping(value = "/CallogView", method = RequestMethod.GET)
    public void CallogView(@RequestParam String emp_id, @RequestParam String doc_id, HttpServletResponse response)
            throws IOException {
    	
    	BTM_Call_Log_Entity employee = BTM_Call_Log_Repo.getProfileByEmployeeId(emp_id);
        if (employee == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No records found");
            return;
        }

        byte[] fileData = null;
        String fallbackFileName = "";

        switch (doc_id) {
            case "1":
                fileData = employee.getCall_log_file();
                fallbackFileName = "Call_log_File";
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid document ID");
                return;
        }

        if (fileData != null) {
            Tika tika = new Tika(); // This is Apache Tika
            String mimeType = tika.detect(fileData);
            String extension = getExtensionFromMimeType1(mimeType);

            String fileName = fallbackFileName + "." + extension;

            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", "inline; filename=" + fileName);
            response.getOutputStream().write(fileData);
            response.getOutputStream().flush();
        } else {
            response.sendError(HttpServletResponse.SC_NO_CONTENT, "File content is null");
        }
    }
    
    private String getExtensionFromMimeType1(String mimeType) {
        switch (mimeType) {
            case "application/pdf":
                return "pdf";
            case "image/jpeg":
                return "jpeg";
            case "image/png":
                return "png";
            case "application/msword":
                return "doc";
            case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
                return "docx";
            case "application/vnd.ms-excel":
                return "xls";
            case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
                return "xlsx";
            default:
                return "bin"; // default binary file
        }
    }
    
    @RequestMapping(value = "/TroubleShootView", method = RequestMethod.GET)
    public void TroubleShootView(@RequestParam String emp_id, @RequestParam String doc_id, HttpServletResponse response)
            throws IOException {
    	System.out.println("empid"+emp_id+"doc_id"+doc_id);
    	BTMTroubleShoot_Entity employee = bTMTroubleShoot_Rep.getProfileByEmployeeId(emp_id);
        if (employee == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "No records found");
            return;
        }

        byte[] fileData = null;
        String fallbackFileName = "";

        switch (doc_id) {
            case "1":
                fileData = employee.getBlob_file();
                fallbackFileName = "Trouble_Shoot";
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid document ID");
                return;
        }

        if (fileData != null) {
            Tika tika = new Tika(); // This is Apache Tika
            String mimeType = tika.detect(fileData);
            String extension = getExtensionFromMimeType2(mimeType);

            String fileName = fallbackFileName + "." + extension;

            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", "inline; filename=" + fileName);
            response.getOutputStream().write(fileData);
            response.getOutputStream().flush();
        } else {
            response.sendError(HttpServletResponse.SC_NO_CONTENT, "File content is null");
        }
    }
    
    private String getExtensionFromMimeType2(String mimeType) {
        switch (mimeType) {
            case "application/pdf":
                return "pdf";
            case "image/jpeg":
                return "jpeg";
            case "image/png":
                return "png";
            case "application/msword":
                return "doc";
            case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
                return "docx";
            case "application/vnd.ms-excel":
                return "xls";
            case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
                return "xlsx";
            default:
                return "bin"; // default binary file
        }
    }
	@RequestMapping(value = "adminResourceTrackManage", method = { RequestMethod.GET, RequestMethod.POST })
	public String adminResourceTrackManage(@RequestParam(required = false) String formmode,
			@RequestParam(required = false) BigDecimal resId, @RequestParam(required = false) String RefId, Model md,
			HttpServletRequest req) throws ParseException {
		String userid2 = (String) req.getSession().getAttribute("USERID");
		md.addAttribute("userid2",userid2);
		md.addAttribute("RoleMenu", resourceMasterRepo.getrole(userid2));
		md.addAttribute("Empname",resourceMasterRepo.getname(userid2));
		System.out.println("Empname"+resourceMasterRepo.getname(userid2));
		System.out.println("userid2"+userid2);
		md.addAttribute("menu", "BTMHeaderMenu");
		if (formmode == null || formmode.equals("list")) {
			md.addAttribute("formmode", "list");
			md.addAttribute("AdminLeaveList", leaveMasterRep.getLeavelist1());
			//leave details
			
			List<Object[]> empList =btmAdminAssociateProfileRep.getallempid();
			List<String> empIds = empList.stream()
			                             .map(obj -> String.valueOf(obj[0])) // assuming empId is in obj[0]
			                             .collect(Collectors.toList());
			md.addAttribute("profileManager", empIds);
			md.addAttribute("remaining_leave_applyleave", leaveMasterRep.remaining_leave_applyleave(userid2));
			System.out.println(leaveMasterRep.remaining_leave_applyleave(userid2) + "remaining_leave_applyleave");
			List<Object[]> remainingLeaveData = leaveMasterRep.remaining_leave_applyleave(userid2);
			md.addAttribute("remaining_leave_applyleave", remainingLeaveData);
			System.out.println("remaining_leave_applyleave: " + remainingLeaveData);

			for (Object[] row : remainingLeaveData) {
				String employeeId = (String) row[0]; // Cast to String for EMPLOYEE_ID
				BigDecimal leaveBalance = (BigDecimal) row[1]; // Cast to BigDecimal for LEAVE_BLC
				md.addAttribute("leaveBalance", leaveBalance);
				System.out.println("Employee ID: " + employeeId + ", Leave Balance: " + leaveBalance);
			}
			md.addAttribute("projectManager", btmProjectMasterRep.getProjectlist());

		} else if (formmode.equals("view")) {

			md.addAttribute("formmode", "view");
			md.addAttribute("MtLeaveMaster", onDutyServices.getLeaveDetail(resId));
			md.addAttribute("LeaveListCounter", leaveMasterCounterRep.getLeaveCounterlist(RefId));

		}

		return "BTMResTrackManage";
	}
        
}
