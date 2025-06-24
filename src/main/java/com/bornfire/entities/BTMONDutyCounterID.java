package com.bornfire.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Embeddable;

import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
public class BTMONDutyCounterID  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal record_no;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date od_date;
	
	public BigDecimal getRecord_no() {
		return record_no;
	}
	public void setRecord_no(BigDecimal record_no) {
		this.record_no = record_no;
	}
	public Date getOd_date() {
		return od_date;
	}
	public void setOd_date(Date od_date) {
		this.od_date = od_date;
	}
	public BTMONDutyCounterID(BigDecimal record_no, Date od_date) {
		super();
		this.record_no = record_no;
		this.od_date = od_date;
	}
	public BTMONDutyCounterID() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
