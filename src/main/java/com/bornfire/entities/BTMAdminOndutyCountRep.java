package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BTMAdminOndutyCountRep extends JpaRepository<BTMAdminOndutyCount, String> {

	public Optional<BTMAdminOndutyCount> findById(String empId);

	@Query(value = "SELECT * FROM TM_EMPLOYEE_ONDUTY_COUNTER where del_flg='N'", nativeQuery = true)

	List<BTMAdminOndutyCount> getProfilelist();
	

	@Query(value = "SELECT * FROM TM_EMPLOYEE_ONDUTY_COUNTER where leave_ref=?1 ", nativeQuery = true)
	
	List<BTMAdminOndutyCount> getondutybyref(String RefId);
	
	@Query(value = "SELECT * FROM TM_EMPLOYEE_ONDUTY_COUNTER where leave_ref=?1", nativeQuery = true)

	List<BTMAdminOndutyCount>  getOndutyCounterlist(String RefId);
	
	@Query(value = "SELECT * FROM TM_EMPLOYEE_ONDUTY_COUNTER where emp_id=?1 and od_date=?2", nativeQuery = true)

	BTMAdminOndutyCount  getOndutyCount(String empid ,String od_date);

}
