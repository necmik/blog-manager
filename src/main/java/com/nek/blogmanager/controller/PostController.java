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

import com.nek.blogmanager.domain.Post;
import com.nek.blogmanager.payload.CreatePostRequest;
import com.nek.blogmanager.payload.PostDto;
import com.nek.blogmanager.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostController {
	private final PostService postService;
	private final ModelMapper modelMapper;

    public PostController(PostService postService, ModelMapper modelMapper) {
    	this.postService = postService;
    	this.modelMapper = modelMapper;
    }

    @GetMapping
    Page<PostDto> getPosts(Pageable pageable) {
    	Page<Post> posts = postService.getPosts(pageable);
    	
    	Page<PostDto> postInfoPage = new PageImpl<>(posts.stream()
    	          .map(this::convertToDto)
    	          .collect(Collectors.toList()));
    	
        return postInfoPage;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Post createPost(@Valid @RequestBody CreatePostRequest createPostRequest) {
    	Post post = convertToEntity(createPostRequest);
    	return postService.createPost(post);
    }

    @GetMapping("{id}")
    Post getPostDetails(@PathVariable("id") final Long id) {
    	return postService.getPostDetails(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    void deletePost(@PathVariable("id") final Long id) {
    	postService.deletePost(id);
    }
    
    private PostDto convertToDto(Post post) {
        PostDto postDto = modelMapper.map(post, PostDto.class);
        return postDto;
    }
    
    private Post convertToEntity(CreatePostRequest createPostRequest) {
        Post post = modelMapper.map(createPostRequest, Post.class);
     
        return post;
    }
}
