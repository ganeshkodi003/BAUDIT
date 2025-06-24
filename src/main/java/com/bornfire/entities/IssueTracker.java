package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="ISSUE_TRACKER")
public class IssueTracker {
    @Id
	private String	srl_no;
	private String	category;
	private String	groups;
	private String	product;
	private String	module;
	private String	options;
	private String	screen;
	private String	event;
	private String	operation;
	private String	oper_desc;
	private String	issue_ref_no;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	date_of_issue;
	private String	rpt_by;
	private String	apr_by;
	private String	nat_of_issue;
	private String	issue_details;
	private String	issue_severity;
	private String	issue_rmks;
	private String	assign_to;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	assign_date;
	private BigDecimal	fix_period;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	del_date;
	private String	fix_details;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	date_of_fix;
	private String	test_by;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	test_date;
	private String	test_results;
	private String	issue_status;
	private BigDecimal	tat_per;
	private String	final_cls;
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
	public String getSrl_no() {
		return srl_no;
	}
	public void setSrl_no(String srl_no) {
		this.srl_no = srl_no;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getGroups() {
		return groups;
	}
	public void setGroups(String groups) {
		this.groups = groups;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public String getScreen() {
		return screen;
	}
	public void setScreen(String screen) {
		this.screen = screen;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getOper_desc() {
		return oper_desc;
	}
	public void setOper_desc(String oper_desc) {
		this.oper_desc = oper_desc;
	}
	public String getIssue_ref_no() {
		return issue_ref_no;
	}
	public void setIssue_ref_no(String issue_ref_no) {
		this.issue_ref_no = issue_ref_no;
	}
	public Date getDate_of_issue() {
		return date_of_issue;
	}
	public void setDate_of_issue(Date date_of_issue) {
		this.date_of_issue = date_of_issue;
	}
	public String getRpt_by() {
		return rpt_by;
	}
	public void setRpt_by(String rpt_by) {
		this.rpt_by = rpt_by;
	}
	public String getApr_by() {
		return apr_by;
	}
	public void setApr_by(String apr_by) {
		this.apr_by = apr_by;
	}
	public String getNat_of_issue() {
		return nat_of_issue;
	}
	public void setNat_of_issue(String nat_of_issue) {
		this.nat_of_issue = nat_of_issue;
	}
	public String getIssue_details() {
		return issue_details;
	}
	public void setIssue_details(String issue_details) {
		this.issue_details = issue_details;
	}
	public String getIssue_severity() {
		return issue_severity;
	}
	public void setIssue_severity(String issue_severity) {
		this.issue_severity = issue_severity;
	}
	public String getIssue_rmks() {
		return issue_rmks;
	}
	public void setIssue_rmks(String issue_rmks) {
		this.issue_rmks = issue_rmks;
	}
	public String getAssign_to() {
		return assign_to;
	}
	public void setAssign_to(String assign_to) {
		this.assign_to = assign_to;
	}
	public Date getAssign_date() {
		return assign_date;
	}
	public void setAssign_date(Date assign_date) {
		this.assign_date = assign_date;
	}
	public BigDecimal getFix_period() {
		return fix_period;
	}
	public void setFix_period(BigDecimal fix_period) {
		this.fix_period = fix_period;
	}
	public Date getDel_date() {
		return del_date;
	}
	public void setDel_date(Date del_date) {
		this.del_date = del_date;
	}
	public String getFix_details() {
		return fix_details;
	}
	public void setFix_details(String fix_details) {
		this.fix_details = fix_details;
	}
	public Date getDate_of_fix() {
		return date_of_fix;
	}
	public void setDate_of_fix(Date date_of_fix) {
		this.date_of_fix = date_of_fix;
	}
	public String getTest_by() {
		return test_by;
	}
	public void setTest_by(String test_by) {
		this.test_by = test_by;
	}
	public Date getTest_date() {
		return test_date;
	}
	public void setTest_date(Date test_date) {
		this.test_date = test_date;
	}
	public String getTest_results() {
		return test_results;
	}
	public void setTest_results(String test_results) {
		this.test_results = test_results;
	}
	public String getIssue_status() {
		return issue_status;
	}
	public void setIssue_status(String issue_status) {
		this.issue_status = issue_status;
	}
	public BigDecimal getTat_per() {
		return tat_per;
	}
	public void setTat_per(BigDecimal tat_per) {
		this.tat_per = tat_per;
	}
	public String getFinal_cls() {
		return final_cls;
	}
	public void setFinal_cls(String final_cls) {
		this.final_cls = final_cls;
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
	public IssueTracker(String srl_no, String category, String groups, String product, String module, String options,
			String screen, String event, String operation, String oper_desc, String issue_ref_no, Date date_of_issue,
			String rpt_by, String apr_by, String nat_of_issue, String issue_details, String issue_severity,
			String issue_rmks, String assign_to, Date assign_date, BigDecimal fix_period, Date del_date,
			String fix_details, Date date_of_fix, String test_by, Date test_date, String test_results,
			String issue_status, BigDecimal tat_per, String final_cls, String entry_user, String modify_user,
			String auth_user, Date entry_time, Date modify_time, Date auth_time, String del_flg, String entity_flg,
			String modify_flg) {
		super();
		this.srl_no = srl_no;
		this.category = category;
		this.groups = groups;
		this.product = product;
		this.module = module;
		this.options = options;
		this.screen = screen;
		this.event = event;
		this.operation = operation;
		this.oper_desc = oper_desc;
		this.issue_ref_no = issue_ref_no;
		this.date_of_issue = date_of_issue;
		this.rpt_by = rpt_by;
		this.apr_by = apr_by;
		this.nat_of_issue = nat_of_issue;
		this.issue_details = issue_details;
		this.issue_severity = issue_severity;
		this.issue_rmks = issue_rmks;
		this.assign_to = assign_to;
		this.assign_date = assign_date;
		this.fix_period = fix_period;
		this.del_date = del_date;
		this.fix_details = fix_details;
		this.date_of_fix = date_of_fix;
		this.test_by = test_by;
		this.test_date = test_date;
		this.test_results = test_results;
		this.issue_status = issue_status;
		this.tat_per = tat_per;
		this.final_cls = final_cls;
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
	public IssueTracker() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
