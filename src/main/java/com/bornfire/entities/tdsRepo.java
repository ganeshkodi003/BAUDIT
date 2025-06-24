package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository

public interface tdsRepo extends JpaRepository<tdsentity, String>  {
	
	
	@Query(value = "SELECT * FROM BTM_TDS where date_of_pay=?1 ", nativeQuery = true)

	List<tdsentity> getuniqueidtds(String d);
	
	@Query(value = "SELECT * FROM BTM_TDS where uniqueids=?1 AND emp_no=?2 ", nativeQuery = true)

	List<tdsentity> gettdswithdec(String moths,String twoDigitYear);
	
	@Query(value = "SELECT * FROM BTM_TDS where EXTRACT(MONTH FROM date_of_pay) =?1 AND EXTRACT(YEAR FROM date_of_pay) =?2 ORDER BY date_of_pay", nativeQuery = true)

	List<tdsentity> gettdswithdecs(String moths,String years);
	
	@Query(value = "SELECT * FROM BTM_TDS where EXTRACT(MONTH FROM date_of_pay) =?1 AND EXTRACT(YEAR FROM date_of_pay) =?2 ORDER BY date_of_pay", nativeQuery = true)

	List<tdsentity> gettdswithfirst(String moths,String years);
	
	// Repository method
	@Query(value = "SELECT * FROM BTM_TDS WHERE month_column = ?1 AND year_column = ?2", nativeQuery = true)
	List<tdsentity> gettdswithdecss(int months, int years);
	
	
	@Query(value = "SELECT * FROM BTM_TDS where uniqueids=?1 ", nativeQuery = true)

	tdsentity getlisttab1(String uniqueids);
	
	@Query(value = "SELECT * FROM BTM_TDS where uniqueids=?1 ", nativeQuery = true)

	tdsentity delete1(String uniqueids);
	
	@Query(value = "SELECT * FROM BTM_TDS where uniqueids=?1 ", nativeQuery = true)

	tdsentity getlisttab2(String uniqueids);

	
	

}