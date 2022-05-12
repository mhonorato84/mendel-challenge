package com.mendel.transactions.service;

import java.util.List;

import com.mendel.transactions.model.entity.Transaction;

public interface ITransactionService {
	
	public  List<Transaction> getByType(String type);
	public void save(Transaction transaction);
}
