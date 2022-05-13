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
import com.mendel.transactions.utils.ValidateTransaction;

/**
 * @author Mario Honorato
 *
 */
@RestController
@RequestMapping("/transactions")
public class TransactionController {

	Logger log = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
	private ITransactionService transactionService;

	@GetMapping("/types/{type}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Transaction>> getTransactionsByType(@PathVariable String type) {
		List<Transaction> listTransactions = null;
		try {
			listTransactions = transactionService.getByType(type);

		} catch (Exception ex) {
			log.error("Error al obtener los estados: " + ex);
		}
		return new ResponseEntity<>(listTransactions, HttpStatus.OK);
	}

	@PostMapping("/{transaction_id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Map<String, Object>> insertTransactions(@PathVariable Long transaction_id,
			@RequestBody TransactionDto transactionDto) {

		Map<String, Object> response = new HashMap<>();
		try {
			Transaction transaction = ConverterTransaction.convertTransactionFromDto(transactionDto, transaction_id);
			String messageValidation = transactionService.validate(transaction);

			if (messageValidation != null && !messageValidation.equals("")) {
				response.put("Message", messageValidation);
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
			transactionService.save(transaction);
			response.put("Message", "Se ha guardado la transacción de forma exitosa");
			return new ResponseEntity<>(response, HttpStatus.CREATED);

		} catch (Exception ex) {
			log.error("Error" + ex);
			response.put("Error Message", ex.getMessage());
			return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@GetMapping("/sum/{transaction_id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Map<String, Object>> getTransactionsByType(@PathVariable Long transaction_id) {
		Map<String, Object> response = new HashMap<>();
		Double sum = null;
		try {
			sum = transactionService.getSumByTransaction(transaction_id);

		} catch (Exception ex) {
			log.error("Error al obtener los estados: " + ex);
		}
		response.put("sum", sum);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}