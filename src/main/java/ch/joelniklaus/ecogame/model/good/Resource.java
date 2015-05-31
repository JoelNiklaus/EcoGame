package ch.joelniklaus.ecogame.model.good;

import javax.persistence.Entity;

@Entity
public class Resource extends Good {

	public Resource() {
		
	}
	
	public Resource(Double price) {
		super(price);
	}
	
}
