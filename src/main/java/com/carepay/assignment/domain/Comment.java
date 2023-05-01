package com.carepay.assignment.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class represents the Comments
 * TODO : Add date property/column and sort the comment lists by date descending
 * @author necmikilic
 *
 */

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Comment cannot be null")
	@NotBlank(message = "Comment cannot be empty")
    @Size(min = 3, max = 100)
    private String comment;
    
    private String author; // TODO: Should be another entity
    
    @ManyToOne
    @JoinColumn(name="post_id", nullable=false)
    private Post post;
}
