package se.seb.assignment.blog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import se.seb.assignment.blog.model.Tag;
import se.seb.assignment.blog.repository.TagRepository;
import se.seb.assignment.blog.service.TagService;

import java.util.List;

public class TagServiceImpl implements TagService {

    @Autowired
    private final TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository){
        super();
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag addTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
}
