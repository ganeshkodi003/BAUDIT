package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Daily_server_update_Repo extends JpaRepository<Daily_server_update_entity, String> {
	
	@Query(value = "SELECT * FROM DAILY_UPDATE_SERVER where server=?1", nativeQuery = true)
	List<Daily_server_update_entity> getschduleddetails(String server);
	
}
