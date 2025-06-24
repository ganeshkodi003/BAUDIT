package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BANK_DETAILS")
public class BankAcctEntity {

	@Id
	private String	bank_acct_no;
	private String	emp_acct_no;
	private String	emp_name;
	private BigDecimal	tran_amt;
	private String	tran_mode;
	private Date	tran_date;
	private String	ifsc_code;
	private String	location;
	private String	print_location;
	private BigDecimal	emp_mobile_no;
	private String	emp_email_id;
	private String	emp_addr1;
	private String	emp_addr2;
	private String	emp_addr3;
	private String	emp_addr4;
	private String	add_det1;
	private String	add_det2;
	private String	add_det3;
	private String	add_det4;
	private String	add_det5;
	private String	tran_remarks;
	private String	tran_type;
	private String	del_flg;
	private String	entity_flg;
	private String	file_gen_usr;
	private Date	file_gen_time;
	private String	upload_flg;
	private String	upload_status;
	private String	rej_flg;
	private String	reupload_flg;
	private Date	reupload_date;
	private String	reupload_status;
	public String getBank_acct_no() {
		return bank_acct_no;
	}
	public void setBank_acct_no(String bank_acct_no) {
		this.bank_acct_no = bank_acct_no;
	}
	public String getEmp_acct_no() {
		return emp_acct_no;
	}
	public void setEmp_acct_no(String emp_acct_no) {
		this.emp_acct_no = emp_acct_no;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public BigDecimal getTran_amt() {
		return tran_amt;
	}
	public void setTran_amt(BigDecimal tran_amt) {
		this.tran_amt = tran_amt;
	}
	public String getTran_mode() {
		return tran_mode;
	}
	public void setTran_mode(String tran_mode) {
		this.tran_mode = tran_mode;
	}
	public Date getTran_date() {
		return tran_date;
	}
	public void setTran_date(Date tran_date) {
		this.tran_date = tran_date;
	}
	public String getIfsc_code() {
		return ifsc_code;
	}
	public void setIfsc_code(String ifsc_code) {
		this.ifsc_code = ifsc_code;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPrint_location() {
		return print_location;
	}
	public void setPrint_location(String print_location) {
		this.print_location = print_location;
	}
	public BigDecimal getEmp_mobile_no() {
		return emp_mobile_no;
	}
	public void setEmp_mobile_no(BigDecimal emp_mobile_no) {
		this.emp_mobile_no = emp_mobile_no;
	}
	public String getEmp_email_id() {
		return emp_email_id;
	}
	public void setEmp_email_id(String emp_email_id) {
		this.emp_email_id = emp_email_id;
	}
	public String getEmp_addr1() {
		return emp_addr1;
	}
	public void setEmp_addr1(String emp_addr1) {
		this.emp_addr1 = emp_addr1;
	}
	public String getEmp_addr2() {
		return emp_addr2;
	}
	public void setEmp_addr2(String emp_addr2) {
		this.emp_addr2 = emp_addr2;
	}
	public String getEmp_addr3() {
		return emp_addr3;
	}
	public void setEmp_addr3(String emp_addr3) {
		this.emp_addr3 = emp_addr3;
	}
	public String getEmp_addr4() {
		return emp_addr4;
	}
	public void setEmp_addr4(String emp_addr4) {
		this.emp_addr4 = emp_addr4;
	}
	public String getAdd_det1() {
		return add_det1;
	}
	public void setAdd_det1(String add_det1) {
		this.add_det1 = add_det1;
	}
	public String getAdd_det2() {
		return add_det2;
	}
	public void setAdd_det2(String add_det2) {
		this.add_det2 = add_det2;
	}
	public String getAdd_det3() {
		return add_det3;
	}
	public void setAdd_det3(String add_det3) {
		this.add_det3 = add_det3;
	}
	public String getAdd_det4() {
		return add_det4;
	}
	public void setAdd_det4(String add_det4) {
		this.add_det4 = add_det4;
	}
	public String getAdd_det5() {
		return add_det5;
	}
	public void setAdd_det5(String add_det5) {
		this.add_det5 = add_det5;
	}
	public String getTran_remarks() {
		return tran_remarks;
	}
	public void setTran_remarks(String tran_remarks) {
		this.tran_remarks = tran_remarks;
	}
	public String getTran_type() {
		return tran_type;
	}
	public void setTran_type(String tran_type) {
		this.tran_type = tran_type;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public String getEntity_flg() {
		return entity_flg;
	}
	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}
	public String getFile_gen_usr() {
		return file_gen_usr;
	}
	public void setFile_gen_usr(String file_gen_usr) {
		this.file_gen_usr = file_gen_usr;
	}
	public Date getFile_gen_time() {
		return file_gen_time;
	}
	public void setFile_gen_time(Date file_gen_time) {
		this.file_gen_time = file_gen_time;
	}
	public String getUpload_flg() {
		return upload_flg;
	}
	public void setUpload_flg(String upload_flg) {
		this.upload_flg = upload_flg;
	}
	public String getUpload_status() {
		return upload_status;
	}
	public void setUpload_status(String upload_status) {
		this.upload_status = upload_status;
	}
	public String getRej_flg() {
		return rej_flg;
	}
	public void setRej_flg(String rej_flg) {
		this.rej_flg = rej_flg;
	}
	public String getReupload_flg() {
		return reupload_flg;
	}
	public void setReupload_flg(String reupload_flg) {
		this.reupload_flg = reupload_flg;
	}
	public Date getReupload_date() {
		return reupload_date;
	}
	public void setReupload_date(Date reupload_date) {
		this.reupload_date = reupload_date;
	}
	public String getReupload_status() {
		return reupload_status;
	}
	public void setReupload_status(String reupload_status) {
		this.reupload_status = reupload_status;
	}
	public BankAcctEntity(String bank_acct_no, String emp_acct_no, String emp_name, BigDecimal tran_amt,
			String tran_mode, Date tran_date, String ifsc_code, String location, String print_location,
			BigDecimal emp_mobile_no, String emp_email_id, String emp_addr1, String emp_addr2, String emp_addr3,
			String emp_addr4, String add_det1, String add_det2, String add_det3, String add_det4, String add_det5,
			String tran_remarks, String tran_type, String del_flg, String entity_flg, String file_gen_usr,
			Date file_gen_time, String upload_flg, String upload_status, String rej_flg, String reupload_flg,
			Date reupload_date, String reupload_status) {
		super();
		this.bank_acct_no = bank_acct_no;
		this.emp_acct_no = emp_acct_no;
		this.emp_name = emp_name;
		this.tran_amt = tran_amt;
		this.tran_mode = tran_mode;
		this.tran_date = tran_date;
		this.ifsc_code = ifsc_code;
		this.location = location;
		this.print_location = print_location;
		this.emp_mobile_no = emp_mobile_no;
		this.emp_email_id = emp_email_id;
		this.emp_addr1 = emp_addr1;
		this.emp_addr2 = emp_addr2;
		this.emp_addr3 = emp_addr3;
		this.emp_addr4 = emp_addr4;
		this.add_det1 = add_det1;
		this.add_det2 = add_det2;
		this.add_det3 = add_det3;
		this.add_det4 = add_det4;
		this.add_det5 = add_det5;
		this.tran_remarks = tran_remarks;
		this.tran_type = tran_type;
		this.del_flg = del_flg;
		this.entity_flg = entity_flg;
		this.file_gen_usr = file_gen_usr;
		this.file_gen_time = file_gen_time;
		this.upload_flg = upload_flg;
		this.upload_status = upload_status;
		this.rej_flg = rej_flg;
		this.reupload_flg = reupload_flg;
		this.reupload_date = reupload_date;
		this.reupload_status = reupload_status;
	}
	public BankAcctEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
