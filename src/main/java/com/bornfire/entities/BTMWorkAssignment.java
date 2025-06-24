package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "WORK_ASSIGN_MASTER")
public class BTMWorkAssignment {
	
	@Id
	private String	srl_no;
	private String	emp_id;
	private String	emp_name;
	private String	emp_desig;
	private String	emp_group;
	private String	emp_team;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	date_of_birth;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	date_of_joining;
	private String	emp_location;
	private String	rpt_manager;
	private String	app_auth;
	private BigDecimal	assign_srl_no;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	assign_date;
	private String	cur_assignment;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	start_date;
	private String	work_detail;
	private String	work_const;
	private String	work_escalation;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	end_date;
	private String	status;
	private String	follow_up_remarks;
	private String	entry_user;
	private String	modify_user;
	private String	auth_user;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	entry_time;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	modify_time;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	auth_time;
	private String	entity_flg;
	private String	modify_flg;
	private String	del_flg;
	private BigDecimal	year;
	private String	month;
	
	public String getSrl_no() {
		return srl_no;
	}
	public void setSrl_no(String srl_no) {
		this.srl_no = srl_no;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_desig() {
		return emp_desig;
	}
	public void setEmp_desig(String emp_desig) {
		this.emp_desig = emp_desig;
	}
	public String getEmp_group() {
		return emp_group;
	}
	public void setEmp_group(String emp_group) {
		this.emp_group = emp_group;
	}
	public String getEmp_team() {
		return emp_team;
	}
	public void setEmp_team(String emp_team) {
		this.emp_team = emp_team;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public Date getDate_of_joining() {
		return date_of_joining;
	}
	public void setDate_of_joining(Date date_of_joining) {
		this.date_of_joining = date_of_joining;
	}
	public String getEmp_location() {
		return emp_location;
	}
	public void setEmp_location(String emp_location) {
		this.emp_location = emp_location;
	}
	public String getRpt_manager() {
		return rpt_manager;
	}
	public void setRpt_manager(String rpt_manager) {
		this.rpt_manager = rpt_manager;
	}
	public String getApp_auth() {
		return app_auth;
	}
	public void setApp_auth(String app_auth) {
		this.app_auth = app_auth;
	}
	public BigDecimal getAssign_srl_no() {
		return assign_srl_no;
	}
	public void setAssign_srl_no(BigDecimal assign_srl_no) {
		this.assign_srl_no = assign_srl_no;
	}
	public Date getAssign_date() {
		return assign_date;
	}
	public void setAssign_date(Date assign_date) {
		this.assign_date = assign_date;
	}
	public String getCur_assignment() {
		return cur_assignment;
	}
	public void setCur_assignment(String cur_assignment) {
		this.cur_assignment = cur_assignment;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public String getWork_detail() {
		return work_detail;
	}
	public void setWork_detail(String work_detail) {
		this.work_detail = work_detail;
	}
	public String getWork_const() {
		return work_const;
	}
	public void setWork_const(String work_const) {
		this.work_const = work_const;
	}
	public String getWork_escalation() {
		return work_escalation;
	}
	public void setWork_escalation(String work_escalation) {
		this.work_escalation = work_escalation;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFollow_up_remarks() {
		return follow_up_remarks;
	}
	public void setFollow_up_remarks(String follow_up_remarks) {
		this.follow_up_remarks = follow_up_remarks;
	}
	public String getEntry_user() {
		return entry_user;
	}
	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}
	public String getModify_user() {
		return modify_user;
	}
	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}
	public String getAuth_user() {
		return auth_user;
	}
	public void setAuth_user(String auth_user) {
		this.auth_user = auth_user;
	}
	public Date getEntry_time() {
		return entry_time;
	}
	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}
	public Date getModify_time() {
		return modify_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	public Date getAuth_time() {
		return auth_time;
	}
	public void setAuth_time(Date auth_time) {
		this.auth_time = auth_time;
	}
	public String getEntity_flg() {
		return entity_flg;
	}
	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}
	public String getModify_flg() {
		return modify_flg;
	}
	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
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
	
	public BTMWorkAssignment(String srl_no, String emp_id, String emp_name, String emp_desig, String emp_group,
			String emp_team, Date date_of_birth, Date date_of_joining, String emp_location, String rpt_manager,
			String app_auth, BigDecimal assign_srl_no, Date assign_date, String cur_assignment, Date start_date,
			String work_detail, String work_const, String work_escalation, Date end_date, String status,
			String follow_up_remarks, String entry_user, String modify_user, String auth_user, Date entry_time,
			Date modify_time, Date auth_time, String entity_flg, String modify_flg, String del_flg, BigDecimal year,
			String month) {
		super();
		this.srl_no = srl_no;
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.emp_desig = emp_desig;
		this.emp_group = emp_group;
		this.emp_team = emp_team;
		this.date_of_birth = date_of_birth;
		this.date_of_joining = date_of_joining;
		this.emp_location = emp_location;
		this.rpt_manager = rpt_manager;
		this.app_auth = app_auth;
		this.assign_srl_no = assign_srl_no;
		this.assign_date = assign_date;
		this.cur_assignment = cur_assignment;
		this.start_date = start_date;
		this.work_detail = work_detail;
		this.work_const = work_const;
		this.work_escalation = work_escalation;
		this.end_date = end_date;
		this.status = status;
		this.follow_up_remarks = follow_up_remarks;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.auth_user = auth_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.auth_time = auth_time;
		this.entity_flg = entity_flg;
		this.modify_flg = modify_flg;
		this.del_flg = del_flg;
		this.year = year;
		this.month = month;
	}
	public BTMWorkAssignment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
