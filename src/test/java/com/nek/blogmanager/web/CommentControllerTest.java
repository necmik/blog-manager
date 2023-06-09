package com.nek.blogmanager.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import com.nek.blogmanager.IntegrationTest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CommentControllerTest extends IntegrationTest {
    @Test
    void listComments() throws Exception {
        mvc.perform((get("/posts/{postId}/comments", 2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[*].comment", hasItem("Kilroy was here")));
    }

    @Test
    void getCommentDetails() throws Exception {
        mvc.perform((get("/posts/{postId}/comments/{commentId}", 2, 2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.comment", equalTo("Kilroy was here")));
    }

    @Test
    void getNonExistingComment() throws Exception {
        mvc.perform((get("/posts/{postId}/comments/{commentId}", 2, 6666)))
                .andExpect(status().isNotFound());
    }

    @Test
    void createComment() throws Exception {
        mvc.perform(
                post("/posts/{postId}/comments", 2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"comment\":\"my comment\", \"author\":\"new author\"}")
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", greaterThan(0)));
    }

    @Test
    void commentTooLong() throws Exception {
        mvc.perform(
                post("/posts/{postId}/comments", 2)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"comment\":\"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx\"}")
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    void commentMissing() throws Exception {
        mvc.perform(
                post("/posts/{postId}/comments", 3)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")
        )
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteComment() throws Exception {
        mvc.perform(
                delete("/posts/{postId}/comments/{commentId}", 2, 2)
        )
                .andExpect(status().isOk());
    }

    @Test
    void deleteNonExistingComment() throws Exception {
        mvc.perform(
                delete("/posts/{postId}/comments/{commentId}", 2, 9876)
        )
                .andExpect(status().isNotFound());
    }
}
