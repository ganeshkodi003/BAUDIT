package com.bornfire.entities;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="BTM_BAM_CATEGORY_CODE_MAINTAINANCE")
public class Bamcategorycodemain {

	@Id
	private String	sl_no;
	private String	head_code;
	private String	head_description;
	private String	category_code;
	private String	category_description;
	private String	sub_category_code;
	private String	sub_category_description;
	private String	asset_code;
	private String	depreciation_method;
	private BigDecimal	depreciation_percentage;
	private String	asset_account_number;
	private String	depreciation_fund_account;
	private String	depreciation_pandl_account;
	private String	solid;
	private String	location;
	
	private String	entry_user;
	private String	modify_user;
	private String	verify_user;
	private Date	entry_time;
	private Date	modify_time;
	private Date	verify_time;
	
	
	
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
	public String getSl_no() {
		return sl_no;
	}
	public void setSl_no(String sl_no) {
		this.sl_no = sl_no;
	}
	public String getHead_code() {
		return head_code;
	}
	public void setHead_code(String head_code) {
		this.head_code = head_code;
	}
	public String getHead_description() {
		return head_description;
	}
	public void setHead_description(String head_description) {
		this.head_description = head_description;
	}
	public String getCategory_code() {
		return category_code;
	}
	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}
	public String getCategory_description() {
		return category_description;
	}
	public void setCategory_description(String category_description) {
		this.category_description = category_description;
	}
	public String getSub_category_code() {
		return sub_category_code;
	}
	public void setSub_category_code(String sub_category_code) {
		this.sub_category_code = sub_category_code;
	}
	public String getSub_category_description() {
		return sub_category_description;
	}
	public void setSub_category_description(String sub_category_description) {
		this.sub_category_description = sub_category_description;
	}
	public String getAsset_code() {
		return asset_code;
	}
	public void setAsset_code(String asset_code) {
		this.asset_code = asset_code;
	}
	public String getDepreciation_method() {
		return depreciation_method;
	}
	public void setDepreciation_method(String depreciation_method) {
		this.depreciation_method = depreciation_method;
	}
	public BigDecimal getDepreciation_percentage() {
		return depreciation_percentage;
	}
	public void setDepreciation_percentage(BigDecimal depreciation_percentage) {
		this.depreciation_percentage = depreciation_percentage;
	}
	public String getAsset_account_number() {
		return asset_account_number;
	}
	public void setAsset_account_number(String asset_account_number) {
		this.asset_account_number = asset_account_number;
	}
	public String getDepreciation_fund_account() {
		return depreciation_fund_account;
	}
	public void setDepreciation_fund_account(String depreciation_fund_account) {
		this.depreciation_fund_account = depreciation_fund_account;
	}
	public String getDepreciation_pandl_account() {
		return depreciation_pandl_account;
	}
	public void setDepreciation_pandl_account(String depreciation_pandl_account) {
		this.depreciation_pandl_account = depreciation_pandl_account;
	}
	public String getSolid() {
		return solid;
	}
	public void setSolid(String solid) {
		this.solid = solid;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	
	public Bamcategorycodemain(String sl_no, String head_code, String head_description, String category_code,
			String category_description, String sub_category_code, String sub_category_description, String asset_code,
			String depreciation_method, BigDecimal depreciation_percentage, String asset_account_number,
			String depreciation_fund_account, String depreciation_pandl_account, String solid, String location,
			String entry_user, String modify_user, String verify_user, Date entry_time, Date modify_time,
			Date verify_time) {
		super();
		this.sl_no = sl_no;
		this.head_code = head_code;
		this.head_description = head_description;
		this.category_code = category_code;
		this.category_description = category_description;
		this.sub_category_code = sub_category_code;
		this.sub_category_description = sub_category_description;
		this.asset_code = asset_code;
		this.depreciation_method = depreciation_method;
		this.depreciation_percentage = depreciation_percentage;
		this.asset_account_number = asset_account_number;
		this.depreciation_fund_account = depreciation_fund_account;
		this.depreciation_pandl_account = depreciation_pandl_account;
		this.solid = solid;
		this.location = location;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.verify_user = verify_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.verify_time = verify_time;
	}
	public Bamcategorycodemain() {
		super();
		// TODO Auto-generated constructor stub
	}
}