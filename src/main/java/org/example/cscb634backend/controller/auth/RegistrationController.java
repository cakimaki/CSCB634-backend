package org.example.cscb634backend.controller.auth;

import jakarta.validation.Valid;
import org.example.cscb634backend.dto.auth.MyUserDto;
import org.example.cscb634backend.entity.auth.MyUser;
import org.example.cscb634backend.service.auth.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user/api ")
public class RegistrationController {
	
	@Autowired
	private MyUserService userService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@PostMapping("/register")
	public ResponseEntity<MyUser> createUser(@Valid @RequestBody  MyUserDto dto){
		MyUser registeredUser = userService.registerUser(dto);
		
		return ResponseEntity.ok(registeredUser);
	}
}
