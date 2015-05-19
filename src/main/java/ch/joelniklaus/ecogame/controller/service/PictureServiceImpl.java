package ch.joelniklaus.ecogame.controller.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.joelniklaus.ecogame.model.Picture;
import ch.joelniklaus.ecogame.model.dao.PictureDao;

@Service
public class PictureServiceImpl implements PictureService{

    @Autowired PictureDao pictureDao;
    
	public String save(Picture picture) {
		pictureDao.save(picture);

		picture = pictureDao.findByFileName(picture.getFileName());

		return picture.getId().toString();
	}

	public Picture getPictureByFileName(String name) {
		
		return pictureDao.findByFileName(name);
	}

	public void deletePicture(Picture picture){
    	File file = new File(picture.getFilePath() + picture.getFileName());
    	file.delete();
    	System.out.println(picture.getId());
		//pictureDao.delete(picture.getId());
	}
	
	public void removePictureById(Long id) {
		pictureDao.delete(id);
	}

	public Picture getPicutreById(Long id) {
		
		return pictureDao.findOne(id);
	}

	
}
