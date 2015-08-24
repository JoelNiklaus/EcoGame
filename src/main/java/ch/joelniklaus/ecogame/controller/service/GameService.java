package ch.joelniklaus.ecogame.controller.service;

import java.util.List;

import ch.joelniklaus.ecogame.controller.pojos.BudgetForm;
import ch.joelniklaus.ecogame.controller.pojos.GameForm;
import ch.joelniklaus.ecogame.model.Game;
import ch.joelniklaus.ecogame.model.Player;

public interface GameService {
	
	public GameForm createGame(GameForm gameForm);
	
	public GameForm editGame(GameForm gameForm);

	public Game joinGame(Long id);

	public Game deleteGame(Long id);

	public Player kickPlayer(Long id);
	
	public Game getGameOfLoggedInPlayer();
	
	public GameForm getGameFormOfLoggedInPlayer();
	
	public boolean loggedInPlayerIsAlreadyPartOfGame();

	public List<Player> getPlayersOfGameOfLoggedInPlayer();
	
	public List<Game> getJoinableGames();
	
	public boolean nameAlreadyExists(String name);
	
	public Game leaveGame();
	
	public BudgetForm saveBudget(BudgetForm budgetForm);

	public Game passYear();
	
	public boolean allBudgetsSubmitted();
	
}
