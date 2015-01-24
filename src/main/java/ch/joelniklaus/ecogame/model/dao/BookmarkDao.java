package ch.joelniklaus.ecogame.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.joelniklaus.ecogame.model.Bookmark;

public interface BookmarkDao extends CrudRepository<Bookmark,Long> {
}
