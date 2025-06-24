package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface btdsviewRepo  extends JpaRepository<btdsview, String>{
	
	@Query(value = "SELECT * FROM btds_view WHERE EXTRACT(MONTH FROM date_of_pay) = ?1 AND EXTRACT(YEAR FROM date_of_pay) = ?2 ORDER BY date_of_pay", nativeQuery = true)
	List<btdsview> getdatetdslist(String a, String b);

}