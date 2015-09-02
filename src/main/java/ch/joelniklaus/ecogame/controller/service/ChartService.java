package ch.joelniklaus.ecogame.controller.service;

import java.util.List;

import ch.joelniklaus.ecogame.model.Player;

public interface ChartService {

	public String buildJsonArray(List<Player> players, List<List<Object>> data);

}
