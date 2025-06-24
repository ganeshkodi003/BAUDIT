package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="GST_BTM")
public class GstBtmEntity {

	private String	gst_type;
	private String	fin_year;
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date	tran_date;
	
	private String	tran_id;
	private String	part_tran_id;
	private String	part_tran_type;
	private String	client;
	private String	gstin;
	private String	invoice_no;
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date	invoice_date;
	private String	inv_desc;
	private BigDecimal	inv_amt;
	private BigDecimal	eligible_amt;
	private BigDecimal	inv_cgst;
	private BigDecimal	inv_sgst;
	private BigDecimal	inv_igst;
	private BigDecimal	total_gst_amt;
	private BigDecimal	inv_tot_amt;
	private Date	payment_date;
	private Date	pay_tran_date;
	private String	rpay_tran_id;
	private String	pay_part_tran_id;
	private String	pay_part_tran_type;
	private String	client_type;
	private String	client_remark;
	@Id
	private String	uniqueid;
	public String getGst_type() {
		return gst_type;
	}
	public void setGst_type(String gst_type) {
		this.gst_type = gst_type;
	}
	public String getFin_year() {
		return fin_year;
	}
	public void setFin_year(String fin_year) {
		this.fin_year = fin_year;
	}
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
	public String getPart_tran_id() {
		return part_tran_id;
	}
	public void setPart_tran_id(String part_tran_id) {
		this.part_tran_id = part_tran_id;
	}
	public String getPart_tran_type() {
		return part_tran_type;
	}
	public void setPart_tran_type(String part_tran_type) {
		this.part_tran_type = part_tran_type;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getGstin() {
		return gstin;
	}
	public void setGstin(String gstin) {
		this.gstin = gstin;
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
	public String getInv_desc() {
		return inv_desc;
	}
	public void setInv_desc(String inv_desc) {
		this.inv_desc = inv_desc;
	}
	public BigDecimal getInv_amt() {
		return inv_amt;
	}
	public void setInv_amt(BigDecimal inv_amt) {
		this.inv_amt = inv_amt;
	}
	public BigDecimal getEligible_amt() {
		return eligible_amt;
	}
	public void setEligible_amt(BigDecimal eligible_amt) {
		this.eligible_amt = eligible_amt;
	}
	public BigDecimal getInv_cgst() {
		return inv_cgst;
	}
	public void setInv_cgst(BigDecimal inv_cgst) {
		this.inv_cgst = inv_cgst;
	}
	public BigDecimal getInv_sgst() {
		return inv_sgst;
	}
	public void setInv_sgst(BigDecimal inv_sgst) {
		this.inv_sgst = inv_sgst;
	}
	public BigDecimal getInv_igst() {
		return inv_igst;
	}
	public void setInv_igst(BigDecimal inv_igst) {
		this.inv_igst = inv_igst;
	}
	public BigDecimal getTotal_gst_amt() {
		return total_gst_amt;
	}
	public void setTotal_gst_amt(BigDecimal total_gst_amt) {
		this.total_gst_amt = total_gst_amt;
	}
	public BigDecimal getInv_tot_amt() {
		return inv_tot_amt;
	}
	public void setInv_tot_amt(BigDecimal inv_tot_amt) {
		this.inv_tot_amt = inv_tot_amt;
	}
	public Date getPayment_date() {
		return payment_date;
	}
	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
	}
	public Date getPay_tran_date() {
		return pay_tran_date;
	}
	public void setPay_tran_date(Date pay_tran_date) {
		this.pay_tran_date = pay_tran_date;
	}
	public String getRpay_tran_id() {
		return rpay_tran_id;
	}
	public void setRpay_tran_id(String rpay_tran_id) {
		this.rpay_tran_id = rpay_tran_id;
	}
	public String getPay_part_tran_id() {
		return pay_part_tran_id;
	}
	public void setPay_part_tran_id(String pay_part_tran_id) {
		this.pay_part_tran_id = pay_part_tran_id;
	}
	public String getPay_part_tran_type() {
		return pay_part_tran_type;
	}
	public void setPay_part_tran_type(String pay_part_tran_type) {
		this.pay_part_tran_type = pay_part_tran_type;
	}
	public String getClient_type() {
		return client_type;
	}
	public void setClient_type(String client_type) {
		this.client_type = client_type;
	}
	public String getClient_remark() {
		return client_remark;
	}
	public void setClient_remark(String client_remark) {
		this.client_remark = client_remark;
	}
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	@Override
	public String toString() {
		return "GstBtmEntity [gst_type=" + gst_type + ", fin_year=" + fin_year + ", tran_date=" + tran_date
				+ ", tran_id=" + tran_id + ", part_tran_id=" + part_tran_id + ", part_tran_type=" + part_tran_type
				+ ", client=" + client + ", gstin=" + gstin + ", invoice_no=" + invoice_no + ", invoice_date="
				+ invoice_date + ", inv_desc=" + inv_desc + ", inv_amt=" + inv_amt + ", eligible_amt=" + eligible_amt
				+ ", inv_cgst=" + inv_cgst + ", inv_sgst=" + inv_sgst + ", inv_igst=" + inv_igst + ", total_gst_amt="
				+ total_gst_amt + ", inv_tot_amt=" + inv_tot_amt + ", payment_date=" + payment_date + ", pay_tran_date="
				+ pay_tran_date + ", rpay_tran_id=" + rpay_tran_id + ", pay_part_tran_id=" + pay_part_tran_id
				+ ", pay_part_tran_type=" + pay_part_tran_type + ", client_type=" + client_type + ", client_remark="
				+ client_remark + ", uniqueid=" + uniqueid + "]";
	}
	public GstBtmEntity(String gst_type, String fin_year, Date tran_date, String tran_id, String part_tran_id,
			String part_tran_type, String client, String gstin, String invoice_no, Date invoice_date, String inv_desc,
			BigDecimal inv_amt, BigDecimal eligible_amt, BigDecimal inv_cgst, BigDecimal inv_sgst, BigDecimal inv_igst,
			BigDecimal total_gst_amt, BigDecimal inv_tot_amt, Date payment_date, Date pay_tran_date,
			String rpay_tran_id, String pay_part_tran_id, String pay_part_tran_type, String client_type,
			String client_remark, String uniqueid) {
		super();
		this.gst_type = gst_type;
		this.fin_year = fin_year;
		this.tran_date = tran_date;
		this.tran_id = tran_id;
		this.part_tran_id = part_tran_id;
		this.part_tran_type = part_tran_type;
		this.client = client;
		this.gstin = gstin;
		this.invoice_no = invoice_no;
		this.invoice_date = invoice_date;
		this.inv_desc = inv_desc;
		this.inv_amt = inv_amt;
		this.eligible_amt = eligible_amt;
		this.inv_cgst = inv_cgst;
		this.inv_sgst = inv_sgst;
		this.inv_igst = inv_igst;
		this.total_gst_amt = total_gst_amt;
		this.inv_tot_amt = inv_tot_amt;
		this.payment_date = payment_date;
		this.pay_tran_date = pay_tran_date;
		this.rpay_tran_id = rpay_tran_id;
		this.pay_part_tran_id = pay_part_tran_id;
		this.pay_part_tran_type = pay_part_tran_type;
		this.client_type = client_type;
		this.client_remark = client_remark;
		this.uniqueid = uniqueid;
	}
	public GstBtmEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}

