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
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "branchAddress.addressLine1", "branch.branchAddress.addressLine1.empty", "Branch address 1 is a required field.");
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "branchAddress.city", "branch.branchAddress.city.empty", "City is a required field.");
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "branchAddress.state", "branch.branchAddress.state", "State is a required field.");
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "branchAddress.country", "branch.branchAddress.country.empty", "Country is a required field.");
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "branchAddress.zipcode", "branch.branchAddress.zipcode.empty", "Zip code is a required field.");
		}
}
