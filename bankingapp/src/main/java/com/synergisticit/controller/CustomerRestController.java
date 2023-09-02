package com.synergisticit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Customer;
import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;
import com.synergisticit.service.CustomerService;
import com.synergisticit.service.UserService;
import com.synergisticit.validation.CustomerValidator;

import jakarta.validation.Valid;

@RestController
public class CustomerRestController {
	
	@Autowired CustomerService customerService;
	@Autowired UserService userService;
	@Autowired CustomerValidator customerValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(customerValidator);
	}
	
	@GetMapping(value="customer/getAll")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> customerList = customerService.findAll();
		
		return new ResponseEntity<List<Customer>>(customerList, HttpStatus.OK);
	}
	
	@PostMapping(value="customer/create")
	public ResponseEntity<?> createCustomer(@RequestBody @Valid Customer customer, BindingResult br) {
		System.out.println("saveCustomer - customer: "+customer);
	
		int i = 1;
		if (br.hasFieldErrors()) {
			List<FieldError> fieldErrors = br.getFieldErrors();
			StringBuilder sb = new StringBuilder("");
			for (FieldError fieldError : fieldErrors) {
				sb = sb.append("\n"+i+".\"")
						.append(fieldError.getField() +"\": ")
						.append(fieldError.getDefaultMessage());
				i++;				
			}
			return new ResponseEntity<StringBuilder>(sb, HttpStatus.ACCEPTED);
		} else {	
			User user = userService.find(customer.getUserId());
			customer.setUser(user);			
			
			Customer c = customerService.save(customer);		
			
			return new ResponseEntity<Customer>(c, HttpStatus.CREATED);
		}
	}
	
	@PutMapping("customer/update")
	public ResponseEntity<String> updateCustomer(@RequestParam long customerId, @RequestBody Customer customer) {
		System.out.println("updateCustomer - customer: "+customer);
		Customer c = customerService.find(customerId);
		
		if (c != null) {
			if (customer.getCustomerName() != null) {
				c.setCustomerName(customer.getCustomerName());
			}
			
			if (customer.getCustomerGender() != null) {
				c.setCustomerGender(customer.getCustomerGender());
			}
			
			if (customer.getCustomerDob() != null) {
				c.setCustomerDob(customer.getCustomerDob());
			}
			
			if (customer.getCustomerMobileNum() != null) {
				c.setCustomerMobileNum(customer.getCustomerMobileNum());
			}
			
			if (customer.getRealId() != null) {
				c.setRealId(customer.getRealId());
			}
			
			if (customer.getCustomerAddress() != null) {
				if (customer.getCustomerAddress().getAddressLine1() != null) {
					c.getCustomerAddress().setAddressLine1(customer.getCustomerAddress().getAddressLine1());
				}
				
				if (customer.getCustomerAddress().getAddressLine2() != null) {
					c.getCustomerAddress().setAddressLine2(customer.getCustomerAddress().getAddressLine2());
				}
				
				if (customer.getCustomerAddress().getCity() != null) {
					c.getCustomerAddress().setCity(customer.getCustomerAddress().getCity());
				}
				
				if (customer.getCustomerAddress().getState() != null) {
					c.getCustomerAddress().setState(customer.getCustomerAddress().getState());
				}
				
				if (customer.getCustomerAddress().getCountry() != null) {
					c.getCustomerAddress().setCountry(customer.getCustomerAddress().getCountry());
				}
				
				if (customer.getCustomerAddress().getZipcode() != null) {
					c.getCustomerAddress().setZipcode(customer.getCustomerAddress().getZipcode());
				}
			}			
			
			customerService.save(c);
			
			return new ResponseEntity<String>("Customer record updated for id: " + customerId, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("There is no customer with id: " + customerId, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("customer/delete")
	public ResponseEntity<String> deleteCustomer(@RequestParam long customerId) {
		System.out.println("deleteCustomer - customerId: " + customerId);
		Customer c = customerService.find(customerId);
		
		if (c != null) {
			customerService.delete(customerId);
			return new ResponseEntity<String>("Customer record deleted for id: " + customerId, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("There is no customer with id: " + customerId, HttpStatus.NOT_FOUND);
		}
	}
	
}
