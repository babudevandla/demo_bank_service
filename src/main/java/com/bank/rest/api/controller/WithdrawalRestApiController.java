package com.bank.rest.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.rest.api.constants.URLConstants;
import com.bank.rest.api.dto.UserTransaction;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(URLConstants.BASE_URL)
@Api(tags = { "Withdrawal and Transactions REST API endpoints" })
public class WithdrawalRestApiController extends BaseRestApiController {

	private static final Logger logger = LoggerFactory.getLogger(WithdrawalRestApiController.class);
	
	@PutMapping(path = "/withdrawal/{customerNumber}")
	@ApiOperation(value = "Withdrawal funds from accounts", notes = "Withdrawal funds from accounts.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Object.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	public ResponseEntity<?> withdrawalDetails(@RequestBody UserTransaction userTransaction,
			@PathVariable Long customerNumber) {
		logger.info("Calling withdrawalDetails()...");
		return transactionsService.withdrawalDetails(userTransaction, customerNumber);
	}
	
}
