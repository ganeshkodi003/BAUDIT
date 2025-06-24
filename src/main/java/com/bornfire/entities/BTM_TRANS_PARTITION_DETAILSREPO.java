package com.bornfire.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BTM_TRANS_PARTITION_DETAILSREPO  extends JpaRepository<BTM_TRANS_PARTITION_DETAILS, String> {

	
	
	
	@Query(value = "SELECT * FROM BTM_TRANS_PARTITION_DETAILS WHERE tran_id =?1 AND part_tran_id=?2 ", nativeQuery = true)
	BTM_TRANS_PARTITION_DETAILS getjourformtpr(String tran_id,String part_tranid);
	
	@Query(value = "SELECT * FROM BTM_TRANS_PARTITION_DETAILS where tran_id=?1 ", nativeQuery = true)
	BTM_TRANS_PARTITION_DETAILS gettranidpartition(String tran_id);
}
