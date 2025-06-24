package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PlacementMaintenanceRep extends JpaRepository<PlacementMaintenance, String>{
	

	@Query(value = "SELECT DISTINCT * FROM BTM_PLACEMENT_MASTER", nativeQuery = true)

	List<PlacementMaintenance> getMaintenance();
	
	@Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER", nativeQuery = true)

	List<PlacementMaintenance> getinvlist();
	
	@Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER ORDER BY INV_NO", nativeQuery = true)

	List<PlacementMaintenance> getpodetails();
	

	@Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER WHERE sp IN ('WHITESTONE', 'STACKPOS') ORDER BY INV_NO", nativeQuery = true)
	List<PlacementMaintenance> getpodetails1();


	
	
	@Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER where PO_ID =?1 ORDER BY INV_NO", nativeQuery = true)

        PlacementMaintenance getpodetailsedit(String poid);
	
	@Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER where PO_ID =?1", nativeQuery = true)

    PlacementMaintenance getpodetails_exist(String poid);
	
	@Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER where FLAG='Y'", nativeQuery = true)

	List<PlacementMaintenance> getSdetail();
	
	@Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER where FLAG='N'", nativeQuery = true)

	List<PlacementMaintenance> getFdetail();

	@Query(value = "SELECT  DISTINCT * FROM BTM_PLACEMENT_MASTER WHERE PO_ID =?1", nativeQuery = true)
	List<PlacementMaintenance> getPoMaintenance(String po_id);
	
	@Query(value = "SELECT  * FROM BTM_PLACEMENT_MASTER WHERE PO_NO =?1", nativeQuery = true)
	List<PlacementMaintenance> getPoMain(String po_no);
	
	@Query(value = "SELECT  * FROM BTM_PLACEMENT_MASTER WHERE PO_ID =?1 ", nativeQuery = true)
	PlacementMaintenance getPoM(String po_id);
	
	@Query(value = "SELECT  * FROM BTM_PLACEMENT_MASTER WHERE PO_ID =?1", nativeQuery = true)
	PlacementMaintenance getPoId(String po_id);
	
	@Query(value = "SELECT  * FROM BTM_PLACEMENT_MASTER WHERE INV_NO =?1", nativeQuery = true)
	PlacementMaintenance getinvoicelist(String inv_no);

	 @Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER WHERE PO_ID =?1",  nativeQuery = true)
	 PlacementMaintenance getMaintenancelist(String po_id);
	 
	 @Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER where  FLAG='Y'", nativeQuery = true)
	 List<PlacementMaintenance> getReupload(String upload_date);
	 
	 @Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER where  PO_NO=?1", nativeQuery = true)
	 List<PlacementMaintenance> getPolist(String po_no); 
	 
	 @Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER where  INV_NO=?1", nativeQuery = true)
	PlacementMaintenance getAlist(String inv_no); 
	 
	 @Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER where  INV_NO=?1 AND inv_date=?2", nativeQuery = true)
		PlacementMaintenance remitenceupload(List<String> invoiceNumbers ,String formattedDate); 
	 
	 @Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER WHERE INV_NO IN (?1) AND inv_date = TO_DATE(?2, 'DD-MM-YYYY')", nativeQuery = true)
	 List<PlacementMaintenance> findByInvoiceNumbersAndDate(List<String> invoiceNumbers, String formattedDate);
	 
	 @Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER WHERE po_no =?1 AND po_month =?2", nativeQuery = true)
	 PlacementMaintenance findByInvoiceNumbersAndDate_spinvoice(String numericValue, String monthYearResult);

	 @Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER WHERE Emp_name =?1 AND po_month =?2", nativeQuery = true)
	 PlacementMaintenance findByInvoiceNumbersAndDate_spinvoice_stackpos(String numericValue, String monthYearResult);
	 
	 @Query(value = "select * from BTM_PLACEMENT_MASTER WHERE po_id =?1  ", nativeQuery = true) 
	 PlacementMaintenance getplacementlist2(String po_id);
	 
	 @Query(value = "select * from BTM_PLACEMENT_MASTER WHERE po_id =?1  ", nativeQuery = true) 
	 PlacementMaintenance getplacementlistgst(String po_id);
	 
	 
	 @Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER WHERE grn_no IS NULL AND TO_CHAR(TO_DATE(po_month, 'YYYY-MON'), 'YYYY-MM') < TO_CHAR(TRUNC(SYSDATE, 'MM'), 'YYYY-MM')", nativeQuery = true) 
	 List<PlacementMaintenance> getponulldetails();
	 
	 @Query(value = "SELECT * FROM btm_placement_master WHERE sp IS NOT NULL AND grn_no IS NOT NULL AND sp = ?1 AND inv_due_date >= ?2 and inv_due_date <= ?3" , nativeQuery = true) 
	 List<PlacementMaintenance> getUpdate(String sp,String inv_due_Date,String inv_date);
	 
	 @Query(value = "SELECT * FROM btm_placement_master WHERE sp IS NOT NULL AND grn_no IS NOT NULL AND sp = ?1 AND inv_due_date >= ?2 and inv_due_date <= ?3" , nativeQuery = true) 
	 List<PlacementMaintenance> getsplist(String sp,String inv_due_Date,String inv_date);
	 
	  @Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER WHERE po_id IN :poIds", nativeQuery = true)
	  List<PlacementMaintenance> getPonullDetails2(@Param("poIds") List<String> poIds);
	  
	 @Query(value = "SELECT *FROM BTM_PLACEMENT_MASTER WHERE sp IS NOT NULL AND grn_no IS NOT NULL AND sp='ASOFT'" , nativeQuery = true) 
	 List<PlacementMaintenance> getUpdate1();
	 
	 @Query(value = "SELECT *FROM BTM_PLACEMENT_MASTER WHERE sp IS NOT NULL AND grn_no IS NOT NULL AND sp='STACKPOS'" , nativeQuery = true) 
	 List<PlacementMaintenance> getUpdate2();
	 
	 @Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER where po_month=?1", nativeQuery = true)
		List<Object[]>  getAbsenteesFromDatabase(String po_month,String location,String po_no );
		 @Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER where po_month=?1 AND  grn_no is  null", nativeQuery = true)
			List<PlacementMaintenance>  getAbsenteesFrom(String po_month);
		 
		 
		 @Query(value = "SELECT * FROM BTM_PLACEMENT_MASTER WHERE UPPER(po_month) = UPPER(?1) AND grn_no IS NULL", nativeQuery = true)
		 List<PlacementMaintenance> getAbsenteesFromUppercase(String po_month);

		 @Query(value = "SELECT * from BTM_PLACEMENT_MASTER where grn_no is NOT null", nativeQuery = true)

			List<PlacementMaintenance> grnstsnotnull();
	
		 @Query(value = "SELECT SUM(TO_NUMBER(REPLACE(TRIM(SP_INV_AMT), ',', ''))) FROM btm_placement_master WHERE sp IS NOT NULL AND grn_no IS NOT NULL AND sp = ?1 AND REGEXP_LIKE(REPLACE(TRIM(SP_INV_AMT), ',', ''), '^-?\\d+(\\.\\d+)?$') AND inv_due_date BETWEEN TO_DATE(?2, 'DD-MON-YYYY') AND TO_DATE(?3, 'DD-MON-YYYY')", nativeQuery = true)
		 BigDecimal getSumSpInvAmt(String sp, String fromDate, String toDate);
		 
		 @Query(value = "SELECT SUM(TO_NUMBER(REPLACE(TRIM(SP_INV_TOT_GST), ',', ''))) FROM btm_placement_master WHERE sp IS NOT NULL AND grn_no IS NOT NULL AND sp = ?1 AND REGEXP_LIKE(REPLACE(TRIM(SP_INV_TOT_GST), ',', ''), '^-?\\d+(\\.\\d+)?$') AND inv_due_date BETWEEN TO_DATE(?2, 'DD-MON-YYYY') AND TO_DATE(?3, 'DD-MON-YYYY')", nativeQuery = true)
		 BigDecimal getSumSpInvTotGst(String sp, String fromDate, String toDate);
		 
		 @Query(value = "SELECT SUM(TO_NUMBER(REPLACE(TRIM(SP_INV_TOT_AMT), ',', ''))) FROM btm_placement_master WHERE sp IS NOT NULL AND grn_no IS NOT NULL AND sp = ?1 AND REGEXP_LIKE(REPLACE(TRIM(SP_INV_TOT_AMT), ',', ''), '^-?\\d+(\\.\\d+)?$') AND inv_due_date BETWEEN TO_DATE(?2, 'DD-MON-YYYY') AND TO_DATE(?3, 'DD-MON-YYYY')", nativeQuery = true)
		 BigDecimal getSumSpInvTotAmt(String sp, String fromDate, String toDate);
}


