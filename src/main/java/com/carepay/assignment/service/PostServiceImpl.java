package com.carepay.assignment.service;

import java.util.Optional;
import java.util.function.Function;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.carepay.assignment.domain.Post;
import com.carepay.assignment.exception.HttpNotFoundErrorException;
import com.carepay.assignment.payload.CreatePostRequest;
import com.carepay.assignment.payload.PostDetails;
import com.carepay.assignment.payload.PostInfo;
import com.carepay.assignment.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {
	
	Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);
	
	private final PostRepository postRepository;
	
	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}
	
    @Override
    public PostDetails createPost(@Valid CreatePostRequest createPostRequest) {
    	Post post = Post.builder()
    			.title(createPostRequest.getTitle())
    			.content(createPostRequest.getContent())
    			.author(createPostRequest.getAuthor())
    			.build();
    	
        Post saved = postRepository.save(post);
        
        PostDetails postDetails = new PostDetails(saved);        
        logger.info("Post {} saved with Id: {}", saved.getTitle(), saved.getId());
        
        return postDetails;
    }

    @Override
    public Page<PostInfo> getPosts(Pageable pageable) {
    	Page<Post> posts = postRepository.findAll(pageable);
    	
    	Page<PostInfo> postInfoPage = posts.map(new Function<Post, PostInfo>() {
    	    @Override
    	    public PostInfo apply(Post post) {
    	    	PostInfo postInfo = new PostInfo();
    	    	postInfo.setId(post.getId());
    	    	postInfo.setTitle(post.getTitle());
    	        return postInfo;
    	    }
    	});
    	
        return postInfoPage;
    }

    @Override
    public PostDetails getPostDetails(Long id) {
        Post post = ServiceUtils.getPostById(id);
        
        PostDetails postDetails = new PostDetails(post);
        
        return postDetails;
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
