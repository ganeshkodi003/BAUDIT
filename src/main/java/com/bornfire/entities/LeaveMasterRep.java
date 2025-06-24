package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;




public interface LeaveMasterRep extends CrudRepository<LeaveMaster, BigDecimal> {

	public Optional<LeaveMaster> findById(BigDecimal empId);
	
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE record_no= ?1 " , nativeQuery = true)
	LeaveMaster findByRecId(BigDecimal id1);

	@Query(value = "SELECT * FROM LEAVE_MASTER where employee_id = ?1 ", nativeQuery = true)
	LeaveMaster findByEmpId(String id1);

	@Query(value = "SELECT * FROM LEAVE_MASTER", nativeQuery = true)
	List<LeaveMaster> getLeavelist();
	
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE EMPLOYEE_ID=?1 AND LEAVE_CATEGORY=?2 AND YEAR=?3", nativeQuery = true)
	List<LeaveMaster> getLeaveReport(String Employeeid, String Leave,String Year);
	
	@Query(value = "SELECT * FROM LEAVE_MASTER where year='2021'", nativeQuery = true)
	List<LeaveMaster> getAdminLeaveList(BigDecimal year);
	
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE year IN ('2023', '2024')", nativeQuery = true)
	List<LeaveMaster> getAdminLeaveList1(BigDecimal year);
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE year IN ('2018', '2019', '2020', '2021', '2022', '2023', '2024')", nativeQuery = true)
	List<LeaveMaster> getAdminLeaveList2(BigDecimal year);

	
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE year = '2024'AND status != 'Rejected' ORDER BY TO_DATE DESC", nativeQuery = true)
	List<LeaveMaster> getAdminLeaveList11(BigDecimal year);
	
	@Query(value = "SELECT EMPLOYEE_ID, EMPLOYEE_NAME, YEAR, STATUS, " +
	        "SUM(NO_OF_DAYS) AS TOTAL_NO_OF_DAYS, " +
	        "12 - COALESCE(SUM(NO_OF_DAYS), 0) AS LEAVE_BALANCE " +
	        "FROM LEAVE_MASTER " +
	        "WHERE YEAR ='2025' AND STATUS = 'Approved' " +
	        "GROUP BY EMPLOYEE_ID, EMPLOYEE_NAME, YEAR, STATUS " +
	        "ORDER BY EMPLOYEE_ID ASC", 
	nativeQuery = true)
	List<Object[]> findLeaveSummaryByYear();

	@Query(value = "SELECT EMPLOYEE_ID, EMPLOYEE_NAME, YEAR, STATUS, " +
            "SUM(NO_OF_DAYS) AS TOTAL_NO_OF_DAYS, " +
            "12 - COALESCE(SUM(NO_OF_DAYS), 0) AS LEAVE_BALANCE " +
            "FROM LEAVE_MASTER " +
            "WHERE YEAR = '2023' AND STATUS = 'Approved' " +
            "GROUP BY EMPLOYEE_ID, EMPLOYEE_NAME, YEAR, STATUS " +
            "ORDER BY EMPLOYEE_ID ASC", nativeQuery = true)
List<Object[]> getAdminLeaveList111(String year);


@Query(value = "SELECT EMPLOYEE_ID, EMPLOYEE_NAME, YEAR, STATUS, " +
        "SUM(NO_OF_DAYS) AS TOTAL_NO_OF_DAYS, " +
        "(12 - COALESCE(SUM(NO_OF_DAYS), 0)) AS LEAVE_BALANCE " +
        "FROM LEAVE_MASTER " +
        "WHERE YEAR = :year AND STATUS = 'Approved' " +
        "GROUP BY EMPLOYEE_ID, EMPLOYEE_NAME, YEAR, STATUS " +
        "ORDER BY EMPLOYEE_ID ASC", nativeQuery = true)
List<Object[]> getAdmindetailsit1(@Param("year") String year);

@Query(value = "SELECT EMPLOYEE_ID, EMPLOYEE_NAME, YEAR, STATUS, " +
        "SUM(NO_OF_DAYS) AS TOTAL_NO_OF_DAYS, " +
        "(12 - COALESCE(SUM(NO_OF_DAYS), 0)) AS LEAVE_BALANCE " +
        "FROM LEAVE_MASTER " +
        "WHERE YEAR = '2021' AND STATUS = 'Approved' " +
        "GROUP BY EMPLOYEE_ID, EMPLOYEE_NAME, YEAR, STATUS " +
        "ORDER BY EMPLOYEE_ID ASC", nativeQuery = true)
List<Object[]> getAdmindetailsit2(String year);

@Query(value = "SELECT EMPLOYEE_ID, EMPLOYEE_NAME, YEAR, STATUS, " +
        "SUM(NO_OF_DAYS) AS TOTAL_NO_OF_DAYS, " +
        "(12 - COALESCE(SUM(NO_OF_DAYS), 0)) AS LEAVE_BALANCE " +
        "FROM LEAVE_MASTER " +
        "WHERE YEAR = '2020' AND STATUS = 'Approved' " +
        "GROUP BY EMPLOYEE_ID, EMPLOYEE_NAME, YEAR, STATUS " +
        "ORDER BY EMPLOYEE_ID ASC", nativeQuery = true)
List<Object[]> getAdmindetailsit3(String year);

@Query(value = "SELECT EMPLOYEE_ID, EMPLOYEE_NAME, YEAR, STATUS, " +
        "SUM(NO_OF_DAYS) AS TOTAL_NO_OF_DAYS, " +
        "(12 - COALESCE(SUM(NO_OF_DAYS), 0)) AS LEAVE_BALANCE " +
        "FROM LEAVE_MASTER " +
        "WHERE YEAR = '2019' AND STATUS = 'Approved' " +
        "GROUP BY EMPLOYEE_ID, EMPLOYEE_NAME, YEAR, STATUS " +
        "ORDER BY EMPLOYEE_ID ASC", nativeQuery = true)
List<Object[]> getAdmindetailsit4(String year);

@Query(value = "SELECT EMPLOYEE_ID, EMPLOYEE_NAME, YEAR, STATUS, " +
        "SUM(NO_OF_DAYS) AS TOTAL_NO_OF_DAYS, " +
        "(12 - COALESCE(SUM(NO_OF_DAYS), 0)) AS LEAVE_BALANCE " +
        "FROM LEAVE_MASTER " +
        "WHERE YEAR = '2018' AND STATUS = 'Approved' " +
        "GROUP BY EMPLOYEE_ID, EMPLOYEE_NAME, YEAR, STATUS " +
        "ORDER BY EMPLOYEE_ID ASC", nativeQuery = true)
List<Object[]> getAdmindetailsit5(String year);

	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE employee_id = 'BFI0148'", nativeQuery = true)
	List<LeaveMaster> gettestig(String year);
	@Query(value = "SELECT EMPLOYEE_ID, EMPLOYEE_NAME, YEAR, STATUS, " +
            "SUM(NO_OF_DAYS) AS TOTAL_NO_OF_DAYS, " +
            "(12 - COALESCE(SUM(NO_OF_DAYS), 0)) AS LEAVE_BALANCE " +
            "FROM LEAVE_MASTER " +
            "WHERE YEAR = '2024' AND STATUS = 'Approved' " +
            "GROUP BY EMPLOYEE_ID, EMPLOYEE_NAME, YEAR, STATUS " +
            "ORDER BY EMPLOYEE_ID ASC", nativeQuery = true)
List<Object[]> getAdmindetailsit6(String year);

@Query(value = "SELECT EMPLOYEE_ID, EMPLOYEE_NAME, YEAR, STATUS, " +
        "SUM(NO_OF_DAYS) AS TOTAL_NO_OF_DAYS, " +
        "(12 - COALESCE(SUM(NO_OF_DAYS), 0)) AS LEAVE_BALANCE " +
        "FROM LEAVE_MASTER " +
        "WHERE YEAR = '2025' AND STATUS = 'Approved' " +
        "GROUP BY EMPLOYEE_ID, EMPLOYEE_NAME, YEAR, STATUS " +
        "ORDER BY EMPLOYEE_ID ASC", nativeQuery = true)
List<Object[]> getAdmindetailsit7(String year);
	
	
	@Query(value = "SELECT * FROM LEAVE_MASTER where status != 'Approved'", nativeQuery = true)
	List<LeaveMaster> getLeavelist1();
	
	@Query(value = "SELECT * FROM LEAVE_MASTER where employee_id = ?1 ", nativeQuery = true)
	List<LeaveMaster> getLeaveListbyid(String userid);
	
	@Query(value = "SELECT * FROM LEAVE_MASTER where record_no = ?1 ", nativeQuery = true)
	LeaveMaster getLeaveListbyRecord(BigDecimal resId);
	
	@Query(value = "SELECT * FROM LEAVE_MASTER where from_date = ?1 AND EMPLOYEE_ID=?2", nativeQuery = true)
	LeaveMaster getLeavebyFromDate(String date,String empid);
	
	@Query(value = "SELECT * FROM LEAVE_MASTER where from_date = ?1 AND EMPLOYEE_ID=?2 ", nativeQuery = true)
	LeaveMaster getLeavebyFromDateapproved(String date,String empid);
	
	@Query(value = "SELECT * FROM LEAVE_MASTER where leave_reference = ?1 ", nativeQuery = true)
	LeaveMaster getleaveMaster(String userid);
	
	
	@Query(value = "SELECT * FROM LEAVE_MASTER where EMPLOYEE_ID=?1 AND from_date=?2 AND YEAR=?3  ", nativeQuery = true)
	LeaveMaster getleaveMastersheet(String employee_id,BigDecimal year, Date from_date,Date to_date );
	
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE year = '2024'", nativeQuery = true)
	List<LeaveMaster> finduser1list();
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE year = '2023'", nativeQuery = true)
	List<LeaveMaster> finduser1listthree();
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE year = '2022'", nativeQuery = true)
	List<LeaveMaster> finduser1listtwo();
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE year = '2021'", nativeQuery = true)
	List<LeaveMaster> finduser1listone();
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE year = '2020'", nativeQuery = true)
	List<LeaveMaster> finduser1listzero();
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE year = '2019'", nativeQuery = true)
	List<LeaveMaster> finduser1listnine();
	@Query(value = "SELECT * FROM LEAVE_MASTER WHERE year = '2018'", nativeQuery = true)
	List<LeaveMaster> finduser1listeight();
	
	@Query(value = "SELECT EMPLOYEE_ID, EMPLOYEE_NAME, YEAR, STATUS, SUM(NO_OF_DAYS) AS TOTAL_NO_OF_DAYS " +
            "FROM LEAVE_MASTER " +
            "WHERE YEAR = '2024' AND STATUS = 'Approved' " +
            "GROUP BY EMPLOYEE_ID, EMPLOYEE_NAME, YEAR, STATUS " +
            "ORDER BY EMPLOYEE_ID ASC", 
    nativeQuery = true)
List<Object[]> findLeaveSummaryByYear1();





@Query(value = "SELECT * FROM LEAVE_MASTER WHERE EMPLOYEE_ID =?1 AND EMPLOYEE_NAME = ?2 AND YEAR =?3 ", 
nativeQuery = true)
List<LeaveMaster> findLeaveSummaryByYearandid(String emp_id, String emp_name, BigDecimal year);

@Modifying
@Transactional
@Query(value = 
    "DECLARE " +
    "    v_leave_blc NUMBER := 12; " + // Initialize LEAVE_BLC to 12
    "    CURSOR c_leaves IS " +
    "        SELECT FROM_DATE, NO_OF_DAYS " +
    "        FROM LEAVE_MASTER " +
    "        WHERE EMPLOYEE_ID = ?1 " +
    "          AND EXTRACT(YEAR FROM FROM_DATE) = ?2 " +
    "          AND STATUS = 'Approved' " +
    "        ORDER BY FROM_DATE ASC; " + // Sort by FROM_DATE in ascending order
    "BEGIN " +
    "    FOR r IN c_leaves LOOP " +
    "        v_leave_blc := v_leave_blc - r.NO_OF_DAYS; " +
    "        UPDATE LEAVE_MASTER " +
    "        SET LEAVE_BLC = v_leave_blc " +
    "        WHERE EMPLOYEE_ID = ?1 " +
    "          AND FROM_DATE = r.FROM_DATE " +
    "          AND EXTRACT(YEAR FROM FROM_DATE) = ?2 " +
    "          AND STATUS = 'Approved'; " +
    "    END LOOP; " +
    "END",
    nativeQuery = true
)
int updateLeaveBalances1(String employeeId, String year);


@Procedure(procedureName = "UPDATE_LEAVE_BALANCES")
int updateLeaveBalances2(String employeeId, int year);

@Modifying
@Transactional
@Query(value = "BEGIN UPDATE_LEAVE_BALANCES(:employeeId, :year); END;", nativeQuery = true)
int updateLeaveBalances(@Param("employeeId") String employeeId, @Param("year") int year);

@Query(value = "SELECT 12 - COALESCE(SUM(NO_OF_DAYS), 0) AS RESULT FROM LEAVE_MASTER WHERE employee_id = ?1  AND year = ?2", nativeQuery = true)
List<LeaveMaster> remaining_leavedemo(String employee,String year );


/*
 * @Query(value =
 * "SELECT EMPLOYEE_ID,12 - SUM(NO_OF_DAYS) AS LEAVE_BLC FROM LEAVE_MASTER WHERE EMPLOYEE_ID = ?1  AND YEAR = '2024' AND STATUS = 'Approved' GROUP BY EMPLOYEE_ID ORDER BY  MIN(FROM_DATE) "
 * , nativeQuery = true) List<Object[]> remaining_leave_applyleave(String
 * userid);
 */

@Query(value = "SELECT EMP_NO,CL_BAL FROM BTM_ELE WHERE EMP_NO= ?1 AND DEL_FLG='N' ", nativeQuery = true)
List<Object[]> remaining_leave_applyleave(String userid);



}