package com.bornfire.entities;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileManagerRep extends CrudRepository<BTMProfileManager, String> {

	public Optional<BTMProfileManager> findById(String empId);

	@Query(value = "SELECT * FROM PROFILE_MANAGER where emp_id = ?1 and del_flg='N'", nativeQuery = true)
	BTMProfileManager findByEmpId(String id1);
}

