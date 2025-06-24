package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ON_DUTY")
public class OnDuty {
	private String leave_ref;
	@Id
	private String record_no;
	private String emp_id;
	private String emp_name;
	private String emp_desig;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date date_of_join;
	private String od_category;
	private String no_of_days;
	@DateTimeFormat(pattern = "dd-MMM-yy")
	private Date od_from;
	@DateTimeFormat(pattern = "dd-MMM-yy")
	private Date od_to;
	private String project;
	private String client;
	private String remarks;
	private String entry_user;
	private String modify_user;
	private String auth_user;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date entry_time;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date modify_time;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date auth_time;
	private String del_flg;
	private String entity_flg;
	private String modify_flg;
	private String reject_remarks;
	private String status;
	private BigDecimal year;
	private String device;

	public String getLeave_ref() {
		return leave_ref;
	}

	public void setLeave_ref(String leave_ref) {
		this.leave_ref = leave_ref;
	}

	public String getRecord_no() {
		return record_no;
	}

	public void setRecord_no(String record_no) {
		this.record_no = record_no;
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

	public Date getDate_of_join() {
		return date_of_join;
	}

	public void setDate_of_join(Date date_of_join) {
		this.date_of_join = date_of_join;
	}

	public String getOd_category() {
		return od_category;
	}

	public void setOd_category(String od_category) {
		this.od_category = od_category;
	}

	public String getNo_of_days() {
		return no_of_days;
	}

	public void setNo_of_days(String no_of_days) {
		this.no_of_days = no_of_days;
	}

	public Date getOd_from() {
		return od_from;
	}

	public void setOd_from(Date od_from) {
		this.od_from = od_from;
	}

	public Date getOd_to() {
		return od_to;
	}

	public void setOd_to(Date od_to) {
		this.od_to = od_to;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public String getDel_flg() {
		return del_flg;
	}

	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
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

	public String getReject_remarks() {
		return reject_remarks;
	}

	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getYear() {
		return year;
	}

	public void setYear(BigDecimal year) {
		this.year = year;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public OnDuty(String leave_ref, String record_no, String emp_id, String emp_name, String emp_desig,
			Date date_of_join, String od_category, String no_of_days, Date od_from, Date od_to, String project,
			String client, String remarks, String entry_user, String modify_user, String auth_user, Date entry_time,
			Date modify_time, Date auth_time, String del_flg, String entity_flg, String modify_flg,
			String reject_remarks, String status, BigDecimal year, String device) {
		super();
		this.leave_ref = leave_ref;
		this.record_no = record_no;
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.emp_desig = emp_desig;
		this.date_of_join = date_of_join;
		this.od_category = od_category;
		this.no_of_days = no_of_days;
		this.od_from = od_from;
		this.od_to = od_to;
		this.project = project;
		this.client = client;
		this.remarks = remarks;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.auth_user = auth_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.auth_time = auth_time;
		this.del_flg = del_flg;
		this.entity_flg = entity_flg;
		this.modify_flg = modify_flg;
		this.reject_remarks = reject_remarks;
		this.status = status;
		this.year = year;
		this.device = device;
	}

	public OnDuty() {
		super();
		// TODO Auto-generated constructor stub
	}

}
