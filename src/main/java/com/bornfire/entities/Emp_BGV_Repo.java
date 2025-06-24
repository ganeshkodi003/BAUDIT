package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Emp_BGV_Repo extends JpaRepository<Emp_BGV_Entity , String>
{
	@Query(value = "SELECT DISTINCT EMPLOYEE_ID, RESOURCE_NAME FROM RESOURCE_MASTER WHERE DEL_FLG = 'N' ORDER BY EMPLOYEE_ID ASC" , nativeQuery = true)
	List<Object[]> getInterEmp();
	
	@Query(value = "SELECT DISTINCT EMPLOYEE_ID,RESOURCE_NAME,QUAL,DESIGN,ROLE,ADDR1,DOB,DOJ,BANK,BANK_ACT_NO,PANCARD,AADHAR,PASSPORT,DRIVING_LICENSE,MOBILE,EMAIL FROM RESOURCE_MASTER WHERE EMPLOYEE_ID = ?1", nativeQuery = true)
	List<Object[]> getInterEmpdetails(String EMPLOYEE_ID);
	
	@Query(value = "SELECT DISTINCT EMP_ID, EMP_NAME FROM LOC_INVOICE_MASTER ORDER BY EMP_ID ASC" , nativeQuery = true)
	List<Object[]> getExtEmp();
	
	@Query(value = "SELECT DISTINCT EMP_ID, EMP_NAME,BANK_NAME,ACCT_NO,PAN,LOCATION FROM LOC_INVOICE_MASTER WHERE EMP_ID = ?1" , nativeQuery = true)
	List<Object[]> getExtEmpdetails(String EMP_ID);
	
	
	@Query(value = "SELECT * FROM EMP_BGV order by emp_name", nativeQuery = true)
    List<Emp_BGV_Entity>  getValues();
    
    
    @Query(value = "SELECT * FROM EMP_BGV where EMP_ID=?1", nativeQuery = true)
    Emp_BGV_Entity  getviewValues(String empId);
    
    
    @Query(value = "SELECT * FROM EMP_BGV where EMP_ID=?1", nativeQuery = true)
    Emp_BGV_Entity getProfileByEmployeeId(String emp_id);
    
}
