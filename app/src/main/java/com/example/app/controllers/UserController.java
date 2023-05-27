package com.example.app.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.DTO.LoginDto;
import com.example.app.DTO.UserDto;
import com.example.app.entities.User;
import com.example.app.response.LoginResponse;
import com.example.app.services.UserService;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {
   
	private UserService userService;
   
    public UserController(UserService userService) {
    	this.userService= userService;
    }
    @PostMapping("/save")
    public String saveUser(@RequestBody UserDto userDto) {
    	String id=userService.addUser(userDto);
    	return id;
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {
    	LoginResponse loginMessage=userService.loginUser(loginDto);
    	return ResponseEntity.ok(loginMessage);
    }
    
    @GetMapping
    public List<User> getAllUsers(){
    	return userService.getAllUsers();
    }
   
    @PostMapping
    public User createUser(@RequestBody User newUser) {
    	return userService.saveOneUser(newUser);
    }
    
    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId) {
       return userService.getOneUser(userId);
    }
    
    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser) {
        return userService.updateOneUser(userId,newUser);
    }
    
    @DeleteMapping
    public void deleteOneUser(@PathVariable Long userId) {
    	userService.deleteOneUser(userId);
    }
}
