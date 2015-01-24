package ch.joelniklaus.ecogame.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.joelniklaus.ecogame.model.Search;

public interface SearchDao extends CrudRepository<Search,Long> {
}
