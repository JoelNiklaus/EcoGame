package ch.joelniklaus.ecogame.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.joelniklaus.ecogame.controller.pojos.GameForm;
import ch.joelniklaus.ecogame.model.Game;
import ch.joelniklaus.ecogame.model.Player;
import ch.joelniklaus.ecogame.model.dao.GameDao;
import ch.joelniklaus.ecogame.model.dao.system.UserDao;

@Service
public class GameServiceImpl implements GameService {
	
	@Autowired
	GameDao gameDao;
	@Autowired
	UserDao userDao;
	@Autowired
	AuthenticationService authService;
	
	@Override
	public GameForm addGame(GameForm gameForm) {
		Game game = setVariables(gameForm, new Game());
		gameDao.save(game);
		gameForm.setId(game.getId());
		return gameForm;
	}

	@Override
	public GameForm editGame(GameForm gameForm) {
		Game game = gameDao.findOne(gameForm.getId());
		game = setVariables(gameForm, game);
		gameDao.save(game);
		return gameForm;
	}

	@Override
	public Game joinGame(Long id) {
		Game game = gameDao.findOne(id);
		game.addPlayer(authService.getLoggedInPlayer());
		gameDao.save(game);
		return game;
	}
	
	@Override
	public List<Player> getPlayersOfGameOfLoggedInPlayer() {
		Game game = getHostedGameOfLoggedInPlayer();
		return game.getPlayers();
	}
	
	@Override
	public boolean loggedInPlayerHasAlreadyHostedGame() {
		return getHostedGameOfLoggedInPlayer() != null;
	}

	@Override
	public boolean loggedInPlayerHasAlreadyJoinedGame() {
		return getJoinedGameOfLoggedInPlayer() != null;
	}
	
	@Override
	public GameForm getGameFormOfLoggedInUser() {
		return new GameForm(getHostedGameOfLoggedInPlayer());
	}
	
	@Override
	@Transactional
	public Player kickPlayer(Long id) {
		Game game = getHostedGameOfLoggedInPlayer();
		Player player = userDao.findOne(id).getPlayer();
		game.kickPlayer(player);
		gameDao.save(game);
		return player;
	}
	
	@Override
	public Game getJoinedGameOfLoggedInPlayer() {
		for (Game game : gameDao.findAll())
			if (game.getPlayers().contains(authService.getLoggedInPlayer()))
				return game;
		return null;
	}

	@Override
	public Game getHostedGameOfLoggedInPlayer() {
		return gameDao.findByHoster(authService.getLoggedInPlayer());
	}
	
	@Override
	public Game getGameOfLoggedInPlayer() {
		Game game = getHostedGameOfLoggedInPlayer();
		if (game != null)
			return game;
		game = getJoinedGameOfLoggedInPlayer();
		if (game != null)
			return game;
		return null;
	}

	private Game setVariables(GameForm gameForm, Game game) {
		game.setName(gameForm.getName());
		game.setMaxNumberOfPlayers(gameForm.getMaxNumberOfPlayers());
		game.setHoster(authService.getLoggedInPlayer());
		return game;
	}
}
