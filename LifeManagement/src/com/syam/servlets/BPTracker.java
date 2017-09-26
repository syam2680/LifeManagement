package com.syam.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syam.db.ExecuteQuery;

@WebServlet("/BPTracker")
public class BPTracker extends HttpServlet {
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int sID=ExecuteQuery.getServletId("BPTracker");
		ArrayList<String> types = ExecuteQuery.getTypes(sID);
		ArrayList<String> columns = ExecuteQuery.getColumns(sID);
		ArrayList<String> values = new ArrayList<String>();
		for (int i = 0; i < columns.size(); i++) {
			values.add(request.getParameter(columns.get(i)));
		}
		System.out.println(values);
		System.out.println(values.size());
		int id=ExecuteQuery.insert(sID, values, types);
		response.sendRedirect("/LifeManagement/GetTask?id="+id);
	}
}
