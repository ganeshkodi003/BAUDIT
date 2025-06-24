package com.bornfire.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.bornfire.entities.BSPF_ENTITY;
import com.bornfire.entities.BankAcctEntity;
import com.bornfire.entities.BankAcctRep;
import com.bornfire.entities.BsalEntity;
import com.bornfire.entities.BsalRep;
import com.bornfire.entities.PlacementMaintenance;
import com.bornfire.entities.spfRepo;

@Service
@ConfigurationProperties("output")
@Transactional

public class BankDetailService {
	
	@Autowired
	SessionFactory sessionFactory;

	
	@Autowired
	BsalRep bsalRep;
	
	@Autowired
	spfRepo SpfRepo;
	
	@Autowired
	BankAcctRep bankAcctRep;
	
	
	public String getviewtobtm ( String b) { 
			System.out.println(b);	
			 try {
			        // AccessRoles up = new AccessRoles();

				  List<BSPF_ENTITY> up1 = SpfRepo.getData(b);
			       // List<BsalEntity> up2 = bsalRep.getData52(b);
			        System.out.println(up1);
			        List<BankAcctEntity> up3 = new ArrayList<>();
			        
			        for(BSPF_ENTITY t:up1) {
			        	BankAcctEntity Q=new BankAcctEntity();
			        	Q.setBank_acct_no(t.getBank_acct_no());
			        	Q.setEmp_acct_no(t.getBank_acct_no());
			        	Q.setEmp_name(t.getEmp_name());
			        	Q.setEmp_email_id(t.getEmail_id());
			        	Q.setTran_amt(t.getNet_salary());
			        	Q.setTran_date(t.getDate_of_pay());
			        	if(t.getBank_name().equals("ICICI")) {
			        		Q.setTran_type("I");
			        	}else {
			        		Q.setTran_type("N");
			        	}
			        	
			        	up3.add(Q);
			        }
			        
			        bankAcctRep.saveAll(up3);
			 }
			 catch (Exception e) {
			        e.printStackTrace();
			        return "Error: " + e.getMessage();
			    }
			
			return "success"; 

			}


}
