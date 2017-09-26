package com.syam.main;

import java.util.ArrayList;

import com.syam.db.ExecuteQuery;
import com.syam.reporter.SummaryAPI;

public class Main {
	public static void main(String[] args) {

		
		ArrayList<ArrayList<String>> data=ExecuteQuery.fetchData(2, "");
		ArrayList<String> headers=ExecuteQuery.getHeaders(2);
		System.out.println(SummaryAPI.getGoalReport(data,headers));
	}
}
