package com.syam.servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syam.reporter.SummaryAPI;

@WebServlet("/getTasks")
public class GetTasksServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
			response.getWriter().println(SummaryAPI.getAllTasks());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
