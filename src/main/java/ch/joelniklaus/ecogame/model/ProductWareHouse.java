package ch.joelniklaus.ecogame.model;

import javax.persistence.Entity;

@Entity
public class ProductWareHouse extends Building {

	public ProductWareHouse() {

	}

	public ProductWareHouse(double value) {
		super(value);
	}
}
