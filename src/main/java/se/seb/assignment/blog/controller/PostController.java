package se.seb.assignment.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.seb.assignment.blog.model.Post;
import se.seb.assignment.blog.service.PostService;

import java.util.List;

@RequestMapping("api/blog")
@RestController
public class PostController {

    @Autowired
    private PostService postService;

    // Add new blog entry
    @PostMapping
    public ResponseEntity<Post> savePost(@RequestBody Post post){
        return new ResponseEntity<Post>(postService.savePost(post), HttpStatus.CREATED);
    }

    // Get all blog entries
    @GetMapping
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
    }

    // Get all blog entries by tag(s)
    @GetMapping("/findByTags")
    public List<Post> getAllPostsByTags(@RequestParam(value = "tags") List<String> tags){
        return postService.getPostsByTags(tags);
    }

    // Delete post by ID
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") long id){
        postService.deletePost(id);
        return new ResponseEntity<String>("Post deleted successfully", HttpStatus.OK);
    }

}
