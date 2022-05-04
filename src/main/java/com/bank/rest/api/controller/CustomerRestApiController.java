package com.bank.rest.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.rest.api.constants.URLConstants;
import com.bank.rest.api.dto.CustomerDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(URLConstants.CUSTOMER_URL)
@Api(tags = { "Customer REST API endpoints" })
public class CustomerRestApiController extends BaseRestApiController{

	protected final Logger logger = LoggerFactory.getLogger(CustomerRestApiController.class);

	@GetMapping(value = "/all")
	@ApiOperation(value = "Find all customers", notes = "Gets details of all the customers")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	public List<CustomerDetails> getAllCustomers() {
		logger.info("CustomerRestApiController::getAllCustomers():: IN");
		return customerService.findAll();
	}

	@PostMapping(value = "/add")
	@ApiOperation(value = "Add a Customer", notes = "Add customer and create an account")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	public ResponseEntity<Object> addCustomer(@RequestBody CustomerDetails customer) {
		logger.info("CustomerRestApiController::addCustomer():: IN");
		return customerService.addCustomer(customer);
	}

	@GetMapping(value = "/{cust_Id}")
	@ApiOperation(value = "Get customer details", notes = "Get Customer details by customer number.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Success", response = CustomerDetails.class, responseContainer = "Object"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	public CustomerDetails getCustomer(@PathVariable Long cust_Id) {
		logger.info("CustomerRestApiController::getCustomer(), cust_Id::{}",cust_Id);
		return customerService.findByCustomerNumber(cust_Id);
	}

	@PutMapping(value = "/{cust_Id}")
	@ApiOperation(value = "Update customer", notes = "Update customer and any other account information associated with him.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Object.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	public ResponseEntity<Object> updateCustomer(@RequestBody CustomerDetails customerDetails,
			@PathVariable Long cust_Id) {
		logger.info("CustomerRestApiController::updateCustomer(), cust_Id::{}",cust_Id);
		return customerService.updateCustomer(customerDetails, cust_Id);
	}

	@DeleteMapping(path = "/{cust_Id}")
	@ApiOperation(value = "Delete customer and related accounts", notes = "Delete customer")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Object.class),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 500, message = "Internal Server Error") })

	public ResponseEntity<Object> deleteCustomer(@PathVariable Long cust_Id) {
		logger.info("CustomerRestApiController::deleteCustomer(), cust_Id::{}",cust_Id);
		return customerService.deleteCustomer(cust_Id);
	}

}