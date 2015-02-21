package ch.joelniklaus.ecogame.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.joelniklaus.ecogame.model.Game;
import ch.joelniklaus.ecogame.model.system.User;

public interface GameDao extends CrudRepository<Game, Long> {
	public Game findByHoster(User player);
}
