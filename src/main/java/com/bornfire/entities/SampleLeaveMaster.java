package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "")
public class SampleLeaveMaster {
	
	private String	leave_ref;
	private BigDecimal	record_no;
	@Id
	private String	emp_id;
	private String	emp_name;
	private String	leave_category;
	private float	leave_counter;
	@DateTimeFormat(pattern = "dd-MMM-yy")
	private String	leave_date;
	private String	leave_day;
	private String	half_day_counter;
	private String	leave_desc;
	private String	del_flg;
	public String getLeave_ref() {
		return leave_ref;
	}
	public void setLeave_ref(String leave_ref) {
		this.leave_ref = leave_ref;
	}
	public BigDecimal getRecord_no() {
		return record_no;
	}
	public void setRecord_no(BigDecimal record_no) {
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
	public String getLeave_category() {
		return leave_category;
	}
	public void setLeave_category(String leave_category) {
		this.leave_category = leave_category;
	}
	public float getLeave_counter() {
		return leave_counter;
	}
	public void setLeave_counter(float leave_counter) {
		this.leave_counter = leave_counter;
	}
	public String getLeave_date() {
		return leave_date;
	}
	public void setLeave_date(String leave_date) {
		this.leave_date = leave_date;
	}
	public String getLeave_day() {
		return leave_day;
	}
	public void setLeave_day(String leave_day) {
		this.leave_day = leave_day;
	}
	public String getHalf_day_counter() {
		return half_day_counter;
	}
	public void setHalf_day_counter(String half_day_counter) {
		this.half_day_counter = half_day_counter;
	}
	public String getLeave_desc() {
		return leave_desc;
	}
	public void setLeave_desc(String leave_desc) {
		this.leave_desc = leave_desc;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	
	public SampleLeaveMaster(String leave_ref, BigDecimal record_no, String emp_id, String emp_name,
			String leave_category, float leave_counter, String leave_date, String leave_day, String half_day_counter,
			String leave_desc, String del_flg) {
		super();
		this.leave_ref = leave_ref;
		this.record_no = record_no;
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.leave_category = leave_category;
		this.leave_counter = leave_counter;
		this.leave_date = leave_date;
		this.leave_day = leave_day;
		this.half_day_counter = half_day_counter;
		this.leave_desc = leave_desc;
		this.del_flg = del_flg;
	}
	
	public SampleLeaveMaster() {
		super();
		// TODO Auto-generated constructor stub
	}

	

}
