package se.seb.assignment.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.seb.assignment.blog.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>{
}
