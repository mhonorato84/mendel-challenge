package com.mendel.transactions.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	
	public List getListTransactionsByType(String type) {
		return this.listTransactions.stream()
				  .filter(c -> c.getType().equals(type))				  
				  .collect(Collectors.toList())
				  .stream().flatMap(p -> Stream.of(p.getTransaction_id()))
				  .collect(Collectors.toList());
		
	}
	
	public Boolean isTransactionNumberInList(Long transactionId) {
		return !this.listTransactions.stream()
				  .filter(c -> c.getTransaction_id().equals(transactionId))				  
				  .collect(Collectors.toList()).isEmpty();		
	}
	
	public Double getSumByTransactionAndParentId(Long transactionId) {
		return this.getSumByParentId(transactionId) + this.getSumByTransactionId(transactionId); 
	} 
	
	private Double getSumByTransactionId(Long transactionId) {
		return this.listTransactions.stream()
				.mapToDouble(x -> x.getTransaction_id().equals(transactionId)? x.getAmount():0.0)				
				.sum();
	}
	
	private Double getSumByParentId(Long transactionId) {
		return this.listTransactions.stream()
				.mapToDouble(x -> x.getParent_id().equals(transactionId)? x.getAmount():0.0)				
				.sum();
	}
	
}
