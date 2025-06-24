package com.bornfire.entities;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Account_Ledger_Rep extends JpaRepository<Account_Ledger_Entity, journalentries_embededid> {
	
	@Query(value = "SELECT TRAN_DATE,TRAN_ID || '/' || PART_TRAN_ID AS TRANSACTION_ID, TRAN_PARTICULAR,ACCT_CRNCY, TRAN_AMT,CASE PART_TRAN_TYPE WHEN 'debit' THEN TRAN_AMT ELSE 0 END as Credit,CASE PART_TRAN_TYPE  WHEN 'credit' THEN TRAN_AMT ELSE 0 END as Debit FROM BTM_JOURNAL_ENTRIES WHERE acct_num =?1 ORDER BY TRAN_DATE", nativeQuery = true)
	List<Object[]> getList(@Param("acct_num") String acct_num);

	@Query(value = "select  * from BTM_JOURNAL_ENTRIES ORDER BY TRAN_ID ASC, PART_TRAN_ID ASC", nativeQuery = true)
	List<Account_Ledger_Entity> findByjournal();

	@Query(value = "SELECT * FROM BTM_JOURNAL_ENTRIES WHERE TRAN_ID =?1 AND PART_TRAN_ID=?2 ", nativeQuery = true)
	Account_Ledger_Entity getjourform(String tran_id,String part_tranid);

	@Query(value = "SELECT * FROM BTM_JOURNAL_ENTRIES  WHERE acct_name LIKE '%GST%' ORDER BY TRAN_ID", nativeQuery = true)
	List<Account_Ledger_Entity> findByjournalgst();
	
	@Query(value = "SELECT * FROM BTM_JOURNAL_ENTRIES  ORDER BY tran_id", nativeQuery = true)
	List<Account_Ledger_Entity> popup();
	
	//@Query(value = "select * from INFOSYS.BDD01 aa where aa.srl_no =?2 and aa.syn_loan_ref = ?1 union all select * from INFOSYS.GUARANTOR_DETAILS bb where bb.srl_no =?2 and bb.syn_loan_ref = ?1", nativeQuery = true)
	//Account_Ledger_Entity getValueByLoanRefAndSrlNo(String syn_loan_ref, String srl_no);
	
	@Query(value = "select * from BTM_JOURNAL_ENTRIES aa where aa.part_tran_id =?3 and aa.tran_id =?1 and aa.acct_num = ?2 ", nativeQuery = true)
	Account_Ledger_Entity getValuepop(String tran_id, String acct_num,String part_tran_id);

	@Query(value= "SELECT tran_id\n"
			+ "FROM BTM_JOURNAL_ENTRIES ORDER BY entry_time DESC FETCH FIRST 1 ROW ONLY",nativeQuery =true)
	String getlast();
	
	@Query(value = "SELECT SRL_NO_SEQ.NEXTVAL AS SRL_NO FROM DUAL", nativeQuery = true)
	String getsrlNo();
	
	/*------------------------ gst---------------------------------------------------- */
	@Query(value = "select  * from BTM_JOURNAL_ENTRIES ", nativeQuery = true)
	List<Account_Ledger_Entity> findbyid1();
	
	@Query(value= "SELECT  * from BTM_JOURNAL_ENTRIES where tran_id=?!",nativeQuery =true)
	String gettranid(String tran_id);
	
	
}
