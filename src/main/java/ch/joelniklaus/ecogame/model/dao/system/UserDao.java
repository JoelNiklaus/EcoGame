package ch.joelniklaus.ecogame.model.dao.system;

import org.springframework.data.repository.CrudRepository;

import ch.joelniklaus.ecogame.model.system.User;

public interface UserDao extends CrudRepository<User,Long> {
	User findByEmail(String email);
}
