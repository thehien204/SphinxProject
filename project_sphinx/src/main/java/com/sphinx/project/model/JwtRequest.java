package com.sphinx.project.model;

import lombok.Data;

@Data
public class JwtRequest {
	
	private String username;
	private String password;	
	
	public JwtRequest() { }

	public JwtRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}
}
