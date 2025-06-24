package com.bornfire.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
@Entity
@Table(name="EMPLOYEE_EXIT_MANAGEMENT")
public class employee_exit_management_entity {

	@Id
	private String Employee_No;
	private String Employee_Name;
	private Date Date_of_Resignation;
	private Date Date_of_Joining;
	private Date Date_of_Birth;
	private String Reason;
	private char entity_flg;
	private char del_flg;
	private Date entry_time;
	private Date modify_time;
	private Date verify_time;
	private String entry_user;
	private String modify_user;
	private String verify_user;
	public String getEmployee_No() {
		return Employee_No;
	}
	public void setEmployee_No(String employee_No) {
		Employee_No = employee_No;
	}
	public String getEmployee_Name() {
		return Employee_Name;
	}
	public void setEmployee_Name(String employee_Name) {
		Employee_Name = employee_Name;
	}
	public Date getDate_of_Resignation() {
		return Date_of_Resignation;
	}
	public void setDate_of_Resignation(Date date_of_Resignation) {
		Date_of_Resignation = date_of_Resignation;
	}
	public Date getDate_of_Joining() {
		return Date_of_Joining;
	}
	public void setDate_of_Joining(Date date_of_Joining) {
		Date_of_Joining = date_of_Joining;
	}
	public Date getDate_of_Birth() {
		return Date_of_Birth;
	}
	public void setDate_of_Birth(Date date_of_Birth) {
		Date_of_Birth = date_of_Birth;
	}
	public String getReason() {
		return Reason;
	}
	public void setReason(String reason) {
		Reason = reason;
	}
	public char getEntity_flg() {
		return entity_flg;
	}
	public void setEntity_flg(char entity_flg) {
		this.entity_flg = entity_flg;
	}
	public char getDel_flg() {
		return del_flg;
	}
	public void setDel_flg(char del_flg) {
		this.del_flg = del_flg;
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
	public employee_exit_management_entity(String employee_No, String employee_Name, Date date_of_Resignation,
			Date date_of_Joining, Date date_of_Birth, String reason, char entity_flg, char del_flg, Date entry_time,
			Date modify_time, Date verify_time, String entry_user, String modify_user, String verify_user) {
		super();
		Employee_No = employee_No;
		Employee_Name = employee_Name;
		Date_of_Resignation = date_of_Resignation;
		Date_of_Joining = date_of_Joining;
		Date_of_Birth = date_of_Birth;
		Reason = reason;
		this.entity_flg = entity_flg;
		this.del_flg = del_flg;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.verify_time = verify_time;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.verify_user = verify_user;
	}
	public employee_exit_management_entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
