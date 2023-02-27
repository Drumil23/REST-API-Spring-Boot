package com.javadrumil.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtResponse {
	private String jwtToken = "ini";

	
	
	public JwtResponse(String token) {
		this.jwtToken = token;

	}



	public String getJwtToken() { return jwtToken; }
	 
}
