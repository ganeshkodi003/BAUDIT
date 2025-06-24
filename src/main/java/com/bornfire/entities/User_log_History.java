package com.bornfire.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "USER_LOG_HISTORY")
public class User_log_History implements Serializable{
	
	@Id
	private User_Log_HistoryID user_Log_HistoryID;
	private String	login_flg;
	private String	login_br;
	private String	login_status;
	private String	logout_status;
	private BigDecimal	session_id;
	private BigDecimal	oper_attempts;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	login_date;
	private String	ip_address;
	private String	log_flg;
	public User_Log_HistoryID getUser_Log_HistoryID() {
		return user_Log_HistoryID;
	}
	public void setUser_Log_HistoryID(User_Log_HistoryID user_Log_HistoryID) {
		this.user_Log_HistoryID = user_Log_HistoryID;
	}
	public String getLogin_flg() {
		return login_flg;
	}
	public void setLogin_flg(String login_flg) {
		this.login_flg = login_flg;
	}
	public String getLogin_br() {
		return login_br;
	}
	public void setLogin_br(String login_br) {
		this.login_br = login_br;
	}
	public String getLogin_status() {
		return login_status;
	}
	public void setLogin_status(String login_status) {
		this.login_status = login_status;
	}
	public String getLogout_status() {
		return logout_status;
	}
	public void setLogout_status(String logout_status) {
		this.logout_status = logout_status;
	}
	public BigDecimal getSession_id() {
		return session_id;
	}
	public void setSession_id(BigDecimal session_id) {
		this.session_id = session_id;
	}
	public BigDecimal getOper_attempts() {
		return oper_attempts;
	}
	public void setOper_attempts(BigDecimal oper_attempts) {
		this.oper_attempts = oper_attempts;
	}
	public Date getLogin_date() {
		return login_date;
	}
	public void setLogin_date(Date login_date) {
		this.login_date = login_date;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public String getLog_flg() {
		return log_flg;
	}
	public void setLog_flg(String log_flg) {
		this.log_flg = log_flg;
	}
	public User_log_History(User_Log_HistoryID user_Log_HistoryID, String login_flg, String login_br,
			String login_status, String logout_status, BigDecimal session_id, BigDecimal oper_attempts, Date login_date,
			String ip_address, String log_flg) {
		super();
		this.user_Log_HistoryID = user_Log_HistoryID;
		this.login_flg = login_flg;
		this.login_br = login_br;
		this.login_status = login_status;
		this.logout_status = logout_status;
		this.session_id = session_id;
		this.oper_attempts = oper_attempts;
		this.login_date = login_date;
		this.ip_address = ip_address;
		this.log_flg = log_flg;
	}
	public User_log_History() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	

}
