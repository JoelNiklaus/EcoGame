package ch.joelniklaus.ecogame.controller.service;

import java.util.List;

import ch.joelniklaus.ecogame.controller.pojos.GameForm;
import ch.joelniklaus.ecogame.model.Game;
import ch.joelniklaus.ecogame.model.Player;

public interface GameService {

	public GameForm addGame(GameForm gameForm);

	public GameForm editGame(GameForm gameForm);
	
	public Game joinGame(Long id);

	public GameForm getGameFormOfLoggedInUser();

	public boolean loggedInPlayerHasAlreadyHostedGame();
	
	public boolean loggedInPlayerHasAlreadyJoinedGame();
	
	public List<Player> getPlayersOfGameOfLoggedInPlayer();
	
	public Player kickPlayer(Long id);
	
	public Game getJoinedGameOfLoggedInPlayer();
	
	public Game getHostedGameOfLoggedInPlayer();

	public Game getGameOfLoggedInPlayer();

}
