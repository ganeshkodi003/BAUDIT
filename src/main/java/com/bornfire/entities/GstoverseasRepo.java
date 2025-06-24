package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface GstoverseasRepo extends JpaRepository<Gstoverseas, String> {
	
	@Query(value = "SELECT *FROM GST_OVERSEAS ", nativeQuery = true) 
	 List<Gstoverseas> findbygstoverseas();
	
	@Query(value = "SELECT *FROM GST_OVERSEAS WHERE EXTRACT(MONTH FROM tran_date) =?1 AND EXTRACT(YEAR FROM tran_date) =?2 ORDER BY TRAN_ID", nativeQuery = true) 
	 List<Gstoverseas> getBygstoversea(String Month,String Year);

	
	 @Query(value = "SELECT * FROM GST_OVERSEAS where uniqueid=?1 ", nativeQuery = true)
	 Gstoverseas findByTranoverseas(String uniqueid);
	 
	 
	
}