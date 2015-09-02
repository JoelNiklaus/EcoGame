package ch.joelniklaus.ecogame.model;

import javax.persistence.Entity;

@Entity
public class Conjuncture extends DataBaseObject {
	
	private Double conjunctureChange = computeConjunctureChange();
	
	// TODO set starting values
	
	private Double cashInterest = 0.001;
	
	private Double shortTermCreditInterest = 0.008;
	
	private Double longTermCreditInterest = 0.005;
	
	private Double devaluation = 0.01;
	
	private Long numberOfPeople = 1000000L;
	
	private Long numberOfBuyingPeople = 100000L;
	
	private Double resourcePrices = 25.5;
	
	private Double resourceWareHousePrice = 100000.0;
	
	private Double productWareHousePrice = 200000.0;
	
	private Double productionHallPrice = 500000.0;

	private Double productionPersonnelMinimumWage = 48000.0;

	private Double representativeMinimumWage = 120000.0;

	public Conjuncture() {

	}
	
	public Conjuncture(Conjuncture conjuncture) {
		this.conjunctureChange = computeConjunctureChange();

		this.cashInterest = conjuncture.cashInterest * conjunctureChange;
		this.devaluation = conjuncture.devaluation * conjunctureChange;
		this.longTermCreditInterest = conjuncture.longTermCreditInterest * conjunctureChange;
		this.numberOfBuyingPeople = (long) (conjuncture.numberOfBuyingPeople * conjunctureChange);
		this.numberOfPeople = (long) (conjuncture.numberOfPeople * conjunctureChange);
		this.productionHallPrice = conjuncture.productionHallPrice * conjunctureChange;
		this.productionPersonnelMinimumWage = conjuncture.productionPersonnelMinimumWage
				* conjunctureChange;
		this.productWareHousePrice = conjuncture.productWareHousePrice * conjunctureChange;
		this.representativeMinimumWage = conjuncture.representativeMinimumWage * conjunctureChange;
		this.resourcePrices = conjuncture.resourcePrices * conjunctureChange;
		this.resourceWareHousePrice = conjuncture.resourceWareHousePrice * conjunctureChange;
		this.shortTermCreditInterest = conjuncture.shortTermCreditInterest * conjunctureChange;
	}

	public Double getRandom() {
		return conjunctureChange;
	}

	public void setRandom(Double random) {
		this.conjunctureChange = random;
	}

	public Double getCashInterest() {
		return cashInterest;
	}

	public void setCashInterest(Double cashInterest) {
		this.cashInterest = cashInterest;
	}

	public Double getShortTermCreditInterest() {
		return shortTermCreditInterest;
	}

	public void setShortTermCreditInterest(Double shortTermCreditInterest) {
		this.shortTermCreditInterest = shortTermCreditInterest;
	}

	public Double getLongTermCreditInterest() {
		return longTermCreditInterest;
	}

	public void setLongTermCreditInterest(Double LongTermCreditInterest) {
		this.longTermCreditInterest = LongTermCreditInterest;
	}

	public Double getDevaluation() {
		return devaluation;
	}

	public void setDevaluation(Double devaluation) {
		this.devaluation = devaluation;
	}

	public Long getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(Long numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	public Long getNumberOfBuyingPeople() {
		return numberOfBuyingPeople;
	}

	public void setNumberOfBuyingPeople(Long numberOfBuyingPeople) {
		this.numberOfBuyingPeople = numberOfBuyingPeople;
	}

	public Double getResourcePrices() {
		return resourcePrices;
	}

	public void setResourcePrices(Double resourcePrices) {
		this.resourcePrices = resourcePrices;
	}

	public Double getResourceWareHousePrice() {
		return resourceWareHousePrice;
	}

	public void setResourceWareHousePrice(Double resourceWareHousePrice) {
		this.resourceWareHousePrice = resourceWareHousePrice;
	}

	public Double getProductWareHousePrice() {
		return productWareHousePrice;
	}

	public void setProductWareHousePrice(Double productWareHousePrice) {
		this.productWareHousePrice = productWareHousePrice;
	}

	public Double getProductionHallPrice() {
		return productionHallPrice;
	}

	public void setProductionHallPrice(Double productionHallPrice) {
		this.productionHallPrice = productionHallPrice;
	}

	public Double getProductionPersonnelMinimumWage() {
		return productionPersonnelMinimumWage;
	}

	public void setProductionPersonnelMinimumWage(Double productionPersonnelMinimumWage) {
		this.productionPersonnelMinimumWage = productionPersonnelMinimumWage;
	}

	public Double getRepresentativeMinimumWage() {
		return representativeMinimumWage;
	}

	public void setRepresentativeMinimumWage(Double representativeMinimumWage) {
		this.representativeMinimumWage = representativeMinimumWage;
	}

	/**
	 * Computes a random number between 0.95 and 1.05.
	 */
	public Double computeConjunctureChange() {
		return (Math.random() / 10.0) + 0.95;
	}

}
