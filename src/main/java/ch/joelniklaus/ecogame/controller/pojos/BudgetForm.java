package ch.joelniklaus.ecogame.controller.pojos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import ch.joelniklaus.ecogame.model.Budget;

public class BudgetForm {
	
	private Long id;
	
	@Min(0)
	@NotNull
	private Long resourceQuality;
	
	@Min(0)
	@NotNull
	private Long productPrice;
	
	@Min(0)
	@NotNull
	private Long productionVolume;
	
	@Min(0)
	@NotNull
	private Long machines;
	
	@Min(0)
	@NotNull
	private Long productionHalls;
	
	@Min(0)
	@NotNull
	private Long resourceWareHouses;

	@Min(0)
	@NotNull
	private Long productWareHouses;
	
	@Min(0)
	@NotNull
	private Long marketing;
	
	@Min(0)
	@NotNull
	private Long dividend;
	
	@Min(0)
	@NotNull
	private Long research;
	
	@Min(0)
	@NotNull
	private Long specialFaculties;
	
	@Min(0)
	@NotNull
	private Long productionPersonnel;

	@Min(0)
	@NotNull
	private Long productionPersonnelTraining;

	@Min(0)
	@NotNull
	private Long productionPersonnelWage;
	
	@Min(0)
	@NotNull
	private Long representative;
	
	@Min(0)
	@NotNull
	private Long representativeTraining;
	
	@Min(0)
	@NotNull
	private Long representativeWage;

	public BudgetForm() {

	}
	
	public BudgetForm(Budget budget) {
		this.dividend = budget.getDividend();
		this.machines = budget.getMachines();
		this.marketing = budget.getMarketing();
		this.productionHalls = budget.getProductionHalls();
		this.productionPersonnel = budget.getProductionPersonnel();
		this.productionPersonnelTraining = budget.getProductionPersonnelTraining();
		this.productionPersonnelWage = budget.getProductionPersonnelWage();
		this.productionVolume = budget.getProductionVolume();
		this.productPrice = budget.getProductPrice();
		this.productWareHouses = budget.getProductWareHouses();
		this.representative = budget.getRepresentative();
		this.representativeTraining = budget.getRepresentativeTraining();
		this.representativeWage = budget.getRepresentativeWage();
		this.research = budget.getResearch();
		this.resourceQuality = budget.getResourceQuality();
		this.resourceWareHouses = budget.getResourceWareHouses();
		this.specialFaculties = budget.getSpecialFaculties();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getResourceQuality() {
		return resourceQuality;
	}

	public void setResourceQuality(Long resourceQuality) {
		this.resourceQuality = resourceQuality;
	}

	public Long getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Long productPrice) {
		this.productPrice = productPrice;
	}

	public Long getProductionVolume() {
		return productionVolume;
	}

	public void setProductionVolume(Long productionVolume) {
		this.productionVolume = productionVolume;
	}

	public Long getMachines() {
		return machines;
	}

	public void setMachines(Long machines) {
		this.machines = machines;
	}

	public Long getProductionHalls() {
		return productionHalls;
	}

	public void setProductionHalls(Long productionHalls) {
		this.productionHalls = productionHalls;
	}

	public Long getResourceWareHouses() {
		return resourceWareHouses;
	}

	public void setResourceWareHouses(Long resourceWareHouses) {
		this.resourceWareHouses = resourceWareHouses;
	}

	public Long getProductWareHouses() {
		return productWareHouses;
	}

	public void setProductWareHouses(Long productWareHouses) {
		this.productWareHouses = productWareHouses;
	}

	public Long getMarketing() {
		return marketing;
	}

	public void setMarketing(Long marketing) {
		this.marketing = marketing;
	}

	public Long getDividend() {
		return dividend;
	}

	public void setDividend(Long dividend) {
		this.dividend = dividend;
	}

	public Long getResearch() {
		return research;
	}

	public void setResearch(Long research) {
		this.research = research;
	}

	public Long getSpecialFaculties() {
		return specialFaculties;
	}

	public void setSpecialFaculties(Long specialFaculties) {
		this.specialFaculties = specialFaculties;
	}

	public Long getProductionPersonnel() {
		return productionPersonnel;
	}

	public void setProductionPersonnel(Long productionPersonnel) {
		this.productionPersonnel = productionPersonnel;
	}

	public Long getRepresentative() {
		return representative;
	}

	public void setRepresentative(Long representative) {
		this.representative = representative;
	}

	public Long getProductionPersonnelTraining() {
		return productionPersonnelTraining;
	}

	public void setProductionPersonnelTraining(Long productionPersonnelTraining) {
		this.productionPersonnelTraining = productionPersonnelTraining;
	}

	public Long getRepresentativeTraining() {
		return representativeTraining;
	}

	public void setRepresentativeTraining(Long representativeTraining) {
		this.representativeTraining = representativeTraining;
	}

	public Long getProductionPersonnelWage() {
		return productionPersonnelWage;
	}

	public void setProductionPersonnelWage(Long productionPersonnelWage) {
		this.productionPersonnelWage = productionPersonnelWage;
	}

	public Long getRepresentativeWage() {
		return representativeWage;
	}

	public void setRepresentativeWage(Long representativeWage) {
		this.representativeWage = representativeWage;
	}
}
