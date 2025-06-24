package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

 

@Repository
public interface TimeSheetBeanRep extends CrudRepository<TimeSheetBean, String> {

	public Optional<TimeSheetBean> findById(String empId);

	@Query(value = "SELECT * FROM EMP_TIME_SHEET where emp_id = ?1 and emp_name= ?2", nativeQuery = true)
	List<TimeSheetBean> findByParam(String id1, String id2);
	
	@Query(value = "SELECT * FROM EMP_TIME_SHEET where emp_id = ?1 and entity_flg='N'", nativeQuery = true)
	TimeSheetBean getTimesheetList(String id2 );
	
	@Query(value = "SELECT * FROM EMP_TIME_SHEET where emp_id = ?1 and year =?2 and month=?3 and entity_flg='N'", nativeQuery = true)
	TimeSheetBean getTimeSheetdata(String id2,String year,String month);
	
	
}