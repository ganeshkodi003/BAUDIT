package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "PLACEMENT_RESOURCE_MASTER")
public class PlacementResourceMaster {

	private String res_group;
	private String group_desc;
	@Id
	private String emp_id;
	private String emp_desig;
	private String emp_name;
	private String nick_name;
	private String father_spouse_name;
	private String father_spouse_mobile;
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date date_of_birth;
	@DateTimeFormat(pattern="dd-MM-yyyy") 
	private Date date_of_joining;
	private String placement_id;
	private String placement_flg;
	private String pf_flg;
	private String esi_flg;
	//private String bvg_flg;
	//private String pt_flg;
	private String aadhar_card_no;
	private String parncard_no;
	private String mobile_no;
	private String email_id;
	private String addr_1;
	private String addr_2;
	private String city;
	private String state;
	private BigDecimal pincode;
	private String country;
	private String qualification;
	private BigDecimal experience;
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date last_billing_date;
	private String remarks;
	private String entity_flg;
	private String del_flg;
	private String modify_flg;
	private String entry_user;
	private String modify_user;
	private String verify_user;
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date entry_time;
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date modify_time;
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date verify_time;

	public String getRes_group() {
		return res_group;
	}

	public void setRes_group(String res_group) {
		this.res_group = res_group;
	}

	public String getGroup_desc() {
		return group_desc;
	}

	public void setGroup_desc(String group_desc) {
		this.group_desc = group_desc;
	}

	public String getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}

	public String getEmp_desig() {
		return emp_desig;
	}

	public void setEmp_desig(String emp_desig) {
		this.emp_desig = emp_desig;
	}

	public String getEmp_name() {
		return emp_name;
	}

	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getFather_spouse_name() {
		return father_spouse_name;
	}

	public void setFather_spouse_name(String father_spouse_name) {
		this.father_spouse_name = father_spouse_name;
	}

	public String getFather_spouse_mobile() {
		return father_spouse_mobile;
	}

	public void setFather_spouse_mobile(String father_spouse_mobile) {
		this.father_spouse_mobile = father_spouse_mobile;
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

	public String getPlacement_id() {
		return placement_id;
	}

	public void setPlacement_id(String placement_id) {
		this.placement_id = placement_id;
	}

	public String getPlacement_flg() {
		return placement_flg;
	}

	public void setPlacement_flg(String placement_flg) {
		this.placement_flg = placement_flg;
	}

	public String getPf_flg() {
		return pf_flg;
	}

	public void setPf_flg(String pf_flg) {
		this.pf_flg = pf_flg;
	}

	public String getEsi_flg() {
		return esi_flg;
	}

	public void setEsi_flg(String esi_flg) {
		this.esi_flg = esi_flg;
	}

	public String getAadhar_card_no() {
		return aadhar_card_no;
	}

	public void setAadhar_card_no(String aadhar_card_no) {
		this.aadhar_card_no = aadhar_card_no;
	}

	public String getParncard_no() {
		return parncard_no;
	}

	public void setParncard_no(String parncard_no) {
		this.parncard_no = parncard_no;
	}

	public String getMobile_no() {
		return mobile_no;
	}

	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getAddr_1() {
		return addr_1;
	}

	public void setAddr_1(String addr_1) {
		this.addr_1 = addr_1;
	}

	public String getAddr_2() {
		return addr_2;
	}

	public void setAddr_2(String addr_2) {
		this.addr_2 = addr_2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public BigDecimal getPincode() {
		return pincode;
	}

	public void setPincode(BigDecimal pincode) {
		this.pincode = pincode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public BigDecimal getExperience() {
		return experience;
	}

	public void setExperience(BigDecimal experience) {
		this.experience = experience;
	}

	public Date getLast_billing_date() {
		return last_billing_date;
	}

	public void setLast_billing_date(Date last_billing_date) {
		this.last_billing_date = last_billing_date;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getEntity_flg() {
		return entity_flg;
	}

	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
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

	public String getVerify_user() {
		return verify_user;
	}

	public void setVerify_user(String verify_user) {
		this.verify_user = verify_user;
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

	public Date getVerify_time() {
		return verify_time;
	}

	public void setVerify_time(Date verify_time) {
		this.verify_time = verify_time;
	}

	public PlacementResourceMaster(String res_group, String group_desc, String emp_id, String emp_desig,
			String emp_name, String nick_name, String father_spouse_name, String father_spouse_mobile,
			Date date_of_birth, Date date_of_joining, String placement_id, String placement_flg, String pf_flg,
			String esi_flg, String aadhar_card_no, String parncard_no, String mobile_no, String email_id, String addr_1,
			String addr_2, String city, String state, BigDecimal pincode, String country, String qualification,
			BigDecimal experience, Date last_billing_date, String remarks, String entity_flg, String del_flg,
			String modify_flg, String entry_user, String modify_user, String verify_user, Date entry_time,
			Date modify_time, Date verify_time) {
		super();
		this.res_group = res_group;
		this.group_desc = group_desc;
		this.emp_id = emp_id;
		this.emp_desig = emp_desig;
		this.emp_name = emp_name;
		this.nick_name = nick_name;
		this.father_spouse_name = father_spouse_name;
		this.father_spouse_mobile = father_spouse_mobile;
		this.date_of_birth = date_of_birth;
		this.date_of_joining = date_of_joining;
		this.placement_id = placement_id;
		this.placement_flg = placement_flg;
		this.pf_flg = pf_flg;
		this.esi_flg = esi_flg;
		this.aadhar_card_no = aadhar_card_no;
		this.parncard_no = parncard_no;
		this.mobile_no = mobile_no;
		this.email_id = email_id;
		this.addr_1 = addr_1;
		this.addr_2 = addr_2;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.country = country;
		this.qualification = qualification;
		this.experience = experience;
		this.last_billing_date = last_billing_date;
		this.remarks = remarks;
		this.entity_flg = entity_flg;
		this.del_flg = del_flg;
		this.modify_flg = modify_flg;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.verify_user = verify_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.verify_time = verify_time;
	}

	public PlacementResourceMaster() {
		super();
		// TODO Auto-generated constructor stub
	}

}
