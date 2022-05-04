package com.bank.rest.api.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.bank.rest.api.service.AccountService;
import com.bank.rest.api.service.CustomerService;
import com.bank.rest.api.service.TransactionsService;

public class BaseRestApiController {
	
	@Autowired
	public CustomerService customerService;
	
	@Autowired
	public AccountService accountService;

	@Autowired
	public TransactionsService transactionsService;
}