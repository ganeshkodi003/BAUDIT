package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "TROUBLE_SHOOT")
public class BTMTroubleShoot_Entity {


	
	private String project_name;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date current_date;
	
	
	private String issue_no;
	private String raised_by;
	private String description;
	private String category;
	private String priority;
	private String assignee;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date open_date;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date close_date;
	private String status;
	private String comments;
	private String remarks;
	
	private String  entry_flag;
	private String modify_flag;
	private String verify_flag;
    private String del_flag;
	private String  entry_time;
	private String modify_time;
	private String verify_time;
	private String  entry_user;
	private String modify_user;
	private String verify_user;
	@Id
	private String uniqueid;
	@Lob
	private byte[]	blob_file;
	public String filename;
	
		public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public Date getCurrent_date() {
		return current_date;
	}

	public void setCurrent_date(Date current_date) {
		this.current_date = current_date;
	}

	public String getIssue_no() {
		return issue_no;
	}

	public void setIssue_no(String issue_no) {
		this.issue_no = issue_no;
	}

	public String getRaised_by() {
		return raised_by;
	}

	public void setRaised_by(String raised_by) {
		this.raised_by = raised_by;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public Date getOpen_date() {
		return open_date;
	}

	public void setOpen_date(Date open_date) {
		this.open_date = open_date;
	}

	public Date getClose_date() {
		return close_date;
	}

	public void setClose_date(Date close_date) {
		this.close_date = close_date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getEntry_flag() {
		return entry_flag;
	}

	public void setEntry_flag(String entry_flag) {
		this.entry_flag = entry_flag;
	}

	public String getModify_flag() {
		return modify_flag;
	}

	public void setModify_flag(String modify_flag) {
		this.modify_flag = modify_flag;
	}

	public String getVerify_flag() {
		return verify_flag;
	}

	public void setVerify_flag(String verify_flag) {
		this.verify_flag = verify_flag;
	}

	public String getDel_flag() {
		return del_flag;
	}

	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}

	public String getEntry_time() {
		return entry_time;
	}

	public void setEntry_time(String entry_time) {
		this.entry_time = entry_time;
	}

	public String getModify_time() {
		return modify_time;
	}

	public void setModify_time(String modify_time) {
		this.modify_time = modify_time;
	}

	public String getVerify_time() {
		return verify_time;
	}

	public void setVerify_time(String verify_time) {
		this.verify_time = verify_time;
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

	public String getUniqueid() {
		return uniqueid;
	}

	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}

	public byte[] getBlob_file() {
		return blob_file;
	}

	public void setBlob_file(byte[] blob_file) {
		this.blob_file = blob_file;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

		public BTMTroubleShoot_Entity(String project_name, Date current_date, String issue_no, String raised_by,
			String description, String category, String priority, String assignee, Date open_date, Date close_date,
			String status, String comments, String remarks, String entry_flag, String modify_flag, String verify_flag,
			String del_flag, String entry_time, String modify_time, String verify_time, String entry_user,
			String modify_user, String verify_user, String uniqueid, byte[] blob_file, String filename) {
		super();
		this.project_name = project_name;
		this.current_date = current_date;
		this.issue_no = issue_no;
		this.raised_by = raised_by;
		this.description = description;
		this.category = category;
		this.priority = priority;
		this.assignee = assignee;
		this.open_date = open_date;
		this.close_date = close_date;
		this.status = status;
		this.comments = comments;
		this.remarks = remarks;
		this.entry_flag = entry_flag;
		this.modify_flag = modify_flag;
		this.verify_flag = verify_flag;
		this.del_flag = del_flag;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.verify_time = verify_time;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.verify_user = verify_user;
		this.uniqueid = uniqueid;
		this.blob_file = blob_file;
		this.filename = filename;
	}

		@Override
		public String toString() {
			return "BTMTroubleShoot_Entity [project_name=" + project_name + ", current_date=" + current_date
					+ ", issue_no=" + issue_no + ", raised_by=" + raised_by + ", description=" + description
					+ ", category=" + category + ", priority=" + priority + ", assignee=" + assignee + ", open_date="
					+ open_date + ", close_date=" + close_date + ", status=" + status + ", comments=" + comments
					+ ", remarks=" + remarks + ", entry_flag=" + entry_flag + ", modify_flag=" + modify_flag
					+ ", verify_flag=" + verify_flag + ", del_flag=" + del_flag + ", entry_time=" + entry_time
					+ ", modify_time=" + modify_time + ", verify_time=" + verify_time + ", entry_user=" + entry_user
					+ ", modify_user=" + modify_user + ", verify_user=" + verify_user + ", uniqueid=" + uniqueid
					+ ", blob_file=" + Arrays.toString(blob_file) + ", filename=" + filename + "]";
		}

		public BTMTroubleShoot_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

	
}