package com.syam.main;

import com.syam.reporter.SummaryAPI;

public class Main 
{
	public static void main(String[] args) {
	     try {	       
	          System.out.println(SummaryAPI.getAllTasks());
	     } catch (Exception e) {
	          e.printStackTrace();
	     }
	}
}
