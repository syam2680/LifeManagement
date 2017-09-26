package com.syam.reporter;

import java.util.ArrayList;

public class SummaryAPI {

	public static String getGoalReport(ArrayList<ArrayList<String>> data, ArrayList<String> headers) {
		StringBuilder table = new StringBuilder();
		table.append(HTMLTags.HTML_START);
		table.append(HTMLTags.HTML_HEAD_START);
		table.append(HTMLTags.CSS_START);
		table.append(HTMLTags.TABLE_STYLE);
		table.append(HTMLTags.CSS_END);
		table.append(HTMLTags.HTML_HEAD_END);
		table.append(HTMLTags.TABLE_START);
		table.append(HTMLTags.TABLE_ROW_START);
		for (String header : headers) {
			table.append(HTMLTags.TABLE_HEADER_START);
			table.append(header);
			table.append(HTMLTags.TABLE_HEADER_END);
		}
		table.append(HTMLTags.TABLE_ROW_END);
		for (ArrayList<String> d : data) {
			table.append(HTMLTags.TABLE_ROW_START);
			for (String f : d) {
				table.append(HTMLTags.TABLE_DATA_START);
				table.append(f);
				table.append("\n");
				table.append(HTMLTags.TABLE_DATA_END);
			}
			table.append(HTMLTags.TABLE_ROW_END);
		}
		table.append(HTMLTags.TABLE_END);
		table.append(HTMLTags.HTML_END);
		return table.toString();
	}

}
