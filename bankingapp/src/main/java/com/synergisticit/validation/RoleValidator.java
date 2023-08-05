package com.synergisticit.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Role;

@Component
public class RoleValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Role.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		Role role = (Role) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "roleName", "role.roleName.empty", "Role name is a required field.");
		
		if (role.getRoleName().length() < 3) {
			errors.rejectValue("roleName", "role.roleName", "Role name must have at least 3 characters");
		}
	}
}
