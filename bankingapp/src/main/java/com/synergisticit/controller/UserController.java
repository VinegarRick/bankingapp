package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.User;
import com.synergisticit.service.UserService;

@Controller
public class UserController {
	
	@Autowired UserService userService;
	
	@RequestMapping("userForm")
	public String userForm(User user, Model model) {
		System.out.println("============userForm============");
		model.addAttribute("users", userService.findAll());
		return "userForm";
	}
	
	
	@RequestMapping("saveUser")
	public ModelAndView saveUser(@ModelAttribute User user) {
		System.out.println("saveUser - user: "+user);
		ModelAndView mav = new ModelAndView("userForm");
		userService.save(user);
		mav.addObject("users", userService.findAll());
		return mav;
	}
	
	@RequestMapping("updateUser")
	public ModelAndView updateUser(@ModelAttribute User user) {
		System.out.println("updateUser - user: "+user);
		ModelAndView mav = new ModelAndView("userForm");
		mav.addObject("u", userService.find(user.getUserId()));
		mav.addObject("users", userService.findAll());
		return mav;
	}
	
	@RequestMapping("deleteUser")
	public ModelAndView deleteUser(User user) {
		ModelAndView mav = new ModelAndView("userForm");
		System.out.println("deleteUser - user: "+user);
		userService.delete(user.getUserId());
		mav.addObject("users", userService.findAll());
		return mav;
	}

}
