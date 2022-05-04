package com.bank.rest.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.rest.api.constants.URLConstants;
import com.bank.rest.api.dto.AccountInformation;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(URLConstants.BASE_URL)
@Api(tags = { "Accounts REST API endpoints" })
public class AccountRestApiController extends BaseRestApiController {

	protected final Logger logger = LoggerFactory.getLogger(AccountRestApiController.class);
	
	@GetMapping(value = URLConstants.ACCOUNTS_URL+"/{accountNumber}")
	@ApiOperation(value = "Get account details", notes = "Find account details by account number")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	public ResponseEntity<?> getByAccountNumber(@PathVariable Long accountNumber) {

		return accountService.findByAccountNumber(accountNumber);
	}

	@PostMapping(value = URLConstants.ACCOUNTS_URL+"/add/{customerNumber}")
	@ApiOperation(value = "Add a new account", notes = "Create an new account for existing customer.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	public ResponseEntity<?> addNewAccount(@RequestBody AccountInformation accountInformation,
			@PathVariable Long customerNumber) {

		return accountService.addNewAccount(accountInformation, customerNumber);
	}
	
	
	
	@GetMapping(value = "/calculate_interest/{accountNumber}")
	@ApiOperation(value = "Get interest rates details", notes = "Find interest rates")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	public ResponseEntity<?> calculateInterestRates(@PathVariable Long accountNumber,@RequestParam Double amount) {

		return accountService.calculateInterestRates(accountNumber,amount);
	}
	

}
