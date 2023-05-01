package com.carepay.assignment.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class represents the Posts
 * TODO : Add date property/column and sort the post lists by date descending
 * @author necmikilic
 *
 */

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Title cannot be null")
	@NotBlank(message = "Title cannot be empty")
    @Size(min = 3, max = 50)
    private String title;
    
    @NotNull(message = "Content cannot be null")
	@NotEmpty(message = "Content cannot be empty")
    private String content;
    
    private String author; // TODO: Should be another entity
    
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final Set<Comment> comments = new HashSet<>();
}
