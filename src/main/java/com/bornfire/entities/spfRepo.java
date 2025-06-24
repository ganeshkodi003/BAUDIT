package com.bornfire.entities;

 

import java.util.List;
import java.util.Optional;

 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

 

@Repository
public interface spfRepo extends JpaRepository <BSPF_ENTITY, String>{


	@Query(value = "SELECT * FROM BSPF_VIEW where salary_month=?1 order by emp_no", nativeQuery = true)
	List<BSPF_ENTITY> getall(String Month);
	
	@Query(value = "SELECT * FROM BSPF_VIEW where salary_month=?1", nativeQuery = true)
	List<BSPF_ENTITY> getData(String b);

 

}