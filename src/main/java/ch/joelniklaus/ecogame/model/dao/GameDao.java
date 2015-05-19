package ch.joelniklaus.ecogame.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.joelniklaus.ecogame.model.Game;
import ch.joelniklaus.ecogame.model.Player;

public interface GameDao extends CrudRepository<Game, Long> {
	public Game findByHoster(Player player);
	
	public Game findByName(String name);
}
