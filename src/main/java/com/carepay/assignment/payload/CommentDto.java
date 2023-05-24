package com.carepay.assignment.payload;

/**
 * Dto object to be returned to comment list service
 * @author necmikilic
 *
 */

public class CommentDto {
	private Long id;
	private String author;
	private String comment;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
