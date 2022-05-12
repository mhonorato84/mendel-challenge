package com.mendel.transactions.utils;

import com.mendel.transactions.model.entity.Transaction;

public class ValidateTransaction {

	
	public static String validateTransaction(Transaction transactionToValidate) {
		
		String message="";
		if(!isValidId(transactionToValidate))
			message += "El número de la transacción no es válido. ";
		if(!isValidParent(transactionToValidate))
			message += "El número de la transacción padre es autoreferencial. ";
		return message;	
	}
	
	
//	isTransactionNumberInList
	static Boolean alreadyExistId(Transaction transactionToValidate) {
		return transactionToValidate.getParent_id()!=transactionToValidate.getTransaction_id();
	}
	static Boolean alreadyExistParent(Transaction transactionToValidate) {
		return transactionToValidate.getParent_id()!=transactionToValidate.getTransaction_id();
	}
	static Boolean isValidParent(Transaction transactionToValidate) {
		return transactionToValidate.getParent_id()!=transactionToValidate.getTransaction_id();
	}
	static Boolean isValidId(Transaction transactionToValidate) {
		return transactionToValidate.getTransaction_id()!=null;
	}
	
	
}
