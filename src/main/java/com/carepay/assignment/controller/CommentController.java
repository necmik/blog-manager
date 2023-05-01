package com.carepay.assignment.controller;

import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.carepay.assignment.payload.CommentDetails;
import com.carepay.assignment.payload.CommentInfo;
import com.carepay.assignment.payload.CreateCommentRequest;
import com.carepay.assignment.service.CommentService;

@RestController
@RequestMapping(path = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {
	 private final CommentService commentService;
	 
	 public CommentController(CommentService commentService) {
		 this.commentService = commentService;
	 }
	 
	 @GetMapping("{postId}/comments")
	 Page<CommentInfo> getComments(@PathVariable("postId") final Long postId, Pageable pageable) {
		 return commentService.getComments(postId, pageable);
	 }
	
	 @PostMapping("{postId}/comments")
	 @ResponseStatus(HttpStatus.CREATED)
	 CommentDetails createComment(@PathVariable("postId") final Long postId, @Valid @RequestBody CreateCommentRequest request) {
		 return commentService.createComment(postId, request);
	 }
	
	 @GetMapping("{postId}/comments/{id}")
	 CommentDetails getCommentDetails(@PathVariable("postId") final Long postId, @PathVariable("id") final Long id) {
		 return commentService.getCommentDetails(postId, id);
	 }
	
	 @DeleteMapping("{postId}/comments/{id}")
	 @ResponseStatus(HttpStatus.OK)
	 void deletePost(@PathVariable("postId") final Long postId, @PathVariable("id") final Long id) {
		 commentService.deleteComment(postId, id);
	 }
}
