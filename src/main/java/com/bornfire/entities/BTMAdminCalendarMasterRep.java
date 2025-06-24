package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BTMAdminCalendarMasterRep extends JpaRepository<BTMAdminCalendarMaster, BigDecimal> {
	
	Optional<BTMAdminCalendarMaster> findById(BigDecimal srl);

	@Query(value = "SELECT year,month from CAL_MASTER order by year DESC", nativeQuery = true)
	List<Object[]> findBysrl();

}
