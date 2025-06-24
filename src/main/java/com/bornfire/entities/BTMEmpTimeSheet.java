package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name= "EMP_TIME_SHEET")
@IdClass(TimesheetID.class)
public class BTMEmpTimeSheet {
	@Id
	private String emp_id;
	@Id
	private BigDecimal year;
	@Id
	private String month;
	private String	emp_name;
	private String	orgn_name;
	private String	proj_name;
	private String	client_name;
	private String	location;
	private String	rpt_mrg;
	private String	apr_auth;
	private String	date_1;
	private String	date_2;
	private String	date_3;
	private String	date_4;
	private String	date_5;
	private String	date_6;
	private String	date_7;
	private String	date_8;
	private String	date_9;
	private String	date_10;
	private String	date_11;
	private String	date_12;
	private String	date_13;
	private String	date_14;
	private String	date_25;
	private String	date_26;
	private String	date_27;
	private String	date_28;
	private String	date_29;
	private String	date_30;
	private String	date_31;
	private String	ts_status;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	date_of_submission;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	date_of_approval;
	private String	review_remarks;
	private Character	del_flg;
	private String	date_15;
	private String	date_16;
	private String	date_17;
	private String	date_18;
	private String	date_19;
	private String	date_20;
	private String	date_21;
	private String	date_22;
	private String	date_23;
	private String	date_24;
	private Character	entity_flg;
	private String auth_user;
	private Date timesheet_apply_date;
	
	
	
	
	public String getEmp_id() {
		return emp_id;
	}




	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}




	public BigDecimal getYear() {
		return year;
	}




	public void setYear(BigDecimal year) {
		this.year = year;
	}




	public String getMonth() {
		return month;
	}




	public void setMonth(String month) {
		this.month = month;
	}




	public String getEmp_name() {
		return emp_name;
	}




	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}




	public String getOrgn_name() {
		return orgn_name;
	}




	public void setOrgn_name(String orgn_name) {
		this.orgn_name = orgn_name;
	}




	public String getProj_name() {
		return proj_name;
	}




	public void setProj_name(String proj_name) {
		this.proj_name = proj_name;
	}




	public String getClient_name() {
		return client_name;
	}




	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}




	public String getLocation() {
		return location;
	}




	public void setLocation(String location) {
		this.location = location;
	}




	public String getRpt_mrg() {
		return rpt_mrg;
	}




	public void setRpt_mrg(String rpt_mrg) {
		this.rpt_mrg = rpt_mrg;
	}




	public String getApr_auth() {
		return apr_auth;
	}




	public void setApr_auth(String apr_auth) {
		this.apr_auth = apr_auth;
	}




	public String getDate_1() {
		return date_1;
	}




	public void setDate_1(String date_1) {
		this.date_1 = date_1;
	}




	public String getDate_2() {
		return date_2;
	}




	public void setDate_2(String date_2) {
		this.date_2 = date_2;
	}




	public String getDate_3() {
		return date_3;
	}




	public void setDate_3(String date_3) {
		this.date_3 = date_3;
	}




	public String getDate_4() {
		return date_4;
	}




	public void setDate_4(String date_4) {
		this.date_4 = date_4;
	}




	public String getDate_5() {
		return date_5;
	}




	public void setDate_5(String date_5) {
		this.date_5 = date_5;
	}




	public String getDate_6() {
		return date_6;
	}




	public void setDate_6(String date_6) {
		this.date_6 = date_6;
	}




	public String getDate_7() {
		return date_7;
	}




	public void setDate_7(String date_7) {
		this.date_7 = date_7;
	}




	public String getDate_8() {
		return date_8;
	}




	public void setDate_8(String date_8) {
		this.date_8 = date_8;
	}




	public String getDate_9() {
		return date_9;
	}




	public void setDate_9(String date_9) {
		this.date_9 = date_9;
	}




	public String getDate_10() {
		return date_10;
	}




	public void setDate_10(String date_10) {
		this.date_10 = date_10;
	}




	public String getDate_11() {
		return date_11;
	}




	public void setDate_11(String date_11) {
		this.date_11 = date_11;
	}




	public String getDate_12() {
		return date_12;
	}




	public void setDate_12(String date_12) {
		this.date_12 = date_12;
	}




	public String getDate_13() {
		return date_13;
	}




	public void setDate_13(String date_13) {
		this.date_13 = date_13;
	}




	public String getDate_14() {
		return date_14;
	}




	public void setDate_14(String date_14) {
		this.date_14 = date_14;
	}




	public String getDate_25() {
		return date_25;
	}




	public void setDate_25(String date_25) {
		this.date_25 = date_25;
	}




	public String getDate_26() {
		return date_26;
	}




	public void setDate_26(String date_26) {
		this.date_26 = date_26;
	}




	public String getDate_27() {
		return date_27;
	}




	public void setDate_27(String date_27) {
		this.date_27 = date_27;
	}




	public String getDate_28() {
		return date_28;
	}




	public void setDate_28(String date_28) {
		this.date_28 = date_28;
	}




	public String getDate_29() {
		return date_29;
	}




	public void setDate_29(String date_29) {
		this.date_29 = date_29;
	}




	public String getDate_30() {
		return date_30;
	}




	public void setDate_30(String date_30) {
		this.date_30 = date_30;
	}




	public String getDate_31() {
		return date_31;
	}




	public void setDate_31(String date_31) {
		this.date_31 = date_31;
	}




	public String getTs_status() {
		return ts_status;
	}




	public void setTs_status(String ts_status) {
		this.ts_status = ts_status;
	}




	public Date getDate_of_submission() {
		return date_of_submission;
	}




	public void setDate_of_submission(Date date_of_submission) {
		this.date_of_submission = date_of_submission;
	}




	public Date getDate_of_approval() {
		return date_of_approval;
	}




	public void setDate_of_approval(Date date_of_approval) {
		this.date_of_approval = date_of_approval;
	}




	public String getReview_remarks() {
		return review_remarks;
	}




	public void setReview_remarks(String review_remarks) {
		this.review_remarks = review_remarks;
	}




	public Character getDel_flg() {
		return del_flg;
	}




	public void setDel_flg(Character del_flg) {
		this.del_flg = del_flg;
	}




	public String getDate_15() {
		return date_15;
	}




	public void setDate_15(String date_15) {
		this.date_15 = date_15;
	}




	public String getDate_16() {
		return date_16;
	}




	public void setDate_16(String date_16) {
		this.date_16 = date_16;
	}




	public String getDate_17() {
		return date_17;
	}




	public void setDate_17(String date_17) {
		this.date_17 = date_17;
	}




	public String getDate_18() {
		return date_18;
	}




	public void setDate_18(String date_18) {
		this.date_18 = date_18;
	}




	public String getDate_19() {
		return date_19;
	}




	public void setDate_19(String date_19) {
		this.date_19 = date_19;
	}




	public String getDate_20() {
		return date_20;
	}




	public void setDate_20(String date_20) {
		this.date_20 = date_20;
	}




	public String getDate_21() {
		return date_21;
	}




	public void setDate_21(String date_21) {
		this.date_21 = date_21;
	}




	public String getDate_22() {
		return date_22;
	}




	public void setDate_22(String date_22) {
		this.date_22 = date_22;
	}




	public String getDate_23() {
		return date_23;
	}




	public void setDate_23(String date_23) {
		this.date_23 = date_23;
	}




	public String getDate_24() {
		return date_24;
	}




	public void setDate_24(String date_24) {
		this.date_24 = date_24;
	}




	public Character getEntity_flg() {
		return entity_flg;
	}




	public void setEntity_flg(Character entity_flg) {
		this.entity_flg = entity_flg;
	}




	public String getAuth_user() {
		return auth_user;
	}




	public void setAuth_user(String auth_user) {
		this.auth_user = auth_user;
	}




	public Date getTimesheet_apply_date() {
		return timesheet_apply_date;
	}




	public void setTimesheet_apply_date(Date timesheet_apply_date) {
		this.timesheet_apply_date = timesheet_apply_date;
	}




	public BTMEmpTimeSheet(String emp_id, BigDecimal year, String month, String emp_name, String orgn_name,
			String proj_name, String client_name, String location, String rpt_mrg, String apr_auth, String date_1,
			String date_2, String date_3, String date_4, String date_5, String date_6, String date_7, String date_8,
			String date_9, String date_10, String date_11, String date_12, String date_13, String date_14,
			String date_25, String date_26, String date_27, String date_28, String date_29, String date_30,
			String date_31, String ts_status, Date date_of_submission, Date date_of_approval, String review_remarks,
			Character del_flg, String date_15, String date_16, String date_17, String date_18, String date_19,
			String date_20, String date_21, String date_22, String date_23, String date_24, Character entity_flg,
			String auth_user, Date timesheet_apply_date) {
		super();
		this.emp_id = emp_id;
		this.year = year;
		this.month = month;
		this.emp_name = emp_name;
		this.orgn_name = orgn_name;
		this.proj_name = proj_name;
		this.client_name = client_name;
		this.location = location;
		this.rpt_mrg = rpt_mrg;
		this.apr_auth = apr_auth;
		this.date_1 = date_1;
		this.date_2 = date_2;
		this.date_3 = date_3;
		this.date_4 = date_4;
		this.date_5 = date_5;
		this.date_6 = date_6;
		this.date_7 = date_7;
		this.date_8 = date_8;
		this.date_9 = date_9;
		this.date_10 = date_10;
		this.date_11 = date_11;
		this.date_12 = date_12;
		this.date_13 = date_13;
		this.date_14 = date_14;
		this.date_25 = date_25;
		this.date_26 = date_26;
		this.date_27 = date_27;
		this.date_28 = date_28;
		this.date_29 = date_29;
		this.date_30 = date_30;
		this.date_31 = date_31;
		this.ts_status = ts_status;
		this.date_of_submission = date_of_submission;
		this.date_of_approval = date_of_approval;
		this.review_remarks = review_remarks;
		this.del_flg = del_flg;
		this.date_15 = date_15;
		this.date_16 = date_16;
		this.date_17 = date_17;
		this.date_18 = date_18;
		this.date_19 = date_19;
		this.date_20 = date_20;
		this.date_21 = date_21;
		this.date_22 = date_22;
		this.date_23 = date_23;
		this.date_24 = date_24;
		this.entity_flg = entity_flg;
		this.auth_user = auth_user;
		this.timesheet_apply_date = timesheet_apply_date;
	}




	public BTMEmpTimeSheet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	


}
