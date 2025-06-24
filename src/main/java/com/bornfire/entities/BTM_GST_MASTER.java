package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BTM_GST_MASTER") 
public class BTM_GST_MASTER {
	
	    private String gst_type;

	    private String fin_year;

	    @DateTimeFormat(pattern = "dd-MM-yyyy")

	    private Date tran_date;
	    @Id
	    private String tran_id;

	    private String part_tran_id;

	    private String part_tran_type;

	    private String client;

	    private String gstin;

	    private String invoice_no;

	    @DateTimeFormat(pattern = "dd-MM-yyyy")

	    private Date invoice_date;

	    private String inv_desc;

	    private BigDecimal inv_amt;

	    private BigDecimal eligible_amt;

	    private BigDecimal inv_cgst;

	    private BigDecimal inv_sgst;

	    private BigDecimal inv_igst;

	    private BigDecimal total_gst_amt;

	    private BigDecimal inv_tot_amt;

	    @DateTimeFormat(pattern = "dd-MM-yyyy")

	    private Date payment_date;

	    @DateTimeFormat(pattern = "dd-MM-yyyy")

	    private Date pay_tran_date;

	    private String pay_tran_id;

	    private String pay_part_tran_id;

	    private String pay_part_tran_type;

	    private String entry_user;

	    private String modify_user;

	    private String post_user;

	    @DateTimeFormat(pattern = "dd-MM-yyyy")

	    private Date entry_time;

	    @DateTimeFormat(pattern = "dd-MM-yyyy")

	    private Date modify_time;

	    @DateTimeFormat(pattern = "dd-MM-yyyy")

	    private Date post_time;

	    private String tran_status;

	    private char entity_flg;

	    private char del_flg;

	    private String client_type;

	    private String client_remark;

	    private String pay_status;

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

		public String getPay_tran_id() {
			return pay_tran_id;
		}

		public void setPay_tran_id(String pay_tran_id) {
			this.pay_tran_id = pay_tran_id;
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

		public String getPost_user() {
			return post_user;
		}

		public void setPost_user(String post_user) {
			this.post_user = post_user;
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

		public Date getPost_time() {
			return post_time;
		}

		public void setPost_time(Date post_time) {
			this.post_time = post_time;
		}

		public String getTran_status() {
			return tran_status;
		}

		public void setTran_status(String tran_status) {
			this.tran_status = tran_status;
		}

		public char getEntity_flg() {
			return entity_flg;
		}

		public void setEntity_flg(char entity_flg) {
			this.entity_flg = entity_flg;
		}

		public char getDel_flg() {
			return del_flg;
		}

		public void setDel_flg(char del_flg) {
			this.del_flg = del_flg;
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

		public String getPay_status() {
			return pay_status;
		}

		public void setPay_status(String pay_status) {
			this.pay_status = pay_status;
		}

		@Override
		public String toString() {
			return "BTM_GST_MASTER [gst_type=" + gst_type + ", fin_year=" + fin_year + ", tran_date=" + tran_date
					+ ", tran_id=" + tran_id + ", part_tran_id=" + part_tran_id + ", part_tran_type=" + part_tran_type
					+ ", client=" + client + ", gstin=" + gstin + ", invoice_no=" + invoice_no + ", invoice_date="
					+ invoice_date + ", inv_desc=" + inv_desc + ", inv_amt=" + inv_amt + ", eligible_amt="
					+ eligible_amt + ", inv_cgst=" + inv_cgst + ", inv_sgst=" + inv_sgst + ", inv_igst=" + inv_igst
					+ ", total_gst_amt=" + total_gst_amt + ", inv_tot_amt=" + inv_tot_amt + ", payment_date="
					+ payment_date + ", pay_tran_date=" + pay_tran_date + ", pay_tran_id=" + pay_tran_id
					+ ", pay_part_tran_id=" + pay_part_tran_id + ", pay_part_tran_type=" + pay_part_tran_type
					+ ", entry_user=" + entry_user + ", modify_user=" + modify_user + ", post_user=" + post_user
					+ ", entry_time=" + entry_time + ", modify_time=" + modify_time + ", post_time=" + post_time
					+ ", tran_status=" + tran_status + ", entity_flg=" + entity_flg + ", del_flg=" + del_flg
					+ ", client_type=" + client_type + ", client_remark=" + client_remark + ", pay_status=" + pay_status
					+ ", getGst_type()=" + getGst_type() + ", getFin_year()=" + getFin_year() + ", getTran_date()="
					+ getTran_date() + ", getTran_id()=" + getTran_id() + ", getPart_tran_id()=" + getPart_tran_id()
					+ ", getPart_tran_type()=" + getPart_tran_type() + ", getClient()=" + getClient() + ", getGstin()="
					+ getGstin() + ", getInvoice_no()=" + getInvoice_no() + ", getInvoice_date()=" + getInvoice_date()
					+ ", getInv_desc()=" + getInv_desc() + ", getInv_amt()=" + getInv_amt() + ", getEligible_amt()="
					+ getEligible_amt() + ", getInv_cgst()=" + getInv_cgst() + ", getInv_sgst()=" + getInv_sgst()
					+ ", getInv_igst()=" + getInv_igst() + ", getTotal_gst_amt()=" + getTotal_gst_amt()
					+ ", getInv_tot_amt()=" + getInv_tot_amt() + ", getPayment_date()=" + getPayment_date()
					+ ", getPay_tran_date()=" + getPay_tran_date() + ", getPay_tran_id()=" + getPay_tran_id()
					+ ", getPay_part_tran_id()=" + getPay_part_tran_id() + ", getPay_part_tran_type()="
					+ getPay_part_tran_type() + ", getEntry_user()=" + getEntry_user() + ", getModify_user()="
					+ getModify_user() + ", getPost_user()=" + getPost_user() + ", getEntry_time()=" + getEntry_time()
					+ ", getModify_time()=" + getModify_time() + ", getPost_time()=" + getPost_time()
					+ ", getTran_status()=" + getTran_status() + ", getEntity_flg()=" + getEntity_flg()
					+ ", getDel_flg()=" + getDel_flg() + ", getClient_type()=" + getClient_type()
					+ ", getClient_remark()=" + getClient_remark() + ", getPay_status()=" + getPay_status()
					+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
					+ "]";
		}

		public BTM_GST_MASTER(String gst_type, String fin_year, Date tran_date, String tran_id, String part_tran_id,
				String part_tran_type, String client, String gstin, String invoice_no, Date invoice_date,
				String inv_desc, BigDecimal inv_amt, BigDecimal eligible_amt, BigDecimal inv_cgst, BigDecimal inv_sgst,
				BigDecimal inv_igst, BigDecimal total_gst_amt, BigDecimal inv_tot_amt, Date payment_date,
				Date pay_tran_date, String pay_tran_id, String pay_part_tran_id, String pay_part_tran_type,
				String entry_user, String modify_user, String post_user, Date entry_time, Date modify_time,
				Date post_time, String tran_status, char entity_flg, char del_flg, String client_type,
				String client_remark, String pay_status) {
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
			this.pay_tran_id = pay_tran_id;
			this.pay_part_tran_id = pay_part_tran_id;
			this.pay_part_tran_type = pay_part_tran_type;
			this.entry_user = entry_user;
			this.modify_user = modify_user;
			this.post_user = post_user;
			this.entry_time = entry_time;
			this.modify_time = modify_time;
			this.post_time = post_time;
			this.tran_status = tran_status;
			this.entity_flg = entity_flg;
			this.del_flg = del_flg;
			this.client_type = client_type;
			this.client_remark = client_remark;
			this.pay_status = pay_status;
		}

		public BTM_GST_MASTER() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
}
