package ch.joelniklaus.ecogame.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.joelniklaus.ecogame.model.good.Resource;

public interface ResourceDao extends CrudRepository<Resource, Long> {
	
}
