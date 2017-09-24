package com.syam.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syam.life.Task;

/**
 * Servlet implementation class CreateTask
 */
@WebServlet("/CreateTask")
public class CreateTask extends HttpServlet {


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String taskName=request.getParameter("taskName");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm"); 
		Date startDate=null;
		Date dueDate=null;
		int priority=Integer.valueOf(request.getParameter("priority"));
		try {
			startDate=df.parse(request.getParameter("startDate"));
			dueDate=df.parse(request.getParameter("dueDate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Task t=new Task(taskName,startDate,dueDate,priority);
		response.sendRedirect("/LifeManagement/GetTask?id="+t.getTaskID());
	}

}
