package com.nek.blogmanager.payload;

/**
 * Dto object to be returned to post list service
 * @author necmikilic
 *
 */

public class PostDto {
    private Long id;
    private String title;
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
