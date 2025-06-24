package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "EXP_MASTER")
public class ExpenseMaster {
	
	@Id
	private String	exp_ref_no;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	exp_rpt_date;
	private String	ass_id;
	private String	ass_name;
	private String	ass_desig;
	private String	ass_group;
	private String	claim_type;
	private String	prj_id;
	private String	crncy;
	private BigDecimal	claim_amt;
	private BigDecimal	rate;
	private BigDecimal	home_crncy_amt;
	private String	exp_det_1;
	private String	exp_det_2;
	private String	exp_det_3;
	private String	exp_det_4;
	private String	exp_det_5;
	private String	exp_det_6;
	private String	exp_det_7;
	private String	exp_det_8;
	private String	exp_det_9;
	private String	exp_det_10;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	rpt_date;
	private BigDecimal	amt_claimed;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	date_of_apr;
	private BigDecimal	amt_apr;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	date_of_adv;
	private BigDecimal	adv_amt;
	private BigDecimal	bal_amt;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date	date_of_settl;
	private String	status;
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
	private String	device;
	public String getExp_ref_no() {
		return exp_ref_no;
	}
	public void setExp_ref_no(String exp_ref_no) {
		this.exp_ref_no = exp_ref_no;
	}
	public Date getExp_rpt_date() {
		return exp_rpt_date;
	}
	public void setExp_rpt_date(Date exp_rpt_date) {
		this.exp_rpt_date = exp_rpt_date;
	}
	public String getAss_id() {
		return ass_id;
	}
	public void setAss_id(String ass_id) {
		this.ass_id = ass_id;
	}
	public String getAss_name() {
		return ass_name;
	}
	public void setAss_name(String ass_name) {
		this.ass_name = ass_name;
	}
	public String getAss_desig() {
		return ass_desig;
	}
	public void setAss_desig(String ass_desig) {
		this.ass_desig = ass_desig;
	}
	public String getAss_group() {
		return ass_group;
	}
	public void setAss_group(String ass_group) {
		this.ass_group = ass_group;
	}
	public String getClaim_type() {
		return claim_type;
	}
	public void setClaim_type(String claim_type) {
		this.claim_type = claim_type;
	}
	public String getPrj_id() {
		return prj_id;
	}
	public void setPrj_id(String prj_id) {
		this.prj_id = prj_id;
	}
	public String getCrncy() {
		return crncy;
	}
	public void setCrncy(String crncy) {
		this.crncy = crncy;
	}
	public BigDecimal getClaim_amt() {
		return claim_amt;
	}
	public void setClaim_amt(BigDecimal claim_amt) {
		this.claim_amt = claim_amt;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public BigDecimal getHome_crncy_amt() {
		return home_crncy_amt;
	}
	public void setHome_crncy_amt(BigDecimal home_crncy_amt) {
		this.home_crncy_amt = home_crncy_amt;
	}
	public String getExp_det_1() {
		return exp_det_1;
	}
	public void setExp_det_1(String exp_det_1) {
		this.exp_det_1 = exp_det_1;
	}
	public String getExp_det_2() {
		return exp_det_2;
	}
	public void setExp_det_2(String exp_det_2) {
		this.exp_det_2 = exp_det_2;
	}
	public String getExp_det_3() {
		return exp_det_3;
	}
	public void setExp_det_3(String exp_det_3) {
		this.exp_det_3 = exp_det_3;
	}
	public String getExp_det_4() {
		return exp_det_4;
	}
	public void setExp_det_4(String exp_det_4) {
		this.exp_det_4 = exp_det_4;
	}
	public String getExp_det_5() {
		return exp_det_5;
	}
	public void setExp_det_5(String exp_det_5) {
		this.exp_det_5 = exp_det_5;
	}
	public String getExp_det_6() {
		return exp_det_6;
	}
	public void setExp_det_6(String exp_det_6) {
		this.exp_det_6 = exp_det_6;
	}
	public String getExp_det_7() {
		return exp_det_7;
	}
	public void setExp_det_7(String exp_det_7) {
		this.exp_det_7 = exp_det_7;
	}
	public String getExp_det_8() {
		return exp_det_8;
	}
	public void setExp_det_8(String exp_det_8) {
		this.exp_det_8 = exp_det_8;
	}
	public String getExp_det_9() {
		return exp_det_9;
	}
	public void setExp_det_9(String exp_det_9) {
		this.exp_det_9 = exp_det_9;
	}
	public String getExp_det_10() {
		return exp_det_10;
	}
	public void setExp_det_10(String exp_det_10) {
		this.exp_det_10 = exp_det_10;
	}
	public Date getRpt_date() {
		return rpt_date;
	}
	public void setRpt_date(Date rpt_date) {
		this.rpt_date = rpt_date;
	}
	public BigDecimal getAmt_claimed() {
		return amt_claimed;
	}
	public void setAmt_claimed(BigDecimal amt_claimed) {
		this.amt_claimed = amt_claimed;
	}
	public Date getDate_of_apr() {
		return date_of_apr;
	}
	public void setDate_of_apr(Date date_of_apr) {
		this.date_of_apr = date_of_apr;
	}
	public BigDecimal getAmt_apr() {
		return amt_apr;
	}
	public void setAmt_apr(BigDecimal amt_apr) {
		this.amt_apr = amt_apr;
	}
	public Date getDate_of_adv() {
		return date_of_adv;
	}
	public void setDate_of_adv(Date date_of_adv) {
		this.date_of_adv = date_of_adv;
	}
	public BigDecimal getAdv_amt() {
		return adv_amt;
	}
	public void setAdv_amt(BigDecimal adv_amt) {
		this.adv_amt = adv_amt;
	}
	public BigDecimal getBal_amt() {
		return bal_amt;
	}
	public void setBal_amt(BigDecimal bal_amt) {
		this.bal_amt = bal_amt;
	}
	public Date getDate_of_settl() {
		return date_of_settl;
	}
	public void setDate_of_settl(Date date_of_settl) {
		this.date_of_settl = date_of_settl;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public ExpenseMaster(String exp_ref_no, Date exp_rpt_date, String ass_id, String ass_name, String ass_desig,
			String ass_group, String claim_type, String prj_id, String crncy, BigDecimal claim_amt, BigDecimal rate,
			BigDecimal home_crncy_amt, String exp_det_1, String exp_det_2, String exp_det_3, String exp_det_4,
			String exp_det_5, String exp_det_6, String exp_det_7, String exp_det_8, String exp_det_9, String exp_det_10,
			Date rpt_date, BigDecimal amt_claimed, Date date_of_apr, BigDecimal amt_apr, Date date_of_adv,
			BigDecimal adv_amt, BigDecimal bal_amt, Date date_of_settl, String status, String entry_user,
			String modify_user, String auth_user, Date entry_time, Date modify_time, Date auth_time, String del_flg,
			String entity_flg, String modify_flg, String device) {
		super();
		this.exp_ref_no = exp_ref_no;
		this.exp_rpt_date = exp_rpt_date;
		this.ass_id = ass_id;
		this.ass_name = ass_name;
		this.ass_desig = ass_desig;
		this.ass_group = ass_group;
		this.claim_type = claim_type;
		this.prj_id = prj_id;
		this.crncy = crncy;
		this.claim_amt = claim_amt;
		this.rate = rate;
		this.home_crncy_amt = home_crncy_amt;
		this.exp_det_1 = exp_det_1;
		this.exp_det_2 = exp_det_2;
		this.exp_det_3 = exp_det_3;
		this.exp_det_4 = exp_det_4;
		this.exp_det_5 = exp_det_5;
		this.exp_det_6 = exp_det_6;
		this.exp_det_7 = exp_det_7;
		this.exp_det_8 = exp_det_8;
		this.exp_det_9 = exp_det_9;
		this.exp_det_10 = exp_det_10;
		this.rpt_date = rpt_date;
		this.amt_claimed = amt_claimed;
		this.date_of_apr = date_of_apr;
		this.amt_apr = amt_apr;
		this.date_of_adv = date_of_adv;
		this.adv_amt = adv_amt;
		this.bal_amt = bal_amt;
		this.date_of_settl = date_of_settl;
		this.status = status;
		this.entry_user = entry_user;
		this.modify_user = modify_user;
		this.auth_user = auth_user;
		this.entry_time = entry_time;
		this.modify_time = modify_time;
		this.auth_time = auth_time;
		this.del_flg = del_flg;
		this.entity_flg = entity_flg;
		this.modify_flg = modify_flg;
		this.device = device;
	}
	public ExpenseMaster() {
		super();
		// TODO Auto-generated constructor stub
	}


}
