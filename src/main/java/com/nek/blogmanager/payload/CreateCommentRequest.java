package com.nek.blogmanager.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 
 * @author necmikilic
 * Dto object to create a new comment
 */

public class CreateCommentRequest {
	
	@NotNull(message = "Comment cannot be null")
	@NotBlank(message = "Comment cannot be empty")
    @Size(min = 3, max = 100)
    private String comment;
	
    private String author;
    
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
}
