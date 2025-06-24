package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BLRSBatchJobAlertRep extends JpaRepository<BLRSBatchJobAlert,String> {
	Optional<BLRSBatchJobAlert> findById(String directorId);
	
	@Query(value = "SELECT distinct JOB_NAME,JOB_TYPE,SCHM_CODE,PERIODICITY,TO_CHAR(LAST_RUN_DATE,'DD-MM-YYYY')LAST_RUN_DATE,TO_CHAR(NEXT_RUN_DATE,'DD-MM-YYYY')NEXT_RUN_DATE,JOB_STATUS FROM BATCH_JOB_MASTER where job_name<>'DRS Replication' and trunc(sysdate) <= end_date and (trunc(next_run_date)=trunc(sysdate) OR trunc(last_run_date)=trunc(sysdate)  OR (trunc(last_run_date)<sysdate AND trunc(next_run_date)<trunc(sysdate)))and del_flg='N' and ENTITY_FLG='Y'order by case when JOB_NAME='Finacle Extraction' then 1 else 2 end", nativeQuery = true)
	List<Object[]> findByJobName();
	
}

