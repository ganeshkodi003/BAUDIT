package com.bornfire.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ON_DUTY")
public class BTMAdminSampleOD {

	private String leave_ref;

	private String record_no;

	@Id
	private String emp_id;

	private String emp_name;

	private String od_category;

	private String od_counter;
 
	private String od_date;

	private String od_day;

	private String half_day_counter;

	private String od_desc;

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

	public String getOd_category() {
		return od_category;
	}

	public void setOd_category(String od_category) {
		this.od_category = od_category;
	}

	public String getOd_counter() {
		return od_counter;
	}

	public void setOd_counter(String od_counter) {
		this.od_counter = od_counter;
	}

	public String getOd_date() {
		return od_date;
	}

	public void setOd_date(String od_date) {
		this.od_date = od_date;
	}

	public String getOd_day() {
		return od_day;
	}

	public void setOd_day(String od_day) {
		this.od_day = od_day;
	}

	public String getHalf_day_counter() {
		return half_day_counter;
	}

	public void setHalf_day_counter(String half_day_counter) {
		this.half_day_counter = half_day_counter;
	}

	public String getOd_desc() {
		return od_desc;
	}

	public void setOd_desc(String od_desc) {
		this.od_desc = od_desc;
	}

	public BTMAdminSampleOD(String leave_ref, String record_no, String emp_id, String emp_name, String od_category,
			String od_counter, String od_date, String od_day, String half_day_counter, String od_desc) {
		super();
		this.leave_ref = leave_ref;
		this.record_no = record_no;
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.od_category = od_category;
		this.od_counter = od_counter;
		this.od_date = od_date;
		this.od_day = od_day;
		this.half_day_counter = half_day_counter;
		this.od_desc = od_desc;
	}

	public BTMAdminSampleOD() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
