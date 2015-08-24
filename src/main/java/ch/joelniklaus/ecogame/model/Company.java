package ch.joelniklaus.ecogame.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Company extends DataBaseObject {

	@OneToOne(cascade = CascadeType.ALL)
	private BankAccount bankAccount;
	
	@OneToMany(fetch = FetchType.EAGER)
	private List<Budget> yearlyBudgets = new ArrayList<Budget>();
	
	@OneToMany
	private List<Result> yearlyResults = new ArrayList<Result>();
	
	public void buyResources(Long number) {
		
	}
	
	public void produce(Long number) {
		
	}
	
	public void sellProducts(Long number) {
		
	}
	
	public void pay(Double amount) {
		bankAccount.charge(amount);
	}
	
	public void receive(Double amount) {
		bankAccount.deposit(amount);
	}
	
	public Company() {

	}

	public Company(Long id, String name, BankAccount bankAccount) {
		super();
		this.id = id;
		this.bankAccount = bankAccount;
	}

	public BankAccount getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}
	
	public void setBudget(int year, Budget budget) {
		if (budgetSubmitted(year))
			this.yearlyBudgets.set(year, budget);
		else
			this.yearlyBudgets.add(year, budget);
	}
	
	public Budget getBudget(int year) {
		return this.yearlyBudgets.get(year);
	}
	
	public boolean budgetSubmitted(int year) {
		return yearlyBudgets.size() > year;
	}

	public List<Budget> getYearlyBudgets() {
		return yearlyBudgets;
	}

	public void setYearlyBudgets(List<Budget> yearlyBudgets) {
		this.yearlyBudgets = yearlyBudgets;
	}

	public List<Result> getYearlyResults() {
		return yearlyResults;
	}

	public void setYearlyResults(List<Result> yearlyResults) {
		this.yearlyResults = yearlyResults;
	}
	
	public void addResult(int year, Result result) {
		this.yearlyResults.add(year, result);
	}
	
	public Result getResult(int year) {
		return this.yearlyResults.get(year);
	}

}
