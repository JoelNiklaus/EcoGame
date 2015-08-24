package ch.joelniklaus.ecogame.model;

import javax.persistence.Entity;

@Entity
public class Result extends DataBaseObject {

	private Long production;

	private Long cash;

	private Long shortTermCredit;

	private Long longTermCredit;
	
	private Long sales;

	private Long balance;

	private Long expenses;

	private Long revenues;

	private Long products;

	private Long marketShare;

	private Long socialIndex;

	private Long sustainabilityIndex;

	private Long priceIndex;
	
	private Long qualityIndex;
	
	private Long pricePerformanceRatio;

	private Long image;

	private Long demandIndex;
	
	public Result() {

	}

	public Long getProduction() {
		return production;
	}
	
	public void setProduction(Long production) {
		this.production = production;
	}
	
	public Long getCash() {
		return cash;
	}
	
	public void setCash(Long cash) {
		this.cash = cash;
	}
	
	public Long getShortTermCredit() {
		return shortTermCredit;
	}
	
	public void setShortTermCredit(Long shortTermCredit) {
		this.shortTermCredit = shortTermCredit;
	}
	
	public Long getLongTermCredit() {
		return longTermCredit;
	}
	
	public void setLongTermCredit(Long longTermCredit) {
		this.longTermCredit = longTermCredit;
	}
	
	public Long getSales() {
		return sales;
	}
	
	public void setSales(Long sales) {
		this.sales = sales;
	}
	
	public Long getBalance() {
		return balance;
	}
	
	public void setBalance(Long balance) {
		this.balance = balance;
	}
	
	public Long getExpenses() {
		return expenses;
	}
	
	public void setExpenses(Long expenses) {
		this.expenses = expenses;
	}
	
	public Long getRevenues() {
		return revenues;
	}
	
	public void setRevenues(Long revenues) {
		this.revenues = revenues;
	}
	
	public Long getProducts() {
		return products;
	}
	
	public void setProducts(Long products) {
		this.products = products;
	}
	
	public Long getMarketShare() {
		return marketShare;
	}
	
	public void setMarketShare(Long marketShare) {
		this.marketShare = marketShare;
	}
	
	public Long getSocialIndex() {
		return socialIndex;
	}
	
	public void setSocialIndex(Long socialIndex) {
		this.socialIndex = socialIndex;
	}
	
	public Long getSustainabilityIndex() {
		return sustainabilityIndex;
	}
	
	public void setSustainabilityIndex(Long sustainabilityIndex) {
		this.sustainabilityIndex = sustainabilityIndex;
	}
	
	public Long getPriceIndex() {
		return priceIndex;
	}
	
	public void setPriceIndex(Long priceIndex) {
		this.priceIndex = priceIndex;
	}
	
	public Long getQualityIndex() {
		return qualityIndex;
	}
	
	public void setQualityIndex(Long qualityIndex) {
		this.qualityIndex = qualityIndex;
	}
	
	public Long getPricePerformanceRatio() {
		return pricePerformanceRatio;
	}
	
	public void setPricePerformanceRatio(Long pricePerformanceRatio) {
		this.pricePerformanceRatio = pricePerformanceRatio;
	}
	
	public Long getImage() {
		return image;
	}
	
	public void setImage(Long image) {
		this.image = image;
	}
	
	public Long getDemandIndex() {
		return demandIndex;
	}
	
	public void setDemandIndex(Long demandIndex) {
		this.demandIndex = demandIndex;
	}

}
