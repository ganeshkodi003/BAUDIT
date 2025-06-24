package com.bornfire.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;

@Embeddable
public class TimesheetID implements Serializable {
	private String emp_id;
	private BigDecimal year;
	private String month;
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
	public TimesheetID(String emp_id, BigDecimal year, String month) {
		super();
		this.emp_id = emp_id;
		this.year = year;
		this.month = month;
	}
	public TimesheetID() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
