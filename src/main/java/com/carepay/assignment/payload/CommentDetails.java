package com.carepay.assignment.payload;

import com.carepay.assignment.domain.Comment;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Dto object to be returned to comment details service
 * @author necmikilic
 *
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class CommentDetails extends CommentInfo{
	private String comment;
	private Long postId; // Investigate: We may not need it because we already get the comment details by post id.
	
	// Convert comment to details dto. Another alternative is using ModelMapper
	public CommentDetails(Comment comment) {
		this.setId(comment.getId());
		this.setAuthor(comment.getAuthor());
		this.setComment(comment.getComment());
		this.setPostId(comment.getPost().getId());
	}
}
