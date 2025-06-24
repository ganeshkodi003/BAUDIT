package com.bornfire.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "GENERAL_INFRA_DETAILS")
public class genral_infra_details_entity {

	
	    private String year;
@Id
	    private String month;

	    private String brand;
	  
	    private String ip_address;

	    private String model;

	    private String mac_address;

	    private String hard_disk;
		@DateTimeFormat(pattern = "dd-MM-yyyy")

	    private Date date_of_purchase;

	    private String ram;

	    private String purchase_from;
	    
	    private String server;
	    private String uniqueid;
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
		public String getMac_address() {
			return mac_address;
		}
		public void setMac_address(String mac_address) {
			this.mac_address = mac_address;
		}
		public String getHard_disk() {
			return hard_disk;
		}
		public void setHard_disk(String hard_disk) {
			this.hard_disk = hard_disk;
		}
		public Date getDate_of_purchase() {
			return date_of_purchase;
		}
		public void setDate_of_purchase(Date date_of_purchase) {
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
		@Override
		public String toString() {
			return "genral_infra_details_entity [year=" + year + ", month=" + month + ", brand=" + brand
					+ ", ip_address=" + ip_address + ", model=" + model + ", mac_address=" + mac_address
					+ ", hard_disk=" + hard_disk + ", date_of_purchase=" + date_of_purchase + ", ram=" + ram
					+ ", purchase_from=" + purchase_from + ", server=" + server + ", uniqueid=" + uniqueid
					+ ", getYear()=" + getYear() + ", getMonth()=" + getMonth() + ", getBrand()=" + getBrand()
					+ ", getIp_address()=" + getIp_address() + ", getModel()=" + getModel() + ", getMac_address()="
					+ getMac_address() + ", getHard_disk()=" + getHard_disk() + ", getDate_of_purchase()="
					+ getDate_of_purchase() + ", getRam()=" + getRam() + ", getPurchase_from()=" + getPurchase_from()
					+ ", getServer()=" + getServer() + ", getUniqueid()=" + getUniqueid() + ", getClass()=" + getClass()
					+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
		}
		public genral_infra_details_entity(String year, String month, String brand, String ip_address, String model,
				String mac_address, String hard_disk, Date date_of_purchase, String ram, String purchase_from,
				String server, String uniqueid) {
			super();
			this.year = year;
			this.month = month;
			this.brand = brand;
			this.ip_address = ip_address;
			this.model = model;
			this.mac_address = mac_address;
			this.hard_disk = hard_disk;
			this.date_of_purchase = date_of_purchase;
			this.ram = ram;
			this.purchase_from = purchase_from;
			this.server = server;
			this.uniqueid = uniqueid;
		}
		public genral_infra_details_entity() {
			super();
			// TODO Auto-generated constructor stub
		}

	
	
	    
}
