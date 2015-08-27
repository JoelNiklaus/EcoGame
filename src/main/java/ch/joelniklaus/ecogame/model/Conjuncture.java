package ch.joelniklaus.ecogame.model;

import javax.persistence.Entity;

@Entity
public class Conjuncture extends DataBaseObject {
	
	private Double conjunctureChange = computeConjunctureChange();
	
	// TODO set starting values
	
	private Double cashInterest;
	
	private Double shortTermCreditInterest;
	
	private Double longTermCreditInterest;
	
	private Double devaluation;
	
	private Double numberOfPeople;
	
	private Double numberOfBuyingPeople;
	
	private Double resourcePrices;
	
	private Double resourceWareHousePrice;
	
	private Double productWareHousePrice;
	
	private Double productionHallPrice;

	private Double productionPersonnelMinimumWage;

	private Double representativeMinimumWage;

	public Conjuncture() {

	}
	
	public Conjuncture(Conjuncture conjuncture) {
		this.conjunctureChange = computeConjunctureChange();

		this.cashInterest = conjuncture.cashInterest * conjunctureChange;
		this.devaluation = conjuncture.devaluation * conjunctureChange;
		this.longTermCreditInterest = conjuncture.longTermCreditInterest * conjunctureChange;
		this.numberOfBuyingPeople = conjuncture.numberOfBuyingPeople * conjunctureChange;
		this.numberOfPeople = conjuncture.numberOfPeople * conjunctureChange;
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

	public Double getNumberOfPeople() {
		return numberOfPeople;
	}

	public void setNumberOfPeople(Double numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	public Double getNumberOfBuyingPeople() {
		return numberOfBuyingPeople;
	}

	public void setNumberOfBuyingPeople(Double numberOfBuyingPeople) {
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
