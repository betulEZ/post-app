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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.DTO.PostDto;
import com.example.app.entities.Post;
import com.example.app.services.PostService;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/posts")
public class PostController {
	@Autowired
	private ModelMapper modelMapper;

	private PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}
	
	@GetMapping
	public List<PostDto> getAllPosts(@RequestParam Optional<Long> userId){
		 List<Post> posts = postService.getAllPost(userId);
	        return posts.stream()
	          .map(this::convertToDto)
	          .collect(Collectors.toList());
	}
	@PostMapping
	public Post createOnePost(@RequestBody PostDto newPostDto) {
		return postService.createOnePost(newPostDto);
	}
	@GetMapping("/{postId}")
	public Post getOnePost(@PathVariable Long postId) {
		return postService.getOnePostById(postId);
	}
	@PutMapping("/{postId}")
	public Post updateOnePost(@PathVariable Long postId, @RequestBody PostDto postDto) {
		return postService.updateOnePostById(postId, postDto);
	}
	@DeleteMapping("/{postId}") 
	public void deleteOnePost(@PathVariable Long postId) {
		postService.deleteOnePostById(postId);
	}
	private PostDto convertToDto(Post post) {
	    PostDto postDto = modelMapper.map(post, PostDto.class);
	    return postDto;
	}

}
