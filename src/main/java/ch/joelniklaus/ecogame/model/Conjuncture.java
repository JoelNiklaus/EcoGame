package ch.joelniklaus.ecogame.model;

import javax.persistence.Entity;

@Entity
public class Conjuncture extends DataBaseObject {

	private Long random;

	private Long cashInterest;

	private Long shortTermCreditInterest;

	private Long longTermCreditInterest;

	private Long devaluation;

	private Long numberOfPeople;

	private Long numberOfBuyingPeople;

	private Long resourcePrices;

	private Long resourceWareHousePrice;

	private Long productWareHousePrice;

	private Long productionHallPrice;
	
	private Long productionPersonnelMinimumWage;
	
	private Long representativeMinimumWage;
	
	public Conjuncture() {
		
	}
	
	public Long getRandom() {
		return random;
	}
	
	public void setRandom(Long random) {
		this.random = random;
	}
	
	public Long getCashInterest() {
		return cashInterest;
	}
	
	public void setCashInterest(Long cashInterest) {
		this.cashInterest = cashInterest;
	}
	
	public Long getShortTermCreditInterest() {
		return shortTermCreditInterest;
	}
	
	public void setShortTermCreditInterest(Long shortTermCreditInterest) {
		this.shortTermCreditInterest = shortTermCreditInterest;
	}
	
	public Long getLongTermCreditInterest() {
		return longTermCreditInterest;
	}
	
	public void setLongTermCreditInterest(Long longTermCreditInterest) {
		this.longTermCreditInterest = longTermCreditInterest;
	}
	
	public Long getDevaluation() {
		return devaluation;
	}
	
	public void setDevaluation(Long devaluation) {
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
	
	public Long getResourcePrices() {
		return resourcePrices;
	}
	
	public void setResourcePrices(Long resourcePrices) {
		this.resourcePrices = resourcePrices;
	}
	
	public Long getResourceWareHousePrice() {
		return resourceWareHousePrice;
	}
	
	public void setResourceWareHousePrice(Long resourceWareHousePrice) {
		this.resourceWareHousePrice = resourceWareHousePrice;
	}
	
	public Long getProductWareHousePrice() {
		return productWareHousePrice;
	}
	
	public void setProductWareHousePrice(Long productWareHousePrice) {
		this.productWareHousePrice = productWareHousePrice;
	}
	
	public Long getProductionHallPrice() {
		return productionHallPrice;
	}
	
	public void setProductionHallPrice(Long productionHallPrice) {
		this.productionHallPrice = productionHallPrice;
	}
	
	public Long getProductionPersonnelMinimumWage() {
		return productionPersonnelMinimumWage;
	}
	
	public void setProductionPersonnelMinimumWage(Long productionPersonnelMinimumWage) {
		this.productionPersonnelMinimumWage = productionPersonnelMinimumWage;
	}
	
	public Long getRepresentativeMinimumWage() {
		return representativeMinimumWage;
	}
	
	public void setRepresentativeMinimumWage(Long representativeMinimumWage) {
		this.representativeMinimumWage = representativeMinimumWage;
	}
}
