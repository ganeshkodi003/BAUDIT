package com.bornfire.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BTM_GST_MASTERREPO extends JpaRepository<BTM_GST_MASTER, String> {

	
	@Query(value = "SELECT * FROM BTM_GST_MASTER WHERE tran_id =?1 AND part_tran_id=?2 ", nativeQuery = true)
	BTM_GST_MASTER getjourform1GST(String tran_id,String part_tranid);
	
	@Query(value = "SELECT * FROM BTM_GST_MASTER where tran_id=?1 ", nativeQuery = true)
	BTM_GST_MASTER gettranidgst(String tran_id);
}
