package com.carepay.assignment.payload;

import lombok.Data;

/**
 * Dto object to be returned to post list service
 * @author necmikilic
 *
 */

@Data
public class PostInfo {
    private Long id;
    private String title;
}
