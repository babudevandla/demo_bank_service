package com.bank.rest.api.constants;

public enum CustomerStatus {

	ACTIVE("ACTIVE"), INACTIVE("INACTIVE");

	String name;

	CustomerStatus(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
