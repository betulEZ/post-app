package com.example.app.controllers;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.app.DTO.CommentDto;
import com.example.app.entities.Comment;
import com.example.app.services.CommentService;
@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/comments")
public class CommentController {
	@Autowired
	private ModelMapper modelMapper;
	
	private CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}
	
	@GetMapping
	public List<CommentDto> getAllComments(@RequestParam Optional<Long> userId,@RequestParam Optional<Long> postId){
		List<Comment> comments=commentService.getAllComments(userId, postId);
			return comments.stream().map(this::convertToDto).collect(Collectors.toList());
	}
	@GetMapping("/{commentId}")
	public Comment getOneComment(@PathVariable Long commentId) {
		return commentService.getOneCommentById(commentId);
	}
	@PostMapping
	public Comment createOneComment(@RequestBody CommentDto commentDto) {
		return commentService.createOneComment(commentDto);
	}
	
	@DeleteMapping("/{commentId}")
	public void deleteOneComment(@PathVariable Long commentId) {
		commentService.deleteOneCommentById(commentId);
		
	}
	private CommentDto convertToDto(Comment comment) {
	    CommentDto CommentDto = modelMapper.map(comment, CommentDto.class);
	    return CommentDto;
	}
	
}
