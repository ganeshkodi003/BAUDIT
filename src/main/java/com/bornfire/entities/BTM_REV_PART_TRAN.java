package com.bornfire.entities;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BTM_REV_PART_TRAN") 
public class BTM_REV_PART_TRAN {

	
	@Id
    private String rev_tran_id;

    private BigDecimal rev_part_tran_id;

    private String rev_acct_num;

    private String rev_acct_name;

    private String rev_tran_type;

    private String rev_part_tran_type;

    private String rev_tran_crncy;

    private BigDecimal rev_tran_amt;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date rev_tran_date;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date rev_value_date;

    private String rev_tran_ref_no;

    private String rev_add_details;

    private char off_set_flg;

	public String getRev_tran_id() {
		return rev_tran_id;
	}

	public void setRev_tran_id(String rev_tran_id) {
		this.rev_tran_id = rev_tran_id;
	}

	public BigDecimal getRev_part_tran_id() {
		return rev_part_tran_id;
	}

	public void setRev_part_tran_id(BigDecimal rev_part_tran_id) {
		this.rev_part_tran_id = rev_part_tran_id;
	}

	public String getRev_acct_num() {
		return rev_acct_num;
	}

	public void setRev_acct_num(String rev_acct_num) {
		this.rev_acct_num = rev_acct_num;
	}

	public String getRev_acct_name() {
		return rev_acct_name;
	}

	public void setRev_acct_name(String rev_acct_name) {
		this.rev_acct_name = rev_acct_name;
	}

	public String getRev_tran_type() {
		return rev_tran_type;
	}

	public void setRev_tran_type(String rev_tran_type) {
		this.rev_tran_type = rev_tran_type;
	}

	public String getRev_part_tran_type() {
		return rev_part_tran_type;
	}

	public void setRev_part_tran_type(String rev_part_tran_type) {
		this.rev_part_tran_type = rev_part_tran_type;
	}

	public String getRev_tran_crncy() {
		return rev_tran_crncy;
	}

	public void setRev_tran_crncy(String rev_tran_crncy) {
		this.rev_tran_crncy = rev_tran_crncy;
	}

	public BigDecimal getRev_tran_amt() {
		return rev_tran_amt;
	}

	public void setRev_tran_amt(BigDecimal rev_tran_amt) {
		this.rev_tran_amt = rev_tran_amt;
	}

	public Date getRev_tran_date() {
		return rev_tran_date;
	}

	public void setRev_tran_date(Date rev_tran_date) {
		this.rev_tran_date = rev_tran_date;
	}

	public Date getRev_value_date() {
		return rev_value_date;
	}

	public void setRev_value_date(Date rev_value_date) {
		this.rev_value_date = rev_value_date;
	}

	public String getRev_tran_ref_no() {
		return rev_tran_ref_no;
	}

	public void setRev_tran_ref_no(String rev_tran_ref_no) {
		this.rev_tran_ref_no = rev_tran_ref_no;
	}

	public String getRev_add_details() {
		return rev_add_details;
	}

	public void setRev_add_details(String rev_add_details) {
		this.rev_add_details = rev_add_details;
	}

	public char getOff_set_flg() {
		return off_set_flg;
	}

	public void setOff_set_flg(char off_set_flg) {
		this.off_set_flg = off_set_flg;
	}

	@Override
	public String toString() {
		return "BTM_REV_PART_TRAN [rev_tran_id=" + rev_tran_id + ", rev_part_tran_id=" + rev_part_tran_id
				+ ", rev_acct_num=" + rev_acct_num + ", rev_acct_name=" + rev_acct_name + ", rev_tran_type="
				+ rev_tran_type + ", rev_part_tran_type=" + rev_part_tran_type + ", rev_tran_crncy=" + rev_tran_crncy
				+ ", rev_tran_amt=" + rev_tran_amt + ", rev_tran_date=" + rev_tran_date + ", rev_value_date="
				+ rev_value_date + ", rev_tran_ref_no=" + rev_tran_ref_no + ", rev_add_details=" + rev_add_details
				+ ", off_set_flg=" + off_set_flg + ", getRev_tran_id()=" + getRev_tran_id() + ", getRev_part_tran_id()="
				+ getRev_part_tran_id() + ", getRev_acct_num()=" + getRev_acct_num() + ", getRev_acct_name()="
				+ getRev_acct_name() + ", getRev_tran_type()=" + getRev_tran_type() + ", getRev_part_tran_type()="
				+ getRev_part_tran_type() + ", getRev_tran_crncy()=" + getRev_tran_crncy() + ", getRev_tran_amt()="
				+ getRev_tran_amt() + ", getRev_tran_date()=" + getRev_tran_date() + ", getRev_value_date()="
				+ getRev_value_date() + ", getRev_tran_ref_no()=" + getRev_tran_ref_no() + ", getRev_add_details()="
				+ getRev_add_details() + ", getOff_set_flg()=" + getOff_set_flg() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	public BTM_REV_PART_TRAN(String rev_tran_id, BigDecimal rev_part_tran_id, String rev_acct_num, String rev_acct_name,
			String rev_tran_type, String rev_part_tran_type, String rev_tran_crncy, BigDecimal rev_tran_amt,
			Date rev_tran_date, Date rev_value_date, String rev_tran_ref_no, String rev_add_details, char off_set_flg) {
		super();
		this.rev_tran_id = rev_tran_id;
		this.rev_part_tran_id = rev_part_tran_id;
		this.rev_acct_num = rev_acct_num;
		this.rev_acct_name = rev_acct_name;
		this.rev_tran_type = rev_tran_type;
		this.rev_part_tran_type = rev_part_tran_type;
		this.rev_tran_crncy = rev_tran_crncy;
		this.rev_tran_amt = rev_tran_amt;
		this.rev_tran_date = rev_tran_date;
		this.rev_value_date = rev_value_date;
		this.rev_tran_ref_no = rev_tran_ref_no;
		this.rev_add_details = rev_add_details;
		this.off_set_flg = off_set_flg;
	}

	public BTM_REV_PART_TRAN() {
		super();
		// TODO Auto-generated constructor stub
	}

}
