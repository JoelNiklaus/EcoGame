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
	private Long id;
	
	private String name;

	@OneToOne
	private BankAccount bankAccount;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Machine> machines = new LinkedList<Machine>();
	
	public void pay(Double amount) {
		bankAccount.charge(amount);
	}
	
	public Company(String name, BankAccount bankAccount) {
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
