package com.bornfire.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bornfire.entities.Event_manager_details_Repo;
import com.bornfire.entities.InvoiceMaster;
import com.bornfire.entities.InvoiceMasterRep;
import com.bornfire.entities.PlacementMaintenance;



@Service
public class checkservice

{
	@Autowired
	InvoiceMasterRep invoiceMasterRep;
	
	@Autowired
	Event_manager_details_Repo event_manager_details_Repo;

	public String extsubmit(PlacementMaintenance PO) {
		// TODO Auto-generated method stub
		InvoiceMaster datainvo = new InvoiceMaster();
		datainvo.setEmp_id(PO.getEmp_id());
		datainvo.setEmp_name(PO.getEmp_name());
		datainvo.setGrn_amt(PO.getGrn_amt());
		datainvo.setGrn_date(PO.getGrn_date());
		datainvo.setGrn_efforts(PO.getGrn_efforts());
		datainvo.setGrn_no(PO.getGrn_no());
		datainvo.setGstin(PO.getGstin());
		datainvo.setInv_amt(PO.getInv_amt());
		datainvo.setInv_cgst(PO.getInv_cgst());
		datainvo.setInv_date(PO.getInv_date());
		datainvo.setInv_due_date(PO.getInv_due_date());
		datainvo.setInv_igst(PO.getInv_igst());
		datainvo.setInv_no(PO.getInv_no());
		datainvo.setInv_sgst(PO.getInv_sgst());
		datainvo.setInv_tot_amt(PO.getInv_tot_amt());
		datainvo.setInv_tot_gst(PO.getInv_tot_gst());
		datainvo.setLocation(PO.getLocation());
		datainvo.setPm_email(PO.getPm_email());
		datainvo.setPo_delivery_date(PO.getPo_delivery_date());
		datainvo.setPo_no(PO.getPo_no());
		datainvo.setPo_rate_inr(PO.getPo_rate_inr());
		datainvo.setProj_mgr(PO.getProj_mgr());
		datainvo.setVendor(PO.getVendor());
		
		 invoiceMasterRep.save(datainvo);
		return"";
		
	}

	
	
	
	
	
	

}
