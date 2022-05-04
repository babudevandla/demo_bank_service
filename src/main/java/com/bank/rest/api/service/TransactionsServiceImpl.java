package com.bank.rest.api.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.rest.api.constants.Constants;
import com.bank.rest.api.constants.TransactionType;
import com.bank.rest.api.dto.TransactionDetails;
import com.bank.rest.api.dto.UserTransaction;
import com.bank.rest.api.entities.Account;
import com.bank.rest.api.entities.Customer;
import com.bank.rest.api.entities.Transaction;
import com.bank.rest.api.repositories.AccountRepository;
import com.bank.rest.api.repositories.CustomerRepository;
import com.bank.rest.api.repositories.TransactionRepository;
import com.bank.rest.api.service.helper.BankingServiceHelper;
import com.bank.rest.api.utils.AccountUtils;
import com.bank.rest.api.utils.StandardJsonResponse;
import com.bank.rest.api.utils.StandardJsonResponseImpl;

@Service
@Transactional
public class TransactionsServiceImpl implements TransactionsService {

	private static final Logger logger = LoggerFactory.getLogger(TransactionsServiceImpl.class);

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private BankingServiceHelper bankingServiceHelper;

	@Override
	public List<Transaction> findByDateBetweenAndType(Date startOfDay, Date endOfDay, String type) {
		return transactionRepository.findByDateBetweenAndType(startOfDay, endOfDay, type);
	}

	@Override
	public Transaction save(Transaction accountTransaction) {
		// TODO Auto-generated method stub
		return transactionRepository.save(accountTransaction);
	}

	@Override
	public ResponseEntity<?> makeDeposit(UserTransaction userTransaction, Long customerNumber) {
		StandardJsonResponse jsonResponse = new StandardJsonResponseImpl();
		HashMap<String, Object> responseData = new HashMap<>();
		
		try {

			double total = 0;
			
			Optional<Customer> customerEntity = customerRepository.findById(customerNumber);
			Optional<Account> account = null;
			if (customerEntity.isPresent()) {
				account = accountRepository.findByAccountNumber(userTransaction.getAccountNumber());
				if (account.isPresent()) {
					
					if(customerNumber != account.get().getCustomer().getCustomer_id()) {
						jsonResponse.setSuccess(false, "Error", "Account Number::" + userTransaction.getAccountNumber() + " not belongs to , Given Customer :"+customerNumber);
						jsonResponse.setHttpResponseCode(HttpStatus.BAD_REQUEST.value());
						return new ResponseEntity<StandardJsonResponse>(jsonResponse, HttpStatus.BAD_REQUEST);
					}
							
				}else {
					jsonResponse.setSuccess(true, "Error", "Account Number: " + userTransaction.getAccountNumber() + " not found.");
					jsonResponse.setHttpResponseCode(HttpStatus.NOT_FOUND.value());
					return new ResponseEntity<StandardJsonResponse>(jsonResponse, HttpStatus.NOT_FOUND);
				}
				
			}else {
				jsonResponse.setSuccess(false, "Error", "Customer Number:" + customerNumber + " not found.");
				jsonResponse.setHttpResponseCode(HttpStatus.NOT_FOUND.value());
				return new ResponseEntity<StandardJsonResponse>(jsonResponse, HttpStatus.NOT_FOUND);
			}
				
				
			
			
			// check maximum limit deposit for the day has been reached
			List<Transaction> deposits = transactionRepository.findByDateBetweenAndTypeAndAccountNumber(
					AccountUtils.getStartOfDay(new Date()), AccountUtils.getEndOfDay(new Date()),
					TransactionType.DEPOSIT.getName(),userTransaction.getAccountNumber());

			if (deposits.size() > 0) {
				for (Transaction accountTransaction : deposits) {
					total += accountTransaction.getAmount();
				}
				if (total + userTransaction.getAmount() > Constants.MAX_DEPOSIT_PER_DAY) {
					jsonResponse.setSuccess(false, "Error", "Deposit for the day should not be more than $150K");
					jsonResponse.setHttpResponseCode(HttpStatus.NOT_ACCEPTABLE.value());
					return new ResponseEntity<StandardJsonResponse>(jsonResponse, HttpStatus.NOT_ACCEPTABLE);
				}
			}

			// Check whether the amount being deposited exceeds the
			// MAX_DEPOSIT_PER_TRANSACTION
			if (userTransaction.getAmount() > Constants.MAX_DEPOSIT_PER_TRANSACTION) {
				jsonResponse.setSuccess(false, "Error", "Deposit per transaction should not be more than $50K");
				jsonResponse.setHttpResponseCode(HttpStatus.NOT_ACCEPTABLE.value());
				return new ResponseEntity<StandardJsonResponse>(jsonResponse, HttpStatus.NOT_ACCEPTABLE);
			}

			// check whether transactions exceeds the max allowed per day
			if (deposits.size() < Constants.MAX_DEPOSIT_TRANSACTIONS_PER_DAY) {
				Transaction accountTransaction = new Transaction(userTransaction.getAccountNumber(),
						userTransaction.getAmount(), TransactionType.DEPOSIT.getName(), new Date());
				accountTransaction.setAccount(account.get());
				Transaction transaction = transactionRepository.save(accountTransaction);

				
				double newBalance = account.get().getBalance() + transaction.getAmount();
				account.get().setBalance(newBalance);

				Account account1 = account.get();
				accountRepository.save(account1);
				
				responseData.put("Deposit Transaction ", bankingServiceHelper.convertToTransactionDomain(transaction));
				
				jsonResponse.setSuccess(true, "SUCCESS", "Deposit sucessfully!");
				jsonResponse.setData(responseData);
				jsonResponse.setHttpResponseCode(HttpStatus.OK.value());
				return new ResponseEntity<StandardJsonResponse>(jsonResponse, HttpStatus.OK);

			} else {
				jsonResponse.setSuccess(false, "Error", "maximum transactions for the day Exceeded");
				jsonResponse.setHttpResponseCode(HttpStatus.NOT_ACCEPTABLE.value());
				return new ResponseEntity<StandardJsonResponse>(jsonResponse, HttpStatus.NOT_ACCEPTABLE);
			}

		} catch (Exception e) {
			logger.error("exception", e);
			jsonResponse.setSuccess(false, StandardJsonResponse.DEFAULT_MSG_TITLE_VALUE,
					StandardJsonResponse.DEFAULT_MSG_NAME_VALUE);
			jsonResponse.setHttpResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<StandardJsonResponse>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@Override
	public ResponseEntity<?> withdrawalDetails(UserTransaction userTransaction, Long customerNumber) {
		StandardJsonResponse jsonResponse = new StandardJsonResponseImpl();

		HashMap<String, Object> responseData = new HashMap<>();
		
		try {

			double total = 0;
			
			Optional<Customer> customerEntity = customerRepository.findById(customerNumber);
			Optional<Account> account = null;
			if (customerEntity.isPresent()) {
				account = accountRepository.findByAccountNumber(userTransaction.getAccountNumber());
				if (account.isPresent()) {
					
					if(customerNumber != account.get().getCustomer().getCustomer_id()) {
						jsonResponse.setSuccess(false, "Error", "Account Number::" + userTransaction.getAccountNumber() + " not belongs to , Given Customer :"+customerNumber);
						jsonResponse.setHttpResponseCode(HttpStatus.BAD_REQUEST.value());
						return new ResponseEntity<StandardJsonResponse>(jsonResponse, HttpStatus.BAD_REQUEST);
					}
							
				}else {
					jsonResponse.setSuccess(true, "Error", "Account Number: " + userTransaction.getAccountNumber() + " not found.");
					jsonResponse.setHttpResponseCode(HttpStatus.NOT_FOUND.value());
					return new ResponseEntity<StandardJsonResponse>(jsonResponse, HttpStatus.NOT_FOUND);
				}
				
			}else {
				jsonResponse.setSuccess(false, "Error", "Customer Number:" + customerNumber + " not found.");
				jsonResponse.setHttpResponseCode(HttpStatus.NOT_FOUND.value());
				return new ResponseEntity<StandardJsonResponse>(jsonResponse, HttpStatus.NOT_FOUND);
			}
			
			// check balance
			double balance = accountRepository.findByAccountNumber(userTransaction.getAccountNumber()).get().getBalance();
			if (userTransaction.getAmount() > balance) {
				jsonResponse.setSuccess(false, "Error", "You have insufficient funds");
				jsonResponse.setHttpResponseCode(HttpStatus.NOT_ACCEPTABLE.value());
				return new ResponseEntity<StandardJsonResponse>(jsonResponse, HttpStatus.NOT_ACCEPTABLE);
			}

			// check maximum limit withdrawal for the day has been reached
			List<Transaction> withdrawals = transactionRepository.findByDateBetweenAndTypeAndAccountNumber(
					AccountUtils.getStartOfDay(new Date()), AccountUtils.getEndOfDay(new Date()),
					TransactionType.WITHDRAWAL.getName(),userTransaction.getAccountNumber());

			if (withdrawals.size() > 0) {
				for (Transaction accountTransaction : withdrawals) {
					total += accountTransaction.getAmount();
				}
				if (total + userTransaction.getAmount() > Constants.MAX_WITHDRAWAL_PER_DAY) {
					jsonResponse.setSuccess(false, "Error", "Withdrawal per day should not be more than $50K");
					jsonResponse.setHttpResponseCode(HttpStatus.NOT_ACCEPTABLE.value());
					return new ResponseEntity<StandardJsonResponse>(jsonResponse, HttpStatus.NOT_ACCEPTABLE);
				}
			}

			// Check whether the amount being withdrawn exceeds the
			// MAX_WITHDRAWAL_PER_TRANSACTION
			if (userTransaction.getAmount() > Constants.MAX_WITHDRAWAL_PER_TRANSACTION) {
				jsonResponse.setSuccess(false, "Error", "Exceeded Maximum Withdrawal Per Transaction");
				jsonResponse.setHttpResponseCode(HttpStatus.NOT_ACCEPTABLE.value());
				return new ResponseEntity<StandardJsonResponse>(jsonResponse, HttpStatus.NOT_ACCEPTABLE);
			}

			// check whether transactions exceeds the max allowed per day
			if (withdrawals.size() < Constants.MAX_WITHDRAWAL_TRANSACTIONS_PER_DAY) {
				Transaction accountTransaction = new Transaction(userTransaction.getAccountNumber(),
						userTransaction.getAmount(), TransactionType.WITHDRAWAL.getName(), new Date());
				accountTransaction.setAccount(account.get());
				Transaction transaction = transactionRepository.save(accountTransaction);
				double amount = transaction.getAmount();

				
				double newBalance = account.get().getBalance() - amount;
				account.get().setBalance(newBalance);
				accountRepository.save(account.get());

				responseData.put("Withdrawal Transaction ", bankingServiceHelper.convertToTransactionDomain(transaction));
				
				jsonResponse.setSuccess(true, "SUCCESS", "Withdrawal sucessfully!");
				jsonResponse.setData(responseData);
				jsonResponse.setHttpResponseCode(HttpStatus.OK.value());
				return new ResponseEntity<StandardJsonResponse>(jsonResponse, HttpStatus.OK);

			} else {
				jsonResponse.setSuccess(false, "Error", "Maximum Withdrawal transactions for the day Exceeded");
				jsonResponse.setHttpResponseCode(HttpStatus.NOT_ACCEPTABLE.value());
				return new ResponseEntity<StandardJsonResponse>(jsonResponse, HttpStatus.NOT_ACCEPTABLE);
			}

		} catch (Exception e) {
			logger.error("exception", e);
			jsonResponse.setSuccess(false, StandardJsonResponse.DEFAULT_MSG_TITLE_VALUE,
					StandardJsonResponse.DEFAULT_MSG_NAME_VALUE);
			jsonResponse.setHttpResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<StandardJsonResponse>(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * Get all transactions for a specific accountNumber
	 * 
	 * @param accountNumber
	 * @return
	 */
	public List<TransactionDetails> findTransactionsByAccountNumber(Long customerNumber, Long accountNumber){
		List<TransactionDetails> transactionDetails = new ArrayList<>();
		Optional<Account> accountEntityOpt = accountRepository.findByAccountNumber(accountNumber);
		if(accountEntityOpt.isPresent()) {
			Optional<List<Transaction>> transactionEntitiesOpt = transactionRepository.findByAccountNumber(accountNumber);
			if(transactionEntitiesOpt.isPresent()) {
				List<Transaction> transactionEntities = transactionEntitiesOpt.get().stream().sorted(Comparator.comparingLong(Transaction::getTx_id).reversed()).limit(10).collect(Collectors.toList());
				transactionEntities.forEach(transaction -> {
					transactionDetails.add(bankingServiceHelper.convertToTransactionDomain(transaction));
				});
			}
		}
		
		return transactionDetails;
	}

}
