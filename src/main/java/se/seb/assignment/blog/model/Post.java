package se.seb.assignment.blog.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Instant;

@Table(name = "post")
@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 1, max = 100)
    @NotNull
    // Title is combination of letters and numbers
    @Pattern(regexp = "^[A-Z0-9 ]+$")
    @Column(name = "title")
    private String title;

    @Size(min = 1, max = 1000)
    @NotNull
    @Column(name = "content")
    private String content;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;


    public Post(){}

    public Post(String title, String content, Instant createdAt) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    // TODO Add TAGS

}
