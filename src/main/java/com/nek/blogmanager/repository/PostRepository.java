package com.nek.blogmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nek.blogmanager.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	
}
