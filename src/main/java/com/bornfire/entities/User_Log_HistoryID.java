package com.bornfire.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;

import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
public class User_Log_HistoryID implements Serializable {

	private String	user_id;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	login_time;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	logout_time;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Date getLogin_time() {
		return login_time;
	}
	public void setLogin_time(Date login_time) {
		this.login_time = login_time;
	}
	public Date getLogout_time() {
		return logout_time;
	}
	public void setLogout_time(Date logout_time) {
		this.logout_time = logout_time;
	}
	public User_Log_HistoryID(String user_id, Date login_time, Date logout_time) {
		super();
		this.user_id = user_id;
		this.login_time = login_time;
		this.logout_time = logout_time;
	}
	public User_Log_HistoryID() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
