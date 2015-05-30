package ch.joelniklaus.ecogame.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Building extends DataBaseObject {

	private Double value;

	public Building() {
		
	}
	
	public Building(double value) {
		this.value = value;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}
