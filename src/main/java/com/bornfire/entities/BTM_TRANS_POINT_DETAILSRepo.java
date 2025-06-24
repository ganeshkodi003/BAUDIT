package com.bornfire.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BTM_TRANS_POINT_DETAILSRepo extends JpaRepository<BTM_TRANS_POINT_DETAILS, String>{

	
	@Query(value = "SELECT * FROM BTM_TRANS_POINT_DETAILS WHERE org_tran_id =?1 AND org_part_tran_id=?2 ", nativeQuery = true)
	BTM_TRANS_POINT_DETAILS getjourform(String tran_id,String part_tranid);
	
	
	@Query(value = "SELECT * FROM BTM_TRANS_POINT_DETAILS where org_tran_id=?1 ", nativeQuery = true)
	BTM_TRANS_POINT_DETAILS gettranid(String tran_id);
}
