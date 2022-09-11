package se.seb.assignment.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.seb.assignment.blog.model.Post;
import se.seb.assignment.blog.model.Tag;

import java.util.List;
import java.util.Set;

public interface PostRepository extends JpaRepository<Post, Long>{

    List<Post> findAllByTagsIn(Set<Tag> tags);

}
