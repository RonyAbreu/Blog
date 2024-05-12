package com.ronyelison.blog.entity;

import com.ronyelison.blog.dto.comment.CommentResponse;
import jakarta.persistence.*;


@Entity(name = "tb_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne
    private User creator;
    @ManyToOne
    private Post post;

    public Comment() {
    }

    public Comment(String text, User creator, Post post) {
        this.text = text;
        this.creator = creator;
        this.post = post;
    }

    public CommentResponse entityToResponse() {
        return new CommentResponse(id, text,post.entityToResponse(), creator.entityToResponse());
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public User getCreator() {
        return creator;
    }

    public Post getPost() {
        return post;
    }
}
