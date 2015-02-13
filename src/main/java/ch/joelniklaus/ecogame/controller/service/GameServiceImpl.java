package ch.joelniklaus.ecogame.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.joelniklaus.ecogame.controller.pojos.GameForm;
import ch.joelniklaus.ecogame.model.Game;
import ch.joelniklaus.ecogame.model.dao.AddressDao;
import ch.joelniklaus.ecogame.model.dao.GameDao;
import ch.joelniklaus.ecogame.model.dao.PictureDao;
import ch.joelniklaus.ecogame.model.dao.UserDao;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	GameDao gameDao;
	@Autowired
	UserDao userDao;
	@Autowired
	AddressDao addressDao;
	@Autowired
	PictureDao pictureDao;

	@Override
	public GameForm saveFrom(GameForm gameForm) {
		Game game = new Game();
		game.setName(gameForm.getName());
		game.setNumberOfPlayers(gameForm.getNumberOfPlayers());
		
		gameDao.save(game);
		
		gameForm.setId(game.getId());

		return gameForm;
	}

}
