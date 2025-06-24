package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="PROJ_SCH")

public class ProjectDetails {
	
	
	@Id
	private String	proj_id;
	private String	client_id;
	private BigDecimal	sch_srl;
	private String	sch_phase;
	private String	sch_activity;
	//@DateTimeFormat(pattern = "dd-MMM-yy")
	//@DateTimeFormat(pattern = "dd-MM-yyyy")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	sch_start_date;
	//@DateTimeFormat(pattern = "dd-MMM-yy")
	//@DateTimeFormat(pattern = "dd-MM-yyyy")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	sch_end_date;
	private String	sch_status;
	private String	mile_stone_flg;
	private String	remarks;
	private String	entry_user;
	private String	modify_user;
	private String	auth_user;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	entry_time;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	modify_time;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	auth_time;
	private String	del_flg;
	private String	entity_flg;
	private String	modify_flg;
	
	
	public String getProj_id() {
		return proj_id;
	}
	public void setProj_id(String proj_id) {
		this.proj_id = proj_id;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public BigDecimal getSch_srl() {
		return sch_srl;
	}
	public void setSch_srl(BigDecimal sch_srl) {
		this.sch_srl = sch_srl;
	}
	public String getSch_phase() {
		return sch_phase;
	}
	public void setSch_phase(String sch_phase) {
		this.sch_phase = sch_phase;
	}
	public String getSch_activity() {
		return sch_activity;
	}
	public void setSch_activity(String sch_activity) {
		this.sch_activity = sch_activity;
	}
	public Date getSch_start_date() {
		return sch_start_date;
	}
	public void setSch_start_date(Date sch_start_date) {
		this.sch_start_date = sch_start_date;
	}
	public Date getSch_end_date() {
		return sch_end_date;
	}
	public void setSch_end_date(Date sch_end_date) {
		this.sch_end_date = sch_end_date;
	}
	public String getSch_status() {
		return sch_status;
	}
	public void setSch_status(String sch_status) {
		this.sch_status = sch_status;
	}
	public String getMile_stone_flg() {
		return mile_stone_flg;
	}
	public void setMile_stone_flg(String mile_stone_flg) {
		this.mile_stone_flg = mile_stone_flg;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	public ProjectDetails(String proj_id, String client_id, BigDecimal sch_srl, String sch_phase, String sch_activity,
			Date sch_start_date, Date sch_end_date, String sch_status, String mile_stone_flg, String remarks,
			String entry_user, String modify_user, String auth_user, Date entry_time, Date modify_time, Date auth_time,
			String del_flg, String entity_flg, String modify_flg) {
		super();
		this.proj_id = proj_id;
		this.client_id = client_id;
		this.sch_srl = sch_srl;
		this.sch_phase = sch_phase;
		this.sch_activity = sch_activity;
		this.sch_start_date = sch_start_date;
		this.sch_end_date = sch_end_date;
		this.sch_status = sch_status;
		this.mile_stone_flg = mile_stone_flg;
		this.remarks = remarks;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.auth_user = auth_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.auth_time = auth_time;
		this.del_flg = del_flg;
		this.entity_flg = entity_flg;
		this.modify_flg = modify_flg;
	}
	public ProjectDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	
	
}
