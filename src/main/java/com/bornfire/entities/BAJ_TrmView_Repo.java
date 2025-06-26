package com.bornfire.entities;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

@Repository
public interface BAJ_TrmView_Repo extends JpaRepository<BAJ_TrmView_Entity, BAJ_TrmView_Entity_Idclass> {

	@Query(value = "Select * From TRMWORK_VIEW Where acct_num =?1 AND TRAN_DATE >= TO_DATE('01-04-2024', 'DD-MM-YYYY') ORDER BY TRAN_DATE ASC", nativeQuery = true)
	List<BAJ_TrmView_Entity> getAccRecord(String acct_num);

	@Query(value = "SELECT * FROM TRMWORK_VIEW WHERE ACCT_NUM = ?1 AND TRUNC(TRAN_DATE) BETWEEN TO_DATE(?2, 'dd-MM-yyyy') AND TO_DATE(?3, 'dd-MM-yyyy') ORDER BY TRAN_DATE", nativeQuery = true)
	List<BAJ_TrmView_Entity> getTranlst(String acct_num, String fromdate, String todate);

	@Query(value = "Select * From TRMWORK_VIEW Where acct_num =?1", nativeQuery = true)
	List<BAJ_TrmView_Entity> getvalue(String acct_num);

	@Query(value = "Select * From TRMWORK_VIEW Where acct_num =?1 AND TRAN_DATE >= TO_DATE('01-04-2024', 'DD-MM-YYYY') ORDER BY TRAN_DATE ASC ", nativeQuery = true)
	List<BAJ_TrmView_Entity> getLedgerEntries(String acct_num);

	/*
	 * @Query(value =
	 * "Select * From TRMWORK_VIEW Where acct_num =?1 ORDER BY TRAN_DATE ASC",
	 * nativeQuery = true) List<BAJ_TrmView_Entity> getLedgerEntries2(String
	 * acct_num);
	 */

	@Query(value = "SELECT * FROM TRMWORK_VIEW WHERE acct_num = ?1 AND TRAN_DATE >= TO_DATE('01-04-2024', 'DD-MM-YYYY') ORDER BY TRAN_DATE ASC", nativeQuery = true)
	List<BAJ_TrmView_Entity> getLedgerEntries2(String acct_num);

	@Query(value = "SELECT * " + "FROM TRMWORK_VIEW " + "WHERE tran_id IN (" + "    SELECT DISTINCT tran_id "
			+ "    FROM TRMWORK_VIEW " + "    WHERE acct_num = :acctNum "
			+ "      AND TRUNC(TRAN_DATE) BETWEEN TO_DATE('01-04-2024', 'dd-MM-yyyy') AND TRUNC(SYSDATE)" + ") "
			+ "AND TRAN_DATE BETWEEN TO_DATE('01-04-2024', 'dd-MM-yyyy') AND TRUNC(SYSDATE) "
			+ "ORDER BY TRAN_DATE ASC", nativeQuery = true)
	List<BAJ_TrmView_Entity> getLedgerEntries3(String acctNum);

	@Query(value = "select  * from TRMWORK_VIEW where tran_id = ?1", nativeQuery = true)
	List<BAJ_TrmView_Entity> findByjournalvalues(String tran_id);

	@Query(value = "select * from TRMWORK_VIEW aa where aa.part_tran_id =?3 and aa.tran_id =?1 and aa.acct_num = ?2 ", nativeQuery = true)
	BAJ_TrmView_Entity getValuepopvalues(String tran_id, String acct_num, String part_tran_id);

	@Query(value = "select * from TRMWORK_VIEW aa where aa.part_tran_id =?2 and aa.tran_id =?1 ", nativeQuery = true)
	BAJ_TrmView_Entity getValuepop(String tran_id, String part_tran_id);

	@Query(value = "SELECT MAX(part_tran_id) AS part_tran_id FROM TRMWORK_VIEW  WHERE tran_id =?1", nativeQuery = true)
	String maxPartranID(String tranId);

	@Query(value = "select  * from  TRMWORK_VIEW where tran_date=?1 order by TRAN_DATE,TRAN_ID,PART_TRAN_ID ", nativeQuery = true)
	List<BAJ_TrmView_Entity> senddate(String date);

	@Query(value = "SELECT * FROM TRMWORK_VIEW WHERE ACCT_NUM = ?1 AND TRUNC(TRAN_DATE) BETWEEN TO_DATE(?2, 'dd-MM-yyyy') AND TO_DATE(?3, 'dd-MM-yyyy') ORDER BY TRAN_DATE", nativeQuery = true)
	List<BAJ_TrmView_Entity> getTranlst2(String acct_num, String fromdate, String todate);

	@Query(value = "SELECT * FROM TRMWORK_VIEW WHERE ACCT_NUM IN (:acctNums) AND TRUNC(TRAN_DATE) BETWEEN TO_DATE(:fromDate, 'dd-MM-yyyy') AND TO_DATE(:toDate, 'dd-MM-yyyy') ORDER BY TRAN_DATE", nativeQuery = true)
	List<BAJ_TrmView_Entity> getTranlstForMultipleAccounts(@Param("acctNums") List<String> acctNums,
			@Param("fromDate") String fromDate, @Param("toDate") String toDate);

	@Query(value = "SELECT * " + "FROM TRMWORK_VIEW " + "WHERE tran_id IN (" + "    SELECT DISTINCT tran_id "
			+ "    FROM TRMWORK_VIEW " + "    WHERE acct_num = ?1 "
			+ "      AND TRUNC(TRAN_DATE) BETWEEN TO_DATE(?2, 'dd-MM-yyyy') AND TO_DATE(?3, 'dd-MM-yyyy')" + ") "
			+ "AND TRAN_DATE BETWEEN TO_DATE(?2, 'dd-MM-yyyy') AND TO_DATE(?3, 'dd-MM-yyyy') ORDER BY TRAN_DATE ASC", nativeQuery = true)
	List<BAJ_TrmView_Entity> getTranlstWithInnerQuery(String acct_num, String fromdate, String todate);

	@Query(value = "SELECT ACCT_NUM, "
			+ "SUM(CASE WHEN PART_TRAN_TYPE = 'debit' THEN TRAN_AMT ELSE 0 END) AS TOTAL_DEBIT_TRAN_AMT, "
			+ "SUM(CASE WHEN PART_TRAN_TYPE = 'credit' THEN TRAN_AMT ELSE 0 END) AS TOTAL_CREDIT_TRAN_AMT, "
			+ "(SUM(CASE WHEN PART_TRAN_TYPE = 'credit' THEN TRAN_AMT ELSE 0 END) - "
			+ " SUM(CASE WHEN PART_TRAN_TYPE = 'debit' THEN TRAN_AMT ELSE 0 END)) AS NET_TRAN_AMT "
			+ "FROM TRMWORK_VIEW " + "WHERE ACCT_NUM = ?1 "
			+ "AND TRAN_DATE BETWEEN TO_DATE(?2, 'dd-MM-yyyy') AND TO_DATE(?3, 'dd-MM-yyyy') "
			+ "GROUP BY ACCT_NUM", nativeQuery = true)
	Object[] getModelValue(String acctNum, String fromDate, String toDate);

	@Query(value = "SELECT * FROM TRMWORK_VIEW WHERE ACCT_NUM IN :acctNums ORDER BY TRAN_DATE ASC", nativeQuery = true)
	List<BAJ_TrmView_Entity> getFiveValues(@Param("acctNums") List<String> acctNums);

	@Query(value = "SELECT * FROM TRMWORK_VIEW WHERE ACCT_NUM IN :acctNums ORDER BY TRAN_DATE ASC", nativeQuery = true)
	List<BAJ_TrmView_Entity> getThreeValues(@Param("acctNums") List<String> acctNums);

	@Query(value = "SELECT * FROM TRMWORK_VIEW WHERE ACCT_NUM IN :acctNums ORDER BY TRAN_DATE ASC", nativeQuery = true)
	List<BAJ_TrmView_Entity> getTwoValues(@Param("acctNums") List<String> acctNums);

	@Query(value = "SELECT * FROM TRMWORK_VIEW WHERE ACCT_NUM IN :acctNums ORDER BY TRAN_DATE ASC", nativeQuery = true)
	List<BAJ_TrmView_Entity> getFourValues(@Param("acctNums") List<String> acctNums);
	
	@Query(value = "SELECT distinct(ACCT_NUM) from TRMWORK_VIEW where Tran_date=?1", nativeQuery = true)
	List<Object[]> getTranid(@Param("balancedate") Date balancedate);
	
	@Query(value = "SELECT distinct(TRAN_ID) from TRMWORK_VIEW where acct_num=?1 and Tran_date=?2", nativeQuery = true)
	List<Object[]> getTranDetails(String acctno,@Param("trandate") Date trandate);
	
	@Query(value = "SELECT distinct(PART_TRAN_ID) from TRMWORK_VIEW where acct_num=?1 and Tran_date=?2 and tran_id=?3", nativeQuery = true)
	List<Object[]> getpartTranDetails(String acctno,@Param("trandate") Date trandate,String tranid);
	
	@Query(value = "SELECT distinct(PART_TRAN_TYPE) from TRMWORK_VIEW where acct_num=?1 and Tran_date=?2 and tran_id=?3 and part_tran_id=?4", nativeQuery = true)
	List<Object[]> getpartTrantypeDetails(String acctno,@Param("trandate") Date trandate,String tranid,String partTranId);
	
	
	@Query(value = "SELECT * from TRMWORK_VIEW where Tran_date=?1", nativeQuery = true)
	List<BAJ_TrmView_Entity> getAllvalues1(@Param("trandate") Date trandate);
	
	@Query(value = "SELECT * from TRMWORK_VIEW where Tran_date=?1 and acct_num=?2", nativeQuery = true)
	List<BAJ_TrmView_Entity> getAllvalues2(@Param("trandate") Date trandate,String acctno);
	
	@Query(value = "SELECT * from TRMWORK_VIEW where Tran_date=?1 and acct_num=?2 and tran_id=?3 ", nativeQuery = true)
	List<BAJ_TrmView_Entity> getAllvalues3(@Param("trandate") Date trandate,String acctno,String tranid);
	
	@Query(value = "SELECT * from TRMWORK_VIEW where Tran_date=?1 and acct_num=?2 and tran_id=?3 and PART_TRAN_ID=?4", nativeQuery = true)
	List<BAJ_TrmView_Entity> getAllvalues4(@Param("trandate") Date trandate,String acctno,String tranid,String partTranId);
}
