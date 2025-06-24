
package com.bornfire.entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnDutyRep extends CrudRepository<OnDuty, String> {

	public Optional<OnDuty> findById(String empId);
	
	@Query(value = "SELECT * FROM ON_DUTY where record_no = ?1 ", nativeQuery = true)
	OnDuty findByRecId(String id1);
	
	@Query(value = "SELECT * FROM ON_DUTY where emp_id = ?1 ", nativeQuery = true)
	OnDuty findByEmpId(String id1);
	
	@Query(value = "SELECT * FROM ON_DUTY where emp_id = ?1 and od_from=?2 ", nativeQuery = true)
	OnDuty OndutyCheck(String id1,String od_from);


	@Query(value = "SELECT * FROM ON_DUTY", nativeQuery = true)
	List<OnDuty> getODlist();
	
	@Query(value = "SELECT * FROM ON_DUTY where year=?1 and status != 'Rejected'", nativeQuery = true)
	List<OnDuty> getOdMasterList1(BigDecimal year);
	
	@Query(value = "SELECT * FROM ON_DUTY where status != 'Approved'", nativeQuery = true)
	List<OnDuty> getODlist1();
	
	@Query(value = "SELECT * FROM ON_DUTY where emp_id = ?1 ", nativeQuery = true)
	List<OnDuty> getOdListbyid(String userid);
	
	@Query(value = "SELECT * FROM ON_DUTY where record_no = ?1 ", nativeQuery = true)
	OnDuty getOdListbyrecord(String resId);
	
	@Query(value = "SELECT * FROM ON_DUTY where leave_ref = ?1 ", nativeQuery = true)
	OnDuty getOdMaster(String userid);
	
}