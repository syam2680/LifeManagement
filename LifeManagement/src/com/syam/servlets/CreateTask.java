package com.syam.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateTask
 */
@WebServlet("/CreateTask")
public class CreateTask extends HttpServlet {

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String goal=request.getParameter("goal");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm"); 
		String startDate=null;
		String dueDate=null;
		int priority=Integer.valueOf(request.getParameter("priority"));
		String category=request.getParameter("category");
		Integer parentGoalID=request.getParameter("parent")==null ? -1 : Integer.valueOf(request.getParameter("parent"));
		String sql = "INSERT INTO GOALS(DESCRIPTION,CATEGORY,START_DATE,DUE_DATE,END_DATE,STATUS,PRIOTIY,PARENT_GOAL_ID) "
				+ " VALUES(?,?,?,?,?,?,?,?)";
		
			startDate=request.getParameter("startDate");
			dueDate=request.getParameter("dueDate");
		
		try  {
			
			String url = "jdbc:sqlite:/Users/syamk/Documents/Database/lifemanagement.db";
	        Connection conn = null;
	        try {
	        	Class.forName("org.sqlite.JDBC").newInstance();
	            conn = DriverManager.getConnection(url);
	            System.out.println("Connection to SQLite has been established.");
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	        }
	        PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, goal);
            pstmt.setString(2, category);
            pstmt.setString(3, startDate);
            pstmt.setString(4, dueDate);
            pstmt.setString(5, dueDate);
            pstmt.setString(6, "YET TO START");
            pstmt.setInt(7, priority);
            pstmt.setInt(8, parentGoalID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}

}
