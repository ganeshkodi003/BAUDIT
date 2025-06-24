package com.bornfire.entities;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "BTM_DAILY_CHECK_LIST")
public class BTM_Daily_Checklist_Entity {

private String resource_name;
private String service_time;
private String service_shift;
private String log_in_time;
private String log_out_time;
@DateTimeFormat(pattern = "dd-MM-yyyy")
private Date modify_time;
private String gateway_connectivity;
private String database_connectivity;
private String connect_24_connectivity;
private String db_memory_check;
private String db_memory_increase;
@DateTimeFormat(pattern = "dd-MM-yyyy")
private Date user_login_expiry_date;
@DateTimeFormat(pattern = "dd-MM-yyyy")
private Date ssl_certificate_expiry_date;
private String nps_transaction_summary_sheetword;
private String nps_transaction_summary_sheetexcel;
private String transaction_monitor_inward;
private String transaction_monitor_outward;
private String transaction_monitor_return;
private String failure_transaction_details;
private String hnps_channel_transaction_details;
private String mobile_and_internet_channel_transaction_details;
@Id
private String uniqueid;

private String service_date;
private String remarks;
private String project;
private String del_flag;

@Lob
private byte[] nps_transaction_summary_sheetword_file;
@Lob
private  byte[] nps_transaction_summary_sheetexcel_file;
@Lob
private  byte[] transaction_monitor_inward_file;
@Lob
private  byte[] transaction_monitor_outward_file;
@Lob
private  byte[] transaction_monitor_return_file;
@Lob
private  byte[] failure_transaction_details_file;
@Lob
private  byte[] hnps_channel_transaction_details_file;
@Lob
private  byte[] mobile_and_internet_channel_transaction_details_file;
public String getResource_name() {
	return resource_name;
}
public void setResource_name(String resource_name) {
	this.resource_name = resource_name;
}
public String getService_time() {
	return service_time;
}
public void setService_time(String service_time) {
	this.service_time = service_time;
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
public Date getModify_time() {
	return modify_time;
}
public void setModify_time(Date modify_time) {
	this.modify_time = modify_time;
}
public String getGateway_connectivity() {
	return gateway_connectivity;
}
public void setGateway_connectivity(String gateway_connectivity) {
	this.gateway_connectivity = gateway_connectivity;
}
public String getDatabase_connectivity() {
	return database_connectivity;
}
public void setDatabase_connectivity(String database_connectivity) {
	this.database_connectivity = database_connectivity;
}
public String getConnect_24_connectivity() {
	return connect_24_connectivity;
}
public void setConnect_24_connectivity(String connect_24_connectivity) {
	this.connect_24_connectivity = connect_24_connectivity;
}
public String getDb_memory_check() {
	return db_memory_check;
}
public void setDb_memory_check(String db_memory_check) {
	this.db_memory_check = db_memory_check;
}
public String getDb_memory_increase() {
	return db_memory_increase;
}
public void setDb_memory_increase(String db_memory_increase) {
	this.db_memory_increase = db_memory_increase;
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
public String getNps_transaction_summary_sheetword() {
	return nps_transaction_summary_sheetword;
}
public void setNps_transaction_summary_sheetword(String nps_transaction_summary_sheetword) {
	this.nps_transaction_summary_sheetword = nps_transaction_summary_sheetword;
}
public String getNps_transaction_summary_sheetexcel() {
	return nps_transaction_summary_sheetexcel;
}
public void setNps_transaction_summary_sheetexcel(String nps_transaction_summary_sheetexcel) {
	this.nps_transaction_summary_sheetexcel = nps_transaction_summary_sheetexcel;
}
public String getTransaction_monitor_inward() {
	return transaction_monitor_inward;
}
public void setTransaction_monitor_inward(String transaction_monitor_inward) {
	this.transaction_monitor_inward = transaction_monitor_inward;
}
public String getTransaction_monitor_outward() {
	return transaction_monitor_outward;
}
public void setTransaction_monitor_outward(String transaction_monitor_outward) {
	this.transaction_monitor_outward = transaction_monitor_outward;
}
public String getTransaction_monitor_return() {
	return transaction_monitor_return;
}
public void setTransaction_monitor_return(String transaction_monitor_return) {
	this.transaction_monitor_return = transaction_monitor_return;
}
public String getFailure_transaction_details() {
	return failure_transaction_details;
}
public void setFailure_transaction_details(String failure_transaction_details) {
	this.failure_transaction_details = failure_transaction_details;
}
public String getHnps_channel_transaction_details() {
	return hnps_channel_transaction_details;
}
public void setHnps_channel_transaction_details(String hnps_channel_transaction_details) {
	this.hnps_channel_transaction_details = hnps_channel_transaction_details;
}
public String getMobile_and_internet_channel_transaction_details() {
	return mobile_and_internet_channel_transaction_details;
}
public void setMobile_and_internet_channel_transaction_details(String mobile_and_internet_channel_transaction_details) {
	this.mobile_and_internet_channel_transaction_details = mobile_and_internet_channel_transaction_details;
}
public String getUniqueid() {
	return uniqueid;
}
public void setUniqueid(String uniqueid) {
	this.uniqueid = uniqueid;
}
public String getService_date() {
	return service_date;
}
public void setService_date(String service_date) {
	this.service_date = service_date;
}
public String getRemarks() {
	return remarks;
}
public void setRemarks(String remarks) {
	this.remarks = remarks;
}
public String getProject() {
	return project;
}
public void setProject(String project) {
	this.project = project;
}
public String getDel_flag() {
	return del_flag;
}
public void setDel_flag(String del_flag) {
	this.del_flag = del_flag;
}
public byte[] getNps_transaction_summary_sheetword_file() {
	return nps_transaction_summary_sheetword_file;
}
public void setNps_transaction_summary_sheetword_file(byte[] nps_transaction_summary_sheetword_file) {
	this.nps_transaction_summary_sheetword_file = nps_transaction_summary_sheetword_file;
}
public byte[] getNps_transaction_summary_sheetexcel_file() {
	return nps_transaction_summary_sheetexcel_file;
}
public void setNps_transaction_summary_sheetexcel_file(byte[] nps_transaction_summary_sheetexcel_file) {
	this.nps_transaction_summary_sheetexcel_file = nps_transaction_summary_sheetexcel_file;
}
public byte[] getTransaction_monitor_inward_file() {
	return transaction_monitor_inward_file;
}
public void setTransaction_monitor_inward_file(byte[] transaction_monitor_inward_file) {
	this.transaction_monitor_inward_file = transaction_monitor_inward_file;
}
public byte[] getTransaction_monitor_outward_file() {
	return transaction_monitor_outward_file;
}
public void setTransaction_monitor_outward_file(byte[] transaction_monitor_outward_file) {
	this.transaction_monitor_outward_file = transaction_monitor_outward_file;
}
public byte[] getTransaction_monitor_return_file() {
	return transaction_monitor_return_file;
}
public void setTransaction_monitor_return_file(byte[] transaction_monitor_return_file) {
	this.transaction_monitor_return_file = transaction_monitor_return_file;
}
public byte[] getFailure_transaction_details_file() {
	return failure_transaction_details_file;
}
public void setFailure_transaction_details_file(byte[] failure_transaction_details_file) {
	this.failure_transaction_details_file = failure_transaction_details_file;
}
public byte[] getHnps_channel_transaction_details_file() {
	return hnps_channel_transaction_details_file;
}
public void setHnps_channel_transaction_details_file(byte[] hnps_channel_transaction_details_file) {
	this.hnps_channel_transaction_details_file = hnps_channel_transaction_details_file;
}
public byte[] getMobile_and_internet_channel_transaction_details_file() {
	return mobile_and_internet_channel_transaction_details_file;
}
public void setMobile_and_internet_channel_transaction_details_file(
		byte[] mobile_and_internet_channel_transaction_details_file) {
	this.mobile_and_internet_channel_transaction_details_file = mobile_and_internet_channel_transaction_details_file;
}
@Override
public String toString() {
	return "BTM_Daily_Checklist_Entity [resource_name=" + resource_name + ", service_time=" + service_time
			+ ", service_shift=" + service_shift + ", log_in_time=" + log_in_time + ", log_out_time=" + log_out_time
			+ ", modify_time=" + modify_time + ", gateway_connectivity=" + gateway_connectivity
			+ ", database_connectivity=" + database_connectivity + ", connect_24_connectivity="
			+ connect_24_connectivity + ", db_memory_check=" + db_memory_check + ", db_memory_increase="
			+ db_memory_increase + ", user_login_expiry_date=" + user_login_expiry_date
			+ ", ssl_certificate_expiry_date=" + ssl_certificate_expiry_date + ", nps_transaction_summary_sheetword="
			+ nps_transaction_summary_sheetword + ", nps_transaction_summary_sheetexcel="
			+ nps_transaction_summary_sheetexcel + ", transaction_monitor_inward=" + transaction_monitor_inward
			+ ", transaction_monitor_outward=" + transaction_monitor_outward + ", transaction_monitor_return="
			+ transaction_monitor_return + ", failure_transaction_details=" + failure_transaction_details
			+ ", hnps_channel_transaction_details=" + hnps_channel_transaction_details
			+ ", mobile_and_internet_channel_transaction_details=" + mobile_and_internet_channel_transaction_details
			+ ", uniqueid=" + uniqueid + ", service_date=" + service_date + ", remarks=" + remarks + ", project="
			+ project + ", del_flag=" + del_flag + ", nps_transaction_summary_sheetword_file="
			+ Arrays.toString(nps_transaction_summary_sheetword_file) + ", nps_transaction_summary_sheetexcel_file="
			+ Arrays.toString(nps_transaction_summary_sheetexcel_file) + ", transaction_monitor_inward_file="
			+ Arrays.toString(transaction_monitor_inward_file) + ", transaction_monitor_outward_file="
			+ Arrays.toString(transaction_monitor_outward_file) + ", transaction_monitor_return_file="
			+ Arrays.toString(transaction_monitor_return_file) + ", failure_transaction_details_file="
			+ Arrays.toString(failure_transaction_details_file) + ", hnps_channel_transaction_details_file="
			+ Arrays.toString(hnps_channel_transaction_details_file)
			+ ", mobile_and_internet_channel_transaction_details_file="
			+ Arrays.toString(mobile_and_internet_channel_transaction_details_file) + ", getResource_name()="
			+ getResource_name() + ", getService_time()=" + getService_time() + ", getService_shift()="
			+ getService_shift() + ", getLog_in_time()=" + getLog_in_time() + ", getLog_out_time()=" + getLog_out_time()
			+ ", getModify_time()=" + getModify_time() + ", getGateway_connectivity()=" + getGateway_connectivity()
			+ ", getDatabase_connectivity()=" + getDatabase_connectivity() + ", getConnect_24_connectivity()="
			+ getConnect_24_connectivity() + ", getDb_memory_check()=" + getDb_memory_check()
			+ ", getDb_memory_increase()=" + getDb_memory_increase() + ", getUser_login_expiry_date()="
			+ getUser_login_expiry_date() + ", getSsl_certificate_expiry_date()=" + getSsl_certificate_expiry_date()
			+ ", getNps_transaction_summary_sheetword()=" + getNps_transaction_summary_sheetword()
			+ ", getNps_transaction_summary_sheetexcel()=" + getNps_transaction_summary_sheetexcel()
			+ ", getTransaction_monitor_inward()=" + getTransaction_monitor_inward()
			+ ", getTransaction_monitor_outward()=" + getTransaction_monitor_outward()
			+ ", getTransaction_monitor_return()=" + getTransaction_monitor_return()
			+ ", getFailure_transaction_details()=" + getFailure_transaction_details()
			+ ", getHnps_channel_transaction_details()=" + getHnps_channel_transaction_details()
			+ ", getMobile_and_internet_channel_transaction_details()="
			+ getMobile_and_internet_channel_transaction_details() + ", getUniqueid()=" + getUniqueid()
			+ ", getService_date()=" + getService_date() + ", getRemarks()=" + getRemarks() + ", getProject()="
			+ getProject() + ", getDel_flag()=" + getDel_flag() + ", getNps_transaction_summary_sheetword_file()="
			+ Arrays.toString(getNps_transaction_summary_sheetword_file())
			+ ", getNps_transaction_summary_sheetexcel_file()="
			+ Arrays.toString(getNps_transaction_summary_sheetexcel_file()) + ", getTransaction_monitor_inward_file()="
			+ Arrays.toString(getTransaction_monitor_inward_file()) + ", getTransaction_monitor_outward_file()="
			+ Arrays.toString(getTransaction_monitor_outward_file()) + ", getTransaction_monitor_return_file()="
			+ Arrays.toString(getTransaction_monitor_return_file()) + ", getFailure_transaction_details_file()="
			+ Arrays.toString(getFailure_transaction_details_file()) + ", getHnps_channel_transaction_details_file()="
			+ Arrays.toString(getHnps_channel_transaction_details_file())
			+ ", getMobile_and_internet_channel_transaction_details_file()="
			+ Arrays.toString(getMobile_and_internet_channel_transaction_details_file()) + ", getClass()=" + getClass()
			+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
}
public BTM_Daily_Checklist_Entity(String resource_name, String service_time, String service_shift, String log_in_time,
		String log_out_time, Date modify_time, String gateway_connectivity, String database_connectivity,
		String connect_24_connectivity, String db_memory_check, String db_memory_increase, Date user_login_expiry_date,
		Date ssl_certificate_expiry_date, String nps_transaction_summary_sheetword,
		String nps_transaction_summary_sheetexcel, String transaction_monitor_inward,
		String transaction_monitor_outward, String transaction_monitor_return, String failure_transaction_details,
		String hnps_channel_transaction_details, String mobile_and_internet_channel_transaction_details,
		String uniqueid, String service_date, String remarks, String project, String del_flag,
		byte[] nps_transaction_summary_sheetword_file, byte[] nps_transaction_summary_sheetexcel_file,
		byte[] transaction_monitor_inward_file, byte[] transaction_monitor_outward_file,
		byte[] transaction_monitor_return_file, byte[] failure_transaction_details_file,
		byte[] hnps_channel_transaction_details_file, byte[] mobile_and_internet_channel_transaction_details_file) {
	super();
	this.resource_name = resource_name;
	this.service_time = service_time;
	this.service_shift = service_shift;
	this.log_in_time = log_in_time;
	this.log_out_time = log_out_time;
	this.modify_time = modify_time;
	this.gateway_connectivity = gateway_connectivity;
	this.database_connectivity = database_connectivity;
	this.connect_24_connectivity = connect_24_connectivity;
	this.db_memory_check = db_memory_check;
	this.db_memory_increase = db_memory_increase;
	this.user_login_expiry_date = user_login_expiry_date;
	this.ssl_certificate_expiry_date = ssl_certificate_expiry_date;
	this.nps_transaction_summary_sheetword = nps_transaction_summary_sheetword;
	this.nps_transaction_summary_sheetexcel = nps_transaction_summary_sheetexcel;
	this.transaction_monitor_inward = transaction_monitor_inward;
	this.transaction_monitor_outward = transaction_monitor_outward;
	this.transaction_monitor_return = transaction_monitor_return;
	this.failure_transaction_details = failure_transaction_details;
	this.hnps_channel_transaction_details = hnps_channel_transaction_details;
	this.mobile_and_internet_channel_transaction_details = mobile_and_internet_channel_transaction_details;
	this.uniqueid = uniqueid;
	this.service_date = service_date;
	this.remarks = remarks;
	this.project = project;
	this.del_flag = del_flag;
	this.nps_transaction_summary_sheetword_file = nps_transaction_summary_sheetword_file;
	this.nps_transaction_summary_sheetexcel_file = nps_transaction_summary_sheetexcel_file;
	this.transaction_monitor_inward_file = transaction_monitor_inward_file;
	this.transaction_monitor_outward_file = transaction_monitor_outward_file;
	this.transaction_monitor_return_file = transaction_monitor_return_file;
	this.failure_transaction_details_file = failure_transaction_details_file;
	this.hnps_channel_transaction_details_file = hnps_channel_transaction_details_file;
	this.mobile_and_internet_channel_transaction_details_file = mobile_and_internet_channel_transaction_details_file;
}
public BTM_Daily_Checklist_Entity() {
	super();
	// TODO Auto-generated constructor stub
}

	
}