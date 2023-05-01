package com.carepay.assignment.service;

import java.util.Optional;
import java.util.function.Function;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.carepay.assignment.domain.Comment;
import com.carepay.assignment.domain.Post;
import com.carepay.assignment.exception.HttpNotFoundErrorException;
import com.carepay.assignment.payload.CommentDetails;
import com.carepay.assignment.payload.CommentInfo;
import com.carepay.assignment.payload.CreateCommentRequest;
import com.carepay.assignment.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService{

	Logger logger = LoggerFactory.getLogger(CommentServiceImpl.class);
	
	private final CommentRepository commentRepository;
	
	public CommentServiceImpl(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}
	
	@Override
	public CommentDetails createComment(Long postId, @Valid CreateCommentRequest request) {
		Post post = ServiceUtils.getPostById(postId);
		
        Comment comment = Comment.builder()
        		.author(request.getAuthor())
        		.comment(request.getComment())
        		.post(post)
        		.build();
        
        Comment saved = commentRepository.save(comment);
        
        CommentDetails commentDetails = new CommentDetails(saved);
        logger.info("Comment saved with Id: {}", saved.getId());
        
        return commentDetails;
	}

	@Override
	public Page<CommentInfo> getComments(Long postId, Pageable pageable) {
		Post post = ServiceUtils.getPostById(postId);
		
		Page<Comment> comments = commentRepository.findAllByPost(post, pageable);
		Page<CommentInfo> commentInfoPage = comments.map(new Function<Comment, CommentInfo>() {
    	    @Override
    	    public CommentInfo apply(Comment comment) {
    	    	CommentInfo commentInfo = new CommentInfo();
    	    	commentInfo.setId(comment.getId());
    	    	commentInfo.setAuthor(comment.getAuthor());
    	    	commentInfo.setComment(comment.getComment());
    	        return commentInfo;
    	    }
    	});
		
		return commentInfoPage;
	}

	@Override
	public CommentDetails getCommentDetails(Long postId, Long id) {
		// For a basic security, get comment by postId and id instead of with only id
		Comment comment = getComment(postId, id);		
        CommentDetails commentDetails = new CommentDetails(comment);
        
        return commentDetails;
	}

	@Override
	public void deleteComment(Long postId, Long id) {
		// For a basic security, check if the comment belongs to the post specified
		Comment comment = getComment(postId, id);
		 
		commentRepository.delete(comment);
    	logger.info("Comment with Id {} deleted", id);
	}	
	
	private Comment getComment(Long postId, Long id) {
		Optional<Comment> comment = commentRepository.findById(id);
		if (comment.isEmpty() || !comment.get().getPost().getId().equals(postId)) {
			throw new HttpNotFoundErrorException("Comment with id " + id + " does not exist");
		}
		
        return comment.get();
	}

}
