package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BTMProjectMasterRep extends CrudRepository<BTMProjectMaster, String> {

	public Optional<BTMProjectMaster> findById(String projId);

	@Query(value = "SELECT * FROM PROJ_MASTER where del_flg='N'", nativeQuery = true)
	List<BTMProjectMaster> getProjectlist();
	
	@Query(value = "SELECT * FROM PROJ_MASTER where del_flg='N' AND PROJ_ID=?1 ", nativeQuery = true)
	BTMProjectMaster getprojectclient(String projId);
	
	

	@Query(value = "SELECT * FROM PROJ_MASTER where PROJ_ID =?1 AND PROJ_NAME=?2 AND del_flg='N'", nativeQuery = true)
	BTMProjectMaster getProjectShow(String Proj_id,String ProjName);
	
	@Query(value = "SELECT * FROM PROJ_MASTER where PROJ_ID =?1 AND CLIENT_ID=?2 AND del_flg='N'", nativeQuery = true)
	List<BTMProjectMaster> getProjectReport(String Proj_id,String ProjName);

	@Query(value = "SELECT count(*) FROM PROJ_MASTER where PROJ_ID=?1 AND PROJ_NAME=?2", nativeQuery = true)
	int getprojectCount(String PROJ_ID, String PROJ_NAME);
	
	@Query(value = "select * from PROJ_MASTER  where proj_id =?1", nativeQuery = true)
	BTMProjectMaster getProjectMaster(String resId);

	@Query(value = "select * from PROJ_MASTER  where proj_id =?1", nativeQuery = true)
	BTMProjectMaster getProjectMasterList(String projId);
	
	@Query(value = "select * from PROJ_MASTER  where proj_id =?1", nativeQuery = true)
	BTMProjectMaster ProjectMasterchecklist(String projId);
}
