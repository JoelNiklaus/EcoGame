package ch.joelniklaus.ecogame.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BankAccount {

	@Id
	@GeneratedValue
	private Long id;

	private Double balance;

	public void charge(Double amount) {
		balance -= amount;
	}

	public void deposit(Double amount) {
		balance += amount;
	}

	public BankAccount(Double balance) {
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

}
