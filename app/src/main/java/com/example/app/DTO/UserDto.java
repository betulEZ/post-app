package com.example.app.DTO;

import lombok.Data;

@Data
public class UserDto {
	Long id;
	String username;
	String email;
	String password;
	
	public UserDto(Long id, String username, String email,String password) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
	
	}
	public UserDto() {
		
	}
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", Username=" + username + ", password=" + password + ", email=" + email + "]";
	}
}
