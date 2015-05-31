package ch.joelniklaus.ecogame.model.building;

import javax.persistence.MappedSuperclass;

import ch.joelniklaus.ecogame.model.DataBaseObject;

@MappedSuperclass
public abstract class Building extends DataBaseObject {
	
	protected Double value;
	
	public Building() {

	}
	
	public Double getValue() {
		return value;
	}
	
	public void setValue(Double value) {
		this.value = value;
	}
	
}
