package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface AttendanceRegisterGetRep extends JpaRepository<AttendanceRegisterGet, AttendanceID> {

	@Query(value = "SELECT * FROM EMP_ATT_MASTER where cal_year = ?1 and cal_month=?2 and cal_date=?3 order by emp_id", nativeQuery = true)
	List<AttendanceRegisterGet>  getAttAlls(String year,String month, String day);
	
	
	 @Query(value = "SELECT DISTINCT E.* FROM EMP_ATT_MASTER E INNER JOIN RESOURCE_MASTER R ON E.emp_id = R.employee_id WHERE E.cal_year = ?1 AND E.cal_month = ?2 AND E.cal_date = ?3 AND R.del_flg = 'N' ORDER BY E.emp_id", nativeQuery = true)
	    List<AttendanceRegisterGet> getAttAll(String year, String month, String day);
	
	@Query(value = "SELECT * FROM EMP_ATT_MASTER where emp_id=?1 and cal_year = ?2 and cal_month=?3 and cal_date=?4 order by emp_id", nativeQuery = true)
	List<AttendanceRegisterGet>  getAttAllInquires(String userid,String year,String month, String day);
	
	@Query(value = " SELECT * FROM EMP_ATT_MASTER WHERE emp_id = ?1 AND cal_year=?2 and cal_month = ?3 order by cal_date", nativeQuery = true)
	public List<AttendanceRegisterGet> getAssociatedata(String userid, String year, String month);
	
	
	/*
	 * @Query(value =
	 * " SELECT * FROM EMP_ATT_MASTER WHERE emp_id = ?1 AND cal_year=?2 and cal_month = ?3 order by cal_date"
	 * , nativeQuery = true) public List<AttendanceRegisterGet>
	 * getAssociatedata(String userid, String year, String month);
	 */
	
	@Query(value = "SELECT * FROM EMP_ATT_MASTER WHERE EMP_ID = ?1 AND cal_month = ?2 and cal_date =?3 and cal_year = ?4 and emp_remarks='On-Duty'", nativeQuery = true)
	AttendanceRegisterGet getdatapresentOnduty(String userid, String cal_month, String cal_date, String cal_year);
	
	@Query(value = "SELECT * FROM EMP_ATT_MASTER WHERE EMP_ID = ?1 AND cal_month = ?2 and cal_date =?3 and cal_year = ?4 and emp_remarks='Leave'", nativeQuery = true)
	AttendanceRegisterGet getdatapresentLeave(String userid, String cal_month, String cal_date, String cal_year);
	
	@Query(value = "SELECT * FROM EMP_ATT_MASTER where emp_id=?1 and login_time=?2", nativeQuery = true)
	AttendanceRegisterGet  getAttendance(String empid,String login_time);
	
	@Query(value = "SELECT * FROM EMP_ATT_MASTER where emp_id=?1 and cal_year=?2 and cal_month=?3 and cal_date=?4", nativeQuery = true)
	AttendanceRegisterGet  getAttendanceDate(String empid,String year,String month,String day); 
	
	@Query(value = "SELECT rm.MOBILE as phone FROM RESOURCE_MASTER rm JOIN EMP_ATT_MASTER ea ON rm.RESOURCE_ID = ea.EMP_ID WHERE ea.EMP_REMARKS = 'On-Duty' AND ea.CAL_YEAR = ?1 AND ea.CAL_MONTH = ?2 AND ea.CAL_DATE = ?3", nativeQuery = true)
	List<String> getsms(String cal_year,String cal_month,String cal_date);
	

	@Query(value = "SELECT rm.mobile FROM RESOURCE_MASTER rm WHERE rm.del_flg = 'N' AND rm.entity_flg = 'Y' AND NOT EXISTS (SELECT * FROM EMP_ATT_MASTER em WHERE rm.EMPLOYEE_ID = em.EMP_ID AND em.del_flg = 'N' AND em.CAL_DATE = '22' AND em.CAL_YEAR = '2024' AND em.CAL_MONTH = '02')", nativeQuery = true)
	List<String> getsmss(String cal_year,String cal_month,String cal_date);

	@Query(value = "SELECT * from EMP_ATT_MASTER ", nativeQuery = true)
	List<AttendanceRegisterGet> getdemo(String cal_day);
}