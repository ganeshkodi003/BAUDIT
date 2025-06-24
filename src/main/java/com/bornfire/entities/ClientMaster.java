package com.bornfire.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="CLIENT_MASTER")
public class ClientMaster {
	
	@Id
	private String	client_srl_no;
	
	private String	client_type;
	private String	client_name;
	private String	client_addr;
	private String	contact_person;
	private String	phone;
	private String	email;
	private String	client_location;
	private String	cin_no;
	private String	gstin;
	private String	bill_to;
	private String	deliver_to;
	private String hsn_code;
	private String vendor_code;
	private String	del_flg;
	private String	entity_flg;
	private String	modify_flg;
	private String	entry_user;
	private String	modify_user;
	private String	verify_user;
	@DateTimeFormat(pattern="dd-MM-yyyy") 
	private Date	entry_time;
	@DateTimeFormat(pattern="dd-MM-yyyy") 
	private Date	modify_time;
	@DateTimeFormat(pattern="dd-MM-yyyy") 
	private Date	verify_time;
	private String tel_fax;
	
	public String getTel_fax() {
		return tel_fax;
	}
	public void setTel_fax(String tel_fax) {
		this.tel_fax = tel_fax;
	}
	public String getClient_srl_no() {
		return client_srl_no;
	}
	public void setClient_srl_no(String client_srl_no) {
		this.client_srl_no = client_srl_no;
	}
	public String getClient_type() {
		return client_type;
	}
	public void setClient_type(String client_type) {
		this.client_type = client_type;
	}
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public String getClient_addr() {
		return client_addr;
	}
	public void setClient_addr(String client_addr) {
		this.client_addr = client_addr;
	}
	public String getContact_person() {
		return contact_person;
	}
	public void setContact_person(String contact_person) {
		this.contact_person = contact_person;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getClient_location() {
		return client_location;
	}
	public void setClient_location(String client_location) {
		this.client_location = client_location;
	}
	public String getCin_no() {
		return cin_no;
	}
	public void setCin_no(String cin_no) {
		this.cin_no = cin_no;
	}
	public String getGstin() {
		return gstin;
	}
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
	public String getBill_to() {
		return bill_to;
	}
	public void setBill_to(String bill_to) {
		this.bill_to = bill_to;
	}
	public String getDeliver_to() {
		return deliver_to;
	}
	public void setDeliver_to(String deliver_to) {
		this.deliver_to = deliver_to;
	}
	public String getHsn_code() {
		return hsn_code;
	}
	public void setHsn_code(String hsn_code) {
		this.hsn_code = hsn_code;
	}
	public String getVendor_code() {
		return vendor_code;
	}
	public void setVendor_code(String vendor_code) {
		this.vendor_code = vendor_code;
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
	public ClientMaster(String client_srl_no, String client_type, String client_name, String client_addr,
			String contact_person, String phone, String email, String client_location, String cin_no, String gstin,
			String bill_to, String deliver_to,String hsn_code,String vendor_code,String del_flg, String entity_flg, String modify_flg, String entry_user,
			String modify_user, String verify_user, Date entry_time, Date modify_time, Date verify_time,String tel_fax) {
		super();
		this.client_srl_no = client_srl_no;
		this.client_type = client_type;
		this.client_name = client_name;
		this.client_addr = client_addr;
		this.contact_person = contact_person;
		this.phone = phone;
		this.email = email;
		this.client_location = client_location;
		this.cin_no = cin_no;
		this.gstin = gstin;
		this.bill_to = bill_to;
		this.deliver_to = deliver_to;
		this.hsn_code = hsn_code;
		this.vendor_code = vendor_code;
		this.del_flg = del_flg;
		this.entity_flg = entity_flg;
		this.modify_flg = modify_flg;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.verify_user = verify_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.verify_time = verify_time;
		this.tel_fax=tel_fax;
	}
	public ClientMaster() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
