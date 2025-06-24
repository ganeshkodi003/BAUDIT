package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveMasterCounterRep extends JpaRepository<LeaveMasterCounter, String> {

	public Optional<LeaveMasterCounter> findById(String empId);

	@Query(value = "SELECT * FROM TM_EMPLOYEE_LEAVE_COUNTER where del_flg='N'", nativeQuery = true)

	List<LeaveMasterCounter> getProfilelist();

	
	@Query(value = "SELECT * FROM TM_EMPLOYEE_LEAVE_COUNTER where leave_ref=?1", nativeQuery = true)

	List<LeaveMasterCounter>  getLeaveCounterlist(String RefId);


	@Query(value = "SELECT * FROM TM_EMPLOYEE_LEAVE_COUNTER where leave_ref=?1", nativeQuery = true)
	
	List<LeaveMasterCounter> getleavelistbyrec(String RefId);
	
@Query(value = "SELECT * FROM TM_EMPLOYEE_LEAVE_COUNTER where emp_id=?1 and leave_date=?2", nativeQuery = true)
	
	LeaveMasterCounter getleaveCheck(String empid,String leave_date);

}
