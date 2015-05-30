package ch.joelniklaus.ecogame.model;

import javax.persistence.Entity;

@Entity
public class BankAccount extends DataBaseObject {
	
	private Double balance;
	
	public void charge(Double amount) {
		balance -= amount;
	}
	
	public void deposit(Double amount) {
		balance += amount;
	}

	public BankAccount() {

	}
	
	public BankAccount(Double balance) {
		this.balance = balance;
	}
	
	public Double getBalance() {
		return balance;
	}
	
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
}
