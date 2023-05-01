package com.carepay.assignment.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.carepay.assignment.domain.Comment;
import com.carepay.assignment.domain.Post;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	public Page<Comment> findAllByPost(Post post, Pageable pageable);
}
