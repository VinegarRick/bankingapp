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
				
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "user.username.empty", "User is a required field.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "user.password.empty", "Password is a required field.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roles", "user.roles.empty", "Role is a required field.");
		
		if (user.getUsername().length() < 4 && user.getUsername().length() > 0) {
			errors.rejectValue("username", "user.username.length", "Username must have at least 4 characters.");
		}
		
		if (user.getPassword().length() < 8 && user.getPassword().length() > 0) {
			errors.rejectValue("password", "user.password.length", "Password must have at least 8 characters.");
		}

	}
	
	

}
