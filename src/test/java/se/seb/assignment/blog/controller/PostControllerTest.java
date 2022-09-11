package se.seb.assignment.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import se.seb.assignment.blog.model.Post;
import se.seb.assignment.blog.model.Tag;
import se.seb.assignment.blog.service.PostService;
import se.seb.assignment.blog.service.TagService;

import java.time.Instant;
import java.util.*;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class PostControllerTest {

    @MockBean
    private PostService postService;

    @MockBean
    private TagService tagService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreatePost() throws Exception {

        Instant now = Instant.now();
        Set<Tag> tags = new HashSet<>();
        Post post = new Post(1, "TITLE", "CONTENT", now, tags);

        mockMvc.perform(post("/api/post").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(post)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    void shouldReturnListPosts() throws Exception {
        List<Post> posts = new ArrayList<>(
                Arrays.asList(new Post(1, "TITLE", "CONTENT", Instant.now(), null),
                        new Post(2, "TITLE 2", "CONTENT 2", Instant.now(), null),
                        new Post(3, "TITLE 3", "CONTENT 3", Instant.now(), null)
                ));

        when(postService.getAllPosts()).thenReturn(posts);
        mockMvc.perform(get("/api/post"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(posts.size()))
                .andDo(print());
    }

    @Test
    void shouldReturnPostWithTagFilter() throws Exception {


        Set<Tag> postTags = new HashSet<>(
                Arrays.asList(new Tag("hello"), new Tag("world"))
        );

        List<Post> posts = new ArrayList<>(
                Arrays.asList(new Post(1, "TITLE", "CONTENT", Instant.now(), postTags)));

        String tagValue = "hello";
        List<String> tags = new ArrayList<>();
        tags.add(tagValue);
        MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
        paramsMap.add("tags", tagValue);

        when(postService.getPostsByTags(tags)).thenReturn(posts);
        mockMvc.perform(get("/api/post/findByTags").params(paramsMap))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(posts.size()))
                .andDo(print());
    }

    @Test
    void shouldDeletePost() throws Exception {
        long id = 1L;

        doNothing().when(postService).deletePost(id);
        mockMvc.perform(delete("/api/post/{id}", id))
                .andExpect(status().isOk())
                .andDo(print());
    }


}