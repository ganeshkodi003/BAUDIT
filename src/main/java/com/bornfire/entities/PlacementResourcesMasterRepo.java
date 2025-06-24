package com.bornfire.entities;

import java.util.List;
//import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlacementResourcesMasterRepo extends CrudRepository<PlacementResourceMaster,String>{
	
	public Optional<PlacementResourceMaster> findById(String empId);
	
	 @Query(value = "select * from PLACEMENT_RESOURCE_MASTER ", nativeQuery = true) 
	 List<PlacementResourceMaster> getplacementlist();
	 
	 @Query(value = "select COUNT(RESOURCE_ID) from PLACEMENT_RESOURCE_MASTER WHERE DEL_FLG = 'Y' AND ENTITY_FLG = 'Y' ", nativeQuery = true) 
	 int gettotalnum();

	
}
