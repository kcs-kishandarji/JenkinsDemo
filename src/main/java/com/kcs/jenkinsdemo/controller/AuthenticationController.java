package com.kcs.jenkinsdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kcs.jenkinsdemo.dto.ResponseVO;
import com.kcs.jenkinsdemo.service.AuthenticationService;

@RestController
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;
	
	@GetMapping("/authenticate")
	public ResponseVO<String> authenticate(@RequestParam("username") String username, @RequestParam("password") String password) {
		return authenticationService.authenticateAndGenerateToken(username, password);
	}
}
