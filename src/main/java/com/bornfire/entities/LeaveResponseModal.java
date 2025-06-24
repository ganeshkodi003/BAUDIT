package com.bornfire.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
@Component
public class LeaveResponseModal {
	private String  dayofmonth;
	private String  day;
	private int  srlno;
	private String  leavedesc;
	private String  leavecount;
	public String getDayofmonth() {
		return dayofmonth;
	}
	public void setDayofmonth(String dayofmonth) {
		this.dayofmonth = dayofmonth;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public int getSrlno() {
		return srlno;
	}
	public void setSrlno(int i) {
		this.srlno = i;
	}
	public String getLeavedesc() {
		return leavedesc;
	}
	public void setLeavedesc(String leavedesc) {
		this.leavedesc = leavedesc;
	}
	public String getLeavecount() {
		return leavecount;
	}
	public void setLeavecount(String leavecount) {
		this.leavecount = leavecount;
	}
	public LeaveResponseModal(String dayofmonth, String day, int srlno, String leavedesc, String leavecount) {
		super();
		this.dayofmonth = dayofmonth;
		this.day = day;
		this.srlno = srlno;
		this.leavedesc = leavedesc;
		this.leavecount = leavecount;
	}
	public LeaveResponseModal() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "LeaveResponseModal [dayofmonth=" + dayofmonth + ", day=" + day + ", srlno=" + srlno + ", leavedesc="
				+ leavedesc + ", leavecount=" + leavecount + "]";
	}
	
	
}
