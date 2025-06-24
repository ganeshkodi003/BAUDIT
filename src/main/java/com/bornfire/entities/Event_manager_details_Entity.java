package com.bornfire.entities;


import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "EVENT_MANAGER_DETAILS")
public class Event_manager_details_Entity {

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "current_date")
	private Date currentDate;

	@Id
	@Column(name = "EVENT_ID", length = 255)
	private String eventId;

	@Column(name = "EVENT_TYPE", length = 255)
	private String eventType;

	@Column(name = "EVENT_DESCRIPTION", length = 500)
	private String eventDescription;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "EVENT_DATE")
	private Date eventDate;

	@DateTimeFormat(pattern = "HH:mm")
	@Column(name = "START_TIME")
	private String startTime;

	@DateTimeFormat(pattern = "HH:mm")
	@Column(name = "END_TIME")
	private String endTime;

	@Column(name = "ORGANIZER_TYPE", length = 255)
	private String organizerType;

	@Column(name = "ORG_EMPLOYEE_NO", length = 255)
	private String orgEmployeeNo;

	@Column(name = "ORG_EMPLOYEE_NAME", length = 255)
	private String orgEmployeeName;
	
	@Column(name = "ORGANIZER_NAME", length = 255)
	private String organizerName;

	@Column(name = "PARTICIPANT_LOCATION", length = 255)
	private String participantLocation;

	@Column(name = "PARTICIPANT_EMP_NO", length = 255)
	private String participantEmpNo;

	@Column(name = "PARTICIPANT_EMP_NAME", length = 255)
	private String participantEmpName;

	@Column(name = "ENTRY_USER", length = 255)
	private String entryUser;

	@Column(name = "ENTRY_TIME")
	private LocalDateTime entryTime;

	@Column(name = "MODIFY_USER", length = 255)
	private String modifyUser;

	@Column(name = "MODIFY_TIME")
	private LocalDateTime modifyTime;

	@Column(name = "VERIFY_USER", length = 255)
	private String verifyUser;

	@Column(name = "VERIFY_TIME")
	private LocalDateTime verifyTime;

	@Column(name = "DEL_FLG", length = 1)
	private String delFlg;

	@Column(name = "ENTITY_FLG", length = 1)
	private String entityFlg;

	@Column(name = "MODIFY_FLG", length = 1)
	private String modifyFlg;

	@Column(name = "EVENTMODE", length = 255)
	private String mode;

	@Column(name = "EVENTREMARKS", length = 255)
	private String remarks;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "EVENT_TO_DATE", length = 255)
	private Date eventToDate;

	@Column(name = "EVENT_FREQUENCY", length = 255)
	private String eventFrequency;

	@Column(name = "SINGLE_FLAG", length = 255)
	private String singleFlag;

	@Column(name = "EVENT_STATUS", length = 255)
	private String event_status;
	
	@Column(name = "ORGANIZER_FEEDBACK", length = 255)
	private String organizer_feedback;
	
	@Column(name = "PARTICIPANTS_FEEDBACK", length = 255)
	private String participants_feedback;

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getOrganizerType() {
		return organizerType;
	}

	public void setOrganizerType(String organizerType) {
		this.organizerType = organizerType;
	}

	public String getOrgEmployeeNo() {
		return orgEmployeeNo;
	}

	public void setOrgEmployeeNo(String orgEmployeeNo) {
		this.orgEmployeeNo = orgEmployeeNo;
	}

	public String getOrgEmployeeName() {
		return orgEmployeeName;
	}

	public void setOrgEmployeeName(String orgEmployeeName) {
		this.orgEmployeeName = orgEmployeeName;
	}

	public String getOrganizerName() {
		return organizerName;
	}

	public void setOrganizerName(String organizerName) {
		this.organizerName = organizerName;
	}

	public String getParticipantLocation() {
		return participantLocation;
	}

	public void setParticipantLocation(String participantLocation) {
		this.participantLocation = participantLocation;
	}

	public String getParticipantEmpNo() {
		return participantEmpNo;
	}

	public void setParticipantEmpNo(String participantEmpNo) {
		this.participantEmpNo = participantEmpNo;
	}

	public String getParticipantEmpName() {
		return participantEmpName;
	}

	public void setParticipantEmpName(String participantEmpName) {
		this.participantEmpName = participantEmpName;
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

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getEventToDate() {
		return eventToDate;
	}

	public void setEventToDate(Date eventToDate) {
		this.eventToDate = eventToDate;
	}

	public String getEventFrequency() {
		return eventFrequency;
	}

	public void setEventFrequency(String eventFrequency) {
		this.eventFrequency = eventFrequency;
	}

	public String getSingleFlag() {
		return singleFlag;
	}

	public void setSingleFlag(String singleFlag) {
		this.singleFlag = singleFlag;
	}

	public String getEvent_status() {
		return event_status;
	}

	public void setEvent_status(String event_status) {
		this.event_status = event_status;
	}

	public String getOrganizer_feedback() {
		return organizer_feedback;
	}

	public void setOrganizer_feedback(String organizer_feedback) {
		this.organizer_feedback = organizer_feedback;
	}

	public String getParticipants_feedback() {
		return participants_feedback;
	}

	public void setParticipants_feedback(String participants_feedback) {
		this.participants_feedback = participants_feedback;
	}

	public Event_manager_details_Entity(Date currentDate, String eventId, String eventType, String eventDescription,
			Date eventDate, String startTime, String endTime, String organizerType, String orgEmployeeNo,
			String orgEmployeeName, String organizerName, String participantLocation, String participantEmpNo,
			String participantEmpName, String entryUser, LocalDateTime entryTime, String modifyUser,
			LocalDateTime modifyTime, String verifyUser, LocalDateTime verifyTime, String delFlg, String entityFlg,
			String modifyFlg, String mode, String remarks, Date eventToDate, String eventFrequency, String singleFlag,
			String event_status, String organizer_feedback, String participants_feedback) {
		super();
		this.currentDate = currentDate;
		this.eventId = eventId;
		this.eventType = eventType;
		this.eventDescription = eventDescription;
		this.eventDate = eventDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.organizerType = organizerType;
		this.orgEmployeeNo = orgEmployeeNo;
		this.orgEmployeeName = orgEmployeeName;
		this.organizerName = organizerName;
		this.participantLocation = participantLocation;
		this.participantEmpNo = participantEmpNo;
		this.participantEmpName = participantEmpName;
		this.entryUser = entryUser;
		this.entryTime = entryTime;
		this.modifyUser = modifyUser;
		this.modifyTime = modifyTime;
		this.verifyUser = verifyUser;
		this.verifyTime = verifyTime;
		this.delFlg = delFlg;
		this.entityFlg = entityFlg;
		this.modifyFlg = modifyFlg;
		this.mode = mode;
		this.remarks = remarks;
		this.eventToDate = eventToDate;
		this.eventFrequency = eventFrequency;
		this.singleFlag = singleFlag;
		this.event_status = event_status;
		this.organizer_feedback = organizer_feedback;
		this.participants_feedback = participants_feedback;
	}

	public Event_manager_details_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	

	
}