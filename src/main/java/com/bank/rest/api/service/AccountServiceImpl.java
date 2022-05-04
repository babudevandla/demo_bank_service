package com.bank.rest.api.service;

import java.util.HashMap;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.rest.api.constants.AccountType;
import com.bank.rest.api.dto.AccountInformation;
import com.bank.rest.api.entities.Account;
import com.bank.rest.api.entities.Customer;
import com.bank.rest.api.repositories.AccountRepository;
import com.bank.rest.api.repositories.CustomerRepository;
import com.bank.rest.api.service.helper.BankingServiceHelper;
import com.bank.rest.api.utils.StandardJsonResponse;
import com.bank.rest.api.utils.StandardJsonResponseImpl;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private BankingServiceHelper bankingServiceHelper;

	/**
	 * Find Account
	 * 
	 * @param accountNumber
	 * @return
	 */
	public ResponseEntity<?> findByAccountNumber(Long accountNumber) {
		logger.info("findByAccountNumber:: accountNumber :{}",accountNumber);
		StandardJsonResponse jsonResponse = new StandardJsonResponseImpl();
		Optional<Account> accountEntityOpt = accountRepository.findByAccountNumber(accountNumber);

		HashMap<String, Object> responseData = new HashMap<>();
		
		if (accountEntityOpt.isPresent()) {
			
			responseData.put("Account Details", bankingServiceHelper.convertToAccountDomain(accountEntityOpt.get()));

			jsonResponse.setSuccess(true, "SUCCESS", "Account Details!");
			jsonResponse.setData(responseData);
			jsonResponse.setHttpResponseCode(HttpStatus.OK.value());

			return new ResponseEntity<StandardJsonResponse>(jsonResponse, HttpStatus.OK);
		} else {
			jsonResponse.setSuccess(true, "Error", "Account Number " + accountNumber + " not found.");
			jsonResponse.setHttpResponseCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<StandardJsonResponse>(jsonResponse, HttpStatus.NOT_FOUND);
		}

	}

	/**
	 * Create new account
	 * 
	 * @param accountInformation
	 * @param cust_Id
	 * 
	 * @return
	 */
	@SuppressWarnings("null")
	public ResponseEntity<?> addNewAccount(AccountInformation accountInformation, Long customerNumber) {

		StandardJsonResponse jsonResponse = new StandardJsonResponseImpl();
		Optional<Customer> customerEntity = customerRepository.findById(customerNumber);

		HashMap<String, Object> responseData = new HashMap<>();
		
		if (customerEntity.isPresent()) {

			if (accountInformation.getAccountNumber() != null) {
				Optional<Account> accountEntity = accountRepository
						.findByAccountNumber(accountInformation.getAccountNumber());
				if (accountEntity.isPresent()) {
					jsonResponse.setSuccess(false, "Error",
							"Given Account Number:" + accountInformation.getAccountNumber()
									+ " is exist! for Customer Number :" + customerNumber);
					jsonResponse.setHttpResponseCode(HttpStatus.BAD_REQUEST.value());
					return new ResponseEntity<StandardJsonResponse>(jsonResponse, HttpStatus.BAD_REQUEST);
				}
			}
			Account account = bankingServiceHelper.convertToAccountEntity(accountInformation);
			if (account == null) {
				account.setAccout_type(AccountType.SAVING.getName());
			}
			account.setCustomer(customerEntity.get());
			accountRepository.save(account);
			
			responseData.put("New Account Details ", bankingServiceHelper.convertToAccountDomain(account));
			jsonResponse.setSuccess(true, "SUCCESS", "New Account created successfully.");
			jsonResponse.setData(responseData);
			jsonResponse.setHttpResponseCode(HttpStatus.CREATED.value());
			return new ResponseEntity<StandardJsonResponse>(jsonResponse, HttpStatus.CREATED);

		} else {
			jsonResponse.setSuccess(false, "Error", "Customer Number" + customerNumber + " not found.");
			jsonResponse.setHttpResponseCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<StandardJsonResponse>(jsonResponse, HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public ResponseEntity<?> calculateInterestRates(Long accountNumber, Double amount) {
		StandardJsonResponse jsonResponse = new StandardJsonResponseImpl();

		Optional<Account> accountEntityOpt = accountRepository.findByAccountNumber(accountNumber);
		HashMap<String, Object> responseData = new HashMap<>();

		if (accountEntityOpt.isPresent()) {
			double rate = calculateInterest(amount, accountEntityOpt.get().getInterestRate());
			responseData.put("Interest Rates", "$ " + rate);

			jsonResponse.setSuccess(true, "SUCCESS", "Calculated Interest Rates!");
			jsonResponse.setData(responseData);
			jsonResponse.setHttpResponseCode(HttpStatus.OK.value());

			return new ResponseEntity<StandardJsonResponse>(jsonResponse, HttpStatus.OK);
		}

		return null;
	}

	private double calculateInterest(Double amount, double interestRate) {
		double amt = amount;
		double interest = (amt * interestRate * 1) / 100.0;
		amt += interest;
		return amt;
	}

}
