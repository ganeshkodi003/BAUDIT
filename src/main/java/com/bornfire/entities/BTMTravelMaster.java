package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "TRAVEL_MASTER")
public class BTMTravelMaster {
	
	@Id
	private String tra_ref;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date tra_req_date;
	private String ass_id;
	private String ass_name;
	private String desig;
	private String groups;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date dob;
	private String marital_status;
	private BigDecimal mobile;
	private String email;
	private String passport;
	private String pan;
	private String address;
	private String remarks;
	private String prj_id;
	private String client_id;
	private String prj_destination;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date tra_start_date;
	private BigDecimal tra_period;
	private String adv_req_flg;
	private String adv_crncy;
	private BigDecimal adv_amt;
	
	private String tra_leg_1;
	private String tra_leg_2;
	private String tra_leg_3;
	private String tra_leg_4;
	private String tra_leg_5;
	private String tra_leg_6;
	private String tra_leg_7;
	private String tra_leg_8;
	private String tra_leg_9;
	private String tra_leg_10;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date adv_req_date;
	private BigDecimal adv_req_amt;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date adv_app_date;
	private BigDecimal adv_app_amt;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date adv_dis_date;
	private BigDecimal adv_dis_amt;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date tra_claim_date;
	private BigDecimal tra_claim_amt;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date tra_claim_app_date;
	private BigDecimal tra_claim_app_amt;
	private BigDecimal amt_short_excess;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date date_of_adj;
	private String tra_status;
	
	private String entry_user;
	private String modify_user;
	private String auth_user;
	private Date entry_time;
	private Date modify_time;
	private Date auth_time;
	private String del_flg;
	private String entity_flg;
	private String modify_flg;
	
	private String booking_req;
	private String booking_mode;
	private String device;

	public String getTra_ref() {
		return tra_ref;
	}

	public void setTra_ref(String tra_ref) {
		this.tra_ref = tra_ref;
	}

	public Date getTra_req_date() {
		return tra_req_date;
	}

	public void setTra_req_date(Date tra_req_date) {
		this.tra_req_date = tra_req_date;
	}

	public String getAss_id() {
		return ass_id;
	}

	public void setAss_id(String ass_id) {
		this.ass_id = ass_id;
	}

	public String getAss_name() {
		return ass_name;
	}

	public void setAss_name(String ass_name) {
		this.ass_name = ass_name;
	}

	public String getDesig() {
		return desig;
	}

	public void setDesig(String desig) {
		this.desig = desig;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMarital_status() {
		return marital_status;
	}

	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}

	public BigDecimal getMobile() {
		return mobile;
	}

	public void setMobile(BigDecimal mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPrj_id() {
		return prj_id;
	}

	public void setPrj_id(String prj_id) {
		this.prj_id = prj_id;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}

	public String getPrj_destination() {
		return prj_destination;
	}

	public void setPrj_destination(String prj_destination) {
		this.prj_destination = prj_destination;
	}

	public Date getTra_start_date() {
		return tra_start_date;
	}

	public void setTra_start_date(Date tra_start_date) {
		this.tra_start_date = tra_start_date;
	}

	public BigDecimal getTra_period() {
		return tra_period;
	}

	public void setTra_period(BigDecimal tra_period) {
		this.tra_period = tra_period;
	}

	public String getAdv_req_flg() {
		return adv_req_flg;
	}

	public void setAdv_req_flg(String adv_req_flg) {
		this.adv_req_flg = adv_req_flg;
	}

	public String getAdv_crncy() {
		return adv_crncy;
	}

	public void setAdv_crncy(String adv_crncy) {
		this.adv_crncy = adv_crncy;
	}

	public BigDecimal getAdv_amt() {
		return adv_amt;
	}

	public void setAdv_amt(BigDecimal adv_amt) {
		this.adv_amt = adv_amt;
	}

	public String getTra_leg_1() {
		return tra_leg_1;
	}

	public void setTra_leg_1(String tra_leg_1) {
		this.tra_leg_1 = tra_leg_1;
	}

	public String getTra_leg_2() {
		return tra_leg_2;
	}

	public void setTra_leg_2(String tra_leg_2) {
		this.tra_leg_2 = tra_leg_2;
	}

	public String getTra_leg_3() {
		return tra_leg_3;
	}

	public void setTra_leg_3(String tra_leg_3) {
		this.tra_leg_3 = tra_leg_3;
	}

	public String getTra_leg_4() {
		return tra_leg_4;
	}

	public void setTra_leg_4(String tra_leg_4) {
		this.tra_leg_4 = tra_leg_4;
	}

	public String getTra_leg_5() {
		return tra_leg_5;
	}

	public void setTra_leg_5(String tra_leg_5) {
		this.tra_leg_5 = tra_leg_5;
	}

	public String getTra_leg_6() {
		return tra_leg_6;
	}

	public void setTra_leg_6(String tra_leg_6) {
		this.tra_leg_6 = tra_leg_6;
	}

	public String getTra_leg_7() {
		return tra_leg_7;
	}

	public void setTra_leg_7(String tra_leg_7) {
		this.tra_leg_7 = tra_leg_7;
	}

	public String getTra_leg_8() {
		return tra_leg_8;
	}

	public void setTra_leg_8(String tra_leg_8) {
		this.tra_leg_8 = tra_leg_8;
	}

	public String getTra_leg_9() {
		return tra_leg_9;
	}

	public void setTra_leg_9(String tra_leg_9) {
		this.tra_leg_9 = tra_leg_9;
	}

	public String getTra_leg_10() {
		return tra_leg_10;
	}

	public void setTra_leg_10(String tra_leg_10) {
		this.tra_leg_10 = tra_leg_10;
	}

	public Date getAdv_req_date() {
		return adv_req_date;
	}

	public void setAdv_req_date(Date adv_req_date) {
		this.adv_req_date = adv_req_date;
	}

	public BigDecimal getAdv_req_amt() {
		return adv_req_amt;
	}

	public void setAdv_req_amt(BigDecimal adv_req_amt) {
		this.adv_req_amt = adv_req_amt;
	}

	public Date getAdv_app_date() {
		return adv_app_date;
	}

	public void setAdv_app_date(Date adv_app_date) {
		this.adv_app_date = adv_app_date;
	}

	public BigDecimal getAdv_app_amt() {
		return adv_app_amt;
	}

	public void setAdv_app_amt(BigDecimal adv_app_amt) {
		this.adv_app_amt = adv_app_amt;
	}

	public Date getAdv_dis_date() {
		return adv_dis_date;
	}

	public void setAdv_dis_date(Date adv_dis_date) {
		this.adv_dis_date = adv_dis_date;
	}

	public BigDecimal getAdv_dis_amt() {
		return adv_dis_amt;
	}

	public void setAdv_dis_amt(BigDecimal adv_dis_amt) {
		this.adv_dis_amt = adv_dis_amt;
	}

	public Date getTra_claim_date() {
		return tra_claim_date;
	}

	public void setTra_claim_date(Date tra_claim_date) {
		this.tra_claim_date = tra_claim_date;
	}

	public BigDecimal getTra_claim_amt() {
		return tra_claim_amt;
	}

	public void setTra_claim_amt(BigDecimal tra_claim_amt) {
		this.tra_claim_amt = tra_claim_amt;
	}

	public Date getTra_claim_app_date() {
		return tra_claim_app_date;
	}

	public void setTra_claim_app_date(Date tra_claim_app_date) {
		this.tra_claim_app_date = tra_claim_app_date;
	}

	public BigDecimal getTra_claim_app_amt() {
		return tra_claim_app_amt;
	}

	public void setTra_claim_app_amt(BigDecimal tra_claim_app_amt) {
		this.tra_claim_app_amt = tra_claim_app_amt;
	}

	public BigDecimal getAmt_short_excess() {
		return amt_short_excess;
	}

	public void setAmt_short_excess(BigDecimal amt_short_excess) {
		this.amt_short_excess = amt_short_excess;
	}

	public Date getDate_of_adj() {
		return date_of_adj;
	}

	public void setDate_of_adj(Date date_of_adj) {
		this.date_of_adj = date_of_adj;
	}

	public String getTra_status() {
		return tra_status;
	}

	public void setTra_status(String tra_status) {
		this.tra_status = tra_status;
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

	public String getBooking_req() {
		return booking_req;
	}

	public void setBooking_req(String booking_req) {
		this.booking_req = booking_req;
	}

	public String getBooking_mode() {
		return booking_mode;
	}

	public void setBooking_mode(String booking_mode) {
		this.booking_mode = booking_mode;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public BTMTravelMaster(String tra_ref, Date tra_req_date, String ass_id, String ass_name, String desig,
			String groups, Date dob, String marital_status, BigDecimal mobile, String email, String passport,
			String pan, String address, String remarks, String prj_id, String client_id, String prj_destination,
			Date tra_start_date, BigDecimal tra_period, String adv_req_flg, String adv_crncy, BigDecimal adv_amt,
			String tra_leg_1, String tra_leg_2, String tra_leg_3, String tra_leg_4, String tra_leg_5, String tra_leg_6,
			String tra_leg_7, String tra_leg_8, String tra_leg_9, String tra_leg_10, Date adv_req_date,
			BigDecimal adv_req_amt, Date adv_app_date, BigDecimal adv_app_amt, Date adv_dis_date,
			BigDecimal adv_dis_amt, Date tra_claim_date, BigDecimal tra_claim_amt, Date tra_claim_app_date,
			BigDecimal tra_claim_app_amt, BigDecimal amt_short_excess, Date date_of_adj, String tra_status,
			String entry_user, String modify_user, String auth_user, Date entry_time, Date modify_time, Date auth_time,
			String del_flg, String entity_flg, String modify_flg, String booking_req, String booking_mode,
			String device) {
		super();
		this.tra_ref = tra_ref;
		this.tra_req_date = tra_req_date;
		this.ass_id = ass_id;
		this.ass_name = ass_name;
		this.desig = desig;
		this.groups = groups;
		this.dob = dob;
		this.marital_status = marital_status;
		this.mobile = mobile;
		this.email = email;
		this.passport = passport;
		this.pan = pan;
		this.address = address;
		this.remarks = remarks;
		this.prj_id = prj_id;
		this.client_id = client_id;
		this.prj_destination = prj_destination;
		this.tra_start_date = tra_start_date;
		this.tra_period = tra_period;
		this.adv_req_flg = adv_req_flg;
		this.adv_crncy = adv_crncy;
		this.adv_amt = adv_amt;
		this.tra_leg_1 = tra_leg_1;
		this.tra_leg_2 = tra_leg_2;
		this.tra_leg_3 = tra_leg_3;
		this.tra_leg_4 = tra_leg_4;
		this.tra_leg_5 = tra_leg_5;
		this.tra_leg_6 = tra_leg_6;
		this.tra_leg_7 = tra_leg_7;
		this.tra_leg_8 = tra_leg_8;
		this.tra_leg_9 = tra_leg_9;
		this.tra_leg_10 = tra_leg_10;
		this.adv_req_date = adv_req_date;
		this.adv_req_amt = adv_req_amt;
		this.adv_app_date = adv_app_date;
		this.adv_app_amt = adv_app_amt;
		this.adv_dis_date = adv_dis_date;
		this.adv_dis_amt = adv_dis_amt;
		this.tra_claim_date = tra_claim_date;
		this.tra_claim_amt = tra_claim_amt;
		this.tra_claim_app_date = tra_claim_app_date;
		this.tra_claim_app_amt = tra_claim_app_amt;
		this.amt_short_excess = amt_short_excess;
		this.date_of_adj = date_of_adj;
		this.tra_status = tra_status;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.auth_user = auth_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.auth_time = auth_time;
		this.del_flg = del_flg;
		this.entity_flg = entity_flg;
		this.modify_flg = modify_flg;
		this.booking_req = booking_req;
		this.booking_mode = booking_mode;
		this.device = device;
	}

	public BTMTravelMaster() {
		super();
		// TODO Auto-generated constructor stub
	}

}
