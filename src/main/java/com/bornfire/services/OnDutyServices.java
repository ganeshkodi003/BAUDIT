package com.bornfire.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bornfire.entities.AttendanceRegister;
import com.bornfire.entities.AttendanceRegisterGet;
import com.bornfire.entities.AttendanceRegisterGetRep;
import com.bornfire.entities.AttendanceRegisterRep;
import com.bornfire.entities.BELE_Table_Entity;
import com.bornfire.entities.BELE_Table_Repo;
import com.bornfire.entities.BTMAdminAssociateProfileRep;
import com.bornfire.entities.BTMAdminOndutyCount;
import com.bornfire.entities.BTMAdminOndutyCountRep;
import com.bornfire.entities.BTMAdminSampleOD;
import com.bornfire.entities.BTMDocumentMaster;
import com.bornfire.entities.BTMDocumentMasterRep;
import com.bornfire.entities.BTMEmpTimeSheet;
import com.bornfire.entities.BTMEmpTimeSheetRep;
import com.bornfire.entities.BTMONDutyCounterID;
import com.bornfire.entities.BTMTravelMaster;
import com.bornfire.entities.BTMTravelMasterRep;
import com.bornfire.entities.DocumentMainRep;
import com.bornfire.entities.DocumentMaintenance;
import com.bornfire.entities.ExpenseMaster;
import com.bornfire.entities.ExtenseMasterRep;
import com.bornfire.entities.IssueTracker;
import com.bornfire.entities.IssueTrackerRep;
import com.bornfire.entities.LeaveMaster;
import com.bornfire.entities.LeaveMasterCounter;
import com.bornfire.entities.LeaveMasterCounterId;
import com.bornfire.entities.LeaveMasterCounterRep;
import com.bornfire.entities.LeaveMasterRep;
import com.bornfire.entities.LeaveResponseModal;
import com.bornfire.entities.OnDuty;
import com.bornfire.entities.OnDutyRep;
import com.bornfire.entities.PlacementMaintenance;
import com.bornfire.entities.SampleLeaveMaster;
import com.bornfire.entities.gst;
import com.ibm.icu.util.Calendar;

@Service
@ConfigurationProperties("output")
@Transactional

public class OnDutyServices {

	private static final String NULL = null;

	@Autowired
	ExtenseMasterRep extenseMasterRep;

	@Autowired
	OnDutyRep onDutyRep;

	@Autowired
	LeaveMasterRep leaveMasterRep;

	@Autowired
	BTMTravelMasterRep BTMtravelMasterRep;

	@Autowired
	BTMAdminAssociateProfileRep btmAdminAssociateProfileRep;

	@Autowired
	BTMAdminOndutyCountRep btmAdminOndutyCountRep;

	@Autowired
	LeaveMasterCounterRep leaveMasterCounterRep;
	
	@Autowired
	BTMEmpTimeSheetRep btmEmpTimeSheetRep;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSource srcdataSource;
	
	@Autowired
	AttendanceRegisterRep attendanceRegisterRep;
	
	@Autowired
	AttendanceRegisterGetRep attendanceRegisterGetRep;
	@Autowired
	BELE_Table_Repo bELE_Table_Repo;

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

//	=================================== Apply On Duty ======================================

	public String addOnDuty(OnDuty onDuty, BTMAdminSampleOD btmAdminOndutyCount, String formmode)
			throws ParseException, SQLException {
		Session hs = sessionFactory.getCurrentSession();
		String msg = "";
		SimpleDateFormat formatdate = new SimpleDateFormat("dd-MMM-yy");
		SimpleDateFormat formatyear = new SimpleDateFormat("yyyy");
		SimpleDateFormat formatmonth = new SimpleDateFormat("MM");
		SimpleDateFormat formatday = new SimpleDateFormat("dd");
		String cal_date = formatdate.format(onDuty.getOd_from());
		String cal_year = formatyear.format(onDuty.getOd_from());
		String cal_month = formatmonth.format(onDuty.getOd_from());
		String cal_day = formatday.format(onDuty.getOd_from());
		
		  OnDuty on = onDutyRep.OndutyCheck(onDuty.getEmp_id(), cal_date);
		  BTMAdminOndutyCount oncount =
		  btmAdminOndutyCountRep.getOndutyCount(onDuty.getEmp_id(), cal_date);
		 
		
		
		if (formmode.equals("add")) {
			DecimalFormat numformate = new DecimalFormat("0000");

			BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT OD_RECORD_NO.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();
			String serialno = numformate.format(billNumber);
			System.out.println("SerialNo:"+serialno);
			OnDuty up = onDuty;
			
			if (on == null && oncount == null) {
				
				System.out.println("On and onCount");
				String LeaveRef = up.getEmp_id() + "/" + serialno;
				up.setLeave_ref(LeaveRef);
				up.setEntity_flg("N");
				up.setStatus("Pending");
				up.setEntry_user(up.getEmp_id());
				up.setModify_user(up.getEmp_id());
				up.setModify_time(new Date());
				up.setEntry_time(new Date());
				up.setRecord_no(serialno);
				up.setDel_flg("N");
				up.setModify_flg("N");
				up.setDevice("SYSTEM");
				

				int year = Calendar.getInstance().get(Calendar.YEAR);

				BigDecimal currentYear = BigDecimal.valueOf(year);

				up.setYear(currentYear);

				onDutyRep.save(up);
				BTMAdminSampleOD up1 = btmAdminOndutyCount;
				String[] list1 = up1.getOd_day().split(",");
				BTMAdminOndutyCount OD_COUNT = new BTMAdminOndutyCount();
				OD_COUNT.setEmp_id(up.getEmp_id());
				OD_COUNT.setEmp_name(up.getEmp_name());
				OD_COUNT.setLeave_ref(LeaveRef);
				OD_COUNT.setOd_category(up.getOd_category());
				OD_COUNT.setOd_desc(up.getProject());
				float a = 1;
				OD_COUNT.setOd_counter(a);
				OD_COUNT.setHalf_day_counter("N");

				for (int i = 0; i < list1.length; i++) {
					BTMONDutyCounterID od1 = new BTMONDutyCounterID();
					od1.setRecord_no(billNumber);
					if (up1.getOd_day().split(",")[i] != "") {

						OD_COUNT.setOd_day(up1.getOd_day().split(",")[i]);
						od1.setOd_date(new SimpleDateFormat("dd-MMM-yy").parse(up1.getOd_date().split(",")[i]));
						OD_COUNT.setBtmONDutyCounterID(od1);
					btmAdminOndutyCountRep.save(OD_COUNT);

					}

				}
				try {
					OnDuty on1 = onDutyRep.OndutyCheck(onDuty.getEmp_id(),cal_date);
					AttendanceRegisterGet att = attendanceRegisterGetRep.getAttendanceDate(onDuty.getEmp_id(),cal_year,cal_month,cal_day);
					if (att.getEmp_remarks().equals("Present")|| att.getEmp_remarks().equals("Leave") || att.getEmp_remarks().equals("On-Duty")) {
						
						ConnectionManager connMgr = new ConnectionManager();
						Connection conn = connMgr.getConnection();
						PreparedStatement ps1 = null;
						int rs = 0;
						String sql2 = "DELETE  BTM.EMP_ATT_MASTER WHERE EMP_ID='"+onDuty.getEmp_id()+"'  AND CAL_YEAR='"+cal_year+"' AND CAL_MONTH='"+cal_month+"' AND CAL_DATE='"+cal_day+"'";
						 ps1 = conn.prepareStatement(sql2.toString());
						 rs = ps1.executeUpdate();
						String actualDate = btmAdminOndutyCount.getOd_date();
						BTMAdminSampleOD upp1 = btmAdminOndutyCount;
						OnDuty upp = onDuty;
						String ipadd = null;
						try {
							ipadd = InetAddress.getLocalHost().toString();
						} catch (UnknownHostException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						for (int i = 0; i < upp1.getOd_date().split(",").length; i++) {
							String sql1 = "INSERT INTO BTM.EMP_ATT_MASTER(EMP_ID,EMP_NAME,LOGIN_TIME,EMP_REMARKS,DEL_FLG,LEAVE_FLG,DEVICE,IP_ADDRESS) VALUES ('"
									+ upp.getEmp_id() + "','" + upp.getEmp_name() + "','" + upp1.getOd_date().split(",")[i]
									+ "','On-Duty','N','N','SYSTEM','" + ipadd + "')";
							ps1 = conn.prepareStatement(sql1.toString());
							rs = ps1.executeUpdate();
						}
					}
				} catch (NullPointerException e) {
					ConnectionManager connMgr = new ConnectionManager();
					Connection conn = connMgr.getConnection();
					PreparedStatement ps1 = null;
					int rs = 0;
					String actualDate = btmAdminOndutyCount.getOd_date();
					BTMAdminSampleOD upp1 = btmAdminOndutyCount;
					OnDuty upp = onDuty;
					String ipadd = null;
					try {
						ipadd = InetAddress.getLocalHost().toString();
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					for (int i = 0; i < upp1.getOd_date().split(",").length; i++) {
						String sql1 = "INSERT INTO BTM.EMP_ATT_MASTER(EMP_ID,EMP_NAME,LOGIN_TIME,EMP_REMARKS,LEAVE_FLG,DEL_FLG,DEVICE,IP_ADDRESS) VALUES ('"
								+ upp.getEmp_id() + "','" + upp.getEmp_name() + "','" + upp1.getOd_date().split(",")[i]
								+ "','On-Duty','N','N','SYSTEM','" + ipadd + "')";
						ps1 = conn.prepareStatement(sql1.toString());
						rs = ps1.executeUpdate();
					}
				}
				msg = "OnDuty Marked Successfully";
			}else if (on != null) {
				msg = "Already On-Duty Marked";
			}
		} 
		

		return msg;
	}

	public List<OnDuty> getODMasterList() {

		List<OnDuty> users = onDutyRep.getODlist();

		return users;
	}
	
	public OnDuty getODDetail(String resId) {

		if (onDutyRep.existsById(resId)) {
			OnDuty up = onDutyRep.findById(resId).get();
			return up;
		} else {
			return new OnDuty();
		}
	};

//	Sequence Generate

	public String getSrlNo() {

		Session hs = sessionFactory.getCurrentSession();
		DecimalFormat numformate = new DecimalFormat("0000");

		BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT OD_RECORD_NO.NEXTVAL AS SRL_NO FROM DUAL")
				.getSingleResult();

		String serialno = numformate.format(billNumber);
		return serialno;
	}

	public String getTravelRef() {

		Session hs = sessionFactory.getCurrentSession();
		DecimalFormat numformate = new DecimalFormat("000");

		BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT TRAVEL_REF_NO.NEXTVAL AS SRL_NO FROM DUAL")
				.getSingleResult();

		String serialno = numformate.format(billNumber);
		return serialno;

	}
	
	public String getExpRef() {

		Session hs = sessionFactory.getCurrentSession();
		DecimalFormat numformate = new DecimalFormat("000");

		BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT EXPSEQ.NEXTVAL AS SRL_NO FROM DUAL")
				.getSingleResult();

		String serialno = numformate.format(billNumber);
		return serialno;

	}

	
	public String addLeaveMaster(LeaveMaster leaveMaster) {

		String msg = "";

		LeaveMaster up = leaveMaster;

		String LeaveRef = up.getEmployee_id() + "/" + up.getRecord_no();

		up.setLeave_reference(LeaveRef);

		up.setEntity_flg("Y");

		leaveMasterRep.save(up);

		msg = "Leave Details Stored Successfully";

		return msg;
	}

	public String addTravelList(BTMTravelMaster BTMtravelMaster) {

		String msg = "";

		BTMTravelMaster up = BTMtravelMaster;
		if (up.getTra_leg_1() != null) {

			up.setTra_leg_1(up.getTra_leg_1().replaceAll(",", "||"));
		} else {
			up.setTra_leg_1(NULL);
		}
		if (up.getTra_leg_2() != null) {

			up.setTra_leg_2(up.getTra_leg_2().replaceAll(",", "||"));
		} else {
			up.setTra_leg_2(NULL);
		}
		if (up.getTra_leg_3() != null) {

			up.setTra_leg_3(up.getTra_leg_3().replaceAll(",", "||"));
		} else {
			up.setTra_leg_3(NULL);
		}
		if (up.getTra_leg_4() != null) {

			up.setTra_leg_4(up.getTra_leg_4().replaceAll(",", "||"));
		} else {
			up.setTra_leg_4(NULL);
		}
		if (up.getTra_leg_5() != null) {

			up.setTra_leg_5(up.getTra_leg_5().replaceAll(",", "||"));
		} else {
			up.setTra_leg_5(NULL);
		}
		if (up.getTra_leg_6() != null) {

			up.setTra_leg_6(up.getTra_leg_6().replaceAll(",", "||"));
		} else {
			up.setTra_leg_6(NULL);
		}

		if (up.getTra_leg_7() != null) {

			up.setTra_leg_7(up.getTra_leg_7().replaceAll(",", "||"));
		} else {
			up.setTra_leg_7(NULL);
		}

		if (up.getTra_leg_8() != null) {

			up.setTra_leg_8(up.getTra_leg_8().replaceAll(",", "||"));
		} else {
			up.setTra_leg_8(NULL);
		}

		if (up.getTra_leg_9() != null) {

			up.setTra_leg_9(up.getTra_leg_9().replaceAll(",", "||"));
		} else {
			up.setTra_leg_9(NULL);
		}

		if (up.getTra_leg_10() != null) {

			up.setTra_leg_10(up.getTra_leg_10().replaceAll(",", "||"));
		} else {
			up.setTra_leg_10(NULL);
		}

		up.setEntity_flg("Y");
		up.setTra_status("Pending");
		up.setEntry_user(up.getAss_id());
		up.setEntry_time(new Date());
		up.setDel_flg("N");

		BTMtravelMasterRep.save(up);

		msg = "Travel Details Stored Successfully";
		return msg;
	}

//	Expense Report

	public String addExpenseReport(ExpenseMaster expenseMaster, String formmode) {

		String msg = "";

		if (formmode.equals("add")) {

			ExpenseMaster up = expenseMaster;
			if (up.getExp_det_1() != null) {

				up.setExp_det_1(up.getExp_det_1().replaceAll(",", "||"));
			} else {
				up.setExp_det_1(NULL);
			}
			if (up.getExp_det_2() != null) {

				up.setExp_det_2(up.getExp_det_2().replaceAll(",", "||"));
			} else {
				up.setExp_det_2(NULL);
			}
			if (up.getExp_det_3() != null) {

				up.setExp_det_3(up.getExp_det_3().replaceAll(",", "||"));
			} else {
				up.setExp_det_3(NULL);
			}
			if (up.getExp_det_4() != null) {

				up.setExp_det_4(up.getExp_det_4().replaceAll(",", "||"));
			} else {
				up.setExp_det_4(NULL);
			}
			if (up.getExp_det_5() != null) {

				up.setExp_det_5(up.getExp_det_5().replaceAll(",", "||"));
			} else {
				up.setExp_det_5(NULL);
			}
			if (up.getExp_det_6() != null) {

				up.setExp_det_6(up.getExp_det_6().replaceAll(",", "||"));
			} else {
				up.setExp_det_6(NULL);
			}
			if (up.getExp_det_7() != null) {

				up.setExp_det_7(up.getExp_det_7().replaceAll(",", "||"));
			} else {
				up.setExp_det_7(NULL);
			}

			if (up.getExp_det_8() != null) {

				up.setExp_det_8(up.getExp_det_8().replaceAll(",", "||"));
			} else {
				up.setExp_det_8(NULL);
			}

			if (up.getExp_det_9() != null) {

				up.setExp_det_9(up.getExp_det_9().replaceAll(",", "||"));
			} else {
				up.setExp_det_9(NULL);
			}
			if (up.getExp_det_10() != null) {

				up.setExp_det_10(up.getExp_det_10().replaceAll(",", "||"));
			} else {
				up.setExp_det_10(NULL);
			}
			up.setEntity_flg("Y");
			up.setStatus("Pending");
			up.setEntry_user(up.getAss_id());
			up.setEntry_time(new Date());
			up.setDel_flg("N");

			extenseMasterRep.save(up);

			msg = "Stored Successfully";
		}

		return msg;

	}

// ================================ Apply Leave Master ========================
	
	public List<LeaveMaster> getLeaveMasterList() {

		List<LeaveMaster> users = leaveMasterRep.getLeavelist();

		return users;
	}

	public String addLeave(LeaveMaster leaveMaster, SampleLeaveMaster sampleLeaveMaster, BELE_Table_Entity bELE_Table_Entity,String formmode)
			throws ParseException, SQLException {
		Session hs = sessionFactory.getCurrentSession();
		String msg = "";
		LeaveMaster up = leaveMaster;
		SimpleDateFormat formatdate = new SimpleDateFormat("dd-MMM-yy");
		SimpleDateFormat formatyear = new SimpleDateFormat("yyyy");
		SimpleDateFormat formatmonth = new SimpleDateFormat("MM");
		SimpleDateFormat formatday = new SimpleDateFormat("dd");
		String cal_date = formatdate.format(leaveMaster.getFrom_date());
		String cal_year = formatyear.format(leaveMaster.getFrom_date());
		String cal_month = formatmonth.format(leaveMaster.getFrom_date());
		String cal_day = formatday.format(leaveMaster.getFrom_date());
		
		LeaveMaster lv = leaveMasterRep.getLeavebyFromDate(cal_date, up.getEmployee_id());
		LeaveMasterCounter lvcount = leaveMasterCounterRep.getleaveCheck(up.getEmployee_id(), cal_date);

		if (formmode.equals("add")) {
			DecimalFormat numformate = new DecimalFormat("0000");

			BigDecimal billNumber = (BigDecimal) hs
					.createNativeQuery("SELECT LEAVE_RECORD_NO.NEXTVAL AS SRL_NO FROM DUAL").getSingleResult();

			String serialno = String.valueOf(numformate.format(billNumber));
			String LeaveRef = up.getEmployee_id() + "/" + serialno;
			System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkk"+LeaveRef+"cal_date"+cal_date);
			if (lv==null & lvcount==null) {
				System.out.println("firstr step");
				up.setLeave_reference(LeaveRef);
				up.setStatus("Pending");
				up.setEntry_time(new Date());
				up.setRecord_no(new BigDecimal(serialno));
				up.setEntry_user(up.getEmployee_id());
				up.setModify_user(up.getEmployee_id());
				up.setEntry_time(new Date());
				up.setModify_time(new Date());
				up.setEntity_flg("N");
				up.setDel_flg("N");
				up.setModify_flg("N");
				up.setDevice("SYSTEM");

				int year = Calendar.getInstance().get(Calendar.YEAR);

				BigDecimal currentYear = BigDecimal.valueOf(year);

				up.setYear(currentYear);

				leaveMasterRep.save(up);
				
				
			

				SampleLeaveMaster up1 = sampleLeaveMaster;
				// String[] list1 = up1.getLeave_day().split(",");
				// LeaveMasterCounter upp =leaveMasterCounterRep.getleavelistbyrec(LeaveRef);
				LeaveMasterCounter Leave_COUNT = new LeaveMasterCounter();

				Leave_COUNT.setEmp_id(up.getEmployee_id());
				Leave_COUNT.setEmp_name(up.getEmployee_name());
				Leave_COUNT.setLeave_ref(LeaveRef);
				Leave_COUNT.setLeave_category(up1.getLeave_category());
				Leave_COUNT.setLeave_desc("LEAVE");
				Leave_COUNT.setLeave_counter(up1.getLeave_counter());
				Leave_COUNT.setHalf_day_counter("N");
				Leave_COUNT.setDel_flg("N");
				

				for (int i1 = 0; i1 < up1.getLeave_day().split(",").length; i1++) {
					LeaveMasterCounterId leave1 = new LeaveMasterCounterId();
					leave1.setRecord_no(billNumber);
					if (up1.getLeave_day().split(",")[i1] != "") {

						Leave_COUNT.setLeave_day(up1.getLeave_day().split(",")[i1]);
						leave1.setLeave_date(
								new SimpleDateFormat("dd-MMM-yy").parse(up1.getLeave_date().split(",")[i1]));
						Leave_COUNT.setLeaveMasterCounterId(leave1);
						leaveMasterCounterRep.save(Leave_COUNT);

					}

				}
				LeaveMaster u = leaveMaster;
				LeaveMaster lv1 = leaveMasterRep.getLeavebyFromDate(cal_date, u.getEmployee_id());
				
				AttendanceRegisterGet att = attendanceRegisterGetRep.getAttendanceDate(u.getEmployee_id(),cal_year,cal_month,cal_day);
				try {
					if (att.getEmp_remarks().equals("Present") || att.getEmp_remarks().equals("On-Duty")) {
							
						ConnectionManager connMgr = new ConnectionManager();
						Connection conn = connMgr.getConnection();
						PreparedStatement ps1 = null;
						int rs = 0;
						String sql2 = "DELETE  BTM.EMP_ATT_MASTER WHERE EMP_ID='"+u.getEmployee_id()+"'  AND CAL_YEAR='"+cal_year+"' AND CAL_MONTH='"+cal_month+"' AND CAL_DATE='"+cal_day+"'";
						 ps1 = conn.prepareStatement(sql2.toString());
						 rs = ps1.executeUpdate();
						String actualDate = sampleLeaveMaster.getLeave_date();
						SampleLeaveMaster up11 = sampleLeaveMaster;
						LeaveMaster up111 = leaveMaster;
						String ipadd = null;
						try {
							ipadd = InetAddress.getLocalHost().toString();
						} catch (UnknownHostException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						for (int i1 = 0; i1 < sampleLeaveMaster.getLeave_date().split(",").length; i1++) {
							String sql3 = "INSERT INTO BTM.EMP_ATT_MASTER(EMP_ID,EMP_NAME,LOGIN_TIME,EMP_REMARKS,DEL_FLG,LEAVE_FLG,DEVICE,IP_ADDRESS) VALUES ('"
									+ up111.getEmployee_id() + "','" + up111.getEmployee_name() + "','"
									+ sampleLeaveMaster.getLeave_date().split(",")[i1] + "','Leave','N','Y','SYSTEM','" + ipadd + "')";
							 ps1 = conn.prepareStatement(sql3.toString());
							 rs = ps1.executeUpdate();
						}
					}
				} catch (NullPointerException e) {
					ConnectionManager connMgr = new ConnectionManager();
					Connection conn = connMgr.getConnection();
					PreparedStatement ps1 = null;
					int rs = 0;
					String actualDate = sampleLeaveMaster.getLeave_date();
					SampleLeaveMaster up11 = sampleLeaveMaster;
					LeaveMaster up111 = leaveMaster;
					String ipadd = null;
					try {
						ipadd = InetAddress.getLocalHost().toString();
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					for (int i1 = 0; i1 < sampleLeaveMaster.getLeave_date().split(",").length; i1++) {
						String sql3 = "INSERT INTO BTM.EMP_ATT_MASTER(EMP_ID,EMP_NAME,LOGIN_TIME,EMP_REMARKS,DEL_FLG,LEAVE_FLG,DEVICE,IP_ADDRESS) VALUES ('"
								+ up111.getEmployee_id() + "','" + up111.getEmployee_name() + "','"
								+ sampleLeaveMaster.getLeave_date().split(",")[i1] + "','Leave','N','Y','SYSTEM','" + ipadd + "')";
						 ps1 = conn.prepareStatement(sql3.toString());
						 rs = ps1.executeUpdate();
					}

				}
				msg = "Leave Marked Successfully";
			}else if (lv != null) {
				msg = "Already Leave Marked";

			}

		} 
		
	
	/*String a=	leaveMaster.getEmployee_id();
	BigDecimal b=	leaveMaster.getYear();
	LocalDate c = leaveMaster.getFrom_date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	LocalDate d = leaveMaster.getTo_date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

	long daysBetween = ChronoUnit.DAYS.between(c, d);

	System.out.println("Number of days between " + c + " and " + d + ": " + daysBetween);
	String w[]=(c.toString()).split("-");
	System.out.println(w[2]+"00000000000000000"+w[0]);
	
	  List<LocalDate> sundays = findSundaysBetweenDates(c, d);
	  
	    for (LocalDate sunday : sundays) {
	        System.out.println("Sunday Holiday ====="+sunday);
	    }
	 
	    List<LocalDate> datesInRange = getDatesBetween(c, d);

        // Print the dates in the range
	    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	   // DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		LeaveMaster lv1 = leaveMasterRep.getLeavebyFromDateapproved(cal_date, up.getEmployee_id());
		if (lv1 == null) {
		    // Leave is not approved
		    return "Not Approved";
		} else {
	    for (LocalDate date : datesInRange) {
	        System.out.println("Processing date: " + date);

	        String formattedDate = date.format(dateFormatter);

	        List<BTMEmpTimeSheet> existingEntries = btmEmpTimeSheetRep.getTimeSheetdatasheet(a, formattedDate.split("-")[1], formattedDate.split("-")[0]);

	        if (!existingEntries.isEmpty()) {
	            // Retrieve the first existing entry (assuming you expect at most one entry)
	            BTMEmpTimeSheet existingEntry = existingEntries.get(0);

	            // Update the existing entry with the new values
	            int dayOfMonth = date.getDayOfMonth();
	            setDayProperty(existingEntry, dayOfMonth, formattedDate + "||00:00||00:00||00:00||00:00||Leave Applied||Leave Applied||NO");

	            // Save the updated entry back to the database
	            btmEmpTimeSheetRep.save(existingEntry);
	        } else {
	            // Handle the case where the entity does not exist
	            System.out.println("Entity does not exist for date: " + formattedDate);
	            // You may want to insert a new entry or skip it based on your logic
	        }
	    }
		}
	    
	   */
	   
		return msg;
	}

	
	private static List<LocalDate> getDatesBetween(LocalDate startDate, LocalDate endDate) {
        List<LocalDate> datesInRange = new ArrayList<>();
        LocalDate currentDate = startDate;

        while (!currentDate.isAfter(endDate)) {
            datesInRange.add(currentDate);
            currentDate = currentDate.plusDays(1);
        }

        return datesInRange;
    }
	
	
	private static void setDayProperty(BTMEmpTimeSheet obj, int day, String value) {
        try {
            // Construct the method name based on the day of the month
            String methodName = "setDate_" + day;

            // Find the method using reflection
            Method method = BTMEmpTimeSheet.class.getMethod(methodName, String.class);

            // Invoke the method to set the value dynamically
            method.invoke(obj, value);
        } catch (Exception e) {
            e.printStackTrace(); // Handle or log the exception accordingly
        }
    }
	
	  public LeaveMaster getLeaveDetail(BigDecimal resId) {
	  
	  if (leaveMasterRep.existsById(resId)) { 
		  LeaveMaster up =leaveMasterRep.findById(resId).get(); 
		  return up; } 
	  else { 
		  return new LeaveMaster();
		  } 
	  }
	  
	  //TimeSheet

	  public String addTimeSheet(BTMEmpTimeSheet save) {
			String msg = "";
			BTMEmpTimeSheet check=btmEmpTimeSheetRep.getTimeSheetdataView(save.getEmp_id(), save.getMonth(), save.getYear());
			if(check==null) {
			BTMEmpTimeSheet up = save;
			up.setEntity_flg('Y');
			up.setDel_flg('Y');
			up.setTimesheet_apply_date(new Date());
			
			btmEmpTimeSheetRep.save(up);
			msg="Time sheet Added succesfully";
			}else {
				msg="Time Sheet Already Added";
			}
			return msg;
			
	  }
	  
	  public String EditTimeSheet(BTMEmpTimeSheet btmEmpTimeSheet,String empid,BigDecimal year,String month) {

			String msg = "";
			BTMEmpTimeSheet up1 =btmEmpTimeSheetRep.getTimeSheetModify(btmEmpTimeSheet.getEmp_id(),btmEmpTimeSheet.getYear(),btmEmpTimeSheet.getMonth());
			if(btmEmpTimeSheet.getDate_1()!=null) {
				up1.setDate_1(btmEmpTimeSheet.getDate_1().replaceAll(",", "||"));
			}else {
				up1.setDate_1(NULL);
			}
			if(btmEmpTimeSheet.getDate_2()!=null) {
				up1.setDate_2(btmEmpTimeSheet.getDate_2().replaceAll(",", "||"));
			}else {
				up1.setDate_2(NULL);
			}
			if(btmEmpTimeSheet.getDate_3()!=null) {
				up1.setDate_3(btmEmpTimeSheet.getDate_3().replaceAll(",", "||"));
			}else {
				up1.setDate_3(NULL);
			}
			if(btmEmpTimeSheet.getDate_4()!=null) {
				up1.setDate_4(btmEmpTimeSheet.getDate_4().replaceAll(",", "||"));
			}else {
				up1.setDate_4(NULL);
			}
			if(btmEmpTimeSheet.getDate_5()!=null) {
				up1.setDate_5(btmEmpTimeSheet.getDate_5().replaceAll(",", "||"));
			}else {
				up1.setDate_5(NULL);
			}
			if(btmEmpTimeSheet.getDate_6()!=null) {
				up1.setDate_6(btmEmpTimeSheet.getDate_6().replaceAll(",", "||"));
			}else {
				up1.setDate_6(NULL);
			}
			if(btmEmpTimeSheet.getDate_7()!=null) {
				up1.setDate_7(btmEmpTimeSheet.getDate_7().replaceAll(",", "||"));
			}else {
				up1.setDate_7(NULL);
			}
			if(btmEmpTimeSheet.getDate_8()!=null) {
				up1.setDate_8(btmEmpTimeSheet.getDate_8().replaceAll(",", "||"));
			}else {
				up1.setDate_8(NULL);
			}
			if(btmEmpTimeSheet.getDate_9()!=null) {
				up1.setDate_9(btmEmpTimeSheet.getDate_9().replaceAll(",", "||"));
			}else {
				up1.setDate_9(NULL);
			}
			if(btmEmpTimeSheet.getDate_10()!=null) {
				up1.setDate_10(btmEmpTimeSheet.getDate_10().replaceAll(",", "||"));
			}else {
				up1.setDate_10(NULL);
			}
			if(btmEmpTimeSheet.getDate_11()!=null) {
				up1.setDate_11(btmEmpTimeSheet.getDate_11().replaceAll(",", "||"));
			}else {
				up1.setDate_11(NULL);
			}
			if(btmEmpTimeSheet.getDate_12()!=null) {
				up1.setDate_12(btmEmpTimeSheet.getDate_12().replaceAll(",", "||"));
			}else {
				up1.setDate_12(NULL);
			}
			if(btmEmpTimeSheet.getDate_13()!=null) {
				up1.setDate_13(btmEmpTimeSheet.getDate_13().replaceAll(",", "||"));
			}else {
				up1.setDate_13(NULL);
			}
			if(btmEmpTimeSheet.getDate_14()!=null) {
				up1.setDate_14(btmEmpTimeSheet.getDate_14().replaceAll(",", "||"));
			}else {
				up1.setDate_14(NULL);
			}
			if(btmEmpTimeSheet.getDate_15()!=null) {
				up1.setDate_15(btmEmpTimeSheet.getDate_15().replaceAll(",", "||"));
			}else {
				up1.setDate_15(NULL);
			}
			if(btmEmpTimeSheet.getDate_16()!=null) {
				up1.setDate_16(btmEmpTimeSheet.getDate_16().replaceAll(",", "||"));
			}else {
				up1.setDate_16(NULL);
			}
			if(btmEmpTimeSheet.getDate_17()!=null) {
				up1.setDate_17(btmEmpTimeSheet.getDate_17().replaceAll(",", "||"));
			}else {
				up1.setDate_17(NULL);
			}
			if(btmEmpTimeSheet.getDate_18()!=null) {
				up1.setDate_18(btmEmpTimeSheet.getDate_18().replaceAll(",", "||"));
			}else {
				up1.setDate_18(NULL);
			}
			if(btmEmpTimeSheet.getDate_19()!=null) {
				up1.setDate_19(btmEmpTimeSheet.getDate_19().replaceAll(",", "||"));
			}else {
				up1.setDate_19(NULL);
			}
			if(btmEmpTimeSheet.getDate_20()!=null) {
				up1.setDate_20(btmEmpTimeSheet.getDate_20().replaceAll(",", "||"));
			}else {
				up1.setDate_20(NULL);
			}
			if(btmEmpTimeSheet.getDate_21()!=null) {
				up1.setDate_21(btmEmpTimeSheet.getDate_21().replaceAll(",", "||"));
			}else {
				up1.setDate_21(NULL);
			}
			if(btmEmpTimeSheet.getDate_22()!=null) {
				up1.setDate_22(btmEmpTimeSheet.getDate_22().replaceAll(",", "||"));
			}else {
				up1.setDate_22(NULL);
			}
			if(btmEmpTimeSheet.getDate_23()!=null) {
				up1.setDate_23(btmEmpTimeSheet.getDate_23().replaceAll(",", "||"));
			}else {
				up1.setDate_23(NULL);
			}
			if(btmEmpTimeSheet.getDate_24()!=null) {
				up1.setDate_24(btmEmpTimeSheet.getDate_24().replaceAll(",", "||"));
			}else {
				up1.setDate_24(NULL);
			}
			if(btmEmpTimeSheet.getDate_25()!=null) {
				up1.setDate_25(btmEmpTimeSheet.getDate_25().replaceAll(",", "||"));
			}else {
				up1.setDate_25(NULL);
			}
			if(btmEmpTimeSheet.getDate_26()!=null) {
				up1.setDate_26(btmEmpTimeSheet.getDate_26().replaceAll(",", "||"));
			}else {
				up1.setDate_26(NULL);
			}
			if(btmEmpTimeSheet.getDate_27()!=null) {
				up1.setDate_27(btmEmpTimeSheet.getDate_27().replaceAll(",", "||"));
			}else {
				up1.setDate_27(NULL);
			}
			if(btmEmpTimeSheet.getDate_28()!=null) {
				up1.setDate_28(btmEmpTimeSheet.getDate_28().replaceAll(",", "||"));
			}else {
				up1.setDate_28(NULL);
			}
			if(btmEmpTimeSheet.getDate_29()!=null) {
				up1.setDate_29(btmEmpTimeSheet.getDate_29().replaceAll(",", "||"));
			}else {
				up1.setDate_29(NULL);
			}
			if(btmEmpTimeSheet.getDate_30()!=null) {
				up1.setDate_30(btmEmpTimeSheet.getDate_30().replaceAll(",", "||"));
			}else {
				up1.setDate_30(NULL);
			}
			if(btmEmpTimeSheet.getDate_31()!=null) {
				up1.setDate_31(btmEmpTimeSheet.getDate_31().replaceAll(",", "||"));
			}else {
				up1.setDate_31(NULL);
			}
			up1.setTs_status(btmEmpTimeSheet.getTs_status());
			btmEmpTimeSheetRep.save(up1);
			
			if(btmEmpTimeSheet.getDate_of_approval()!=null) {
				up1.setDate_of_approval(btmEmpTimeSheet.getDate_of_approval());
			}
			if(btmEmpTimeSheet.getDate_of_submission()!=null){
				up1.setDate_of_submission(btmEmpTimeSheet.getDate_of_submission());
				up1.setDel_flg('N');
				up1.setEntity_flg('N');
			}
			if(btmEmpTimeSheet.getReview_remarks()!=null) {
				up1.setReview_remarks(btmEmpTimeSheet.getReview_remarks());
				up1.setTs_status("Completed");
				btmEmpTimeSheetRep.save(up1);
			}
			msg="Time sheet Submitted succesfully";
			return msg;
			
	  }
	  public BTMEmpTimeSheet getTimeSheetselect(String emp_id) {

			Session session = sessionFactory.getCurrentSession();
			Query<BTMEmpTimeSheet> query = session.createQuery("from BTMEmpTimeSheet where emp_id=?1 order by year desc,month desc", BTMEmpTimeSheet.class);
			query.setParameter(1, emp_id);

			BTMEmpTimeSheet result = query.setMaxResults(1).uniqueResult();

			return result;
		}
	  public AttendanceRegister getMonthlyInquiries(String emp_id,String year,String month) {

			Session session = sessionFactory.getCurrentSession();
			Query<AttendanceRegister> query = session.createQuery("from AttendanceRegister where emp_id = ?1 AND cal_year=?2 and cal_month =?3", AttendanceRegister.class);
			query.setParameter(1, emp_id);
			query.setParameter(2, year);
			query.setParameter(3, month);
			AttendanceRegister result = query.setMaxResults(1).getSingleResult();

			return result;
		}
	  @Autowired
	  IssueTrackerRep issueTrackerRep;
	  public String addissue(IssueTracker save,String formmode) {
			String msg = "";
			IssueTracker up = save;
			up.setEntity_flg("N");
			up.setDel_flg("N");
			issueTrackerRep.save(save);
			msg="Issue Added Succesfully";
			return msg;
			
	  }
	  public String editissue(IssueTracker save,String formmode) {
			String msg = "";
			IssueTracker up = save;
			up.setEntity_flg("N");
			up.setDel_flg("N");
			up.setModify_flg("Y");
			issueTrackerRep.save(up);
			msg="Issue Modified Succesfully";
	  
			return msg;
			
	  }
	  @Autowired
	  DocumentMainRep documentMainRep;
	  public String uploadDoc(DocumentMaintenance DocMain,byte[] file) throws IOException {
			String msg = "";
			
			DocumentMaintenance doc = new DocumentMaintenance();
			doc.setEmp_id(DocMain.getEmp_id());
			doc.setDocument("document");
			doc.setFile_upload(file);
			doc.setFile_name(DocMain.getFile_name());
			doc.setEmp_name(DocMain.getEmp_name());
			documentMainRep.save(doc);
			
			msg="Document Uploaded Succesfully";
			
			return msg;
			
			
	  }
	  
	  
	  public String uploadDocadmin(DocumentMaintenance DocMain,byte[] file) throws IOException {
			String msg = "";
			
			DocumentMaintenance doc = new DocumentMaintenance();
			doc.setEmp_id(DocMain.getEmp_id());
			doc.setDocument("Adhar");
			doc.setFile_upload(file);
			doc.setFile_name(DocMain.getFile_name());
			doc.setEmp_name(DocMain.getEmp_name());
			documentMainRep.save(doc);
			
			msg="Document Uploaded Succesfully";
			
			return msg;
			
			
	  }
	  @Autowired
		BTMDocumentMasterRep btmDocumentMasterRep;
		  public String uploadDoc(BTMDocumentMaster Doc_ref_no,byte[] file) throws IOException {
				String msg = "";
				System.out.println("second step into service");
				BTMDocumentMaster doc = new BTMDocumentMaster();
				doc.setDoc_ref_no(Doc_ref_no.getDoc_ref_no());
				doc.setDocument(file);
				doc.setDoc_name(Doc_ref_no.getDoc_name());
				doc.setDoc_desc(Doc_ref_no.getDoc_desc());
				doc.setDoc_group(Doc_ref_no.getDoc_group());
				doc.setDoc_type(Doc_ref_no.getDoc_type());
				doc.setDoc_id(Doc_ref_no.getDoc_id());
				btmDocumentMasterRep.save(Doc_ref_no);
				
				msg="Document Uploaded Succesfully";
				
				return msg;
		  }
	  @Autowired
	  ExcelUploadService  excelUploadService;
	  public List<IssueTracker> UploadIssue(String screenId, MultipartFile file, String userid,IssueTracker issuetracker)
				throws FileNotFoundException, SQLException, IOException {

			List<IssueTracker> msg =  excelUploadService.UploadIssue(userid, file, userid, issuetracker);

			return msg;

		}
	  public String UploadPO(String screenId, MultipartFile file, String userid,PlacementMaintenance placementMaintenance)
				throws FileNotFoundException, SQLException, IOException {

			String msg =  excelUploadService.UploadPO(userid, file, userid, placementMaintenance);

			return msg;

		}
	  public ArrayList<LeaveResponseModal> dateSelector(String firstDate,String lastDate) {	
			
			ConnectionManager connMgr = new ConnectionManager();
			Connection conn = connMgr.getConnection();
			PreparedStatement ps = null;
			
			StringBuffer sql = new StringBuffer();
			HolidayMaster hmd= new HolidayMaster();
			ArrayList<String>  arl1 =hmd.Holidayselect();
			sql.append("select to_char(rownum - 1 + TO_DATE('"+firstDate+"', 'DD-MM-YYYY'),'DD-MM-YYYY') date_of_month,to_char(rownum - 1 + TO_DATE('"+firstDate+"', 'DD-MM-YYYY'),'DAY') day from all_objects where rownum < TO_DATE('"+lastDate+"', 'DD-MM-YYYY') - TO_DATE('"+firstDate+"', 'DD-MM-YYYY') + 2");
			
			ArrayList<LeaveResponseModal> arl = new ArrayList<LeaveResponseModal>();
			
			try {	     
				 ps = conn.prepareStatement(sql.toString());
				 ResultSet rs  = ps.executeQuery();
				 int i=1;
				while (rs.next()) {
					LeaveResponseModal lmb = new LeaveResponseModal();
					lmb.setDayofmonth((rs.getString("date_of_month")));
					lmb.setDay(rs.getString("DAY"));
					
					lmb.setSrlno(i++);
					if(lmb.getDay().trim().equals("SATURDAY") || lmb.getDay().trim().equals("SUNDAY")){
						lmb.setLeavedesc("WEEK END");
						lmb.setLeavedesc("0.0");
					}else{
						lmb.setLeavedesc("LEAVE");
						lmb.setLeavedesc("1.0");
					}if(arl1.contains(rs.getString("date_of_month"))) {
						lmb.setLeavedesc("HOLIDAY");
						lmb.setLeavedesc("0.0");
					}
					arl.add(lmb);
					
				}
				
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {			
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			return arl;
				
				
				
			}
	  
	  
	  
	  public static List<LocalDate> findSundaysBetweenDates(LocalDate fromDate, LocalDate toDate) {
	        List<LocalDate> sundays = new ArrayList<>();

	        while (!fromDate.isAfter(toDate)) {
	            if (fromDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
	                sundays.add(fromDate);
	            }
	            fromDate = fromDate.plusDays(1);
	        }

	        return sundays;
	    }
}