package com.carepay.assignment.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.carepay.assignment.payload.CommentDetails;
import com.carepay.assignment.payload.CommentInfo;
import com.carepay.assignment.payload.CreateCommentRequest;

public interface CommentService {
	CommentDetails createComment(Long postId, @Valid CreateCommentRequest request);

    Page<CommentInfo> getComments(Long postId, final Pageable pageable);

    CommentDetails getCommentDetails(Long postId, Long id);

    void deleteComment(Long postId, Long id);
}
