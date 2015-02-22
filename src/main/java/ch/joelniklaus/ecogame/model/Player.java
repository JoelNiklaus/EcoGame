package ch.joelniklaus.ecogame.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Player extends Controllable {

	@OneToOne
	private Company company;

	public Player() {

	}

	public Player(String name, BankAccount bankAccount) {
		super(name, bankAccount);
	}
	
	public Company getCompany() {
		return company;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}

}
