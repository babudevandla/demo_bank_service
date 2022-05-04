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
@Api(tags = { "Deposit and Transactions REST API endpoints" })
public class DepositRestApiController extends BaseRestApiController{

	private static final Logger logger = LoggerFactory.getLogger(DepositRestApiController.class);


	@PutMapping(path = "/deposit/{customerNumber}")
	@ApiOperation(value = "Deposit funds in accounts", notes = "Deposit funds in accounts.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Object.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	public ResponseEntity<?>  makeDeposit(@RequestBody UserTransaction userTransaction,
			@PathVariable Long customerNumber) {
		logger.info("DepositRestApiController::makeDeposit(),customerNumber :{} ",customerNumber);
		return transactionsService.makeDeposit(userTransaction, customerNumber);
	}
	
}
