package com.readymade.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class User {
	private Integer id;

	@NotBlank
	@Email(message="this is invalid email")
	private String email;
	
	@Size(min = 6, max = 12, message="should be something")
	private String password;
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	public User() {
	}
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
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
}
