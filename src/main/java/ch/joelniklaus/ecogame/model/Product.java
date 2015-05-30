package ch.joelniklaus.ecogame.model;

import javax.persistence.Entity;

@Entity
public class Product extends DataBaseObject {

	private Double price;

	public Product() {
		
	}
	
	public Product(double price) {
		this.price = price;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
