package com.bornfire.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SPF")

public class Spfentity {
	
	@Id
	private String	employee_id;
	private String	employee_name;
	private String	designation;
	private String	mon;
	
	private String	dob;
	private String	doj;
	private String	name_as_per_aadhar;
	private String	uan_number;
	private String	epf_account_number;
	private String	basic_salary;
	private String	gross_salary;
	private String	employee_contribution1;
	private String	employee_contribution2;
	private String	employee_contribution3;
	private String	total_employer_contribution;
	
	
	public String getMon() {
		return mon;
	}
	public void setMon(String mon) {
		this.mon = mon;
	}
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		this.doj = doj;
	}
	public String getName_as_per_aadhar() {
		return name_as_per_aadhar;
	}
	public void setName_as_per_aadhar(String name_as_per_aadhar) {
		this.name_as_per_aadhar = name_as_per_aadhar;
	}
	public String getUan_number() {
		return uan_number;
	}
	public void setUan_number(String uan_number) {
		this.uan_number = uan_number;
	}
	public String getEpf_account_number() {
		return epf_account_number;
	}
	public void setEpf_account_number(String epf_account_number) {
		this.epf_account_number = epf_account_number;
	}
	public String getBasic_salary() {
		return basic_salary;
	}
	public void setBasic_salary(String basic_salary) {
		this.basic_salary = basic_salary;
	}
	public String getGross_salary() {
		return gross_salary;
	}
	public void setGross_salary(String gross_salary) {
		this.gross_salary = gross_salary;
	}
	public String getEmployee_contribution1() {
		return employee_contribution1;
	}
	public void setEmployee_contribution1(String employee_contribution1) {
		this.employee_contribution1 = employee_contribution1;
	}
	public String getEmployee_contribution2() {
		return employee_contribution2;
	}
	public void setEmployee_contribution2(String employee_contribution2) {
		this.employee_contribution2 = employee_contribution2;
	}
	public String getEmployee_contribution3() {
		return employee_contribution3;
	}
	public void setEmployee_contribution3(String employee_contribution3) {
		this.employee_contribution3 = employee_contribution3;
	}
	public String getTotal_employer_contribution() {
		return total_employer_contribution;
	}
	public void setTotal_employer_contribution(String total_employer_contribution) {
		this.total_employer_contribution = total_employer_contribution;
	}
	
	public Spfentity(String employee_id, String employee_name, String designation, String mon, String dob, String doj,
			String name_as_per_aadhar, String uan_number, String epf_account_number, String basic_salary,
			String gross_salary, String employee_contribution1, String employee_contribution2,
			String employee_contribution3, String total_employer_contribution) {
		super();
		this.employee_id = employee_id;
		this.employee_name = employee_name;
		this.designation = designation;
		this.mon = mon;
		this.dob = dob;
		this.doj = doj;
		this.name_as_per_aadhar = name_as_per_aadhar;
		this.uan_number = uan_number;
		this.epf_account_number = epf_account_number;
		this.basic_salary = basic_salary;
		this.gross_salary = gross_salary;
		this.employee_contribution1 = employee_contribution1;
		this.employee_contribution2 = employee_contribution2;
		this.employee_contribution3 = employee_contribution3;
		this.total_employer_contribution = total_employer_contribution;
	}
	public Spfentity() {
		super();
		// TODO Auto-generated constructor stub
	}

}
