package com.synergisticit.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Customer;

@Component
public class CustomerValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Customer.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		Customer customer = (Customer) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerName", "customer.customerName.empty", "Customer name is a required field.");
		
		if (customer.getCustomerMobileNum().length() < 10) {
			errors.rejectValue("customerMobileNum", "customer.customerMobileNum", "Customer mobile number must have at least 10 digits");
		}
	}
}
