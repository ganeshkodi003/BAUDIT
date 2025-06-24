package com.bornfire.entities;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class BtmChecklistDto {
	@Id
    private String resourceName;
    private String serviceShift;
    private String serviceTime;
    private String logInTime;
    private String logOutTime;
    private String project;
    private String uniqueId;
    private String delFlg;
    private String entryFlg;
    private Date userLoginExpiryDate;
    private Date sslCertificateExpiryDate;
	public BtmChecklistDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BtmChecklistDto(String resourceName, String serviceShift, String serviceTime, String logInTime,
			String logOutTime, String project, String uniqueId, String delFlg, String entryFlg,
			Date userLoginExpiryDate, Date sslCertificateExpiryDate) {
		super();
		this.resourceName = resourceName;
		this.serviceShift = serviceShift;
		this.serviceTime = serviceTime;
		this.logInTime = logInTime;
		this.logOutTime = logOutTime;
		this.project = project;
		this.uniqueId = uniqueId;
		this.delFlg = delFlg;
		this.entryFlg = entryFlg;
		this.userLoginExpiryDate = userLoginExpiryDate;
		this.sslCertificateExpiryDate = sslCertificateExpiryDate;
	}
	@Override
	public String toString() {
		return "BtmChecklistDto [resourceName=" + resourceName + ", serviceShift=" + serviceShift + ", serviceTime="
				+ serviceTime + ", logInTime=" + logInTime + ", logOutTime=" + logOutTime + ", project=" + project
				+ ", uniqueId=" + uniqueId + ", delFlg=" + delFlg + ", entryFlg=" + entryFlg + ", userLoginExpiryDate="
				+ userLoginExpiryDate + ", sslCertificateExpiryDate=" + sslCertificateExpiryDate
				+ ", getResourceName()=" + getResourceName() + ", getServiceShift()=" + getServiceShift()
				+ ", getServiceTime()=" + getServiceTime() + ", getLogInTime()=" + getLogInTime() + ", getLogOutTime()="
				+ getLogOutTime() + ", getProject()=" + getProject() + ", getUniqueId()=" + getUniqueId()
				+ ", getDelFlg()=" + getDelFlg() + ", getEntryFlg()=" + getEntryFlg() + ", getUserLoginExpiryDate()="
				+ getUserLoginExpiryDate() + ", getSslCertificateExpiryDate()=" + getSslCertificateExpiryDate()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public String getServiceShift() {
		return serviceShift;
	}
	public void setServiceShift(String serviceShift) {
		this.serviceShift = serviceShift;
	}
	public String getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}
	public String getLogInTime() {
		return logInTime;
	}
	public void setLogInTime(String logInTime) {
		this.logInTime = logInTime;
	}
	public String getLogOutTime() {
		return logOutTime;
	}
	public void setLogOutTime(String logOutTime) {
		this.logOutTime = logOutTime;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getDelFlg() {
		return delFlg;
	}
	public void setDelFlg(String delFlg) {
		this.delFlg = delFlg;
	}
	public String getEntryFlg() {
		return entryFlg;
	}
	public void setEntryFlg(String entryFlg) {
		this.entryFlg = entryFlg;
	}
	public Date getUserLoginExpiryDate() {
		return userLoginExpiryDate;
	}
	public void setUserLoginExpiryDate(Date userLoginExpiryDate) {
		this.userLoginExpiryDate = userLoginExpiryDate;
	}
	public Date getSslCertificateExpiryDate() {
		return sslCertificateExpiryDate;
	}
	public void setSslCertificateExpiryDate(Date sslCertificateExpiryDate) {
		this.sslCertificateExpiryDate = sslCertificateExpiryDate;
	}

    // Constructor, getters, and setters
}
