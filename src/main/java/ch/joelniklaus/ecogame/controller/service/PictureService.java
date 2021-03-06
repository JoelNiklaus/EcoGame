package ch.joelniklaus.ecogame.controller.service;

import ch.joelniklaus.ecogame.model.Picture;


public interface PictureService {
	
	/**
	 * 
	 * @param picture
	 * @return ID
	 */
	public String save(Picture picture);
	
	/**
	 * @param path
	 * @return picture
	 */
	public Picture getPictureByFileName(String name);
	
	/**
	 * 
	 * @param id
	 * @return picture
	 */
	public Picture getPicutreById(Long id);
	
	/**
	 * 
	 * @param id
	 */
	public void removePictureById(Long id);
	
	/**
	 * 
	 * @param picture
	 */
	public void deletePicture(Picture picture);
	

}
