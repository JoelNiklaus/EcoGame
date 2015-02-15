package ch.joelniklaus.ecogame.controller.service;

import ch.joelniklaus.ecogame.controller.pojos.GameForm;

public interface GameService {
	
	public GameForm addGame(GameForm gameForm);
	
	public GameForm editGame(GameForm gameForm);

}
