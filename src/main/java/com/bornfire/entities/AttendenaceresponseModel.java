package com.bornfire.entities;

import org.springframework.stereotype.Component;

@Component
public class AttendenaceresponseModel {
	
	private String emp_id;
	private String emp_name;
	private String present_count;
	private int holidays;
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
	public String getPresent_count() {
		return present_count;
	}
	public void setPresent_count(String present_count) {
		this.present_count = present_count;
	}
	
	public AttendenaceresponseModel(String emp_id, String emp_name, String present_count, int holidays) {
		super();
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.present_count = present_count;
		this.holidays = holidays;
	}
	public AttendenaceresponseModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "AttendenaceresponseModel [emp_id=" + emp_id + ", emp_name=" + emp_name + ", present_count="
				+ present_count + ", holidays=" + holidays + "]";
	}
	public int getHolidays() {
		return holidays;
	}
	public void setHolidays(int holidays) {
		this.holidays = holidays;
	}
	
	

}
