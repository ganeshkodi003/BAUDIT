package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface BTMMTimeSheetRep extends JpaRepository<BTMMTimeSheet, String> {
	

	@Query(value = "SELECT * FROM EMP_TIME_SHEET where emp_id = ?1 and year =?2 and month=?3", nativeQuery = true) 
	BTMMTimeSheet getTimeSheetVerify(String empId, BigDecimal year, String month);
	
	@Query(value = "SELECT * FROM EMP_TIME_SHEET where emp_id = ?1 ORDER BY YEAR  ASC", nativeQuery = true) 
	List<BTMMTimeSheet> getTimeSheetList(String empId);
	
	@Query(value = "SELECT * FROM EMP_TIME_SHEET", nativeQuery = true) 
	List<BTMMTimeSheet> getTimeSheet();
	
	
}
