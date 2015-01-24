package ch.joelniklaus.ecogame.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.joelniklaus.ecogame.model.User;

public interface UserDao extends CrudRepository<User,Long> {
	User findByEmail(String email);
}
