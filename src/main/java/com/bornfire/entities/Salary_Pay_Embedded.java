package com.bornfire.entities;

import javax.persistence.Embeddable;
import javax.persistence.Id;

import java.io.Serializable;
import java.util.Date;

@Embeddable
public class Salary_Pay_Embedded implements Serializable {
	
	private static final long serialVersionUID=1L;
	@Id
	private String	emp_no;
	@Id
	private Date	ctc_eff_date;
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public Date getCtc_eff_date() {
		return ctc_eff_date;
	}
	public void setCtc_eff_date(Date ctc_eff_date) {
		this.ctc_eff_date = ctc_eff_date;
	}
	
	
	
	
	


    
    // Constructors, getters, setters, equals, and hashCode methods

}
