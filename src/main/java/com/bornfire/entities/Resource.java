package com.bornfire.entities;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

public class Resource {

	@Id
	private String	resource_id_1;
	private String	resource_name_1;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	start_date_1;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	end_date_1;
	private String	project_period_1;
	@ManyToOne
    private ProjectDetailsEntity projectDetails;
	public String getResource_id_1() {
		return resource_id_1;
	}
	public void setResource_id_1(String resource_id_1) {
		this.resource_id_1 = resource_id_1;
	}
	public String getResource_name_1() {
		return resource_name_1;
	}
	public void setResource_name_1(String resource_name_1) {
		this.resource_name_1 = resource_name_1;
	}
	public Date getStart_date_1() {
		return start_date_1;
	}
	public void setStart_date_1(Date start_date_1) {
		this.start_date_1 = start_date_1;
	}
	public Date getEnd_date_1() {
		return end_date_1;
	}
	public void setEnd_date_1(Date end_date_1) {
		this.end_date_1 = end_date_1;
	}
	public String getProject_period_1() {
		return project_period_1;
	}
	public void setProject_period_1(String project_period_1) {
		this.project_period_1 = project_period_1;
	}
	public ProjectDetailsEntity getProjectDetails() {
		return projectDetails;
	}
	public void setProjectDetails(ProjectDetailsEntity projectDetails) {
		this.projectDetails = projectDetails;
	}
	public Resource(String resource_id_1, String resource_name_1, Date start_date_1, Date end_date_1,
			String project_period_1, ProjectDetailsEntity projectDetails) {
		super();
		this.resource_id_1 = resource_id_1;
		this.resource_name_1 = resource_name_1;
		this.start_date_1 = start_date_1;
		this.end_date_1 = end_date_1;
		this.project_period_1 = project_period_1;
		this.projectDetails = projectDetails;
	}
	public Resource() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
