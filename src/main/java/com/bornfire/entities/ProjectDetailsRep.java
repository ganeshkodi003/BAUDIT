package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProjectDetailsRep extends JpaRepository<ProjectDetailsEntity, String> {

	@Query(value = "SELECT resource_id, resource_name, project_id, project_name, client_id, client_name, country, start_date, end_date,resource_id_1  FROM AMC_PROJECT_DETAILS WHERE del_flag = 'N'", nativeQuery = true)
	List<Object[]> getProjectList();

	@Query(value = "SELECT resource_id FROM AMC_PROJECT_DETAILS WHERE PROJECT_ID = ?1", nativeQuery = true)
	List<String> getcalllogproj(String resourceId);
	
	@Query(value = "SELECT * FROM AMC_PROJECT_DETAILS WHERE resource_id = ?1", nativeQuery = true)
	ProjectDetailsEntity getprojectview(String projectId);
    
    @Query(value = "SELECT * FROM AMC_PROJECT_DETAILS WHERE PROJECT_ID = ?1", nativeQuery = true)
    List<ProjectDetailsEntity> getprojectDetails(String projectId);
    
    @Query(value = "SELECT * FROM AMC_PROJECT_DETAILS where RESOURCE_ID_1=?1 and  PROJECT_ID = ?2 ", nativeQuery = true)

    ProjectDetailsEntity delete1(String resourceid,String projectid);

	@Query(value = "select * from AMC_PROJECT_DETAILS where resource_id=?1", nativeQuery = true)
	 ProjectDetailsEntity getprojectdetails(String resourceName);
	
	@Query(value = "select * from AMC_PROJECT_DETAILS where project_id=?1 and resource_id=?2", nativeQuery = true)
	Optional<ProjectDetailsEntity> findByProjectIdAndResourceId1(String project_id, String resource_id);

}
