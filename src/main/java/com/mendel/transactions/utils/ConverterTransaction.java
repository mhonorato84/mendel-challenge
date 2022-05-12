package com.mendel.transactions.utils;

import com.mendel.transactions.model.dto.TransactionDto;
import com.mendel.transactions.model.entity.Transaction;

public class ConverterTransaction {
	
	public static Transaction convertTransactionFromDto(TransactionDto transactionDto, Long idTransaction) {
		return new Transaction(idTransaction, transactionDto.getAmount(), transactionDto.getType(), transactionDto.getParent_id());
	}

	public static TransactionDto convertTransactionToDto(Transaction transaction) {
		return new TransactionDto(transaction.getAmount(), transaction.getType(), transaction.getParent_id());
	}

}
