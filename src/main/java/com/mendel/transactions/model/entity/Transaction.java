package com.mendel.transactions.model.entity;


public class Transaction {

	public Transaction(Long transaction_id, Double amount, String type, Long parent_id) {
		super();
		this.transaction_id = transaction_id;
		this.amount = amount;
		this.type = type;
		this.parent_id = parent_id;
	}

	public Transaction() {}
	private Long transaction_id;
	
	private Double amount;
	
	private String type;
	
	private Long parent_id;

	public Long getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(Long transaction_id) {
		this.transaction_id = transaction_id;
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
