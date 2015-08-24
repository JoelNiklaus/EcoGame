package ch.joelniklaus.ecogame.model;

import javax.persistence.Entity;

import ch.joelniklaus.ecogame.controller.pojos.BudgetForm;

@Entity
public class Budget extends DataBaseObject {
	
	private Long resourceQuality;
	
	private Long productPrice;
	
	private Long productionVolume;
	
	private Long machines;
	
	private Long productionHalls;
	
	private Long resourceWareHouses;

	private Long productWareHouses;
	
	private Long marketing;
	
	private Long dividend;
	
	private Long research;
	
	private Long specialFaculties;
	
	private Long productionPersonnel;
	
	private Long productionPersonnelWage;
	
	private Long productionPersonnelTraining;
	
	private Long representative;
	
	private Long representativeTraining;
	
	private Long representativeWage;

	public Budget(BudgetForm budgetForm) {
		setVariables(budgetForm);
	}
	
	public Budget() {
		
	}

	public void setVariables(BudgetForm budgetForm) {
		this.dividend = budgetForm.getDividend();
		this.machines = budgetForm.getMachines();
		this.marketing = budgetForm.getMarketing();
		this.productionHalls = budgetForm.getProductionHalls();
		this.productionPersonnel = budgetForm.getProductionPersonnel();
		this.productionPersonnelTraining = budgetForm.getProductionPersonnelTraining();
		this.productionPersonnelWage = budgetForm.getProductionPersonnelWage();
		this.productionVolume = budgetForm.getProductionVolume();
		this.productPrice = budgetForm.getProductPrice();
		this.productWareHouses = budgetForm.getProductWareHouses();
		this.representative = budgetForm.getRepresentative();
		this.representativeTraining = budgetForm.getRepresentativeTraining();
		this.representativeWage = budgetForm.getRepresentativeWage();
		this.research = budgetForm.getResearch();
		this.resourceQuality = budgetForm.getResourceQuality();
		this.resourceWareHouses = budgetForm.getResourceWareHouses();
		this.specialFaculties = budgetForm.getSpecialFaculties();
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
	
	@Override
	public String toString() {
		return "Budget [resourceQuality=" + resourceQuality + ", productPrice=" + productPrice
				+ ", productionVolume=" + productionVolume + ", machines=" + machines
				+ ", productionHalls=" + productionHalls + ", resourceWareHouses="
				+ resourceWareHouses + ", productWareHouses=" + productWareHouses + ", marketing="
				+ marketing + ", dividend=" + dividend + ", research=" + research
				+ ", specialFaculties=" + specialFaculties + ", productionPersonnel="
				+ productionPersonnel + ", productionPersonnelWage=" + productionPersonnelWage
				+ ", productionPersonnelTraining=" + productionPersonnelTraining
				+ ", representative=" + representative + ", representativeTraining="
				+ representativeTraining + ", representativeWage=" + representativeWage + "]";
	}
}
