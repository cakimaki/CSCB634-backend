package org.example.cscb634backend.controller;

import org.example.cscb634backend.entity.auth.MyUser;
import org.example.cscb634backend.repository.MyUserRepository;
import org.example.cscb634backend.service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {
	
	@Autowired
	private MyUserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@PostMapping("/register/user")
	public MyUser createUser(@RequestBody MyUser user){
		userService.saveUser(user);
		return user;
	}
}
