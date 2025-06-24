package com.bornfire.entities;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository 
public interface BTMTroubleShoot_Rep extends JpaRepository<BTMTroubleShoot_Entity, String>{

	@Query(value = "SELECT * FROM TROUBLE_SHOOT  WHERE del_flag='N' ORDER BY CAST(issue_no AS INTEGER)", nativeQuery = true)
	List<BTMTroubleShoot_Entity> getTroubleshootlist();
	
	@Query(value = " SELECT * FROM TROUBLE_SHOOT WHERE issue_no=?1", nativeQuery = true)
	BTMTroubleShoot_Entity findByIdCustom(String issue_no);
	
	@Query(value = "SELECT * FROM TROUBLE_SHOOT where uniqueid=?1 ", nativeQuery = true)

	BTMTroubleShoot_Entity delete1(String uniqueids);
	
	@Query(value = " SELECT * FROM TROUBLE_SHOOT WHERE issue_no=?1", nativeQuery = true)
	BTMTroubleShoot_Entity findidmodify(String issue_no);
	
	 @Modifying                                                                          
     @Transactional
	 @Query(value = "delete from TROUBLE_SHOOT where issue_no=?1 ", nativeQuery = true)
     void deletethevalue(String issue_no);   
	 
	 @Query(value = "SELECT * FROM TROUBLE_SHOOT WHERE TRIM(CURRENT_DATE) = TO_CHAR(SYSDATE, 'DD-MM-YY') ORDER BY ISSUE_NO + 0 DESC FETCH FIRST ROW ONLY", nativeQuery = true)
	 BTMTroubleShoot_Entity findGreatestIssueNo();
	 
	 
	 @Query(value = "SELECT * FROM TROUBLE_SHOOT WHERE ISSUE_NO=?1", nativeQuery = true)
	 BTMTroubleShoot_Entity findByIssueNo(String issueNo);

	 @Query(value = "SELECT * FROM TROUBLE_SHOOT where UNIQUEID=?1", nativeQuery = true)
	 BTMTroubleShoot_Entity getProfileByEmployeeId(String emp_id);
}
