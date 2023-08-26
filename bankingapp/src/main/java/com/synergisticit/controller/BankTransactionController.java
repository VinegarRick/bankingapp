package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.BankTransaction;
import com.synergisticit.service.AccountService;
import com.synergisticit.service.BankTransactionService;
import com.synergisticit.validation.BankTransactionValidator;

import jakarta.validation.Valid;

@Controller
public class BankTransactionController {

	@Autowired BankTransactionService bankTransactionService;
	@Autowired AccountService accountService;
	@Autowired BankTransactionValidator bankTransactionValidator;
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(bankTransactionValidator);
		
	}
	
	@RequestMapping("bankTransactionForm")
	public String bankTransactionForm(BankTransaction bankTransaction, Model model) {
		System.out.println("============bankTransactionForm============");
		model.addAttribute("bankTransactions", bankTransactionService.findAll());
		
		return "bankTransactionForm";
	}
	
	
	@RequestMapping("saveBankTransaction")
	public ModelAndView saveBankTransaction(@ModelAttribute @Valid BankTransaction bankTransaction, BindingResult br) {
		System.out.println("saveBankTransaction - bankTransaction: "+bankTransaction);
		ModelAndView mav = new ModelAndView("bankTransactionForm");
				
		if (br.hasErrors()) {
			mav.addObject("bankTransactions", bankTransactionService.findAll());
			return mav;
		}
		
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
		
		/*Long toAccountId = bankTransaction.getBankTransactionToAccount();
		if (toAccountId != null && toAccountId > 0) {
			Account toAccount = accountService.find(toAccountId);
			toAccount.setAccountBalance(toAccount.getAccountBalance() + transactionAmount);
		}
		
		Long fromAccountId = bankTransaction.getBankTransactionFromAccount();
		if (fromAccountId != null && fromAccountId > 0) {
			Account fromAccount = accountService.find(fromAccountId);
			fromAccount.setAccountBalance(fromAccount.getAccountBalance() - transactionAmount);
		}*/
		
		bankTransactionService.save(bankTransaction);
		mav.addObject("bankTransactions", bankTransactionService.findAll());
		
		return mav;
	}
	
	@RequestMapping("updateBankTransaction")
	public ModelAndView updateBankTransaction(@ModelAttribute BankTransaction bankTransaction) {
		System.out.println("updateBankTransaction - bankTransaction: "+bankTransaction);
		ModelAndView mav = new ModelAndView("bankTransactionForm");
		mav.addObject("bt", bankTransactionService.find(bankTransaction.getBankTransactionId()));
		mav.addObject("bankTransactions", bankTransactionService.findAll());
		
		return mav;
	}
	
	@RequestMapping("deleteBankTransaction")
	public ModelAndView deleteBankTransaction(BankTransaction bankTransaction) {
		ModelAndView mav = new ModelAndView("bankTransactionForm");
		System.out.println("deleteBankTransaction - bankTransaction: "+bankTransaction);
		bankTransactionService.delete(bankTransaction.getBankTransactionId());
		mav.addObject("bankTransactions", bankTransactionService.findAll());
		
		return mav;
	}	
	
}
