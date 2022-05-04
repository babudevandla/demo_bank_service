package com.bank.rest.api.service.helper;

import org.springframework.stereotype.Component;

import com.bank.rest.api.dto.AccountInformation;
import com.bank.rest.api.dto.CustomerDetails;
import com.bank.rest.api.dto.TransactionDetails;
import com.bank.rest.api.entities.Account;
import com.bank.rest.api.entities.Customer;
import com.bank.rest.api.entities.Transaction;

@Component
public class BankingServiceHelper {

	public CustomerDetails convertToCustomerDomain(Customer customer) {

		return new CustomerDetails(customer.getCustomer_id(), customer.getFirstName(), customer.getLastName(),
				customer.getAddress(), customer.getCity(), customer.getState(), customer.getMobileNum(),
				customer.getJoinDate(), customer.getUpdatedDate(), customer.getStatus());

	}

	public Customer convertToCustomerEntity(CustomerDetails customerDetails) {

		return new Customer(customerDetails.getCustomer_id(), customerDetails.getFirstName(),
				customerDetails.getLastName(), customerDetails.getAddress(), customerDetails.getCity(),
				customerDetails.getState(), customerDetails.getMobileNum(), customerDetails.getJoinDate(),
				customerDetails.getUpdatedDate(), customerDetails.getStatus());

	}

	public AccountInformation convertToAccountDomain(Account account) {

		return new AccountInformation(account.getAccountNumber(), account.getAccout_type(), account.getAccount_name(),
				account.getDescription(), account.getBalance(), account.getInterestRate());
	}

	public Account convertToAccountEntity(AccountInformation accInfo) {

		return new Account(accInfo.getAccountNumber(), accInfo.getAccout_type(), accInfo.getAccount_name(),
				accInfo.getDescription(), accInfo.getBalance(), accInfo.getInterestRate());
	}

	public TransactionDetails convertToTransactionDomain(Transaction transaction) {

		return new TransactionDetails(transaction.getTx_id(),transaction.getAccountNumber(), transaction.getDate(), transaction.getType(),
				transaction.getAmount());
	}

	public Transaction convertToTransactionEntity(TransactionDetails transactionDetails) {

		return new Transaction(transactionDetails.getAccountNumber(), transactionDetails.getTxAmount(),
				transactionDetails.getTxType(), transactionDetails.getTxDateTime());
	}

}