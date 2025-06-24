package com.bornfire.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	Connection conn;
	public Connection getConnection() {
		try {
				Class.forName("oracle.jdbc.driver.OracleDriver");			
				DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());				 
			//	System.out.println("1--- INSIDE CONNECTION MANAGER");
				
		        //conn = DriverManager.getConnection("jdbc:oracle:thin:@43.240.66.155:1521:ORCL","BTM_TEST","btmtest");
				//conn = DriverManager.getConnection("jdbc:oracle:thin:@43.240.66.158:1600:BTM","btm","btm");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@103.224.241.244:1521/orcl","btm","btm");
	    
				//conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.99:1521:orcl","btm","btm");
                //conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.3.162:1521:orcl","btm","btm1");
		        //conn = DriverManager.getConnection("jdbc:oracle:thin:@43.240.66.155:1521:ORCL","btm","btm");
		        //conn = DriverManager.getConnection("jdbc:oracle:thin:@43.240.66.155:1522:bon","btm","btm2017");
			//	System.out.println("INSIDE CONNECTION MANAGER");			
		}  catch (SQLException sqlexcp) {
			sqlexcp.printStackTrace();		
		} catch(Exception e){
			e.printStackTrace();
		}
		
		
		return conn;
	}
}
