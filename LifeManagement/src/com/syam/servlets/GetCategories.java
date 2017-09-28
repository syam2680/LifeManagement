package com.syam.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syam.db.ExecuteQuery;

@WebServlet("/GetCategories")
public class GetCategories  extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sID=ExecuteQuery.getServletId("GetCategories");
		try {
			String filter="";
			ArrayList<ArrayList<String>> data=ExecuteQuery.fetchData(sID, filter);
			String res="";
			for(int j=0;j<data.size();j++)
			{
				ArrayList<String> d=data.get(j);
				for(int i=0;i<d.size();i++)
				{
					res+= d.get(i);
				}
				if(j+1<data.size())
				res+=",";
			}
			response.getWriter().println(res);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
