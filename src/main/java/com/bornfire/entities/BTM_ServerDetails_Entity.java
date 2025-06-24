package com.bornfire.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;


@Entity
@Table(name = "BTM_Server_Details")
public class BTM_ServerDetails_Entity {

	
    private String licence_no;
    private String brand;
    @Id
    private String ipaddress;
    private String model;
    private String macaddress;
    private String harddisk;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateofpurchase; 
    private String ram;
    private String purchasefrom;
    private String server;
    private String oracleversion;
    private String directory;
    private String oracle_home;
    private String database_name;
    private String datafiles;
    private String oraclesid;
    private String logfiles;
    private String filepath;
    private String controlfile;
    private String application_name;
    private String application_path;
    private String app_git_details;
    private String app_services;
    private String app_users;
    private String app_accessibility;
    private String app_owner;
	public String getLicence_no() {
		return licence_no;
	}
	public void setLicence_no(String licence_no) {
		this.licence_no = licence_no;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getMacaddress() {
		return macaddress;
	}
	public void setMacaddress(String macaddress) {
		this.macaddress = macaddress;
	}
	public String getHarddisk() {
		return harddisk;
	}
	public void setHarddisk(String harddisk) {
		this.harddisk = harddisk;
	}
	public Date getDateofpurchase() {
		return dateofpurchase;
	}
	public void setDateofpurchase(Date dateofpurchase) {
		this.dateofpurchase = dateofpurchase;
	}
	public String getRam() {
		return ram;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}
	public String getPurchasefrom() {
		return purchasefrom;
	}
	public void setPurchasefrom(String purchasefrom) {
		this.purchasefrom = purchasefrom;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getOracleversion() {
		return oracleversion;
	}
	public void setOracleversion(String oracleversion) {
		this.oracleversion = oracleversion;
	}
	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	public String getOracle_home() {
		return oracle_home;
	}
	public void setOracle_home(String oracle_home) {
		this.oracle_home = oracle_home;
	}
	public String getDatabase_name() {
		return database_name;
	}
	public void setDatabase_name(String database_name) {
		this.database_name = database_name;
	}
	public String getDatafiles() {
		return datafiles;
	}
	public void setDatafiles(String datafiles) {
		this.datafiles = datafiles;
	}
	public String getOraclesid() {
		return oraclesid;
	}
	public void setOraclesid(String oraclesid) {
		this.oraclesid = oraclesid;
	}
	public String getLogfiles() {
		return logfiles;
	}
	public void setLogfiles(String logfiles) {
		this.logfiles = logfiles;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getControlfile() {
		return controlfile;
	}
	public void setControlfile(String controlfile) {
		this.controlfile = controlfile;
	}
	public String getApplication_name() {
		return application_name;
	}
	public void setApplication_name(String application_name) {
		this.application_name = application_name;
	}
	public String getApplication_path() {
		return application_path;
	}
	public void setApplication_path(String application_path) {
		this.application_path = application_path;
	}
	public String getApp_git_details() {
		return app_git_details;
	}
	public void setApp_git_details(String app_git_details) {
		this.app_git_details = app_git_details;
	}
	public String getApp_services() {
		return app_services;
	}
	public void setApp_services(String app_services) {
		this.app_services = app_services;
	}
	public String getApp_users() {
		return app_users;
	}
	public void setApp_users(String app_users) {
		this.app_users = app_users;
	}
	public String getApp_accessibility() {
		return app_accessibility;
	}
	public void setApp_accessibility(String app_accessibility) {
		this.app_accessibility = app_accessibility;
	}
	public String getApp_owner() {
		return app_owner;
	}
	public void setApp_owner(String app_owner) {
		this.app_owner = app_owner;
	}
	public BTM_ServerDetails_Entity(String licence_no, String brand, String ipaddress, String model, String macaddress,
			String harddisk, Date dateofpurchase, String ram, String purchasefrom, String server, String oracleversion,
			String directory, String oracle_home, String database_name, String datafiles, String oraclesid,
			String logfiles, String filepath, String controlfile, String application_name, String application_path,
			String app_git_details, String app_services, String app_users, String app_accessibility, String app_owner) {
		super();
		this.licence_no = licence_no;
		this.brand = brand;
		this.ipaddress = ipaddress;
		this.model = model;
		this.macaddress = macaddress;
		this.harddisk = harddisk;
		this.dateofpurchase = dateofpurchase;
		this.ram = ram;
		this.purchasefrom = purchasefrom;
		this.server = server;
		this.oracleversion = oracleversion;
		this.directory = directory;
		this.oracle_home = oracle_home;
		this.database_name = database_name;
		this.datafiles = datafiles;
		this.oraclesid = oraclesid;
		this.logfiles = logfiles;
		this.filepath = filepath;
		this.controlfile = controlfile;
		this.application_name = application_name;
		this.application_path = application_path;
		this.app_git_details = app_git_details;
		this.app_services = app_services;
		this.app_users = app_users;
		this.app_accessibility = app_accessibility;
		this.app_owner = app_owner;
	}
	public BTM_ServerDetails_Entity() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	
	
	
}
