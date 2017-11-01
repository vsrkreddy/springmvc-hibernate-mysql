package com.company.testdbcon;

import java.sql.DriverManager;

import com.mysql.jdbc.Connection;

public class TestJdbcConnection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false";
		String user = "springstudent";
		String password = "springstudent";
		
		try{
			
			Connection myConn = (Connection) DriverManager.getConnection(jdbcUrl, user, password);
			System.out.println("Connection Successful");
			
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

}
