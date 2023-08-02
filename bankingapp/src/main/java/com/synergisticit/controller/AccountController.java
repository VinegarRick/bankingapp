package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Account;
import com.synergisticit.service.AccountService;

@Controller
public class AccountController {
	
	@Autowired
	AccountService accountService;
	
	@RequestMapping("accountForm")
	public String accountForm(Account account, Model model) {
		System.out.println("============accountForm============");
		model.addAttribute("accounts", accountService.findAll());
		return "accountForm";
	}
	
	
	@RequestMapping("saveAccount")
	public ModelAndView saveAccount(@ModelAttribute Account account) {
		System.out.println("saveAccount - account: "+account);
		ModelAndView mav = new ModelAndView("accountForm");
		accountService.save(account);
		mav.addObject("accounts", accountService.findAll());
		return mav;
	}
	
	@RequestMapping("updateAccount")
	public ModelAndView updateAccount(@ModelAttribute Account account) {
		System.out.println("updateAccount - account: "+account);
		ModelAndView mav = new ModelAndView("accountForm");
		mav.addObject("a", accountService.find(account.getAccountId()));
		mav.addObject("accounts", accountService.findAll());
		return mav;
	}
	
	@RequestMapping("deleteAccount")
	public ModelAndView deleteAccount(Account account) {
		ModelAndView mav = new ModelAndView("accountForm");
		System.out.println("deleteAccount - account: "+account);
		accountService.delete(account.getAccountId());
		mav.addObject("accounts", accountService.findAll());
		return mav;
	}	
}
