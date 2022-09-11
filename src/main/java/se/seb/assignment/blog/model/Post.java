package se.seb.assignment.blog.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Table(name = "post")
@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    @Size(min = 1, max = 100, message = "Title cannot be more than 100 chars")
    @NotNull
    // Title is combination of letters and numbers
    @Pattern(regexp = "^[A-Z0-9 ]+$", message = "Title can contain only capital case letters and numbers")
    @Column(name = "title")
    private String title;

    @Size(min = 1, max = 1000, message = "The blog entry cannot exceed 1000 chars")
    @NotNull
    @Column(name = "content")
    private String content;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
            })
    @JoinTable(name = "post_tags", joinColumns = @JoinColumn(name = "post_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    private Set<Tag> tags = new HashSet<>();

    public Post(){}

    public Post(String title, String content, Instant createdAt) {
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Post(long id, String title, String content, Instant createdAt, Set<Tag> tags) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.tags = tags;
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

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }


}
