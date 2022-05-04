package com.bank.rest.api.service;

import org.springframework.http.ResponseEntity;

import com.bank.rest.api.dto.AccountInformation;

public interface AccountService {

	ResponseEntity<?> findByAccountNumber(Long account_id);

	ResponseEntity<?> addNewAccount(AccountInformation accountInformation, Long cust_Id);

	ResponseEntity<?> calculateInterestRates(Long accountNumber, Double amount);


}
