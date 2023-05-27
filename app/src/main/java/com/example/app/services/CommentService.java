package com.example.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.app.DTO.CommentDto;
import com.example.app.entities.Comment;
import com.example.app.entities.Post;
import com.example.app.entities.User;
import com.example.app.repositorys.CommentRepository;

@Service
public class CommentService {
    
	private CommentRepository commentRepository;
	private UserService userService;
	private PostService postService;

	public CommentService(CommentRepository commentRepository,UserService userService,  PostService postService) {
		this.commentRepository = commentRepository;
		this.userService = userService;
		this.postService = postService;
	}

	public List<Comment> getAllComments(Optional<Long> userId, Optional<Long> postId) {
		if(userId.isPresent() && postId.isPresent()) {
			return commentRepository.findByUserIdAndPostId(userId.get(),postId.get());
		}else if(userId.isPresent()) {
			return commentRepository.findByUserId(userId.get());
		}else if(postId.isPresent()) {
			return commentRepository.findByPostId(postId.get());
		}else
			return commentRepository.findAll();
	}

	public Comment getOneCommentById(Long commentId) {
		return commentRepository.findById(commentId).orElse(null);
	}

	public Comment createOneComment(CommentDto commentDto) {
		User user = userService.getOneUser(commentDto.getUserId());
		Post post =postService.getOnePostById(commentDto.getPostId());
		if( user != null && post !=null) {
			Comment commentToSave = new Comment();
			commentToSave.setId(commentDto.getId());
			commentToSave.setPost(post);
			commentToSave.setUser(user);
			commentToSave.setText(commentDto.getText());
			return commentRepository.save(commentToSave);
		}else 
		   return null;
	}

	public void deleteOneCommentById(Long commentId) {
		commentRepository.deleteById(commentId);
		
	}
	
	
}
