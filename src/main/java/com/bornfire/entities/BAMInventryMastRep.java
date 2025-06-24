package com.bornfire.entities;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BAMInventryMastRep extends JpaRepository<BAMInventorymaster, String> {

	 @Query(value = "SELECT DISTINCT asst_srl_no FROM BTM_BAM_INVENTORY_MASTER", nativeQuery = true)
	    List<String>  getdatas();
	 
	 @Query(value = "SELECT * from BTM_BAM_INVENTORY_MASTER where asst_srl_no = ?1 ",nativeQuery = true) 
	 BAMInventorymaster  getview(String asst_srl_no);
	

	 @Query(value = "SELECT * from BTM_BAM_INVENTORY_MASTER where del_flg='N' ORDER BY ASST_ACCT_NO ASC ",nativeQuery = true) 
	 List<BAMInventorymaster>  getall();


	 @Query(value = "SELECT * FROM BTM_BAM_INVENTORY_MASTER ORDER BY entry_time DESC FETCH FIRST ROW ONLY", nativeQuery = true)
	 BAMInventorymaster findLatestRecord();
	 	 
	 @Query(value = "SELECT asst_srl_no FROM BTM_BAM_INVENTORY_MASTER ORDER BY entry_time DESC FETCH FIRST ROW ONLY", nativeQuery = true)
	 String findLatestAssetSerialNumber();
 
	 @Query(value = "SELECT count(*) from BTM_BAM_INVENTORY_MASTER where TO_CHAR(date_of_purchase,'MM-YYYY')=?1",nativeQuery = true) 
	  int getdata(String monthyear);
	 
	 @Query(value = "SELECT count(*) from BTM_BAM_INVENTORY_MASTER where date_of_purchase>=TO_DATE(?1,'dd-MM-YYYY') and date_of_purchase<=TO_DATE(?2,'dd-MM-YYYY')",nativeQuery = true) 
	  int gettotal(String stryear,String endyear);
	 
}
