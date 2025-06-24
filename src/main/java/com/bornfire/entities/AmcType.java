package com.bornfire.entities;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

public class AmcType {

	@Id
	private String	amc_type_2;
	private String	location_2;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	start_date_2;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	end_date_2;
	private String	duration_2;
	private String	year_2;
	@ManyToOne
    private ProjectDetailsEntity projectDetails;
	public String getAmc_type_2() {
		return amc_type_2;
	}
	public void setAmc_type_2(String amc_type_2) {
		this.amc_type_2 = amc_type_2;
	}
	public String getLocation_2() {
		return location_2;
	}
	public void setLocation_2(String location_2) {
		this.location_2 = location_2;
	}
	public Date getStart_date_2() {
		return start_date_2;
	}
	public void setStart_date_2(Date start_date_2) {
		this.start_date_2 = start_date_2;
	}
	public Date getEnd_date_2() {
		return end_date_2;
	}
	public void setEnd_date_2(Date end_date_2) {
		this.end_date_2 = end_date_2;
	}
	public String getDuration_2() {
		return duration_2;
	}
	public void setDuration_2(String duration_2) {
		this.duration_2 = duration_2;
	}
	public String getYear_2() {
		return year_2;
	}
	public void setYear_2(String year_2) {
		this.year_2 = year_2;
	}
	public ProjectDetailsEntity getProjectDetails() {
		return projectDetails;
	}
	public void setProjectDetails(ProjectDetailsEntity projectDetails) {
		this.projectDetails = projectDetails;
	}
	public AmcType(String amc_type_2, String location_2, Date start_date_2, Date end_date_2, String duration_2,
			String year_2, ProjectDetailsEntity projectDetails) {
		super();
		this.amc_type_2 = amc_type_2;
		this.location_2 = location_2;
		this.start_date_2 = start_date_2;
		this.end_date_2 = end_date_2;
		this.duration_2 = duration_2;
		this.year_2 = year_2;
		this.projectDetails = projectDetails;
	}
	public AmcType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
