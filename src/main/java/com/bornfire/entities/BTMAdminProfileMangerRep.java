package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BTMAdminProfileMangerRep extends CrudRepository<BTMAdminProfileManager, String> {

	public Optional<BTMAdminProfileManager> findById(String empId);

	@Query(value = "SELECT * FROM PROFILE_MANAGER where del_flg='N' ORDER BY emp_id", nativeQuery = true)
	List<BTMAdminProfileManager> getProfilelist();
	
	@Query(value = "SELECT * FROM PROFILE_MANAGER where emp_id=?1 and del_flg=?2", nativeQuery = true)
	List<BTMAdminProfileManager> getProfileReport(String emp_id , String flg);
}
