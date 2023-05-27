package com.example.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.app.DTO.PostDto;
import com.example.app.entities.Post;
import com.example.app.entities.User;
import com.example.app.repositorys.PostRepository;

@Service
public class PostService {
	
	private PostRepository postRepository;
	private UserService userService;

	public PostService(PostRepository postRepository,UserService userService) {
		this.postRepository = postRepository;
		this.userService=userService;
	}

	public List<Post> getAllPost(Optional<Long> userId) {
		if(userId.isPresent())
			return postRepository.findByUserId(userId.get());
		return postRepository.findAll();
	}

	public Post getOnePostById(Long postId) {
		return postRepository.findById(postId).orElse(null);
	}

	public Post createOnePost(PostDto newPostDto) {
		User user = userService.getOneUser(newPostDto.getUserId());
		if(user==null)
			return null;
		Post toSave =new Post();
		toSave.setId(newPostDto.getId());
		toSave.setText(newPostDto.getText());
		toSave.setTitle(newPostDto.getTitle());
		toSave.setUser(user);
		return postRepository.save(toSave);
	}

	public Post updateOnePostById(Long postId,PostDto postDto) {
	   Optional <Post> post = postRepository.findById(postId);
	   if(post.isPresent()) {
		   Post toUpdate= post.get();
		   toUpdate.setText(postDto.getText());
		   toUpdate.setTitle(postDto.getTitle());
		   postRepository.save(toUpdate);
		   return toUpdate;
	   }
		   return null;
	   
	}

	public void deleteOnePostById(Long postId) {
		postRepository.deleteById(postId);
	}

}
