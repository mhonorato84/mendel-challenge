package com.mendel.transactions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mendel.transactions.model.dao.InMemoryTransactions;
import com.mendel.transactions.model.entity.Transaction;
import com.mendel.transactions.service.ITransactionService;

@Service
public class TransactionServiceImpl implements ITransactionService{

	@Autowired
	InMemoryTransactions inMemoryTransactions;
	
	@Override
	public List<Transaction> getByType(String type) {	
		return inMemoryTransactions.getListTransactionsByType(type);
	}

	@Override
	public void save(Transaction transaction) {
		inMemoryTransactions.addToListTransaction(transaction);
	}

	@Override
	public Double getSumByTransaction(Long transactionId) {
		return inMemoryTransactions.getSumByTransactionAndParentId(transactionId);
	}

	@Override
	public Boolean isTransactionNumberInList(Long transactionId) {
		 return inMemoryTransactions.isTransactionNumberInList(transactionId);		
	}

	
}
