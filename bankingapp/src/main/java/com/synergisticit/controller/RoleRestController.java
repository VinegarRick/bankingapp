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

import com.synergisticit.domain.Account;
import com.synergisticit.domain.Branch;
import com.synergisticit.domain.Customer;
import com.synergisticit.domain.Role;
import com.synergisticit.service.RoleService;
import com.synergisticit.validation.RoleValidator;

import jakarta.validation.Valid;

@RestController
public class RoleRestController {

	@Autowired RoleService roleService;
	@Autowired RoleValidator roleValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(roleValidator);
	}
	
	@GetMapping(value="role/getAll")
	public ResponseEntity<List<Role>> getAllRoles() {
		List<Role> roleList = roleService.findAll();
		
		return new ResponseEntity<List<Role>>(roleList, HttpStatus.OK);
	}
	
	@PostMapping(value="role/create")
	public ResponseEntity<?> createUser(@RequestBody @Valid Role role, BindingResult br) {
		System.out.println("saveRole - role: " + role);
		
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
			return new ResponseEntity<StringBuilder>(sb, HttpStatus.ACCEPTED);
		} else {			
			Role r = roleService.save(role);		
			
			return new ResponseEntity<Role>(r, HttpStatus.CREATED);
		}		
	}
	
	@PutMapping("role/update")
	public ResponseEntity<String> updateRole(@RequestParam long roleId, @RequestBody Role role) {
		System.out.println("updateRole - role: " + role);
		Role r = roleService.find(roleId);
		
		if (r != null) {
			if (role.getRoleName() != null) {
				r.setRoleName(role.getRoleName());
			}
			
			roleService.save(r);
			
			return new ResponseEntity<String>("Role record updated for id: " + roleId, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("There is no role with id: " + roleId, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("role/delete")
	public ResponseEntity<String> deleteRole(@RequestParam long roleId) {
		System.out.println("deleteRole - roleId: " + roleId);
		Role r = roleService.find(roleId);
		
		if (r != null) {
			roleService.delete(roleId);
			
			return new ResponseEntity<String>("Role record deleted for id: " + roleId, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("There is no role with id: " + roleId, HttpStatus.OK);
		}
	}
	
}
