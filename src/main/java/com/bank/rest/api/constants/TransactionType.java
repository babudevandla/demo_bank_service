package com.bank.rest.api.constants;

public enum TransactionType {

	DEPOSIT("DEPOSIT"), WITHDRAWAL("WITHDRAWAL");

	String name;

	TransactionType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}