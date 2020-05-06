package com.kcs.jenkinsdemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/demoGet")
	public String demoGet() {
		return "Hello from Demo GET API";
	}
	
	@PostMapping("/demoPost") 
	public String demoPost() {
		return "Hello from Demo POST API";
	}
}
