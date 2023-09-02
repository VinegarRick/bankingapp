package com.synergisticit.controller;

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
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.synergisticit.domain.User;
import com.synergisticit.domain.Role;
import com.synergisticit.service.RoleService;
import com.synergisticit.service.UserService;
import com.synergisticit.validation.UserValidator;

import jakarta.validation.Valid;

@RestController
public class UserRestController {
	
	@Autowired UserService userService;
	@Autowired RoleService roleService;
	@Autowired UserValidator userValidator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(userValidator);
	}
	
	@GetMapping(value="user/getAll")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> userList = userService.findAll();
		
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}

	@PostMapping(value="user/create")
	public ResponseEntity<?> createUser(@RequestBody @Valid User user, BindingResult br) {
		System.out.println("saveUser - user: "+user);
		System.out.println("user role: " + user.getRoles());
		
		int i = 1;
		if (br.hasFieldErrors()) {
			List<FieldError> fieldErrors = br.getFieldErrors();/*
			StringBuilder sb = new StringBuilder("");
			for (FieldError fieldError : fieldErrors) {
				sb = sb.append("\n"+i+".\"")
						.append(fieldError.getField() +"\": ")
						.append(fieldError.getDefaultMessage());
				i++;
			}
			return new ResponseEntity<StringBuilder>(sb, HttpStatus.ACCEPTED);*/
			
		    List<String> errorMessages = new ArrayList<>();
		    for (FieldError fieldError : fieldErrors) {
		        errorMessages.add(fieldError.getDefaultMessage());
		    }
		    
		    Map<String, Object> response = new HashMap<>();
		    response.put("status", HttpStatus.BAD_REQUEST.value());
		    response.put("errors", errorMessages);
		    
		    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);			
		} else {
			
			User u = userService.save(user);
			
			return new ResponseEntity<User>(u, HttpStatus.CREATED);
		}
		
	}
	
	@PutMapping("user/update")
	public ResponseEntity<?> updateUser(@RequestParam long userId, @RequestBody User user) {
		System.out.println("updateUser - user: "+user);
		User u = userService.find(userId);	
		
		if (u != null) {
			if (user.getUsername() != null) {
				u.setUsername(user.getUsername());
			}
			
			if (user.getPassword() != null) {
				u.setPassword(user.getPassword());
			}
			
			userService.save(u);
			
			return new ResponseEntity<String>("User record updated for id: " + userId, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("There is no user with id: " + userId, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("user/delete")
	public ResponseEntity<?> deleteUser(@RequestParam long userId) {
		System.out.println("deleteUser - user: " + userId);
		User u = userService.find(userId);
		
		if (u != null) {
			userService.delete(userId);
			
			return new ResponseEntity<String>("User record deleted for id: " + userId, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("There is no account with id: " + userId, HttpStatus.NOT_FOUND);
		}
	}

}
