package com.bornfire.entities;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BTMTravelMasterRep extends CrudRepository<BTMTravelMaster, String> {

	public Optional<BTMTravelMaster> findById(String empId);

	@Query(value = "SELECT * FROM TRAVEL_MASTER", nativeQuery = true)
	List<BTMTravelMaster> getTravellist();
	
	@Query(value = "SELECT * FROM TRAVEL_MASTER where Tra_status!='Completed' and Tra_status!='Rejected' and del_flg='N'", nativeQuery = true)
	List<BTMTravelMaster> getTravellist1();
	
	@Query(value = "select * from TRAVEL_MASTER  where tra_ref=?1", nativeQuery = true)
	BTMTravelMaster getTravelMaster(String resId);
	
	@Query(value = "select * from TRAVEL_MASTER  where ass_id=?1", nativeQuery = true)
	List<BTMTravelMaster> getTravelListbyid(String Id);
	
}

