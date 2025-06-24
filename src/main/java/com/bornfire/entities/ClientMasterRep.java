package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientMasterRep extends CrudRepository<ClientMaster, String> {
	
	 //ClientMaster findByClient_srl_no(String client_srl_no);
	
	 @Query(value = "select * from CLIENT_MASTER  where CLIENT_SRL_NO =?1  ", nativeQuery = true) 
	 ClientMaster getClientlist(String client_srl_no);

		
	 @Query(value = "select * from CLIENT_MASTER where  CLIENT_SRL_NO =?1 ",nativeQuery = true)
	 ClientMaster getClientlist11(String client_srl_no);
	 
	 @Query(value = "select * from CLIENT_MASTER where  CLIENT_LOCATION =?1 ",nativeQuery = true)
	 ClientMaster getClientAddress(String client_location);
		 
	 @Query(value = "select * from CLIENT_MASTER ", nativeQuery = true) 
	 List<ClientMaster> getClientlist2();
}