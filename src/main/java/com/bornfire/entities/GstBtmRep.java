
package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface GstBtmRep extends JpaRepository<GstBtmEntity, String>{


	@Query(value = "SELECT *FROM gst_btm WHERE gst_type='Received' AND EXTRACT(MONTH FROM tran_date) =?1 AND EXTRACT(YEAR FROM tran_date) =?2 ORDER BY TRAN_ID", nativeQuery = true) 
	 List<GstBtmEntity> getByIndia(String Month,String Year);
	@Query(value = "SELECT *FROM gst_btm WHERE  TRAN_ID=?1", nativeQuery = true) 
	 List<GstBtmEntity> getByIndia1(String Month,String Year);
	 
	 @Query(value = "SELECT * FROM gst_btm WHERE gst_type = 'Paid' AND EXTRACT(MONTH FROM tran_date) =?1 AND EXTRACT(YEAR FROM tran_date) =?2 ORDER BY TRAN_ID", nativeQuery = true) 
	 List<GstBtmEntity> getOnIndia(String Month,String Year);
	 
	 @Query(value = "SELECT * FROM GST_BTM where uniqueid=?1 ", nativeQuery = true)
	 GstBtmEntity findByTran(String uniqueid);
	 
	 @Query(value = "INSERT INTO gst_btm "
	            + "SELECT * FROM gst_view "
	            + "WHERE EXTRACT(MONTH FROM tran_date) = ?1 AND EXTRACT(YEAR FROM tran_date) = ?2", nativeQuery = true)
	    void getInsert(String month, String year);

}

