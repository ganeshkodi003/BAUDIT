package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface bexpiRepo extends JpaRepository<BEXPI_entity,String> {
	
	@Query(value = "SELECT * FROM BEXPI_VIEW WHERE EXTRACT(MONTH FROM tran_date) = ?1 AND EXTRACT(YEAR FROM tran_date) = ?2 ORDER BY TRAN_ID", nativeQuery = true)
	List<BEXPI_entity> getDataOverseas(String a, String b);

}

