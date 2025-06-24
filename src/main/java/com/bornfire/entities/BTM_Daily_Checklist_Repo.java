package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BTM_Daily_Checklist_Repo extends JpaRepository<BTM_Daily_Checklist_Entity, String> {
	
	 @Query(value = "SELECT * FROM BTM_DAILY_CHECK_LIST where uniqueid =?1 ", nativeQuery = true)
	 BTM_Daily_Checklist_Entity findBykeyid(String resource_name);

	 
	 @Query(value = "SELECT * FROM BTM_DAILY_CHECK_LIST where uniqueid=?1", nativeQuery = true)
	 BTM_Daily_Checklist_Entity getuniqueidamc(String uniqueid );
	
	 
	 @Query(value = "SELECT d.resource_name, " +
             "       d.service_shift, " +
             "       d.service_time, " +
             "       d.log_in_time, " +
             "       d.log_out_time, " +
             "       d.project, " +
             "       d.UNIQUEID, " +
             "       m.DEL_FLG, " +
             "       m.ENTRY_FLG, " +
             "       m.USER_LOGIN_EXPIRY_DATE, " +
             "       m.SSL_CERTIFICATE_EXPIRY_DATE " +
             "FROM BTM_DAILY_CHECK_LIST d " +
             "JOIN MASTER_TABLE_AMC_DAILYLIST m ON d.resource_name = m.resource_name " +
             "WHERE d.resource_name = ?1 " +
             "AND d.del_flag = 'N'", nativeQuery = true)
List<Object[]> getamclistwithuser(String resourceName);

@Query(value = "SELECT d.resource_name, " +
        "       d.service_shift, " +
        "       d.service_time, " +
        "       d.log_in_time, " +
        "       d.log_out_time, " +
        "       d.project, " +
        "       d.UNIQUEID, " +
        "       m.DEL_FLG, " +
        "       m.ENTRY_FLG, " +
        "       m.USER_LOGIN_EXPIRY_DATE, " +
        "       m.SSL_CERTIFICATE_EXPIRY_DATE " +
        "FROM BTM_DAILY_CHECK_LIST d " +
        "JOIN MASTER_TABLE_AMC_DAILYLIST m ON d.resource_name = m.resource_name " +
        "WHERE d.del_flag = 'N'", nativeQuery = true)
List<Object[]> getamclist();

		@Query(value = "select  * from BTM_DAILY_CHECK_LIST where resource_name= ?1 and  service_date= ?2 and  service_time= ?3 and  service_shift= ?4", nativeQuery = true)
		List<BTM_Daily_Checklist_Entity> getamcmail(String resource_name,String service_date,String service_time1,String service_shift);
		
		@Query(value = "SELECT * FROM BTM_DAILY_CHECK_LIST where uniqueid=?1 ", nativeQuery = true)

		BTM_Daily_Checklist_Entity delete1(String uniqueids);
}