package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface Employee_Onboarding_Repo extends JpaRepository<Employee_Onboarding_Entity, String> {

	// Fetch all records from BTM_EMPLOYEES_ONBOARDING_PROCESS table
	@Query(value = "SELECT * FROM BTM_EMPLOYEES_ONBOARDING_PROCESS", nativeQuery = true)
	List<Employee_Onboarding_Entity> getAllProfiles();

	// Fetch a single record by employee_id
	@Query(value = "SELECT * FROM BTM_EMPLOYEES_ONBOARDING_PROCESS WHERE employee_id = ?1", nativeQuery = true)
	Employee_Onboarding_Entity getProfileByEmployeeId(String employee_id);

	@Query(value = "SELECT * FROM BTM_EMPLOYEES_ONBOARDING_PROCESS WHERE employee_id = ?1 and Del_flg = 'N'", nativeQuery = true)
	Employee_Onboarding_Entity getProfileByEmployeeIddel(String employee_id);

	@Query(value = "SELECT * FROM BTM_EMPLOYEES_ONBOARDING_PROCESS WHERE employee_id = ?1 and Del_flg = 'N'", nativeQuery = true)
	Employee_Onboarding_Entity getProfileByEmployeeId1(String employee_id);

	/*
	 * @Query(value = "SELECT * FROM BTM_EMPLOYEES_ONBOARDING_PROCESS \r\n" +
	 * "WHERE employee_id = 'BFI0193' \r\n" +
	 * "AND (verify_flg IS NULL OR verify_flg = 'N')", nativeQuery = true)
	 * Employee_Onboarding_Entity getProfileByEmployeeIds(String employee_id);
	 */
	/*
	 * @Query(value = "SELECT * FROM BTM_EMPLOYEES_ONBOARDING_PROCESS", nativeQuery
	 * = true) List<Employee_Onboarding_Entity> getAllProfiles1();
	 */

	@Query(value = "SELECT * FROM BTM_EMPLOYEES_ONBOARDING_PROCESS " + "WHERE employee_id = :employeeId "
			+ "AND (verify_flg IS NULL OR verify_flg = 'N')", nativeQuery = true)
	Employee_Onboarding_Entity getProfileByEmployeeIds(String employeeId);

	@Query(value = "SELECT * FROM BTM_EMPLOYEES_ONBOARDING_PROCESS WHERE DEL_FLG='N' ORDER BY EMPLOYEE_ID", nativeQuery = true)
	List<Employee_Onboarding_Entity> getAllProfiles1();
	
	@Query("SELECT e FROM Employee_Onboarding_Entity e WHERE e.document_no1 = :documentNo OR e.document_no2 = :documentNo OR e.document_no3 = :documentNo OR e.document_no4 = :documentNo OR e.document_no5 = :documentNo")
	List<Employee_Onboarding_Entity> findByAnyDocumentNo(@Param("documentNo") String documentNo);

	@Query(value = "SELECT verify_flg FROM BTM_EMPLOYEES_ONBOARDING_PROCESS WHERE employee_id=?1 ", nativeQuery = true)
	String allowviewormodify(String employee_id);
}
