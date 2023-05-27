package com.example.app.DTO;

import lombok.Data;

@Data
public class CommentDto {
	Long Id;
	String text;
	Long userId;
	Long postId;
	String userName;
	
}
