package com.carepay.assignment;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.carepay.assignment.repository.PostRepository;
import com.carepay.assignment.service.ServiceUtils;

@Component
public class StaticContextInitializer {

	@Autowired
	private PostRepository postRepository;
	
	// Set postRepository component to be available in static methods inside ServiceUtils
	@PostConstruct
	public void init() {
		ServiceUtils.setPostRepository(postRepository);
	}
}
