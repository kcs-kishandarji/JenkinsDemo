package com.kcs.jenkinsdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kcs.jenkinsdemo.dto.ResponseVO;
import com.kcs.jenkinsdemo.dto.UserVO;
import com.kcs.jenkinsdemo.service.UserService;

@BaseRestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public ResponseVO<List<UserVO>> findAll() {
		return userService.findAll();
	}
	
	@GetMapping("/user/{id}")
	public ResponseVO<UserVO> findById(@PathVariable("id") Long id) {
		return userService.findById(id.longValue());
	}	
	
	@PostMapping("/user")
	public ResponseVO<Long> save(@RequestBody UserVO vo) {
		return userService.save(vo); 
	}
	
	@PutMapping("/user")
	public ResponseVO<Long> update(@RequestBody UserVO vo) {
		return userService.update(vo); 
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseVO<Void> delete(@PathVariable("id") Long id) {
		return userService.delete(id); 
	}
}
