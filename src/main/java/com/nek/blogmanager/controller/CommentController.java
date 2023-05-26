package com.nek.blogmanager.controller;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

import com.nek.blogmanager.domain.Comment;
import com.nek.blogmanager.payload.CommentDto;
import com.nek.blogmanager.payload.CreateCommentRequest;
import com.nek.blogmanager.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
public class CommentController {
	 private final CommentService commentService;
	 private final ModelMapper modelMapper;
	 
	 public CommentController(CommentService commentService, ModelMapper modelMapper) {
		 this.commentService = commentService;
		 this.modelMapper = modelMapper;
	 }
	 
	 @GetMapping("{postId}/comments")
	 Page<CommentDto> getComments(@PathVariable("postId") final Long postId, Pageable pageable) {
		 Page<Comment> comments = commentService.getComments(postId, pageable);
	    	
		 Page<CommentDto> commentInfoPage = new PageImpl<>(comments.stream()
    	          .map(this::convertToDto)	
    	          .collect(Collectors.toList()));
	    	
		 return commentInfoPage;
	 }
	
	 @PostMapping("{postId}/comments")
	 @ResponseStatus(HttpStatus.CREATED)
	 Comment createComment(@PathVariable("postId") final Long postId, 
			 @Valid @RequestBody CreateCommentRequest request) {
		 Comment comment = convertToEntity(request);
		 return commentService.createComment(postId, comment);
	 }
	
	 @GetMapping("{postId}/comments/{id}")
	 Comment getCommentDetails(@PathVariable("postId") final Long postId, @PathVariable("id") final Long id) {
		 return commentService.getCommentDetails(postId, id);
	 }
	
	 @DeleteMapping("{postId}/comments/{id}")
	 @ResponseStatus(HttpStatus.OK)
	 void deletePost(@PathVariable("postId") final Long postId, @PathVariable("id") final Long id) {
		 commentService.deleteComment(postId, id);
	 }
	 
	 private CommentDto convertToDto(Comment comment) {
		 CommentDto commentDto = modelMapper.map(comment, CommentDto.class);
		 return commentDto;
	 }
	 
	 private Comment convertToEntity(CreateCommentRequest createCommentRequest) {
		 Comment comment = modelMapper.map(createCommentRequest, Comment.class);
	     
		 return comment;
	 }
}
