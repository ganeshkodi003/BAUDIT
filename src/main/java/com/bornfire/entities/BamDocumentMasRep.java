package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BamDocumentMasRep extends JpaRepository<Bamdocumentmanager, String> {
	
	 @Query(value = "SELECT * from BTM_DOCUMENT_MANAGER where DEL_FLG ='N'",nativeQuery = true) 
	 List<Bamdocumentmanager> getunverified();
	 
	 @Query(value = "SELECT * from BTM_DOCUMENT_MANAGER where DEL_FLG ='Y'",nativeQuery = true) 
	 List<Bamdocumentmanager> getverified();
	

}
