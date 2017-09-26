package com.syam.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) {
		String url = "jdbc:sqlite:/Users/syamk/Documents/Database/lifemanagement.db";
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC").newInstance();
			conn = DriverManager.getConnection(url);
			System.out.println("Connection to SQLite has been established.");
			String sql = "INSERT INTO GOALS(DESCRIPTION,CATEGORY,START_DATE,DUE_DATE,END_DATE,STATUS,PRIOTIY) "
					+ " VALUES(?,?,?,?,?,?,?)";
			 PreparedStatement pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, "goal");
	            pstmt.setString(2, "category");
	            pstmt.setString(3, "startDate");
	            pstmt.setString(4, "dueDate");
	            pstmt.setString(5, "dueDate");
	            pstmt.setString(6, "YET TO START");
	            pstmt.setInt(7, 10);
	            //pstmt.setInt(8, parentGoalID);
	            int c=pstmt.executeUpdate();
	            System.out.println(c);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
