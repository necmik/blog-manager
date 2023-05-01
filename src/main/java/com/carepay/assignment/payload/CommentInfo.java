package com.carepay.assignment.payload;

import lombok.Data;

/**
 * Dto object to be returned to comment list service
 * @author necmikilic
 *
 */

@Data
public class CommentInfo {
	private Long id;
	private String author;
	private String comment;
}
