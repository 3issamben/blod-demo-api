package se.seb.assignment.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import se.seb.assignment.blog.model.Post;
import se.seb.assignment.blog.repository.PostRepository;
import se.seb.assignment.blog.service.PostService;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository){
        super();
        this.postRepository = postRepository;
    }


    @Override
    public Post savePost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
    }

    @Override
    public void deletePost(long id) {
        postRepository.deleteById(id);
    }
}
