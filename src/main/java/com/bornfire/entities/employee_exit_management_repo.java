package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface employee_exit_management_repo  extends JpaRepository<employee_exit_management_entity, String>{

	@Query(value = "SELECT * FROM  EMPLOYEE_EXIT_MANAGEMENT   ", nativeQuery = true)
	List<employee_exit_management_entity> getListEmployeeDetails();
}
