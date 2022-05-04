package com.bank.rest.api.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transaction_details", schema = "online_bank")
public class Transaction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TX_ID")
	private Long tx_id;

	private Long accountNumber;

	private double amount;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "accout_id", nullable = false)
	private Account account;

	private String type;

	private Date date;

	public Transaction() {
	}

	public Transaction(Long tx_id, Long accountNumber, double amount, String type, Date date) {
		super();
		this.tx_id = tx_id;
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.type = type;
		this.date = date;
	}

	public Transaction(Long accountNumber, double amount, String type, Date date) {
		super();
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.type = type;
		this.date = date;
	}

	public Long getTx_id() {
		return tx_id;
	}

	public void setTx_id(Long tx_id) {
		this.tx_id = tx_id;
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Transaction [tx_id=" + tx_id + ", accountNumber=" + accountNumber + ", amount=" + amount + ", account="
				+ account + ", type=" + type + ", date=" + date + "]";
	}

}
