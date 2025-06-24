package com.bornfire.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
public class AttendanceID  implements Serializable {
	private String emp_id;
	private Date login_time;
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public Date getLogin_time() {
		return login_time;
	}
	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}
	public AttendanceID(String emp_id, Date login_time) {
		super();
		this.emp_id = emp_id;
		this.login_time = login_time;
	}
	public AttendanceID() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
