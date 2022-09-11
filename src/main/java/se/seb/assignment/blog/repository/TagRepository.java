package se.seb.assignment.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.seb.assignment.blog.model.Tag;


public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String name);
}
