package ch.joelniklaus.ecogame.model.good;

import javax.persistence.MappedSuperclass;

import ch.joelniklaus.ecogame.model.DataBaseObject;

@MappedSuperclass
public class Good extends DataBaseObject {
	
	protected Double price;
	
	public Good() {

	}

	public Good(double price) {
		this.price = price;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
}
