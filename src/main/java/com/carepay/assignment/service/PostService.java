package com.carepay.assignment.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.carepay.assignment.domain.Post;

public interface PostService {
    Post createPost(@Valid Post post);
    
    Page<Post> getPosts(final Pageable pageable);
    
    Post getPostDetails(Long id);
    
    void deletePost(Long id);
}
