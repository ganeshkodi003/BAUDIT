package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface DocumentMainRep extends JpaRepository<DocumentMaintenance, String> {
 
	@Query(value = "SELECT * FROM DOCUMENT_MAINTENANCE", nativeQuery = true)
	List<DocumentMaintenance> Documents();
	
	@Query(value = "SELECT * FROM DOCUMENT_MAINTENANCE where emp_id=?1 AND srl_no=?2 ", nativeQuery = true)
	DocumentMaintenance Documentview(String emp_id,String srl_no);
	
	

	@Query(value = "SELECT * FROM DOCUMENT_MAINTENANCE where emp_id=?1 AND srl_no=?2 ", nativeQuery = true)
	DocumentMaintenance getEmployeeData(String emp_id,String srl_no);
	  @Query("SELECT MAX(d.srl_no) FROM DocumentMaintenance d")
	    String findMaxSrlNo();
}
