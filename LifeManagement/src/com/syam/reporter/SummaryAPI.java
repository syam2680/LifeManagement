package com.syam.reporter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import com.syam.life.Task;

public class SummaryAPI {
	public static String getAllTasks() throws IllegalArgumentException, IllegalAccessException {
		TreeMap<Integer, Task> database = Task.getDB();
		StringBuilder table = new StringBuilder();
		table.append(TABLE_START);
		table.append(TABLE_ROW_START);
		for (String header : getTaskHeaders()) {
			table.append(TABLE_HEADER_START);
			table.append(header);
			table.append(TABLE_HEADER_END);
		}
		table.append(TABLE_ROW_END);
		Class cls = Task.class;
		Set<String> required = getRequiredFields();
		for (Task t : database.values()) {
			table.append(TABLE_ROW_START);
			for (Field f : cls.getDeclaredFields()) {
				if (required.contains(f.getName())) {
					f.setAccessible(true);
					table.append(TABLE_DATA_START);
					table.append(f.get(t));
					table.append("\n");
					table.append(TABLE_DATA_END);
				}
			}
			table.append(TABLE_ROW_END);
		}
		table.append(TABLE_END);
		return table.toString();
	}

	public static String getTask(int id) throws IllegalArgumentException, IllegalAccessException {
		TreeMap<Integer, Task> database = Task.getDB();
		StringBuilder table = new StringBuilder();
		table.append(TABLE_START);
		table.append(TABLE_ROW_START);
		for (String header : getTaskHeaders()) {
			table.append(TABLE_HEADER_START);
			table.append(header);
			table.append(TABLE_HEADER_END);
		}
		table.append(TABLE_ROW_END);
		Class cls = Task.class;
		Set<String> required = getRequiredFields();
		Task t = database.get(id);
		table.append(TABLE_ROW_START);
		for (Field f : cls.getDeclaredFields()) {
			if (required.contains(f.getName())) {
				f.setAccessible(true);
				table.append(TABLE_DATA_START);
				table.append(f.get(t));
				table.append("\n");
				table.append(TABLE_DATA_END);
			}
		}
		table.append(TABLE_ROW_END);
		table.append(TABLE_END);
		return table.toString();
	}

	public static List<String> getTaskHeaders() {
		List<String> headers = new ArrayList<String>();
		headers.add("Task ID");
		headers.add("Task Name");
		headers.add("Completed");
		headers.add("Start Date");
		headers.add("Due Date");
		headers.add("End Date");
		headers.add("Priority");
		headers.add("Category");
		return headers;
	}

	public static Set<String> getRequiredFields() {
		Set<String> requireFields = new HashSet<String>();
		requireFields.add("taskID");
		requireFields.add("taskName");
		requireFields.add("isCompleted");
		requireFields.add("startDate");
		requireFields.add("dueDate");
		requireFields.add("endDate");
		requireFields.add("priority");
		requireFields.add("category");
		
		return requireFields;
	}

	private static String TABLE_START = "<table>\n";
	private static String TABLE_END = "</table>\n";
	private static String TABLE_ROW_START = "<tr>\n";
	private static String TABLE_ROW_END = "</tr>\n";
	private static String TABLE_DATA_START = "<td>\n";
	private static String TABLE_DATA_END = "</td>\n";
	private static String TABLE_HEADER_START = "<th>\n";
	private static String TABLE_HEADER_END = "</th>\n";
}
