package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BTMProjectTeamDetailsRep extends CrudRepository<ProjectTeamDetails, String> {
	
	public Optional<ProjectTeamDetails> findById(String projId);
	
	@Query(value = "SELECT * FROM PROJ_TEAM where del_flg='N'", nativeQuery = true)
	List<ProjectTeamDetails> getProjectlist();

	@Query(value = "select * from PROJ_TEAM  where proj_id =?1", nativeQuery = true)
	ProjectTeamDetails getProjectTeamDetails(String resId);
	
	@Query(value = "select * from PROJ_TEAM  where PROJ_ID=?1 AND del_flg='N'", nativeQuery = true)
	ProjectTeamDetails getProjectTeamMaster(String Proj_id);

	


}