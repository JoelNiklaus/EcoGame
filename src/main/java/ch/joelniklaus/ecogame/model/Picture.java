package ch.joelniklaus.ecogame.model;

import javax.persistence.Entity;

@Entity
public class Picture extends DataBaseObject {
	
	private String filePath;
	private String fileName;
	
	public String getFilePath() {
		return filePath;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
