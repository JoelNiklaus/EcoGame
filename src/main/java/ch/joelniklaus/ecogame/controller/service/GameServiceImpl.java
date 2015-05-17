package ch.joelniklaus.ecogame.controller.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.joelniklaus.ecogame.controller.exceptions.InvalidIdException;
import ch.joelniklaus.ecogame.controller.exceptions.InvalidUserException;
import ch.joelniklaus.ecogame.controller.pojos.GameForm;
import ch.joelniklaus.ecogame.model.Game;
import ch.joelniklaus.ecogame.model.Player;
import ch.joelniklaus.ecogame.model.dao.GameDao;
import ch.joelniklaus.ecogame.model.dao.PlayerDao;
import ch.joelniklaus.ecogame.model.dao.system.UserDao;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	GameDao gameDao;
	@Autowired
	UserDao userDao;
	@Autowired
	PlayerDao playerDao;
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
		if (id == null)
			throw new InvalidIdException("Id must not be null.");
		Game game = gameDao.findOne(id);
		if (game == null)
			throw new InvalidIdException("Could not find game to join.");
		Player loggedInPlayer = authService.getLoggedInPlayer();
		if (game.getHoster().equals(loggedInPlayer))
			throw new InvalidUserException("You cannot join your own hosted game.");
		game.addPlayer(loggedInPlayer);
		gameDao.save(game);
		return game;
	}

	@Override
	public Game deleteGame(Long id) {
		Game game = gameDao.findOne(id);
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
		Player player = playerDao.findOne(id);
		game.kickPlayer(player);
		game = gameDao.save(game);
		return player;
	}

	@Override
	public Game getJoinedGameOfLoggedInPlayer() {
		for (Game game : gameDao.findAll())
			for (Player player : game.getPlayers())
				if (player.getId() == authService.getLoggedInPlayer().getId())
					return game;
		// only possible if equals method works properly:
		// if (game.getPlayers().contains(authService.getLoggedInPlayer()))
		// return game;
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
