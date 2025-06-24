package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BTM_Call_Log_Repo extends JpaRepository<BTM_Call_Log_Entity, String>{
	
	@Query(value = "SELECT * FROM BTM_CALL_LOG", nativeQuery = true)
	List<BTM_Call_Log_Entity> getCalllogList();
	
	@Query(value = "SELECT * FROM BTM_CALL_LOG WHERE project_name =?1 AND lodged_date_and_time=?2 AND call_assign_to=?3", nativeQuery = true)
	BTM_Call_Log_Entity getcalledit(String project_name, String lodged_date_and_time, String call_assign_to);
	
	@Query(value = "SELECT * FROM BTM_CALL_LOG where uniqueid=?1", nativeQuery = true)
BTM_Call_Log_Entity getuniqueid(String uniqueid );

	@Query(value = "SELECT * FROM BTM_CALL_LOG where uniqueid=?1 ", nativeQuery = true)
	BTM_Call_Log_Entity getUniqueid(String uniqueid);
	
	
	@Query(value = "SELECT * FROM BTM_CALL_LOG where uniqueid=?1 ", nativeQuery = true)
	BTM_Call_Log_Entity getUniqueidpdf(String uniqueid);
	
	
	@Query(value = "SELECT a.project_name, " +
            "       a.lodged_by, " +
            "       a.lodged_date_and_time, " +
            "       a.call_details, " +
            "       a.received_by, " +
            "       a.call_assign_to, " +
            "       a.status, "  +
            "       a.uniqueid "  + // Removed the comma after d.uniqueid
            "FROM BTM_CALL_LOG a " +
            "WHERE a.CALL_ASSIGN_TO = ?1 AND del_flg='N'  order by lodged_date_and_time ", nativeQuery = true)
List<Object[]> getcallloglist(String resourcename);

@Query(value = "SELECT a.project_name, " +
        "       a.lodged_by, " +
        "       a.lodged_date_and_time, " +
        "       a.call_details, " +
        "       a.received_by, " +
        "       a.call_assign_to, " +
        "       a.status, "  +
        "       a.uniqueid "  + // Removed the comma after d.uniqueid
        "FROM BTM_CALL_LOG a " +
        "WHERE del_flg='N'  order by lodged_date_and_time ", nativeQuery = true)
List<Object[]> getcallloglistFor();


@Query(value = "SELECT * FROM BTM_CALL_LOG where UNIQUEID=?1", nativeQuery = true)
BTM_Call_Log_Entity getProfileByEmployeeId(String emp_id);
}
