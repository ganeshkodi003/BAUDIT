package com.bornfire.entities;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface AttendanceRegisterRep extends JpaRepository<AttendanceRegister, AttendanceID> {

	public Optional<AttendanceRegister> findById(String emp_id);

	@Query(value = "SELECT * FROM EMP_ATT_MASTER where emp_id = ?1 ", nativeQuery = true)
	AttendanceRegister findByEmpId(String id1);

	@Query(value = "SELECT * FROM EMP_ATT_MASTER where login_time = ?1 ", nativeQuery = true)
	List<AttendanceRegister>  getALL(Date date);
	
	@Query(value = "SELECT * FROM EMP_ATT_MASTER", nativeQuery = true)
	List<AttendanceRegister>  getAbsent();
	
	@Query(value = "SELECT * FROM EMP_ATT_MASTER where cal_year = ?1 and cal_month=?2 and cal_date=?3 order by emp_id", nativeQuery = true)
	List<AttendanceRegister>  getAtt(String year,String month, String day);

	@Query(value = "SELECT * FROM EMP_ATT_MASTER where login_time=?1 order by login_time1", nativeQuery = true)
	List<AttendanceRegister>  getDailyReport(String login_time); 
	
	
	@Query(value = "SELECT * FROM EMP_ATT_MASTER where cal_month=?1 and cal_year=?2 order by emp_id", nativeQuery = true)
	List<AttendanceRegister>  getMonthReport(String cal_month,String cal_year); 
	
	@Query(value = "SELECT * FROM EMP_ATT_MASTER", nativeQuery = true)
	String[]  getALL2();
	

	
	@Query(value = "select ATTENDANCE_SRL.nextval from dual ", nativeQuery = true)
	String getsrl();
	 
	
	@Query(value = "SELECT * FROM EMP_ATT_MASTER WHERE EMP_ID = ?1 AND cal_month = ?2 and cal_date =?3 and cal_year = ?4 ", nativeQuery = true)
	AttendanceRegister getdatapresent(String userid, String cal_month, String cal_date, String cal_year);
	
	@Query(value = "SELECT * FROM EMP_ATT_MASTER WHERE EMP_ID = ?1 AND cal_month = ?2 and cal_date =?3 and cal_year = ?4 AND EMP_REMARKS='On-Duty'", nativeQuery = true)
	AttendanceRegister getdatapresentOnduty(String userid, String cal_month, String cal_date, String cal_year);
	
	@Query(value = "SELECT * FROM EMP_ATT_MASTER WHERE EMP_ID = ?1 AND cal_month = ?2 and cal_date =?3 and cal_year = ?4 and emp_remarks='Leave'", nativeQuery = true)
	AttendanceRegister getdatapresentLeave(String userid, String cal_month, String cal_date, String cal_year);
	
	@Query(value = "select count(EMP_ID) From EMP_ATT_MASTER where login_time = ?1", nativeQuery = true)
	int getALLpresent(Date dat1);
	
	@Query(value = "select count(Distinct EMP_ID) From EMP_ATT_MASTER where cal_year = ?1 and cal_month=?2 and cal_date=?3 and emp_remarks='Present'", nativeQuery = true)
	int getALpresent(String year , String month , String day);
	
	@Query(value = "select count(EMP_ID) From EMP_ATT_MASTER where cal_year = ?1 and cal_month=?2 and cal_date=?3 and emp_remarks='On-Duty'", nativeQuery = true)
	AttendanceRegister getOnduty(String year , String month , String day);
	
	@Query(value = "select count(EMP_ID) From EMP_ATT_MASTER where cal_year = ?1 and cal_month=?2 and cal_date=?3 and emp_remarks='On-Duty'", nativeQuery = true)
	int getALLOnDuty(String year , String month , String day);
	
	@Query(value = "select count(EMP_ID) From EMP_ATT_MASTER where cal_year = ?1 and cal_month=?2 and cal_date=?3 and emp_remarks='Leave'", nativeQuery = true)
	int getALLAbsent(String year , String month , String day);

	
	@Query(value = "select * From EMP_ATT_MASTER where cal_year = ?1 and cal_month=?2 and cal_date=?3 and emp_remarks='Absent'", nativeQuery = true)
	List<AttendanceRegister> getALLabsent(String year , String month , String day);
	
	
	@Query(value = " SELECT * FROM EMP_ATT_MASTER WHERE CAL_MONTH = ?1", nativeQuery = true)
	 List<AttendanceRegister> getMonthly(String month);
	
	
	@Query(value = " SELECT count(EMP_ID) FROM EMP_ATT_MASTER WHERE cal_month = ?1", nativeQuery = true)
	 String getPresentcnt1(String month);
	
	@Query(value = " SELECT count(EMP_ID) FROM EMP_ATT_MASTER WHERE emp_id = ?1 AND cal_year = ?2 AND cal_month=?3 AND EMP_REMARKS='Present' AND TO_CHAR (login_time, 'DY', 'NLS_DATE_LANGUAGE=ENGLISH') NOT IN ('SAT', 'SUN') ", nativeQuery = true)
	 String getPresentcnt(String resource_id, String year , String monht);
	
	
	@Query(value = " SELECT * FROM EMP_ATT_MASTER WHERE emp_id = ?1 AND cal_year=?2 and cal_month = ?3 order by cal_date", nativeQuery = true)
	public List<AttendanceRegister> getdata(String userid, String year, String month);
	

	@Query(value = "SELECT * FROM EMP_ATT_MASTER WHERE emp_id=?1 AND LOGIN_TIME = ?2", nativeQuery = true)
	List<AttendanceRegister>  getEmpatt(String empid,Date dat1);
	
	@Query(value = " SELECT * FROM EMP_ATT_MASTER WHERE emp_id = ?1", nativeQuery = true)
	public List<AttendanceRegister> getdatainq(String userid);
	
	@Query(value = "SELECT DISTINCT emp_id FROM EMP_ATT_MASTER  ", nativeQuery = true)
	List<String> getcailychecklist();
	
	@Query(value = "select  * from EMP_ATT_MASTER where emp_id= ?1 AND cal_year= ?2 AND cal_month= ?3 AND cal_date= ?4 ", nativeQuery = true)
	AttendanceRegister getchecklist(String a,String year,String month,String day);
	
	
	@Query(value = "select * from  EMP_ATT_MASTER  where emp_id= ?1 AND cal_year= ?2 AND cal_month= ?3 AND cal_date= ?4 ", nativeQuery = true)
	AttendanceRegister getchecklistlogintime(String a,int currentYear,String month,String day);

	
	
	
	//list foe sms
	
	
	
	//@Query(value="SELECT * FROM RESOURCE_MASTER WHERE employee_id=?1 ", nativeQuery = true)
	//String getrole(String empid);
	
    @Transactional
	@Modifying
	@Query(value ="update EMP_ATT_MASTER\r\n" + 
			"set logout_time = CURRENT_TIMESTAMP\r\n" + 
			"where EMP_ID = ?1 AND cal_month = ?2 and cal_date =?3 and cal_year = ?4",nativeQuery = true)
  int  logoutupdate(String userid1, String cal_month, String cal_date, String cal_year);

    @Transactional
  	@Modifying
  	@Query(value ="update EMP_ATT_MASTER\r\n" + 
  			"set login_time = CURRENT_TIMESTAMP\r\n" + 
  			"where EMP_ID = ?1 AND cal_month = ?2 and cal_date =?3 and cal_year = ?4 and emp_remarks='On-Duty'",nativeQuery = true)
    int  loginupdate(String userid1, String cal_month, String cal_date, String cal_year);
	
    @Transactional
  	@Modifying
  	@Query(value ="update EMP_ATT_MASTER\r\n" + 
  			"set login_time = CURRENT_TIMESTAMP\r\n" + 
  			"where EMP_ID = ?1 AND cal_month = ?2 and cal_date =?3 and cal_year = ?4 and emp_remarks='Leave'",nativeQuery = true)
    int  loginupdate1(String userid1, String cal_month, String cal_date, String cal_year);
	
    
    
    @Query(value = "SELECT COUNT(*) FROM EMP_ATT_MASTER " + "WHERE cal_year = :year " + "AND cal_month = :month "
			+ "AND cal_date = :day " + "AND emp_remarks = 'Leave' " + "AND emp_name IS NOT NULL", nativeQuery = true)
	Integer getLeaveCount(@Param("year") int year, @Param("month") int month, @Param("day") int day);
	
	@Query(value = "SELECT COUNT(*) FROM EMP_ATT_MASTER " + "WHERE cal_year = :year " + "AND cal_month = :month "
			+ "AND cal_date = :day " + "AND emp_remarks = 'On-Duty' " + "AND emp_name IS NOT NULL", nativeQuery = true)
	Integer geOnDutyCount(@Param("year") int year, @Param("month") int month, @Param("day") int day);
	
	@Query(value = "SELECT COUNT(DISTINCT emp_name)  FROM EMP_ATT_MASTER " + "WHERE cal_year = :year " + "AND cal_month = :month "
			+ "AND cal_date = :day " + "AND emp_remarks = 'Present' " + "AND emp_name IS NOT NULL", nativeQuery = true)
	Integer getPresentCount(@Param("year") int year, @Param("month") int month, @Param("day") int day);
	
	
	@Query(value = "SELECT EMP_ID,EMP_NAME FROM EMP_ATT_MASTER " + "WHERE cal_year = :year " + "AND cal_month = :month "
			+ "AND cal_date = :day " + "AND emp_remarks = 'Leave' " + "AND emp_name IS NOT NULL", nativeQuery = true)
	List<Object[]> getLeaveNames(@Param("year") String year, @Param("month") String month, @Param("day") String day);
}