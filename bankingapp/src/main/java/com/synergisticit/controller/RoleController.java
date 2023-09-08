package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Role;
import com.synergisticit.service.RoleService;
import com.synergisticit.validation.RoleValidator;

import jakarta.validation.Valid;

@Controller
public class RoleController {

	@Autowired RoleService roleService;
	@Autowired RoleValidator roleValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(roleValidator);
	}
	
	@RequestMapping("roleForm")
	public String roleForm(Role role, Model model) {
		System.out.println("==========roleForm===========");
		model.addAttribute("roles", roleService.findAll());
		
		return "roleForm";
	}
	
	@RequestMapping("saveRole")
	public ModelAndView saveRole(@ModelAttribute @Valid Role role, BindingResult br) {
		ModelAndView mav = new ModelAndView("roleForm");
		mav.addObject("roles", roleService.findAll());
		
		if (br.hasErrors()) {
			return mav;
		}
		
		mav = new ModelAndView("redirect:/customerForm");
		mav.addObject("roles", roleService.findAll());
		
		roleService.save(role);
		
		return mav;
	}
	
	@RequestMapping("updateRole")
	public ModelAndView updateRole(@ModelAttribute Role role) {
		ModelAndView mav = new ModelAndView("roleForm");
		mav.addObject("r", roleService.find(role.getRoleId()));
		mav.addObject("roles", roleService.findAll());
		
		return mav;
	}
	
	@RequestMapping("deleteRole")
	public ModelAndView deleteRole(Role role) {
		ModelAndView mav = new ModelAndView("roleForm");
		roleService.delete(role.getRoleId());
		mav.addObject("roles", roleService.findAll());
		
		return mav;
	}
	
}
