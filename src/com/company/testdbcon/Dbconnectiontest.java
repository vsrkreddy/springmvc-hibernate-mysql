package com.company.testdbcon;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Dbconnectiontest
 */
@WebServlet("/Dbconnectiontest")
public class Dbconnectiontest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url ="jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false";
		String username ="springstudent";
		String password = "springstudent";
		String driver ="com.mysql.jdbc.Driver";
		
		try {
			PrintWriter out = response.getWriter();
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, username, password);
			out.println("Success");
			out.close();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
