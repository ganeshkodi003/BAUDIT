package com.bornfire.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="BANK_MASTER")
public class BankMaster {
	
	@Id
	private String	bank_srl_no;
	
	private String	bank_name;
	private String	bank_br;
	
	private String	acc_number;
	private String	acc_name;
	private String	bank_addr;
	private String	acct_type;
	private String	acct_crncy;
	private String	addr;
	private String	ifsc_code;
	private String	swift_code;
	private String	payment_code;
	private String	payment_mode;
	private String	del_flg;
	private String	entity_flg;
	private String	modify_flg;
	private String	entry_user;
	private String	modify_user;
	private String	verify_user;
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date	entry_time;
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date	modify_time;
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date	verify_time;
	public String getBank_srl_no() {
		return bank_srl_no;
	}
	public void setBank_srl_no(String bank_srl_no) {
		this.bank_srl_no = bank_srl_no;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getBank_br() {
		return bank_br;
	}
	public void setBank_br(String bank_br) {
		this.bank_br = bank_br;
	}
	public String getAcc_number() {
		return acc_number;
	}
	public void setAcc_number(String acc_number) {
		this.acc_number = acc_number;
	}
	public String getAcc_name() {
		return acc_name;
	}
	public void setAcc_name(String acc_name) {
		this.acc_name = acc_name;
	}
	public String getBank_addr() {
		return bank_addr;
	}
	public void setBank_addr(String bank_addr) {
		this.bank_addr = bank_addr;
	}
	public String getAcct_type() {
		return acct_type;
	}
	public void setAcct_type(String acct_type) {
		this.acct_type = acct_type;
	}
	public String getAcct_crncy() {
		return acct_crncy;
	}
	public void setAcct_crncy(String acct_crncy) {
		this.acct_crncy = acct_crncy;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getIfsc_code() {
		return ifsc_code;
	}
	public void setIfsc_code(String ifsc_code) {
		this.ifsc_code = ifsc_code;
	}
	public String getSwift_code() {
		return swift_code;
	}
	public void setSwift_code(String swift_code) {
		this.swift_code = swift_code;
	}
	public String getPayment_code() {
		return payment_code;
	}
	public void setPayment_code(String payment_code) {
		this.payment_code = payment_code;
	}
	public String getPayment_mode() {
		return payment_mode;
	}
	public void setPayment_mode(String payment_mode) {
		this.payment_mode = payment_mode;
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
	public String getModify_flg() {
		return modify_flg;
	}
	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
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
	public String getVerify_user() {
		return verify_user;
	}
	public void setVerify_user(String verify_user) {
		this.verify_user = verify_user;
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
	public Date getVerify_time() {
		return verify_time;
	}
	public void setVerify_time(Date verify_time) {
		this.verify_time = verify_time;
	}
	
	
	
	
	public BankMaster(String bank_srl_no, String bank_name, String bank_br, String acc_number, String acc_name,
			String bank_addr, String acct_type, String acct_crncy, String addr, String ifsc_code, String swift_code,
			String payment_code, String payment_mode, String del_flg, String entity_flg, String modify_flg,
			String entry_user, String modify_user, String verify_user, Date entry_time, Date modify_time,
			Date verify_time) {
		super();
		this.bank_srl_no = bank_srl_no;
		this.bank_name = bank_name;
		this.bank_br = bank_br;
		this.acc_number = acc_number;
		this.acc_name = acc_name;
		this.bank_addr = bank_addr;
		this.acct_type = acct_type;
		this.acct_crncy = acct_crncy;
		this.addr = addr;
		this.ifsc_code = ifsc_code;
		this.swift_code = swift_code;
		this.payment_code = payment_code;
		this.payment_mode = payment_mode;
		this.del_flg = del_flg;
		this.entity_flg = entity_flg;
		this.modify_flg = modify_flg;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.verify_user = verify_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.verify_time = verify_time;
	}
	public BankMaster() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	 
	

}
