package com.bank.rest.api.dto;

import java.util.Date;

public class TransactionDetails {

	private Long tx_id;

	private Long accountNumber;

	private Date txDateTime;

	private String txType;

	private Double txAmount;

	public TransactionDetails() {

	}

	public TransactionDetails(Long tx_id, Long accountNumber, Date txDateTime, String txType, Double txAmount) {
		super();
		this.tx_id = tx_id;
		this.accountNumber = accountNumber;
		this.txDateTime = txDateTime;
		this.txType = txType;
		this.txAmount = txAmount;
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

	public Date getTxDateTime() {
		return txDateTime;
	}

	public void setTxDateTime(Date txDateTime) {
		this.txDateTime = txDateTime;
	}

	public String getTxType() {
		return txType;
	}

	public void setTxType(String txType) {
		this.txType = txType;
	}

	public Double getTxAmount() {
		return txAmount;
	}

	public void setTxAmount(Double txAmount) {
		this.txAmount = txAmount;
	}

	@Override
	public String toString() {
		return "TransactionDetails [accountNumber=" + accountNumber + ", txDateTime=" + txDateTime + ", txType="
				+ txType + ", txAmount=" + txAmount + "]";
	}

}