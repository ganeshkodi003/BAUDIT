package com.bornfire.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bornfire.entities.BTMEmpTimeSheet;
import com.bornfire.entities.BTMEmpTimeSheetRep;
import com.bornfire.entities.TimeSheetBeanstruct;

@Service
@ConfigurationProperties("output")
@Transactional
public class Timesheetmaster {

	@Autowired
	BTMEmpTimeSheetRep btmEmpTimeSheetRep;
	public ArrayList<String> selectdetails(String resid,String month,BigDecimal year) throws SQLException {
		
		ArrayList<String> arl = new ArrayList<String>();
		ConnectionManager connMgr = new ConnectionManager();
		Connection conn = connMgr.getConnection();
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		
			sql.append("select DATE_1,DATE_2,DATE_3,DATE_4,DATE_5,DATE_6,DATE_7,DATE_8,DATE_9,DATE_10,DATE_11,DATE_12,DATE_13,DATE_14,DATE_15,DATE_16,DATE_17,DATE_18,DATE_19,DATE_20,DATE_21,DATE_22,DATE_23,DATE_24,DATE_25,DATE_26,DATE_27,DATE_28,DATE_29,DATE_30,DATE_31 from BKEMP_TIME_SHEET where EMP_ID='"+resid+"' and MONTH='"+month+"' and YEAR='"+year+"'");
		
		
		try {
	    		BTMEmpTimeSheet time = btmEmpTimeSheetRep.getTimeSheetModify(resid, year, month);
			
			arl.add(time.getDate_1());
			arl.add(time.getDate_2());
			arl.add(time.getDate_3());
			arl.add(time.getDate_4());
			arl.add(time.getDate_5());
			arl.add(time.getDate_6());
			arl.add(time.getDate_7());
			arl.add(time.getDate_8());
			arl.add(time.getDate_9());
			arl.add(time.getDate_10());
			arl.add(time.getDate_11());
			arl.add(time.getDate_12());
			arl.add(time.getDate_13());
			arl.add(time.getDate_14());
			arl.add(time.getDate_15());
			arl.add(time.getDate_16());
			arl.add(time.getDate_17());
			arl.add(time.getDate_18());
			arl.add(time.getDate_19());
			arl.add(time.getDate_20());
			arl.add(time.getDate_21());
			arl.add(time.getDate_22());
			arl.add(time.getDate_23());
			arl.add(time.getDate_24());
			arl.add(time.getDate_25());
			arl.add(time.getDate_26());
			arl.add(time.getDate_27());
			arl.add(time.getDate_28());
			arl.add(time.getDate_29());
			arl.add(time.getDate_30());
			arl.add(time.getDate_31());
			
			/*
			 * arl.add((rs.getString("DATE_2"))); arl.add((rs.getString("DATE_3")));
			 * arl.add((rs.getString("DATE_4"))); arl.add((rs.getString("DATE_5")));
			 * arl.add((rs.getString("DATE_6"))); arl.add((rs.getString("DATE_7")));
			 * arl.add((rs.getString("DATE_8"))); arl.add((rs.getString("DATE_9")));
			 * arl.add((rs.getString("DATE_10"))); arl.add((rs.getString("DATE_11")));
			 * arl.add((rs.getString("DATE_12"))); arl.add((rs.getString("DATE_13")));
			 * arl.add((rs.getString("DATE_14"))); arl.add((rs.getString("DATE_15")));
			 * arl.add((rs.getString("DATE_16"))); arl.add((rs.getString("DATE_17")));
			 * arl.add((rs.getString("DATE_18"))); arl.add((rs.getString("DATE_19")));
			 * arl.add((rs.getString("DATE_20"))); arl.add((rs.getString("DATE_21")));
			 * arl.add((rs.getString("DATE_22"))); arl.add((rs.getString("DATE_23")));
			 * arl.add((rs.getString("DATE_24"))); arl.add((rs.getString("DATE_25")));
			 * arl.add((rs.getString("DATE_26"))); arl.add((rs.getString("DATE_27")));
			 * arl.add((rs.getString("DATE_28"))); arl.add((rs.getString("DATE_29")));
			 * arl.add((rs.getString("DATE_30"))); arl.add((rs.getString("DATE_31")));
			 */
		    
		    
		    
	    	
	    
		   
	        
		
		} finally {

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					
				}
			}
		}
		return arl;
				}
public ArrayList<String> DailyActivitydetails(String day,String month,BigDecimal year) {
		
		ArrayList<String> arl = new ArrayList<String>();
		//HttpServletRequest request = ServletActionContext.getRequest();
		//HttpSession session = request.getSession();
		ConnectionManager connMgr = new ConnectionManager();
		Connection conn = connMgr.getConnection();
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		PreparedStatement ps1 = null;
		StringBuffer sql1 = new StringBuffer();
		DateFormat df=new DateFormat();
		HolidayMaster hld=new HolidayMaster();
		 ArrayList<String> hl=hld.Holidayselect();
		String proj="";
		String client="";
		
			sql.append("select "+day+",EMP_ID,EMP_NAME,YEAR,MONTH FROM EMP_TIME_SHEET WHERE  MONTH='"+month+"' and YEAR='"+year+"' order by COALESCE(" + day + ", 'ZZZZZ'), emp_id");
		
		
		try {
		ps=conn.prepareStatement(sql.toString());
		
	    ResultSet rs=ps.executeQuery();
	     

		while(rs.next()){
			System.out.println(rs.getString("EMP_ID"));
			arl.add(NullCheck.isNotNull(rs.getString("EMP_ID")));
			arl.add(NullCheck.isNotNull(rs.getString("EMP_NAME")));
			arl.add(NullCheck.isNotNull(rs.getString("YEAR")));
			arl.add(NullCheck.isNotNull(rs.getString("MONTH")));
			arl.add(NullCheck.isNotNull(rs.getString(day)));
			
		  
		    
		    
		  
	        
		}
		}catch (SQLException e) {
			
			e.printStackTrace();
		} finally {

			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					
				}
			}
		}
		return arl;
				}
}
