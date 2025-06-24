package com.bornfire.entities;

import java.math.BigDecimal;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BTM_TRANS_POINT_DETAILS") 
public class BTM_TRANS_POINT_DETAILS {
	@Id
    private String org_tran_id;

    private BigDecimal org_part_tran_id;

    private String org_acct_num;

    private String org_acct_name;

    private String org_tran_type;

    private String org_part_tran_type;

    private String org_acct_crncy;

    private BigDecimal org_tran_amt;

    private String org_tran_particular;

    private String org_tran_remarks;

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private Date org_tran_date;

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private Date org_value_date;

    private String org_tran_ref_no;

    private String org_add_details;

    private String org_partition_type;

    private String org_partition_det;

    private BigDecimal org_gst_amount;

    private String org_gst_type;

    private BigDecimal offset_tran_amt;

    private char single_rev_flg;

    private BigDecimal bal_outstd_amt;

    private char del_flg;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date last_offset_date;

    private Integer no_of_offset_tran;

	public String getOrg_tran_id() {
		return org_tran_id;
	}

	public void setOrg_tran_id(String org_tran_id) {
		this.org_tran_id = org_tran_id;
	}

	public BigDecimal getOrg_part_tran_id() {
		return org_part_tran_id;
	}

	public void setOrg_part_tran_id(BigDecimal org_part_tran_id) {
		this.org_part_tran_id = org_part_tran_id;
	}

	public String getOrg_acct_num() {
		return org_acct_num;
	}

	public void setOrg_acct_num(String org_acct_num) {
		this.org_acct_num = org_acct_num;
	}

	public String getOrg_acct_name() {
		return org_acct_name;
	}

	public void setOrg_acct_name(String org_acct_name) {
		this.org_acct_name = org_acct_name;
	}

	public String getOrg_tran_type() {
		return org_tran_type;
	}

	public void setOrg_tran_type(String org_tran_type) {
		this.org_tran_type = org_tran_type;
	}

	public String getOrg_part_tran_type() {
		return org_part_tran_type;
	}

	public void setOrg_part_tran_type(String org_part_tran_type) {
		this.org_part_tran_type = org_part_tran_type;
	}

	public String getOrg_acct_crncy() {
		return org_acct_crncy;
	}

	public void setOrg_acct_crncy(String org_acct_crncy) {
		this.org_acct_crncy = org_acct_crncy;
	}

	public BigDecimal getOrg_tran_amt() {
		return org_tran_amt;
	}

	public void setOrg_tran_amt(BigDecimal org_tran_amt) {
		this.org_tran_amt = org_tran_amt;
	}

	public String getOrg_tran_particular() {
		return org_tran_particular;
	}

	public void setOrg_tran_particular(String org_tran_particular) {
		this.org_tran_particular = org_tran_particular;
	}

	public String getOrg_tran_remarks() {
		return org_tran_remarks;
	}

	public void setOrg_tran_remarks(String org_tran_remarks) {
		this.org_tran_remarks = org_tran_remarks;
	}

	public Date getOrg_tran_date() {
		return org_tran_date;
	}

	public void setOrg_tran_date(Date org_tran_date) {
		this.org_tran_date = org_tran_date;
	}

	public Date getOrg_value_date() {
		return org_value_date;
	}

	public void setOrg_value_date(Date org_value_date) {
		this.org_value_date = org_value_date;
	}

	public String getOrg_tran_ref_no() {
		return org_tran_ref_no;
	}

	public void setOrg_tran_ref_no(String org_tran_ref_no) {
		this.org_tran_ref_no = org_tran_ref_no;
	}

	public String getOrg_add_details() {
		return org_add_details;
	}

	public void setOrg_add_details(String org_add_details) {
		this.org_add_details = org_add_details;
	}

	public String getOrg_partition_type() {
		return org_partition_type;
	}

	public void setOrg_partition_type(String org_partition_type) {
		this.org_partition_type = org_partition_type;
	}

	public String getOrg_partition_det() {
		return org_partition_det;
	}

	public void setOrg_partition_det(String org_partition_det) {
		this.org_partition_det = org_partition_det;
	}

	public BigDecimal getOrg_gst_amount() {
		return org_gst_amount;
	}

	public void setOrg_gst_amount(BigDecimal org_gst_amount) {
		this.org_gst_amount = org_gst_amount;
	}

	public String getOrg_gst_type() {
		return org_gst_type;
	}

	public void setOrg_gst_type(String org_gst_type) {
		this.org_gst_type = org_gst_type;
	}

	public BigDecimal getOffset_tran_amt() {
		return offset_tran_amt;
	}

	public void setOffset_tran_amt(BigDecimal offset_tran_amt) {
		this.offset_tran_amt = offset_tran_amt;
	}

	public char getSingle_rev_flg() {
		return single_rev_flg;
	}

	public void setSingle_rev_flg(char single_rev_flg) {
		this.single_rev_flg = single_rev_flg;
	}

	public BigDecimal getBal_outstd_amt() {
		return bal_outstd_amt;
	}

	public void setBal_outstd_amt(BigDecimal bal_outstd_amt) {
		this.bal_outstd_amt = bal_outstd_amt;
	}

	public char getDel_flg() {
		return del_flg;
	}

	public void setDel_flg(char del_flg) {
		this.del_flg = del_flg;
	}

	public Date getLast_offset_date() {
		return last_offset_date;
	}

	public void setLast_offset_date(Date last_offset_date) {
		this.last_offset_date = last_offset_date;
	}

	public Integer getNo_of_offset_tran() {
		return no_of_offset_tran;
	}

	public void setNo_of_offset_tran(Integer no_of_offset_tran) {
		this.no_of_offset_tran = no_of_offset_tran;
	}

	@Override
	public String toString() {
		return "BTM_TRANS_POINT_DETAILS [org_tran_id=" + org_tran_id + ", org_part_tran_id=" + org_part_tran_id
				+ ", org_acct_num=" + org_acct_num + ", org_acct_name=" + org_acct_name + ", org_tran_type="
				+ org_tran_type + ", org_part_tran_type=" + org_part_tran_type + ", org_acct_crncy=" + org_acct_crncy
				+ ", org_tran_amt=" + org_tran_amt + ", org_tran_particular=" + org_tran_particular
				+ ", org_tran_remarks=" + org_tran_remarks + ", org_tran_date=" + org_tran_date + ", org_value_date="
				+ org_value_date + ", org_tran_ref_no=" + org_tran_ref_no + ", org_add_details=" + org_add_details
				+ ", org_partition_type=" + org_partition_type + ", org_partition_det=" + org_partition_det
				+ ", org_gst_amount=" + org_gst_amount + ", org_gst_type=" + org_gst_type + ", offset_tran_amt="
				+ offset_tran_amt + ", single_rev_flg=" + single_rev_flg + ", bal_outstd_amt=" + bal_outstd_amt
				+ ", del_flg=" + del_flg + ", last_offset_date=" + last_offset_date + ", no_of_offset_tran="
				+ no_of_offset_tran + ", getOrg_tran_id()=" + getOrg_tran_id() + ", getOrg_part_tran_id()="
				+ getOrg_part_tran_id() + ", getOrg_acct_num()=" + getOrg_acct_num() + ", getOrg_acct_name()="
				+ getOrg_acct_name() + ", getOrg_tran_type()=" + getOrg_tran_type() + ", getOrg_part_tran_type()="
				+ getOrg_part_tran_type() + ", getOrg_acct_crncy()=" + getOrg_acct_crncy() + ", getOrg_tran_amt()="
				+ getOrg_tran_amt() + ", getOrg_tran_particular()=" + getOrg_tran_particular()
				+ ", getOrg_tran_remarks()=" + getOrg_tran_remarks() + ", getOrg_tran_date()=" + getOrg_tran_date()
				+ ", getOrg_value_date()=" + getOrg_value_date() + ", getOrg_tran_ref_no()=" + getOrg_tran_ref_no()
				+ ", getOrg_add_details()=" + getOrg_add_details() + ", getOrg_partition_type()="
				+ getOrg_partition_type() + ", getOrg_partition_det()=" + getOrg_partition_det()
				+ ", getOrg_gst_amount()=" + getOrg_gst_amount() + ", getOrg_gst_type()=" + getOrg_gst_type()
				+ ", getOffset_tran_amt()=" + getOffset_tran_amt() + ", getSingle_rev_flg()=" + getSingle_rev_flg()
				+ ", getBal_outstd_amt()=" + getBal_outstd_amt() + ", getDel_flg()=" + getDel_flg()
				+ ", getLast_offset_date()=" + getLast_offset_date() + ", getNo_of_offset_tran()="
				+ getNo_of_offset_tran() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	public BTM_TRANS_POINT_DETAILS(String org_tran_id, BigDecimal org_part_tran_id, String org_acct_num,
			String org_acct_name, String org_tran_type, String org_part_tran_type, String org_acct_crncy,
			BigDecimal org_tran_amt, String org_tran_particular, String org_tran_remarks, Date org_tran_date,
			Date org_value_date, String org_tran_ref_no, String org_add_details, String org_partition_type,
			String org_partition_det, BigDecimal org_gst_amount, String org_gst_type, BigDecimal offset_tran_amt,
			char single_rev_flg, BigDecimal bal_outstd_amt, char del_flg, Date last_offset_date,
			Integer no_of_offset_tran) {
		super();
		this.org_tran_id = org_tran_id;
		this.org_part_tran_id = org_part_tran_id;
		this.org_acct_num = org_acct_num;
		this.org_acct_name = org_acct_name;
		this.org_tran_type = org_tran_type;
		this.org_part_tran_type = org_part_tran_type;
		this.org_acct_crncy = org_acct_crncy;
		this.org_tran_amt = org_tran_amt;
		this.org_tran_particular = org_tran_particular;
		this.org_tran_remarks = org_tran_remarks;
		this.org_tran_date = org_tran_date;
		this.org_value_date = org_value_date;
		this.org_tran_ref_no = org_tran_ref_no;
		this.org_add_details = org_add_details;
		this.org_partition_type = org_partition_type;
		this.org_partition_det = org_partition_det;
		this.org_gst_amount = org_gst_amount;
		this.org_gst_type = org_gst_type;
		this.offset_tran_amt = offset_tran_amt;
		this.single_rev_flg = single_rev_flg;
		this.bal_outstd_amt = bal_outstd_amt;
		this.del_flg = del_flg;
		this.last_offset_date = last_offset_date;
		this.no_of_offset_tran = no_of_offset_tran;
	}

	public BTM_TRANS_POINT_DETAILS() {
		super();
		// TODO Auto-generated constructor stub
	}
    
}
