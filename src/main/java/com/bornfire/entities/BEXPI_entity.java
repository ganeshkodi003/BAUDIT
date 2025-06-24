package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BEXPI_VIEW")
public class BEXPI_entity {
	
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	tran_date;
	@Id
	private String	tran_id;
	private BigDecimal	part_tran_id;
	private String	part_tran_type;
	private String	tran_crncy;
	private String	inv_no;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	inv_date;
	private BigDecimal	inv_amt_fcy;
	private BigDecimal	rate;
	private BigDecimal	inv_amt_inr;
	private String	client;
	private String	description;
	private String	employee;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	start_date;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	end_date;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	payment_date;
	private BigDecimal	remit_amt_inr;
	private String	bank_account;
	private String	invoice_no;
	private BigDecimal	remit_amt_fcy;
	private BigDecimal	remit_rate;
	private BigDecimal	fbank_chg_fcy;
	private BigDecimal	ex_fluc;
	private BigDecimal	bank_charges;
	private String	del_flg;
	private String	entity_flg;
	private String	entry_user;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	entry_time;
	private String	modify_user;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	modify_time;
	private String	verify_user;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	verify_time;
	private String	modify_flg;
	public Date getTran_date() {
		return tran_date;
	}
	public void setTran_date(Date tran_date) {
		this.tran_date = tran_date;
	}
	public String getTran_id() {
		return tran_id;
	}
	public void setTran_id(String tran_id) {
		this.tran_id = tran_id;
	}
	public BigDecimal getPart_tran_id() {
		return part_tran_id;
	}
	public void setPart_tran_id(BigDecimal part_tran_id) {
		this.part_tran_id = part_tran_id;
	}
	public String getPart_tran_type() {
		return part_tran_type;
	}
	public void setPart_tran_type(String part_tran_type) {
		this.part_tran_type = part_tran_type;
	}
	public String getTran_crncy() {
		return tran_crncy;
	}
	public void setTran_crncy(String tran_crncy) {
		this.tran_crncy = tran_crncy;
	}
	public String getInv_no() {
		return inv_no;
	}
	public void setInv_no(String inv_no) {
		this.inv_no = inv_no;
	}
	public Date getInv_date() {
		return inv_date;
	}
	public void setInv_date(Date inv_date) {
		this.inv_date = inv_date;
	}
	public BigDecimal getInv_amt_fcy() {
		return inv_amt_fcy;
	}
	public void setInv_amt_fcy(BigDecimal inv_amt_fcy) {
		this.inv_amt_fcy = inv_amt_fcy;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public BigDecimal getInv_amt_inr() {
		return inv_amt_inr;
	}
	public void setInv_amt_inr(BigDecimal inv_amt_inr) {
		this.inv_amt_inr = inv_amt_inr;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public Date getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
	}
	public BigDecimal getRemit_amt_inr() {
		return remit_amt_inr;
	}
	public void setRemit_amt_inr(BigDecimal remit_amt_inr) {
		this.remit_amt_inr = remit_amt_inr;
	}
	public String getBank_account() {
		return bank_account;
	}
	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}
	public String getInvoice_no() {
		return invoice_no;
	}
	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}
	public BigDecimal getRemit_amt_fcy() {
		return remit_amt_fcy;
	}
	public void setRemit_amt_fcy(BigDecimal remit_amt_fcy) {
		this.remit_amt_fcy = remit_amt_fcy;
	}
	public BigDecimal getRemit_rate() {
		return remit_rate;
	}
	public void setRemit_rate(BigDecimal remit_rate) {
		this.remit_rate = remit_rate;
	}
	public BigDecimal getFbank_chg_fcy() {
		return fbank_chg_fcy;
	}
	public void setFbank_chg_fcy(BigDecimal fbank_chg_fcy) {
		this.fbank_chg_fcy = fbank_chg_fcy;
	}
	public BigDecimal getEx_fluc() {
		return ex_fluc;
	}
	public void setEx_fluc(BigDecimal ex_fluc) {
		this.ex_fluc = ex_fluc;
	}
	public BigDecimal getBank_charges() {
		return bank_charges;
	}
	public void setBank_charges(BigDecimal bank_charges) {
		this.bank_charges = bank_charges;
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
	public String getEntry_user() {
		return entry_user;
	}
	public void setEntry_user(String entry_user) {
		this.entry_user = entry_user;
	}
	public Date getEntry_time() {
		return entry_time;
	}
	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}
	public String getModify_user() {
		return modify_user;
	}
	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}
	public Date getModify_time() {
		return modify_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	public String getVerify_user() {
		return verify_user;
	}
	public void setVerify_user(String verify_user) {
		this.verify_user = verify_user;
	}
	public Date getVerify_time() {
		return verify_time;
	}
	public void setVerify_time(Date verify_time) {
		this.verify_time = verify_time;
	}
	public String getModify_flg() {
		return modify_flg;
	}
	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}
	@Override
	public String toString() {
		return "BEXPI_entity [tran_date=" + tran_date + ", tran_id=" + tran_id + ", part_tran_id=" + part_tran_id
				+ ", part_tran_type=" + part_tran_type + ", tran_crncy=" + tran_crncy + ", inv_no=" + inv_no
				+ ", inv_date=" + inv_date + ", inv_amt_fcy=" + inv_amt_fcy + ", rate=" + rate + ", inv_amt_inr="
				+ inv_amt_inr + ", client=" + client + ", description=" + description + ", employee=" + employee
				+ ", start_date=" + start_date + ", end_date=" + end_date + ", payment_date=" + payment_date
				+ ", remit_amt_inr=" + remit_amt_inr + ", bank_account=" + bank_account + ", invoice_no=" + invoice_no
				+ ", remit_amt_fcy=" + remit_amt_fcy + ", remit_rate=" + remit_rate + ", fbank_chg_fcy=" + fbank_chg_fcy
				+ ", ex_fluc=" + ex_fluc + ", bank_charges=" + bank_charges + ", del_flg=" + del_flg + ", entity_flg="
				+ entity_flg + ", entry_user=" + entry_user + ", entry_time=" + entry_time + ", modify_user="
				+ modify_user + ", modify_time=" + modify_time + ", verify_user=" + verify_user + ", verify_time="
				+ verify_time + ", modify_flg=" + modify_flg + "]";
	}
	public BEXPI_entity(Date tran_date, String tran_id, BigDecimal part_tran_id, String part_tran_type,
			String tran_crncy, String inv_no, Date inv_date, BigDecimal inv_amt_fcy, BigDecimal rate,
			BigDecimal inv_amt_inr, String client, String description, String employee, Date start_date, Date end_date,
			Date payment_date, BigDecimal remit_amt_inr, String bank_account, String invoice_no,
			BigDecimal remit_amt_fcy, BigDecimal remit_rate, BigDecimal fbank_chg_fcy, BigDecimal ex_fluc,
			BigDecimal bank_charges, String del_flg, String entity_flg, String entry_user, Date entry_time,
			String modify_user, Date modify_time, String verify_user, Date verify_time, String modify_flg) {
		super();
		this.tran_date = tran_date;
		this.tran_id = tran_id;
		this.part_tran_id = part_tran_id;
		this.part_tran_type = part_tran_type;
		this.tran_crncy = tran_crncy;
		this.inv_no = inv_no;
		this.inv_date = inv_date;
		this.inv_amt_fcy = inv_amt_fcy;
		this.rate = rate;
		this.inv_amt_inr = inv_amt_inr;
		this.client = client;
		this.description = description;
		this.employee = employee;
		this.start_date = start_date;
		this.end_date = end_date;
		this.payment_date = payment_date;
		this.remit_amt_inr = remit_amt_inr;
		this.bank_account = bank_account;
		this.invoice_no = invoice_no;
		this.remit_amt_fcy = remit_amt_fcy;
		this.remit_rate = remit_rate;
		this.fbank_chg_fcy = fbank_chg_fcy;
		this.ex_fluc = ex_fluc;
		this.bank_charges = bank_charges;
		this.del_flg = del_flg;
		this.entity_flg = entity_flg;
		this.entry_user = entry_user;
		this.entry_time = entry_time;
		this.modify_user = modify_user;
		this.modify_time = modify_time;
		this.verify_user = verify_user;
		this.verify_time = verify_time;
		this.modify_flg = modify_flg;
	}
	public BEXPI_entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}