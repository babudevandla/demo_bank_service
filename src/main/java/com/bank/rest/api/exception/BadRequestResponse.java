package com.bank.rest.api.exception;

import java.util.Map;

public class BadRequestResponse {
    private Map<String, String> data;

    
	public BadRequestResponse(Map<String, String> data) {
		super();
		this.data = data;
	}

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}
    
    
}