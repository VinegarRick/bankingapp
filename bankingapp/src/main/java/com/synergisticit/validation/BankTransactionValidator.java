package com.synergisticit.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.synergisticit.domain.BankTransaction;

@Component
public class BankTransactionValidator implements Validator {
	
	@Override
	public boolean supports(Class<?> clazz) {
		return BankTransaction.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		BankTransaction bankTransaction = (BankTransaction) target;
	}
}
