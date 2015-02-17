package ch.joelniklaus.ecogame.controller.service;

import java.util.List;

import ch.joelniklaus.ecogame.controller.pojos.GameForm;
import ch.joelniklaus.ecogame.model.Game;
import ch.joelniklaus.ecogame.model.User;

public interface GameService {
	
	public GameForm addGame(GameForm gameForm);
	
	public GameForm editGame(GameForm gameForm);

	public Game joinGame(Long id);
	
	public GameForm getGameFormOfLoggedInUser();
	
	public boolean loggedInUserHasAlreadyHostedGame();

	public boolean loggedInUserHasAlreadyJoinedGame();

	public List<User> getPlayersOfGameOfLoggedInUser();

	public User kickPlayer(Long id);
	
}
