package com.example.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.app.DTO.LoginDto;
import com.example.app.DTO.UserDto;
import com.example.app.entities.User;
import com.example.app.repositorys.UserRepository;
import com.example.app.response.LoginResponse;

@Service
public class UserService {
   UserRepository userRepository;

   @Autowired
   private PasswordEncoder passwordEncoder;
   
   public UserService(UserRepository userRepository) {
	this.userRepository = userRepository;
   }

   public String addUser(UserDto userDto) {
	   User user=new User(
			   userDto.getId(),
			   userDto.getUsername(),
			   userDto.getEmail(),
			   this.passwordEncoder.encode(userDto.getPassword())
			   );
	   userRepository.save(user);
		return user.getUsername();
	} 
   public LoginResponse  loginUser(LoginDto loginDTO) {
       User user1 = userRepository.findByEmail(loginDTO.getEmail());
       if (user1 != null) {
           String password = loginDTO.getPassword();
           String encodedPassword = user1.getPassword();
           Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
           if (isPwdRight) {
               Optional<User> user = userRepository.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
               if (user.isPresent()) {
                   return new LoginResponse("Login Success", true);
               } else {
                   return new LoginResponse("Login Failed", false);
               }
           } else {

               return new LoginResponse("password Not Match", false);
           }
       }else {
           return new LoginResponse("Email not exits", false);
       }


   }
   
   public List<User> getAllUsers() {
	  return userRepository.findAll();
   }

   public User saveOneUser(User newUser) {
     return userRepository.save(newUser);
   }

   public User getOneUser(Long userId) {
	  return userRepository.findById(userId).orElse(null);
   }

   public User updateOneUser(Long userId, User newUser) {
	  Optional<User> user=userRepository.findById(userId);  
      if(user.isPresent()) {
    	User foundUser = user.get();
    	foundUser.setUsername(newUser.getUsername());
    	foundUser.setPassword(newUser.getPassword());
    	userRepository.save(foundUser);
    	return foundUser;
      }else
    	return null;
     }

   public void deleteOneUser(Long userId) {
	   userRepository.deleteById(userId);
	 }


    
   
}
