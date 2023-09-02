package com.synergisticit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.synergisticit.domain.Branch;
import com.synergisticit.service.BranchService;
import com.synergisticit.validation.BranchValidator;

import jakarta.validation.Valid;

@RestController
public class BranchRestController {
	
	@Autowired BranchService branchService;
	@Autowired BranchValidator branchValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(branchValidator);
	}
	
	@GetMapping(value="branch/getAll")
	public ResponseEntity<List<Branch>> getAllBranches() {
		List<Branch> branchList = branchService.findAll();
		
		return new ResponseEntity<List<Branch>>(branchList, HttpStatus.OK);
	}
	
	@PostMapping(value="branch/create")
	public ResponseEntity<?> createBranch(@RequestBody @Valid Branch branch, BindingResult br) {
		System.out.println("saveBranch - branch: "+branch);
		
		int i = 1;
		if (br.hasFieldErrors()) {
			List<FieldError> fieldErrors = br.getFieldErrors();
			StringBuilder sb = new StringBuilder("");
			for (FieldError fieldError : fieldErrors) {
				sb = sb.append("\n"+i+".\"")
						.append(fieldError.getField() +"\": ")
						.append(fieldError.getDefaultMessage());
				i++;				
			}
			//return ResponseEntity.badRequest().body("Validation failed");
			return new ResponseEntity<StringBuilder>(sb, HttpStatus.ACCEPTED);
		} else {
			Branch b = branchService.save(branch);
			
			return new ResponseEntity<Branch>(b, HttpStatus.CREATED);
		}
	}
	
	@PutMapping(value="branch/update")
	public ResponseEntity<String> updateBranch(@RequestParam long branchId, Branch branch) {
		System.out.println("updateBranch - branch: "+branch);
		Branch b = branchService.find(branchId);
		
		if (b != null) {
			if (branch.getBranchName() != null) {
				b.setBranchName(branch.getBranchName());
			}
			
			if (branch.getBranchAddress() != null) {
				if (branch.getBranchAddress().getAddressLine1() != null) {
					b.getBranchAddress().setAddressLine1(branch.getBranchAddress().getAddressLine1());
				}
				
				if (branch.getBranchAddress().getAddressLine2() != null) {
					b.getBranchAddress().setAddressLine2(branch.getBranchAddress().getAddressLine2());
				}
				
				if (branch.getBranchAddress().getCity() != null) {
					b.getBranchAddress().setCity(branch.getBranchAddress().getCity());
				}
				
				if (branch.getBranchAddress().getState() != null) {
					b.getBranchAddress().setState(branch.getBranchAddress().getState());
				}
				
				if (branch.getBranchAddress().getCountry() != null) {
					b.getBranchAddress().setCountry(branch.getBranchAddress().getCountry());
				}
				
				if (branch.getBranchAddress().getZipcode() != null) {
					b.getBranchAddress().setZipcode(branch.getBranchAddress().getZipcode());
				}
			}
			
			branchService.save(b);
			
			return new ResponseEntity<String>("Branch record updated for id: " + branchId, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("There is no branch with id: " + branchId, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("branch/delete")
	public ResponseEntity<String> deleteBranch(@RequestParam long branchId) {
		System.out.println("deleteBranch - branch: " + branchId);
		Branch b = branchService.find(branchId);
		
		if (b != null) {
			branchService.delete(branchId);
			
			return new ResponseEntity<String>("Branch record deleted for id: " + branchId, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("There is no branch with id: " + branchId, HttpStatus.NOT_FOUND);
		}
	}
	
}
