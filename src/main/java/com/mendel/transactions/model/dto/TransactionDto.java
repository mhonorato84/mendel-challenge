package com.mendel.transactions.model.dto;


public class TransactionDto {
	
	private Double amount;
	
	private String type;
	
	private Long parent_id;

	
	public TransactionDto(Double amount, String type, Long parent_id) {
		super();
		this.amount = amount;
		this.type = type;
		this.parent_id = parent_id;
	}
	
	public TransactionDto() {
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}
	
	
	
}
