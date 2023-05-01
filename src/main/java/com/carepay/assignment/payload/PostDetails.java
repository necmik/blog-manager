package com.carepay.assignment.payload;

import com.carepay.assignment.domain.Post;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Dto object to be returned to post details service
 * @author necmikilic
 *
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PostDetails extends PostInfo {
    private String content;
    private String author;
    
    // Convert post entity to detail dto. Another alternative is using ModelMapper
    public PostDetails(Post post) {
    	this.setId(post.getId());
    	this.setTitle(post.getTitle());
    	this.setContent(post.getContent());
    	this.setAuthor(post.getAuthor());
    }
}
