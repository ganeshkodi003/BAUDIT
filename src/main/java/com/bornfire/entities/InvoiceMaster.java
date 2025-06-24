package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "LOC_INVOICE_MASTER")
public class InvoiceMaster {
	
	@Id
	private String po_id;
	
	private String	inv_no;
	private String	vendor;
	private String	location;
	private String	po_no;
	private String	proj_mgr;
	private String	pm_email;
	private String	gstin;
	private String	emp_name;
	private String	emp_id;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	po_delivery_date;
	private String	grn_no;
	private String	grn_date;
	private String	grn_efforts;
	private String	grn_amt;
	
	private String	inv_amt;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	inv_date;
	private String	inv_sgst;
	private String	inv_cgst;
	private String	inv_igst;
	private String	inv_tot_gst;
	private String	inv_tot_amt;
	private String	po_rate_inr;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	inv_due_date;
	private String cin;
	private String pan;
	private String gstin_1;
	private String vendor1;
	private String hsn;
	private String bank_name;
	private String acct_type;
	private String acct_no;
	private String acct_name;
	private String ifsc_code;
	private String swift_code;
	private String bill_to;
	private String deliver_to;
	private String status;
	private String total_amt;
	private String enclosures;
	private String tel_fax;
	public String getTel_fax() {
		return tel_fax;
	}
	public void setTel_fax(String tel_fax) {
		this.tel_fax = tel_fax;
	}
	public String getEnclosures() {
		return enclosures;
	}
	public void setEnclosures(String enclosures) {
		this.enclosures = enclosures;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTotal_amt() {
		return total_amt;
	}
	public void setTotal_amt(String total_amt) {
		this.total_amt = total_amt;
	}
	public String getBill_to() {
		return bill_to;
	}
	public void setBill_to(String bill_to) {
		this.bill_to = bill_to;
	}
	public String getDeliver_to() {
		return deliver_to;
	}
	public void setDeliver_to(String deliver_to) {
		this.deliver_to = deliver_to;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getGstin_1() {
		return gstin_1;
	}
	public void setGstin_1(String gstin_1) {
		this.gstin_1 = gstin_1;
	}
	public String getVendor1() {
		return vendor1;
	}
	public void setVendor1(String vendor1) {
		this.vendor1 = vendor1;
	}
	public String getHsn() {
		return hsn;
	}
	public void setHsn(String hsn) {
		this.hsn = hsn;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getAcct_type() {
		return acct_type;
	}
	public void setAcct_type(String acct_type) {
		this.acct_type = acct_type;
	}
	public String getAcct_no() {
		return acct_no;
	}
	public void setAcct_no(String acct_no) {
		this.acct_no = acct_no;
	}
	public String getAcct_name() {
		return acct_name;
	}
	public void setAcct_name(String acct_name) {
		this.acct_name = acct_name;
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
	public String getPo_id() {
		return po_id;
	}
	public void setPo_id(String po_id) {
		this.po_id = po_id;
	}
	public String getInv_no() {
		return inv_no;
	}
	public void setInv_no(String inv_no) {
		this.inv_no = inv_no;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPo_no() {
		return po_no;
	}
	public void setPo_no(String po_no) {
		this.po_no = po_no;
	}
	public String getProj_mgr() {
		return proj_mgr;
	}
	public void setProj_mgr(String proj_mgr) {
		this.proj_mgr = proj_mgr;
	}
	public String getPm_email() {
		return pm_email;
	}
	public void setPm_email(String pm_email) {
		this.pm_email = pm_email;
	}
	public String getGstin() {
		return gstin;
	}
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public Date getPo_delivery_date() {
		return po_delivery_date;
	}
	public void setPo_delivery_date(Date po_delivery_date) {
		this.po_delivery_date = po_delivery_date;
	}
	public String getGrn_no() {
		return grn_no;
	}
	public void setGrn_no(String grn_no) {
		this.grn_no = grn_no;
	}
	public String getGrn_date() {
		return grn_date;
	}
	public void setGrn_date(String grn_date) {
		this.grn_date = grn_date;
	}
	public String getGrn_efforts() {
		return grn_efforts;
	}
	public void setGrn_efforts(String grn_efforts) {
		this.grn_efforts = grn_efforts;
	}
	public String getGrn_amt() {
		return grn_amt;
	}
	public void setGrn_amt(String grn_amt) {
		this.grn_amt = grn_amt;
	}
	public String getInv_amt() {
		return inv_amt;
	}
	public void setInv_amt(String inv_amt) {
		this.inv_amt = inv_amt;
	}
	public Date getInv_date() {
		return inv_date;
	}
	public void setInv_date(Date inv_date) {
		this.inv_date = inv_date;
	}
	public String getInv_sgst() {
		return inv_sgst;
	}
	public void setInv_sgst(String inv_sgst) {
		this.inv_sgst = inv_sgst;
	}
	public String getInv_cgst() {
		return inv_cgst;
	}
	public void setInv_cgst(String inv_cgst) {
		this.inv_cgst = inv_cgst;
	}
	public String getInv_igst() {
		return inv_igst;
	}
	public void setInv_igst(String inv_igst) {
		this.inv_igst = inv_igst;
	}
	public String getInv_tot_gst() {
		return inv_tot_gst;
	}
	public void setInv_tot_gst(String inv_tot_gst) {
		this.inv_tot_gst = inv_tot_gst;
	}
	public String getInv_tot_amt() {
		return inv_tot_amt;
	}
	public void setInv_tot_amt(String inv_tot_amt) {
		this.inv_tot_amt = inv_tot_amt;
	}
	public String getPo_rate_inr() {
		return po_rate_inr;
	}
	public void setPo_rate_inr(String po_rate_inr) {
		this.po_rate_inr = po_rate_inr;
	}
	public Date getInv_due_date() {
		return inv_due_date;
	}
	public void setInv_due_date(Date inv_due_date) {
		this.inv_due_date = inv_due_date;
	}
	public InvoiceMaster(String po_id, String inv_no, String vendor, String location, String po_no, String proj_mgr,
			String pm_email, String gstin, String emp_name, String emp_id, Date po_delivery_date, String grn_no,
			String grn_date, String grn_efforts, String grn_amt, String inv_amt, Date inv_date, String inv_sgst,
			String inv_cgst, String inv_igst, String inv_tot_gst, String inv_tot_amt, String po_rate_inr,
			Date inv_due_date, String cin, String pan, String gstin_1, String vendor1, String hsn, String bank_name,
			String acct_type, String acct_no, String acct_name, String ifsc_code, String swift_code,String status,String total_amt,String enclosures,String tel_fax) {
		super();
		this.po_id = po_id;
		this.inv_no = inv_no;
		this.vendor = vendor;
		this.location = location;
		this.po_no = po_no;
		this.proj_mgr = proj_mgr;
		this.pm_email = pm_email;
		this.gstin = gstin;
		this.emp_name = emp_name;
		this.emp_id = emp_id;
		this.po_delivery_date = po_delivery_date;
		this.grn_no = grn_no;
		this.grn_date = grn_date;
		this.grn_efforts = grn_efforts;
		this.grn_amt = grn_amt;
		this.inv_amt = inv_amt;
		this.inv_date = inv_date;
		this.inv_sgst = inv_sgst;
		this.inv_cgst = inv_cgst;
		this.inv_igst = inv_igst;
		this.inv_tot_gst = inv_tot_gst;
		this.inv_tot_amt = inv_tot_amt;
		this.po_rate_inr = po_rate_inr;
		this.inv_due_date = inv_due_date;
		this.cin = cin;
		this.pan = pan;
		this.gstin_1 = gstin_1;
		this.vendor1 = vendor1;
		this.hsn = hsn;
		this.bank_name = bank_name;
		this.acct_type = acct_type;
		this.acct_no = acct_no;
		this.acct_name = acct_name;
		this.ifsc_code = ifsc_code;
		this.swift_code = swift_code;
		this.status=status;
		this.total_amt=total_amt;
		this.enclosures=enclosures;
		this.tel_fax=tel_fax;
	}
	public InvoiceMaster() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "InvoiceMaster [po_id="+po_id+",inv_no=" + inv_no + ", vendor=" + vendor + ", location=" + location + ", po_no=" + po_no
				+ ", proj_mgr=" + proj_mgr + ", pm_email=" + pm_email + ", gstin=" + gstin + ", emp_name=" + emp_name
				+ ", emp_id=" + emp_id + ", po_delivery_date=" + po_delivery_date + ", grn_no=" + grn_no + ", grn_date="
				+ grn_date + ", grn_efforts=" + grn_efforts + ", grn_amt=" + grn_amt + ", inv_amt=" + inv_amt
				+ ", inv_date=" + inv_date + ", inv_sgst=" + inv_sgst + ", inv_cgst=" + inv_cgst + ", inv_igst="
				+ inv_igst + ", inv_tot_gst=" + inv_tot_gst + ", inv_tot_amt=" + inv_tot_amt + ", po_rate_inr="
				+ po_rate_inr + ", inv_due_date=" + inv_due_date + "]";
	}
	
	
	
	
	
	
	
}
