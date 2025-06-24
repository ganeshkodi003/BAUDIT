package com.bornfire.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbaadm.get")
public class tbaadmgetEntity {

	 @Id
	    private String empId;
	    private char entityCreFlg;
	    private char delFlg;
	    private String empIntls;
	    private String solId;
	    private String empName;
	    private String empShortName;
	    private Long empSignPowerNum;
	    private Double empSignPowerAmt;
	    private String empDesig;
	    private String empStat;
	    private Integer totModTimes;
	    private String lchgUserId;
	    private Date lchgTime;
	    private String rcreUserId;
	    private Date rcreTime;
	    private char isHeadTeller;
	    private Integer tsCnt;
	    private String empEmailId;
	    private String alt1EmpName;
	    private String alt1EmpShortName;
		public String getEmpId() {
			return empId;
		}
		public void setEmpId(String empId) {
			this.empId = empId;
		}
		public char getEntityCreFlg() {
			return entityCreFlg;
		}
		public void setEntityCreFlg(char entityCreFlg) {
			this.entityCreFlg = entityCreFlg;
		}
		public char getDelFlg() {
			return delFlg;
		}
		public void setDelFlg(char delFlg) {
			this.delFlg = delFlg;
		}
		public String getEmpIntls() {
			return empIntls;
		}
		public void setEmpIntls(String empIntls) {
			this.empIntls = empIntls;
		}
		public String getSolId() {
			return solId;
		}
		public void setSolId(String solId) {
			this.solId = solId;
		}
		public String getEmpName() {
			return empName;
		}
		public void setEmpName(String empName) {
			this.empName = empName;
		}
		public String getEmpShortName() {
			return empShortName;
		}
		public void setEmpShortName(String empShortName) {
			this.empShortName = empShortName;
		}
		public Long getEmpSignPowerNum() {
			return empSignPowerNum;
		}
		public void setEmpSignPowerNum(Long empSignPowerNum) {
			this.empSignPowerNum = empSignPowerNum;
		}
		public Double getEmpSignPowerAmt() {
			return empSignPowerAmt;
		}
		public void setEmpSignPowerAmt(Double empSignPowerAmt) {
			this.empSignPowerAmt = empSignPowerAmt;
		}
		public String getEmpDesig() {
			return empDesig;
		}
		public void setEmpDesig(String empDesig) {
			this.empDesig = empDesig;
		}
		public String getEmpStat() {
			return empStat;
		}
		public void setEmpStat(String empStat) {
			this.empStat = empStat;
		}
		public Integer getTotModTimes() {
			return totModTimes;
		}
		public void setTotModTimes(Integer totModTimes) {
			this.totModTimes = totModTimes;
		}
		public String getLchgUserId() {
			return lchgUserId;
		}
		public void setLchgUserId(String lchgUserId) {
			this.lchgUserId = lchgUserId;
		}
		public Date getLchgTime() {
			return lchgTime;
		}
		public void setLchgTime(Date lchgTime) {
			this.lchgTime = lchgTime;
		}
		public String getRcreUserId() {
			return rcreUserId;
		}
		public void setRcreUserId(String rcreUserId) {
			this.rcreUserId = rcreUserId;
		}
		public Date getRcreTime() {
			return rcreTime;
		}
		public void setRcreTime(Date rcreTime) {
			this.rcreTime = rcreTime;
		}
		public char getIsHeadTeller() {
			return isHeadTeller;
		}
		public void setIsHeadTeller(char isHeadTeller) {
			this.isHeadTeller = isHeadTeller;
		}
		public Integer getTsCnt() {
			return tsCnt;
		}
		public void setTsCnt(Integer tsCnt) {
			this.tsCnt = tsCnt;
		}
		public String getEmpEmailId() {
			return empEmailId;
		}
		public void setEmpEmailId(String empEmailId) {
			this.empEmailId = empEmailId;
		}
		public String getAlt1EmpName() {
			return alt1EmpName;
		}
		public void setAlt1EmpName(String alt1EmpName) {
			this.alt1EmpName = alt1EmpName;
		}
		public String getAlt1EmpShortName() {
			return alt1EmpShortName;
		}
		public void setAlt1EmpShortName(String alt1EmpShortName) {
			this.alt1EmpShortName = alt1EmpShortName;
		}
		public tbaadmgetEntity(String empId, char entityCreFlg, char delFlg, String empIntls, String solId,
				String empName, String empShortName, Long empSignPowerNum, Double empSignPowerAmt, String empDesig,
				String empStat, Integer totModTimes, String lchgUserId, Date lchgTime, String rcreUserId, Date rcreTime,
				char isHeadTeller, Integer tsCnt, String empEmailId, String alt1EmpName, String alt1EmpShortName) {
			super();
			this.empId = empId;
			this.entityCreFlg = entityCreFlg;
			this.delFlg = delFlg;
			this.empIntls = empIntls;
			this.solId = solId;
			this.empName = empName;
			this.empShortName = empShortName;
			this.empSignPowerNum = empSignPowerNum;
			this.empSignPowerAmt = empSignPowerAmt;
			this.empDesig = empDesig;
			this.empStat = empStat;
			this.totModTimes = totModTimes;
			this.lchgUserId = lchgUserId;
			this.lchgTime = lchgTime;
			this.rcreUserId = rcreUserId;
			this.rcreTime = rcreTime;
			this.isHeadTeller = isHeadTeller;
			this.tsCnt = tsCnt;
			this.empEmailId = empEmailId;
			this.alt1EmpName = alt1EmpName;
			this.alt1EmpShortName = alt1EmpShortName;
		}
		public tbaadmgetEntity() {
			super();
			// TODO Auto-generated constructor stub
		}

	    
	
}
