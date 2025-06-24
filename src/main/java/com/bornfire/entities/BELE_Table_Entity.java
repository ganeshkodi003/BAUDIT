package com.bornfire.entities;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name = "BTM_ELE")
public class BELE_Table_Entity {

    @Id
    @Column(name = "EMP_NO", length = 10)
    private String empNo;

    @Column(name = "ORGN", length = 50)
    private String orgn;

    @Column(name = "EMP_NAME", length = 50)
    private String empName;

    @Column(name = "EMP_DESIG", length = 15)
    private String empDesig;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "DOB")
    private Date dob;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "DOJ")
    private Date doj;

    @Column(name = "YEAR")
    private BigDecimal year; // NUMBER mapped to BigDecimal

    @Column(name = "CL_OPN")
    private BigDecimal clOpn;

    @Column(name = "CL_AVAIL")
    private BigDecimal clAvail;

    @Column(name = "CL_BAL")
    private BigDecimal clBal;

    @Column(name = "EL_OPN")
    private BigDecimal elOpn;

    @Column(name = "EL_AVAIL")
    private BigDecimal elAvail;

    @Column(name = "EL_BAL")
    private BigDecimal elBal;

    @Column(name = "LOP_OPN")
    private BigDecimal lopOpn;

    @Column(name = "LOP_AVAIL")
    private BigDecimal lopAvail;

    @Column(name = "LOP_BAL")
    private BigDecimal lopBal;

    @Column(name = "SL_OPN")
    private BigDecimal slOpn;

    @Column(name = "SL_AVAIL")
    private BigDecimal slAvail;

    @Column(name = "SL_BAL")
    private BigDecimal slBal;

    @Column(name = "OTR_OPN")
    private BigDecimal otrOpn;

    @Column(name = "OTR_AVAIL")
    private BigDecimal otrAvail;

    @Column(name = "OTR_BAL")
    private BigDecimal otrBal;

    @Column(name = "ENTRY_USER", length = 15)
    private String entryUser;

    @Column(name = "MODIFY_USER", length = 15)
    private String modifyUser;

    @Column(name = "AUTH_USER", length = 15)
    private String authUser;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "ENTRY_TIME")
    private Date entryTime;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "MODIFY_TIME")
    private Date modifyTime;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(name = "AUTH_TIME")
    private Date authTime;

    @Column(name = "DEL_FLG", length = 1)
    private String delFlg;

    @Column(name = "ENTITY_FLG", length = 1)
    private String entityFlg;

    @Column(name = "MODIFY_FLG", length = 1)
    private String modifyFlg;

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getOrgn() {
		return orgn;
	}

	public void setOrgn(String orgn) {
		this.orgn = orgn;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpDesig() {
		return empDesig;
	}

	public void setEmpDesig(String empDesig) {
		this.empDesig = empDesig;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public BigDecimal getYear() {
		return year;
	}

	public void setYear(BigDecimal year) {
		this.year = year;
	}

	public BigDecimal getClOpn() {
		return clOpn;
	}

	public void setClOpn(BigDecimal clOpn) {
		this.clOpn = clOpn;
	}

	public BigDecimal getClAvail() {
		return clAvail;
	}

	public void setClAvail(BigDecimal clAvail) {
		this.clAvail = clAvail;
	}

	public BigDecimal getClBal() {
		return clBal;
	}

	public void setClBal(BigDecimal clBal) {
		this.clBal = clBal;
	}

	public BigDecimal getElOpn() {
		return elOpn;
	}

	public void setElOpn(BigDecimal elOpn) {
		this.elOpn = elOpn;
	}

	public BigDecimal getElAvail() {
		return elAvail;
	}

	public void setElAvail(BigDecimal elAvail) {
		this.elAvail = elAvail;
	}

	public BigDecimal getElBal() {
		return elBal;
	}

	public void setElBal(BigDecimal elBal) {
		this.elBal = elBal;
	}

	public BigDecimal getLopOpn() {
		return lopOpn;
	}

	public void setLopOpn(BigDecimal lopOpn) {
		this.lopOpn = lopOpn;
	}

	public BigDecimal getLopAvail() {
		return lopAvail;
	}

	public void setLopAvail(BigDecimal lopAvail) {
		this.lopAvail = lopAvail;
	}

	public BigDecimal getLopBal() {
		return lopBal;
	}

	public void setLopBal(BigDecimal lopBal) {
		this.lopBal = lopBal;
	}

	public BigDecimal getSlOpn() {
		return slOpn;
	}

	public void setSlOpn(BigDecimal slOpn) {
		this.slOpn = slOpn;
	}

	public BigDecimal getSlAvail() {
		return slAvail;
	}

	public void setSlAvail(BigDecimal slAvail) {
		this.slAvail = slAvail;
	}

	public BigDecimal getSlBal() {
		return slBal;
	}

	public void setSlBal(BigDecimal slBal) {
		this.slBal = slBal;
	}

	public BigDecimal getOtrOpn() {
		return otrOpn;
	}

	public void setOtrOpn(BigDecimal otrOpn) {
		this.otrOpn = otrOpn;
	}

	public BigDecimal getOtrAvail() {
		return otrAvail;
	}

	public void setOtrAvail(BigDecimal otrAvail) {
		this.otrAvail = otrAvail;
	}

	public BigDecimal getOtrBal() {
		return otrBal;
	}

	public void setOtrBal(BigDecimal otrBal) {
		this.otrBal = otrBal;
	}

	public String getEntryUser() {
		return entryUser;
	}

	public void setEntryUser(String entryUser) {
		this.entryUser = entryUser;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getAuthUser() {
		return authUser;
	}

	public void setAuthUser(String authUser) {
		this.authUser = authUser;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getAuthTime() {
		return authTime;
	}

	public void setAuthTime(Date authTime) {
		this.authTime = authTime;
	}

	public String getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}

	public String getEntityFlg() {
		return entityFlg;
	}

	public void setEntityFlg(String entityFlg) {
		this.entityFlg = entityFlg;
	}

	public String getModifyFlg() {
		return modifyFlg;
	}

	public void setModifyFlg(String modifyFlg) {
		this.modifyFlg = modifyFlg;
	}

	public BELE_Table_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BELE_Table_Entity(String empNo, String orgn, String empName, String empDesig, Date dob, Date doj,
			BigDecimal year, BigDecimal clOpn, BigDecimal clAvail, BigDecimal clBal, BigDecimal elOpn,
			BigDecimal elAvail, BigDecimal elBal, BigDecimal lopOpn, BigDecimal lopAvail, BigDecimal lopBal,
			BigDecimal slOpn, BigDecimal slAvail, BigDecimal slBal, BigDecimal otrOpn, BigDecimal otrAvail,
			BigDecimal otrBal, String entryUser, String modifyUser, String authUser, Date entryTime, Date modifyTime,
			Date authTime, String delFlg, String entityFlg, String modifyFlg) {
		super();
		this.empNo = empNo;
		this.orgn = orgn;
		this.empName = empName;
		this.empDesig = empDesig;
		this.dob = dob;
		this.doj = doj;
		this.year = year;
		this.clOpn = clOpn;
		this.clAvail = clAvail;
		this.clBal = clBal;
		this.elOpn = elOpn;
		this.elAvail = elAvail;
		this.elBal = elBal;
		this.lopOpn = lopOpn;
		this.lopAvail = lopAvail;
		this.lopBal = lopBal;
		this.slOpn = slOpn;
		this.slAvail = slAvail;
		this.slBal = slBal;
		this.otrOpn = otrOpn;
		this.otrAvail = otrAvail;
		this.otrBal = otrBal;
		this.entryUser = entryUser;
		this.modifyUser = modifyUser;
		this.authUser = authUser;
		this.entryTime = entryTime;
		this.modifyTime = modifyTime;
		this.authTime = authTime;
		this.delFlg = delFlg;
		this.entityFlg = entityFlg;
		this.modifyFlg = modifyFlg;
	}

    
    
}
