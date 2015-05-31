package ch.joelniklaus.ecogame.model.good;

import javax.persistence.Entity;

@Entity
public class Product extends Good {

	public Product() {
		
	}
	
	public Product(Double price) {
		super(price);
	}

}
