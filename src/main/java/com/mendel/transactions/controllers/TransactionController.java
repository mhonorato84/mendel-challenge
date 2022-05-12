package com.mendel.transactions.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mendel.transactions.model.dto.TransactionDto;
import com.mendel.transactions.model.entity.Transaction;
import com.mendel.transactions.service.ITransactionService;
import com.mendel.transactions.utils.ConverterTransaction;



@RestController
@RequestMapping("/transactions")
public class TransactionController {

	private static final String WRITING_TYPE_FINDALL_NOT_FOUND_EXCEPTION_MSG = "No existen tipo de escritos cargados";

	Logger log = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	private ITransactionService transactionService;
	
	
	@GetMapping("/types/{type}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Map<String, Object>> getTransactionsByType(@PathVariable String type) {
		Map<String, Object> response = new HashMap<>();		
		List<Transaction> listTransactions = null;
		try {
			 listTransactions = transactionService.getByType("");

		} catch (Exception ex) {
			log.error("Error al obtener los estados: " + ex);
		}
		response.put("data", listTransactions);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	

	@PostMapping("/{transaction_id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Map<String, Object>> insertTransactions(@PathVariable Long transaction_id, @RequestBody TransactionDto transactionDto) {
		
		//TODO realizar validaciones respectivas
		Map<String, Object> response = new HashMap<>();
		//Convert DTO to Transaction
		Transaction transaction = ConverterTransaction.convertTransactionFromDto(transactionDto, transaction_id);
		
		try {
			transactionService.save(transaction);					
			return new ResponseEntity<>(response, HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}


	
}