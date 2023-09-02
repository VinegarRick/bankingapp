package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.BankTransaction;
import com.synergisticit.domain.Branch;
import com.synergisticit.domain.Customer;
import com.synergisticit.service.AccountService;
import com.synergisticit.service.BankTransactionService;
import com.synergisticit.validation.BankTransactionValidator;

import jakarta.validation.Valid;

@Controller
public class BankTransactionRestController {

	@Autowired BankTransactionService bankTransactionService;
	@Autowired AccountService accountService;
	@Autowired BankTransactionValidator bankTransactionValidator;
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(bankTransactionValidator);
		
	}
	
	@GetMapping(value="banktransaction/getAll")
	public ResponseEntity<List<BankTransaction>> getAllBankTransactions() {
		List<BankTransaction> bankTransactionList = bankTransactionService.findAll();
		
		return new ResponseEntity<List<BankTransaction>>(bankTransactionList, HttpStatus.OK);
	}
	
	@PostMapping(value="banktransaction/create")
	public ResponseEntity<?> createBankTransanction(@RequestBody @Valid BankTransaction bankTransaction, BindingResult br) {
		System.out.println("saveBankTransaction - bankTransaction: " + bankTransaction);
		
		int i = 1;
		if (br.hasFieldErrors()) {
			List<FieldError> fieldErrors = br.getFieldErrors();
			StringBuilder sb = new StringBuilder("");
			for (FieldError fieldError : fieldErrors) {
				sb = sb.append("\n"+i+".\"")
						.append(fieldError.getField() +"\": ")
						.append(fieldError.getDefaultMessage());
				i++;				
			}
			return new ResponseEntity<StringBuilder>(sb, HttpStatus.ACCEPTED);
		} else {
			double transactionAmount = bankTransaction.getTransactionAmount();
			Long toAccountId = bankTransaction.getBankTransactionToAccount();
			Long fromAccountId = bankTransaction.getBankTransactionFromAccount();
			
			if (toAccountId != null && fromAccountId != null) {
				bankTransactionService.balanceTransfer(fromAccountId, toAccountId, transactionAmount);
			} else if (toAccountId != null) {
				bankTransactionService.addBalance(toAccountId, transactionAmount);
			} else {
				bankTransactionService.subtractBalance(fromAccountId, transactionAmount);
			}
			
			BankTransaction bt = bankTransactionService.save(bankTransaction);		
			
			return new ResponseEntity<BankTransaction>(bt, HttpStatus.CREATED);
		}
	}
	
	@PutMapping(value="banktransaction/update")
	public ResponseEntity<String> updateBankTransaction(@RequestParam long bankTransactionId, @RequestBody BankTransaction bankTransaction) {
		System.out.println("updateBankTransaction - bankTransaction: " + bankTransactionId);
		BankTransaction bt = bankTransactionService.find(bankTransactionId);
		
		if (bt != null) {
			if (bankTransaction.getBankTransactionToAccount() != null) {
				bt.setBankTransactionToAccount(bankTransaction.getBankTransactionToAccount());
			}
			
			if (bankTransaction.getBankTransactionFromAccount() != null) {
				bt.setBankTransactionFromAccount(bankTransaction.getBankTransactionFromAccount());
			}
			
			if (bankTransaction.getTransactionType() != null) {
				bt.setTransactionType(bankTransaction.getTransactionType());
			}
			
			if (bankTransaction.getBankTransactionDateTime() != null) {
				bt.setBankTransactionDateTime(bankTransaction.getBankTransactionDateTime());
			}
			
			if (bankTransaction.getComments() != null) {
				bt.setComments(bankTransaction.getComments());
			}
			
			bankTransactionService.save(bt);
			
			return new ResponseEntity<String>("Bank transaction record updated for id: " + bankTransactionId, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("There is no bank trnsaction with id: " + bankTransactionId, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("banktransaction/delete")
	public ResponseEntity<String> deleteBankTransaction(@RequestParam long bankTransactionId) {
		System.out.println("deleteBankTransaction - bankTransaction: " + bankTransactionId);
		BankTransaction bt = bankTransactionService.find(bankTransactionId);
		
		if (bt != null) {
			bankTransactionService.delete(bankTransactionId);
			
			return new ResponseEntity<String>("Bank transaction record deleted for id: " + bankTransactionId, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("There is no bank transaction record with id: " + bankTransactionId, HttpStatus.NOT_FOUND);
		}
	}
	
}
