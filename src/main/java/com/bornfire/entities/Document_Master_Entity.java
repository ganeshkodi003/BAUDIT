package com.bornfire.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "DOCUMENT_MASTER")
public class Document_Master_Entity {
	
	private String	doc_ref_no;
	private String	doc_id;
	private String	doc_name;
	private String	doc_desc;
	private String	doc_type;
	private String	doc_group;
	private String	access_type;
	private String	access_group;
	private String	doc_folder;
	private String	doc_location;
	private String	doc_owner;
	private String	doc_approver;
	private String	doc_uploader;
	private String	doc_verifier;
	private String	entry_user;
	private String	modify_user;
	private String	auth_user;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	entry_time;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	modify_time;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	auth_time;
	private String	del_flg;
	private String	entity_flg;
	private String	modify_flg;
	@Id
	private String	emp_id;
	@Lob
	private byte[]	doc_image;
	private String	emp_name;
	private String	file_type;
	private String	file_name;
	@Lob
	private byte[]	document;
	public String getDoc_ref_no() {
		return doc_ref_no;
	}
	public void setDoc_ref_no(String doc_ref_no) {
		this.doc_ref_no = doc_ref_no;
	}
	public String getDoc_id() {
		return doc_id;
	}
	public void setDoc_id(String doc_id) {
		this.doc_id = doc_id;
	}
	public String getDoc_name() {
		return doc_name;
	}
	public void setDoc_name(String doc_name) {
		this.doc_name = doc_name;
	}
	public String getDoc_desc() {
		return doc_desc;
	}
	public void setDoc_desc(String doc_desc) {
		this.doc_desc = doc_desc;
	}
	public String getDoc_type() {
		return doc_type;
	}
	public void setDoc_type(String doc_type) {
		this.doc_type = doc_type;
	}
	public String getDoc_group() {
		return doc_group;
	}
	public void setDoc_group(String doc_group) {
		this.doc_group = doc_group;
	}
	public String getAccess_type() {
		return access_type;
	}
	public void setAccess_type(String access_type) {
		this.access_type = access_type;
	}
	public String getAccess_group() {
		return access_group;
	}
	public void setAccess_group(String access_group) {
		this.access_group = access_group;
	}
	public String getDoc_folder() {
		return doc_folder;
	}
	public void setDoc_folder(String doc_folder) {
		this.doc_folder = doc_folder;
	}
	public String getDoc_location() {
		return doc_location;
	}
	public void setDoc_location(String doc_location) {
		this.doc_location = doc_location;
	}
	public String getDoc_owner() {
		return doc_owner;
	}
	public void setDoc_owner(String doc_owner) {
		this.doc_owner = doc_owner;
	}
	public String getDoc_approver() {
		return doc_approver;
	}
	public void setDoc_approver(String doc_approver) {
		this.doc_approver = doc_approver;
	}
	public String getDoc_uploader() {
		return doc_uploader;
	}
	public void setDoc_uploader(String doc_uploader) {
		this.doc_uploader = doc_uploader;
	}
	public String getDoc_verifier() {
		return doc_verifier;
	}
	public void setDoc_verifier(String doc_verifier) {
		this.doc_verifier = doc_verifier;
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
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public byte[] getDoc_image() {
		return doc_image;
	}
	public void setDoc_image(byte[] doc_image) {
		this.doc_image = doc_image;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public String getFile_type() {
		return file_type;
	}
	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public byte[] getDocument() {
		return document;
	}
	public void setDocument(byte[] document) {
		this.document = document;
	}
	public Document_Master_Entity(String doc_ref_no, String doc_id, String doc_name, String doc_desc, String doc_type,
			String doc_group, String access_type, String access_group, String doc_folder, String doc_location,
			String doc_owner, String doc_approver, String doc_uploader, String doc_verifier, String entry_user,
			String modify_user, String auth_user, Date entry_time, Date modify_time, Date auth_time, String del_flg,
			String entity_flg, String modify_flg, String emp_id, byte[] doc_image, String emp_name, String file_type,
			String file_name, byte[] document) {
		super();
		this.doc_ref_no = doc_ref_no;
		this.doc_id = doc_id;
		this.doc_name = doc_name;
		this.doc_desc = doc_desc;
		this.doc_type = doc_type;
		this.doc_group = doc_group;
		this.access_type = access_type;
		this.access_group = access_group;
		this.doc_folder = doc_folder;
		this.doc_location = doc_location;
		this.doc_owner = doc_owner;
		this.doc_approver = doc_approver;
		this.doc_uploader = doc_uploader;
		this.doc_verifier = doc_verifier;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.auth_user = auth_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.auth_time = auth_time;
		this.del_flg = del_flg;
		this.entity_flg = entity_flg;
		this.modify_flg = modify_flg;
		this.emp_id = emp_id;
		this.doc_image = doc_image;
		this.emp_name = emp_name;
		this.file_type = file_type;
		this.file_name = file_name;
		this.document = document;
	}
	public Document_Master_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
