package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface Bamcatcodemaintainrep extends JpaRepository<Bamcategorycodemain, String> {
	
	@Query(value = "select * from BTM_BAM_CATEGORY_CODE_MAINTAINANCE where head_code=?1 ", nativeQuery = true)
	Bamcategorycodemain getheadcode(String headcode);
	
	@Query(value = "select * from BTM_BAM_CATEGORY_CODE_MAINTAINANCE ", nativeQuery = true)
	List<Bamcategorycodemain> getall();
	
	@Query(value = "select * from BTM_BAM_CATEGORY_CODE_MAINTAINANCE where asset_code=?1 ", nativeQuery = true)
	Bamcategorycodemain getbyId(String asset_code);
	
	@Query(value = "SELECT * FROM BTM_BAM_CATEGORY_CODE_MAINTAINANCE ORDER BY sl_no ", nativeQuery = true)
	List<Bamcategorycodemain> findAllOrderedBySlNo(); 
}
