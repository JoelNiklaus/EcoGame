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
import ch.joelniklaus.ecogame.model.ChartInformation;
import ch.joelniklaus.ecogame.model.Company;
import ch.joelniklaus.ecogame.model.Conjuncture;
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
	@Autowired
	ChartService chartService;

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
		Player loggedInPlayer = authService.getLoggedInPlayer();
		game.addPlayer(loggedInPlayer);
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

		int year = game.getYear();
		Company company = loggedInPlayer.getCompany();
		for (int y = 1; y < year; y++) {
			company.addResult(y, new Result());
			company.setBudget(y, new Budget());
		}
		companyDao.save(company);

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
			int year = game.getYear();
			// TODO compute the results for the company here
			Result previousResult = company.getResult(year - 1);
			Result result = new Result();
			company.addResult(year, result);
			companyDao.save(company);
		}
	}

	@Override
	public boolean nameAlreadyExists(String name) {
		if (gameDao.findByName(name) != null)
			return true;
		return false;
	}
	
	@Override
	public List<ChartInformation> buildResultChartInformation() {
		List<ChartInformation> chartInfos = new LinkedList<ChartInformation>();
		Game game = getGameOfLoggedInPlayer();
		List<Player> players = game.getPlayers();
		List<List<Object>> data = null;
		List<String> titles = new LinkedList<String>();
		for (Player player : players)
			titles.add(player.getUsername());

		data = new LinkedList<List<Object>>();
		for (int i = 0; i < game.getYear(); i++) {
			List<Object> list = new LinkedList<Object>();
			for (Player player : players)
				list.add(player.getCompany().getResult(i).getBalance());
			data.add(list);
		}
		chartInfos.add(new ChartInformation("Balance", chartService.buildJsonArray(titles, data)));

		data = new LinkedList<List<Object>>();
		for (int i = 0; i < game.getYear(); i++) {
			List<Object> list = new LinkedList<Object>();
			for (Player player : players)
				list.add(player.getCompany().getResult(i).getCash());
			data.add(list);
		}
		chartInfos.add(new ChartInformation("Cash", chartService.buildJsonArray(titles, data)));
		
		data = new LinkedList<List<Object>>();
		for (int i = 0; i < game.getYear(); i++) {
			List<Object> list = new LinkedList<Object>();
			for (Player player : players)
				list.add(player.getCompany().getResult(i).getDemandIndex());
			data.add(list);
		}
		chartInfos.add(new ChartInformation("DemandIndex", chartService
				.buildJsonArray(titles, data)));

		data = new LinkedList<List<Object>>();
		for (int i = 0; i < game.getYear(); i++) {
			List<Object> list = new LinkedList<Object>();
			for (Player player : players)
				list.add(player.getCompany().getResult(i).getExpenses());
			data.add(list);
		}
		chartInfos.add(new ChartInformation("Expenses", chartService.buildJsonArray(titles, data)));

		data = new LinkedList<List<Object>>();
		for (int i = 0; i < game.getYear(); i++) {
			List<Object> list = new LinkedList<Object>();
			for (Player player : players)
				list.add(player.getCompany().getResult(i).getImage());
			data.add(list);
		}
		chartInfos.add(new ChartInformation("Image", chartService.buildJsonArray(titles, data)));

		data = new LinkedList<List<Object>>();
		for (int i = 0; i < game.getYear(); i++) {
			List<Object> list = new LinkedList<Object>();
			for (Player player : players)
				list.add(player.getCompany().getResult(i).getLongTermCredit());
			data.add(list);
		}
		chartInfos.add(new ChartInformation("LongTermCredit", chartService.buildJsonArray(titles,
				data)));

		data = new LinkedList<List<Object>>();
		for (int i = 0; i < game.getYear(); i++) {
			List<Object> list = new LinkedList<Object>();
			for (Player player : players)
				list.add(player.getCompany().getResult(i).getMarketShare());
			data.add(list);
		}
		chartInfos.add(new ChartInformation("MarketShare", chartService
				.buildJsonArray(titles, data)));

		data = new LinkedList<List<Object>>();
		for (int i = 0; i < game.getYear(); i++) {
			List<Object> list = new LinkedList<Object>();
			for (Player player : players)
				list.add(player.getCompany().getResult(i).getPriceIndex());
			data.add(list);
		}
		chartInfos
				.add(new ChartInformation("PriceIndex", chartService.buildJsonArray(titles, data)));

		data = new LinkedList<List<Object>>();
		for (int i = 0; i < game.getYear(); i++) {
			List<Object> list = new LinkedList<Object>();
			for (Player player : players)
				list.add(player.getCompany().getResult(i).getPricePerformanceRatio());
			data.add(list);
		}
		chartInfos.add(new ChartInformation("PricePerformanceRatio", chartService.buildJsonArray(
				titles, data)));
		
		data = new LinkedList<List<Object>>();
		for (int i = 0; i < game.getYear(); i++) {
			List<Object> list = new LinkedList<Object>();
			for (Player player : players)
				list.add(player.getCompany().getResult(i).getProduction());
			data.add(list);
		}
		chartInfos
				.add(new ChartInformation("Production", chartService.buildJsonArray(titles, data)));
		
		data = new LinkedList<List<Object>>();
		for (int i = 0; i < game.getYear(); i++) {
			List<Object> list = new LinkedList<Object>();
			for (Player player : players)
				list.add(player.getCompany().getResult(i).getProducts());
			data.add(list);
		}
		chartInfos.add(new ChartInformation("Products", chartService.buildJsonArray(titles, data)));
		
		data = new LinkedList<List<Object>>();
		for (int i = 0; i < game.getYear(); i++) {
			List<Object> list = new LinkedList<Object>();
			for (Player player : players)
				list.add(player.getCompany().getResult(i).getQualityIndex());
			data.add(list);
		}
		chartInfos.add(new ChartInformation("QualityIndex", chartService.buildJsonArray(titles,
				data)));
		
		data = new LinkedList<List<Object>>();
		for (int i = 0; i < game.getYear(); i++) {
			List<Object> list = new LinkedList<Object>();
			for (Player player : players)
				list.add(player.getCompany().getResult(i).getRevenues());
			data.add(list);
		}
		chartInfos.add(new ChartInformation("Revenues", chartService.buildJsonArray(titles, data)));
		
		data = new LinkedList<List<Object>>();
		for (int i = 0; i < game.getYear(); i++) {
			List<Object> list = new LinkedList<Object>();
			for (Player player : players)
				list.add(player.getCompany().getResult(i).getSales());
			data.add(list);
		}
		chartInfos.add(new ChartInformation("Sales", chartService.buildJsonArray(titles, data)));
		
		data = new LinkedList<List<Object>>();
		for (int i = 0; i < game.getYear(); i++) {
			List<Object> list = new LinkedList<Object>();
			for (Player player : players)
				list.add(player.getCompany().getResult(i).getShortTermCredit());
			data.add(list);
		}
		chartInfos.add(new ChartInformation("ShortTermCredit", chartService.buildJsonArray(titles,
				data)));
		
		data = new LinkedList<List<Object>>();
		for (int i = 0; i < game.getYear(); i++) {
			List<Object> list = new LinkedList<Object>();
			for (Player player : players)
				list.add(player.getCompany().getResult(i).getSocialIndex());
			data.add(list);
		}
		chartInfos.add(new ChartInformation("SocialIndex", chartService
				.buildJsonArray(titles, data)));
		
		data = new LinkedList<List<Object>>();
		for (int i = 0; i < game.getYear(); i++) {
			List<Object> list = new LinkedList<Object>();
			for (Player player : players)
				list.add(player.getCompany().getResult(i).getSustainabilityIndex());
			data.add(list);
		}
		chartInfos.add(new ChartInformation("SustainabilityIndex", chartService.buildJsonArray(
				titles, data)));
		
		return chartInfos;
	}

	@Override
	public List<ChartInformation> buildConjunctureChartInformation() {
		List<ChartInformation> chartInfos = new LinkedList<ChartInformation>();
		Game game = getGameOfLoggedInPlayer();
		Conjuncture conjuncture = game.getConjuncture(game.getYear());
		List<List<Object>> data = null;
		List<String> titles = null;

		titles = new LinkedList<String>();
		titles.add("Devaluation");
		titles.add("NumberOfBuyingPeople");
		titles.add("NumberOfPeople");
		titles.add("Random");

		data = new LinkedList<List<Object>>();
		for (int i = 0; i < game.getYear(); i++) {
			List<Object> list = new LinkedList<Object>();
			list.add(conjuncture.getDevaluation());
			list.add(conjuncture.getNumberOfBuyingPeople());
			list.add(conjuncture.getNumberOfPeople());
			list.add(conjuncture.getRandom());
			data.add(list);
		}
		chartInfos.add(new ChartInformation("Conjuncture", chartService
				.buildJsonArray(titles, data)));

		titles = new LinkedList<String>();
		titles.add("CashInterest");
		titles.add("LongTermCreditInterest");
		titles.add("ShortTermCreditInterest");

		data = new LinkedList<List<Object>>();
		for (int i = 0; i < game.getYear(); i++) {
			List<Object> list = new LinkedList<Object>();
			list.add(conjuncture.getCashInterest());
			list.add(conjuncture.getLongTermCreditInterest());
			list.add(conjuncture.getShortTermCreditInterest());
			data.add(list);
		}
		chartInfos
				.add(new ChartInformation("Interests", chartService.buildJsonArray(titles, data)));

		titles = new LinkedList<String>();
		titles.add("ProductionHallPrice");
		titles.add("ProductWareHousePrice");
		titles.add("ResourcePrices");
		titles.add("ResourceWareHousePrice");

		data = new LinkedList<List<Object>>();
		for (int i = 0; i < game.getYear(); i++) {
			List<Object> list = new LinkedList<Object>();
			list.add(conjuncture.getProductionHallPrice());
			list.add(conjuncture.getProductWareHousePrice());
			list.add(conjuncture.getResourcePrices());
			list.add(conjuncture.getResourceWareHousePrice());
			data.add(list);
		}
		chartInfos.add(new ChartInformation("Prices", chartService.buildJsonArray(titles, data)));
		
		titles = new LinkedList<String>();
		titles.add("ProductionPersonnelMinimumWage");
		titles.add("RepresentativeMinimumWage");

		data = new LinkedList<List<Object>>();
		for (int i = 0; i < game.getYear(); i++) {
			List<Object> list = new LinkedList<Object>();
			list.add(conjuncture.getProductionPersonnelMinimumWage());
			list.add(conjuncture.getRepresentativeMinimumWage());
			data.add(list);
		}
		chartInfos.add(new ChartInformation("Wages", chartService.buildJsonArray(titles, data)));
		
		return chartInfos;
	}
}
