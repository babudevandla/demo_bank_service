package com.bank.rest.api.constants;

public enum AccountType {

	SAVING("SAVING"), CURRENT("CURRENT");

	String name;

	AccountType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
