package com.syam.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class CreateConnection {
	private static Connection connection = null;

	public static Connection getConnection() {
		if (connection == null) {
			String url = "jdbc:sqlite:/Users/syamk/Documents/Database/lifemanagement.db";
			try {
				Class.forName("org.sqlite.JDBC").newInstance();
				connection = DriverManager.getConnection(url);
				System.out.println("Connection to SQLite has been established.");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return connection;
	}
}
