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
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerGender", "customer.customerGender.empty", "Customer gender is a required field.");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerDob", "customer.customerDob.empty", "Customer DOB is a required field.");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerMobileNum", "customer.customerMobileNum.empty", "Customer mobile number is a required field.");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerAddress.addressLine1", "customer.customerAddress.addressLine1.empty", "Customer address 1 is a required field.");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerAddress.city", "customer.customerAddress.city.empty", "City is a required field.");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerAddress.state", "customer.customerAddress.state", "State is a required field.");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerAddress.country", "customer.customerAddress.country.empty", "Country is a required field.");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "customerAddress.zipcode", "customer.customerAddress.zipcode.empty", "Zip code is a required field.");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "realId", "customer.realId.empty", "Real Id is a required field.");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "customer.userId.empty", "User Id is a required field.");
		
		if (customer.getCustomerMobileNum().length() < 10) {
			errors.rejectValue("customerMobileNum", "customer.customerMobileNum", "Customer mobile number must have at least 10 digits");
		}
	}
}
