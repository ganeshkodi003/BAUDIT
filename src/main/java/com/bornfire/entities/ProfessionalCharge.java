package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "CLIENT_PROF_CHGS")
public class ProfessionalCharge {

	@Id
	private BigDecimal srl_ref_no;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date lodge_date;
	private String client_id;
	private String client_name;
	private String gstin_no;
	private String pancard_no;
	private String invoice_no;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date invoice_date;
	private String description;
	private String resource_desc;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date month_date;
	private String period_desc;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date start_date;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date end_date;
	private String no_of_days;
	private BigDecimal efforts_units;
	private BigDecimal rate;
	private BigDecimal amount;
	private BigDecimal cgst;
	private BigDecimal sgst;
	private BigDecimal igst;
	private BigDecimal total_gst;
	private BigDecimal total_invoice_amount;
	private BigDecimal tds;
	private String rmks;
	private String bornfire_inv_no;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date payment_date;
	private BigDecimal payment_ayment_amount;
	private String bank_name;
	private String bank_remarks;
	private String reversal_flag;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date reversal_date;
	private String entity_flg;
	private String del_flg;
	private String modify_flg;
	private String entry_user;
	private String modify_user;
	private String verify_user;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date entry_date;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date modify_date;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date verify_date;

	public BigDecimal getSrl_ref_no() {
		return srl_ref_no;
	}

	public void setSrl_ref_no(BigDecimal srl_ref_no) {
		this.srl_ref_no = srl_ref_no;
	}

	public Date getLodge_date() {
		return lodge_date;
	}

	public void setLodge_date(Date lodge_date) {
		this.lodge_date = lodge_date;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getGstin_no() {
		return gstin_no;
	}

	public void setGstin_no(String gstin_no) {
		this.gstin_no = gstin_no;
	}

	public String getPancard_no() {
		return pancard_no;
	}

	public void setPancard_no(String pancard_no) {
		this.pancard_no = pancard_no;
	}

	public String getInvoice_no() {
		return invoice_no;
	}

	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}

	public Date getInvoice_date() {
		return invoice_date;
	}

	public void setInvoice_date(Date invoice_date) {
		this.invoice_date = invoice_date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getResource_desc() {
		return resource_desc;
	}

	public void setResource_desc(String resource_desc) {
		this.resource_desc = resource_desc;
	}

	public Date getMonth_date() {
		return month_date;
	}

	public void setMonth_date(Date month_date) {
		this.month_date = month_date;
	}

	public String getPeriod_desc() {
		return period_desc;
	}

	public void setPeriod_desc(String period_desc) {
		this.period_desc = period_desc;
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

	public String getNo_of_days() {
		return no_of_days;
	}

	public void setNo_of_days(String no_of_days) {
		this.no_of_days = no_of_days;
	}

	public BigDecimal getEfforts_units() {
		return efforts_units;
	}

	public void setEfforts_units(BigDecimal efforts_units) {
		this.efforts_units = efforts_units;
	}

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getCgst() {
		return cgst;
	}

	public void setCgst(BigDecimal cgst) {
		this.cgst = cgst;
	}

	public BigDecimal getSgst() {
		return sgst;
	}

	public void setSgst(BigDecimal sgst) {
		this.sgst = sgst;
	}

	public BigDecimal getIgst() {
		return igst;
	}

	public void setIgst(BigDecimal igst) {
		this.igst = igst;
	}

	public BigDecimal getTotal_gst() {
		return total_gst;
	}

	public void setTotal_gst(BigDecimal total_gst) {
		this.total_gst = total_gst;
	}

	public BigDecimal getTotal_invoice_amount() {
		return total_invoice_amount;
	}

	public void setTotal_invoice_amount(BigDecimal total_invoice_amount) {
		this.total_invoice_amount = total_invoice_amount;
	}

	public BigDecimal getTds() {
		return tds;
	}

	public void setTds(BigDecimal tds) {
		this.tds = tds;
	}

	public String getRmks() {
		return rmks;
	}

	public void setRmks(String rmks) {
		this.rmks = rmks;
	}

	public String getBornfire_inv_no() {
		return bornfire_inv_no;
	}

	public void setBornfire_inv_no(String bornfire_inv_no) {
		this.bornfire_inv_no = bornfire_inv_no;
	}

	public Date getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
	}

	public BigDecimal getPayment_ayment_amount() {
		return payment_ayment_amount;
	}

	public void setPayment_ayment_amount(BigDecimal payment_ayment_amount) {
		this.payment_ayment_amount = payment_ayment_amount;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getBank_remarks() {
		return bank_remarks;
	}

	public void setBank_remarks(String bank_remarks) {
		this.bank_remarks = bank_remarks;
	}

	public String getReversal_flag() {
		return reversal_flag;
	}

	public void setReversal_flag(String reversal_flag) {
		this.reversal_flag = reversal_flag;
	}

	public Date getReversal_date() {
		return reversal_date;
	}

	public void setReversal_date(Date reversal_date) {
		this.reversal_date = reversal_date;
	}

	public String getEntity_flg() {
		return entity_flg;
	}

	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}

	public String getDel_flg() {
		return del_flg;
	}

	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
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

	public Date getEntry_date() {
		return entry_date;
	}

	public void setEntry_date(Date entry_date) {
		this.entry_date = entry_date;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

	public Date getVerify_date() {
		return verify_date;
	}

	public void setVerify_date(Date verify_date) {
		this.verify_date = verify_date;
	}

	public ProfessionalCharge(BigDecimal srl_ref_no, Date lodge_date, String client_id, String client_name,
			String gstin_no, String pancard_no, String invoice_no, Date invoice_date, String description,
			String resource_desc, Date month_date, String period_desc, Date start_date, Date end_date,
			String no_of_days, BigDecimal efforts_units, BigDecimal rate, BigDecimal amount, BigDecimal cgst,
			BigDecimal sgst, BigDecimal igst, BigDecimal total_gst, BigDecimal total_invoice_amount, BigDecimal tds,
			String rmks, String bornfire_inv_no, Date payment_date, BigDecimal payment_ayment_amount, String bank_name,
			String bank_remarks, String reversal_flag, Date reversal_date, String entity_flg, String del_flg,
			String modify_flg, String entry_user, String modify_user, String verify_user, Date entry_date,
			Date modify_date, Date verify_date) {
		super();
		this.srl_ref_no = srl_ref_no;
		this.lodge_date = lodge_date;
		this.client_id = client_id;
		this.client_name = client_name;
		this.gstin_no = gstin_no;
		this.pancard_no = pancard_no;
		this.invoice_no = invoice_no;
		this.invoice_date = invoice_date;
		this.description = description;
		this.resource_desc = resource_desc;
		this.month_date = month_date;
		this.period_desc = period_desc;
		this.start_date = start_date;
		this.end_date = end_date;
		this.no_of_days = no_of_days;
		this.efforts_units = efforts_units;
		this.rate = rate;
		this.amount = amount;
		this.cgst = cgst;
		this.sgst = sgst;
		this.igst = igst;
		this.total_gst = total_gst;
		this.total_invoice_amount = total_invoice_amount;
		this.tds = tds;
		this.rmks = rmks;
		this.bornfire_inv_no = bornfire_inv_no;
		this.payment_date = payment_date;
		this.payment_ayment_amount = payment_ayment_amount;
		this.bank_name = bank_name;
		this.bank_remarks = bank_remarks;
		this.reversal_flag = reversal_flag;
		this.reversal_date = reversal_date;
		this.entity_flg = entity_flg;
		this.del_flg = del_flg;
		this.modify_flg = modify_flg;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.verify_user = verify_user;
		this.entry_date = entry_date;
		this.modify_date = modify_date;
		this.verify_date = verify_date;
	}

	public ProfessionalCharge() {
		super();
		// TODO Auto-generated constructor stub
	}

}
