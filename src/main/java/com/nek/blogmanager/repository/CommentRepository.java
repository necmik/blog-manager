package com.nek.blogmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.nek.blogmanager.domain.Comment;
import com.nek.blogmanager.domain.Post;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	public Page<Comment> findAllByPost(Post post, Pageable pageable);
}
