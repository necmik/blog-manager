package com.nek.blogmanager.service;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nek.blogmanager.domain.Post;
import com.nek.blogmanager.exception.HttpNotFoundErrorException;
import com.nek.blogmanager.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {
	
	Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);
	
	private final PostRepository postRepository;
	
	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
    @Override
    public Post createPost(@Valid Post post) {
        Post saved = postRepository.save(post);
        
        logger.info("Post {} saved with Id: {}", saved.getTitle(), saved.getId());
        
        return saved;
    }

    @Override
    public Page<Post> getPosts(Pageable pageable) {
    	return postRepository.findAll(pageable);
    }
    
    @Override
    public Post getPostDetails(Long id) {        
    	Optional<Post> optPost = postRepository.findById(id);
        if (optPost.isEmpty()) {
        	throw new HttpNotFoundErrorException("Post with id " + id + " does not exist");
        }      
        
        return optPost.get();
    }

    @Override
    public void deletePost(Long id) {
    	Optional<Post> optPost = postRepository.findById(id);
        if (optPost.isEmpty()) {
        	throw new HttpNotFoundErrorException("Post with id " + id + " does not exist");
        }
        
    	postRepository.delete(optPost.get());
    	logger.info("Post with Id {} deleted", id);
    }
}
