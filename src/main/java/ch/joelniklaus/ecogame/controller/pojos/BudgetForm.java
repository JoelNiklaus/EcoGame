package ch.joelniklaus.ecogame.controller.pojos;

public class BudgetForm {

	private Long id;
	
	private Long numberOfResourcesToBuy;
	
	private Long numberOfProductsToProduce;
	
	private Long numberOfProductsToSell;

	public BudgetForm() {
		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getNumberOfResourcesToBuy() {
		return numberOfResourcesToBuy;
	}
	
	public void setNumberOfResourcesToBuy(Long numberOfResourcesToBuy) {
		this.numberOfResourcesToBuy = numberOfResourcesToBuy;
	}
	
	public Long getNumberOfProductsToProduce() {
		return numberOfProductsToProduce;
	}
	
	public void setNumberOfProductsToProduce(Long numberOfProductsToProduce) {
		this.numberOfProductsToProduce = numberOfProductsToProduce;
	}
	
	public Long getNumberOfProductsToSell() {
		return numberOfProductsToSell;
	}
	
	public void setNumberOfProductsToSell(Long numberOfProductsToSell) {
		this.numberOfProductsToSell = numberOfProductsToSell;
	}

}
