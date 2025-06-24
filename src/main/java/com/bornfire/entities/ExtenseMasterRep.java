package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtenseMasterRep extends CrudRepository<ExpenseMaster, String> {
	
	public Optional<ExpenseMaster> findById(String assId);

	@Query(value = "SELECT * FROM EXP_MASTER where ass_id = ?1 ", nativeQuery = true)
	ExpenseMaster findByassId(String id1);
	
	@Query(value = "SELECT * FROM EXP_MASTER where ass_id = ?1 ", nativeQuery = true)
	List<ExpenseMaster> getListByassId(String id1);

}
