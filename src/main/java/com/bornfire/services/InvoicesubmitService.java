package com.bornfire.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bornfire.entities.ClientMaster;
import com.bornfire.entities.ClientMasterRep;
import com.bornfire.entities.InvoiceMaster;
import com.bornfire.entities.InvoiceMasterRep;

@Service
public class InvoicesubmitService {
	
	@Autowired
	InvoiceMasterRep invoiceMasterRep;
	
	@Autowired
	ClientMasterRep clientMasterRep;
	private static String formatLakh(double d) {
	    String s = String.format(Locale.UK, "%1.2f", Math.abs(d));
	    s = s.replaceAll("(.+)(...\\...)", "$1,$2");
	    while (s.matches("\\d{3,},.+")) {
	        s = s.replaceAll("(\\d+)(\\d{2},.+)", "$1,$2");
	    }
	    return d < 0 ? ("-" + s) : s;
	}
	
	public String checksub(List<HashMap<Integer,String>> mapList) throws ParseException {
		for (HashMap<Integer, String> item : mapList) {
			String msg;
			//PlacementMaintenance PO = new PlacementMaintenance();
			InvoiceMaster IM=new InvoiceMaster();
			ClientMaster CM=new ClientMaster();
			{				
				
				
				
				
				String sDate3 = item.get(21);
				if (sDate3 != null && !sDate3.isEmpty()) {
				    try {
				        // Remove any extra double quotes from sDate3
				        sDate3 = sDate3.replace("\"", "");
				        System.out.println("Cleaned date string: " + sDate3);
				        
				        // Parse the cleaned date string
				        Date date3 = new SimpleDateFormat("dd-MM-yyyy").parse(sDate3);
				        
				    } catch (ParseException e) {
				        e.printStackTrace();
				        msg = "PO Delivery Date Not Valid";
				        return msg;
				    }
			}else {
			}
					 SimpleDateFormat formatdate = new SimpleDateFormat("ddMMyyyy");
					 Date date3 = new SimpleDateFormat("dd-MM-yyyy").parse(sDate3);
						String m = formatdate.format(date3);
				
				IM.setVendor(item.get(0));
				IM.setEmp_id(item.get(14));
				IM.setEmp_name(item.get(13));
				
				IM.setGstin(item.get(12));
				
				IM.setInv_tot_amt(item.get(31));
				IM.setLocation(item.get(1));
				IM.setPm_email(item.get(7));
				if(sDate3!="") {
					Date date31 = new SimpleDateFormat("dd-MM-yyyy").parse(sDate3);
					IM.setPo_delivery_date(date31);
					}else {
					}
				IM.setPo_no(item.get(2));
				
				System.out.println(formatLakh(Double.valueOf(item.get(19))));
				//System.out.println();
				IM.setPo_rate_inr(formatLakh(Double.valueOf(item.get(19))));
				IM.setProj_mgr(item.get(6));
				//IM.setInv_no(item.get(39));
				IM.setPo_id(item.get(2) + item.get(14) + m);
				IM.setCin(item.get(34));
				IM.setPan(item.get(35));
				IM.setVendor1(item.get(37));
				IM.setGstin_1(item.get(36));
				IM.setHsn(item.get(38));
				IM.setBank_name(item.get(39));
				IM.setAcct_type(item.get(40));
				IM.setAcct_no(item.get(41));
				IM.setAcct_name(item.get(42));
				IM.setIfsc_code(item.get(43));
				IM.setSwift_code(item.get(44));
		
			ClientMaster CM2=	clientMasterRep.getClientAddress(item.get(1));	 
				//checkservice.extsubmit(PO);
					IM.setTel_fax(CM2.getTel_fax());
					IM.setBill_to(CM2.getBill_to());
					IM.setDeliver_to(CM2.getDeliver_to());
				System.out.println(CM2.getBill_to());
				System.out.println(CM2.getDeliver_to());
				
				
				invoiceMasterRep.save(IM);
			//	System.out.println(datainvo.getPo_no());
				

               
                
				//invoiceMasterRep.save(datainvo);

				msg = "Excel Data Uploaded Successfully";


			}
				
		}
		return "success";
	}
	

}
