package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BAJ_DABView_Rep extends JpaRepository<BAJ_DABView_Entity,String >{

	@Query(value = "SELECT TRAN_DATE, TRAN_DATE_BAL FROM ( " + "SELECT TRAN_DATE, TRAN_DATE_BAL " + "FROM  DAB_VIEW "
			+ "WHERE ACCT_NUM = :acct_num "
			+ "AND TO_DATE(:fromdateref, 'DD-MM-YYYY') - 1 BETWEEN TRAN_DATE AND END_TRAN_DATE " + "UNION ALL "
			+ "SELECT TRAN_DATE, TRAN_DATE_BAL " + "FROM  DAB_VIEW " + "WHERE ACCT_NUM = :acct_num "
			+ "AND TRAN_DATE_BAL IS NOT NULL " + "AND TRAN_DATE < TO_DATE(:fromdateref, 'DD-MM-YYYY') "
			+ "ORDER BY TRAN_DATE DESC " + "FETCH FIRST 1 ROW ONLY) " + "FETCH FIRST 1 ROW ONLY", nativeQuery = true)
	Object[] getTranlst(@Param("acct_num") String acct_num, @Param("fromdateref") String fromdateref);

	@Query(value = "SELECT TRAN_DATE, TRAN_DATE_BAL FROM ( " + "SELECT TRAN_DATE, TRAN_DATE_BAL " + "FROM  DAB_VIEW "
			+ "WHERE ACCT_NUM = :acct_num "
			+ "AND TO_DATE('01-04-2024', 'DD-MM-YYYY') - 1 BETWEEN TRAN_DATE AND END_TRAN_DATE " + "UNION ALL "
			+ "SELECT TRAN_DATE, TRAN_DATE_BAL " + "FROM  DAB_VIEW " + "WHERE ACCT_NUM = :acct_num "
			+ "AND TRAN_DATE_BAL IS NOT NULL " + "AND TRAN_DATE < TO_DATE('01-04-2024', 'DD-MM-YYYY') "
			+ "ORDER BY TRAN_DATE DESC " + "FETCH FIRST 1 ROW ONLY) " + "FETCH FIRST 1 ROW ONLY", nativeQuery = true)
	Object[] getTranlst2(@Param("acct_num") String acct_num);

	@Query(value = "SELECT gl_desc AS primary_gl_desc, " + "gl_code, " + "gl_desc, " + "glsh_code, "
			+ "gl_desc AS secondary_gl_desc, " + "COUNT(GLSH_CODE) AS sum, " + "acct_crncy, "
			+ "CASE WHEN SUM(tran_date_bal) > 0 THEN SUM(tran_date_bal) ELSE 0 END AS credit, "
			+ "CASE WHEN SUM(tran_date_bal) < 0 THEN ABS(SUM(tran_date_bal)) ELSE 0 END AS debit " + "FROM DAB_VIEW "
			+ "WHERE :balancedate BETWEEN TRAN_DATE AND END_TRAN_DATE "
			+ "GROUP BY gl_desc, gl_code, glsh_code, acct_crncy " + "ORDER BY glsh_code ASC", nativeQuery = true)
	List<Object[]> getbalance(@Param("balancedate") Date balancedate);

	@Query(value = "SELECT ACCT_NUM, acct_name, glsh_code, "
			+ "SUM(CASE WHEN tran_date_bal > 0 THEN tran_date_bal ELSE 0 END) AS total_credit, "
			+ "SUM(CASE WHEN tran_date_bal < 0 THEN ABS(tran_date_bal) ELSE 0 END) AS total_debit " + "FROM DAB_VIEW "
			+ "WHERE :formattedDate BETWEEN tran_date AND end_tran_date AND glsh_code = :glshCode "
			+ "GROUP BY ACCT_NUM, acct_name, glsh_code", nativeQuery = true)
	List<Object[]> getAccountBalancesByGlshCode(@Param("formattedDate") String formattedDate,
			@Param("glshCode") String glshCode);

	@Query(value = "SELECT * FROM DAB_VIEW WHERE gl_desc='Income' AND :balancedate BETWEEN TRAN_DATE AND END_TRAN_DATE", nativeQuery = true)
	List<BAJ_DABView_Entity> getfilteredrec2(@Param("balancedate") Date balancedate);

	@Query(value = "SELECT * FROM DAB_VIEW WHERE gl_desc='Expense' AND :balancedate BETWEEN TRAN_DATE AND END_TRAN_DATE", nativeQuery = true)
	List<BAJ_DABView_Entity> getfilteredrec3(@Param("balancedate") Date balancedate); 
	
	
	@Query(value = "SELECT  GLSH_CODE, GLSH_DESC,COUNT(GLSH_CODE) as sum, acct_crncy, SUM(tran_date_bal) FROM DAB_VIEW WHERE gl_desc='Asset' AND :balancedate BETWEEN TRAN_DATE AND END_TRAN_DATE GROUP BY GLSH_CODE, GLSH_DESC, acct_crncy ORDER BY GLSH_CODE ASC", nativeQuery = true)
	List<Object[]> getfilteredrec(@Param("balancedate") Date balancedate);

	@Query(value = "SELECT  GLSH_CODE, GLSH_DESC,COUNT(GLSH_CODE) as sum, acct_crncy, SUM(tran_date_bal) FROM DAB_VIEW WHERE gl_desc='Liability' AND :balancedate BETWEEN TRAN_DATE AND END_TRAN_DATE GROUP BY GLSH_CODE, GLSH_DESC, acct_crncy ORDER BY GLSH_CODE ASC", nativeQuery = true)
	List<Object[]> getfilteredrec1(@Param("balancedate") Date balancedate);
	
	
	
}
