package com.carepay.assignment.payload;

/**
 * 
 * @author necmikilic
 * Dto object to create a new comment
 */

public class CreateCommentRequest {
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
