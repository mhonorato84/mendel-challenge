package com.mendel.transactions.utils;

import com.mendel.transactions.model.entity.Transaction;

public class ValidateTransaction {
	public static final String ID_NOT_VALID = "El número de la transacción no es válido. ";
	public static final String ID_PARENT_SELF_REFERENCE = "El número de la transacción padre es autoreferencial. ";
	public static final String ID_EXISTING = "El número de transación fue registrado previamente";
	public static final String ID_PARENT_NOT_EXISTING = "El número de transación al que hace referencia es inexistente";

	public static String validateTransaction(Transaction transactionToValidate) {
		String message = "";
		if (!isValidId(transactionToValidate))
			message += ID_NOT_VALID;
		if (!isValidParent(transactionToValidate))
			message += ID_PARENT_SELF_REFERENCE;
		return message;
	}

	static Boolean alreadyExistId(Transaction transactionToValidate) {
		return transactionToValidate.getParent_id() != transactionToValidate.getTransaction_id();
	}

	static Boolean alreadyExistParent(Transaction transactionToValidate) {
		return transactionToValidate.getParent_id() != transactionToValidate.getTransaction_id();
	}

	static Boolean isValidParent(Transaction transactionToValidate) {
		return transactionToValidate.getParent_id() != transactionToValidate.getTransaction_id();
	}

	static Boolean isValidId(Transaction transactionToValidate) {
		return transactionToValidate.getTransaction_id() != null;
	}

}
