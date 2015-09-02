package ch.joelniklaus.ecogame.controller.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ChartServiceImpl implements ChartService {
	/**
	 * Example: String json = "["+ "['Year', 'Sales', 'Expenses'],"+ "['2013',  1000,      400],"+
	 * "['2014',  1170,      460],"+ "['2015',  660,       1120],"+ "['2016',  1030,      540]"+
	 * "]";
	 */
	@Override
	public String buildJsonArray(List<String> titles, List<List<Object>> data) {
		StringBuilder json = new StringBuilder();
		
		json.append("[");
		
		json.append("['Year'");
		for (String title : titles)
			json.append(", '" + title + "'");
		json.append("]");
		
		int yearCounter = 2015;
		for (List<Object> list : data) {
			json.append(",['" + yearCounter + "'");
			yearCounter++;
			for (Object object : list)
				json.append(", " + object.toString());
			json.append("]");
		}
		
		json.append("]");
		
		return json.toString();
	}
}
