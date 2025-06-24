package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BTMEventMasterRep extends CrudRepository<BTMEventMaster, String> {
	
	public Optional<BTMEventMaster> findById(String empId);
	
	@Query(value = "SELECT * FROM EVENT_MASTER ORDER BY SRL_NO", nativeQuery = true)
	List<BTMEventMaster> getScreenlist();
	

}
