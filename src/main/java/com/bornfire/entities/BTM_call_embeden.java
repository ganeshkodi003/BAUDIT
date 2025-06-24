package com.bornfire.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.Id;

@Embeddable
public class BTM_call_embeden implements Serializable{
	
	private static final long serialVersionUID=1L;
	
	
	@Id
	private String project_name;
	@Id
	private String lodged_date_and_time;
	@Id
	private String call_assign_to;
	
	@Id
	private String lodged_by;

	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getLodged_date_and_time() {
		return lodged_date_and_time;
	}

	public void setLodged_date_and_time(String lodged_date_and_time) {
		this.lodged_date_and_time = lodged_date_and_time;
	}

	public String getCall_assign_to() {
		return call_assign_to;
	}

	public void setCall_assign_to(String call_assign_to) {
		this.call_assign_to = call_assign_to;
	}

	public String getLodged_by() {
		return lodged_by;
	}

	public void setLodged_by(String lodged_by) {
		this.lodged_by = lodged_by;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
