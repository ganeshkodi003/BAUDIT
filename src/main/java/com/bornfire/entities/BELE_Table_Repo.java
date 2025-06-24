package com.bornfire.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BELE_Table_Repo extends JpaRepository<BELE_Table_Entity, String>{

	@Query(value = "SELECT * FROM BTM_ELE where EMP_NO=?1", nativeQuery = true)
	BELE_Table_Entity getvalues(String empid);
	
}
