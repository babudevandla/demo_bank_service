package com.bank.rest.api.dto;

public class UserTransaction {

	private Long accountNumber;
	private double amount;

	public UserTransaction() {

	}

	public UserTransaction(Long accountNumber, double amount) {
		super();
		this.accountNumber = accountNumber;
		this.amount = amount;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "UserTransaction [accountNumber=" + accountNumber + ", amount=" + amount + "]";
	}

}