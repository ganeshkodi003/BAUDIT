package com.bornfire.entities;

 

import java.util.List;
import java.util.Optional;

 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

 

@Repository
public interface spf_repo extends JpaRepository <spf_entity, String>{
	@Query(value = "SELECT * FROM BSPF_BTM WHERE salary_month = ?1 order by emp_no", nativeQuery = true)
	List<spf_entity> getall(String Month);
	
	@Query(value = "SELECT * FROM BSPF_BTM WHERE salary_month = ?1 order by emp_no", nativeQuery = true)
	List<spf_entity> getData(String Month);
	
	@Query(value = "SELECT * FROM BSPF_BTM where CTC_AMT<'300000' and ESI!='0' and salary_month=?1 ", nativeQuery = true)
	List<spf_entity> getESI(String Month);
	
	@Query(value = "SELECT EMP_NO,EMP_NAME,SALARY_MONTH,BASIC_PAY,GROSS_SALARY,SPF FROM BSPF_BTM where salary_month=?1", nativeQuery = true)
	Object[] getSpfdetails(String formattedDate);
	
	@Query(value = "SELECT * FROM BSPF_BTM where unique_id=?1 ", nativeQuery = true)
	spf_entity findit(String a);
	
	@Query(value = "SELECT SUM(SPF) FROM BSPF_BTM WHERE SALARY_MONTH=?1 ", nativeQuery = true)
	Object[] getSpfbalance(String formattedDate);
	
	@Query(value = "SELECT EMP_NO,EMP_NAME,SALARY_MONTH,BASIC_PAY,GROSS_SALARY,ESI FROM BSPF_BTM WHERE SALARY_MONTH=?1 ", nativeQuery = true)
	Object[] getESF2details(String formattedDate);
	
	@Query(value = "SELECT SUM(ESI) FROM BSPF_BTM WHERE SALARY_MONTH=?1", nativeQuery = true)
	Object[] getESF1details(String formattedDate);
}