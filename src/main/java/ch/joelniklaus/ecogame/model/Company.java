package ch.joelniklaus.ecogame.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Company extends DataBaseObject {

	@OneToOne(cascade = CascadeType.ALL)
	private BankAccount bankAccount;
	
	// @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	// private List<ResourceWareHouse> resourceWareHouses = new LinkedList<ResourceWareHouse>();
	//
	// @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	// private List<Machine> machines = new LinkedList<Machine>();
	//
	// @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	// private List<ProductWareHouse> productWareHouses = new LinkedList<ProductWareHouse>();
	
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

}
