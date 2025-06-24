package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BTMDocumentMasterRep extends CrudRepository<BTMDocumentMaster, String> {
	
	public Optional<BTMDocumentMaster> findById(String empId);

	@Query(value = "SELECT * from DOCUMENT_MASTER where entity_flg ='Y'", nativeQuery = true)
	List<BTMDocumentMaster> getDocumentlist();
	
	@Query(value = "SELECT * from DOCUMENT_MASTER where entity_flg ='S'", nativeQuery = true)
	List<BTMDocumentMaster> getDocumentlist1();
	
	@Query(value = "SELECT * FROM DOCUMENT_MASTER where doc_id =?1", nativeQuery = true)
	BTMDocumentMaster getDocument1(String doc_id);
	
	@Query(value = "SELECT * FROM DOCUMENT_MASTER where doc_ref_no =?1", nativeQuery = true)
	BTMDocumentMaster getDocument(String SrlNo);

}
