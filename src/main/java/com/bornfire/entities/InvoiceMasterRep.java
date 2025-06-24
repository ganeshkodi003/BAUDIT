package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceMasterRep extends JpaRepository<InvoiceMaster, String> {

	//public Optional<InvoiceMaster> findById(String invoice_no);
	
	@Query(value = "select * from LOC_INVOICE_MASTER WHERE PO_ID =?1  ", nativeQuery = true) 
	 InvoiceMaster getplacementlist2(String po_id);

	@Query(value = "SELECT * FROM loc_invoice_master ORDER BY inv_no", nativeQuery = true)
	List<InvoiceMaster>  getplacementlist();
	
	@Query(value = "SELECT * FROM loc_invoice_master WHERE TO_CHAR(po_delivery_date, 'MM') = ?1 And  TO_CHAR(po_delivery_date, 'yy') = ?2 ORDER BY inv_no", nativeQuery = true)
	List<InvoiceMaster>  getplacementlist1(int month, String yearString);
	
	
	@Query(value = "SELECT EMP_NAME, GRN_NO, PO_DELIVERY_DATE, GRN_DATE, INV_NO, INV_DATE, VENDOR, GRN_AMT, INV_IGST, PO_ID "
	        + "FROM loc_invoice_master WHERE TO_CHAR(po_delivery_date, 'MM-yy') = ?1 "
	        + "ORDER BY inv_no", nativeQuery = true)
	List<Object[]> getgrndate(String monthyear);
	
	
	@Query(value = "SELECT INV_NO,REMIT_AMT, REMIT_DATE, TDS_REC, CREDIT_NOTE, CN_DATE, GRN_AMT, REMITENCE_REFERENCE, PO_ID FROM BTM_PLACEMENT_MASTER WHERE TO_CHAR(po_delivery_date, 'MM-yy') = :monthyear ORDER BY INV_NO", nativeQuery = true)
	List<Object[]> getRemittanceData(String monthyear);




}
