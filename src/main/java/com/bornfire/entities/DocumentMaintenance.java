package com.bornfire.entities;

import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "DOCUMENT_MAINTENANCE")
public class DocumentMaintenance {
    
	
	private String emp_id;
	private String emp_name;
	private String file_name;
	private String document;
	@Lob
	private byte[] file_upload;
	@Id
 	private String unique_id;
	 @GeneratedValue(strategy = GenerationType.IDENTITY) // Or GenerationType.SEQUENCE if you're using sequences
	private String srl_no;
	private String type;
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	public byte[] getFile_upload() {
		return file_upload;
	}
	public void setFile_upload(byte[] file_upload) {
		this.file_upload = file_upload;
	}
	public String getUnique_id() {
		return unique_id;
	}
	public void setUnique_id(String unique_id) {
		this.unique_id = unique_id;
	}
	public String getSrl_no() {
		return srl_no;
	}
	public void setSrl_no(String srl_no) {
		this.srl_no = srl_no;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "DocumentMaintenance [emp_id=" + emp_id + ", emp_name=" + emp_name + ", file_name=" + file_name
				+ ", document=" + document + ", file_upload=" + Arrays.toString(file_upload) + ", unique_id="
				+ unique_id + ", srl_no=" + srl_no + ", type=" + type + ", getEmp_id()=" + getEmp_id()
				+ ", getEmp_name()=" + getEmp_name() + ", getFile_name()=" + getFile_name() + ", getDocument()="
				+ getDocument() + ", getFile_upload()=" + Arrays.toString(getFile_upload()) + ", getUnique_id()="
				+ getUnique_id() + ", getSrl_no()=" + getSrl_no() + ", getType()=" + getType() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	public DocumentMaintenance(String emp_id, String emp_name, String file_name, String document, byte[] file_upload,
			String unique_id, String srl_no, String type) {
		super();
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.file_name = file_name;
		this.document = document;
		this.file_upload = file_upload;
		this.unique_id = unique_id;
		this.srl_no = srl_no;
		this.type = type;
	}
	public DocumentMaintenance() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
