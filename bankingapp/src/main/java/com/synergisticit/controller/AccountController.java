package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Account;
import com.synergisticit.domain.Branch;
import com.synergisticit.domain.Customer;
import com.synergisticit.service.AccountService;
import com.synergisticit.service.BranchService;
import com.synergisticit.service.CustomerService;
import com.synergisticit.validation.AccountValidator;

import jakarta.validation.Valid;

@Controller
public class AccountController {
	
	@Autowired AccountService accountService;
	@Autowired BranchService branchService;
	@Autowired CustomerService customerService;
	@Autowired AccountValidator accountValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(accountValidator);
	}
	
	@RequestMapping("accountForm")
	public String accountForm(Account account, Model model) {
		System.out.println("============accountForm============");
		model.addAttribute("accounts", accountService.findAll());
		model.addAttribute("branches", branchService.findAll());
		model.addAttribute("customers", customerService.findAll());
		
		return "accountForm";
	}
	
	
	@RequestMapping("saveAccount")
	public ModelAndView saveAccount(@ModelAttribute @Valid Account account, BindingResult br) {
	//public ModelAndView saveAccount(@ModelAttribute @Valid Account account, BindingResult br, @RequestParam("accountBranch.branchId") Long branchId, @RequestParam("accountCustomer.customerId") Long customerId) {
		System.out.println("saveAccount - account: "+account);
		ModelAndView mav = new ModelAndView("accountForm");
		
		if (br.hasErrors()) {
			mav.addObject("accounts", accountService.findAll());
			mav.addObject("branches", branchService.findAll());
			mav.addObject("customers", customerService.findAll());		
			
			return mav;
		}
		
	    Branch branch = branchService.find(account.getBranchId());
	    account.setAccountBranch(branch);
		
	    Customer customer = customerService.find(account.getCustomerId());
	    account.setAccountCustomer(customer);
		
		accountService.save(account);
		mav.addObject("accounts", accountService.findAll());
		mav.addObject("branches", branchService.findAll());
		mav.addObject("customers", customerService.findAll());
		
		return mav;
	}
	
	@RequestMapping("updateAccount")
	public ModelAndView updateAccount(@ModelAttribute Account account, @ModelAttribute Branch branch, @ModelAttribute Customer customer) {
		System.out.println("updateAccount - account: "+account);
		ModelAndView mav = new ModelAndView("accountForm");
		mav.addObject("a", accountService.find(account.getAccountId()));
		mav.addObject("accounts", accountService.findAll());
		mav.addObject("b", branchService.find(branch.getBranchId()));
		mav.addObject("branches", branchService.findAll());
		mav.addObject("c", customerService.find(customer.getCustomerId()));
		mav.addObject("customers", customerService.findAll());
		
		return mav;
	}
	
	@RequestMapping("deleteAccount")
	public ModelAndView deleteAccount(Account account) {
		ModelAndView mav = new ModelAndView("accountForm");
		System.out.println("deleteAccount - account: "+account);
		accountService.delete(account.getAccountId());
		mav.addObject("accounts", accountService.findAll());
		mav.addObject("branches", branchService.findAll());
		mav.addObject("customers", customerService.findAll());
		
		return mav;
	}	
}
