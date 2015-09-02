package ch.joelniklaus.ecogame.controller.service;

import java.util.List;

public interface ChartService {
	
	public String buildJsonArray(List<String> titles, List<List<Object>> data);
	
}
