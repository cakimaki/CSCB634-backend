package org.example.cscb634backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

	@GetMapping("/")
	public String index(){
		return "Connection has been established";
	}
}
