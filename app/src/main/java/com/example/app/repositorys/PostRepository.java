package com.example.app.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

	List<Post> findByUserId(Long userId);

}
