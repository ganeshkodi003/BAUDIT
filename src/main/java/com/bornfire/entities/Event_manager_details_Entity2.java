package com.bornfire.entities;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "SERVICE_DETAILS")
public class Event_manager_details_Entity2 {



	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "SERVICE_DATE", length = 255)
	private Date serviceDate;

	@Id
	@Column(name = "SERVICE_NO", length = 255)
	private String serviceNo;

	@Column(name = "SERVICE_TYPE", length = 255)
	private String serviceType;

	@Column(name = "SERVICE_DESCRIPTION", length = 255)
	private String serviceDescription;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "SERVICE_FROM_DATE", length = 255)
	private Date serviceFromDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "SERVICE_TO_DATE", length = 255)
	private Date serviceToDate;


	@Column(name = "SERVICER_NAME", length = 255)
	private String servicerName;

	@Column(name = "SERVICER_CONTACT_NO", length = 255)
	private String servicerContactNo;

	@Column(name = "SERVICE_DETAILS", length = 255)
	private String serviceDetails;

	@Column(name = "FEES_DETAILS", length = 255)
	private String feesDetails;
	
	@Column(name = "del_flg", length = 1)
	private String delFlg;

	@Column(name = "entity_flg", length = 1)
	private String entityFlg;

	@Column(name = "modify_flg", length = 1)
	private String modifyFlg;

	@Column(name = "entry_user", length = 255)
	private String entryUser;

	@Column(name = "entry_time")
	private LocalDateTime entryTime;

	@Column(name = "modify_user", length = 255)
	private String modifyUser;

	@Column(name = "modify_time")
	private LocalDateTime modifyTime;

	@Column(name = "verify_user", length = 255)
	private String verifyUser;

	@Column(name = "verify_time")
	private LocalDateTime verifyTime;

	

	public Date getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}

	public String getServiceNo() {
		return serviceNo;
	}

	public void setServiceNo(String serviceNo) {
		this.serviceNo = serviceNo;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}

	public Date getServiceFromDate() {
		return serviceFromDate;
	}

	public void setServiceFromDate(Date serviceFromDate) {
		this.serviceFromDate = serviceFromDate;
	}

	public Date getServiceToDate() {
		return serviceToDate;
	}

	public void setServiceToDate(Date serviceToDate) {
		this.serviceToDate = serviceToDate;
	}

	public String getServicerName() {
		return servicerName;
	}

	public void setServicerName(String servicerName) {
		this.servicerName = servicerName;
	}

	public String getServicerContactNo() {
		return servicerContactNo;
	}

	public void setServicerContactNo(String servicerContactNo) {
		this.servicerContactNo = servicerContactNo;
	}

	public String getServiceDetails() {
		return serviceDetails;
	}

	public void setServiceDetails(String serviceDetails) {
		this.serviceDetails = serviceDetails;
	}

	public String getFeesDetails() {
		return feesDetails;
	}

	public void setFeesDetails(String feesDetails) {
		this.feesDetails = feesDetails;
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

	public String getEntryUser() {
		return entryUser;
	}

	public void setEntryUser(String entryUser) {
		this.entryUser = entryUser;
	}

	public LocalDateTime getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
	}

	public String getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(String modifyUser) {
		this.modifyUser = modifyUser;
	}

	public LocalDateTime getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(LocalDateTime modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getVerifyUser() {
		return verifyUser;
	}

	public void setVerifyUser(String verifyUser) {
		this.verifyUser = verifyUser;
	}

	public LocalDateTime getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(LocalDateTime verifyTime) {
		this.verifyTime = verifyTime;
	}

	public Event_manager_details_Entity2(Date serviceDate, String serviceNo, String serviceType,
			String serviceDescription, Date serviceFromDate, Date serviceToDate, String servicerName,
			String servicerContactNo, String serviceDetails, String feesDetails, String delFlg, String entityFlg,
			String modifyFlg, String entryUser, LocalDateTime entryTime, String modifyUser, LocalDateTime modifyTime,
			String verifyUser, LocalDateTime verifyTime) {
		super();
		this.serviceDate = serviceDate;
		this.serviceNo = serviceNo;
		this.serviceType = serviceType;
		this.serviceDescription = serviceDescription;
		this.serviceFromDate = serviceFromDate;
		this.serviceToDate = serviceToDate;
		this.servicerName = servicerName;
		this.servicerContactNo = servicerContactNo;
		this.serviceDetails = serviceDetails;
		this.feesDetails = feesDetails;
		this.delFlg = delFlg;
		this.entityFlg = entityFlg;
		this.modifyFlg = modifyFlg;
		this.entryUser = entryUser;
		this.entryTime = entryTime;
		this.modifyUser = modifyUser;
		this.modifyTime = modifyTime;
		this.verifyUser = verifyUser;
		this.verifyTime = verifyTime;
	}

	public Event_manager_details_Entity2() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public String toString() {
		return "Event_manager_details_Entity2 [serviceDate=" + serviceDate + ", serviceNo=" + serviceNo
				+ ", serviceType=" + serviceType + ", serviceDescription=" + serviceDescription + ", serviceFromDate="
				+ serviceFromDate + ", serviceToDate=" + serviceToDate + ", servicerName=" + servicerName
				+ ", servicerContactNo=" + servicerContactNo + ", serviceDetails=" + serviceDetails + ", feesDetails="
				+ feesDetails + ", delFlg=" + delFlg + ", entityFlg=" + entityFlg + ", modifyFlg=" + modifyFlg
				+ ", entryUser=" + entryUser + ", entryTime=" + entryTime + ", modifyUser=" + modifyUser
				+ ", modifyTime=" + modifyTime + ", verifyUser=" + verifyUser + ", verifyTime=" + verifyTime
				+ ", getServiceDate()=" + getServiceDate() + ", getServiceNo()=" + getServiceNo()
				+ ", getServiceType()=" + getServiceType() + ", getServiceDescription()=" + getServiceDescription()
				+ ", getServiceFromDate()=" + getServiceFromDate() + ", getServiceToDate()=" + getServiceToDate()
				+ ", getServicerName()=" + getServicerName() + ", getServicerContactNo()=" + getServicerContactNo()
				+ ", getServiceDetails()=" + getServiceDetails() + ", getFeesDetails()=" + getFeesDetails()
				+ ", getDelFlg()=" + getDelFlg() + ", getEntityFlg()=" + getEntityFlg() + ", getModifyFlg()="
				+ getModifyFlg() + ", getEntryUser()=" + getEntryUser() + ", getEntryTime()=" + getEntryTime()
				+ ", getModifyUser()=" + getModifyUser() + ", getModifyTime()=" + getModifyTime() + ", getVerifyUser()="
				+ getVerifyUser() + ", getVerifyTime()=" + getVerifyTime() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	
}
