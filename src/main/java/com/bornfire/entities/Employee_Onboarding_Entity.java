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
@Table(name = "BTM_EMPLOYEES_ONBOARDING_PROCESS")
public class Employee_Onboarding_Entity {
	@Id
	private String	employee_id;
	private String	employee_name;
	private String	designation;
	private String	blood_group;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	date_of_birth;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	date_of_joining;
	private String	gender;
	private String	marital_status;
	private String	mobile_no;
	private String	personal_email_id;
	private String	pan_number;
	private String	aadhar_number;
	private String	address;
	private String	location;
	private String	emergency_contact_person;
	private String	emergency_contact_no;
	private String	bank_name;
	private String	bank_account_no;
	private String	ifsc_code;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	DOR;
	private String	password;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	password_expiry_date;
	private String	login_status_flag;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	account_expiry_date;
	private String	disable_flag;
	private String	disable_remarks;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	disable_start_date;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date	disable_end_date;
	private String	bornfire_mail_id;
	private String user_remarks;
	
	
	private String	educational_qualification;
	private String	additional_qualification;
	private String	certificates;
	private String	skill_set;
	private BigDecimal	experience;
	private String	project_experience;
	private String	entry_user;
	private String	modify_user;
	private String	auth_user;
	private Date	auth_time;
	private String	del_flg;
	private String	modify_flg;
	private String	entity_flg;
	private String	organization_name1;
	private String	organization_name2;
	private String	organization_name3;
	private String	organization_name4;
	private String	organization_name5;
	private String	emp_designation1;
	private String	emp_designation2;
	private String	emp_designation3;
	private String	emp_designation4;
	private String	emp_designation5;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	emp_start_date1;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	emp_start_date2;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	emp_start_date3;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	emp_start_date4;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	emp_start_date5;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	emp_end_date1;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	emp_end_date2;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	emp_end_date3;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	emp_end_date4;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	emp_end_date5;
	private String	nature_of_employment1;
	private String	nature_of_employment2;
	private String	nature_of_employment3;
	private String	nature_of_employment4;
	private String	nature_of_employment5;
	private String	emp_rol_and_res1;
	private String	emp_rol_and_res2;
	private String	emp_rol_and_res3;
	private String	emp_rol_and_res4;
	private String	emp_rol_and_res5;
	private String	emp_location1;
	private String	emp_location2;
	private String	emp_location3;
	private String	emp_location4;
	private String	emp_location5;
	private Date	entry_time;
	private Date	modify_time;
	private BigDecimal	srl_no;
	private String	project_type1;
	private String	project_type2;
	private String	project_type3;
	private String	project_type4;
	private String	project_type5;
	private String	project_type6;
	private String	project_type7;
	private String	project_type8;
	private String	project_type9;
	private String	project_type10;
	private String	project_name1;
	private String	project_name2;
	private String	project_name3;
	private String	project_name4;
	private String	project_name5;
	private String	project_name6;
	private String	project_name7;
	private String	project_name8;
	private String	project_name9;
	private String	project_name10;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	proj_start_date1;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	proj_start_date2;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	proj_start_date3;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	proj_start_date4;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	proj_start_date5;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	proj_start_date6;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	proj_start_date7;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	proj_start_date8;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	proj_start_date9;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	proj_start_date10;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	proj_end_date1;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	proj_end_date2;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	proj_end_date3;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	proj_end_date4;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	proj_end_date5;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	proj_end_date6;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	proj_end_date7;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	proj_end_date8;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	proj_end_date9;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	proj_end_date10;
	private String	client1;
	private String	client2;
	private String	client3;
	private String	client4;
	private String	client5;
	private String	client6;
	private String	client7;
	private String	client8;
	private String	client9;
	private String	client10;
	private String	rol_and_res1;
	private String	rol_and_res2;
	private String	rol_and_res3;
	private String	rol_and_res4;
	private String	rol_and_res5;
	private String	rol_and_res6;
	private String	rol_and_res7;
	private String	rol_and_res8;
	private String	rol_and_res9;
	private String	rol_and_res10;
	private String	pro_location1;
	private String	pro_location2;
	private String	pro_location3;
	private String	pro_location4;
	private String	pro_location5;
	private String	pro_location6;
	private String	pro_location7;
	private String	pro_location8;
	private String	pro_location9;
	private String	pro_location10;
	private String	document_type1;
	private String	document_type2;
	private String	document_type3;
	private String	document_type4;
	private String	document_type5;
	private String	document_type6;
	private String	document_type7;
	private String	document_type8;
	private String	document_type9;
	private String	document_type10;
	private String	document_name1;
	private String	document_name2;
	private String	document_name3;
	private String	document_name4;
	private String	document_name5;
	private String	document_name6;
	private String	document_name7;
	private String	document_name8;
	private String	document_name9;
	private String	document_name10;
	private String	document_no1;
	private String	document_no2;
	private String	document_no3;
	private String	document_no4;
	private String	document_no5;
	private String	document_no6;
	private String	document_no7;
	private String	document_no8;
	private String	document_no9;
	private String	document_no10;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	issue_date1;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	issue_date2;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	issue_date3;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	issue_date4;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	issue_date5;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	issue_date6;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	issue_date7;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	issue_date8;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	issue_date9;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	issue_date10;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	expiry_date1;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	expiry_date2;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	expiry_date3;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	expiry_date4;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	expiry_date5;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	expiry_date6;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	expiry_date7;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	expiry_date8;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	expiry_date9;
	@DateTimeFormat(pattern = "yyyy-MM-dd")

	private Date	expiry_date10;
	private String	place_of_issue1;
	private String	place_of_issue2;
	private String	place_of_issue3;
	private String	place_of_issue4;
	private String	place_of_issue5;
	private String	place_of_issue6;
	private String	place_of_issue7;
	private String	place_of_issue8;
	private String	place_of_issue9;
	private String	place_of_issue10;
	@Lob
	private  byte[]	document_upload1;
	 @Lob
	private  byte[]	document_upload10;
	 @Lob
	private  byte[]	document_upload2;
	 @Lob
	private  byte[]	document_upload3;
	 @Lob
	private  byte[]	document_upload4;
	 @Lob
	private  byte[]	document_upload5;
	 @Lob
	private  byte[]	document_upload6;
	 @Lob
	private  byte[]	document_upload7;
	 @Lob
	private  byte[]	document_upload8;
	 @Lob
	private  byte[]	document_upload9;
	private String	verify_flg;
	private String	document_upload_format1;
	private String	document_upload_format2;
	private String	document_upload_format3;
	private String	document_upload_format4;
	private String	document_upload_format5;
	private String	document_upload_format6;
	private String	document_upload_format7;
	private String	document_upload_format8;
	private String	document_upload_format9;
	private String	document_upload_format10;
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getBlood_group() {
		return blood_group;
	}
	public void setBlood_group(String blood_group) {
		this.blood_group = blood_group;
	}
	public Date getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public Date getDate_of_joining() {
		return date_of_joining;
	}
	public void setDate_of_joining(Date date_of_joining) {
		this.date_of_joining = date_of_joining;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMarital_status() {
		return marital_status;
	}
	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getPersonal_email_id() {
		return personal_email_id;
	}
	public void setPersonal_email_id(String personal_email_id) {
		this.personal_email_id = personal_email_id;
	}
	public String getPan_number() {
		return pan_number;
	}
	public void setPan_number(String pan_number) {
		this.pan_number = pan_number;
	}
	public String getAadhar_number() {
		return aadhar_number;
	}
	public void setAadhar_number(String aadhar_number) {
		this.aadhar_number = aadhar_number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEmergency_contact_person() {
		return emergency_contact_person;
	}
	public void setEmergency_contact_person(String emergency_contact_person) {
		this.emergency_contact_person = emergency_contact_person;
	}
	public String getEmergency_contact_no() {
		return emergency_contact_no;
	}
	public void setEmergency_contact_no(String emergency_contact_no) {
		this.emergency_contact_no = emergency_contact_no;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public String getBank_account_no() {
		return bank_account_no;
	}
	public void setBank_account_no(String bank_account_no) {
		this.bank_account_no = bank_account_no;
	}
	public String getIfsc_code() {
		return ifsc_code;
	}
	public void setIfsc_code(String ifsc_code) {
		this.ifsc_code = ifsc_code;
	}
	public Date getDOR() {
		return DOR;
	}
	public void setDOR(Date dOR) {
		DOR = dOR;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getPassword_expiry_date() {
		return password_expiry_date;
	}
	public void setPassword_expiry_date(Date password_expiry_date) {
		this.password_expiry_date = password_expiry_date;
	}
	public String getLogin_status_flag() {
		return login_status_flag;
	}
	public void setLogin_status_flag(String login_status_flag) {
		this.login_status_flag = login_status_flag;
	}
	public Date getAccount_expiry_date() {
		return account_expiry_date;
	}
	public void setAccount_expiry_date(Date account_expiry_date) {
		this.account_expiry_date = account_expiry_date;
	}
	public String getDisable_flag() {
		return disable_flag;
	}
	public void setDisable_flag(String disable_flag) {
		this.disable_flag = disable_flag;
	}
	public String getDisable_remarks() {
		return disable_remarks;
	}
	public void setDisable_remarks(String disable_remarks) {
		this.disable_remarks = disable_remarks;
	}
	public Date getDisable_start_date() {
		return disable_start_date;
	}
	public void setDisable_start_date(Date disable_start_date) {
		this.disable_start_date = disable_start_date;
	}
	public Date getDisable_end_date() {
		return disable_end_date;
	}
	public void setDisable_end_date(Date disable_end_date) {
		this.disable_end_date = disable_end_date;
	}
	public String getBornfire_mail_id() {
		return bornfire_mail_id;
	}
	public void setBornfire_mail_id(String bornfire_mail_id) {
		this.bornfire_mail_id = bornfire_mail_id;
	}
	public String getUser_remarks() {
		return user_remarks;
	}
	public void setUser_remarks(String user_remarks) {
		this.user_remarks = user_remarks;
	}
	public String getEducational_qualification() {
		return educational_qualification;
	}
	public void setEducational_qualification(String educational_qualification) {
		this.educational_qualification = educational_qualification;
	}
	public String getAdditional_qualification() {
		return additional_qualification;
	}
	public void setAdditional_qualification(String additional_qualification) {
		this.additional_qualification = additional_qualification;
	}
	public String getCertificates() {
		return certificates;
	}
	public void setCertificates(String certificates) {
		this.certificates = certificates;
	}
	public String getSkill_set() {
		return skill_set;
	}
	public void setSkill_set(String skill_set) {
		this.skill_set = skill_set;
	}
	public BigDecimal getExperience() {
		return experience;
	}
	public void setExperience(BigDecimal experience) {
		this.experience = experience;
	}
	public String getProject_experience() {
		return project_experience;
	}
	public void setProject_experience(String project_experience) {
		this.project_experience = project_experience;
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
	public String getAuth_user() {
		return auth_user;
	}
	public void setAuth_user(String auth_user) {
		this.auth_user = auth_user;
	}
	public Date getAuth_time() {
		return auth_time;
	}
	public void setAuth_time(Date auth_time) {
		this.auth_time = auth_time;
	}
	public String getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(String del_flg) {
		this.del_flg = del_flg;
	}
	public String getModify_flg() {
		return modify_flg;
	}
	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}
	public String getEntity_flg() {
		return entity_flg;
	}
	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}
	public String getOrganization_name1() {
		return organization_name1;
	}
	public void setOrganization_name1(String organization_name1) {
		this.organization_name1 = organization_name1;
	}
	public String getOrganization_name2() {
		return organization_name2;
	}
	public void setOrganization_name2(String organization_name2) {
		this.organization_name2 = organization_name2;
	}
	public String getOrganization_name3() {
		return organization_name3;
	}
	public void setOrganization_name3(String organization_name3) {
		this.organization_name3 = organization_name3;
	}
	public String getOrganization_name4() {
		return organization_name4;
	}
	public void setOrganization_name4(String organization_name4) {
		this.organization_name4 = organization_name4;
	}
	public String getOrganization_name5() {
		return organization_name5;
	}
	public void setOrganization_name5(String organization_name5) {
		this.organization_name5 = organization_name5;
	}
	public String getEmp_designation1() {
		return emp_designation1;
	}
	public void setEmp_designation1(String emp_designation1) {
		this.emp_designation1 = emp_designation1;
	}
	public String getEmp_designation2() {
		return emp_designation2;
	}
	public void setEmp_designation2(String emp_designation2) {
		this.emp_designation2 = emp_designation2;
	}
	public String getEmp_designation3() {
		return emp_designation3;
	}
	public void setEmp_designation3(String emp_designation3) {
		this.emp_designation3 = emp_designation3;
	}
	public String getEmp_designation4() {
		return emp_designation4;
	}
	public void setEmp_designation4(String emp_designation4) {
		this.emp_designation4 = emp_designation4;
	}
	public String getEmp_designation5() {
		return emp_designation5;
	}
	public void setEmp_designation5(String emp_designation5) {
		this.emp_designation5 = emp_designation5;
	}
	public Date getEmp_start_date1() {
		return emp_start_date1;
	}
	public void setEmp_start_date1(Date emp_start_date1) {
		this.emp_start_date1 = emp_start_date1;
	}
	public Date getEmp_start_date2() {
		return emp_start_date2;
	}
	public void setEmp_start_date2(Date emp_start_date2) {
		this.emp_start_date2 = emp_start_date2;
	}
	public Date getEmp_start_date3() {
		return emp_start_date3;
	}
	public void setEmp_start_date3(Date emp_start_date3) {
		this.emp_start_date3 = emp_start_date3;
	}
	public Date getEmp_start_date4() {
		return emp_start_date4;
	}
	public void setEmp_start_date4(Date emp_start_date4) {
		this.emp_start_date4 = emp_start_date4;
	}
	public Date getEmp_start_date5() {
		return emp_start_date5;
	}
	public void setEmp_start_date5(Date emp_start_date5) {
		this.emp_start_date5 = emp_start_date5;
	}
	public Date getEmp_end_date1() {
		return emp_end_date1;
	}
	public void setEmp_end_date1(Date emp_end_date1) {
		this.emp_end_date1 = emp_end_date1;
	}
	public Date getEmp_end_date2() {
		return emp_end_date2;
	}
	public void setEmp_end_date2(Date emp_end_date2) {
		this.emp_end_date2 = emp_end_date2;
	}
	public Date getEmp_end_date3() {
		return emp_end_date3;
	}
	public void setEmp_end_date3(Date emp_end_date3) {
		this.emp_end_date3 = emp_end_date3;
	}
	public Date getEmp_end_date4() {
		return emp_end_date4;
	}
	public void setEmp_end_date4(Date emp_end_date4) {
		this.emp_end_date4 = emp_end_date4;
	}
	public Date getEmp_end_date5() {
		return emp_end_date5;
	}
	public void setEmp_end_date5(Date emp_end_date5) {
		this.emp_end_date5 = emp_end_date5;
	}
	public String getNature_of_employment1() {
		return nature_of_employment1;
	}
	public void setNature_of_employment1(String nature_of_employment1) {
		this.nature_of_employment1 = nature_of_employment1;
	}
	public String getNature_of_employment2() {
		return nature_of_employment2;
	}
	public void setNature_of_employment2(String nature_of_employment2) {
		this.nature_of_employment2 = nature_of_employment2;
	}
	public String getNature_of_employment3() {
		return nature_of_employment3;
	}
	public void setNature_of_employment3(String nature_of_employment3) {
		this.nature_of_employment3 = nature_of_employment3;
	}
	public String getNature_of_employment4() {
		return nature_of_employment4;
	}
	public void setNature_of_employment4(String nature_of_employment4) {
		this.nature_of_employment4 = nature_of_employment4;
	}
	public String getNature_of_employment5() {
		return nature_of_employment5;
	}
	public void setNature_of_employment5(String nature_of_employment5) {
		this.nature_of_employment5 = nature_of_employment5;
	}
	public String getEmp_rol_and_res1() {
		return emp_rol_and_res1;
	}
	public void setEmp_rol_and_res1(String emp_rol_and_res1) {
		this.emp_rol_and_res1 = emp_rol_and_res1;
	}
	public String getEmp_rol_and_res2() {
		return emp_rol_and_res2;
	}
	public void setEmp_rol_and_res2(String emp_rol_and_res2) {
		this.emp_rol_and_res2 = emp_rol_and_res2;
	}
	public String getEmp_rol_and_res3() {
		return emp_rol_and_res3;
	}
	public void setEmp_rol_and_res3(String emp_rol_and_res3) {
		this.emp_rol_and_res3 = emp_rol_and_res3;
	}
	public String getEmp_rol_and_res4() {
		return emp_rol_and_res4;
	}
	public void setEmp_rol_and_res4(String emp_rol_and_res4) {
		this.emp_rol_and_res4 = emp_rol_and_res4;
	}
	public String getEmp_rol_and_res5() {
		return emp_rol_and_res5;
	}
	public void setEmp_rol_and_res5(String emp_rol_and_res5) {
		this.emp_rol_and_res5 = emp_rol_and_res5;
	}
	public String getEmp_location1() {
		return emp_location1;
	}
	public void setEmp_location1(String emp_location1) {
		this.emp_location1 = emp_location1;
	}
	public String getEmp_location2() {
		return emp_location2;
	}
	public void setEmp_location2(String emp_location2) {
		this.emp_location2 = emp_location2;
	}
	public String getEmp_location3() {
		return emp_location3;
	}
	public void setEmp_location3(String emp_location3) {
		this.emp_location3 = emp_location3;
	}
	public String getEmp_location4() {
		return emp_location4;
	}
	public void setEmp_location4(String emp_location4) {
		this.emp_location4 = emp_location4;
	}
	public String getEmp_location5() {
		return emp_location5;
	}
	public void setEmp_location5(String emp_location5) {
		this.emp_location5 = emp_location5;
	}
	public Date getEntry_time() {
		return entry_time;
	}
	public void setEntry_time(Date entry_time) {
		this.entry_time = entry_time;
	}
	public Date getModify_time() {
		return modify_time;
	}
	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	public BigDecimal getSrl_no() {
		return srl_no;
	}
	public void setSrl_no(BigDecimal srl_no) {
		this.srl_no = srl_no;
	}
	public String getProject_type1() {
		return project_type1;
	}
	public void setProject_type1(String project_type1) {
		this.project_type1 = project_type1;
	}
	public String getProject_type2() {
		return project_type2;
	}
	public void setProject_type2(String project_type2) {
		this.project_type2 = project_type2;
	}
	public String getProject_type3() {
		return project_type3;
	}
	public void setProject_type3(String project_type3) {
		this.project_type3 = project_type3;
	}
	public String getProject_type4() {
		return project_type4;
	}
	public void setProject_type4(String project_type4) {
		this.project_type4 = project_type4;
	}
	public String getProject_type5() {
		return project_type5;
	}
	public void setProject_type5(String project_type5) {
		this.project_type5 = project_type5;
	}
	public String getProject_type6() {
		return project_type6;
	}
	public void setProject_type6(String project_type6) {
		this.project_type6 = project_type6;
	}
	public String getProject_type7() {
		return project_type7;
	}
	public void setProject_type7(String project_type7) {
		this.project_type7 = project_type7;
	}
	public String getProject_type8() {
		return project_type8;
	}
	public void setProject_type8(String project_type8) {
		this.project_type8 = project_type8;
	}
	public String getProject_type9() {
		return project_type9;
	}
	public void setProject_type9(String project_type9) {
		this.project_type9 = project_type9;
	}
	public String getProject_type10() {
		return project_type10;
	}
	public void setProject_type10(String project_type10) {
		this.project_type10 = project_type10;
	}
	public String getProject_name1() {
		return project_name1;
	}
	public void setProject_name1(String project_name1) {
		this.project_name1 = project_name1;
	}
	public String getProject_name2() {
		return project_name2;
	}
	public void setProject_name2(String project_name2) {
		this.project_name2 = project_name2;
	}
	public String getProject_name3() {
		return project_name3;
	}
	public void setProject_name3(String project_name3) {
		this.project_name3 = project_name3;
	}
	public String getProject_name4() {
		return project_name4;
	}
	public void setProject_name4(String project_name4) {
		this.project_name4 = project_name4;
	}
	public String getProject_name5() {
		return project_name5;
	}
	public void setProject_name5(String project_name5) {
		this.project_name5 = project_name5;
	}
	public String getProject_name6() {
		return project_name6;
	}
	public void setProject_name6(String project_name6) {
		this.project_name6 = project_name6;
	}
	public String getProject_name7() {
		return project_name7;
	}
	public void setProject_name7(String project_name7) {
		this.project_name7 = project_name7;
	}
	public String getProject_name8() {
		return project_name8;
	}
	public void setProject_name8(String project_name8) {
		this.project_name8 = project_name8;
	}
	public String getProject_name9() {
		return project_name9;
	}
	public void setProject_name9(String project_name9) {
		this.project_name9 = project_name9;
	}
	public String getProject_name10() {
		return project_name10;
	}
	public void setProject_name10(String project_name10) {
		this.project_name10 = project_name10;
	}
	public Date getProj_start_date1() {
		return proj_start_date1;
	}
	public void setProj_start_date1(Date proj_start_date1) {
		this.proj_start_date1 = proj_start_date1;
	}
	public Date getProj_start_date2() {
		return proj_start_date2;
	}
	public void setProj_start_date2(Date proj_start_date2) {
		this.proj_start_date2 = proj_start_date2;
	}
	public Date getProj_start_date3() {
		return proj_start_date3;
	}
	public void setProj_start_date3(Date proj_start_date3) {
		this.proj_start_date3 = proj_start_date3;
	}
	public Date getProj_start_date4() {
		return proj_start_date4;
	}
	public void setProj_start_date4(Date proj_start_date4) {
		this.proj_start_date4 = proj_start_date4;
	}
	public Date getProj_start_date5() {
		return proj_start_date5;
	}
	public void setProj_start_date5(Date proj_start_date5) {
		this.proj_start_date5 = proj_start_date5;
	}
	public Date getProj_start_date6() {
		return proj_start_date6;
	}
	public void setProj_start_date6(Date proj_start_date6) {
		this.proj_start_date6 = proj_start_date6;
	}
	public Date getProj_start_date7() {
		return proj_start_date7;
	}
	public void setProj_start_date7(Date proj_start_date7) {
		this.proj_start_date7 = proj_start_date7;
	}
	public Date getProj_start_date8() {
		return proj_start_date8;
	}
	public void setProj_start_date8(Date proj_start_date8) {
		this.proj_start_date8 = proj_start_date8;
	}
	public Date getProj_start_date9() {
		return proj_start_date9;
	}
	public void setProj_start_date9(Date proj_start_date9) {
		this.proj_start_date9 = proj_start_date9;
	}
	public Date getProj_start_date10() {
		return proj_start_date10;
	}
	public void setProj_start_date10(Date proj_start_date10) {
		this.proj_start_date10 = proj_start_date10;
	}
	public Date getProj_end_date1() {
		return proj_end_date1;
	}
	public void setProj_end_date1(Date proj_end_date1) {
		this.proj_end_date1 = proj_end_date1;
	}
	public Date getProj_end_date2() {
		return proj_end_date2;
	}
	public void setProj_end_date2(Date proj_end_date2) {
		this.proj_end_date2 = proj_end_date2;
	}
	public Date getProj_end_date3() {
		return proj_end_date3;
	}
	public void setProj_end_date3(Date proj_end_date3) {
		this.proj_end_date3 = proj_end_date3;
	}
	public Date getProj_end_date4() {
		return proj_end_date4;
	}
	public void setProj_end_date4(Date proj_end_date4) {
		this.proj_end_date4 = proj_end_date4;
	}
	public Date getProj_end_date5() {
		return proj_end_date5;
	}
	public void setProj_end_date5(Date proj_end_date5) {
		this.proj_end_date5 = proj_end_date5;
	}
	public Date getProj_end_date6() {
		return proj_end_date6;
	}
	public void setProj_end_date6(Date proj_end_date6) {
		this.proj_end_date6 = proj_end_date6;
	}
	public Date getProj_end_date7() {
		return proj_end_date7;
	}
	public void setProj_end_date7(Date proj_end_date7) {
		this.proj_end_date7 = proj_end_date7;
	}
	public Date getProj_end_date8() {
		return proj_end_date8;
	}
	public void setProj_end_date8(Date proj_end_date8) {
		this.proj_end_date8 = proj_end_date8;
	}
	public Date getProj_end_date9() {
		return proj_end_date9;
	}
	public void setProj_end_date9(Date proj_end_date9) {
		this.proj_end_date9 = proj_end_date9;
	}
	public Date getProj_end_date10() {
		return proj_end_date10;
	}
	public void setProj_end_date10(Date proj_end_date10) {
		this.proj_end_date10 = proj_end_date10;
	}
	public String getClient1() {
		return client1;
	}
	public void setClient1(String client1) {
		this.client1 = client1;
	}
	public String getClient2() {
		return client2;
	}
	public void setClient2(String client2) {
		this.client2 = client2;
	}
	public String getClient3() {
		return client3;
	}
	public void setClient3(String client3) {
		this.client3 = client3;
	}
	public String getClient4() {
		return client4;
	}
	public void setClient4(String client4) {
		this.client4 = client4;
	}
	public String getClient5() {
		return client5;
	}
	public void setClient5(String client5) {
		this.client5 = client5;
	}
	public String getClient6() {
		return client6;
	}
	public void setClient6(String client6) {
		this.client6 = client6;
	}
	public String getClient7() {
		return client7;
	}
	public void setClient7(String client7) {
		this.client7 = client7;
	}
	public String getClient8() {
		return client8;
	}
	public void setClient8(String client8) {
		this.client8 = client8;
	}
	public String getClient9() {
		return client9;
	}
	public void setClient9(String client9) {
		this.client9 = client9;
	}
	public String getClient10() {
		return client10;
	}
	public void setClient10(String client10) {
		this.client10 = client10;
	}
	public String getRol_and_res1() {
		return rol_and_res1;
	}
	public void setRol_and_res1(String rol_and_res1) {
		this.rol_and_res1 = rol_and_res1;
	}
	public String getRol_and_res2() {
		return rol_and_res2;
	}
	public void setRol_and_res2(String rol_and_res2) {
		this.rol_and_res2 = rol_and_res2;
	}
	public String getRol_and_res3() {
		return rol_and_res3;
	}
	public void setRol_and_res3(String rol_and_res3) {
		this.rol_and_res3 = rol_and_res3;
	}
	public String getRol_and_res4() {
		return rol_and_res4;
	}
	public void setRol_and_res4(String rol_and_res4) {
		this.rol_and_res4 = rol_and_res4;
	}
	public String getRol_and_res5() {
		return rol_and_res5;
	}
	public void setRol_and_res5(String rol_and_res5) {
		this.rol_and_res5 = rol_and_res5;
	}
	public String getRol_and_res6() {
		return rol_and_res6;
	}
	public void setRol_and_res6(String rol_and_res6) {
		this.rol_and_res6 = rol_and_res6;
	}
	public String getRol_and_res7() {
		return rol_and_res7;
	}
	public void setRol_and_res7(String rol_and_res7) {
		this.rol_and_res7 = rol_and_res7;
	}
	public String getRol_and_res8() {
		return rol_and_res8;
	}
	public void setRol_and_res8(String rol_and_res8) {
		this.rol_and_res8 = rol_and_res8;
	}
	public String getRol_and_res9() {
		return rol_and_res9;
	}
	public void setRol_and_res9(String rol_and_res9) {
		this.rol_and_res9 = rol_and_res9;
	}
	public String getRol_and_res10() {
		return rol_and_res10;
	}
	public void setRol_and_res10(String rol_and_res10) {
		this.rol_and_res10 = rol_and_res10;
	}
	public String getPro_location1() {
		return pro_location1;
	}
	public void setPro_location1(String pro_location1) {
		this.pro_location1 = pro_location1;
	}
	public String getPro_location2() {
		return pro_location2;
	}
	public void setPro_location2(String pro_location2) {
		this.pro_location2 = pro_location2;
	}
	public String getPro_location3() {
		return pro_location3;
	}
	public void setPro_location3(String pro_location3) {
		this.pro_location3 = pro_location3;
	}
	public String getPro_location4() {
		return pro_location4;
	}
	public void setPro_location4(String pro_location4) {
		this.pro_location4 = pro_location4;
	}
	public String getPro_location5() {
		return pro_location5;
	}
	public void setPro_location5(String pro_location5) {
		this.pro_location5 = pro_location5;
	}
	public String getPro_location6() {
		return pro_location6;
	}
	public void setPro_location6(String pro_location6) {
		this.pro_location6 = pro_location6;
	}
	public String getPro_location7() {
		return pro_location7;
	}
	public void setPro_location7(String pro_location7) {
		this.pro_location7 = pro_location7;
	}
	public String getPro_location8() {
		return pro_location8;
	}
	public void setPro_location8(String pro_location8) {
		this.pro_location8 = pro_location8;
	}
	public String getPro_location9() {
		return pro_location9;
	}
	public void setPro_location9(String pro_location9) {
		this.pro_location9 = pro_location9;
	}
	public String getPro_location10() {
		return pro_location10;
	}
	public void setPro_location10(String pro_location10) {
		this.pro_location10 = pro_location10;
	}
	public String getDocument_type1() {
		return document_type1;
	}
	public void setDocument_type1(String document_type1) {
		this.document_type1 = document_type1;
	}
	public String getDocument_type2() {
		return document_type2;
	}
	public void setDocument_type2(String document_type2) {
		this.document_type2 = document_type2;
	}
	public String getDocument_type3() {
		return document_type3;
	}
	public void setDocument_type3(String document_type3) {
		this.document_type3 = document_type3;
	}
	public String getDocument_type4() {
		return document_type4;
	}
	public void setDocument_type4(String document_type4) {
		this.document_type4 = document_type4;
	}
	public String getDocument_type5() {
		return document_type5;
	}
	public void setDocument_type5(String document_type5) {
		this.document_type5 = document_type5;
	}
	public String getDocument_type6() {
		return document_type6;
	}
	public void setDocument_type6(String document_type6) {
		this.document_type6 = document_type6;
	}
	public String getDocument_type7() {
		return document_type7;
	}
	public void setDocument_type7(String document_type7) {
		this.document_type7 = document_type7;
	}
	public String getDocument_type8() {
		return document_type8;
	}
	public void setDocument_type8(String document_type8) {
		this.document_type8 = document_type8;
	}
	public String getDocument_type9() {
		return document_type9;
	}
	public void setDocument_type9(String document_type9) {
		this.document_type9 = document_type9;
	}
	public String getDocument_type10() {
		return document_type10;
	}
	public void setDocument_type10(String document_type10) {
		this.document_type10 = document_type10;
	}
	public String getDocument_name1() {
		return document_name1;
	}
	public void setDocument_name1(String document_name1) {
		this.document_name1 = document_name1;
	}
	public String getDocument_name2() {
		return document_name2;
	}
	public void setDocument_name2(String document_name2) {
		this.document_name2 = document_name2;
	}
	public String getDocument_name3() {
		return document_name3;
	}
	public void setDocument_name3(String document_name3) {
		this.document_name3 = document_name3;
	}
	public String getDocument_name4() {
		return document_name4;
	}
	public void setDocument_name4(String document_name4) {
		this.document_name4 = document_name4;
	}
	public String getDocument_name5() {
		return document_name5;
	}
	public void setDocument_name5(String document_name5) {
		this.document_name5 = document_name5;
	}
	public String getDocument_name6() {
		return document_name6;
	}
	public void setDocument_name6(String document_name6) {
		this.document_name6 = document_name6;
	}
	public String getDocument_name7() {
		return document_name7;
	}
	public void setDocument_name7(String document_name7) {
		this.document_name7 = document_name7;
	}
	public String getDocument_name8() {
		return document_name8;
	}
	public void setDocument_name8(String document_name8) {
		this.document_name8 = document_name8;
	}
	public String getDocument_name9() {
		return document_name9;
	}
	public void setDocument_name9(String document_name9) {
		this.document_name9 = document_name9;
	}
	public String getDocument_name10() {
		return document_name10;
	}
	public void setDocument_name10(String document_name10) {
		this.document_name10 = document_name10;
	}
	public String getDocument_no1() {
		return document_no1;
	}
	public void setDocument_no1(String document_no1) {
		this.document_no1 = document_no1;
	}
	public String getDocument_no2() {
		return document_no2;
	}
	public void setDocument_no2(String document_no2) {
		this.document_no2 = document_no2;
	}
	public String getDocument_no3() {
		return document_no3;
	}
	public void setDocument_no3(String document_no3) {
		this.document_no3 = document_no3;
	}
	public String getDocument_no4() {
		return document_no4;
	}
	public void setDocument_no4(String document_no4) {
		this.document_no4 = document_no4;
	}
	public String getDocument_no5() {
		return document_no5;
	}
	public void setDocument_no5(String document_no5) {
		this.document_no5 = document_no5;
	}
	public String getDocument_no6() {
		return document_no6;
	}
	public void setDocument_no6(String document_no6) {
		this.document_no6 = document_no6;
	}
	public String getDocument_no7() {
		return document_no7;
	}
	public void setDocument_no7(String document_no7) {
		this.document_no7 = document_no7;
	}
	public String getDocument_no8() {
		return document_no8;
	}
	public void setDocument_no8(String document_no8) {
		this.document_no8 = document_no8;
	}
	public String getDocument_no9() {
		return document_no9;
	}
	public void setDocument_no9(String document_no9) {
		this.document_no9 = document_no9;
	}
	public String getDocument_no10() {
		return document_no10;
	}
	public void setDocument_no10(String document_no10) {
		this.document_no10 = document_no10;
	}
	public Date getIssue_date1() {
		return issue_date1;
	}
	public void setIssue_date1(Date issue_date1) {
		this.issue_date1 = issue_date1;
	}
	public Date getIssue_date2() {
		return issue_date2;
	}
	public void setIssue_date2(Date issue_date2) {
		this.issue_date2 = issue_date2;
	}
	public Date getIssue_date3() {
		return issue_date3;
	}
	public void setIssue_date3(Date issue_date3) {
		this.issue_date3 = issue_date3;
	}
	public Date getIssue_date4() {
		return issue_date4;
	}
	public void setIssue_date4(Date issue_date4) {
		this.issue_date4 = issue_date4;
	}
	public Date getIssue_date5() {
		return issue_date5;
	}
	public void setIssue_date5(Date issue_date5) {
		this.issue_date5 = issue_date5;
	}
	public Date getIssue_date6() {
		return issue_date6;
	}
	public void setIssue_date6(Date issue_date6) {
		this.issue_date6 = issue_date6;
	}
	public Date getIssue_date7() {
		return issue_date7;
	}
	public void setIssue_date7(Date issue_date7) {
		this.issue_date7 = issue_date7;
	}
	public Date getIssue_date8() {
		return issue_date8;
	}
	public void setIssue_date8(Date issue_date8) {
		this.issue_date8 = issue_date8;
	}
	public Date getIssue_date9() {
		return issue_date9;
	}
	public void setIssue_date9(Date issue_date9) {
		this.issue_date9 = issue_date9;
	}
	public Date getIssue_date10() {
		return issue_date10;
	}
	public void setIssue_date10(Date issue_date10) {
		this.issue_date10 = issue_date10;
	}
	public Date getExpiry_date1() {
		return expiry_date1;
	}
	public void setExpiry_date1(Date expiry_date1) {
		this.expiry_date1 = expiry_date1;
	}
	public Date getExpiry_date2() {
		return expiry_date2;
	}
	public void setExpiry_date2(Date expiry_date2) {
		this.expiry_date2 = expiry_date2;
	}
	public Date getExpiry_date3() {
		return expiry_date3;
	}
	public void setExpiry_date3(Date expiry_date3) {
		this.expiry_date3 = expiry_date3;
	}
	public Date getExpiry_date4() {
		return expiry_date4;
	}
	public void setExpiry_date4(Date expiry_date4) {
		this.expiry_date4 = expiry_date4;
	}
	public Date getExpiry_date5() {
		return expiry_date5;
	}
	public void setExpiry_date5(Date expiry_date5) {
		this.expiry_date5 = expiry_date5;
	}
	public Date getExpiry_date6() {
		return expiry_date6;
	}
	public void setExpiry_date6(Date expiry_date6) {
		this.expiry_date6 = expiry_date6;
	}
	public Date getExpiry_date7() {
		return expiry_date7;
	}
	public void setExpiry_date7(Date expiry_date7) {
		this.expiry_date7 = expiry_date7;
	}
	public Date getExpiry_date8() {
		return expiry_date8;
	}
	public void setExpiry_date8(Date expiry_date8) {
		this.expiry_date8 = expiry_date8;
	}
	public Date getExpiry_date9() {
		return expiry_date9;
	}
	public void setExpiry_date9(Date expiry_date9) {
		this.expiry_date9 = expiry_date9;
	}
	public Date getExpiry_date10() {
		return expiry_date10;
	}
	public void setExpiry_date10(Date expiry_date10) {
		this.expiry_date10 = expiry_date10;
	}
	public String getPlace_of_issue1() {
		return place_of_issue1;
	}
	public void setPlace_of_issue1(String place_of_issue1) {
		this.place_of_issue1 = place_of_issue1;
	}
	public String getPlace_of_issue2() {
		return place_of_issue2;
	}
	public void setPlace_of_issue2(String place_of_issue2) {
		this.place_of_issue2 = place_of_issue2;
	}
	public String getPlace_of_issue3() {
		return place_of_issue3;
	}
	public void setPlace_of_issue3(String place_of_issue3) {
		this.place_of_issue3 = place_of_issue3;
	}
	public String getPlace_of_issue4() {
		return place_of_issue4;
	}
	public void setPlace_of_issue4(String place_of_issue4) {
		this.place_of_issue4 = place_of_issue4;
	}
	public String getPlace_of_issue5() {
		return place_of_issue5;
	}
	public void setPlace_of_issue5(String place_of_issue5) {
		this.place_of_issue5 = place_of_issue5;
	}
	public String getPlace_of_issue6() {
		return place_of_issue6;
	}
	public void setPlace_of_issue6(String place_of_issue6) {
		this.place_of_issue6 = place_of_issue6;
	}
	public String getPlace_of_issue7() {
		return place_of_issue7;
	}
	public void setPlace_of_issue7(String place_of_issue7) {
		this.place_of_issue7 = place_of_issue7;
	}
	public String getPlace_of_issue8() {
		return place_of_issue8;
	}
	public void setPlace_of_issue8(String place_of_issue8) {
		this.place_of_issue8 = place_of_issue8;
	}
	public String getPlace_of_issue9() {
		return place_of_issue9;
	}
	public void setPlace_of_issue9(String place_of_issue9) {
		this.place_of_issue9 = place_of_issue9;
	}
	public String getPlace_of_issue10() {
		return place_of_issue10;
	}
	public void setPlace_of_issue10(String place_of_issue10) {
		this.place_of_issue10 = place_of_issue10;
	}
	public byte[] getDocument_upload1() {
		return document_upload1;
	}
	public void setDocument_upload1(byte[] document_upload1) {
		this.document_upload1 = document_upload1;
	}
	public byte[] getDocument_upload10() {
		return document_upload10;
	}
	public void setDocument_upload10(byte[] document_upload10) {
		this.document_upload10 = document_upload10;
	}
	public byte[] getDocument_upload2() {
		return document_upload2;
	}
	public void setDocument_upload2(byte[] document_upload2) {
		this.document_upload2 = document_upload2;
	}
	public byte[] getDocument_upload3() {
		return document_upload3;
	}
	public void setDocument_upload3(byte[] document_upload3) {
		this.document_upload3 = document_upload3;
	}
	public byte[] getDocument_upload4() {
		return document_upload4;
	}
	public void setDocument_upload4(byte[] document_upload4) {
		this.document_upload4 = document_upload4;
	}
	public byte[] getDocument_upload5() {
		return document_upload5;
	}
	public void setDocument_upload5(byte[] document_upload5) {
		this.document_upload5 = document_upload5;
	}
	public byte[] getDocument_upload6() {
		return document_upload6;
	}
	public void setDocument_upload6(byte[] document_upload6) {
		this.document_upload6 = document_upload6;
	}
	public byte[] getDocument_upload7() {
		return document_upload7;
	}
	public void setDocument_upload7(byte[] document_upload7) {
		this.document_upload7 = document_upload7;
	}
	public byte[] getDocument_upload8() {
		return document_upload8;
	}
	public void setDocument_upload8(byte[] document_upload8) {
		this.document_upload8 = document_upload8;
	}
	public byte[] getDocument_upload9() {
		return document_upload9;
	}
	public void setDocument_upload9(byte[] document_upload9) {
		this.document_upload9 = document_upload9;
	}
	public String getVerify_flg() {
		return verify_flg;
	}
	public void setVerify_flg(String verify_flg) {
		this.verify_flg = verify_flg;
	}
	public String getDocument_upload_format1() {
		return document_upload_format1;
	}
	public void setDocument_upload_format1(String document_upload_format1) {
		this.document_upload_format1 = document_upload_format1;
	}
	public String getDocument_upload_format2() {
		return document_upload_format2;
	}
	public void setDocument_upload_format2(String document_upload_format2) {
		this.document_upload_format2 = document_upload_format2;
	}
	public String getDocument_upload_format3() {
		return document_upload_format3;
	}
	public void setDocument_upload_format3(String document_upload_format3) {
		this.document_upload_format3 = document_upload_format3;
	}
	public String getDocument_upload_format4() {
		return document_upload_format4;
	}
	public void setDocument_upload_format4(String document_upload_format4) {
		this.document_upload_format4 = document_upload_format4;
	}
	public String getDocument_upload_format5() {
		return document_upload_format5;
	}
	public void setDocument_upload_format5(String document_upload_format5) {
		this.document_upload_format5 = document_upload_format5;
	}
	public String getDocument_upload_format6() {
		return document_upload_format6;
	}
	public void setDocument_upload_format6(String document_upload_format6) {
		this.document_upload_format6 = document_upload_format6;
	}
	public String getDocument_upload_format7() {
		return document_upload_format7;
	}
	public void setDocument_upload_format7(String document_upload_format7) {
		this.document_upload_format7 = document_upload_format7;
	}
	public String getDocument_upload_format8() {
		return document_upload_format8;
	}
	public void setDocument_upload_format8(String document_upload_format8) {
		this.document_upload_format8 = document_upload_format8;
	}
	public String getDocument_upload_format9() {
		return document_upload_format9;
	}
	public void setDocument_upload_format9(String document_upload_format9) {
		this.document_upload_format9 = document_upload_format9;
	}
	public String getDocument_upload_format10() {
		return document_upload_format10;
	}
	public void setDocument_upload_format10(String document_upload_format10) {
		this.document_upload_format10 = document_upload_format10;
	}
	public Employee_Onboarding_Entity(String employee_id, String employee_name, String designation, String blood_group,
			Date date_of_birth, Date date_of_joining, String gender, String marital_status, String mobile_no,
			String personal_email_id, String pan_number, String aadhar_number, String address, String location,
			String emergency_contact_person, String emergency_contact_no, String bank_name, String bank_account_no,
			String ifsc_code, Date dOR, String password, Date password_expiry_date, String login_status_flag,
			Date account_expiry_date, String disable_flag, String disable_remarks, Date disable_start_date,
			Date disable_end_date, String bornfire_mail_id, String user_remarks, String educational_qualification,
			String additional_qualification, String certificates, String skill_set, BigDecimal experience,
			String project_experience, String entry_user, String modify_user, String auth_user, Date auth_time,
			String del_flg, String modify_flg, String entity_flg, String organization_name1, String organization_name2,
			String organization_name3, String organization_name4, String organization_name5, String emp_designation1,
			String emp_designation2, String emp_designation3, String emp_designation4, String emp_designation5,
			Date emp_start_date1, Date emp_start_date2, Date emp_start_date3, Date emp_start_date4,
			Date emp_start_date5, Date emp_end_date1, Date emp_end_date2, Date emp_end_date3, Date emp_end_date4,
			Date emp_end_date5, String nature_of_employment1, String nature_of_employment2,
			String nature_of_employment3, String nature_of_employment4, String nature_of_employment5,
			String emp_rol_and_res1, String emp_rol_and_res2, String emp_rol_and_res3, String emp_rol_and_res4,
			String emp_rol_and_res5, String emp_location1, String emp_location2, String emp_location3,
			String emp_location4, String emp_location5, Date entry_time, Date modify_time, BigDecimal srl_no,
			String project_type1, String project_type2, String project_type3, String project_type4,
			String project_type5, String project_type6, String project_type7, String project_type8,
			String project_type9, String project_type10, String project_name1, String project_name2,
			String project_name3, String project_name4, String project_name5, String project_name6,
			String project_name7, String project_name8, String project_name9, String project_name10,
			Date proj_start_date1, Date proj_start_date2, Date proj_start_date3, Date proj_start_date4,
			Date proj_start_date5, Date proj_start_date6, Date proj_start_date7, Date proj_start_date8,
			Date proj_start_date9, Date proj_start_date10, Date proj_end_date1, Date proj_end_date2,
			Date proj_end_date3, Date proj_end_date4, Date proj_end_date5, Date proj_end_date6, Date proj_end_date7,
			Date proj_end_date8, Date proj_end_date9, Date proj_end_date10, String client1, String client2,
			String client3, String client4, String client5, String client6, String client7, String client8,
			String client9, String client10, String rol_and_res1, String rol_and_res2, String rol_and_res3,
			String rol_and_res4, String rol_and_res5, String rol_and_res6, String rol_and_res7, String rol_and_res8,
			String rol_and_res9, String rol_and_res10, String pro_location1, String pro_location2, String pro_location3,
			String pro_location4, String pro_location5, String pro_location6, String pro_location7,
			String pro_location8, String pro_location9, String pro_location10, String document_type1,
			String document_type2, String document_type3, String document_type4, String document_type5,
			String document_type6, String document_type7, String document_type8, String document_type9,
			String document_type10, String document_name1, String document_name2, String document_name3,
			String document_name4, String document_name5, String document_name6, String document_name7,
			String document_name8, String document_name9, String document_name10, String document_no1,
			String document_no2, String document_no3, String document_no4, String document_no5, String document_no6,
			String document_no7, String document_no8, String document_no9, String document_no10, Date issue_date1,
			Date issue_date2, Date issue_date3, Date issue_date4, Date issue_date5, Date issue_date6, Date issue_date7,
			Date issue_date8, Date issue_date9, Date issue_date10, Date expiry_date1, Date expiry_date2,
			Date expiry_date3, Date expiry_date4, Date expiry_date5, Date expiry_date6, Date expiry_date7,
			Date expiry_date8, Date expiry_date9, Date expiry_date10, String place_of_issue1, String place_of_issue2,
			String place_of_issue3, String place_of_issue4, String place_of_issue5, String place_of_issue6,
			String place_of_issue7, String place_of_issue8, String place_of_issue9, String place_of_issue10,
			byte[] document_upload1, byte[] document_upload10, byte[] document_upload2, byte[] document_upload3,
			byte[] document_upload4, byte[] document_upload5, byte[] document_upload6, byte[] document_upload7,
			byte[] document_upload8, byte[] document_upload9, String verify_flg, String document_upload_format1,
			String document_upload_format2, String document_upload_format3, String document_upload_format4,
			String document_upload_format5, String document_upload_format6, String document_upload_format7,
			String document_upload_format8, String document_upload_format9, String document_upload_format10) {
		super();
		this.employee_id = employee_id;
		this.employee_name = employee_name;
		this.designation = designation;
		this.blood_group = blood_group;
		this.date_of_birth = date_of_birth;
		this.date_of_joining = date_of_joining;
		this.gender = gender;
		this.marital_status = marital_status;
		this.mobile_no = mobile_no;
		this.personal_email_id = personal_email_id;
		this.pan_number = pan_number;
		this.aadhar_number = aadhar_number;
		this.address = address;
		this.location = location;
		this.emergency_contact_person = emergency_contact_person;
		this.emergency_contact_no = emergency_contact_no;
		this.bank_name = bank_name;
		this.bank_account_no = bank_account_no;
		this.ifsc_code = ifsc_code;
		DOR = dOR;
		this.password = password;
		this.password_expiry_date = password_expiry_date;
		this.login_status_flag = login_status_flag;
		this.account_expiry_date = account_expiry_date;
		this.disable_flag = disable_flag;
		this.disable_remarks = disable_remarks;
		this.disable_start_date = disable_start_date;
		this.disable_end_date = disable_end_date;
		this.bornfire_mail_id = bornfire_mail_id;
		this.user_remarks = user_remarks;
		this.educational_qualification = educational_qualification;
		this.additional_qualification = additional_qualification;
		this.certificates = certificates;
		this.skill_set = skill_set;
		this.experience = experience;
		this.project_experience = project_experience;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.auth_user = auth_user;
		this.auth_time = auth_time;
		this.del_flg = del_flg;
		this.modify_flg = modify_flg;
		this.entity_flg = entity_flg;
		this.organization_name1 = organization_name1;
		this.organization_name2 = organization_name2;
		this.organization_name3 = organization_name3;
		this.organization_name4 = organization_name4;
		this.organization_name5 = organization_name5;
		this.emp_designation1 = emp_designation1;
		this.emp_designation2 = emp_designation2;
		this.emp_designation3 = emp_designation3;
		this.emp_designation4 = emp_designation4;
		this.emp_designation5 = emp_designation5;
		this.emp_start_date1 = emp_start_date1;
		this.emp_start_date2 = emp_start_date2;
		this.emp_start_date3 = emp_start_date3;
		this.emp_start_date4 = emp_start_date4;
		this.emp_start_date5 = emp_start_date5;
		this.emp_end_date1 = emp_end_date1;
		this.emp_end_date2 = emp_end_date2;
		this.emp_end_date3 = emp_end_date3;
		this.emp_end_date4 = emp_end_date4;
		this.emp_end_date5 = emp_end_date5;
		this.nature_of_employment1 = nature_of_employment1;
		this.nature_of_employment2 = nature_of_employment2;
		this.nature_of_employment3 = nature_of_employment3;
		this.nature_of_employment4 = nature_of_employment4;
		this.nature_of_employment5 = nature_of_employment5;
		this.emp_rol_and_res1 = emp_rol_and_res1;
		this.emp_rol_and_res2 = emp_rol_and_res2;
		this.emp_rol_and_res3 = emp_rol_and_res3;
		this.emp_rol_and_res4 = emp_rol_and_res4;
		this.emp_rol_and_res5 = emp_rol_and_res5;
		this.emp_location1 = emp_location1;
		this.emp_location2 = emp_location2;
		this.emp_location3 = emp_location3;
		this.emp_location4 = emp_location4;
		this.emp_location5 = emp_location5;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.srl_no = srl_no;
		this.project_type1 = project_type1;
		this.project_type2 = project_type2;
		this.project_type3 = project_type3;
		this.project_type4 = project_type4;
		this.project_type5 = project_type5;
		this.project_type6 = project_type6;
		this.project_type7 = project_type7;
		this.project_type8 = project_type8;
		this.project_type9 = project_type9;
		this.project_type10 = project_type10;
		this.project_name1 = project_name1;
		this.project_name2 = project_name2;
		this.project_name3 = project_name3;
		this.project_name4 = project_name4;
		this.project_name5 = project_name5;
		this.project_name6 = project_name6;
		this.project_name7 = project_name7;
		this.project_name8 = project_name8;
		this.project_name9 = project_name9;
		this.project_name10 = project_name10;
		this.proj_start_date1 = proj_start_date1;
		this.proj_start_date2 = proj_start_date2;
		this.proj_start_date3 = proj_start_date3;
		this.proj_start_date4 = proj_start_date4;
		this.proj_start_date5 = proj_start_date5;
		this.proj_start_date6 = proj_start_date6;
		this.proj_start_date7 = proj_start_date7;
		this.proj_start_date8 = proj_start_date8;
		this.proj_start_date9 = proj_start_date9;
		this.proj_start_date10 = proj_start_date10;
		this.proj_end_date1 = proj_end_date1;
		this.proj_end_date2 = proj_end_date2;
		this.proj_end_date3 = proj_end_date3;
		this.proj_end_date4 = proj_end_date4;
		this.proj_end_date5 = proj_end_date5;
		this.proj_end_date6 = proj_end_date6;
		this.proj_end_date7 = proj_end_date7;
		this.proj_end_date8 = proj_end_date8;
		this.proj_end_date9 = proj_end_date9;
		this.proj_end_date10 = proj_end_date10;
		this.client1 = client1;
		this.client2 = client2;
		this.client3 = client3;
		this.client4 = client4;
		this.client5 = client5;
		this.client6 = client6;
		this.client7 = client7;
		this.client8 = client8;
		this.client9 = client9;
		this.client10 = client10;
		this.rol_and_res1 = rol_and_res1;
		this.rol_and_res2 = rol_and_res2;
		this.rol_and_res3 = rol_and_res3;
		this.rol_and_res4 = rol_and_res4;
		this.rol_and_res5 = rol_and_res5;
		this.rol_and_res6 = rol_and_res6;
		this.rol_and_res7 = rol_and_res7;
		this.rol_and_res8 = rol_and_res8;
		this.rol_and_res9 = rol_and_res9;
		this.rol_and_res10 = rol_and_res10;
		this.pro_location1 = pro_location1;
		this.pro_location2 = pro_location2;
		this.pro_location3 = pro_location3;
		this.pro_location4 = pro_location4;
		this.pro_location5 = pro_location5;
		this.pro_location6 = pro_location6;
		this.pro_location7 = pro_location7;
		this.pro_location8 = pro_location8;
		this.pro_location9 = pro_location9;
		this.pro_location10 = pro_location10;
		this.document_type1 = document_type1;
		this.document_type2 = document_type2;
		this.document_type3 = document_type3;
		this.document_type4 = document_type4;
		this.document_type5 = document_type5;
		this.document_type6 = document_type6;
		this.document_type7 = document_type7;
		this.document_type8 = document_type8;
		this.document_type9 = document_type9;
		this.document_type10 = document_type10;
		this.document_name1 = document_name1;
		this.document_name2 = document_name2;
		this.document_name3 = document_name3;
		this.document_name4 = document_name4;
		this.document_name5 = document_name5;
		this.document_name6 = document_name6;
		this.document_name7 = document_name7;
		this.document_name8 = document_name8;
		this.document_name9 = document_name9;
		this.document_name10 = document_name10;
		this.document_no1 = document_no1;
		this.document_no2 = document_no2;
		this.document_no3 = document_no3;
		this.document_no4 = document_no4;
		this.document_no5 = document_no5;
		this.document_no6 = document_no6;
		this.document_no7 = document_no7;
		this.document_no8 = document_no8;
		this.document_no9 = document_no9;
		this.document_no10 = document_no10;
		this.issue_date1 = issue_date1;
		this.issue_date2 = issue_date2;
		this.issue_date3 = issue_date3;
		this.issue_date4 = issue_date4;
		this.issue_date5 = issue_date5;
		this.issue_date6 = issue_date6;
		this.issue_date7 = issue_date7;
		this.issue_date8 = issue_date8;
		this.issue_date9 = issue_date9;
		this.issue_date10 = issue_date10;
		this.expiry_date1 = expiry_date1;
		this.expiry_date2 = expiry_date2;
		this.expiry_date3 = expiry_date3;
		this.expiry_date4 = expiry_date4;
		this.expiry_date5 = expiry_date5;
		this.expiry_date6 = expiry_date6;
		this.expiry_date7 = expiry_date7;
		this.expiry_date8 = expiry_date8;
		this.expiry_date9 = expiry_date9;
		this.expiry_date10 = expiry_date10;
		this.place_of_issue1 = place_of_issue1;
		this.place_of_issue2 = place_of_issue2;
		this.place_of_issue3 = place_of_issue3;
		this.place_of_issue4 = place_of_issue4;
		this.place_of_issue5 = place_of_issue5;
		this.place_of_issue6 = place_of_issue6;
		this.place_of_issue7 = place_of_issue7;
		this.place_of_issue8 = place_of_issue8;
		this.place_of_issue9 = place_of_issue9;
		this.place_of_issue10 = place_of_issue10;
		this.document_upload1 = document_upload1;
		this.document_upload10 = document_upload10;
		this.document_upload2 = document_upload2;
		this.document_upload3 = document_upload3;
		this.document_upload4 = document_upload4;
		this.document_upload5 = document_upload5;
		this.document_upload6 = document_upload6;
		this.document_upload7 = document_upload7;
		this.document_upload8 = document_upload8;
		this.document_upload9 = document_upload9;
		this.verify_flg = verify_flg;
		this.document_upload_format1 = document_upload_format1;
		this.document_upload_format2 = document_upload_format2;
		this.document_upload_format3 = document_upload_format3;
		this.document_upload_format4 = document_upload_format4;
		this.document_upload_format5 = document_upload_format5;
		this.document_upload_format6 = document_upload_format6;
		this.document_upload_format7 = document_upload_format7;
		this.document_upload_format8 = document_upload_format8;
		this.document_upload_format9 = document_upload_format9;
		this.document_upload_format10 = document_upload_format10;
	}
	public Employee_Onboarding_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Employee_Onboarding_Entity [employee_id=" + employee_id + ", employee_name=" + employee_name
				+ ", designation=" + designation + ", blood_group=" + blood_group + ", date_of_birth=" + date_of_birth
				+ ", date_of_joining=" + date_of_joining + ", gender=" + gender + ", marital_status=" + marital_status
				+ ", mobile_no=" + mobile_no + ", personal_email_id=" + personal_email_id + ", pan_number=" + pan_number
				+ ", aadhar_number=" + aadhar_number + ", address=" + address + ", location=" + location
				+ ", emergency_contact_person=" + emergency_contact_person + ", emergency_contact_no="
				+ emergency_contact_no + ", bank_name=" + bank_name + ", bank_account_no=" + bank_account_no
				+ ", ifsc_code=" + ifsc_code + ", DOR=" + DOR + ", password=" + password + ", password_expiry_date="
				+ password_expiry_date + ", login_status_flag=" + login_status_flag + ", account_expiry_date="
				+ account_expiry_date + ", disable_flag=" + disable_flag + ", disable_remarks=" + disable_remarks
				+ ", disable_start_date=" + disable_start_date + ", disable_end_date=" + disable_end_date
				+ ", bornfire_mail_id=" + bornfire_mail_id + ", user_remarks=" + user_remarks
				+ ", educational_qualification=" + educational_qualification + ", additional_qualification="
				+ additional_qualification + ", certificates=" + certificates + ", skill_set=" + skill_set
				+ ", experience=" + experience + ", project_experience=" + project_experience + ", entry_user="
				+ entry_user + ", modify_user=" + modify_user + ", auth_user=" + auth_user + ", auth_time=" + auth_time
				+ ", del_flg=" + del_flg + ", modify_flg=" + modify_flg + ", entity_flg=" + entity_flg
				+ ", organization_name1=" + organization_name1 + ", organization_name2=" + organization_name2
				+ ", organization_name3=" + organization_name3 + ", organization_name4=" + organization_name4
				+ ", organization_name5=" + organization_name5 + ", emp_designation1=" + emp_designation1
				+ ", emp_designation2=" + emp_designation2 + ", emp_designation3=" + emp_designation3
				+ ", emp_designation4=" + emp_designation4 + ", emp_designation5=" + emp_designation5
				+ ", emp_start_date1=" + emp_start_date1 + ", emp_start_date2=" + emp_start_date2 + ", emp_start_date3="
				+ emp_start_date3 + ", emp_start_date4=" + emp_start_date4 + ", emp_start_date5=" + emp_start_date5
				+ ", emp_end_date1=" + emp_end_date1 + ", emp_end_date2=" + emp_end_date2 + ", emp_end_date3="
				+ emp_end_date3 + ", emp_end_date4=" + emp_end_date4 + ", emp_end_date5=" + emp_end_date5
				+ ", nature_of_employment1=" + nature_of_employment1 + ", nature_of_employment2="
				+ nature_of_employment2 + ", nature_of_employment3=" + nature_of_employment3
				+ ", nature_of_employment4=" + nature_of_employment4 + ", nature_of_employment5="
				+ nature_of_employment5 + ", emp_rol_and_res1=" + emp_rol_and_res1 + ", emp_rol_and_res2="
				+ emp_rol_and_res2 + ", emp_rol_and_res3=" + emp_rol_and_res3 + ", emp_rol_and_res4=" + emp_rol_and_res4
				+ ", emp_rol_and_res5=" + emp_rol_and_res5 + ", emp_location1=" + emp_location1 + ", emp_location2="
				+ emp_location2 + ", emp_location3=" + emp_location3 + ", emp_location4=" + emp_location4
				+ ", emp_location5=" + emp_location5 + ", entry_time=" + entry_time + ", modify_time=" + modify_time
				+ ", srl_no=" + srl_no + ", project_type1=" + project_type1 + ", project_type2=" + project_type2
				+ ", project_type3=" + project_type3 + ", project_type4=" + project_type4 + ", project_type5="
				+ project_type5 + ", project_type6=" + project_type6 + ", project_type7=" + project_type7
				+ ", project_type8=" + project_type8 + ", project_type9=" + project_type9 + ", project_type10="
				+ project_type10 + ", project_name1=" + project_name1 + ", project_name2=" + project_name2
				+ ", project_name3=" + project_name3 + ", project_name4=" + project_name4 + ", project_name5="
				+ project_name5 + ", project_name6=" + project_name6 + ", project_name7=" + project_name7
				+ ", project_name8=" + project_name8 + ", project_name9=" + project_name9 + ", project_name10="
				+ project_name10 + ", proj_start_date1=" + proj_start_date1 + ", proj_start_date2=" + proj_start_date2
				+ ", proj_start_date3=" + proj_start_date3 + ", proj_start_date4=" + proj_start_date4
				+ ", proj_start_date5=" + proj_start_date5 + ", proj_start_date6=" + proj_start_date6
				+ ", proj_start_date7=" + proj_start_date7 + ", proj_start_date8=" + proj_start_date8
				+ ", proj_start_date9=" + proj_start_date9 + ", proj_start_date10=" + proj_start_date10
				+ ", proj_end_date1=" + proj_end_date1 + ", proj_end_date2=" + proj_end_date2 + ", proj_end_date3="
				+ proj_end_date3 + ", proj_end_date4=" + proj_end_date4 + ", proj_end_date5=" + proj_end_date5
				+ ", proj_end_date6=" + proj_end_date6 + ", proj_end_date7=" + proj_end_date7 + ", proj_end_date8="
				+ proj_end_date8 + ", proj_end_date9=" + proj_end_date9 + ", proj_end_date10=" + proj_end_date10
				+ ", client1=" + client1 + ", client2=" + client2 + ", client3=" + client3 + ", client4=" + client4
				+ ", client5=" + client5 + ", client6=" + client6 + ", client7=" + client7 + ", client8=" + client8
				+ ", client9=" + client9 + ", client10=" + client10 + ", rol_and_res1=" + rol_and_res1
				+ ", rol_and_res2=" + rol_and_res2 + ", rol_and_res3=" + rol_and_res3 + ", rol_and_res4=" + rol_and_res4
				+ ", rol_and_res5=" + rol_and_res5 + ", rol_and_res6=" + rol_and_res6 + ", rol_and_res7=" + rol_and_res7
				+ ", rol_and_res8=" + rol_and_res8 + ", rol_and_res9=" + rol_and_res9 + ", rol_and_res10="
				+ rol_and_res10 + ", pro_location1=" + pro_location1 + ", pro_location2=" + pro_location2
				+ ", pro_location3=" + pro_location3 + ", pro_location4=" + pro_location4 + ", pro_location5="
				+ pro_location5 + ", pro_location6=" + pro_location6 + ", pro_location7=" + pro_location7
				+ ", pro_location8=" + pro_location8 + ", pro_location9=" + pro_location9 + ", pro_location10="
				+ pro_location10 + ", document_type1=" + document_type1 + ", document_type2=" + document_type2
				+ ", document_type3=" + document_type3 + ", document_type4=" + document_type4 + ", document_type5="
				+ document_type5 + ", document_type6=" + document_type6 + ", document_type7=" + document_type7
				+ ", document_type8=" + document_type8 + ", document_type9=" + document_type9 + ", document_type10="
				+ document_type10 + ", document_name1=" + document_name1 + ", document_name2=" + document_name2
				+ ", document_name3=" + document_name3 + ", document_name4=" + document_name4 + ", document_name5="
				+ document_name5 + ", document_name6=" + document_name6 + ", document_name7=" + document_name7
				+ ", document_name8=" + document_name8 + ", document_name9=" + document_name9 + ", document_name10="
				+ document_name10 + ", document_no1=" + document_no1 + ", document_no2=" + document_no2
				+ ", document_no3=" + document_no3 + ", document_no4=" + document_no4 + ", document_no5=" + document_no5
				+ ", document_no6=" + document_no6 + ", document_no7=" + document_no7 + ", document_no8=" + document_no8
				+ ", document_no9=" + document_no9 + ", document_no10=" + document_no10 + ", issue_date1=" + issue_date1
				+ ", issue_date2=" + issue_date2 + ", issue_date3=" + issue_date3 + ", issue_date4=" + issue_date4
				+ ", issue_date5=" + issue_date5 + ", issue_date6=" + issue_date6 + ", issue_date7=" + issue_date7
				+ ", issue_date8=" + issue_date8 + ", issue_date9=" + issue_date9 + ", issue_date10=" + issue_date10
				+ ", expiry_date1=" + expiry_date1 + ", expiry_date2=" + expiry_date2 + ", expiry_date3=" + expiry_date3
				+ ", expiry_date4=" + expiry_date4 + ", expiry_date5=" + expiry_date5 + ", expiry_date6=" + expiry_date6
				+ ", expiry_date7=" + expiry_date7 + ", expiry_date8=" + expiry_date8 + ", expiry_date9=" + expiry_date9
				+ ", expiry_date10=" + expiry_date10 + ", place_of_issue1=" + place_of_issue1 + ", place_of_issue2="
				+ place_of_issue2 + ", place_of_issue3=" + place_of_issue3 + ", place_of_issue4=" + place_of_issue4
				+ ", place_of_issue5=" + place_of_issue5 + ", place_of_issue6=" + place_of_issue6 + ", place_of_issue7="
				+ place_of_issue7 + ", place_of_issue8=" + place_of_issue8 + ", place_of_issue9=" + place_of_issue9
				+ ", place_of_issue10=" + place_of_issue10 + ", document_upload1=" + Arrays.toString(document_upload1)
				+ ", document_upload10=" + Arrays.toString(document_upload10) + ", document_upload2="
				+ Arrays.toString(document_upload2) + ", document_upload3=" + Arrays.toString(document_upload3)
				+ ", document_upload4=" + Arrays.toString(document_upload4) + ", document_upload5="
				+ Arrays.toString(document_upload5) + ", document_upload6=" + Arrays.toString(document_upload6)
				+ ", document_upload7=" + Arrays.toString(document_upload7) + ", document_upload8="
				+ Arrays.toString(document_upload8) + ", document_upload9=" + Arrays.toString(document_upload9)
				+ ", verify_flg=" + verify_flg + ", document_upload_format1=" + document_upload_format1
				+ ", document_upload_format2=" + document_upload_format2 + ", document_upload_format3="
				+ document_upload_format3 + ", document_upload_format4=" + document_upload_format4
				+ ", document_upload_format5=" + document_upload_format5 + ", document_upload_format6="
				+ document_upload_format6 + ", document_upload_format7=" + document_upload_format7
				+ ", document_upload_format8=" + document_upload_format8 + ", document_upload_format9="
				+ document_upload_format9 + ", document_upload_format10=" + document_upload_format10 + "]";
	}
	
}