package com.synergisticit.controller;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.Branch;
import com.synergisticit.domain.Customer;
import com.synergisticit.service.AccountService;
import com.synergisticit.service.BranchService;
import com.synergisticit.service.CustomerService;
import com.synergisticit.validation.AccountValidator;

import jakarta.validation.Valid;

@RestController
public class AccountRestController {
	
	@Autowired AccountService accountService;
	@Autowired BranchService branchService;
	@Autowired CustomerService customerService;
	@Autowired AccountValidator accountValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(accountValidator);
	}
	
	@GetMapping(value="account/getAll")
	public ResponseEntity<List<Account>> getAllAccounts() {
		List<Account> accountList = accountService.findAll();
		
		return new ResponseEntity<List<Account>>(accountList, HttpStatus.OK);
	}
	
	@PostMapping(value="account/create")
	public ResponseEntity<?> createAccount(@RequestBody @Valid Account account, BindingResult br) {
		System.out.println("saveAccount - account: "+account);
		
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
			//return ResponseEntity.badRequest().body("Validation failed");
			return new ResponseEntity<StringBuilder>(sb, HttpStatus.ACCEPTED);
		} else {
		    Branch branch = branchService.find(account.getBranchId());
		    account.setAccountBranch(branch);
			
		    Customer customer = customerService.find(account.getCustomerId());
		    account.setAccountCustomer(customer);
			
			Account a = accountService.save(account);		
			
			return new ResponseEntity<Account>(a, HttpStatus.CREATED);
		}
	}
	
	@PutMapping("account/update")
	public ResponseEntity<?> updateAccount(@RequestParam long accountId, @RequestBody Account account) {
		System.out.println("updateAccount - account: "+account);
		Account a = accountService.find(accountId);
		
		if (a != null) {
			if (account.getAccountHolder() != null) {
				a.setAccountHolder(account.getAccountHolder());
			}
			
			if (account.getAccountType() != null) {
				a.setAccountType(account.getAccountType());
			}
			
			if (account.getAccountDateOpen() != null) {
				a.setAccountDateOpen(account.getAccountDateOpen());
			}
			
			if (account.getAccountHolder() != null) {
				a.setAccountHolder(account.getAccountHolder());
			}
			
			accountService.save(a);
			
			return new ResponseEntity<String>("Account record updated for id: " + accountId, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("There is no account with id: " + accountId, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("account/delete")
	public ResponseEntity<?> deleteAccount(@RequestParam long accountId) {
		System.out.println("deleteAccount - accountId: " + accountId);
		Account a = accountService.find(accountId);
		if (a != null) {
			accountService.delete(accountId);
			return new ResponseEntity<String>("Account record deleted for id: " + accountId,HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("There is no account with id: " + accountId, HttpStatus.NOT_FOUND);
		}
	}	
}
