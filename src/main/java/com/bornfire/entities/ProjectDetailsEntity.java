package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="AMC_PROJECT_DETAILS")
public class ProjectDetailsEntity {

	@Id
	private String	resource_id;
	private String	address;
	private String	client_id;
	private String	client_name;
	private String	client_type;
	private String	contact_person;
	private BigDecimal	contact_person_number;
	private String	country;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	end_date;
	private String	project_id;
	private String	project_name;
	private String	project_period;
	private String	project_type;
	private String	remarks_1;
	private String	remarks_2;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date start_date;
	private String	resource_name;
	private String	contact_person_2;
	private BigDecimal	contact_person_number_2;
	private String	contact_person_3;
	private BigDecimal	contact_person_number_3;
	private String	designation_1;
	private String	email_1;
	private String	designation_2;
	private String	email_2;
	private String	designation_3;
	private String	email_3;
	
	private String	resource_id_1;
	private String	resource_name_1;
	private String	start_date_1;
	private String	end_date_1;
	private String	project_period_1;
	private String	project_name_2;
	private String	location_2;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	start_date_2;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	end_date_2;
	private String	duration_2;
	private String	year_2;
	private String	amc_type_3;
	private String	amc_period_3;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	start_date_3;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	end_date_3;
	private String	project_name_3;
	private String	remarks_3;
	private String	del_flag;
	private String	uniqueid;
	public String getResource_id() {
		return resource_id;
	}
	public void setResource_id(String resource_id) {
		this.resource_id = resource_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getClient_id() {
		return client_id;
	}
	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public String getClient_type() {
		return client_type;
	}
	public void setClient_type(String client_type) {
		this.client_type = client_type;
	}
	public String getContact_person() {
		return contact_person;
	}
	public void setContact_person(String contact_person) {
		this.contact_person = contact_person;
	}
	public BigDecimal getContact_person_number() {
		return contact_person_number;
	}
	public void setContact_person_number(BigDecimal contact_person_number) {
		this.contact_person_number = contact_person_number;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getProject_id() {
		return project_id;
	}
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getProject_period() {
		return project_period;
	}
	public void setProject_period(String project_period) {
		this.project_period = project_period;
	}
	public String getProject_type() {
		return project_type;
	}
	public void setProject_type(String project_type) {
		this.project_type = project_type;
	}
	public String getRemarks_1() {
		return remarks_1;
	}
	public void setRemarks_1(String remarks_1) {
		this.remarks_1 = remarks_1;
	}
	public String getRemarks_2() {
		return remarks_2;
	}
	public void setRemarks_2(String remarks_2) {
		this.remarks_2 = remarks_2;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public String getResource_name() {
		return resource_name;
	}
	public void setResource_name(String resource_name) {
		this.resource_name = resource_name;
	}
	public String getContact_person_2() {
		return contact_person_2;
	}
	public void setContact_person_2(String contact_person_2) {
		this.contact_person_2 = contact_person_2;
	}
	public BigDecimal getContact_person_number_2() {
		return contact_person_number_2;
	}
	public void setContact_person_number_2(BigDecimal contact_person_number_2) {
		this.contact_person_number_2 = contact_person_number_2;
	}
	public String getContact_person_3() {
		return contact_person_3;
	}
	public void setContact_person_3(String contact_person_3) {
		this.contact_person_3 = contact_person_3;
	}
	public BigDecimal getContact_person_number_3() {
		return contact_person_number_3;
	}
	public void setContact_person_number_3(BigDecimal contact_person_number_3) {
		this.contact_person_number_3 = contact_person_number_3;
	}
	public String getDesignation_1() {
		return designation_1;
	}
	public void setDesignation_1(String designation_1) {
		this.designation_1 = designation_1;
	}
	public String getEmail_1() {
		return email_1;
	}
	public void setEmail_1(String email_1) {
		this.email_1 = email_1;
	}
	public String getDesignation_2() {
		return designation_2;
	}
	public void setDesignation_2(String designation_2) {
		this.designation_2 = designation_2;
	}
	public String getEmail_2() {
		return email_2;
	}
	public void setEmail_2(String email_2) {
		this.email_2 = email_2;
	}
	public String getDesignation_3() {
		return designation_3;
	}
	public void setDesignation_3(String designation_3) {
		this.designation_3 = designation_3;
	}
	public String getEmail_3() {
		return email_3;
	}
	public void setEmail_3(String email_3) {
		this.email_3 = email_3;
	}
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
	public String getStart_date_1() {
		return start_date_1;
	}
	public void setStart_date_1(String start_date_1) {
		this.start_date_1 = start_date_1;
	}
	public String getEnd_date_1() {
		return end_date_1;
	}
	public void setEnd_date_1(String end_date_1) {
		this.end_date_1 = end_date_1;
	}
	public String getProject_period_1() {
		return project_period_1;
	}
	public void setProject_period_1(String project_period_1) {
		this.project_period_1 = project_period_1;
	}
	public String getProject_name_2() {
		return project_name_2;
	}
	public void setProject_name_2(String project_name_2) {
		this.project_name_2 = project_name_2;
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
	public String getAmc_type_3() {
		return amc_type_3;
	}
	public void setAmc_type_3(String amc_type_3) {
		this.amc_type_3 = amc_type_3;
	}
	public String getAmc_period_3() {
		return amc_period_3;
	}
	public void setAmc_period_3(String amc_period_3) {
		this.amc_period_3 = amc_period_3;
	}
	public Date getStart_date_3() {
		return start_date_3;
	}
	public void setStart_date_3(Date start_date_3) {
		this.start_date_3 = start_date_3;
	}
	public Date getEnd_date_3() {
		return end_date_3;
	}
	public void setEnd_date_3(Date end_date_3) {
		this.end_date_3 = end_date_3;
	}
	public String getProject_name_3() {
		return project_name_3;
	}
	public void setProject_name_3(String project_name_3) {
		this.project_name_3 = project_name_3;
	}
	public String getRemarks_3() {
		return remarks_3;
	}
	public void setRemarks_3(String remarks_3) {
		this.remarks_3 = remarks_3;
	}
	public String getDel_flag() {
		return del_flag;
	}
	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	public ProjectDetailsEntity(String resource_id, String address, String client_id, String client_name,
			String client_type, String contact_person, BigDecimal contact_person_number, String country, Date end_date,
			String project_id, String project_name, String project_period, String project_type, String remarks_1,
			String remarks_2, Date start_date, String resource_name, String contact_person_2,
			BigDecimal contact_person_number_2, String contact_person_3, BigDecimal contact_person_number_3,
			String designation_1, String email_1, String designation_2, String email_2, String designation_3,
			String email_3, String resource_id_1, String resource_name_1, String start_date_1, String end_date_1,
			String project_period_1, String project_name_2, String location_2, Date start_date_2, Date end_date_2,
			String duration_2, String year_2, String amc_type_3, String amc_period_3, Date start_date_3,
			Date end_date_3, String project_name_3, String remarks_3, String del_flag, String uniqueid) {
		super();
		this.resource_id = resource_id;
		this.address = address;
		this.client_id = client_id;
		this.client_name = client_name;
		this.client_type = client_type;
		this.contact_person = contact_person;
		this.contact_person_number = contact_person_number;
		this.country = country;
		this.end_date = end_date;
		this.project_id = project_id;
		this.project_name = project_name;
		this.project_period = project_period;
		this.project_type = project_type;
		this.remarks_1 = remarks_1;
		this.remarks_2 = remarks_2;
		this.start_date = start_date;
		this.resource_name = resource_name;
		this.contact_person_2 = contact_person_2;
		this.contact_person_number_2 = contact_person_number_2;
		this.contact_person_3 = contact_person_3;
		this.contact_person_number_3 = contact_person_number_3;
		this.designation_1 = designation_1;
		this.email_1 = email_1;
		this.designation_2 = designation_2;
		this.email_2 = email_2;
		this.designation_3 = designation_3;
		this.email_3 = email_3;
		this.resource_id_1 = resource_id_1;
		this.resource_name_1 = resource_name_1;
		this.start_date_1 = start_date_1;
		this.end_date_1 = end_date_1;
		this.project_period_1 = project_period_1;
		this.project_name_2 = project_name_2;
		this.location_2 = location_2;
		this.start_date_2 = start_date_2;
		this.end_date_2 = end_date_2;
		this.duration_2 = duration_2;
		this.year_2 = year_2;
		this.amc_type_3 = amc_type_3;
		this.amc_period_3 = amc_period_3;
		this.start_date_3 = start_date_3;
		this.end_date_3 = end_date_3;
		this.project_name_3 = project_name_3;
		this.remarks_3 = remarks_3;
		this.del_flag = del_flag;
		this.uniqueid = uniqueid;
	}
	public ProjectDetailsEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
