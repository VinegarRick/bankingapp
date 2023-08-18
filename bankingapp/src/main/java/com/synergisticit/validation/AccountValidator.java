package com.synergisticit.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.synergisticit.domain.Account;

@Component
public class AccountValidator implements Validator {
	
	@Override
	public boolean supports (Class<?> clazz) {
		return Account.class.equals(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		Account account =  (Account) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountHolder", "account.accountHolder.empty", "Account holder is a required field");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountType", "account.accountType.empty", "Account type is a required field");
		
		
	}
}
