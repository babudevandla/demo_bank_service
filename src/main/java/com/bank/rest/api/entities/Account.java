package com.bank.rest.api.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "account", schema = "online_bank")
public class Account implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACC_ID")
	private Long accout_id;

	private Long accountNumber;

	private String accout_type;
	private String account_name;
	private String description;
	private double balance;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private Set<Transaction> transactions;

	private double interestRate;

	public Account() {

	}

	public Account(Long accountNumber, String accout_type, String account_name, String description, double balance,
			Customer customer, double interestRate) {
		super();
		this.accountNumber = accountNumber;
		this.accout_type = accout_type;
		this.account_name = account_name;
		this.description = description;
		this.balance = balance;
		this.customer = customer;
		this.interestRate = interestRate;
	}

	public Account(Long accountNumber, String accout_type, String account_name, String description, double balance,
			double interestRate) {
		super();
		this.accountNumber = accountNumber;
		this.accout_type = accout_type;
		this.account_name = account_name;
		this.description = description;
		this.balance = balance;
		this.interestRate = interestRate;
	}

	public Long getAccout_id() {
		return accout_id;
	}

	public void setAccout_id(Long accout_id) {
		this.accout_id = accout_id;
	}

	public Long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	@Override
	public String toString() {
		return "Account [accout_id=" + accout_id + ", accountNumber=" + accountNumber + ", accout_type=" + accout_type
				+ ", account_name=" + account_name + ", description=" + description + ", balance=" + balance
				+ ", customer=" + customer + ", transactions=" + transactions + ", interestRate=" + interestRate + "]";
	}

}
