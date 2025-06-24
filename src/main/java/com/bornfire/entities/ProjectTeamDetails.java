package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="PROJ_TEAM")

public class ProjectTeamDetails {
	
	@Id
	private String	proj_id;
	private String	client_id;
	private BigDecimal	team_srl;
	private String	ass_id;
	private String	ass_name;
	private String	ass_desig;
	private String	ass_role;
	//@DateTimeFormat(pattern = "dd-MMM-yy")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	start_date;
	//@DateTimeFormat(pattern = "dd-MMM-yy")
@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	end_date;
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
	private String	location;
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
	public BigDecimal getTeam_srl() {
		return team_srl;
	}
	public void setTeam_srl(BigDecimal team_srl) {
		this.team_srl = team_srl;
	}
	public String getAss_id() {
		return ass_id;
	}
	public void setAss_id(String ass_id) {
		this.ass_id = ass_id;
	}
	public String getAss_name() {
		return ass_name;
	}
	public void setAss_name(String ass_name) {
		this.ass_name = ass_name;
	}
	public String getAss_desig() {
		return ass_desig;
	}
	public void setAss_desig(String ass_desig) {
		this.ass_desig = ass_desig;
	}
	public String getAss_role() {
		return ass_role;
	}
	public void setAss_role(String ass_role) {
		this.ass_role = ass_role;
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public ProjectTeamDetails(String proj_id, String client_id, BigDecimal team_srl, String ass_id, String ass_name,
			String ass_desig, String ass_role, Date start_date, Date end_date, String remarks, String entry_user,
			String modify_user, String auth_user, Date entry_time, Date modify_time, Date auth_time, String del_flg,
			String entity_flg, String modify_flg, String location) {
		super();
		this.proj_id = proj_id;
		this.client_id = client_id;
		this.team_srl = team_srl;
		this.ass_id = ass_id;
		this.ass_name = ass_name;
		this.ass_desig = ass_desig;
		this.ass_role = ass_role;
		this.start_date = start_date;
		this.end_date = end_date;
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
		this.location = location;
	}
	public ProjectTeamDetails() {
		super();
		// TODO Auto-generated constructor stub
	}


	

}
