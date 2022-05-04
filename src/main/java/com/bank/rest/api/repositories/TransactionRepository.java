package com.bank.rest.api.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bank.rest.api.entities.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

	 public Optional<List<Transaction>> findByAccountNumber(Long accountNumber);
	 
	 List<Transaction> findByDateBetweenAndType(Date StartOfDay, Date endOfDay, String type);

	public List<Transaction> findByDateBetweenAndTypeAndAccountNumber(Date startOfDay, Date endOfDay, String type,
			Long accountNumber);
    
}