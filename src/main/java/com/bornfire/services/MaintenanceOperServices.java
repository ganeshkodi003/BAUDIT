package com.bornfire.services;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bornfire.entities.BTMAdminAssociateProfile;
import com.bornfire.entities.BTMWorkAssignment;
import com.bornfire.entities.BTMWorkAssignmentRep;
import com.bornfire.entities.LeaveMaster;
import com.ibm.icu.util.Calendar;

@Service
@ConfigurationProperties("output")
@Transactional
public class MaintenanceOperServices {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	BTMWorkAssignmentRep btmWorkAssignmentRep;

	public String addWorkAssign(BTMWorkAssignment btmWorkAssignment, String formmode,String userid,String emp_name) throws ParseException {
		Session hs = sessionFactory.getCurrentSession();
		String msg = "";
		
		if (formmode.equals("add")) {
			DecimalFormat numformate = new DecimalFormat("000");

			BigDecimal billNumber = (BigDecimal) hs.createNativeQuery("SELECT WORK_SRL_NO.NEXTVAL AS SRL_NO FROM DUAL")
					.getSingleResult();
			String serialno = String.valueOf(numformate.format(billNumber));
			System.out.println(serialno + "sno");

			BTMWorkAssignment up = btmWorkAssignment;
			String srlNo = up.getEmp_team() + serialno + up.getEmp_id();
			up.setEmp_name(emp_name);
			up.setSrl_no(srlNo);
			up.setEntity_flg("N");
			up.setDel_flg("N");

			btmWorkAssignmentRep.save(up);
			msg = "Added Successfully";

		} else if (formmode.equals("edit")) {
			BTMWorkAssignment up1 =	btmWorkAssignmentRep.getWorkMaster(userid);
			
			//BTMWorkAssignment up = new BTMWorkAssignment();
			BTMWorkAssignment up = btmWorkAssignment;
			
			up1.setEmp_location(up.getEmp_location());
			up1.setEmp_group(up.getEmp_group());
			up1.setEmp_team(up.getEmp_team());
			up1.setAssign_date(up.getAssign_date());
			up1.setCur_assignment(up.getCur_assignment());
			up1.setStart_date(up.getStart_date());
			up1.setWork_detail(up.getWork_detail());
			up1.setWork_const(up.getWork_const());
			up1.setWork_escalation(up.getWork_escalation());
			up1.setEnd_date(up.getEnd_date());
			up1.setStatus(up.getStatus());
			up1.setFollow_up_remarks(up.getFollow_up_remarks());
			up1.setModify_flg("Y");
			up1.setEntity_flg("Y");
			up1.setDel_flg("N");
			btmWorkAssignmentRep.save(up1);
			
			msg = "Edited Successfully";

		}

		return msg;
	}
}
