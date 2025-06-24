package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalChargeRep extends CrudRepository<ProfessionalCharge, BigDecimal> {

	public Optional<ProfessionalCharge> findById(BigDecimal srlNo);
	
	 @Query(value = "select * from CLIENT_PROF_CHGS ", nativeQuery = true) 
	 List<ProfessionalCharge> getplacementlist1();

}