package ch.joelniklaus.ecogame.model.dao.system;

import org.springframework.data.repository.CrudRepository;

import ch.joelniklaus.ecogame.model.system.Picture;

public interface PictureDao extends CrudRepository<Picture,Long> {
	Picture findByFileName(String path);
}
