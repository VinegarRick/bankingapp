package com.synergisticit.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.synergisticit.domain.Branch;

@Component
public class BranchValidator implements Validator {

		@Override
		public boolean supports(Class<?> clazz) {
			return Branch.class.equals(clazz);
		}
		
		@Override
		public void validate(Object target, Errors errors) {
			Branch branch = (Branch) target;
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "branchName", "branch.branchName.empty", "Branch name is a required field.");
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "branchAddress", "branch.branchAddress.empty", "Branch address is a required field.");
		}
}
