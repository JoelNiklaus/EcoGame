package ch.joelniklaus.ecogame.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.joelniklaus.ecogame.model.Game;

public interface GameDao extends CrudRepository<Game, Long> {
	public Game findByName(String name);
}
