package com.syam.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ExecuteQuery {
	public static void insert(int id, ArrayList<String> values, ArrayList<String> types) {
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
	
	public static void getHeadersAndColumns(int id,ArrayList<String> headers,ArrayList<String> columns) {
		Connection conn = CreateConnection.getConnection();
		Statement stmt = null;
		String query = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT REPORT_HEADER FROM REPORT_HEADERS WHERE REPORT_ID=" + id + " ORDER BY REPORT_HEADER_SEQ;");
			while (rs.next()) {
				headers.add(rs.getString("REPORT_HEADER"));
			}
			rs = stmt.executeQuery("SELECT REPORT_COLUMN FROM REPORT_HEADERS WHERE REPORT_ID=" + id + " ORDER BY REPORT_HEADER_SEQ;");
			while (rs.next()) {
				columns.add(rs.getString("REPORT_COLUMN"));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	public static void fetchData(int id,String filter,ArrayList<String> headers,ArrayList<String> values) {
		Connection conn = CreateConnection.getConnection();
		Statement stmt = null;
		String query = getQuery(id);
		ArrayList<String> columns=new ArrayList<String>();
		int i=0;
		getHeadersAndColumns(id, headers, columns);
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query + " " + filter + ";");
			while (rs.next()) {
				values.add(rs.getString(columns.get(i++)));
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
