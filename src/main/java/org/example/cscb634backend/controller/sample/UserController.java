package org.example.cscb634backend.controller.sample;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {
	
	@GetMapping("/")
	public String helloUserController(){
		return "User access level";
	}
	
}