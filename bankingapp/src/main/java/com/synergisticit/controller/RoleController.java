package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Role;
import com.synergisticit.service.RoleService;

@Controller
public class RoleController {

	@Autowired
	RoleService roleService;
	
	@RequestMapping("roleForm")
	public String roleForm(Role role, Model model) {
		System.out.println("==========roleForm===========");
		model.addAttribute("roles", roleService.findAll());
		
		return "roleForm";
	}
	
	@RequestMapping("saveRole")
	public ModelAndView saveRole(@ModelAttribute Role role) {
		ModelAndView mav = new ModelAndView("roleForm");
		roleService.save(role);
		mav.addObject("roles", roleService.findAll());
		
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
