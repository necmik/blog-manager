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

import com.carepay.assignment.payload.CreatePostRequest;
import com.carepay.assignment.payload.PostDetails;
import com.carepay.assignment.payload.PostInfo;
import com.carepay.assignment.service.PostService;

@RestController
@RequestMapping(path = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {
	private final PostService postService;

    public PostController(PostService postService) {
    	this.postService = postService;
    }

    @GetMapping
    Page<PostInfo> getPosts(Pageable pageable) {
    	return postService.getPosts(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    PostDetails createPost(@Valid @RequestBody CreatePostRequest createPostRequest) {
    	return postService.createPost(createPostRequest);
    }

    @GetMapping("{id}")
    PostDetails getPostDetails(@PathVariable("id") final Long id) {
    	return postService.getPostDetails(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    void deletePost(@PathVariable("id") final Long id) {
    	postService.deletePost(id);
    }
}
