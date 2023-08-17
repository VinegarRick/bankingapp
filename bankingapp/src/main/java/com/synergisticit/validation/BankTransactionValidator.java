package com.synergisticit.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
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
				
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bankTransactionDateTime", "bankTransaction.bankTransactionDateTime", "Date and time must be selected.");
		
		if (bankTransaction.getBankTransactionFromAccount() == null && bankTransaction.getBankTransactionToAccount() == null) {
			errors.rejectValue("bankTransactionToAccount", "bankTransaction.bankTransactionToAccount", "To Account and From Account cannot both be empty.");
			errors.rejectValue("bankTransactionFromAccount", "bankTransaction.bankTransactionFromAccount", "To Account and From Account cannot both be empty.");
		}
		
		if (bankTransaction.getTransactionAmount() < 0) {
			errors.rejectValue("transactionAmount", "bankTransaction.transactionAmount", "Transaction amount cannot be negative.");
		}
	}
}
