package com.bornfire.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
public class LeaveMasterCounterId implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal	record_no;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	leave_date;
	public BigDecimal getRecord_no() {
		return record_no;
	}
	public void setRecord_no(BigDecimal record_no) {
		this.record_no = record_no;
	}
	public Date getLeave_date() {
		return leave_date;
	}
	public void setLeave_date(Date leave_date) {
		this.leave_date = leave_date;
	}
	
	public LeaveMasterCounterId(BigDecimal record_no, Date leave_date) {
		super();
		this.record_no = record_no;
		this.leave_date = leave_date;
	}
	public LeaveMasterCounterId() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
