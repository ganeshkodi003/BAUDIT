package com.bornfire.services;

import java.util.Optional;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bornfire.entities.AttendanceID;
import com.bornfire.entities.AttendanceRegister;
import com.bornfire.entities.AttendanceRegisterRep;
import com.bornfire.entities.BTMAdminAssociateProfile;
import com.bornfire.entities.BTMAdminAssociateProfileRep;
import com.bornfire.entities.PlacementResourceMaster;

@Service
@ConfigurationProperties("output")
@Transactional
public class InquiriesServices {

	// private static final Logger logger =
	// LoggerFactory.getLogger(PlacementServices.class);

	@Autowired
	AttendanceRegisterRep attendanceRegisterRep;

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	DataSource srcdataSource;

	@Autowired
	BTMAdminAssociateProfileRep btmAdminAssociateProfileRep;

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

	public AttendanceRegister getUser(AttendanceID userid3) {

		if (attendanceRegisterRep.existsById(userid3)) {
			AttendanceRegister up = attendanceRegisterRep.findById(userid3).get();
			up.getDel_flg();
			return up;
		} else {
			return new AttendanceRegister();
		}

	};

	public BTMAdminAssociateProfile getAssociateData(String id) {

		if (btmAdminAssociateProfileRep.existsById(id)) {
			BTMAdminAssociateProfile up = btmAdminAssociateProfileRep.findById(id).get();
			up.getDel_flg();
			return up;
		} else {
			return new BTMAdminAssociateProfile();
		}

	};

}
