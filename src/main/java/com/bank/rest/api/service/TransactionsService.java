package com.bank.rest.api.service;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bank.rest.api.dto.TransactionDetails;
import com.bank.rest.api.dto.UserTransaction;
import com.bank.rest.api.entities.Transaction;

public interface TransactionsService {

	List<Transaction> findByDateBetweenAndType(Date startOfDay, Date endOfDay, String name);

	Transaction save(Transaction accountTransaction);

	ResponseEntity<?> makeDeposit(UserTransaction userTransaction, Long customerNumber);

	ResponseEntity<?> withdrawalDetails(UserTransaction userTransaction, Long customerNumber);

	List<TransactionDetails> findTransactionsByAccountNumber(Long customerNumber, Long accountNumber);
}
