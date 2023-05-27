package com.example.app.DTO;

import lombok.Data;

@Data
public class LoginDto {
	String email;
	String password;
	public LoginDto(String email, String password) {
		this.email = email;
		this.password = password;
	}
	public LoginDto() {}
	@Override
	public String toString() {
		return "LoginDto [email=" + email + ", password=" + password + "]";
	}
	
	
}
