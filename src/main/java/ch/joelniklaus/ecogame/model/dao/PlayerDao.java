package ch.joelniklaus.ecogame.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.joelniklaus.ecogame.model.Player;

public interface PlayerDao extends CrudRepository<Player, Long> {
	Player findByEmail(String email);

	Player findByUsername(String username);
}
