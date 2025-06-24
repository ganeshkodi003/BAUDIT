package com.bornfire.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EmbeddedIdEntity implements Serializable {

    private String user_id;
    private String emp_name;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public EmbeddedIdEntity(String user_id, String emp_name) {
		super();
		this.user_id = user_id;
		this.emp_name = emp_name;
	}

    
    // Constructors, getters, setters, equals, and hashCode methods

}
