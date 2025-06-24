package com.bornfire.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BTM_REV_PART_TRANREPO extends JpaRepository<BTM_REV_PART_TRAN, String> {

	
	
	
	@Query(value = "SELECT * FROM BTM_REV_PART_TRAN WHERE rev_tran_id =?1 AND rev_part_tran_id=?2 ", nativeQuery = true)
	BTM_REV_PART_TRAN getjourform1(String tran_id,String part_tranid);
}
