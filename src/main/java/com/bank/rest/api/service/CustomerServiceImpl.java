package com.bank.rest.api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.rest.api.dto.CustomerDetails;
import com.bank.rest.api.entities.Customer;
import com.bank.rest.api.repositories.CustomerRepository;
import com.bank.rest.api.service.helper.BankingServiceHelper;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private BankingServiceHelper bankingServiceHelper;

	public List<CustomerDetails> findAll() {

		List<CustomerDetails> allCustomerDetails = new ArrayList<>();

		Iterable<Customer> customerList = customerRepository.findAll();

		customerList.forEach(customer -> {
			allCustomerDetails.add(bankingServiceHelper.convertToCustomerDomain(customer));
		});

		return allCustomerDetails;
	}

	/**
	 * CREATE Customer
	 * 
	 * @param customerDetails
	 * @return
	 */
	public ResponseEntity<Object> addCustomer(CustomerDetails customerDetails) {

		Customer customer = bankingServiceHelper.convertToCustomerEntity(customerDetails);
		customer.setJoinDate(new Date());
		customerRepository.save(customer);

		return ResponseEntity.status(HttpStatus.CREATED).body("New Customer created successfully.");
	}

	/**
	 * READ Customer
	 * 
	 * @param cust_id
	 * @return
	 */

	public CustomerDetails findByCustomerNumber(Long customerNumber) {

		Optional<Customer> customerEntity = customerRepository.findById(customerNumber);

		if (customerEntity.isPresent())
			return bankingServiceHelper.convertToCustomerDomain(customerEntity.get());

		return null;
	}

	/**
	 * UPDATE Customer
	 * 
	 * @param customerDetails
	 * @param customerNumber
	 * @return
	 */
	public ResponseEntity<Object> updateCustomer(CustomerDetails customerDetails, Long customerNumber) {
		Optional<Customer> managedCustomerEntityOpt = customerRepository.findById(customerNumber);
		Customer unmanagedCustomerEntity = bankingServiceHelper.convertToCustomerEntity(customerDetails);
		if (managedCustomerEntityOpt.isPresent()) {
			Customer managedCustomerEntity = managedCustomerEntityOpt.get();

			managedCustomerEntity.setStatus(unmanagedCustomerEntity.getStatus());
			managedCustomerEntity.setFirstName(unmanagedCustomerEntity.getFirstName());
			managedCustomerEntity.setLastName(unmanagedCustomerEntity.getLastName());
			managedCustomerEntity.setAddress(unmanagedCustomerEntity.getAddress());
			managedCustomerEntity.setCity(unmanagedCustomerEntity.getCity());
			managedCustomerEntity.setState(unmanagedCustomerEntity.getState());
			managedCustomerEntity.setUpdatedDate(new Date());

			customerRepository.save(managedCustomerEntity);

			return ResponseEntity.status(HttpStatus.OK).body("Success: Customer updated.");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer Number " + customerNumber + " not found.");
		}
	}

	/**
	 * DELETE Customer
	 * 
	 * @param cust_id
	 * @return
	 */
	public ResponseEntity<Object> deleteCustomer(Long customerNumber) {

		Optional<Customer> managedCustomerEntityOpt = customerRepository.findById(customerNumber);

		if (managedCustomerEntityOpt.isPresent()) {
			Customer managedCustomerEntity = managedCustomerEntityOpt.get();
			customerRepository.delete(managedCustomerEntity);
			return ResponseEntity.status(HttpStatus.OK).body("Success: Customer deleted.");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer does not exist.");
		}

		// TODO: Delete all customer entries
	}

}
