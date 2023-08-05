package com.synergisticit.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.User;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User)target;
		
		System.out.println("hahaha");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "user.username.empty", "User is a required field.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "user.password.empty", "Password is a required field.");
		
		if (user.getUsername().length() < 4) {
			errors.rejectValue("username", "user.username.length", "Username must have at least 4 characters.");
		}
		
		if (user.getPassword().length() < 8) {
			errors.rejectValue("password", "user.password.length", "Password must have at least 8 characters.");
		}

	}
	
	

}
