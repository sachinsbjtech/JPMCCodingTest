package com.jpmc.dataObjects;

import java.time.LocalDate;

public class Instruction {
	private String entity;
	
	private String buySellIndicator;
	
	private Double agreedFx;
	
	private String currency;
	
	private LocalDate  instructionDate;
	
	private LocalDate settlementDate;
	
	private LocalDate actualSellementDate;
	
	private Long units;
	
	private Double price;
	
	private Double totalAmount;

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public String getBuySellIndicator() {
		return buySellIndicator;
	}

	public void setBuySellIndicator(String buySellIndicator) {
		this.buySellIndicator = buySellIndicator;
	}

	public Double getAgreedFx() {
		return agreedFx;
	}

	public void setAgreedFx(Double agreedFx) {
		this.agreedFx = agreedFx;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public LocalDate getInstructionDate() {
		return instructionDate;
	}

	public void setInstructionDate(LocalDate instructionDate) {
		this.instructionDate = instructionDate;
	}

	public LocalDate getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(LocalDate settlementDate) {
		this.settlementDate = settlementDate;
	}

	public LocalDate getActualSellementDate() {
		return actualSellementDate;
	}

	public void setActualSellementDate(LocalDate actualSellementDate) {
		this.actualSellementDate = actualSellementDate;
	}

	public Long getUnits() {
		return units;
	}

	public void setUnits(Long units) {
		this.units = units;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Entity :"+this.getEntity());
		sb.append("BuySellIndicator :"+this.getBuySellIndicator());
		sb.append("Agreed Fx :"+this.getAgreedFx());
		sb.append("Currency :"+this.getCurrency());
		sb.append("InstructionDate :"+this.getInstructionDate());
		sb.append("SettlementDate :"+this.getSettlementDate());
		sb.append("Actual SettlementDate:"+this.getActualSellementDate());
		sb.append("units :"+this.getUnits());
		sb.append("Price :"+this.getPrice());
		return sb.toString();
	}

}
