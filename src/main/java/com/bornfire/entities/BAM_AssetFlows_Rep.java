package com.bornfire.entities;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BAM_AssetFlows_Rep extends CrudRepository<BAM_AssetFlows_Entity, BigDecimal>{

	  @Query(value = "SELECT * FROM BTM_FLOW_ASSETS b WHERE b.rowid IN (SELECT MIN(rowid) FROM BTM_FLOW_ASSETS where gen_delete_flg is null and sale_flg is null GROUP BY asset_name)", nativeQuery = true)
	  List<BAM_AssetFlows_Entity> getdata();
	
	  
	  @Query(value = "SELECT * from BTM_FLOW_ASSETS where asset_serial_no = ?1 ",nativeQuery = true) 
	  List<BAM_AssetFlows_Entity>  getview(String asset_serial_no);
	  
	  
	  @Query(value = "SELECT DISTINCT asset_serial_no FROM BTM_FLOW_ASSETS", nativeQuery = true)
	    List<String>  getdatas();

	    @Query(value = "SELECT * FROM BTM_FLOW_ASSETS WHERE TO_CHAR(gen_flow_strt_date, 'dd-MM-yyyy') = :currentDate", nativeQuery = true)
	  List<BAM_AssetFlows_Entity> getDep(LocalDate currentDate);
	  
	  @Query(value = "SELECT * from BTM_FLOW_ASSETS where srl_no = ?1 ",nativeQuery = true) 
	  BAM_AssetFlows_Entity srl_noget(String srl_no);
	  
	  @Query(value = "SELECT * from BTM_FLOW_ASSETS where gen_tran_id = ?1 ",nativeQuery = true) 
	  BAM_AssetFlows_Entity gettranview(String gen_tran_id);
	  
	  
	  @Query(value = "Select * from BTM_FLOW_ASSETS where gen_tran_id! ='0' ",nativeQuery = true) 
	  List<BAM_AssetFlows_Entity> gettrandata();
	  

	  @Query(value = "Select *from BTM_FLOW_ASSETS where TO_CHAR(gen_flow_strt_date, 'dd-MM-yyyy') < :currentDate AND GEN_FLOW_ID='DEPR'",nativeQuery = true) 
	  List<BAM_AssetFlows_Entity> getprevbatchjobs(LocalDate currentDate);
	  
	  @Query(value = "Select count(*)from BTM_FLOW_ASSETS where gen_flow_strt_date >=TO_DATE(?1,'dd-MM-YYYY') AND gen_flow_strt_date <=TO_DATE(?2,'dd-MM-YYYY') AND GEN_FLOW_ID='ACQN'",nativeQuery = true) 
	  int gettotal(String stryear,String endyear);
	  
	  @Query(value = "Select count(*)from BTM_FLOW_ASSETS where TO_CHAR(gen_flow_strt_date,'MM-YYYY')=?1 AND GEN_FLOW_ID='DEPR'",nativeQuery = true) 
	  int getdep(String monthyear);
	  
	  @Query(value = "Select NVL(sum(gen_original_cost),0)from BTM_FLOW_ASSETS where TO_CHAR(gen_flow_strt_date,'MM-YYYY')=?1 AND GEN_FLOW_ID='DEPR'",nativeQuery = true) 
	  int getintialcost(String monthyear);
	  
	  @Query(value = "Select NVL(sum(gen_book_value),0)from BTM_FLOW_ASSETS where TO_CHAR(gen_flow_strt_date,'MM-YYYY')=?1 AND GEN_FLOW_ID='DEPR'",nativeQuery = true) 
	  int getbookvalue(String monthyear);
	  
	  @Query(value = "SELECT * FROM BTM_FLOW_ASSETS WHERE asset_serial_no = ?1", nativeQuery = true) 
	  List<BAM_AssetFlows_Entity> findBySrlNo(String asset_serial_no);

	    @Query(value = "SELECT * FROM BTM_FLOW_ASSETS WHERE asset_serial_no = ?1 ORDER BY srl_no DESC FETCH FIRST 1 ROWS ONLY", nativeQuery = true)
	    List<BAM_AssetFlows_Entity> findLatestByAssetSerialNo(String asset_serial_no); 
	    
	    @Query(value = "SELECT * FROM BTM_FLOW_ASSETS WHERE asset_serial_no = ?1 ", nativeQuery = true)
	    List<BAM_AssetFlows_Entity> getRecords(String headcode);
}