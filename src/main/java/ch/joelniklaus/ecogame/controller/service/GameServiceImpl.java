package ch.joelniklaus.ecogame.controller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.joelniklaus.ecogame.controller.pojos.GameForm;
import ch.joelniklaus.ecogame.model.Game;
import ch.joelniklaus.ecogame.model.dao.GameDao;
import ch.joelniklaus.ecogame.model.dao.system.UserDao;
import ch.joelniklaus.ecogame.model.system.User;

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
		game.addPlayer(authService.getLoggedInUser());
		gameDao.save(game);
		return game;
	}

	@Override
	public List<User> getPlayersOfGameOfLoggedInUser() {
		Game game = getHostedGameOfLoggedInUser();
		return game.getPlayers();
	}

	@Override
	public boolean loggedInUserHasAlreadyHostedGame() {
		return getHostedGameOfLoggedInUser() != null;
	}
	
	@Override
	public boolean loggedInUserHasAlreadyJoinedGame() {
		return getJoinedGameOfLoggedInUser() != null;
	}

	@Override
	public GameForm getGameFormOfLoggedInUser() {
		return new GameForm(getHostedGameOfLoggedInUser());
	}

	@Override
	@Transactional
	public User kickPlayer(Long id) {
		Game game = getHostedGameOfLoggedInUser();
		User player = userDao.findOne(id);
		game.kickPlayer(player);
		gameDao.save(game);
		return player;
	}

	@Override
	public Game getJoinedGameOfLoggedInUser() {
		for (Game game : gameDao.findAll())
			if (game.getPlayers().contains(authService.getLoggedInUser()))
				return game;
		return null;
	}
	
	@Override
	public Game getHostedGameOfLoggedInUser() {
		return gameDao.findByHoster(authService.getLoggedInUser());
	}

	@Override
	public Game getGameOfLoggedInUser() {
		Game game = getHostedGameOfLoggedInUser();
		if (game != null)
			return game;
		game = getJoinedGameOfLoggedInUser();
		if (game != null)
			return game;
		return null;
	}
	
	private Game setVariables(GameForm gameForm, Game game) {
		game.setName(gameForm.getName());
		game.setMaxNumberOfPlayers(gameForm.getMaxNumberOfPlayers());
		game.setHoster(authService.getLoggedInUser());
		return game;
	}
}
