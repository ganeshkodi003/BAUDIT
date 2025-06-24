package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankMasterRep extends CrudRepository<BankMaster, String>{
	
	
	   // BankMaster findBybank_srl_no(String bank_srl_no);
	
		 @Query(value = "select * from BANK_MASTER  where bank_srl_no =?1", nativeQuery = true) 
		 BankMaster getBanklist(String bank_srl_no);
		 
		 @Query(value="select * from BANK_MASTER  ", nativeQuery = true)
		 List<BankMaster> getBanklist2();

}
