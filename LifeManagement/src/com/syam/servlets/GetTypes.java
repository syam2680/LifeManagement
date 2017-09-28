package com.syam.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syam.db.ExecuteQuery;





@WebServlet("/GetTypes")
public class GetTypes extends HttpServlet
{
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sID=ExecuteQuery.getServletId("GetTypes");
		try {
			String filter="";			
			ArrayList<ArrayList<String>> data=ExecuteQuery.fetchData(sID, filter);
			String res="";
			for(int i=0;i<data.size();i++)
			{
				ArrayList<String> d=data.get(i);

				
			}
			response.getWriter().println(res);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
