package com.bank.rest.api.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bank.rest.api.entities.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    
}