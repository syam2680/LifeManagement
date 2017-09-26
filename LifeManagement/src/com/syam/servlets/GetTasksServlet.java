package com.syam.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syam.db.ExecuteQuery;
import com.syam.reporter.SummaryAPI;

@WebServlet("/getTasks")
public class GetTasksServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
		try {
			ArrayList<ArrayList<String>> data=ExecuteQuery.fetchData(2, "");
			ArrayList<String> headers=ExecuteQuery.getHeaders(2);
			response.getWriter().println(SummaryAPI.getGoalReport(data,headers));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
