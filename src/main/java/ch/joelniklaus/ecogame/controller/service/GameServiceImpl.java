package ch.joelniklaus.ecogame.controller.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.joelniklaus.ecogame.controller.exceptions.InvalidIdException;
import ch.joelniklaus.ecogame.controller.pojos.BudgetForm;
import ch.joelniklaus.ecogame.controller.pojos.GameForm;
import ch.joelniklaus.ecogame.model.Budget;
import ch.joelniklaus.ecogame.model.Company;
import ch.joelniklaus.ecogame.model.Game;
import ch.joelniklaus.ecogame.model.Player;
import ch.joelniklaus.ecogame.model.Result;
import ch.joelniklaus.ecogame.model.dao.BudgetDao;
import ch.joelniklaus.ecogame.model.dao.CompanyDao;
import ch.joelniklaus.ecogame.model.dao.GameDao;
import ch.joelniklaus.ecogame.model.dao.PlayerDao;

@Service
public class GameServiceImpl implements GameService {
	
	@Autowired
	GameDao gameDao;
	@Autowired
	PlayerDao playerDao;
	@Autowired
	CompanyDao companyDao;
	@Autowired
	BudgetDao budgetDao;
	@Autowired
	AuthenticationService authService;

	@Override
	@Transactional
	public BudgetForm saveBudget(BudgetForm budgetForm) {
		int year = getGameOfLoggedInPlayer().getYear();
		Company company = authService.getLoggedInPlayer().getCompany();
		Budget budget = null;
		if (company.budgetSubmitted(year))
			budget = company.getBudget(year);
		else
			budget = new Budget();
		budget.setVariables(budgetForm);
		budgetDao.save(budget);

		company.setBudget(year, budget);
		companyDao.save(company);

		return budgetForm;
	}
	
	@Override
	@Transactional
	public GameForm createGame(GameForm gameForm) {
		Game game = new Game(gameForm);
		game.addPlayer(authService.getLoggedInPlayer());
		gameDao.save(game);
		gameForm.setId(game.getId());
		return gameForm;
	}

	@Override
	@Transactional
	public GameForm editGame(GameForm gameForm) {
		Game game = getGameOfLoggedInPlayer();
		game.setName(gameForm.getName());
		game.setMaxNumberOfPlayers(gameForm.getMaxNumberOfPlayers());
		gameDao.save(game);
		return gameForm;
	}

	@Override
	@Transactional
	public Game joinGame(Long id) {
		if (id == null)
			throw new InvalidIdException("Id must not be null.");
		Game game = gameDao.findOne(id);
		if (game == null)
			throw new InvalidIdException("Could not find game to join.");
		Player loggedInPlayer = authService.getLoggedInPlayer();
		game.addPlayer(loggedInPlayer);
		gameDao.save(game);
		return game;
	}
	
	@Override
	@Transactional
	public Game deleteGame(Long id) {
		Game game = gameDao.findOne(id);
		gameDao.delete(game);
		return game;
	}
	
	@Override
	@Transactional
	public Player kickPlayer(Long id) {
		Game game = getGameOfLoggedInPlayer();
		Player player = playerDao.findOne(id);
		game.kickPlayer(player);
		game = gameDao.save(game);
		return player;
	}

	@Override
	@Transactional
	public Game leaveGame() {
		Game game = getGameOfLoggedInPlayer();
		Player player = authService.getLoggedInPlayer();
		game.kickPlayer(player);
		game = gameDao.save(game);
		if (game.getPlayers().isEmpty())
			gameDao.delete(game);
		return game;
	}

	@Override
	public List<Game> getJoinableGames() {
		List<Game> joinableGames = new LinkedList<Game>();
		for (Game game : gameDao.findAll())
			if (!game.equals(getGameOfLoggedInPlayer()))
				joinableGames.add(game);
		return joinableGames;
	}
	
	@Override
	public List<Player> getPlayersOfGameOfLoggedInPlayer() {
		Game game = getGameOfLoggedInPlayer();
		return game.getPlayers();
	}

	@Override
	public boolean loggedInPlayerIsAlreadyPartOfGame() {
		return getGameOfLoggedInPlayer() != null;
	}
	
	@Override
	public GameForm getGameFormOfLoggedInPlayer() {
		return new GameForm(getGameOfLoggedInPlayer());
	}
	
	@Override
	public Game getGameOfLoggedInPlayer() {
		for (Game game : gameDao.findAll())
			if (game.getPlayers().contains(authService.getLoggedInPlayer()))
				return game;
		return null;
	}
	
	@Override
	public boolean allBudgetsSubmitted() {
		Game game = getGameOfLoggedInPlayer();
		for (Player player : game.getPlayers())
			if (!player.getCompany().budgetSubmitted(game.getYear()))
				return false;
		return true;
	}

	@Override
	public Game passYear() {
		Game game = getGameOfLoggedInPlayer();
		game.passYear();
		gameDao.save(game);
		computeResults();
		return game;
	}
	
	private void computeResults() {
		Game game = getGameOfLoggedInPlayer();
		Company company = null;
		for (Player player : game.getPlayers()) {
			company = player.getCompany();
			// TODO compute the results for the company here
			Result result = new Result();
			company.addResult(game.getYear(), result);
			companyDao.save(company);
		}
	}

	@Override
	public boolean nameAlreadyExists(String name) {
		if (gameDao.findByName(name) != null)
			return true;
		return false;
	}
}
