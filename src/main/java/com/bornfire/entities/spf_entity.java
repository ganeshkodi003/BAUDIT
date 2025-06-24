package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="BSPF_BTM")
public class spf_entity {

	@Id
	private String unique_id;
	private String insurance_no;
	private String	emp_no;
	private String	emp_name;
	private String	emp_desig;
	private Date	date_of_birth;
	private Date	date_of_join;
	private String	spf_acct_no;
	private String	urn_no;
	private BigDecimal	no_of_days;
	private BigDecimal	days_paid;
	private String	bank_name;
	private String	bank_acct_no;
	private String	salary_month;
	private String	basic_pay;
	private BigDecimal	hra;
	private BigDecimal	spl_allow;
	private BigDecimal	medi_reimb;
	private BigDecimal	conv_allow;
	private BigDecimal	lunch_allow;
	private BigDecimal	edu_allow;
	private BigDecimal	buss_attire;
	private BigDecimal	car_maint;
	private BigDecimal	leave_travel_allow;
	private BigDecimal	outstn_allow;
	private BigDecimal	annual_loyal_bonus;
	private BigDecimal	otr_allow;
	private String	gross_salary;
	private BigDecimal	spf;
	private BigDecimal	tds;
	private BigDecimal	prof_tax;
	private BigDecimal	esi;
	private BigDecimal	recovery;
	private BigDecimal	otr_ded;
	private BigDecimal	total_deductions;
	private BigDecimal	net_salary;
	private Date	date_of_pay;
	private BigDecimal	cum_tds_fy;
	private BigDecimal	ctc_amt;
	private BigDecimal	mobile_no;
	private String	email_id;
	private String	ifsc_code;
	private String	remarks;
	private String	adhar_no;
	private String emp_cont_12;
	private String emp_cont_833;
	private String emp_cont_367;
	private String tot_emp_cont;
	private String tran_type;
	
	
	
	public String getTran_type() {
		return tran_type;
	}
	public void setTran_type(String tran_type) {
		this.tran_type = tran_type;
	}
	public String getEmp_cont_12() {
		return emp_cont_12;
	}
	public void setEmp_cont_12(String emp_cont_12) {
		this.emp_cont_12 = emp_cont_12;
	}
	public String getEmp_cont_833() {
		return emp_cont_833;
	}
	public void setEmp_cont_833(String emp_cont_833) {
		this.emp_cont_833 = emp_cont_833;
	}
	public String getEmp_cont_367() {
		return emp_cont_367;
	}
	public void setEmp_cont_367(String emp_cont_367) {
		this.emp_cont_367 = emp_cont_367;
	}
	public String getTot_emp_cont() {
		return tot_emp_cont;
	}
	public void setTot_emp_cont(String tot_emp_cont) {
		this.tot_emp_cont = tot_emp_cont;
	}
	
	public String getUnique_id() {
		return unique_id;
	}
	public void setUnique_id(String unique_id) {
		this.unique_id = unique_id;
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_desig() {
		return emp_desig;
	}
	public void setEmp_desig(String emp_desig) {
		this.emp_desig = emp_desig;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public Date getDate_of_join() {
		return date_of_join;
	}
	public void setDate_of_join(Date date_of_join) {
		this.date_of_join = date_of_join;
	}
	public String getSpf_acct_no() {
		return spf_acct_no;
	}
	public void setSpf_acct_no(String spf_acct_no) {
		this.spf_acct_no = spf_acct_no;
	}
	public String getUrn_no() {
		return urn_no;
	}
	public void setUrn_no(String urn_no) {
		this.urn_no = urn_no;
	}
	public BigDecimal getNo_of_days() {
		return no_of_days;
	}
	public void setNo_of_days(BigDecimal no_of_days) {
		this.no_of_days = no_of_days;
	}
	public BigDecimal getDays_paid() {
		return days_paid;
	}
	public void setDays_paid(BigDecimal days_paid) {
		this.days_paid = days_paid;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getBank_acct_no() {
		return bank_acct_no;
	}
	public void setBank_acct_no(String bank_acct_no) {
		this.bank_acct_no = bank_acct_no;
	}
	public String getSalary_month() {
		return salary_month;
	}
	public void setSalary_month(String salary_month) {
		this.salary_month = salary_month;
	}
	public String getBasic_pay() {
		return basic_pay;
	}
	public void setBasic_pay(String basic_pay) {
		this.basic_pay = basic_pay;
	}
	public BigDecimal getHra() {
		return hra;
	}
	public void setHra(BigDecimal hra) {
		this.hra = hra;
	}
	public BigDecimal getSpl_allow() {
		return spl_allow;
	}
	public void setSpl_allow(BigDecimal spl_allow) {
		this.spl_allow = spl_allow;
	}
	public BigDecimal getMedi_reimb() {
		return medi_reimb;
	}
	public void setMedi_reimb(BigDecimal medi_reimb) {
		this.medi_reimb = medi_reimb;
	}
	public BigDecimal getConv_allow() {
		return conv_allow;
	}
	public void setConv_allow(BigDecimal conv_allow) {
		this.conv_allow = conv_allow;
	}
	public BigDecimal getLunch_allow() {
		return lunch_allow;
	}
	public void setLunch_allow(BigDecimal lunch_allow) {
		this.lunch_allow = lunch_allow;
	}
	public BigDecimal getEdu_allow() {
		return edu_allow;
	}
	public void setEdu_allow(BigDecimal edu_allow) {
		this.edu_allow = edu_allow;
	}
	public BigDecimal getBuss_attire() {
		return buss_attire;
	}
	public void setBuss_attire(BigDecimal buss_attire) {
		this.buss_attire = buss_attire;
	}
	public BigDecimal getCar_maint() {
		return car_maint;
	}
	public void setCar_maint(BigDecimal car_maint) {
		this.car_maint = car_maint;
	}
	public BigDecimal getLeave_travel_allow() {
		return leave_travel_allow;
	}
	public void setLeave_travel_allow(BigDecimal leave_travel_allow) {
		this.leave_travel_allow = leave_travel_allow;
	}
	public BigDecimal getOutstn_allow() {
		return outstn_allow;
	}
	public void setOutstn_allow(BigDecimal outstn_allow) {
		this.outstn_allow = outstn_allow;
	}
	public BigDecimal getAnnual_loyal_bonus() {
		return annual_loyal_bonus;
	}
	public void setAnnual_loyal_bonus(BigDecimal annual_loyal_bonus) {
		this.annual_loyal_bonus = annual_loyal_bonus;
	}
	public BigDecimal getOtr_allow() {
		return otr_allow;
	}
	public void setOtr_allow(BigDecimal otr_allow) {
		this.otr_allow = otr_allow;
	}
	public String getGross_salary() {
		return gross_salary;
	}
	public void setGross_salary(String gross_salary) {
		this.gross_salary = gross_salary;
	}
	public BigDecimal getSpf() {
		return spf;
	}
	public void setSpf(BigDecimal spf) {
		this.spf = spf;
	}
	public BigDecimal getTds() {
		return tds;
	}
	public void setTds(BigDecimal tds) {
		this.tds = tds;
	}
	public BigDecimal getProf_tax() {
		return prof_tax;
	}
	public void setProf_tax(BigDecimal prof_tax) {
		this.prof_tax = prof_tax;
	}
	public BigDecimal getEsi() {
		return esi;
	}
	public void setEsi(BigDecimal esi) {
		this.esi = esi;
	}
	public BigDecimal getRecovery() {
		return recovery;
	}
	public void setRecovery(BigDecimal recovery) {
		this.recovery = recovery;
	}
	public BigDecimal getOtr_ded() {
		return otr_ded;
	}
	public void setOtr_ded(BigDecimal otr_ded) {
		this.otr_ded = otr_ded;
	}
	public BigDecimal getTotal_deductions() {
		return total_deductions;
	}
	public void setTotal_deductions(BigDecimal total_deductions) {
		this.total_deductions = total_deductions;
	}
	public BigDecimal getNet_salary() {
		return net_salary;
	}
	public void setNet_salary(BigDecimal net_salary) {
		this.net_salary = net_salary;
	}
	public Date getDate_of_pay() {
		return date_of_pay;
	}
	public void setDate_of_pay(Date date_of_pay) {
		this.date_of_pay = date_of_pay;
	}
	public BigDecimal getCum_tds_fy() {
		return cum_tds_fy;
	}
	public void setCum_tds_fy(BigDecimal cum_tds_fy) {
		this.cum_tds_fy = cum_tds_fy;
	}
	public BigDecimal getCtc_amt() {
		return ctc_amt;
	}
	public void setCtc_amt(BigDecimal ctc_amt) {
		this.ctc_amt = ctc_amt;
	}
	public BigDecimal getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(BigDecimal mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getIfsc_code() {
		return ifsc_code;
	}
	public void setIfsc_code(String ifsc_code) {
		this.ifsc_code = ifsc_code;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getAdhar_no() {
		return adhar_no;
	}
	public void setAdhar_no(String adhar_no) {
		this.adhar_no = adhar_no;
	}
	public spf_entity(String unique_id,String emp_no, String emp_name, String emp_desig, Date date_of_birth, Date date_of_join,
			String spf_acct_no, String urn_no, BigDecimal no_of_days, BigDecimal days_paid, String bank_name,
			String bank_acct_no, String salary_month, String basic_pay, BigDecimal hra, BigDecimal spl_allow,
			BigDecimal medi_reimb, BigDecimal conv_allow, BigDecimal lunch_allow, BigDecimal edu_allow,
			BigDecimal buss_attire, BigDecimal car_maint, BigDecimal leave_travel_allow, BigDecimal outstn_allow,
			BigDecimal annual_loyal_bonus, BigDecimal otr_allow, String gross_salary, BigDecimal spf,
			BigDecimal tds, BigDecimal prof_tax, BigDecimal esi, BigDecimal recovery, BigDecimal otr_ded,
			BigDecimal total_deductions, BigDecimal net_salary, Date date_of_pay, BigDecimal cum_tds_fy,
			BigDecimal ctc_amt, BigDecimal mobile_no, String email_id, String ifsc_code, String remarks,String tran_type,
			String adhar_no) {
		super();
		this.tran_type=tran_type;
		this.unique_id=unique_id;
		this.emp_no = emp_no;
		this.emp_name = emp_name;
		this.emp_desig = emp_desig;
		this.date_of_birth = date_of_birth;
		this.date_of_join = date_of_join;
		this.spf_acct_no = spf_acct_no;
		this.urn_no = urn_no;
		this.no_of_days = no_of_days;
		this.days_paid = days_paid;
		this.bank_name = bank_name;
		this.bank_acct_no = bank_acct_no;
		this.salary_month = salary_month;
		this.basic_pay = basic_pay;
		this.hra = hra;
		this.spl_allow = spl_allow;
		this.medi_reimb = medi_reimb;
		this.conv_allow = conv_allow;
		this.lunch_allow = lunch_allow;
		this.edu_allow = edu_allow;
		this.buss_attire = buss_attire;
		this.car_maint = car_maint;
		this.leave_travel_allow = leave_travel_allow;
		this.outstn_allow = outstn_allow;
		this.annual_loyal_bonus = annual_loyal_bonus;
		this.otr_allow = otr_allow;
		this.gross_salary = gross_salary;
		this.spf = spf;
		this.tds = tds;
		this.prof_tax = prof_tax;
		this.esi = esi;
		this.recovery = recovery;
		this.otr_ded = otr_ded;
		this.total_deductions = total_deductions;
		this.net_salary = net_salary;
		this.date_of_pay = date_of_pay;
		this.cum_tds_fy = cum_tds_fy;
		this.ctc_amt = ctc_amt;
		this.mobile_no = mobile_no;
		this.email_id = email_id;
		this.ifsc_code = ifsc_code;
		this.remarks = remarks;
		this.adhar_no = adhar_no;
	}
	public spf_entity() {
		super();
		
	}
	

	public String toFormattedString() {
        // Implement a custom method to format the entity as a string
        return  basic_pay.toString();
        
    }
	public String getInsurance_no() {
		return insurance_no;
	}
	public void setInsurance_no(String insurance_no) {
		this.insurance_no = insurance_no;
	}

	
	
}
