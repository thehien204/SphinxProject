package com.sphinx.project.model;

import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
@Data
public class JwtResponse {
	private final String token;
//	private final String user;
	private final String message;
	private final int status;

	public JwtResponse(String token, String message, int status) {
		this.token = token;
		this.message = message;
		this.status = status;
	}



	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
