package com.bornfire.services;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bornfire.entities.AttendanceID;
import com.bornfire.entities.AttendanceRegister;
import com.bornfire.entities.AttendanceRegisterGet;
import com.bornfire.entities.AttendanceRegisterGetRep;
import com.bornfire.entities.AttendanceRegisterRep;
import com.bornfire.entities.ResourceMaster;
import com.bornfire.entities.ResourceMasterRepo;
import com.bornfire.entities.UserLogHistoryRepo;
import com.bornfire.entities.User_Log_HistoryID;
import com.bornfire.entities.User_log_History;
import com.ibm.icu.text.SimpleDateFormat;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;

@Service
@Transactional
public class AttendanceRegisterService {

	@Autowired
	SessionFactory sessionfactory;

	@Autowired
	AttendanceRegisterRep attendanceRegisterRep;

	@Autowired
	ResourceMasterRepo resourceMasterRepo;

	@Autowired
	UserLogHistoryRepo userLogHistoryRepo;

	@Autowired
	AttendanceRegisterGetRep attendanceRegisterGetRep;

	public String Registersubmit(ResourceMaster user, String session_value) {
		// login Time
		String msg = "successs";
		System.out.println("hihihihiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat simpDate = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
		SimpleDateFormat formatweekday = new SimpleDateFormat("EEEE");
		SimpleDateFormat formatyear = new SimpleDateFormat("yyy");
		SimpleDateFormat formatdate = new SimpleDateFormat("dd-MMM-yy");
		SimpleDateFormat login = new SimpleDateFormat("dd-MMM-yyy HH:mm:ss");
		SimpleDateFormat formatdat = new SimpleDateFormat("dd");
		SimpleDateFormat formatMonth = new SimpleDateFormat("MM");

		String cal_month = formatMonth.format(cal.getTime());
		String cal_date = formatdat.format(cal.getTime());
		String cal_year = formatyear.format(cal.getTime());
		String str = formatdate.format(cal.getTime());
		Date dat1 = null;
		try {
			dat1 = formatdate.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Session hs = sessionfactory.getCurrentSession();

		AttendanceRegister attendanceRegisterdata;
		AttendanceRegister user1 = new AttendanceRegister();
		AttendanceID id = new AttendanceID();
		AttendanceRegisterGet attendanceRegisterdata2 = attendanceRegisterGetRep
				.getdatapresentOnduty(user.getResource_id(), cal_month, cal_date, cal_year);
		AttendanceRegisterGet attendanceRegisterdata3 = attendanceRegisterGetRep
				.getdatapresentLeave(user.getResource_id(), cal_year, cal_month, cal_date);
		AttendanceRegister attendanceRegisterdata20 = attendanceRegisterRep.getdatapresentOnduty(user.getResource_id(),
				cal_month, cal_date, cal_year);
		AttendanceRegister attendanceRegisterdata30 = attendanceRegisterRep.getdatapresentLeave(user.getResource_id(),
				cal_month, cal_date, cal_year);
		try {

			attendanceRegisterdata = attendanceRegisterRep.getdatapresent(user.getResource_id(), cal_month, cal_date,
					cal_year);
			if (!(attendanceRegisterdata.getId().getEmp_id().isEmpty())) {

				String user_id = user.getResource_id();
				ResourceMaster resourcmaster = resourceMasterRepo.getuserData(user_id);

				resourcmaster.setSession_id(session_value);

				resourceMasterRepo.save(resourcmaster);
			}
		} catch (NullPointerException e) {
			// catching null pointer exception from above try block

			// inserting new data into attendance and into user
			// log/////////////////////////////////////////////////////////
			// first attendance entry for particular user for current
			// date///////////////////////////////////////
			String ipadd = null;
			try {
				ipadd = InetAddress.getLocalHost().toString();
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			ConnectionManager connMgr = new ConnectionManager();
			Connection conn = connMgr.getConnection();
			PreparedStatement ps1 = null;

			int rs = 0;
			String logintime = login.format(cal.getTime());
			String sql3 = "INSERT INTO BTM.EMP_ATT_MASTER(EMP_ID,EMP_NAME,FIRST_ENTRY_TIME,LOGIN_TIME,EMP_REMARKS,IP_ADDRESS,DEL_FLG,LEAVE_FLG,DEVICE) VALUES ('"
					+ user.getEmployee_id() + "','" + user.getResource_name() + "','" + formatdate.format(cal.getTime())
					+ "',(TO_DATE('" + logintime + "', 'dd/mm/yyyy HH24:Mi:ss')),'Present','" + ipadd
					+ "','N','N','SYSTEM')";
			try {
				ps1 = conn.prepareStatement(sql3.toString());
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				rs = ps1.executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String user_id = user.getResource_id();
			ResourceMaster resourcmaster = resourceMasterRepo.getuserData(user_id);

			resourcmaster.setSession_id(session_value);
			resourceMasterRepo.save(resourcmaster);

			User_log_History userlog = new User_log_History();
			User_Log_HistoryID userlogid = new User_Log_HistoryID();

			userlogid.setUser_id(user_id);
			userlogid.setLogin_time(dat1);
			userlog.setUser_Log_HistoryID(userlogid);

			userlog.setLogin_status("Active");
			userlog.setIp_address(ipadd);

			userLogHistoryRepo.save(userlog);
		}
		try {
			if (attendanceRegisterdata3.getLogin_time1().equals("00:00:00")) {

				Session hs1 = sessionfactory.getCurrentSession();
				attendanceRegisterRep.loginupdate1(user.getResource_id(), cal_month, cal_date, cal_year);
				hs1.saveOrUpdate(attendanceRegisterdata30);

			}
		} catch (NullPointerException e) {
		}
		try {
			if (attendanceRegisterdata2.getLogin_time1().equals("00:00:00")) {
				Session hs1 = sessionfactory.getCurrentSession();
				attendanceRegisterRep.loginupdate(user.getResource_id(), cal_month, cal_date, cal_year);
				hs1.saveOrUpdate(attendanceRegisterdata20);
			}
		} catch (NullPointerException e) {
		}

		return msg;

	}

	public String logoutsubmit(String userid1) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat simpDate = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
		SimpleDateFormat formatweekday = new SimpleDateFormat("EEEE");
		SimpleDateFormat formatyear = new SimpleDateFormat("yyyy");
		SimpleDateFormat formatdate = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formatdat = new SimpleDateFormat("dd");
		SimpleDateFormat formatMonth = new SimpleDateFormat("MM");

		String cal_month = formatMonth.format(cal.getTime());
		String cal_date = formatdat.format(cal.getTime());
		String cal_year = formatyear.format(cal.getTime());
		String msg = "success";
		String str = formatdate.format(cal.getTime());
		Date dat2 = null;
		try {
			dat2 = formatdate.parse(str);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		AttendanceRegister attendanceRegisterdata2 = attendanceRegisterRep.getdatapresent(userid1, cal_month, cal_date,
				cal_year);
		AttendanceID id = new AttendanceID();

		Session hs = sessionfactory.getCurrentSession();
		attendanceRegisterRep.logoutupdate(userid1, cal_month, cal_date, cal_year);
		hs.saveOrUpdate(attendanceRegisterdata2);
		return msg;
	}
}
