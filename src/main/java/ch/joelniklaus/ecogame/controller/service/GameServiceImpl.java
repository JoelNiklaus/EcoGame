package ch.joelniklaus.ecogame.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.joelniklaus.ecogame.controller.pojos.GameForm;
import ch.joelniklaus.ecogame.model.Game;
import ch.joelniklaus.ecogame.model.dao.GameDao;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	GameDao gameDao;
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
	
	private Game setVariables(GameForm gameForm, Game game) {
		game.setName(gameForm.getName());
		game.setNumberOfPlayers(gameForm.getNumberOfPlayers());
		game.setHoster(authService.getLoggedInUser());
		return game;
	}

}
