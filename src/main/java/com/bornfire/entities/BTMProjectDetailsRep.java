package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BTMProjectDetailsRep extends CrudRepository<ProjectDetails, String> {
	
	public Optional<ProjectDetails> findById(String projId);

	@Query(value = "SELECT * FROM PROJ_SCH where del_flg='N'", nativeQuery = true)
	List<ProjectDetails> getProjectDetailslist();
		
	@Query(value = "select * from PROJ_SCH  where proj_id =?1 and del_flg='N'", nativeQuery = true)
	ProjectDetails getProjectDetails(String resId1);

}
