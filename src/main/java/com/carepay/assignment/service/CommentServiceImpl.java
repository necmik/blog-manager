package com.carepay.assignment.service;

import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.carepay.assignment.domain.Comment;
import com.carepay.assignment.domain.Post;
import com.carepay.assignment.exception.HttpNotFoundErrorException;
import com.carepay.assignment.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService{

	Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);
	
	private final CommentRepository commentRepository;
	private final PostService postService;
	
	public CommentServiceImpl(CommentRepository commentRepository, PostService postService) {
		this.commentRepository = commentRepository;
		this.postService = postService;
	}
	
	@Override
	public Comment createComment(Long postId, @Valid Comment comment) {
		Post post = postService.getPostDetails(postId);
		comment.setPost(post);
		
        Comment saved = commentRepository.save(comment);
        
        logger.info("Comment saved with Id: {}", saved.getId());
        
        return saved;
	}

	@Override
	public Page<Comment> getComments(Long postId, Pageable pageable) {
		Post post = postService.getPostDetails(postId);
		
		Page<Comment> comments = commentRepository.findAllByPost(post, pageable);
		
		return comments;
	}

	@Override
	public Comment getCommentDetails(Long postId, Long id) {
        return getCommentByPostId(postId, id);
	}

	@Override
	public void deleteComment(Long postId, Long id) {
		Comment comment = getCommentByPostId(postId, id);
		 
		commentRepository.delete(comment);
    	logger.info("Comment with Id {} deleted", id);
	}	
	
	private Comment getCommentByPostId(Long postId, Long commentId) {
		Post post = postService.getPostDetails(postId);
		Set<Comment> comments = post.getComments();
		Optional<Comment> comment = comments.stream()
				.filter((Comment c) -> c.getId().equals(commentId))
				.findFirst();
		
		if (!comment.isPresent()) {
			throw new HttpNotFoundErrorException("Comment with id " + commentId + " does not exist");
		}
		
        return comment.get();
	}
}
