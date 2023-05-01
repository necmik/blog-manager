package com.carepay.assignment.payload;

import lombok.Data;

/**
 * Dto object to create a new post
 * @author necmikilic
 *
 */

@Data
public class CreatePostRequest {
    private String title;
    private String content;
    private String author;
}
