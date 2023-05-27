package com.example.app.DTO;

import lombok.Data;

@Data
public class PostDto {
   
	Long Id;
	String text;
	String title;
	Long userId;
	String userName;
}
