package org.example.cscb634backend.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

	@GetMapping("/")
	public String index(){
		return "Connection has been established";
	}
	
	@GetMapping("/home")
	public String handleHomePage(){
		return "home";
	}
	@GetMapping("/user/home")
	public String handleUserHome(){
		return "user";
	}
	@GetMapping("/admin/home")
	public String handleAdminHome(){
		return "home_admin";
	}
}
