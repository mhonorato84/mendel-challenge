package com.mendel.transactions.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mendel.transactions.model.dao.InMemoryTransactions;
import com.mendel.transactions.model.entity.Transaction;
import com.mendel.transactions.service.ITransactionService;
import com.mendel.transactions.utils.ValidateTransaction;

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
	
	public String validateTransactionBeforeAdd(Transaction transaction) {
		String messageValidation = this.validate(transaction);		
		return messageValidation;
			
		
}
	public String validate(Transaction transaction) {
		StringBuilder validateMessage = new StringBuilder();
		//Chequeo si el id no es nulo y si el parent id no hace referencia al id de la transacción
		validateMessage.append(ValidateTransaction.validateTransaction(transaction));
		//Chequeo si existe previamente
		validateMessage.append(this.checkIfExistTransaction(transaction.getTransaction_id()));
		if (transaction.getParent_id() > 0L)
			//Chequeo si la referencia del padre existe o no, en caso de ser mayor a 0
			validateMessage.append(this.checkIfExistTransactionParent(transaction.getParent_id()));
		return validateMessage.toString();
	}

	private String checkIfExistTransaction(Long transactionId) {
		return this.isTransactionNumberInList(transactionId) ? ValidateTransaction.ID_EXISTING : "";
	}

	private String checkIfExistTransactionParent(Long parentId) {
		return !this.isTransactionNumberInList(parentId) ? ValidateTransaction.ID_PARENT_NOT_EXISTING : "";
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
