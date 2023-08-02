package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.BankTransaction;
import com.synergisticit.service.BankTransactionService;

@Controller
public class BankTransactionController {

	@Autowired
	BankTransactionService bankTransactionService;
	
	@RequestMapping("bankTransactionForm")
	public String bankTransactionForm(BankTransaction bankTransaction, Model model) {
		System.out.println("============bankTransactionForm============");
		model.addAttribute("bankTransactions", bankTransactionService.findAll());
		return "bankTransactionForm";
	}
	
	
	@RequestMapping("saveBankTransaction")
	public ModelAndView saveBankTransaction(@ModelAttribute BankTransaction bankTransaction) {
		System.out.println("saveBankTransaction - bankTransaction: "+bankTransaction);
		ModelAndView mav = new ModelAndView("bankTransactionForm");
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
