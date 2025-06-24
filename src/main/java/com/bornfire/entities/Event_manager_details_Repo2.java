package com.bornfire.entities;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Event_manager_details_Repo2 extends JpaRepository<Event_manager_details_Entity2, String> {

	@Query(value = "select  * from SERVICE_DETAILS where del_flg='N' ORDER BY SERVICE_NO", nativeQuery = true)
	List<Event_manager_details_Entity2> geteventvalues();
	
	@Query(value = "select  * from SERVICE_DETAILS where del_flg='N'and SERVICE_NO=?1", nativeQuery = true)
	List<Event_manager_details_Entity2> getserviceRecords(String Service_No);
	
	 @Query(value = "SELECT COALESCE(MAX(SERVICE_NO), '1') AS Last_Event_Id FROM SERVICE_DETAILS", nativeQuery =
			  true) 
	String getServiceNo();
	 
	  @Modifying
	  @Transactional
	  @Query(value = "DELETE FROM SERVICE_DETAILS WHERE SERVICE_NO = ?1", nativeQuery = true)
	  void deleteService(String serviceId);
	  
	
}
