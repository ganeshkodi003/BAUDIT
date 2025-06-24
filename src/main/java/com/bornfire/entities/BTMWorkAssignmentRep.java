package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BTMWorkAssignmentRep extends CrudRepository<BTMWorkAssignment, String> {

	public Optional<BTMWorkAssignment> findById(String empId);

	@Query(value = "SELECT * FROM WORK_ASSIGN_MASTER", nativeQuery = true)
	List<BTMWorkAssignment> getWorkAssignlist();

	@Query(value = "SELECT * FROM WORK_ASSIGN_MASTER where srl_no =?1", nativeQuery = true)
	BTMWorkAssignment getWorkAssign(String SrlNo);
	
	@Query(value = "SELECT * FROM WORK_ASSIGN_MASTER where srl_no =?1", nativeQuery = true)
	BTMWorkAssignment getWorkMaster(String userid);

	@Query(value = "SELECT * FROM WORK_ASSIGN_MASTER where emp_id =?1", nativeQuery = true)
	BTMWorkAssignment getWorkAssign1(String empId);
	
	@Query(value = "select * from  WORK_ASSIGN_MASTER where entity_flg='N' ORDER BY emp_id ASC ", nativeQuery = true)
	List<BTMWorkAssignment> getWorkAssigndetail();
	
	//@Query(value="SELECT DISTINCT EMP_ID,RPT_MANAGER,APP_AUTH,EMP_LOCATION FROM WORK_ASSIGN_MASTER where EMP_ID =?1",nativeQuery = true)
	
	//BTMWorkAssignment getWorkAssign1(String empId);

	@Query(value = "SELECT * FROM WORK_ASSIGN_MASTER where emp_id =?1", nativeQuery = true)
	List<BTMWorkAssignment> getWorkAssignListById(String userid);

}
