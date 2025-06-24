package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "ORGANIZATION_DETAILS")
public class BTMAdminOrganizationMaster {

	private String orgn_name;
	private String short_name;
	@Id
	private String regn_no;
	private String gst_ref;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date dor;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date doc;
	private String pan_ref;
	private String tan_ref;
	private String website;
	private String email;
	private String land_line_1;
	private String land_line_2;
	private String mobile;
	private String alt_mobile;
	
	
	private String loc_addr1;
	private String loc_addr2;
	private String loc_city;
	private String loc_state;
	private String loc_country;
	private String loc_postal_code;
	
	private String regn_addr1;
	private String regn_addr2;
	private String regn_city;
	private String regn_state;
	private String regn_country;
	private String regn_postal_code;
	
	private String adm_addr1;
	private String adm_addr2;
	private String adm_city;
	private String adm_state;
	private String adm_country;
	private String adm_postal_code;
	
	private BigDecimal no_of_emp;
	
	private BigDecimal life_of_pw;
	private BigDecimal casual_leave;
	private BigDecimal sick_leave;
	private BigDecimal earned_leave;
	private BigDecimal spl_leave;
	private String free_text_1;
	private String free_text_2;
	private String free_text_3;
	private String free_text_4;
	private String free_text_5;
	private String free_text_6;
	private String free_text_7;
	private String free_text_8;
	private String entry_user;
	private String modify_user;
	private String auth_user;
	private Date entry_time;
	private Date modify_time;
	private Date auth_time;
	private String del_flg;
	private String entity_flg;
	private String modify_flg;

	public String getOrgn_name() {
		return orgn_name;
	}

	public void setOrgn_name(String orgn_name) {
		this.orgn_name = orgn_name;
	}

	public String getShort_name() {
		return short_name;
	}

	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}

	public String getRegn_no() {
		return regn_no;
	}

	public void setRegn_no(String regn_no) {
		this.regn_no = regn_no;
	}

	public String getGst_ref() {
		return gst_ref;
	}

	public void setGst_ref(String gst_ref) {
		this.gst_ref = gst_ref;
	}

	public Date getDor() {
		return dor;
	}

	public void setDor(Date dor) {
		this.dor = dor;
	}

	public Date getDoc() {
		return doc;
	}

	public void setDoc(Date doc) {
		this.doc = doc;
	}

	public String getPan_ref() {
		return pan_ref;
	}

	public void setPan_ref(String pan_ref) {
		this.pan_ref = pan_ref;
	}

	public String getTan_ref() {
		return tan_ref;
	}

	public void setTan_ref(String tan_ref) {
		this.tan_ref = tan_ref;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLand_line_1() {
		return land_line_1;
	}

	public void setLand_line_1(String land_line_1) {
		this.land_line_1 = land_line_1;
	}

	public String getLand_line_2() {
		return land_line_2;
	}

	public void setLand_line_2(String land_line_2) {
		this.land_line_2 = land_line_2;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAlt_mobile() {
		return alt_mobile;
	}

	public void setAlt_mobile(String alt_mobile) {
		this.alt_mobile = alt_mobile;
	}

	public String getLoc_addr1() {
		return loc_addr1;
	}

	public void setLoc_addr1(String loc_addr1) {
		this.loc_addr1 = loc_addr1;
	}

	public String getLoc_addr2() {
		return loc_addr2;
	}

	public void setLoc_addr2(String loc_addr2) {
		this.loc_addr2 = loc_addr2;
	}

	public String getLoc_city() {
		return loc_city;
	}

	public void setLoc_city(String loc_city) {
		this.loc_city = loc_city;
	}

	public String getLoc_state() {
		return loc_state;
	}

	public void setLoc_state(String loc_state) {
		this.loc_state = loc_state;
	}

	public String getLoc_country() {
		return loc_country;
	}

	public void setLoc_country(String loc_country) {
		this.loc_country = loc_country;
	}

	public String getLoc_postal_code() {
		return loc_postal_code;
	}

	public void setLoc_postal_code(String loc_postal_code) {
		this.loc_postal_code = loc_postal_code;
	}

	public String getRegn_addr1() {
		return regn_addr1;
	}

	public void setRegn_addr1(String regn_addr1) {
		this.regn_addr1 = regn_addr1;
	}

	public String getRegn_addr2() {
		return regn_addr2;
	}

	public void setRegn_addr2(String regn_addr2) {
		this.regn_addr2 = regn_addr2;
	}

	public String getRegn_city() {
		return regn_city;
	}

	public void setRegn_city(String regn_city) {
		this.regn_city = regn_city;
	}

	public String getRegn_state() {
		return regn_state;
	}

	public void setRegn_state(String regn_state) {
		this.regn_state = regn_state;
	}

	public String getRegn_country() {
		return regn_country;
	}

	public void setRegn_country(String regn_country) {
		this.regn_country = regn_country;
	}

	public String getRegn_postal_code() {
		return regn_postal_code;
	}

	public void setRegn_postal_code(String regn_postal_code) {
		this.regn_postal_code = regn_postal_code;
	}

	public String getAdm_addr1() {
		return adm_addr1;
	}

	public void setAdm_addr1(String adm_addr1) {
		this.adm_addr1 = adm_addr1;
	}

	public String getAdm_addr2() {
		return adm_addr2;
	}

	public void setAdm_addr2(String adm_addr2) {
		this.adm_addr2 = adm_addr2;
	}

	public String getAdm_city() {
		return adm_city;
	}

	public void setAdm_city(String adm_city) {
		this.adm_city = adm_city;
	}

	public String getAdm_state() {
		return adm_state;
	}

	public void setAdm_state(String adm_state) {
		this.adm_state = adm_state;
	}

	public String getAdm_country() {
		return adm_country;
	}

	public void setAdm_country(String adm_country) {
		this.adm_country = adm_country;
	}

	public String getAdm_postal_code() {
		return adm_postal_code;
	}

	public void setAdm_postal_code(String adm_postal_code) {
		this.adm_postal_code = adm_postal_code;
	}

	public BigDecimal getNo_of_emp() {
		return no_of_emp;
	}

	public void setNo_of_emp(BigDecimal no_of_emp) {
		this.no_of_emp = no_of_emp;
	}

	public BigDecimal getLife_of_pw() {
		return life_of_pw;
	}

	public void setLife_of_pw(BigDecimal life_of_pw) {
		this.life_of_pw = life_of_pw;
	}

	public BigDecimal getCasual_leave() {
		return casual_leave;
	}

	public void setCasual_leave(BigDecimal casual_leave) {
		this.casual_leave = casual_leave;
	}

	public BigDecimal getSick_leave() {
		return sick_leave;
	}

	public void setSick_leave(BigDecimal sick_leave) {
		this.sick_leave = sick_leave;
	}

	public BigDecimal getEarned_leave() {
		return earned_leave;
	}

	public void setEarned_leave(BigDecimal earned_leave) {
		this.earned_leave = earned_leave;
	}

	public BigDecimal getSpl_leave() {
		return spl_leave;
	}

	public void setSpl_leave(BigDecimal spl_leave) {
		this.spl_leave = spl_leave;
	}

	public String getFree_text_1() {
		return free_text_1;
	}

	public void setFree_text_1(String free_text_1) {
		this.free_text_1 = free_text_1;
	}

	public String getFree_text_2() {
		return free_text_2;
	}

	public void setFree_text_2(String free_text_2) {
		this.free_text_2 = free_text_2;
	}

	public String getFree_text_3() {
		return free_text_3;
	}

	public void setFree_text_3(String free_text_3) {
		this.free_text_3 = free_text_3;
	}

	public String getFree_text_4() {
		return free_text_4;
	}

	public void setFree_text_4(String free_text_4) {
		this.free_text_4 = free_text_4;
	}

	public String getFree_text_5() {
		return free_text_5;
	}

	public void setFree_text_5(String free_text_5) {
		this.free_text_5 = free_text_5;
	}

	public String getFree_text_6() {
		return free_text_6;
	}

	public void setFree_text_6(String free_text_6) {
		this.free_text_6 = free_text_6;
	}

	public String getFree_text_7() {
		return free_text_7;
	}

	public void setFree_text_7(String free_text_7) {
		this.free_text_7 = free_text_7;
	}

	public String getFree_text_8() {
		return free_text_8;
	}

	public void setFree_text_8(String free_text_8) {
		this.free_text_8 = free_text_8;
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

	public String getEntity_flg() {
		return entity_flg;
	}

	public void setEntity_flg(String entity_flg) {
		this.entity_flg = entity_flg;
	}

	public String getModify_flg() {
		return modify_flg;
	}

	public void setModify_flg(String modify_flg) {
		this.modify_flg = modify_flg;
	}

	public BTMAdminOrganizationMaster(String orgn_name, String short_name, String regn_no, String gst_ref, Date dor,
			Date doc, String pan_ref, String tan_ref, String website, String email, String land_line_1,
			String land_line_2, String mobile, String alt_mobile, String loc_addr1, String loc_addr2, String loc_city,
			String loc_state, String loc_country, String loc_postal_code, String regn_addr1, String regn_addr2,
			String regn_city, String regn_state, String regn_country, String regn_postal_code, String adm_addr1,
			String adm_addr2, String adm_city, String adm_state, String adm_country, String adm_postal_code,
			BigDecimal no_of_emp, BigDecimal life_of_pw, BigDecimal casual_leave, BigDecimal sick_leave,
			BigDecimal earned_leave, BigDecimal spl_leave, String free_text_1, String free_text_2, String free_text_3,
			String free_text_4, String free_text_5, String free_text_6, String free_text_7, String free_text_8,
			String entry_user, String modify_user, String auth_user, Date entry_time, Date modify_time, Date auth_time,
			String del_flg, String entity_flg, String modify_flg) {
		super();
		this.orgn_name = orgn_name;
		this.short_name = short_name;
		this.regn_no = regn_no;
		this.gst_ref = gst_ref;
		this.dor = dor;
		this.doc = doc;
		this.pan_ref = pan_ref;
		this.tan_ref = tan_ref;
		this.website = website;
		this.email = email;
		this.land_line_1 = land_line_1;
		this.land_line_2 = land_line_2;
		this.mobile = mobile;
		this.alt_mobile = alt_mobile;
		this.loc_addr1 = loc_addr1;
		this.loc_addr2 = loc_addr2;
		this.loc_city = loc_city;
		this.loc_state = loc_state;
		this.loc_country = loc_country;
		this.loc_postal_code = loc_postal_code;
		this.regn_addr1 = regn_addr1;
		this.regn_addr2 = regn_addr2;
		this.regn_city = regn_city;
		this.regn_state = regn_state;
		this.regn_country = regn_country;
		this.regn_postal_code = regn_postal_code;
		this.adm_addr1 = adm_addr1;
		this.adm_addr2 = adm_addr2;
		this.adm_city = adm_city;
		this.adm_state = adm_state;
		this.adm_country = adm_country;
		this.adm_postal_code = adm_postal_code;
		this.no_of_emp = no_of_emp;
		this.life_of_pw = life_of_pw;
		this.casual_leave = casual_leave;
		this.sick_leave = sick_leave;
		this.earned_leave = earned_leave;
		this.spl_leave = spl_leave;
		this.free_text_1 = free_text_1;
		this.free_text_2 = free_text_2;
		this.free_text_3 = free_text_3;
		this.free_text_4 = free_text_4;
		this.free_text_5 = free_text_5;
		this.free_text_6 = free_text_6;
		this.free_text_7 = free_text_7;
		this.free_text_8 = free_text_8;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.auth_user = auth_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.auth_time = auth_time;
		this.del_flg = del_flg;
		this.entity_flg = entity_flg;
		this.modify_flg = modify_flg;
	}

	public BTMAdminOrganizationMaster() {
		super();
		// TODO Auto-generated constructor stub
	}

}
