package com.bornfire.entities;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

	@Repository
	public interface AccessRolesRep extends JpaRepository<AccessRoles, String> {


		public Optional<AccessRoles> findById(String role_id);

		@Query(value = "select * from BTM_ACS_RLS", nativeQuery = true)
		List<AccessRoles> getRolelist();
		
		@Query(value = "select * from BTM_ACS_RLS where role_id=?1 ", nativeQuery = true)
		AccessRoles getView(String role_id);
		
		@Query(value = "select * from BTM_ACS_RLS where role_id=?1 ", nativeQuery = true)
		AccessRoles findByIds(String role_id);

		@Modifying
		@Transactional
		@Query(value = "DELETE FROM BTM_ACS_RLS WHERE role_id = ?1", nativeQuery = true)
		void deleteRoleId(String role_id);

	}