package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface MASTER_TABLE_AMC_DAILYLIST_Repo extends JpaRepository<MASTER_TABLE_AMC_DAILYLIST, String>{

	
	
	 
	 @Query(value = "select * from MASTER_TABLE_AMC_DAILYLIST WHERE resource_name=?1", nativeQuery = true)
	List<	MASTER_TABLE_AMC_DAILYLIST> findbypopup(String resource_name);
	
	
	@Query(value="select * from MASTER_TABLE_AMC_DAILYLIST WHERE resource_name=?1",nativeQuery=true)
	MASTER_TABLE_AMC_DAILYLIST findByUserId(String userid);
}
