package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="EVENT_MASTER")
public class BTMEventMaster {
	
	
	@Id
	private BigDecimal	srl_no;
	private String	module_id;
	private String	module_desc;
	private String	menu_id;
	private String	menu_desc;
	private String	option_id;
	private String	option_desc;
	private String	screen_id;
	private String	screen_name;
	private String	operation;
	private String	access_type;
	private String	access_desc;
	private String	channel_id;
	private String	channel_desc;
	private String	script_type;
	private String	script_name;
	private String	exe_name;
	private String	exe_desc;
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
	public BigDecimal getSrl_no() {
		return srl_no;
	}
	public void setSrl_no(BigDecimal srl_no) {
		this.srl_no = srl_no;
	}
	public String getModule_id() {
		return module_id;
	}
	public void setModule_id(String module_id) {
		this.module_id = module_id;
	}
	public String getModule_desc() {
		return module_desc;
	}
	public void setModule_desc(String module_desc) {
		this.module_desc = module_desc;
	}
	public String getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}
	public String getMenu_desc() {
		return menu_desc;
	}
	public void setMenu_desc(String menu_desc) {
		this.menu_desc = menu_desc;
	}
	public String getOption_id() {
		return option_id;
	}
	public void setOption_id(String option_id) {
		this.option_id = option_id;
	}
	public String getOption_desc() {
		return option_desc;
	}
	public void setOption_desc(String option_desc) {
		this.option_desc = option_desc;
	}
	public String getScreen_id() {
		return screen_id;
	}
	public void setScreen_id(String screen_id) {
		this.screen_id = screen_id;
	}
	public String getScreen_name() {
		return screen_name;
	}
	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getAccess_type() {
		return access_type;
	}
	public void setAccess_type(String access_type) {
		this.access_type = access_type;
	}
	public String getAccess_desc() {
		return access_desc;
	}
	public void setAccess_desc(String access_desc) {
		this.access_desc = access_desc;
	}
	public String getChannel_id() {
		return channel_id;
	}
	public void setChannel_id(String channel_id) {
		this.channel_id = channel_id;
	}
	public String getChannel_desc() {
		return channel_desc;
	}
	public void setChannel_desc(String channel_desc) {
		this.channel_desc = channel_desc;
	}
	public String getScript_type() {
		return script_type;
	}
	public void setScript_type(String script_type) {
		this.script_type = script_type;
	}
	public String getScript_name() {
		return script_name;
	}
	public void setScript_name(String script_name) {
		this.script_name = script_name;
	}
	public String getExe_name() {
		return exe_name;
	}
	public void setExe_name(String exe_name) {
		this.exe_name = exe_name;
	}
	public String getExe_desc() {
		return exe_desc;
	}
	public void setExe_desc(String exe_desc) {
		this.exe_desc = exe_desc;
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
	public BTMEventMaster(BigDecimal srl_no, String module_id, String module_desc, String menu_id, String menu_desc,
			String option_id, String option_desc, String screen_id, String screen_name, String operation,
			String access_type, String access_desc, String channel_id, String channel_desc, String script_type,
			String script_name, String exe_name, String exe_desc, String entry_user, String modify_user,
			String auth_user, Date entry_time, Date modify_time, Date auth_time, String del_flg, String entity_flg,
			String modify_flg) {
		super();
		this.srl_no = srl_no;
		this.module_id = module_id;
		this.module_desc = module_desc;
		this.menu_id = menu_id;
		this.menu_desc = menu_desc;
		this.option_id = option_id;
		this.option_desc = option_desc;
		this.screen_id = screen_id;
		this.screen_name = screen_name;
		this.operation = operation;
		this.access_type = access_type;
		this.access_desc = access_desc;
		this.channel_id = channel_id;
		this.channel_desc = channel_desc;
		this.script_type = script_type;
		this.script_name = script_name;
		this.exe_name = exe_name;
		this.exe_desc = exe_desc;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.auth_user = auth_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.auth_time = auth_time;
		this.del_flg = del_flg;
		this.entity_flg = entity_flg;
		this.modify_flg = modify_flg;
	}
	public BTMEventMaster() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	

}
