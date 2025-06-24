package com.bornfire.entities;

import java.util.List;
import java.util.Optional;
import java.util.Date;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BTMAdminAssociateModRep extends CrudRepository<BTMAdminAssociateMod, String>  {

	public Optional<BTMAdminAssociateMod> findById(String resId);
	
	@Query(value = "select * from RESOURCE_MASTER_MOD where ENTITY_FLG='N' AND DEL_FLG='N' AND MODIFY_FLG='Y' ", nativeQuery = true)
	List<BTMAdminAssociateMod> getAssociateCancellist();
	
	@Query(value = "select * from RESOURCE_MASTER_MOD where ENTITY_FLG='N' AND DEL_FLG='N' AND MODIFY_FLG='Y' ", nativeQuery = true)
	List<BTMAdminAssociateMod> getAssociateVerifylist();

	@Query(value = "select * from RESOURCE_MASTER_MOD where resource_id =?1 and del_flg='N' ", nativeQuery = true)
	BTMAdminAssociateMod getEmployeedetail(String userid);
}
