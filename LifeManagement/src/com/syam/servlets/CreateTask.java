package com.syam.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syam.db.ExecuteQuery;

/**
 * Servlet implementation class CreateTask
 */
@WebServlet("/CreateTask")
public class CreateTask extends HttpServlet {

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<String> types = ExecuteQuery.getTypes(1);
		ArrayList<String> columns = ExecuteQuery.getColumns(1);
		ArrayList<String> values = new ArrayList<String>();
		for (int i = 0; i < columns.size(); i++) {
			values.add(request.getParameter(columns.get(i)));
		}
		System.out.println(values);
		System.out.println(values.size());
		ExecuteQuery.insert(1, values, types);
	}

}
