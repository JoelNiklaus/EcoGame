package ch.joelniklaus.ecogame.model.dao;

import org.springframework.data.repository.CrudRepository;

import ch.joelniklaus.ecogame.model.Picture;

public interface PictureDao extends CrudRepository<Picture,Long> {
	Picture findByFileName(String path);
}
