package com.synergisticit.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Customer;
import com.synergisticit.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer find(Long customerId) {
		Optional<Customer> customer = customerRepository.findById(customerId);
		if (customer.isPresent()) {
			return customer.get();
		} else {
			return null;
		}
	}

	@Override
	public List<Customer> findAll() {
		List<Customer> customeres = new ArrayList<>();
		Iterable<Customer> iterable = customerRepository.findAll();
		Iterator<Customer> iter = iterable.iterator();
		
		while (iter.hasNext()) {
			customeres.add(iter.next());
		}
		
		return customeres;
	}

	@Override
	public void delete(Long customerId) {
		customerRepository.deleteById(customerId);
	}

	@Override
	public boolean exists(Long customerId) {
		return customerRepository.existsById(customerId);
	}

}
