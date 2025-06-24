package com.bornfire.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Btm_calllog_dto {
	
	
	private String project_name;
	
	 private String lodged_by;
	 @DateTimeFormat(pattern = "dd-MM-yy")
		    private Date lodged_date_and_time;
		    private String call_details;
		    private String received_by;
		   
		    private String call_assign_to;
		    private String status;
		    @Id
		    private String uniqueid;
			public Btm_calllog_dto() {
				super();
				// TODO Auto-generated constructor stub
			}
			public Btm_calllog_dto(String project_name, String lodged_by, Date lodged_date_and_time,
					String call_details, String received_by, String call_assign_to, String status, String uniqueid) {
				super();
				this.project_name = project_name;
				this.lodged_by = lodged_by;
				this.lodged_date_and_time = lodged_date_and_time;
				this.call_details = call_details;
				this.received_by = received_by;
				this.call_assign_to = call_assign_to;
				this.status = status;
				this.uniqueid = uniqueid;
			}
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
			public String getUniqueid() {
				return uniqueid;
			}
			public void setUniqueid(String uniqueid) {
				this.uniqueid = uniqueid;
			}
			@Override
			public String toString() {
				return "Btm_calllog_dto [project_name=" + project_name + ", lodged_by=" + lodged_by
						+ ", lodged_date_and_time=" + lodged_date_and_time + ", call_details=" + call_details
						+ ", received_by=" + received_by + ", call_assign_to=" + call_assign_to + ", status=" + status
						+ ", uniqueid=" + uniqueid + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
						+ ", toString()=" + super.toString() + "]";
			}

}
