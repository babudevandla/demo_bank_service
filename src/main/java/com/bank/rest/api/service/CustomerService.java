package com.bank.rest.api.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.bank.rest.api.dto.CustomerDetails;

public interface CustomerService {

	List<CustomerDetails> findAll();

	ResponseEntity<Object> addCustomer(CustomerDetails customer);

	CustomerDetails findByCustomerNumber(Long cust_Id);

	ResponseEntity<Object> updateCustomer(CustomerDetails customerDetails, Long cust_Id);

	ResponseEntity<Object> deleteCustomer(Long cust_Id);

}
