package ch.joelniklaus.ecogame.model;

import javax.persistence.Entity;

@Entity
public class Resource extends DataBaseObject {

	private Double price;
	
	public Resource() {
		
	}
	
	public Resource(double price) {
		this.price = price;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
