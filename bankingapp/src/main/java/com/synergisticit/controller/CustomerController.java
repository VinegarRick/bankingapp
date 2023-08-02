package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Customer;
import com.synergisticit.service.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping("customerForm")
	public String customerForm(Customer customer, Model model) {
		System.out.println("============customerForm============");
		model.addAttribute("customers", customerService.findAll());
		return "customerForm";
	}
	
	
	@RequestMapping("saveCustomer")
	public ModelAndView saveCustomer(@ModelAttribute Customer customer) {
		System.out.println("saveCustomer - customer: "+customer);
		ModelAndView mav = new ModelAndView("customerForm");
		customerService.save(customer);
		mav.addObject("customers", customerService.findAll());
		return mav;
	}
	
	@RequestMapping("updateCustomer")
	public ModelAndView updateCustomer(@ModelAttribute Customer customer) {
		System.out.println("updateCustomer - customer: "+customer);
		ModelAndView mav = new ModelAndView("customerForm");
		mav.addObject("c", customerService.find(customer.getCustomerId()));
		mav.addObject("customers", customerService.findAll());
		return mav;
	}
	
	@RequestMapping("deleteCustomer")
	public ModelAndView deleteCustomer(Customer customer) {
		ModelAndView mav = new ModelAndView("customerForm");
		System.out.println("deleteCustomer - customer: "+customer);
		customerService.delete(customer.getCustomerId());
		mav.addObject("customers", customerService.findAll());
		return mav;
	}	
}
