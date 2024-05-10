package com.ronyelison.blog.controller;

import com.ronyelison.blog.dto.comment.CommentRequest;
import com.ronyelison.blog.dto.comment.CommentResponse;
import com.ronyelison.blog.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ap/v1/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentResponse> insertComment(@RequestBody @Valid CommentRequest commentRequest) {
        // TODO
    }
}
