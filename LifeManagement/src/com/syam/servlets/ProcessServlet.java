package com.syam.servlets;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syam.db.ExecuteQuery;

public class ProcessServlet {

	public static Object process(HttpServletRequest request, HttpServletResponse response,String servlet)
	{
		int sID=ExecuteQuery.getServletId(servlet);
		ArrayList<String> types = ExecuteQuery.getTypes(sID);
		ArrayList<String> columns = ExecuteQuery.getColumns(sID);
		ArrayList<String> values = new ArrayList<String>();
		for (int i = 0; i < columns.size(); i++) {
			values.add(request.getParameter(columns.get(i)));
		}
		System.out.println(values);
		System.out.println(values.size());
		int id=ExecuteQuery.insert(sID, values, types);
		return id;
	}
}
