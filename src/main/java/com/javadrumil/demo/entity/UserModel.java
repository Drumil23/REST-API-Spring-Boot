package com.javadrumil.demo.entity;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserModel {
	
	@NotBlank(message = "Name shouldn't be empty!")
	private String name;
	
	@NotNull(message = "Email shouldn't be empty!")
	@Email(message = "Enter valid email!")
	private String email;
	
	@NotNull(message = "Password shouldn't be empty!")
	@Size(min = 5, message = "Set strong password!")
	private String password;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	private Long age = 0L;
}
