package com.carepay.assignment.service;

import java.util.Optional;
import com.carepay.assignment.domain.Post;
import com.carepay.assignment.exception.HttpNotFoundErrorException;
import com.carepay.assignment.repository.PostRepository;

public class ServiceUtils {

	private static PostRepository postRepository;
	
	public static void setPostRepository(PostRepository postRepository) {
		ServiceUtils.postRepository = postRepository;
	}
	
	private ServiceUtils() {	
	}
	
	public static Post getPostById(Long id) {
		Optional<Post> optPost = postRepository.findById(id);
        if (optPost.isEmpty()) {
        	throw new HttpNotFoundErrorException("Post with id " + id + " does not exist");
        }
        
        return optPost.get();
	}
}
