package se.seb.assignment.blog.service;

import se.seb.assignment.blog.model.Tag;

import java.util.List;

public interface TagService {
    List<Tag> getAllTags();
    Tag addTag(Tag tag);
    void deleteTag(Long id);
}
