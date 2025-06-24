package com.bornfire.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import com.bornfire.config.AES;
import com.bornfire.config.Emailsent;
import com.bornfire.config.PasswordEncryption;
import com.bornfire.entities.FinUserProfile;
import com.bornfire.entities.FinUserProfileRep;
import com.bornfire.entities.ResourceMaster;
import com.bornfire.entities.ResourceMasterRepo;
import com.bornfire.entities.UserProfile;
import com.bornfire.entities.UserProfileRep;
import com.itextpdf.text.log.SysoCounter;
import com.bornfire.entities.BTMSession;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Service
@ConfigurationProperties("output")
@Transactional
public class LoginServices {

	private static final Logger logger = LoggerFactory.getLogger(LoginServices.class);

	@Autowired
	UserProfileRep userProfileRep;

	@Autowired
	FinUserProfileRep finUserProfileRep;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSource srcdataSource;
	
	@Autowired
	ResourceMasterRepo resourceMasterRepo;

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

	/*
	 * Getting 3 inputs -
	 * 
	 * UserProfile Object, Formmode - Valid values : add, edit, inputuser - user who
	 * edited the data
	 * 
	 * if formmode is add - Get password from application.properties create the user
	 * 
	 * if formmode is edit - Get password from database for that user and use other
	 * fields came from front end.
	 * 
	 * 
	 */

	public String addUser(UserProfile userProfile, String formmode, String inputUser)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		String msg = "";
		UserProfile up1 = userProfile;

		int test = userProfileRep.getcount(up1.getUserid());


		if (test != 0) {

			msg = "User Already Exist";

		} else {

			if (formmode.equals("add")) {

				UserProfile up = userProfile;

				String encryptedPassword = PasswordEncryption.getEncryptedPassword(this.password);

				if (up.getLogin_status().equals("Active")) {
					up.setUser_locked_flg("N");
				} else {
					up.setUser_locked_flg("Y");
				}

				if (up.getUser_status().equals("Active")) {
					up.setDisable_flg("N");
				} else {
					up.setDisable_flg("Y");
				}

				
				up.setEntity_flg("Y");

				up.setEntry_time(new Date());
				up.setEntry_user(inputUser);
				up.setDel_flg("N");
				up.setLogin_flg("N");
				up.setNo_of_attmp(0);
				up.setPassword(encryptedPassword);
                Emailsent emailfun = new Emailsent();
                try {
					String message = emailfun.execute( up.getEmail_id());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//userProfileRep.save(up);

				msg = "User Created Successfully";

			}
		}
		if (formmode.equals("edit")) {

			UserProfile up = userProfile;

			String encryptedPassword = PasswordEncryption.getEncryptedPassword(this.password);

			if (up.getLogin_status().equals("Active")) {
				up.setUser_locked_flg("N");
			} else {
				up.setUser_locked_flg("Y");
			}

			if (up.getUser_status().equals("Active")) {
				up.setDisable_flg("N");
			} else {
				up.setDisable_flg("Y");
			}

			up.setEntity_flg("Y");
			up.setModify_time(new Date());
			up.setModify_user(inputUser);
			up.setModify_flg("Y");
			up.setDel_flg("N");
			up.setLogin_flg("N");
			up.setNo_of_attmp(0);
			up.setPassword(encryptedPassword);
			userProfileRep.save(up);
			msg = "User Edited Successfully";

		}

		return msg;

	}

	public List<FinUserProfile> getFinUsersList() {

		Session hs = sessionFactory.getCurrentSession();
		return hs.createQuery("from FinUserProfile ", FinUserProfile.class).getResultList();

	}

	public Iterable<UserProfile> getUsersList() {

		Iterable<UserProfile> users = userProfileRep.findAll();

		return users;

	}

	public UserProfile getUser(String id) {
		logger.info(id);
		if (userProfileRep.existsById(id)) {
			UserProfile up = userProfileRep.findById(id).get();
			logger.info(up.getEntity_flg());
			return up;
		} else {
			return new UserProfile();
		}

	};

	public UserProfile getFinUser(String id) {
		logger.info(id);
		if (finUserProfileRep.existsById(id)) {

			DateFormat dateFormat = new SimpleDateFormat("hh:mm");

			FinUserProfile fup = finUserProfileRep.findById(id).get();

			UserProfile up = new UserProfile();

			up.setUserid(fup.getUserid());
			up.setUsername(fup.getFinGenEmpTb().getEmp_name());
			up.setEmpid(fup.getFinGenEmpTb().getEmp_id());
			up.setEmp_name(fup.getFinGenEmpTb().getEmp_name());
			up.setBranch_code(fup.getFinSolTb().getSdl_id());
			up.setBranch_name(fup.getFinSolTb().getSol_desc());
			up.setBank_code(fup.getFinSolTb().getBank_code());
			up.setBank_name(fup.getFinSolTb().getAbbr_bank_name());
			up.setEmail_id(fup.getFinGenEmpTb().getEmp_email_id());

			up.setInactive_time(fup.getUser_max_inactive_time().toString());
			up.setDisable_start_date(fup.getUser_disabled_from_date());
			up.setDisable_end_date(fup.getUser_disabled_upto_date());
			up.setAcc_exp_date(fup.getUser_acct_expy_date());
			up.setLogin_low(dateFormat.format(fup.getUser_login_time_low()));
			up.setLogin_high(dateFormat.format(fup.getUser_login_time_high()));

			return up;

		} else {

			return new UserProfile();
		}

	}

	public String verifyUser(UserProfile userProfile, String inputUser) {
		String msg = "";

		Optional<UserProfile> up = userProfileRep.findById(userProfile.getUserid());

		try {

			if (up.isPresent()) {

				userProfile.setPassword(up.get().getPassword());

				if (userProfile.getLogin_status().equals("Active")) {
					userProfile.setUser_locked_flg("N");
				} else {
					userProfile.setUser_locked_flg("Y");
				}

				if (userProfile.getUser_status().equals("Active")) {
					userProfile.setDisable_flg("N");
				} else {
					userProfile.setDisable_flg("Y");
				}

				userProfile.setNo_of_attmp(0);
				userProfile.setEntity_flg("Y");
				userProfile.setLogin_flg("N");
				userProfile.setAuth_user(inputUser);
				userProfile.setAuth_time(new Date());

				userProfileRep.save(userProfile);
			}

			msg = "User Verified Successfully";

		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
			msg = "Error Occured. Please contact Administrator";
		}

		return msg;
	}

	public String passwordReset(UserProfile userprofile, String userid) {

		String msg = "";

		try {
			String encryptedPassword = PasswordEncryption.getEncryptedPassword(this.password);

			Optional<UserProfile> up = userProfileRep.findById(userprofile.getUserid());

			if (up.isPresent()) {

				UserProfile user = up.get();

				user.setPassword(encryptedPassword);

				user.setNo_of_attmp(0);
				user.setLogin_flg("N");
				user.setUser_locked_flg("N");
				userProfileRep.save(user);
			}

			msg = "Password Resetted Successfully";

		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {

			e.printStackTrace();

			msg = "Error Occured. Please contact Administrator";
		}

		return msg;
	}

	/*
	 * Getting LoginFlg -
	 * 
	 * If loginFlg = 'N' - User should be prompted to change password. else thats
	 * not required.
	 * 
	 * Loginflg ='N' will be updated at the time of new user creation and at the
	 * time of password reset by admin.
	 * 
	 */

	public String checkPasswordChangeReq(String userid) {

		Optional<UserProfile> up = userProfileRep.findById(userid);
		String loginflg = up.get().getLogin_flg();

		return loginflg;
	}

	public int checkAcctexpirty(String userid) {

		Optional<ResourceMaster> up = resourceMasterRepo.findById(userid);
		//Date expDate = up.get().getAcc_exp_date();
		Date expDate = up.get().getAcct_expy_date();
		Date currDate = new Date();

		DateTime dt1 = new DateTime(currDate);
		DateTime dt2 = new DateTime(expDate);

		int remaindays = Days.daysBetween(dt1, dt2).getDays();

		logger.info("Account Expired in:" + remaindays);
		return remaindays;
	}

	public int checkpassexpirty(String userid) {

		Optional<ResourceMaster> up = resourceMasterRepo.findById(userid);
		Date expDate = up.get().getPw_expy_date();
		Date currDate = new Date();

		DateTime dt1 = new DateTime(currDate);
		DateTime dt2 = new DateTime(expDate);

		int remaindays = Days.daysBetween(dt1, dt2).getDays();

		logger.info("Password Expired in:" + remaindays);
		return remaindays;
	}

//Password Change Functionality 

	public String changePassword(String oldpass, String newpass, String userid) {

		String msg = "";
		Optional<ResourceMaster> up = resourceMasterRepo.findById(userid);
		String encrypted_password =null;
		try {
			 encrypted_password =	AES.encrypt(oldpass);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		ResourceMaster user = up.get();
		
if(encrypted_password.equals(user.getPassword())) {
	if (up.isPresent()) {

		
			//	if (PasswordEncryption.validatePassword(encrypted_password, user.getPassword())) {

				//	if (!PasswordEncryption.validatePassword(newpass, user.getPassword())) {

						String encryptedPassword = null;
						try {
							encryptedPassword = AES.encrypt(newpass);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(encryptedPassword+"new one");

						user.setPassword(encryptedPassword);
					//	user.setDel_flg("Y");

						final Calendar cal = Calendar.getInstance();
						cal.add(Calendar.MONTH,1);
						System.out.println(cal.getTime());
						user.setPw_expy_date(cal.getTime());
								
						resourceMasterRepo.save(user);

						msg = "Password Changed Successfully";

					} else {

						msg = "New password cannot be Same as Old password";
					}

				} else {
					msg = "Incorrect Old Password!";
				}
			
		
		return msg;
	};

	public void SessionLogging(String menuname, String menuid, String sessionid, String userid, String ip,
			String status) {
		Session hs = sessionFactory.getCurrentSession();
		System.out.println("Enter Into Session Logging");
		try {

			if (menuname.equals("LOGOUT")) {

				hs.createQuery("update XBRLSession set status='IN-ACTIVE' where session_id = ?1")
						.setParameter(1, sessionid).executeUpdate();

			} else {

				hs.save(new BTMSession(menuname, menuid, sessionid, userid, ip, new Date(), status));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public File getUserLogFile(Date fromdate, Date todate) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

		String path = exportpath;
		String fileName = "USER_LOGS_" + dateFormat.format(new Date()) + ".xlsx";
		File outputFile;

		File jasperFile;

		File folders = new File(path);
		if (!folders.exists()) {
			folders.mkdirs();
		}

		try {
			jasperFile = ResourceUtils.getFile("classpath:static/jasper/USER_LOGS/UserLogs.jasper");
			JasperReport jr = (JasperReport) JRLoader.loadObject(jasperFile);
			HashMap<String, Object> map = new HashMap<String, Object>();

			logger.info("Assigning Parameters for Jasper");
			map.put("FromDate", dateFormat.format(fromdate));
			map.put("ToDate", dateFormat.format(todate));

			path = path + "/" + fileName;
			JasperPrint jp = JasperFillManager.fillReport(jr, map, srcdataSource.getConnection());
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setExporterInput(new SimpleExporterInput(jp));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(path));
			exporter.exportReport();
			logger.info("Excel File exported");

		} catch (FileNotFoundException | JRException | SQLException e) {

			e.printStackTrace();
		}

		outputFile = new File(path);

		return outputFile;
	}

	public List<BTMSession> getUserLog(Date fromdate, Date todate) {

		Session hs = sessionFactory.getCurrentSession();

		List<BTMSession> ls = hs.createQuery(
				"from XBRLSession where trunc(entry_time,'DD') between ?1 and ?2 and menu in ('LOGIN','LOGOUT') order by entry_time desc ",
				BTMSession.class).setParameter(1, fromdate).setParameter(2, todate).getResultList();

		return ls;
	}

}
