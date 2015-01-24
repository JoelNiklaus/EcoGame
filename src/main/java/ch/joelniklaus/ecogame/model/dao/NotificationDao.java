package ch.joelniklaus.ecogame.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.joelniklaus.ecogame.model.Notification;

public interface NotificationDao extends CrudRepository<Notification,Long> {

	Iterable<Notification> findAllByUserId(Long userId);
}
