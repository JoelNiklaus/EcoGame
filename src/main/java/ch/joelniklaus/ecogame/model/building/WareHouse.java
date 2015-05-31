package ch.joelniklaus.ecogame.model.building;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class WareHouse extends Building {

	protected Integer capacity;

	public WareHouse() {

	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

}
