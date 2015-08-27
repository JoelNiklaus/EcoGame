package ch.joelniklaus.ecogame.controller.service;

import org.springframework.stereotype.Service;

import ch.joelniklaus.ecogame.model.Company;

@Service
public class ChartServiceImpl implements ChartService {

	public String buildJsonArray() {
		// String json = "["+
		// "['Year', 'Sales', 'Expenses'],"+
		// "['2013',  1000,      400],"+
		// "['2014',  1170,      460],"+
		// "['2015',  660,       1120],"+
		// "['2016',  1030,      540]"+
		// "]";
		// return json;
		
		StringBuilder json = new StringBuilder();

		json.append("[");
		json.append("['Year'");
		for (Company company : companies)
			json.append(", '" + company.getName() + "'");
		json.append("]");
		
		json.append("]");

		return json.toString();
	}
}
