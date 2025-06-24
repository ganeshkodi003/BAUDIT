package com.bornfire.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
public class AssociateResponseModel {
	
	private String  emp_id;
	private String  emp_name;
	private Date  att_date;
	private String  att_day;
	private String  remarks;
	private String status;
	@DateTimeFormat(pattern="HH:MM:SS:SS")
	private String login_time1;
	@DateTimeFormat(pattern="HH:MM:SS:SS")
	private String logout_time1;
	private long difference;
	private String login;
	private String logout;
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
	public Date getAtt_date() {
		return att_date;
	}
	public void setAtt_date(Date att_date) {
		this.att_date = att_date;
	}
	public String getAtt_day() {
		return att_day;
	}
	public void setAtt_day(String att_day) {
		this.att_day = att_day;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getLogin_time1() {
		return login_time1;
	}
	public void setLogin_time1(String login_time1) {
		this.login_time1 = login_time1;
	}
	public String getLogout_time1() {
		return logout_time1;
	}
	public void setLogout_time1(String logout_time1) {
		this.logout_time1 = logout_time1;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getLogout() {
		return logout;
	}
	public void setLogout(String logout) {
		this.logout = logout;
	}
	
	public AssociateResponseModel(String emp_id, String emp_name, Date att_date, String att_day, String remarks,
			String status, String login_time1, String logout_time1, long difference, String login, String logout) {
		super();
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.att_date = att_date;
		this.att_day = att_day;
		this.remarks = remarks;
		this.status = status;
		this.login_time1 = login_time1;
		this.logout_time1 = logout_time1;
		this.difference = difference;
		this.login = login;
		this.logout = logout;
	}
	
	@Override
	public String toString() {
		return "AssociateResponseModel [emp_id=" + emp_id + ", emp_name=" + emp_name + ", att_date=" + att_date
				+ ", att_day=" + att_day + ", remarks=" + remarks + ", status=" + status + ", login_time1="
				+ login_time1 + ", logout_time1=" + logout_time1 + ", difference=" + difference + ", login=" + login
				+ ", logout=" + logout + "]";
	}
	public AssociateResponseModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getDifference() {
		return difference;
	}
	public void setDifference(long difference) {
		this.difference = difference;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
