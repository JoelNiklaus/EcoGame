package ch.joelniklaus.ecogame.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class Machine extends DataBaseObject {
	
	private Integer productionRate;
	
	public List<Product> produce(Company company, int numberOfProducts) {
		company.pay(100.00);
		List<Product> products = new LinkedList<Product>();
		for (int i = 0; i < numberOfProducts; i++)
			products.add(new Product(100.00));
		return products;
	}
	
	public Machine() {
		
	}
	
	public Integer getProductionRate() {
		return productionRate;
	}
	
	public void setProductionRate(Integer productionRate) {
		this.productionRate = productionRate;
	}

}
