package com.nek.blogmanager.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nek.blogmanager.domain.Comment;

import jakarta.validation.Valid;

public interface CommentService {
	Comment createComment(Long postId, @Valid Comment comment);

    Page<Comment> getComments(Long postId, final Pageable pageable);

    Comment getCommentDetails(Long postId, Long id);

    void deleteComment(Long postId, Long id);
}
