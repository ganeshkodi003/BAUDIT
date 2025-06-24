package com.bornfire.entities;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Chart_Acc_Rep extends JpaRepository<Chart_Acc_Entity, String>{
	@Query(value = "SELECT * FROM BTM_CHART_OF_ACCOUNTS WHERE del_flg='N' ORDER BY CLASSIFICATION ASC", nativeQuery = true)
    List<Chart_Acc_Entity> getList();
    
    @Query(value = "SELECT * FROM BTM_CHART_OF_ACCOUNTS WHERE acct_num =?1", nativeQuery = true)
    Chart_Acc_Entity getaedit(String acct_num );
    
    @Query(value = "select * from BTM_CHART_OF_ACCOUNTS aa where aa.classification =?3 and aa.gl_code =?1 and aa.acct_num = ?2 ", nativeQuery = true)
	Chart_Acc_Entity getValuepop(String gl_code, String acct_num,String classification);
    
    @Query(value = "select * from BTM_CHART_OF_ACCOUNTS aa where aa.acct_num = ?1 ", nativeQuery = true)
    List<Chart_Acc_Entity> getValuepoplist(String acct_num);
    
    @Query(value = "SELECT * FROM BTM_CHART_OF_ACCOUNTS ORDER BY acct_num ", nativeQuery = true)
    List<Chart_Acc_Entity> getlistpopup();
}
