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

import com.synergisticit.domain.Branch;
import com.synergisticit.service.BranchService;
import com.synergisticit.validation.BranchValidator;

import jakarta.validation.Valid;

@Controller
public class BranchController {
	
	@Autowired BranchService branchService;
	@Autowired BranchValidator branchValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(branchValidator);
	}
	
	@RequestMapping("branchForm")
	public String branchForm(Branch branch, Model model) {
		System.out.println("============branchForm============");
		model.addAttribute("branches", branchService.findAll());
		
		return "branchForm";
	}
	
	
	@RequestMapping("saveBranch")
	public ModelAndView saveBranch(@ModelAttribute @Valid Branch branch, BindingResult br) {
	//public String saveBranch(@ModelAttribute @Valid Branch branch, BindingResult br) {
		System.out.println("saveBranch - branch: "+branch);
		ModelAndView mav = new ModelAndView("branchForm");
		mav.addObject("branches", branchService.findAll());
		
		if (br.hasErrors()) {	
			return mav;
		}
		
		branchService.save(branch);
		
		return mav;
		//return "redirect:/branchForm";
	}
	
	@RequestMapping("updateBranch")
	public ModelAndView updateBranch(@ModelAttribute Branch branch) {
		System.out.println("updateBranch - branch: "+branch);
		ModelAndView mav = new ModelAndView("branchForm");
		mav.addObject("b", branchService.find(branch.getBranchId()));
		mav.addObject("branches", branchService.findAll());
		
		return mav;
	}
	
	@RequestMapping("deleteBranch")
	public ModelAndView deleteBranch(Branch branch) {
		ModelAndView mav = new ModelAndView("branchForm");
		System.out.println("deleteBranch - branch: "+branch);
		branchService.delete(branch.getBranchId());
		mav.addObject("branches", branchService.findAll());
		
		return mav;
	}	
}
