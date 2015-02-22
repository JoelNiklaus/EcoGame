package ch.joelniklaus.ecogame.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public abstract class Controllable {
	
	@Id
	@GeneratedValue
	protected Long id;
	
	protected String name;

	@OneToOne
	protected BankAccount bankAccount;
	
	public void pay(Double amount) {
		bankAccount.charge(amount);
	}
	
	public void receive(Double amount) {
		bankAccount.deposit(amount);
	}

	public Controllable() {

	}
	
	public Controllable(String name, BankAccount bankAccount) {
		this.name = name;
		this.bankAccount = bankAccount;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}
	
}
