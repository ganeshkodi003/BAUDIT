package com.bornfire.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "EMP_ATT_MASTER")
@Component
public class AttendanceRegister  {

	 @EmbeddedId
 private AttendanceID id;
	
	private String emp_name;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date logout_time;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private String logout_time1;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String login_time1;
	
	private String leave_flg;
	private String leave_category;
	private String leave_remarks;
	

	private String first_entry_time;
	private String last_update_time;
	private String del_flg;
	private String emp_remarks;
	
	
	private String ip_address;
	private String device;
	private String login_status;
	private String cal_year;
	private String cal_month;
	private String cal_date;
	public AttendanceID getId() {
		return id;
	}
	public void setId(AttendanceID id) {
		this.id = id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public Date getLogout_time() {
		return logout_time;
	}
	public void setLogout_time(Date logout_time) {
		this.logout_time = logout_time;
	}
	public String getLogout_time1() {
		return logout_time1;
	}
	public void setLogout_time1(String logout_time1) {
		this.logout_time1 = logout_time1;
	}
	public String getLogin_time1() {
		return login_time1;
	}
	public void setLogin_time1(String login_time1) {
		this.login_time1 = login_time1;
	}
	public String getLeave_flg() {
		return leave_flg;
	}
	public void setLeave_flg(String leave_flg) {
		this.leave_flg = leave_flg;
	}
	public String getLeave_category() {
		return leave_category;
	}
	public void setLeave_category(String leave_category) {
		this.leave_category = leave_category;
	}
	public String getLeave_remarks() {
		return leave_remarks;
	}
	public void setLeave_remarks(String leave_remarks) {
		this.leave_remarks = leave_remarks;
	}
	public String getFirst_entry_time() {
		return first_entry_time;
	}
	public void setFirst_entry_time(String first_entry_time) {
		this.first_entry_time = first_entry_time;
	}
	public String getLast_update_time() {
		return last_update_time;
	}
	public void setLast_update_time(String last_update_time) {
		this.last_update_time = last_update_time;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public String getEmp_remarks() {
		return emp_remarks;
	}
	public void setEmp_remarks(String emp_remarks) {
		this.emp_remarks = emp_remarks;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getLogin_status() {
		return login_status;
	}
	public void setLogin_status(String login_status) {
		this.login_status = login_status;
	}
	public String getCal_year() {
		return cal_year;
	}
	public void setCal_year(String cal_year) {
		this.cal_year = cal_year;
	}
	public String getCal_month() {
		return cal_month;
	}
	public void setCal_month(String cal_month) {
		this.cal_month = cal_month;
	}
	public String getCal_date() {
		return cal_date;
	}
	public void setCal_date(String cal_date) {
		this.cal_date = cal_date;
	}
	@Override
	public String toString() {
		return "AttendanceRegister [id=" + id + ", emp_name=" + emp_name + ", logout_time=" + logout_time
				+ ", logout_time1=" + logout_time1 + ", login_time1=" + login_time1 + ", leave_flg=" + leave_flg
				+ ", leave_category=" + leave_category + ", leave_remarks=" + leave_remarks + ", first_entry_time="
				+ first_entry_time + ", last_update_time=" + last_update_time + ", del_flg=" + del_flg
				+ ", emp_remarks=" + emp_remarks + ", ip_address=" + ip_address + ", device=" + device
				+ ", login_status=" + login_status + ", cal_year=" + cal_year + ", cal_month=" + cal_month
				+ ", cal_date=" + cal_date + ", getId()=" + getId() + ", getEmp_name()=" + getEmp_name()
				+ ", getLogout_time()=" + getLogout_time() + ", getLogout_time1()=" + getLogout_time1()
				+ ", getLogin_time1()=" + getLogin_time1() + ", getLeave_flg()=" + getLeave_flg()
				+ ", getLeave_category()=" + getLeave_category() + ", getLeave_remarks()=" + getLeave_remarks()
				+ ", getFirst_entry_time()=" + getFirst_entry_time() + ", getLast_update_time()="
				+ getLast_update_time() + ", getDel_flg()=" + getDel_flg() + ", getEmp_remarks()=" + getEmp_remarks()
				+ ", getIp_address()=" + getIp_address() + ", getDevice()=" + getDevice() + ", getLogin_status()="
				+ getLogin_status() + ", getCal_year()=" + getCal_year() + ", getCal_month()=" + getCal_month()
				+ ", getCal_date()=" + getCal_date() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	public AttendanceRegister(AttendanceID id, String emp_name, Date logout_time, String logout_time1, String login_time1,
			String leave_flg, String leave_category, String leave_remarks, String first_entry_time,
			String last_update_time, String del_flg, String emp_remarks, String ip_address, String device,
			String login_status, String cal_year, String cal_month, String cal_date) {
		super();
		this.id = id;
		this.emp_name = emp_name;
		this.logout_time = logout_time;
		this.logout_time1 = logout_time1;
		this.login_time1 = login_time1;
		this.leave_flg = leave_flg;
		this.leave_category = leave_category;
		this.leave_remarks = leave_remarks;
		this.first_entry_time = first_entry_time;
		this.last_update_time = last_update_time;
		this.del_flg = del_flg;
		this.emp_remarks = emp_remarks;
		this.ip_address = ip_address;
		this.device = device;
		this.login_status = login_status;
		this.cal_year = cal_year;
		this.cal_month = cal_month;
		this.cal_date = cal_date;
	}
	public AttendanceRegister() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}