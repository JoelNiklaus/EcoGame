package ch.joelniklaus.ecogame.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Company {

	@Id
	@GeneratedValue
	protected Long id;

	protected String name;
	
	@OneToOne
	protected BankAccount bankAccount;

	@OneToMany(fetch = FetchType.EAGER)
	private List<Machine> machines = new LinkedList<Machine>();

	public void pay(Double amount) {
		bankAccount.charge(amount);
	}

	public void receive(Double amount) {
		bankAccount.deposit(amount);
	}

	public Company() {
		
	}
	
	public Company(Long id, String name, BankAccount bankAccount, List<Machine> machines) {
		super();
		this.id = id;
		this.name = name;
		this.bankAccount = bankAccount;
		this.machines = machines;
	}
}
