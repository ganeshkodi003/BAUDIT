package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface BTM_ServerDetails_Repo extends JpaRepository<BTM_ServerDetails_Entity, String> {
	
	@Query(value = "SELECT server FROM BTM_Server_Details", nativeQuery = true)
	List<String> getListofServices();
	
	@Query(value = "SELECT * FROM BTM_Server_Details where server=?1 ", nativeQuery = true)
	BTM_ServerDetails_Entity getServerInfo(String selectedValue);

	
}
