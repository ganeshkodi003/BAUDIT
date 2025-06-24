package com.bornfire.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.bornfire.entities.Emp_BGV_Entity;
import com.bornfire.entities.Emp_BGV_Repo;

@Service
public class BGVService {

	 	@Autowired
	    Emp_BGV_Repo emp_BGV_Repo;

	    public void saveEmployeeData(Emp_BGV_Entity emp_BGV_Entity) {
	        emp_BGV_Repo.save(emp_BGV_Entity);
	    }
}
