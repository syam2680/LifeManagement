package com.syam.main;

import java.util.ArrayList;

import com.syam.db.ExecuteQuery;

public class Main {
	public static void main(String[] args) {

		ArrayList<String> types = ExecuteQuery.getTypes(1);
		ArrayList<String> columns = ExecuteQuery.getColumns(1);
		ArrayList<String> values = new ArrayList<String>();
		for (int i = 0; i < columns.size(); i++) {
			values.add("1");
		}
		System.out.println(columns);
		System.out.println(values.size());
		ExecuteQuery.insert(1, values, types);
	}
}
