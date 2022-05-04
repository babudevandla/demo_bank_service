package com.bank.rest.api.dto;

public class AccountInformation {

	private Long accountNumber;
	private String accout_type;
	private String account_name;
	private String description;
	private double balance;
	private double interestRate;

	public AccountInformation() {

	}

	public AccountInformation(Long accountNumber, String accout_type, String account_name, String description,
			double balance, double interestRate) {
		super();
		this.accountNumber = accountNumber;
		this.accout_type = accout_type;
		this.account_name = account_name;
		this.description = description;
		this.balance = balance;
		this.interestRate = interestRate;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccout_type() {
		return accout_type;
	}

	public void setAccout_type(String accout_type) {
		this.accout_type = accout_type;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	@Override
	public String toString() {
		return "AccountInformation [accountNumber=" + accountNumber + ", accout_type=" + accout_type + ", account_name="
				+ account_name + ", description=" + description + ", balance=" + balance + ", interestRate="
				+ interestRate + "]";
	}

}