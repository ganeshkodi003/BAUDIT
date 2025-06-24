package com.bornfire.entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface ResourceMasterRepo extends CrudRepository<ResourceMaster, String> {

	@Query(value = "select COUNT(RESOURCE_ID) from RESOURCE_MASTER WHERE DEL_FLG = 'N' AND ENTITY_FLG = 'Y' ", nativeQuery = true)
	int gettotalnum();

	@Query(value = "select * from RESOURCE_MASTER WHERE DEL_FLG = 'N' AND ENTITY_FLG = 'Y' AND VIRTUAL_FLG  ='N' ORDER BY RESOURCE_ID ASC ", nativeQuery = true)
	List<ResourceMaster> gettotaluser();

	@Query(value = "select * from RESOURCE_MASTER WHERE DEL_FLG = 'N' AND ENTITY_FLG = 'Y' AND VIRTUAL_FLG  ='N' ORDER BY RESOURCE_ID ASC ", nativeQuery = true)
	List<ResourceMaster> gettotaluserNAME();

	@Query(value = "SELECT * FROM RESOURCE_MASTER WHERE employee_id=?1 ", nativeQuery = true)
	ResourceMaster getrole(String empid);
	
	

	@Query(value = "SELECT resource_name FROM RESOURCE_MASTER WHERE employee_id=?1 ", nativeQuery = true)
	String getname(String empid);

	@Query(value = "SELECT * FROM RESOURCE_MASTER WHERE employee_id=?1 ", nativeQuery = true)
	ResourceMaster getverifyappointment(String empid);

	@Query(value = "SELECT * FROM RESOURCE_MASTER where  DEL_FLG = 'N' ", nativeQuery = true)
	List<ResourceMaster> getroleall();

	@Query(value = "select * from RESOURCE_MASTER where employee_id=?1", nativeQuery = true)
	List<ResourceMaster> getuser(String empid);

	@Query(value = "select * from RESOURCE_MASTER where employee_id=?1", nativeQuery = true)
	ResourceMaster getuserData(String empid);

	@Query(value = "SELECT resource_id, resource_name FROM resource_master WHERE del_flg != 'Y' MINUS SELECT emp_id, emp_name FROM emp_att_master WHERE cal_year = ?1 AND cal_month = ?2 AND cal_date = ?3", nativeQuery = true)
	List<Object[]> getAbsentees(String year, String month, String day);

	@Query(value = "SELECT COUNT(*) FROM ( "
			+ "SELECT resource_id, resource_name FROM resource_master WHERE del_flg != 'Y' " + "MINUS "
			+ "SELECT emp_id, emp_name FROM emp_att_master WHERE cal_year = ?1 AND cal_month = ?2 AND cal_date = ?3)", nativeQuery = true)
	Integer getAbsenteesCount(int year, int month, int day);

	@Query(value = "SELECT mobile FROM resource_master WHERE del_flg != 'Y' AND resource_id NOT IN ( SELECT emp_id   FROM emp_att_master  WHERE cal_year = ?1  AND cal_month = ?2  AND cal_date = ?3)", nativeQuery = true)
	List<String> smssenmding(String year, String month, String day);

	@Query(value = "SELECT resource_id FROM RESOURCE_MASTER order by resource_id", nativeQuery = true)
	List<String> getalist();

	@Query(value = "SELECT * FROM RESOURCE_MASTER WHERE resource_id=?1 ", nativeQuery = true)
	ResourceMaster getarole(String empid);

	@Query(value = "SELECT EMPLOYEE_ID, DOJ, RESOURCE_NAME, OFFICE_EMAIL FROM RESOURCE_MASTER WHERE TO_CHAR(DOJ, 'MM-DD') = TO_CHAR(:currentDate, 'MM-DD') AND DEL_FLG='N'", nativeQuery = true)
	List<Object[]> anniversary(@Param("currentDate") LocalDate currentDate);

	@Query(value = "SELECT EMPLOYEE_ID, RESOURCE_NAME, DOB, OFFICE_EMAIL FROM RESOURCE_MASTER WHERE TO_CHAR(DOB, 'MM-DD') = TO_CHAR(:currentDate, 'MM-DD') AND DEL_FLG='N'", nativeQuery = true)
	List<Object[]> birthday(@Param("currentDate") LocalDate currentDate);

	@Query(value = "SELECT EMPLOYEE_ID, DOJ, RESOURCE_NAME, OFFICE_EMAIL FROM RESOURCE_MASTER WHERE DOJ = :currentDate AND DEL_FLG='N'", nativeQuery = true)
	List<Object[]> welcome(@Param("currentDate") LocalDate currentDate);

	@Query(value = "SELECT ROLE FROM RESOURCE_MASTER WHERE employee_id=?1 ", nativeQuery = true)
	String getroleid(String empid);

	@Query(value = "SELECT EMPLOYEE_ID, RESOURCE_NAME, OFFICE_EMAIL FROM RESOURCE_MASTER WHERE DEL_FLG='N'", nativeQuery = true)
	List<Object[]> fetchAllEmployees();

	@Query(value = "SELECT RESOURCE_NAME FROM RESOURCE_MASTER WHERE employee_id=?1 ", nativeQuery = true)
	String getname1(String empid);
	
	
	@Query(value = "SELECT COUNT(*) FROM ( "
			+ "SELECT resource_id, resource_name FROM resource_master WHERE del_flg != 'Y' " + "MINUS "
			+ "SELECT emp_id, emp_name FROM emp_att_master WHERE cal_year = ?1 AND cal_month = ?2 AND cal_date = ?3)", nativeQuery = true)
	Integer getAbsenteesCountsfilter(String year, String month, String day);
	
	
	@Query(value = "SELECT DISTINCT RESOURCE_ID ,resource_name, email FROM RESOURCE_MASTER where DEL_FLG='N' ORDER BY RESOURCE_ID ASC", nativeQuery = true)
	List<String[]> getEmployeeIDs();
	
	
	@Query(value = "SELECT * FROM RESOURCE_MASTER where EMPLOYEE_ID=?1 AND DEL_FLG='N' ", nativeQuery = true)
	ResourceMaster getSingleDetails(String refNo);

}
