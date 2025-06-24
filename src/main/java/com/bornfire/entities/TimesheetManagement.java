package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

//import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "TIMESHEET_MASTER")
public class TimesheetManagement {
	@Id
	private String	placement_id;
	private String	emp_name;
	private String	po_ref_no;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	po_date;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	start_date;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	end_date;
	private String	placement_co;
	private String	billing_co;
	private String	project;
	private String	client;
	private BigDecimal	rate;
	private String	location;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	month;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	grn_date;
	private String	grn_no;
	private BigDecimal	efforts;
	private BigDecimal	value;
	private String	remarks;
	
	
	
	private String	grn_srl_no;
	
	private String	entity_flg;
	private String	del_flg;
	private String	modify_flg;
	private String	entry_user;
	private String	modify_user;
	private String	verify_user;
	private Date	entry_time;
	private Date	modify_time;
	private Date	verify_time;
	public String getPlacement_id() {
		return placement_id;
	}
	public void setPlacement_id(String placement_id) {
		this.placement_id = placement_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getPo_ref_no() {
		return po_ref_no;
	}
	public void setPo_ref_no(String po_ref_no) {
		this.po_ref_no = po_ref_no;
	}
	public Date getPo_date() {
		return po_date;
	}
	public void setPo_date(Date po_date) {
		this.po_date = po_date;
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
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public String getPlacement_co() {
		return placement_co;
	}
	public void setPlacement_co(String placement_co) {
		this.placement_co = placement_co;
	}
	public String getBilling_co() {
		return billing_co;
	}
	public void setBilling_co(String billing_co) {
		this.billing_co = billing_co;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getGrn_srl_no() {
		return grn_srl_no;
	}
	public void setGrn_srl_no(String grn_srl_no) {
		this.grn_srl_no = grn_srl_no;
	}
	public Date getMonth() {
		return month;
	}
	public void setMonth(Date month) {
		this.month = month;
	}
	public String getGrn_no() {
		return grn_no;
	}
	public void setGrn_no(String grn_no) {
		this.grn_no = grn_no;
	}
	public Date getGrn_date() {
		return grn_date;
	}
	public void setGrn_date(Date grn_date) {
		this.grn_date = grn_date;
	}
	public BigDecimal getEfforts() {
		return efforts;
	}
	public void setEfforts(BigDecimal efforts) {
		this.efforts = efforts;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public TimesheetManagement(String placement_id, String emp_name, String po_ref_no, Date po_date, Date start_date,
			Date end_date, BigDecimal rate, String placement_co, String billing_co, String project, String client,
			String location, String grn_srl_no, Date month, String grn_no, Date grn_date, BigDecimal efforts,
			BigDecimal value, String remarks, String entity_flg, String del_flg, String modify_flg, String entry_user,
			String modify_user, String verify_user, Date entry_time, Date modify_time, Date verify_time) {
		super();
		this.placement_id = placement_id;
		this.emp_name = emp_name;
		this.po_ref_no = po_ref_no;
		this.po_date = po_date;
		this.start_date = start_date;
		this.end_date = end_date;
		this.rate = rate;
		this.placement_co = placement_co;
		this.billing_co = billing_co;
		this.project = project;
		this.client = client;
		this.location = location;
		this.grn_srl_no = grn_srl_no;
		this.month = month;
		this.grn_no = grn_no;
		this.grn_date = grn_date;
		this.efforts = efforts;
		this.value = value;
		this.remarks = remarks;
		this.entity_flg = entity_flg;
		this.del_flg = del_flg;
		this.modify_flg = modify_flg;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.verify_user = verify_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.verify_time = verify_time;
	}
	public TimesheetManagement() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
