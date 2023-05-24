package com.nek.blogmanager.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nek.blogmanager.domain.Post;

public interface PostService {
    Post createPost(@Valid Post post);
    
    Page<Post> getPosts(final Pageable pageable);
    
    Post getPostDetails(Long id);
    
    void deletePost(Long id);
}
