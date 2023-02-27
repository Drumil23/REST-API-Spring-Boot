package com.javadrumil.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javadrumil.demo.entity.AuthModel;
import com.javadrumil.demo.entity.JwtResponse;
import com.javadrumil.demo.entity.User;
import com.javadrumil.demo.entity.UserModel;
import com.javadrumil.demo.security.CustomUserDetailsService;
import com.javadrumil.demo.service.UserService;
import com.javadrumil.demo.util.JwtTokenUtil;

//import jakarta.validation.Valid;

@RestController
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody AuthModel authModel) throws Exception{
		
		
		authenticate(authModel.getEmail(), authModel.getPassword());
		
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		generate jwt token
		
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authModel.getEmail());
		
		final String token = jwtTokenUtil.generateToken(userDetails);
		
		System.out.println("Token Generated = " + token);
		
		return new ResponseEntity<JwtResponse>(new JwtResponse(token),HttpStatus.OK);
	}
	
	private void authenticate(String email, String password) throws Exception {
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
			
		} catch (DisabledException e) {
			throw new Exception("User disabled!");
		} catch (BadCredentialsException e) {
			throw new Exception("Bad Credential!");
		}
		
	}

	@PostMapping("/register")
	public ResponseEntity<User> save(@Valid @RequestBody UserModel user){
		return new ResponseEntity<User>(userService.createUser(user),HttpStatus.CREATED);
	}
}
