package com.bornfire.entities;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Salary_Pay_Rep extends JpaRepository<Salary_Pay_Entity, String>{
	
	@Query(value = "SELECT * FROM BTM_SAL_STR WHERE del_flg='N' ORDER BY EMP_NO", nativeQuery = true)
    List<Salary_Pay_Entity> getList();
    
	@Query(value = "SELECT * FROM BTM_SAL_STR ", nativeQuery = true)
	List<Salary_Pay_Entity> getLists();

	@Query(value = "SELECT * FROM BTM_SAL_STR WHERE emp_no =?1 ", nativeQuery = true)
    Salary_Pay_Entity getaedit(String emp_no);
	
	@Query(value = "SELECT * FROM BTM_SAL_STR WHERE emp_no =?1 and ctc_eff_date=?2", nativeQuery = true)
    Salary_Pay_Entity getaedit1(String emp_no,Date ctc_eff_date);
	
	@Query(value = "SELECT * FROM BTM_SAL_STR WHERE emp_no =?1 and ctc_eff_date=?2", nativeQuery = true)
	List<Salary_Pay_Entity> getactc(String emp_no, String ctc_eff_date );
	
	@Query(value = "SELECT * FROM BTM_SAL_STR WHERE emp_no =?1 and ctc_eff_date=?2", nativeQuery = true)
    Salary_Pay_Entity savectc(String emp_no,String ctc_eff_date);
	
	@Query(value = "select  * from BTM_CANDI_EVALU_FORM where ref_no= ?1",nativeQuery = true)
	String getsalfromcvf(String ref_no);
	
	@Query(value = "select * from BTM_SAL_STR WHERE emp_no =?1 and ctc_eff_date=?2",nativeQuery = true)
	Salary_Pay_Entity getsalssss(String emp_no,Date ctc_eff_date);
	
	@Query(value = "SELECT emp_no FROM BTM_SAL_STR  ", nativeQuery = true)
    List<Salary_Pay_Entity> getalist();

	
	
	/*
	 * @Query(value = "SELECT emp_no FROM BTM_SAL_STR ", nativeQuery = true)
	 * List<String> getctc();
	 */
	
}