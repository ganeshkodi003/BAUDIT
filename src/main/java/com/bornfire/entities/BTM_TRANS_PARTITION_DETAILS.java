package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "BTM_TRANS_PARTITION_DETAILS") 
public class BTM_TRANS_PARTITION_DETAILS {

	
	  @Id
	    private String tran_id;

	    private BigDecimal part_tran_id;

	    private String acct_num;

	    private String acct_name;

	    private String tran_type;

	    private String part_tran_type;

	    private String acct_crncy;

	    private BigDecimal tran_amt;

	    private String tran_particular;

	    private String tran_remarks;

	    @DateTimeFormat(pattern = "dd-MM-yyyy")
	    private Date tran_date;

	    @DateTimeFormat(pattern = "dd-MM-yyyy")
	    private Date value_date;

	    private String tran_ref_no;

	    private String add_details;

	    private String partition_type;

	    private String partition_det;

	    private BigDecimal gst_amount;

	    private String gst_type;

		public String getTran_id() {
			return tran_id;
		}

		public void setTran_id(String tran_id) {
			this.tran_id = tran_id;
		}

		public BigDecimal getPart_tran_id() {
			return part_tran_id;
		}

		public void setPart_tran_id(BigDecimal part_tran_id) {
			this.part_tran_id = part_tran_id;
		}

		public String getAcct_num() {
			return acct_num;
		}

		public void setAcct_num(String acct_num) {
			this.acct_num = acct_num;
		}

		public String getAcct_name() {
			return acct_name;
		}

		public void setAcct_name(String acct_name) {
			this.acct_name = acct_name;
		}

		public String getTran_type() {
			return tran_type;
		}

		public void setTran_type(String tran_type) {
			this.tran_type = tran_type;
		}

		public String getPart_tran_type() {
			return part_tran_type;
		}

		public void setPart_tran_type(String part_tran_type) {
			this.part_tran_type = part_tran_type;
		}

		public String getAcct_crncy() {
			return acct_crncy;
		}

		public void setAcct_crncy(String acct_crncy) {
			this.acct_crncy = acct_crncy;
		}

		public BigDecimal getTran_amt() {
			return tran_amt;
		}

		public void setTran_amt(BigDecimal tran_amt) {
			this.tran_amt = tran_amt;
		}

		public String getTran_particular() {
			return tran_particular;
		}

		public void setTran_particular(String tran_particular) {
			this.tran_particular = tran_particular;
		}

		public String getTran_remarks() {
			return tran_remarks;
		}

		public void setTran_remarks(String tran_remarks) {
			this.tran_remarks = tran_remarks;
		}

		public Date getTran_date() {
			return tran_date;
		}

		public void setTran_date(Date tran_date) {
			this.tran_date = tran_date;
		}

		public Date getValue_date() {
			return value_date;
		}

		public void setValue_date(Date value_date) {
			this.value_date = value_date;
		}

		public String getTran_ref_no() {
			return tran_ref_no;
		}

		public void setTran_ref_no(String tran_ref_no) {
			this.tran_ref_no = tran_ref_no;
		}

		public String getAdd_details() {
			return add_details;
		}

		public void setAdd_details(String add_details) {
			this.add_details = add_details;
		}

		public String getPartition_type() {
			return partition_type;
		}

		public void setPartition_type(String partition_type) {
			this.partition_type = partition_type;
		}

		public String getPartition_det() {
			return partition_det;
		}

		public void setPartition_det(String partition_det) {
			this.partition_det = partition_det;
		}

		public BigDecimal getGst_amount() {
			return gst_amount;
		}

		public void setGst_amount(BigDecimal gst_amount) {
			this.gst_amount = gst_amount;
		}

		public String getGst_type() {
			return gst_type;
		}

		public void setGst_type(String gst_type) {
			this.gst_type = gst_type;
		}

		@Override
		public String toString() {
			return "BTM_TRANS_PARTITION_DETAILS [tran_id=" + tran_id + ", part_tran_id=" + part_tran_id + ", acct_num="
					+ acct_num + ", acct_name=" + acct_name + ", tran_type=" + tran_type + ", part_tran_type="
					+ part_tran_type + ", acct_crncy=" + acct_crncy + ", tran_amt=" + tran_amt + ", tran_particular="
					+ tran_particular + ", tran_remarks=" + tran_remarks + ", tran_date=" + tran_date + ", value_date="
					+ value_date + ", tran_ref_no=" + tran_ref_no + ", add_details=" + add_details + ", partition_type="
					+ partition_type + ", partition_det=" + partition_det + ", gst_amount=" + gst_amount + ", gst_type="
					+ gst_type + ", getTran_id()=" + getTran_id() + ", getPart_tran_id()=" + getPart_tran_id()
					+ ", getAcct_num()=" + getAcct_num() + ", getAcct_name()=" + getAcct_name() + ", getTran_type()="
					+ getTran_type() + ", getPart_tran_type()=" + getPart_tran_type() + ", getAcct_crncy()="
					+ getAcct_crncy() + ", getTran_amt()=" + getTran_amt() + ", getTran_particular()="
					+ getTran_particular() + ", getTran_remarks()=" + getTran_remarks() + ", getTran_date()="
					+ getTran_date() + ", getValue_date()=" + getValue_date() + ", getTran_ref_no()=" + getTran_ref_no()
					+ ", getAdd_details()=" + getAdd_details() + ", getPartition_type()=" + getPartition_type()
					+ ", getPartition_det()=" + getPartition_det() + ", getGst_amount()=" + getGst_amount()
					+ ", getGst_type()=" + getGst_type() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
					+ ", toString()=" + super.toString() + "]";
		}

		public BTM_TRANS_PARTITION_DETAILS(String tran_id, BigDecimal part_tran_id, String acct_num, String acct_name,
				String tran_type, String part_tran_type, String acct_crncy, BigDecimal tran_amt, String tran_particular,
				String tran_remarks, Date tran_date, Date value_date, String tran_ref_no, String add_details,
				String partition_type, String partition_det, BigDecimal gst_amount, String gst_type) {
			super();
			this.tran_id = tran_id;
			this.part_tran_id = part_tran_id;
			this.acct_num = acct_num;
			this.acct_name = acct_name;
			this.tran_type = tran_type;
			this.part_tran_type = part_tran_type;
			this.acct_crncy = acct_crncy;
			this.tran_amt = tran_amt;
			this.tran_particular = tran_particular;
			this.tran_remarks = tran_remarks;
			this.tran_date = tran_date;
			this.value_date = value_date;
			this.tran_ref_no = tran_ref_no;
			this.add_details = add_details;
			this.partition_type = partition_type;
			this.partition_det = partition_det;
			this.gst_amount = gst_amount;
			this.gst_type = gst_type;
		}

		public BTM_TRANS_PARTITION_DETAILS() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
}
