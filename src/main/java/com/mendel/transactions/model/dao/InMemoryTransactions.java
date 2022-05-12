package com.mendel.transactions.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.mendel.transactions.model.entity.Transaction;

@Component
@ConfigurationProperties("static")
public class InMemoryTransactions {

	private final List<Transaction> listTransactions = new ArrayList<Transaction>();
	
	public void addToListTransaction(Transaction transaction) {
		this.listTransactions.add(transaction);
	}
	public List<Transaction> getListTransactions() {
		return this.listTransactions;
	}
}
