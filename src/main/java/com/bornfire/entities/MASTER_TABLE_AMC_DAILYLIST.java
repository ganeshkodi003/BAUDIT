package com.bornfire.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "MASTER_TABLE_AMC_DAILYLIST")
public class MASTER_TABLE_AMC_DAILYLIST {

	@Id
	private String resource_name;	
	private String service_shift;
	private String log_in_time;
	private String log_out_time;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date user_login_expiry_date;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date ssl_certificate_expiry_date;
	private String uniqueid;
	private String project;
	private String del_flg;
	private String entry_flg;
	private String master_flg;
	public String getResource_name() {
		return resource_name;
	}
	public void setResource_name(String resource_name) {
		this.resource_name = resource_name;
	}
	public String getService_shift() {
		return service_shift;
	}
	public void setService_shift(String service_shift) {
		this.service_shift = service_shift;
	}
	public String getLog_in_time() {
		return log_in_time;
	}
	public void setLog_in_time(String log_in_time) {
		this.log_in_time = log_in_time;
	}
	public String getLog_out_time() {
		return log_out_time;
	}
	public void setLog_out_time(String log_out_time) {
		this.log_out_time = log_out_time;
	}
	public Date getUser_login_expiry_date() {
		return user_login_expiry_date;
	}
	public void setUser_login_expiry_date(Date user_login_expiry_date) {
		this.user_login_expiry_date = user_login_expiry_date;
	}
	public Date getSsl_certificate_expiry_date() {
		return ssl_certificate_expiry_date;
	}
	public void setSsl_certificate_expiry_date(Date ssl_certificate_expiry_date) {
		this.ssl_certificate_expiry_date = ssl_certificate_expiry_date;
	}
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public String getEntry_flg() {
		return entry_flg;
	}
	public void setEntry_flg(String entry_flg) {
		this.entry_flg = entry_flg;
	}
	public String getMaster_flg() {
		return master_flg;
	}
	public void setMaster_flg(String master_flg) {
		this.master_flg = master_flg;
	}
	@Override
	public String toString() {
		return "MASTER_TABLE_AMC_DAILYLIST [resource_name=" + resource_name + ", service_shift=" + service_shift
				+ ", log_in_time=" + log_in_time + ", log_out_time=" + log_out_time + ", user_login_expiry_date="
				+ user_login_expiry_date + ", ssl_certificate_expiry_date=" + ssl_certificate_expiry_date
				+ ", uniqueid=" + uniqueid + ", project=" + project + ", del_flg=" + del_flg + ", entry_flg="
				+ entry_flg + ", master_flg=" + master_flg + ", getResource_name()=" + getResource_name()
				+ ", getService_shift()=" + getService_shift() + ", getLog_in_time()=" + getLog_in_time()
				+ ", getLog_out_time()=" + getLog_out_time() + ", getUser_login_expiry_date()="
				+ getUser_login_expiry_date() + ", getSsl_certificate_expiry_date()=" + getSsl_certificate_expiry_date()
				+ ", getUniqueid()=" + getUniqueid() + ", getProject()=" + getProject() + ", getDel_flg()="
				+ getDel_flg() + ", getEntry_flg()=" + getEntry_flg() + ", getMaster_flg()=" + getMaster_flg()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	public MASTER_TABLE_AMC_DAILYLIST() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MASTER_TABLE_AMC_DAILYLIST(String resource_name, String service_shift, String log_in_time,
			String log_out_time, Date user_login_expiry_date, Date ssl_certificate_expiry_date, String uniqueid,
			String project, String del_flg, String entry_flg, String master_flg) {
		super();
		this.resource_name = resource_name;
		this.service_shift = service_shift;
		this.log_in_time = log_in_time;
		this.log_out_time = log_out_time;
		this.user_login_expiry_date = user_login_expiry_date;
		this.ssl_certificate_expiry_date = ssl_certificate_expiry_date;
		this.uniqueid = uniqueid;
		this.project = project;
		this.del_flg = del_flg;
		this.entry_flg = entry_flg;
		this.master_flg = master_flg;
	}
	
	
}
