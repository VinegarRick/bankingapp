package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Customer;

public interface CustomerService {
	Customer save(Customer customer);
	Customer find(Long customerId);
	List<Customer> findAll();
	void delete(Long customerId);
	boolean exists(Long customerId);
}
