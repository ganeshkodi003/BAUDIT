package com.bornfire.entities;

import java.util.List;
//import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlacementMasterRep extends CrudRepository<PlacementMaster,String>{
		
		public Optional<PlacementMaster> findById(String empId);
		
		@Query(value = "SELECT * FROM PO_MASTER_TABLE where placement_id = ?1 and emp_name= ?2", nativeQuery = true)
		List<PlacementMaster> findByParam(String id1,String id2);
}
