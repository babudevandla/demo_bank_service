package com.bank.rest.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.rest.api.constants.URLConstants;
import com.bank.rest.api.dto.TransactionDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(URLConstants.BASE_URL)
@Api(tags = { "Transactions REST API endpoints" })
public class TransactionRestApiController extends BaseRestApiController {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionRestApiController.class);
	
	
	@GetMapping(path = "/transactions/{customerNumber}/{accountNumber}")
	@ApiOperation(value = "Get all transactions", notes = "Get all Transactions by account number")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	public List<TransactionDetails> getTransactionByAccountNumber(@PathVariable Long customerNumber,@PathVariable Long accountNumber) {
		LOGGER.info("TransactionRestApiController::getTransactionByAccountNumber() , customerNumber :{}, accountNumber:{}",customerNumber,accountNumber);
		return transactionsService.findTransactionsByAccountNumber(customerNumber,accountNumber);
	}
	
}
