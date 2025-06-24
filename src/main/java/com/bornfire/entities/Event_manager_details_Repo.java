package com.bornfire.entities;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Event_manager_details_Repo extends JpaRepository<Event_manager_details_Entity, String> {

	@Query(value = "select  * from EVENT_MANAGER_DETAILS ORDER BY TO_NUMBER(EVENT_ID) ASC", nativeQuery = true)
	List<Event_manager_details_Entity> geteventvalues();
	
	@Query(value = "select  * from EVENT_MANAGER_DETAILS where Event_Id=?1 ", nativeQuery = true)
	List<Event_manager_details_Entity> getrecords(String eventId);
	
	@Query(value = "select  * from EVENT_MANAGER_DETAILS where Event_Id=?1 ", nativeQuery = true)
	List<Event_manager_details_Entity> getrecordsmodify(String eventId);
	
	  @Query(value = "SELECT COALESCE(MAX(Event_Id), '1') AS Last_Event_Id FROM EVENT_MANAGER_DETAILS", nativeQuery =
	  true) 
	  String geteventNO1();
	 
	  @Modifying
	  @Transactional
	  @Query(value = "DELETE FROM EVENT_MANAGER_DETAILS WHERE Event_Id = ?1", nativeQuery = true)
	  void deleteEvent(String eventId);
	  

	  @Query(value = "SELECT * FROM EVENT_MANAGER_DETAILS WHERE Event_Id = ?1", nativeQuery = true)
	  Event_manager_details_Entity getIdvalues(String eventId); 
	  
	  @Query(value = "SELECT ORG_EMPLOYEE_NO FROM EVENT_MANAGER_DETAILS WHERE Event_Id = ?1", nativeQuery = true)
	  String getFeedback(String eventId); 
	  
	  @Query(value = "SELECT * FROM EVENT_MANAGER_DETAILS WHERE Event_Id = ?1", nativeQuery = true)
	  Event_manager_details_Entity getIdvalues2(String eventId); 
	  
	  @Query(value = "SELECT PARTICIPANT_EMP_NO FROM EVENT_MANAGER_DETAILS WHERE Event_Id = ?1", nativeQuery = true)
	  String getParFeedback(String eventId); 
	  
	  @Query(value = "select ORG_EMPLOYEE_NO,PARTICIPANT_EMP_NO from EVENT_MANAGER_DETAILS where Event_Id=?1 ", nativeQuery = true)
	  List<Object[]> getorgpar(String eventId);
}


