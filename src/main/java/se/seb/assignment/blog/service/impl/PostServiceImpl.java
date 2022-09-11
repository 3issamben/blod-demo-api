package se.seb.assignment.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import se.seb.assignment.blog.model.Post;
import se.seb.assignment.blog.model.Tag;
import se.seb.assignment.blog.repository.PostRepository;
import se.seb.assignment.blog.repository.TagRepository;
import se.seb.assignment.blog.service.PostService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private final PostRepository postRepository;

    @Autowired
    private final TagRepository tagRepository;

    public PostServiceImpl(PostRepository postRepository, TagRepository tagRepository){
        super();
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
    }


    @Override
    public Post savePost(Post post) {

        Set<Tag> tags = new HashSet<>();
        for (Tag tag: post.getTags()){
            Tag newTag = tagRepository.findByName(tag.getName());
            newTag = newTag == null ? tagRepository.save(new Tag(tag.getName())) : newTag;
            tags.add(newTag);
        }
        post.setTags(tags);
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

    @Override
    public List<Post> getPostsByTags(List<String> tags) {

        Set<Tag> foundTags = new HashSet<>();

        for (String tag : tags) {
            Tag foundTag = tagRepository.findByName(tag);
            if (foundTag != null) foundTags.add(foundTag);
        }
        return postRepository.findAllByTagsIn(foundTags);
    }

}
