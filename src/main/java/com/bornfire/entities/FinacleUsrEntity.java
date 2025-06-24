package com.bornfire.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class FinacleUsrEntity {

	  @EmbeddedId
	    private EmbeddedIdEntity id;
	private String user_work_class;
	private String user_emp_id;
	private String emp_desig;
	private String emp_email_id;
	
	public String getUser_work_class() {
		return user_work_class;
	}
	public void setUser_work_class(String user_work_class) {
		this.user_work_class = user_work_class;
	}
	public String getUser_emp_id() {
		return user_emp_id;
	}
	public void setUser_emp_id(String user_emp_id) {
		this.user_emp_id = user_emp_id;
	}
	public String getEmp_desig() {
		return emp_desig;
	}
	public void setEmp_desig(String emp_desig) {
		this.emp_desig = emp_desig;
	}
	public String getEmp_email_id() {
		return emp_email_id;
	}
	public void setEmp_email_id(String emp_email_id) {
		this.emp_email_id = emp_email_id;
	}
	public FinacleUsrEntity(String user_id, String emp_name, String user_work_class, String user_emp_id,
			String emp_desig, String emp_email_id) {
		super();
		this.user_work_class = user_work_class;
		this.user_emp_id = user_emp_id;
		this.emp_desig = emp_desig;
		this.emp_email_id = emp_email_id;
	}
	public FinacleUsrEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
