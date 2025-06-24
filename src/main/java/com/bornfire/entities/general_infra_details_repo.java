package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface general_infra_details_repo extends JpaRepository<genral_infra_details_entity, String> {
	
	@Query(value = "SELECT * FROM GENERAL_INFRA_DETAILS ", nativeQuery = true)
	List<genral_infra_details_entity> getgeneraldetailsall();
	
	@Query(value = "SELECT * FROM GENERAL_INFRA_DETAILS where server=?1", nativeQuery = true)
	genral_infra_details_entity getgeneraldetails(String server);
	
	@Query(value = "SELECT * FROM GENERAL_INFRA_DETAILS where year=?1 and month=?2 and server=?3", nativeQuery = true)
	genral_infra_details_entity getserverdailysheet(String year,String month,String server);

}
