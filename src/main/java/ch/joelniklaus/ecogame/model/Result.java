package ch.joelniklaus.ecogame.model;

import javax.persistence.Entity;

@Entity
public class Result extends DataBaseObject {
	
	private Long production = 0L;
	
	private Long cash = 1000000L;
	
	private Long shortTermCredit = 0L;
	
	private Long longTermCredit = 0L;

	private Long sales = 0L;
	
	private Long balance = cash - shortTermCredit - longTermCredit;
	
	private Long expenses = 0L;
	
	private Long revenues = 0L;
	
	private Long products = 0L;
	
	private Long marketShare = 0L;
	
	private Long socialIndex = 0L;
	
	private Long sustainabilityIndex = 0L;
	
	private Long priceIndex = 0L;

	private Long qualityIndex = 0L;

	private Long pricePerformanceRatio = 0L;
	
	private Long image = 0L;
	
	private Long demandIndex = 0L;

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
