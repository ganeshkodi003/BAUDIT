package com.bornfire.entities;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BTMAdminOrganizationMasterRep extends CrudRepository<BTMAdminOrganizationMaster, String> {

	public Optional<BTMAdminOrganizationMaster> findById(String empId);
	
	@Query(value = "select * from ORGANIZATION_DETAILS where del_flg='N' ", nativeQuery = true)
	BTMAdminOrganizationMaster getOrganizationdetail(String userid);


}
