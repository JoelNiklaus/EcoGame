package ch.joelniklaus.ecogame.model;

import javax.persistence.Entity;

@Entity
public class ResourceWareHouse extends Building {

	public ResourceWareHouse() {

	}

	public ResourceWareHouse(double value) {
		super(value);
	}
}
