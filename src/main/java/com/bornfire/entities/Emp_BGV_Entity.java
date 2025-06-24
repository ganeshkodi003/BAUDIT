package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "EMP_BGV")
public class Emp_BGV_Entity {
	private String	emp_type;
	private String	organization;
	@Id
	private String	emp_id;
	private String	emp_name;
	private String	qual;
	private String	designation;
	private String	role;
	private String	address;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	date_of_birth;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	date_of_joining;
	private String	bank_name;
	private String	acct_no;
	private String	pan_no;
	private String	aadhaar_no;
	private String	passport;
	private String	driving_license;
	private BigDecimal	mobile_no;
	private String	email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	date_of_email_intimation;
	private String	previous_experience;
	private String	check_details;
	@Lob
	private byte[]	degree_cert;
	@Lob
	private byte[]	id_proof;
	@Lob
	private byte[] declaration_form;
	@Lob
	private byte[]	verify_cert;
	@Lob
	private byte[]	exp_letter;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	doc_frm_emp;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	doc_sub_date;
	private String	follow_up_action_1;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	remainder_date_1;
	private String	follow_up_action_2;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	remainder_date_2;
	private String	follow_up_action_3;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	remainder_date_3;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	doc_send_date;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	date_of_receipt;
	private String	doc_status;
	private String	remarks;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	invoice_date;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	pay_date;
	private BigDecimal	invoice_amt;
	@Lob
	@Column(name = "upload_invoice", columnDefinition = "BLOB")
	private byte[] upload_invoice;
	private String	entry_user;
	private String	modify_user;
	private String	auth_user;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	entry_time;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	modify_time;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	auth_time;
	private String	entity_flg;
	private String	modify_flg;
	private String	del_flg;
	public String getEmp_type() {
		return emp_type;
	}
	public void setEmp_type(String emp_type) {
		this.emp_type = emp_type;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
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
	public String getQual() {
		return qual;
	}
	public void setQual(String qual) {
		this.qual = qual;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public Date getDate_of_joining() {
		return date_of_joining;
	}
	public void setDate_of_joining(Date date_of_joining) {
		this.date_of_joining = date_of_joining;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getAcct_no() {
		return acct_no;
	}
	public void setAcct_no(String acct_no) {
		this.acct_no = acct_no;
	}
	public String getPan_no() {
		return pan_no;
	}
	public void setPan_no(String pan_no) {
		this.pan_no = pan_no;
	}
	public String getAadhaar_no() {
		return aadhaar_no;
	}
	public void setAadhaar_no(String aadhaar_no) {
		this.aadhaar_no = aadhaar_no;
	}
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public String getDriving_license() {
		return driving_license;
	}
	public void setDriving_license(String driving_license) {
		this.driving_license = driving_license;
	}
	public BigDecimal getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(BigDecimal mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDate_of_email_intimation() {
		return date_of_email_intimation;
	}
	public void setDate_of_email_intimation(Date date_of_email_intimation) {
		this.date_of_email_intimation = date_of_email_intimation;
	}
	public String getPrevious_experience() {
		return previous_experience;
	}
	public void setPrevious_experience(String previous_experience) {
		this.previous_experience = previous_experience;
	}
	public String getCheck_details() {
		return check_details;
	}
	public void setCheck_details(String check_details) {
		this.check_details = check_details;
	}
	public byte[] getDegree_cert() {
		return degree_cert;
	}
	public void setDegree_cert(byte[] degree_cert) {
		this.degree_cert = degree_cert;
	}
	public byte[] getId_proof() {
		return id_proof;
	}
	public void setId_proof(byte[] id_proof) {
		this.id_proof = id_proof;
	}
	public byte[] getDeclaration_form() {
		return declaration_form;
	}
	public void setDeclaration_form(byte[] declaration_form) {
		this.declaration_form = declaration_form;
	}
	public byte[] getVerify_cert() {
		return verify_cert;
	}
	public void setVerify_cert(byte[] verify_cert) {
		this.verify_cert = verify_cert;
	}
	public byte[] getExp_letter() {
		return exp_letter;
	}
	public void setExp_letter(byte[] exp_letter) {
		this.exp_letter = exp_letter;
	}
	public Date getDoc_frm_emp() {
		return doc_frm_emp;
	}
	public void setDoc_frm_emp(Date doc_frm_emp) {
		this.doc_frm_emp = doc_frm_emp;
	}
	public Date getDoc_sub_date() {
		return doc_sub_date;
	}
	public void setDoc_sub_date(Date doc_sub_date) {
		this.doc_sub_date = doc_sub_date;
	}
	public String getFollow_up_action_1() {
		return follow_up_action_1;
	}
	public void setFollow_up_action_1(String follow_up_action_1) {
		this.follow_up_action_1 = follow_up_action_1;
	}
	public Date getRemainder_date_1() {
		return remainder_date_1;
	}
	public void setRemainder_date_1(Date remainder_date_1) {
		this.remainder_date_1 = remainder_date_1;
	}
	public String getFollow_up_action_2() {
		return follow_up_action_2;
	}
	public void setFollow_up_action_2(String follow_up_action_2) {
		this.follow_up_action_2 = follow_up_action_2;
	}
	public Date getRemainder_date_2() {
		return remainder_date_2;
	}
	public void setRemainder_date_2(Date remainder_date_2) {
		this.remainder_date_2 = remainder_date_2;
	}
	public String getFollow_up_action_3() {
		return follow_up_action_3;
	}
	public void setFollow_up_action_3(String follow_up_action_3) {
		this.follow_up_action_3 = follow_up_action_3;
	}
	public Date getRemainder_date_3() {
		return remainder_date_3;
	}
	public void setRemainder_date_3(Date remainder_date_3) {
		this.remainder_date_3 = remainder_date_3;
	}
	public Date getDoc_send_date() {
		return doc_send_date;
	}
	public void setDoc_send_date(Date doc_send_date) {
		this.doc_send_date = doc_send_date;
	}
	public Date getDate_of_receipt() {
		return date_of_receipt;
	}
	public void setDate_of_receipt(Date date_of_receipt) {
		this.date_of_receipt = date_of_receipt;
	}
	public String getDoc_status() {
		return doc_status;
	}
	public void setDoc_status(String doc_status) {
		this.doc_status = doc_status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getInvoice_date() {
		return invoice_date;
	}
	public void setInvoice_date(Date invoice_date) {
		this.invoice_date = invoice_date;
	}
	public Date getPay_date() {
		return pay_date;
	}
	public void setPay_date(Date pay_date) {
		this.pay_date = pay_date;
	}
	public BigDecimal getInvoice_amt() {
		return invoice_amt;
	}
	public void setInvoice_amt(BigDecimal invoice_amt) {
		this.invoice_amt = invoice_amt;
	}
	public byte[] getUpload_invoice() {
		return upload_invoice;
	}
	public void setUpload_invoice(byte[] upload_invoice) {
		this.upload_invoice = upload_invoice;
	}
	public String getEntry_user() {
		return entry_user;
	}
	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}
	public String getModify_user() {
		return modify_user;
	}
	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}
	public String getAuth_user() {
		return auth_user;
	}
	public void setAuth_user(String auth_user) {
		this.auth_user = auth_user;
	}
	public Date getEntry_time() {
		return entry_time;
	}
	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}
	public Date getModify_time() {
		return modify_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	public Date getAuth_time() {
		return auth_time;
	}
	public void setAuth_time(Date auth_time) {
		this.auth_time = auth_time;
	}
	public String getEntity_flg() {
		return entity_flg;
	}
	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}
	public String getModify_flg() {
		return modify_flg;
	}
	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public Emp_BGV_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
}
