package com.nek.blogmanager.payload;

/**
 * Dto object to create a new post
 * @author necmikilic
 *
 */

public class CreatePostRequest {
    private String title;
    private String content;
    private String author;
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
    
	
}
