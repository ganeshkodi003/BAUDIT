package com.bornfire.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;


public class HolidayMaster {

	public ArrayList<String> Holidayselect() {
		//HttpServletRequest request = ServletActionContext.getRequest();
		
		//System.out.println(request.getParameter("holidaydate"));
		//System.out.println("inside holidaydetails");
		ArrayList<String> arl = new ArrayList<String>();
		
		String status = "";
		ConnectionManager connMgr = new ConnectionManager();
		Connection conn = connMgr.getConnection();
		PreparedStatement ps = null;
		StringBuffer sql = new StringBuffer();
		
		
		
		sql.append("select to_char(RECORD_DATE,'dd-mm-yyyy')RECORD_DATE from HOLIDAY_MASTER where DEL_FLG='N' ");
		try {
		ps=conn.prepareStatement(sql.toString());
		
	    ResultSet rs=ps.executeQuery();
	     

		while(rs.next()){
			
			
			arl.add(rs.getString("RECORD_DATE"));
			
	 
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
