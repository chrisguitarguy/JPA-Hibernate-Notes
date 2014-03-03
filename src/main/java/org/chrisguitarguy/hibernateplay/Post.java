package org.chrisguitarguy.hibernateplay;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="posts", indexes={
    @Index(name="post_author_idx", columnList="author_id"),
    @Index(name="post_slug_idx", columnList="slug", unique=true) // same thing as the UniqueConstraint below?
}, uniqueConstraints={
    @UniqueConstraint(name="post_slug_uniq", columnNames={"slug"})
})
class Post {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Long id;

    @Lob
    @Column(name="content")
    private String content;

    @Column(name="slug")
    private String slug;

    @ManyToOne(optional=false)
    @JoinColumn(name="author_id", nullable=false)
    private Author author;

    public Post(String content, String slug, Author author) {
        this.content = content;
        this.slug = slug;
        this.author = author;
    }

    public Post() {
        // noop
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSlug() {
        return this.slug;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Author getAuthor(Author author) {
        return author;
    }
}
