package com.bornfire.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Document_Master_Repo  extends JpaRepository<Document_Master_Entity, String>{

}
