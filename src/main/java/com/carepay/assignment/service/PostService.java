package com.carepay.assignment.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.carepay.assignment.domain.Post;
import com.carepay.assignment.payload.CreatePostRequest;
import com.carepay.assignment.payload.PostDetails;
import com.carepay.assignment.payload.PostInfo;

public interface PostService {
    PostDetails createPost(@Valid CreatePostRequest createPostRequest);

    Page<PostInfo> getPosts(final Pageable pageable);
    Post getPostById(Long id);
    PostDetails getPostDetails(Long id);

    void deletePost(Long id);
}
