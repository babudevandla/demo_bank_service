package com.bank.rest.api.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bank.rest.api.entities.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

	Optional<Account> findByAccountNumber(Long accountNumber);

	Optional<Account> findByAccountNumberAndCustomer(Long accountNumber, Long customerNumber);
}