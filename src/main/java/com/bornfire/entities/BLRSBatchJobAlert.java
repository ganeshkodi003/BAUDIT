package com.bornfire.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BATCH_JOB_MASTER")
public class BLRSBatchJobAlert implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String	job_id;
	private String	job_desc;
	private String	table_name;
	private String	main_job_name;
	@Id
	@Column(name = "job_name")
	private String	job_name;
	private String	step_name;
	private String	cron_schedule;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	last_run_date;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	next_run_date;
	private String	job_type;
	private String	job_no;
	private String	schm_code;
	private String	periodicity;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	start_date;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	end_date;
	private String	job_status;
	private String	start_time;
	private String	status;
	private String	rem_type;
	private String	del_flg;
	private String	email_id;
	private String	password;
	private String	header;
	private String	footer;
	private String	email_flg;
	private String	sms_flg;
	private String	entity_flg;
	private String	entry_user;
	private String	entry_time;
	private String	modify_user;
	private String	modify_time;
	private String	verify_user;
	private String	verify_time;
	private Date	lchg_time;
	private String	modify_flg;
	private String	department;
	public String getJob_id() {
		return job_id;
	}
	public void setJob_id(String job_id) {
		this.job_id = job_id;
	}
	public String getJob_desc() {
		return job_desc;
	}
	public void setJob_desc(String job_desc) {
		this.job_desc = job_desc;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getMain_job_name() {
		return main_job_name;
	}
	public void setMain_job_name(String main_job_name) {
		this.main_job_name = main_job_name;
	}
	public String getJobName() {
		return job_name;
	}
	public void setJobName(String job_name) {
		this.job_name = job_name;
	}
	public String getStep_name() {
		return step_name;
	}
	public void setStep_name(String step_name) {
		this.step_name = step_name;
	}
	public String getCron_schedule() {
		return cron_schedule;
	}
	public void setCron_schedule(String cron_schedule) {
		this.cron_schedule = cron_schedule;
	}
	public Date getLast_run_date() {
		return last_run_date;
	}
	public void setLast_run_date(Date last_run_date) {
		this.last_run_date = last_run_date;
	}
	public Date getNext_run_date() {
		return next_run_date;
	}
	public void setNext_run_date(Date next_run_date) {
		this.next_run_date = next_run_date;
	}
	public String getJob_type() {
		return job_type;
	}
	public void setJob_type(String job_type) {
		this.job_type = job_type;
	}
	public String getJob_no() {
		return job_no;
	}
	public void setJob_no(String job_no) {
		this.job_no = job_no;
	}
	public String getSchm_code() {
		return schm_code;
	}
	public void setSchm_code(String schm_code) {
		this.schm_code = schm_code;
	}
	public String getPeriodicity() {
		return periodicity;
	}
	public void setPeriodicity(String periodicity) {
		this.periodicity = periodicity;
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
	public String getJob_status() {
		return job_status;
	}
	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRem_type() {
		return rem_type;
	}
	public void setRem_type(String rem_type) {
		this.rem_type = rem_type;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getFooter() {
		return footer;
	}
	public void setFooter(String footer) {
		this.footer = footer;
	}
	public String getEmail_flg() {
		return email_flg;
	}
	public void setEmail_flg(String email_flg) {
		this.email_flg = email_flg;
	}
	public String getSms_flg() {
		return sms_flg;
	}
	public void setSms_flg(String sms_flg) {
		this.sms_flg = sms_flg;
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
	public String getEntry_time() {
		return entry_time;
	}
	public void setEntry_time(String entry_time) {
		this.entry_time = entry_time;
	}
	public String getModify_user() {
		return modify_user;
	}
	public void setModify_user(String modify_user) {
		this.modify_user = modify_user;
	}
	public String getModify_time() {
		return modify_time;
	}
	public void setModify_time(String modify_time) {
		this.modify_time = modify_time;
	}
	public String getVerify_user() {
		return verify_user;
	}
	public void setVerify_user(String verify_user) {
		this.verify_user = verify_user;
	}
	public String getVerify_time() {
		return verify_time;
	}
	public void setVerify_time(String verify_time) {
		this.verify_time = verify_time;
	}
	public Date getLchg_time() {
		return lchg_time;
	}
	public void setLchg_time(Date lchg_time) {
		this.lchg_time = lchg_time;
	}
	public String getModify_flg() {
		return modify_flg;
	}
	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public BLRSBatchJobAlert(String job_id, String job_desc, String table_name, String main_job_name, String job_name,
			String step_name, String cron_schedule, Date last_run_date, Date next_run_date, String job_type,
			String job_no, String schm_code, String periodicity, Date start_date, Date end_date, String job_status,
			String start_time, String status, String rem_type, String del_flg, String email_id, String password,
			String header, String footer, String email_flg, String sms_flg, String entity_flg, String entry_user,
			String entry_time, String modify_user, String modify_time, String verify_user, String verify_time,
			Date lchg_time, String modify_flg, String department) {
		super();
		this.job_id = job_id;
		this.job_desc = job_desc;
		this.table_name = table_name;
		this.main_job_name = main_job_name;
		this.job_name = job_name;
		this.step_name = step_name;
		this.cron_schedule = cron_schedule;
		this.last_run_date = last_run_date;
		this.next_run_date = next_run_date;
		this.job_type = job_type;
		this.job_no = job_no;
		this.schm_code = schm_code;
		this.periodicity = periodicity;
		this.start_date = start_date;
		this.end_date = end_date;
		this.job_status = job_status;
		this.start_time = start_time;
		this.status = status;
		this.rem_type = rem_type;
		this.del_flg = del_flg;
		this.email_id = email_id;
		this.password = password;
		this.header = header;
		this.footer = footer;
		this.email_flg = email_flg;
		this.sms_flg = sms_flg;
		this.entity_flg = entity_flg;
		this.entry_user = entry_user;
		this.entry_time = entry_time;
		this.modify_user = modify_user;
		this.modify_time = modify_time;
		this.verify_user = verify_user;
		this.verify_time = verify_time;
		this.lchg_time = lchg_time;
		this.modify_flg = modify_flg;
		this.department = department;
	}
	public BLRSBatchJobAlert() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
