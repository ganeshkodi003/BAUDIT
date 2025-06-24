package com.bornfire.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TM_EMPLOYEE_ONDUTY_COUNTER")
public class BTMAdminOndutyCount {
	@Id
	BTMONDutyCounterID btmONDutyCounterID;

	private String leave_ref;
	private String emp_id;

	private String emp_name;

	private String od_category;

	private Float od_counter;

	private String od_day;

	private String half_day_counter;

	private String od_desc;

	
	public String getLeave_ref() {
		return leave_ref;
	}


	public void setLeave_ref(String leave_ref) {
		this.leave_ref = leave_ref;
	}


	public BTMONDutyCounterID getBtmONDutyCounterID() {
		return btmONDutyCounterID;
	}


	public void setBtmONDutyCounterID(BTMONDutyCounterID btmONDutyCounterID) {
		this.btmONDutyCounterID = btmONDutyCounterID;
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


	public Float getOd_counter() {
		return od_counter;
	}


	public void setOd_counter(Float od_counter) {
		this.od_counter = od_counter;
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


	public BTMAdminOndutyCount(BTMONDutyCounterID btmONDutyCounterID, String leave_ref, String emp_id, String emp_name,
			String od_category, Float od_counter, String od_day, String half_day_counter, String od_desc) {
		super();
		this.btmONDutyCounterID = btmONDutyCounterID;
		this.leave_ref = leave_ref;
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.od_category = od_category;
		this.od_counter = od_counter;
		this.od_day = od_day;
		this.half_day_counter = half_day_counter;
		this.od_desc = od_desc;
	}

 

	public BTMAdminOndutyCount() {
		super();
		// TODO Auto-generated constructor stub
	}

}
