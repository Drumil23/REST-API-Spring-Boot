package com.javadrumil.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javadrumil.demo.entity.User;
import com.javadrumil.demo.entity.UserModel;
import com.javadrumil.demo.service.UserService;

//import jakarta.validation.Valid;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/profile")
	public ResponseEntity<User> readUser(){
		return new ResponseEntity<User>(userService.readUser(),HttpStatus.OK);
	}
	
	@PutMapping("/profile")
	public ResponseEntity<User> updateUser(@RequestBody UserModel user){
		return new ResponseEntity<User>(userService.updateUser(user),HttpStatus.OK);
	}
	@DeleteMapping("/deactivate")
	public ResponseEntity<HttpStatus> deleteUser(){
		userService.deleteUser();
		return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		
		
	}

}
