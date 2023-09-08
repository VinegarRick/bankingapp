package com.synergisticit.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.synergisticit.domain.Customer;
import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;
import com.synergisticit.service.CustomerService;
import com.synergisticit.service.UserService;
import com.synergisticit.validation.CustomerValidator;

import jakarta.validation.Valid;

@Controller
public class CustomerController {
	
	@Autowired CustomerService customerService;
	@Autowired UserService userService;
	@Autowired CustomerValidator customerValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(customerValidator);
	}
	
	@RequestMapping("customerForm")
	public String customerForm(Customer customer, Model model) {
		System.out.println("============customerForm============");
		model.addAttribute("customers", customerService.findAll());
		
		return "customerForm";
	}
	
	
	@RequestMapping("saveCustomer")
	public ModelAndView saveCustomer(@ModelAttribute @Valid Customer customer, BindingResult br) {
		System.out.println("saveCustomer - customer: "+customer);
		ModelAndView mav = new ModelAndView("customerForm");
		mav.addObject("customers", customerService.findAll());
		
		if (br.hasErrors()) {
			return mav;
		}
		
		User user = userService.find(customer.getUserId());
		customer.setUser(user);
		
		mav = new ModelAndView("redirect:/customerForm");
		
		mav.addObject("customers", customerService.findAll());
		customerService.save(customer);
		
		return mav;
	}
	
	@RequestMapping("updateCustomer")
	public ModelAndView updateCustomer(@ModelAttribute Customer customer, @ModelAttribute User user) {
		System.out.println("updateCustomer - customer: "+customer);
		ModelAndView mav = new ModelAndView("customerForm");
		mav.addObject("c", customerService.find(customer.getCustomerId()));
		mav.addObject("customers", customerService.findAll());
		mav.addObject("u", userService.find(user.getUserId()));
		mav.addObject("users", userService.findAll());
		
		return mav;
	}
	
	@RequestMapping("deleteCustomer")
	public ModelAndView deleteCustomer(Customer customer) {
		ModelAndView mav = new ModelAndView("customerForm");
		System.out.println("deleteCustomer - customer: "+customer);
		customerService.delete(customer.getCustomerId());
		mav.addObject("customers", customerService.findAll());
		mav.addObject("users", userService.findAll());
		
		return mav;
	}	
}
