package com.bornfire.entities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface IssueTrackerRep extends JpaRepository<IssueTracker, String> {

	@Query(value = "SELECT * FROM ISSUE_TRACKER where srl_no =?1", nativeQuery = true)
	IssueTracker getIssue(String srl_no);
	@Query(value = "SELECT * FROM ISSUE_TRACKER", nativeQuery = true)
	List<IssueTracker> getIssueList();
	
	@Query(value = "SELECT * FROM ISSUE_TRACKER where del_flg='Y'", nativeQuery = true)
	List<IssueTracker> getIssueFormat();
}
