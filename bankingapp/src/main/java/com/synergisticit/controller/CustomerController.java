package com.synergisticit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Customer;
import com.synergisticit.domain.Role;
import com.synergisticit.domain.User;
import com.synergisticit.service.CustomerService;
import com.synergisticit.service.UserService;

@Controller
public class CustomerController {
	
	@Autowired CustomerService customerService;
	@Autowired UserService userService;
	
	@RequestMapping("customerForm")
	public String customerForm(Customer customer, Model model) {
		System.out.println("============customerForm============");
		model.addAttribute("customers", customerService.findAll());
		
		return "customerForm";
	}
	
	
	@RequestMapping("saveCustomer")
	public ModelAndView saveCustomer(@ModelAttribute Customer customer, @RequestParam("user.userId") Long userId) {
		System.out.println("saveCustomer - customer: "+customer);
		ModelAndView mav = new ModelAndView("customerForm");
		
		User user = userService.find(userId);
		customer.setUser(user);
		
		customerService.save(customer);
		mav.addObject("customers", customerService.findAll());
		
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
