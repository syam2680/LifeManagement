package com.syam.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syam.db.ExecuteQuery;
import com.syam.reporter.SummaryAPI;

/**
 * Servlet implementation class GetTask
 */
@WebServlet("/GetTask")
public class GetTask extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=Integer.valueOf(request.getParameter("id"));
		try {
			String filter="WHERE GOAL_ID="+id;
			ArrayList<ArrayList<String>> data=ExecuteQuery.fetchData(2, filter);
			ArrayList<String> headers=ExecuteQuery.getHeaders(2);
			response.getWriter().println(SummaryAPI.getGoalReport(data,headers));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
