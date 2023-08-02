package com.synergisticit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Branch;
import com.synergisticit.service.BranchService;

@Controller
public class BranchController {
	
	@Autowired
	BranchService branchService;
	
	@RequestMapping("branchForm")
	public String branchForm(Branch branch, Model model) {
		System.out.println("============branchForm============");
		model.addAttribute("branchs", branchService.findAll());
		
		return "branchForm";
	}
	
	
	@RequestMapping("saveBranch")
	public ModelAndView saveBranch(@ModelAttribute Branch branch) {
		System.out.println("saveBranch - branch: "+branch);
		ModelAndView mav = new ModelAndView("branchForm");
		branchService.save(branch);
		mav.addObject("branchs", branchService.findAll());
		
		return mav;
	}
	
	@RequestMapping("updateBranch")
	public ModelAndView updateBranch(@ModelAttribute Branch branch) {
		System.out.println("updateBranch - branch: "+branch);
		ModelAndView mav = new ModelAndView("branchForm");
		mav.addObject("b", branchService.find(branch.getBranchId()));
		mav.addObject("branchs", branchService.findAll());
		
		return mav;
	}
	
	@RequestMapping("deleteBranch")
	public ModelAndView deleteBranch(Branch branch) {
		ModelAndView mav = new ModelAndView("branchForm");
		System.out.println("deleteBranch - branch: "+branch);
		branchService.delete(branch.getBranchId());
		mav.addObject("branchs", branchService.findAll());
		
		return mav;
	}	
}
