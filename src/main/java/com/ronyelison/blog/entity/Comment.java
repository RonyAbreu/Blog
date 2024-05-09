package com.ronyelison.blog.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "tb_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne
    private User creator;
    @ManyToMany
    private List<Post> posts = new ArrayList<>();

    public Comment(String text, User creator) {
        this.text = text;
        this.creator = creator;
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

    public List<Post> getPosts() {
        return posts;
    }
}
