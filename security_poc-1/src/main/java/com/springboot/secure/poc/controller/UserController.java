package com.springboot.secure.poc.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.secure.poc.entity.User;
import com.springboot.secure.poc.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostConstruct
	public void initRolesAndUsers() {
		userService.initRolesAndUser();
	}
	
	@PostMapping({"/registerNewUser"})
	public User registerNewUser(@RequestBody User user) {
		return userService.registerNewUser(user);
	}
	
	@GetMapping({"/forAdmin"})
	@PreAuthorize("hasRole('Admin')")
	public String forAdmin() {
		return "This URL is only accessible to admin";
	}
	@GetMapping({"/forUser"})
	@PreAuthorize("hasRole('User')")

	public String forUser() {
		return "This URL is only accessible to the user";
	}
	
	
}
