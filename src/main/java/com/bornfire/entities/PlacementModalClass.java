package com.bornfire.entities;

import java.util.Date;

import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

public class PlacementModalClass {

	private String	vendor;
	private String	location;
	private String	po_no;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	po_date;
	private String	extn_flg;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	extn_date;
	private String	proj_mgr;
	private String	pm_email;
	private String	unit_loc;
	private String	gstin;
	private String	emp_name;
	private String	emp_id;
	private String	no_of_items;
	private String	total_value;
	private String	po_item_no;
	private String	po_qty;
	private String	po_rate_inr;
	private String	po_amt_inr;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	po_delivery_date;
	private String	po_month;
	private String	grn_no;
	private String	grn_date;
	private String	grn_efforts;
	private String	grn_amt;
	private String	check_flg;
	private String	inv_no;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	inv_date;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	inv_due_date;
	private String	inv_amt;
	private String	inv_sgst;
	private String	inv_cgst;
	private String	inv_igst;
	private String	inv_tot_gst;
	private String	inv_tot_amt;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	dp_date;
	private String	dp_no;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	remit_date;
	private String	remit_amt;
	private String	tds_rec;
	private String	credit_note;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	cn_date;
	private String	sp_rate;
	private String	rate_mode;
	private String	fixed_amt;
	private String	percent;
	private String	sp_inv_no;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	sp_inv_date;
	private String	sp_inv_amt;
	private String	sp_inv_sgst;
	private String	sp_inv_cgst;
	private String	sp_inv_igst;
	private String	sp_inv_tot_gst;
	private String	sp_inv_tot_amt;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	sp_paymt_date;
	private String	sp_paymt_amt;
	private String	sp_tds;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	sp_tds_remit_date;
	private String	sp_tds_ref_no;
	private String	del_flg;
	private String	entity_flg;
	private String	modify_flg;
	private String	entry_user;
	private String	modify_user;
	private Date	entry_time;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	modify_time;
	private Date	verify_time;
	private Date	upload_time;
	private Date	expiary_date;
	private Date	extended_exipiry_date;
	private String	status;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	resubmit_date;
	private String	new_inv_no;
	private String	re_sumbit_flg;
	private String	bank_name;
	private String	sp;
	private String	ack_flag;
	private String	upload_flg;
	private String emp_no;
	private String emp_ref_no;
	private String bill_total_amt;
	private String revised_invoice;
	private Date status_date;
	private String cancel_flg;
	private Date cancel_date;
	private String cancel_po_amt;
	private String cancel_remarks;
	private String auth_user;
	private String srl_no;
	private Character flag;
	private String message;
	@DateTimeFormat(pattern = "dd-MM-yyyy")	
	private Date auth_time;
	private String upload_date;
	@Id
	private String po_id;
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
	public Date getPo_date() {
		return po_date;
	}
	public void setPo_date(Date po_date) {
		this.po_date = po_date;
	}
	public String getExtn_flg() {
		return extn_flg;
	}
	public void setExtn_flg(String extn_flg) {
		this.extn_flg = extn_flg;
	}
	public Date getExtn_date() {
		return extn_date;
	}
	public void setExtn_date(Date extn_date) {
		this.extn_date = extn_date;
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
	public String getUnit_loc() {
		return unit_loc;
	}
	public void setUnit_loc(String unit_loc) {
		this.unit_loc = unit_loc;
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
	public String getNo_of_items() {
		return no_of_items;
	}
	public void setNo_of_items(String no_of_items) {
		this.no_of_items = no_of_items;
	}
	public String getTotal_value() {
		return total_value;
	}
	public void setTotal_value(String total_value) {
		this.total_value = total_value;
	}
	public String getPo_item_no() {
		return po_item_no;
	}
	public void setPo_item_no(String po_item_no) {
		this.po_item_no = po_item_no;
	}
	public String getPo_qty() {
		return po_qty;
	}
	public void setPo_qty(String po_qty) {
		this.po_qty = po_qty;
	}
	public String getPo_rate_inr() {
		return po_rate_inr;
	}
	public void setPo_rate_inr(String po_rate_inr) {
		this.po_rate_inr = po_rate_inr;
	}
	public String getPo_amt_inr() {
		return po_amt_inr;
	}
	public void setPo_amt_inr(String po_amt_inr) {
		this.po_amt_inr = po_amt_inr;
	}
	public Date getPo_delivery_date() {
		return po_delivery_date;
	}
	public void setPo_delivery_date(Date po_delivery_date) {
		this.po_delivery_date = po_delivery_date;
	}
	public String getPo_month() {
		return po_month;
	}
	public void setPo_month(String po_month) {
		this.po_month = po_month;
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
	public String getCheck_flg() {
		return check_flg;
	}
	public void setCheck_flg(String check_flg) {
		this.check_flg = check_flg;
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
	public Date getInv_due_date() {
		return inv_due_date;
	}
	public void setInv_due_date(Date inv_due_date) {
		this.inv_due_date = inv_due_date;
	}
	public String getInv_amt() {
		return inv_amt;
	}
	public void setInv_amt(String inv_amt) {
		this.inv_amt = inv_amt;
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
	public Date getDp_date() {
		return dp_date;
	}
	public void setDp_date(Date dp_date) {
		this.dp_date = dp_date;
	}
	public String getDp_no() {
		return dp_no;
	}
	public void setDp_no(String dp_no) {
		this.dp_no = dp_no;
	}
	public Date getRemit_date() {
		return remit_date;
	}
	public void setRemit_date(Date remit_date) {
		this.remit_date = remit_date;
	}
	public String getRemit_amt() {
		return remit_amt;
	}
	public void setRemit_amt(String remit_amt) {
		this.remit_amt = remit_amt;
	}
	public String getTds_rec() {
		return tds_rec;
	}
	public void setTds_rec(String tds_rec) {
		this.tds_rec = tds_rec;
	}
	public String getCredit_note() {
		return credit_note;
	}
	public void setCredit_note(String credit_note) {
		this.credit_note = credit_note;
	}
	public Date getCn_date() {
		return cn_date;
	}
	public void setCn_date(Date cn_date) {
		this.cn_date = cn_date;
	}
	public String getSp_rate() {
		return sp_rate;
	}
	public void setSp_rate(String sp_rate) {
		this.sp_rate = sp_rate;
	}
	public String getRate_mode() {
		return rate_mode;
	}
	public void setRate_mode(String rate_mode) {
		this.rate_mode = rate_mode;
	}
	public String getFixed_amt() {
		return fixed_amt;
	}
	public void setFixed_amt(String fixed_amt) {
		this.fixed_amt = fixed_amt;
	}
	public String getPercent() {
		return percent;
	}
	public void setPercent(String percent) {
		this.percent = percent;
	}
	public String getSp_inv_no() {
		return sp_inv_no;
	}
	public void setSp_inv_no(String sp_inv_no) {
		this.sp_inv_no = sp_inv_no;
	}
	public Date getSp_inv_date() {
		return sp_inv_date;
	}
	public void setSp_inv_date(Date sp_inv_date) {
		this.sp_inv_date = sp_inv_date;
	}
	public String getSp_inv_amt() {
		return sp_inv_amt;
	}
	public void setSp_inv_amt(String sp_inv_amt) {
		this.sp_inv_amt = sp_inv_amt;
	}
	public String getSp_inv_sgst() {
		return sp_inv_sgst;
	}
	public void setSp_inv_sgst(String sp_inv_sgst) {
		this.sp_inv_sgst = sp_inv_sgst;
	}
	public String getSp_inv_cgst() {
		return sp_inv_cgst;
	}
	public void setSp_inv_cgst(String sp_inv_cgst) {
		this.sp_inv_cgst = sp_inv_cgst;
	}
	public String getSp_inv_igst() {
		return sp_inv_igst;
	}
	public void setSp_inv_igst(String sp_inv_igst) {
		this.sp_inv_igst = sp_inv_igst;
	}
	public String getSp_inv_tot_gst() {
		return sp_inv_tot_gst;
	}
	public void setSp_inv_tot_gst(String sp_inv_tot_gst) {
		this.sp_inv_tot_gst = sp_inv_tot_gst;
	}
	public String getSp_inv_tot_amt() {
		return sp_inv_tot_amt;
	}
	public void setSp_inv_tot_amt(String sp_inv_tot_amt) {
		this.sp_inv_tot_amt = sp_inv_tot_amt;
	}
	public Date getSp_paymt_date() {
		return sp_paymt_date;
	}
	public void setSp_paymt_date(Date sp_paymt_date) {
		this.sp_paymt_date = sp_paymt_date;
	}
	public String getSp_paymt_amt() {
		return sp_paymt_amt;
	}
	public void setSp_paymt_amt(String sp_paymt_amt) {
		this.sp_paymt_amt = sp_paymt_amt;
	}
	public String getSp_tds() {
		return sp_tds;
	}
	public void setSp_tds(String sp_tds) {
		this.sp_tds = sp_tds;
	}
	public Date getSp_tds_remit_date() {
		return sp_tds_remit_date;
	}
	public void setSp_tds_remit_date(Date sp_tds_remit_date) {
		this.sp_tds_remit_date = sp_tds_remit_date;
	}
	public String getSp_tds_ref_no() {
		return sp_tds_ref_no;
	}
	public void setSp_tds_ref_no(String sp_tds_ref_no) {
		this.sp_tds_ref_no = sp_tds_ref_no;
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
	public Date getUpload_time() {
		return upload_time;
	}
	public void setUpload_time(Date upload_time) {
		this.upload_time = upload_time;
	}
	public Date getExpiary_date() {
		return expiary_date;
	}
	public void setExpiary_date(Date expiary_date) {
		this.expiary_date = expiary_date;
	}
	public Date getExtended_exipiry_date() {
		return extended_exipiry_date;
	}
	public void setExtended_exipiry_date(Date extended_exipiry_date) {
		this.extended_exipiry_date = extended_exipiry_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getResubmit_date() {
		return resubmit_date;
	}
	public void setResubmit_date(Date resubmit_date) {
		this.resubmit_date = resubmit_date;
	}
	public String getNew_inv_no() {
		return new_inv_no;
	}
	public void setNew_inv_no(String new_inv_no) {
		this.new_inv_no = new_inv_no;
	}
	public String getRe_sumbit_flg() {
		return re_sumbit_flg;
	}
	public void setRe_sumbit_flg(String re_sumbit_flg) {
		this.re_sumbit_flg = re_sumbit_flg;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getSp() {
		return sp;
	}
	public void setSp(String sp) {
		this.sp = sp;
	}
	public String getAck_flag() {
		return ack_flag;
	}
	public void setAck_flag(String ack_flag) {
		this.ack_flag = ack_flag;
	}
	public String getUpload_flg() {
		return upload_flg;
	}
	public void setUpload_flg(String upload_flg) {
		this.upload_flg = upload_flg;
	}
	public String getEmp_no() {
		return emp_no;
	}
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
	}
	public String getEmp_ref_no() {
		return emp_ref_no;
	}
	public void setEmp_ref_no(String emp_ref_no) {
		this.emp_ref_no = emp_ref_no;
	}
	public String getBill_total_amt() {
		return bill_total_amt;
	}
	public void setBill_total_amt(String bill_total_amt) {
		this.bill_total_amt = bill_total_amt;
	}
	public String getRevised_invoice() {
		return revised_invoice;
	}
	public void setRevised_invoice(String revised_invoice) {
		this.revised_invoice = revised_invoice;
	}
	public Date getStatus_date() {
		return status_date;
	}
	public void setStatus_date(Date status_date) {
		this.status_date = status_date;
	}
	public String getCancel_flg() {
		return cancel_flg;
	}
	public void setCancel_flg(String cancel_flg) {
		this.cancel_flg = cancel_flg;
	}
	public Date getCancel_date() {
		return cancel_date;
	}
	public void setCancel_date(Date cancel_date) {
		this.cancel_date = cancel_date;
	}
	public String getCancel_po_amt() {
		return cancel_po_amt;
	}
	public void setCancel_po_amt(String cancel_po_amt) {
		this.cancel_po_amt = cancel_po_amt;
	}
	public String getCancel_remarks() {
		return cancel_remarks;
	}
	public void setCancel_remarks(String cancel_remarks) {
		this.cancel_remarks = cancel_remarks;
	}
	public String getAuth_user() {
		return auth_user;
	}
	public void setAuth_user(String auth_user) {
		this.auth_user = auth_user;
	}
	public String getSrl_no() {
		return srl_no;
	}
	public void setSrl_no(String srl_no) {
		this.srl_no = srl_no;
	}
	public Character getFlag() {
		return flag;
	}
	public void setFlag(Character flag) {
		this.flag = flag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getAuth_time() {
		return auth_time;
	}
	public void setAuth_time(Date auth_time) {
		this.auth_time = auth_time;
	}
	public String getUpload_date() {
		return upload_date;
	}
	public void setUpload_date(String upload_date) {
		this.upload_date = upload_date;
	}
	public String getPo_id() {
		return po_id;
	}
	public void setPo_id(String po_id) {
		this.po_id = po_id;
	}
	public PlacementModalClass(String vendor, String location, String po_no, Date po_date, String extn_flg,
			Date extn_date, String proj_mgr, String pm_email, String unit_loc, String gstin, String emp_name,
			String emp_id, String no_of_items, String total_value, String po_item_no, String po_qty, String po_rate_inr,
			String po_amt_inr, Date po_delivery_date, String po_month, String grn_no, String grn_date,
			String grn_efforts, String grn_amt, String check_flg, String inv_no, Date inv_date, Date inv_due_date,
			String inv_amt, String inv_sgst, String inv_cgst, String inv_igst, String inv_tot_gst, String inv_tot_amt,
			Date dp_date, String dp_no, Date remit_date, String remit_amt, String tds_rec, String credit_note,
			Date cn_date, String sp_rate, String rate_mode, String fixed_amt, String percent, String sp_inv_no,
			Date sp_inv_date, String sp_inv_amt, String sp_inv_sgst, String sp_inv_cgst, String sp_inv_igst,
			String sp_inv_tot_gst, String sp_inv_tot_amt, Date sp_paymt_date, String sp_paymt_amt, String sp_tds,
			Date sp_tds_remit_date, String sp_tds_ref_no, String del_flg, String entity_flg, String modify_flg,
			String entry_user, String modify_user, Date entry_time, Date modify_time, Date verify_time,
			Date upload_time, Date expiary_date, Date extended_exipiry_date, String status, Date resubmit_date,
			String new_inv_no, String re_sumbit_flg, String bank_name, String sp, String ack_flag, String upload_flg,
			String emp_no, String emp_ref_no, String bill_total_amt, String revised_invoice, Date status_date,
			String cancel_flg, Date cancel_date, String cancel_po_amt, String cancel_remarks, String auth_user,
			String srl_no, Character flag, String message, Date auth_time, String upload_date, String po_id) {
		super();
		this.vendor = vendor;
		this.location = location;
		this.po_no = po_no;
		this.po_date = po_date;
		this.extn_flg = extn_flg;
		this.extn_date = extn_date;
		this.proj_mgr = proj_mgr;
		this.pm_email = pm_email;
		this.unit_loc = unit_loc;
		this.gstin = gstin;
		this.emp_name = emp_name;
		this.emp_id = emp_id;
		this.no_of_items = no_of_items;
		this.total_value = total_value;
		this.po_item_no = po_item_no;
		this.po_qty = po_qty;
		this.po_rate_inr = po_rate_inr;
		this.po_amt_inr = po_amt_inr;
		this.po_delivery_date = po_delivery_date;
		this.po_month = po_month;
		this.grn_no = grn_no;
		this.grn_date = grn_date;
		this.grn_efforts = grn_efforts;
		this.grn_amt = grn_amt;
		this.check_flg = check_flg;
		this.inv_no = inv_no;
		this.inv_date = inv_date;
		this.inv_due_date = inv_due_date;
		this.inv_amt = inv_amt;
		this.inv_sgst = inv_sgst;
		this.inv_cgst = inv_cgst;
		this.inv_igst = inv_igst;
		this.inv_tot_gst = inv_tot_gst;
		this.inv_tot_amt = inv_tot_amt;
		this.dp_date = dp_date;
		this.dp_no = dp_no;
		this.remit_date = remit_date;
		this.remit_amt = remit_amt;
		this.tds_rec = tds_rec;
		this.credit_note = credit_note;
		this.cn_date = cn_date;
		this.sp_rate = sp_rate;
		this.rate_mode = rate_mode;
		this.fixed_amt = fixed_amt;
		this.percent = percent;
		this.sp_inv_no = sp_inv_no;
		this.sp_inv_date = sp_inv_date;
		this.sp_inv_amt = sp_inv_amt;
		this.sp_inv_sgst = sp_inv_sgst;
		this.sp_inv_cgst = sp_inv_cgst;
		this.sp_inv_igst = sp_inv_igst;
		this.sp_inv_tot_gst = sp_inv_tot_gst;
		this.sp_inv_tot_amt = sp_inv_tot_amt;
		this.sp_paymt_date = sp_paymt_date;
		this.sp_paymt_amt = sp_paymt_amt;
		this.sp_tds = sp_tds;
		this.sp_tds_remit_date = sp_tds_remit_date;
		this.sp_tds_ref_no = sp_tds_ref_no;
		this.del_flg = del_flg;
		this.entity_flg = entity_flg;
		this.modify_flg = modify_flg;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.verify_time = verify_time;
		this.upload_time = upload_time;
		this.expiary_date = expiary_date;
		this.extended_exipiry_date = extended_exipiry_date;
		this.status = status;
		this.resubmit_date = resubmit_date;
		this.new_inv_no = new_inv_no;
		this.re_sumbit_flg = re_sumbit_flg;
		this.bank_name = bank_name;
		this.sp = sp;
		this.ack_flag = ack_flag;
		this.upload_flg = upload_flg;
		this.emp_no = emp_no;
		this.emp_ref_no = emp_ref_no;
		this.bill_total_amt = bill_total_amt;
		this.revised_invoice = revised_invoice;
		this.status_date = status_date;
		this.cancel_flg = cancel_flg;
		this.cancel_date = cancel_date;
		this.cancel_po_amt = cancel_po_amt;
		this.cancel_remarks = cancel_remarks;
		this.auth_user = auth_user;
		this.srl_no = srl_no;
		this.flag = flag;
		this.message = message;
		this.auth_time = auth_time;
		this.upload_date = upload_date;
		this.po_id = po_id;
	}
	public PlacementModalClass() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
