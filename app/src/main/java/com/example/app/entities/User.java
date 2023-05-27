package com.example.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="user")
@Data
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class User {
	@Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String username;
	String email;
	String password;

	public User(Long id, String username, String email,String password ) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		
	}
	public User() {
		
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", Username=" + username + ", password=" + password + ", email=" + email + "]";
	}
	
	
	
	

}
