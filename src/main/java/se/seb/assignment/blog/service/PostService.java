package se.seb.assignment.blog.service;

import se.seb.assignment.blog.model.Post;

import java.util.List;

public interface PostService {

    Post savePost(Post post);
    List<Post> getAllPosts();
    void deletePost(long id);
}
