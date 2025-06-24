package com.bornfire.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Lob;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Arrays;
import java.util.Date;
@Entity
@Table(name="BTM_CALL_LOG")
//@IdClass(BTM_call_embeden.class)
public class BTM_Call_Log_Entity {
	
	

 private String project_name;
	
 private String lodged_by;
 @DateTimeFormat(pattern = "dd-MM-yy")
	    private Date lodged_date_and_time;
	    private String call_details;
	    private String received_by;
	   
	    private String call_assign_to;
	    private String status;
	    private String remarks;
	    private String entry_flg;
	   
	    private String verify_flg;
	    private String modify_time;
	    private String verify_time;
	    private String entry_time;
	    private String del_flg;
	    @Id
	    private String uniqueid;
	    private String modify_flg;
	    private String lodged_time;
	    @Lob
	    private byte[]	 call_log_file;
		public String getProject_name() {
			return project_name;
		}
		public void setProject_name(String project_name) {
			this.project_name = project_name;
		}
		public String getLodged_by() {
			return lodged_by;
		}
		public void setLodged_by(String lodged_by) {
			this.lodged_by = lodged_by;
		}
		public Date getLodged_date_and_time() {
			return lodged_date_and_time;
		}
		public void setLodged_date_and_time(Date lodged_date_and_time) {
			this.lodged_date_and_time = lodged_date_and_time;
		}
		public String getCall_details() {
			return call_details;
		}
		public void setCall_details(String call_details) {
			this.call_details = call_details;
		}
		public String getReceived_by() {
			return received_by;
		}
		public void setReceived_by(String received_by) {
			this.received_by = received_by;
		}
		public String getCall_assign_to() {
			return call_assign_to;
		}
		public void setCall_assign_to(String call_assign_to) {
			this.call_assign_to = call_assign_to;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getRemarks() {
			return remarks;
		}
		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}
		public String getEntry_flg() {
			return entry_flg;
		}
		public void setEntry_flg(String entry_flg) {
			this.entry_flg = entry_flg;
		}
		public String getVerify_flg() {
			return verify_flg;
		}
		public void setVerify_flg(String verify_flg) {
			this.verify_flg = verify_flg;
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
		public String getEntry_time() {
			return entry_time;
		}
		public void setEntry_time(String entry_time) {
			this.entry_time = entry_time;
		}
		public String getDel_flg() {
			return del_flg;
		}
		public void setDel_flg(String del_flg) {
			this.del_flg = del_flg;
		}
		public String getUniqueid() {
			return uniqueid;
		}
		public void setUniqueid(String uniqueid) {
			this.uniqueid = uniqueid;
		}
		public String getModify_flg() {
			return modify_flg;
		}
		public void setModify_flg(String modify_flg) {
			this.modify_flg = modify_flg;
		}
		public String getLodged_time() {
			return lodged_time;
		}
		public void setLodged_time(String lodged_time) {
			this.lodged_time = lodged_time;
		}
		public byte[] getCall_log_file() {
			return call_log_file;
		}
		public void setCall_log_file(byte[] call_log_file) {
			this.call_log_file = call_log_file;
		}
		@Override
		public String toString() {
			return "BTM_Call_Log_Entity [project_name=" + project_name + ", lodged_by=" + lodged_by
					+ ", lodged_date_and_time=" + lodged_date_and_time + ", call_details=" + call_details
					+ ", received_by=" + received_by + ", call_assign_to=" + call_assign_to + ", status=" + status
					+ ", remarks=" + remarks + ", entry_flg=" + entry_flg + ", verify_flg=" + verify_flg
					+ ", modify_time=" + modify_time + ", verify_time=" + verify_time + ", entry_time=" + entry_time
					+ ", del_flg=" + del_flg + ", uniqueid=" + uniqueid + ", modify_flg=" + modify_flg
					+ ", lodged_time=" + lodged_time + ", call_log_file=" + Arrays.toString(call_log_file)
					+ ", getProject_name()=" + getProject_name() + ", getLodged_by()=" + getLodged_by()
					+ ", getLodged_date_and_time()=" + getLodged_date_and_time() + ", getCall_details()="
					+ getCall_details() + ", getReceived_by()=" + getReceived_by() + ", getCall_assign_to()="
					+ getCall_assign_to() + ", getStatus()=" + getStatus() + ", getRemarks()=" + getRemarks()
					+ ", getEntry_flg()=" + getEntry_flg() + ", getVerify_flg()=" + getVerify_flg()
					+ ", getModify_time()=" + getModify_time() + ", getVerify_time()=" + getVerify_time()
					+ ", getEntry_time()=" + getEntry_time() + ", getDel_flg()=" + getDel_flg() + ", getUniqueid()="
					+ getUniqueid() + ", getModify_flg()=" + getModify_flg() + ", getLodged_time()=" + getLodged_time()
					+ ", getCall_log_file()=" + Arrays.toString(getCall_log_file()) + ", getClass()=" + getClass()
					+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
		}
		public BTM_Call_Log_Entity(String project_name, String lodged_by, Date lodged_date_and_time,
				String call_details, String received_by, String call_assign_to, String status, String remarks,
				String entry_flg, String verify_flg, String modify_time, String verify_time, String entry_time,
				String del_flg, String uniqueid, String modify_flg, String lodged_time, byte[] call_log_file) {
			super();
			this.project_name = project_name;
			this.lodged_by = lodged_by;
			this.lodged_date_and_time = lodged_date_and_time;
			this.call_details = call_details;
			this.received_by = received_by;
			this.call_assign_to = call_assign_to;
			this.status = status;
			this.remarks = remarks;
			this.entry_flg = entry_flg;
			this.verify_flg = verify_flg;
			this.modify_time = modify_time;
			this.verify_time = verify_time;
			this.entry_time = entry_time;
			this.del_flg = del_flg;
			this.uniqueid = uniqueid;
			this.modify_flg = modify_flg;
			this.lodged_time = lodged_time;
			this.call_log_file = call_log_file;
		}
		public BTM_Call_Log_Entity() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
	    

}
