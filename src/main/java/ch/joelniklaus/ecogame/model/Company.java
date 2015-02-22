package ch.joelniklaus.ecogame.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Company extends Controllable {
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Machine> machines = new LinkedList<Machine>();
	
	public Company() {

	}

	public Company(String name, BankAccount bankAccount) {
		super(name, bankAccount);
	}
	
}
