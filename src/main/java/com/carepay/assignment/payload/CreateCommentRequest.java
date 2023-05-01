package com.carepay.assignment.payload;

import lombok.Data;

/**
 * 
 * @author necmikilic
 * Dto object to create a new comment
 */

@Data
public class CreateCommentRequest {
    private String comment;
    private String author;
    
}
