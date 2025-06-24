package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "LEAVE_MASTER")
public class LeaveMaster {
	private String leave_reference;
	
	@Id
	private BigDecimal record_no;
	
	private String employee_id;
	private String employee_name;
	private String designation;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date date_of_join;
	private String leave_category;
	private String no_of_days;
	@DateTimeFormat(pattern = "dd-MMM-yy")
	private Date from_date;
	@DateTimeFormat(pattern = "dd-MMM-yy")
	private Date to_date;
	private String reason_for_leave;
	private String remarks;
	private String leave_blc;
	private String modify_flg;
	private String del_flg;
	private String entity_flg;
	private String reject_remarks;
	private String entry_user;
	private String modify_user;
	private String auth_user;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date entry_time;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date modify_time;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date auth_time;
	private String status;
	private BigDecimal year;
	private String device;

	public String getLeave_reference() {
		return leave_reference;
	}

	public void setLeave_reference(String leave_reference) {
		this.leave_reference = leave_reference;
	}

	public BigDecimal getRecord_no() {
		return record_no;
	}

	public void setRecord_no(BigDecimal record_no) {
		this.record_no = record_no;
	}

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Date getDate_of_join() {
		return date_of_join;
	}

	public void setDate_of_join(Date date_of_join) {
		this.date_of_join = date_of_join;
	}

	public String getLeave_category() {
		return leave_category;
	}

	public void setLeave_category(String leave_category) {
		this.leave_category = leave_category;
	}

	public String getNo_of_days() {
		return no_of_days;
	}

	public void setNo_of_days(String no_of_days) {
		this.no_of_days = no_of_days;
	}

	public Date getFrom_date() {
		return from_date;
	}

	public void setFrom_date(Date from_date) {
		this.from_date = from_date;
	}

	public Date getTo_date() {
		return to_date;
	}

	public void setTo_date(Date to_date) {
		this.to_date = to_date;
	}

	public String getReason_for_leave() {
		return reason_for_leave;
	}

	public void setReason_for_leave(String reason_for_leave) {
		this.reason_for_leave = reason_for_leave;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getLeave_blc() {
		return leave_blc;
	}

	public void setLeave_blc(String leave_blc) {
		this.leave_blc = leave_blc;
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

	public String getEntity_flg() {
		return entity_flg;
	}

	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}

	public String getReject_remarks() {
		return reject_remarks;
	}

	public void setReject_remarks(String reject_remarks) {
		this.reject_remarks = reject_remarks;
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

	public LeaveMaster(String leave_reference, BigDecimal record_no, String employee_id, String employee_name,
			String designation, Date date_of_join, String leave_category, String no_of_days, Date from_date,
			Date to_date, String reason_for_leave, String remarks, String leave_blc, String modify_flg, String del_flg,
			String entity_flg, String reject_remarks, String entry_user, String modify_user, String auth_user,
			Date entry_time, Date modify_time, Date auth_time, String status, BigDecimal year, String device) {
		super();
		this.leave_reference = leave_reference;
		this.record_no = record_no;
		this.employee_id = employee_id;
		this.employee_name = employee_name;
		this.designation = designation;
		this.date_of_join = date_of_join;
		this.leave_category = leave_category;
		this.no_of_days = no_of_days;
		this.from_date = from_date;
		this.to_date = to_date;
		this.reason_for_leave = reason_for_leave;
		this.remarks = remarks;
		this.leave_blc = leave_blc;
		this.modify_flg = modify_flg;
		this.del_flg = del_flg;
		this.entity_flg = entity_flg;
		this.reject_remarks = reject_remarks;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.auth_user = auth_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.auth_time = auth_time;
		this.status = status;
		this.year = year;
		this.device = device;
	}

	@Override
	public String toString() {
		return "LeaveMaster [leave_reference=" + leave_reference + ", record_no=" + record_no + ", employee_id="
				+ employee_id + ", employee_name=" + employee_name + ", designation=" + designation + ", date_of_join="
				+ date_of_join + ", leave_category=" + leave_category + ", no_of_days=" + no_of_days + ", from_date="
				+ from_date + ", to_date=" + to_date + ", reason_for_leave=" + reason_for_leave + ", remarks=" + remarks
				+ ", leave_blc=" + leave_blc + ", modify_flg=" + modify_flg + ", del_flg=" + del_flg + ", entity_flg="
				+ entity_flg + ", reject_remarks=" + reject_remarks + ", entry_user=" + entry_user + ", modify_user="
				+ modify_user + ", auth_user=" + auth_user + ", entry_time=" + entry_time + ", modify_time="
				+ modify_time + ", auth_time=" + auth_time + ", status=" + status + ", year=" + year + ", device="
				+ device + ", getLeave_reference()=" + getLeave_reference() + ", getRecord_no()=" + getRecord_no()
				+ ", getEmployee_id()=" + getEmployee_id() + ", getEmployee_name()=" + getEmployee_name()
				+ ", getDesignation()=" + getDesignation() + ", getDate_of_join()=" + getDate_of_join()
				+ ", getLeave_category()=" + getLeave_category() + ", getNo_of_days()=" + getNo_of_days()
				+ ", getFrom_date()=" + getFrom_date() + ", getTo_date()=" + getTo_date() + ", getReason_for_leave()="
				+ getReason_for_leave() + ", getRemarks()=" + getRemarks() + ", getLeave_blc()=" + getLeave_blc()
				+ ", getModify_flg()=" + getModify_flg() + ", getDel_flg()=" + getDel_flg() + ", getEntity_flg()="
				+ getEntity_flg() + ", getReject_remarks()=" + getReject_remarks() + ", getEntry_user()="
				+ getEntry_user() + ", getModify_user()=" + getModify_user() + ", getAuth_user()=" + getAuth_user()
				+ ", getEntry_time()=" + getEntry_time() + ", getModify_time()=" + getModify_time()
				+ ", getAuth_time()=" + getAuth_time() + ", getStatus()=" + getStatus() + ", getYear()=" + getYear()
				+ ", getDevice()=" + getDevice() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	public LeaveMaster() {
		super();
		// TODO Auto-generated constructor stub
	}

}
