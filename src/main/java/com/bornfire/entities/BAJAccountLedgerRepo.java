package com.bornfire.entities;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BAJAccountLedgerRepo extends JpaRepository<BAJAccountLedger_Entity, String>{

	@Query(value = "select * from COA_VIEW where ENTITY_FLG='Y' and DEL_FLG='N' ORDER BY ACCT_NUM ASC", nativeQuery = true)
	List<BAJAccountLedger_Entity> getList();

	@Query(value = "Select * From COA_VIEW Where acct_num =?1 ", nativeQuery = true)
	BAJAccountLedger_Entity getaccno(String acct_num);

	@Query(value = "SELECT DISTINCT GLSH_CODE, CLASSIFICATION, GL_DESC, ACCT_CRNCY, GL_CODE, GLSH_DESC, "
			+ "COUNT(GLSH_CODE) AS total_count, " + "SUM(CASE WHEN acct_bal > 0 THEN acct_bal ELSE 0 END) AS cr_amt, "
			+ "SUM(CASE WHEN acct_bal < 0 THEN ABS(acct_bal) ELSE 0 END) AS dr_amt " + "FROM COA_VIEW "
			+ "WHERE del_flg = 'N' " + "GROUP BY GLSH_CODE, CLASSIFICATION, GL_DESC, ACCT_CRNCY, GL_CODE, GLSH_DESC "
			+ "ORDER BY GLSH_CODE ASC", nativeQuery = true)
	Object[] getglcode();

	@Query(value = "SELECT * FROM COA_VIEW WHERE ENTITY_FLG='Y' and del_flg='N' ORDER BY ACCT_NUM, CLASSIFICATION ASC", nativeQuery = true)
	List<BAJAccountLedger_Entity> getListView();

	@Query(value = "select  * FROM COA_VIEW  where GLSH_CODE=?1", nativeQuery = true)
	List<BAJAccountLedger_Entity> getglsh(String glshCode);

	@Query(value = "SELECT * FROM COA_VIEW WHERE del_flg='N' AND classification='Income' ORDER BY CLASSIFICATION ASC", nativeQuery = true)
	List<BAJAccountLedger_Entity> getList3();

	@Query(value = "SELECT * FROM COA_VIEW WHERE del_flg='N' AND classification='Expenses' ORDER BY CLASSIFICATION ASC", nativeQuery = true)
	List<BAJAccountLedger_Entity> getList4();
	
    @Query(value = "SELECT GLSH_CODE, GLSH_DESC,COUNT(GLSH_CODE) as sum, acct_crncy, SUM(ACCT_BAL) FROM COA_VIEW WHERE del_flg='N' AND classification='Asset' GROUP BY GLSH_CODE, GLSH_DESC, acct_crncy ORDER BY GLSH_CODE ASC", nativeQuery = true)
    List<Object[]> getList1();

    
    @Query(value = "SELECT GLSH_CODE, GLSH_DESC, COUNT(GLSH_CODE) as sum, acct_crncy, SUM(ACCT_BAL) FROM COA_VIEW WHERE del_flg='N' AND classification='Liability' GROUP BY GLSH_CODE, GLSH_DESC, acct_crncy ORDER BY GLSH_CODE ASC", nativeQuery = true)
    List<Object[]> getList2();
    
    @Query(value = "SELECT " +
            "dv.gl_desc AS primary_gl_desc, " +
            "dv.gl_code, " +
            "dv.glsh_code, " +
            "dv.glsh_desc AS secondary_gl_desc, " +
            "cv.classification, " +
            "COUNT(dv.glsh_code) AS sum, " +
            "dv.acct_crncy, " +
            "CASE WHEN SUM(dv.tran_date_bal) > 0 THEN SUM(dv.tran_date_bal) ELSE 0 END AS credit, " +
            "CASE WHEN SUM(dv.tran_date_bal) < 0 THEN ABS(SUM(dv.tran_date_bal)) ELSE 0 END AS debit " +
        "FROM DAB_VIEW dv " +
        "JOIN COA_VIEW cv ON dv.acct_num = cv.acct_num " +
        "WHERE TO_DATE('31-03-2025', 'DD-MM-YYYY') BETWEEN dv.TRAN_DATE AND dv.END_TRAN_DATE " +
        "AND cv.del_flg = 'N' " +
        "GROUP BY dv.gl_desc, dv.gl_code, dv.glsh_code, dv.glsh_desc, dv.acct_crncy, cv.classification " +
        "ORDER BY dv.glsh_code ASC", 
        nativeQuery = true)
    List<Object[]> getBalanceWithClassification();
    
    @Query(value = "SELECT " +
            "dv.gl_desc AS primary_gl_desc, " +
            "dv.gl_code, " +
            "dv.glsh_code, " +
            "dv.glsh_desc AS secondary_gl_desc, " +
            "cv.classification, " +
            "COUNT(dv.glsh_code) AS sum, " +
            "dv.acct_crncy, " +
            "CASE WHEN SUM(dv.tran_date_bal) > 0 THEN SUM(dv.tran_date_bal) ELSE 0 END AS credit, " +
            "CASE WHEN SUM(dv.tran_date_bal) < 0 THEN ABS(SUM(dv.tran_date_bal)) ELSE 0 END AS debit " +
        "FROM DAB_VIEW dv " +
        "JOIN COA_VIEW cv ON dv.acct_num = cv.acct_num " +
        "WHERE :balancedate BETWEEN dv.TRAN_DATE AND dv.END_TRAN_DATE " +
        "AND cv.del_flg = 'N' " +
        "GROUP BY dv.gl_desc, dv.gl_code, dv.glsh_code, dv.glsh_desc, dv.acct_crncy, cv.classification " +
        "ORDER BY dv.glsh_code ASC", 
        nativeQuery = true)
    List<Object[]> getbalance(@Param("balancedate") Date balancedate);
    
    
    
    @Query(value =
		    "SELECT a.CLASSIFICATION, " +
		    "       a.ACCT_TYPE, " +
		    "       a.GL_CODE, " +
		    "       a.GLSH_CODE, " +
		    "       a.GL_DESC, " +
		    "       a.GLSH_DESC, " +
		    "       a.ACCT_NUM, " +
		    "       a.ACCT_NAME, " +
		    "       a.ACCT_CRNCY, " +
		    "       a.ACCT_STATUS, " +
		    "       DECODE(b.TRAN_DATE, TO_DATE('31-03-2025', 'DD-MM-YYYY'), b.TRAN_DR_BAL, 0) AS DR, " +
		    "       DECODE(b.TRAN_DATE, TO_DATE('31-03-2025', 'DD-MM-YYYY'), b.TRAN_CR_BAL, 0) AS CR, " +
		    "       b.TRAN_DATE_BAL, " +
		    "       b.TRAN_TOT_NET " +
		    "FROM DAB_VIEW b, " +
		    "     COA_VIEW a " +
		    "WHERE a.ACCT_NUM = b.ACCT_NUM " +
		    "  AND a.CLASSIFICATION = 'Income' " +
		    "  AND TO_DATE('31-03-2025', 'DD-MM-YYYY') BETWEEN b.TRAN_DATE AND b.END_TRAN_DATE " +
		    "ORDER BY a.CLASSIFICATION, a.ACCT_NUM, b.ACCT_NUM ASC",
		    nativeQuery = true)
		List<Object[]> getfilteredrec4();
    
		 @Query(value =
				    "SELECT a.CLASSIFICATION, " +
				    "       a.ACCT_TYPE, " +
				    "       a.GL_CODE, " +
				    "       a.GLSH_CODE, " +
				    "       a.GL_DESC, " +
				    "       a.GLSH_DESC, " +
				    "       a.ACCT_NUM, " +
				    "       a.ACCT_NAME, " +
				    "       a.ACCT_CRNCY, " +
				    "       a.ACCT_STATUS, " +
				    "       DECODE(b.TRAN_DATE, TO_DATE('31-03-2025', 'DD-MM-YYYY'), b.TRAN_DR_BAL, 0) AS DR, " +
				    "       DECODE(b.TRAN_DATE, TO_DATE('31-03-2025', 'DD-MM-YYYY'), b.TRAN_CR_BAL, 0) AS CR, " +
				    "       b.TRAN_DATE_BAL, " +
				    "       b.TRAN_TOT_NET " +
				    "FROM DAB_VIEW b, " +
				    "     COA_VIEW a " +
				    "WHERE a.ACCT_NUM = b.ACCT_NUM " +
				    "  AND a.CLASSIFICATION = 'Expenses' " +
				    "  AND TO_DATE('31-03-2025', 'DD-MM-YYYY') BETWEEN b.TRAN_DATE AND b.END_TRAN_DATE " +
				    "ORDER BY a.CLASSIFICATION, a.ACCT_NUM, b.ACCT_NUM ASC",
				    nativeQuery = true)
				List<Object[]> getfilteredrec5();
				
				
				
				 @Query(value =
						    "SELECT a.CLASSIFICATION, " +
						    "       a.ACCT_TYPE, " +
						    "       a.GL_CODE, " +
						    "       a.GLSH_CODE, " +
						    "       a.GL_DESC, " +
						    "       a.GLSH_DESC, " +
						    "       a.ACCT_NUM, " +
						    "       a.ACCT_NAME, " +
						    "       a.ACCT_CRNCY, " +
						    "       a.ACCT_STATUS, " +
						    "       DECODE(b.TRAN_DATE, TO_DATE('31-03-2025', 'DD-MM-YYYY'), b.TRAN_DR_BAL, 0) AS DR, " +
						    "       DECODE(b.TRAN_DATE, TO_DATE('31-03-2025', 'DD-MM-YYYY'), b.TRAN_CR_BAL, 0) AS CR, " +
						    "       b.TRAN_DATE_BAL, " +
						    "       b.TRAN_TOT_NET " +
						    "FROM DAB_VIEW b, " +
						    "     COA_VIEW a " +
						    "WHERE a.ACCT_NUM = b.ACCT_NUM " +
						    "  AND a.CLASSIFICATION = 'Asset' " +
						    "  AND TO_DATE('31-03-2025', 'DD-MM-YYYY') BETWEEN b.TRAN_DATE AND b.END_TRAN_DATE " +
						    "ORDER BY a.CLASSIFICATION, a.ACCT_NUM, b.ACCT_NUM ASC",
						    nativeQuery = true)
						List<Object[]> getfilteredrec2();
						
						@Query(value =
							    "SELECT a.CLASSIFICATION, " +
							    "       a.ACCT_TYPE, " +
							    "       a.GL_CODE, " +
							    "       a.GLSH_CODE, " +
							    "       a.GL_DESC, " +
							    "       a.GLSH_DESC, " +
							    "       a.ACCT_NUM, " +
							    "       a.ACCT_NAME, " +
							    "       a.ACCT_CRNCY, " +
							    "       a.ACCT_STATUS, " +
							    "       DECODE(b.TRAN_DATE, TO_DATE('31-03-2025', 'DD-MM-YYYY'), b.TRAN_DR_BAL, 0) AS DR, " +
							    "       DECODE(b.TRAN_DATE, TO_DATE('31-03-2025', 'DD-MM-YYYY'), b.TRAN_CR_BAL, 0) AS CR, " +
							    "       b.TRAN_DATE_BAL, " +
							    "       b.TRAN_TOT_NET " +
							    "FROM DAB_VIEW b, " +
							    "     COA_VIEW a " +
							    "WHERE a.ACCT_NUM = b.ACCT_NUM " +
							    "  AND a.CLASSIFICATION IN ('Liability', 'Equity') " +
							    "  AND TO_DATE('31-03-2025', 'DD-MM-YYYY') BETWEEN b.TRAN_DATE AND b.END_TRAN_DATE " +
							    "ORDER BY a.CLASSIFICATION, a.ACCT_NUM, b.ACCT_NUM ASC",
							    nativeQuery = true)
							List<Object[]> getfilteredrec3();
		    
    
							 @Query(value =
									    "SELECT a.CLASSIFICATION, " +
									    "       a.ACCT_TYPE, " +
									    "       a.GL_CODE, " +
									    "       a.GLSH_CODE, " +
									    "       a.GL_DESC, " +
									    "       a.GLSH_DESC, " +
									    "       a.ACCT_NUM, " +
									    "       a.ACCT_NAME, " +
									    "       a.ACCT_CRNCY, " +
									    "       a.ACCT_STATUS, " +
									    "       DECODE(b.TRAN_DATE, TO_DATE('31-03-2025', 'DD-MM-YYYY'), b.TRAN_DR_BAL, 0) AS DR, " +
									    "       DECODE(b.TRAN_DATE, TO_DATE('31-03-2025', 'DD-MM-YYYY'), b.TRAN_CR_BAL, 0) AS CR, " +
									    "       b.TRAN_DATE_BAL, " +
									    "       b.TRAN_TOT_NET " +
									    "FROM DAB_VIEW b, " +
									    "     COA_VIEW a " +
									    "WHERE a.ACCT_NUM = b.ACCT_NUM " +
									    "  AND a.CLASSIFICATION = 'Asset' " +
									    "  AND :balancedate BETWEEN b.TRAN_DATE AND b.END_TRAN_DATE " +
									    "ORDER BY a.CLASSIFICATION, a.ACCT_NUM, b.ACCT_NUM ASC",
									    nativeQuery = true)
									List<Object[]> getfilteredrec(@Param("balancedate") Date balancedate);

									
									@Query(value =
										    "SELECT a.CLASSIFICATION, " +
										    "       a.ACCT_TYPE, " +
										    "       a.GL_CODE, " +
										    "       a.GLSH_CODE, " +
										    "       a.GL_DESC, " +
										    "       a.GLSH_DESC, " +
										    "       a.ACCT_NUM, " +
										    "       a.ACCT_NAME, " +
										    "       a.ACCT_CRNCY, " +
										    "       a.ACCT_STATUS, " +
										    "       DECODE(b.TRAN_DATE, TO_DATE('31-03-2025', 'DD-MM-YYYY'), b.TRAN_DR_BAL, 0) AS DR, " +
										    "       DECODE(b.TRAN_DATE, TO_DATE('31-03-2025', 'DD-MM-YYYY'), b.TRAN_CR_BAL, 0) AS CR, " +
										    "       b.TRAN_DATE_BAL, " +
										    "       b.TRAN_TOT_NET " +
										    "FROM DAB_VIEW b, " +
										    "     COA_VIEW a " +
										    "WHERE a.ACCT_NUM = b.ACCT_NUM " +
										    "  AND a.CLASSIFICATION IN ('Liability', 'Equity') " +
										    "  AND :balancedate BETWEEN b.TRAN_DATE AND b.END_TRAN_DATE " +
										    "ORDER BY a.CLASSIFICATION, a.ACCT_NUM, b.ACCT_NUM ASC",
										    nativeQuery = true)
										List<Object[]>   getfilteredrec1(@Param("balancedate") Date balancedate);

	    
}
