package com.bornfire.entities;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimesheetManagementRep extends CrudRepository<TimesheetManagement, String> {

	public Optional<TimesheetManagement> findById(String empId);

	@Query(value = "SELECT * FROM TIMESHEET_MASTER where placement_id = ?1 and emp_name= ?2", nativeQuery = true)
	List<TimesheetManagement> findByParam(String id1, String id2);
	
	@Query(value = "SELECT * FROM TIMESHEET_MASTER where placement_id = ?1 ", nativeQuery = true)
	TimesheetManagement getTimesheetList(String id2 );
}
