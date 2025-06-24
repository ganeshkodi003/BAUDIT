package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GstRep extends JpaRepository<gst, String> {

	//public Optional<InvoiceMaster> findById(String invoice_no);
	
	@Query(value = "SELECT *FROM gst_view WHERE gst_type='Recived' AND EXTRACT(MONTH FROM tran_date) =?1 AND EXTRACT(YEAR FROM tran_date) =?2 ORDER BY TRAN_ID", nativeQuery = true) 
	 List<gst> getByIndia(String Month,String Year);
	 
	 @Query(value = " SELECT * FROM gst_view  EXTRACT(MONTH FROM tran_date) =?1 AND EXTRACT(YEAR FROM tran_date) =?2 ORDER BY TRAN_ID", nativeQuery = true) 
	 List<gst> getOnIndia(String Month,String Year);
	 
	 @Query(value = "SELECT * FROM gst_view WHERE EXTRACT(MONTH FROM tran_date) =?1 AND EXTRACT(YEAR FROM tran_date) =?2", nativeQuery = true) 
	 List<gst> getData(String Month,String Year);
	 
	 @Query(value = "SELECT * FROM gst_view WHERE EXTRACT(MONTH FROM tran_date) = ?1 AND EXTRACT(YEAR FROM tran_date) = ?2 ORDER BY TRAN_ID", nativeQuery = true)
		List<gst> getDatagst(String a, String b);
	 
	
}