package com.syam.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ExecuteQuery {
	public static int insert(int id, ArrayList<String> values, ArrayList<String> types) {
		Connection conn = CreateConnection.getConnection();
		String query = getQuery(id);
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(query);
			for (int i = 1; i <= values.size(); i++) {
				System.out.println(i  + " "+ types.get(i-1) + " " + values.get(i-1));
				if (types.get(i - 1).equals("String")) {
					pstmt.setString(i, values.get(i-1));
				} else if (types.get(i - 1).equals("Integer")) {					
					pstmt.setInt(i, Integer.valueOf(values.get(i-1)));
				}
			}
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getMaxGoalID();
	}

	public static String getQuery(int id) {
		Connection conn = CreateConnection.getConnection();
		Statement stmt = null;
		String query = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT REPORT_QUERY FROM REPORT_QUERY WHERE REPORT_ID=" + id + ";");
			while (rs.next()) {
				query = rs.getString("REPORT_QUERY");
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return query;
	}
	
		
	public static ArrayList<String>  getColumns(int id) {
		Connection conn = CreateConnection.getConnection();
		Statement stmt = null;
		ArrayList<String> columns=new ArrayList<String> ();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT REPORT_COLUMN FROM REPORT_HEADERS WHERE REPORT_ID=" + id + " ORDER BY REPORT_HEADER_SEQ;");
			while (rs.next()) {
				columns.add(rs.getString("REPORT_COLUMN"));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return columns;
	}
	
	public static ArrayList<String>  getTypes(int id) {
		Connection conn = CreateConnection.getConnection();
		Statement stmt = null;
		ArrayList<String> types=new ArrayList<String> ();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT COLUMN_JAVA_TYPE FROM REPORT_HEADERS WHERE REPORT_ID=" + id + " ORDER BY REPORT_HEADER_SEQ;");
			while (rs.next()) {
				types.add(rs.getString("COLUMN_JAVA_TYPE"));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return types;
	}
	
	public static int  getMaxGoalID() {
		Connection conn = CreateConnection.getConnection();
		Statement stmt = null;
		int goalID=0;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(GOAL_ID) GOAL_ID FROM GOALS");
			while (rs.next()) {
				goalID=Integer.valueOf((rs.getString("GOAL_ID")));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goalID;
	}
	
	
	public static ArrayList<String> getHeaders(int id) {
		Connection conn = CreateConnection.getConnection();
		Statement stmt = null;
		ArrayList<String> headers=new ArrayList<String> ();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT REPORT_HEADER FROM REPORT_HEADERS WHERE REPORT_ID=" + id + " ORDER BY REPORT_HEADER_SEQ;");
			while (rs.next()) {
				headers.add(rs.getString("REPORT_HEADER"));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return headers;
	}
	
	public static ArrayList<ArrayList<String>> fetchData(int id,String filter) {
		Connection conn = CreateConnection.getConnection();
		Statement stmt = null;
		String query = getQuery(id);
		ArrayList<String> columns=getColumns(id);
		ArrayList<String> headers=getHeaders(id);
		ArrayList<ArrayList<String>> data=new 	ArrayList<ArrayList<String>>();		
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query + " " + filter + ";");
			while (rs.next()) {
				ArrayList<String> temp=new ArrayList<String>();
				for(String column:columns)
				{
					temp.add(rs.getString(column));
				}
				data.add(temp);
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
	
}
