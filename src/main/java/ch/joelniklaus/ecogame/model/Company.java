package ch.joelniklaus.ecogame.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Company extends DataBaseObject {
	
	protected String name;

	@OneToOne(cascade = CascadeType.ALL)
	protected BankAccount bankAccount;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Machine> machines = new LinkedList<Machine>();
	
	public void buyResources(int number) {
		
	}
	
	public void produce(int number) {
		
	}
	
	public void sellProducts(int number) {
		
	}
	
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
