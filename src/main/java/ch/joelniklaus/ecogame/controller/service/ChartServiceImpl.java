package ch.joelniklaus.ecogame.controller.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ch.joelniklaus.ecogame.model.Player;

@Service
public class ChartServiceImpl implements ChartService {
	/**
	 * Example: String json = "["+ "['Year', 'Sales', 'Expenses'],"+ "['2013',  1000,      400],"+
	 * "['2014',  1170,      460],"+ "['2015',  660,       1120],"+ "['2016',  1030,      540]"+
	 * "]";
	 */
	@Override
	public String buildJsonArray(List<Player> players, List<List<Object>> data) {
		StringBuilder json = new StringBuilder();

		json.append("[");

		json.append("['Year'");
		for (Player player : players)
			json.append(", '" + player.getUsername() + "'");
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
