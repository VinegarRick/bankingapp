package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.ArrayList;

import com.synergisticit.domain.User;
import com.synergisticit.domain.Role;
import com.synergisticit.service.RoleService;
import com.synergisticit.service.UserService;
import com.synergisticit.validation.UserValidator;

import jakarta.validation.Valid;

@Controller
public class UserController {
	
	@Autowired UserService userService;
	@Autowired RoleService roleService;
	@Autowired UserValidator userValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}
	
	@RequestMapping("userForm")
	public String userForm(User user, Model model) {
		System.out.println("============userForm============");
		model.addAttribute("users", userService.findAll());
		model.addAttribute("roles", roleService.findAll());
		
		return "userForm";
	}
	
	
	@RequestMapping("saveUser")
	public ModelAndView saveUser(@Valid @ModelAttribute User user, BindingResult br) {
		System.out.println("saveUser - user: "+user);
		ModelAndView mav = new ModelAndView("userForm");
		
		mav.addObject("users", userService.findAll());
		mav.addObject("roles", roleService.findAll());				
		
		if (br.hasErrors()) {
			return mav;
		}
		
		userService.save(user);
		
		mav = new ModelAndView("redirect:/userForm");
		mav.addObject("users", userService.findAll());
		mav.addObject("roles", roleService.findAll());		
		
		return mav;
	}
	
	@RequestMapping("updateUser")
	public ModelAndView updateUser(@ModelAttribute User user) {
		System.out.println("updateUser - user: "+user);
		ModelAndView mav = new ModelAndView("userForm");
		User existingUser = userService.find(user.getUserId());
		mav.addObject("u", userService.find(user.getUserId()));
		mav.addObject("users", userService.findAll());
		mav.addObject("roles", roleService.findAll());
		
		//List<Role> selectedRoles = new ArrayList<>(user.getRoles());
		List<Role> selectedRoles = new ArrayList<>(existingUser.getRoles());
		mav.addObject("selectedRoles", selectedRoles);
		
		return mav;
	}
	
	@RequestMapping("deleteUser")
	public ModelAndView deleteUser(User user) {
		ModelAndView mav = new ModelAndView("userForm");
		System.out.println("deleteUser - user: "+user);
		userService.delete(user.getUserId());
		mav.addObject("users", userService.findAll());
		mav.addObject("roles", roleService.findAll());
		
		return mav;
	}

}
