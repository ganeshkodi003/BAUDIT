package com.bornfire.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Entity
@Table(name = "DAILY_UPDATE_SERVER")
public class Daily_server_update_entity {
	private String year; 
  
	
    private String month;  
    private String brand;  
    private String ip_address;  
    private String model;  
    private String hard_disk;  
    private String date_of_purchase;  
    private String ram;  
    private String purchase_from;  
    private String server; 
    @Id
    private String uniqueid; 
	@DateTimeFormat(pattern = "dd-MM-yyyy")

    private Date date_of_service;  
    private String application_name; 
    private String server_name; 
    private String status;  
    private String location;  
    private String startup_time;  
    private String shutdown_time;  
    private String oracle_sid;  
    private String remarks;
    private String day1;
    private String day2;
    private String day3;
    private String day4;
    private String day5;
    private String day6;
    private String day7;
    private String day8;
    private String day9;
    private String day10;
    private String day11;
    private String day12;
    private String day13;
    private String day14;
    private String day15;
    private String day16;
    private String day17;
    private String day18;
    private String day19;
    private String day20;
    private String day21;
    private String day22;
    private String day23;
    private String day24;
    private String day25;
    private String day26;
    private String day27;
    private String day28;
    private String day29;
    private String day30;
    private String day31;
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getHard_disk() {
		return hard_disk;
	}
	public void setHard_disk(String hard_disk) {
		this.hard_disk = hard_disk;
	}
	public String getDate_of_purchase() {
		return date_of_purchase;
	}
	public void setDate_of_purchase(String date_of_purchase) {
		this.date_of_purchase = date_of_purchase;
	}
	public String getRam() {
		return ram;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}
	public String getPurchase_from() {
		return purchase_from;
	}
	public void setPurchase_from(String purchase_from) {
		this.purchase_from = purchase_from;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getUniqueid() {
		return uniqueid;
	}
	public void setUniqueid(String uniqueid) {
		this.uniqueid = uniqueid;
	}
	public Date getDate_of_service() {
		return date_of_service;
	}
	public void setDate_of_service(Date date_of_service) {
		this.date_of_service = date_of_service;
	}
	public String getApplication_name() {
		return application_name;
	}
	public void setApplication_name(String application_name) {
		this.application_name = application_name;
	}
	public String getServer_name() {
		return server_name;
	}
	public void setServer_name(String server_name) {
		this.server_name = server_name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStartup_time() {
		return startup_time;
	}
	public void setStartup_time(String startup_time) {
		this.startup_time = startup_time;
	}
	public String getShutdown_time() {
		return shutdown_time;
	}
	public void setShutdown_time(String shutdown_time) {
		this.shutdown_time = shutdown_time;
	}
	public String getOracle_sid() {
		return oracle_sid;
	}
	public void setOracle_sid(String oracle_sid) {
		this.oracle_sid = oracle_sid;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getDay1() {
		return day1;
	}
	public void setDay1(String day1) {
		this.day1 = day1;
	}
	public String getDay2() {
		return day2;
	}
	public void setDay2(String day2) {
		this.day2 = day2;
	}
	public String getDay3() {
		return day3;
	}
	public void setDay3(String day3) {
		this.day3 = day3;
	}
	public String getDay4() {
		return day4;
	}
	public void setDay4(String day4) {
		this.day4 = day4;
	}
	public String getDay5() {
		return day5;
	}
	public void setDay5(String day5) {
		this.day5 = day5;
	}
	public String getDay6() {
		return day6;
	}
	public void setDay6(String day6) {
		this.day6 = day6;
	}
	public String getDay7() {
		return day7;
	}
	public void setDay7(String day7) {
		this.day7 = day7;
	}
	public String getDay8() {
		return day8;
	}
	public void setDay8(String day8) {
		this.day8 = day8;
	}
	public String getDay9() {
		return day9;
	}
	public void setDay9(String day9) {
		this.day9 = day9;
	}
	public String getDay10() {
		return day10;
	}
	public void setDay10(String day10) {
		this.day10 = day10;
	}
	public String getDay11() {
		return day11;
	}
	public void setDay11(String day11) {
		this.day11 = day11;
	}
	public String getDay12() {
		return day12;
	}
	public void setDay12(String day12) {
		this.day12 = day12;
	}
	public String getDay13() {
		return day13;
	}
	public void setDay13(String day13) {
		this.day13 = day13;
	}
	public String getDay14() {
		return day14;
	}
	public void setDay14(String day14) {
		this.day14 = day14;
	}
	public String getDay15() {
		return day15;
	}
	public void setDay15(String day15) {
		this.day15 = day15;
	}
	public String getDay16() {
		return day16;
	}
	public void setDay16(String day16) {
		this.day16 = day16;
	}
	public String getDay17() {
		return day17;
	}
	public void setDay17(String day17) {
		this.day17 = day17;
	}
	public String getDay18() {
		return day18;
	}
	public void setDay18(String day18) {
		this.day18 = day18;
	}
	public String getDay19() {
		return day19;
	}
	public void setDay19(String day19) {
		this.day19 = day19;
	}
	public String getDay20() {
		return day20;
	}
	public void setDay20(String day20) {
		this.day20 = day20;
	}
	public String getDay21() {
		return day21;
	}
	public void setDay21(String day21) {
		this.day21 = day21;
	}
	public String getDay22() {
		return day22;
	}
	public void setDay22(String day22) {
		this.day22 = day22;
	}
	public String getDay23() {
		return day23;
	}
	public void setDay23(String day23) {
		this.day23 = day23;
	}
	public String getDay24() {
		return day24;
	}
	public void setDay24(String day24) {
		this.day24 = day24;
	}
	public String getDay25() {
		return day25;
	}
	public void setDay25(String day25) {
		this.day25 = day25;
	}
	public String getDay26() {
		return day26;
	}
	public void setDay26(String day26) {
		this.day26 = day26;
	}
	public String getDay27() {
		return day27;
	}
	public void setDay27(String day27) {
		this.day27 = day27;
	}
	public String getDay28() {
		return day28;
	}
	public void setDay28(String day28) {
		this.day28 = day28;
	}
	public String getDay29() {
		return day29;
	}
	public void setDay29(String day29) {
		this.day29 = day29;
	}
	public String getDay30() {
		return day30;
	}
	public void setDay30(String day30) {
		this.day30 = day30;
	}
	public String getDay31() {
		return day31;
	}
	public void setDay31(String day31) {
		this.day31 = day31;
	}
	@Override
	public String toString() {
		return "Daily_server_update_entity [year=" + year + ", month=" + month + ", brand=" + brand + ", ip_address="
				+ ip_address + ", model=" + model + ", hard_disk=" + hard_disk + ", date_of_purchase="
				+ date_of_purchase + ", ram=" + ram + ", purchase_from=" + purchase_from + ", server=" + server
				+ ", uniqueid=" + uniqueid + ", date_of_service=" + date_of_service + ", application_name="
				+ application_name + ", server_name=" + server_name + ", status=" + status + ", location=" + location
				+ ", startup_time=" + startup_time + ", shutdown_time=" + shutdown_time + ", oracle_sid=" + oracle_sid
				+ ", remarks=" + remarks + ", day1=" + day1 + ", day2=" + day2 + ", day3=" + day3 + ", day4=" + day4
				+ ", day5=" + day5 + ", day6=" + day6 + ", day7=" + day7 + ", day8=" + day8 + ", day9=" + day9
				+ ", day10=" + day10 + ", day11=" + day11 + ", day12=" + day12 + ", day13=" + day13 + ", day14=" + day14
				+ ", day15=" + day15 + ", day16=" + day16 + ", day17=" + day17 + ", day18=" + day18 + ", day19=" + day19
				+ ", day20=" + day20 + ", day21=" + day21 + ", day22=" + day22 + ", day23=" + day23 + ", day24=" + day24
				+ ", day25=" + day25 + ", day26=" + day26 + ", day27=" + day27 + ", day28=" + day28 + ", day29=" + day29
				+ ", day30=" + day30 + ", day31=" + day31 + ", getYear()=" + getYear() + ", getMonth()=" + getMonth()
				+ ", getBrand()=" + getBrand() + ", getIp_address()=" + getIp_address() + ", getModel()=" + getModel()
				+ ", getHard_disk()=" + getHard_disk() + ", getDate_of_purchase()=" + getDate_of_purchase()
				+ ", getRam()=" + getRam() + ", getPurchase_from()=" + getPurchase_from() + ", getServer()="
				+ getServer() + ", getUniqueid()=" + getUniqueid() + ", getDate_of_service()=" + getDate_of_service()
				+ ", getApplication_name()=" + getApplication_name() + ", getServer_name()=" + getServer_name()
				+ ", getStatus()=" + getStatus() + ", getLocation()=" + getLocation() + ", getStartup_time()="
				+ getStartup_time() + ", getShutdown_time()=" + getShutdown_time() + ", getOracle_sid()="
				+ getOracle_sid() + ", getRemarks()=" + getRemarks() + ", getDay1()=" + getDay1() + ", getDay2()="
				+ getDay2() + ", getDay3()=" + getDay3() + ", getDay4()=" + getDay4() + ", getDay5()=" + getDay5()
				+ ", getDay6()=" + getDay6() + ", getDay7()=" + getDay7() + ", getDay8()=" + getDay8() + ", getDay9()="
				+ getDay9() + ", getDay10()=" + getDay10() + ", getDay11()=" + getDay11() + ", getDay12()=" + getDay12()
				+ ", getDay13()=" + getDay13() + ", getDay14()=" + getDay14() + ", getDay15()=" + getDay15()
				+ ", getDay16()=" + getDay16() + ", getDay17()=" + getDay17() + ", getDay18()=" + getDay18()
				+ ", getDay19()=" + getDay19() + ", getDay20()=" + getDay20() + ", getDay21()=" + getDay21()
				+ ", getDay22()=" + getDay22() + ", getDay23()=" + getDay23() + ", getDay24()=" + getDay24()
				+ ", getDay25()=" + getDay25() + ", getDay26()=" + getDay26() + ", getDay27()=" + getDay27()
				+ ", getDay28()=" + getDay28() + ", getDay29()=" + getDay29() + ", getDay30()=" + getDay30()
				+ ", getDay31()=" + getDay31() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	public Daily_server_update_entity(String year, String month, String brand, String ip_address, String model,
			String hard_disk, String date_of_purchase, String ram, String purchase_from, String server, String uniqueid,
			Date date_of_service, String application_name, String server_name, String status, String location,
			String startup_time, String shutdown_time, String oracle_sid, String remarks, String day1, String day2,
			String day3, String day4, String day5, String day6, String day7, String day8, String day9, String day10,
			String day11, String day12, String day13, String day14, String day15, String day16, String day17,
			String day18, String day19, String day20, String day21, String day22, String day23, String day24,
			String day25, String day26, String day27, String day28, String day29, String day30, String day31) {
		super();
		this.year = year;
		this.month = month;
		this.brand = brand;
		this.ip_address = ip_address;
		this.model = model;
		this.hard_disk = hard_disk;
		this.date_of_purchase = date_of_purchase;
		this.ram = ram;
		this.purchase_from = purchase_from;
		this.server = server;
		this.uniqueid = uniqueid;
		this.date_of_service = date_of_service;
		this.application_name = application_name;
		this.server_name = server_name;
		this.status = status;
		this.location = location;
		this.startup_time = startup_time;
		this.shutdown_time = shutdown_time;
		this.oracle_sid = oracle_sid;
		this.remarks = remarks;
		this.day1 = day1;
		this.day2 = day2;
		this.day3 = day3;
		this.day4 = day4;
		this.day5 = day5;
		this.day6 = day6;
		this.day7 = day7;
		this.day8 = day8;
		this.day9 = day9;
		this.day10 = day10;
		this.day11 = day11;
		this.day12 = day12;
		this.day13 = day13;
		this.day14 = day14;
		this.day15 = day15;
		this.day16 = day16;
		this.day17 = day17;
		this.day18 = day18;
		this.day19 = day19;
		this.day20 = day20;
		this.day21 = day21;
		this.day22 = day22;
		this.day23 = day23;
		this.day24 = day24;
		this.day25 = day25;
		this.day26 = day26;
		this.day27 = day27;
		this.day28 = day28;
		this.day29 = day29;
		this.day30 = day30;
		this.day31 = day31;
	}
	public Daily_server_update_entity() {
		super();
		// TODO Auto-generated constructor stub
	}
   
    
	

}
